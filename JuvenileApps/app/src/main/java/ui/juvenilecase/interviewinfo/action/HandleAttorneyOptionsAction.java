package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.address.reply.AddressResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.family.GetFamilyMemberContactEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.interviewinfo.GetJuvenileBenefitsEvent;
import messaging.interviewinfo.GetNextJuvenileCourtDateEvent;
import messaging.interviewinfo.SaveInterviewDocumentEvent;
import messaging.interviewinfo.reply.InterviewReportResponseEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.interviewinfo.reply.JuvenileCourtDateEvent;
import messaging.interviewinfo.to.EmploymentHistoryTO;
import messaging.interviewinfo.to.FamilyInformationTO;
import messaging.juvenile.GetJuvenileJobsEvent;
import messaging.juvenile.reply.JJSReferralResponseEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberMartialStatusResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.referral.GetJuvenileCasefileReferralDetailsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileInterviewInfoHelper;
import ui.juvenilecase.action.DisplayFamilyConstellationDetailsAction;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.interviewinfo.form.FamilyMemberDetailsBean;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.interviewinfo.form.RequestAppointedAttorneyPrintBean;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author awidjaja
 * 
 */
public class HandleAttorneyOptionsAction extends JIMSBaseAction
{
	private final String contactAdmin = "Please contact your System Administrator with a description of this problem." ;
	
	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, 
	 * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, 
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/*
	 * (non-Javadoc)
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
	 * 
	 */
	public ActionForward requestAttorneyAppt(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;

		// casefileId is null when this action is loaded from
		//  a use case outside of conduct interview
		if( form.getCasefileId() == null )
		{
			UIJuvenileInterviewInfoHelper.populateInterviewForm(form, aRequest);
		}

		JuvenileFamilyForm.Constellation constellationDetails = 
				UIJuvenileInterviewInfoHelper.getJuvenileConstellationDetails(form.getJuvenileNum());

		if( constellationDetails != null )
		{
			form.setActiveConstellationNum( constellationDetails.getFamilyNumber() );
		}

		// Reuse existing action to get family financial info, and employment info
		// The structure of Guardian object is as follows
		// JuvenileFamilyForm.Guardian contains:
		// - Financial Info (expenses and assets)
		// - Collection employmentInfoList
		DisplayFamilyConstellationDetailsAction displayFamilyDetailsAction = new DisplayFamilyConstellationDetailsAction();

		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm)aRequest.getSession().getAttribute("juvenileFamilyForm");

		if( myFamForm == null )
		{
			myFamForm = new JuvenileFamilyForm();
		}

		myFamForm.setSelectedValue(form.getActiveConstellationNum());
		displayFamilyDetailsAction.execute(aMapping, myFamForm, aRequest, aResponse);
		aRequest.getSession().setAttribute("juvenileFamilyForm", myFamForm);
		
		((JuvenileInterviewForm)aForm).setAction("requestAttorneyAppointment");
		aRequest.setAttribute("state", "requestAttorneyAppointment");

		// select first available guardian's financial information, 
		// then store it in form
		// It is a requirement to have a financial record pre-selected
		boolean first = true;
		for( Iterator emplIter = myFamForm.getCurrentConstellation().getGuardiansList().iterator(); 
				emplIter.hasNext()  &&  first; /*empty*/ )
		{
			JuvenileFamilyForm.Guardian guardian = (JuvenileFamilyForm.Guardian)emplIter.next();
			if( first )
			{
				guardian.setFinancialInfoSelected(true);
				first = false;
			}
		}
		
		form.setGuardianEmploymentRecord(myFamForm.getCurrentConstellation().getGuardiansList());

