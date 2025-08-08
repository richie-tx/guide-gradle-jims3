/*
 * Created on Jun 24, 2005
 *
 */
package ui.juvenilecase.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.reply.JuvenileJobResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.JuvenileJobForm;

/**
 * @author sprakash
 *  
 */
public class DisplayJuvenileJobDetailsAction extends Action
{

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        ActionForward forward = new ActionForward();
        JuvenileJobForm jjForm = (JuvenileJobForm) aForm;

        // Get the contactNum off the request (set in the hyperlink)
        String jobNum = aRequest.getParameter("jobNum");
        if (jobNum == null || jobNum.equals(""))
        {
            // some problem
            aMapping.findForward(UIConstants.FAILURE);
        }

        // get job resposne event
        JuvenileJobResponseEvent jcResponseEvent = getJuvenileJobResponseEvent(jobNum, jjForm.getJobs());
        jjForm.setJobProperties(jcResponseEvent);
        jjForm.setAction(UIConstants.VIEW);

        forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;

        //	   	
        //		String forward = UIConstants.SUCCESS;
        //
        //			JuvenileJobForm juvjobForm = (JuvenileJobForm) aForm;
        //
        //			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        //			SaveJuvenileJobEvent requestEvent =
        //					(SaveJuvenileJobEvent) EventFactory.getInstance(
        //								JuvenileControllerServiceNames.GETJUVENILEJOBS);
        //						requestEvent.setJuvenileNum(juvjobForm.getJuvenileId());
        //			dispatch.postEvent(requestEvent);
        //			
        //			CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
        //			Collection jobs = MessageUtil.compositeToCollection(replyEvent,
        // JuvenileJobResponseEvent.class);
        //			
        //			
        //			
        //			
        //			
        //			ReturnException returnException =
        //										   (ReturnException) WebUtil.filterComposite(replyEvent, ReturnException.class);
        //			if (returnException == null){
        //				juvjobForm.setJuvenileJobs(jobs);
        //			}else{
        //				juvjobForm.setErrorMessage(returnException.getMessage());
        //			}
        //		
        //			return aMapping.findForward(UIConstants.SUCCESS);

    }

    private JuvenileJobResponseEvent getJuvenileJobResponseEvent(String jobNum, Collection jobs)
    {
        JuvenileJobResponseEvent jobRespEvent = null;
        Iterator iter = jobs.iterator();
        while (iter.hasNext())
        {
            jobRespEvent = (JuvenileJobResponseEvent) iter.next();
            if (jobRespEvent.getJobNum().equals(jobNum))
            {
                break;
            }
        }
        return jobRespEvent;
    }

}
