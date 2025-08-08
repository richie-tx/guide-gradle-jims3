//Source file: C:\\views\\juvenileCasework\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefileNonComplianceNoticeDetailsAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeProbationViolationResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeSanctionResponseEvent;
import messaging.codetable.criminal.reply.JuvenileTechnicalVOPCodesResponseEvent;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileNonComplianceForm;


public class DisplayJuvenileCasefileNonComplianceNoticeDetailsAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.view", "displayNoticeDetails");
		keyMap.put("button.printNotice", "printNotice");
	}	
   
   public DisplayJuvenileCasefileNonComplianceNoticeDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward displayNoticeDetails(ActionMapping aMapping, ActionForm aForm, 
		   HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
		JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
		jncForm.setConfirmationMsg("");
		jncForm.setJuvenileCompletedStatus("");
		jncForm.setActionTakenId("");
		jncForm.setActionTakenDesc("");
		jncForm.setOtherActionTakenComment("");
		jncForm.setCompletionDateStr("");
		jncForm.setJuvenileCompletedComments("");
	    jncForm.setSignatureSignedDateStr("");
		jncForm.setSignatureStatus("");
		jncForm.setProbationViolationList(new ArrayList());
// find record based on noticeId
	   String noticeId = aRequest.getParameter("noticeID");
	   for (int x=0; x<jncForm.getExistingNoticesList().size(); x++)
	   {
		   CasefileNonComplianceNoticeResponseEvent jcnre = (CasefileNonComplianceNoticeResponseEvent) jncForm.getExistingNoticesList().get(x);
		   if (noticeId.equals(jcnre.getCasefileNonComplianceNoticeId()))
		   {
			  jncForm.setSelectedNoticeId(noticeId);
			  jncForm.setNonComplianceDate(jcnre.getNonComplianceDate());
			  jncForm.setSanctionAssignedDate(jcnre.getSanctionAssignedDate());
			  jncForm.setSanctionCompleteByDate(jcnre.getCompleteSanctionByDate());
			  jncForm.setParentalNotified("false");
			  if (jcnre.isParentInformed() == true)
			  { 	  
				  jncForm.setParentalNotified("true");
			  }	 
			  jncForm.setProbationViolationLevelDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCVIOLATION_LEVEL,jcnre.getViolationLevelId()));		
			  jncForm.setSanctionLevelDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCSANCTION_LEVEL,jcnre.getSanctionLevelId() ) );
			   
// Begin Load probation violation display list			   
			   List temp0 = ComplexCodeTableHelper.getTechnicalVOPs();
			   List temp1 = (List) jcnre.getProbationViolations();
			   if (temp1 == null)
			   {
				   temp1 = new ArrayList(); 
			   }
			   List temp2 = new ArrayList();
			   for (int v=0; v<temp1.size(); v++)
			   {
				   CasefileNonComplianceNoticeProbationViolationResponseEvent cnc = (CasefileNonComplianceNoticeProbationViolationResponseEvent) temp1.get(v);
				   for (int c=0; c<temp0.size(); c++)
				   {	   
					   JuvenileTechnicalVOPCodesResponseEvent jtvcre = (JuvenileTechnicalVOPCodesResponseEvent) temp0.get(c);
					   if (cnc.getJuvenileTechnicalVOPCodesId().equals(jtvcre.getCode()))
					   {	
						   temp2.add(jtvcre);
						   break;
					   }	
				   }
			   }
			   jncForm.setProbationViolationList(sortVOPs(temp2) );
// End load probation violation display list
			   temp0 = new ArrayList();
			   temp1 = new ArrayList();
			   temp2 = new ArrayList();
			   if (jcnre.getSanctions() != null)
			   {  
				   Iterator iter = jcnre.getSanctions().iterator();
				   while(iter.hasNext()){
						CasefileNonComplianceNoticeSanctionResponseEvent ncre = (CasefileNonComplianceNoticeSanctionResponseEvent) iter.next();	
						if (!"OTH".equals(ncre.getJuvenileVOPSanctionCodesId() ) )
						{	
							temp0.add(ncre);
						} else {
							temp2.add(ncre);
						}
					}
			   } 		   
			   temp1 = sortSanctions( temp0 );
