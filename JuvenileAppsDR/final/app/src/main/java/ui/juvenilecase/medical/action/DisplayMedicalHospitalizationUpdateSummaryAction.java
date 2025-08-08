//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\medical\\action\\DisplayMedicalHospitalizationUpdateSummaryAction.java

package ui.juvenilecase.medical.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.medical.UIJuvenileProfileMedicalHelper;
import ui.juvenilecase.medical.form.MedicalForm;
import ui.security.SecurityUIHelper;


public class DisplayMedicalHospitalizationUpdateSummaryAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 462CE3DA0237
    */
   public DisplayMedicalHospitalizationUpdateSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 462CBCAE0235
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   	MedicalForm medForm = (MedicalForm) aForm;
	   	medForm.setActionType("summary");
	   	String admissionTypeId = medForm.getHospRec().getAdmissionTypeId();
/*
	   	String comments = medForm.getHospRec().getHospitalizationReason();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			medForm.getHospRec().setHospitalizationReason(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}
		*/
	   	medForm.getHospRec().setAdmissionType(UIJuvenileProfileMedicalHelper.getAdmissionType(medForm, admissionTypeId));
	   	medForm.setHospitalLengthOfStayDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.HOSPITAL_LENGTH_OF_STAY, medForm.getHospitalLengthOfStay()));
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
   
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");			
	}	
}
