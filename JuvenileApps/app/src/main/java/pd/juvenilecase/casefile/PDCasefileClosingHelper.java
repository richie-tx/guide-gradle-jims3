/*
 * Created on December 07, 2005
 * @author mchowdhury
 */
package pd.juvenilecase.casefile;

import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDJuvenileCaseConstants;

import java.util.Date;

public final class PDCasefileClosingHelper
{
    /**
	 * 
	 */
    private PDCasefileClosingHelper()
    {
    }

    /**
     * @param casefileClosing
     */
    public static void postCasefileClosingDetails(CasefileClosingInfo casefileClosing)
    {
	if (casefileClosing != null)
	{
	    CasefileClosingResponseEvent response = new CasefileClosingResponseEvent();
	    
	    response.setPetitionNumber(casefileClosing.getPetitionNumber());
	    response.setClosingEvaluation(casefileClosing.getClosingEvaluation());
	    response.setFacilityReleaseReasonId(casefileClosing.getFacilityReleaseReasonId());
	    response.setSupervisionOutcomeId(casefileClosing.getSupervisionOutcomeId());
	    response.setFacilityReleaseReasonName(casefileClosing.getFacilityReleaseReasonName());
	    response.setPermanencyPlanId(casefileClosing.getPermanencyPlanId());
	    response.setPermanencyPlanName(casefileClosing.getPermanencyPlanName());
	    response.setFacilityId(casefileClosing.getFacilityId());
	    response.setPermanencyPlanName(casefileClosing.getPermanencyPlanName());
	    response.setSupervisionNumber(casefileClosing.getSupervisionNumber());
	    response.setSupervisionEndDate(casefileClosing.getSupervisionEndDate());
	    response.setLevelOfCareId(casefileClosing.getLevelOfCareId());
	    response.setLevelOfCareName(casefileClosing.getLevelOfCareName());
	    response.setCasefileClosingStatus(casefileClosing.getCasefileClosingStatus());
	    response.setSpecialNotes(casefileClosing.getSpecialNotes());
	    response.setExitPlanTemplateLocation(casefileClosing.getExitPlanTemplateLocation());
	    response.setSupervisionOutcomeId(casefileClosing.getSupervisionOutcomeId());
	    response.setSupervisionOutcomeDescriptionId(casefileClosing.getSupervisionOutcomeDescriptionId());
	    response.setControllingReferralId(casefileClosing.getControllingReferralId());
	    response.setExpectedReleaseDate(casefileClosing.getExpectedReleaseDate());
	    response.setClosingComments(casefileClosing.getClosingComments());
	    response.setCasefileClosingInfoId(casefileClosing.getOID().toString());
	    response.setRejectReason(casefileClosing.getRejectionReason());
	    response.setTopic(PDJuvenileCaseConstants.CASEFILE_CLOSING_EVENT_TOPIC);
	    response.setClosingLetterGenerated(casefileClosing.isClosingLetterGenerated());
	    response.setClosingPktGenerated(casefileClosing.isClosingPktGenerated());
	    response.setRecordCLM(casefileClosing.getRecordCLM());
	    response.setJuvLocUnitId(casefileClosing.getJuvLocUnitId());
	    response.setCreatedBy(casefileClosing.getCreateUserID());
	    //production support
	    if (casefileClosing.getCreateUserID() != null)
	    {
		response.setCreateUserID(casefileClosing.getCreateUserID());
	    }
	    if (casefileClosing.getCreateTimestamp() != null)
	    {
		response.setCreateDate(new Date(casefileClosing.getCreateTimestamp().getTime()));
	    }
	    if (casefileClosing.getUpdateUserID() != null)
	    {
		response.setUpdateUser(casefileClosing.getUpdateUserID());
	    }
	    if (casefileClosing.getUpdateTimestamp() != null)
	    {
		response.setUpdateDate(new Date(casefileClosing.getUpdateTimestamp().getTime()));
	    }
	    if (casefileClosing.getCreateJIMS2UserID() != null)
	    {
		response.setCreateJIMS2UserID(casefileClosing.getCreateJIMS2UserID());
	    }
	    if (casefileClosing.getUpdateJIMS2UserID() != null)
	    {
		response.setUpdateJIMS2UserID(casefileClosing.getUpdateJIMS2UserID());
	    }

	    sendResponseEvent(response);
	}
    }

    /**
     * @param event
     *            IEvent
     */
    public static void sendResponseEvent(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	dispatch.postEvent(event);
    }

    /**
     * @param updateEvent
     */
    public static void postDuplicateErrorResponse(UpdateCasefileClosingEvent updateEvent)
    {
	DuplicateRecordErrorResponseEvent errorResponseEvent = new DuplicateRecordErrorResponseEvent();
	StringBuffer buffer = new StringBuffer("A record with the Supervision Number ");
	buffer.append(updateEvent.getSupervisionNumber());
	buffer.append(" exists.");
	errorResponseEvent.setMessage(buffer.toString());
	sendResponseEvent(errorResponseEvent);
    }

}
