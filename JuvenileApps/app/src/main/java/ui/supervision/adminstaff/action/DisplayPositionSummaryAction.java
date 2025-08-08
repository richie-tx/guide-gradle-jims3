/*
 * Created on Mar 15, 2007
 *
 */
package ui.supervision.adminstaff.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.adminstaff.Position;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffForm;

/**
 * @author jjose
 *
 */
public class DisplayPositionSummaryAction extends JIMSBaseAction {
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.submit", "next");
	}
	
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		if(myForm.getAction().equals(UIConstants.CREATE) || myForm.getAction().equals(UIConstants.UPDATE)){
			if(myForm.getSelectedValue()!=null && !myForm.getSelectedValue().equals("")){
				Position pos=UIAdminStaffHelper.findPositionInList(myForm.getSelectedValue(),myForm.getAvailableSupervisors());
				myForm.setUpdatedSupervisor(pos);
				Position thisPos = myForm.getPosition();
				thisPos.setSupervisor(pos);
			}
		}
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
}// END CLASS
