// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerlocation\\action\\DisplayLocationUnitUpdateSummaryAction.java

package ui.supervision.administerlocation.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.ValidateLocationUnitDetailsEvent;
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

import ui.supervision.administerlocation.form.LocationForm;

// Class to validate location unit details when the user clicked on edit link
// and to forward to next page.

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayLocationUnitUpdateSummaryAction extends LookupDispatchAction  {

	/**
	 * @roseuid 4664661A0365
	 */
	public DisplayLocationUnitUpdateSummaryAction() {

	}

	/**
	 * Validates LocationUnitName
	 * on success displays location unit summary page.
	 * on failure remains on the same page.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		locationForm.setAction(UIConstants.UPDATE);
		locationForm.setMisc(UIConstants.SUMMARY);
		ValidateLocationUnitDetailsEvent validateEvent = new ValidateLocationUnitDetailsEvent();
		validateEvent.setUpdate(true); // set to true to validate only locationUnitName for update location unit.
		validateEvent.setLocationId(locationForm.getLocationId());
		validateEvent.setLocationUnitName(locationForm.getLocUnit().getLocationUnitName());
		validateEvent.setLocationUnitId(locationForm.getLocUnit().getJuvLocationUnitId());
		//CompositeResponse compositeResponse = postRequestEvent(validateEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);
		CompositeResponse validateResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(validateResponse);	
		if (validateResponse != null && validateResponse.hasResponses()) {
			ErrorResponseEvent errorResp = (ErrorResponseEvent) MessageUtil.filterComposite(validateResponse,
					ErrorResponseEvent.class);
			//sendToErrorPage(aRequest, errorResp.getMessage());
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorResp.getMessage()));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.EDIT);
		}

		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		locationForm.clear();
		locationForm.clearAddress();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		HashMap keyMap = new HashMap();
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.next", "next");
		return keyMap;
	}
	
}
