/*
 * Created on Mar 19, 2007
 *
 */
package ui.supervision.adminstaff.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.Casenote;
import ui.supervision.adminstaff.Position;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffForm;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayPositionAssignCasenoteAction extends JIMSBaseAction {
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.selectUser", "selectUser");
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		//AdminStaffSearchForm myForm=(AdminStaffSearchForm)aForm;
		//myForm.clearUserSearch();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward selectUser(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String prepopulatedCasenote=" assigned to supervisee(s).";
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		String strForward=UIConstants.SUCCESS;
		AdminStaffSearchForm mySearchForm=(AdminStaffSearchForm)getSessionForm(aMapping, aRequest,UIConstants.ADMIN_STAFF_SEARCH_FORM,true);
		myForm.setSecondaryAction("");
		Casenote myCasenote=new Casenote();
		Date myDate=new Date();
		myCasenote.setCasenoteDate(myDate);
		myCasenote.setCasenoteTime(DateUtil.dateToString(myDate,UIConstants.TIME24_FMT_1));
		myCasenote.setContextType("Good Context");
		
		myForm.setCasenote(myCasenote);
		Position myPos=UIAdminStaffHelper.findPositionFromUserInList(myForm.getSelectedValue(), mySearchForm.getFoundUsers());
		if(myPos!=null && myPos.getUser()!=null ){
			myForm.setReassignedUser(myPos.getUser());
			if(myForm.getReassignedUser().getUserName()!=null && !myForm.getReassignedUser().getUserName().getFormattedName().equals("")){
				myCasenote.setCasenoteText(myForm.getReassignedUser().getUserName().getFormattedName() + prepopulatedCasenote);
			}
			else{
				myCasenote.setCasenoteText("NO OFFICER " + prepopulatedCasenote);
			}
			
		}
		else{
			//sendToErrorPage(aRequest,UIConstants.ERROR_EVENT)
			strForward=UIConstants.FAILURE;
		}
		/* 
		  if(!(myForm.getPosition().isHasCaseload())){
			myForm.setSecondaryAction(UIConstants.SUMMARY);
			strForward=UIConstants.SUCCESS_SUMMARY;		
		}*/
		
		return aMapping.findForward(strForward);
	}
}
