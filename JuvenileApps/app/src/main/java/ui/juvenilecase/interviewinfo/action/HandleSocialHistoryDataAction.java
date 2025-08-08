package ui.juvenilecase.interviewinfo.action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.interviewinfo.CreateSocialHistoryReportEvent;
import messaging.interviewinfo.GetSocialHistoryReportDataEvent;
import messaging.interviewinfo.reply.MissingConstellationResponseEvent;
import messaging.interviewinfo.reply.SocialHistoryReportDataResponseEvent;
import messaging.interviewinfo.to.FamilyInformationTO;
import messaging.interviewinfo.to.MentalHealthTestResultTO;
import messaging.interviewinfo.to.OffenseInformationTO;
import messaging.interviewinfo.to.SchoolHistoryTO;
import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import messaging.interviewinfo.to.SupervisionRuleTO;
import messaging.interviewinfo.to.TraitTO;
import messaging.interviewinfo.to.WarrantInformationTO;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.mentalhealth.GetDSMIVResultsEvent;
import messaging.mentalhealth.GetMentalHealthAFResultsEvent;
import messaging.mentalhealth.GetMentalHealthAchievementResultsEvent;
import messaging.mentalhealth.GetMentalHealthIQResultsEvent;
import messaging.mentalhealth.reply.AFTestResponseEvent;
import messaging.mentalhealth.reply.ATTestResponseEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import messaging.mentalhealth.reply.IQTestResponseEvent;
import messaging.programreferral.GetProgramReferralDetailsEvent;
import messaging.programreferral.GetProgramReferralListEvent;
import messaging.programreferral.ProgramReferralRetrieverAttribute;
import messaging.programreferral.ProgramReferralRetrieverAttributeImpl;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent ;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.UIConstants;
import net.minidev.json.JSONObject;
import pd.juvenilecase.interviewinfo.InterviewDocument;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.report.transactions.PDFReporting;
import ui.action.JIMSBaseAction;
import ui.common.ActivityOldestFirstComparator;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.JuvenileDetentionFacility;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.activities.ActivitiesHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.interviewinfo.form.SocialHistoryForm;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author awidjaja
 * 
 */
