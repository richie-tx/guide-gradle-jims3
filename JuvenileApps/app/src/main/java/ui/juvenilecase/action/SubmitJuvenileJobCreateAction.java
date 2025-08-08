/*
 * Created on Jun 29, 2005
 *
 */
package ui.juvenilecase.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.SaveJuvenileJobEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.JuvenileJobForm;

/**
 * @author athorat
 *  
 */
public class SubmitJuvenileJobCreateAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileJobForm juvjobForm = (JuvenileJobForm) aForm;

        SaveJuvenileJobEvent requestEvent = (SaveJuvenileJobEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.SAVEJUVENILEJOB);

        requestEvent.setJuvenileNum(juvjobForm.getJuvenileId());
        requestEvent.setEmploymentPlace(juvjobForm.getEmploymentPlace());
        requestEvent.setSalary(juvjobForm.getSalary());
        requestEvent.setSalaryRate(juvjobForm.getSalaryRateId());
        requestEvent.setEmploymentStatus(juvjobForm.getEmploymentStatusId());
        requestEvent.setSupervisorFirstName(juvjobForm.getSupervisorFirstName());
        requestEvent.setSupervisorMiddleName(juvjobForm.getSupervisorMiddleName());
        requestEvent.setSupervisorLastName(juvjobForm.getSupervisorLastName());
        requestEvent.setSupervisorFamilyNum(juvjobForm.getSupervisorFamilyNum());
        requestEvent.setWorkHours(juvjobForm.getWorkHours());
        requestEvent.setJobDescription(juvjobForm.getJobDescription());
        requestEvent.setEntryDate(new Date());

        MessageUtil.postRequest(requestEvent);       

        juvjobForm.setAction(UIConstants.CONFIRM);
        
        return aMapping.findForward(UIConstants.SUCCESS);
    }
}
