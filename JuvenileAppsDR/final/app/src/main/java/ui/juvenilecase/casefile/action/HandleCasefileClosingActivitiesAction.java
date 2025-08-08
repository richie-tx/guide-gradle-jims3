/*
 * Created on Dec 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.casefile.SaveCommonAppDocEvent;
import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import messaging.caseplan.GetCaseplansByJuvenileNumberEvent;
import messaging.caseplan.GetGoalListForReviewEvent;
import messaging.caseplan.reply.CaseplanListResponseEvent;
import messaging.caseplan.reply.GoalDetailsResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.interviewinfo.GetInterviewDetailEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.GuardianInfoResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.LocationControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.contact.user.helper.UIUserFormHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.CasefileClosingReportBean;
import ui.juvenilecase.casefile.ClosingPacketReportPrintBean;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.ClientSatisfactionSurveyPrintBean;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.casefile.form.CommunityExitPlanPrintBean;
import ui.juvenilecase.casefile.form.DeferredAdjudicationLetterPrintBean;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author jjose
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleCasefileClosingActivitiesAction extends JIMSBaseAction
{
	private enum LANGUAGE_PACKET { ENGLISH, SPANISH } ;

	/**
	 * @roseuid 4396047D00FE
	 */
	public HandleCasefileClosingActivitiesAction()
	{
	}

	/*
	 * 
	 */
	public ActionForward updateClosing(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;	
		myClosingForm.setCompletedDescId("");
		myClosingForm.setFailureDescId("");
		if (UIConstants.OUTCOME_COMPLETE.equalsIgnoreCase(myClosingForm.getSupervisionOutcomeId() ) )
		{
			myClosingForm.setCompletedDescId(myClosingForm.getSupervsionOutcomeDescriptionId());
		}
		if (UIConstants.OUTCOME_FAILURE_TO_COMPLY.equalsIgnoreCase(myClosingForm.getSupervisionOutcomeId() ) )
		{
			myClosingForm.setCompletedDescId(myClosingForm.getSupervsionOutcomeDescriptionId());
		}	
		myClosingForm.setAction(UIConstants.UPDATE);

		return aMapping.findForward(UIConstants.UPDATE_CLOSING);
	}

	/*
	 * 
	 */
	public ActionForward searchPage(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
	}

	/*
	 * 
	 */
	public ActionForward sendApproval(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;

		myClosingForm.clearActions();
		String rejectReason = myClosingForm.getRejectReason();
		myClosingForm.setRejectReason(UIConstants.EMPTY_STRING);
		
		try
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

			UpdateCasefileClosingEvent myEvent = 
					UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
							myClosingForm, UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED);
			// getting clm logon Id from session
			myEvent.setClmLogonID(casefileForm.getCaseloadManagerId());
			myEvent.setJuvenileNum(casefileForm.getJuvenileNum());
			myEvent.setCasefileID(casefileForm.getSupervisionNum());
			myEvent.setRecordJuvUnit(casefileForm.getRecordJuvUnit());
			myEvent.setRecordCLM(casefileForm.getRecordCLM());
			myEvent.setSendForApproval(true);

			dispatch.postEvent(myEvent);
			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			MessageUtil.filterComposite(compositeResponse, CasefileClosingResponseEvent.class);

			casefileForm.setCaseStatusId(UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED);

			UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(),
					ActivityConstants.CASEFILE_SUBMITTED_TO_SUPERVISOR_FOR_APPROVAL, UIConstants.EMPTY_STRING);
		}
		catch( Exception e )
		{
			myClosingForm.setRejectReason(rejectReason);
			return aMapping.findForward(UIConstants.SUCCESS_HOME);
		}
		
		myClosingForm.setAction(UIConstants.APPROVE);
		myClosingForm.setSecondaryAction(UIConstants.CONFIRM);
		
		return aMapping.findForward(UIConstants.SUCCESS_HOME);
	}

	/*
	 * 
	 */
	public ActionForward approve(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		myClosingForm.clearActions();
		List<CasefileClosingForm.CasefileException> allExceptions = new ArrayList<CasefileClosingForm.CasefileException>();
		List<ProgramReferralResponseEvent> closedProgramReferrals = UIProgramReferralHelper.getClosedCasefileProgramReferrals( casefileForm.getSupervisionNum() ) ;
		if (closedProgramReferrals != null
			& closedProgramReferrals.size() > 0 ) {
		    for (ProgramReferralResponseEvent programReferralResp : closedProgramReferrals ){
			if ( programReferralResp.getEndDate().after( DateUtil.stringToDate(myClosingForm.getSupervisionEndDate(), DateUtil.DATE_FMT_1) )
				&& !"REJ".equals( programReferralResp.getReferralSubStatusCd() )
				&& !"WIT".equals( programReferralResp.getReferralSubStatusCd() ) 
				&& !"CAN".equals( programReferralResp.getReferralSubStatusCd() ) ){
			    CasefileClosingForm.CasefileException caseFileException = new CasefileClosingForm.CasefileException();
			    caseFileException.setExceptionId(programReferralResp.getReferralId());
			    caseFileException.setExceptionType(PDJuvenileCaseConstants.PROGRAM_REFERRAL_END_DATE_AFTER);
			    caseFileException.setExceptionMessage("The program referral, " + programReferralResp.getProviderProgramName() +" has an end date after the close date of the casefile.  Correction to the appropriate program end date is required" );
			    allExceptions.add( caseFileException );
			}
			
		    }
		 
		    
		    if ( allExceptions != null 
			    && allExceptions.size() > 0 ){
			myClosingForm.setCasefileExceptions(allExceptions);
			return aMapping.findForward(UIConstants.EXCEPTIONSAPPROVE);
		    }
		   
		}
		
		
		
		
		//ER 64581 Check supervision type.  If court or Intake close casefile instead of just approving it.
		/*String supervisionType = casefileForm.getSupervisionTypeId();
		if (supervisionType.equals(PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_COURT_SUPERVISION) || (supervisionType.equals(PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_INTAKE_SCREENING_SUPERVISION))) {
			closeCasefile(myClosingForm, casefileForm); 
		} else {
			approveClosing(myClosingForm, casefileForm);
		}*/
		//ER 66245 MJCW: Separate Casefile closing and printing.  Close all casefiles at CLM approval.
		closeCasefile(myClosingForm, casefileForm); 
		return aMapping.findForward(UIConstants.SUCCESS_HOME);
	}

	/*
	 * 
	 */
	public ActionForward reject(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.REJECT);
	}

	/*
	 * 
	 */
	public ActionForward exitPlan(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		myClosingForm.clearActions();

		if( casefileForm.getSupervisionTypeId() == null ||
				casefileForm.getSupervisionTypeId().trim().equals(UIConstants.EMPTY_STRING) )
		{
			return aMapping.findForward(UIConstants.SUCCESS_HOME);
		}
		
		if( casefileForm.getSupervisionCategoryId().equalsIgnoreCase(
				UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM) ||
				casefileForm.getSupervisionCategoryId().equalsIgnoreCase(
						UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ) )
		{
			return communityExitPlan(aMapping, aForm, aRequest, aResponse);
		}
		else if( casefileForm.getSupervisionCategoryId().equalsIgnoreCase(
				UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES) )
		{
			return residentialExitPlan(aMapping, aForm, aRequest, aResponse);
		}

		return aMapping.findForward(UIConstants.SUCCESS_HOME);
	}

	/*
	 * 
	 */
	private ActionForward residentialExitPlan(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward("residentialExitPlanSuccess");
	}

	/*
 * 
 */
	private ActionForward communityExitPlan(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CommunityExitPlanPrintBean printDataBean = new CommunityExitPlanPrintBean();
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);

		printDataBean = this.prepareCommunityExitPlanPrintBean(casefileForm, printDataBean);
	
		Collection closingEvalution = new ArrayList();
		closingEvalution.add("");
		closingEvalution.add(((CasefileClosingForm)aForm).getClosingEvalution());
		printDataBean.setClosingEvalution(closingEvalution);
		
		Collection closingComments = new ArrayList();
		closingComments.add("");
		closingComments.add(((CasefileClosingForm)aForm).getClosingComments());
		printDataBean.setClosingComments(closingComments);

		UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(),
				ActivityConstants.EXIT_PLAN_GENERATED, UIConstants.EMPTY_STRING);

		CompositeResponse compResp = sendPrintRequest(
				"REPORTING::COMMUNITY_EXIT_PLAN", printDataBean, null);

		ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
				MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		
		if(aReportRespEvt != null && aReportRespEvt.getContent() == null)
		{
			sendToErrorPage(aRequest, "error.generic", "Problems generating report");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		if(aReportRespEvt != null && aReportRespEvt.getContent() != null && aReportRespEvt.getContent().length < 1 )
        	{
        		sendToErrorPage(aRequest, "error.generic", "Problems generating report");
        		return aMapping.findForward(UIConstants.FAILURE);
        	}

		SaveCommonAppDocEvent saveEvt = new SaveCommonAppDocEvent();
		saveEvt.setCasefileId(casefileForm.getSupervisionNum());
		saveEvt.setDocument(aReportRespEvt.getContent());
		saveEvt.setDocTypeCd(CommonAppDocResponseEvent.COMMUNITY_EXIT_PLAN);

		CompositeResponse response1 = postRequestEvent(saveEvt);
		CommonAppDocResponseEvent respEvt = (CommonAppDocResponseEvent)
				MessageUtil.filterComposite(response1, CommonAppDocResponseEvent.class);
		if( respEvt == null )
		{
			sendToErrorPage(aRequest, "error.generic", "Problems generating and saving report");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		try
		{
			setPrintContentResp(aResponse, compResp, "COMMUNITY_EXIT_PLAN", UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
		}

		return null;
	}

	/*
	 * 
	 */
	public CommunityExitPlanPrintBean prepareCommunityExitPlanPrintBean(
			JuvenileCasefileForm casefileForm,
			CommunityExitPlanPrintBean printDataBean)
	{
		printDataBean.setCurrentDate(DateUtil.dateToString( DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));

		printDataBean.setJuvenile(casefileForm.getJuvenileName());
		
		printDataBean.setProbationTermDate(DateUtil.dateToString(
			casefileForm.getActivationDate(), DateUtil.DATE_FMT_1));

		printDataBean.setTerminationDate(DateUtil.dateToString(
			casefileForm.getExpectedSupervisionEndDate(), DateUtil.DATE_FMT_1));

		GetCaseplansByJuvenileNumberEvent evt = new GetCaseplansByJuvenileNumberEvent();
		evt.setJuvenileNum(casefileForm.getJuvenileNum());
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		Collection caseplans = MessageUtil.compositeToCollection(compositeResponse,
				CaseplanListResponseEvent.class);

		Collection colGoalEndRecommendations = new ArrayList();
		if( notNullNotEmptyCollection( caseplans ) )
		{
			Iterator<CaseplanListResponseEvent> iterCaseplan = caseplans.iterator();
			while( iterCaseplan.hasNext() )
			{
				CaseplanListResponseEvent curRespCaseplan = iterCaseplan.next();

				// Get all goals, as well as rules associated to this Caseplan
				GetGoalListForReviewEvent nevt = (GetGoalListForReviewEvent)
						EventFactory.getInstance(JuvenileCasePlanControllerServiceNames.GETGOALLISTFORREVIEW);
				nevt.setCaseplanID(curRespCaseplan.getCaseplanID());
				CompositeResponse compResp = postRequestEvent(nevt);

				Collection goalDetails = (Collection)MessageUtil.compositeToCollection(
						compResp, GoalDetailsResponseEvent.class);

				if( notNullNotEmptyCollection( goalDetails ) )
				{
					Iterator<GoalDetailsResponseEvent> iterGoals = goalDetails.iterator();
					while( iterGoals.hasNext() )
					{
						GoalDetailsResponseEvent goal = iterGoals.next();
						colGoalEndRecommendations.add(goal.getEndRecommendations());
					}
				}
			} // while
		}
		
		printDataBean.setGoalEndRecommendations(colGoalEndRecommendations);
	
		List activeReferrals = UIProgramReferralHelper.getJuvenileProgramReferrals(
				casefileForm.getJuvenileNum(), casefileForm.getSupervisionNum());
		Collection spNames = new ArrayList();

		if( activeReferrals != null && activeReferrals.size() > 0)
		{
			Iterator<ProgramReferralResponseEvent> iter = activeReferrals.iterator();
			while( iter.hasNext() )
			{
				ProgramReferralResponseEvent resp = iter.next();
				if( !resp.isInHouse() )
				{
					PhoneNumberBean phoneBean = new PhoneNumberBean();
					if( resp.getPhone() != null )
					{
						phoneBean = new PhoneNumberBean(resp.getPhone());
					}
					
					String str = null;
					
					if(resp.getJuvServiceProviderName() != null && !resp.getJuvServiceProviderName().equals("") && 
						phoneBean.getFormattedPhoneNumber() != null && !phoneBean.getFormattedPhoneNumber().equals("") &&
						resp.getExtnNum() != null && !resp.getExtnNum().equals("")){
					    
					    str = resp.getJuvServiceProviderName() + "    " + phoneBean.getFormattedPhoneNumber() + " - " + resp.getExtnNum();
					    
					} else if(resp.getJuvServiceProviderName() != null && !resp.getJuvServiceProviderName().equals("") && 
						phoneBean.getFormattedPhoneNumber() != null && !phoneBean.getFormattedPhoneNumber().equals("")){
					    
					    str = resp.getJuvServiceProviderName() + "    " + phoneBean.getFormattedPhoneNumber();
					} else if(resp.getJuvServiceProviderName() != null && !resp.getJuvServiceProviderName().equals("")) {
					    
					    str = resp.getJuvServiceProviderName();
					} else {
					    str = "";
					}
					
					if(str != null && !str.equals("")){
					    spNames.add(str);
					}
					
				}
			} // while
		}

		if( spNames != null && spNames.size() > 0)
		{
			printDataBean.setServiceProviderDetails(spNames);
		}

		return printDataBean;
	}

	/*
	 * 
	 */
	public ActionForward commonApp(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);

		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		if( casefileForm != null &&
				!casefileForm.getJuvenileNum().equals(myClosingForm.getJuvenileNum()) )
		{
			myClosingForm = new CasefileClosingForm();
			myClosingForm.setSupervisionNumber(casefileForm.getSupervisionNum());
			myClosingForm.setJuvenileNum(casefileForm.getJuvenileNum());
			myClosingForm.clearActions();
		}
		
		CommonAppForm appForm = (CommonAppForm)
				aRequest.getSession().getAttribute("commonAppForm");

		if( appForm == null || 
				!myClosingForm.getJuvenileNum().equals(appForm.getJuvenileNum()) )
		{
			appForm = new CommonAppForm();
		}
		else
		{
			appForm.clear() ;
		}

		myClosingForm.setReferrals(new ArrayList());
		HttpSession session = aRequest.getSession();
		session.setAttribute("commonAppForm", appForm);
		appForm.setCurrentOffenses(new ArrayList());

		CasefileClosingResponseEvent event = 
				UIJuvenileCasefileClosingHelper.getCasefileClosingDetails(casefileForm.getSupervisionNum());
		if( event != null )
		{
			UIJuvenileCasefileClosingHelper.setClosingInfoFROMClosingRespEvt(
					myClosingForm, event);
		}

		session.setAttribute("casefileClosingForm", myClosingForm);
		UIJuvenileCasefileClosingHelper.getReferrals(myClosingForm, 
				casefileForm.getJuvenileNum());

		UIJuvenileCasefileClosingHelper.getProfileReferrals(myClosingForm,
				casefileForm.getJuvenileNum());
		
		UIJuvenileCasefileClosingHelper.getMaxSeverityOffense(
				myClosingForm.getReferrals(), casefileForm.getJuvenileNum(), false);
		
		return aMapping.findForward("commonAppSuccess");
	}

	/*
	 * 
	 */
	private void prepareClosingPacketBean(
			ClosingPacketReportPrintBean myReportBean, String probationOfficerId)
	{
		GetOfficerProfileEvent gofe = (GetOfficerProfileEvent)
				EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);

		gofe.setOfficerProfileId(probationOfficerId);
		CompositeResponse response = MessageUtil.postRequest(gofe);
		OfficerProfileResponseEvent officerProfileResponse = (OfficerProfileResponseEvent)
				MessageUtil.filterComposite(response, OfficerProfileResponseEvent.class);

		if( officerProfileResponse != null )
		{
			/*myReportBean.setProbationOfficer( new Name(
					officerProfileResponse.getLastName(), 
					officerProfileResponse.getMiddleName(),
					officerProfileResponse.getFirstName())); *///old Format//
			// changed format 36225
			String probationOfficerName = officerProfileResponse.getFirstName()+" "+ officerProfileResponse.getLastName();
			myReportBean.setProbationOfficerName(probationOfficerName);

			String locationId = officerProfileResponse.getJuvLocationId();
			if( notNullNotEmptyString( locationId ) )
			{
				GetJuvenileLocationEvent gle = (GetJuvenileLocationEvent)
						EventFactory.getInstance(LocationControllerServiceNames.GETJUVENILELOCATION);

				gle.setLocationId(locationId);
				response = MessageUtil.postRequest(gle);
				LocationResponseEvent lre = (LocationResponseEvent)
						MessageUtil.filterComposite(response, LocationResponseEvent.class);

				List juvLocUnits = (List)lre.getLocationUnits();

				if( notNullNotEmptyString( officerProfileResponse.getJuvUnitId())  &&
						notNullNotEmptyCollection( juvLocUnits ) )
				{
					int len = juvLocUnits.size();
					for( int i = 0; i < len; i++ )
					{
						LocationResponseEvent locResp = (LocationResponseEvent)juvLocUnits.get(i);
						if( officerProfileResponse.getJuvUnitId().equalsIgnoreCase(
								locResp.getJuvLocationUnitId()) )
						{
							myReportBean.setLocationUnitName(locResp.getLocationUnitName());
							PhoneNumberBean phone = new PhoneNumberBean(
									locResp.getJuvLocationUnitPhoneNumber());
							myReportBean.setOfficerPhone(phone.getFormattedPhoneNumber());
							break;
						}
					} // for
				}

				AddressResponseEvent addressResponseEvent = lre.getLocationAddress();
				StringBuffer buf = new StringBuffer( addressResponseEvent.getStreetNum() )
						.append(" ").append(addressResponseEvent.getStreetName())
						.append(" ").append(addressResponseEvent.getStreetType())
						.append(" ").append(addressResponseEvent.getCity())
						.append(", ").append(addressResponseEvent.getState())
						.append(" ").append(addressResponseEvent.getZipCode());

				if( notNullNotEmptyString( addressResponseEvent.getAdditionalZipCode()) )
				{
					buf.append("-").append(addressResponseEvent.getAdditionalZipCode());
				}

				myReportBean.setLocationUnitAddress(buf.toString());
			}
		}
	}

	/*
	 * 
	 */
	private void getUpdateClosingPacketInfo(CasefileClosingForm myClosingForm,
			JuvenileCasefileForm casefileForm)
	{
		if( myClosingForm.isClosingLetterGenerated() )
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			UpdateCasefileClosingEvent myEvent = 
					UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
							myClosingForm, UIConstants.CASEFILE_CASE_STATUS_CLOSED);
			myEvent.setClosingPktGenerated(true);
			myEvent.setClosingLetterGenerated(true);
			myEvent.setCreate(false);
			myEvent.setCasefileID(casefileForm.getSupervisionNum());
			
			dispatch.postEvent(myEvent);
			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			casefileForm.setCaseStatusId(UIConstants.CASEFILE_CASE_STATUS_CLOSED);
		}
		else if( !myClosingForm.isClosingPktGenerated() )
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			UpdateCasefileClosingEvent myEvent = 
					UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
							myClosingForm,UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED);

			myEvent.setClosingPktGenerated(true);
			myEvent.setCreate(false);
			myEvent.setCasefileID(casefileForm.getSupervisionNum());
			
			dispatch.postEvent(myEvent);
			CompositeResponse compositeResponse = (CompositeResponse)dispatch	.getReply();
			MessageUtil.processReturnException(compositeResponse);
		}
	}

	/*
	 * 
	 */
	private ActionForward getUpdateClosingLetterInfo(CasefileClosingForm myClosingForm,
			JuvenileCasefileForm casefileForm, HttpServletRequest aRequest, ActionMapping aMapping)
	{
		if( myClosingForm.isClosingPktGenerated() )
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			UpdateCasefileClosingEvent myEvent = 
					UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
							myClosingForm, UIConstants.CASEFILE_CASE_STATUS_CLOSED);
			
			if (myEvent.getCasefileClosingInfoId() == null || myEvent.getCasefileClosingInfoId().equals("")) {
				sendToErrorPage(aRequest, "error.casefileUnavailable", myEvent.getSupervisionNumber());
				return aMapping.findForward("failure");
			} 
			
			myEvent.setClosingPktGenerated(true);
			myEvent.setClosingLetterGenerated(true);
			myEvent.setCreate(false);
			myEvent.setCasefileID(casefileForm.getSupervisionNum());
			myEvent.setJuvenileNum(casefileForm.getJuvenileNum());
			
			dispatch.postEvent(myEvent);
			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			
			casefileForm.setCaseStatusId(UIConstants.CASEFILE_CASE_STATUS_CLOSED);
		}
		else if( !myClosingForm.isClosingLetterGenerated() )
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			UpdateCasefileClosingEvent myEvent = 
					UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
							myClosingForm, UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED);
			myEvent.setClosingLetterGenerated(true);
			myEvent.setCreate(false);
			myEvent.setCasefileID(casefileForm.getSupervisionNum());
			myEvent.setJuvenileNum(casefileForm.getJuvenileNum());
			
			dispatch.postEvent(myEvent);
			CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
		}
		return null;
	}

	/*
	 * 
	 */
	public void closingPacket(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse, 
			LANGUAGE_PACKET language )
	{
	    	
		final String reportName = (language == LANGUAGE_PACKET.ENGLISH) ? 
				"REPORTING::RESTRICTED_ACCESS_EN" : "REPORTING::RESTRICTED_ACCESS_SP" ;
		final String fileName = (language == LANGUAGE_PACKET.ENGLISH) ? 
				"CLOSING_PACKET_EN" : "CLOSING_PACKET_SP" ;
		
		String submitType= aRequest.getParameter("submitType");
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);

		ClosingPacketReportPrintBean myReportBean = new ClosingPacketReportPrintBean();
		prepareClosingPacketBean(myReportBean, casefileForm.getProbationOfficerId());
		if ( !"ajax".equals( submitType ) ){
		    UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(),
						ActivityConstants.CLOSING_PACKET_GENERATED, UIConstants.EMPTY_STRING);
		}

		CompositeResponse compResp = sendPrintRequest( reportName, myReportBean, null);
		try
		{
			setPrintContentResp(aResponse, compResp, fileName, UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
		}

		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		if( casefileForm.getCaseStatusId().equalsIgnoreCase(
				UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED)
				&& !"ajax".equals( submitType ) )
		{
			getUpdateClosingPacketInfo(myClosingForm, casefileForm);
		}
		myClosingForm.setClosingPktGenerated(true);
	}

	/*
	 * 
	 */
	 //36225 Task
	public void closingPacketBFO(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse, 
			LANGUAGE_PACKET language )
	{
		final String reportName = (language == LANGUAGE_PACKET.ENGLISH) ? 
				"REPORTING::RESTRICTED_ACCESS_EN" : "REPORTING::RESTRICTED_ACCESS_SP" ;
		
		String submitType= aRequest.getParameter("submitType");
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);

		ClosingPacketReportPrintBean myReportBean = new ClosingPacketReportPrintBean();
		prepareClosingPacketBean(myReportBean, casefileForm.getProbationOfficerId());
		
		if ( !"ajax".equals( submitType ) ){
		    UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(),
						ActivityConstants.CLOSING_PACKET_GENERATED, UIConstants.EMPTY_STRING);
		}
		
		aRequest.getSession().setAttribute("reportInfo", myReportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		if(reportName == "REPORTING::RESTRICTED_ACCESS_EN"){
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.MJC_RESTRCITED_ACCESS_EN);
		}else if(reportName == "REPORTING::RESTRICTED_ACCESS_SP"){
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.MJC_RESTRICTED_ACCESS_ES);
		}

		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		if( casefileForm.getCaseStatusId().equalsIgnoreCase(
				UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED)
				&& !"ajax".equals( submitType ) )
		{
			getUpdateClosingPacketInfo(myClosingForm, casefileForm);
		}
		myClosingForm.setClosingPktGenerated(true);
	}

	
	public ActionForward closingPacketEnglish(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		closingPacketBFO( aMapping, aForm, aRequest, aResponse, LANGUAGE_PACKET.ENGLISH ) ;
	//	closingPacket( aMapping, aForm, aRequest, aResponse, LANGUAGE_PACKET.ENGLISH ) ;
		String source = (String) aRequest.getParameter("action");
		if(source != null && source.equalsIgnoreCase("calendar"))
			return aMapping.findForward("calendarSuccess");
		else
			return null;
	
	}

	/*
	 * 
	 */
	public ActionForward closingPacketSpanish(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
	    	closingPacketBFO( aMapping, aForm, aRequest, aResponse, LANGUAGE_PACKET.SPANISH ) ;
		//closingPacket( aMapping, aForm, aRequest, aResponse, LANGUAGE_PACKET.SPANISH ) ;
		String source = (String) aRequest.getParameter("action");
		if(source != null && source.equalsIgnoreCase("calendar"))
			return aMapping.findForward("calendarSuccess");
		else
			return null;
	}	
	
	public ActionForward closingLetterEnglish(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		closingLetter( aMapping, aForm, aRequest, aResponse, "ENGLISH") ;
		aRequest.setAttribute("pageType", UIConstants.SUMMARY);
		String source = (String) aRequest.getParameter("action");
		if(source != null && source.equalsIgnoreCase("calendar"))
			return aMapping.findForward("calendarSuccess");
		else
			return null;
	}
	
	public ActionForward closingLetterEnglishBFO(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		closingLetterBFO( aMapping, aForm, aRequest, aResponse, "ENGLISH") ;
		aRequest.setAttribute("pageType", UIConstants.SUMMARY);
		String source = (String) aRequest.getParameter("action");
		if(source != null && source.equalsIgnoreCase("calendar"))
			return aMapping.findForward("calendarSuccess");
		else
			return null;
	}

	/*
	 * 
	 */
	public ActionForward closingLetterSpanish(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		closingLetter( aMapping, aForm, aRequest, aResponse, "SPANISH") ;
		String source = (String) aRequest.getParameter("action");
		if(source != null && source.equalsIgnoreCase("calendar"))
			return aMapping.findForward("calendarSuccess");
		else
			return null;
	}
	
	/*
	 * 
	 */
	public ActionForward closingLetterSpanishBFO(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		closingLetterBFO( aMapping, aForm, aRequest, aResponse, "SPANISH") ;
		String source = (String) aRequest.getParameter("action");
		if(source != null && source.equalsIgnoreCase("calendar"))
			return aMapping.findForward("calendarSuccess");
		else
			return null;
	}
	
	/*
	 * 
	 */
	public ActionForward closingLetter(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse, String language)
	{
		final String reportName = (language == "ENGLISH") ? 
				"REPORTING::CLOSING_LETTER_ENGLISH" : "REPORTING::CLOSING_LETTER_SPANISH" ;
		final String fileName = (language == "ENGLISH") ? 
				"CLOSING_LETTER_EN" : "CLOSING_LETTER_SP" ;
		
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		String source = (String) aRequest.getParameter("action");
		String submitType= aRequest.getParameter("submitType");
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		
		//added for ER# 75517 -  Move closing documents to Calendar
		if(myClosingForm==null || (source!=null && source.equalsIgnoreCase("calendar")))
		{
			myClosingForm = new CasefileClosingForm();
			myClosingForm.setSupervisionNumber(casefileForm.getSupervisionNum());
			myClosingForm.setJuvenileNum(casefileForm.getJuvenileNum());
			myClosingForm.clearActions();
			CasefileClosingResponseEvent event = UIJuvenileCasefileClosingHelper.getCasefileClosingDetails(casefileForm.getSupervisionNum());
			myClosingForm.setReferrals(new ArrayList());
			myClosingForm.setSupervisionEndDate(casefileForm.getSupervisionEndDateStr());
			if( event != null )
			{
				UIJuvenileCasefileClosingHelper.setClosingInfoFROMClosingRespEvt(
						myClosingForm, event);
			}
			HttpSession session = aRequest.getSession();
			session.setAttribute("casefileClosingForm", myClosingForm);
			UIJuvenileCasefileClosingHelper.getReferrals(myClosingForm, 
					casefileForm.getJuvenileNum());

			UIJuvenileCasefileClosingHelper.getProfileReferrals(myClosingForm,
					casefileForm.getJuvenileNum());
			
			UIJuvenileCasefileClosingHelper.getMaxSeverityOffense(
					myClosingForm.getReferrals(), casefileForm.getJuvenileNum(), false);
		}
		
		
		CasefileClosingReportBean myReportBean = new CasefileClosingReportBean();
		String courtId = "";
		List refs = new ArrayList(myClosingForm.getReferralList());
		if (refs != null && refs.size() > 0){
			for (int x=0; x<refs.size(); x++ ){
				JuvenileCasefileReferralsResponseEvent jcrResp = (JuvenileCasefileReferralsResponseEvent) refs.get(x);
				if (jcrResp.getCourtId() != null && !"".equalsIgnoreCase(jcrResp.getCourtId() ) )
				{
					courtId = jcrResp.getCourtId();
					break;
				}
			}
		}
		refs = null;
		// defect 75262 - added 3-14-2013
		if (courtId == null || "".equals(courtId)){
			courtId = "314";
		}
		myReportBean.setCourtNum(courtId);
		myReportBean.setJuvenile(casefileForm.getJuvenileName());
		myReportBean.setSupervisionEndDate(casefileForm.getSupervisionEndDateStr());
		IUserInfo loggedonUser = SecurityUIHelper.getUser();

		if( loggedonUser != null )
		{
			myReportBean.setOfficerLastName(loggedonUser.getLastName());
			myReportBean.setOfficerFirstName(loggedonUser.getFirstName());
			myReportBean.setOfficerMiddleName(loggedonUser.getMiddleName());
		}

		OfficerProfileResponseEvent officer = 
				UIUserFormHelper.getUserOfficerProfile(SecurityUIHelper.getLogonId());
		if(officer!=null)
		{
			GetOfficerProfileEvent gofe = (GetOfficerProfileEvent)
					EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
			gofe.setOfficerProfileId(officer.getOfficerId());
			CompositeResponse response = postRequestEvent(gofe);
			OfficerProfileResponseEvent officerProfileResponse = (OfficerProfileResponseEvent)
					MessageUtil.filterComposite(response, OfficerProfileResponseEvent.class);
	
			if( officerProfileResponse != null )
			{
				myReportBean.setManagerFirstName(officerProfileResponse .getManagerFirstName());
				myReportBean.setManagerMiddleName(officerProfileResponse.getManagerMiddleName());
				myReportBean.setManagerLastName(officerProfileResponse.getManagerLastName());
	
				String locationId = officerProfileResponse.getJuvLocationId();
				if( notNullNotEmptyString( locationId ) )
				{
					GetJuvenileLocationEvent gle = (GetJuvenileLocationEvent)
							EventFactory.getInstance(LocationControllerServiceNames.GETJUVENILELOCATION);
					gle.setLocationId(locationId);
					response = MessageUtil.postRequest(gle);
					LocationResponseEvent lre = (LocationResponseEvent)
							MessageUtil.filterComposite(response, LocationResponseEvent.class);
	
					List juvLocUnits = (List)lre.getLocationUnits();
					if( notNullNotEmptyString( officerProfileResponse.getJuvUnitId())  &&
							notNullNotEmptyCollection( juvLocUnits ) )
					{
						int len = juvLocUnits.size();
						for( int i = 0; i < len; i++ )
						{
							LocationResponseEvent locResp = (LocationResponseEvent)juvLocUnits.get(i);
							if( officerProfileResponse.getJuvUnitId().equalsIgnoreCase(
									locResp.getJuvLocationUnitId()) )
							{
								myReportBean.setLocationUnitName(locResp.getLocationUnitName());
								PhoneNumberBean phone = new PhoneNumberBean(
										locResp.getJuvLocationUnitPhoneNumber());
								myReportBean.setManagerPhone(phone.getFormattedPhoneNumber());
								break;
							}
						}
					}
	
					AddressResponseEvent addressResponseEvent = lre.getLocationAddress();
					Address locationAddress = new Address();
					locationAddress.setStreetNum(addressResponseEvent.getStreetNum());
					locationAddress.setStreetName(addressResponseEvent.getStreetName());
					locationAddress.setStreetType(addressResponseEvent.getStreetType());
					locationAddress.setAptNum(addressResponseEvent.getAptNum());
					locationAddress.setCity(addressResponseEvent.getCity());
					locationAddress.setState(addressResponseEvent.getState());
					locationAddress.setZipCode(addressResponseEvent.getZipCode());
					locationAddress.setAdditionalZipCode(addressResponseEvent.getAdditionalZipCode());
					myReportBean.setLocationAddress(locationAddress);
				}
			}
		}

		// Get Guardian info and address
		String juvenileNum = casefileForm.getJuvenileNum();
		String casefileId = casefileForm.getSupervisionNum();
		myReportBean.setCasefileId(casefileId);
		
		FamilyConstellationMemberListResponseEvent guardianInfo = getGuardianInfo(juvenileNum);

		if( guardianInfo != null )
		{
			myReportBean.setMemberRelationship(guardianInfo.getRelationToJuvenile());
			myReportBean.setGuardianName(new Name(guardianInfo.getFirstName(),
					guardianInfo.getMiddleName(), guardianInfo.getLastName()));

			Address guardianAddress = getGuardianLatestAddress(guardianInfo.getMemberNum());
			if( guardianAddress != null )
			{
				myReportBean.setGuardianAddress(guardianAddress);
			}
			else
			{
				sendToErrorPage(aRequest, "error.inHomeGuardianMissingAddress");
				return aMapping.findForward("failure");
			}
		}
		else
		{
			sendToErrorPage(aRequest, "error.noInhomeGuardian");
			return aMapping.findForward("failure");
		}

/*		GetAssignmentsByCasefileIdEvent getReferrals = (GetAssignmentsByCasefileIdEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		getReferrals.setCasefileId(casefileId);

		response = postRequestEvent(getReferrals);

		Collection collReferrals = MessageUtil.compositeToCollection(response,
				AssignmentResponseEvent.class);

		StringBuffer petNums = new StringBuffer();

		if( collReferrals != null )
		{
			Iterator<AssignmentResponseEvent> iterReferrals = collReferrals.iterator();
			while( iterReferrals.hasNext() )
			{
				AssignmentResponseEvent assignments = iterReferrals.next();
				String referralNum = assignments.getReferralNum();
				if( notNullNotEmptyString( referralNum ) )
				{
					GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent)
							EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS);
					pet.setJuvenileNum(juvenileNum);
					pet.setReferralNum(referralNum);
					response = postRequestEvent(pet);
					Collection respPetitions = MessageUtil.compositeToCollection(
							response, JJSChargeResponseEvent.class);
					if( respPetitions != null )
					{
						Iterator<JJSChargeResponseEvent> iterPetitions = respPetitions.iterator();
						while( iterPetitions.hasNext() )
						{
							JJSChargeResponseEvent respP = iterPetitions.next();
							if( petNums.length() > 0 )
							{
								petNums.append(", ");
							}
							petNums.append(respP.getPetitionNum());
						}
					}
				}
			}  // while
		}

		myReportBean.setPetitionNumber(petNums.toString());*/
		myReportBean.setCurrentDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
		if(source != null && source.equalsIgnoreCase("calendar"))
			myReportBean.setSource("calendar");
		else
			myReportBean.setSource("");
		// record activity
		if ( !"ajax".equals( submitType ) ) {
		    UIJuvenileHelper.createActivity(casefileId,	ActivityConstants.CLOSING_LETTER_GENERATED, UIConstants.EMPTY_STRING);
		}
		
		CompositeResponse compResp = sendPrintRequest(reportName, myReportBean, null);
		try
		{
			setPrintContentResp(aResponse, compResp, fileName, UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
		}
		
		if( casefileForm.getCaseStatusId().equalsIgnoreCase(
				UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED)
				&& !"ajax".equals( submitType ))
		{
			getUpdateClosingLetterInfo(myClosingForm, casefileForm, aRequest, aMapping);
		}
		myClosingForm.setClosingLetterGenerated(true);

		return null;
	}
	
	/*
	 * 
	 */
	public ActionForward closingLetterBFO(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse, String language)
	{
		final String reportName = (language == "ENGLISH") ? 
				"REPORTING::CLOSING_LETTER_ENGLISH" : "REPORTING::CLOSING_LETTER_SPANISH" ;
		final String fileName = (language == "ENGLISH") ? 
				"CLOSING_LETTER_EN" : "CLOSING_LETTER_SP" ;
		
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		String source = (String) aRequest.getParameter("action");
		String submitType= aRequest.getParameter("submitType");
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		
		//added for ER# 75517 -  Move closing documents to Calendar
		if(myClosingForm==null || (source!=null && source.equalsIgnoreCase("calendar")))
		{
			myClosingForm = new CasefileClosingForm();
			myClosingForm.setSupervisionNumber(casefileForm.getSupervisionNum());
			myClosingForm.setJuvenileNum(casefileForm.getJuvenileNum());
			myClosingForm.clearActions();
			CasefileClosingResponseEvent event = UIJuvenileCasefileClosingHelper.getCasefileClosingDetails(casefileForm.getSupervisionNum());
			myClosingForm.setReferrals(new ArrayList());
			myClosingForm.setSupervisionEndDate(casefileForm.getSupervisionEndDateStr());
			if( event != null )
			{
				UIJuvenileCasefileClosingHelper.setClosingInfoFROMClosingRespEvt(
						myClosingForm, event);
			}
			HttpSession session = aRequest.getSession();
			session.setAttribute("casefileClosingForm", myClosingForm);
			UIJuvenileCasefileClosingHelper.getReferrals(myClosingForm, 
					casefileForm.getJuvenileNum());

			UIJuvenileCasefileClosingHelper.getProfileReferrals(myClosingForm,
					casefileForm.getJuvenileNum());
			
			UIJuvenileCasefileClosingHelper.getMaxSeverityOffense(
					myClosingForm.getReferrals(), casefileForm.getJuvenileNum(), false);
		}
		
		
		CasefileClosingReportBean myReportBean = new CasefileClosingReportBean();
		String courtId = "";
		List refs = new ArrayList(myClosingForm.getReferralList());
		if (refs != null && refs.size() > 0){
			for (int x=0; x<refs.size(); x++ ){
				JuvenileCasefileReferralsResponseEvent jcrResp = (JuvenileCasefileReferralsResponseEvent) refs.get(x);
				if (jcrResp.getCourtId() != null && !"".equalsIgnoreCase(jcrResp.getCourtId() ) )
				{
					courtId = jcrResp.getCourtId();
					break;
				}
			}
		}
		refs = null;
		// defect 75262 - added 3-14-2013
		if (courtId == null || "".equals(courtId)){
			courtId = "314";
		}
		myReportBean.setCourtNum(courtId);
		myReportBean.setJuvenile(casefileForm.getJuvenileName());
		myReportBean.setSupervisionEndDate(casefileForm.getSupervisionEndDateStr());
		IUserInfo loggedonUser = SecurityUIHelper.getUser();

		if( loggedonUser != null )
		{
			myReportBean.setOfficerLastName( loggedonUser.getLastName().replaceAll("\\(.*\\)", "") );
			myReportBean.setOfficerFirstName( loggedonUser.getFirstName().replaceAll("\\(.*\\)", "") );
			myReportBean.setOfficerMiddleName(loggedonUser.getMiddleName());
		}

		OfficerProfileResponseEvent officer = 
				UIUserFormHelper.getUserOfficerProfile(SecurityUIHelper.getLogonId());
		if(officer!=null)
		{
			GetOfficerProfileEvent gofe = (GetOfficerProfileEvent)
					EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
			gofe.setOfficerProfileId(officer.getOfficerId());
			CompositeResponse response = postRequestEvent(gofe);
			OfficerProfileResponseEvent officerProfileResponse = (OfficerProfileResponseEvent)
					MessageUtil.filterComposite(response, OfficerProfileResponseEvent.class);
	
			if( officerProfileResponse != null )
			{
				myReportBean.setManagerFirstName(officerProfileResponse .getManagerFirstName());
				myReportBean.setManagerMiddleName(officerProfileResponse.getManagerMiddleName());
				myReportBean.setManagerLastName(officerProfileResponse.getManagerLastName());
	
				String locationId = officerProfileResponse.getJuvLocationId();
				if( notNullNotEmptyString( locationId ) )
				{
					GetJuvenileLocationEvent gle = (GetJuvenileLocationEvent)
							EventFactory.getInstance(LocationControllerServiceNames.GETJUVENILELOCATION);
					gle.setLocationId(locationId);
					response = MessageUtil.postRequest(gle);
					LocationResponseEvent lre = (LocationResponseEvent)
							MessageUtil.filterComposite(response, LocationResponseEvent.class);
	
					List juvLocUnits = (List)lre.getLocationUnits();
					if( notNullNotEmptyString( officerProfileResponse.getJuvUnitId())  &&
							notNullNotEmptyCollection( juvLocUnits ) )
					{
						int len = juvLocUnits.size();
						for( int i = 0; i < len; i++ )
						{
							LocationResponseEvent locResp = (LocationResponseEvent)juvLocUnits.get(i);
							if( officerProfileResponse.getJuvUnitId().equalsIgnoreCase(
									locResp.getJuvLocationUnitId()) )
							{
								myReportBean.setLocationUnitName(locResp.getLocationUnitName());
								PhoneNumberBean phone = new PhoneNumberBean(
										locResp.getJuvLocationUnitPhoneNumber());
								myReportBean.setManagerPhone(phone.getFormattedPhoneNumber());
								break;
							}
						}
					}
	
					AddressResponseEvent addressResponseEvent = lre.getLocationAddress();
					Address locationAddress = new Address();
					locationAddress.setStreetNum(addressResponseEvent.getStreetNum());
					locationAddress.setStreetName(addressResponseEvent.getStreetName());
					locationAddress.setStreetType(addressResponseEvent.getStreetType());
					locationAddress.setAptNum(addressResponseEvent.getAptNum());
					locationAddress.setCity(addressResponseEvent.getCity());
					locationAddress.setState(addressResponseEvent.getState());
					locationAddress.setZipCode(addressResponseEvent.getZipCode());
					locationAddress.setAdditionalZipCode(addressResponseEvent.getAdditionalZipCode());
					myReportBean.setLocationAddress(locationAddress);
				}
			}
		}
		// Get Guardian info and address
		String juvenileNum = casefileForm.getJuvenileNum();
		String casefileId = casefileForm.getSupervisionNum();
		myReportBean.setCasefileId(casefileId);
		
		FamilyConstellationMemberListResponseEvent guardianInfo = getGuardianInfo(juvenileNum);

		if( guardianInfo != null )
		{
			myReportBean.setMemberRelationship(guardianInfo.getRelationToJuvenile());
			myReportBean.setGuardianName(new Name(guardianInfo.getFirstName(),
					guardianInfo.getMiddleName(), guardianInfo.getLastName()));

			Address guardianAddress = getGuardianLatestAddress(guardianInfo.getMemberNum());
			if( guardianAddress != null )
			{
				myReportBean.setGuardianAddress(guardianAddress);
			}
			else
			{
				sendToErrorPage(aRequest, "error.inHomeGuardianMissingAddress");
				return aMapping.findForward("failure");
			}
		}
		else
		{
			sendToErrorPage(aRequest, "error.noInhomeGuardian");
			return aMapping.findForward("failure");
		}

		myReportBean.setCurrentDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
		if(source != null && source.equalsIgnoreCase("calendar"))
			myReportBean.setSource("calendar");
		else
			myReportBean.setSource("");
		// record activity
		if ( !"ajax".equals( submitType )) {
		    UIJuvenileHelper.createActivity(casefileId,	ActivityConstants.CLOSING_LETTER_GENERATED, UIConstants.EMPTY_STRING);
		}
		
		aRequest.getSession().setAttribute("reportInfo", myReportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		if(reportName == "REPORTING::CLOSING_LETTER_ENGLISH"){
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.MJC_CLOSING_LETTER_EN);
		}else if(reportName == "REPORTING::CLOSING_LETTER_SPANISH"){
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.MJC_CLOSING_LETTER_ES);
		}
		return null;
	}

	/*
	 * <KISHORE> JIMS200061102 : Spanish CF Closing Client Satis. Survery (UI) - KK
	 */
	public ActionForward clientSatisfactionSurvey(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return(aMapping.findForward("clientSatisfactionSurvey"));
	}
	
	/*
	 * <KISHORE> JIMS200061102 : Spanish CF Closing Client Satis. Survery (UI) - KK
	 */
	public ActionForward generateDocument(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		String reportName = "REPORTING::CLIENT_SATISFACTION_SURVEY_EN";
		if(myClosingForm.isSpanishText())
			reportName = "REPORTING::CLIENT_SATISFACTION_SURVEY_ES";
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		ClientSatisfactionSurveyPrintBean myReportBean = new ClientSatisfactionSurveyPrintBean();
		
		myReportBean.clearAll();
		myReportBean = this.prepareClientSatisfactionSurveyPrintBean(casefileForm,
				myReportBean, aRequest);

		CompositeResponse compResp = sendPrintRequest(
				reportName, myReportBean, null);
		try
		{
			setPrintContentResp(aResponse, compResp, "CLIENT_SATISFACTION_SURVEY",
					UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
		}

		return null;
	}

	/*
	 * 
	 */
	public ActionForward generateDocumentBFO(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		String reportName = "REPORTING::CLIENT_SATISFACTION_SURVEY_EN";
		if(myClosingForm.isSpanishText())
			reportName = "REPORTING::CLIENT_SATISFACTION_SURVEY_ES";
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		ClientSatisfactionSurveyPrintBean myReportBean = new ClientSatisfactionSurveyPrintBean();
		
		myReportBean.clearAll();
		myReportBean = this.prepareClientSatisfactionSurveyPrintBean(casefileForm,
				myReportBean, aRequest);
		
		aRequest.getSession().setAttribute("reportInfo", myReportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		if(reportName == "REPORTING::CLIENT_SATISFACTION_SURVEY_EN"){
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CLIENT_SATISFACTION_SURVEY_EN);
		}else if(reportName == "REPORTING::CLIENT_SATISFACTION_SURVEY_ES"){
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CLIENT_SATISFACTION_SURVEY_ES);
		}
		return null;
	}
	
	/*
	 * 
	 */
	public ClientSatisfactionSurveyPrintBean prepareClientSatisfactionSurveyPrintBean(
			JuvenileCasefileForm casefileForm,
			ClientSatisfactionSurveyPrintBean myReportBean,
			HttpServletRequest aRequest)
	{
		
		Collection guardians = UIJuvenileFamilyHelper.getGuardians(casefileForm.getJuvenileNum());
		if(guardians!=null && guardians.size()>0){				
			Iterator guardiansIter=guardians.iterator();
			while(guardiansIter.hasNext()){
				GuardianInfoResponseEvent myRespEvt=(GuardianInfoResponseEvent)guardiansIter.next();
				if (myRespEvt.getStreetName() != null && !myRespEvt.getStreetName().equals("")) {
					String str = "" ;
					StringBuffer fullStreetName = new StringBuffer();
					if (myRespEvt.getStreetNumber() != null) {
						fullStreetName.append(myRespEvt.getStreetNumber());
						fullStreetName.append(" ");
						if (myRespEvt.getStreetNumSuffixId() != null && !myRespEvt.getStreetNumSuffixId().equals("")){
							fullStreetName.append(CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_SUFFIX, myRespEvt.getStreetNumSuffixId()));
							fullStreetName.append(" ");
						}
					}
					if (myRespEvt.getStreetName() != null) {
						fullStreetName.append(myRespEvt.getStreetName());
						fullStreetName.append(" ");
					}
					str = myRespEvt.getStreetTypeId() ;
					if( str != null && str.trim().length() > 0 )
					{
						fullStreetName.append(CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_TYPE, str) );
						fullStreetName.append(" ");
					}
					str = myRespEvt.getAptNumber();
					if( str != null && str.trim().length() > 0 )
					{
						fullStreetName.append(str);
					}
					myReportBean.setFullStreetName(fullStreetName.toString());
					StringBuffer cityStateZip = new StringBuffer();
					if (myRespEvt.getCity() != null) {
						cityStateZip.append(myRespEvt.getCity());
						cityStateZip.append(", ");
					}
					str = myRespEvt.getStateId();
					if( str != null && str.trim().length() > 0 )
					{
						cityStateZip.append(CodeHelper.getCodeDescription(PDCodeTableConstants.STATE_ABBR, str));
						cityStateZip.append(" ");
					}
					if (myRespEvt.getZipCode() != null) {
						cityStateZip.append(myRespEvt.getZipCode());						
					}
					myReportBean.setCityStateZip(cityStateZip.toString());
					break;
				}
		   	}
		}
		myReportBean.setProbationOfficer(casefileForm.getProbationOfficerName());
		myReportBean.setCasefileId(casefileForm.getSupervisionNum());
		myReportBean.setCurrentDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
		return myReportBean;
	}

	/*
	 * 
	 */
	public ActionForward serviceProviderSatisfactionSurvey( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		ClientSatisfactionSurveyPrintBean myReportBean = new ClientSatisfactionSurveyPrintBean();
		myReportBean.clearAll();
		
		if( !prepareVendorSatisfactionSurveyPrintBean(casefileForm, myReportBean, aRequest) )
		{
			return aMapping.findForward("successHome");
		}

		CompositeResponse compResp = sendPrintRequest(
				"REPORTING::VENDOR_SATISFACTION_SURVEY", myReportBean, null);
		try
		{
			setPrintContentResp(aResponse, compResp, "VENDOR_SATISFACTION_SURVEY",
					UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
		}

		return null;
	}
	
	/*
	 * 
	 */
	public ActionForward serviceProviderSatisfactionSurveyBFO( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		ClientSatisfactionSurveyPrintBean myReportBean = new ClientSatisfactionSurveyPrintBean();
		myReportBean.clearAll();
		
		if( !prepareVendorSatisfactionSurveyPrintBean(casefileForm, myReportBean, aRequest) )
		{
			return aMapping.findForward("successHome");
		}

		aRequest.getSession().setAttribute("reportInfo", myReportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.VENDOR_SATISFACTION_SURVEY);
		return null;
	}

	/*
		 * 
		 */
	public boolean prepareVendorSatisfactionSurveyPrintBean(
			JuvenileCasefileForm casefileForm,
			ClientSatisfactionSurveyPrintBean myReportBean,
			HttpServletRequest aRequest)
	{
		myReportBean.setProbationOfficer(casefileForm.getProbationOfficerName());
		myReportBean.setCasefileId(casefileForm.getSupervisionNum());
		myReportBean.setCurrentDate(DateUtil.dateToString(
				DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
		
		List activeReferrals = UIProgramReferralHelper.getJuvenileProgramReferrals(
				casefileForm.getJuvenileNum(), casefileForm.getSupervisionNum());

		Collection spNames = new ArrayList();

		if( activeReferrals != null )
		{
			Iterator<ProgramReferralResponseEvent> iter = activeReferrals.iterator();
			while( iter.hasNext() )
			{
				ProgramReferralResponseEvent resp = iter.next();
				spNames.add(resp.getJuvServiceProviderName());
			}
		}

		if( !spNames.isEmpty() )
		{
			myReportBean.setServiceProviderName(spNames);
			return true;
		}
		else
		{
			sendToErrorPage(aRequest, "error.spName.notFound");
			return false;
		}
	}

	/*
		 * 
		 */
	public ActionForward deferredAdjudicationLetter(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		DeferredAdjudicationLetterPrintBean myReportBean = new DeferredAdjudicationLetterPrintBean();

		myReportBean.clearAll();
		String errorKey = prepareDeferredAdjudicationLetterPrintBean(casefileForm, myReportBean);
		if( notNullNotEmptyString( errorKey ) )
		{
			sendToErrorPage(aRequest, errorKey);
			aMapping.findForward("failure");
		}

		CompositeResponse compResp = sendPrintRequest(
				"REPORTING::DEFERRED_ADJUDICATION_CLOSING_LETTER", myReportBean, null);
		try
		{
			setPrintContentResp(aResponse, compResp,
					"DEFERRED_ADJUDICATION_CLOSING_LETTER", UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
		}
		
		if( casefileForm.getCaseStatusId().equalsIgnoreCase(
				UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED) )
		{
			getUpdateClosingLetterInfo(myClosingForm, casefileForm, aRequest, aMapping);
		}
		myClosingForm.setClosingLetterGenerated(true);

		return null;
	}

	/*
	 * 
	 */
	public ActionForward deferredProsecutionLetter(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		DeferredAdjudicationLetterPrintBean myReportBean = new DeferredAdjudicationLetterPrintBean();
		myReportBean.clearAll();

		String errorKey = prepareDeferredAdjudicationLetterPrintBean(casefileForm, myReportBean);
		if( notNullNotEmptyString( errorKey ) )
		{
			sendToErrorPage(aRequest, errorKey);
			return aMapping.findForward("failure");
		}

		CompositeResponse compResp = 
			sendPrintRequest("REPORTING::DEFERRED_PROSECUTION_CLOSING_LETTER", myReportBean, null);
		try
		{
			setPrintContentResp(aResponse, compResp,
					"DEFERRED_PROSECUTION_CLOSING_LETTER", UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
		}

		if( casefileForm.getCaseStatusId().equalsIgnoreCase(
				UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED) )
		{
			getUpdateClosingLetterInfo(myClosingForm, casefileForm, aRequest, aMapping);
		}
		myClosingForm.setClosingLetterGenerated(true);

		return null;
	}

	/*
	 * 
	 */
	public String prepareDeferredAdjudicationLetterPrintBean(
			JuvenileCasefileForm casefileForm,
			DeferredAdjudicationLetterPrintBean myReportBean)
	{
		myReportBean.setJuvenile(casefileForm.getJuvenileName());
		myReportBean.setProbationOfficer(casefileForm.getProbationOfficerName());
		IUserInfo loggedonUser = SecurityUIHelper.getUser();

		if( loggedonUser != null )
		{
			myReportBean.setOfficerLastName(loggedonUser.getLastName());
			myReportBean.setOfficerFirstName(loggedonUser.getFirstName());
			myReportBean.setOfficerMiddleName(loggedonUser.getMiddleName());
		}

		OfficerProfileResponseEvent officer = 
				UIUserFormHelper.getUserOfficerProfile(SecurityUIHelper.getLogonId());

		GetOfficerProfileEvent gofe = (GetOfficerProfileEvent)
				EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
		gofe.setOfficerProfileId(officer.getOfficerId());
		CompositeResponse response = postRequestEvent(gofe);
		OfficerProfileResponseEvent officerProfileResponse = (OfficerProfileResponseEvent)
				MessageUtil.filterComposite(response, OfficerProfileResponseEvent.class);

		if( officerProfileResponse != null )
		{
			myReportBean.setManagerFirstName(officerProfileResponse .getManagerFirstName());
			myReportBean.setManagerMiddleName(officerProfileResponse.getManagerMiddleName());
			myReportBean.setManagerLastName(officerProfileResponse.getManagerLastName());

			String locationId = officerProfileResponse.getJuvLocationId();
			if( notNullNotEmptyString( locationId ) )
			{
				GetJuvenileLocationEvent gle = (GetJuvenileLocationEvent)
						EventFactory.getInstance(LocationControllerServiceNames.GETJUVENILELOCATION);
				gle.setLocationId(locationId);
				response = MessageUtil.postRequest(gle);
				LocationResponseEvent lre = (LocationResponseEvent)
						MessageUtil.filterComposite(response, LocationResponseEvent.class);

				List juvLocUnits = (List)lre.getLocationUnits();
				if( notNullNotEmptyString( officerProfileResponse.getJuvUnitId())  &&
						notNullNotEmptyCollection( juvLocUnits ) )
				{
					int len = juvLocUnits.size();
					for( int i = 0; i < len; i++ )
					{
						LocationResponseEvent locResp = (LocationResponseEvent)juvLocUnits.get(i);
						if( officerProfileResponse.getJuvUnitId().equalsIgnoreCase(
								locResp.getJuvLocationUnitId()) )
						{
							myReportBean.setLocationUnitName(locResp.getLocationUnitName());
							PhoneNumberBean phone = new PhoneNumberBean(
									locResp.getJuvLocationUnitPhoneNumber());
							myReportBean.setManagerPhone(phone.getFormattedPhoneNumber());
							break;
						}
					}
				}

				AddressResponseEvent addressResponseEvent = lre.getLocationAddress();
				Address locationAddress = new Address();
				locationAddress.setStreetNum(addressResponseEvent.getStreetNum());
				locationAddress.setStreetName(addressResponseEvent.getStreetName());
				locationAddress.setStreetType(addressResponseEvent.getStreetType());
				locationAddress.setAptNum(addressResponseEvent.getAptNum());
				locationAddress.setCity(addressResponseEvent.getCity());
				locationAddress.setState(addressResponseEvent.getState());
				locationAddress.setZipCode(addressResponseEvent.getZipCode());
				locationAddress.setAdditionalZipCode(addressResponseEvent.getAdditionalZipCode());

				myReportBean.setLocationAddress(locationAddress);
			}
		}
		// Get Guardian info and address
		String juvenileNum = casefileForm.getJuvenileNum();
		String casefileId = casefileForm.getSupervisionNum();

		FamilyConstellationMemberListResponseEvent guardianInfo = getGuardianInfo(juvenileNum);

		if( guardianInfo != null )
		{
			myReportBean.setMemberRelationship(guardianInfo.getRelationToJuvenile());
			myReportBean.setGuardianName(new Name(guardianInfo.getFirstName(),
					guardianInfo.getMiddleName(), guardianInfo.getLastName()));

			Address guardianAddress = getGuardianLatestAddress(guardianInfo.getMemberNum());
			if( guardianAddress != null )
			{
				myReportBean.setGuardianAddress(guardianAddress);
			}
			else
			{
				return "error.inHomeGuardianMissingAddress";
			}
		}
		else
		{
			return "error.noInhomeGuardian";
		}

		GetAssignmentsByCasefileIdEvent getReferrals = (GetAssignmentsByCasefileIdEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		getReferrals.setCasefileId(casefileId);

		CompositeResponse refResponse = postRequestEvent(getReferrals);

		Collection collReferrals = MessageUtil.compositeToCollection(refResponse,
				AssignmentResponseEvent.class);

		StringBuffer petNums = new StringBuffer();

		if( collReferrals != null )
		{
			Iterator<AssignmentResponseEvent> iterReferrals = collReferrals.iterator();
			while( iterReferrals.hasNext() )
			{
				AssignmentResponseEvent assignments = iterReferrals.next();
				String referralNum = assignments.getReferralNum();
				if( notNullNotEmptyString( referralNum ) )
				{
					GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent)
							EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS);
					pet.setJuvenileNum(juvenileNum);
					pet.setReferralNum(referralNum);

					response = MessageUtil.postRequest(pet);
					Collection respPetitions = MessageUtil.compositeToCollection(
							response, JJSChargeResponseEvent.class);
					
					if( respPetitions != null )
					{
						Iterator<JJSChargeResponseEvent> iterPetitions = respPetitions.iterator();
						while( iterPetitions.hasNext() )
						{
							JJSChargeResponseEvent respP = iterPetitions.next();
							if( !petNums.equals(UIConstants.EMPTY_STRING) )
							{
								petNums.append(respP.getPetitionNum());
							}
							else
							{
								petNums.append(", ");
								petNums.append(respP.getPetitionNum());
							}
						}
					}
				}
			} // while
		}

		myReportBean.setPetitionNumber(petNums.toString());
		myReportBean.setCurrentDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
		
		return UIConstants.EMPTY_STRING;
	}

	/*
	 * 
	 */
	private Address getGuardianLatestAddress(String memberNumber)
	{
		Address guardianAddress = null ;

		GetFamilyMemberAddressEvent memberAddressEvent = (GetFamilyMemberAddressEvent)
				EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);
		memberAddressEvent.setMemberNum(memberNumber);

		CompositeResponse response = postRequestEvent(memberAddressEvent);
		Collection addressResponses = 
			MessageUtil.compositeToCollection(response,	AddressResponseEvent.class);

		if( notNullNotEmptyCollection( addressResponses ) )
		{	// Get the latest address of the guardian 
			// (when sorted, it's always the first one)
			Collections.sort((List)addressResponses);
			AddressResponseEvent addre = (AddressResponseEvent)addressResponses.iterator().next();

			guardianAddress = new Address() ;
			if( addre.getStreetNum() != null )
			{
				guardianAddress.setStreetNum(addre.getStreetNum());
				if (addre.getStreetNumSuffix() != null){
					StringBuilder sb = new StringBuilder(addre.getStreetNum());
					sb.append(" ");
					sb.append(addre.getStreetNumSuffix());
					guardianAddress.setStreetNum(sb.toString());
				}
			}
			guardianAddress.setStreetName(addre.getStreetName());
			guardianAddress.setStreetType(addre.getStreetType());
			guardianAddress.setAptNumber(addre.getAptNum());
			guardianAddress.setCity(addre.getCity());
			guardianAddress.setState(addre.getState());
			guardianAddress.setZipCode(addre.getZipCode());
			guardianAddress.setAdditionalZipCode(addre.getAdditionalZipCode());
		}

		return guardianAddress;
	}
	
	/*
	 * 
	 */
	private FamilyConstellationMemberListResponseEvent getGuardianInfo( String juvenileNum)
	{
		GetFamilyConstellationsEvent getFamilyEvent = (GetFamilyConstellationsEvent)
				EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
		getFamilyEvent.setJuvenileNum(juvenileNum);

		CompositeResponse response = postRequestEvent(getFamilyEvent);
		Collection coll = MessageUtil.compositeToCollection(response,
				FamilyConstellationMemberListResponseEvent.class);

		if( notNullNotEmptyCollection( coll ) )
		{
			Iterator<FamilyConstellationMemberListResponseEvent> iter = coll.iterator();
			while( iter.hasNext() )
			{
				FamilyConstellationMemberListResponseEvent fme = iter.next();
				if( fme.isGuardian() )
				{
					return fme;
				}
			}
		}

		return null;
	}

	/*
	 * 
	 */
	public ActionForward returnToNotifications(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		myClosingForm.setAction(UIConstants.EMPTY_STRING);
		
		return dispatch(getPath(aRequest));
	}

	/*
	 * 
	 */
	private String getPath(HttpServletRequest aRequest)
	{
		String portName = UIConstants.EMPTY_STRING + aRequest.getServerPort();
		String url = aRequest.getScheme() + "://" + aRequest.getServerName() + ":" +
				portName + "/appshell/displayHome.do";
		
		return url;
	}

	private ActionForward dispatch(String returnPath)
	{
		ActionForward newForward = new ActionForward(returnPath);
		newForward.setRedirect(true);
		
		return newForward;
	}

	/*
	 * 
	 */
	public ActionForward returnToCasefile(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward("returnToCasefile");
	}

	/*
	 * 
	 */
	public ActionForward interview(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
		String interviewId = myClosingForm.getSelectedValue();

		GetInterviewDetailEvent event = new GetInterviewDetailEvent();
		event.setInterviewId(interviewId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		Collection interviews = MessageUtil.compositeToCollection(
				(CompositeResponse)dispatch.getReply(), InterviewDetailResponseEvent.class);
		
		Iterator<InterviewDetailResponseEvent> iter = interviews.iterator();
		InterviewDetailResponseEvent detail = iter.next();

		JuvenileInterviewForm interviewForm = new JuvenileInterviewForm();
		interviewForm.getCurrentInterview().populateInterviewData(detail);
		// Set address status for user-entered addresses.
		if( detail.getJuvLocUnitId() == null )
		{
			interviewForm.setAddressStatus(detail.getAddress().getAddressStatus());
		}

		interviewForm.setStatus(UIConstants.VIEW);
		aRequest.setAttribute(UIConstants.ACTION, UIConstants.VIEW);
		aRequest.getSession(true).setAttribute("juvenileInterviewForm", interviewForm);
		
		JuvenilePhotoForm photoform = new JuvenilePhotoForm();
		aRequest.getSession(true).setAttribute("juvenilePhotoForm", photoform);

		return aMapping.findForward("interviewSuccess");
	}

	/*
	 * given a String, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ;
	}
	
	/*
	 * given a String, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyCollection( Collection col )
	{
		return( col != null  &&  !col.isEmpty() ) ;
	}

	private void approveClosing(CasefileClosingForm myClosingForm, JuvenileCasefileForm casefileForm) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateCasefileClosingEvent myEvent = 
				UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
						myClosingForm, UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED);
		myEvent.setSendForApproval(false);
		myEvent.setCasefileID(casefileForm.getSupervisionNum());
		
		dispatch.postEvent(myEvent);
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();

		MessageUtil.processReturnException(compositeResponse);
		CasefileClosingResponseEvent event = (CasefileClosingResponseEvent)
				MessageUtil.filterComposite(compositeResponse, CasefileClosingResponseEvent.class);

		casefileForm.setCaseStatusId(UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED);

		UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(),
				ActivityConstants.CASE_REVIEW_CLOSING_APPROVED, UIConstants.EMPTY_STRING);

		myClosingForm.setAction(UIConstants.CONFIRM);
		myClosingForm.setSecondaryAction(UIConstants.APPROVE);
		
		
		event.setSubject("Casefile closing has been approved for Juvenile # " +
				casefileForm.getJuvenileNum());
		event.setIdentity(casefileForm.getProbationOfficerLogonId());

		String s = casefileForm.getSupervisionNum() + " for " +
				casefileForm.getJuvenileName().getFormattedName() + ", Juvenile# " +
				casefileForm.getJuvenileNum() + " has been approved for closing";

		event.setNotificationMessage(s);
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)
				EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic("JC.APPROVE.CASEFILE.CLOSING.REQUEST");
		notificationEvent.setSubject(event.getSubject());
		notificationEvent.addIdentity("juvenileProbationOfficer", (IAddressable)event);
		notificationEvent.addContentBean(event);
		
		IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatchNotification.postEvent(notificationEvent);
	}
	
	private void closeCasefile(CasefileClosingForm myClosingForm, JuvenileCasefileForm casefileForm) {
		UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(),
				ActivityConstants.CASE_REVIEW_CLOSING_APPROVED, UIConstants.EMPTY_STRING);
		
		myClosingForm.setAction(UIConstants.CONFIRM);
		myClosingForm.setSecondaryAction(UIConstants.CLOSE);
		//send closing notice to JPO
		//update status on casefile
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateCasefileClosingEvent myEvent = 
				UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
						myClosingForm, UIConstants.CASEFILE_CASE_STATUS_CLOSED);
		
//		myEvent.setClosingPktGenerated(true);
//		myEvent.setClosingLetterGenerated(true);
		myEvent.setCasefileID(casefileForm.getSupervisionNum());
		myEvent.setCreate(false);
		myEvent.setApprovalForRequest(true);
		dispatch.postEvent(myEvent);
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		CasefileClosingResponseEvent event = (CasefileClosingResponseEvent)	MessageUtil.filterComposite(compositeResponse, CasefileClosingResponseEvent.class);

		casefileForm.setCaseStatusId(UIConstants.CASEFILE_CASE_STATUS_CLOSED);

		event.setSubject("Casefile#: " + casefileForm.getSupervisionNum() + " has been closed");
		event.setIdentity(casefileForm.getProbationOfficerLogonId());

		String s = "Casefile#: " + casefileForm.getSupervisionNum() + " for Juvenile: " +
				casefileForm.getJuvenileName().getFormattedName() + 
				" was closed by " + casefileForm.getCaseloadManagerName() +
				" on " + DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1);

		event.setNotificationMessage(s);
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)
				EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic("JC.CASEFILE.CLOSING");
		notificationEvent.setSubject(event.getSubject());
		notificationEvent.addIdentity("juvenileProbationOfficer", (IAddressable)event);
		notificationEvent.addContentBean(event);
		
		IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatchNotification.postEvent(notificationEvent);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.sendApprovalRequest", "sendApproval");
		keyMap.put("button.searchPage", "searchPage");
		keyMap.put("button.updateClosing", "updateClosing");
		keyMap.put("button.generateExitPlan", "exitPlan");
		keyMap.put("button.createUpdateExitPlan", "exitPlan");
		keyMap.put("button.commonApp", "commonApp");
		keyMap.put("button.approve", "approve");
		keyMap.put("button.reject", "reject");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.closingPacketEnglish", "closingPacketEnglish");
		keyMap.put("button.closingPacketSpanish", "closingPacketSpanish");
		keyMap.put("button.closingLetterEnglish", "closingLetterEnglishBFO");
		keyMap.put("button.closingLetterSpanish", "closingLetterSpanishBFO");
		keyMap.put("button.clientSatisfactionSurvey", "clientSatisfactionSurvey");
		keyMap.put("button.deferredAdjudicationLetter", "deferredAdjudicationLetter");
		keyMap.put("button.deferredProsecutionLetter", "deferredProsecutionLetter");
		keyMap.put("button.serviceProviderSatisfactionSurvey", "serviceProviderSatisfactionSurveyBFO");
		keyMap.put("button.returnToNotifications", "returnToNotifications");
		keyMap.put("button.returnToCasefile", "returnToCasefile");
		keyMap.put("button.interview", "interview");
		keyMap.put("button.generateDocument", "generateDocumentBFO");
	}

} // END CLASS
