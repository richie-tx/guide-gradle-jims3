//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\party\\action\\SubmitPartyCreateUpdateAction.java
/*
 *  05/09/2006  Hien Rodriguez  Revise to use by PASO & AOOC use cases - only Back button 
 *  is implemented now.
 */

package ui.contact.party.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.party.form.PartyForm;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitPartyCreateUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 416D2530019D
	 */
	public SubmitPartyCreateUpdateAction()
	{

	}

	/**
		* @see LookupDispatchAction#getKeyMethodMap()
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.back", "back");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.update", "update");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		PartyForm pf = (PartyForm) aForm;
		String action = pf.getAction();
		String secondaryAction = pf.getSecondaryAction();
	
		forward = aMapping.findForward(UIConstants.BACK);
	
	/*	if (action.equals(UIConstants.VIEW))
		{
			if (secondaryAction.equals("PASO"))
			{
				forward = aMapping.findForward("backToPASO");
				
			}
			else
				if (secondaryAction.equals("AOOC"))
				{
					forward = aMapping.findForward("backToAOOC");
				}
				else
				{
					forward = aMapping.findForward(UIConstants.FAILURE);
				}
		}*/
//		forward.setRedirect(true);
//		forward.setContextRelative(false);
		return forward;
	}

	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	/*			PartyForm partyForm = (PartyForm) aForm;
				partyForm.setAction("create");
				// fill in all the party info
				UpdatePartyEvent requestEvent = new UpdatePartyEvent();
				partyForm.populatePartyEvent(requestEvent);
						
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(requestEvent);
		
				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				//Map responsesMap = MessageUtil.groupByTopic(compositeResponse);
		
				Object obj = MessageUtil.filterComposite(compositeResponse, ReturnException.class);
				if (obj != null)
				{
					return aMapping.findForward(UIConstants.FAILURE);
				}
		//		else
		//		{
		////			PartyResponseEvent pre =
		////				(PartyResponseEvent) MessageUtil.filterComposite(compositeResponse, PartyResponseEvent.class);
		//			
		//		}
				
				return aMapping.findForward(UIConstants.CREATE_SUCCESS);*/
		return null;
	}

	public ActionForward update(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return null;
	}

	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return null;
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return null;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
