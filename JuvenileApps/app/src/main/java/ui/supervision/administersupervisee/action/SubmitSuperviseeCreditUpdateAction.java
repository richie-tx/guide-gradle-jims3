package ui.supervision.administersupervisee.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.UpdateSuperviseeEvent;
import messaging.administersupervisee.UpdateSuperviseeHistoryEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administersupervisee.form.SuperviseeCreditDataControlForm;

public class SubmitSuperviseeCreditUpdateAction extends JIMSBaseAction {

	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		// TODO Auto-generated method stub
		keyMap.put("button.next", "next");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.backToSearch", "backToSearch");		
	}

	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   return aMapping.findForward("summary");
	   }
	   
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   SuperviseeCreditDataControlForm this_form = (SuperviseeCreditDataControlForm)aForm;
		   
		   if (this_form.getUpdateAction().equals("Update"))
		   {
		   			//create and populate event for updating supervisee
			   UpdateSuperviseeEvent update_supervisee_event = new UpdateSuperviseeEvent();
			   update_supervisee_event.setDefendantId(this_form.getSpn());
			   update_supervisee_event.setUpdateType(CaseloadConstants.SUPERVISEE_UPDATE_CREDIT);
			   update_supervisee_event.setAssignedProgramUnitId(this_form.getSelectedDivisionPgmUnitId());
			   update_supervisee_event.setProgramUnitAssignmentDate(
					   DateUtil.stringToDate(this_form.getProgramUnitAssignDate(), 
							   DateUtil.DATE_FMT_1));
			   update_supervisee_event.setCaseloadCreditStaffPositionId(this_form.getSelectedOfficerId());
			   
			   		//update supervisee entity 
			   MessageUtil.postRequest(update_supervisee_event);
			   
		   }
		   else		//correct supervisee history record
			   if (this_form.getUpdateAction().equals("Correct"))
			   {
				   		//update history record with the specified data
				   UpdateSuperviseeHistoryEvent hist_event = 
					   	new UpdateSuperviseeHistoryEvent(); 
				   hist_event.setUpdateAction("Update");
				   hist_event.setSuperviseeHistoryId(this_form.getSelectedSuperviseeHistoryId());
				   hist_event.setAssignedProgramUnitId(this_form.getSelectedDivisionPgmUnitId());
				   hist_event.setCaseloadCreditStaffPositionId(this_form.getSelectedOfficerId());
				   hist_event.setProgramUnitAssignmentDate(this_form.getProgramUnitAssignDate());				   
				   MessageUtil.postRequest(hist_event);
			   }
			   else		//delete supervisee history record
				   if (this_form.getUpdateAction().equals("Delete"))
				   {
					   		//delete specified history record
					   UpdateSuperviseeHistoryEvent hist_event = 
						   	new UpdateSuperviseeHistoryEvent(); 
					   hist_event.setUpdateAction("Delete");
					   hist_event.setSuperviseeHistoryId(this_form.getSelectedSuperviseeHistoryId());				   
					   MessageUtil.postRequest(hist_event);
				   }		   
		   return aMapping.findForward("finish");
	   }	
	   
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   SuperviseeCreditDataControlForm this_form = (SuperviseeCreditDataControlForm)aForm;
		   this_form.clear();
		   
		   return aMapping.findForward("backToSearch");
	   }	   
}
