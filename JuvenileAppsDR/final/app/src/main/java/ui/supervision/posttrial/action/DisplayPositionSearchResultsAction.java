//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\DisplayWorkgroupSearchResultsAction.java

package ui.supervision.posttrial.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
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
import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.Position;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;
import ui.supervision.posttrial.form.CSCDTaskForm;


/**
 * @author hrodriguez
 */
public class DisplayPositionSearchResultsAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayPositionSearchResultsAction() {

	}	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45DB5B250219
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		String forwardStr = UIConstants.SEARCH_SUCCESS;
		CSCDTaskForm cForm = (CSCDTaskForm) aForm;
		//AdminStaffSearchForm cForm=(AdminStaffSearchForm)aForm;
		ArrayList list = new ArrayList();
		GetCSCDSupervisionStaffEvent anEvent = (GetCSCDSupervisionStaffEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);
		anEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());;
	    anEvent.setCjadNum(cForm.getCjad());
		anEvent.setDivisionId(cForm.getDivisionId());
		anEvent.setPositionName(cForm.getPositionName());
		anEvent.setProgramSectionId(cForm.getProgramSectionId());
		anEvent.setProgramUnitId(cForm.getProgramUnitId());
		if(cForm.getName()!=null){
			anEvent.setStaffMiddleName(cForm.getName().getMiddleName());
			anEvent.setStaffFirstName(cForm.getName().getFirstName());
			anEvent.setStaffLastName(cForm.getName().getLastName());
		}
		anEvent.setCstsOfficerTypeId(cForm.getOfficerTypeId());
		anEvent.setStaffLogonId(cForm.getUserId());
		anEvent.setWorkGroupName(cForm.getWorkgroupName());
		
		CompositeResponse myResp=postRequestEvent(anEvent);
		Collection users = MessageUtil.compositeToCollection(myResp, CSCDSupervisionStaffResponseEvent.class);

		List locationCodes = ComplexCodeTableHelper.getLocationCodes();
		
		if (users!=null && !users.isEmpty())
		{
			Iterator iter = users.iterator();
			while (iter.hasNext())
			{
				CSCDSupervisionStaffResponseEvent staffPosRespEvt = (CSCDSupervisionStaffResponseEvent)iter.next();
				list.add(UIAdminStaffHelper.getPositionFromStaffRespEvt(staffPosRespEvt, locationCodes));
			}
		}
		cForm.setFoundUsers(list);
        cForm.setSelectedValue("");
		if(cForm.getFoundUsers()!=null && cForm.getFoundUsers().size()==1){
			Position myPos=(Position)cForm.getFoundUsers().get(0);
				if(myPos.getUser()!=null)
						cForm.setSelectedValue(myPos.getUser().getUserId());
		}
		else if(cForm.getFoundUsers()==null || cForm.getFoundUsers().size()<1){
			sendToErrorPage(aRequest,"error.no.search.results.found");
			forwardStr=UIConstants.FAILURE;
		}
        return aMapping.findForward(forwardStr);
	}

	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		//CSCDTaskForm ctForm = (CSCDTaskForm) aForm;
		AdminStaffSearchForm asForm=(AdminStaffSearchForm)aForm;		
		asForm.setUserId("");
		asForm.getName().setLastName("");
		asForm.getName().setFirstName("");
		asForm.getName().setMiddleName("");
		asForm.setCjad("");
		asForm.setPositionName("");
		asForm.setDivisionId("");
		asForm.setProgramUnitId("");
		asForm.setProgramSectionId("");
		asForm.setWorkgroupName("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

}