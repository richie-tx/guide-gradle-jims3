/**
 * Created on Jun 4, 2007
 * @author awidjaja 
 */
package ui.juvenilecase.caseplan.action;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.caseplan.GenerateSignCaseplanEvent;
import messaging.caseplan.GetCaseplanAcknowledgementByCaseplanIdEvent;
import messaging.caseplan.GetCaseplanReportEvent;
import messaging.caseplan.reply.CaseplanAcknowledgementResponseEvent;
import messaging.caseplan.reply.CaseplanListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCaseplanHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.helper.JuvenileCaseworkAlertsHelper;

public class HandleGenerateCaseplanAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.print", "print");
		keyMap.put("button.generateDraft", "generateDraft");
		keyMap.put("button.generateFinal", "generateFinal");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}
	
	public ActionForward print(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)	
	{
		CaseplanForm form = (CaseplanForm) aForm;
		GetCaseplanReportEvent evt=new GetCaseplanReportEvent();
		evt.setCaseplanId(form.getCurrentCaseplan().getCaseplanId());
		CompositeResponse response1=postRequestEvent(evt);
		CaseplanListResponseEvent respEvt = (CaseplanListResponseEvent)MessageUtil.filterComposite(response1, CaseplanListResponseEvent.class);
		if(respEvt==null || respEvt.getReport()==null){
			sendToErrorPage(aRequest,"error.generic","Problems displaying report");
			return aMapping.findForward(UIConstants.FAILURE);
		}
	
		try {
			byte[] contentRep=(byte[])respEvt.getReport();
			setPrintContentResp(aResponse, contentRep, "CASEPLAN_FINAL", 
					UIConstants.PRINT_AS_PDF_DOC);
			
			
			//postRequestEvent(saveReview);
			
		}
		catch(GeneralFeedbackMessageException e) {
			sendToErrorPage(aRequest,"error.generic","Problems displaying report");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		return null;
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)	
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	
	public ActionForward generateDraft(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		HttpSession aSession = aRequest.getSession(); //
		JuvenileCasefileForm juvCaseForm = (JuvenileCasefileForm)aSession.getAttribute("juvenileCasefileForm"); //added for JIMS200075824 to add the probation date in the draft 
		CaseplanForm form = (CaseplanForm)aForm;		
		form.setSupervisionEndDateStr(juvCaseForm.getSupervisionEndDateStr()); ////added for JIMS200075824 to add the probation date in the draft
		form.setPreviousAcknowledgements(new ArrayList());
		String templateName = "";
		if(form.isResidential()) {
			templateName = "REPORTING::CHILD_FAMILY_CASEPLAN_REVIEW";
		}
		else {
			templateName = "REPORTING::FIELD_SUPERVISION_CASEPLAN_REVIEW";
		}
			
		CompositeResponse compResp = sendPrintRequest(templateName, form, null);
		try {
			setPrintContentResp(aResponse, compResp, "CASEPLAN_DRAFT", UIConstants.PRINT_AS_PDF_DOC);
		}
		catch(GeneralFeedbackMessageException e) {
			sendToErrorPage(aRequest, "");
		}

		// Adding record in activity table
		UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.DRAFT_CASE_PLAN_GENERATED, "");
		
		return aMapping.findForward(null);
	}
	
	public ActionForward generateFinal(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		HttpSession aSession = aRequest.getSession(); 
		JuvenileCasefileForm juvCaseForm = (JuvenileCasefileForm)aSession.getAttribute("juvenileCasefileForm"); //added for JIMS200075824 to add the probation date in the draft 		
		CaseplanForm form = (CaseplanForm)aForm;
		form.setSupervisionEndDateStr(juvCaseForm.getSupervisionEndDateStr()); ////added for JIMS200075824 to add the probation date in the draft
		
		//get previous acknowledgements
		GetCaseplanAcknowledgementByCaseplanIdEvent ackEvent = (GetCaseplanAcknowledgementByCaseplanIdEvent)EventFactory.getInstance(JuvenileCasePlanControllerServiceNames.GETCASEPLANACKNOWLEDGEMENTBYCASEPLANID);
		ackEvent.setCaseplanId(form.getCurrentCaseplan().getCaseplanId());
		IDispatch dispatch1=EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(ackEvent);	
		CompositeResponse response=(CompositeResponse)dispatch1.getReply();
		Collection <CaseplanAcknowledgementResponseEvent> listResponse = (Collection)MessageUtil.compositeToCollection(response, CaseplanAcknowledgementResponseEvent.class);
		//Collections.sort((List)listResponse);
		
		ArrayList forProcessing = new ArrayList();
		 List sortedList = new ArrayList(listResponse);
		  ArrayList sortFields = new ArrayList();
         sortFields.add(new ReverseComparator(new BeanComparator("entryDate")));        
         sortFields.add(new ReverseComparator(new BeanComparator("createDate")));
         ComparatorChain multiSort = new ComparatorChain(sortFields);
         Collections.sort(sortedList, multiSort);
         Iterator iter=sortedList.iterator();
       //grab the latest acknowledgement record for the juvenile
		while(iter.hasNext())
		{
			CaseplanAcknowledgementResponseEvent evt =(CaseplanAcknowledgementResponseEvent) iter.next();				
			if(evt.getSignatureStatus()!=null &&(evt.getSignatureStatus().equalsIgnoreCase("Juvenile refused to sign") || evt.getSignatureStatus().equalsIgnoreCase("Juvenile Signed")|| evt.getSignatureStatus().equalsIgnoreCase("Juvenile Signature Not Applicable")))
			{
				forProcessing.add(evt);
				break;
			}
		}
		
		 //grab the latest acknowledgement record for the guardian
		 iter=sortedList.iterator();
		 while(iter.hasNext())
			{
				CaseplanAcknowledgementResponseEvent evt =(CaseplanAcknowledgementResponseEvent) iter.next();				
				if(evt.getSignatureStatus()!=null &&(evt.getSignatureStatus().equalsIgnoreCase("Guardian refused to sign") || evt.getSignatureStatus().equalsIgnoreCase("Guardian Signed")|| evt.getSignatureStatus().equalsIgnoreCase("Not Available for Guardian Signature")|| evt.getSignatureStatus().equalsIgnoreCase("Guardian Signature Not Applicable")))
				{
					forProcessing.add(evt);
					break;
				}
			}
		form.setPreviousAcknowledgements(forProcessing);
		JuvenileCasefileForm casefileForm=UIJuvenileHelper.getJuvenileCasefileForm(aRequest,true);
		CompositeResponse compResp;
		if(form.isResidential()) {
			compResp = sendPrintRequest("REPORTING::CHILD_FAMILY_CASEPLAN_REVIEW", form, null);
		}
		else{
			compResp = sendPrintRequest("REPORTING::FIELD_SUPERVISION_CASEPLAN_REVIEW", form, null);
		}
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent) MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		if(aReportRespEvt!=null && aReportRespEvt.getContent()!=null && aReportRespEvt.getContent().length>0){
			GenerateSignCaseplanEvent evt = new GenerateSignCaseplanEvent();
			evt.setCaseplanId(form.getCurrentCaseplan().getCaseplanId());
			evt.setReport(aReportRespEvt.getContent());
			evt.setSigned(false);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(evt);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			
			if(form.getCurrentCaseplan().getStatus().equalsIgnoreCase("REVIEWED")){
				UIJuvenileCaseplanHelper.fetchCaseplanDetails(form); 
			}
			form.setSelectedValue(form.getCurrentCaseplan().getCaseplanId());
			aRequest.setAttribute("statusReport", "confirmFinal");

			//Creating Task
			JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper();
			String subject = "";
			
			// For Non-Residential supervisionType = Deferred Adjudication, Prosecution
			if(!(form.getSupervisionType().equalsIgnoreCase(UIConstants.CASEFILE_SUPERVISION_TYPE_RESIDENTIAL_SUPERVISION))){
				subject = "Non-residential caseplan needs JPO review for Supervision #" + form.getCasefileId()+", Juvenile# "+ form.getJuvenileNum();
				String scheduleClassName = mojo.naming.CalendarConstants.MONTHLY_SCHEDULE_CLASS;
				//String scheduleClassName = mojo.naming.CalendarConstants.QUARTERLY_SCHEDULE_CLASS;
				String action = "Link";
				helper.scheduleCasePlanReviewTask(casefileForm.getProbationOfficerLogonId(),"MJCW.JPO.NONRESIDENTIAL.CASEPLAN.NOTIFICATION",subject,form,2160,scheduleClassName,action,"jpo");
			}	
			// For Residential supervisionType
			if((form.getSupervisionType().equalsIgnoreCase(UIConstants.CASEFILE_SUPERVISION_TYPE_RESIDENTIAL_SUPERVISION))){
				subject = "Residential caseplan needs JPO review for Supervision #" + form.getCasefileId()+", Juvenile# "+ form.getJuvenileNum();
				String scheduleClassName = mojo.naming.CalendarConstants.MONTHLY_SCHEDULE_CLASS;
				String action = "Link";
				helper.scheduleCasePlanReviewTask(casefileForm.getProbationOfficerLogonId(),"MJCW.JPO.RESIDENTIAL.CASEPLAN.NOTIFICATION",subject,form,720,scheduleClassName,action,"jpo");		
			}	
			
			//End
			
			
			return aMapping.findForward("returnToCaseplan");
		}
		else{  // this should go to same page but show user an error message
			return aMapping.findForward("success");	
		}
		
	}

}
