package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ui.common.Name;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.GetJuvenileAttendanceEvent;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.interviewinfo.CreateParentalStatementReportEvent;
import messaging.interviewinfo.GetInterviewDetailEvent;
import messaging.interviewinfo.GetInterviewTaskListEvent;
import messaging.interviewinfo.GetServiceAndAttendanceEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import messaging.interviewinfo.reply.InterviewServiceAndAttendanceResponseEvent;
import messaging.interviewinfo.reply.InterviewTaskResponseEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import messaging.interviewinfo.to.ParentalStatementReportDataTO;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.InterviewConstants;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.InterviewDocument;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.report.transactions.PDFReporting;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileInterviewInfoHelper;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.schedulecalendarevent.UISupervisionCalendarHelper;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm.PersonResponsible;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author awidjaja
 * 
 */
public class HandleJuvInterviewSelectionAction extends JIMSBaseAction
{
	private final String contactAdmin = "Please contact your System Administrator with a description of this problem." ;
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward printParentalStatement(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return(aMapping.findForward("parentalStatement"));
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward generateDocument(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		CreateParentalStatementReportEvent reqEvent = new CreateParentalStatementReportEvent();

		reqEvent.setCasefileId(form.getCasefileId());
		reqEvent.setSpanishText(form.isSpanishText());

		// Add record in activity table
		UIJuvenileHelper.createActivity(form.getCasefileId(),
				ActivityConstants.PARENTAL_WRITTEN_STATEMENT_REVIEWED, "");
		
		String casefileId = form.getCasefileId();
		if( casefileId != null && casefileId.trim().length() > 0 )
		{
			JuvenileCasefile casefile = JuvenileCasefile.find( casefileId );
			// Profile stripping fix task 97536
			//Juvenile juvenile = casefile.getJuvenile();
			JuvenileCore juvenile = casefile.getJuvenile();
			
			ParentalStatementReportDataTO reportTO = new ParentalStatementReportDataTO();
			InterviewHelper.buildParentalStatementReportData( reportTO, juvenile, casefile );
			aRequest.getSession().setAttribute("reportInfo", reportTO);
			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			//let the pdfManager know that the report should be saved in the request during report creation
			aRequest.setAttribute("isPdfSaveNeeded", "true");
	
			if ( reqEvent.isSpanishText() )
			{
				reqEvent.setReportName("REPORTING::PARENTAL_STATEMENT_REPORT_ES");
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.PARENTAL_STATEMENT_REPORT_ES);
			}
			else
			{
				reqEvent.setReportName("REPORTING::PARENTAL_STATEMENT_REPORT_EN");
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.PARENTAL_STATEMENT_REPORT_EN);
			}
			reqEvent.addDataObject(reportTO);
			byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
			if( pdfDocument == null || pdfDocument.length < 1 )
			{
				sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
				return aMapping.findForward(UIConstants.FAILURE);
			}
			IReport report  = PDFReporting.getInstance();
			IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
			ReportResponseEvent respEvent = new ReportResponseEvent();
			respEvent.setContent(pdfDocument);
			respEvent.setContentType(report.getContentType());
			respEvent.setFileName(report.getTemplate());	
			
			InterviewDocument document = new InterviewDocument();
			document.setDocument( respEvent.getContent() );
			document.setCasefileId( reqEvent.getCasefileId() );
			document.setDocumentTypeCodeId( "PWS" );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 
			
			dispatch.postEvent(respEvent);
		}
		// no need to forward since response has already been committed.
		return null;
	}

	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward generateUJACDocument(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		CreateParentalStatementReportEvent reqEvent = new CreateParentalStatementReportEvent();

		reqEvent.setCasefileId(form.getCasefileId());
		reqEvent.setSpanishText(form.isSpanishText());

		// Add record in activity table
		UIJuvenileHelper.createActivity(form.getCasefileId(),
				ActivityConstants.PARENTAL_WRITTEN_STATEMENT_REVIEWED, "");

		CompositeResponse compResponse = postRequestEvent(reqEvent);
		if( compResponse == null )
		{
			sendToErrorPage(aRequest, "error.common");
		}

		ReportResponseEvent aRespEvent = (ReportResponseEvent)
				MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);

		String fileName = aRespEvent.getFileName().substring(
				aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf";

		try
		{
			setPrintContentResp(aResponse, compResponse, fileName, UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			e.printStackTrace();
		}

		// no need to forward since response has already been committed.
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping,
	 * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return(aMapping.findForward(UIConstants.CANCEL));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping,
	 * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward view(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		String interviewId = form.getCurrentInterview().getInterviewId();
		String forward = UIConstants.VIEW_SUCCESS;

		GetInterviewDetailEvent event = new GetInterviewDetailEvent();
		event.setInterviewId(interviewId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		Collection interviews = MessageUtil.compositeToCollection(
				(CompositeResponse)dispatch.getReply(),
				InterviewDetailResponseEvent.class);

		Iterator iter = interviews.iterator();
		InterviewDetailResponseEvent detail = null ;
		if( iter.hasNext() )
		{
			detail = (InterviewDetailResponseEvent)iter.next() ;
			form.getCurrentInterview().populateInterviewData(detail);
			/* search for and copy the count over */ 
		}
		else
		{ // collection is empty
			sendToErrorPage(aRequest, "error.generic", "Interview Details not available");
			return( aMapping.findForward( forward ) ) ;
		}

		// Set address status for user-entered addresses.
		if( detail.getJuvLocUnitId() == null )
		{
			form.setAddressStatus(detail.getAddress().getAddressStatus());
		}

		// if status of interview is incomplete, it is updateable
		if( !detail.getInterviewStatusCd().equals(
				InterviewConstants.INTERVIEW_STATUS_COMPLETE) )
		{
			// Interview is only update-able in casefile search
			String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, false, true);
			String casefileNum = UIJuvenileCaseworkHelper.getCasefileNumber(aRequest, false, true);
			UIJuvenileInterviewInfoHelper.setInterviewCreationData(form, juvenileNum, casefileNum);

			form.setAction(UIConstants.UPDATE);
			forward = UIConstants.UPDATE_SUCCESS;
			form.setAuthorized(true);
			// Since previous action has already summary information of
			// interviews with the task count in it, we should just iterate
			// and tell the JSP to show the Interview Checklist button.
			// The actual Checklist will be queried when the user press the button.
			form.getCurrentInterview().setInterviewTasks(null);
			Collection<JuvenileInterview> interviewList = form.getAllInterviews();
			if( interviewList != null )
			{
				for( JuvenileInterview summaryInfo : interviewList )
				{
					if( summaryInfo.getInterviewId().equals(interviewId) )
					{
						if( summaryInfo.getTaskCount() > 0 )
						{
							List listIndicatingTask = new ArrayList();
							listIndicatingTask.add(new InterviewTaskResponseEvent());
							form.getCurrentInterview().setInterviewTasks(listIndicatingTask);
							form.getCurrentInterview().setCalEventId(summaryInfo.getCalEventId());
							break ;
						}
					}
				} // foreach
			}
			//<KISHORE>JIMS200059211 : MJCW: Document attendance for all int.types(UI)-KK
			setAttendanceInfo(form);
		}

		if( detail.getInterviewStatusCd().equals( InterviewConstants.INTERVIEW_STATUS_COMPLETE) )
		{
			form.setAuthorized(true);
			 form.setAction(UIConstants.UPDATE);    	//Bug #67710
			//<KISHORE>JIMS200059211 : MJCW: Document attendance for all int.types(UI)-KK
			setAttendanceInfo(form);
		}
		GetServiceAndAttendanceEvent getServiceEvent = (GetServiceAndAttendanceEvent) EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETSERVICEANDATTENDANCE);
		JuvenileInterview ji =  form.getCurrentInterview();
		
		if (ji.getCalEventId() == null || ji.getCalEventId().equals("")) {
			ActionMessage myError=new ActionMessage("error.casefileUnavailable",ji.getCasefileId());
			ArrayList coll=new ArrayList();
			coll.add(myError);
			sendToErrorPage(aRequest,coll);
			return aMapping.findForward(UIConstants.CANCEL);
		} 
		
		getServiceEvent.setCalEventId(ji.getCalEventId());
		CompositeResponse resp = MessageUtil.postRequest(getServiceEvent);		
		List eventsList = MessageUtil.compositeToList(resp, InterviewServiceAndAttendanceResponseEvent.class);
		Iterator<InterviewServiceAndAttendanceResponseEvent> events = eventsList.iterator();
		while( events.hasNext() )
		{
			InterviewServiceAndAttendanceResponseEvent attendanceEvent = events.next();
			if(attendanceEvent != null)
			{
				ji.setProgressReport(attendanceEvent.getProgressNotes());
				ji.setUserComments(attendanceEvent.getEventComments());
				ji.setAttendanceStatusCd(attendanceEvent.getAttendanceCode());
				ji.setAttendanceStatusDescription(CodeHelper.getCodeDescription("SERVEVENT_ATTENDANCE_STATUS", attendanceEvent.getAttendanceCode()));
			}
		}

		
		/* 22apr2009 - mjt - onhold for future use
		 * however, if this is still commented out 
		 * by the end may 2009, remove it all
		 *       
 * 		CreateInterviewTaskListEvent event = new CreateInterviewTaskListEvent();
		event.setInterviewId( form.getCurrentInterview().getInterviewId());
			CompositeResponse response = postRequestEvent(event);
			ErrorResponseEvent error = (ErrorResponseEvent) 
					MessageUtil.filterComposite(response, ErrorResponseEvent.class);
			if(error != null)
			{
				sendToErrorPage(aRequest, error.getMessage());
				return aMapping.findForward(UIConstants.FAILURE);
			}
			
			Collection tasks = MessageUtil.compositeToCollection( response, InterviewTaskResponseEvent.class );
			Collections.sort((List)tasks);
			form.getCurrentInterview().setInterviewTasks(tasks);
*/		

		form.setStatus(UIConstants.VIEW);
		aRequest.setAttribute("action", "view");

		return aMapping.findForward(forward);
	}

	/**
	 * This action will decide whether to forward to update screen, or if the
	 * checklist has been generated, it will forward to checklist page.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;

		GetInterviewTaskListEvent getTaskEvent = new GetInterviewTaskListEvent();
		getTaskEvent.setInterviewId(form.getCurrentInterview().getInterviewId());
		CompositeResponse compResp = postRequestEvent(getTaskEvent);

		Collection tasks = (Collection)MessageUtil.compositeToCollection(compResp,
				InterviewTaskResponseEvent.class);
		if( tasks.size() > 0 )
		{
			Collections.sort((List)tasks);
			form.getCurrentInterview().setInterviewTasks(tasks);
			// forward to task page.
			return aMapping.findForward("taskList");
		}

		return( view(aMapping, aForm, aRequest, aResponse) );
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addInterview(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, false, true);
		String casefileNum = UIJuvenileCaseworkHelper.getCasefileNumber(aRequest, false, true);

		UIJuvenileInterviewInfoHelper.setInterviewCreationData(form, juvenileNum, casefileNum);
		form.setCurrentInterview( new JuvenileInterview(
				form.getPersonsInterviewedList(), form.getInterviewLocationList()));

		JuvenilePhotoForm myPhotoForm = UIJuvenileHelper.getJuvPhotoForm(aRequest, true);
		myPhotoForm.setJuvenileNumber(form.getJuvenileNum());

		HttpSession session = aRequest.getSession();
		session.setAttribute("juvenilePhotoForm", myPhotoForm);

		form.setAction(UIConstants.CREATE);

		//<KISHORE>JIMS200059211 : MJCW: Document attendance for all int.types(UI)-KK
		setAttendanceInfo(form);
		
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}

	//<KISHORE>JIMS200059211 : MJCW: Document attendance for all int.types(UI)-KK
	private void setAttendanceInfo(JuvenileInterviewForm form) {
		Collection names = UISupervisionCalendarHelper.getJuvenileContacts(form.getJuvenileNum());
		form.setContactNames(names);
		if(UIConstants.CREATE.equalsIgnoreCase(form.getAction()) 
				|| form.getAction().equals("record")){
			form.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED);
			form.setAttendanceStatus(CodeHelper.getCode(
					PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS,PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED).getDescription());
			form.setAddlAttendees(UIConstants.EMPTY_STRING);
			form.setSelectedAttendeeNamesAsString(UIConstants.EMPTY_STRING);
			form.setSelectedAttendeeNames(new String[0]);
			form.setSelectedNamesList(new ArrayList());
		}else if(UIConstants.UPDATE.equalsIgnoreCase(form.getAction())){
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			// We need to get the serviceEventId and then get it's attendance
			String serviceEventId = null;
			serviceEventId = getServiceEventId(form.getCurrentInterview().getCalEventId());
			
			if(StringUtils.isNotEmpty(serviceEventId))
			{
				form.setServiceEventId(serviceEventId);
				GetJuvenileAttendanceEvent getAttendanceEvent = (GetJuvenileAttendanceEvent)
				EventFactory.getInstance(ServiceEventControllerServiceNames.GETJUVENILEATTENDANCE);
				getAttendanceEvent.setJuvenileId(form.getJuvenileNum());
				getAttendanceEvent.setServiceEventId(serviceEventId);
				dispatch.postEvent(getAttendanceEvent);		
				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);
				Collection<ServiceEventAttendanceResponseEvent> attendanceEvents =
					MessageUtil.compositeToCollection(compositeResponse, ServiceEventAttendanceResponseEvent.class);
				if(!attendanceEvents.isEmpty() )
				{
					for( ServiceEventAttendanceResponseEvent attendanceResponseEvent : attendanceEvents )
					{
						form.setAttendanceStatusCd(attendanceResponseEvent.getAttendanceStatusCd());
						form.setAttendanceStatus(attendanceResponseEvent.getAttendanceStatusDescription());
						form.setAddlAttendees(attendanceResponseEvent.getAddlAttendees());
						form.setSelectedNamesList(attendanceResponseEvent.getAddlAttendeeNames());
						String attendeeNamesAsStr = UIConstants.EMPTY_STRING;
						if(attendanceResponseEvent.getAddlAttendeeNames() != null){
							int count = attendanceResponseEvent.getAddlAttendeeNames().size();
							String[] selectedAttendeeNames = new String[count];
							count = 0;
							for(Object obj:attendanceResponseEvent.getAddlAttendeeNames()){
								AttendeeNameResponseEvent attendee = (AttendeeNameResponseEvent)obj;
								attendeeNamesAsStr += attendee.getFormattedName() + ";";
								selectedAttendeeNames[count++] = attendee.getFormattedName();
							}
							form.setSelectedAttendeeNamesAsString(attendeeNamesAsStr);
							form.setSelectedAttendeeNames(selectedAttendeeNames);
						}
					}
				}
				ArrayList attendanceStatusList = new ArrayList();
				attendanceStatusList.add( CodeHelper.getCode(
						PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS,PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED));
				attendanceStatusList.add( CodeHelper.getCode(
						PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS,PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED));
				attendanceStatusList.add( CodeHelper.getCode(
						PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS,PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT));
				form.setAttendanceStatusList(attendanceStatusList);
			}
		}
	}

	private String getServiceEventId(String calEventId) {
		String serviceEventId = "";
		GetServiceAndAttendanceEvent getServiceEvent = (GetServiceAndAttendanceEvent) EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETSERVICEANDATTENDANCE);
		getServiceEvent.setCalEventId(calEventId);
		CompositeResponse resp = MessageUtil.postRequest(getServiceEvent);		
		List eventsList = MessageUtil.compositeToList(resp, InterviewServiceAndAttendanceResponseEvent.class);
		
		Iterator<InterviewServiceAndAttendanceResponseEvent> events = eventsList.iterator();
		while(events.hasNext()){
			InterviewServiceAndAttendanceResponseEvent event = events.next();
			serviceEventId = event.getServiceEventId();
		}
		return serviceEventId;
	}
	

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward recordReceived(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;		
		String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, false, true);
		String casefileNum = UIJuvenileCaseworkHelper.getCasefileNumber(aRequest, false, true);

		UIJuvenileInterviewInfoHelper.setInterviewCreationData(form, juvenileNum, casefileNum);
		form.setCurrentInterview( new JuvenileInterview(
				form.getPersonsInterviewedList(), form.getInterviewLocationList()));
		form.getCurrentInterview().setInterviewTypeId(InterviewConstants.INTERVIEW_TYPE_ID_DR);    //default interview type to Document Received
		//form.getCurrentInterview().setJuvLocUnitId("37");   //default interview location unit to Data Control
		Iterator locationIter = form.getInterviewLocationList().iterator();
		while(locationIter.hasNext())
		{
			LocationResponseEvent locResp = (LocationResponseEvent)locationIter.next();
			if(locResp.getLocationUnitName() != null && locResp.getLocationUnitName().toUpperCase().equals(InterviewConstants.INTERVIEW_LOACATION_DATACONTROL))
				form.getCurrentInterview().setJuvLocUnitId(locResp.getJuvLocationUnitId());
		}
		form.getCurrentInterview().setInterviewDate(DateUtil.getCurrentDate());		
		form.getCurrentInterview().setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED);
		String [] personsInterviewed = new String[form.getPersonsInterviewedList().size()];
		Name newName= new Name(form.getJuvenileName().getFirstName(), form.getJuvenileName().getMiddleName(), form.getJuvenileName().getLastName());
		personsInterviewed[0]=newName.getFormattedName();		
		Iterator iter = form.getPersonsInterviewedList().iterator();
		PersonResponsible persResponsible = new PersonResponsible();
		Collection persons = new ArrayList();
		while(iter.hasNext())
		{
			InterviewPersonResponseEvent person = (InterviewPersonResponseEvent)iter.next();
			String tempName=person.getFormattedName();
			persResponsible.setName(tempName.trim() +" ("+person.getRelationship()+")");
			persResponsible.setType(person.getRelationship());
			persResponsible.setFirstName(person.getFirstName());
			persResponsible.setMiddleName(person.getMiddleName());
			persResponsible.setLastName(person.getLastName());
			if(tempName.trim().equals(personsInterviewed[0]))
			{
				personsInterviewed[0]=tempName.trim()+" ("+person.getRelationship()+")";
			}
			persons.add(persResponsible);
			persResponsible = new PersonResponsible();
		}
		form.getCurrentInterview().setPersonsInterviewed(personsInterviewed);
		Collections.sort((List)persons);
		form.setPersonsInterviewedList(persons);
		JuvenilePhotoForm myPhotoForm = UIJuvenileHelper.getJuvPhotoForm(aRequest, true);
		myPhotoForm.setJuvenileNumber(form.getJuvenileNum());

		HttpSession session = aRequest.getSession();
		session.setAttribute("juvenilePhotoForm", myPhotoForm);

		form.setAction("record");		
		setAttendanceInfo(form);
		
			return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "link");
		keyMap.put("button.view", "view");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.addUnscheduledInterview", "addInterview");
		keyMap.put("button.printParentalStatement", "printParentalStatement");
		keyMap.put("button.generateDocument", "generateDocument");
		keyMap.put("button.recordsReceived", "recordReceived");
	}
	
	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
   {
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
}