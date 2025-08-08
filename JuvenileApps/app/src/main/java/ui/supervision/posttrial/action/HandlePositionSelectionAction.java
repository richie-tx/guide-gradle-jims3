//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\posttrial\\action\\HandlePositionSelectionAction.java

package ui.supervision.posttrial.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
import ui.supervision.adminstaff.Position;
import ui.supervision.posttrial.form.CSCDTaskForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class HandlePositionSelectionAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public HandlePositionSelectionAction() {

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
		CSCDTaskForm ctForm = (CSCDTaskForm) aForm;
		String taskToStr = "";
		if (ctForm.getFoundUsers() != null){
			Iterator fuIter = ctForm.getFoundUsers().iterator();
			Position pos = null;
			Position sub1pos = null;
			Position sub2pos = null;
			while (fuIter.hasNext() && taskToStr.equals("")){
			    pos = (Position)fuIter.next();
			    if(pos.getPositionId().equalsIgnoreCase(ctForm.getSelectedValue())){
			    	taskToStr = pos.getUser().getUserName().getLastName() + ", " +  pos.getUser().getUserName().getFirstName() + " " + pos.getUser().getUserName().getMiddleName() + " | " + pos.getPositionName();
			    	taskToStr.trim(); 
			    	break;
			    }
			    if (pos.getSubordinates() != null){
				    Iterator sub1Iter = pos.getSubordinates().iterator();
				    while (sub1Iter.hasNext() && taskToStr.equals("")){
				    	sub1pos = (Position)sub1Iter.next();
					    if(sub1pos.getPositionId().equalsIgnoreCase(ctForm.getSelectedValue())){
					    	taskToStr = sub1pos.getUser().getUserName().getLastName() + ", " +  sub1pos.getUser().getUserName().getFirstName() + " " + sub1pos.getUser().getUserName().getMiddleName() + " | " + sub1pos.getPositionName();
					    	taskToStr.trim(); 
					    	break;
					    }
					    if (sub1pos.getSubordinates() != null){
						    Iterator sub2Iter = pos.getSubordinates().iterator();
						    while (sub2Iter.hasNext()){
						    	sub2pos = (Position)sub2Iter.next();
							    if(sub2pos.getPositionId().equalsIgnoreCase(ctForm.getSelectedValue())){
							    	taskToStr = sub2pos.getUser().getUserName().getLastName() + ", " +  sub2pos.getUser().getUserName().getFirstName() + " " + sub2pos.getUser().getUserName().getMiddleName() + " | " + sub2pos.getPositionName();
							    	taskToStr.trim(); 
							    	break;
							    }
						    }
					    }    
				    }
			    } 
			}	    
		}
		
		ctForm.setTaskTo(taskToStr);
		ctForm.setTaskDueDateStr("");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 2);
			
		Date today = DateUtil.stringToDate(DateUtil.dateToString(calendar.getTime(), DateUtil.DATE_FMT_1), 
					DateUtil.DATE_FMT_1);
		
		ctForm.setTaskDueDateStr(DateUtil.dateToString(today, DateUtil.DATE_FMT_1));
		
		ctForm.setTaskSubject("");
		ctForm.setTaskSeverityLevelId("");
		ctForm.setTaskNextActionGroupId("");
		ctForm.setTaskNextActionId("");
		ctForm.setTaskText("");
					
		
		
		return aMapping.findForward(UIConstants.NEXT);
	}
}
