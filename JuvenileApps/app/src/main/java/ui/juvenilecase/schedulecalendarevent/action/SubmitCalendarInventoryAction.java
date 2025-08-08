//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\schedulecalendarevent\\action\\DisplayCalendarEventListAction.java

package ui.juvenilecase.schedulecalendarevent.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetJuvLocationUnitDetailsEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.InterviewAppointmentLetterDataEvent;
import messaging.calendar.to.EventAppointmentLetterTO;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.to.Address;
import messaging.contact.to.PhoneNumberBean;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.report.GenericPrintRequestEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileFamilyControllerServiceNames;
import naming.LocationControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.JournalForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class SubmitCalendarInventoryAction extends JIMSBaseAction
{
	/**
	 * @roseuid 4576E78400F1
	 */
	public SubmitCalendarInventoryAction()
	{
	}

	/*
	 * removed next() and finish() functions because they've been commented out
	 * since 5/20/2007 - mjt
	 */

	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward returnToCalendar( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.RETURN_SUCCESS ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward generateEnglishAppointmentLetter( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return generateAppointmentLetter( aMapping, aForm, 
				aRequest, aResponse, "REPORTING::INTERVIEW_APPOINTMENT_LETTER" ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward generateSpanishAppointmentLetter( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return generateAppointmentLetter( aMapping, aForm, 
				aRequest, aResponse, "REPORTING::INTERVIEW_APPOINTMENT_LETTER_SPANISH" ) ;

	}
	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward englishGenerateBFOAppointmentLetter( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{

		//return generateAppointmentLetter( aMapping, aForm, 
		//		aRequest, aResponse, "REPORTING::INTERVIEW_APPOINTMENT_LETTER" ) ;
		
		EventAppointmentLetterTO eventAppointmentLetterTO = new EventAppointmentLetterTO();
		//get Data
		generateBFOAppointmentLetter(aMapping, aForm, aRequest, aResponse, eventAppointmentLetterTO);
		
		aRequest.getSession().setAttribute("eventAppointmentLetterTO", eventAppointmentLetterTO);
		// generate report
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.INTERVIEW_APPOINTMENT_LETTER_EN);
		
		
	
		return null;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return 
	 */
	public ActionForward spanishGenerateBFOAppointmentLetter( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		//return generateAppointmentLetter( aMapping, aForm, 
		//		aRequest, aResponse, "REPORTING::INTERVIEW_APPOINTMENT_LETTER_SPANISH" ) ;
		
		EventAppointmentLetterTO eventAppointmentLetterTO = new EventAppointmentLetterTO();
		//get Data
		generateBFOAppointmentLetter(aMapping, aForm, aRequest, aResponse, eventAppointmentLetterTO);
		
		aRequest.getSession().setAttribute("eventAppointmentLetterTO", eventAppointmentLetterTO);
		// generate report
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.INTERVIEW_APPOINTMENT_LETTER_ES);

		return null;

	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @param eventAppointmentLetterTO
	 * @return 
	 */
	private void generateBFOAppointmentLetter (ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse, EventAppointmentLetterTO eventAppointmentLetterTO)
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		CalendarServiceEventResponseEvent cser = form.getCurrentEvent() ;
		
		eventAppointmentLetterTO.setCurrentDate( DateUtil.dateToString( new Date(), "MM/dd/yyyy" ) ) ;
		
		eventAppointmentLetterTO.setEventDate( DateUtil.dateToString( cser.getEventDate(), "EEEE, MM/dd/yy" ) ) ;
		eventAppointmentLetterTO.setEventTime( DateUtil.dateToString( cser.getEventDate(), "h:mm a" ) ) ;
		eventAppointmentLetterTO.setEventComments( cser.getEventComments() ) ;
		JuvenileInterview interview = form.getCurrentInterview() ;

		Collection displayList = new ArrayList() ;
		String [ ] recordsInventoryList = interview.getRecordsInventoryIds() ;

		// It is not required for the user to select a document attendance before
		// he/she can generate an appointment letter
		if( recordsInventoryList != null && recordsInventoryList.length > 0 )
		{
			for( int i = 0; i < recordsInventoryList.length; i++ )
			{
				String displayStr = CodeHelper.getCodeDescriptionByCode( 
						form.getRecordsInventoryList(), recordsInventoryList[ i ] ) ;
				
				if( displayStr.equals( "OTHER DOCUMENTS" ) || 
						displayStr.equals( "OTHER COURT ORDERS" ) )
				{
					StringBuffer other = new StringBuffer() ;
					other.append( displayStr ) ;
					other.append( " ( " ) ;
					other.append( interview.getOtherInventoryRecords() ) ;
					other.append( " ) " ) ;
					displayList.add( other.toString() ) ;
				}
				else
				{
					displayList.add( displayStr ) ;
				}
			}
			Collections.sort( (List)displayList ) ;
		}

		interview.setRecordsInventoryDisplay( displayList ) ;
		eventAppointmentLetterTO.setInventoryDocuments( (List)displayList ) ;

		populateOfficerInfoTO( eventAppointmentLetterTO, cser.getLocationId(), cser.getLocationUnitId() ) ;

		String juvenileNum = form.getJuvenileNum() ;
		GetFamilyConstellationsEvent getFamilyEvent = (GetFamilyConstellationsEvent)
				EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS ) ;
		getFamilyEvent.setJuvenileNum( juvenileNum ) ;

		CompositeResponse getFamilyResponse = postRequestEvent( getFamilyEvent ) ;

		Collection<FamilyConstellationMemberListResponseEvent> memberList = 
				MessageUtil.compositeToCollection( getFamilyResponse, FamilyConstellationMemberListResponseEvent.class ) ;
		boolean hasFamily = false ;
		boolean hasGuardian = false ;
		if( memberList != null )
		{
			for( FamilyConstellationMemberListResponseEvent fme: memberList )
			{
				if( fme.isGuardian() && fme.isInHomeStatus() )
				{
					hasGuardian = true ;
					GetFamilyMemberAddressEvent memberAddressEvent = (GetFamilyMemberAddressEvent)
							EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS ) ;
					memberAddressEvent.setMemberNum( fme.getMemberNum() ) ;
					
					CompositeResponse getMemberAddressResponse = postRequestEvent( memberAddressEvent ) ;
					Collection<AddressResponseEvent> addressResponses = 
							MessageUtil.compositeToCollection( getMemberAddressResponse, AddressResponseEvent.class ) ;

					if( addressResponses != null )
					{
						// Sorting will put the latest address entered at the top of the list.
						Collections.sort( (List)addressResponses ) ;

						for( AddressResponseEvent addre : addressResponses )
						{
							if( addre.getAddressTypeId().equals( "RES" ) )
							{
								hasFamily = true ;

								eventAppointmentLetterTO.setMemberRelationship( fme.getRelationToJuvenile() ) ;
								eventAppointmentLetterTO.setGuardianFirstName( fme.getFirstName() ) ;
								eventAppointmentLetterTO.setGuardianLastName( fme.getLastName() ) ;
								eventAppointmentLetterTO.setGuardianMiddleName( fme.getMiddleName() ) ;

								Address guardianAddress = new Address() ;
								guardianAddress.setStreetNum( addre.getStreetNum() ) ;
								guardianAddress.setStreetName( addre.getStreetName() ) ;
								guardianAddress.setStreetTypeCode( addre.getStreetTypeCode() ) ;
								guardianAddress.setStreetType( addre.getStreetType() ) ;
								guardianAddress.setAptNum( addre.getAptNum() ) ;
								guardianAddress.setCity( addre.getCity() ) ;
								guardianAddress.setState( addre.getState() ) ;
								guardianAddress.setStateCode( addre.getStateCode() ) ;
								guardianAddress.setZipCode( addre.getZipCode() ) ;
								guardianAddress.setAdditionalZipCode( addre.getAdditionalZipCode() ) ;
								guardianAddress.setStreetNumSuffix(addre.getStreetNumSuffix());
								guardianAddress.setStreetNumSuffixCode( addre.getStreetSuffixCode());
								eventAppointmentLetterTO.setGuardianAddress( guardianAddress ) ;
								break ;
							}
						} // for
					}
					break ;
				}
			} // outer for
		}

		if( !hasFamily )
		{
			if( !hasGuardian )
			{
				this.saveErrors( aRequest, "error.noInhomeGuardian" ) ;
			}
			else
			{
				this.saveErrors( aRequest, "error.noAddressesToDisplay" ) ;
			}
		
			aMapping.findForward( UIConstants.NEXT ) ;
			return;
		}

		// Get casefileId from juvenileCasefileForm that's in session
		JuvenileCasefileForm juvCasefileForm = (JuvenileCasefileForm)
				aRequest.getSession().getAttribute( "juvenileCasefileForm" ) ;

		Name juvenileName = juvCasefileForm.getJuvenileName() ;
		if( juvenileName != null )
		{
			eventAppointmentLetterTO.setJuvenileLastName( juvenileName.getLastName() ) ;
			eventAppointmentLetterTO.setJuvenileFirstName( juvenileName.getFirstName() ) ;
			eventAppointmentLetterTO.setJuvenileMiddleName( juvenileName.getMiddleName() ) ;
		}

		eventAppointmentLetterTO.setServiceLocationName( cser.getServiceLocationName() ) ;

		if( cser.getLocationUnitId() != null )
		{
			GetJuvLocationUnitDetailsEvent reqEvent = new GetJuvLocationUnitDetailsEvent() ;
			reqEvent.setJuvLocUnitId( cser.getLocationUnitId() ) ;
			CompositeResponse getLocUnitResponse = postRequestEvent( reqEvent ) ;
			LocationResponseEvent respEvent = (LocationResponseEvent)
					MessageUtil.filterComposite( getLocUnitResponse, LocationResponseEvent.class ) ;

			PhoneNumberBean formattedPhone = new PhoneNumberBean( respEvent.getPhoneNumber() ) ;
			eventAppointmentLetterTO.setInterviewLocationPhone( formattedPhone.getFormattedPhoneNumber() ) ;
		}
	}
	
	/*
	 * @param interviewData
	 * @param aLocationId
	 * @param aLocationUnitId
	 */
	private void populateOfficerInfoTO( EventAppointmentLetterTO eventAppointmentLetterTO, 
			String aLocationId, String aLocationUnitId )
	{
		// Getting officer info, officer's manager, and officer's location/location unit
		IUserInfo loggedonUser = SecurityUIHelper.getUser() ;
		if( loggedonUser != null )
		{
			eventAppointmentLetterTO.setOfficerLastName( loggedonUser.getLastName() ) ;
			eventAppointmentLetterTO.setOfficerFirstName( loggedonUser.getFirstName() ) ;
			eventAppointmentLetterTO.setOfficerMiddleName( loggedonUser.getMiddleName() ) ;
		}

		OfficerProfileResponseEvent officer = UIUserFormHelper.getUserOfficerProfile( SecurityUIHelper.getLogonId() ) ;

		CompositeResponse response = null ;
		OfficerProfileResponseEvent officerProfileResponse = null ;
		if( officer != null )
		{
			GetOfficerProfileEvent gofe = (GetOfficerProfileEvent)
					EventFactory.getInstance( OfficerProfileControllerServiceNames.GETOFFICERPROFILE ) ;
			gofe.setOfficerProfileId( officer.getOfficerId() ) ;
			response = postRequestEvent( gofe ) ;

			officerProfileResponse = (OfficerProfileResponseEvent)
					MessageUtil.filterComposite( response, OfficerProfileResponseEvent.class ) ;
		}
		eventAppointmentLetterTO.setOfficerPhone( "" ) ;
		eventAppointmentLetterTO.setManagerFirstName( "" ) ;
		eventAppointmentLetterTO.setManagerMiddleName( "" ) ;
		eventAppointmentLetterTO.setManagerLastName( "" ) ;
		
		if( officerProfileResponse != null )
		{
			StringBuffer sbPhone = new StringBuffer() ;
			if( notNullNotEmptyString( officerProfileResponse.getWorkPhone() ) )
			{
				PhoneNumber phone = new PhoneNumber( officerProfileResponse.getWorkPhone() ) ;
				sbPhone.append( phone.getFormattedPhoneNumber() ) ;
				if( notNullNotEmptyString( officerProfileResponse.getExtn() ) )
				{
					sbPhone.append( " Ext. " ) ;
					sbPhone.append( officerProfileResponse.getExtn() ) ;
				}
				eventAppointmentLetterTO.setOfficerPhone( sbPhone.toString() ) ;
			}

			eventAppointmentLetterTO.setManagerFirstName( officerProfileResponse.getManagerFirstName() ) ;
			eventAppointmentLetterTO.setManagerMiddleName( officerProfileResponse.getManagerMiddleName() ) ;
			eventAppointmentLetterTO.setManagerLastName( officerProfileResponse.getManagerLastName() ) ;
		}

		Address locationAddress = new Address() ;
		eventAppointmentLetterTO.setLocationUnitAddress( locationAddress ) ;
		String locationId = aLocationId ;
		if( notNullNotEmptyString( locationId ) )
		{
			GetJuvenileLocationEvent gle = (GetJuvenileLocationEvent)EventFactory.getInstance( LocationControllerServiceNames.GETJUVENILELOCATION ) ;
			gle.setLocationId( locationId ) ;
			response = MessageUtil.postRequest( gle ) ;
			LocationResponseEvent lre = (LocationResponseEvent)
					MessageUtil.filterComposite( response, LocationResponseEvent.class ) ;
			AddressResponseEvent addressResponseEvent = lre.getLocationAddress() ;

			Collection<LocationResponseEvent> juvLocUnits = lre.getLocationUnits() ;
			if( notNullNotEmptyString( aLocationUnitId ) ) 
			{
				if( juvLocUnits != null &&  ! juvLocUnits.isEmpty() )
				{
					for( LocationResponseEvent locResp: juvLocUnits)
					{
						if( aLocationUnitId.equalsIgnoreCase( locResp.getJuvLocationUnitId() ) )
						{
							eventAppointmentLetterTO.setLocationUnitName( locResp.getLocationUnitName() ) ;
							PhoneNumberBean phone = new PhoneNumberBean( locResp.getJuvLocationUnitPhoneNumber() ) ;
							eventAppointmentLetterTO.setManagerPhone( phone.getFormattedPhoneNumber() ) ;
							if( locResp.getLocationAddress() != null && 
									notNullNotEmptyString( locResp.getLocationAddress().getStreetName() ) )
							{
								addressResponseEvent = locResp.getLocationAddress() ;
							}
							break ;
						}
					}
				}
			}

			locationAddress.setStreetNum( addressResponseEvent.getStreetNum() ) ;
			locationAddress.setStreetName( addressResponseEvent.getStreetName() ) ;
			locationAddress.setStreetType( addressResponseEvent.getStreetType() ) ;
			locationAddress.setAptNum( addressResponseEvent.getAptNum() ) ;
			locationAddress.setCity( addressResponseEvent.getCity() ) ;
			locationAddress.setState( addressResponseEvent.getState() ) ;
			locationAddress.setZipCode( addressResponseEvent.getZipCode() ) ;
			locationAddress.setAdditionalZipCode( addressResponseEvent.getAdditionalZipCode() ) ;
		}
	}

	
	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @param reportName
	 * @return
	 */
	private ActionForward generateAppointmentLetter( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse, String reportName )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		CalendarServiceEventResponseEvent cser = form.getCurrentEvent() ;

		InterviewAppointmentLetterDataEvent interviewData = new InterviewAppointmentLetterDataEvent() ;
		interviewData.setCurrentDate( DateUtil.dateToString( new Date(), "MM/dd/yyyy" ) ) ;
		
		interviewData.setEventDate( DateUtil.dateToString( cser.getEventDate(), "EEEE, MM/dd/yy" ) ) ;
		interviewData.setEventTime( DateUtil.dateToString( cser.getEventDate(), "h:mm a" ) ) ;
		interviewData.setEventComments( cser.getEventComments() ) ;
		JuvenileInterview interview = form.getCurrentInterview() ;

		Collection displayList = new ArrayList() ;
		String [ ] recordsInventoryList = interview.getRecordsInventoryIds() ;

		// It is not required for the user to select a document attendance before
		// he/she can generate an appointment letter
		if( recordsInventoryList != null && recordsInventoryList.length > 0 )
		{
			for( int i = 0; i < recordsInventoryList.length; i++ )
			{
				String displayStr = CodeHelper.getCodeDescriptionByCode( 
						form.getRecordsInventoryList(), recordsInventoryList[ i ] ) ;
				
				if( displayStr.equals( "OTHER DOCUMENTS" ) || 
						displayStr.equals( "OTHER COURT ORDERS" ) )
				{
					StringBuffer other = new StringBuffer() ;
					other.append( displayStr ) ;
					other.append( " ( " ) ;
					other.append( interview.getOtherInventoryRecords() ) ;
					other.append( " ) " ) ;
					displayList.add( other.toString() ) ;
				}
				else
				{
					displayList.add( displayStr ) ;
				}
			}
			Collections.sort( (List)displayList ) ;
		}

		interview.setRecordsInventoryDisplay( displayList ) ;
		interviewData.setInventoryDocuments( (List)displayList ) ;

		populateOfficerInfo( interviewData, cser.getLocationId(), cser.getLocationUnitId() ) ;

		String juvenileNum = form.getJuvenileNum() ;
		GetFamilyConstellationsEvent getFamilyEvent = (GetFamilyConstellationsEvent)
				EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS ) ;
		getFamilyEvent.setJuvenileNum( juvenileNum ) ;

		CompositeResponse getFamilyResponse = postRequestEvent( getFamilyEvent ) ;

		Collection<FamilyConstellationMemberListResponseEvent> memberList = 
				MessageUtil.compositeToCollection( getFamilyResponse, FamilyConstellationMemberListResponseEvent.class ) ;
		boolean hasFamily = false ;
		boolean hasGuardian = false ;
		if( memberList != null )
		{
			for( FamilyConstellationMemberListResponseEvent fme: memberList )
			{
				if( fme.isGuardian() && fme.isInHomeStatus() )
				{
					hasGuardian = true ;
					GetFamilyMemberAddressEvent memberAddressEvent = (GetFamilyMemberAddressEvent)
							EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS ) ;
					memberAddressEvent.setMemberNum( fme.getMemberNum() ) ;
					
					CompositeResponse getMemberAddressResponse = postRequestEvent( memberAddressEvent ) ;
					Collection<AddressResponseEvent> addressResponses = 
							MessageUtil.compositeToCollection( getMemberAddressResponse, AddressResponseEvent.class ) ;

					if( addressResponses != null )
					{
						// Sorting will put the latest address entered at the top of the list.
						Collections.sort( (List)addressResponses ) ;

						for( AddressResponseEvent addre : addressResponses )
						{
							if( addre.getAddressTypeId().equals( "RES" ) )
							{
								hasFamily = true ;

								interviewData.setMemberRelationship( fme.getRelationToJuvenile() ) ;
								interviewData.setGuardianFirstName( fme.getFirstName() ) ;
								interviewData.setGuardianLastName( fme.getLastName() ) ;
								interviewData.setGuardianMiddleName( fme.getMiddleName() ) ;

								Address guardianAddress = new Address() ;
								guardianAddress.setStreetNum( addre.getStreetNum() ) ;
								guardianAddress.setStreetName( addre.getStreetName() ) ;
								guardianAddress.setStreetTypeCode( addre.getStreetTypeCode() ) ;
								guardianAddress.setStreetType( addre.getStreetType() ) ;
								guardianAddress.setAptNum( addre.getAptNum() ) ;
								guardianAddress.setCity( addre.getCity() ) ;
								guardianAddress.setState( addre.getState() ) ;
								guardianAddress.setStateCode( addre.getStateCode() ) ;
								guardianAddress.setZipCode( addre.getZipCode() ) ;
								guardianAddress.setAdditionalZipCode( addre.getAdditionalZipCode() ) ;
								guardianAddress.setStreetNumSuffix(addre.getStreetNumSuffix());
								guardianAddress.setStreetNumSuffixCode( addre.getStreetSuffixCode());
								interviewData.setGuardianAddress( guardianAddress ) ;
								break ;
							}
						} // for
					}
					break ;
				}
			} // outer for
		}

		if( !hasFamily )
		{
			if( !hasGuardian )
			{
				this.saveErrors( aRequest, "error.noInhomeGuardian" ) ;
			}
			else
			{
				this.saveErrors( aRequest, "error.noAddressesToDisplay" ) ;
			}
			
			return aMapping.findForward( UIConstants.NEXT ) ;
		}

		// Get casefileId from juvenileCasefileForm that's in session
		JuvenileCasefileForm juvCasefileForm = (JuvenileCasefileForm)
				aRequest.getSession().getAttribute( "juvenileCasefileForm" ) ;

		Name juvenileName = juvCasefileForm.getJuvenileName() ;
		if( juvenileName != null )
		{
			interviewData.setJuvenileLastName( juvenileName.getLastName() ) ;
			interviewData.setJuvenileFirstName( juvenileName.getFirstName() ) ;
			interviewData.setJuvenileMiddleName( juvenileName.getMiddleName() ) ;
		}

		interviewData.setServiceLocationName( cser.getServiceLocationName() ) ;

		if( cser.getLocationUnitId() != null )
		{
			GetJuvLocationUnitDetailsEvent reqEvent = new GetJuvLocationUnitDetailsEvent() ;
			reqEvent.setJuvLocUnitId( cser.getLocationUnitId() ) ;
			CompositeResponse getLocUnitResponse = postRequestEvent( reqEvent ) ;
			LocationResponseEvent respEvent = (LocationResponseEvent)
					MessageUtil.filterComposite( getLocUnitResponse, LocationResponseEvent.class ) ;

			PhoneNumberBean formattedPhone = new PhoneNumberBean( respEvent.getPhoneNumber() ) ;
			interviewData.setInterviewLocationPhone( formattedPhone.getFormattedPhoneNumber() ) ;
		}

		// Adding record in activity table
		UIJuvenileHelper.createActivity( juvCasefileForm.getSupervisionNum(), ActivityConstants.INTERVIEW_APPOINTMENT_LETTER_GENERATED, "" ) ;

		GenericPrintRequestEvent apptLetterEvent = new GenericPrintRequestEvent() ;
		apptLetterEvent.addDataObject( interviewData ) ;
		apptLetterEvent.setReportName( reportName ) ;

		CompositeResponse compResponse = postRequestEvent( apptLetterEvent ) ;

		ReportResponseEvent aRespEvent = (ReportResponseEvent)MessageUtil.filterComposite( compResponse, ReportResponseEvent.class ) ;
		aResponse.setContentType( "application/x-file-download" ) ;
		aResponse.setHeader( "Content-disposition", "attachment; filename=" + aRespEvent.getFileName().substring( aRespEvent.getFileName().lastIndexOf( "/" ) + 1 ) + ".pdf" ) ;
		aResponse.setHeader( "Cache-Control", "max-age=" + 1200 ) ;
		aResponse.setContentLength( aRespEvent.getContent().length ) ;
		aResponse.resetBuffer() ;
		OutputStream os ;
		try
		{
			os = aResponse.getOutputStream() ;
			os.write( aRespEvent.getContent(), 0, aRespEvent.getContent().length ) ;
			os.flush() ;
			os.close() ;
		}
		catch( IOException e )
		{
			e.printStackTrace() ;
		}
		
		return null ;
	}

	/*
	 * @param interviewData
	 * @param aLocationId
	 * @param aLocationUnitId
	 */
	private void populateOfficerInfo( InterviewAppointmentLetterDataEvent interviewData, 
			String aLocationId, String aLocationUnitId )
	{
		// Getting officer info, officer's manager, and officer's location/location unit
		IUserInfo loggedonUser = SecurityUIHelper.getUser() ;
		if( loggedonUser != null )
		{
			interviewData.setOfficerLastName( loggedonUser.getLastName() ) ;
			interviewData.setOfficerFirstName( loggedonUser.getFirstName() ) ;
			interviewData.setOfficerMiddleName( loggedonUser.getMiddleName() ) ;
		}

		OfficerProfileResponseEvent officer = UIUserFormHelper.getUserOfficerProfile( SecurityUIHelper.getLogonId() ) ;

		CompositeResponse response = null ;
		OfficerProfileResponseEvent officerProfileResponse = null ;
		if( officer != null )
		{
			GetOfficerProfileEvent gofe = (GetOfficerProfileEvent)
					EventFactory.getInstance( OfficerProfileControllerServiceNames.GETOFFICERPROFILE ) ;
			gofe.setOfficerProfileId( officer.getOfficerId() ) ;
			response = postRequestEvent( gofe ) ;

			officerProfileResponse = (OfficerProfileResponseEvent)
					MessageUtil.filterComposite( response, OfficerProfileResponseEvent.class ) ;
		}
		interviewData.setOfficerPhone( "" ) ;
		interviewData.setManagerFirstName( "" ) ;
		interviewData.setManagerMiddleName( "" ) ;
		interviewData.setManagerLastName( "" ) ;
		
		if( officerProfileResponse != null )
		{
			StringBuffer sbPhone = new StringBuffer() ;
			if( notNullNotEmptyString( officerProfileResponse.getWorkPhone() ) )
			{
				PhoneNumber phone = new PhoneNumber( officerProfileResponse.getWorkPhone() ) ;
				sbPhone.append( phone.getFormattedPhoneNumber() ) ;
				if( notNullNotEmptyString( officerProfileResponse.getExtn() ) )
				{
					sbPhone.append( " Ext. " ) ;
					sbPhone.append( officerProfileResponse.getExtn() ) ;
				}
				interviewData.setOfficerPhone( sbPhone.toString() ) ;
			}

			interviewData.setManagerFirstName( officerProfileResponse.getManagerFirstName() ) ;
			interviewData.setManagerMiddleName( officerProfileResponse.getManagerMiddleName() ) ;
			interviewData.setManagerLastName( officerProfileResponse.getManagerLastName() ) ;
		}

		Address locationAddress = new Address() ;
		interviewData.setLocationUnitAddress( locationAddress ) ;
		String locationId = aLocationId ;
		if( notNullNotEmptyString( locationId ) )
		{
			GetJuvenileLocationEvent gle = (GetJuvenileLocationEvent)EventFactory.getInstance( LocationControllerServiceNames.GETJUVENILELOCATION ) ;
			gle.setLocationId( locationId ) ;
			response = MessageUtil.postRequest( gle ) ;
			LocationResponseEvent lre = (LocationResponseEvent)
					MessageUtil.filterComposite( response, LocationResponseEvent.class ) ;
			AddressResponseEvent addressResponseEvent = lre.getLocationAddress() ;

			Collection<LocationResponseEvent> juvLocUnits = lre.getLocationUnits() ;
			if( notNullNotEmptyString( aLocationUnitId ) ) 
			{
				if( juvLocUnits != null &&  ! juvLocUnits.isEmpty() )
				{
					for( LocationResponseEvent locResp: juvLocUnits)
					{
						if( aLocationUnitId.equalsIgnoreCase( locResp.getJuvLocationUnitId() ) )
						{
							interviewData.setLocationUnitName( locResp.getLocationUnitName() ) ;
							PhoneNumberBean phone = new PhoneNumberBean( locResp.getJuvLocationUnitPhoneNumber() ) ;
							interviewData.setManagerPhone( phone.getFormattedPhoneNumber() ) ;
							if( locResp.getLocationAddress() != null && 
									notNullNotEmptyString( locResp.getLocationAddress().getStreetName() ) )
							{
								addressResponseEvent = locResp.getLocationAddress() ;
							}
							break ;
						}
					}
				}
			}

			locationAddress.setStreetNum( addressResponseEvent.getStreetNum() ) ;
			locationAddress.setStreetName( addressResponseEvent.getStreetName() ) ;
			locationAddress.setStreetType( addressResponseEvent.getStreetType() ) ;
			locationAddress.setAptNum( addressResponseEvent.getAptNum() ) ;
			locationAddress.setCity( addressResponseEvent.getCity() ) ;
			locationAddress.setState( addressResponseEvent.getState() ) ;
			locationAddress.setZipCode( addressResponseEvent.getZipCode() ) ;
			locationAddress.setAdditionalZipCode( addressResponseEvent.getAdditionalZipCode() ) ;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.CANCEL ) ;
		return forward ;
	}

	/**
	 * @param aRequest
	 * @param errorkey
	 */
	private void saveErrors( HttpServletRequest aRequest, String errorKey )
	{
		ActionErrors errors = new ActionErrors() ;
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( errorKey, SecurityUIHelper.getLogonId() ) ) ;
		saveErrors( aRequest, errors ) ;
	}

	/* given a string, returns true if that string
	 * is not null and contains one or more characters
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null && str.trim().length() > 0 ) ;	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.generateAppointmentLetterEnglish", "generateEnglishAppointmentLetter" ) ;
		keyMap.put( "button.generateAppointmentLetterSpanish", "generateSpanishAppointmentLetter" ) ;
		keyMap.put( "button.generateBFOAppointmentLetterEnglish", "englishGenerateBFOAppointmentLetter" ) ;
		keyMap.put( "button.generateBFOAppointmentLetterSpanish", "spanishGenerateBFOAppointmentLetter" ) ;
		keyMap.put( "button.returnToCalendar", "returnToCalendar" ) ;
		//		

	}
}
