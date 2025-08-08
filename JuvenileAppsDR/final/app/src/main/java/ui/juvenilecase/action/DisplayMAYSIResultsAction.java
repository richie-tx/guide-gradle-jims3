//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayMAYSIResultsAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.MentalHealthForm;

/**
 * @author dgibler
 *
 */
public class DisplayMAYSIResultsAction extends Action{
   
   /**
    * @roseuid 42791FCB030D
    */
   public DisplayMAYSIResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42791D56036C
    */
   public ActionForward execute(ActionMapping aMapping, 
   								ActionForm aForm, HttpServletRequest aRequest, 
   								HttpServletResponse aResponse) {
  
		MentalHealthForm mentalHealthForm = (MentalHealthForm)aForm;
		mentalHealthForm.setAction("");							
    	// get the juvenileNumber from the juvenile or casefile form (juvenile first)
    	String juvNum =UIJuvenileHelper.getJuvenileNumber(aRequest,true,true); 
    		//aRequest.getParameter("juvenileNum");
    	
    	//PDJuvenileCaseConstants.JUVENILENUM_PARAM is the standard
    	//to pass in juvenile number via query string.
    	if(juvNum == null || juvNum.length() < 1)
    		juvNum = aRequest.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM);
    	
  		
		mentalHealthForm.setJuvenileNum(juvNum);

		
		Collection assessments = UIJuvenileCaseworkHelper.fetchPreviousMAYSIAssessments(juvNum);
    	
    	if(assessments == null) { // ERROR OCCURRED
			return aMapping.findForward(UIConstants.FAILURE);
    	}
    	
		Collections.sort((ArrayList)assessments);
    	mentalHealthForm.setPreviousMAYSIResults(assessments);
    	
    	// code added for defect #72492 to display all profile tabs
	JuvenileMainForm jmForm = (JuvenileMainForm)aRequest.getSession().getAttribute("juvenileMainForm");
	if(jmForm == null )
	{
		jmForm = new JuvenileMainForm();
	}
        if (jmForm.isHasCasefile() == false){
        	jmForm.setHasCasefile(true);  // must be true to display tabs
        }
        aRequest.getSession().setAttribute("juvenileProfileMainForm", jmForm);
        // end defect code
        
        JuvenileProfileForm profileForm = UIJuvenileHelper.getHeaderForm(aRequest);
    	if(profileForm==null)
    	{
    		UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, mentalHealthForm.getJuvenileNum());
    	}
        
    	return aMapping.findForward(UIConstants.SUCCESS);

   }
}
