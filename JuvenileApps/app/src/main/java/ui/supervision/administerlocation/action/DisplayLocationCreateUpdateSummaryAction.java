//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerlocation\\action\\DisplayLocationCreateUpdateSummaryAction.java

package ui.supervision.administerlocation.action;

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
import ui.common.AddressHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.administerlocation.form.LocationForm;
import ui.supervision.administerlocation.form.LocationForm.LocationAddress;

public class DisplayLocationCreateUpdateSummaryAction extends LookupDispatchAction {

	/**
	 * @roseuid 45117C510181
	 */
	public DisplayLocationCreateUpdateSummaryAction() {

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
		//String agencyId = "JUV";
		
		if (locationForm.getAction().equals("update") && locationForm.getOldLocationName().equals(locationName) && 
				locationForm.getOldlocationCd().equals(locationCd)){
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
		if(errorMessgs!=null && !errorMessgs.isEmpty()){
			Iterator errorMessgsList = errorMessgs.iterator();
			ActionErrors errors = new ActionErrors();
			while(errorMessgsList.hasNext())
			{
				ErrorResponseEvent errorResp = (ErrorResponseEvent) errorMessgsList.next();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorResp.getMessage(), "Duplicate name"));
				saveErrors(aRequest, errors);
				}
			return aMapping.findForward("failure");
		}
		LocationAddress locationAddress = locationForm.getLocationAddress();
		AddressHelper.validateAddress(locationAddress);
		locationForm.setAddressStatus(locationAddress.getValidated());
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
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
