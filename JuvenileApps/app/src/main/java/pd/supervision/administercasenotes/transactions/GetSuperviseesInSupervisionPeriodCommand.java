//Source file:
//C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\GetSuperviseesInSupervisionPeriodCommand.java

package pd.supervision.administercasenotes.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.administercasenotes.GetSuperviseesInSupervisionPeriodEvent;
import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.party.GetPartyListEvent;
import messaging.supervisionorder.GetActivePriorSupervisionPeriodsDynamicEvent;
import messaging.supervisionorder.GetMostRecentInactiveOrderForCaseEvent;
import messaging.supervisionorder.GetOrdersForSupervisionPeriodIdsEvent;
import messaging.supervisionorder.reply.SuperviseeListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;
import pd.contact.user.UserProfile;
import pd.supervision.Court;
import pd.supervision.administercasenotes.SpnCasenotesHandler;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderPeriodStaff;
import pd.supervision.supervisionorder.SupervisionPeriod;

public class GetSuperviseesInSupervisionPeriodCommand implements ICommand {

	/**
	 * This command returns a list of Supervisees (Party) that match the
	 * criteria in the passed event.
	 * 
	 * @roseuid 44F462BA02BB
	 */
	public GetSuperviseesInSupervisionPeriodCommand() {

	}

	/**
	 * Returns a list of Supervisees (Party) that match the criteria in the
	 * passed event.
	 * 
	 * @param event
	 * @roseuid 44EE1143016D
	 */
	public void execute(IEvent event) {
		//Retrieve parties matching name from M204
		GetSuperviseesInSupervisionPeriodEvent inEvent = (GetSuperviseesInSupervisionPeriodEvent) event;		

		Iterator parties = this.findParties(inEvent);
		if(parties == null || !parties.hasNext()){
			return;
		}
		Map spnMap = new HashMap();
		PartyListResponseEvent party = null;
		Map courtMap = this.getCourts();

		while (parties.hasNext()) {
			party = (PartyListResponseEvent) parties.next();
			String spn = SpnCasenotesHandler.padSpn(party.getSpn());
			if (!spnMap.containsKey(spn)){
				spnMap.put(spn, party);
			}
		}
		
		StringBuffer defendantIds = new StringBuffer();
		Iterator defendantIdsIter = spnMap.keySet().iterator();
		while(defendantIdsIter.hasNext()){
			String defendantId = (String) defendantIdsIter.next();
			defendantIds.append("'");
			defendantIds.append(defendantId);
			defendantIds.append("'");
			if(defendantIdsIter.hasNext()){
				defendantIds.append(",");
			}
		}
		
		if(defendantIds.toString().length() < 1){
			return;
		}
		
		GetActivePriorSupervisionPeriodsDynamicEvent goEvent = new GetActivePriorSupervisionPeriodsDynamicEvent();
		goEvent.setAgencyId(pd.security.PDSecurityHelper.getUserAgencyId());
		goEvent.setDefendantIds(defendantIds.toString());
		goEvent.setActive(inEvent.isActiveSupervisionPeriod());		
		Iterator spIter = SupervisionPeriod.findAll(goEvent);
		Map defendantIdsMap = new HashMap();
		while(spIter.hasNext()){
			SupervisionPeriod sp = (SupervisionPeriod) spIter.next();
			if(!defendantIdsMap.containsKey(sp.getDefendantId() + sp.getAgencyId())){
				List list = new ArrayList();
				list.add(sp.getOID());
				defendantIdsMap.put(sp.getDefendantId() + sp.getAgencyId(), list);
			}else{
				List list = (List) defendantIdsMap.get(sp.getDefendantId() + sp.getAgencyId());
				list.add(sp.getOID());
				defendantIdsMap.put(sp.getDefendantId() + sp.getAgencyId(), list);
			}
		}	
		
		parties = spnMap.values().iterator();
		while (parties.hasNext()) {
			party = (PartyListResponseEvent) parties.next();
			String spn = SpnCasenotesHandler.padSpn(party.getSpn());
			String agencyId = inEvent.getUserAgencyId();
	
			List supPeriods = (List) defendantIdsMap.get(spn + agencyId);

			if (supPeriods != null && !supPeriods.isEmpty()) {
				GetOrdersForSupervisionPeriodIdsEvent getOrdersEvent = new GetOrdersForSupervisionPeriodIdsEvent();
				getOrdersEvent.setPeriodIds(supPeriods);
				Iterator iter = SupervisionOrderPeriodStaff.findAll(getOrdersEvent);

				if (iter != null && iter.hasNext()) {
					SupervisionOrderPeriodStaff order = null;
					//SupervisionOrder inactiveOrder = null;
					Map caseMap = new HashMap();

					while (iter.hasNext()) {
						order = (SupervisionOrderPeriodStaff) iter.next();
						if (inEvent.isActiveSupervisionPeriod()) {
							//Need active order for case when searching in
							// active supervision period.
							if (PDCodeTableConstants.STATUS_ACTIVE_ID.equals(order.getOrderStatusId())) {
								caseMap.put(order.getCriminalCaseId(), order.getCriminalCaseId());
								this.postResponseEvent(party, order, courtMap);
							} else {
								continue;
							}
						} else if (caseMap.get(order.getCriminalCaseId()) != null) {
							continue; //already processed this case in this
							// non-active supervision period.
						} else { //process case in this non-active supervision
							// period
							caseMap.put(order.getCriminalCaseId(), order.getCriminalCaseId());
							//inactiveOrder = this.getMostCurrentInactiveOrderForCase(agencyId, order.getCriminalCaseId());
							this.postResponseEvent(party, order, courtMap);
						}
					}
				}
			}
		}
	}

