//Source file: C:\\views\\juvenileCasework\\app\\src\\ui\\juvenilecase\\action\\DisplayGEDProgramCreateAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.SaveJuvenileGEDProgramEvent;
import messaging.juvenile.reply.JuvenileGEDProgramResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileGEDProgramForm;
import ui.juvenilecase.form.JuvenileSchoolHistoryForm;

public class SubmitGEDProgramCreateAction extends JIMSBaseAction
{
    /* (non-Javadoc)
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.finish", "finish");
	keyMap.put("button.returnToSchoolDetails", "ReturnToSchoolDetails");
	keyMap.put("button.cancel", "cancel");
    }

    public SubmitGEDProgramCreateAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	JuvenileGEDProgramForm jgpForm = (JuvenileGEDProgramForm) aForm;
	/** add code to presist data */
	SaveJuvenileGEDProgramEvent saveEvent = (SaveJuvenileGEDProgramEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEGEDPROGRAM);

	saveEvent.setJuvenileNum(jgpForm.getJuvenileId());
	saveEvent.setEnrollmentDate(DateUtil.stringToDate(jgpForm.getEnrollmentDateStr(), DateUtil.DATE_FMT_1));
	saveEvent.setVerificationDate(DateUtil.stringToDate(jgpForm.getVerificationDateStr(), DateUtil.DATE_FMT_1));
	saveEvent.setEnrollmentStatusCd(jgpForm.getEnrollmentStatusId());
	saveEvent.setParticipationCd(jgpForm.getParticipationLevelId());
	saveEvent.setProgramCd(jgpForm.getProgramId());
	saveEvent.setProgramOtherDesc(jgpForm.getOtherProgramDesc());
	saveEvent.setGedCompleted(jgpForm.isGedCompleted());
	saveEvent.setCompletionDate(DateUtil.stringToDate(jgpForm.getCompletionDateStr(), DateUtil.DATE_FMT_1));
	saveEvent.setAwarded(jgpForm.isAwarded());
	saveEvent.setAwardedDate(DateUtil.stringToDate(jgpForm.getAwardedDateStr(), DateUtil.DATE_FMT_1));

	MessageUtil.postRequest(saveEvent);
	/** Adding new GED Program to existing list */
	JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm) getSessionForm(aMapping, aRequest, "juvenileSchoolHistoryForm", true);
	List programGEDs = new ArrayList();
	if (schoolForm.getExistingGEDPrograms() != null)
	{
	    programGEDs = schoolForm.getExistingGEDPrograms();
	}
	JuvenileGEDProgramResponseEvent jgEvent = new JuvenileGEDProgramResponseEvent();
	jgEvent.setEnrollmentDate(DateUtil.stringToDate(jgpForm.getEnrollmentDateStr(), DateUtil.DATE_FMT_1));
	jgEvent.setVerificationDate(DateUtil.stringToDate(jgpForm.getVerificationDateStr(), DateUtil.DATE_FMT_1));
	jgEvent.setEnrollmentStatusDesc(jgpForm.getEnrollmentStatusDesc());
	jgEvent.setParticipationDesc(jgpForm.getParticipationLevelDesc());
	jgEvent.setProgramDesc(jgpForm.getProgramDesc());
	jgEvent.setProgramOtherDesc(jgpForm.getOtherProgramDesc());
	jgEvent.setCompletionDate(DateUtil.stringToDate(jgpForm.getCompletionDateStr(), DateUtil.DATE_FMT_1));
	jgEvent.setAwarded(jgpForm.isAwarded());
	jgEvent.setGedCompleted( jgpForm.isGedCompleted());
	jgEvent.setAwardedDate(DateUtil.stringToDate(jgpForm.getAwardedDateStr(), DateUtil.DATE_FMT_1));
	programGEDs.add(jgEvent);
	schoolForm.setExistingGEDPrograms(programGEDs);
	programGEDs = null;
	jgpForm.setAction(UIConstants.CONFIRM);
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward ReturnToSchoolDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	JuvenileGEDProgramForm jgpForm = (JuvenileGEDProgramForm) aForm;
	jgpForm.clear();
	return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileGEDProgramForm jgpForm = (JuvenileGEDProgramForm) aForm;
	jgpForm.clear();
	return aMapping.findForward(UIConstants.CANCEL);
    }
}
