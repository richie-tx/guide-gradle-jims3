//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderSearchAction.java

package ui.supervision.supervisionorder.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

/**
 * @author dgibler
 *  
 */
public class DisplaySupervisionOrderSearchAction extends JIMSBaseAction{

	/**
	 * @roseuid 438F23F301FA
	 */
	public DisplaySupervisionOrderSearchAction() {

	}
	protected void addButtonMapping(Map keyMap) {
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 438F22CC0018
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SupervisionOrderSearchForm orderSearchForm = (SupervisionOrderSearchForm) aForm;
		if (orderSearchForm != null) {
			orderSearchForm.clear();
		}
		Object obj = aRequest.getSession().getAttribute("supervisionOrderForm");
		if (obj != null) {
			SupervisionOrderForm supOrderForm = (SupervisionOrderForm) obj;
			
			supOrderForm.clear();
			supOrderForm.setPrimaryCaseOrderKey("");
		}
		orderSearchForm.setAction("");
		orderSearchForm.setSecondaryAction("");
		String positionCode = this.findUserPosition();
		orderSearchForm.setUserPosition(positionCode);
		orderSearchForm.setSelectedValue("SPN");  // default setting
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS, UIUtil.getCurrentUserAgencyID()));
	}
	
	public String findUserPosition(){
		String positionCode = "";
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setLogonId(SecurityUIHelper.getLogonId());		
		LightCSCDStaffResponseEvent staffResponseEvent = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);

		if(staffResponseEvent != null){
			positionCode = staffResponseEvent.getJobTitleCD();
		}
		return positionCode;	
	}
}
