/**
 * 
 */
package ui.supervision.administersupervisee.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administersupervisee.GetSuperviseeDataEvent;
import messaging.administersupervisee.GetSuperviseeHistoryEvent;
import messaging.administersupervisee.reply.SuperviseeDetailResponseEvent;
import messaging.administersupervisee.reply.SuperviseeHistoryResponseEvent;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.organization.GetProgramUnitEvent;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import messaging.transferobjects.OrganizationTO;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.StringUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administersupervisee.form.SuperviseeCreditDataControlForm;
import ui.supervision.adminstaff.UIAdminStaffHelper;

/**
 * @author cc_cwalters
 *
 */
public class DisplaySuperviseeCreditDataControlAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	@Override
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub
		
		keyMap.put("button.link", "link");
		keyMap.put("button.submit", "search");
		keyMap.put("button.update", "update");
		keyMap.put("button.correct", "correct");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.next", "next");
	}
	
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   SuperviseeCreditDataControlForm this_form = 
			   	(SuperviseeCreditDataControlForm)aForm;
		   
		   this_form.clear();
		   
		   this_form.setUserAgencyId(SecurityUIHelper.getUserAgencyId());
		   return aMapping.findForward("displaySearch");
	   }


	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward search(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   SuperviseeCreditDataControlForm this_form = (SuperviseeCreditDataControlForm)aForm;
		   
		   		//pad spn with 0s
		   this_form.setSpn(UICommonSupervisionHelper.padSpn(this_form.getSpn()));
		   
		   		//retrieve supervisee data
		   GetSuperviseeDataEvent supervisee_data_event = new GetSuperviseeDataEvent();
		   supervisee_data_event.setSuperviseeId(this_form.getSpn());
		   
		   SuperviseeDetailResponseEvent supervisee_response = 
			   (SuperviseeDetailResponseEvent)
			   		MessageUtil.postRequest(supervisee_data_event, 
			   							SuperviseeDetailResponseEvent.class);

		   if (supervisee_response != null)
		   {
			  //Defect 67846 Historical information should be displayed on inactive supervisees.
			   /*if (StringUtil.isEmpty(
					   supervisee_response.getAssignedProgramUnitId()))
			   {
				   		//return to search page if supervisee has not been assigned to program unit 
				   sendToErrorPage(aRequest,"error.supervisee.no.program.unit");
				   return aMapping.findForward("displaySearch");				   
			   }
			   else
			   {*/
			   		//set form display elements
				   Name supervisee_name = new Name();
				   supervisee_name.setFirstName(supervisee_response.getFirstName());
				   supervisee_name.setLastName(supervisee_response.getLastName());
				   supervisee_name.setMiddleName(supervisee_response.getMiddleName());		   
				   this_form.setSuperviseeName(supervisee_name);
				   
				   this_form.setFirstName(supervisee_response.getFirstName());
				   this_form.setLastName(supervisee_response.getLastName());
				   this_form.setMiddleName(supervisee_response.getFirstName());		   
				   this_form.setSex(supervisee_response.getSexId());
				   this_form.setSsn(supervisee_response.getSsn());
				   this_form.setDateOfBirth(supervisee_response.getDateOfBirth());		   
				   this_form.setDateOfBirthStr(
						   DateUtil.dateToString(
								   supervisee_response.getDateOfBirth(), 
								   		DateUtil.DATE_FMT_1));
				   
				   this_form.setCurrentlySupervised(supervisee_response.isCurrentlySupervised());
				   if (supervisee_response.isCurrentlySupervised()){
					   this_form.setProgramUnitId(supervisee_response.getAssignedProgramUnitId());		   
					   this_form.setSelectedDivisionPgmUnitId(supervisee_response.getAssignedProgramUnitId());
					   this_form.setProgramUnitName(supervisee_response.getAssignedProgramUnitName());
				   
					   this_form.setProgramUnitAssignDate(
						   DateUtil.dateToString(
								   supervisee_response.getProgramUnitAssignmentDate(), 
								   		DateUtil.DATE_FMT_1));
					   this_form.setWorkloadCreditPositionId(supervisee_response.getCaseloadCreditStaffPositionId());
					   this_form.setSelectedOfficerId(supervisee_response.getCaseloadCreditStaffPositionId());
					   this_form.setWorkloadCreditStaffPositionName(supervisee_response.getCaseloadCreditStaffPositionName());
					   this_form.setCreateDate(DateUtil.dateToString(
								   supervisee_response.getCreateDate(), 
								   		DateUtil.DATE_FMT_1));
				   } else {
					   this_form.setProgramUnitId(null);		   
					   this_form.setSelectedDivisionPgmUnitId(null);
					   this_form.setProgramUnitName(null);
				   
					   this_form.setProgramUnitAssignDate(null);
					   this_form.setWorkloadCreditPositionId(null);
					   this_form.setSelectedOfficerId(null);
					   this_form.setWorkloadCreditStaffPositionName(null);
					   this_form.setCreateDate(null);
				   }
				   
						//sort history records chronologically and remove the most recent update
				   List<SuperviseeHistoryResponseEvent> 
				   			supervisee_history_responses = 
				   					supervisee_response.getSuperviseeHistory();
				   
				   	//retrieve names of program units and officers in history records
				   int num_history = supervisee_history_responses.size();
				   List filteredHistory = new ArrayList();
				   for (int i=0;i<num_history;i++)
				   {
					   SuperviseeHistoryResponseEvent 
					   		this_history = supervisee_history_responses.get(i);
					   
					    if (this_history.getType().equalsIgnoreCase(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PU)
					    		|| this_history.getType().equalsIgnoreCase(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_STAFF)){
							//retrieve program unit name
					    	if (!StringUtil.isEmpty(this_history.getAssignedProgramUnitId()))
					    	{
					    		GetProgramUnitEvent program_unit_hist_event = new GetProgramUnitEvent();
					    		program_unit_hist_event.setProgramUnitId(this_history.getAssignedProgramUnitId());
					    		GetProgramUnitResponseEvent program_unit_hist_response = 
					    			(GetProgramUnitResponseEvent)
										MessageUtil.postRequest(program_unit_hist_event, 
											GetProgramUnitResponseEvent.class);
							
					    		String program_unit_name = 
					    			(program_unit_hist_response.getOrganizationTO() != null)
										? program_unit_hist_response.
											getOrganizationTO().getDescription():"" ;
											this_history.setAssignedProgramUnitName(program_unit_name);
					    	}
						
							//retrieve officer names
					    	if (!StringUtil.isEmpty(this_history.getCaseloadCreditStaffPositionId()))
					    	{
					    		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
					    		ev.setStaffPositionId(this_history.getCaseloadCreditStaffPositionId());
					    		ev.setOfficerNameNeeded(true);
					    		LightCSCDStaffResponseEvent staffResponseEvent = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);

					    		String officer_name = (staffResponseEvent != null)? staffResponseEvent.getOfficerNameQualifiedByPosition():"" ;											
					    		this_history.setCaseloadCreditStaffPositionName(officer_name);					
					    	}
					    	filteredHistory.add(this_history);
					    }
				   }
					
				   if (filteredHistory.size() > 0){
					   Collections.sort(filteredHistory, 
							SuperviseeHistoryResponseEvent.superviseeHistoryComparator);
					   filteredHistory.remove(filteredHistory.size()-1);
		   			}
				   this_form.setSuperviseeHistory(filteredHistory);
				   
				   	//display search results screen
				   return aMapping.findForward("searchResults");			   				   
//			   }

		   }
		   else	
		   {		//supervisee not found
			   sendToErrorPage(aRequest,"error.no.search.results.found");
			   
			   		//return to search screen
			   return aMapping.findForward("displaySearch");			   			   
		   }

	   }
	   
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   SuperviseeCreditDataControlForm this_form = (SuperviseeCreditDataControlForm)aForm;
		   this_form.setUpdateAction("Update");
		   
		   	//retrieve division program hierarchy
		   if (this_form.getDivisionPgmUnitsList() == null 
				   || this_form.getDivisionPgmUnitsList().isEmpty())
		   {
			   this_form.setDivisionPgmUnitsList(
						new ArrayList( UIAdminStaffHelper.getActiveOrganizationalHeirarchy() ));
			}	
		   		   
		   return aMapping.findForward("updateProgramUnit");
	   }

	   /**
	    * Retrieve specified history record
	    * @param thisForm
	    */
	   private SuperviseeCreditDataControlForm
	   				getHistoryRecord(SuperviseeCreditDataControlForm thisForm)
	   {
	   		//retrieve selected history record
		   GetSuperviseeHistoryEvent get_hist_event = new GetSuperviseeHistoryEvent();
		   get_hist_event.setSuperviseeHistoryId(thisForm.getSelectedSuperviseeHistoryId());		   
		   SuperviseeHistoryResponseEvent 
		   		hist_response = (SuperviseeHistoryResponseEvent)
		   			MessageUtil.postRequest(get_hist_event, 
		   							SuperviseeHistoryResponseEvent.class); 
		   
		   		//set history response on form
		   thisForm.setProgramUnitId(hist_response.getAssignedProgramUnitId());
		   thisForm.setWorkloadCreditPositionId(hist_response.getCaseloadCreditStaffPositionId());
		   thisForm.setSelectedDivisionPgmUnitId(hist_response.getAssignedProgramUnitId());
		   thisForm.setSelectedOfficerId(hist_response.getCaseloadCreditStaffPositionId());
		   thisForm.setProgramUnitAssignDate(hist_response.getProgramUnitAssignmentDate());		   
		   
				//retrieve officer name
			if (!StringUtil.isEmpty(hist_response.getCaseloadCreditStaffPositionId()))
			{
	    		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
	    		ev.setStaffPositionId(hist_response.getCaseloadCreditStaffPositionId());	
	    		ev.setOfficerNameNeeded(true);
	    		LightCSCDStaffResponseEvent staffResponseEvent = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
	    		
				String officer_name = (staffResponseEvent != null)? staffResponseEvent.getOfficerNameQualifiedByPosition():"" ;								
				hist_response.setCaseloadCreditStaffPositionName(officer_name);	
				thisForm.setWorkloadCreditStaffPositionName(officer_name);
			}				
		   
			return thisForm;
	   }//end of getHistoryRecord()
	   
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward correct(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   SuperviseeCreditDataControlForm this_form = (SuperviseeCreditDataControlForm)aForm;
		   
		   if (!StringUtil.isEmpty(this_form.getSelectedSuperviseeHistoryId()) )
		   {
			   this_form.setUpdateAction("Correct");
			   
			   		//retrieve selected history record
			   this_form = getHistoryRecord(this_form);
			   
			   	//retrieve division program hierarchy
			   if (this_form.getDivisionPgmUnitsList() == null 
					   || this_form.getDivisionPgmUnitsList().isEmpty())
			   {
				   this_form.setDivisionPgmUnitsList(
							new ArrayList( UIAdminStaffHelper.getActiveOrganizationalHeirarchy() ));
				}	
			   		//proceed to update program unit flow of correct history
			   return aMapping.findForward("updateProgramUnit");
		   }
		   else
		   {
			   		//return to search results screen
			   return aMapping.findForward("searchResults");
		   }			   		   		   
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
		   		//retrieve program unit name
		   SuperviseeCreditDataControlForm this_form = (SuperviseeCreditDataControlForm)aForm;
		   GetProgramUnitEvent get_program_unit_event = new GetProgramUnitEvent();
		   get_program_unit_event.setProgramUnitId(this_form.getSelectedDivisionPgmUnitId());
		   GetProgramUnitResponseEvent program_unit_response = (GetProgramUnitResponseEvent)
			    MessageUtil.postRequest(get_program_unit_event, 
			    		GetProgramUnitResponseEvent.class);
		   
		   		//set program unit name
		   OrganizationTO prog_unit = program_unit_response.getOrganizationTO();
		   String prog_unit_name = (prog_unit != null)?prog_unit.getDescription():"";
		   this_form.setSelectedDivisionPgmUnitName(prog_unit_name);
		   
			if (!StringUtil.isEmpty(this_form.getWorkloadCreditPositionId()))
			{
			   		//retrieve positions for given program unit
				GetCSCDSupervisionStaffEvent 
					get_positions_event = new GetCSCDSupervisionStaffEvent();
	
				get_positions_event.setAgencyId(SecurityUIHelper.getUserAgencyId());
				get_positions_event.setProgramUnitId(
						this_form.getSelectedDivisionPgmUnitId());
				List prog_unit_positions = 
					MessageUtil.postRequestListFilter(
							get_positions_event, 
								CSCDSupervisionStaffResponseEvent.class);
				
					//add details of current workload position if program unit changed
				if ((this_form.getSelectedDivisionPgmUnitId() != null) &&
						!this_form.getSelectedDivisionPgmUnitId().equals(
								this_form.getProgramUnitId()))
				{
					if (!StringUtil.isEmpty(this_form.getWorkloadCreditPositionId()))
					{
						GetCSCDSupervisionStaffEvent get_position_event = new GetCSCDSupervisionStaffEvent();
						get_position_event.setStaffPositionId(this_form.getWorkloadCreditPositionId());
						CSCDSupervisionStaffResponseEvent position_response = 
							(CSCDSupervisionStaffResponseEvent)
								MessageUtil.postRequest(get_position_event, 
									CSCDSupervisionStaffResponseEvent.class);

						if (position_response != null)
						{
							//add current workload position to list from newly selected program unit
							prog_unit_positions.add(position_response);
						}
					}
				}
				
					//sort positions and add to form
				Collections.sort(prog_unit_positions, 
						CSCDSupervisionStaffResponseEvent.staffnameComparator);
				this_form.setProgramUnitPositions(prog_unit_positions);
			   
					//move to update workload credit position
				return aMapping.findForward("updateOfficer");
			}
			else
			{		//skip to summary screen if not workload credit assigned
				return aMapping.findForward("summary");
			}
	   }	
	   
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   SuperviseeCreditDataControlForm this_form = (SuperviseeCreditDataControlForm)aForm;
		   
		   if (!StringUtil.isEmpty(this_form.getSelectedSuperviseeHistoryId()) )
		   {

			   this_form.setUpdateAction("Delete");
	
		   		//retrieve selected history record
			   this_form = getHistoryRecord(this_form);
			   
			   return aMapping.findForward("delete");
		   }
		   else
		   {
			   		//return to search results screen
			   return aMapping.findForward("searchResults");
		   }			   		   		   
		   
	   }	   
}
