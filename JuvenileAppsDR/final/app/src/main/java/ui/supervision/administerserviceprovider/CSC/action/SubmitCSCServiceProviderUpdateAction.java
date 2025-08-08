/*
 * Created on Dec 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.csserviceprovider.SaveServiceProviderEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import messaging.csserviceprovider.reply.DuplicateServiceProviderResponseEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.CSC.AdminServiceProviderHelper;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderCSCDForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitCSCServiceProviderUpdateAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.saveAndContinue","saveNContinue");
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		String forward="";		
		if (!myForm.getCancelPath().equals("")){				
			forward = myForm.getCancelPath();
			myForm.setCancelPath("");
		}else {
			forward = "cancelSearch";
		}
		return aMapping.findForward(forward);
	}
	
	public ActionForward saveNContinue(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		
		RequestEvent myEvent=AdminServiceProviderHelper.getServProvSaveEvent(myForm);
		if(myForm.getAction().equals(UIConstants.INACTIVATE)){
			((SaveServiceProviderEvent)myEvent).setStatusCode(PDCodeTableConstants.ASP_CS_SERVPROV_INACTIVE);
		}
		String userAgency=SecurityUIHelper.getUserAgencyId();
		// Add user agency to request event
        CompositeResponse myResp=this.postRequestEvent(myEvent);
        String forward=UIConstants.SUCCESS;
        Object obj1=MessageUtil.filterComposite(myResp,DuplicateServiceProviderResponseEvent.class);
        if(obj1!=null){
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Duplicate Service Provider Name Specified");
        	forward=UIConstants.FAILURE;
        }
        else{
	        Object obj=MessageUtil.filterComposite(myResp,CSServiceProviderResponseEvent.class);
	        if(obj==null || !(((CSServiceProviderResponseEvent)obj).isOperationSuccessful())){
	        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"An unknown error was encountered, Service Provider may not have been saved");
	        	forward="failureSummary";
	        }
	        else{
	        	CSServiceProviderResponseEvent myServProvResp=(CSServiceProviderResponseEvent)obj;
	        	if(UIConstants.SUCCESS.equals(forward)){
					AdminServiceProviderHelper.setServProvFormFromRespEvent(myForm,myServProvResp);
					if(UIConstants.CREATE.equals(myForm.getAction()))
						aRequest.setAttribute("confirmMsg","Service Provider successfully created.");
					else if(UIConstants.UPDATE.equals(myForm.getAction()))
						aRequest.setAttribute("confirmMsg","Service Provider successfully updated.");
					else if(UIConstants.INACTIVATE.equals(myForm.getAction()))
						aRequest.setAttribute("confirmMsg","Service Provider successfully inactivated.");
					myForm.setSecondaryAction(UIConstants.CONFIRM);
	        	}
	        }
        }
        
		return aMapping.findForward(forward);
	}

}// END CLASS
