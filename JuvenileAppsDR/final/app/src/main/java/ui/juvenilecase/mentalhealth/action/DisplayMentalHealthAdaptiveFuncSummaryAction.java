//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthAdpativeFuncSummaryAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.mentalhealth.GetMentalHealthAFTestDataEvent;
import messaging.mentalhealth.reply.AFTestResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;
import ui.juvenilecase.mentalhealth.form.JOTChargeReportBean;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.PetitionDetailsForm;
import ui.juvenilecase.form.AssignedReferralsForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayMentalHealthAdaptiveFuncSummaryAction extends
		JIMSBaseAction {

	/**
	 * @roseuid 45D4AEA3012D
	 */
	public DisplayMentalHealthAdaptiveFuncSummaryAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D49C83028D
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setActionType("summary");
		return forward;
	}

	public ActionForward view(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		// get the provider and instructor name
		Collection coll = sessForm.getAfResultsList();
		Iterator iter = coll.iterator();
		while (iter.hasNext()) {
			AFTestResponseEvent resp = (AFTestResponseEvent) iter.next();
			if (resp.getTestId().equals(sessForm.getSelectedValue())) {
				sessForm.setServiceProviderName(resp.getServiceProviderName());
				sessForm.setInstructorName(resp.getInstructorName());
				break;
			}
		}
		GetMentalHealthAFTestDataEvent afEvent = (GetMentalHealthAFTestDataEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHAFTESTDATA);
		afEvent.setTestId(sessForm.getSelectedValue());
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(afEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Object obj = MessageUtil.filterComposite(response,AFTestResponseEvent.class);
		if (obj != null) {
			AFTestResponseEvent resp = (AFTestResponseEvent) obj;
			fillAFRec(resp, sessForm.getAfRec());
		}
		sessForm.setActionType("view");
		sessForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	private void fillAFRec(AFTestResponseEvent resp,TestingSessionForm.AdaptiveFunctioningTest afTest) {
		afTest.setTestDate(DateUtil.dateToString(resp.getTestDate(),"MM/dd/yyyy"));
		afTest.setTestNameId(resp.getTestName());
		afTest.setStandardScore(resp.getStandardScore());
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}
	
	public ActionForward UJACPrint(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		HttpSession aSession = aRequest.getSession();
		JuvenileCasefileForm caseForm = (JuvenileCasefileForm)aSession.getAttribute("juvenileCasefileForm");			
		PetitionDetailsForm petitionForm = (PetitionDetailsForm)aSession.getAttribute("petitionDetailsForm");
		AssignedReferralsForm assignedRefForm = (AssignedReferralsForm)aSession.getAttribute("assignedReferralsForm");		
		JOTChargeReportBean report = new JOTChargeReportBean();
		report.setJuvenileNum(caseForm.getJuvenileNum());
		report.setJuvenileName(caseForm.getJuvenileFullName());
		report.setReferralNum(assignedRefForm.getReferralNum());
		report.setRaceId(caseForm.getRaceId());
		report.setCurrentAge(caseForm.getCurrentAge());
		report.setGender(caseForm.getSex());		
		//get the SID number
		GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		requestEvent.setJuvenileNum(caseForm.getJuvenileNum());

		CompositeResponse replyEvent = postRequestEvent(requestEvent);

		JuvenileProfileDetailResponseEvent juvenile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
						JuvenileProfileDetailResponseEvent.class);
		report.setSidNumber(juvenile.getSID());
		report.setDateOfBirth(DateUtil.dateToString(juvenile.getDateOfBirth(), UIConstants.DATE_FMT_1));
		report.setMultiracial(juvenile.isMultiracial());
		
		//U.S 88526
        	if (juvenile.getHispanic() != null)
        	{
        	    if (juvenile.getHispanic().equalsIgnoreCase("Y"))
        	    {
        		report.setHispanic(true);
        	    }
        	    else
        	    {
        		report.setHispanic(false);
        	    }
        	}
		report.setVerifiedDOB(juvenile.isVerifiedDOB());
		report.setArrestDate(petitionForm.getArrestDate());
		report.setArrestTime(petitionForm.getArrestTime());
		report.setPetitionNum(petitionForm.getPetitionNum());
		
		List charges = petitionForm.getJotCharges();
		if(charges!=null)
			report.setJotCharges(charges);
		List sumOfFactsList = (List) petitionForm.getSummaryOfFacts();
		int size = sumOfFactsList.size();
		if(size > 0 ) {
			String fact = "";
			StringBuffer factsBuffer = new StringBuffer();
			for (int s = 0; s < size; s++) {
				fact = (String) sumOfFactsList.get(s);
				factsBuffer.append(fact);
			}
			report.setSummaryOF(factsBuffer.toString());
		}
		report.setSummaryOfFacts(petitionForm.getSummaryOfFacts());
		
		CompositeResponse compResp = sendPrintRequest("REPORTING::JOT_CHARGE_REPORT",
				report, null);
		
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent) MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		if (aReportRespEvt.getContent() == null	|| aReportRespEvt.getContent().length < 1) {
			sendToErrorPage(aRequest, "error.generic",	"Problems generating report");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		try {
			setPrintContentResp(aResponse, compResp, "JOT_Charge",UIConstants.PRINT_AS_PDF_DOC);
		} catch (GeneralFeedbackMessageException e) {
			sendToErrorPage(aRequest, "");
		}
		ActionForward forward = aMapping.findForward(UIConstants.PRINT_SUCCESS);
		return forward;
	}

	public ActionForward print(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		HttpSession aSession = aRequest.getSession();
		JuvenileCasefileForm caseForm = (JuvenileCasefileForm)aSession.getAttribute("juvenileCasefileForm");			
		PetitionDetailsForm petitionForm = (PetitionDetailsForm)aSession.getAttribute("petitionDetailsForm");
		AssignedReferralsForm assignedRefForm = (AssignedReferralsForm)aSession.getAttribute("assignedReferralsForm");		
		JOTChargeReportBean report = new JOTChargeReportBean();
		report.setJuvenileNum(caseForm.getJuvenileNum());
		report.setJuvenileName(caseForm.getJuvenileFullName());
		report.setReferralNum(assignedRefForm.getReferralNum());
		report.setRaceId(caseForm.getRaceId());
		report.setCurrentAge(caseForm.getCurrentAge());
		report.setGender(caseForm.getSex());		
		//get the SID number
		GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		requestEvent.setJuvenileNum(caseForm.getJuvenileNum());

		CompositeResponse replyEvent = postRequestEvent(requestEvent);

		JuvenileProfileDetailResponseEvent juvenile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
						JuvenileProfileDetailResponseEvent.class);
		report.setSidNumber(juvenile.getSID());
		report.setDateOfBirth(DateUtil.dateToString(juvenile.getDateOfBirth(), UIConstants.DATE_FMT_1));
		report.setMultiracial(juvenile.isMultiracial());
		//U.S 88526
		if(juvenile.getHispanic()!=null && juvenile.getHispanic().equalsIgnoreCase("Y")){
		    report.setHispanic(true);
		}else{
		    report.setHispanic(false);
		}
		report.setVerifiedDOB(juvenile.isVerifiedDOB());
		report.setArrestDate(petitionForm.getArrestDate());
		report.setArrestTime(petitionForm.getArrestTime());
		report.setPetitionNum(petitionForm.getPetitionNum());
		
		List charges = petitionForm.getJotCharges();
		if(charges!=null)
			report.setJotCharges(charges);
		List sumOfFactsList = (List) petitionForm.getSummaryOfFacts();
		if (sumOfFactsList != null) {
			int size = sumOfFactsList.size();
			if(size > 0 ) {
				String fact = "";
				StringBuffer factsBuffer = new StringBuffer();
				for (int s = 0; s < size; s++) {
					fact = (String) sumOfFactsList.get(s);
					factsBuffer.append(fact);
				}
				report.setSummaryOF(factsBuffer.toString());
			}
			report.setSummaryOfFacts(petitionForm.getSummaryOfFacts());
		}
		aRequest.getSession().setAttribute("reportInfo", report);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.JOT_CHARGE);

	    return null;
	}
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.view", "view");
		keyMap.put("button.print", "print");
	}
}