// Add Other Sanction if present to end of list			
			   if (temp2.size() > 0)
			   {
				   temp1.add(temp2.get(0));
			   }
			   jncForm.setSelectedSanctionsList(temp1);
			   temp0 = null;
			   temp1 = null;
			   temp2 = null;
			   if (jcnre.getSignatureStatusId() == null || "".equals(jcnre.getSignatureStatusId()))
			   {
				   jncForm.setSignatureStatusLiteral("UNSIGNED"); 
			   } else {
				   jncForm.setSignatureStatus(jcnre.getSignatureStatusId() );
				   jncForm.setSignatureStatusLiteral(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCSIGNATURE_STATUS, jcnre.getSignatureStatusId()));
				   jncForm.setSignatureSignedDate(jcnre.getSignedDate() );
			   }
			   jncForm.setJuvenileCompletedStatusLit(jcnre.getJuvenileCompletedLit() );
			   jncForm.setJuvenileCompletedStatus(jcnre.getCompletionStatusId() );
			   jncForm.setCompletionDate(jcnre.getCompletionDate() );
			   jncForm.setActionTakenId(jcnre.getActionTakenId() );
			   if ("OTH".equals(jcnre.getActionTakenId() ) )
			   {		   
				   jncForm.setActionTakenDesc("Other");
			   } else {
				   jncForm.setActionTakenDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCACTION_TAKEN, jcnre.getActionTakenId() ) ); 
			   }	   
			   jncForm.setOtherActionTakenComment(jcnre.getActionTakenComments() );
			   jncForm.setJuvenileCompletedComments(jcnre.getCompletionComments() );
			   break;
		   }   
	   }
   
	   jncForm.setAction(UIConstants.VIEW);
	   return aMapping.findForward("viewSuccess");   
   }   

   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward printNotice(ActionMapping aMapping, ActionForm aForm,
		   HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception 
   {
	   boolean documnetFound = false;
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   String noticeId = jncForm.getSelectedNoticeId();
	   for (int x=0; x<jncForm.getExistingNoticesList().size(); x++)
	   {
		   CasefileNonComplianceNoticeResponseEvent jcnre = (CasefileNonComplianceNoticeResponseEvent) jncForm.getExistingNoticesList().get(x);
		   if (noticeId.equals(jcnre.getCasefileNonComplianceNoticeId()))
		   {
				String documentId = jcnre.getDocumentId();    
				for(int n=0; n<jncForm.getDocuments().size(); n++)
				{
					CasefileDocumentsResponseEvent doc = (CasefileDocumentsResponseEvent) jncForm.getDocuments().get(n);
					if (doc.getReportId().equals(documentId))
					{	
						documnetFound = true;
						StringBuffer documentName = new StringBuffer();
						documentName.append(doc.getReportType());
						documentName.append("_");    	
						String documentEntryDate = DateUtil.dateToString(doc.getEntryDate(), UIConstants.DATE_FMT_1);
						documentName.append(documentEntryDate.replaceAll("/", ""));
						
						try {
							setPrintContentResp(aResponse, (byte[])doc.getReport(), documentName.toString(), UIConstants.PRINT_AS_PDF_DOC);
						}
						catch(GeneralFeedbackMessageException e) {
							sendToErrorPage(aRequest, "");
						}
						break;
					} 
				}	
		   }
	   }   
//			return null;
	    if (documnetFound == false)
	    {	
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
									"No document found to display, possible missing or invalid docuement identifier"));
			saveErrors(aRequest, errors);
	    }
	    return aMapping.findForward("reprintSuccess") ;  
   } 
   
	public static List sortSanctions(Collection sanctionsList){
		Iterator iter = sanctionsList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			CasefileNonComplianceNoticeSanctionResponseEvent ncre = (CasefileNonComplianceNoticeSanctionResponseEvent) iter.next();	
			map.put(ncre.getOtherText(), ncre);
		}
		return new ArrayList(map.values());
	} 
	
	public static List sortVOPs(Collection vopList){
		Iterator iter = vopList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			JuvenileTechnicalVOPCodesResponseEvent jtre = (JuvenileTechnicalVOPCodesResponseEvent) iter.next();	
			map.put(jtre.getDescription(), jtre);
		}
		return new ArrayList(map.values());
	} 
}
