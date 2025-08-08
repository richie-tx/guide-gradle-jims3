//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageassociate\\action\\HandleAssociateDisplayOptionsAction.java

package ui.supervision.manageassociate.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageassociate.GetSuperviseeAssociateEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.manageassociate.form.AssociateForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleAssociateDisplayOptionsAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 45E5DF8102CE
    */
   public HandleAssociateDisplayOptionsAction() 
   {
    
   }
   
   protected void addButtonMapping(Map keyMap)
	{
   		keyMap.put("button.link", "link");
		keyMap.put("button.next", "next");
		keyMap.put("button.reset", "reset");
		keyMap.put("button.addAssociate", "addAssociate");
		keyMap.put("button.update", "update");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.view", "view");
	}
   
   public ActionForward link(ActionMapping aMapping, ActionForm aForm,
		  HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
   		
		String selVal=(String)aRequest.getParameter("selectedValue");
		String fromPath=(String)aRequest.getParameter("fromPath");
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		String oldPath=forward.getPath();
		String newPath=oldPath + "&selectedValue=" + selVal + "&fromPath=" + fromPath;
		loadHeaderInfo(aMapping, aRequest, selVal);
		//forward.setPath(newPath);
		return new ActionForward(newPath);
	}
   
   public ActionForward addAssociate(ActionMapping aMapping, ActionForm aForm,
		  HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
	
		AssociateForm associateForm = (AssociateForm)aForm;
		SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		
		if (associateForm.getFromPath().equals(UIConstants.FROM_CASELOAD)){
			associateForm.setSelectedValue(myHeaderForm.getSuperviseeId());
		}
		else{  // if not from caseload then must have SPN coming into selectedValue field on form to work
			String superviseeId=associateForm.getSelectedValue();
			myHeaderForm.setSuperviseeId(superviseeId);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		}
		
		
		associateForm.setupNewAssociate(associateForm.getSelectedValue(), associateForm.getFromPath());
		
		return aMapping.findForward(UIConstants.CREATE);
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
		}
		} 
		return aMapping.findForward(UIConstants.CANCEL);
	}
   
   public ActionForward update(ActionMapping aMapping, ActionForm aForm,
		  HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
	
		AssociateForm associateForm = (AssociateForm)aForm;
		
		associateForm.setAction("update");
		String associateId = aRequest.getParameter("associateId");
		
		if (associateId!=null && !associateId.equals("")){
			this.populateAssociateForm(associateForm,associateId);	
		}
		
		return aMapping.findForward(UIConstants.UPDATE);
	}
   
   public ActionForward delete(ActionMapping aMapping, ActionForm aForm,
		  HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
	
		AssociateForm associateForm = (AssociateForm)aForm;
		
		associateForm.setAction("delete");
		String associateId = aRequest.getParameter("associateId");
		
		if (associateId!=null && !associateId.equals("")){
			this.populateAssociateForm(associateForm,associateId);	
		}
		
		return aMapping.findForward(UIConstants.DELETE);
	}
   
   	public void populateAssociateForm(AssociateForm associateForm,String associateId){
	
   		GetSuperviseeAssociateEvent supAssocEvt = new GetSuperviseeAssociateEvent();				
   		supAssocEvt.setAssociateId(associateId);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(supAssocEvt);
		
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);
		
		AssociateResponseEvent associateRespEvt = (AssociateResponseEvent)MessageUtil.filterComposite(response, AssociateResponseEvent.class);
		
		associateForm.setForm(associateRespEvt);
		return;	
	}
   	
   	public ActionForward view(ActionMapping aMapping, ActionForm aForm,
  		  HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
  	
   		AssociateForm  associateForm = (AssociateForm) aForm;
   	 /* 	associateForm.setAction("createSummary");*/ 
   		
   		associateForm.setAction("view");
		String associateId = aRequest.getParameter("associateId");
		
		if (associateId!=null && !associateId.equals("")){
			this.populateAssociateForm(associateForm,associateId);	
		}
   		 
   		return aMapping.findForward(UIConstants.VIEW);
  	}
   	
   	private void loadHeaderInfo(ActionMapping aMapping,HttpServletRequest aRequest,String superviseeId) throws GeneralFeedbackMessageException {
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm)getSessionForm(aMapping, aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		myHeaderForm.setSuperviseeId(superviseeId);
	}
}
