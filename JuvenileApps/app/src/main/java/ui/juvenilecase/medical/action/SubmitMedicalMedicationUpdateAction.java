//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\medical\\action\\SubmitMedicalMedicationUpdateAction.java

package ui.juvenilecase.medical.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.CreateMedicalMedicationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.medical.UIJuvenileProfileMedicalHelper;
import ui.juvenilecase.medical.form.MedicalForm;


public class SubmitMedicalMedicationUpdateAction extends JIMSBaseAction 
{
   
   /**
    * @roseuid 462CE3DE0266
    */
   public SubmitMedicalMedicationUpdateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 462CBCB7012B
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		MedicalForm medForm = (MedicalForm) aForm;
   		MedicalForm.Medication medic = medForm.getMedicRec();
   		CreateMedicalMedicationEvent createEvent=(CreateMedicalMedicationEvent)EventFactory.getInstance(JuvenileControllerServiceNames.CREATEMEDICALMEDICATION);
   		//added  for update medication ERJIMS200076779
   		
	   	 if(medic.getAction().equals(UIConstants.UPDATE)){	   		 
	   		createEvent.setMedicationListId(medForm.getSelectedValue());	   		
	     }	     
   	 //ended 
	   	 
   		createEvent.setJuvenileNum(medForm.getJuvenileNum());
   		createEvent.setEntryDate(new Date());
   		//createEvent.setCurrentlyTaking(medic.isCurrentlyTaking()); 
   		createEvent.setDosage(medic.getDosage());
   		createEvent.setFrequencyId(medic.getFrequencyId());
   		createEvent.setCurrentlyTakingMedId(medic.getCurrentlyTakingMedId()); 
   		createEvent.setModificationReason(medic.getModificationReason()); 
   		createEvent.setMedicationReason(medic.getMedicationReason());
   		createEvent.setMedicationTypeId(medic.getMedCode());
   		createEvent.setPhysicianName(medic.getPhysicianName());
   		createEvent.setPhysicianPhone(medic.getPhysicianPhone());   		
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(createEvent);	   		
   		CompositeResponse response = (CompositeResponse) dispatch.getReply();
   		// Perform Error handling
   		MessageUtil.processReturnException(response); 
   		medForm.setActionType("confirm");
   		medForm.setConfirmMessage("Medication Information successfully added.");
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
   			medForm.setMedicationList(UIJuvenileProfileMedicalHelper.getMedicationList(medForm.getJuvenileNum()));   			
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
   
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");	
		keyMap.put("button.returnToMedical", "returnToMedical");
	}	
}
