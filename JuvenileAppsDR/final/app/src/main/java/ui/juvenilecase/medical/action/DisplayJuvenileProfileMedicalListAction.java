//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\medical\\action\\DisplayJuvenileProfileMedicalListAction.java

package ui.juvenilecase.medical.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.form.TraitsForm;
import ui.juvenilecase.medical.UIJuvenileProfileMedicalHelper;
import ui.juvenilecase.medical.form.MedicalForm;


public class DisplayJuvenileProfileMedicalListAction extends Action
{
   
   /**
    * @roseuid 462CE3D801E9
    */
   public DisplayJuvenileProfileMedicalListAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 462CBCC6013E
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
   		TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
        traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_MEDICAL_ISSUES);
        traitsForm.clear();
        String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        traitsForm.setJuvenileNumber(juvenileNum);
        traitsForm.setUICasefile(false); // mark the fact that we are coming in from profile not casefile
        traitsForm.setTraitTypeId("3");
        traitsForm.initializeTraitForm(true);
        traitsForm.setActiveCasefileFound(UIJuvenileTraitsHelper.findActiveCasefile(juvenileNum));
        traitsForm.setExpandTraits(0);
       // traitsForm.setAction(UIConstants.VIEW);      
        MedicalForm medForm = UIJuvenileHelper.getMedicalForm(aRequest, true);
        medForm.setJuvenileNum(juvenileNum);
        //get the lists
        medForm.setHealthIssuesList(UIJuvenileProfileMedicalHelper.getHealthIssuesList(medForm.getJuvenileNum()));
        medForm.setMedicationList(UIJuvenileProfileMedicalHelper.getMedicationList(medForm.getJuvenileNum()));
        medForm.setHospitalizationList(UIJuvenileProfileMedicalHelper.getHospitalizationList(medForm));
   		return forward;
   }
   
  
}
