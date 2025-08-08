/*
 * Created on Mar 19, 2007
 */
package ui.supervision.adminstaff.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.GetUsersForCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.supervision.adminstaff.Position;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;

/**
 * @author jjose
 */
public class DisplayPositionUserSearchResultsAction extends JIMSBaseAction {
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.link", "link");
	}
	
	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffSearchForm myForm=(AdminStaffSearchForm)aForm;
		myForm.clearUserSearch();
		return aMapping.findForward(UIConstants.FAILURE);
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffSearchForm myForm=(AdminStaffSearchForm)aForm;
		myForm.clearUserSearch();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	/**
	 * The <code>submit</code> dispatch helper method allows a dispatch action of type
	 * "Submit" to be processed and a view to be rendered.
	 *  
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, 
							HttpServletRequest aRequest,HttpServletResponse aResponse) {
		
		AdminStaffSearchForm myForm=(AdminStaffSearchForm)aForm;
		String mapForward=UIConstants.SEARCH_SUCCESS;
		
		ArrayList<Position> foundUsersList = null;
		
		GetUsersForCSCDSupervisionStaffEvent usersEvent = (GetUsersForCSCDSupervisionStaffEvent) EventFactory
																	.getInstance(CSCDStaffPositionControllerServiceNames.GETUSERSFORCSCDSUPERVISIONSTAFF);
		UIAdminStaffHelper.populateGetUsersForStaffEvent(myForm,usersEvent);
		
		CompositeResponse myResp = MessageUtil.postRequest(usersEvent);
		Collection<CSCDSupervisionStaffResponseEvent> users = MessageUtil.compositeToCollection(myResp, 
																		CSCDSupervisionStaffResponseEvent.class);
		if (users!=null && !users.isEmpty()) {
			foundUsersList = new ArrayList<Position>();
			List locationCodes = ComplexCodeTableHelper.getLocationCodes();
			for(CSCDSupervisionStaffResponseEvent user : users){
				foundUsersList.add(UIAdminStaffHelper.getPositionFromStaffRespEvt(user, locationCodes));
			}
		}
		myForm.setFoundUsers(foundUsersList);
		
		// Single/No result situation
		if(foundUsersList != null && foundUsersList.size() == 1){
			Position myPos = foundUsersList.get(0);
			myForm.setSelectedValue((myPos.getUser() != null) ? myPos.getUser().getUserId() : null);
		} else if (foundUsersList == null || (foundUsersList != null && foundUsersList.size() == 0)){
			sendToErrorPage(aRequest,"error.no.search.results.found");
			mapForward=UIConstants.FAILURE;
		}
		
		return aMapping.findForward(mapForward);
	}

}
