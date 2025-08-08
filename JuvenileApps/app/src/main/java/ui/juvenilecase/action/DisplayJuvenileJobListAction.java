package ui.juvenilecase.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileJobsEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileJobForm;
import ui.juvenilecase.form.JuvenileProfileForm;

public class DisplayJuvenileJobListAction extends Action
{

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception
    {

        String returnVal = UIConstants.FAILURE;

        JuvenileJobForm jjForm = (JuvenileJobForm) aForm;

        GetJuvenileJobsEvent jobsEvent = (GetJuvenileJobsEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETJUVENILEJOBS);
        JuvenileProfileForm profileForm = UIJuvenileHelper.getHeaderForm(aRequest);
        jobsEvent.setJuvenileNum(profileForm.getJuvenileNum());
        List jobs = MessageUtil.postRequestListFilter(jobsEvent, JuvenileJobResponseEvent.class);

        // sort this list based on entryDate
        Collections.sort(jobs);

        int size = 0;
        if (jobs == null)
        {
            return aMapping.findForward(returnVal);
        }
        else
        {
            returnVal = UIConstants.SUCCESS;
            size = jobs.size();
        }

        // start processing
        jjForm.clear();
        jjForm.reset(aMapping, aRequest);
        jjForm.setJuvenileId(profileForm.getJuvenileNum());
        jjForm.setJobs(jobs);

        return aMapping.findForward(returnVal);
    }

}