		// Retrieve step-parent(s)/Influential Adult employment record, 
		// who is not guardian
		Collection stepParentEmploymentRecord = new ArrayList();
		for( Iterator iter = myFamForm.getCurrentConstellation().getMemberList().iterator(); 
				iter.hasNext(); /*empty*/ )
		{
			JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();
			
			if( memberMatchesCriteria( myMember ) )
			{	// get employment info record
				JuvenileFamilyForm.Guardian myGuardian = new JuvenileFamilyForm.Guardian();
				myGuardian.setMemberNumber(myMember.getMemberNumber());
				myGuardian.setName(myMember.getMemberName());

				UIJuvenileFamilyHelper.getEmploymentMemberInfo(myMember.getMemberNumber(), myGuardian);
				myGuardian.setRelationshipToJuvId(myMember.getRelationshipToJuvId());
				myGuardian.setRelationshipToJuv(myMember.getRelationshipToJuv());
				myGuardian.setDeceased(myMember.getDeceasedYesNo());
				myGuardian.setIncarcerated(myMember.getIncarceratedYesNo());
				stepParentEmploymentRecord.add(myGuardian);
			}

		}

		form.setStepParentEmploymentRecord(stepParentEmploymentRecord);

		// Get guardian address info and create a hashmap out of it
		// The Reason why it's a hashmap is because the guardian info, financial
		// info,
		// and address info need to be displayed for each guardian.
		List guardiansAndOtherParents = new ArrayList();
		guardiansAndOtherParents.addAll(form.getGuardianEmploymentRecord());
		guardiansAndOtherParents.addAll(form.getStepParentEmploymentRecord());
		HashMap guardiansAddressInfo = getGuardiansAddressInfo(guardiansAndOtherParents);
		form.setGuardianAddressMap(guardiansAddressInfo);

		HashMap guardiansPhoneInfo = getGuardiansPhoneInfo(guardiansAndOtherParents);
		form.setGuardianPhoneMap(guardiansPhoneInfo);
		if (form.getCasefileId() == null || form.getCasefileId().equals("")) {
			sendToErrorPage(aRequest, "error.casefileUnavailable");
			return aMapping.findForward(UIConstants.CANCEL);
		}
		form.setNextCourtDate(getNextCourtDate(form.getCasefileId()));

