/*
 * Created on Dec 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerlocation.CSC.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.ValidateLocationEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.supervision.administerlocation.form.LocationForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayLocationCreateUpdateSummaryForCSCAction extends LookupDispatchAction
{
	/**
	 * @cc_bjangay
	 */
	public DisplayLocationCreateUpdateSummaryForCSCAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45104B4102DA
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;

		String locationName = locationForm.getLocationName();
		String locationCd = locationForm.getLocationCd();
				
		if (locationForm.getAction().equals("update") && locationForm.getOldlocationCd().equals(locationCd)){
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		
		ValidateLocationEvent validateEvent = new ValidateLocationEvent();
		validateEvent.setAgencyId(SecurityUIHelper.getUserAgencyId()); //changed vj
		validateEvent.setLocationName(locationName);
		validateEvent.setLocationCd(locationCd);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		Collection errorMessgs = MessageUtil.compositeToCollection(compositeResponse , ErrorResponseEvent.class);
		//ErrorResponseEvent nameError = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		boolean errorMsgAdded = false;
		if(errorMessgs!=null && !errorMessgs.isEmpty()){
			Iterator errorMessgsList = errorMessgs.iterator();
			ActionErrors errors = new ActionErrors();
			while(errorMessgsList.hasNext())
			{
				ErrorResponseEvent errorResp = (ErrorResponseEvent) errorMessgsList.next();
				// update flow should allow same location name - defect 74988 added 02/18/2013
				if (locationForm.getAction().equals("update") && !"error.duplicate.locationName".equalsIgnoreCase(errorResp.getMessage() ) ) 
				{
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorResp.getMessage(), "Duplicate name"));
					saveErrors(aRequest, errors);
					errorMsgAdded = true;
				}	
			}
			if (errorMsgAdded)
			{	
				return aMapping.findForward("failure");
			}
		}
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		String secAction=locationForm.getSecondaryAction();
		locationForm.setSecondaryAction("");
		if(secAction!=null && secAction.equals(UIConstants.SERVICE_PROVIDER_SUCCESS)){
			return aMapping.findForward("backToCSLocation");
		}
		else
			return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");

		return keyMap;
	}
}
