/*
 * Created on Dec 30, 2005
 *
 */
package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.CreateActivityEvent;
import messaging.casefile.SaveCommonAppDocEvent;
import messaging.casefile.UpdateCourtExitPlanEvent;
import messaging.casefile.UpdateExitReportDSMDiagnosisEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import messaging.casefile.reply.DSMDiagnosisResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCasefileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.casefile.ActivityHelper;
import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.CasefileClosingHelper;
import ui.juvenilecase.casefile.CommonAppHelper;
import ui.juvenilecase.casefile.CommonAppReportPrintBean;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author jjose
 * SubmitCommonAppExitReportAction
 */
public class SubmitCommonAppExitReportAction extends JIMSBaseAction {

	/**
	 * saveAndContinue
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward saveAndContinue(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse) {
		CommonAppForm myCommonAppForm = (CommonAppForm) aForm;
		JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		//#bug fix 35750 starts
		CasefileClosingForm myClosingForm = (CasefileClosingForm) UIJuvenileHelper.getJuvenileCasefileClosingForm(aRequest,casefileForm.getSupervisionNum());
		myCommonAppForm.setCasefileId(casefileForm.getSupervisionNum());
		//#bug fix 35750 ends
		myCommonAppForm.setAction(UIConstants.CONFIRM_UPDATE);
		myCommonAppForm.setSecondaryAction("");
		myCommonAppForm.setSelectedValue("");

		UpdateCourtExitPlanEvent myEvent = UIJuvenileCasefileClosingHelper.getUpdateCourtExitPlanEventFROMCommonAppForm(myCommonAppForm);

		myEvent.setPrevCasefileClosingInfoId(myClosingForm.getClosingInfoId());
		myEvent.setExitPlanTemplateLocation(myClosingForm.getReportFileLocOnServer());
		myEvent.setCasefileClosingStatus(casefileForm.getCaseStatusId());
		myEvent.setSupervisionNum(myClosingForm.getSupervisionNumber());
		myEvent.setControllingReferralId( myCommonAppForm.getSelectedControllingReferral() );
		myEvent.setResponses(UIUtil.getUpdateResponseEvtFromUIQuestionGroups(myCommonAppForm.getExitReportQuestions(), myClosingForm
						.getClosingInfoId(), myCommonAppForm.getReportType()));

		CompositeResponse compositeResponse = MessageUtil.postRequest(myEvent);
		CasefileClosingResponseEvent response = (CasefileClosingResponseEvent) MessageUtil.filterComposite(compositeResponse,
						CasefileClosingResponseEvent.class);
		if (response != null && response.getCasefileClosingInfoId()!=null) {
			myCommonAppForm.setClosingInfoId(response.getCasefileClosingInfoId());
			myClosingForm.setClosingInfoId(response.getCasefileClosingInfoId());
		}
		myCommonAppForm.setExitReportFilled(true);
		myClosingForm.setSelectedDispostion(myCommonAppForm.getSelectedDisposition());
		if (myCommonAppForm.isDsmDiagnosisFilled()) {
			Collection<CommonAppForm.Diagnosis> coll = myCommonAppForm.getDsmResults();
			Iterator<CommonAppForm.Diagnosis> iter = coll.iterator();
			while (iter.hasNext()) {
				CommonAppForm.Diagnosis diagnosis = iter.next();
				String testId = diagnosis.getTestId();
				Collection<DSMDiagnosisResponseEvent> diagnosisResults = diagnosis.getDiagnosisResults();
				Iterator<DSMDiagnosisResponseEvent> diagnosisResultsIter = diagnosisResults.iterator();
				while (diagnosisResultsIter.hasNext()) {
					DSMDiagnosisResponseEvent diagnosisResp =  diagnosisResultsIter.next();
					if (!diagnosisResp.getDiagnosis().equals("")) {
						UpdateExitReportDSMDiagnosisEvent event = (UpdateExitReportDSMDiagnosisEvent) EventFactory
								.getInstance(JuvenileCasefileControllerServiceNames.UPDATEEXITREPORTDSMDIAGNOSIS);
						event.setCasefileCosingInfoId(myCommonAppForm.getClosingInfoId());
						event.setTestSessId(testId);
						event.setDiagnosisCd(diagnosisResp.getDiagnosisCd());
						event.setConditionCd(diagnosisResp.getConditionCd());
						event.setSeverityCd(diagnosisResp.getSeverityCd());
						event.setDsmDiagnosisId(diagnosisResp.getDsmDiagnosisId());
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
						dispatch.postEvent(event);
						ReturnException returnException = (ReturnException) MessageUtil.filterComposite(compositeResponse,
										ReturnException.class);
						if (returnException != null) {
							sendToErrorPage(aRequest, "error.common");
						}
					}
				}
			}
			return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
		} else {
			CasefileClosingHelper.getDSMDiagnosisResults(myCommonAppForm);
			return aMapping.findForward("dsmDiagnosisSuccess");
		}
	}
	
	
	
	/**
	 * print
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward print(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CommonAppForm myCommonAppForm = (CommonAppForm) aForm;
		JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);

		String juvenileNum = casefileForm.getJuvenileNum();
		String casefileId = casefileForm.getSupervisionNum();

		myCommonAppForm.setAction("");

		CommonAppReportPrintBean myReportBean = CommonAppHelper.prepareBean(myCommonAppForm,juvenileNum, casefileId);

		myReportBean.setCourtOrder(myCommonAppForm.getCourtOrder());
		myReportBean.setJuvenileNumber(juvenileNum);

		List<CommonAppForm.Placement> placements = new ArrayList<CommonAppForm.Placement>();
		List<CommonAppForm.Placement> outofHomePlacements = new ArrayList<CommonAppForm.Placement>();

		StringBuilder sbRecentOutOfHomeDischargeDate = new StringBuilder (); //added for JIMS200077404  
		double averageNumberOfChildCareDays = CommonAppHelper.getJuvenilePlacementHistory(myCommonAppForm, juvenileNum, placements, outofHomePlacements,sbRecentOutOfHomeDischargeDate);
		myReportBean.setAverageNumberOfChildCareDays(averageNumberOfChildCareDays);
		myReportBean.setPlacements(placements);
		myReportBean.setOutofHomePlacements(outofHomePlacements.size());
		
		myReportBean.setRecentOutOfHomeDischargeDate(sbRecentOutOfHomeDischargeDate.toString());
		//ER 11036 changes starts
		myReportBean.setLocCurrentOutofHomePlacement(myCommonAppForm.getPlacement().getRecentLevelOfCare());
		myReportBean.setDischargeReason(myCommonAppForm.getPlacement().getRecentReasonForDischarge());
		//ER 11036 changes ends
		//CommonAppHelper.getOffenseInformation(myReportBean, juvenileNum, casefileId,myCommonAppForm); //155588
		CommonAppHelper.getOffenseInformationNew(myReportBean, juvenileNum, casefileId,myCommonAppForm); //155588
		CommonAppHelper.getFamilyHistoryInfo(myReportBean, juvenileNum, casefileId);

		CommonAppHelper.getDSMResults(myReportBean, myCommonAppForm);

		aRequest.getSession().setAttribute("reportInfo", myReportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.COMMON_APPLICATION_FOR_PLACEMENT_OF_CHILDREN);
		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
		
		if(pdfDocument!=null){
			SaveCommonAppDocEvent saveEvt = new SaveCommonAppDocEvent();
			saveEvt.setCasefileId(casefileId);
			saveEvt.setDocument(pdfDocument);
			saveEvt.setDocTypeCd(UIConstants.COMMONAPP_REPORT);
	
			CompositeResponse response = postRequestEvent(saveEvt);
			CommonAppDocResponseEvent respEvt = (CommonAppDocResponseEvent) MessageUtil.filterComposite(response, CommonAppDocResponseEvent.class);
			if (respEvt == null) {
				sendToErrorPage(aRequest, "error.generic","Problems generating and saving report");
				return aMapping.findForward(UIConstants.FAILURE);
			}
			
			// remove the pdf report attributes from session when finished saving to database
			aRequest.removeAttribute("isPdfSaveNeeded");
			aRequest.removeAttribute("pdfSavedReport");
			
			//add an activity after printing. //added for #34115
			CreateActivityEvent activityEvt = new CreateActivityEvent();
			activityEvt.setActivityTitle("Common App Report.");
			activityEvt.setSupervisionNumber(casefileForm.getSupervisionNum());
			activityEvt.setRecordActivity(true);
			activityEvt.setActivityDate(DateUtil.getCurrentDate());
			activityEvt.setComments("Common App Report Printed. "+"CaseFile: "+ casefileForm.getSupervisionNum() + "Juvenile: " + casefileForm.getJuvenileNum());
			activityEvt.setActivityCategoryId( ActivityConstants.ACTIVITY_CATEGORY_REPORTING);
			activityEvt.setActivityTypeId(ActivityConstants.ACTIVITY_CATEGORY_COMMONAPP_REPORT);
			activityEvt.setActivityCodeId(ActivityConstants.ACTIVITY_CODE_COMMONAPP_REPORT);
			activityEvt.setActivityTime(DateUtil.getHHMMSSWithColonFromDate(DateUtil.getCurrentDate()));
			MessageUtil.postRequest(activityEvt);
			
			/*ActivityHelper helper = new ActivityHelper();
			helper.createActivity(activityEvt);
			helper = null;*/
		}
		return null;
	}
	
	
	/**
	 * returnToCasefile
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward returnToCasefile(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse) {
		CommonAppForm myCommonAppForm = (CommonAppForm) aForm;
		myCommonAppForm.setAction("");
		return aMapping.findForward(UIConstants.SUCCESS_HOME);
	}

	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4395C2380355
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	/**
	 * back
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.saveAndContinue", "saveAndContinue");
		keyMap.put("button.printCommonApp", "print");
		keyMap.put("button.returnToCasefileClosingDetails", "returnToCasefile");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}

}// END CLASS