// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\GetSuperviseesInSupervisionPeriodCommand.java

package pd.supervision.administercasenotes.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.administercasenotes.GetSuperviseeInSupervisionPeriodEvent;
import messaging.administercasenotes.reply.SupervisionPeriodNotFoundEvent;
import messaging.administercasenotes.to.CasenoteCaseTO;
import messaging.domintf.contact.party.ISupervisee;
import messaging.party.GetPartyDataEvent;
import messaging.supervisionorder.GetPriorSupervisionPeriodsEvent;
import messaging.supervisionorder.GetSupervisionOrdersForSupervisionPeriodEvent;
import messaging.supervisionorder.reply.SuperviseeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.CollectionUtil;
import naming.PDConstants;
import naming.PartyControllerServiceNames;
import pd.common.util.NameUtil;
import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;
import pd.contact.user.UserProfile;
import pd.criminalcase.CriminalCase;
import pd.km.util.Name;
import pd.supervision.Court;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercasenotes.CasenoteHelper;
import pd.supervision.administercasenotes.SpnCasenotesHandler;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

public class GetSuperviseeInSupervisionPeriodCommand implements ICommand {

	/**
	 * @roseuid 44F462BA02BB
	 */
	public GetSuperviseeInSupervisionPeriodCommand() {

	}

	/**
	 * @param event
	 * @roseuid 44EE1143016D
	 */
	public void execute(IEvent event) {
		GetSuperviseeInSupervisionPeriodEvent inEvent = (GetSuperviseeInSupervisionPeriodEvent) event;

		// Lookup defendant by SPN
		String spn = inEvent.getSpn();
		// get the Supervisee (party) info
		ISupervisee supervisee = getSuperviseeBySPN(spn);
		if (supervisee != null) {
			// based on which supervision period is being sought, the active or
			// some prior period,
			// we must get the additional information
			SupervisionPeriod supPeriod = null;

			Collection supervisionPeriods = new ArrayList();
			if (inEvent.isActiveSupervisionPeriod()) {
				//supervision period can be null if there supervisee is no
				// longer
				// being actisupervised.
				supPeriod = SupervisionPeriod.findActiveSupervisionPeriod(
						SpnCasenotesHandler.padSpn(spn), inEvent
								.getUserAgencyId());
				if (supPeriod != null) {
					supervisionPeriods.add(supPeriod);
				} else {
				}
			} else {
				//get prior supervision periods
				supervisionPeriods = this.getPriorSupervisionPeriods(spn,
						inEvent.getUserAgencyId());
			}
			if (supervisionPeriods.size() == 0) {
				EventManager.getSharedInstance(EventManager.REPLY).postEvent(
						new SupervisionPeriodNotFoundEvent());
			} else {
				Iterator iter = supervisionPeriods.iterator();
				while (iter.hasNext()) {
					supPeriod = (SupervisionPeriod) iter.next();
					// Now, find all supervision orders in this period
					this.getSupervisionOrdersForSupervisionPeriod(supervisee,
							supPeriod);

					// post the PartyResponseEvent
					EventManager.getSharedInstance(EventManager.REPLY)
							.postEvent((SuperviseeResponseEvent) supervisee);
				}
			}
		} else
		// Supervisee not found
		{
			// post an empty ResponseEvent to indicate that the Supervisee was
			// not found
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(
					new ResponseEvent());
			return;
		}
	}

	private Collection getPriorSupervisionPeriods(String spn, String agencyId) {
		GetPriorSupervisionPeriodsEvent getPriorSupPeriodsEvent = new GetPriorSupervisionPeriodsEvent();
		if (spn.length() < 8) {
			StringBuffer sb = new StringBuffer(spn);
			while (sb.length() < 8) {
				sb.insert(0, "0");
			}
			getPriorSupPeriodsEvent.setSpn(sb.toString());
		} else {
		    getPriorSupPeriodsEvent.setSpn(spn);
		}
		getPriorSupPeriodsEvent.setAgencyId(agencyId);
		Collection supPeriods = new ArrayList();
		Iterator iter = SupervisionPeriod.findAll(getPriorSupPeriodsEvent);
		if (iter != null && iter.hasNext()) {
			while (iter.hasNext()) {
				SupervisionPeriod supPeriod = (SupervisionPeriod) iter.next();
				supPeriods.add(supPeriod);
			}
		}
		return supPeriods;
	}

