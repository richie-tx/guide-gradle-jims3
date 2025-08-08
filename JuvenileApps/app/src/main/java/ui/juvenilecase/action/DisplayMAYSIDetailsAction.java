//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayMAYSIDetailsAction.java

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.form.MentalHealthForm;


/**
 * @author dgibler
 *
 */
public class DisplayMAYSIDetailsAction extends Action {
   
   /**
    * @roseuid 42791FCA0232
    */
   public DisplayMAYSIDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42791D57017E
    */
   public ActionForward execute(ActionMapping aMapping, 
   								ActionForm aForm, HttpServletRequest aRequest, 
   								HttpServletResponse aResponse) {
    
    	MentalHealthForm mentalHealthForm = (MentalHealthForm)aForm;
				
		String assessmentId = mentalHealthForm.getAssessmentId();
		String subAssessId = mentalHealthForm.getSubAssessId();
		String maysiDetailId = mentalHealthForm.getMaysiDetailId();
		
		//mentalHealthForm.get
		MAYSIDetailsResponseEvent detail = UIJuvenileCaseworkHelper.fetchMAYSIDetails(assessmentId, subAssessId, maysiDetailId);

		if(detail != null && detail.getAssessOfficerName() != null) {
		    String fName = detail.getAssessOfficerName().getFirstName();
		    String lName = detail.getAssessOfficerName().getLastName();
		    String mName = detail.getAssessOfficerName().getMiddleName();

		    // Remove anything after the parenthesis only if the field contains one
		    if (fName != null && fName.contains("(")) {
		        fName = fName.replaceAll("\\s*\\(.*\\)", "");
		    }
		    if (lName != null && lName.contains("(")) {
		        lName = lName.replaceAll("\\s*\\(.*\\)", "");
		    }
		    if (mName != null && mName.contains("(")) {
		        mName = mName.replaceAll("\\s*\\(.*\\)", "");
		    }

		    // Set cleaned values back
		    detail.getAssessOfficerName().setFirstName(fName);
		    detail.getAssessOfficerName().setLastName(lName);
		    detail.getAssessOfficerName().setMiddleName(mName);
		}

		if(detail == null) { // ERROR OCCURRED
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		// populate the form with the response
		mentalHealthForm.populateFromMAYSIDetailsEvent(detail);		
				
		return aMapping.findForward(UIConstants.SUCCESS);
   }
}
