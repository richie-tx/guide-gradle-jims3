package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileSchoolByIDEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
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

import ui.juvenilecase.form.JuvenileSchoolHistoryForm;

/**
 * 
 * @author awidjaja
 *
 * 11/09/2006
 * Note: This Action will eventually replace ProcessJuvenileSchoolAction, because
 * the current implementation is getting all details in DisplayJuvenileSchoolAction 
 * (which should only get summary details only). 
 * This action is also being used in interviewinfo (Conduct Interview Part 2, Social
 * History) - educationalHistoryTile.jsp
 */
public class DisplayJuvSchoolDetailsAction extends LookupDispatchAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward displayJuvSchoolDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileSchoolHistoryForm form = (JuvenileSchoolHistoryForm) aForm;
		String schoolId = aRequest.getParameter("schoolHistoryId");
		
		GetJuvenileSchoolByIDEvent event =
				(GetJuvenileSchoolByIDEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILESCHOOLBYID);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		event.setSchoolId(schoolId);
		dispatch.postEvent(event);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		
		JuvenileSchoolHistoryResponseEvent schoolDetails = 
			(JuvenileSchoolHistoryResponseEvent)MessageUtil.filterComposite(compositeResponse, JuvenileSchoolHistoryResponseEvent.class);
		
		form.setSchoolHistoryDetails(schoolDetails);
		
		return aMapping.findForward(UIConstants.VIEW);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.view", "displayJuvSchoolDetails");
		return keyMap;
	}
	
	
	
		
	
}