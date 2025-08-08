//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageassociate\\action\\SubmitAssociateSummaryAction.java

package ui.supervision.manageassociate.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageassociate.CreateUpdateSuperviseeAssociateEvent;
import messaging.manageassociate.reply.DeleteAssociateErrorEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.manageassociate.UIManageAssociateHelper;
import ui.supervision.manageassociate.form.AssociateForm;

public class SubmitAssociateSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");
	}
   /**
    * @roseuid 45E5DF82038A
    */
   public SubmitAssociateSummaryAction() 
   {
    
   }
	/**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650350
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
   							   HttpServletRequest aRequest, HttpServletResponse aResponse) {
   	AssociateForm associateForm = (AssociateForm)aForm;
   	
   	//  Sending PD Request EventS
   	CreateUpdateSuperviseeAssociateEvent evt = UIManageAssociateHelper.getCreateUpdateAssociateEvent(associateForm); 
   	
   	if (associateForm.getAction().equals(UIConstants.CREATE)){	
   		aRequest.setAttribute("confirmMsg","Associate successfully created.");
   		evt.setCreate(true);
   	}
   	if (associateForm.getAction().equals(UIConstants.UPDATE)){
   		aRequest.setAttribute("confirmMsg","Associate successfully updated.");
   	  		evt.setUpdate(true);
   	}
   	if (associateForm.getAction().equals(UIConstants.DELETE)){
   		aRequest.setAttribute("confirmMsg","Associate successfully deleted.");
   		evt.setDelete(true);
   	}	
   	
   	
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(evt);

	//	Get PD Response Event
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	DeleteAssociateErrorEvent assocError = (DeleteAssociateErrorEvent)MessageUtil.filterComposite(compositeResponse, DeleteAssociateErrorEvent.class);
	if(assocError != null)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.cannotDeleteAssociate","Associate Error"));		
		saveErrors(aRequest, errors);
		return aMapping.findForward("failure");
	}
	String forward = UIConstants.CREATE_SUCCESS;
	if (associateForm.getFromPath().equals(UIConstants.FROM_CASENOTES)){
		forward = UIConstants.CREATE_CASENOTES_SUCCESS;
	}
	else{
		if (associateForm.getFromPath().equals(UIConstants.FROM_CASELOAD)){
			associateForm.setSecondaryAction("confirm");
			forward = UIConstants.CASELOAD_SUCCESS;
		}
		else{
			if (associateForm.getFromPath().equals(UIConstants.FROM_COMPLIANCE_CREATE_CASENOTE)){
				forward = UIConstants.COMPLIANCE_CREATE_CASENOTE_SUCCESS;
			}
			else{
				if (associateForm.getFromPath().equals(UIConstants.FROM_COMPLIANCE_RESOLVE_CASENOTE)){
					forward = UIConstants.COMPLIANCE_RESOLVE_CASENOTE_SUCCESS;
				}
				else{
					if (associateForm.getFromPath().equals(UIConstants.FROM_COMPLIANCE_SET_TO_NONCOMPLIANT_CASENOTE)){
						forward = UIConstants.COMPLIANCE_SET_NONCOMPLIANT_CASENOTE_SUCCESS;
					}
					else{
						if (associateForm.getFromPath().equals(UIConstants.FROM_FIELD_RESULTS)){
							forward = UIConstants.FIELD_RESULTS_SUCCESS;
						}
					}
				}
			}
		}	
	}
   	
	return aMapping.findForward(forward);
   }
   
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
		   		HttpServletRequest aRequest, HttpServletResponse aResponse){
		AssociateForm  associateForm = (AssociateForm) aForm;
		
		if (associateForm.getFromPath().equals(UIConstants.FROM_CASENOTES)){
		return aMapping.findForward(UIConstants.CANCEL_CASENOTES);
		}
		else{
			if (associateForm.getFromPath().equals(UIConstants.FROM_CASELOAD)){
				return aMapping.findForward(UIConstants.CANCEL_CASELOAD);
			}else{
				if (associateForm.getFromPath().equals(UIConstants.FROM_FIELD_RESULTS)){
					return aMapping.findForward(UIConstants.CANCEL_FIELD_RESULTS);
				}
			}
		} 
		return aMapping.findForward(UIConstants.CANCEL);
	}
}
