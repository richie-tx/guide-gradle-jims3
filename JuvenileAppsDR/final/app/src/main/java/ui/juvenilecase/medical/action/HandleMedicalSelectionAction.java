//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\medical\\action\\HandleMedicalSelectionAction.java

package ui.juvenilecase.medical.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetMedicalHealthIssueDataEvent;
import messaging.juvenile.GetMedicalHospitalizationDataEvent;
import messaging.juvenile.GetMedicalMedicationDataEvent;
import messaging.juvenile.reply.JuvenileHealthIssueResponseEvent;
import messaging.juvenile.reply.JuvenileHospitalizationResponseEvent;
import messaging.juvenile.reply.JuvenileMedicationResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.medical.UIJuvenileProfileMedicalHelper;
import ui.juvenilecase.medical.form.MedicalForm;


public class HandleMedicalSelectionAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 462CE3DC00C0
    */
   public HandleMedicalSelectionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 462CBC9902B2
    */
   public ActionForward healthIssues(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		MedicalForm med = (MedicalForm) aForm;
   		med.getHsRec().clear();
   		med.getHsRec().setAction(UIConstants.CREATE);
   		med.setHealthIssues(ComplexCodeTableHelper.getActiveHealthIssues());
   		ActionForward forward = aMapping.findForward("addHealthIssueSuccess");
	    return forward;   		
   }
   
  //added for update funtionality ER JIMS200076774 
   public ActionForward updateHealthIssues(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {   		
   		MedicalForm medForm = (MedicalForm)aForm; 
   		GetMedicalHealthIssueDataEvent reqEvent = (GetMedicalHealthIssueDataEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETMEDICALHEALTHISSUEDATA);
		reqEvent.setHealthIssueId(medForm.getSelectedValue());
	    CompositeResponse resp = postRequestEvent(reqEvent);  	  
		
		if(resp==null)
			sendToErrorPage(aRequest, "Error retrieving medical medication record.");
   		else
   		{
   			JuvenileHealthIssueResponseEvent issueResp = (JuvenileHealthIssueResponseEvent)MessageUtil.filterComposite(resp, JuvenileHealthIssueResponseEvent.class);
   			MedicalForm.HealthIssue healthIssue = medForm.getHsRec();
   			healthIssue.clear();
   			//healthIssue.clearSearchCriteria(); 
   			healthIssue.setAction(UIConstants.UPDATE);
   			//medForm.setEntryDate(new Date());
   			medForm.setEntryDate(issueResp.getEntryDate());
   			healthIssue.setHealthStatusId("I");
   			healthIssue.setHealthStatus("INACTIVE");
   			/*medication.setMedication(getMedicationName(medForm.getMedicationList(), medicResp.getMedicationTypeId()));   			
   			medication.setCurrentlyTakingMedId(medicResp.getCurrentlyTakingMedication());
   			String currentlyTakeingMed = SimpleCodeTableHelper.getDescrByCode("MEDICATION_CURRENT", medicResp.getCurrentlyTakingMedication());//added to get the description of the code
   			medication.setCurrentlyTakingMed(currentlyTakeingMed);*/
   			healthIssue.setIssueId(issueResp.getIssueId());
   			healthIssue.setIssueStatusId(issueResp.getIssueStatusId());
   			healthIssue.setConditionLevelId(issueResp.getConditionLevelId());
   			healthIssue.setConditionSeverityId(issueResp.getConditionSeverityId());
   			//healthIssue.setModificationReason(issueResp.getModificationReason());
   			medForm.setEntryDate(issueResp.getEntryDate());   			
   			
   			medForm.setHsRec(healthIssue);
   			   			
   		}
			
			ActionForward forward = aMapping.findForward("updateHealthIssueSuccess");
			return forward;
   }  
   //Ended
   
   public ActionForward addMedication(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		MedicalForm med = (MedicalForm) aForm;
   		med.getMedicRec().clear();
   		med.getMedicRec().clearSearchCriteria();
   		med.getMedicRec().setAction(UIConstants.CREATE); 
   		//med.setMedications(UIJuvenileProfileMedicalHelper.getMedicationCodes());
   		ActionForward forward = aMapping.findForward("addMedicationSuccess");
	    return forward;  
   }
   
   //added for ER 76779
   public ActionForward updateMedication(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {   		
   		MedicalForm medForm = (MedicalForm)aForm;  	    
	    GetMedicalMedicationDataEvent medEvent = (GetMedicalMedicationDataEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETMEDICALMEDICATIONDATA);
		medEvent.setMedicationId(medForm.getSelectedValue());
		CompositeResponse resp = postRequestEvent(medEvent);  	  
		
		if(resp==null)
			sendToErrorPage(aRequest, "Error retrieving medical medication record.");
   		else
   		{
   			JuvenileMedicationResponseEvent medicResp = (JuvenileMedicationResponseEvent)MessageUtil.filterComposite(resp,JuvenileMedicationResponseEvent.class);
   			MedicalForm.Medication medication = medForm.getMedicRec();
   			medication.clear();
   			medication.clearSearchCriteria(); 
   			medication.setAction(UIConstants.UPDATE);
   			//medForm.setEntryDate(new Date());
   			medForm.setEntryDate(medicResp.getEntryDate());
   			
   			medication.setMedication(getMedicationName(medForm.getMedicationList(), medicResp.getMedicationTypeId()));   			
   			medication.setCurrentlyTakingMedId(medicResp.getCurrentlyTakingMedication());
   			/*String currentlyTakeingMed = SimpleCodeTableHelper.getDescrByCode("MEDICATION_CURRENT", medicResp.getCurrentlyTakingMedication());//added to get the description of the code
   			medication.setCurrentlyTakingMed(currentlyTakeingMed);*/
   			medication.setCurrentlyTakingMedId(UIConstants.NO);
   			medication.setDosage(medicResp.getDosage());
   			medication.setFrequencyId(medicResp.getFrequencyId());
   			medication.setMedCode(medicResp.getMedicationTypeId());
   			medication.setPhysicianName(UIJuvenileProfileMedicalHelper.getNameFromString(medicResp.getPhysicianName()));
   			medication.setPhysicianPhone(new PhoneNumber(medicResp.getPhysicianPhoneNum()));
   			medication.setMedicationReason(medicResp.getReasonForMedication());
   			//medication.setModificationReason(medicResp.getModificationReason());
   			
   			medForm.setMedicRec(medication);
   		}
			
			ActionForward forward = aMapping.findForward("updateMedicationSuccess");
			return forward;
   }
   
      //Ended
  
   public ActionForward addHospitalization(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		MedicalForm med = (MedicalForm) aForm;
   		med.getHospRec().clear();
   		med.clear();
   		med.setAdmissionTypes(UIJuvenileProfileMedicalHelper.getAdmissionTypes());
   		med.setHospitalLengthOfStays(CodeHelper.getHospitalLengthOfStays());
   		ActionForward forward = aMapping.findForward("addHospSuccess");
	    return forward;  
   }
   public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		MedicalForm medForm = (MedicalForm) aForm;
   		ActionForward forward = new ActionForward();
   		medForm.getHsRec().setAction(UIConstants.VIEW);
   		medForm.getMedicRec().setAction(UIConstants.VIEW); 
   		
   		if(medForm.getActionType().equalsIgnoreCase("viewHealthIssue"))
   		{
   			GetMedicalHealthIssueDataEvent reqEvent = (GetMedicalHealthIssueDataEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETMEDICALHEALTHISSUEDATA);
   			reqEvent.setHealthIssueId(medForm.getSelectedValue());
   			CompositeResponse resp = postRequestEvent(reqEvent);  	  
   			
   			if(resp==null)
   				sendToErrorPage(aRequest, "Error retrieving medical health issue record.");
	   		else
	   		{
	   			JuvenileHealthIssueResponseEvent issueResp = (JuvenileHealthIssueResponseEvent)MessageUtil.filterComposite(resp, JuvenileHealthIssueResponseEvent.class);
	   			MedicalForm.HealthIssue healthIssue = medForm.getHsRec();
	   			medForm.setEntryDate(issueResp.getEntryDate());
	   			healthIssue.setIssueId(issueResp.getIssueId());
	   			healthIssue.setIssueStatusId(issueResp.getIssueStatusId());
	   			String healthStatus = SimpleCodeTableHelper.getDescrByCode("HEALTH_STATUS",issueResp.getHealthStatusId());
	   			healthIssue.setHealthStatus(healthStatus);
	   			//healthIssue.setHealthStatusId(issueResp.getHealthStatusId());
	   			String healthStatusCurrent = healthIssue.getHealthStatus();
	   			if(healthStatusCurrent.equals("HIPAA PROTECTED")){
	   				healthIssue.setHealthStatusFull("Health Insurance Portability and Accountability Act");
	   			}else{
	   				healthIssue.setHealthStatusFull("");
	   			}
	   			healthIssue.setConditionLevelId(issueResp.getConditionLevelId());
	   			healthIssue.setConditionSeverityId(issueResp.getConditionSeverityId());
	   			healthIssue.setModificationReason(issueResp.getModificationReason());
	   			medForm.setEntryDate(issueResp.getEntryDate());
	   		}
   			forward = 	aMapping.findForward("viewHealthIssueSuccess");
   		}
   		else if(medForm.getActionType().equalsIgnoreCase("viewMedication"))
   		{
   			GetMedicalMedicationDataEvent medEvent = (GetMedicalMedicationDataEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETMEDICALMEDICATIONDATA);
   			medEvent.setMedicationId(medForm.getSelectedValue());
   			CompositeResponse resp = postRequestEvent(medEvent);  	  
   			
   			if(resp==null)
   				sendToErrorPage(aRequest, "Error retrieving medical medication record.");
	   		else
	   		{
	   			JuvenileMedicationResponseEvent medicResp = (JuvenileMedicationResponseEvent)MessageUtil.filterComposite(resp,JuvenileMedicationResponseEvent.class);
	   			MedicalForm.Medication medication = medForm.getMedicRec();
	   			medForm.setEntryDate(medicResp.getEntryDate());
	   			//medication.setMedicationId(medicResp.getMedicationTypeId());
	   			
	   			medication.setMedication(getMedicationName(medForm.getMedicationList(), medicResp.getMedicationTypeId()));
	   			/*String medicationName = medication.getMedication(); 
	   			String dosage = medicResp.getDosage();
	   			medication.setMedication(medicationName+"; " +dosage);
*/	   			//medication.setCurrentlyTaking(medicResp.isCurrentlyTakingMedication());
	   			String currentlyTakeingMed = SimpleCodeTableHelper.getDescrByCode("MEDICATION_CURRENT", medicResp.getCurrentlyTakingMedication());//added new to get the description of the code
	   			medication.setCurrentlyTakingMed(currentlyTakeingMed);
	   			String currentlyTakingMedication = medication.getCurrentlyTakingMed();
	   			if(currentlyTakingMedication.equals("HIPAA PROTECTED")){
	   				medication.setCurrentlyTakingMedicationFull("Health Insurance Portability and Accountability Act");
	   			}
	   			else{
	   				medication.setCurrentlyTakingMedicationFull("");
	   			}
	   			medication.setDosage(medicResp.getDosage());
	   			medication.setFrequencyId(medicResp.getFrequencyId());
	   			medication.setPhysicianName(UIJuvenileProfileMedicalHelper.getNameFromString(medicResp.getPhysicianName()));
	   			medication.setPhysicianPhone(new PhoneNumber(medicResp.getPhysicianPhoneNum()));
	   			medication.setMedicationReason(medicResp.getReasonForMedication());
	   			medication.setModificationReason(medicResp.getModificationReason());	   			
	   		}
   			forward = 	aMapping.findForward("viewMedicationSuccess");
   		}
   		else if(medForm.getActionType().equalsIgnoreCase("viewHospitalization"))
   		{
   			GetMedicalHospitalizationDataEvent hospEvent =(GetMedicalHospitalizationDataEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETMEDICALHOSPITALIZATIONDATA);
   			hospEvent.setHospitalizationId(medForm.getSelectedValue());
   			CompositeResponse resp = postRequestEvent(hospEvent);  	  
   			
   			if(resp==null)
   				sendToErrorPage(aRequest, "Error retrieving medical hospitalization record.");
	   		else
	   		{
	   			JuvenileHospitalizationResponseEvent hospResp = (JuvenileHospitalizationResponseEvent)MessageUtil.filterComposite(resp, JuvenileHospitalizationResponseEvent.class);
	   			MedicalForm.Hospitalization hosp = medForm.getHospRec();
	   			hosp.setAdmissionDate(hospResp.getAdmissionDate());
	   			hosp.setAdmissionTypeId(hospResp.getAdmissionTypeId());
	   			hosp.setAdmissionType(UIJuvenileProfileMedicalHelper.getAdmissionType(medForm, hospResp.getAdmissionTypeId()));
	   			hosp.setFacilityName(hospResp.getFacilityName());
	   			hosp.setHospitalizationReason(hospResp.getHospitalizationReason());
	   			hosp.setReleaseDate(hospResp.getReleaseDate());
	   			hosp.setAdmittingPhysicianName(UIJuvenileProfileMedicalHelper.getNameFromString(hospResp.getPhysicianName()));
	   			hosp.setPhysicianPhone(new PhoneNumber(hospResp.getPhysicianPhoneNum()));
	   		}
   			
   			forward = 	aMapping.findForward("viewHospSuccess");
   		}
   		medForm.setActionType("view");
   		medForm.setConfirmMessage("");
   		
	    return forward;   
   }
   private String getMedicationName(Collection medicationList, String medicTypeId)
   {
   		Iterator iter = medicationList.iterator();
   		while(iter.hasNext())
   		{
   			JuvenileMedicationResponseEvent medicResp = (JuvenileMedicationResponseEvent)iter.next();
   			if(medicResp.getMedicationTypeId().equals(medicTypeId))
   				return medicResp.getMedicationName();
   		}
   		return null;
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
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.addHealthIssues", "healthIssues");
		keyMap.put("button.addMedication", "addMedication");
		keyMap.put("button.addHospitalization", "addHospitalization");
		keyMap.put("button.view", "view");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.updateMedication", "updateMedication");
		keyMap.put("button.updateHealthIssues", "updateHealthIssues");
	}	
}
