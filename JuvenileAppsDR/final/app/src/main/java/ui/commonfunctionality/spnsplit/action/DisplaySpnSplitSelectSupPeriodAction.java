//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\DisplaySpnSplitSelectSupPeriodAction.java

package ui.commonfunctionality.spnsplit.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.party.GetPartyEvent;
import messaging.spnconsolidation.reply.SpnConsolidationErrorResponseEvent;
import messaging.spnsplit.GetCaseSupervisionPeriodsEvent;
import messaging.spnsplit.ProcessSpnSplitEvent;
import messaging.spnsplit.reply.SpnSplitErrorResponseEvent;
import messaging.spnsplit.reply.SpnSplitOrderDetailsResponseEvent;
import messaging.spnsplit.reply.SpnSplitOrderPeriodResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.PartyControllerServiceNames;
import naming.SPNSplitControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.commonfunctionality.spnsplit.form.SpnSplitForm;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.CaseInfo;
import ui.supervision.SupervisionPeriodInfo;


public class DisplaySpnSplitSelectSupPeriodAction  extends JIMSBaseAction
{
   
   /**
    * @roseuid 4561E28501D8
    */
   public DisplaySpnSplitSelectSupPeriodAction() 
   {
    
   }
   
	protected void addButtonMapping(Map keyMap) {
	    keyMap.put("button.superviseeSplit", "superviseeSplit");
	    keyMap.put("button.caseSplit", "caseSplit");
	    keyMap.put("button.cancel", "cancel");
	    keyMap.put("button.reset", "reset");
	    keyMap.put("button.continue", "resume");
	}    
   
