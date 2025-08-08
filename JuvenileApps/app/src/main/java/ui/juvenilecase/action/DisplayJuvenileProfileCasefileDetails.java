package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.Name;
import ui.juvenilecase.form.JuvenileCasefileForm;

public class DisplayJuvenileProfileCasefileDetails extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileCasefileForm form = (JuvenileCasefileForm) aForm;
        String casefileId = aRequest.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM);
        form.clear();
        form.populateJuvenileCasefileForm(casefileId);

        return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param casefileEvent
     * @param form
     */
    private void setForm(JuvenileCasefileResponseEvent casefileEvent, JuvenileCasefileForm form)
    {
        // Set properties on the casefile form
        form.setActivationDate(casefileEvent.getActivationDate());
        form.setAssignmentDate(casefileEvent.getAssignmentDate());
        form.setCaseStatusId(casefileEvent.getCaseStatusId());
        form.setCurrentAge(casefileEvent.getJuvenileCurrentAge());
        //form.setExpectedSupervisionEndDate(???);
        form.setIsNewMAYSINeeded(casefileEvent.getIsMAYSINeeded());
        form.setJuvenileName(new Name(casefileEvent.getJuvenileFirstName(), casefileEvent.getJuvenileMiddleName(),
                casefileEvent.getJuvenileLastName()));
        form.setJuvenileNum(casefileEvent.getJuvenileNum());
        form.setProbationOfficerName(new Name(casefileEvent.getProbationOfficerFirstName(), casefileEvent
                .getProbationOfficerMiddleName(), casefileEvent.getProbationOfficerLastName()));
        form.setProbationOfficerLogonId(casefileEvent.getProbationOfficerLogonId());
        form.setRaceId(casefileEvent.getJuvenileRaceId());
        form.setSexId(casefileEvent.getJuvenileSexId());
        form.setSupervisionNum(casefileEvent.getSupervisionNum());
        form.setSupervisionTypeId(casefileEvent.getSupervisionTypeId());

    }

    private JuvenileCasefileResponseEvent fetchCasefile(String casefileId)
    {
        GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory
                .getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
        getCasefileEvent.setSupervisionNumber(casefileId);

        JuvenileCasefileResponseEvent casefileEvent = (JuvenileCasefileResponseEvent) MessageUtil.postRequest(
                getCasefileEvent, JuvenileCasefileResponseEvent.class);

        return casefileEvent;
    }
}
