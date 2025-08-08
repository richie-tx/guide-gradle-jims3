package pd.supervision.administercompliance.transactions.daos;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.GetNCReportingEvent;
import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCReportingEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCLastKnownAddressResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.cscdcalendar.GetViolationReportCalEventsEvent;
import messaging.party.GetPartyDataEvent;
import messaging.supervisionorder.GetActiveSupervisionOrderByCaseAndGroupEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionOrderConditionConstants;
import naming.ViolationReportConstants;
import pd.address.Address;
import pd.codetable.supervision.SupervisionCode;
import pd.common.DAOHandler;
import pd.contact.party.Party;
import pd.security.PDSecurityHelper;
import pd.supervision.Group;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.NonComplianceEvent;
import pd.supervision.administercompliance.Reporting;
import pd.supervision.administercompliance.ViolationReport;
import pd.supervision.administercompliance.ViolationReportUtility;
import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionorder.SupervisionOrderConditionView;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class ReportingDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public ReportingDAO()
	{
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#execute(java.lang.Object)
	 */
	public void execute(Object object) {
		postDb2Data(object.toString());
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {
		deleteJims2Data(object.toString());		
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		UpdateNCResponseEvent reqEvent = (UpdateNCResponseEvent) object;
		createViolationReport(reqEvent);
		
		Enumeration enumer = reqEvent.getRequests();
		Reporting rep = null;
		
		Map existingMap = Reporting.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId()); 

		while(enumer.hasMoreElements()){
			UpdateNCReportingEvent rEvent = (UpdateNCReportingEvent) enumer.nextElement();
			if(rEvent.getReportingid() == null || rEvent.getReportingid().equals("")){
				rep = new Reporting();
				rep.setReporting(rEvent,reqEvent.getNcResponseId());
				rep.commit();
			}else{
				rep = Reporting.find(rEvent.getReportingid());
				if(rep != null && existingMap.containsKey(rep.getOID())){
					existingMap.remove(rep.getOID());
				}
			}
		}
		
		ViolationReport vr = ViolationReport.find(reqEvent.getNcResponseId());
		if(vr != null){
			vr.setLastKnownAddress(reqEvent);
			vr.commit();
		}		
		setComments (ViolationReportConstants.REQUEST_REPORTING, reqEvent.getNcResponseId(), reqEvent);
		
        // at this point existingmap only contains unwanted stuffs
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			rep = (Reporting) unwantedIter.next();
			if(rep != null){
				rep.delete();
				rep.commit();
			}
		}
		
		postDb2Data(reqEvent.getNcResponseId());
 	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;
	
		if(ViolationReportConstants.CREATE_MODE.equalsIgnoreCase(reqEvent.getMode())){
			GetNCReportingEvent gEvent = (GetNCReportingEvent) reqEvent.getRequests().nextElement();
			postLegacyData(reqEvent.getNcResponseId(), gEvent);
		}else{
			this.postDb2Data(reqEvent.getNcResponseId());
 
		    ViolationReport vr = ViolationReport.find(reqEvent.getNcResponseId());
			if(vr != null){
				String addressType = vr.getAddressType();
				String state = vr.getStateId();
				NCLastKnownAddressResponseEvent ncResp = this.getLastKnownAddressResponseEvent(addressType,vr.getAptNumber(),vr.getCity(), vr.getLastContactDate(), state, vr.getStreetName(),vr.getStreetNumber(), vr.getZipcode());
				MessageUtil.postReply(ncResp);	
				
				postComments(ViolationReportConstants.REQUEST_REPORTING,reqEvent.getNcResponseId());
			}
		}			
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#refresh(java.lang.Object)
	 */
	public void refresh(Object object) {
		RefreshNCResponseComponentsEvent refEvent = (RefreshNCResponseComponentsEvent) object;
		
		// delete jims2 data
		ViolationReport vr = ViolationReport.find(refEvent.getNcResponseId());
		if(vr != null){
			deleteJims2Data(refEvent.getNcResponseId());
			Comment comment = getComments(ViolationReportConstants.REQUEST_REPORTING, refEvent.getNcResponseId());
			if(comment != null){
				comment.delete();
			}
			
			vr.setLastKnownAddress("",null,"","","","","");			
		}
		
		// retrieve from Legacy
		GetNCReportingEvent gEvent = new GetNCReportingEvent();
		gEvent.setDefendantId(refEvent.getDefendantId());
		gEvent.setCaseId(refEvent.getCaseId());
		gEvent.setCdi(refEvent.getCdi());
        postLegacyData(refEvent.getNcResponseId(),gEvent);
	}
	
	private Map getEventTypes(String agencyId) {
		Map map = new HashMap();
		GetSupervisionCodesEvent getCodesEvent = new GetSupervisionCodesEvent();
		getCodesEvent.setCodeTableName("EVENT_TYPE");
		getCodesEvent.setAgencyId(agencyId);
		Iterator iter = SupervisionCode.findAll("codeTableName", "EVENT_TYPE");
		while(iter.hasNext()){
			SupervisionCode code = (SupervisionCode)iter.next();
			if ( "CSC".equals(code.getAgencyId())){
				map.put(code.getCode(), code.getDescription());
			}
		}
		return map;
	}
	
	/**
	 * @param ncResponseId
	 * @return
	 */
	private boolean postDb2Data(String ncResponseId){
		boolean isExist = false;
		Iterator iterator = Reporting.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		
		Map eventTypes = this.getEventTypes(PDSecurityHelper.getUserAgencyId());
		while(iterator.hasNext()){
			Reporting r = (Reporting) iterator.next();
			NonComplianceEventResponseEvent resp = r.getResponseEvent(eventTypes);
			resp.setNcResponseId(ncResponseId);
			resp.setTopic(ViolationReportConstants.REQUEST_REPORTING);
			MessageUtil.postReply(resp);
			isExist = true;
		}
		
		if(isExist){
			ViolationReport vr = ViolationReport.find(ncResponseId);
			if(vr != null){
				NCLastKnownAddressResponseEvent response = new NCLastKnownAddressResponseEvent();
				if(vr.getAddressType() != null && !"".equals(vr.getAddressType())){
					if(vr.getAddressTypeCode() != null){
						response.setAddressType(vr.getAddressTypeCode().getDescription());
						response.setAddressTypeId(vr.getAddressType());
					}
				}
				response.setAptNumber(vr.getAptNumber());
				response.setCity(vr.getCity());
				response.setLastContactDate(vr.getLastContactDate());
				
				if(vr.getStateId() != null && !"".equals(vr.getStateId())){
					if(vr.getState() != null){
						response.setState(vr.getState().getDescription());
						response.setStateId(vr.getStateId());
					}
				}
				response.setState(vr.getStateId());
				response.setStreetName(vr.getStreetName());
				response.setStreetNumber(vr.getStreetNumber());
				response.setZip(vr.getZipcode());
				MessageUtil.postReply(response);	
			}
		}
		return isExist;
	}
	
	/**
	 * @param event
	 * @param ncResponseId
	 */
	private void postLegacyData(String ncResponseId, Object object) {
		GetNCReportingEvent gEvent = (GetNCReportingEvent) object;
		Iterator iterGroup = Group.findAll(ViolationReportConstants.GROUP_NAME, ViolationReportConstants.GROUP_REPORTING);
		Group group = null;
		String agencyCd = PDSecurityHelper.getUserAgencyId().toString();
		while(iterGroup.hasNext()){
			group = (Group) iterGroup.next();
			if( agencyCd != "" && agencyCd.equals( group.getAgencyId() )&& 1 == group.getGroupLevel()){
			    break;	
			}
			group = null;
		}
		if(group == null){		
			ReturnException re = new ReturnException("Group Reporting is absent in database");
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(re);
			return;
		}
		
		GetActiveSupervisionOrderByCaseAndGroupEvent requestEvent = new GetActiveSupervisionOrderByCaseAndGroupEvent();
		requestEvent.setCaseId(new StringBuffer(gEvent.getCdi()).append(gEvent.getCaseId()).toString());
		requestEvent.setGroupId(group.getOID());
		Iterator iterator = SupervisionOrderConditionView.findAll(requestEvent);
		
		SupervisionOrderConditionView view = null;
		boolean isExist = false;
		while(iterator.hasNext()){
			view = (SupervisionOrderConditionView) iterator.next();
			
			Condition condition = Condition.find(new StringBuffer("").append(view.getConditionId()).toString());
			Iterator iterSupervisionTypes = condition.getSupervisionEventTypes().iterator();
			HashMap map = new HashMap();
			while(iterSupervisionTypes.hasNext()){
			    SupervisionCode code = (SupervisionCode) iterSupervisionTypes.next();
			    map.put(code.getCode(), code.getDescription());
			}

			Iterator iter = NonComplianceEvent.findAllByNumericParam(SupervisionOrderConditionConstants.SUPERVISION_ORDER_CONDITION_ID, view.getOID());
		   	while(iter.hasNext()){
		   	    NonComplianceEvent nce = (NonComplianceEvent) iter.next();
		   	    if(nce != null){
		   	   	    NonComplianceEventResponseEvent resp = nce.getResponseEvent(map);
		   	       	resp.setNonComplianceEventId(new StringBuffer("L").append(resp.getNonComplianceEventId()).toString());
		   	       	MessageUtil.postReply(resp);
		   	        isExist = true;
		   	    }
		   	}		    
		}
		
		if(isExist){
			GetViolationReportCalEventsEvent vEvent = new GetViolationReportCalEventsEvent();
			vEvent.setDefendantId(gEvent.getDefendantId());
			Iterator csIter = CSEvent.findAll(vEvent);
			GetPartyDataEvent getEvent = null;     
			Map map = new HashMap();
			while(csIter.hasNext()){
			    CSEvent cs = (CSEvent) csIter.next();
			    getEvent = new GetPartyDataEvent(); 
				getEvent.setSpn(gEvent.getDefendantId());
		
		        Party party = null;
		        if(!map.containsKey(getEvent.getSpn())){
		        	party = Party.find(getEvent);
		        	map.put(getEvent.getSpn(), party);
		        }else{
		        	party = (Party) map.get(getEvent.getSpn());
		        }
		        
		        if (party != null) {
		        	Address addr = party.getCurrentAddress();
		        	if(addr != null){
			        	NCLastKnownAddressResponseEvent response = new NCLastKnownAddressResponseEvent();
						if(addr.getAddressTypeId() != null && !"".equals(addr.getAddressTypeId())){
							if(addr.getAddressType() != null){
								response.setAddressType(addr.getAddressType().getDescription());
								response.setAddressTypeId(addr.getAddressTypeId());
							}
						}
						response.setAptNumber(addr.getAptNum());
						response.setCity(addr.getCity());
						response.setLastContactDate(cs.getEventDate());
						
						if(addr.getStateId() != null && !"".equals(addr.getStateId())){
							if(addr.getState() != null){
								response.setState(addr.getState().getDescription());
								response.setStateId(addr.getStateId());
							}
						}
						response.setStreetName(addr.getStreetName());
						response.setStreetNumber(addr.getStreetNum());
						response.setZip(addr.getZipCode());
						MessageUtil.postReply(response);
		        	}
		        }
			}
		}
	}
	
	private NCLastKnownAddressResponseEvent getLastKnownAddressResponseEvent(String addressType, String aptNumber, String city, Date lastContactDate, String state, String streetName, String streetNumber, String zip ){
		NCLastKnownAddressResponseEvent resp = new NCLastKnownAddressResponseEvent();
    	resp.setAddressType(addressType);
    	resp.setAptNumber(aptNumber);
    	resp.setCity(city);
    	resp.setLastContactDate(lastContactDate);
		resp.setState(state);
		resp.setStreetName(streetName);
		resp.setStreetNumber(streetNumber);
		resp.setZip(zip);
    	return resp;
	}	
	
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId) {
		Iterator iterator = Reporting.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			Reporting r = (Reporting) iterator.next();
		    r.delete();
		}
	}
}