   private boolean partyFinder(SpnSplitForm myForm, HttpServletRequest aRequest){
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	GetPartyEvent requestEvent = (GetPartyEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTY);
//	Collection spnColl = new ArrayList();
	String validSpn=myForm.getValidSpn().getSpn();
	String errSpn=myForm.getErroneousSpn().getSpn();
	if(UIUtil.stripZeroes(validSpn).equals(UIUtil.stripZeroes(errSpn))){
		sendToErrorPage(aRequest,"error.duplicateSpnEntered");
		return false;
	}
	myForm.clearErrSpn();
	myForm.clearValidSpn();
	myForm.getValidSpn().setSpn(validSpn);
	ArrayList mySpnList=new ArrayList();
	mySpnList.add(errSpn);
	mySpnList.add(validSpn);
	myForm.getErroneousSpn().setSpn(errSpn);
	requestEvent.setSpns(mySpnList);
	dispatch.postEvent(requestEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	// error handling
 	Collection errors = MessageUtil.compositeToCollection(response, SpnConsolidationErrorResponseEvent.class);
	if(errors != null && errors.size() > 0){
		StringBuffer sb = new StringBuffer();
		boolean isFirst = true;
		for(Iterator iter = errors.iterator(); iter.hasNext(); ){
			SpnConsolidationErrorResponseEvent spnErrorEvent = (SpnConsolidationErrorResponseEvent)iter.next();
			sb.append(spnErrorEvent.getErroneousSpn());
			if(isFirst){
				if(errors.size()>1)
				sb.append(", ");
				isFirst = false;
			}
		}
		
		sendToErrorPage(aRequest,"error.noparty.found", " for " + sb.toString());
		return false;
	}
	
	// process party list
	Collection parties = MessageUtil.compositeToCollection(response, PartyListResponseEvent.class);
	Iterator partyIter = parties.iterator();
	boolean erroneousSpnIsValid=false;
	boolean validSpnIsValid=false;
	while (partyIter.hasNext()) {
		PartyListResponseEvent partyList = (PartyListResponseEvent) partyIter.next();
		String spn = partyList.getSpn();
		if (myForm.getErroneousSpn().getSpn().indexOf(spn)!=-1) {
			erroneousSpnIsValid=true;
			SpnSplitForm.SpnInfo myErrSpnInfo=getSpnInfoFromRespEvt(partyList);
			if(myErrSpnInfo==null){
				erroneousSpnIsValid= false;
			}
			else{
				myErrSpnInfo.setSpn(myForm.getErroneousSpn().getSpn());
				myForm.setErroneousSpn(myErrSpnInfo);
			}
		}
		else if (myForm.getValidSpn().getSpn().indexOf(spn)!=-1) {
			validSpnIsValid=true;
			SpnSplitForm.SpnInfo myValidSpnInfo=getSpnInfoFromRespEvt(partyList);
			if(myValidSpnInfo==null){
				validSpnIsValid= false;
			}
			else{
				myValidSpnInfo.setSpn(myForm.getValidSpn().getSpn());
				myForm.setValidSpn(myValidSpnInfo);
			}
		}
	}
	if(!erroneousSpnIsValid || !validSpnIsValid){
		if(!erroneousSpnIsValid)
			sendToErrorPage(aRequest,"error.noparty.found", " for " + myForm.getErroneousSpn().getSpn() );
		else
			sendToErrorPage(aRequest,"error.noparty.found", " for " + myForm.getValidSpn().getSpn() );
		return false;
	}
	return true;
   }
   
   
   private SpnSplitForm.SpnInfo getSpnInfoFromRespEvt(PartyListResponseEvent partyList){
   	if(partyList==null)
   		return null;
   	SpnSplitForm.SpnInfo mySpnInfo=new SpnSplitForm.SpnInfo();
   	mySpnInfo.setSpn(partyList.getSpn());
   	mySpnInfo.setDob(partyList.getDateOfBirth());
   	mySpnInfo.setJailIndId(partyList.getJailInd());
   	mySpnInfo.getName().setFirstName(partyList.getFirstName());
   	mySpnInfo.getName().setLastName(partyList.getLastName());
   	mySpnInfo.setRaceId(partyList.getRaceId());
   	mySpnInfo.setSexId(partyList.getSexId());
	return mySpnInfo;
	
   }
   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
   public ActionForward caseSplit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse) {
    SpnSplitForm myForm = (SpnSplitForm) aForm;
    String forward = UIConstants.SUCCESS_CASE;
    myForm.setSelectedValue("");
      // Could not find the party info
     if(!partyFinder(myForm,aRequest)){
     	return aMapping.findForward(UIConstants.FAILURE);
     }
    
      IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
      GetCaseSupervisionPeriodsEvent requestEvent = (GetCaseSupervisionPeriodsEvent) EventFactory.getInstance(SPNSplitControllerServiceNames.GETCASESUPERVISIONPERIODS);
      requestEvent.setErroneousSpn(myForm.getErroneousSpn().getSpn());
      requestEvent.setValidSpn(myForm.getValidSpn().getSpn());
      dispatch.postEvent(requestEvent);
      CompositeResponse response = (CompositeResponse) dispatch.getReply();
      MessageUtil.processReturnException(response);
      // error handling
      Collection periodRespEvents = MessageUtil.compositeToCollection(response, SpnSplitOrderPeriodResponseEvent.class);
      if(periodRespEvents==null || periodRespEvents.size()<=0){
      	sendToErrorPage(aRequest,"errors.noCasesExist");
        return aMapping.findForward(UIConstants.FAILURE);
      }
      else{
      	Collection mySupPeriodInfoList = convert(periodRespEvents);
      	String myInvalidCases=checkForInvalidStatus(mySupPeriodInfoList);
      	if(myInvalidCases!=null && !myInvalidCases.trim().equals("")){
      		// invalid cases exist error and report.
			sendToErrorPage(aRequest,"errors.invalidCasesExist",myInvalidCases);
            return aMapping.findForward(UIConstants.FAILURE);
      	}
      	myForm.setSupPeriods(mySupPeriodInfoList);
      	myForm.setCurrentSupPeriod(null);
      	if(mySupPeriodInfoList==null || mySupPeriodInfoList.size()<=0){
      		sendToErrorPage(aRequest,"errors.noCasesExist");
            return aMapping.findForward(UIConstants.FAILURE);
      	}
      	else if (mySupPeriodInfoList.size()==1){
      		// Go straight to orders selection page
      		SupervisionPeriodInfo mySupPeriodIndo=(SupervisionPeriodInfo)mySupPeriodInfoList.iterator().next();
      		mySupPeriodIndo.setSelected(true);
      		myForm.setSelectedValue(mySupPeriodIndo.getSupPeriodId());
      		return aMapping.findForward("successOrders");
      	}
      }
      myForm.clearOrdersSelection(false);
     
      return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
   public ActionForward superviseeSplit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	        HttpServletResponse aResponse)  throws GeneralFeedbackMessageException {
	    SpnSplitForm myForm = (SpnSplitForm) aForm;
	    String forward = UIConstants.SUCCESS_SUPERVISEE;
	    myForm.setSelectedValue("");
	    myForm.setSelectedTopic("");
	    myForm.setShowBackButton(UIConstants.YES);
	      // Could not find the party info
	    if(!partyFinder(myForm,aRequest)){
	     	forward = UIConstants.FAILURE;
	    } 
		return aMapping.findForward(forward); 
   }

