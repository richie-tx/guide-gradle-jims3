/*
 * Created on Oct 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.commonfunctionality.spnconsolidation.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.spnconsolidation.ConsolidateSpnsEvent;
import messaging.spnconsolidation.UpdateSpnConsolidationLogEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.SpnConsolidationControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.commonfunctionality.spnconsolidation.form.SpnConsolidationForm;

/**
 * @author ryoung
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SubmitSpnConsolidationAction extends LookupDispatchAction {

	/**
	 * @roseuid 438F241400E1
	 */
	public SubmitSpnConsolidationAction() {

	}

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.newConsolidation", "newConsolidation");
		keyMap.put("button.mainPage", "mainMenu");
		return keyMap;
	}

	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ActionForward forward = new ActionForward();
		SpnConsolidationForm spnConForm = (SpnConsolidationForm) aForm;

		// Consolidate aliasSpn to baseSpn
		ConsolidateSpnsEvent requestEvent = (ConsolidateSpnsEvent) EventFactory
				.getInstance(SpnConsolidationControllerServiceNames.CONSOLIDATESPNS);

		requestEvent.setAliasSpn( spnConForm.getAliasSpn() );
		requestEvent.setBaseSpn( spnConForm.getBaseSpn() );

		dispatch.postEvent(requestEvent);

		// Returns error handling
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		Exception e = (Exception) MessageUtil.filterComposite(response, ReturnException.class);
		boolean hasError = false;
		if ( e != null) {
			hasError = true;
		}

		// Update log event (JIMS2.CSSPNCONLOG table) per requirement
		UpdateSpnConsolidationLogEvent updateEvent = (UpdateSpnConsolidationLogEvent) EventFactory
				.getInstance(SpnConsolidationControllerServiceNames.UPDATESPNCONSOLIDATIONLOG);

		if (!hasError) {
			updateEvent.setStatus("SUCCESS");
		} else {
			updateEvent.setStatus("FAIL");
			updateEvent.setLogDetail( e.getCause().toString() );
		}
		updateEvent.setAliasSpn( spnConForm.getAliasSpn() );
		updateEvent.setBaseSpn( spnConForm.getBaseSpn() );
		updateEvent.setInvocSource("JIMS2");

		dispatch.postEvent( updateEvent );

		// Forward error handling
		if ( e != null ) {
			sendToErrorPage(aRequest, "error.spnConsolidation.fail");
			forward = aMapping.findForward(UIConstants.FAILURE);
		} else {
			spnConForm.setAction(UIConstants.CONFIRM);
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}
		return forward;
	}

	public ActionForward newConsolidation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.NEW_CONSOLIDATION);
	}

	public ActionForward mainMenu(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.MAIN_MENU);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
	
	/**
	 * 
	 * @param aSpn
	 * @return
	 */
	  public String padSpn(String aSpn)
	    {
	        String spn = aSpn;
	        if (spn != null && !spn.trim().equals(""))
	        {
	            while (spn.length() < 8)
	            {
	                spn = "0" + spn;
	            }
	        }
	        return spn;
	    }
	
}
