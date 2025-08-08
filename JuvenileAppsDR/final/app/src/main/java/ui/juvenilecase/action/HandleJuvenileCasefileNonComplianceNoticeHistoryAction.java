//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\HandleJuvenileCasefileVOPSelectionAction.java

package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import mojo.km.util.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileNonComplianceForm;


public class HandleJuvenileCasefileNonComplianceNoticeHistoryAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "printNotice");
	}	
   
   /**
    * @roseuid 467FB5C80014
    */
   public HandleJuvenileCasefileNonComplianceNoticeHistoryAction() 
   {
    
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
	   String documentId = aRequest.getParameter("documentID");
	   boolean documnetFound = false;
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   jncForm.setConfirmationMsg("");
	   if(documentId != null && !"".equals(documentId))
	   {
			for(int n=0; n<jncForm.getDocuments().size(); n++)
			{
				CasefileDocumentsResponseEvent doc = (CasefileDocumentsResponseEvent) jncForm.getDocuments().get(n);
				if (doc.getReportId().equals(documentId))
				{	
					documnetFound = true;
					if (doc.getReport() != null && doc.getReport().toString().length() > 0)
					{	
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
					} else {
						jncForm.setConfirmationMsg("Printing Error.\nPossible no document information found to display.");
					}
					break;
				} 
			}	
		}
//		return null;
	    if (documnetFound == false)
	    {	
	    	jncForm.setConfirmationMsg("Printing Error.\nNo document found to display, possible missing or invalid docuement identifier.");
	    }
	    return aMapping.findForward("reprintSuccess") ;  
   }  
}
