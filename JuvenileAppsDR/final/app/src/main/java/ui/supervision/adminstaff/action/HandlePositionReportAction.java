/*
 * Created on Mar 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.adminstaff.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.GetStaffPositionHistoryEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffReportForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandlePositionReportAction extends JIMSBaseAction {

	
	protected void addButtonMapping(Map keyMap) {
		
		keyMap.put("button.print", "print");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.resetFilter", "resetFilter");
		keyMap.put("button.view", "retiredPosition");
	}
	
	public ActionForward submit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffReportForm myReportForm=(AdminStaffReportForm)aForm;
		GetStaffPositionHistoryEvent staffHistoryEvent = (GetStaffPositionHistoryEvent) EventFactory
		.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONHISTORY);
		UIAdminStaffHelper.populateGetStaffPosHistoryEvent(myReportForm,staffHistoryEvent);
		CompositeResponse myResp=postRequestEvent(staffHistoryEvent);
		ArrayList list=UIAdminStaffHelper.processReportResults(myResp);
		if(list==null || list.size()<1){
			sendToErrorPage(aRequest,"error.no.search.results.found");
		}
		myReportForm.setFilteredList(list);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward resetFilter(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffReportForm myReportForm=(AdminStaffReportForm)aForm;
		myReportForm.resetFilterSearch();
		myReportForm.setFilteredList(myReportForm.getPositionList());
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward print(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		// Currently this method does nothing, place holder in case of future printing needs
		return aMapping.findForward("print");
	}
	
	public ActionForward retiredPosition(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		AdminStaffReportForm myReportForm=(AdminStaffReportForm)getSessionForm(aMapping,aRequest,UIConstants.ADMIN_STAFF_REPORT_FORM,true);
		myReportForm.clearAll();
		myReportForm.setAction(UIConstants.VIEW);
		myReportForm.setReportType(UIAdminStaffHelper.REPORT_TYPE_POSITION);
		       
	    myReportForm.resetDatesForSearch();
	    
		GetStaffPositionHistoryEvent staffHistoryEvent = (GetStaffPositionHistoryEvent) EventFactory
		.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONHISTORY);
		staffHistoryEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		staffHistoryEvent.setSearchkeyId(myReportForm.getPositionId());
	    staffHistoryEvent.setPositionStatusId(myReportForm.getPositionStatusId());
	    staffHistoryEvent.setReportType(UIAdminStaffHelper.REPORT_TYPE_POSITION);
	    
		CompositeResponse myResp=postRequestEvent(staffHistoryEvent);
		ArrayList list=UIAdminStaffHelper.processReportResults(myResp);
		if(list==null || list.size()<1){
			sendToErrorPage(aRequest,"error.no.search.results.found");
		}
		myReportForm.setFilteredList(list);
		myReportForm.setPositionList(list);
		
		return aMapping.findForward("retiredPosition");
	}
	
	
	
}
