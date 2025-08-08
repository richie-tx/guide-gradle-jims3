//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\medical\\action\\SubmitMedicalHospitalizationUpdateAction.java

package ui.juvenilecase.medical.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.CreateMedicalHospitalizationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.medical.UIJuvenileProfileMedicalHelper;
import ui.juvenilecase.medical.form.MedicalForm;


public class SubmitMedicalHospitalizationUpdateAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 462CE3DD0331
    */
   public SubmitMedicalHospitalizationUpdateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 462CBCAE0274
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	MedicalForm medForm = (MedicalForm) aForm;
   	MedicalForm.Hospitalization hosp = medForm.getHospRec();
   	CreateMedicalHospitalizationEvent createEvt = (CreateMedicalHospitalizationEvent)EventFactory.getInstance(JuvenileControllerServiceNames.CREATEMEDICALHOSPITALIZATION);
   	createEvt.setJuvenileNum(medForm.getJuvenileNum());
   	createEvt.setEntryDate(new Date());
   	createEvt.setAdmissionDate(DateUtil.stringToDate(hosp.getAdmissionDate(), DateUtil.DATE_FMT_1));
   	createEvt.setAdmissionTypeId(hosp.getAdmissionTypeId());
   	createEvt.setFacilityName(hosp.getFacilityName());
   	createEvt.setHospitalizationReason(hosp.getHospitalizationReason());
   	createEvt.setReleaseDate(DateUtil.stringToDate(hosp.getReleaseDate(), DateUtil.DATE_FMT_1));
   	createEvt.setPhysicianName(hosp.getAdmittingPhysicianName());
   	createEvt.setPhysicianPhone(hosp.getPhysicianPhone());
   	createEvt.setAdmitYear(medForm.getAdmitYear());
   	createEvt.setLengthOfStay(medForm.getHospitalLengthOfStay());
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(createEvt);	   		
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	// Perform Error handling
	MessageUtil.processReturnException(response); 
	medForm.setActionType("confirm");
	medForm.setConfirmMessage("Hospitalization Information successfully added.");
	ActionForward forward = aMapping.findForward(UIConstants.FINISH);
    return forward;
   }
   public ActionForward returnToMedical(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
   		MedicalForm medForm = (MedicalForm) aForm;   
   		if(!medForm.getActionType().equalsIgnoreCase("view"))
   			medForm.setHospitalizationList(UIJuvenileProfileMedicalHelper.getHospitalizationList(medForm));
   		medForm.setConfirmMessage("");
   		ActionForward forward = aMapping.findForward(UIConstants.RETURN_SUCCESS);
   		return forward;
	}
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

   public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);		
		return forward;
	}
   
   protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");	
		keyMap.put("button.returnToMedical", "returnToMedical");
	}	
}
