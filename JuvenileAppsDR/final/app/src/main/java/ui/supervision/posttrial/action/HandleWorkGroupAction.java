//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\action\\DisplayCaseHistoryListAction.java

package ui.supervision.posttrial.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.posttrial.form.CSCDTaskForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class HandleWorkGroupAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public HandleWorkGroupAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CSCDTaskForm cscdTForm = (CSCDTaskForm) aForm;

		Iterator workgroupIter = cscdTForm.getWorkgroupList().iterator();
		WorkGroupResponseEvent wgre = null;
		while (workgroupIter.hasNext()){
		    wgre = (WorkGroupResponseEvent)workgroupIter.next();
		    if(cscdTForm.getWorkgroupId().equalsIgnoreCase(wgre.getWorkgroupId())){
		    	break;
		    }
		}
		if(wgre != null){
			cscdTForm.setTaskTo(wgre.getWorkgroupName());
			cscdTForm.setWorkgroupId(wgre.getWorkgroupId());
			cscdTForm.setSelectedValue( "" );
		}
		cscdTForm.setTaskDueDateStr("");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 2);
			
		Date today = DateUtil.stringToDate(DateUtil.dateToString(calendar.getTime(), DateUtil.DATE_FMT_1), 
					DateUtil.DATE_FMT_1);
		
		cscdTForm.setTaskDueDateStr(DateUtil.dateToString(today, DateUtil.DATE_FMT_1));
		
		cscdTForm.setTaskSubject("");
		cscdTForm.setTaskSeverityLevelId("");
		cscdTForm.setTaskNextActionGroupId("");
		cscdTForm.setTaskNextActionId("");
		cscdTForm.setTaskText("");
		
		return aMapping.findForward(UIConstants.NEXT);
	}
}
