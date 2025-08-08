/*
 * Created on Mar 19, 2007
 *
 */
package ui.supervision.adminstaff.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.VerifyCjadNumEvent;
import messaging.cscdstaffposition.reply.DuplicateCjadNumEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.form.AdminStaffForm;

/**
 * @author jjose
 *
 */
public class DisplayPositionAssignSummaryAction extends JIMSBaseAction {
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
	}
	
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		if(myForm.getReassignedUser()!=null){
			if (myForm.getReassignedUser().getCjad()!= null && !myForm.getReassignedUser().getCjad().equals("")){
	            VerifyCjadNumEvent requestEvent = (VerifyCjadNumEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.VERIFYCJADNUM);
	            requestEvent.setCjadNum(myForm.getReassignedUser().getCjad());
	            requestEvent.setStaffPositionId(myForm.getPosition().getPositionId());
	            requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
	            requestEvent.setUserProfileId(myForm.getReassignedUser().getUserId());
	            CompositeResponse cr=postRequestEvent(requestEvent);
	            Object errorResponse = MessageUtil.filterComposite(cr, DuplicateCjadNumEvent.class);
	            if(errorResponse!=null){
	            	sendToErrorPage(aRequest,"error.duplicateCJAD");
	            	return aMapping.findForward(UIConstants.FAILURE);
	            }
	        } 
		}
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		myForm.setSecondaryAction("");
		return aMapping.findForward(UIConstants.BACK);
	}
}
