/*
 * Created on Dec 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.csserviceprovider.GetIncarcerationProgramEvent;
import messaging.csserviceprovider.GetProgramUnitProgramEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import messaging.csserviceprovider.reply.DuplicateServiceProviderProgramResponseEvent;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import messaging.transferobjects.OrganizationTO;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerServiceProviderConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerserviceprovider.CSC.AdminServiceProviderHelper;
import ui.supervision.administerserviceprovider.CSC.ServiceProviderLightProgramBean;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderCSCDForm;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderProgramForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSCProgramUpdateAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.view","view");
		keyMap.put("button.viewLink","viewLink");
		keyMap.put("button.viewDetails", "viewDetails");
		keyMap.put("button.create","create");
		keyMap.put("button.update","update");
		keyMap.put("button.activate","activate");
		keyMap.put("button.inactivate","inactivate");
		keyMap.put("button.suspend","suspend");
		keyMap.put("button.investigate","investigate");
		keyMap.put("button.next","next");
		keyMap.put("button.saveAndContinue","saveNContinue");
		
		keyMap.put("button.transfer","continueToLocation");
		keyMap.put("button.continue","continueFromLocation");
		
		keyMap.put("button.getDivisionProgramUnits", "getDivisionProgramUnits");
	}
	
	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		String forward = "";
		if (myForm.getSecondaryAction()!= null){
			if (myForm.getSecondaryAction().equals(UIConstants.SUMMARY)){
				forward = UIConstants.SUCCESS;			
			}else{
				forward = UIConstants.BACK;	
			}
		}else{
			forward = UIConstants.BACK;	
		}
		return aMapping.findForward(forward);
	}

	
	public ActionForward continueToLocation(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		// set up and forward to location form
		return aMapping.findForward("toLocation");
	}
	
	public ActionForward continueFromLocation(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		// update the location drop down and return -- don't have to do anything to do that its done in the form when the getter is called.
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward update(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.UPDATE);
		myForm.setSecondaryAction("");
		if(loadServiceProviderProgramDetails(myForm, mySPForm.getServiceProviderId(),aRequest)){
			mySPForm.setSelectedProgram(myForm.getProgramId());
			myForm.setOrigProgramName(myForm.getName());
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		else
			return aMapping.findForward("failureDashboard");
	}
	
	
	private boolean loadServiceProviderProgramDetails(ServiceProviderProgramForm myForm, HttpServletRequest aRequest){
		
		String mySelVal=myForm.getSelectedValue();
		myForm.clear();
		
		myForm.setProgramId(mySelVal);
		String msg=AdminServiceProviderHelper.updateProgramForm(myForm);
		if(msg!=null && !msg.trim().equals("")){
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,msg);
			return false;
		}
		return true;
	}

	private boolean loadServiceProviderProgramDetails(ServiceProviderProgramForm myForm, String aServiceProvId, HttpServletRequest aRequest){
		
		String mySelVal=myForm.getSelectedValue();
		myForm.clear();
		
		myForm.setProgramId(mySelVal);
		String msg=AdminServiceProviderHelper.updateProgramForm(myForm);
		if(msg!=null && !msg.trim().equals("")){
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,msg);
			return false;
		}
		myForm.setServiceProviderId(aServiceProvId);
		return true;
		
	}
	
	public ActionForward activate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.ACTIVATE);
		myForm.setSecondaryAction("");
		if(loadServiceProviderProgramDetails(myForm, mySPForm.getServiceProviderId(), aRequest)){
			mySPForm.setSelectedProgram(myForm.getProgramId());
			myForm.setChangeToStatusDate(new Date());
			myForm.setChangeToStatusId(PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE);
			myForm.setChangeToStatusReason("");
			if(AdminServiceProviderHelper.hasAdminContact((List)mySPForm.getContacts())){
				return aMapping.findForward(UIConstants.SUCCESS);
			}
			else{
				sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"An active administrative contact must exist to activate programs");
				return aMapping.findForward("failureDashboard");
			}
		}
		else return aMapping.findForward("failureDashboard");
	}
	
	public ActionForward inactivate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.INACTIVATE);
		myForm.setSecondaryAction("");
		if(loadServiceProviderProgramDetails(myForm, mySPForm.getServiceProviderId(),aRequest)){
			mySPForm.setSelectedProgram(myForm.getProgramId());
			myForm.setChangeToStatusDate(new Date());
			myForm.setChangeToStatusId(PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE);
			myForm.setChangeToStatusReason("");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		else return aMapping.findForward("failureDashboard");
	}
	
	public ActionForward suspend(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.SUSPEND);
		myForm.setSecondaryAction("");
		if(loadServiceProviderProgramDetails(myForm, mySPForm.getServiceProviderId(),aRequest)){
			mySPForm.setSelectedProgram(myForm.getProgramId());
			myForm.setChangeToStatusDate(new Date());
			myForm.setChangeToStatusId(PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED);
			myForm.setChangeToStatusReason("");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		else return aMapping.findForward("failureDashboard");
	}
	
	public ActionForward investigate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.INVESTIGATE);
		myForm.setSecondaryAction("");
		if(loadServiceProviderProgramDetails(myForm, mySPForm.getServiceProviderId(),aRequest)){
			mySPForm.setSelectedProgram(myForm.getProgramId());
			myForm.setChangeToStatusDate(new Date());
			myForm.setChangeToStatusId(PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION);
			myForm.setChangeToStatusReason("");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		else return aMapping.findForward("failureDashboard");
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward("cancelDashboard");
	}
	
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}

	public ActionForward getDivisionProgramUnits(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		
		List program_unit_list = new ArrayList();
		if (myForm.getSelectedDivision() !=  null
				&& !myForm.getSelectedDivision().equals("")){
			for(int i=0; i<myForm.getAvailableDivisions().size();i++){
				OrganizationTO org = (OrganizationTO) myForm.getAvailableDivisions().get(i);
				if(myForm.getSelectedDivision().equals(org.getOID())){
					program_unit_list = new ArrayList(org.getChildOrganizations());
					break;
				}
			}
		}
		Collections.sort(program_unit_list, GetProgramUnitResponseEvent.programUnitNameComparator);
		
		//set program units on form
		myForm.setDivisionProgramUnits(program_unit_list);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward saveNContinue(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String forward="successDashboard";
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		
		if (!StringUtil.isEmpty(myForm.getSelectedIncarcerationCondition()))
		{
				//make sure that the specified incarceration program is not on another program
			GetIncarcerationProgramEvent incarceration_prog_event = new GetIncarcerationProgramEvent();
			incarceration_prog_event.setIncarcerationConditionId(myForm.getSelectedIncarcerationCondition());
			
				//attempt to retrieve an active program with the given condition ID
			CSProgramResponseEvent 
				incarceration_program = (CSProgramResponseEvent) MessageUtil.postRequest(incarceration_prog_event, CSProgramResponseEvent.class);
			
				//disallow program update if incarceration program specified is already associated with another program
			if (!StringUtil.isEmpty(incarceration_program.getProgramId())
					&& (!incarceration_program.getProgramId().equals(myForm.getProgramId()))
				)
			{
				sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Duplicate Incarceration Program Specified");
				forward=UIConstants.SUCCESS;  // return user back to update page
				return aMapping.findForward(forward);
			}
		}
		
			//make sure that the specified program unit is not on another non-inactive program
		if (!StringUtil.isEmpty(myForm.getSelectedProgramUnit()))
		{			
			GetProgramUnitProgramEvent pup_event = new GetProgramUnitProgramEvent();
			pup_event.setProgramId(myForm.getProgramId());
			pup_event.setProgramUnitId(myForm.getSelectedProgramUnit());
			pup_event.setProgramStatus(CSAdministerServiceProviderConstants.INACTIVE_PROG_STATUS);
			
				//attempt to retrieve an active program with the program unit ID
			CSProgramResponseEvent pup_program = (CSProgramResponseEvent)MessageUtil.postRequest(pup_event,CSProgramResponseEvent.class);
			
				//disallow program update if  program unit specified is already associated with another program
			if (!StringUtil.isEmpty(pup_program.getProgramId())
					&& (!pup_program.getProgramId().equals(myForm.getProgramId()))
				)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Duplicate Program Unit Specified");
				forward=UIConstants.SUCCESS;  // return user back to update page
				return aMapping.findForward(forward);
			}
		}
		
		// check for duplicate program name for current SP
		String dupNameFound = "";
		if (UIConstants.UPDATE.equals(myForm.getAction()) && !myForm.getName().equalsIgnoreCase(myForm.getOrigProgramName()) ) { 
			List pgmInfo = new ArrayList(mySPForm.getPrograms());
			for (int p=0; p<pgmInfo.size(); p++){
				ServiceProviderLightProgramBean spp = (ServiceProviderLightProgramBean)pgmInfo.get(p);
				if (myForm.getName().equalsIgnoreCase(spp.getProgramName()) ) {
		        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Duplicate Program Name Specified");
		        	forward=UIConstants.SUCCESS;  // return user back to update page
		        	dupNameFound = "Y";
		        	break;
				}
			}	
		}
		// end duplicate check
		if (dupNameFound == "") {
	        RequestEvent myEvent=AdminServiceProviderHelper.getProgramSaveEvent(myForm);
	        CompositeResponse myResp=this.postRequestEvent(myEvent);
	        Object obj1=MessageUtil.filterComposite(myResp,DuplicateServiceProviderProgramResponseEvent.class);
	        if(obj1!=null){
	        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Duplicate Service Provider Program Specified");
	        	forward=UIConstants.SUCCESS;  // return user back to update page
	        }
	        else{
		        Object obj=MessageUtil.filterComposite(myResp,CSServiceProviderResponseEvent.class);
		        if(obj==null){
		        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"An unknown error was encountered, Program may not have been saved");
		        	forward=UIConstants.FAILURE;
		        }
		        else{
		        	CSServiceProviderResponseEvent myRespEvt=(CSServiceProviderResponseEvent)obj;
		        	if(myRespEvt.isOperationSuccessful()){
						AdminServiceProviderHelper.updateServiceProviderForm(mySPForm);
						if(UIConstants.CREATE.equals(myForm.getAction()))
							aRequest.setAttribute("confirmMsg","Program successfully created.");
						else if(UIConstants.UPDATE.equals(myForm.getAction()))
							aRequest.setAttribute("confirmMsg","Program successfully updated.");
						else if(UIConstants.INACTIVATE.equals(myForm.getAction()))
							aRequest.setAttribute("confirmMsg","Program successfully inactivated.");
						else if(UIConstants.SUSPEND.equals(myForm.getAction()))
							aRequest.setAttribute("confirmMsg","Program successfully suspended.");
						else if(UIConstants.ACTIVATE.equals(myForm.getAction()))
							aRequest.setAttribute("confirmMsg","Program successfully activated.");
						else if(UIConstants.INVESTIGATE.equals(myForm.getAction()))
							aRequest.setAttribute("confirmMsg","Program successfully placed under investigation.");
						myForm.setSecondaryAction(UIConstants.CONFIRM);
		        	}
		        	else{
		        		sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"An unknown error was encountered, Program may not have been saved");
		        		forward=UIConstants.FAILURE;
		        	}
		        }
	        }
		}     
        return aMapping.findForward(forward);
	}
	
	public ActionForward view(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.VIEW);
		myForm.setSecondaryAction("");
		if(loadServiceProviderProgramDetails(myForm, mySPForm.getServiceProviderId(),aRequest)){
			mySPForm.setSelectedProgram(myForm.getProgramId());
			return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
		}
		else
			return aMapping.findForward("failureDashboard");
	}
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward viewLink(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm programForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm spForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		
		programForm.setAction(UIConstants.VIEW);
		programForm.setSecondaryAction("");
		
		spForm.setServiceProviderId(programForm.getServiceProviderId());
		if(loadServiceProviderProgramDetails(programForm, spForm.getServiceProviderId(),aRequest))
		{
			spForm.setSelectedProgram(programForm.getProgramId());
		
	//		load Service Provider Details
			String errMsg=AdminServiceProviderHelper.updateServiceProviderForm(spForm);
			if(errMsg!=null)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
			}
			
			return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
		}
		else
		{
			return aMapping.findForward("failureDashboard");
		}
	}
	
	
	/**
	 * This method is invoked to view Program Details popup window from Program Referrals
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward viewDetails(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm programForm =(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm serviceProviderForm =(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		
		programForm.setAction(UIConstants.VIEW);
		programForm.setSecondaryAction("");
		
//		load Program Details
		if((!loadServiceProviderProgramDetails(programForm, aRequest)))
		{
			return aMapping.findForward(UIConstants.DETAIL_SUCCESS);
		}
		
//		load Service Provider Details
		serviceProviderForm.setServiceProviderId(programForm.getServiceProviderId());
		String errMsg=AdminServiceProviderHelper.updateServiceProviderForm(serviceProviderForm);
		if(errMsg!=null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
		}
		
		return aMapping.findForward(UIConstants.DETAIL_SUCCESS);
	}
	
	
	public ActionForward create(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderProgramForm myForm=(ServiceProviderProgramForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.clear();
		myForm.setServiceProviderId(mySPForm.getServiceProviderId());
		myForm.setAction(UIConstants.CREATE);
		myForm.setSecondaryAction("");
		myForm.setProgramStartDate(new Date());
		myForm.setChangeToStatusReason("New Program Created");
		myForm.setChangeToStatusDate(new Date());
		
			//set available divisions and incarceration conditions
		AdminServiceProviderHelper.setAvailableDivisions(myForm);
		AdminServiceProviderHelper.setIncarcerationConditions(myForm);
		
		if(AdminServiceProviderHelper.hasAdminContact((List)mySPForm.getContacts())){
			myForm.setChangeToStatusId(PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE);
		}
		else{
			myForm.setChangeToStatusId(PDCodeTableConstants.ASP_CS_PROGRAM_PENDING);
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

}// END CLASS