public class HandleSocialHistoryDataAction extends JIMSBaseAction
{
	private final String JPO_DATA_TAB = "jpoData" ;
	private final String TAB_ID_PARM = "tabId" ;
	private final String REFERRAL_ID_PARM = "referralId" ;
	private final String JPO_DATA_SUCCESS = "jpoDataSuccess" ;
	private final String SUCCESS = "Success" ;
	private final String contactAdmin = "Please contact your System Administrator with a description of this problem." ;
	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.BACK) );
	}

	/*
	 * 
	 */
	public ActionForward viewSocialHistoryData(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm)aForm;
		form.setSocialHistoryData(new SocialHistoryReportDataTO());
		form.setSelectedJPOData(0);
		form.setActivities(new ArrayList());
		
		// Get casefileId from juvenileCasefileForm that's in session
		JuvenileCasefileForm casefileForm = UIJuvenileCaseworkHelper.getHeaderForm(aRequest, true);

		form.setCasefileId(casefileForm.getSupervisionNum());
		form.setJuvenileNum(casefileForm.getJuvenileNum());
		// Get Social History Data
		GetSocialHistoryReportDataEvent event = (GetSocialHistoryReportDataEvent)
				EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETSOCIALHISTORYREPORTDATA);
		event.setCasefileId(form.getCasefileId());
		event.setWarrantHistoryNeeded(true);
		 CompositeResponse composite = MessageUtil.postRequest(event);
		//List shDataList = MessageUtil.postRequestListFilter(event, SocialHistoryReportDataResponseEvent.class);
		MissingConstellationResponseEvent error = (MissingConstellationResponseEvent)MessageUtil.filterComposite(
                composite,MissingConstellationResponseEvent.class);
		if( error !=null )
		{
			// FIXME no results should show a relevant error message instead of
			// "error.common"
			ActionErrors errors = new ActionErrors();
			//errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.common"));
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.missingConstellation"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		List<SocialHistoryReportDataResponseEvent> shDataList = MessageUtil.compositeToList(composite, SocialHistoryReportDataResponseEvent.class);
		for( Iterator<SocialHistoryReportDataResponseEvent> iter = shDataList.iterator(); 
				iter.hasNext(); /*empty*/ )
		{
			SocialHistoryReportDataResponseEvent data = iter.next();
			SocialHistoryReportDataTO tObj = data.getTO();

			// Sort all of the collections
			Collections.sort(tObj.getGangTraits());
			Collections.sort(tObj.getStrengthTraits());
			Collections.sort(tObj.getSubstanceAbuseTraits());
			Collections.sort(tObj.getSubstanceAbuseInformation());
			Collections.sort(tObj.getEducationalHistory());
			Collections.sort(tObj.getEmploymentHistory());
			Collections.sort(tObj.getReferralHistory());
			Collections.reverse(tObj.getReferralHistory());
			
			String casefileId = casefileForm.getSupervisionNum();
			List activeReferrals = UIProgramReferralHelper.getActiveCasefileProgramReferrals(casefileId);
			if( activeReferrals.size() > 1 )
			{
				Collections.sort(activeReferrals);
			}
			form.setActiveReferralList(activeReferrals);

			List closedReferrals = UIProgramReferralHelper.getClosedCasefileProgramReferrals(casefileId);
			if( closedReferrals.size() > 1 )
			{
				Collections.sort(closedReferrals);
			}
			form.setClosedReferralList(closedReferrals);
			List myReferrals = activeReferrals;
			myReferrals.addAll(closedReferrals);

			if( myReferrals.size() > 1 )
			{
				Collections.sort(myReferrals);
			}
			form.setProgramReferralList(myReferrals);
			form.setSocialHistoryData(data.getTO());
			form.getSocialHistoryData().setProgramReferralList(myReferrals);
		}

		// Navigate to correct page
		form.setCurrentTab( JPO_DATA_TAB );

		// Get Activities of type EVS and POF
		ArrayList myList = new ArrayList();
		getActivities(myList, form);
		form.setActivities(myList);

		return aMapping.findForward( JPO_DATA_SUCCESS );
	}

	/*
	 * 
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm)aForm;
		JuvenileCasefileForm casefileForm = UIJuvenileCaseworkHelper.getHeaderForm(aRequest, true);
		
		form.setCasefileId(casefileForm.getSupervisionNum());
		form.setJuvenileNum(casefileForm.getJuvenileNum());
		
		String tabId = aRequest.getParameter( TAB_ID_PARM );
		form.setCurrentTab(tabId);

		if( tabId != null )
		{
			tabId = tabId + SUCCESS ;
		}
		else
		{
			tabId = JPO_DATA_SUCCESS ;
		}

		return aMapping.findForward(tabId);
	}

	/*
	 * 
	 */
	private void getActivities(List myList, SocialHistoryForm form)
	{
		if( myList == null )
		{
			myList = new ArrayList();
		}
		Collection myEVSCodes = ActivitiesHelper.getActivities(
				form.getCasefileId(), null, null, UIConstants.ACTIVITY_CODE_EVS, form.getJuvenileNum());
		Collections.sort((List)myEVSCodes, new ActivityOldestFirstComparator());
		myList.addAll(myEVSCodes);
		
		Collection myPOFCodes = ActivitiesHelper.getActivities(
				form.getCasefileId(), null, null, UIConstants.ACTIVITY_CODE_POF, form.getJuvenileNum());
		Collections.sort((List)myPOFCodes, new ActivityOldestFirstComparator());
		myList.addAll(myPOFCodes);
		
		return;
	}

	/*
	 * 
	 */
	public ActionForward saveChanges(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm)aForm;
		String tabId = form.getCurrentTab();

		if( tabId != null )
		{
			tabId = tabId + SUCCESS ;
		}
		else
		{
			tabId = JPO_DATA_SUCCESS ;
		}

		return aMapping.findForward(tabId);
	}
	
	/*
	 * 
	 */
	public ActionForward generateDraft(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	{
		
		SocialHistoryForm form = (SocialHistoryForm)aForm;
		String submitType= aRequest.getParameter("submitType");
		
		
		if( form.getSocialHistoryData().isGeneric()  || 
			form.getSocialHistoryData().isReportToRefereeInitiation()	)
		{
			boolean hasAtLeastOneIncluded = false;
			List familyInfo = form.getSocialHistoryData().getFamilyInformation(); 
			Iterator<FamilyInformationTO> familyInfoIter = familyInfo.iterator();
			while (familyInfoIter.hasNext())
			{
				if (familyInfoIter.next().isIncluded()) 
				{
					hasAtLeastOneIncluded = true;
				}
			} 
			
			if (!hasAtLeastOneIncluded) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.FamilyMembers"));
				saveErrors(aRequest, errors);
				if ("ajax".equals(submitType)){
				    JSONObject error = new JSONObject();
				    error.put("error","At least one family member must be included in the report.  Access the Juvenile Info tab and select at least one person from the Family Member section.");
				    aResponse.getWriter().println(error);
				    return null;
				} else {
				    return aMapping.findForward(JPO_DATA_SUCCESS); 
				}
			}
		}
		if( form.getSocialHistoryData().isReportToRefereeInitiation() )
		{
			//check for juvenile to be in detention	
			boolean inDetention = false;	
			//check for alleged offense to have been selected	
			boolean hasOneIncluded = false;
			List offenseInfo = form.getSocialHistoryData().getJuvenileOffenses(); 
			Iterator<OffenseInformationTO> offenseInfoIter = offenseInfo.iterator();
			while (offenseInfoIter.hasNext())
			{
				OffenseInformationTO offTo = offenseInfoIter.next();
				if (offTo.isPresentAllegedOffense()) 
				{
					hasOneIncluded = true;
					break;
				}
			} 
			
			if (!hasOneIncluded) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.Offense"));
				saveErrors(aRequest, errors);
				
				if ("ajax".equals(submitType)){
				    JSONObject error = new JSONObject();
				    error.put("error","At least one present alleged offense must be included in the report. Access the Present Offense tab and select at least one offense.");
				    aResponse.getWriter().println(error);
				    return null;
				} else {
				    return aMapping.findForward(JPO_DATA_SUCCESS); 
				}
			}
			//added for Bug #40594 
			/*if(form.getSocialHistoryData().getJuvenileFacilityStayRecord()!=null && form.getSocialHistoryData().getJuvenileFacilityStayRecord().size()>0)
			{
				Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
				if( obj.getClass().equals(JuvenileDetentionFacility.class) )
				{
					//build the Juvenile Stay records
					List tempStayList = InterviewHelper.buildJuvenileFacilityStayRecord(form.getJuvenileNum());
					form.getSocialHistoryData().setJuvenileFacilityStayRecord(tempStayList);
				}
			}*/ 
			/*	List facilityInfo = form.getSocialHistoryData().getJuvenileFacilityStayRecord(); 
			Iterator<JuvenileDetentionFacilitiesResponseEvent> facilityInfoIter = facilityInfo.iterator();
			while (facilityInfoIter.hasNext())
			{
				
				JuvenileDetentionFacilitiesResponseEvent juvDetFac = (JuvenileDetentionFacilitiesResponseEvent)facilityInfoIter.next();
				if (juvDetFac!=null && (juvDetFac.getReleaseDate() == null ||
						juvDetFac.getReleaseDate().equals(""))) 
				{
					inDetention = true;
					break;
				}
			} */ //above block commented to add the  code below for task 47901; US 38921

			boolean reportToreferee = false;
			String juvNumber = form.getJuvenileNum();
			JuvenileDetentionFacilitiesResponseEvent facilityAdmissionInfo =JuvenileFacilityHelper.getInFacilityDetails(juvNumber);
			Collection<JuvenileFacilityResponseEvent> allFacilities = JuvenileFacilityHelper.loadAllFacilities();
			if (facilityAdmissionInfo.getAdmitDate() != null){
			if (facilityAdmissionInfo.getReleaseDate()== null){
			    inDetention = true;
			    String facilityCode = facilityAdmissionInfo.getDetainedFacility();
			    String juvPlacementTypeTJPC = JuvenileFacilityHelper.getFacilityJuvTJPCPlacementType(allFacilities, facilityCode);
			    if (juvPlacementTypeTJPC!= null){
				if (juvPlacementTypeTJPC.equalsIgnoreCase("D") || juvPlacementTypeTJPC.equalsIgnoreCase("H") || juvPlacementTypeTJPC.equalsIgnoreCase("E")){
				    reportToreferee = true;
				    Collection facilityInfo = JuvenileFacilityHelper.getJuvFacilityDetails(juvNumber,null);
				    Collections.sort((List)facilityInfo, JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator);
				    Collections.reverse((List)facilityInfo);
				    form.getSocialHistoryData().setJuvenileFacilityStayRecord((List) facilityInfo);
				}	
			    }
			    Date detAdmitDate = facilityAdmissionInfo.getAdmitDate();
			    String detentionAdmitDate = DateUtil.dateToString(detAdmitDate, "MM/dd/yyyy");
			    form.getSocialHistoryData().setDetentionAdmitDate(detentionAdmitDate);
			}
			}
			if (!inDetention) {
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.Detention"));
			    saveErrors(aRequest, errors);
			    if ("ajax".equals(submitType)){
				JSONObject error = new JSONObject();
				error.put("error","Juvenile is not currently in a facility.  The Report to Referee Initiation cannot be generated unless the juvenile is in a facility.");
				aResponse.getWriter().println(error);
				return null;
			    } else {
				return aMapping.findForward(JPO_DATA_SUCCESS); 
			    }
			     
			}
			
			if (!reportToreferee) {
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.TJPC"));
			    saveErrors(aRequest, errors);
			    if ("ajax".equals(submitType)){
				JSONObject error = new JSONObject();
				error.put("error","The Report to Referee Initiation can be generated only when the juvenile is in a facility with 'Detention', 'Hospital', or 'Emergency Shelter' status.");
				aResponse.getWriter().println(error);
				return null;
			    } else {
				return aMapping.findForward(JPO_DATA_SUCCESS); 
			    }
			}
			
		}
		
		// Notified by is whoever the current logged on user is.
		IUserInfo user = SecurityUIHelper.getUser();
		form.getSocialHistoryData().setCurrentUser(new Name(user.getFirstName(), user.getMiddleName(), user.getLastName()).getFormattedName());

		// Get Activities of type EVS and populate SocialHistoryDataTO
		// This is for Significant Information section of the report
		List evsActivitiesComments = form.getSocialHistoryData().getEvsActivityComments();
		List pofActivities = new ArrayList();
		form.getSocialHistoryData().setPofActivities(pofActivities);
		evsActivitiesComments.clear();

		GetActivitiesEvent getActivitiesEvent = (GetActivitiesEvent)
				EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
		getActivitiesEvent.setActivityCodeId(ActivityConstants.EVALUATIVE_SUMMARY);
		getActivitiesEvent.setCasefileID(form.getCasefileId());
		getActivitiesEvent.setJuvenileNum(form.getJuvenileNum());

		CompositeResponse compositeResponse = postRequestEvent(getActivitiesEvent);
		
		{ Collection activityResults = MessageUtil.compositeToCollection(
				compositeResponse, ActivityResponseEvent.class);
			if( activityResults != null && activityResults.size() > 0 )
			{
				for( Iterator iter = activityResults.iterator(); iter.hasNext(); )
				{
					ActivityResponseEvent activity = (ActivityResponseEvent)iter.next();
					evsActivitiesComments.add(activity);
				}
			}
		}

		Collections.sort((List)evsActivitiesComments, new ActivityOldestFirstComparator());

		GetActivitiesEvent getPofActivitiesEvent = (GetActivitiesEvent)
				EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
		getPofActivitiesEvent.setActivityCodeId(UIConstants.ACTIVITY_CODE_POF);
		getPofActivitiesEvent.setCasefileID(form.getCasefileId());
		getPofActivitiesEvent.setJuvenileNum(form.getJuvenileNum());

		compositeResponse = postRequestEvent(getPofActivitiesEvent);
		
		{ Collection activityResults = MessageUtil.compositeToCollection(
				compositeResponse, ActivityResponseEvent.class);
			if( activityResults != null && activityResults.size() > 0 )
			{
				for( Iterator iter = activityResults.iterator(); iter.hasNext(); )
				{
					ActivityResponseEvent activity = (ActivityResponseEvent)iter.next();
					pofActivities.add(activity);
				}
			}
		}
		Collections.sort((List)pofActivities, new ActivityOldestFirstComparator());

		if( !form.getSocialHistoryData().isGeneric() ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			form.getSocialHistoryData().setMentalHealthTestingHistory(
					getMentalHealthTestingHistory(form.getJuvenileNum()));

			GetProgramReferralListEvent gpre = (GetProgramReferralListEvent)EventFactory.getInstance(
					JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);
			ProgramReferralRetrieverAttribute casefileAttr = new ProgramReferralRetrieverAttributeImpl(
					ProgramReferralRetrieverAttribute.CASEFILE);
			casefileAttr.setAttributeValue(form.getCasefileId());

			List attributeList = new ArrayList();
			attributeList.add(casefileAttr);
			gpre.setReferralAttributes(attributeList);
			gpre.setDetailsNeeded(false);

			form.getSocialHistoryData().setProgramReferrals(form.getProgramReferralList());
		}
		//following block was commented as part of bug 50700. JuvenileFacilityStayRecord list is set again below.  
	/*	List orgList = new ArrayList();  // temporary preserve original data in JuvenileDetentionFacilitiesResponseEvent.class
		if( form.getSocialHistoryData().getJuvenileFacilityStayRecord() != null && 
				form.getSocialHistoryData().getJuvenileFacilityStayRecord().size() > 0 )
		{
			Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
			// Since we're using the same list to transfer data from PD -> UI, then UI -> UJAC, 
			// we need to know when to translate the object from ResponseEvent to UI Obj.
			if( obj.getClass().equals(JuvenileDetentionFacilitiesResponseEvent.class) )
			{
				List uiList = new ArrayList();
				int size = form.getSocialHistoryData().getJuvenileFacilityStayRecord().size();
				for( int i = 0; i < size; i++ )
				{
					JuvenileDetentionFacilitiesResponseEvent re = (JuvenileDetentionFacilitiesResponseEvent)
							form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(i);
					uiList.add(new JuvenileDetentionFacility(re));
					orgList.add(re);
				}
				form.getSocialHistoryData().setJuvenileFacilityStayRecord(uiList);
			}
		}*/ 
		
		//Rebuilds the school history if report request is "generic".
		//Remove all school history except for most recent.
	if( form.getSocialHistoryData().isGeneric() ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			List existingSchoolHistory = form.getSocialHistoryData().getEducationalHistory();
			List newSchoolHistory = UIJuvenileHelper.buildSchoolHistory(form.getJuvenileNum());
			Collections.sort(newSchoolHistory);
			List finalSchoolHistory = new ArrayList();
			
			//Remove Old History
			Iterator<SchoolHistoryTO> itr1 = existingSchoolHistory.iterator();
			if (itr1.hasNext())
			{
				//Remove all records
				form.getSocialHistoryData().getEducationalHistory().removeAll(existingSchoolHistory);
			} 
			
			//Add only the most recent school history record
			Iterator<SchoolHistoryTO> itr2 = newSchoolHistory.iterator();
			if (itr2.hasNext())
			{
				//Get first record (most recent school history)
				SchoolHistoryTO to = itr2.next();
				finalSchoolHistory.add(to);
				//Add first record to education history
				form.getSocialHistoryData().getEducationalHistory().addAll(finalSchoolHistory);
			} 
			
		} 
		
	if(form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			//Rebuilds the warrant history if report request is "report to Referee Initiation".
			//Remove all warrants except for those successfully served within 3 days of the detention admit date.
			List existingWarrantHistory = form.getSocialHistoryData().getWarrantHistory();
			List finalWarrantHistory = new ArrayList();
			
			//Add only the successful warrants within 3 days of the detention admit date
			Iterator<WarrantInformationTO> itr2 = existingWarrantHistory.iterator();
			while (itr2.hasNext())
			{
				//Check dates and status on warrant
				WarrantInformationTO to = itr2.next();
				if (to.getServiceStatus().equals("SUCCESSFUL"))
				{
					Date serviceDate = to.getServiceDate();
					Date detentionAdmitDate = DateUtil.stringToDate(form.getSocialHistoryData().getDetentionAdmitDate(), "MM/dd/yyyy");
					if(detentionAdmitDate.after(serviceDate)){
						double days = (double) (detentionAdmitDate.getTime() - serviceDate.getTime()) / (24*60*60*1000);
						if(days > 3.0 ){
							continue;
						}else {
							finalWarrantHistory.add(to);
						}
					}
					if(serviceDate.after(detentionAdmitDate)){
						double days = (double) (serviceDate.getTime() - detentionAdmitDate.getTime()) / (24*60*60*1000);
						if(days > 3.0 ){
							continue;
						}else {
							finalWarrantHistory.add(to);
						}
					}
				}
			} 
			form.getSocialHistoryData().getRefInitiationWarrantHistory().addAll(finalWarrantHistory);			
		} 
	if( form.getSocialHistoryData().isGeneric()) {
		//code below added for US 11094. Task#41503
		String juvNum = form.getJuvenileNum();
		JuvenileDetentionFacilitiesResponseEvent facilityAdmissionInfo =JuvenileFacilityHelper.getInFacilityDetails(juvNum);
		boolean isInFacility = false;
		Collection<JuvenileFacilityResponseEvent> allFacilities = JuvenileFacilityHelper.loadAllFacilities();
		if (facilityAdmissionInfo.getReleaseDate()== null){
		    //get the TJPC Code for the corresponding DETAINEDFACILITY (facility code) from M204
		    String facilityCode = facilityAdmissionInfo.getDetainedFacility();
		    String juvPlacementTypeTJPC = JuvenileFacilityHelper.getFacilityJuvTJPCPlacementType(allFacilities, facilityCode);
		    
		    if (juvPlacementTypeTJPC!= null && !juvPlacementTypeTJPC.equalsIgnoreCase("D") ){
			isInFacility = true;
			JuvenileFacilityResponseEvent facilityInfo = JuvenileFacilityHelper.getFacility(allFacilities, facilityCode);
			String facilityName = facilityInfo.getDescription();
			form.getSocialHistoryData().setFacilityName(facilityName);
			form.getSocialHistoryData().setInFacility(isInFacility);
			}
	//code below added to fix the missing admit date on the report
		    Date detAdmitDate = facilityAdmissionInfo.getAdmitDate();
		    String detentionAdmitDate = DateUtil.dateToString(detAdmitDate, "MM/dd/yyyy");
		    form.getSocialHistoryData().setDetentionAdmitDate(detentionAdmitDate);
		}
			
	//code below added for US 11078, Task# 47899 //NOTE: always use detainedFacility and not the facility name
		Collection facilityInfo = JuvenileFacilityHelper.getJuvFacilityDetails(juvNum,null);
		Collection<JuvenileDetentionFacilitiesResponseEvent> editedFacilityList = new ArrayList<JuvenileDetentionFacilitiesResponseEvent>();
		Iterator<JuvenileDetentionFacilitiesResponseEvent> facilityInfoIter = facilityInfo.iterator();
		while (facilityInfoIter.hasNext()){
		    JuvenileDetentionFacilitiesResponseEvent faciityInfoDetail = facilityInfoIter.next();
		    String facilityCode = faciityInfoDetail.getDetainedFacility();
		    String juvPlacementTypeTJPC = JuvenileFacilityHelper.getFacilityJuvTJPCPlacementType(allFacilities, facilityCode);
		   if (juvPlacementTypeTJPC!= null && !juvPlacementTypeTJPC.equalsIgnoreCase("D") ){
			 SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
			    Date admitDate = faciityInfoDetail.getAdmitDate();
			    Date releaseDate = faciityInfoDetail.getReleaseDate();
			    if (admitDate!= null){
				faciityInfoDetail.setAdmitDateStr(sdf.format(admitDate));
			    }
			    if (releaseDate!=null){
				faciityInfoDetail.setReleaseDateStr(sdf.format(releaseDate));
			    }
			editedFacilityList.add(faciityInfoDetail);
			
		    }
		}
		
		Collections.sort((List)editedFacilityList, JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator);
		Collections.reverse((List)editedFacilityList);
		form.getSocialHistoryData().setFacilityHistoryInfo((List)editedFacilityList);
	}
		
		CreateSocialHistoryReportEvent reqEvent = new CreateSocialHistoryReportEvent();
		reqEvent.setCasefileId(form.getCasefileId());
		reqEvent.setSocialHistoryReportDataTO(form.getSocialHistoryData());
		if( form.getSocialHistoryData().isGeneric() )
		{
			reqEvent.setGeneric(true);
		}
		if( form.getSocialHistoryData().isReportToRefereeInitiation() )
		{
			reqEvent.setReportToReferee(true);
		}
		
		
		
		// Add record in activity table
		UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.SOCIAL_HISTORY_REPORT_GENERATED, "");

		// remove the date/time/user stamp in the various Comment fields 
		removeDateTimeUserFromComments( reqEvent ) ;		
		
		String casefileId = reqEvent.getCasefileId();
		SocialHistoryReportDataTO reportTO = reqEvent.getSocialHistoryReportDataTO();
		reportTO.setDraft("Y");
		
		// Prepare report fields. 
		InterviewHelper.prepareSocialHistoryReportData( reportTO, casefileId, reqEvent.isReportToReferee() );		
		aRequest.getSession().setAttribute("reportInfo", reportTO);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		
		String docTypeCodeId = "SHR";  //default value 
		// Build report.
		if( reqEvent.isGeneric() )
		{
			reqEvent.setReportName("REPORTING::SOCIAL_HISTORY_REPORT_GENERIC");
			docTypeCodeId = "CRS"; // CRIS report
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SOCIAL_HISTORY_REPORT_GENERIC);
		} else if (reqEvent.isReportToReferee()) {
			reqEvent.setReportName("REPORTING::SOCIAL_HISTORY_REFEREE_TO_INITIATION");
			docTypeCodeId = "RTR";  // Report to Referee
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.REPORT_TO_REFEREE_INITIATION);
			} else {
				reqEvent.setReportName("REPORTING::SOCIAL_HISTORY_REPORT");
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SOCIAL_HISTORY_REPORT);
		}
		reqEvent.addDataObject(reportTO);
		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
		if( pdfDocument == null || pdfDocument.length < 1 )
		{
			sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
			
			if ("ajax".equals(submitType)){
			    JSONObject error = new JSONObject();
			    error.put("error","Problems generating report. " + contactAdmin);
			    aResponse.getWriter().println(error);
			    aResponse.getWriter().println("Problems generating report. " + contactAdmin);
			    return null;
			} else {
			    return aMapping.findForward(UIConstants.FAILURE);
			}
		}
		IReport report  = PDFReporting.getInstance();
		IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
		ReportResponseEvent respEvent = new ReportResponseEvent();
		try
		{
			respEvent.setContent(pdfDocument);
			respEvent.setContentType(report.getContentType());
			respEvent.setFileName(report.getTemplate());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw(e);
		}	
		InterviewDocument document = new InterviewDocument();
		document.setDocument( respEvent.getContent() );
		document.setCasefileId( casefileId );
		document.setDocumentTypeCodeId(docTypeCodeId );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 	
		dispatch.postEvent(respEvent);
		//Rebuilds original school list after report is generated so all school 
		//history shows up in the school tab 
		if( form.getSocialHistoryData().isGeneric()  ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			List existingSchoolHistory = form.getSocialHistoryData().getEducationalHistory();
			List newSchoolHistory = UIJuvenileHelper.buildSchoolHistory(form.getJuvenileNum());
			Collections.sort(newSchoolHistory);
			
			form.getSocialHistoryData().getEducationalHistory().removeAll(existingSchoolHistory);
			form.getSocialHistoryData().getEducationalHistory().addAll(newSchoolHistory);
		}
		//Commenting the code below; bug 50700. JuvenileFacilityStayRecord list is getting reset here.
		/*// convert from JuvenileDetentionFacility back to original JuvenileDetentionFacilitiesResponseEvent in case
		// user selects to generate another report. This should prevent ClassCastException
		if( form.getSocialHistoryData().getJuvenileFacilityStayRecord() != null && 
				form.getSocialHistoryData().getJuvenileFacilityStayRecord().size() > 0 )
		{
			Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
			if( obj.getClass().equals(JuvenileDetentionFacility.class) )
			{
				form.getSocialHistoryData().setJuvenileFacilityStayRecord(orgList);
				orgList = null;
			}
		}		*/
		// dont need to forward since response is already committed.
		return null;
	}
	
	/*
	 * 
	 */
	public ActionForward generateReport(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	{
		
		SocialHistoryForm form = (SocialHistoryForm)aForm;
		String submitType= aRequest.getParameter("submitType");
		System.out.println("Submit type: " + submitType );

		if( form.getSocialHistoryData().isGeneric()  || 
			form.getSocialHistoryData().isReportToRefereeInitiation()	)
		{
			boolean hasAtLeastOneIncluded = false;
			List familyInfo = form.getSocialHistoryData().getFamilyInformation(); 
			Iterator<FamilyInformationTO> familyInfoIter = familyInfo.iterator();
			while (familyInfoIter.hasNext())
			{
				if (familyInfoIter.next().isIncluded()) 
				{
					hasAtLeastOneIncluded = true;
				}
			} 
			
			if (!hasAtLeastOneIncluded) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.FamilyMembers"));
				saveErrors(aRequest, errors);
				if ("ajax".equals(submitType)){
				    JSONObject error = new JSONObject();
				    error.put("error","At least one family member must be included in the report.  Access the Juvenile Info tab and select at least one person from the Family Member section.");
				    aResponse.getWriter().println(error.toString());
				    return null;
				} else {
				    return aMapping.findForward(JPO_DATA_SUCCESS); 
				}
				
			}
		}
		if( form.getSocialHistoryData().isReportToRefereeInitiation() )
		{
			//check for juvenile to be in detention	
		    boolean inDetention = false;
			//check for alleged offense to have been selected	
			boolean hasOneIncluded = false;
			List offenseInfo = form.getSocialHistoryData().getJuvenileOffenses(); 
			Iterator<OffenseInformationTO> offenseInfoIter = offenseInfo.iterator();
			while (offenseInfoIter.hasNext())
			{
				OffenseInformationTO offTo = offenseInfoIter.next();
				if (offTo.isPresentAllegedOffense()) 
				{
					hasOneIncluded = true;
				}
			} 
			
			if (!hasOneIncluded) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.Offense"));
				saveErrors(aRequest, errors);
				if ("ajax".equals(submitType)){
				    JSONObject error = new JSONObject();
				    error.put("error","At least one present alleged offense must be included in the report. Access the Present Offense tab and select at least one offense.");
				    aResponse.getWriter().println(error.toString());
				    return null;
				} else {
				    return aMapping.findForward(JPO_DATA_SUCCESS); 
				}
				
				
			}
		/*	//added for Bug #40594 
			if(form.getSocialHistoryData().getJuvenileFacilityStayRecord()!=null && form.getSocialHistoryData().getJuvenileFacilityStayRecord().size()>0)
			{
				Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
				if( obj.getClass().equals(JuvenileDetentionFacility.class) )
				{
					//build the Juvenile Stay records
					List tempStayList = InterviewHelper.buildJuvenileFacilityStayRecord(form.getJuvenileNum());
					form.getSocialHistoryData().setJuvenileFacilityStayRecord(tempStayList);
				}
			}
			List facilityInfo = form.getSocialHistoryData().getJuvenileFacilityStayRecord(); 
			Iterator<JuvenileDetentionFacilitiesResponseEvent> facilityInfoIter = facilityInfo.iterator();
			while (facilityInfoIter.hasNext())
			{
				JuvenileDetentionFacilitiesResponseEvent juvDetFac = (JuvenileDetentionFacilitiesResponseEvent)facilityInfoIter.next();
				if (juvDetFac.getReleaseDate() == null ||
						juvDetFac.getReleaseDate().equals("")) 
				{
					inDetention = true;
					break;
				}
			}*/ //above block is commented to add the  code below for task 47901; US 38921, Getting info from SQL; see below
		boolean reportToreferee = false;
		String juvNumber = form.getJuvenileNum();
		JuvenileDetentionFacilitiesResponseEvent facilityAdmissionInfo =JuvenileFacilityHelper.getInFacilityDetails(juvNumber);
		Collection<JuvenileFacilityResponseEvent> allFacilities = JuvenileFacilityHelper.loadAllFacilities();
		if (facilityAdmissionInfo.getAdmitDate() != null){
		    if (facilityAdmissionInfo.getReleaseDate()== null){
			inDetention = true;
			String facilityCode = facilityAdmissionInfo.getDetainedFacility();
			String juvPlacementTypeTJPC = JuvenileFacilityHelper.getFacilityJuvTJPCPlacementType(allFacilities, facilityCode);
			if (juvPlacementTypeTJPC!= null){
			    if (juvPlacementTypeTJPC.equalsIgnoreCase("D") || juvPlacementTypeTJPC.equalsIgnoreCase("H") || juvPlacementTypeTJPC.equalsIgnoreCase("E")){
				reportToreferee = true;
				Collection facilityInfo = JuvenileFacilityHelper.getJuvFacilityDetails(juvNumber,null);
				Collections.sort((List)facilityInfo, JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator);
				Collections.reverse((List)facilityInfo);
				form.getSocialHistoryData().setJuvenileFacilityStayRecord((List) facilityInfo);
			    }	
			}
			Date detAdmitDate = facilityAdmissionInfo.getAdmitDate();
			String detentionAdmitDate = DateUtil.dateToString(detAdmitDate, "MM/dd/yyyy");
			form.getSocialHistoryData().setDetentionAdmitDate(detentionAdmitDate);
		    }
		}
    		if (!inDetention) {
    		    ActionErrors errors = new ActionErrors();
    		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.Detention"));
    		    saveErrors(aRequest, errors);
    		    if ("ajax".equals(submitType)){
    			JSONObject error = new JSONObject();
    			error.put("error","Juvenile is not currently in a facility.  The Report to Referee Initiation cannot be generated unless the juvenile is in a facility.");
    			aResponse.getWriter().println(error.toString());
    			return null;
    		    } else {
    			return aMapping.findForward(JPO_DATA_SUCCESS); 
    		    }
    		    
    		}
    			
    		if (!reportToreferee) {
    		    ActionErrors errors = new ActionErrors();
    		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.TJPC"));
    		    saveErrors(aRequest, errors);
    		    if ("ajax".equals(submitType)){
    			JSONObject error = new JSONObject();
    			error.put("error","The Report to Referee Initiation can be generated only when the juvenile is in a facility with 'Detention', 'Hospital', or 'Emergency Shelter' status.");
 			aResponse.getWriter().println(error.toString());
 			return null;
 		    } else {
 			return aMapping.findForward(JPO_DATA_SUCCESS); 
 		    }
    		}
		
		}
		
		// Notified by is whoever the current logged on user is.
		IUserInfo user = SecurityUIHelper.getUser();
		form.getSocialHistoryData().setCurrentUser(new Name(user.getFirstName(), user.getMiddleName(), user.getLastName()).getFormattedName());

		// Get Activities of type EVS and populate SocialHistoryDataTO
		// This is for Significant Information section of the report
		List evsActivitiesComments = form.getSocialHistoryData().getEvsActivityComments();
		List pofActivities = new ArrayList();
		form.getSocialHistoryData().setPofActivities(pofActivities);
		evsActivitiesComments.clear();

		GetActivitiesEvent getActivitiesEvent = (GetActivitiesEvent)
				EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
		getActivitiesEvent.setActivityCodeId(ActivityConstants.EVALUATIVE_SUMMARY);
		getActivitiesEvent.setCasefileID(form.getCasefileId());
		getActivitiesEvent.setJuvenileNum(form.getJuvenileNum());

		CompositeResponse compositeResponse = postRequestEvent(getActivitiesEvent);
		
		{ Collection activityResults = MessageUtil.compositeToCollection(
				compositeResponse, ActivityResponseEvent.class);
			if( activityResults != null && activityResults.size() > 0 )
			{
				for( Iterator iter = activityResults.iterator(); iter.hasNext(); )
				{
					ActivityResponseEvent activity = (ActivityResponseEvent)iter.next();
					evsActivitiesComments.add(activity);
				}
			}
		}

		Collections.sort((List)evsActivitiesComments, new ActivityOldestFirstComparator());

		GetActivitiesEvent getPofActivitiesEvent = (GetActivitiesEvent)
				EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
		getPofActivitiesEvent.setActivityCodeId(UIConstants.ACTIVITY_CODE_POF);
		getPofActivitiesEvent.setCasefileID(form.getCasefileId());
		getPofActivitiesEvent.setJuvenileNum(form.getJuvenileNum());

		compositeResponse = postRequestEvent(getPofActivitiesEvent);
		
		{ Collection activityResults = MessageUtil.compositeToCollection(
				compositeResponse, ActivityResponseEvent.class);
			if( activityResults != null && activityResults.size() > 0 )
			{
				for( Iterator iter = activityResults.iterator(); iter.hasNext(); )
				{
					ActivityResponseEvent activity = (ActivityResponseEvent)iter.next();
					pofActivities.add(activity);
				}
			}
		}
		Collections.sort((List)pofActivities, new ActivityOldestFirstComparator());

		if( !form.getSocialHistoryData().isGeneric() ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			form.getSocialHistoryData().setMentalHealthTestingHistory(
					getMentalHealthTestingHistory(form.getJuvenileNum()));

			GetProgramReferralListEvent gpre = (GetProgramReferralListEvent)EventFactory.getInstance(
					JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);
			ProgramReferralRetrieverAttribute casefileAttr = new ProgramReferralRetrieverAttributeImpl(
					ProgramReferralRetrieverAttribute.CASEFILE);
			casefileAttr.setAttributeValue(form.getCasefileId());

			List attributeList = new ArrayList();
			attributeList.add(casefileAttr);
			gpre.setReferralAttributes(attributeList);
			gpre.setDetailsNeeded(false);

			form.getSocialHistoryData().setProgramReferrals(form.getProgramReferralList());
		}
		
		/*List orgList = new ArrayList();  // temporary preserve original data in JuvenileDetentionFacilitiesResponseEvent.class
		if( form.getSocialHistoryData().getJuvenileFacilityStayRecord() != null && 
				form.getSocialHistoryData().getJuvenileFacilityStayRecord().size() > 0 )
		{
			Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
			// Since we're using the same list to transfer data from PD -> UI, then UI -> UJAC, 
			// we need to know when to translate the object from ResponseEvent to UI Obj.
			if( obj.getClass().equals(JuvenileDetentionFacilitiesResponseEvent.class) )
			{
				List uiList = new ArrayList();
				int size = form.getSocialHistoryData().getJuvenileFacilityStayRecord().size();
				for( int i = 0; i < size; i++ )
				{
					JuvenileDetentionFacilitiesResponseEvent re = (JuvenileDetentionFacilitiesResponseEvent)
							form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(i);
					uiList.add(new JuvenileDetentionFacility(re));
					orgList.add(re);
				}
				form.getSocialHistoryData().setJuvenileFacilityStayRecord(uiList);
			}
		}*/
		
		//Rebuilds the school history if report request is "generic".
		//Remove all school history except for most recent.
		if( form.getSocialHistoryData().isGeneric() ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			List existingSchoolHistory = form.getSocialHistoryData().getEducationalHistory();
			List newSchoolHistory = UIJuvenileHelper.buildSchoolHistory(form.getJuvenileNum());
			Collections.sort(newSchoolHistory);
			List finalSchoolHistory = new ArrayList();
			
			//Remove Old History
			Iterator<SchoolHistoryTO> itr1 = existingSchoolHistory.iterator();
			if (itr1.hasNext())
			{
				//Remove all records
				form.getSocialHistoryData().getEducationalHistory().removeAll(existingSchoolHistory);
			} 
			
			//Add only the most recent school history record
			Iterator<SchoolHistoryTO> itr2 = newSchoolHistory.iterator();
			if (itr2.hasNext())
			{
				//Get first record (most recent school history)
				SchoolHistoryTO to = itr2.next();
				finalSchoolHistory.add(to);
				//Add first record to education history
				form.getSocialHistoryData().getEducationalHistory().addAll(finalSchoolHistory);
			} 
			
		} 
		
		if(form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			//Rebuilds the warrant history if report request is "report to Referee Initiation".
			//Remove all warrants except for those successfully served within 3 days of the detention admit date.
			List existingWarrantHistory = form.getSocialHistoryData().getWarrantHistory();
			List finalWarrantHistory = new ArrayList();
			
			//Add only the successful warrants within 3 days of the detention admit date
			Iterator<WarrantInformationTO> itr2 = existingWarrantHistory.iterator();
			while (itr2.hasNext())
			{
				//Check dates and status on warrant
				WarrantInformationTO to = itr2.next();
				if (to.getServiceStatus().equals("SUCCESSFUL"))
				{
					Date serviceDate = to.getServiceDate();
					Date detentionAdmitDate = DateUtil.stringToDate(form.getSocialHistoryData().getDetentionAdmitDate(), "MM/dd/yyyy");
					if(detentionAdmitDate.after(serviceDate)){
						double days = (double) (detentionAdmitDate.getTime() - serviceDate.getTime()) / (24*60*60*1000);
						if(days > 3.0 ){
							continue;
						}else {
							finalWarrantHistory.add(to);
						}
					}
					if(serviceDate.after(detentionAdmitDate)){
						double days = (double) (serviceDate.getTime() - detentionAdmitDate.getTime()) / (24*60*60*1000);
						if(days > 3.0 ){
							continue;
						}else {
							finalWarrantHistory.add(to);
						}
					}
				}
			} 
			form.getSocialHistoryData().getRefInitiationWarrantHistory().addAll(finalWarrantHistory);			
		} 
		
    	if( form.getSocialHistoryData().isGeneric()) {
    		//code below added for US 11094. Task#41503
    		String juvNum = form.getJuvenileNum();
    		JuvenileDetentionFacilitiesResponseEvent facilityAdmissionInfo =JuvenileFacilityHelper.getInFacilityDetails(juvNum);
    		boolean isInFacility = false;
    		Collection<JuvenileFacilityResponseEvent> allFacilities = JuvenileFacilityHelper.loadAllFacilities();
    		if (facilityAdmissionInfo.getReleaseDate()== null){
    		    //get the TJPC Code for the corresponding DETAINEDFACILITY (facility code) from M204
    		    String facilityCode = facilityAdmissionInfo.getDetainedFacility();
    		    String juvPlacementTypeTJPC = JuvenileFacilityHelper.getFacilityJuvTJPCPlacementType(allFacilities, facilityCode);
    		    
    		    if (juvPlacementTypeTJPC!= null && !juvPlacementTypeTJPC.equalsIgnoreCase("D") ){
    			isInFacility = true;
    			JuvenileFacilityResponseEvent facilityInfo = JuvenileFacilityHelper.getFacility(allFacilities, facilityCode);
    			String facilityName = facilityInfo.getDescription();
    			form.getSocialHistoryData().setFacilityName(facilityName);
    			form.getSocialHistoryData().setInFacility(isInFacility);
    			}
    		  //code below added to fix the missing admit date on the report
    		    Date detAdmitDate = facilityAdmissionInfo.getAdmitDate();
    		    String detentionAdmitDate = DateUtil.dateToString(detAdmitDate, "MM/dd/yyyy");
    		    form.getSocialHistoryData().setDetentionAdmitDate(detentionAdmitDate);
    		}
    			
    		//code below added for US 11078, Task# 47899 //NOTE: always use detainedFacility and not the facility name
    		Collection facilityInfo = JuvenileFacilityHelper.getJuvFacilityDetails(juvNum,null);
    		Collection<JuvenileDetentionFacilitiesResponseEvent> editedFacilityList = new ArrayList<JuvenileDetentionFacilitiesResponseEvent>();
    		Iterator<JuvenileDetentionFacilitiesResponseEvent> facilityInfoIter = facilityInfo.iterator();
    		while (facilityInfoIter.hasNext()){
    		    JuvenileDetentionFacilitiesResponseEvent faciityInfoDetail = facilityInfoIter.next();
    		    String facilityCode = faciityInfoDetail.getDetainedFacility();
    		    String juvPlacementTypeTJPC = JuvenileFacilityHelper.getFacilityJuvTJPCPlacementType(allFacilities, facilityCode);
    		   if (juvPlacementTypeTJPC!= null && !juvPlacementTypeTJPC.equalsIgnoreCase("D") ){
    			 SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
    			    Date admitDate = faciityInfoDetail.getAdmitDate();
    			    Date releaseDate = faciityInfoDetail.getReleaseDate();
    			    if (admitDate!= null){
    				faciityInfoDetail.setAdmitDateStr(sdf.format(admitDate));
    			    }
    			    if (releaseDate!=null){
    				faciityInfoDetail.setReleaseDateStr(sdf.format(releaseDate));
    			    }
    			    editedFacilityList.add(faciityInfoDetail);
    			    
    		   }
    		}
    		Collections.sort((List)editedFacilityList, JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator);
    		Collections.reverse((List)editedFacilityList);
    		form.getSocialHistoryData().setFacilityHistoryInfo((List)editedFacilityList);
    	}
		CreateSocialHistoryReportEvent reqEvent = new CreateSocialHistoryReportEvent();
		reqEvent.setCasefileId(form.getCasefileId());
		reqEvent.setSocialHistoryReportDataTO(form.getSocialHistoryData());
		if( form.getSocialHistoryData().isGeneric() )
		{
			reqEvent.setGeneric(true);
		}
		if( form.getSocialHistoryData().isReportToRefereeInitiation() )
		{
			reqEvent.setReportToReferee(true);
		}
		
		// Add record in activity table
		UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.SOCIAL_HISTORY_REPORT_GENERATED, "");

		// remove the date/time/user stamp in the various Comment fields 
		removeDateTimeUserFromComments( reqEvent ) ;		
		
		String casefileId = reqEvent.getCasefileId();
		SocialHistoryReportDataTO reportTO = reqEvent.getSocialHistoryReportDataTO();
		reportTO.setDraft("N");
		
		// Prepare report fields. 
		InterviewHelper.prepareSocialHistoryReportData( reportTO, casefileId, reqEvent.isReportToReferee() );		
		aRequest.getSession().setAttribute("reportInfo", reportTO);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		
		String docTypeCodeId = "SHR";  //default value 
		// Build report.
		
        		if( reqEvent.isGeneric() )
        		{
        			reqEvent.setReportName("REPORTING::SOCIAL_HISTORY_REPORT_GENERIC");
        			docTypeCodeId = "CRS"; // CRIS report
        			
        			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SOCIAL_HISTORY_REPORT_GENERIC);
        			
        		} else if (reqEvent.isReportToReferee()) {
        			reqEvent.setReportName("REPORTING::SOCIAL_HISTORY_REFEREE_TO_INITIATION");
        			docTypeCodeId = "RTR";  // Report to Referee
        			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.REPORT_TO_REFEREE_INITIATION);
        			} else {
        				reqEvent.setReportName("REPORTING::SOCIAL_HISTORY_REPORT");
        				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SOCIAL_HISTORY_REPORT);
        		}
        		reqEvent.addDataObject(reportTO);
        		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
        		if( pdfDocument == null || pdfDocument.length < 1 )
        		{
        		    if ("ajax".equals(submitType)){
        			JSONObject error = new JSONObject();
            			error.put("error", "Problems generating report. " + contactAdmin);
         			aResponse.getWriter().println( error.toString() );
         			return null;
         		    } else {
         			sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
        			return aMapping.findForward(UIConstants.FAILURE);
         		    }
        		    	
        			
        		}
		
        		IReport report  = PDFReporting.getInstance();
        		IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
        		ReportResponseEvent respEvent = new ReportResponseEvent();
        		try
        		{
        			respEvent.setContent(pdfDocument);
        			respEvent.setContentType(report.getContentType());
        			respEvent.setFileName(report.getTemplate());
        		}
        		catch(Exception e)
        		{
        			e.printStackTrace();
        			throw(e);
        		}
        		
        		InterviewDocument document = new InterviewDocument();
        		document.setDocument( respEvent.getContent() );
        		document.setCasefileId( casefileId );
        		document.setDocumentTypeCodeId(docTypeCodeId );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 	
        		dispatch.postEvent(respEvent);
		
		//Rebuilds original school list after report is generated so all school 
		//history shows up in the school tab 
		if( form.getSocialHistoryData().isGeneric()  ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			List existingSchoolHistory = form.getSocialHistoryData().getEducationalHistory();
			List newSchoolHistory = UIJuvenileHelper.buildSchoolHistory(form.getJuvenileNum());
			Collections.sort(newSchoolHistory);
			
			form.getSocialHistoryData().getEducationalHistory().removeAll(existingSchoolHistory);
			form.getSocialHistoryData().getEducationalHistory().addAll(newSchoolHistory);
		}
		//Commenting the code below; bug 50700. JuvenileFacilityStayRecord list is getting reset here.
		/* // convert from JuvenileDetentionFacility back to original JuvenileDetentionFacilitiesResponseEvent in case
		// user selects to generate another report. This should prevent ClassCastException
		if( form.getSocialHistoryData().getJuvenileFacilityStayRecord() != null && 
				form.getSocialHistoryData().getJuvenileFacilityStayRecord().size() > 0 )
		{
			Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
			if( obj.getClass().equals(JuvenileDetentionFacility.class) )
			{
				form.getSocialHistoryData().setJuvenileFacilityStayRecord(orgList);
				orgList = null;
			}
		}		
		// dont need to forward since response is already committed.
		 */
		return null;
	}
	
	/*
	 * 
	 */
	public ActionForward generateReportUJAC(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		
		SocialHistoryForm form = (SocialHistoryForm)aForm;
		form.setErrorMessage("");
		
		if( form.getSocialHistoryData().isGeneric()  || 
			form.getSocialHistoryData().isReportToRefereeInitiation()	)
		{
			boolean hasAtLeastOneIncluded = false;
			List familyInfo = form.getSocialHistoryData().getFamilyInformation(); 
			Iterator<FamilyInformationTO> familyInfoIter = familyInfo.iterator();
			while (familyInfoIter.hasNext())
			{
				if (familyInfoIter.next().isIncluded()) 
				{
					hasAtLeastOneIncluded = true;
				}
			} 
			
			if (!hasAtLeastOneIncluded) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.FamilyMembers"));
				form.setErrorMessage("At least one family member must be included in the report.  Access the Juvenile Info tab and select at least one person from the Family Member section.");
				saveErrors(aRequest, errors);
	            return aMapping.findForward(JPO_DATA_SUCCESS); 
			}
		}
		if( form.getSocialHistoryData().isReportToRefereeInitiation() )
		{
			//check for juvenile to be in detention	
			boolean inDetention = false;
			List facilityInfo = form.getSocialHistoryData().getJuvenileFacilityStayRecord(); 
			Iterator<JuvenileDetentionFacilitiesResponseEvent> facilityInfoIter = facilityInfo.iterator();
			while (facilityInfoIter.hasNext())
			{
				JuvenileDetentionFacilitiesResponseEvent juvDetFac = facilityInfoIter.next();
				if (juvDetFac.getReleaseDate() == null ||
						juvDetFac.getReleaseDate().equals("")) 
				{
					inDetention = true;
					break;
				}
			} 
			
			if (!inDetention) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.Detention"));
				form.setErrorMessage("Juvenile is not currently in a facility.  The Report to Referee Initiation cannot be generated unless the juvenile is in a facility.");
				saveErrors(aRequest, errors);
	            return aMapping.findForward(JPO_DATA_SUCCESS); 
			}
			//check for alleged offense to have been selected	
			boolean hasOneIncluded = false;
			List offenseInfo = form.getSocialHistoryData().getJuvenileOffenses(); 
			Iterator<OffenseInformationTO> offenseInfoIter = offenseInfo.iterator();
			while (offenseInfoIter.hasNext())
			{
				OffenseInformationTO offTo = offenseInfoIter.next();
				if (offTo.isPresentAllegedOffense()) 
				{
					hasOneIncluded = true;
				}
			} 
			
			if (!hasOneIncluded) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.interview.generateReport.Offense"));
				form.setErrorMessage("At least one present alleged offense must be included in the report. Access the Present Offense tab and select at least one offense.");
				saveErrors(aRequest, errors);
	            return aMapping.findForward(JPO_DATA_SUCCESS); 
			}
		}
		
		// Notified by is whoever the current logged on user is.
		IUserInfo user = SecurityUIHelper.getUser();
		form.getSocialHistoryData().setCurrentUser(new Name(user.getFirstName(), user.getMiddleName(), user.getLastName()).getFormattedName());

		// Get Activities of type EVS and populate SocialHistoryDataTO
		// This is for Significant Information section of the report
		List evsActivitiesComments = form.getSocialHistoryData().getEvsActivityComments();
		List pofActivities = new ArrayList();
		form.getSocialHistoryData().setPofActivities(pofActivities);
		evsActivitiesComments.clear();

		GetActivitiesEvent getActivitiesEvent = (GetActivitiesEvent)
				EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
		getActivitiesEvent.setActivityCodeId(ActivityConstants.EVALUATIVE_SUMMARY);
		getActivitiesEvent.setCasefileID(form.getCasefileId());
		getActivitiesEvent.setJuvenileNum(form.getJuvenileNum());

		CompositeResponse compositeResponse = postRequestEvent(getActivitiesEvent);
		
		{ Collection activityResults = MessageUtil.compositeToCollection(
				compositeResponse, ActivityResponseEvent.class);
			if( activityResults != null && activityResults.size() > 0 )
			{
				for( Iterator iter = activityResults.iterator(); iter.hasNext(); )
				{
					ActivityResponseEvent activity = (ActivityResponseEvent)iter.next();
					evsActivitiesComments.add(activity);
				}
			}
		}

		Collections.sort((List)evsActivitiesComments, new ActivityOldestFirstComparator());

		GetActivitiesEvent getPofActivitiesEvent = (GetActivitiesEvent)
				EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
		getPofActivitiesEvent.setActivityCodeId(UIConstants.ACTIVITY_CODE_POF);
		getPofActivitiesEvent.setCasefileID(form.getCasefileId());
		getPofActivitiesEvent.setJuvenileNum(form.getJuvenileNum());

		compositeResponse = postRequestEvent(getPofActivitiesEvent);
		
		{ Collection activityResults = MessageUtil.compositeToCollection(
				compositeResponse, ActivityResponseEvent.class);
			if( activityResults != null && activityResults.size() > 0 )
			{
				for( Iterator iter = activityResults.iterator(); iter.hasNext(); )
				{
					ActivityResponseEvent activity = (ActivityResponseEvent)iter.next();
					pofActivities.add(activity);
				}
			}
		}
		Collections.sort((List)pofActivities, new ActivityOldestFirstComparator());

		if( !form.getSocialHistoryData().isGeneric() ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			form.getSocialHistoryData().setMentalHealthTestingHistory(
					getMentalHealthTestingHistory(form.getJuvenileNum()));

			GetProgramReferralListEvent gpre = (GetProgramReferralListEvent)EventFactory.getInstance(
					JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);
			ProgramReferralRetrieverAttribute casefileAttr = new ProgramReferralRetrieverAttributeImpl(
					ProgramReferralRetrieverAttribute.CASEFILE);
			casefileAttr.setAttributeValue(form.getCasefileId());

			List attributeList = new ArrayList();
			attributeList.add(casefileAttr);
			gpre.setReferralAttributes(attributeList);
			gpre.setDetailsNeeded(false);

			form.getSocialHistoryData().setProgramReferrals(form.getProgramReferralList());
		}
		List orgList = new ArrayList();  // temporary preserve original data in JuvenileDetentionFacilitiesResponseEvent.class
		if( form.getSocialHistoryData().getJuvenileFacilityStayRecord() != null && 
				form.getSocialHistoryData().getJuvenileFacilityStayRecord().size() > 0 )
		{
			Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
			// Since we're using the same list to transfer data from PD -> UI, then UI -> UJAC, 
			// we need to know when to translate the object from ResponseEvent to UI Obj.
			if( obj.getClass().equals(JuvenileDetentionFacilitiesResponseEvent.class) )
			{
				List uiList = new ArrayList();
				int size = form.getSocialHistoryData().getJuvenileFacilityStayRecord().size();
				for( int i = 0; i < size; i++ )
				{
					JuvenileDetentionFacilitiesResponseEvent re = (JuvenileDetentionFacilitiesResponseEvent)
							form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(i);
					uiList.add(new JuvenileDetentionFacility(re));
					orgList.add(re);
				}
				form.getSocialHistoryData().setJuvenileFacilityStayRecord(uiList);
			}
		}
		
		//Rebuilds the school history if report request is "generic".
		//Remove all school history except for most recent.
		if( form.getSocialHistoryData().isGeneric() ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			List existingSchoolHistory = form.getSocialHistoryData().getEducationalHistory();
			List newSchoolHistory = UIJuvenileHelper.buildSchoolHistory(form.getJuvenileNum());
			Collections.sort(newSchoolHistory);
			List finalSchoolHistory = new ArrayList();
			
			//Remove Old History
			Iterator<SchoolHistoryTO> itr1 = existingSchoolHistory.iterator();
			if (itr1.hasNext())
			{
				//Remove all records
				form.getSocialHistoryData().getEducationalHistory().removeAll(existingSchoolHistory);
			} 
			
			//Add only the most recent school history record
			Iterator<SchoolHistoryTO> itr2 = newSchoolHistory.iterator();
			if (itr2.hasNext())
			{
				//Get first record (most recent school history)
				SchoolHistoryTO to = itr2.next();
				finalSchoolHistory.add(to);
				//Add first record to education history
				form.getSocialHistoryData().getEducationalHistory().addAll(finalSchoolHistory);
			} 
			
		} 
		
		if(form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			//Rebuilds the warrant history if report request is "report to Referee Initiation".
			//Remove all warrants except for those successfully served within 3 days of the detention admit date.
			List existingWarrantHistory = form.getSocialHistoryData().getWarrantHistory();
			List finalWarrantHistory = new ArrayList();
			
			//Add only the successful warrants within 3 days of the detention admit date
			Iterator<WarrantInformationTO> itr2 = existingWarrantHistory.iterator();
			while (itr2.hasNext())
			{
				//Check dates and status on warrant
				WarrantInformationTO to = itr2.next();
				if (to.getServiceStatus().equals("SUCCESSFUL"))
				{
					Date serviceDate = to.getServiceDate();
					Date detentionAdmitDate = DateUtil.stringToDate(form.getSocialHistoryData().getDetentionAdmitDate(), "MM/dd/yyyy");
					if(detentionAdmitDate.after(serviceDate)){
						double days = (double) (detentionAdmitDate.getTime() - serviceDate.getTime()) / (24*60*60*1000);
						if(days > 3.0 ){
							continue;
						}else {
							finalWarrantHistory.add(to);
						}
					}
					if(serviceDate.after(detentionAdmitDate)){
						double days = (double) (serviceDate.getTime() - detentionAdmitDate.getTime()) / (24*60*60*1000);
						if(days > 3.0 ){
							continue;
						}else {
							finalWarrantHistory.add(to);
						}
					}
				}
			} 
			form.getSocialHistoryData().getRefInitiationWarrantHistory().addAll(finalWarrantHistory);			
		} 

		CreateSocialHistoryReportEvent reqEvent = new CreateSocialHistoryReportEvent();
		reqEvent.setCasefileId(form.getCasefileId());
		reqEvent.setSocialHistoryReportDataTO(form.getSocialHistoryData());
		if( form.getSocialHistoryData().isGeneric() )
		{
			reqEvent.setGeneric(true);
		}
		if( form.getSocialHistoryData().isReportToRefereeInitiation() )
		{
			reqEvent.setReportToReferee(true);
		}
		
		// Add record in activity table
		UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.SOCIAL_HISTORY_REPORT_GENERATED, "");

		// remove the date/time/user stamp in the various Comment fields 
		removeDateTimeUserFromComments( reqEvent ) ;
		
		compositeResponse = postRequestEvent(reqEvent);
		ReportResponseEvent aRespEvent = (ReportResponseEvent)MessageUtil.filterComposite(compositeResponse, ReportResponseEvent.class);

		try
		{
			aResponse.setContentType("application/x-file-download");
			aResponse.setHeader("Content-disposition", "attachment; filename=" 
					+ aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");
			aResponse.setHeader("Cache-Control", "max-age=" + 1200);
			aResponse.setContentLength(aRespEvent.getContent().length);
			aResponse.resetBuffer();
			OutputStream os = aResponse.getOutputStream();
			os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
			os.flush();
			os.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		//Rebuilds original school list after report is generated so all school 
		//history shows up in the school tab 
		if( form.getSocialHistoryData().isGeneric()  ||
				form.getSocialHistoryData().isReportToRefereeInitiation())
		{
			List existingSchoolHistory = form.getSocialHistoryData().getEducationalHistory();
			List newSchoolHistory = UIJuvenileHelper.buildSchoolHistory(form.getJuvenileNum());
			Collections.sort(newSchoolHistory);
			
			form.getSocialHistoryData().getEducationalHistory().removeAll(existingSchoolHistory);
			form.getSocialHistoryData().getEducationalHistory().addAll(newSchoolHistory);
		}
		// convert from JuvenileDetentionFacility back to original JuvenileDetentionFacilitiesResponseEvent in case
		// user selects to generate another report. This should prevent ClassCastException
		if( form.getSocialHistoryData().getJuvenileFacilityStayRecord() != null && 
				form.getSocialHistoryData().getJuvenileFacilityStayRecord().size() > 0 )
		{
			Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
			if( obj.getClass().equals(JuvenileDetentionFacility.class) )
			{
				form.getSocialHistoryData().setJuvenileFacilityStayRecord(orgList);
				orgList = null;
			}
		}
		
		// dont need to forward since response is already committed.
		return null;
	}

	/* 
	 * iterate through the request event, looking
	 * for all comment type fields that have an
	 * appended date/time stamp/userName, such as:
	 * "THIS IS A COMMENT. [10/01/2008 00:00 - JONES, WANDA]"
	 */
	private boolean removeDateTimeUserFromComments( CreateSocialHistoryReportEvent reqEvent )
	{
		boolean rtn = true ;
		String newComments = null ;
		SocialHistoryReportDataTO report = reqEvent.getSocialHistoryReportDataTO() ;

		// both are of the same response event type - abstracted to a common function
		stripResponseEventComments( report.getEvsActivityComments().iterator() ) ;
		stripResponseEventComments( report.getPofActivities().iterator() ) ;
		
		// special case
		Iterator iter = report.getProgramReferralList().iterator() ;
		while( iter.hasNext() )
		{
			ProgramReferralResponseEvent act = (ProgramReferralResponseEvent)iter.next() ;
			if( act.getReferralComments() != null )
			{
				for( Iterator<ProgramReferralCommentResponseEvent> commentIter = act.getReferralComments().iterator();
						commentIter.hasNext(); /*empty*/ )
				{
					ProgramReferralCommentResponseEvent cmt = commentIter.next() ;
					if( (newComments = stripDateTimeAndUser( cmt.getCommentText()) ) != null )
					{
						cmt.setCommentText(newComments) ;
					}
				}
			}
		}
		
		// special case of [stripping] comments for SupervisionRule
		iter = report.getCompliantSupervisionRules().iterator() ;
		while( iter.hasNext() )
		{
			SupervisionRuleTO act = (SupervisionRuleTO)iter.next() ;
			if( (newComments = stripDateTimeAndUser( act.getResolvedDesc()) ) != null )
			{
				act.setResolvedDesc(newComments) ;
			}
		}

		// the next are all of trait type - abstracted to a common function
		stripTraitComments( report.getEducationalPerformanceTraits().iterator() ) ;
		stripTraitComments( report.getGangTraits().iterator() ) ;
		stripTraitComments( report.getSubstanceAbuseTraits().iterator() ) ;
		
		return( rtn ) ;
	}

	/*
	 * given an Iterator to Traits, visit each one, pass its
	 * comment to a function to strip the comments out. 
	 * if the String returned is not null, then assign that 
	 * String back into the comments for the report
	 */
	private boolean stripTraitComments( Iterator<TraitTO> iter )
	{
		boolean rtn = false ;
		String newComments = null ;
		
		while( iter.hasNext() )
		{
			TraitTO act = iter.next() ;
			if( (newComments = stripDateTimeAndUser( act.getTraitComments()) ) != null )
			{
				act.setTraitComments(newComments) ;
			}
		}
		
		return( rtn ) ;
	}

	/*
	 * give an Iterator to a response event, visit each one 
	 * and pass its comments as an arg to strip those comments. 
	 * if the String returned is not null, then assign that 
	 * String back into the comments for the report
	 */
	private boolean stripResponseEventComments( Iterator<ActivityResponseEvent> iter )
	{
		boolean rtn = false ;
		String newComments = null ;
		
		while( iter.hasNext() )
		{
			ActivityResponseEvent act = iter.next() ;
			if( (newComments = stripDateTimeAndUser( act.getComments()) ) != null )
			{
				act.setComments(newComments) ;
			}
		}
		
		return( rtn ) ;
	}
	
	/* given a String, scan it for a left brace char 
	 * and ensure there is a closing right brace char ...
	 * if so, strip everything from where the left brace 
	 * begins to its right ... sample input string:
	 * "THIS IS EVS ACTIVITY. [09/13/2007 00:00 - JONES, WANDA]"
	 * 
	 */
	private String stripDateTimeAndUser( String str )
	{
		String nstr = null ; 
		
		if( str != null  &&  (str.length() > 0) )
		{
			int leftBraceLocation = str.indexOf( '[') ;
			if( leftBraceLocation > (-1) )
			{
				int rightBraceLocation = str.indexOf( ']') ;
				if( rightBraceLocation > leftBraceLocation )
				{
					nstr = str.substring( 0, leftBraceLocation ) ;
				}
			}
		}
		
		return( nstr ) ;
	}
	
	/*
	 * 
	 */
	private List getMentalHealthTestingHistory(String juvenileNum)
	{
		List testResults = new ArrayList();
		MentalHealthTestResultTO testResult = null;

		GetMentalHealthAFResultsEvent getAdaptiveFuncEvt = (GetMentalHealthAFResultsEvent)
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHAFRESULTS);
		getAdaptiveFuncEvt.setJuvenileNum(juvenileNum);
		CompositeResponse replyEvent = postRequestEvent(getAdaptiveFuncEvt);
		
		List testingSession = MessageUtil.compositeToList(replyEvent, AFTestResponseEvent.class);
		if( testingSession != null && testingSession.size() > 0 )
		{
			int len = testingSession.size();
			for( int i = 0; i < len; i++ )
			{
				AFTestResponseEvent test = (AFTestResponseEvent)testingSession.get(i);
				testResult = new MentalHealthTestResultTO();
				testResult.setTestDate(test.getTestDate());
				testResult.setTestType(MentalHealthTestResultTO.ADAPTIVE_FUNCTIONING_TEST);
				testResult.setTestName(SimpleCodeTableHelper.getDescrByCode("AF_TEST_NAME", test.getTestName()));
				testResult.setServiceProviderName(test.getServiceProviderName());
				testResults.add(testResult);
			}
		}

		GetMentalHealthAchievementResultsEvent getAchievementEvt = (GetMentalHealthAchievementResultsEvent)
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHACHIEVEMENTRESULTS);
		getAchievementEvt.setJuvenileNum(juvenileNum);
		replyEvent = postRequestEvent(getAchievementEvt);

		testingSession = MessageUtil.compositeToList(replyEvent, ATTestResponseEvent.class);
		if( testingSession != null && testingSession.size() > 0 )
		{
			int len = testingSession.size();
			for( int i = 0; i < len; i++ )
			{
				ATTestResponseEvent test = (ATTestResponseEvent)testingSession.get(i);
				testResult = new MentalHealthTestResultTO();
				testResult.setTestDate(test.getTestDate());
				testResult.setTestType(MentalHealthTestResultTO.ACHIEVEMENT_TEST);
				testResult.setTestName(test.getTestname());
				testResult.setServiceProviderName(test.getServiceProviderName());
				testResults.add(testResult);
			}
		}

		GetMentalHealthIQResultsEvent getIQEvt = (GetMentalHealthIQResultsEvent)
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHIQRESULTS);
		getIQEvt.setJuvenileNum(juvenileNum);
		replyEvent = postRequestEvent(getIQEvt);

		testingSession = MessageUtil.compositeToList(replyEvent, IQTestResponseEvent.class);
		if( testingSession != null && testingSession.size() > 0 )
		{
			int len = testingSession.size();
			for( int i = 0; i < len; i++ )
			{
				IQTestResponseEvent test = (IQTestResponseEvent)testingSession.get(i);
				testResult = new MentalHealthTestResultTO();
				testResult.setTestDate(test.getTestDate());
				testResult.setTestType(MentalHealthTestResultTO.IQ_TEST);
				testResult.setTestName(test.getTestName());
				testResult.setServiceProviderName(test.getServiceProviderName());
				testResults.add(testResult);
			}
		}

		GetDSMIVResultsEvent getDSMEvt = (GetDSMIVResultsEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETDSMIVRESULTS);
		getDSMEvt.setJuvenileNum(juvenileNum);
		replyEvent = postRequestEvent(getDSMEvt);

		testingSession = MessageUtil.compositeToList(replyEvent, DSMIVTestResponseEvent.class);
		if( testingSession != null && testingSession.size() > 0 )
		{
			int len = testingSession.size();
			for( int i = 0; i < len; i++ )
			{
				DSMIVTestResponseEvent test = (DSMIVTestResponseEvent)testingSession.get(i);
				testResult = new MentalHealthTestResultTO();
				testResult.setTestDate(test.getTestDate());
				testResult.setTestType(MentalHealthTestResultTO.ADAPTIVE_FUNCTIONING_TEST);
				// DSM Test has no name
				// testResult.setTestName();
				testResult.setServiceProviderName(test.getServiceProviderName());
				testResults.add(testResult);
			}
		}

		if( testResults != null && testResults.size() > 0 )
		{
			Collections.sort(testResults);
		}

		return testResults;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward displayProgramReferralDetails(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm)aForm;
		String referralId = aRequest.getParameter( REFERRAL_ID_PARM );

		UIProgramReferralBean programReferral = null;
		if( referralId != null && (referralId.length() > 0) )
		{
			GetProgramReferralDetailsEvent gpdt = (GetProgramReferralDetailsEvent)EventFactory.getInstance(
					JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS);
			gpdt.setProgramReferralId(referralId);
			CompositeResponse compositeResponse = (CompositeResponse)MessageUtil.postRequest(gpdt);
			ProgramReferralResponseEvent respDetail = (ProgramReferralResponseEvent)
					MessageUtil.filterComposite(compositeResponse, ProgramReferralResponseEvent.class);
			if( respDetail != null )
			{
				programReferral = new UIProgramReferralBean(respDetail);
				form.setProgramReferral(programReferral);
			}
		}

		return aMapping.findForward(UIConstants.VIEW_DETAIL);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.saveChanges", "saveChanges");
		keyMap.put("button.generateDraftReport", "generateDraft");
		keyMap.put("button.generateFinalReport", "generateReport");
		keyMap.put("button.viewSocialHistoryData", "viewSocialHistoryData");
		keyMap.put("button.referral", "displayProgramReferralDetails");
		keyMap.put("button.link", "link");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
	}

}