//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplaySubsequentMAYSIAction.java

package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenile.SaveJuvenileProfileDocumentEvent;
import messaging.mentalhealth.UpdateSubsequentMAYSICommentEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.mentalhealth.reply.MAYSISubAssessResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.juvenilecase.JuvenileCaseHelper;

import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.MaysiDataReportBean;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.MentalHealthForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author dgibler
 *
 */
public class DisplaySubsequentMAYSIAction extends LookupDispatchAction {
   
   /**
    * @roseuid 42791FCD03C8
    */
   public DisplaySubsequentMAYSIAction() 
   {
    
   }

   protected Map getKeyMethodMap()
	  {
		  Map buttonMap = new HashMap();
		  buttonMap.put("button.subsAssessment", "subsAssessment");
		  buttonMap.put("button.updateComment", "updateComment");
		  buttonMap.put("button.finish", "finish");
		  buttonMap.put("button.back", "back");
		  buttonMap.put("button.cancel", "cancel");
		  buttonMap.put("button.link", "subsAssessmentfromTask");
		  buttonMap.put("button.PrintMaysiForm", "printMaysiReport");
		  return buttonMap;
	  }
	  
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42791D7B01E6
    */
   public ActionForward subsAssessment(ActionMapping aMapping, 
   								ActionForm aForm, HttpServletRequest aRequest, 
   								HttpServletResponse aResponse) 
	{
		MentalHealthForm maysiForm = (MentalHealthForm) aForm;
		if(maysiForm.getAssessmentOption().equalsIgnoreCase(maysiForm.SUBSEQUENT_NEEDED))
		{
			maysiForm.setHasPreviousMAYSI(false);
			maysiForm.clearForSubAssessment();
    		return aMapping.findForward(UIConstants.SUCCESS);
		}
		else
		{
			return aMapping.findForward(UIConstants.FAILURE);
		}
   }
   
   public ActionForward updateComment(ActionMapping aMapping, 
		ActionForm aForm, HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
        MentalHealthForm maysiForm = (MentalHealthForm) aForm;
        if(maysiForm.getAssessmentOption().equalsIgnoreCase(maysiForm.SUBSEQUENT_DONE))
        {
            maysiForm.setUpdatedMaysiComments("");
            return aMapping.findForward(UIConstants.UPDATE_COMMENT);
        }
        else
        {
            return aMapping.findForward(UIConstants.FAILURE);
        }
   }
   
   public ActionForward finish(ActionMapping aMapping, 
		ActionForm aForm, HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
       MentalHealthForm maysiForm = (MentalHealthForm) aForm;
       if(maysiForm.getAssessmentOption().equalsIgnoreCase(maysiForm.SUBSEQUENT_DONE))
       {
	   
	   UpdateSubsequentMAYSICommentEvent updateEvent = (UpdateSubsequentMAYSICommentEvent) EventFactory
		   .getInstance(JuvenileMentalHealthControllerServiceNames.UPDATESUBSEQUENTMAYSICOMMENT);
	   updateEvent.setMaysiSubAssessmentId(maysiForm.getSubAssessId());
	   updateEvent.setExistingComments(maysiForm.getSubsAssessmentComments());
	   updateEvent.setNewComments(maysiForm.getUpdatedMaysiComments());
	   MAYSISubAssessResponseEvent detail = (MAYSISubAssessResponseEvent) MessageUtil.postRequest(updateEvent,
	                MAYSISubAssessResponseEvent.class);
	   if (detail != null ){
	       maysiForm.setUpdatedMaysiComments(detail.getReviewComments());
	       return aMapping.findForward(UIConstants.UPDATE_SUMMARY);
	   } else {
	       return aMapping.findForward(UIConstants.UPDATE_COMMENT);
	   }
       }
       else
       {
           return aMapping.findForward(UIConstants.UPDATE_COMMENT);
       }
   }
   
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
							   HttpServletRequest aRequest, 
							   HttpServletResponse aResponse)
   {
			 return aMapping.findForward(UIConstants.BACK);
   }
  
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
							   HttpServletRequest aRequest, 
							   HttpServletResponse aResponse)
   {
			   return aMapping.findForward(UIConstants.CANCEL);
   }
   
   /**
 * @param aMapping
 * @param aForm
 * @param aRequest
 * @param aResponse
 * @return
 * method called from the task.
 * need to complete.
 */
