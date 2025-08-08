//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\DisplaySpnSplitSummaryAction.java

package ui.commonfunctionality.spnsplit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.commonfunctionality.spnsplit.form.SpnSplitForm;


public class DisplaySpnSplitTopicSummaryAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 4561E28602A4
    */
   public DisplaySpnSplitTopicSummaryAction() 
   {
   }

   /**
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    * @return Map
    */
	protected void addButtonMapping(Map keyMap) {
       keyMap.put("button.next", "next");
	}
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 455E28BC00E8
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
        SpnSplitForm myForm = (SpnSplitForm) aForm;
	    if (myForm.getSelectedTopic().equalsIgnoreCase(UIConstants.ASSESSMENT)){
	    	loadSelectedAssessments(myForm, aRequest);
	    }else if (myForm.getSelectedTopic().equalsIgnoreCase(UIConstants.ASSOCIATE)){
	    	loadSelectedAssociates(myForm, aRequest);
	    }else if (myForm.getSelectedTopic().equalsIgnoreCase(UIConstants.SUPERVISION_PLAN)){
	    	loadSelectedSupervisionPlans(myForm, aRequest);
	    }
        return aMapping.findForward(UIConstants.SUCCESS);
   }
   
	/**
	 * @param SpnSplitForm
	 * @param aRequest
	 */
	private void loadSelectedAssessments(SpnSplitForm ssForm,HttpServletRequest aRequest)
	{
		ssForm.setSelectedAssessments(new ArrayList());
		List aEvents = new ArrayList();
		if (ssForm.getSelectedAssessmentIds() != null){
			for (int x = 0; x < ssForm.getSelectedAssessmentIds().length; x++) {
				List<AssessmentSummaryResponseEvent> assessments = ssForm.getCurrentAssessments();
				for( AssessmentSummaryResponseEvent aEvent : assessments ) {
					if( aEvent.getAssessmentId() != null && aEvent.getAssessmentId().equals(ssForm.getSelectedAssessmentIds()[x]) ) {
						aEvents.add(aEvent);
						break;
					}
				}
			}	
	       	ssForm.setSelectedAssessments(aEvents);
		}
		if (ssForm.getSelectedAssessments().isEmpty()) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No assessment selected to process");
		}	
	}

	/**
	 * @param SpnSplitForm
	 * @param aRequest
	 */
	private void loadSelectedAssociates(SpnSplitForm ssForm,HttpServletRequest aRequest)
	{
		ssForm.setSelectedAssociates(new ArrayList());
		List assocEvents = new ArrayList();
		if (ssForm.getSelectedAssociateIds() != null){
			for (int x = 0; x < ssForm.getSelectedAssociateIds().length; x++) {
				List<AssociateResponseEvent> associates = ssForm.getCurrentAssociates();
				for( AssociateResponseEvent aEvent : associates ) {
					if( aEvent.getAssociateId() != null && aEvent.getAssociateId().equals(ssForm.getSelectedAssociateIds()[x]) ) {
						assocEvents.add(aEvent);
						break;
					}
				}
			}	
	       	ssForm.setSelectedAssociates(assocEvents);
		}
		if (ssForm.getSelectedAssociates().isEmpty()) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No associates found");
		}
	}	

	/**
	 * @param SpnSplitForm
	 * @param aRequest
	 */
	private void loadSelectedSupervisionPlans(SpnSplitForm ssForm,HttpServletRequest aRequest)
	{
		ssForm.setSelectedSupervisionPlans(new ArrayList());
		List spEvents = new ArrayList();
		if (ssForm.getSelectedSupervisionPlanIds() != null){
			for (int x = 0; x < ssForm.getSelectedSupervisionPlanIds().length; x++) {
				List<SupervisionPlanDetailsResponseEvent> sPlans = ssForm.getCurrentSupervisionPlans();
				for( SupervisionPlanDetailsResponseEvent spEvent : sPlans ) {
					if( spEvent.getSupervisionPlanId() != null && spEvent.getSupervisionPlanId().equals(ssForm.getSelectedSupervisionPlanIds()[x]) ) {
						spEvents.add(spEvent);
						break;
					}
				}
			}	
	       	ssForm.setSelectedSupervisionPlans(spEvents);
		}
		if (ssForm.getSelectedSupervisionPlans().isEmpty()) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No supervision plan found");
		}
	}
  
}