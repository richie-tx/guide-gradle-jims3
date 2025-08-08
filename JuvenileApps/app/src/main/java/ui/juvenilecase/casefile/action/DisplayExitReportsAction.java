/*
 * Created on Dec 30, 2005
 *
 */
package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetCommonAppDocByOIDEvent;
import messaging.casefile.GetCommonAppDocsEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.casefile.form.CommonAppDocBean;
import ui.juvenilecase.casefile.form.CommonAppForm;

/**
 * @author jjose
 *  
 */
public class DisplayExitReportsAction extends JIMSBaseAction
{
		
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4395C2380355
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }
    
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
    	CommonAppForm commonAppForm = (CommonAppForm)aForm;
    	
    	String casefileId = UIJuvenileCaseworkHelper.getCasefileNumber(aRequest, false ,true);
    	
    	GetCommonAppDocsEvent getCommonAppDocsEvent = 
    		(GetCommonAppDocsEvent)EventFactory.getInstance(
    				JuvenileCasefileControllerServiceNames.GETCOMMONAPPDOCS);
    	getCommonAppDocsEvent.setCasefileId(casefileId);
    	CompositeResponse response = postRequestEvent(getCommonAppDocsEvent);
    	
    	Collection exitPlanList = 
    		MessageUtil.compositeToCollection(response, CommonAppDocResponseEvent.class);
		Collections.sort((List)exitPlanList);
    	List uiExitPlanList = new ArrayList();
    	
		for(Iterator iter = exitPlanList.iterator();iter.hasNext();) {
			CommonAppDocResponseEvent re = (CommonAppDocResponseEvent) iter.next();
			uiExitPlanList.add(new CommonAppDocBean(re));
		}
		
		commonAppForm.setExitPlanList(uiExitPlanList);
		
		if (commonAppForm.isFromCommonApp()) {
			commonAppForm.setFromCommonApp(false);
    		return aMapping.findForward("successCommonApp");
    	} else {
    		return aMapping.findForward(UIConstants.SUCCESS);
    	}
		
    }
    
    public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
    	CommonAppForm form = (CommonAppForm)aForm;
    	GetCommonAppDocByOIDEvent getDoc = 
    		(GetCommonAppDocByOIDEvent)EventFactory.getInstance(
    				JuvenileCasefileControllerServiceNames.GETCOMMONAPPDOCBYOID);
    	getDoc.setCommonAppDocId(form.getSelectedExitPlanId());
    	CompositeResponse response = postRequestEvent(getDoc);
    	CommonAppDocResponseEvent document = 
    		(CommonAppDocResponseEvent)MessageUtil.filterComposite(
    				response, CommonAppDocResponseEvent.class);
    	
    	//Append document type + create date to be the file name, for easier identification
    	StringBuffer documentName = new StringBuffer();
    	documentName.append(document.getDocTypeCd());
    	documentName.append("_");    	
    	String documentCreateDate = DateUtil.dateToString(document.getCreateDate(), UIConstants.DATE_FMT_1);
    	documentName.append(documentCreateDate.replaceAll("/", ""));
    	
    	try {
			setPrintContentResp(aResponse, (byte[])document.getDocument(), documentName.toString(), UIConstants.PRINT_AS_PDF_DOC);
		}
		catch(GeneralFeedbackMessageException e) {
			sendToErrorPage(aRequest, "");
		}
		return null;
    	
    	
    }

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.link", "link");
		keyMap.put("button.view", "view");
	}

    
}// END CLASS
