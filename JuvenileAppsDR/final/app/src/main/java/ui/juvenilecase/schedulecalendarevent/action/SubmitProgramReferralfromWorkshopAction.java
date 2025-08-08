//Source file: C:\\views\\MJCW\\app\\src\\ui\\juvenilecase\\schedulecalendarevent\\action\\SubmitProgramReferralAction.java

package ui.juvenilecase.schedulecalendarevent.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mojo.km.security.IUserInfo;
import naming.UIConstants;
import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.security.SecurityUIHelper;

public class SubmitProgramReferralfromWorkshopAction extends JIMSBaseAction
{

    /**
     * @roseuid 463F6823009B
     */
    public SubmitProgramReferralfromWorkshopAction()
    {

    }

    public ActionForward goBackToScheduleEvent(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;

	UIProgramReferralBean programReferralBean = form.getProgramReferral();
	String forwardStr = UIConstants.SUCCESS;
	if ("SPSummary".equals(form.getFromPage()))
	{
	    forwardStr = "sp" + forwardStr;
	    form.setReferralFound(true);
	}
	IUserInfo user = SecurityUIHelper.getUser();
	Name userName = new Name(user.getFirstName(), "", user.getLastName());
	programReferralBean.setCurrentUserName(userName.getFormattedName());
	programReferralBean.setSentDate(new Date());
	programReferralBean.setControllingReferralNum(UIProgramReferralHelper.getControllingRefNumber(form.getCaseFileId()));
	aRequest.setAttribute("pageType", "summary");
	return aMapping.findForward(forwardStr);
    }

    public ActionForward showSummaryScreen(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.SUMMARY);
    }

    /* (non-Javadoc)
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "showSummaryScreen");
	keyMap.put("button.next", "goBackToScheduleEvent");
    }
}
