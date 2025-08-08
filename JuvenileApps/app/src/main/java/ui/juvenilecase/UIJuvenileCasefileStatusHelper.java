/*
 * Created on April 19, 2012
 * 
 */
package ui.juvenilecase;

import javax.servlet.http.HttpServletRequest;

import naming.UIConstants;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;

/**
 * @author cShimek
 * 
 */
public class UIJuvenileCasefileStatusHelper {
	
	public static boolean casefileStatusClosed(HttpServletRequest aRequest)
	{
		JuvenileCasefileForm jcfForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
		
		if (jcfForm!= null && (UIConstants.CASEFILE_CASE_STATUS_CLOSED.equals(jcfForm.getCaseStatusId()) || UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED.equals(jcfForm.getCaseStatusId() ) )) {
			return false;
		}
		return true;
	}
	
	public static boolean casefileStatusClosed(HttpServletRequest aRequest, String casefileId)
	{
		JuvenileCasefileForm jcfForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
		if(jcfForm==null)
		{
			jcfForm = new JuvenileCasefileForm();
			jcfForm.populateJuvenileCasefileForm(casefileId);
		}
		if (jcfForm!= null && (UIConstants.CASEFILE_CASE_STATUS_CLOSED.equals(jcfForm.getCaseStatusId()) || UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED.equals(jcfForm.getCaseStatusId() ) )) {
			return false;
		}
		return true;
	}
	
	public static boolean supervisionCategoryCheck(String supervisionTypeId, boolean allowUpdates)
	{
		if (allowUpdates == true) {
			return allowUpdates;
		}
		boolean result = UIJuvenileCaseworkHelper.isSupTypeInCat(supervisionTypeId, UIConstants.CASEFILE_SUPERVISION_CAT_PRE_PETITION);
		return result;
	}
	
	public static boolean isActiveCaseFile(JuvenileCasefileForm casefileForm){
	    
	    //JuvenileCasefileForm jcfForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
	    if(casefileForm != null && casefileForm.getCaseStatusId() != null && UIConstants.ACTIVE_STATUS_ID.equalsIgnoreCase(casefileForm.getCaseStatusId())){
		return true;
	    }
	    
	    return false;
	}
}
