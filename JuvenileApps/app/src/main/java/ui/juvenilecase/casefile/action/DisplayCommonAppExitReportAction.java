/*
 * Created on Dec 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetDSMDiagnosisByClosingInfoIdEvent;
import messaging.casefile.GetResponseEvent;
import messaging.casefile.reply.DSMDiagnosisResponseEvent;
import messaging.casefile.reply.QuestionAnswerResponseEvent;
import messaging.family.GetBenefitsAssessmentsEvent;
import messaging.family.GetGuardianFinancialInfoEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentResponseEvent;
import messaging.juvenilecase.reply.GuardianFinancialInfoResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayCommonAppExitReportAction extends LookupDispatchAction {

	/**
	 * On click of Cancel
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	/**
	 * On click of back
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CommonAppForm myCommonAppForm=(CommonAppForm)aForm;
		String reportType = myCommonAppForm.getPreviousTab();
		Collection previousTabQuestions = (Collection) myCommonAppForm.getCummulativeExitReportQuestions().get(reportType);
		myCommonAppForm.setExitReportQuestions(previousTabQuestions);
		myCommonAppForm.setPreviousExitReportQuestions(previousTabQuestions);
		myCommonAppForm.setReportType(reportType);
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/**
	 * displayExitReport
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward displayExitReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CommonAppForm myCommonAppForm=(CommonAppForm)aForm;
		//ER changes 11046 starts
		//Save the previous Data.
		if(myCommonAppForm.getReportType()!=null && myCommonAppForm.getPreviousTab()!=null){
			if(!myCommonAppForm.getReportType().equals("CAER")&& !myCommonAppForm.getPreviousTab().equals(myCommonAppForm.getReportType())){
				UIJuvenileCasefileClosingHelper.saveCommonAppDetails(aForm, aRequest);
			}
		}
		
		String reportType = "";
		myCommonAppForm.setReportType("");
		//ER changes 11046 ends
		//#bug fix 35750 starts
		JuvenileCasefileForm casefileForm=UIJuvenileHelper.getJuvenileCasefileForm(aRequest,true);
		CasefileClosingForm myClosingForm=(CasefileClosingForm)UIJuvenileHelper.getJuvenileCasefileClosingForm(aRequest,casefileForm.getSupervisionNum());
		//#bug fix 35750 ends
		myCommonAppForm.setAction(UIConstants.UPDATE);
		myCommonAppForm.setSecondaryAction("");
		myCommonAppForm.setSelectedValue("");
		myCommonAppForm.setClosingInfoId(myClosingForm.getClosingInfoId());		
		myCommonAppForm.setDsmDiagnosisFilled(false);
		myCommonAppForm.setExitReportFilled(false);
		myCommonAppForm.setDsmDiagnosisExists(false);
		myCommonAppForm.setDsmResults(new ArrayList());
		myCommonAppForm.setCaseStatus(casefileForm.getCaseStatusId());
		
		//ER changes 11046 starts
		reportType= (String) aRequest.getParameter(UIConstants.REPORT_TYPE);
		if(myClosingForm.getReportFileLocOnServer()!=null && myClosingForm.getReportFileLocOnServer().contains(UIConstants.OLD_REPORT_YR)){
			reportType="CAER";
		}else{
			myCommonAppForm.setReportType(UIConstants.CASP);
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
		}
		
		if(myClosingForm.getReportFileLocOnServer()!=null && !myClosingForm.getReportFileLocOnServer().contains(UIConstants.OLD_REPORT_YR)){ 
			myCommonAppForm.setReportType(UIConstants.CASP);
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
		}
		
		if(reportType!=null &&!reportType.equals(UIConstants.CAER)){
			myCommonAppForm.setReportType(reportType);
		}
		//ER changes 11046 ends
		// Populate Common App Form for prinitng CommonAppReport 
		this.getJuvenileExitReportQuestions(myCommonAppForm, myClosingForm);
		if(myCommonAppForm.getReportType().equals(UIConstants.CAFH)|| myCommonAppForm.getReportType().equals(UIConstants.CAER) || myCommonAppForm.getReportType().equals(UIConstants.CASP)){ //added for bug fix 23314
			this.getExitReportDSMDiagnosis(myCommonAppForm);
		}
		//this.getMedicaidInfo(myCommonAppForm, casefileForm);
		//this.getGuardianFinancialInfo(myCommonAppForm, casefileForm);
		
		//ER changes 11046 starts
		if(myCommonAppForm.getReportType().equals(UIConstants.CASP)){
			myCommonAppForm.setPreviousTab(UIConstants.CASP);
			myCommonAppForm.setPreviousReport(myClosingForm.getReportFileLocOnServer());
			myCommonAppForm.setPreviousExitReportQuestions(myCommonAppForm.getExitReportQuestions());
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
			myCommonAppForm.setErrorPage(UIConstants.CA_SCREENINGPROFILE_ERQ_TAB);
			return aMapping.findForward(UIConstants.CA_SCREENINGPROFILE_ERQ_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CAFD)){
			myCommonAppForm.setPreviousTab(UIConstants.CAFD);
			myCommonAppForm.setPreviousReport(myClosingForm.getReportFileLocOnServer());
			myCommonAppForm.setPreviousExitReportQuestions(myCommonAppForm.getExitReportQuestions());
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
			myCommonAppForm.setErrorPage(UIConstants.CA_FACILITYDETAILS_ERQ_TAB);
			return aMapping.findForward(UIConstants.CA_FACILITYDETAILS_ERQ_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CASA)){
			myCommonAppForm.setPreviousTab(UIConstants.CASA);
			myCommonAppForm.setPreviousReport(myClosingForm.getReportFileLocOnServer());
			myCommonAppForm.setPreviousExitReportQuestions(myCommonAppForm.getExitReportQuestions());
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
			myCommonAppForm.setErrorPage(UIConstants.CA_SOCIALASSESSMENT_ERQ_TAB);
			return aMapping.findForward(UIConstants.CA_SOCIALASSESSMENT_ERQ_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CADH)){
			myCommonAppForm.setPreviousTab(UIConstants.CADH);
			myCommonAppForm.setPreviousReport(myClosingForm.getReportFileLocOnServer());
			myCommonAppForm.setPreviousExitReportQuestions(myCommonAppForm.getExitReportQuestions());
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
			myCommonAppForm.setErrorPage(UIConstants.CA_DELINQUENCYHISTORY_ERQ_TAB);
			return aMapping.findForward(UIConstants.CA_DELINQUENCYHISTORY_ERQ_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CASN)){
			myCommonAppForm.setPreviousTab(UIConstants.CASN);
			myCommonAppForm.setPreviousReport(myClosingForm.getReportFileLocOnServer());
			myCommonAppForm.setPreviousExitReportQuestions(myCommonAppForm.getExitReportQuestions());
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
			myCommonAppForm.setErrorPage(UIConstants.CA_SPECIALNEEDS_ERQ_TAB);
			return aMapping.findForward(UIConstants.CA_SPECIALNEEDS_ERQ_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CAAH)){
			myCommonAppForm.setPreviousTab(UIConstants.CAAH);
			myCommonAppForm.setPreviousReport(myClosingForm.getReportFileLocOnServer());
			myCommonAppForm.setPreviousExitReportQuestions(myCommonAppForm.getExitReportQuestions());
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
			myCommonAppForm.setErrorPage(UIConstants.CA_SUBSTANCEABUSEHISTORY_ERQ_TAB);
			return aMapping.findForward(UIConstants.CA_SUBSTANCEABUSEHISTORY_ERQ_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CAFH)){
			myCommonAppForm.setPreviousTab(UIConstants.CAFH);
			myCommonAppForm.setPreviousReport(myClosingForm.getReportFileLocOnServer());
			myCommonAppForm.setPreviousExitReportQuestions(myCommonAppForm.getExitReportQuestions());
			myClosingForm.setReportFileLocOnServer(UIConstants.EMPTY_STRING);
			myCommonAppForm.setErrorPage(UIConstants.CA_FAMILYHISTORY_ERQ_TAB);
			return aMapping.findForward(UIConstants.CA_FAMILYHISTORY_ERQ_TAB);
		}
		//ER changes 11046 ends
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * On Click of next
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CommonAppForm myCommonAppForm=(CommonAppForm)aForm;
		
		// TODO RETRIEVE Common App disposition Information
		Collection errors=UIUtil.checkQuestionResponses(myCommonAppForm.getExitReportQuestions());
		if(errors!=null && errors.size()>0){
			sendToErrorPage(aRequest,errors);
			//ER changes 11046 starts
			if(myCommonAppForm.getReportType().equals(UIConstants.CAER)){
				return aMapping.findForward("reportFailure");
			}
			else{
				return aMapping.findForward(myCommonAppForm.getErrorPage());
			}
			//ER changes 11046 ends
		}
		myCommonAppForm.setAction(UIConstants.UPDATE_SUMMARY);
		myCommonAppForm.setSecondaryAction("");
		myCommonAppForm.setSelectedValue("");
		//ER changes 11046 starts
		if(myCommonAppForm.getReportType().equals(UIConstants.CASP)){
			myCommonAppForm.setPreviousTab(UIConstants.CASP);
			return aMapping.findForward(UIConstants.CA_SCREENINGPROFILE_ERQ_SUMMARY_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CAFD)){
			myCommonAppForm.setPreviousTab(UIConstants.CAFD);
			return aMapping.findForward(UIConstants.CA_FACILITYDETAILS_ERQ_SUMMARY_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CASA)){
			myCommonAppForm.setPreviousTab(UIConstants.CASA);
			return aMapping.findForward(UIConstants.CA_SOCIALASSESSMENT_ERQ_SUMMARY_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CADH)){
			myCommonAppForm.setPreviousTab(UIConstants.CADH);
			return aMapping.findForward(UIConstants.CA_DELINQUENCYHISTORY_ERQ_SUMMARY_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CASN)){
			myCommonAppForm.setPreviousTab(UIConstants.CASN);
			return aMapping.findForward(UIConstants.CA_SPECIALNEEDS_ERQ_SUMMARY_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CAAH)){
			myCommonAppForm.setPreviousTab(UIConstants.CAAH);
			return aMapping.findForward(UIConstants.CA_SUBSTANCEABUSEHISTORY_SUMMARY_ERQ_TAB);
		}
		if(myCommonAppForm.getReportType().equals(UIConstants.CAFH)){
			myCommonAppForm.setPreviousTab(UIConstants.CAFH);
			return aMapping.findForward(UIConstants.CA_FAMILYHISTORY_ERQ_SUMMARY_TAB);
		}
		//ER changes 11046 ends
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}
   
	/**
	* sendToErrorPage
	* @param aRequest
	*/
	private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors) {
		ActionErrors errors = new ActionErrors();
		if(aActionErrors!=null && aActionErrors.size()>0){
			Iterator i=aActionErrors.iterator();
			while(i.hasNext()){
				ActionMessage error=(ActionMessage)i.next();
				errors.add(ActionErrors.GLOBAL_MESSAGE,error);
			}
			saveErrors(aRequest, errors);
		}
	}
	
	/**
	 * JuvenileExitReportQuestions
	 * @param myCommonAppForm
	 * @param myClosingForm
	 */
	private void getJuvenileExitReportQuestions(CommonAppForm myCommonAppForm, CasefileClosingForm myClosingForm) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetResponseEvent rEvent =
				 (GetResponseEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETRESPONSE);
		rEvent.setReferenceId(myClosingForm.getClosingInfoId());
		rEvent.setResponseType(myCommonAppForm.getReportType());
		rEvent.setResponseTemplateLocation(myClosingForm.getReportFileLocOnServer());
		dispatch.postEvent(rEvent);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse composite = (CompositeResponse) replyEvent;
		MessageUtil.processReturnException(composite);
		QuestionAnswerResponseEvent questionAnsResponseEvent = (QuestionAnswerResponseEvent) MessageUtil.filterComposite(composite, QuestionAnswerResponseEvent.class);
		if(questionAnsResponseEvent!=null){
			myClosingForm.setReportFileLocOnServer(questionAnsResponseEvent.getName());
			Collection questionGroupResponseEvents = questionAnsResponseEvent.getQuestionGroupResponseEvents();
			Collection uiQuestionGroups=UIUtil.mapQuestion_GroupRespEvtsToUIQuestionGroup(questionGroupResponseEvents);
			myCommonAppForm.setExitReportQuestions(uiQuestionGroups);
			//changes for 11046.
			myCommonAppForm.getCummulativeExitReportQuestions().put(myCommonAppForm.getReportType(), uiQuestionGroups);
		}
	}
	
	/**
	 * ExitReportDSMDiagnosis
	 * @param myCommonAppForm
	 */
	private void getExitReportDSMDiagnosis(CommonAppForm myCommonAppForm) {
		GetDSMDiagnosisByClosingInfoIdEvent rEvent =
        	(GetDSMDiagnosisByClosingInfoIdEvent) EventFactory.getInstance(
        JuvenileCasefileControllerServiceNames.GETDSMDIAGNOSISBYCLOSINGINFOID);
        rEvent.setClosingInfoId(myCommonAppForm.getClosingInfoId());       
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(rEvent);
        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        Collection events = MessageUtil.compositeToCollection(compositeResponse, DSMDiagnosisResponseEvent.class);       
        HashMap diagnosisMap =null;
        Iterator iter = events.iterator();
        Collection diagRespColl=null;
        while(iter.hasNext())
        {
        	DSMDiagnosisResponseEvent diagResp = (DSMDiagnosisResponseEvent)iter.next();
        	//ER Changes 13222 . Excluding "No diagnosis"
        	if(diagResp.getDiagnosisCd()!=null && !diagResp.getDiagnosisCd().isEmpty() && !diagResp.getDiagnosisCd().equals("V71.090"))
        	{
	        	if(diagnosisMap == null)
	        	{
	        		diagnosisMap = new HashMap();
	        		diagRespColl = new ArrayList();
	        		diagRespColl.add(diagResp);
	        		diagnosisMap.put(diagResp.getTestSessId(),diagRespColl);
	        	}
	        	else if(!diagnosisMap.containsKey(diagResp.getTestSessId()))
	        	{
	        		diagRespColl = new ArrayList();
	        		diagRespColl.add(diagResp);
	        		diagnosisMap.put(diagResp.getTestSessId(),diagRespColl);
	        	}
	        	else
	        	{
	        		Collection coll = (Collection)diagnosisMap.get(diagResp.getTestSessId());
	        		diagnosisMap.remove(diagResp.getTestSessId());
	        		coll.add(diagResp);
	        		diagnosisMap.put(diagResp.getTestSessId(),coll);
	        	}
        	}
        }
        if(diagnosisMap != null)
        {
	        Collection check = diagnosisMap.values();
	        Iterator mapIter = check.iterator();
	        Collection dsmDiagnosisResults = new ArrayList();
	        while(mapIter.hasNext())
	        {
	        	Collection dsmColl = (Collection)mapIter.next();
	        	String testSessId = ((DSMDiagnosisResponseEvent)dsmColl.toArray()[0]).getTestSessId();
	        	CommonAppForm.Diagnosis diag = new CommonAppForm.Diagnosis();
	        	diag.setTestId(testSessId);
	        	diag.setDiagnosisResults(dsmColl);
	        	dsmDiagnosisResults.add(diag);        	
	        }
	        if(check.size()!=0){
	        	myCommonAppForm.setDsmDiagnosisExists(true);
	        }
	        myCommonAppForm.setDsmResults(dsmDiagnosisResults);
        }
	}
	
	/**
	 * MedicaidInfo
	 * @param myCommonAppForm
	 * @param casefileForm
	 */
	private void getMedicaidInfo(CommonAppForm myCommonAppForm, JuvenileCasefileForm casefileForm){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetBenefitsAssessmentsEvent reqGuard =
			(GetBenefitsAssessmentsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETBENEFITSASSESSMENTS);
		reqGuard.setJuvenileNum(casefileForm.getJuvenileNum());
		dispatch.postEvent(reqGuard);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse composite = (CompositeResponse) replyEvent;
		MessageUtil.processReturnException(composite);
		Collection col = MessageUtil.compositeToCollection(composite, BenefitsAssessmentResponseEvent.class);
		Iterator respGuardIter = col.iterator();
		SortedMap map = new TreeMap();
		while(respGuardIter.hasNext()){
			BenefitsAssessmentResponseEvent bResp = (BenefitsAssessmentResponseEvent) respGuardIter.next();
			map.put(bResp.getEntryDate(),bResp);
		}
		BenefitsAssessmentResponseEvent bResp = (BenefitsAssessmentResponseEvent) map.values().toArray()[col.size()-1];
		myCommonAppForm.setEligibleForMedicaid(bResp.isEligibleForMedicaid());
		myCommonAppForm.setReceivingMedicaid(bResp.isReceivingMedicaid());
	}
	
	/**
	 * GuardianFinancialInfo
	 * @param myCommonAppForm
	 * @param casefileForm
	 */
	private void getGuardianFinancialInfo(CommonAppForm myCommonAppForm, JuvenileCasefileForm casefileForm){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetGuardianFinancialInfoEvent reqGuardFin =
								 (GetGuardianFinancialInfoEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETGUARDIANFINANCIALINFO);
		reqGuardFin.setJuvenileId(casefileForm.getJuvenileNum());
		dispatch.postEvent(reqGuardFin);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse composite = (CompositeResponse) replyEvent;
		MessageUtil.processReturnException(composite);
		Collection guardians = MessageUtil.compositeToCollection(composite, GuardianFinancialInfoResponseEvent.class);
		ArrayList myGuardians = new ArrayList();
		Iterator myIter=guardians.iterator();
		while(myIter.hasNext()){
			GuardianFinancialInfoResponseEvent myGuardianResp=(GuardianFinancialInfoResponseEvent)myIter.next();
			CommonAppForm.GuardianFinancialInformation myGuardian = new CommonAppForm.GuardianFinancialInformation();
			if(myGuardianResp!=null){
				myGuardian.setFirstName(myGuardianResp.getFirstName());
				myGuardian.setMiddleName(myGuardianResp.getMiddleName());
				myGuardian.setLastName(myGuardianResp.getLastName());
				myGuardian.setDisabled(myGuardianResp.isDisabled());
				myGuardian.setOccupation(myGuardianResp.getOccupation());
				myGuardian.setEmployerName(myGuardianResp.getEmployerName());
				myGuardian.setSalary(myGuardianResp.getSalary());
				myGuardian.setSalaryRate(myGuardianResp.getSalaryRate());
				myGuardian.setEmployerAddressId(myGuardianResp.getEmployerAddressId());
				myGuardian.setOtherIncomeAmount(myGuardianResp.getOtherIncomeAmount());
				myGuardian.setAnnualNetIncome(myGuardianResp.getAnnualNetIncome());
				myGuardian.setFoodStamps(myGuardianResp.isFoodStamps());
				myGuardian.setIntangibleProperty(myGuardianResp.getIntangibleProperty());
				myGuardian.setMonthlyLifeInsurancePremium(myGuardianResp.getMonthlyLifeInsurancePremium());
				myGuardian.setPropertyValue(myGuardianResp.getPropertyValue());
				myGuardian.setTanf(myGuardianResp.getTanf());	
				myGuardian.setSsi(myGuardianResp.getSsi());
			}
			myGuardians.add(myGuardian);
		}
		myCommonAppForm.setGuardianFinancialInfo(myGuardians);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map buttonMap = new HashMap();
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.link", "displayExitReport");
		buttonMap.put("button.next", "next");
		return buttonMap;
	}
}
