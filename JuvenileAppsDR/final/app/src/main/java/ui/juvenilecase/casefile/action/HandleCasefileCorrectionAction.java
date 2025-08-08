package ui.juvenilecase.casefile.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.UpdateJuvenileCasefileCorrectionEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.casefile.form.CasefileCorrectionForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.security.SecurityUIHelper;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleCasefileCorrectionAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.submit", "next");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.returnToCasefile", "returnToCasefile");
	}

	
	 public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	 	CasefileCorrectionForm casefileCorrectionForm = (CasefileCorrectionForm) aForm;
	 	casefileCorrectionForm.clear();
	 	casefileCorrectionForm.setAction(UIConstants.UPDATE);
	 	casefileCorrectionForm.setSecondaryAction("");
	 	casefileCorrectionForm.setSelectedValue("");
	 	try{
	 		JuvenileCasefileForm myForm=(JuvenileCasefileForm)this.getSessionForm(aMapping,aRequest,UIConstants.CASEFILE_HEADER_FORM,false);
	 		String casefileId=myForm.getSupervisionNum();
	 		if(casefileId==null || casefileId.trim().equals("")){
	 			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Casefile number cannot be found and therefore casefile cannot be loaded.");
		 		return aMapping.findForward("detailPage");
	 		}
	 		casefileCorrectionForm.setCasefileId(casefileId);
	 		casefileCorrectionForm.setClmId(myForm.getCaseloadManagerId());
	 		try{
	 			casefileCorrectionForm.setCurrentUserId(SecurityUIHelper.getLogonId());
	 		}
	 		catch(Exception e){
	 			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "User Id cannot be determined therefore security restrictions prohibit changing the casefile informaion.");
		 		return aMapping.findForward("detailPage");
	 		}
	 		String supCatId=UIJuvenileCaseworkHelper.getSupCatFromType(myForm.getSupervisionTypeId());
	 		
	 		//retrieve category subtypes
	 		String caseStatus = myForm.getCaseStatusId();
	 		List myTypes = new ArrayList();
	 		if(caseStatus.equals("A")){
	 		   myTypes = UIJuvenileCaseworkHelper.getAllSupTypesInCategory(supCatId);	 		    
	 		}
	 		else {
	 		   myTypes = UIJuvenileCaseworkHelper.getAllSupTypesInSupervisionCategory(supCatId);
	 		}
	 		
	 		//List myTypes=UIJuvenileCaseworkHelper.getAllSupTypesInSupervisionCategory(supCatId);
	 		//added for bug 50021
	 		/*if(myTypes!=null && myTypes.size()>0){
	 			Iterator myTypesIter=myTypes.iterator();
	 			while(myTypesIter.hasNext()){
	 				SupervisionTypeMapResponseEvent myRespEvt=(SupervisionTypeMapResponseEvent)myTypesIter.next();
	 				myRespEvt.setSupervisionType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE,myRespEvt.getSupervisionTypeId()));
	 			}
	 		}*/
	 		//added for bug 50021
	 		
	 		Collections.sort((List)myTypes);
	 		casefileCorrectionForm.setSupervisionTypes(myTypes);
	 		casefileCorrectionForm.setChangeStatusToClosingPending(false);
	 		casefileCorrectionForm.setCurrentCasefileStatusId(myForm.getCaseStatusId());
	 		casefileCorrectionForm.setCurrentSupCatId(UIJuvenileCaseworkHelper.getSupCatFromType(myForm.getSupervisionTypeId()));
	 		casefileCorrectionForm.setCurrentSupTypeId(myForm.getSupervisionTypeId());
	 		casefileCorrectionForm.setChangeToSupTypeId(myForm.getSupervisionTypeId());
	 	}
	 	catch(Exception e){
	 		sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Casefile number cannot be found and therefore casefile cannot be loaded.");
	 		return aMapping.findForward("detailPage");
	 	}
	
	 	return aMapping.findForward(UIConstants.SUCCESS);
    }
	 
	
	 
	 public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	 	CasefileCorrectionForm casefileCorrectionForm = (CasefileCorrectionForm) aForm;
	 	casefileCorrectionForm.setAction(UIConstants.UPDATE);
	 	casefileCorrectionForm.setSecondaryAction("");
	 	casefileCorrectionForm.setSelectedValue("");
	 	String casefileIdOffCorrForm=casefileCorrectionForm.getCasefileId();
	 	String casefileId=null;
	 	try{
	 		if(casefileCorrectionForm.getChangeToSupTypeId()==null || casefileCorrectionForm.getChangeToSupTypeId().trim().equals("")){
	 			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Change Supervision Type to is required");
		 		return aMapping.findForward(UIConstants.SUCCESS);
	 		}
	 		JuvenileCasefileForm myForm=(JuvenileCasefileForm)this.getSessionForm(aMapping,aRequest,UIConstants.CASEFILE_HEADER_FORM,false);
	 		casefileId=myForm.getSupervisionNum();
	 		if(casefileId==null || casefileId.trim().equals("") || casefileIdOffCorrForm==null || casefileIdOffCorrForm.trim().equals("")){
	 			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Verfication of casefile id failed, may have been caused by trying to reupdate the casefile using the back button");
		 		return aMapping.findForward(UIConstants.SUCCESS);
	 		}
	 		casefileCorrectionForm.setSecondaryAction(UIConstants.SUMMARY);
	 	}
	 	catch(Exception e){
	 		sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Verfication of casefile id failed, may have been caused by trying to reupdate the casefile using the back button");
	 		return aMapping.findForward(UIConstants.SUCCESS);
	 	}
	 	return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
    }
	 
	 public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	 	CasefileCorrectionForm casefileCorrectionForm = (CasefileCorrectionForm) aForm;
	 	casefileCorrectionForm.setAction(UIConstants.UPDATE);
	 	casefileCorrectionForm.setSecondaryAction(UIConstants.SUMMARY);
	 	UpdateJuvenileCasefileCorrectionEvent myEvent=new UpdateJuvenileCasefileCorrectionEvent();
	 	myEvent.setChangeCasefileStatus(casefileCorrectionForm.isChangeStatusToClosingPending());
	 	myEvent.setCasfileStatusId(PDJuvenileCaseConstants.CASESTATUS_ACTIVE);
	 	myEvent.setChangeCasefileType(true);
	 	if(casefileCorrectionForm.getChangeToSupTypeId()==null || casefileCorrectionForm.getChangeToSupTypeId().trim().equals("")){
	 		myEvent.setChangeCasefileType(false);
	 	}
	 	myEvent.setPrevSupTypeId(casefileCorrectionForm.getCurrentSupTypeId());
	 	myEvent.setSupervisionNumber(casefileCorrectionForm.getCasefileId());
	 	myEvent.setSupTypeId(casefileCorrectionForm.getChangeToSupTypeId());
	 	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(myEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);	
	 	Collection myErrors=MessageUtil.compositeToCollection(response,ErrorResponseEvent.class);
	 	
	 	if(myErrors==null || myErrors.size()<1){
	 	
	 		try{
	 			JuvenileCasefileResponseEvent myRespEvt=(JuvenileCasefileResponseEvent)MessageUtil.filterComposite(response,JuvenileCasefileResponseEvent.class);
	 			if(myRespEvt!=null){
	 				casefileCorrectionForm.setSecondaryAction(UIConstants.CONFIRM);
	 				JuvenileCasefileForm myForm=(JuvenileCasefileForm)this.getSessionForm(aMapping,aRequest,UIConstants.CASEFILE_HEADER_FORM,false);
	 				myForm.setSupervisionTypeId(myRespEvt.getSupervisionTypeId());
	 				myForm.setCaseStatusId(myRespEvt.getCaseStatusId());
	 				casefileCorrectionForm.setChangeStatusToClosingPending(false);
	 				casefileCorrectionForm.setCurrentCasefileStatusId(myRespEvt.getCaseStatusId());
	 				casefileCorrectionForm.setCurrentSupTypeId(myRespEvt.getSupervisionTypeId());
	 			}
		 		else{
		 			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Casefile data was not updated. An error has occurred");
		 			return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
		 		}
		 	}
		 	catch(Exception e){
		 		sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Casefile data was updated but header could not be refreshed");
		 		return aMapping.findForward(UIConstants.SUCCESS);
		 	}
	 	}
	 	else{
	 		Iterator errIter=myErrors.iterator();
	 		while(errIter.hasNext()){
	 			ErrorResponseEvent myErr=(ErrorResponseEvent)errIter.next();
	 			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,myErr.getMessage());
	 		}
	 		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	 	}
	 	casefileCorrectionForm.setSelectedValue("");
	 	
	 	return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
    }
	 
	 public ActionForward returnToCasefile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	 	
	 	return aMapping.findForward("returnToCasefile");
    }
	 
}// END CLASS
