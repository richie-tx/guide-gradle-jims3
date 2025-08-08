//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\DisplaySpnSplitSelectSupPeriodAction.java

package ui.commonfunctionality.spnsplit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import messaging.spnsplit.GetSpnSplitTopicEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SpinSplitConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.commonfunctionality.spnsplit.form.SpnSplitForm;


public class DisplaySpnSplitSelectedTopicAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 4561E28501D8
    */
   public DisplaySpnSplitSelectedTopicAction() 
   {
    
   }
   
	protected void addButtonMapping(Map keyMap) {
	    keyMap.put("button.next", "next");
	}
   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */	
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	        HttpServletResponse aResponse) {
	    SpnSplitForm myForm = (SpnSplitForm) aForm;
	    String forward = UIConstants.SUCCESS;
	    myForm.setSelectedValue("");
	    myForm.setListsSet(false);
	    myForm.setCurrentAssessments(new ArrayList());
	    myForm.setCurrentAssociates(new ArrayList());
	    myForm.setCurrentSupervisionPlans(new ArrayList());

	    if (myForm.getSelectedTopic().equalsIgnoreCase(UIConstants.ASSESSMENT)){
	    	findAssessments(myForm, myForm.getErroneousSpn().getSpn(), aRequest);
	    }else if (myForm.getSelectedTopic().equalsIgnoreCase(UIConstants.ASSOCIATE)){
	    	findAssociates(myForm, myForm.getErroneousSpn().getSpn(), aRequest);
	    }else if (myForm.getSelectedTopic().equalsIgnoreCase(UIConstants.SUPERVISION_PLAN)){
	    	findSupervisionPlans(myForm, myForm.getErroneousSpn().getSpn(), aRequest);
	    }
	    if (myForm.isListsSet() == false){
	    	forward = UIConstants.FAILURE;
	    }
	    return aMapping.findForward(forward); 
   }

	/**
	 * @param SpnSplitForm
	 * @param SPN
	 */
	private void findAssessments(SpnSplitForm ssForm, String SPN, HttpServletRequest aRequest)
	{
    	GetSpnSplitTopicEvent gEvent = new GetSpnSplitTopicEvent();
        gEvent.setTopic(SpinSplitConstants.ASSESSMENT);
        gEvent.setDefendantId(SPN);
        
        CompositeResponse response = MessageUtil.postRequest(gEvent);
        List list = MessageUtil.compositeToList(response, AssessmentSummaryResponseEvent.class);
        if  (list != null && !list.isEmpty()){
    		List<AssessmentSummaryResponseEvent> assessments = list ;
    		for( AssessmentSummaryResponseEvent aEvent : assessments )
    		{
    		  if( aEvent.getAssessmentTypeId() != null && !aEvent.getAssessmentTypeId().equals("") )
    		  {
    			String assessmentTypeName = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE,
    					aEvent.getAssessmentTypeId());
    			aEvent.setAssessmentTypeName(assessmentTypeName);
    		  }
    		}
        	ssForm.setCurrentAssessments(list);
        	ssForm.setListsSet(true);
		} else {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No assessment found for Erroneous SPN");
		}	
	}

	/**
	 * @param SpnSplitForm
	 * @param SPN
	 */
	private void findAssociates(SpnSplitForm ssForm, String eSPN, HttpServletRequest aRequest)
	{
		GetSpnSplitTopicEvent gEvent = new GetSpnSplitTopicEvent();
        gEvent.setTopic(SpinSplitConstants.ASSOCIATE);
// this leading zero strip may need to be removed at a future date
        gEvent.setDefendantId(stripLeadingZeroes(eSPN));  
        
        CompositeResponse response = MessageUtil.postRequest(gEvent);
        List list = MessageUtil.compositeToList(response, AssociateResponseEvent.class);
        if  (list != null && !list.isEmpty()){
        	String assocName = "";
        	String commaSp = ", ";
    		List<AssociateResponseEvent> associates = list;
    		for( AssociateResponseEvent aEvent : associates )
    		{
    			if( aEvent.getRelationshipTypeId() != null && !aEvent.getRelationshipTypeId().equals("") ) {
    				aEvent.setRelationship(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSCD_RELATIONSHIP, aEvent.getRelationshipTypeId()));
    				commaSp = ", ";
	    			if (aEvent.getAssocLastName() == null){
	    				aEvent.setAssocLastName("");
	    			}
	    			if (aEvent.getAssocFirstName() == null){
	    				aEvent.setAssocFirstName("");
	    			}
	    			if (aEvent.getAssocMiddleName() == null){
	    				aEvent.setAssocMiddleName("");
	    			}
	    			if (aEvent.getAssocFirstName().equals("") && aEvent.getAssocMiddleName().equals("")) {
	    				commaSp = "";
	    			}	
	    			assocName = aEvent.getAssocLastName() + commaSp + aEvent.getAssocFirstName() + " " + aEvent.getAssocMiddleName();
	    			aEvent.setAssocFormattedName(assocName.trim());
	    		  }
    		}
        	ssForm.setCurrentAssociates(list);
        	ssForm.setListsSet(true);
		} else {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No associates found for Erroneous SPN");
		}	
	}

	private String stripLeadingZeroes(String strValue){
		String temp = "";
		while (strValue.substring(0, 1).equals("0")){
			temp = strValue.replaceFirst("0", "");
			strValue = temp;
		}
		return strValue;
	}
	/**
	 * @param SpnSplitForm
	 * @param SPN
	 */
	private void findSupervisionPlans(SpnSplitForm ssForm, String eSPN, HttpServletRequest aRequest)
	{
		GetSpnSplitTopicEvent gEvent = new GetSpnSplitTopicEvent();
        gEvent.setTopic(SpinSplitConstants.SUPERVISION_PLAN);
        gEvent.setDefendantId(eSPN);  
        
        CompositeResponse response = MessageUtil.postRequest(gEvent);
        List list = MessageUtil.compositeToList(response, SupervisionPlanDetailsResponseEvent.class);
        if (list != null && !list.isEmpty()){
    		List<SupervisionPlanDetailsResponseEvent> supervisionPlans = list;
    		for( SupervisionPlanDetailsResponseEvent spEvent : supervisionPlans )
    		{
    			if( spEvent.getStatusCd() != null && !spEvent.getStatusCd().equals("") ) {
    				spEvent.setStatusLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS, spEvent.getStatusCd()));
    			}
    		}
        	ssForm.setCurrentSupervisionPlans(list);
        	ssForm.setListsSet(true);
		} else {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No supervision plans found for Erroneous SPN");
		}	
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	        HttpServletResponse aResponse) {
		SpnSplitForm ssForm = (SpnSplitForm) aForm;
		ssForm.setCurrentAssessments(new ArrayList());
		ssForm.setCurrentAssociates(new ArrayList());
		ssForm.setCurrentSupervisionPlans(new ArrayList());
	    return aMapping.findForward(UIConstants.BACK);
	}
	
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	        HttpServletResponse aResponse) {
		SpnSplitForm myForm = (SpnSplitForm) aForm;
	    myForm.clear();
	    return aMapping.findForward(UIConstants.CANCEL_MAIN_PAGE_HOME);
	}
				
}