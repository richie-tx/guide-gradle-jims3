/*
 * Created on Dec 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.profileactivities.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.activities.form.ActivitiesForm;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayProfileActivitiesAction extends LookupDispatchAction {

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward displayActivities(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		ActivitiesForm form = (ActivitiesForm) aForm;
		if (form == null)
		{
			form = new ActivitiesForm();
			aRequest.getSession().setAttribute(UIConstants.ACTIVITIES_FORM, form);
		}
		form.clearAll();	
		form.setSecondaryAction("");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileActivityTypeCodesEvent reqEvent =
			(GetJuvenileActivityTypeCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);		
		dispatch.postEvent(reqEvent);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse composite = (CompositeResponse) replyEvent;
		JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil.filterComposite(composite, JuvenileActivityTypeCodeResponseEvent.class);
		String fromFacility = aRequest.getParameter("fromFacility");
		if(fromFacility!= null){
		if(fromFacility.equalsIgnoreCase("true")){
		    form.setSelectedCategoryId("RES");
		    //aRequest.getSession().setAttribute(fromFacility, "true");
		   // aRequest.setAttribute(fromFacility, "true");
		    form.setActivityCodes(response.getReturnValues());
		    form.setActivityResults(new ArrayList());
		}else{
		    	form.setActivityCodes(response.getReturnValues());
		}
		} else{
		    	form.setActivityCodes(response.getReturnValues());
		}
		System.out.println("TEST+TEST" + fromFacility);	
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)	
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.link", "displayActivities");
		keyMap.put("button.viewActivities", "viewActivities");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}

}
