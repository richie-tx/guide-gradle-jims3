package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileJobEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenileJobForm;

/**
 * 
 * @author awidjaja
 *
 * 11/09/2006
 * Note: This Action will eventually replace DisplayJuvenileJobDetailsAction, because
 * the current implementation is getting all details in DisplayJuvenileJobListAction 
 * (which should only get summary details only). 
 * This action is also being used in interviewinfo (Conduct Interview Part 2, Social
 * History) - employmentHistoryTile.jsp
 */
public class DisplayJuvJobDetailsAction extends LookupDispatchAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward displayJuvJobDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileJobForm form = (JuvenileJobForm) aForm;
		String jobNum = aRequest.getParameter("jobNum");
		
		GetJuvenileJobEvent event =
				(GetJuvenileJobEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEJOB);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		event.setJobId(jobNum);
		dispatch.postEvent(event);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		
		JuvenileJobResponseEvent jobDetails = 
			(JuvenileJobResponseEvent)MessageUtil.filterComposite(compositeResponse, JuvenileJobResponseEvent.class);
		
		form.setJobProperties(jobDetails);
		
		return aMapping.findForward(UIConstants.VIEW);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.link", "displayJuvJobDetails");
		return keyMap;
	}
	
	
	
	
	
		
	
}