public ActionForward subsAssessmentfromTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		MentalHealthForm maysiForm = (MentalHealthForm)aForm; 
		if(maysiForm == null ){
			maysiForm = new MentalHealthForm();
		}
		maysiForm.setMaysiId(aRequest.getParameter("maysiId"));
		maysiForm.setJuvenileNum(aRequest.getParameter("juvenileNum"));
		//maysiForm.setAssessmentOfficerID(aRequest.getParameter("officerId"));
		maysiForm.setCasefileId(aRequest.getParameter("caseFileId"));
		maysiForm.setAssessmentOption(aRequest.getParameter("assessmentOption"));
		maysiForm.setReferralNum(aRequest.getParameter("referralNumber"));
		maysiForm.setLocationUnitId(aRequest.getParameter("locationUnitId"));
		maysiForm.setFacilityTypeId(aRequest.getParameter("facilityTypeId"));
		maysiForm.setLengthOfStayId(aRequest.getParameter("lengthOfStayId"));
		
		HttpSession session = aRequest.getSession();
    	JuvenileProfileForm profileForm = (JuvenileProfileForm) session.getAttribute("juvenileProfileHeader");
    	if(profileForm==null)
    	{
    		UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, maysiForm.getJuvenileNum());
    	}
			if(maysiForm.getAssessmentOption() != null &&  maysiForm.getAssessmentOption().equalsIgnoreCase(maysiForm.SUBSEQUENT_NEEDED))
			{
			maysiForm.setHasPreviousMAYSI(false);
			maysiForm.clearForSubAssessment();
			return aMapping.findForward(UIConstants.SUCCESS);
			}
			else
			{
				return aMapping.findForward(UIConstants.FAILURE);
			}
	}

	public ActionForward printMaysiReport(ActionMapping aMapping, ActionForm aForm, 
    	HttpServletRequest aRequest, HttpServletResponse aResponse)
        {
    	    	MentalHealthForm mentalHealthForm = (MentalHealthForm)aForm;
    	    	if(mentalHealthForm == null ){
    	    	    mentalHealthForm = new MentalHealthForm();
    	    	}
	    
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
		
		MaysiDataReportBean reportBean = JuvenileCaseHelper.populateMAYSIReportDetails(detail);
		
		aRequest.getSession().setAttribute("reportInfo", reportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.MAYSI_MENTAL_HEALTH_REPORT);
		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
		
		if(pdfDocument!=null){
        		// persist a copy of the BFO pdf document
        		SaveJuvenileProfileDocumentEvent saveEvent = 
        			(SaveJuvenileProfileDocumentEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEDOCUMENT);
        		saveEvent.setDocument(pdfDocument);
        		saveEvent.setDocumentTypeCodeId(UIConstants.MAYSI_MENTAL_HEALTH_REPORT_CODE);
        		saveEvent.setJuvenileNum(detail.getJuvenileNum());
        		saveEvent.setEntryDate(DateUtil.getCurrentDate());
        		CompositeResponse resp =  MessageUtil.postRequest(saveEvent);
        		ReturnException returnException =
        			   (ReturnException) MessageUtil.filterComposite(resp, ReturnException.class);
        
        		//if (returnException != null) {
        		//	form.setErrorMsg("Error occured saving MAYSI Mental Health Report.");
        		//	form.setReportGenerated("Y");
        		//}       		       		
		}
		
		// remove the pdf report attributes from session when finished saving to database
		aRequest.removeAttribute("isPdfSaveNeeded");
		aRequest.removeAttribute("pdfSavedReport");
		
	    return null; //aMapping.findForward(UIConstants.SUCCESS); /
        }
   
}