		return aMapping.findForward("requestAttorneyAppointment");
	}

	/*
	 * 
	 */
	private boolean memberMatchesCriteria( JuvenileFamilyForm.MemberList myMember )
	{
		boolean rtn = false ;
		
		if( !myMember.isGuardian() &&
				myMember.isInHomeStatus() &&
				!myMember.isDeceased() &&
				( myMember.getRelationshipToJuvId().equals("SM") ||
					myMember.getRelationshipToJuvId().equals("SF") || 
					myMember.getRelationshipToJuvId().equals("IA")) )
		{
			rtn = true ;
		}
		
		return( rtn ) ;
	}

	/*
	 * 
	 */
	private HashMap getGuardiansAddressInfo(Collection guardians)
	{
		HashMap guardianAddressMap = new HashMap();

		for( Iterator gIter = guardians.iterator(); gIter.hasNext(); /*empty*/ )
		{
			JuvenileFamilyForm.Guardian guardian = (JuvenileFamilyForm.Guardian)gIter.next();

			// Sending PD Request Event
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetFamilyMemberAddressEvent addrEvent = (GetFamilyMemberAddressEvent)
					EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);

			addrEvent.setMemberNum(guardian.getMemberNumber());
			dispatch.postEvent(addrEvent);

			// Get PD Response Event
			CompositeResponse addrResponse = (CompositeResponse)dispatch.getReply();

			// Perform Error handling
			MessageUtil.processReturnException(addrResponse);
			Map addrDataMap = MessageUtil.groupByTopic(addrResponse);
			AddressResponseEvent responseEvt;
			
			ArrayList myNewList = new ArrayList();
			if( addrDataMap != null )
			{
				Collection addresses = (Collection)
						addrDataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_ADDRESS_TOPIC);
				if( notNullNotEmptyCollection( addresses ) )
				{
					Iterator iter = addresses.iterator();
					while( iter.hasNext() )
					{
						if( (responseEvt = (AddressResponseEvent)iter.next()) != null )
						{
							JuvenileMemberForm.MemberAddress myAddress = 
									UIJuvenileHelper.getJuvMemberFormMemberAddresFROMAddrRespEvt(responseEvt);
							if( myAddress != null )
							{
								myNewList.add(myAddress);
							}
						}
					}  //while
				}
				
				UIJuvenileHelper.sortMemberAddressList(myNewList);
				if( myNewList.iterator() != null && myNewList.iterator().hasNext() )
				{
					guardianAddressMap.put(guardian.getMemberNumber(), myNewList.iterator().next());
				}
			}
		} // for

		return guardianAddressMap;
	}

	/*
	 * 
	 */
	private HashMap getGuardiansPhoneInfo(Collection guardians)
	{
		HashMap guardianPhoneMap = new HashMap();

		for( Iterator gIter = guardians.iterator(); gIter.hasNext(); /*empty*/ )
		{
			JuvenileFamilyForm.Guardian guardian = (JuvenileFamilyForm.Guardian)gIter.next();
			
			GetFamilyMemberContactEvent event = (GetFamilyMemberContactEvent)
					EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERCONTACT);

			event.setMemberId(guardian.getMemberNumber());
			CompositeResponse response = postRequestEvent(event);
			Collection phoneNumbers = MessageUtil.compositeToCollection(
					response, FamilyMemberPhoneResponseEvent.class);

			// Get the most recent primary phone number.
			FamilyMemberPhoneResponseEvent primaryPhone = null;
			if( notNullNotEmptyCollection( phoneNumbers ) )
			{
				for( Iterator phoneIter = phoneNumbers.iterator(); phoneIter.hasNext(); /*empty*/ )
				{
					FamilyMemberPhoneResponseEvent phoneRE = (FamilyMemberPhoneResponseEvent)phoneIter.next();
					if( phoneRE.isPrimaryInd())
					{	// is phoneRE date after latestPhone date
						if( primaryPhone == null ||
								phoneRE.getEntryDate().compareTo(primaryPhone.getEntryDate()) == 1 )
						{
							primaryPhone = phoneRE;
						}
					}
				}  // for
			}
			
			if( primaryPhone != null && 
					primaryPhone.getPhoneNum() != null &&
					!"".equals(primaryPhone.getPhoneNum()) )
			{
				PhoneNumberBean phone = new PhoneNumberBean(primaryPhone.getPhoneNum());
				guardianPhoneMap.put(guardian.getMemberNumber(), phone);
			}

		}

		return guardianPhoneMap;
	}

	/*
	 * given a Collection, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyCollection( Collection coll )
	{
		return( coll != null &&  !coll.isEmpty() ) ;
	}
	
	/*
	 * 
	 */
	private JuvenileCourtDateEvent getNextCourtDate(String casefileId)
	{
		GetNextJuvenileCourtDateEvent request = (GetNextJuvenileCourtDateEvent)
				EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETNEXTJUVENILECOURTDATE);
		request.setCasefileId(casefileId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);

		// Get PD Response Event
		CompositeResponse nextCourtDateResponse = (CompositeResponse)dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(nextCourtDateResponse);

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		Object obj = MessageUtil.filterComposite(compositeResponse, JuvenileCourtDateEvent.class);

		return( (JuvenileCourtDateEvent)obj );
	}


	/*
	 * 
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		String comments = form.getAttorneyOptionsNotes();
		
		if( comments != null  &&  comments.length() > 0 )
		{
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name( user.getFirstName(), "", user.getLastName() );
			
			form.setAttorneyOptionsNotes(comments + " [" +
					DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " +
					userName.getFormattedName() + "]");
		}
		form.setAction("summary");
		aRequest.setAttribute("state", "summary");

		return aMapping.findForward("requestAttorneyAppointment");
	}

	/*
	 * 
	 */
	public ActionForward generateDocument_UJAC(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		aRequest.setAttribute("state", "confirm");

		RequestAppointedAttorneyPrintBean myReportBean = new RequestAppointedAttorneyPrintBean();

		myReportBean.setJuvenileName(form.getJuvenileName());
		myReportBean.setJuvenileNum(form.getJuvenileNum());

		{ List tList = transformFamilyMemberInfo(
				(List)form.getGuardianEmploymentRecord(), 
				form.getGuardianAddressMap(), 
				form.getGuardianPhoneMap(), true) ;

			myReportBean.setGuardians( tList );

			tList = transformFamilyMemberInfo(
				(List)form.getStepParentEmploymentRecord(), 
				form.getGuardianAddressMap(), 
				form.getGuardianPhoneMap(), false) ;
			myReportBean.setOtherKnownParent( tList );
		}

		setFinancialInfo(myReportBean, (List)form.getGuardianEmploymentRecord());
		myReportBean.setTotalAnnualIncome(calculateTotalAnnualIncome(myReportBean));
		myReportBean.setAdditionalComments(form.getAttorneyOptionsNotes());

		myReportBean.setYouthCoveredByMedicaid(
				determineMedicaidCoverage(form.getJuvenileNum()));

		setOffenseInfo(myReportBean, form.getCasefileId());

		CompositeResponse compResp = sendPrintRequest(
				"REPORTING::REQUEST_ATTORNEY_REPORT", myReportBean, null);
		
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
				MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		
		if( aReportRespEvt == null || 
				aReportRespEvt.getContent() == null ||
				aReportRespEvt.getContent().length < 1 )
		{
			sendToErrorPage( aRequest, "error.generic",
					"Problems generating report. " +contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}

		SaveInterviewDocumentEvent saveEvt = new SaveInterviewDocumentEvent();
		saveEvt.setCasefileId(form.getCasefileId());
		saveEvt.setDocument(aReportRespEvt.getContent());
		saveEvt.setDocumentTypeCodeId("RAA");
		// CODE_TABLE_NAME = INTERVIEW_DOCTYPE);

		CompositeResponse response1 = postRequestEvent(saveEvt);
		InterviewReportResponseEvent respEvt = (InterviewReportResponseEvent)
				MessageUtil.filterComposite(response1, InterviewReportResponseEvent.class);
		if( respEvt == null )
		{
			sendToErrorPage( aRequest, "error.generic", 
					"Problems saving the generated report. " +contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}

		try
		{
			setPrintContentResp(aResponse, compResp, "REQUEST_APPOINTED_ATTORNEY",
					UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, "");
		}

		// no need to forward since response has already been committed.
		return null;
	}
	
	/*
	 * 
	 */
	public ActionForward generateDocument(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		aRequest.setAttribute("state", "confirm");

		RequestAppointedAttorneyPrintBean myReportBean = new RequestAppointedAttorneyPrintBean();

		myReportBean.setJuvenileName(form.getJuvenileName());
		myReportBean.setJuvenileNum(form.getJuvenileNum());

		{ List tList = transformFamilyMemberInfo(
				(List)form.getGuardianEmploymentRecord(), 
				form.getGuardianAddressMap(), 
				form.getGuardianPhoneMap(), true) ;

			myReportBean.setGuardians( tList );

			tList = transformFamilyMemberInfo(
				(List)form.getStepParentEmploymentRecord(), 
				form.getGuardianAddressMap(), 
				form.getGuardianPhoneMap(), false) ;
			myReportBean.setOtherKnownParent( tList );
		}

		setFinancialInfo(myReportBean, (List)form.getGuardianEmploymentRecord());
		myReportBean.setTotalAnnualIncome(calculateTotalAnnualIncome(myReportBean));
		myReportBean.setAdditionalComments(form.getAttorneyOptionsNotes());

		myReportBean.setYouthCoveredByMedicaid(
				determineMedicaidCoverage(form.getJuvenileNum()));

		setOffenseInfo(myReportBean, form.getCasefileId());
		
		aRequest.getSession().setAttribute("reportInfo", myReportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.REQUEST_ATTORNEY_REPORT);
		
		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");

		if( pdfDocument == null || pdfDocument.length < 1 )
		{
			sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		/*CompositeResponse compResp = sendPrintRequest(
				"REPORTING::REQUEST_ATTORNEY_REPORT", myReportBean, null);
		
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
				MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		
		if( aReportRespEvt == null || 
				aReportRespEvt.getContent() == null ||
				aReportRespEvt.getContent().length < 1 )
		{
			sendToErrorPage( aRequest, "error.generic",
					"Problems generating report. " +contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}*/

		SaveInterviewDocumentEvent saveEvt = new SaveInterviewDocumentEvent();
		saveEvt.setCasefileId(form.getCasefileId());
		saveEvt.setDocument(pdfDocument);
		saveEvt.setDocumentTypeCodeId("RAA");
		// CODE_TABLE_NAME = INTERVIEW_DOCTYPE);

		CompositeResponse response1 = postRequestEvent(saveEvt);
		InterviewReportResponseEvent respEvt = (InterviewReportResponseEvent)
				MessageUtil.filterComposite(response1, InterviewReportResponseEvent.class);
		if( respEvt == null )
		{
			sendToErrorPage( aRequest, "error.generic", 
					"Problems saving the generated report. " +contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}

		// no need to forward since response has already been committed.
		return null;
	}

	/*
	 * 
	 */
	private double calculateTotalAnnualIncome( RequestAppointedAttorneyPrintBean myReportBean)
	{
		double totalAnnualIncome = 0;

		FamilyMemberDetailsBean guardian1 = null;
		FamilyMemberDetailsBean guardian2 = null;

		List guardians = myReportBean.getGuardians();
		if( notNullNotEmptyCollection( guardians ) )
		{
			Iterator guardianIter = guardians.iterator();
			guardian1 = (FamilyMemberDetailsBean)guardianIter.next();

			if( guardian1.isInHome() )
			{
				if( guardianIter.hasNext() )
				{
					guardian2 = (FamilyMemberDetailsBean)guardianIter.next();
					if( !guardian2.isInHome() )
					{
						guardian2 = null;
					}
				}
			}
			else if( guardianIter.hasNext() )
			{
				guardian1 = (FamilyMemberDetailsBean)guardianIter.next();
			}
		}

		if( guardian1.isBirthParent() )
		{
			if( guardian2 != null && guardian2.isBirthParent() )
			{
				totalAnnualIncome = guardian1.getTotalAnnualIncome() +
						guardian2.getTotalAnnualIncome();
			}
			else if( guardian2 != null && guardian2.isStepParent() )
			{
				totalAnnualIncome = guardian1.getTotalAnnualIncome() +
						guardian2.getTotalAnnualIncome() / 2;
			}
			else
			{
				totalAnnualIncome = guardian1.getTotalAnnualIncome();
			}
		}
		else if( guardian1.isStepParent() )
		{
			if( guardian2 != null && guardian2.isBirthParent() )
			{
				totalAnnualIncome = guardian1.getTotalAnnualIncome() / 2 +
						guardian2.getTotalAnnualIncome();
			}
			else
			{
				totalAnnualIncome = guardian1.getTotalAnnualIncome() / 2;
			}
		}
		else
		{	// Don't include income of parents other than birth parent or step parent
			if( guardian2 != null && guardian2.isBirthParent() )
			{
				totalAnnualIncome = guardian2.getTotalAnnualIncome();
			}
			else if( guardian2 != null && guardian2.isStepParent() )
			{
				totalAnnualIncome = guardian2.getTotalAnnualIncome() / 2;
			}
		}

		totalAnnualIncome += myReportBean.getSsi() +
				myReportBean.getChildSupport() + myReportBean.getTanf() * 12;

		return totalAnnualIncome;

	}

	/*
	 * 
	 */
	private String determineMedicaidCoverage(String juvenileNum)
	{
		String medicaidCoverage = "NO";
		GetJuvenileBenefitsEvent event = new GetJuvenileBenefitsEvent();
		event.setJuvenileNum(juvenileNum);
		event.setTitle4eAndMedicaidOnly(true);
		
		CompositeResponse compResponse = postRequestEvent(event);
		Collection juvenileBenefits = MessageUtil.compositeToCollection(
				compResponse, JuvenileBenefitResponseEvent.class);

		for( Iterator benefitsIter = juvenileBenefits.iterator(); 
				benefitsIter.hasNext(); /*empty*/ )
		{
			JuvenileBenefitResponseEvent benefit = (JuvenileBenefitResponseEvent)benefitsIter.next();
			if( "MCAD".equalsIgnoreCase(benefit.getEligibilityTypeId()) )
			{
				medicaidCoverage = "YES";
				break;
			}
		}
		
		return medicaidCoverage;
	}

	/*
	 * 
	 */
	private List transformFamilyMemberInfo(List familyMemberInfo,
			HashMap addressMap, HashMap phoneMap, boolean isGuardian)
	{
		List familyMemberTfmed = new ArrayList();

		for( Iterator famIter = familyMemberInfo.iterator(); famIter.hasNext(); )
		{
			JuvenileFamilyForm.Guardian guardian = (JuvenileFamilyForm.Guardian)famIter.next();
			FamilyMemberDetailsBean member = new FamilyMemberDetailsBean();
			
			member.setMemberId(guardian.getMemberNumber());
			member.setName(guardian.getName());
			member.setRelationshipToJuvId(guardian.getRelationshipToJuvId());
			member.setRelationshipToJuv(guardian.getRelationshipToJuv());
			member.setInHome(guardian.isInHomeStatus());

			if( notNullNotEmptyCollection( guardian.getEmploymentInfoList() ) )
			{
				List selEmploymentRecord = new ArrayList();
				for( Iterator emplIter = guardian.getEmploymentInfoList().iterator(); 
						emplIter.hasNext(); /*empty*/)
				{
					JuvenileMemberForm.MemberEmployment emp = (JuvenileMemberForm.MemberEmployment)emplIter.next();
					if( emp.isSelected() )
					{
						selEmploymentRecord.add(emp);
					}
				}
				member.setEmploymentRecord(selEmploymentRecord);
			}

			Object obj = addressMap.get(guardian.getMemberNumber());
			if( obj != null )
			{
				JuvenileMemberForm.MemberAddress address = (JuvenileMemberForm.MemberAddress)obj;
				if( address != null )
				{
					member.setAddress(address);
				}
			}
			
			PhoneNumberBean phone = (PhoneNumberBean)phoneMap.get(guardian.getMemberNumber());
			if( phone != null )
			{
				member.setPhone(phone);
			}
			
			if( isGuardian )
			{
				GetFamilyMemberDetailsEvent getMaritalStatusEvent = new GetFamilyMemberDetailsEvent();
				getMaritalStatusEvent.setMemberNum(guardian.getMemberNumber());
				
				CompositeResponse resp = postRequestEvent(getMaritalStatusEvent);
				FamilyMemberMartialStatusResponseEvent maritalStatus = (FamilyMemberMartialStatusResponseEvent)
						MessageUtil.filterComposite(resp, FamilyMemberMartialStatusResponseEvent.class);
				
				if( maritalStatus != null &&
						"MA".equalsIgnoreCase(maritalStatus.getMartialStatusId()) )
				{
					member.setMarried(true);
					member.setMarriedToMemberId(maritalStatus.getRelatedFamMemId());
				}
			}

			familyMemberTfmed.add(member);
		}
		
		return familyMemberTfmed;
	}

	/*
	 * 
	 */
	private void setFinancialInfo(RequestAppointedAttorneyPrintBean myReportBean, List guardians)
	{
		for( Iterator famIter = guardians.iterator(); famIter.hasNext(); /*empty*/)
		{
			JuvenileFamilyForm.Guardian guardian = (JuvenileFamilyForm.Guardian)famIter.next();
			if( guardian.isFinancialInfoSelected() )
			{
				String tStr = guardian.getSsi() ;
				if( notNullNotEmptyString( tStr ) )
				{
					myReportBean.setSsi(Double.parseDouble(tStr));
				}

				tStr = guardian.getFoodStamps() ;
				if( notNullNotEmptyString( tStr ) )
				{
					myReportBean.setFoodStamps(Double.parseDouble(tStr));
				}
				
				tStr = guardian.getChildSupportReceived() ;
				if( notNullNotEmptyString( tStr ) )
				{
					myReportBean.setChildSupport(Double.parseDouble(tStr));
				}
				
				tStr = guardian.getTanfAfdc() ;
				if( notNullNotEmptyString( tStr ) )
				{
					myReportBean.setTanf(Double.parseDouble(tStr));
				}
				
				tStr = guardian.getNumberLivingInHome() ;
				if( notNullNotEmptyString( tStr ) )
				{
					myReportBean.setNumberInHousehold(Integer.parseInt(tStr));
				}
			}
		}
	}

	
	/*
	 * given a String, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null &&  str.length() > 0) ;
	}
	
	/*
	 * 
	 */
	private void setOffenseInfo(RequestAppointedAttorneyPrintBean myReportBean, String casefileId)
	{
		String juvenileNum = myReportBean.getJuvenileNum();
		GetAssignmentsByCasefileIdEvent getAssignments = new GetAssignmentsByCasefileIdEvent();
		
		getAssignments.setCasefileId(casefileId);
		CompositeResponse getAssignmentsResp = postRequestEvent(getAssignments);
		Collection assignments = MessageUtil.compositeToCollection(
				getAssignmentsResp, AssignmentResponseEvent.class);

		JJSChargeResponseEvent mostSeverePetition = null;
		JuvenileInterviewForm.PetitionLevelDegree mostSevere = null;

		// Get all of the petition that the juvenile has (by referralId)
		StringBuffer petitionNumbers = new StringBuffer();

		if( notNullNotEmptyCollection( assignments ) )
		{
			for( Iterator assignmentIter = assignments.iterator(); 
					assignmentIter.hasNext();  /*empty*/)
			{
				AssignmentResponseEvent assignment = (AssignmentResponseEvent)assignmentIter.next();

				GetJuvenileCasefileReferralDetailsEvent getJuvCasefileReferral = new GetJuvenileCasefileReferralDetailsEvent();
				getJuvCasefileReferral.setJuvenileNum(juvenileNum);
				getJuvCasefileReferral.setReferralNum(assignment.getReferralNum());
				
				CompositeResponse getJuvCasefileReferralResp = postRequestEvent(getJuvCasefileReferral);
				Collection referrals = MessageUtil.compositeToCollection(
						getJuvCasefileReferralResp, JJSReferralResponseEvent.class);
				
				if( notNullNotEmptyCollection( referrals ) )
				{
					Collections.sort((List)referrals);
					JJSReferralResponseEvent latestReferral = (JJSReferralResponseEvent)
							referrals.iterator().next();
					
					myReportBean.setCourtId(latestReferral.getCourtId());
					myReportBean.setPendingCourtDate(latestReferral.getCourtDate());
				}

				GetJuvenileCasefilePetitionsEvent getJuvCasefilePetition = new GetJuvenileCasefilePetitionsEvent();
				getJuvCasefilePetition.setJuvenileNum(juvenileNum);
				getJuvCasefilePetition.setReferralNum(assignment.getReferralNum());

				CompositeResponse getJuvCasefilePetitionResp = postRequestEvent(getJuvCasefilePetition);
				Collection petitions = MessageUtil.compositeToCollection(
						getJuvCasefilePetitionResp, JJSChargeResponseEvent.class);
				
				if( notNullNotEmptyCollection( petitions ) )
				{
					// TODO: Need to get the most offense level
					Collections.sort((List)petitions);

					for( Iterator petitionIter = petitions.iterator(); 
							petitionIter.hasNext(); /*empty*/ )
					{
						JJSChargeResponseEvent petition = (JJSChargeResponseEvent)petitionIter.next();
						if( mostSeverePetition == null )
						{
							mostSeverePetition = petition;
							mostSevere = getLevelDegreeObj(mostSeverePetition.getLevelDegree());
						}
						else
						{
							JuvenileInterviewForm.PetitionLevelDegree curLevelDegree = 
									getLevelDegreeObj(petition.getLevelDegree());

							if( mostSevere != null &&
									curLevelDegree.getLevel() < mostSevere.getLevel() )
							{
								mostSeverePetition = petition;
								mostSevere = curLevelDegree;
							}
						}
						
						if( petitionNumbers.length() > 0 )
						{
							petitionNumbers.append(", ");
						}
						petitionNumbers.append(petition.getPetitionNum());
					} // for
					
					if( mostSevere != null )
					{
						myReportBean.setOffenseLevel(mostSevere.getLevelLiteral());
					}
				}
			}  // for
		}
		
		myReportBean.setPetitionNum(petitionNumbers.toString());
	}

	/*
	 * 
	 */
	private JuvenileInterviewForm.PetitionLevelDegree getLevelDegreeObj( String levelDegree)
	{
		for( Iterator iter = JuvenileInterviewForm.petitionLevels.iterator(); 
				iter.hasNext(); /*empty */ )
		{
			JuvenileInterviewForm.PetitionLevelDegree p = 
					(JuvenileInterviewForm.PetitionLevelDegree)iter.next();
			if( p.getLevelCode().equalsIgnoreCase(levelDegree) )
			{
				return p;
			}
		}
		
		return null;
	}

	/*
	 * 
	 */
	private void populateFamilyInformationTO(FamilyInformationTO guardianTO,
			JuvenileFamilyForm.Guardian guardian,
			JuvenileMemberForm.MemberEmployment employment)
	{
		if( guardianTO.getFamilyConstellationMemberId() == null ||
				guardianTO.getFamilyConstellationMemberId().length() == 0 )
		{
			guardianTO.setFamilyConstellationMemberId(guardian.getConstellationMemberId());
		}
		
		guardianTO.setFinancialInfoSelected(guardian.isFinancialInfoSelected());

		if( employment.isSelected() )
		{
			EmploymentHistoryTO empHistTO = new EmploymentHistoryTO();
			empHistTO.setOID(employment.getMemberEmploymentId());
			empHistTO.setIncluded(true);
			guardianTO.getEmploymentHistory().add(empHistTO);
		}
	}

	/*
	 * 
	 */
	private Collection getJuvenileJobs(String juvenileId)
	{
		GetJuvenileJobsEvent rEvent = (GetJuvenileJobsEvent)
				EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEJOBS);
		rEvent.setJuvenileNum(juvenileId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rEvent);

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		MessageUtil.groupByTopic(compositeResponse);
		Collection obj = MessageUtil.compositeToCollection(
				compositeResponse, JuvenileJobResponseEvent.class);

		return obj;
	}

	/*
	 * 
	 */
	private HashMap createJuvenileJobsMap(Collection juvenileJobs)
	{
		HashMap jobsMap = new HashMap();
		for( Iterator iter = juvenileJobs.iterator(); iter.hasNext(); /*empty*/ )
		{
			JuvenileJobResponseEvent re = (JuvenileJobResponseEvent)iter.next();
			jobsMap.put(re.getJobNum(), re);
		}

		return jobsMap;
	}

	/*
	 * 
	 */
	private HashMap createGuardianJobs(Collection guardians, String[] selGuardianEmpInfo)
	{
		HashMap guardiansMap = new HashMap();

		if( selGuardianEmpInfo != null && selGuardianEmpInfo.length > 0 )
		{
			for( int i = 0; i < selGuardianEmpInfo.length; i++ )
			{
				for( Iterator iter = guardians.iterator(); iter.hasNext(); /*empty*/ )
				{
					JuvenileFamilyForm.Guardian g = (JuvenileFamilyForm.Guardian)iter.next();
					Collection empInfo = g.getEmploymentInfoList();
					if( empInfo != null )
					{
						for( Iterator empInfoIter = empInfo.iterator(); 
								empInfoIter.hasNext(); /*empty*/ )
						{
							JuvenileMemberForm.MemberEmployment emp = 
									(JuvenileMemberForm.MemberEmployment)empInfoIter.next();
							if( selGuardianEmpInfo[i].equals(emp.getMemberEmploymentId()) )
							{
								Collection empInfoFromMap = (Collection)guardiansMap.get(selGuardianEmpInfo[i]);
								empInfoFromMap.add(emp);
							}
						} // for
					}
				} // for
			} // for
		}
		
		return guardiansMap;
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.requestAttorneyAppointment", "requestAttorneyAppt");
		keyMap.put("button.requestAttorneyAppt", "requestAttorneyAppt");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.next", "next");
		keyMap.put("button.generateDocument", "generateDocument");
	}
}