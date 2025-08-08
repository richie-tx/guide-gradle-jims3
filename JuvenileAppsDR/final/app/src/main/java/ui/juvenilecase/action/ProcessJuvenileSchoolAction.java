/*
 * Created on Jun 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.ujac.util.BeanComparator;

import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.family.GetFamilyMembersforTEAReportEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.GetSchoolNameSearchEvent;
import messaging.juvenile.SaveJuvenileProfileDocumentEvent;
import messaging.juvenile.SaveJuvenileSchoolHistoryEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.reply.FamilyMemberforTEAReportResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.Features;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileConstants;
import naming.UIConstants;
import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.SchoolHistoryComparator;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.TEAStudentDataReportBean;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileSchoolHistoryForm;
import ui.juvenilecase.form.TraitsForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author jfisher
 */
public class ProcessJuvenileSchoolAction extends JIMSBaseAction
{
	private static final String DISPLAY_SCHOOL_FORM = "displaySchool";

	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "addSchool");
		keyMap.put("button.removeSelected", "removeSchools");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.addSchoolHistory", "showAddSchools");
		keyMap.put("button.addGEDProgram", "showAddGEDProgram");
		keyMap.put("button.backToSchoolList", "backToSchool");
		keyMap.put("button.go", "changeSchools");
		keyMap.put("button.go2", "changeHomeSchools");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.returnToSchoolDetails", "schoolDetails");
		keyMap.put("button.view", "viewdetails");
		keyMap.put("button.search", "searchBySchoolName");
		keyMap.put("button.find", "showSchoolDistricts");
		keyMap.put("button.select", "showSelectedSchools");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.PrintEnrollmentForm", "printTEAReport");
	}
	/*
	 * 
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
	
		SaveJuvenileSchoolHistoryEvent saveEvent = (SaveJuvenileSchoolHistoryEvent)
				EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILESCHOOLHISTORY);

		JuvenileSchoolHistoryResponseEvent schoolHistoryCreateEvent = 
				this.createJuvenileSchoolHistory(schoolForm);
		saveEvent.addRequest(schoolHistoryCreateEvent);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveEvent);
		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
		schoolForm.setSecondaryAction("confirm");

		return( aMapping.findForward(UIConstants.FINISH) );
	}

	/*
	 * 
	 */
	public ActionForward schoolDetails(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		String juvenileNum = schoolForm.getJuvenileNum();
		schoolForm.clearAddSchoolForm();
		Collection schoolHistory = UIJuvenileHelper.fetchSchoolHistory( juvenileNum);
		schoolForm.setSchoolHistory(schoolHistory);
		schoolForm.setAction(UIConstants.VIEW);
		schoolForm.setSuccess(true);
		
		return( aMapping.findForward(UIConstants.VIEW_DETAIL) );
	}

	/*
	 * 
	 */
	public ActionForward addSchool(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm form = (JuvenileSchoolHistoryForm)aForm;
		//bug fix 14887 starts.
		if(form.getSchoolCode()!=null ){
			form.setSchoolName(form.getSchoolCode().getSchoolDescription());
		}
		
		// load new descriptions
		form.setAcademicPerformanceDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ACADEMIC_PERFORMANCE,form.getAcademicPerformance()));
		form.setSchoolInfoVerifiedByDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SCHOOL_INFO_VERIFICATION,form.getSchoolInfoVerifiedBy()));
		//bug fix 14887 ends.
		if (!"".equals(form.getSpecificSchoolName() ) )
		{
			String temp = "";
			StringBuffer addr = new StringBuffer();
	    	addr.append(form.getStreetNum());
	    	addr.append(" ");
	    	if (!"".equals(form.getStreetName()))
	    	{	
	    		addr.append(form.getStreetName());
	    		addr.append(", ");
	    	}
	    	addr.append(form.getCity());
	    	addr.append(" ");
	    	if (!"".equals(form.getStateId()))
	    	{
	    		temp = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR,form.getStateId());
		    	addr.append(temp);
		    	addr.append(" ");
	    	}
	    	addr.append(form.getZipCode());
	    	if (!"".equals(form.getZipCodeExt()))
	    	{	
	    		addr.append("-");
	    		addr.append(form.getZipCodeExt());
	    	}
	    	temp = addr.toString();
	    	form.setSpecificSchoolAddress(temp.trim());
	    	addr = null;
	    	temp = null;
		}
		form.setSecondaryAction("details");

		return( aMapping.findForward(UIConstants.NEXT) );
	}

	/*
	 * 
	 */
	public ActionForward showAddSchools(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm form = (JuvenileSchoolHistoryForm)aForm;
		form.clearAddSchoolForm();
		this.setShowAddSchoolsForm(form);
		form.getNewSchoolHistory().clear();
		
		setSchoolHistory(form);
		form.setSecondaryAction("update");

		return( aMapping.findForward(DISPLAY_SCHOOL_FORM)  );			
	}

	/**
	 * @param form
	 */
	private void setShowAddSchoolsForm(JuvenileSchoolHistoryForm form)
	{
		if( form.getGradeLevels().isEmpty() )
		{
			Collection gradeLevels = CodeHelper.getGradeLevelsCodes();
			form.setGradeLevels(gradeLevels);
			form.setGradesRepeated(gradeLevels);
		}

		if( form.getAppropriateGradeLevels().isEmpty() )
		{
			Collection appropriateGradeLevels = CodeHelper.getAppropriateGradeLevelsCodes();
			form.setAppropriateGradeLevels(appropriateGradeLevels);
		}

		if( form.getExitTypes().isEmpty() )
		{
			Collection exitTypes = CodeHelper.getExitTypes();
			form.setExitTypes(exitTypes);
		}

		if( form.getSchoolDistricts().isEmpty() )
		{
			Collection schoolDistricts = UIJuvenileHelper.fetchSchoolDistricts();
			this.setSchoolCollections(schoolDistricts, form);
		}

		if( form.getParticipation().isEmpty() )
		{
			Collection participation = CodeHelper.getParticipationCodes();
			form.setParticipation(participation);
		}
		
		if( form.getSplEduCategory().isEmpty() ) //added for spl edu 
		{
			Collection splEduCategory = CodeHelper.getSplEduCategoryCode();
			form.setSplEduCategory(splEduCategory); //Bug Report #29768
		}

		if( form.getProgramAttending().isEmpty() )
		{
			Collection programAttending = CodeHelper.getProgramAttendingCodes();
			form.setProgramAttending(programAttending);
		}

		if( form.getRuleInfraction().isEmpty() )
		{
			Collection ruleInfraction = CodeHelper.getRuleInfractionCodes();
			form.setRuleInfraction(ruleInfraction);
		}

		if( form.getSchoolAttendanceStatus().isEmpty() )
		{
			Collection schoolAttendanceStatus = CodeHelper.getSchoolAttendanceStatusCodes();
			ArrayList activeSchoolAttendanceStatus = new ArrayList();
			Iterator i = schoolAttendanceStatus.iterator();
			while (i.hasNext())
			{
				CodeResponseEvent codeRespEvent = (CodeResponseEvent) i.next();
				if (codeRespEvent.getStatus().toUpperCase() != null && codeRespEvent.getStatus().toUpperCase().equals(PDCodeTableConstants.INACT)) {
	               continue;
				} else {
					activeSchoolAttendanceStatus.add(codeRespEvent);
				}
			}
			form.setAllSchoolAttendanceStatus(schoolAttendanceStatus);
			form.setSchoolAttendanceStatus(activeSchoolAttendanceStatus);
		}
		
		if(form.getStates() == null || form.getStates().isEmpty() )
		{
			form.setStates(new ArrayList());
			Collection stateCodes = CodeHelper.getStateCodes();
			form.setStates( (List) stateCodes);
		}
	}

	/**
	 * 
	 * @param schoolDistricts
	 * @param form
	 */
	private void setSchoolCollections( 
			Collection<JuvenileSchoolDistrictCodeResponseEvent> schoolDistricts, 
			JuvenileSchoolHistoryForm form)
	{
		Map schoolMap = new HashMap();
		Map schoolDistrictMap = new HashMap();
		// Add school
		Collection<JuvenileSchoolDistrictCodeResponseEvent> schools = null;
		
		//Collection<CodeResponseEvent> gedSchools = CodeHelper.getCodes( PDCodeTableConstants.GEDPROGRAM, true );

		if( schoolDistricts != null )
		{
		    for( JuvenileSchoolDistrictCodeResponseEvent school : schoolDistricts )
			{
			   String districtKey = school.getDistrictCode();
				
				// Add school district
				if( schoolDistrictMap.containsKey(districtKey) == false )
				{
					CodeResponseEvent schoolDistrict = new CodeResponseEvent();
					schoolDistrict.setCode(school.getDistrictCode());
					schoolDistrict.setDescription(school.getDistrictDescription());
					schoolDistrictMap.put(districtKey, schoolDistrict);
				}

				if( schoolMap.containsKey(districtKey) == false )
				{ // initialize the schools for this district based on the districtKey
					schools = new ArrayList();
					schoolMap.put(districtKey, schools);
				}

				schools = (Collection)schoolMap.get(districtKey);
					
				schools.add(school);
				    
			}
		    
			/*  for( CodeResponseEvent gedSchool : gedSchools ) {
			
			JuvenileSchoolDistrictCodeResponseEvent gedResp = new JuvenileSchoolDistrictCodeResponseEvent();
			gedResp.setDistrictCode("141");
			gedResp.setSchoolDescription( gedSchool.getDescription());
			gedResp.setSchoolCode(gedSchool.getCodeId());
			
			schools = (Collection)schoolMap.get("141");
			
			schools.add(gedResp);
			  }		*/	
		    			
		}
		form.setSchoolDistricts(schoolDistrictMap);
		form.setSchools(schoolMap);
	}
	
	private void setSchoolHistory(JuvenileSchoolHistoryForm form) {
		    Collection schoolHistories = UIJuvenileHelper.fetchSchoolHistory(form.getJuvenileNum());

		if (schoolHistories.size() > 0) {
			Collections.sort((List) schoolHistories,new SchoolHistoryComparator());
			JuvenileSchoolHistoryResponseEvent mySchoolHistoryResp =
				(JuvenileSchoolHistoryResponseEvent) ((ArrayList) schoolHistories).get(0);
			form.setSchoolDistrictId(mySchoolHistoryResp.getSchoolDistrictId());
			form.setSchoolId(mySchoolHistoryResp.getSchoolId());
			//bug fix 14887 starts.
			if(mySchoolHistoryResp.getHomeSchoolDistrictId()!=null){
			form.setHomeSchoolDistrictId(mySchoolHistoryResp.getHomeSchoolDistrictId());
			form.setHomeSchoolId(mySchoolHistoryResp.getHomeSchoolId());
			}
			//bug fix 14887 starts.
			form.setEntryDate("");
			form.setVerifiedDateString("");
			//bug fix 14887 starts.
			form.setGradeLevelId(mySchoolHistoryResp.getGradeLevelCode());
			//bug fix 14887 ends.
			form.setProgramAttendingId("");
			form.setSplEduCategoryId(mySchoolHistoryResp.getSplEduCategoryCode());
			form.setAppropriateGradeLevelId(mySchoolHistoryResp.getAppropriateLevelCode());
			form.setExitTypeId("C");  //default for new entry
			if (mySchoolHistoryResp.getExitTypeCode() != null && !"".equals(mySchoolHistoryResp.getExitTypeCode()) ){
				form.setExitTypeId(mySchoolHistoryResp.getExitTypeCode());
			}
			form.setGradeAverageId(mySchoolHistoryResp.getGradeAverage());
			form.setGradesRepeatNumber(mySchoolHistoryResp.getGradesRepeatNumber());
			form.setGradesRepeatedId(mySchoolHistoryResp.getGradesRepeatedCode());
			form.setGradeRepeatTotal(mySchoolHistoryResp.getGradeRepeatTotal());
			form.setReasonForGradeLevelChange(mySchoolHistoryResp.getGradeChangeReason());
			form.setParticipationId(mySchoolHistoryResp.getParticipationCode());
			//changes for JIMS200077279 MJCW:  Add Eligible for Enrollment Dte to TEAt(UI) starts
			form.setEligibilityEnrollmentDate("");
			//changes for JIMS200077279 MJCW:  Add Eligible for Enrollment Dte to TEAt(UI) ends
			//form.setProgramAttendingId(mySchoolHistoryResp.getProgramAttendingCode());
			//form.setSplEduCategoryId(mySchoolHistoryResp.getSplEduCategoryCode()); //added for spl edu 
			
			form.setRuleInfractionId(mySchoolHistoryResp.getRuleInfractionCode());
			form.setSchoolAttendanceStatusId(mySchoolHistoryResp.getSchoolAttendanceStatusCode());
			form.setTruancyHistory(mySchoolHistoryResp.getTruancyHistory());
			//Changes for JIMS200077276  MJCW:  Add new field Student ID to TEA Rpt(UI) starts
			form.setHasFeature(UIJuvenileHelper.isFeatureAllowed(Features.JCW_PRF_MAS_SCL_STU_U));
			form.setEducationService(mySchoolHistoryResp.getEducationService());
			if(form.getJuvenileNum()!=null){
				 GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) 
				 EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
				 requestEvent.setJuvenileNum(form.getJuvenileNum());
				 
				 CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
				 JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,JuvenileProfileDetailResponseEvent.class);
				 if(detail != null){ 
					 form.setStudentId(detail.getStudentId());
					 form.setEducationId(detail.getEducationId());
				  }
			}
			//Changes for JIMS200077276  MJCW:  Add new field Student ID to TEA Rpt(UI) Ends
		}
	}

	/**
	 * @param form
	 * @return
	 */
	private JuvenileSchoolHistoryResponseEvent createJuvenileSchoolHistory(
			JuvenileSchoolHistoryForm form)
	{
		JuvenileSchoolHistoryResponseEvent schoolHistory = new JuvenileSchoolHistoryResponseEvent();

		String schoolHistoryId = String.valueOf(System.currentTimeMillis());
		schoolHistory.setSchoolHistoryId(schoolHistoryId);

		schoolHistory.setAppropriateLevelCode(form.getAppropriateGradeLevelId());
		String appropriateLevelDescription =
			CodeHelper.getCodeDescriptionByCode(form.getAppropriateGradeLevels(), form.getAppropriateGradeLevelId());
		schoolHistory.setAppropriateLevelDescription(appropriateLevelDescription);

		schoolHistory.setExitTypeCode(form.getExitTypeId());
		String exitTypeDescription =
			CodeHelper.getCodeDescriptionByCode(form.getExitTypes(), form.getExitTypeId());
		schoolHistory.setExitTypeDescription(exitTypeDescription);

		schoolHistory.setGradeLevelCode(form.getGradeLevelId());
		String gradeLevelDescription = 
			CodeHelper.getCodeDescriptionByCode(form.getGradeLevels(), form.getGradeLevelId());
		schoolHistory.setGradeLevelDescription(gradeLevelDescription);

		schoolHistory.setGradeAverage(form.getGradeAverageId());
		
		schoolHistory.setGradeRepeatTotal(form.getGradeRepeatTotal());

		schoolHistory.setGradesRepeatNumber(form.getGradesRepeatNumber());
		schoolHistory.setGradesRepeatedCode(form.getGradesRepeatedId());
		String gradesRepeatedDescription =
			CodeHelper.getCodeDescriptionByCode(form.getGradesRepeated(), form.getGradesRepeatedId());
		schoolHistory.setGradesRepeatedDescription(gradesRepeatedDescription);
		schoolHistory.setGradeChangeReason(form.getReasonForGradeLevelChange());

		schoolHistory.setParticipationCode(form.getParticipationId());
		String participationDescription =
			CodeHelper.getCodeDescriptionByCode(form.getParticipation(), form.getParticipationId());
		schoolHistory.setParticipationDescription(participationDescription);

		schoolHistory.setProgramAttendingCode(form.getProgramAttendingId());
		String programAttendingDescription =
			CodeHelper.getCodeDescriptionByCode(form.getProgramAttending(), form.getProgramAttendingId());
		schoolHistory.setProgramAttendingDescription(programAttendingDescription);
		
		//added for spl edu 
		
		schoolHistory.setSplEduCategoryCode(form.getSplEduCategoryId());
		String splEduDescription =
			CodeHelper.getCodeDescriptionByCode(form.getSplEduCategory(), form.getSplEduCategoryId());
		schoolHistory.setSplEduCategoryDescription(splEduDescription);
       //end
		schoolHistory.setRuleInfractionCode(form.getRuleInfractionId());
		String ruleInfractionDescription = 
			CodeHelper.getCodeDescriptionByCode(form.getRuleInfraction(), form.getRuleInfractionId());
		schoolHistory.setRuleInfractionDescription(ruleInfractionDescription);

		schoolHistory.setSchoolAttendanceStatusCode(form.getSchoolAttendanceStatusId());
		String schoolAttendanceStatusDescription = 
				CodeHelper.getCodeDescriptionByCode(form.getAllSchoolAttendanceStatus(),form.getSchoolAttendanceStatusId());

		schoolHistory.setSchoolAttendanceStatusDescription(schoolAttendanceStatusDescription);
		schoolHistory.setTruancyHistory(form.getTruancyHistory());
		schoolHistory.setJuvenileNum(form.getJuvenileNum());

		Date lastAttendedDate = DateUtil.stringToDate(form.getLastAttendedDateString(), UIConstants.DATE_FMT_1);
		schoolHistory.setLastAttendedDate(lastAttendedDate);
		schoolHistory.setSchoolDistrictId(form.getSchoolDistrictId());

		Date verifiedDate = DateUtil.stringToDate(form.getVerifiedDateString(), UIConstants.DATE_FMT_1);
		schoolHistory.setVerifiedDate(verifiedDate);
		//Changes for JIMS200077279 starts
		Date eligibilityEnrollmentDate = DateUtil.stringToDate(form.getEligibilityEnrollmentDate(), UIConstants.DATE_FMT_1);
		schoolHistory.setEligibilityEnrollmentDate(eligibilityEnrollmentDate);
		schoolHistory.setEducationService(form.getEducationService());
		
		//Changes for JIMS200077279 Ends

		schoolHistory.setSchoolDistrictId(form.getSchoolDistrictId());
		schoolHistory.setAcademicPerformance( form.getAcademicPerformance());
		schoolHistory.setSchoolInfoVerifiedBy( form.getSchoolInfoVerifiedBy());
		schoolHistory.setTruancy(form.isTruancy());
		schoolHistory.setVerifiedByOther(form.getSchoolVerfifyOther());
		
		//GED Information
		schoolHistory.setCompletionDate(DateUtil.stringToDate(form.getCompletionDateStr(), UIConstants.DATE_FMT_1));
		schoolHistory.setGedCompleted(form.isGedCompleted());
		schoolHistory.setGedAwarded(form.isAwarded());
		schoolHistory.setGedAwardedDate(DateUtil.stringToDate(form.getAwardedDateStr(), UIConstants.DATE_FMT_1));

		JuvenileSchoolDistrictCodeResponseEvent schoolCode = form.getSchoolCode();
		if( schoolCode != null )
		{
			schoolHistory.setSchoolDistrictId(schoolCode.getDistrictCode());
			schoolHistory.setSchoolDistrict(schoolCode.getDistrictDescription());
			schoolHistory.setSchoolId(schoolCode.getSchoolCode());
			schoolHistory.setSchool(schoolCode.getSchoolDescription());
			schoolHistory.setTEASchoolNumber(schoolCode.getTeaCode());
		}
		
		schoolHistory.setHomeSchoolDistrictId(form.getHomeSchoolDistrictId());

		schoolCode = form.getHomeSchoolCode();
		if( schoolCode != null )
		{
			schoolHistory.setHomeSchoolDistrictId(schoolCode.getDistrictCode());
			schoolHistory.setHomeSchoolDistrict(schoolCode.getDistrictDescription());
			schoolHistory.setHomeSchoolId(schoolCode.getSchoolCode());
			schoolHistory.setHomeSchool(schoolCode.getSchoolDescription());
		}
		
		if (form.getSpecificSchoolName() != null & !"".equals(form.getSpecificSchoolName()))
		{
			schoolHistory.setSpecificSchoolName(form.getSpecificSchoolName());
			// data to build GLAdress row
			schoolHistory.setSchoolStreetNum(form.getStreetNum());
			schoolHistory.setSchoolStreetName(form.getStreetName());
			schoolHistory.setSchoolCity(form.getCity());
			schoolHistory.setSchoolState(form.getStateId());
			schoolHistory.setSchoolZip(form.getZipCode());
			schoolHistory.setSchoolZipCodeExt(form.getZipCodeExt());
		}
		return schoolHistory;
	}

	/*
	 * 
	 */
	public ActionForward removeSchools(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm form = (JuvenileSchoolHistoryForm)aForm;
		String[] removeSchoolHistory = form.getRemoveSchoolHistory();

		Collection newSchoolHistory = new ArrayList();
		newSchoolHistory.addAll(form.getNewSchoolHistory());

		if( removeSchoolHistory != null )
		{
			for( String historyStr : removeSchoolHistory )
			{
				Iterator<JuvenileSchoolHistoryResponseEvent> i = newSchoolHistory.iterator();
				while( i.hasNext() )
				{
					JuvenileSchoolHistoryResponseEvent schoolHistory = i.next();
					
					if( historyStr.equals(schoolHistory.getSchoolHistoryId()) )
					{
						form.getNewSchoolHistory().remove(schoolHistory);
					}
				}
			} // for
		}
		return(aMapping.findForward(DISPLAY_SCHOOL_FORM));
	}

	/*
	 * 
	 */
	public ActionForward changeSchools(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm form = (JuvenileSchoolHistoryForm)aForm;
		String homeSchoolInd = aRequest.getParameter("homeSchoolRequired");
		if (homeSchoolInd != null && homeSchoolInd.equals("Y")){
			form.setHomeSchoolId(null);
		} else {
			form.setSchoolId(null);
			form.setHomeSchoolId(null);
			form.setHomeSchoolDistrictId(null);
		}
				
		return(aMapping.findForward(DISPLAY_SCHOOL_FORM)) ;
	}
	
	/*
	 * 
	 */
	public ActionForward backToSchool(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
		traitsForm.setAction(UIConstants.VIEW);
		
		return aMapping.findForward(UIConstants.FINISH);
	}

	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm form = (JuvenileSchoolHistoryForm)aForm;
		String forwardString = UIConstants.CANCEL ;
		String secondaryAction = form.getSecondaryAction();
		if(! nullOrEmptyString(secondaryAction) )
		{		
			if(secondaryAction.equals("cancelSearch"))
			{
				form.setSecondaryAction("");
				forwardString = UIConstants.CANCEL_SEARCH;
			} else
			{
				forwardString = UIConstants.CANCEL;
			}
		}	
		return( aMapping.findForward(forwardString) ); 
	}

	/*
	 * 
	 */
	private boolean nullOrEmptyString( String str )
	{
		return( str == null  ||  str.length() == 0 ) ;
	}
	
	/*
	 * 
	 */
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		schoolForm.setSchoolSearchName("");
		schoolForm.setSchoolDistrictList(new ArrayList());
		
		return( aMapping.findForward(UIConstants.REFRESH_SUCCESS) );
	}

	/*
	 * 
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		String forward = UIConstants.BACK;
		if ("infoPage".equalsIgnoreCase(schoolForm.getBackFlowIndicator()) )
		{
			 forward = "backToInfo";
			 schoolForm.setBackFlowIndicator("");
		}
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42B03B350171
	 */
	public ActionForward viewdetails(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		String schoolHistoryId = (String)aRequest.getParameter("schoolHistoryId");
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
	
		//set the Education ID for display
		HttpSession session = aRequest.getSession();
		JuvenileMainForm mainForm = (JuvenileMainForm)session.getAttribute("juvenileProfileMainForm");
		schoolForm.setEducationId(mainForm.getEducationId());
		//Changes for JIMS200077276  MJCW:  Add new field Student ID to TEA Rpt(UI) 
		String juvenileNum =schoolForm.getJuvenileNum();
		if(juvenileNum!=null){
			GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) 
			EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
			requestEvent.setJuvenileNum(juvenileNum);
			CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
			JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,JuvenileProfileDetailResponseEvent.class);
			 if (detail != null)
			  { 
				 schoolForm.setStudentId(detail.getStudentId());
			  }else{ // ERROR OCCURRED
				  schoolForm.setStudentId("");
				  return aMapping.findForward(UIConstants.FAILURE);
			  }
		}
		//Changes for JIMS200077276  MJCW:  Add new field Student ID to TEA Rpt(UI) 
		this.setJuvenileSchoolHistoryForm(schoolForm, schoolHistoryId);
		schoolForm.setSecondaryAction("viewdetails");
		
		return( aMapping.findForward(UIConstants.VIEW) );
	}

	/**
	 * @param form
	 * @return
	 */
	private void setJuvenileSchoolHistoryForm(
			JuvenileSchoolHistoryForm schoolForm, String schoolHistoryId)
	{
		this.setShowAddSchoolsForm(schoolForm);
		Collection<JuvenileSchoolHistoryResponseEvent> schoolHistoryList = schoolForm.getSchoolHistory();

		if( notNullNotEmptyCollection( schoolHistoryList) )
		{
			for( JuvenileSchoolHistoryResponseEvent schoolHistory : schoolHistoryList )
			{
				if( schoolHistory.getSchoolHistoryId().equals(schoolHistoryId) )
				{
					schoolForm.setSchoolHistoryDetails(schoolHistory);
					break;
				}
			}
		}
	}

	/*
	 * 
	 */
	private boolean notNullNotEmptyCollection( Collection col )
	{
		return( col != null &&  ! col.isEmpty() ) ;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42B03B350171 Method for search by SchoolName
	 */
	public ActionForward searchBySchoolName(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		schoolForm.setSchoolSearchName("");
		schoolForm.setSecondaryAction("cancelSearch");
		schoolForm.setSchoolDistrictList(new ArrayList());
		String schoolType = aRequest.getParameter("homeSchoolSearchInd");
		schoolForm.setHomeSchoolSearchInd(schoolType);

		return( aMapping.findForward("searchSchool") ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42B03B350171 Method to display school names along with their
	 *          district names.
	 */
	public ActionForward showSchoolDistricts(ActionMapping aMapping, 	ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward("schoolDistricts");
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		GetSchoolNameSearchEvent searchEvent = new GetSchoolNameSearchEvent();

		searchEvent.setSchoolDescription(schoolForm.getSchoolSearchName());
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(searchEvent);
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		Collection schoolDistrictsList = 
			MessageUtil.compositeToCollection(response, JuvenileSchoolDistrictCodeResponseEvent.class);
		
		schoolForm.setSchoolDistrictList(schoolDistrictsList);
		if( schoolForm.getSchoolDistrictList().size() == 0 )
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
			saveErrors(aRequest, errors);
		}

		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return Method Invoked after selecting a school from school name search
	 *         jsp.
	 */
	public ActionForward showSelectedSchools(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(DISPLAY_SCHOOL_FORM);

		JuvenileSchoolHistoryForm form = (JuvenileSchoolHistoryForm)aForm;
		// form.clearAddSchoolForm();
		this.setShowAddSchoolsForm(form);
		form.getNewSchoolHistory().clear();
	
		if( StringUtils.isNotEmpty(form.getSelectedSchoolId()) )
		{
			String schoolId = form.getSelectedSchoolId();
			Collection<JuvenileSchoolDistrictCodeResponseEvent> coll = form.getSchoolDistrictList();
			
			if( notNullNotEmptyCollection(coll) )
			{
				for( JuvenileSchoolDistrictCodeResponseEvent newScDt : coll )
				{
					if( schoolId.equalsIgnoreCase(newScDt.getOid()) )
					{
						if (form.getHomeSchoolSearchInd() != null && form.getHomeSchoolSearchInd().equals("H")){
							form.setHomeSchoolId(newScDt.getSchoolCode());
							form.setHomeSchoolDistrictId(newScDt.getDistrictCode());
						} else {
							form.setSchoolId(newScDt.getSchoolCode());
							form.setSchoolDistrictId(newScDt.getDistrictCode());
							form.setHomeSchoolDistrictId(null);
							form.setHomeSchoolId(null);
						}
						break;
					}
				}
			}
		}

		return forward;
	}
	
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		//bug fix 14887 starts.
		JuvenileSchoolDistrictCodeResponseEvent js = schoolForm.getSchoolCode();
		if (js !=null && !js.getSchoolDisplayLiteral().equals("")){
			schoolForm.setSchoolName(js.getSchoolDisplayLiteral()); 
			schoolForm.setInstructionType(js.getInstructionType());
		}
		schoolForm.setHomeSchoolId("");
		schoolForm.setHomeSchoolDistrictId("");
		//bug fix 14887 ends.
		return( aMapping.findForward(UIConstants.CANCEL_SEARCH) );
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return Method Invoked after selecting a Add GED Program button on school details jsp.
	 */
	public ActionForward showAddGEDProgram(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{	
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		aRequest.setAttribute("juvenileID", schoolForm.getJuvenileNum());
		return( aMapping.findForward(UIConstants.CONTINUE_SUCCESS) );
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return Method Invoked after selecting a Add GED Program button on school details jsp.
	 */
	public ActionForward printTEAReportUJAC(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{	
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		schoolForm.setErrorMsg("");
		schoolForm.setReportGenerated("");
		TEAStudentDataReportBean reportBean = new TEAStudentDataReportBean();
		// get data to load report heading - juvenile profile
	    GetJuvenileProfileMainEvent requestEvent = 
	    	(GetJuvenileProfileMainEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	    requestEvent.setJuvenileNum(schoolForm.getJuvenileNum() );

	    CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

	    JuvenileProfileDetailResponseEvent detail =
	    	(JuvenileProfileDetailResponseEvent)MessageUtil.filterComposite(replyEvent,JuvenileProfileDetailResponseEvent.class);

		String tempStr = detail.getLastName() + ", " 
				+ detail.getFirstName() + "  " + detail.getMiddleName() + " " + detail.getNameSuffix();
	    reportBean.setJuvenileName(tempStr.trim());
		reportBean.setJuvenileNumber(detail.getJuvenileNum());
		reportBean.setDateOfBirth( UIConstants.EMPTY_STRING);
		reportBean.setEducationId(detail.getEducationId());
		tempStr = UIConstants.EMPTY_STRING;
		if( detail.getDateOfBirth() != null )
		{
			reportBean.setDateOfBirth( DateUtil.dateToString(detail.getDateOfBirth(),UIConstants.DATE_FMT_1 ) );
			// Get age based on year
			int age = UIUtil.getAgeInYears( detail.getDateOfBirth() );
			if( age > 0 )
			{
				tempStr = String.valueOf( age );
			}
		}
		reportBean.setCurrentAge(tempStr);
		reportBean.setVerifiedDOB( UIConstants.NO_FULL_TEXT );
		if( detail.isVerifiedDOB() )
		{
			reportBean.setVerifiedDOB( UIConstants.YES_FULL_TEXT );
		}
		tempStr = UIConstants.EMPTY_STRING;
		if (detail.getSSN() != null && !UIConstants.EMPTY_STRING.equals(detail.getSSN()))
		{
			tempStr = detail.getSSN();
		}
		if (detail.getPEIMSId() != null && !UIConstants.EMPTY_STRING.equals(detail.getPEIMSId()) )
		{
			if (!UIConstants.EMPTY_STRING.equals(tempStr) )
			{
				tempStr = tempStr + "/";
			}
			tempStr = tempStr + detail.getPEIMSId() ;
		}
		reportBean.setSsnPEIMSId(tempStr);
		reportBean.setGender(detail.getSex());
		reportBean.setRace(detail.getRace());
		reportBean.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode("JUV_ETHNICITY", detail.getEthnicity()));
		reportBean.setMultiracialDesc( UIConstants.NO_FULL_TEXT );
		if( detail.isMultiracial() )
		{
			reportBean.setMultiracialDesc( UIConstants.YES_FULL_TEXT );
		}
		if( detail.getHispanic()!=null && detail.getHispanic().equalsIgnoreCase("N"))
		{
			reportBean.setHispanicDesc( UIConstants.NO_FULL_TEXT );
		}
		
		//U.S 88526
		if( detail.getHispanic()!=null && detail.getHispanic().equalsIgnoreCase("Y"))
		{
			reportBean.setHispanicDesc( UIConstants.YES_FULL_TEXT );
		}
		// get detention Admit Date
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Send PD Request Event
		GetJuvenileDetentionFacilitiesEvent event = (GetJuvenileDetentionFacilitiesEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
		event.setJuvenileNum(schoolForm.getJuvenileNum());
//		event.setJuvenileNum("310192"); for testing only
		dispatch.postEvent(event);

		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
		tempStr = UIConstants.EMPTY_STRING;
		reportBean.setDetentionAdmitDate(tempStr);
		reportBean.setDetentionFacilityHistory(new ArrayList());
		Map dataMap = MessageUtil.groupByTopic(response);
		if( dataMap != null )
		{
			Collection facilityHistory = (Collection)dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_FACILITY_HISTORY_TOPIC);
			if( facilityHistory != null  &&  facilityHistory.size() > 0 )
			{
				List theList = new ArrayList(facilityHistory);

				ArrayList sortFields = new ArrayList();
				sortFields.add(new ReverseComparator(new BeanComparator("admitDate")));
				sortFields.add(new ReverseComparator(new BeanComparator("admitTime")));
				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort(theList, multiSort);

				facilityHistory = new ArrayList(theList);
				for (int x=0; x<theList.size(); x++)
				{
					JuvenileDetentionFacilitiesResponseEvent jdfEvent = (JuvenileDetentionFacilitiesResponseEvent) theList.get(x);
					jdfEvent.setFacilityName(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, jdfEvent.getDetainedFacility() ) );
				}

				reportBean.setDetentionFacilityHistory(theList);
				for (int x=0; x<theList.size(); x++)
				{
					JuvenileDetentionFacilitiesResponseEvent jdfEvent = (JuvenileDetentionFacilitiesResponseEvent) theList.get(x);
					if ("DET".equals(jdfEvent.getDetainedFacility()) )
					{
						reportBean.setDetentionAdmitDate(DateUtil.dateToString(jdfEvent.getAdmitDate(), UIConstants.DATE_FMT_1 ) );
						break;
					}
				}
				theList = null;
			}
		}	
		reportBean.setIncluded(false);
		// get family information
		reportBean.setFamilyInformation(new ArrayList());
		// Send PD Request Event
		GetFamilyMembersforTEAReportEvent rptEvent = 
	    	(GetFamilyMembersforTEAReportEvent) EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERSFORTEAREPORT);
		rptEvent.setJuvenileId(schoolForm.getJuvenileNum());
		List familyMembers = MessageUtil.postRequestListFilter(rptEvent, FamilyMemberforTEAReportResponseEvent.class);
		// load display values into responseEvent
		if (familyMembers != null)
		{	
			int totEvents = familyMembers.size();
			for (int x =0; x<totEvents; x++){
				FamilyMemberforTEAReportResponseEvent fEvent = (FamilyMemberforTEAReportResponseEvent) familyMembers.get(x);
				tempStr = fEvent.getLastName();
				if (StringUtils.isNotEmpty(fEvent.getFirstName() ) )
				{
					if (StringUtils.isNotEmpty(tempStr))
					{
						tempStr += ", " + fEvent.getFirstName();
					}
				}
				fEvent.setFullName(tempStr.trim());
				fEvent.setRelationToJuvenile(CodeHelper.getCodeDescription(PDCodeTableConstants.RELATIONSHIP_JUVENILE,	fEvent.getRelationToJuvenileCd() ) );
				// build address descriptions
				fEvent.setState(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR, fEvent.getStateCd() ) );
				fEvent.setStreetType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STREET_TYPE, fEvent.getStreetTypeCd() ) );
				tempStr = UIConstants.EMPTY_STRING;
				if (StringUtils.isNotEmpty(fEvent.getZipCode() ))
				{	
					tempStr = fEvent.getZipCode();
					if (StringUtils.isNotEmpty(fEvent.getZipCodeExtension() ) )
					{
						tempStr += "-" + fEvent.getZipCodeExtension();
					}
				}
				fEvent.setFormattedZipCode(tempStr.trim());
				tempStr = UIConstants.EMPTY_STRING;
				StringBuffer sb = new StringBuffer();
				if (StringUtils.isNotEmpty(fEvent.getStreetNumber() ) ) 
				{
					sb.append(fEvent.getStreetNumber() );
					sb.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getStreetNumberSuffix() ) ) 
				{
					sb.append(fEvent.getStreetNumberSuffix());
					sb.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getStreetName() ) ) 
				{
					sb.append(fEvent.getStreetName());
					sb.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getStreetType()) ) 
				{
					sb.append(fEvent.getStreetType());
					sb.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getApartmentNumber()) ) 
				{
					sb.append(fEvent.getApartmentNumber());
					sb.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getStreetAddress2()) ) 
				{
					sb.append(fEvent.getStreetAddress2());
					sb.append(" ");
				}
				tempStr = sb.toString().trim();
				fEvent.setFormattedAddress1(tempStr);
				tempStr = UIConstants.EMPTY_STRING;
				sb = new StringBuffer();
				if (StringUtils.isNotEmpty(fEvent.getCity()) ) 
				{
					sb.append(fEvent.getCity());
					sb.append(", ");
				}
				if (StringUtils.isNotEmpty(fEvent.getState()) ) 
				{
					sb.append(fEvent.getState());
					sb.append(" ");
				}
				sb.append(fEvent.getFormattedZipCode());
				tempStr = sb.toString().trim();
				fEvent.setFormattedAddress2(tempStr);
				tempStr = UIConstants.EMPTY_STRING;
				if (StringUtils.isNotEmpty(fEvent.getPhoneNumber()) )
				{
					PhoneNumberBean p = new PhoneNumberBean(fEvent.getPhoneNumber());
					tempStr = p.getFormattedPhoneNumber().toString();
				}
				fEvent.setFormattedPhoneNumber(tempStr);
				if (fEvent.isGuardian() ){
					fEvent.setGuardianYes("Yes");
				}
				if (fEvent.isInHome()){
					fEvent.setInHomeYes("Yes");
				}
				if (fEvent.isIncarcerated()){
					fEvent.setIncarceratedYes("Yes");
				}
				if (fEvent.isDeceased() ){
					fEvent.setDeceasedYes("Yes");
				}
			}
		}	
		
		reportBean.setFamilyInformation(familyMembers);
		
		reportBean.setSchoolHistory((List) schoolForm.getSchoolHistory());
		if (reportBean.getSchoolHistory() != null && reportBean.getSchoolHistory().size() > 0){
			reportBean.setIncluded(true);
		}
		
		tempStr = null;
		final String fileName = "TEA_STUDENT_DATA_REPORT";
		CompositeResponse compResp = sendPrintRequest("REPORTING::TEA_REPORT", reportBean, null);
		try
		{
			setPrintContentResp(aResponse, compResp, fileName, UIConstants.PRINT_AS_PDF_DOC);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage(aRequest, UIConstants.EMPTY_STRING);
		} 
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
			MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		// persist a copy of the document
		SaveJuvenileProfileDocumentEvent saveEvent = 
			(SaveJuvenileProfileDocumentEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEDOCUMENT);
		saveEvent.setDocument(aReportRespEvt.getContent());
		saveEvent.setDocumentTypeCodeId("TEA");
		saveEvent.setJuvenileNum(schoolForm.getJuvenileNum());
		saveEvent.setEntryDate(DateUtil.getCurrentDate());
		CompositeResponse resp =  MessageUtil.postRequest(saveEvent);
		ReturnException returnException =
			   (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

		if (returnException != null) {
			schoolForm.setErrorMsg("Error occured saving TEA Report.");
			schoolForm.setReportGenerated("Y");
		}

		return null;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return Method Invoked after selecting a Add GED Program button on school details jsp.
	 */
	public ActionForward printTEAReport(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{	
		JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm)aForm;
		schoolForm.setErrorMsg("");
		schoolForm.setReportGenerated("");
		TEAStudentDataReportBean reportBean = new TEAStudentDataReportBean();
		// get data to load report heading - juvenile profile
	    GetJuvenileProfileMainEvent requestEvent = 
	    	(GetJuvenileProfileMainEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	    requestEvent.setJuvenileNum(schoolForm.getJuvenileNum() );

	    CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

	    JuvenileProfileDetailResponseEvent detail =
	    	(JuvenileProfileDetailResponseEvent)MessageUtil.filterComposite(replyEvent,JuvenileProfileDetailResponseEvent.class);
	    
	    StringBuffer tempStr = new StringBuffer();
	    if (StringUtils.isNotEmpty(detail.getLastName())) {
	    	tempStr.append(detail.getLastName());
		}
	    if (StringUtils.isNotEmpty(detail.getFirstName())) {
	    	tempStr.append(", ");
	    	tempStr.append(detail.getFirstName());
		}
	    if (StringUtils.isNotEmpty(detail.getMiddleName())){
	    	tempStr.append(" ");
	    	tempStr.append(detail.getMiddleName());
	    }
	    if (StringUtils.isNotEmpty(detail.getNameSuffix())){
	    	tempStr.append(" ");
	    	tempStr.append(detail.getNameSuffix());
	    }
		;
	    reportBean.setJuvenileName(tempStr.toString());
		reportBean.setJuvenileNumber(detail.getJuvenileNum());
		reportBean.setDateOfBirth( UIConstants.EMPTY_STRING);
		if(StringUtils.isNotEmpty(detail.getEducationId())){
			reportBean.setEducationId(detail.getEducationId());
		}else{
			reportBean.setEducationId("");
		}
		//Changes for JIMS200077276  MJCW:  Add new field Student ID to TEA Rpt(UI) starts
		String studentId=detail.getStudentId();
		if(StringUtils.isNotEmpty(studentId)){
			//Strip leading zeros
			reportBean.setStudentId(StringUtils.stripStart(studentId, "0"));
		}else{
			reportBean.setStudentId("");
		}
		//Changes for JIMS200077276  MJCW:  Add new field Student ID to TEA Rpt(UI) ends
		tempStr = new StringBuffer();
		if( detail.getDateOfBirth() != null )
		{
			reportBean.setDateOfBirth( DateUtil.dateToString(detail.getDateOfBirth(),UIConstants.DATE_FMT_1 ) );
			// Get age based on year
			int age = UIUtil.getAgeInYears( detail.getDateOfBirth() );
			if( age > 0 )
			{
				reportBean.setCurrentAge(String.valueOf( age ));
			}else{

				reportBean.setCurrentAge("");
			}
		}
		reportBean.setVerifiedDOB( UIConstants.NO_FULL_TEXT );
		if( detail.isVerifiedDOB() )
		{
			reportBean.setVerifiedDOB( UIConstants.YES_FULL_TEXT );
		}
		tempStr = new StringBuffer();
		if (StringUtils.isNotEmpty(detail.getSSN()))
		{
			tempStr.append(detail.getSSN());
		}
		if (StringUtils.isNotEmpty(detail.getPEIMSId()) )
		{
			if (StringUtils.isNotEmpty(tempStr.toString()) )
			{
				tempStr.append("/");
			}
			tempStr.append(detail.getPEIMSId());
		}
		reportBean.setSsnPEIMSId(tempStr.toString());
		reportBean.setGender(detail.getSex());
		reportBean.setRace(detail.getRace());
		reportBean.setEthnicityDesc(SimpleCodeTableHelper.getDescrByCode("JUV_ETHNICITY", detail.getEthnicity()));
		reportBean.setMultiracialDesc( UIConstants.NO_FULL_TEXT );
		if( detail.isMultiracial() )
		{
			reportBean.setMultiracialDesc( UIConstants.YES_FULL_TEXT );
		}
		reportBean.setHispanicDesc( UIConstants.NO_FULL_TEXT);
		if( detail.getHispanic()!=null && detail.getHispanic().equalsIgnoreCase("Y"))
		{
			reportBean.setHispanicDesc( UIConstants.YES_FULL_TEXT );
		}
		// get detention Admit Date
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Send PD Request Event
		GetJuvenileDetentionFacilitiesEvent event = (GetJuvenileDetentionFacilitiesEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
		event.setJuvenileNum(schoolForm.getJuvenileNum());
//		event.setJuvenileNum("310192"); for testing only
		dispatch.postEvent(event);

		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
		tempStr = new StringBuffer();
		reportBean.setDetentionAdmitDate("");
		reportBean.setDetentionFacilityHistory(new ArrayList());
		Map dataMap = MessageUtil.groupByTopic(response);
		if( dataMap != null )
		{
			Collection facilityHistory = (Collection)dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_FACILITY_HISTORY_TOPIC);
			if( facilityHistory != null  &&  facilityHistory.size() > 0 )
			{
				List theList = new ArrayList(facilityHistory);

				ArrayList sortFields = new ArrayList();
				sortFields.add(new ReverseComparator(new BeanComparator("admitDate")));
				sortFields.add(new ReverseComparator(new BeanComparator("admitTime")));
				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort(theList, multiSort);

				facilityHistory = new ArrayList(theList);
				for (int x=0; x<theList.size(); x++)
				{
					JuvenileDetentionFacilitiesResponseEvent jdfEvent = (JuvenileDetentionFacilitiesResponseEvent) theList.get(x);
					jdfEvent.setFacilityName(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, jdfEvent.getDetainedFacility() ) );
				}

				reportBean.setDetentionFacilityHistory(theList);
				for (int x=0; x<theList.size(); x++)
				{
					JuvenileDetentionFacilitiesResponseEvent jdfEvent = (JuvenileDetentionFacilitiesResponseEvent) theList.get(x);
					if ("DET".equals(jdfEvent.getDetainedFacility()) )
					{
						reportBean.setDetentionAdmitDate(DateUtil.dateToString(jdfEvent.getAdmitDate(), UIConstants.DATE_FMT_1 ) );
						break;
					}
				}
				theList = null;
			}
		}	
		reportBean.setIncluded(false);
		// get family information
		reportBean.setFamilyInformation(new ArrayList());
		// Send PD Request Event
		GetFamilyMembersforTEAReportEvent rptEvent = 
	    	(GetFamilyMembersforTEAReportEvent) EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERSFORTEAREPORT);
		rptEvent.setJuvenileId(schoolForm.getJuvenileNum());
		List familyMembers = MessageUtil.postRequestListFilter(rptEvent, FamilyMemberforTEAReportResponseEvent.class);
		// load display values into responseEvent
		if (familyMembers != null)
		{	
			int totEvents = familyMembers.size();
			for (int x =0; x<totEvents; x++){
				FamilyMemberforTEAReportResponseEvent fEvent = (FamilyMemberforTEAReportResponseEvent) familyMembers.get(x);
				StringBuffer familyFullName = new StringBuffer();
				if (StringUtils.isNotEmpty(fEvent.getLastName())) {
					familyFullName.append(fEvent.getLastName());
				}
				if (StringUtils.isNotEmpty(fEvent.getFirstName() ) )
				{
					familyFullName.append(", ");
					familyFullName.append(fEvent.getFirstName());
				}
				fEvent.setFullName(familyFullName.toString());
				fEvent.setRelationToJuvenile(CodeHelper.getCodeDescription(PDCodeTableConstants.RELATIONSHIP_JUVENILE,	fEvent.getRelationToJuvenileCd() ) );
				//ER Changes JIMS200074578 Starts
				if(fEvent.getRelationToJuvenile().contains(UIConstants.FOSTER)&&!fEvent.getRelationToJuvenile().contains(UIConstants.CHILD)){
					reportBean.setFosterParent(UIConstants.YES_FULL_TEXT);
				}
				//ER Changes JIMS200074578 Ends
				// build address descriptions
				fEvent.setState(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR, fEvent.getStateCd() ) );
				fEvent.setStreetType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STREET_TYPE, fEvent.getStreetTypeCd() ) );
				tempStr = new StringBuffer();
				if (StringUtils.isNotEmpty(fEvent.getZipCode() ))
				{	
					tempStr.append(fEvent.getZipCode());
					if (StringUtils.isNotEmpty(fEvent.getZipCodeExtension() ) )
					{
						tempStr.append("-");
						tempStr.append(fEvent.getZipCodeExtension());
					}
				}
				fEvent.setFormattedZipCode(tempStr.toString());
				tempStr =  new StringBuffer();
				if (StringUtils.isNotEmpty(fEvent.getStreetNumber() ) ) 
				{
					tempStr.append(fEvent.getStreetNumber() );
					tempStr.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getStreetNumberSuffix() ) ) 
				{
					tempStr.append(fEvent.getStreetNumberSuffix());
					tempStr.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getStreetName() ) ) 
				{
					tempStr.append(fEvent.getStreetName());
					tempStr.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getStreetType()) ) 
				{
					tempStr.append(fEvent.getStreetType());
					tempStr.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getApartmentNumber()) ) 
				{
					tempStr.append(fEvent.getApartmentNumber());
					tempStr.append(" ");
				}
				if (StringUtils.isNotEmpty(fEvent.getStreetAddress2()) ) 
				{
					tempStr.append(fEvent.getStreetAddress2());
					tempStr.append(" ");
				}
				fEvent.setFormattedAddress1(tempStr.toString());
				tempStr = new StringBuffer();
				if (StringUtils.isNotEmpty(fEvent.getCity()) ) 
				{
					tempStr.append(fEvent.getCity());
					tempStr.append(", ");
				}
				if (StringUtils.isNotEmpty(fEvent.getState()) ) 
				{
					tempStr.append(fEvent.getState());
					tempStr.append(" ");
				}
				tempStr.append(fEvent.getFormattedZipCode());
				fEvent.setFormattedAddress2(tempStr.toString());
				tempStr = new StringBuffer();
				if (StringUtils.isNotEmpty(fEvent.getPhoneNumber()) )
				{
					PhoneNumberBean p = new PhoneNumberBean(fEvent.getPhoneNumber());
					tempStr.append(p.getFormattedPhoneNumber().toString());
				}
				fEvent.setFormattedPhoneNumber(tempStr.toString());
				tempStr = new StringBuffer();
				if (fEvent.isGuardian() ){
					fEvent.setGuardianYes(UIConstants.YES_FULL_TEXT);
					//ER Changes JIMS200074578 Starts
					if(UIJuvenileHelper.isMiltary(fEvent.getMemberId())){
						reportBean.setMilitary(UIConstants.YES_FULL_TEXT);
					}
					//ER Changes JIMS200074578 ends
				}
				if (fEvent.isInHome()){
					fEvent.setInHomeYes("Yes");
				}
				if (fEvent.isIncarcerated()){
					fEvent.setIncarceratedYes("Yes");
				}
				if (fEvent.isDeceased() ){
					fEvent.setDeceasedYes("Yes");
				}
			}
		}	
		
		reportBean.setFamilyInformation(familyMembers);
		
		reportBean.setSchoolHistory((List) schoolForm.getSchoolHistory());
		if (reportBean.getSchoolHistory() != null && reportBean.getSchoolHistory().size() > 0){
			reportBean.setIncluded(true);
			//ER Changes JIMS200077279 starts
			JuvenileSchoolHistoryResponseEvent mySchoolHistory =
				(JuvenileSchoolHistoryResponseEvent) ((ArrayList) schoolForm.getSchoolHistory()).get(0); // get the most recent record date.
			Date eligibEnrollDate = mySchoolHistory.getEligibilityEnrollmentDate();
			if(eligibEnrollDate==null)
			{
				reportBean.setEligibilityEnrollmentDate("");
			}else{
				reportBean.setEligibilityEnrollmentDate(DateUtil.dateToString(eligibEnrollDate, UIConstants.DATE_FMT_1));
			}
			//ER Changes JIMS200077279 Ends
		}
		//ER Changes JIMS200074578 Starts
		boolean isDyslexia =UIJuvenileHelper.isDyslexia(schoolForm.getJuvenileNum(), PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_EDUCATIONAL_PERFORMANCE);
		
		if(isDyslexia){
			reportBean.setDyslexia(UIConstants.YES_FULL_TEXT);
		}
		//ER Changes JIMS200074578 Ends
		
		aRequest.getSession().setAttribute("reportInfo", reportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.TEA_REPORT);
		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
		
		// persist a copy of the BFO pdf document
		SaveJuvenileProfileDocumentEvent saveEvent = 
			(SaveJuvenileProfileDocumentEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEDOCUMENT);
		saveEvent.setDocument(pdfDocument);
		saveEvent.setDocumentTypeCodeId("TEA");
		saveEvent.setJuvenileNum(schoolForm.getJuvenileNum());
		saveEvent.setEntryDate(DateUtil.getCurrentDate());
		CompositeResponse resp =  MessageUtil.postRequest(saveEvent);
		ReturnException returnException =
			   (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

		if (returnException != null) {
			schoolForm.setErrorMsg("Error occured saving TEA Report.");
			schoolForm.setReportGenerated("Y");
		}
		
		// remove the pdf report attributes from session when finished saving to database
		aRequest.removeAttribute("isPdfSaveNeeded");
		aRequest.removeAttribute("pdfSavedReport");
		tempStr = null;

		return null;
	}
}