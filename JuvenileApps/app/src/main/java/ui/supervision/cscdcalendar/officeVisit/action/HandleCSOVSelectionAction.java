/*
 * Created on Mar 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.officeVisit.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administercasenotes.GetSuperviseeInSupervisionPeriodEvent;
import messaging.administercasenotes.domintf.ICasenote;
import messaging.administercasenotes.reply.CasenotePrintResponseEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.administercasenotes.to.CasenoteCaseTO;
import messaging.cscdcalendar.GetCSEventCasenotesEvent;
import messaging.cscdcalendar.GetMonthlyCSCalendarEvent;
import messaging.cscdcalendar.reply.CSGroupOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.MonthlyCSCalendarResponseEvent;
import messaging.report.GenericPrintRequestEvent;
import messaging.supervisionorder.reply.SuperviseeResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.CasenoteControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.contact.user.helper.UIUserFormHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.CaseInfo;
import ui.supervision.Casenote;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercasenotes.CasenotesJournalBean;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleCSOVSelectionAction extends JIMSBaseAction {

	public ActionForward update(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.setConfirmMsg("");
		form.loadSelectedEvent(form.getSelectedEventId());
		//load supervisee header form to be displayed in JSP
		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm)getSessionForm(aMapping, aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		myHeaderForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		//load supervisee form to be displayed in JSP
		SuperviseeForm superviseeForm = (SuperviseeForm)getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_FORM,true);
		//clear old supervisee form
		superviseeForm.clear();
		superviseeForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		
		form.setActivityFlow(UIConstants.UPDATE);
		
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}
	
	public ActionForward reschedule(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String returnString = "";
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.setConfirmMsg("");
		form.setActivityFlow("reschedule");
		
		form.loadSelectedEvent(form.getSelectedEventId());
		//load supervisee header form to be displayed in JSP
		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm)getSessionForm(aMapping, aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		
		//load supervisee form to be displayed in JSP
		SuperviseeForm superviseeForm = (SuperviseeForm)getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_FORM,true);
		//clear old supervisee form
		superviseeForm.clear();
		superviseeForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
		
		if (myHeaderForm.getOfficerPositionId() != null) {
			myHeaderForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
			returnString = UIConstants.UPDATE_SUCCESS;
		}else{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No active Supervision order exists for this SPN");
			returnString = UIConstants.ERROR;
			form.setActivityFlow("view");
		}
		return aMapping.findForward(returnString);
	}
	
	public ActionForward enterResults(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		
		form.loadSelectedEvent(form.getSelectedEventId());
		//load supervisee header form to be displayed in JSP
		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm)getSessionForm(aMapping, aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		myHeaderForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		form.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		
		//load supervisee form to be displayed in JSP
		SuperviseeForm superviseeForm = (SuperviseeForm)getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_FORM,true);
		superviseeForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
		
		form.setActivityFlow("enterResults");
		return aMapping.findForward("enterResultsSuccess");
	}
	
	public ActionForward delete(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		
		form.loadSelectedEvent(form.getSelectedEventId());
		//load supervisee header form to be displayed in JSP
		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm)getSessionForm(aMapping, aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		myHeaderForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		
		form.setActivityFlow(UIConstants.DELETE);
		aRequest.setAttribute("status","summary");
		
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}
	
	public ActionForward finish(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.FINISH);	
	}

	public ActionForward finishAndScheduleNext(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String returnString = "";
		//load supervisee header form to check for officer position Id
		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm)getSessionForm(aMapping, aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		if (myHeaderForm.getOfficerPositionId() != null) {
			returnString = "finishAndScheduleNext";
		}else{
			aRequest.setAttribute("status", UIConstants.SUMMARY);
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No active Supervision order exists for this SPN");
			returnString = UIConstants.ERROR;
		}
		
		return aMapping.findForward(returnString);
	}
	
	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/**
	 * 
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.setAltFlow("");
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 * @throws IOException 
	 */
	public ActionForward printCasenote(ActionMapping aMapping,	ActionForm aForm,HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws IOException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		form.setConfirmMsg("");
		String defendantId = "";
		
		if ( "OV".equals( form.getEventTypeCd() )){
			
			List ovList = form.getOfficeVisits();
			for ( int a=0; a< ovList.size(); a++ ){
				
				CSOfficeVisitResponseEvent ovResp = (CSOfficeVisitResponseEvent) ovList.get(a);
				if( form.getSelectedEventId().equals(ovResp.getEventId() )){
					
					defendantId = ovResp.getSuperviseeId();
					break;
				}
			}
		}else{
			
			List gvList= form.getGroupOfficeVisits();
			for ( int a=0; a< gvList.size(); a++ ){
				
				CSGroupOfficeVisitResponseEvent gvResp = (CSGroupOfficeVisitResponseEvent) gvList.get(a);
				List gvOfficeVisits = (List) gvResp.getOfficeVisits();
				
				for ( int b =0; b< gvOfficeVisits.size(); b ++ ){
					
					CSOfficeVisitResponseEvent ovResp = (CSOfficeVisitResponseEvent) gvOfficeVisits.get(b);
					if( form.getSelectedEventId().equals( ovResp.getEventId() )){
						
						defendantId = ovResp.getSuperviseeId();
						break;
					}
				}
			}
		}
		
		List casenoteResp = new ArrayList();
		CasenotePrintResponseEvent printResp = new CasenotePrintResponseEvent();
		
		GetCSEventCasenotesEvent reqEvent = new GetCSEventCasenotesEvent();
		reqEvent.setCsEventId( form.getSelectedEventId() );
		
		casenoteResp = MessageUtil.postRequestListFilter(reqEvent, CasenoteResponseEvent.class);
		Casenote newCasenote = new Casenote();
		List prCasenotesList = new ArrayList();
		
		HttpSession session = aRequest.getSession(); 
		CasenoteJournalForm casenoteJournalForm = new CasenoteJournalForm();
							session.setAttribute("casenoteJournalForm", casenoteJournalForm);
		
		
		if (!casenoteResp.isEmpty())
		{
			CasenoteSearchForm searchForm = new CasenoteSearchForm();
			Collection casenoteSubjectList = searchForm.getCasenoteSubjectList();
			Collection casenoteTypeList = searchForm.getCasenoteTypeList();
			Collection collateralList = searchForm.getCollateralList();
			Collection contactMethodList = searchForm.getContactMethodList();
			String currentUserId = UIUtil.getCurrentUserID();

			Iterator iter = casenoteResp.iterator();
			while (iter.hasNext())
			{
				ICasenote casenote = (CasenoteResponseEvent)iter.next();
				newCasenote = UICasenoteHelper.getCasenote(casenote, currentUserId);
				newCasenote.setCasenoteTypeId(casenote.getCasenoteTypeId(), casenoteTypeList);
				String[] associatesArr = UICasenoteHelper.getArrayFromCollection(casenote.getAssociates());
				newCasenote.setAssociateIds(associatesArr, collateralList);
				String[] cnSubjs = UICasenoteHelper.getArrayFromCollection(casenote.getSubjects());
				newCasenote.setSubjectIds(cnSubjs, casenoteSubjectList);
				newCasenote.setAssociateIds(associatesArr, collateralList);
				newCasenote.setContactMethodId(casenote.getContactMethodId(), contactMethodList);
				prCasenotesList.add(newCasenote);
			}
			UICasenoteHelper.resolveCreatorNames(prCasenotesList);
			casenoteJournalForm.getSearchCasenote().setCasenoteResults( prCasenotesList );
		}
		
			CasenotesJournalBean printDataBean = new CasenotesJournalBean();
			List printList = this.getSuperviseeInfo( printResp, prCasenotesList, defendantId  );
			printDataBean.setEvents( printList );
		
			GenericPrintRequestEvent casenotesPrintEvent = new GenericPrintRequestEvent();
		    casenotesPrintEvent.addDataObject( printDataBean );   
		    casenotesPrintEvent.setReportName("REPORTING::CASENOTE_CSEVENT");
		    	    
	   	    ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.postRequest(casenotesPrintEvent, ReportResponseEvent.class );
	   	    
	   	    aResponse.setContentType("application/x-file-download");
	   	    aResponse.setHeader("Content-disposition", "attachment; filename="
	   	    							+ aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");   
	   	    aResponse.setHeader("Cache-Control", "must-revalidate");   
	   	    aResponse.setContentLength(aRespEvent.getContent().length);   
	   	    aResponse.resetBuffer();   
	   	    OutputStream os;
	   	    os = aResponse.getOutputStream();
	   	    os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);   
	   	    os.flush();   
	   	    os.close(); 

	   	    return null;
		
	}
	
	private List getSuperviseeInfo(	CasenotePrintResponseEvent printResponse, List casenoteList, String spn )
	{
		
		List responseList = new ArrayList();
		GetSuperviseeInSupervisionPeriodEvent mySuperviseeEvent = (GetSuperviseeInSupervisionPeriodEvent) EventFactory
											.getInstance(CasenoteControllerServiceNames.GETSUPERVISEEINSUPERVISIONPERIOD);
			mySuperviseeEvent.setSpn( spn );
			mySuperviseeEvent.setUserAgencyId( UIUtil.getCurrentUserAgencyID() );
			mySuperviseeEvent.setActiveSupervisionPeriod( true );
			
			SuperviseeResponseEvent supResp = (SuperviseeResponseEvent) 
									MessageUtil.postRequest( mySuperviseeEvent, SuperviseeResponseEvent.class );

		if ( supResp != null ){
			
			List myCasesList = new ArrayList();
			List caseList = (List) supResp.getCases();
			CaseInfo myCase = new CaseInfo();
			
			for ( int x=0; x< caseList.size(); x++ ){
				
				CasenoteCaseTO caseTO = (CasenoteCaseTO) caseList.get(x);
				
				myCase.setCaseNum(caseTO.getCaseNum());
				myCase.setCdi(caseTO.getCdi());
				myCase.setCourt( caseTO.getCourtNum( ));
				myCase.setCaseSupPeriodBeginDate(caseTO.getCaseSupervisionPeriodBeginDate());
				myCase.setCaseSupPeriodEndDate(caseTO.getCaseSupervisionPeriodEndDate() );
				myCase.setSupPeriodId(caseTO.getSupervisionPeriodId());
				myCase.setSuperviseeName( caseTO.getSuperviseeName());
				myCasesList.add(myCase);
				myCase = new CaseInfo();
				
			}
			
			printResponse.setSuperviseeName( UIUserFormHelper.formatFullName( supResp.getFirstName(), supResp.getMiddleName(), supResp.getLastName() ));
			printResponse.setConnectionCd( supResp.getConnectionId() );
			printResponse.setDefendantId( supResp.getSpn() );
			printResponse.setOfficerName( supResp.getOfficerName() );
			printResponse.setOfficerPosition( supResp.getOfficerPosition() );
			printResponse.setProgramUnit( supResp.getUnit());
			printResponse.setSex( supResp.getSexId() );
			printResponse.setRace( supResp.getRaceId() );
			printResponse.setCases( myCasesList );
			printResponse.setCasenoteResults( casenoteList );
			printResponse.setDateOfBirth( supResp.getDateOfBirth() );
			this.getNextContact( supResp.getSpn(), printResponse );
			responseList.add( printResponse );
		}
		return responseList;
	}
	
	private void getNextContact( String spn,CasenotePrintResponseEvent printRequest ){
		
		GetMonthlyCSCalendarEvent getMonthlyEvents = 
			(GetMonthlyCSCalendarEvent)EventFactory.getInstance(CSEventControllerServiceNames.GETMONTHLYCSCALENDAR);
		getMonthlyEvents.setCurrentContext("S");		
		StringBuffer padSpn = new StringBuffer( spn );
		while ( padSpn.length() < 8 ){
			padSpn.insert( 0, "0" );
	    }
		getMonthlyEvents.setSuperviseeId(padSpn.toString());		
		getMonthlyEvents.setStartDatetime(Calendar.getInstance().getTime());
		
		List eventsList = MessageUtil.postRequestListFilter( getMonthlyEvents, MonthlyCSCalendarResponseEvent.class);

		if( eventsList != null && !eventsList.isEmpty() ){
			// Sort list if has data
			Collections.sort( eventsList, MonthlyCSCalendarResponseEvent.CSCalendarDateComparator );
			Collections.reverse( eventsList );
			Iterator<MonthlyCSCalendarResponseEvent> calendarEvents = eventsList.iterator();
			while( calendarEvents.hasNext() ){
				MonthlyCSCalendarResponseEvent event = (MonthlyCSCalendarResponseEvent) calendarEvents.next();

				if (event.getStartDatetime().after(getMonthlyEvents.getStartDatetime()) ||
					( event.getStartTime() != null && !event.getStartTime().equals("") &&
					  event.getStartTime().after(getMonthlyEvents.getStartDatetime()) ) ) {
					if(event.getStatus().equals("O")) {
						if( printRequest.getNextContactDate() == null || printRequest.getNextContactDate().after(event.getStartDatetime()) ){
						
							printRequest.setNextContactDate(event.getStartDatetime());
						// if starttime present use it, as it contains date and time value.	
							if (event.getStartTime() != null && !event.getStartTime().equals("")){
								printRequest.setNextContactDate(event.getStartTime());
							}
							printRequest.setNextContactTime(event.getStartTime());
							printRequest.setContactMethod(event.getCategoryDesc());
						}
					}
				} 
			}
		}
	}
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.update","update");
		keyMap.put("button.reschedule","reschedule");
		keyMap.put("button.enterResults","enterResults");
		keyMap.put("button.updateResults","enterResults");
		keyMap.put("button.delete","delete");
		keyMap.put("button.back","back");
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.finishAndScheduleNext", "finishAndScheduleNext");
		keyMap.put("button.printCasenote","printCasenote");
	}
}