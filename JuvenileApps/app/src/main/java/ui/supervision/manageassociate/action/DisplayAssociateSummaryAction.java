//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageassociate\\action\\DisplayAssociateSummaryAction.java

package ui.supervision.manageassociate.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.manageassociate.GetCopySuperviseeResidenceAddressEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.manageassociate.UIManageAssociateHelper;
import ui.supervision.manageassociate.form.AssociateForm;

public class DisplayAssociateSummaryAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 45E5DF800203
    */
   public DisplayAssociateSummaryAction(){
    
   }
   
   protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.reset", "reset");
		keyMap.put("button.copy", "copySuperviseeResidenceAddress");
	}
   
/*	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}
	*/
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
							  HttpServletRequest aRequest, HttpServletResponse aResponse){
		AssociateForm  associateForm = (AssociateForm) aForm;
	 /* 	associateForm.setAction("createSummary");*/ 
		
		
		return aMapping.findForward(UIConstants.CREATE_SUMMARY);
	}
	
	public ActionForward reset(ActionMapping aMapping, ActionForm aForm,
							   HttpServletRequest aRequest, HttpServletResponse aResponse){
  	   AssociateForm  associateForm = (AssociateForm) aForm;
  	   associateForm.clear();
	   return aMapping.findForward(UIConstants.RESET_SUCCESS);
   }

	public ActionForward copySuperviseeResidenceAddress(ActionMapping aMapping, ActionForm aForm,
					   HttpServletRequest aRequest, HttpServletResponse aResponse){
		AssociateForm  associateForm = (AssociateForm) aForm;
	   	
		// Sending PD Request Event
	   	GetCopySuperviseeResidenceAddressEvent evt = UIManageAssociateHelper.getCopySuperviseeResidenceAddressEvent(associateForm); 
	   	
	   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	   	dispatch.postEvent(evt);

		//	Get PD Response Event
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// check for errors
		ReturnException returnException =(ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException != null){
		            return aMapping.findForward(UIConstants.FAILURE);
		}

		PartyResponseEvent partyAddressResponse = (PartyResponseEvent) MessageUtil.filterComposite(response, PartyResponseEvent.class);
		
		associateForm.copySuperviseeResidenceAddress(partyAddressResponse);
		
		return aMapping.findForward(UIConstants.COPY_SUCCESS);
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