   private String checkForInvalidStatus(Collection aSupPeriodInfoObjs){
	   	if(aSupPeriodInfoObjs==null || aSupPeriodInfoObjs.size()<=0){
	   		return "";
	   	}
	   	boolean isFirst=true;
	   	int counter=0;
	   	int splitter=0;
	   	StringBuffer myInvalidList=new StringBuffer();
	   	Iterator iter=aSupPeriodInfoObjs.iterator();
	   	while(iter.hasNext()){
	   		SupervisionPeriodInfo myInfo=(SupervisionPeriodInfo)iter.next();
	   		if(myInfo.getCases()!=null && myInfo.getCases().size()>0){
	   			Iterator iterCases=myInfo.getCases().iterator();
	   			while (iterCases.hasNext()){
	   				CaseInfo myCaseInfo=(CaseInfo)iterCases.next();
	   				if(myCaseInfo.getOrderStatusId().equalsIgnoreCase(PDCodeTableConstants.STATUS_DRAFT_ID) || 
	   						myCaseInfo.getOrderStatusId().equalsIgnoreCase(PDCodeTableConstants.STATUS_PENDING_ID) || 
							myCaseInfo.getOrderStatusId().equalsIgnoreCase(PDCodeTableConstants.STATUS_INCOMPLETE_ID) ||
							myCaseInfo.getOrderStatusId().equalsIgnoreCase(PDCodeTableConstants.STATUS_WITHDRAWN_ID)){
	   					
	   					if(!isFirst)
	   						myInvalidList.append(", ");
	   					if(splitter>3){
	   						myInvalidList.append("<br>");
	   						splitter=0;
	   					}
	   					
	   					myInvalidList.append(myCaseInfo.getCaseNum());
	   					splitter++;
	   					counter++;
	   					if(isFirst)
	   						isFirst=false;
	   				}
	   			}
	   		}
	   	}
	   	return myInvalidList.toString();
   }
   
