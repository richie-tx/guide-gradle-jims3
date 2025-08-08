/*
 * Created on Aug 2, 2006
 *
 */
package ui.supervision.administercasenotes.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.reply.SupervisionPeriodNotFoundEvent;
import messaging.domintf.contact.party.ISupervisee;
import messaging.supervisionorder.reply.SuperviseeResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.BasicSupervisee;
import ui.supervision.CaseInfo;
import ui.supervision.SupervisionPeriodInfo;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;

/**
 * @author jjose
 *  
 */
public class DisplayAdministerCasenotesSearchResultsAction extends
		JIMSBaseAction {

	private static final String ACTIVE = "A";

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.compliance", "compliance");
		keyMap.put("button.assessments", "assessments");
		keyMap.put("button.viewCasenotes", "viewCasenote");
		keyMap.put("button.createCasenote", "createCasenote");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.link", "viewCasenote");
	}
	
	public ActionForward compliance(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)throws ui.exception.GeneralFeedbackMessageException {
		SuperviseeSearchForm superviseeSearchForm = (SuperviseeSearchForm) aForm;
		String myForward="toCompliance";
		String spn=superviseeSearchForm.getSelectedValue();
		if(spn==null || spn.trim().equals("")){
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"A Supervisee must be selected");
			myForward=UIConstants.FAILURE;
		}
		else{
			SuperviseeInfoHeaderForm supInfoHeaderForm = (SuperviseeInfoHeaderForm)getSessionForm(aMapping, aRequest, "superviseeInfoHeaderForm", true);	    
			supInfoHeaderForm.setSpn(spn);
		}
		return aMapping.findForward(myForward);
	}
	
	public ActionForward assessments(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)throws ui.exception.GeneralFeedbackMessageException {
		SuperviseeSearchForm superviseeSearchForm = (SuperviseeSearchForm) aForm;
		String myForward="toAssessments";
		String spn=superviseeSearchForm.getSelectedValue();
		if(spn==null || spn.trim().equals("")){
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"A Supervisee must be selected");
			myForward=UIConstants.FAILURE;
		}
		else{
			SuperviseeInfoHeaderForm supInfoHeaderForm = (SuperviseeInfoHeaderForm)getSessionForm(aMapping, aRequest, "superviseeInfoHeaderForm", true);	    
			supInfoHeaderForm.setSpn(spn);
		}
		return aMapping.findForward(myForward);
	}

	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeSearchForm superviseeSearchForm = (SuperviseeSearchForm) aForm;
		CasenoteJournalForm myJournalForm = UICommonSupervisionHelper.getCasenoteJournalForm(aRequest, true);
		String myForward=commonFunctionality(aMapping,aForm,aRequest,aResponse);
		return aMapping.findForward(myForward);
	}
	
	private String commonFunctionality(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse){
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		CasenoteJournalForm myJournalForm = UICommonSupervisionHelper.getCasenoteJournalForm(aRequest, true);
		CasenoteSearchForm searchForm = myJournalForm.getSearchCasenote();
		myJournalForm.clearAll();
		myJournalForm.setAction(UIConstants.VIEW);
		myJournalForm.clearCurrentCasenoteAction();
		myForm.setSpn(myForm.getSelectedValue());
		List supervisees=null;
		String myForward=null;
		if(myForm.getSupervisionPeriodAsBool()){  // Basic Active Name or SPN search
			supervisees=tempBasicSupSearch(UIConstants.SPN,myForm,aRequest,null);
			if(supervisees!=null && supervisees.size()>0){
				BasicSupervisee superviseeInfo = (BasicSupervisee) supervisees.iterator().next();
				if(superviseeInfo.getCases()!=null && superviseeInfo.getCases().size()>0){
					CaseInfo myCaseInfo=(CaseInfo)superviseeInfo.getCases().iterator().next();
					myForm.setSupervisionPeriodId(myCaseInfo.getSupPeriodId());
				}
			}
		}
		else{  // passive search			
			List mySupPeriodsList=UICasenoteHelper.getPriorSupervisionPeriods(myForm.getSpn(),SecurityUIHelper.getUserAgencyId());
			if(mySupPeriodsList==null && mySupPeriodsList.size()==0){ // Basic Passive Spn Search no sup periods to choose from
				sendToErrorPage(aRequest, "error.noSupPeriods");
				myForward=UIConstants.FAILURE;
			}
			else if(mySupPeriodsList!=null && mySupPeriodsList.size()==1){  // Basic Passive Spn Search one sup period only
				SupervisionPeriodInfo mySupInfo=(SupervisionPeriodInfo)mySupPeriodsList.get(0);
				myForm.setSupervisionPeriodId(mySupInfo.getSupPeriodId());
				if(supervisees==null || supervisees.size()<=0){
					supervisees=tempBasicSupSearch(UIConstants.SPN, myForm,aRequest,mySupInfo.getSupPeriodId());
				}
			}
			else{ // Basic Passive Spn more than one period so must sho periods first to select
				myForm.setSearchSupPeriodsResults(mySupPeriodsList);
				if(supervisees==null || supervisees.size()<=0){
					SupervisionPeriodInfo mySupInfo=(SupervisionPeriodInfo)mySupPeriodsList.get(0);
					supervisees=tempBasicSupSearch(UIConstants.SPN, myForm,aRequest,mySupInfo.getSupPeriodId());
				}
				myForward="supervisionPeriodSelect";
			}
		}
			BasicSupervisee superviseeInfo = new BasicSupervisee();
					
			
			if(supervisees != null){
				myForm.setSearchResults(supervisees);
				superviseeInfo = (BasicSupervisee) supervisees.iterator().next();
			} else {
				superviseeInfo = (BasicSupervisee) myForm.getSearchResults().iterator().next();
//				supervisees = new ArrayList();
//				supervisees.add(superviseeInfo);
//				myForm.setSearchResults(supervisees);
//				if(superviseeInfo == null){
					sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"SPN not found");
					return "error";
//				}
			}
			
		//	myForm.setSupervisionPeriodId(superviseeInfo.getSupervisionPeriodId());
		//	 Set spn header info
				String myRetVal=UICommonSupervisionHelper.loadSupHeaderInfoFromSupSearchForm(aRequest,myForm, superviseeInfo,myForm.getSupervisionPeriodId());
			
			if(myRetVal!=null){
				sendToErrorPage(aRequest,myRetVal);
				myForward=UIConstants.FAILURE;
			}
			else{ // no errors setting header now if 
					myJournalForm.setSuperviseeId(myForm.getSpn());
					myJournalForm.setSupervisionPeriod(myForm.getSupervisionPeriod());
					myJournalForm.setSupervisionPeriodId(myForm.getSupervisionPeriodId());
					HashMap mySupPeriodMap = new HashMap();
					if(superviseeInfo.getCases()!=null && superviseeInfo.getCases().size()>0){
						Iterator caseIter=superviseeInfo.getCases().iterator();
						while (caseIter.hasNext()) {
							CaseInfo caseInfoObj = (CaseInfo) caseIter.next();
							if (mySupPeriodMap.containsKey(caseInfoObj.getSupPeriodId())) {
								SupervisionPeriodInfo myPeriodInfo = (SupervisionPeriodInfo) mySupPeriodMap
										.get(caseInfoObj.getSupPeriodId());
								myPeriodInfo.addCase(caseInfoObj);
							}
							else{
								Collection mySupPeriodsList=myForm.getSearchSupPeriodsResults();
								if(mySupPeriodsList!=null && mySupPeriodsList.size()>0){
									Iterator mySupPeriodIter=mySupPeriodsList.iterator();
									while(mySupPeriodIter.hasNext()){
										SupervisionPeriodInfo mySupInfo=(SupervisionPeriodInfo)mySupPeriodIter.next();
										if(caseInfoObj.getSupPeriodId().equals(mySupInfo.getSupPeriodId())){
											mySupPeriodMap.put(mySupInfo.getSupPeriodId(),mySupInfo);
											mySupInfo.addCase(caseInfoObj);
											break;
										}
										
									}
								}
							}
						}
					}
					
			}
		return myForward;
	}

