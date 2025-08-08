//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\medical\\action\\DisplayMedicalMedicationUpdateSummaryAction.java

package ui.juvenilecase.medical.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileMedicationTypeResponseEvent;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.juvenilecase.medical.UIJuvenileProfileMedicalHelper;
import ui.juvenilecase.medical.form.MedicalForm;
import ui.security.SecurityUIHelper;


public class DisplayMedicalMedicationUpdateSummaryAction extends JIMSBaseAction 
{
   
   /**
    * @roseuid 462CE3DB016C
    */
   public DisplayMedicalMedicationUpdateSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 462CBCB700EC
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   	MedicalForm medForm = (MedicalForm) aForm;
/*
	   	String comments = medForm.getMedicRec().getMedicationReason();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			medForm.getMedicRec().setMedicationReason(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}	   	
	   	*/
	   	medForm.setActionType("summary");
	   	//medForm.setConfirmMessage("");
	   	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
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
   
   public ActionForward validateCode(ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
   {
   	MedicalForm medForm = (MedicalForm)aForm;
   	Collection coll = UIJuvenileProfileMedicalHelper.getMedicationCodesById(medForm.getMedicRec().getMedCode());
   	if( coll.isEmpty() || coll.size()==0 )
   	{
   	 	medForm.setActionType("");	
   		medForm.getMedicRec().setMedication("");
   		sendToErrorPage(aRequest, "error.noRecords");
   	}
   	else
   	{
		Iterator iter = coll.iterator();
		while(iter.hasNext())
		{
			JuvenileMedicationTypeResponseEvent newMedic = (JuvenileMedicationTypeResponseEvent)iter.next();
			if(medForm.getMedicRec().getMedCode().toUpperCase().equals(newMedic.getMedicationTypeId()))
			{
				medForm.getMedicRec().setMedication(newMedic.getMedication());
				//medForm.getMedicRec().setMedication(newMedic.getTradeName()+ "; " + newMedic.getDosageAdmin()+ "; " +newMedic.getStrength());
				//medForm.getMedicRec().setDosage(newMedic.getStrength());
				break;
			}
		}
	   	medForm.setActionType("validateMedCode");	   
   	}
   	
   	ActionForward forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
   	return forward;
   	
   }

   
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");	
		keyMap.put("button.validateMedicationCode", "validateCode");	
	}	
}