   private Collection convert(Collection respEvents){
	   	if(respEvents==null || respEvents.size()<=0)
	   		return null;
	   	ArrayList supPeriodInfoList=new ArrayList();
	   	Iterator iter=respEvents.iterator();
	   	while(iter.hasNext()){
	   		SpnSplitOrderPeriodResponseEvent mySpnSplitOrderPeriodRespEvt=(SpnSplitOrderPeriodResponseEvent)iter.next();
	   		SupervisionPeriodInfo mySupPerInfo=new SupervisionPeriodInfo();
	   		mySupPerInfo.setSupPeriodId(mySpnSplitOrderPeriodRespEvt.getSupPeriodId());
	   		mySupPerInfo.setSupPeriodBeginDate(mySpnSplitOrderPeriodRespEvt.getSupPeriodBeginDate());
	   		if(mySpnSplitOrderPeriodRespEvt.getSupPeriodEndDate()!=null ){
	   			mySupPerInfo.setSupPeriodEndDate(mySpnSplitOrderPeriodRespEvt.getSupPeriodEndDate());
	   		}
	   		else{
	   			mySupPerInfo.setSupPeriodEndDate(null);
	   		}
	   		if(mySpnSplitOrderPeriodRespEvt.getSpnSplitOrderDetails()!=null && mySpnSplitOrderPeriodRespEvt.getSpnSplitOrderDetails().size()>0){
	   			Iterator iterCases=mySpnSplitOrderPeriodRespEvt.getSpnSplitOrderDetails().iterator();
	   			while (iterCases.hasNext()){
	   				SpnSplitOrderDetailsResponseEvent mySpnSplitOrderDetailsRespEvt= (SpnSplitOrderDetailsResponseEvent)iterCases.next();
	   				CaseInfo myCaseInfo=new CaseInfo();
					myCaseInfo.setAgencyId(mySpnSplitOrderDetailsRespEvt.getAgencyId());
					myCaseInfo.setCaseNum(mySpnSplitOrderDetailsRespEvt.getCaseNumber());
					myCaseInfo.setCaseSupPeriodBeginDate(null);
					myCaseInfo.setCaseSupPeriodEndDate(null);
					myCaseInfo.setCdi(mySpnSplitOrderDetailsRespEvt.getCdi());
					myCaseInfo.setCourt(mySpnSplitOrderDetailsRespEvt.getCourtId());
					myCaseInfo.setOrderFileDate(mySpnSplitOrderDetailsRespEvt.getOrderFiledDate());
					myCaseInfo.setOrderStatusId(mySpnSplitOrderDetailsRespEvt.getOrderStatus());
					myCaseInfo.setOrderId(mySpnSplitOrderDetailsRespEvt.getSupOrderId());
					myCaseInfo.setCaseSupPeriodBeginDate(mySpnSplitOrderDetailsRespEvt.getSupOrderBeginDate());
					myCaseInfo.setCaseSupPeriodEndDate(mySpnSplitOrderDetailsRespEvt.getSupOrderEndDate());
					myCaseInfo.setOrderVersionNum(mySpnSplitOrderDetailsRespEvt.getVersionNumber());
					myCaseInfo.setOrderVersionId(mySpnSplitOrderDetailsRespEvt.getVersionType());
					mySupPerInfo.addCase(myCaseInfo);
	   			}
	   			Collections.sort((List)mySupPerInfo.getCases());
	   			supPeriodInfoList.add(mySupPerInfo);
	   		}
	   	}
	   	Collections.sort((List)supPeriodInfoList);
	   	return supPeriodInfoList;
   }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
   public ActionForward reset(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	        HttpServletResponse aResponse) {
		   	SpnSplitForm myForm = (SpnSplitForm) aForm;
		   	myForm.getErroneousSpn().setSpn("");
		   	myForm.getValidSpn().setSpn("");
		    return aMapping.findForward(UIConstants.FAILURE);
		}   
 
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	        HttpServletResponse aResponse) {
		SpnSplitForm myForm = (SpnSplitForm) aForm;
	    myForm.clear();
	    return aMapping.findForward(UIConstants.CANCEL_MAIN_PAGE_HOME);
	}
	
	  /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward resume(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
	  		SpnSplitForm myForm = (SpnSplitForm) aForm;
	   		myForm.setAction(UIConstants.CONFIRM);
	   		// Create Event and populate it
	   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	   		ProcessSpnSplitEvent requestEvent = (ProcessSpnSplitEvent) EventFactory.getInstance(SPNSplitControllerServiceNames.PROCESSSPNSPLIT);
	        requestEvent.setErroneousSpn(myForm.getErroneousSpn().getSpn());
	        requestEvent.setValidSpn(myForm.getValidSpn().getSpn());
	        requestEvent.setAgencyId(UIUtil.getCurrentUserAgencyID());
	        requestEvent.setSpnSplitExceptionId(myForm.getSpnSplitExceptionId());
	        dispatch.postEvent(requestEvent);
	        CompositeResponse response = (CompositeResponse) dispatch.getReply();
	        MessageUtil.processReturnException(response);
	        // error handling
	        Collection periodRespEvents = MessageUtil.compositeToCollection(response, SpnSplitErrorResponseEvent.class);
	        if(periodRespEvents!=null && periodRespEvents.size()>0){
	        	Iterator iterErr=periodRespEvents.iterator();
	        	SpnSplitErrorResponseEvent myRespEvt=(SpnSplitErrorResponseEvent)iterErr.next();
	        	sendToErrorPage(aRequest,"error.generic",myRespEvt.getErroneousSpn() + ": " + myRespEvt.getMsg());
	        	return aMapping.findForward(UIConstants.FAILURE);
	        }
	        myForm.setConfirmationMessage("SPN Split successful and casenote recorded.");
	   	    return aMapping.findForward("resumeSuccess");
	   }
}