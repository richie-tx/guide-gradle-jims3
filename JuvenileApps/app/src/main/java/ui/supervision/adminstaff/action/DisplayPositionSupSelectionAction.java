/*
 * Created on Mar 16, 2007
 *
 */
package ui.supervision.adminstaff.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.GetSupervisorsEvent;
import messaging.cscdstaffposition.ValidateStaffPositionEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.cscdstaffposition.reply.CourtPreviouslyAssignedEvent;
import messaging.cscdstaffposition.reply.DuplicateCjadNumEvent;
import messaging.cscdstaffposition.reply.DuplicateDivisionManagerEvent;
import messaging.cscdstaffposition.reply.DuplicatePOIEvent;
import messaging.cscdstaffposition.reply.DuplicatePositionNameEvent;
import messaging.cscdstaffposition.reply.NoDivisionManagerEvent;
import messaging.cscdstaffposition.reply.NoSupervisorEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.supervision.adminstaff.Position;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffForm;

/**
 * @author jjose
 *
 */
public class DisplayPositionSupSelectionAction extends JIMSBaseAction {
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.submit", "next");
	}
	
	private String validateStaffPosition(AdminStaffForm myForm, HttpServletRequest aRequest){
		String strForward=UIConstants.SUCCESS;
		ValidateStaffPositionEvent requestEvent = (ValidateStaffPositionEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.VALIDATESTAFFPOSITION);
		UIAdminStaffHelper.populateValidateStaffPositionEvent(myForm.getPosition(),requestEvent);
		
		CompositeResponse cr=postRequestEvent(requestEvent);
		Object errorResponse=MessageUtil.compositeToCollection(cr, ErrorResponseEvent.class);
		if(errorResponse!=null){
	    	errorResponse = MessageUtil.filterComposite(cr, DuplicatePositionNameEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.duplicate.positionName");
	        	strForward=(UIConstants.FAILURE);
	        }
	    	errorResponse = MessageUtil.filterComposite(cr, DuplicateCjadNumEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.duplicateCJAD");
	        	strForward=(UIConstants.FAILURE);
	        }
	    	errorResponse = MessageUtil.filterComposite(cr, DuplicatePOIEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.duplicatePOI");
	        	strForward=(UIConstants.FAILURE);
	        }
	    	errorResponse = MessageUtil.filterComposite(cr, DuplicateDivisionManagerEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.existingStaffDivisionManager");
	        	strForward=(UIConstants.FAILURE);
	        }
	    	errorResponse = MessageUtil.filterComposite(cr, NoDivisionManagerEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.noStaffDivisionManager");
	        	strForward=(UIConstants.FAILURE);
	        }
	    	errorResponse = MessageUtil.filterComposite(cr, NoSupervisorEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.noStaffSupervisor");
	        	strForward=(UIConstants.FAILURE);
	        }
	    	/*errorResponse = MessageUtil.filterComposite(cr, NoOfficeManagerEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.noOfficeManager");
	        	strForward=(UIConstants.FAILURE);
	        }*/
	    	
	    	Collection previouslyAssignedCourts = MessageUtil.compositeToCollection(cr, CourtPreviouslyAssignedEvent.class);
	    	
	    	if(previouslyAssignedCourts != null && previouslyAssignedCourts.size() > 0){
	    	    List sortedCourts = new ArrayList();
	    	    Iterator iter = previouslyAssignedCourts.iterator();
	    	    CourtPreviouslyAssignedEvent cpa = null;
	    	    while (iter.hasNext()){
	    	        cpa = (CourtPreviouslyAssignedEvent) iter.next();
	    	        sortedCourts.add(cpa);
	    	    }
	    	    Collections.sort(sortedCourts);
	    	    iter = sortedCourts.iterator();
	    	    StringBuffer message = new StringBuffer();
	    	    if (sortedCourts.size() > 1){
	    	        message.append("Courts ");
	    	    } else {
	    	        message.append("Court ");
	    	    }
	    	    boolean firstTime = true;
	    	    while (iter.hasNext()){
	    	        cpa = (CourtPreviouslyAssignedEvent) iter.next();
	    	        if (firstTime){
	    	            firstTime = false;
	    	        } else {
	    	            message.append(", ");
	    	        }
	    	        message.append(cpa.getCourtNum());
	    	    }
	    	    message.append(" already assigned to another CLO");
	    	    this.sendToErrorPage(aRequest, "error.generic", message.toString());
	        	strForward=(UIConstants.FAILURE);
	        }

		}
		return strForward;
	}
	
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		myForm.setUpdatedSupervisor(null);
		myForm.setSelectedValue("");
		String strForward=UIConstants.SUCCESS;
		if(!myForm.getPosition().isHasCaseload()){
			myForm.getPosition().setProbOfficerInd(null);
			myForm.getPosition().setOfficerTypeId(null);
		}
		strForward=validateStaffPosition(myForm,aRequest);
		CompositeResponse cr;
		
		if(strForward.equals(UIConstants.SUCCESS)){
			if(!myForm.getPosition().getPositionTypeId().equals(PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR) ){
				
				List locationCodes = ComplexCodeTableHelper.getLocationCodes();
				
				myForm.setSelectedValue("");
				GetSupervisorsEvent supEvent = (GetSupervisorsEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.GETSUPERVISORS);
				UIAdminStaffHelper.populateGetSupervisorsEvent(myForm.getPosition(),supEvent);
				cr=postRequestEvent(supEvent);
				Collection staffPositions = MessageUtil.compositeToCollection(cr, CSCDSupervisionStaffResponseEvent.class);	
				ArrayList list=new ArrayList();
				myForm.setAvailableSupervisors(list);
				if (staffPositions!=null && !staffPositions.isEmpty())
				{
					if(myForm.getPosition()!=null && myForm.getPosition().getSupervisor()!=null && myForm.getPosition().getSupervisor().getPositionId()!=null)
					myForm.setSelectedValue(myForm.getPosition().getSupervisor().getPositionId());
					Iterator iter = staffPositions.iterator();
					while (iter.hasNext())
					{
						CSCDSupervisionStaffResponseEvent staffPosRespEvt = (CSCDSupervisionStaffResponseEvent)iter.next();
						list.add(UIAdminStaffHelper.getPositionFromStaffRespEvt(staffPosRespEvt, locationCodes));
					}
				}
				else{
					if(myForm.getPosition().getPositionTypeId().equals(PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR)){
						sendToErrorPage(aRequest,"error.noStaffDivisionManager");
					}
					else
						sendToErrorPage(aRequest,"error.noStaffSupervisor");
					strForward=UIConstants.FAILURE;
				}
				if(myForm.getPosition().getPositionTypeId().equals(PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR)
						&& list.size()==1){
					Position pos=(Position)myForm.getAvailableSupervisors().get(0);
					myForm.setUpdatedSupervisor(pos);
					myForm.getPosition().setSupervisor(pos);
					strForward=UIConstants.SUMMARY_SUCCESS;
				}  else {
					list = this.filterOutDivisionManager(list);
					myForm.setAvailableSupervisors(list);
				}		    
			}
			else{
					
				strForward=UIConstants.SUMMARY_SUCCESS;
	
			}
		}
		
		return aMapping.findForward(strForward);
	}

	private ArrayList filterOutDivisionManager(ArrayList supervisorList)
	{
		ArrayList filteredList = new ArrayList();
		if (supervisorList != null && supervisorList.size() > 0){
			Position divMgr = (Position) supervisorList.get(0);
			List childList = divMgr.getSubordinates();
			Position pos = null;
			for (int i = 0; i < childList.size(); i++)
			{
				pos = (Position) childList.get(i);
				filteredList.add(pos);
			}
			supervisorList = filteredList;
		}
		return supervisorList;
	}
	
	
}
