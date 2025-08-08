/*
 * Created on Aug 2, 2006
 *
 */
package ui.supervision.administercasenotes.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.GetSuperviseesInSupervisionPeriodEvent;
import messaging.administercasenotes.reply.SupervisionPeriodNotFoundEvent;
import messaging.contact.domintf.IName;
import messaging.criminalcase.GetCaseEvent;
import messaging.domintf.contact.party.ISupervisee;
import messaging.info.reply.CountInfoMessage;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.SuperviseeListResponseEvent;
import messaging.supervisionorder.reply.SuperviseeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CasenoteControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.supervision.BasicSupervisee;
import ui.supervision.CaseInfo;
import ui.supervision.SupervisionPeriodInfo;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;

/**
 * @author jjose
 *  
 */
public class DisplayAdministerCasenotesSearchAction extends
		LookupDispatchAction {

	public static final String ACTIVE = "A";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.advancedSuperviseeSearch", "advancedSearch");
		keyMap.put("button.basicSearch", "basicSearch");
		keyMap.put("button.link", "link");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}


	
	
	private ActionForward tempCommonSuperviseeSearch(ActionMapping aMapping, SuperviseeSearchForm myForm, HttpServletRequest aRequest){
		List supervisees=null;
		String myForward=null;
		myForm.setSearchSupPeriodsResults(null);
		boolean forwardFound=false;
		myForm.setSupervisionPeriodId("");
		if(myForm.getAction().equalsIgnoreCase(UIConstants.BASIC)){
			if(myForm.getSupervisionPeriodAsBool()){  // Basic Active Name or SPN search
				supervisees=tempBasicSupSearch(myForm.getSearchBy(),myForm,aRequest,null);
				if(supervisees!=null && supervisees.size()>0){
					BasicSupervisee superviseeInfo = (BasicSupervisee) supervisees.iterator().next();
					if(superviseeInfo.getCases()!=null && superviseeInfo.getCases().size()>0){
						CaseInfo myCaseInfo=(CaseInfo)superviseeInfo.getCases().iterator().next();
						myForm.setSupervisionPeriodId(myCaseInfo.getSupPeriodId());
					} else {
					    //Supervision period not set when prior search was name search.
					    myForm.setSupervisionPeriodId(superviseeInfo.getSupervisionPeriodId());
					}
				}
			}
			else{  // passive search
				if(!(myForm.getSearchBy().equalsIgnoreCase(UIConstants.SPN))){  // Basic Passive Name search 
					myForm.setSpn("");
					supervisees=tempBasicSupSearch(myForm.getSearchBy(),myForm,aRequest,null);
					if(supervisees!=null && supervisees.size()==1){   // If only one result treat as spn search passive
						BasicSupervisee mySup=(BasicSupervisee)supervisees.get(0);
						myForm.setSpn(mySup.getSpn());
					}
				}
				if((myForm.getSearchBy().equalsIgnoreCase(UIConstants.SPN)) || (myForm.getSpn()!=null && !myForm.getSpn().equals(""))){ // Passive SPN Search
					List mySupPeriodsList=UICasenoteHelper.getPriorSupervisionPeriods(myForm.getSpn(),SecurityUIHelper.getUserAgencyId());
					if(mySupPeriodsList==null || mySupPeriodsList.size()==0){ // Basic Passive Spn Search no sup periods to choose from
						sendToErrorPage(aRequest, "error.noSupPeriods");
						myForward=UIConstants.BASIC_SEARCH_SUCCESS;
						forwardFound=true;
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
						forwardFound=true;
					}
				}
			}
		}
		else{  // advanced search either passive or active
			supervisees=tempAdvancedSearch(myForm,aRequest);
			if(!myForm.getSupervisionPeriodAsBool()){  // If Not Advanced Passive Search aka passive serach
				if(supervisees!=null && supervisees.size()==1){   // If only one result treat as spn search passive
					BasicSupervisee mySup=(BasicSupervisee)supervisees.get(0);
					myForm.setSpn(mySup.getSpn());
					List mySupPeriodsList=UICasenoteHelper.getPriorSupervisionPeriods(myForm.getSpn(),SecurityUIHelper.getUserAgencyId());
					if(mySupPeriodsList==null && mySupPeriodsList.size()==0){ // Advanced Passive Spn Search no sup periods to choose from
						sendToErrorPage(aRequest, "error.noSupPeriods");
						myForward=UIConstants.ADVANCED_SEARCH_SUCCESS;
						forwardFound=true;
					}
					else if(mySupPeriodsList!=null && mySupPeriodsList.size()==1){  // Advanced Passive Spn Search one sup period only
						SupervisionPeriodInfo mySupInfo=(SupervisionPeriodInfo)mySupPeriodsList.get(0);
						supervisees=tempBasicSupSearch(UIConstants.SPN, myForm,aRequest,mySupInfo.getSupPeriodId());
						myForm.setSupervisionPeriodId(mySupInfo.getSupPeriodId());
					}
					else{ // Advanced Passive Spn more than one period so must sho periods first to select
						myForm.setSearchSupPeriodsResults(mySupPeriodsList);
						SupervisionPeriodInfo mySupInfo=(SupervisionPeriodInfo)mySupPeriodsList.get(0);
						supervisees=tempBasicSupSearch(UIConstants.SPN, myForm,aRequest,mySupInfo.getSupPeriodId());
						myForward="supervisionPeriodSelect";
						forwardFound=true;
					}
				}
			}
			else{
				if(supervisees!=null && supervisees.size()>0){
					BasicSupervisee superviseeInfo = (BasicSupervisee) supervisees.iterator().next();
					if(superviseeInfo.getCases()!=null && superviseeInfo.getCases().size()>0){
						CaseInfo myCaseInfo=(CaseInfo)superviseeInfo.getCases().iterator().next();
						myForm.setSupervisionPeriodId(myCaseInfo.getSupPeriodId());
					} else {
					    myForm.setSupervisionPeriodId(superviseeInfo.getSupervisionPeriodId());
					}
				}
			}
		}
		if (supervisees == null || supervisees.isEmpty()) {
			sendToErrorPage(aRequest, "error.no.search.results.found");
			boolean isTooManyFound = this.isTooManyResults(aRequest);
			if(isTooManyFound){
			    sendToErrorPage(aRequest, "error.max.limit.exceeded");
			}
			if(myForm.getAction().equalsIgnoreCase(UIConstants.BASIC)){
				myForward=UIConstants.BASIC_SEARCH_SUCCESS;
			}
			else{
				myForward=UIConstants.ADVANCED_SEARCH_SUCCESS;
			}
			forwardFound=true;	
			
		}
		else if(supervisees.size()==1){  // only one spn found do single spn found
			myForm.setSearchResults(supervisees);
			
			BasicSupervisee superviseeInfo = (BasicSupervisee) supervisees.iterator().next();
			//Rebuild BasicSupervisee object if hasPartialInfo is true. This situation occurs
			//when a search by name or advanced search was done and only one search result
			//was found.  This BasicSupervisee object was built from a SuperviseeListResponseEvent
			//which does not have employment, cases and address information.
			if (superviseeInfo.isHasPartialInfo()){
			    myForm.setSpn(superviseeInfo.getSpn());
				supervisees=tempBasicSupSearch("SPN",myForm,aRequest,null);
				if(supervisees!=null && supervisees.size()>0){
					superviseeInfo = (BasicSupervisee) supervisees.iterator().next();
				}
			} 
			myForm.setSpn(superviseeInfo.getSpn());
//			 Set spn header info
			String myRetVal=UICommonSupervisionHelper.loadSupHeaderInfoFromSupSearchForm(aRequest,myForm, superviseeInfo,myForm.getSupervisionPeriodId());
			if(myRetVal!=null){
				sendToErrorPage(aRequest,myRetVal);
				if(myForm.getAction().equalsIgnoreCase(UIConstants.BASIC)){
					myForward=UIConstants.BASIC_SEARCH_SUCCESS;
				}
				else{
					myForward=UIConstants.ADVANCED_SEARCH_SUCCESS;
				}
			}
			else{ // no errors setting header now if 
				if(!forwardFound){  // able to go to journal directly
					CasenoteJournalForm myJournalForm = UICommonSupervisionHelper
					.getCasenoteJournalForm(aRequest, true);
					myJournalForm.setSuperviseeId(myForm.getSpn());
					//06/30/09 dag - For some reason setSupervisionPeriodId() was commented out and should
					//not be.  The supervision period OID is integral to Administer Casenotes.
					myJournalForm.setSupervisionPeriod(myForm.getSupervisionPeriod());
					myJournalForm.setSupervisionPeriodId(myForm.getSupervisionPeriodId());
					myForward="adminJournalView";
				    forwardFound=true;
				}
				else{   // a forward was set so this means mulitple sup periods where found
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
			}
		}
		else { // Multiple Spn's found do spn selection search
			Collections.sort((List)supervisees);
			myForm.setSearchResults(supervisees);
			myForward = UIConstants.SUCCESS;
		}
		return aMapping.findForward(myForward);
	}

	
	public List tempBasicSupSearch(String aSearchBy,SuperviseeSearchForm myForm, HttpServletRequest aRequest, String aSelectedSupPerId){
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
		} else if(aSearchBy.equalsIgnoreCase(UIConstants.CASE)){						
			
			
			String spn = UICommonSupervisionHelper.getSpnFromCaseFileId(myForm.getCdi()+myForm.getCaseNum());
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
		else if (myForm.getSuperviseeName() != null) {
			foundSups=tempAdvancedSearch(myForm,aRequest);
		}
		return foundSups;
	}
	
	/**
	 * returns a list of BasicSupervisee Objects, either returns back null, 1, or multiple
	 * @param myForm
	 * @return
	 */
	private List tempAdvancedSearch(SuperviseeSearchForm myForm, HttpServletRequest aRequest){
		IDispatch dispatch = EventManager
		.getSharedInstance(EventManager.REQUEST);

		GetSuperviseesInSupervisionPeriodEvent requestEvent = (GetSuperviseesInSupervisionPeriodEvent) EventFactory
				.getInstance(CasenoteControllerServiceNames.GETSUPERVISEESINSUPERVISIONPERIOD);
		
		IName name = myForm.getSuperviseeName();
		requestEvent.setFirstName(name.getFirstName());
		requestEvent.setLastName(name.getLastName());
		requestEvent.setActiveSupervisionPeriod(myForm.getSupervisionPeriodAsBool());
		requestEvent.setUserAgencyId(SecurityUIHelper.getUserAgencyId());
		requestEvent.setCjisNum(myForm.getCJIS());
		requestEvent.setSsn(myForm.getSsn().toString());
		requestEvent.setSidNum(myForm.getSID());
		requestEvent.setFbiNum(myForm.getFBI());
		requestEvent.setActiveSupervisionPeriod(myForm
				.getSupervisionPeriodAsBool());
		requestEvent.setUserAgencyId(SecurityUIHelper.getUserAgencyId());
		requestEvent.setRaceId(myForm.getRaceId());
		requestEvent.setSexId(myForm.getSexId());
		requestEvent.setDriverLicenseNum(myForm.getDlNum());
		requestEvent.setDriverLicenseStateId(myForm.getDlStateId());
		requestEvent.setDateOfBirth(myForm.getDateOfBirth());
		
		dispatch.postEvent(requestEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch
				.getReply();
		
		/*if (MessageUtil.filterComposite(compositeResponse, CountInfoMessage.class) != null){
		    sendToErrorPage(aRequest, "error.max.limit.exceeded");
		   tooManyResults = true;
		    }*/
		
		MessageUtil.processReturnException(compositeResponse);
		
		Collection supervisees = MessageUtil.compositeToCollection(
				compositeResponse, SuperviseeListResponseEvent.class);
        StringBuffer padCrt = null;
        StringBuffer padSpn = null;
		
		for (Iterator iter = supervisees.iterator(); iter.hasNext();) {
			SuperviseeListResponseEvent responseEvent = (SuperviseeListResponseEvent) iter.next();				
			padCrt = new StringBuffer(responseEvent.getCourtNum());
		    if (padCrt.length() < 3){
		    	while (padCrt.length() < 3){
		    		padCrt.insert(0, "0");
		    	}
		    	responseEvent.setCourtNum(padCrt.toString());
		    }
		    padSpn = new StringBuffer(responseEvent.getSpn());
		    if (padSpn.length() < 8){
		    	while (padSpn.length() < 8){
		    		padSpn.insert(0, "0");
		    	}
		    	responseEvent.setSpn(padSpn.toString());
		    }
		}
		return UICasenoteHelper.getBasicSupervisessFromSupListRespEvts(supervisees);
	}
	
	
	public boolean isTooManyResults(HttpServletRequest aRequest){
	    
	    boolean tooManyResults = false;
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    
	    CompositeResponse compositeResponse = (CompositeResponse) dispatch
		.getReply();
	    
	    if (MessageUtil.filterComposite(compositeResponse, CountInfoMessage.class) != null){
		    sendToErrorPage(aRequest, "error.max.limit.exceeded");
		     tooManyResults = true;
		    }

	    return tooManyResults;
	    
	}
	
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		String myForward = UIConstants.SUCCESS;
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		myForm.setAction(myForm.getSelectedValue());
		CasenoteJournalForm myJournalForm = UICommonSupervisionHelper
		.getCasenoteJournalForm(aRequest, true);
		myJournalForm.clearAll();
		return tempCommonSuperviseeSearch(aMapping,myForm,aRequest);
//		if (myForm.getAction().equals(UIConstants.BASIC)) {
//			return processBasicSearch(aMapping, myForm, aRequest, aResponse);
//		} else {
//			return processAdvancedSearch(aMapping, myForm, aRequest, aResponse);
//		}
	}
	
	public ActionForward advancedSearch(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		myForm.clearAll();
		//			 set defaults up
		myForm.setAction(UIConstants.ADVANCED);
		return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
	}

	

	public ActionForward basicSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		myForm.clearAll();
		//			 set defaults up
		myForm.setSearchBy("SPN");
		myForm.setAction(UIConstants.BASIC);
		return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
	}

	
	public ActionForward link(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		//			myForm.clearAll();
		//			 set defaults up

		return basicSearch(aMapping, aForm, aRequest, aResponse);
	}
	
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		String tempAction = myForm.getAction();
		myForm.clearAll();
		// set defaults up
		myForm.setAction(tempAction);
		if (tempAction.equalsIgnoreCase(UIConstants.ADVANCED))
			return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);

		return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
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

	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

}// END CLASS
