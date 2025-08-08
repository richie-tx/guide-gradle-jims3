/*
 * Created on Feb 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.cscdcalendar;

import naming.PDCodeTableConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.CSOtherResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.domintf.contact.party.IParty;
import messaging.party.GetPartyDataEvent;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSEventBuilder {	
	
	public static CSOtherResponseEvent buildOtherEvent(CSEvent event, boolean buildChildren) {
		CSOtherResponseEvent resp = new CSOtherResponseEvent();
		resp.setEventId(event.getOID());
		resp.setEventType(event.getEventTypeId());
		if(event.getEventTypeId().equals(PDCodeTableConstants.OTHER_EVENT_TYPE)) {
			resp.setEventTypeDesc(event.getOtherEventType());
		} else {
			resp.setEventTypeDesc(event.getEventType().getDescription());
		}
		
		resp.setEventDate(event.getEventDate());
		if(event.getStartTime()!=null) {
			resp.setStartTime(CSEventHelper.formatDateIntoTimeInAMPM(event.getStartTime()));
		} else {
			resp.setStartTime("");
		}
		if(event.getEndTime()!=null) {
			resp.setEndTime(CSEventHelper.formatDateIntoTimeInAMPM(event.getEndTime()));
		} else {
			resp.setEndTime("");
		}
		resp.setStatus(event.getStatusId());
		resp.setEventName(event.getEventName());
		resp.setPhonenum(event.getPhoneNumber());
		resp.setNarrative(event.getNarrative());
		resp.setOtherType(event.getOtherEventType());
		resp.setPurpose(event.getPurpose());
		resp.setOutcome(event.getOutCome());
		SupervisionCode outcomeObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
				PDCodeTableConstants.CSCD_AGENCY,
				PDCodeTableConstants.FV_OUTCOME, event.getOutCome());
		if(outcomeObj!=null) {
			resp.setOutcomeDesc(outcomeObj.getDescription());
		}
		resp.setPositionId(event.getPositionId());
		resp.setSuperviseeId(event.getPartyId());
		resp.setResultUserId(event.getResultUserId());
		resp.setResultPositionId(event.getResultPositionId());
		if(buildChildren) {
			resp.setPartyEvent(getPartyResponse(event));
			resp.setPositionEvent(getOfficerResponse(event));
		}
		return resp;
	}
	
	public static PartyResponseEvent getPartyResponse(CSEvent csEvent) {
		PartyResponseEvent partyRes = null;
		if(csEvent.getPartyId()!=null && csEvent.getParty()!=null) {
			Party party = csEvent.getParty();
			IParty partyInfo = PDPartyHelper.getPartyResponseEvent(party);	
			partyRes = (PartyResponseEvent)partyInfo;
		}		
		return partyRes;
	}
	
	// added to retrieve current current name from M204 JIMS200062401
	public static PartyResponseEvent getPartyListResponse(CSEvent csEvent) {
		PartyResponseEvent partyRes = new PartyResponseEvent();
	 	GetPartyDataEvent getPartyData = new GetPartyDataEvent();
		getPartyData.setSpn( csEvent.getPartyId() );
		getPartyData.setCurrentNameInd("Y");
		Party party = Party.find( getPartyData );
		if( party != null ){
			IParty partyInfo = PDPartyHelper.getPartyResponseEvent( party );					
			partyRes = (PartyResponseEvent)partyInfo;
		}
		return partyRes;
	}
	
	public static CSCDSupervisionStaffResponseEvent getOfficerResponse(CSEvent csEvent) {
		CSCDSupervisionStaffResponseEvent positionRes = null;
		if(csEvent.getPositionId()!=null && csEvent.getPosition()!=null) {
			positionRes = CSCDStaffPositionHelper.getStaffResponseEvent(csEvent.getPosition(), null);			
		}
		return positionRes;
	}
	
	public static CSOfficeVisitResponseEvent buildOfficeVisit(CSEvent event, boolean buildChildren) {
		CSOfficeVisitResponseEvent resp = new CSOfficeVisitResponseEvent();
		resp.setEventId(event.getOID());
		resp.setEventType(event.getEventTypeId());
		event.getEventType().getDescription();
		resp.setEventDate(event.getEventDate());
		if(event.getStartTime()!=null) {
			resp.setStartTime(CSEventHelper.formatDateIntoTimeInAMPM(event.getStartTime()));
		} else {
			resp.setStartTime("");
		}
		if(event.getEndTime()!=null) {
			resp.setEndTime(CSEventHelper.formatDateIntoTimeInAMPM(event.getEndTime()));
		} else {
			resp.setEndTime("");
		}
		resp.setStatus(event.getStatusId());
		resp.setEventName(event.getEventName());
		resp.setPhonenum(event.getPhoneNumber());
		resp.setNarrative(event.getNarrative());		
		resp.setPurpose(event.getPurpose());
		resp.setOutcome(event.getOutCome());
		SupervisionCode outcomeObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
				PDCodeTableConstants.CSCD_AGENCY,
				PDCodeTableConstants.OV_OUTCOME, event.getOutCome());
		if(outcomeObj!=null) {
			resp.setOutcomeDesc(outcomeObj.getDescription());
		}
		resp.setPositionId(event.getPositionId());
		resp.setRescheduleReason(event.getRescheduleReason());
		resp.setSuperviseeId(event.getPartyId());
		resp.setResultUserId(event.getResultUserId());
		resp.setResultPositionId(event.getResultPositionId());
		if(buildChildren) {
			resp.setPartyEvent(getPartyResponse(event));
			resp.setPositionEvent(getOfficerResponse(event));
		}
		return resp;
	}

}
