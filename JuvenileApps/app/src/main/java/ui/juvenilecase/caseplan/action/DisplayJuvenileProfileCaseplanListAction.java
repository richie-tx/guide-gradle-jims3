package ui.juvenilecase.caseplan.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.caseplan.GetCaseplansByJuvenileNumberEvent;
import messaging.caseplan.reply.CaseplanListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;
/**
 * 
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayJuvenileProfileCaseplanListAction extends Action
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		//entry point for caseplan profile, set to profile tabs/views
		CaseplanForm form = (CaseplanForm) aForm;
		form.setJuvProfile(true);
		
		String juvNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
		form.setJuvenileNum(juvNum); //added new 
	 	GetCaseplansByJuvenileNumberEvent evt = new GetCaseplansByJuvenileNumberEvent();
		evt.setJuvenileNum(juvNum);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		
		Collection caseplans = MessageUtil.compositeToCollection(compositeResponse, CaseplanListResponseEvent.class);
		
		form.setCaseplanList(caseplans);

		Collection activities = MessageUtil.compositeToCollection(compositeResponse, ActivityResponseEvent.class);
		form.setProfileActivityList(activities);
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
		keyMap.put("button.link", "displayCaseplanList");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	
	
	
		
	
}