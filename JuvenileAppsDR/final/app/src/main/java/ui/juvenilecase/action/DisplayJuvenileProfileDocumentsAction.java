// Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileProfileDocumentsAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileProfileDocumentsEvent;
import messaging.juvenile.reply.JuvenileProfileDocumentResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.casefile.form.CasefileDocumentsForm;
import ui.juvenilecase.form.JuvenileProfileForm;

public class DisplayJuvenileProfileDocumentsAction extends JIMSBaseAction
{

	/**
     * @roseuid 42B1A2B00196
     */
    public DisplayJuvenileProfileDocumentsAction()
    {

    }

 	protected void addButtonMapping(Map keyMap) {
 		keyMap.put("button.link", "listDocuments");
		keyMap.put("button.view", "displayDocument");
 	}

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @throws GeneralFeedbackMessageException 
     * @roseuid 42B03B350171
     */
    public ActionForward listDocuments(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
    {
    	CasefileDocumentsForm docForm = (CasefileDocumentsForm) aForm;
        docForm.clearAll();
    	JuvenileProfileForm juvProfForm = (JuvenileProfileForm) getSessionForm(aMapping, aRequest, "juvenileProfileHeader", true);
        GetJuvenileProfileDocumentsEvent getEvent = new  GetJuvenileProfileDocumentsEvent();
        getEvent.setJuvenileNum(juvProfForm.getJuvenileNum());
		CompositeResponse response =  MessageUtil.postRequest(getEvent);
		ReturnException returnException =
			   (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

		if (returnException == null) {
			List docList = MessageUtil.compositeToList(response, JuvenileProfileDocumentResponseEvent.class);
			for (int x=0; x<docList.size(); x++) {
				JuvenileProfileDocumentResponseEvent jpEvent = (JuvenileProfileDocumentResponseEvent) docList.get(x);
				jpEvent.setDocumentTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVPROF_DOCUMENT_TYPE,jpEvent.getDocumentTypeId()));
			}
			
			docForm.setDocuments((ArrayList) docList);
		} 
        return aMapping.findForward(UIConstants.SUCCESS); 
    }

    public ActionForward displayDocument(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	CasefileDocumentsForm docForm = (CasefileDocumentsForm) aForm;
    	docForm.setErrorMsg(UIConstants.EMPTY_STRING);
    	List documents = new ArrayList(docForm.getDocuments());
		String documentId = aRequest.getParameter("documentId");
		String documentType = aRequest.getParameter("documentType");
		aRequest.setAttribute("documentId",null);
		aRequest.setAttribute("documentType", null);
		if(StringUtils.isEmpty(documentId) || StringUtils.isEmpty(documentType) || documents == null || documents.isEmpty())
		{
			docForm.setErrorMsg("Error occured processing request to display document.");
	        return aMapping.findForward(UIConstants.SUCCESS); 
		}
		viewDocument(documentId,documents,aRequest,aResponse);
		
		documentId = null;
		documentType = null;
        return null; 
    }
    /**
     * View Document.
     * @param documentId
     * @param documents
     * @param aRequest
     * @param aResponse
     */
	private void viewDocument(String documentId,List documents,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		int len = documents.size();
		for (int x=0; x<len; x++)
		{
			JuvenileProfileDocumentResponseEvent resp = (JuvenileProfileDocumentResponseEvent) documents.get(x);
			if (resp.getDocumentId().equals(documentId))
			{
				if (resp.getDocument() != null)
				{
			    	final String documentName = resp.getDocumentTypeDesc().replace(" ", "_");
			    	try {
						setPrintContentResp(aResponse, (byte[])resp.getDocument(), documentName.toString(), UIConstants.PRINT_AS_PDF_DOC);
					}
					catch(Exception e) {
						sendToErrorPage(aRequest, "");
					}
					break;
				}
			}
		}
	}    
}