private List tempBasicSupSearch(String aSearchBy,SuperviseeSearchForm myForm, HttpServletRequest aRequest, String aSelectedSupPerId){
	List foundSups=null;
	String myForward = UIConstants.SUCCESS;
	Collection myFoundSupsRespEvts=null;
	if(aSearchBy.equalsIgnoreCase(UIConstants.SPN)){
		String spn = myForm.getSpn();
		if (spn != null && spn.trim().length() > 0) {	
			CompositeResponse response=null;
				 response = UICommonSupervisionHelper.getSuperviseeInfo(
						SecurityUIHelper.getUserAgencyId(), spn, myForm.getSupervisionPeriodAsBool(),
						aSelectedSupPerId);
			if (MessageUtil.filterComposite(response,
					SupervisionPeriodNotFoundEvent.class) != null) {
				// RETURN WITH ERROR PAGE
				sendToErrorPage(aRequest, "error.invalidSupPeriod");
			} else {
				myFoundSupsRespEvts = MessageUtil.compositeToCollection(response,
						SuperviseeResponseEvent.class);
			}
			if (myFoundSupsRespEvts != null && !myFoundSupsRespEvts.isEmpty()) {
				foundSups=new ArrayList();
				ISupervisee superviseeInfo = (ISupervisee) myFoundSupsRespEvts.iterator().next();
				foundSups.add(UICasenoteHelper
						.getBasicSupervisee(superviseeInfo));
			}
		} 
	}
	return foundSups;
}
	

	
	
	public ActionForward viewCasenote(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeSearchForm superviseeSearchForm = (SuperviseeSearchForm) aForm;
		CasenoteJournalForm myJournalForm = UICommonSupervisionHelper.getCasenoteJournalForm(aRequest, true);
		CasenoteSearchForm searchForm = myJournalForm.getSearchCasenote();
		myJournalForm.clearAll();
		myJournalForm.setAction(UIConstants.VIEW);
		myJournalForm.clearCurrentCasenoteAction();
		myJournalForm.setSuperviseeId(superviseeSearchForm.getSelectedValue());
		myJournalForm.setSupervisionPeriodId(superviseeSearchForm.getSupervisionPeriodId());
		String myForward=commonFunctionality(aMapping,superviseeSearchForm,aRequest,aResponse);
		if(myForward==null)
			return aMapping.findForward("adminJournalView");
		else
			return aMapping.findForward(myForward);
	}


	public ActionForward createCasenote(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SuperviseeSearchForm superviseeSearchForm = (SuperviseeSearchForm) aForm;
		CasenoteJournalForm myJournalForm = UICommonSupervisionHelper.getCasenoteJournalForm(aRequest, true);
		myJournalForm.clearAll();
		myJournalForm.setAction(UIConstants.VIEW);
		myJournalForm.clearCurrentCasenoteAction();
		myJournalForm.getCurrentCasenote().setAction(UIConstants.CREATE);
		CasenoteSearchForm searchForm = myJournalForm.getSearchCasenote();
		searchForm.clearAll();
		myJournalForm.setSuperviseeId(superviseeSearchForm.getSelectedValue());
		myJournalForm.setSupervisionPeriodId(superviseeSearchForm.getSupervisionPeriodId());
		String myForward=commonFunctionality(aMapping,superviseeSearchForm,aRequest,aResponse);
		if(myForward==null)
			return aMapping.findForward("adminJournalCreate");
		else
			return aMapping.findForward(myForward);
	}

	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}



}// END CLASS