	/**
	 * Retrieves supervision orders for a supervisee within a given supervision
	 * period.
	 * 
	 * @param supPeriod
	 */
	private void getSupervisionOrdersForSupervisionPeriod(
			ISupervisee supervisee, SupervisionPeriod supPeriod) {

		CasenoteHelper helper = CasenoteHelper.getInstance();
		
		GetSupervisionOrdersForSupervisionPeriodEvent periodEvent = new GetSupervisionOrdersForSupervisionPeriodEvent();
		periodEvent.setSupervisionPeriodId("" + supPeriod.getOID());
		List supOrdersList = CollectionUtil.iteratorToList( new Home().findAll(periodEvent,
				SupervisionOrder.class ));
		boolean nameNeeded = true;
		if (supervisee.getLastName() != null && !supervisee.getLastName().equals(PDConstants.BLANK)){
		    nameNeeded = false;
		} 
		CriminalCase theCase = null;
		Map caseMap = new HashMap();
		
		//sort Cases by caseid and statuscd ascending order
		Collections.sort((List)supOrdersList, GetSuperviseeInSupervisionPeriodCommand.supervisionOrderComparator );
		String criminalCaseId = "";
		Iterator <SupervisionOrder> sortedOrderIter = supOrdersList.iterator();
		
		while (sortedOrderIter.hasNext()) {
			SupervisionOrder supervisionOrder = (SupervisionOrder) sortedOrderIter
					.next();
			criminalCaseId = supervisionOrder.getCriminalCaseId();
			//A case may have multiple orders with the same supervision period.
			if ( caseMap.get( criminalCaseId ) != null ) {
				continue;
			} else {
				caseMap.put(criminalCaseId, supervisionOrder);
			}
			CasenoteCaseTO caseInfo = new CasenoteCaseTO();
			theCase = supervisionOrder.getCriminalCase();
			if (theCase != null) {
				if (nameNeeded) {
					String defendantName = theCase.getDefendantName();
					if (defendantName != null && !defendantName.equals("")) {
						Name defName = NameUtil
								.getNameFromString(defendantName);
						supervisee.setLastName(defName.getLastName());
						supervisee.setFirstName(defName.getFirstName());
						supervisee.setMiddleName(defName.getMiddleName());
					}
					nameNeeded = false;
				}
				if (supervisee.getOfficerName() == null){
				    supervisee.setOfficerName( helper.getProbationOfficerNameAsString(theCase.getProbationOfficerId()));
				}
				if (supervisee.getProbationOfficerId() == null){
				    supervisee.setProbationOfficerId(theCase.getProbationOfficerId());
				}
				caseInfo.setCaseNum(theCase.getCaseNum());
				caseInfo.setCdi(theCase.getCourtDivisionId());
				caseInfo.setSupOrderId((String)supervisionOrder.getOID());
			}
			// TODO: Set the actual values for these attribute when they
			// become available.
			//supervisee.setNextContactDate(null);
			//supervisee.setContactMethod("");
			//supervisee.setContactReason("");
			//supervisee.isInComplianceInd(true);

			// RRY fix null pointer for Mary
			Court currentCourt = supervisionOrder.getCurrentCourt();
			String substrCourt = "";
			if ( currentCourt != null ){
				
				caseInfo.setCourtNum(currentCourt.getCourtNumber());
			}else{
				String courtId = supervisionOrder.getCurrentCourtId();
				if ( courtId.length() > 5 ){
					substrCourt = courtId.substring(4);
					caseInfo.setCourtNum( substrCourt );
				}

			}
			caseInfo.setOffenseCodeId(theCase.getOffenseCodeId());
			caseInfo.setSupervisionPeriodId("" + supPeriod.getOID());
			caseInfo.setSupervisionPeriodBeginDate(supPeriod
					.getSupervisionBeginDate());
			caseInfo.setSupervisionPeriodEndDate(supPeriod
					.getSupervisionEndDate());
			caseInfo.setSuperviseeName(theCase.getDefendantName());
			
			//Replaced with info from supervision order per defect JIMS200065767
			//Calculate case supervision period.
			/* spre = SupervisionOrderHelper.getCaseSupervisionPeriod(
					supervisionOrder.getAgencyId(), supervisionOrder
							.getCriminalCaseId());
			if (spre != null) {
				caseInfo.setCaseSupervisionPeriodBeginDate(spre
						.getSupervisionBeginDate());
				caseInfo.setCaseSupervisionPeriodEndDate(spre
						.getSupervisionEndDate());
			}*/
			caseInfo.setCaseSupervisionPeriodBeginDate(supervisionOrder.getCaseSupervisionBeginDate());
			caseInfo.setCaseSupervisionPeriodEndDate(supervisionOrder.getCaseSupervisionEndDate());
			supervisee.insertCase(caseInfo);
		}
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

	/**
	 * @param updateObject
	 * @roseuid 44F462BA02CF
	 */
	public void update(Object updateObject) {

	}

	private ISupervisee getSuperviseeBySPN(String spn) {
		/*
		 * This will not satisfy the requirements for this lookup. See
		 * Requirements below:
		 * 
		 * The name, court and connection will be displayed for the most recent
		 * supervision case. Note that this is not necessarily the latest name
		 * on the party record. For example, the party can be the defendant on a
		 * new case where there is no supervision and has provided a different
		 * name.
		 */
		GetPartyDataEvent getEvent = (GetPartyDataEvent) EventFactory
				.getInstance(PartyControllerServiceNames.GETPARTYDATA);

		getEvent.setSpn(spn);
		// indicate that we want the current name for Party
		getEvent.setCurrentNameInd("Y");
		ISupervisee superviseeInfo = null;

		Party party = Party.find(getEvent);
		if (party != null) {
			superviseeInfo = PDPartyHelper.getSuperviseeLightDetailsResponseEvent(party);
			superviseeInfo.setConnectionId("DEF");
			this.getSuperviseeInfoFromSupervisee(superviseeInfo);
			return superviseeInfo;
		}
		return superviseeInfo;
	}

    /**
     * @param spn
     * @return
     */
    private void getSuperviseeInfoFromSupervisee(ISupervisee superviseeInfo) {
        StringBuffer paddedSpn = new StringBuffer(superviseeInfo.getSpn());
        while (paddedSpn.length() < 8){
            paddedSpn.insert(0,"0");
        }
        Supervisee supervisee = Supervisee.findByDefendantId(paddedSpn.toString());
        if (supervisee != null){
            if (supervisee.getAssignedProgramUnitId() != null){
                Organization org = Organization.find(supervisee.getAssignedProgramUnitId());
                if (org != null){
                    superviseeInfo.setUnit(org.getDescription());
                }
            }
            CSCDStaffPosition staffPos = CSCDStaffPosition.find(supervisee.getAssignedStaffPositionId());
            if (staffPos != null && staffPos.getUserProfileId() != null){
                Name name = new Name();
                UserProfile userProfile = staffPos.getUserProfile();
                name.setFirstName(userProfile.getFirstName());
                name.setMiddleName(userProfile.getMiddleName());
                name.setLastName(userProfile.getLastName());
                superviseeInfo.setOfficerName(name.getFormattedName());
                superviseeInfo.setProbationOfficerId(staffPos.getProbationOfficerInd());
                if(staffPos.getPositionName() != null && !"".equals(staffPos.getPositionName())){
                	superviseeInfo.setOfficerPosition(" | " + staffPos.getPositionName());
                }else{
                	superviseeInfo.setOfficerPosition("");
                }
            } else {
                superviseeInfo.setOfficerName("");
            }
        } else {
            superviseeInfo.setOfficerName(PDConstants.BLANK);
        }
    }

	public static Comparator supervisionOrderComparator = new Comparator() {
		
		public int compare( Object firstCase, Object nextCase ) {
		  int result = 0;
		  
		  StringBuffer caseSb1 = new StringBuffer();
		  StringBuffer caseSb2 = new StringBuffer();
		  caseSb1.append( ((SupervisionOrder)firstCase).getCriminalCaseId() ).append(((SupervisionOrder)firstCase).getOrderStatusId() );
		  caseSb2.append( ((SupervisionOrder)nextCase).getCriminalCaseId() ).append(((SupervisionOrder)nextCase).getOrderStatusId() );
		  String caseId1 = caseSb1.toString();
		  String caseId2 = caseSb2.toString();
		  
		  if( caseId1 == null )
		  {
			  result = -1;
		  }
		  else if( caseId2 == null )
		  {
			  result = -1;
		  }
		  else 
		  {
			  result = caseId1.compareTo(caseId2);
		  }
		  return result;
		}	
	};

}
