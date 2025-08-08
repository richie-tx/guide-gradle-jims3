package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

public class PerformUpdateReferralDateAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateReferralDateAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String logonId = SecurityUIHelper.getLogonId();
		String referralNum = regform.getReferralNum();
		String newCasefileId = regform.getNewcasefileId();
		if(newCasefileId != null ){
			newCasefileId = newCasefileId.trim();
		}
		
		
		String referralBegindate 			= regform.getRefBeginDate();
		String referralEnddate 				= regform.getRefEndDate();
		String referralAckdate 				= regform.getRefAckDate();
		String referralSentdate				= regform.getRefSentDate();
		String controllingReferral			= regform.getNewControllingReferral();
		String programReferralAssignmentDate		= regform.getProgramReferralAssignmentDate();
		String originalProgramReferralAssignmentDate	= regform.getOriginalProgramReferralAssignmentDate();
		String programReferralAssignmentId		= regform.getProgramReferralAssignmentId();
		//add fund source  for US 180996
		String fundSource 				= regform.getNewFundSource();
			
		// prepare event
		UpdateProductionSupportJuvenileProgramReferralEvent updateProgramReferral = (UpdateProductionSupportJuvenileProgramReferralEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRAL);
		
		// check that referral number provided
		if (referralNum == null || referralNum.equals("")) {

			regform.setMsg("PerformUpdateReferralDateAction.java - Referral Number was null.");
			return (mapping.findForward("error"));
		}
		// set the referral number
		updateProgramReferral.setReferralNum(referralNum);
		// original referral record
		ProductionSupportJuvenileProgramReferralResponseEvent originalReferralResponseEvent = getReferralsById(referralNum);
		// Begin Date 
		boolean isRefBeginDateChanged = checkIfTwoValuesChanged(referralBegindate,DateUtil.dateToString(originalReferralResponseEvent.getBeginDate(),DateUtil.DATE_FMT_1));
		if ( isRefBeginDateChanged ) {
    			log.info("Will Update Begin Date for JUVPROGREF_ID= " + referralNum + "Old Begin Date:"+ originalReferralResponseEvent.getBeginDate() + "New Begin Date:"+ referralBegindate  + "LogonId" + logonId);
    			updateProgramReferral.setBeginDate(DateUtil.stringToDate(referralBegindate , DateUtil.DATE_FMT_1));
			regform.setIsRefBeginDateChanged("true");
			updateProgramReferral.setRefBeginDateChanged(true);
			
		} else {
		    	updateProgramReferral.setRefBeginDateChanged(false);
			regform.setIsRefBeginDateChanged("false");
		}

		// End Date 
		boolean isRefEndDateChanged = checkIfTwoValuesChanged(referralEnddate,DateUtil.dateToString(originalReferralResponseEvent.getEndDate(),DateUtil.DATE_FMT_1));
		if ( isRefEndDateChanged ) {
		    
			log.info("Will Update End Date for JUVPROGREF_ID= " + referralNum  + "Old End Date:" + originalReferralResponseEvent.getEndDate() + "New End Date:"+ referralEnddate + "LogonId" + logonId);
			updateProgramReferral.setEndDate(DateUtil.stringToDate(referralEnddate, DateUtil.DATE_FMT_1));			
			updateProgramReferral.setRefEndDateChanged(true);
			regform.setIsRefEndDateChanged("true");
	       } else {
		   	updateProgramReferral.setRefEndDateChanged(false);
		   	regform.setIsRefEndDateChanged("false");
	       } 
		
		//Ack Date
		boolean isRefAckDateChanged =  checkIfTwoValuesChanged(referralAckdate, DateUtil.dateToString(originalReferralResponseEvent.getAckDate(), DateUtil.DATE_FMT_1) );
		if ( isRefAckDateChanged ){
		    	updateProgramReferral.setAckDate(DateUtil.stringToDate(referralAckdate, DateUtil.DATE_FMT_1));
		    	updateProgramReferral.setRefAckDateChanged(true);
		    	regform.setIsRefAckDateChanged("true");
		    	
		} else {
		    	updateProgramReferral.setRefAckDateChanged(false);
		    	regform.setIsRefAckDateChanged("false");
		}
		
		//Sent Date 
		boolean isRefSentDateChanged =  checkIfTwoValuesChanged(referralSentdate, DateUtil.dateToString(originalReferralResponseEvent.getSentDate(), DateUtil.DATE_FMT_1 ));
		if ( isRefSentDateChanged ) {
		    	updateProgramReferral.setSentDate(DateUtil.stringToDate(referralSentdate, DateUtil.DATE_FMT_1));
		    	updateProgramReferral.setRefSentDateChanged(true);
		    	regform.setIsRefSentDateChanged("true");
		} else {
		    	updateProgramReferral.setRefSentDateChanged(false);
		    	regform.setIsRefSentDateChanged("false");
		}
		
		//Controlling Referral
		boolean isControllingReferralChanged = checkIfTwoValuesChanged(controllingReferral, originalReferralResponseEvent.getControllingReferralNum() );
		if ( isControllingReferralChanged ) { 
		    	if ( "".equals( controllingReferral) ) {
		    	    updateProgramReferral.setControllingReferral(null);
		    	} else {
		    	    updateProgramReferral.setControllingReferral(controllingReferral);
		    	}
		    	
		    	updateProgramReferral.setControllingReferralChanged(true);
		    	regform.setIsControllingReferralChanged("true");
		    
		} else {
		    	updateProgramReferral.setControllingReferralChanged(false);
		    	regform.setIsControllingReferralChanged("false");
		}
		
		// programReferralAssignmentDate
		boolean isProgramReferralAssignmentDateChanged = checkIfTwoValuesChanged(originalProgramReferralAssignmentDate, programReferralAssignmentDate );
		if ( isProgramReferralAssignmentDateChanged ){
		    updateProgramReferral.setProgramReferralAssignmentDate(DateUtil.stringToDate(programReferralAssignmentDate, DateUtil.DATE_FMT_1));
		    updateProgramReferral.setProgramReferralAssignmentDateChanged(true);
		    updateProgramReferral.setProgramReferralAssignmentId(programReferralAssignmentId);
		    regform.setIsProgramReferralAssignmentDateChanged("true");
		} else {
		    regform.setIsProgramReferralAssignmentDateChanged("false");
		    updateProgramReferral.setProgramReferralAssignmentDateChanged(false);
		}
		
		//fundSource
		// programReferralAssignmentDate
		boolean isFundSourceChanged = checkIfTwoValuesChanged(fundSource, originalReferralResponseEvent.getFundSource() );
		if ( isFundSourceChanged ){
		    	if ( "".equals( fundSource) ) {
		    	    updateProgramReferral.setFundSource(null);
		    	} else {
		    	    updateProgramReferral.setFundSource(fundSource);
		    	}
		    	
		    	updateProgramReferral.setFundSourceChanged(true);
		    	regform.setIsfundSourceChanged("true");
		    
		} else {
		    	updateProgramReferral.setFundSourceChanged(false);
		    	regform.setIsfundSourceChanged("false");
		}
		
		String originalCasefileId = originalReferralResponseEvent.getCaseFileId();
		// CaseFileId - Check for valid casefileId and set
		if (newCasefileId != null 
			&& newCasefileId.equals("")==false
			&& ! newCasefileId.equals( originalCasefileId ) ) {
			// check that the new casefile number is not the same as the existing casefile number - if so throw back error
			//if (originalCasefileId != null && !originalCasefileId.equals("") && originalCasefileId.trim().equals(newCasefileId.trim())) {
			//	regform.setMsg("Please enter a new casefileId. This is the same casefileId that already exists.");
			//	return (mapping.findForward("error"));
			//}
			
			//Check to see if new value is a valid casefile
			if (isValidCasefile(newCasefileId))
			{
				regform.setCasefileId(newCasefileId);
				log.info("Will Update CasefileId for JUVPROGREF_ID= " + referralNum + "Old CasefileId:" + originalReferralResponseEvent.getCaseFileId() + "New CasefileId:" + newCasefileId + "LogonId" + logonId);
				updateProgramReferral.setCasefileId(newCasefileId);
				regform.setIsCaseFileIdChanged("true");
				
				//Create a record in CSPROGRFASGNHIST table - US 101123
				UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent updateProgramReferralAssignmentHistoryEvent = 
					(UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent) 
				EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALASSIGNMENTHISTORY) ;
				updateProgramReferralAssignmentHistoryEvent.setCreateNewRecord( true );
				updateProgramReferralAssignmentHistoryEvent.setCasefileId( newCasefileId );
				updateProgramReferralAssignmentHistoryEvent.setJuvProgRefId(regform.getReferralNum());
				
				
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(updateProgramReferralAssignmentHistoryEvent);
			}
			else {
				regform.setMsg("Error - New casefile ID did not match any existing records.");
				return mapping.findForward("error");
			}
		}
		else{
		    regform.setIsCaseFileIdChanged("false");
		    updateProgramReferral.setCasefileId(originalCasefileId);
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		log.info("UPDATE JUVENILE BEGIN, END, AND/OR CASEFULE FOR REFERRAL: " + referralNum + " LOGON ID: " +  SecurityUIHelper.getLogonId());
		dispatch.postEvent(updateProgramReferral);

		return mapping.findForward("success");

	}
	
	/**
	 * Check if the casefileId is valid
	 * @param casefileId
	 * @return
	 */
	private boolean isValidCasefile(String casefileId){
		
		/**
		 * Search for Casefiles
		 */
		GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
		EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
		getCasefileEvent.setSupervisionNumber( casefileId ) ;
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( getCasefileEvent ) ;

		CompositeResponse aResponse = (CompositeResponse)dispatch.getReply( ) ;
		
		JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent)
			MessageUtil.filterComposite( aResponse, JuvenileCasefileResponseEvent.class ) ;
		
		if (casefile==null){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * get the existing referral record
	 * @param referralNum
	 * @return
	 */
	public static ProductionSupportJuvenileProgramReferralResponseEvent getReferralsById(String referralNum){
		ArrayList<ProductionSupportJuvenileProgramReferralResponseEvent> juvprogrefs = null;
		ProductionSupportJuvenileProgramReferralResponseEvent responseEvent = null;
		// Get and set Associated JuvProgRefs
		GetProductionSupportJuvenileProgramReferralsEvent getJuvenileProgramRerralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALS );
		getJuvenileProgramRerralsEvent.setReferralNum(referralNum );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralsEvent);
		juvprogrefs = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProductionSupportJuvenileProgramReferralResponseEvent.class);
		
		for(ProductionSupportJuvenileProgramReferralResponseEvent event: juvprogrefs){
			responseEvent = (ProductionSupportJuvenileProgramReferralResponseEvent)event;
			break;
		}
		
		return responseEvent;
	}
	/**(
	 * compare two string values and determine if they are equal
	 * @param value
	 * @param compareValue
	 * @return
	 */
	private boolean checkIfTwoValuesChanged(String newValue, String OlderValue){
		boolean result = false;
		
		if(newValue != null 
			&& OlderValue != null){
			if(!newValue.equals(OlderValue)){
				result = true;
			}
		}else if (newValue != null
			        && !("").equals(newValue)
				&& OlderValue == null){
				result = true;
		}
		
		return result;
	}

}
