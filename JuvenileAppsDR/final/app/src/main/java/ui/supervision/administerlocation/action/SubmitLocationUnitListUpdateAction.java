// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerlocation\\action\\SubmitLocationUnitListUpdateAction.java

package ui.supervision.administerlocation.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.CreateUpdateJuvLocationUnitEvent;
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
import ui.supervision.administerlocation.UILocationUnitHelper;
import ui.supervision.administerlocation.form.LocationForm;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitLocationUnitListUpdateAction extends LookupDispatchAction {

	/**
	 * @roseuid 466466010019
	 */
	public SubmitLocationUnitListUpdateAction() {

	}

	/**
	 * method to save newly added location units.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4664621D012A
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		String agencyId = SecurityUIHelper.getUserAgencyId();//changed vj
		CreateUpdateJuvLocationUnitEvent locUnitEvent = new CreateUpdateJuvLocationUnitEvent();
		Collection newUnitList = locationForm.getNewLocUnitList();
		LocationForm.LocationUnit newLocUnit;
		if (!newUnitList.isEmpty()) {
			Iterator unitIterator = newUnitList.iterator();
			while (unitIterator.hasNext()) {
				newLocUnit = (LocationForm.LocationUnit) unitIterator.next();
				locUnitEvent.setCreate(true);
				locUnitEvent.setLocationId(locationForm.getLocationId());
				locUnitEvent.setLocationUnitName(newLocUnit.getLocationUnitName());
				locUnitEvent.setJuvUnitCd(newLocUnit.getJuvUnitCd());
				locUnitEvent.setUnitStatusCd("A");
				locUnitEvent.setPhone(newLocUnit.getPhoneNumber());
				//CompositeResponse compositeResponse = postRequestEvent(locUnitEvent);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(locUnitEvent);
				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				MessageUtil.processReturnException(compositeResponse);	
				if (compositeResponse != null && compositeResponse.hasResponses()){
					//sendToErrorPage(aRequest, "error.common");
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.common"));
					saveErrors(aRequest, errors);
					return aMapping.findForward(UIConstants.FINISH);
				}
			}
		}
		locationForm.setAction(UIConstants.CREATE);
		locationForm.clearLocationUnit();
		locationForm.setNewLocUnitList(new ArrayList());
		locationForm.setLocationUnitList(UILocationUnitHelper.getLocationUnitList(locationForm.getLocationId(),
				agencyId));
		return aMapping.findForward(UIConstants.FINISH);
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
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}

}