	private Map getCourts() {
		Iterator iter = Court.findAll();
		List courtList = CollectionUtil.iteratorToList(iter);
		Court court = null;
		Map courtMap = new HashMap();
		for (int i = 0; i < courtList.size(); i++) {
			court = (Court) courtList.get(i);
			courtMap.put(court.getOID(), court);
		}
		return courtMap;
	}

	private Iterator findParties(GetSuperviseesInSupervisionPeriodEvent inEvent) {

		GetPartyListEvent request = new GetPartyListEvent();
		request.setBirthDate(inEvent.getDateOfBirth());
		request.setCjisNum(inEvent.getCjisNum());
		request.setDriverLicenseNum(inEvent.getDriverLicenseNum());
		request.setDriverLicenseStateId(inEvent.getDriverLicenseStateId());
		request.setFbiNum(inEvent.getFbiNum());
	    request.setFirstName(inEvent.getFirstName());
	    request.setMiddleName(inEvent.getMiddleName());
	    request.setLastName(inEvent.getLastName());
		request.setRaceId(inEvent.getRaceId());
		request.setSexId(inEvent.getSexId());
		request.setSidNum(inEvent.getSidNum());
		request.setSsn(inEvent.getSsn());
		MetaDataResponseEvent mdre = Party.findMeta(request);
		
		Party party = null;
		PartyListResponseEvent re = null;
		List reList = new ArrayList();

		if (mdre.getCount() > PDConstants.SEARCH_LIMIT){
			CountInfoMessage infoEvent = new CountInfoMessage();
			infoEvent.setCount(mdre.getCount());  
			MessageUtil.postReply(infoEvent);
		} else {
			List partyList = CollectionUtil.iteratorToList(Party.findAll(request));

			for (int i = 0; i < partyList.size(); i++) {
				party = (Party) partyList.get(i);
				re = PDPartyHelper.getPartyLightResponseEvent(party);
				reList.add(re);
			}
		}
		return reList.iterator();
		
	}

	/**
	 * @param party
	 * @param order
	 */
	private void postResponseEvent(PartyListResponseEvent party, SupervisionOrderPeriodStaff order, Map courtMap) {
		SuperviseeListResponseEvent response = new SuperviseeListResponseEvent(party);
		response.setName(party.getName());
		Name aName = new Name();
    	if(order.getUserProfileId() != null){
	    	UserProfile userProfile = UserProfile.find(order.getUserProfileId());
	    	if(userProfile != null && userProfile.getLastName() != null){	    		
                aName.setFirstName(userProfile.getFirstName());
                aName.setMiddleName(userProfile.getMiddleName());
                aName.setLastName(userProfile.getLastName());	            
	        } else {
	            aName.setLastName("No Officer Assigned");
	        }
	    }else{
	    	aName.setLastName("No Officer Assigned");
	    }
	    response.setOfficerName(aName);
		
		Court currCourt = (Court) courtMap.get(order.getCurrentCourtId());
		if (currCourt != null){
			response.setCourtNum(currCourt.getCourtNumber());
		}
		response.setConnectionId("DEF");
		response.setOid(party.getOid());
		response.setSupervisionPeriodId(new StringBuffer("").append(order.getSprPeriodId()).toString());
		MessageUtil.postReply(response);
	}
	
	private SupervisionOrder getMostCurrentInactiveOrderForCase(String agencyId, String caseId) {
		GetMostRecentInactiveOrderForCaseEvent theEvent = new GetMostRecentInactiveOrderForCaseEvent();
		theEvent.setAgencyId(agencyId);
		theEvent.setCaseId(caseId);
		theEvent.setOrderStatusId(PDCodeTableConstants.STATUS_INACTIVE_ID);
		Iterator iter = SupervisionOrder.findAll(theEvent);
		SupervisionOrder order = null;
		Date prevDate = null;
		if (iter != null && iter.hasNext()) {
			while (iter.hasNext()) {
				//todo: check for most recent date.
				order = (SupervisionOrder) iter.next();
				if (prevDate == null
						|| (order.getInactivationDate() != null
								&& (order.getInactivationDate().after(prevDate)) || order
								.getInactivationDate().equals(prevDate))) {
					order = SupervisionOrder.find(order.getOID().toString());
					prevDate = order.getInactivationDate();
				}
			}
		}
		return order;
	}

	/**
	 * @param updateObject
	 * @roseuid 44F462BA02CF
	 */
	public void update(Object updateObject) {

	}
	
	/**
	 * @param event
	 * @roseuid 44EE1143016F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 44EE11430176
	 */
	public void onUnregister(IEvent event) {

	}
}
