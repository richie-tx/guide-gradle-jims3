package ui.juvenilecase.referral.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetAllOffenseCodesEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.interviewinfo.to.JOTTO;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetActiveCasefileReferralsEvent;
import messaging.juvenilecase.GetJJSReferralDetailsEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.juvenile.JuvenileHelper;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.juvenilewarrant.JuvenileOffenderTracking;
import pd.juvenilewarrant.JuvenileOffenderTrackingCoActor;
import pd.km.util.Name;
import ui.common.Address;
import ui.common.AddressHelper;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.casefile.TEAStudentDataReportBean;
import ui.juvenilecase.casefile.form.GuardianBean;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.juvenilecase.referral.JuvenileReferralOffenseBean;
import ui.juvenilecase.referral.form.JuvenileReferralForm;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author nemathew
 */

public class ProcessReferralBriefingAction extends LookupDispatchAction
{

    protected Map getKeyMethodMap()
    {
	Map keyMap = new HashMap();
	keyMap.put("button.updateSsn", "updateJuvenileSsn");
	keyMap.put("button.createSsnViewLog", "createSsnViewLog");
	keyMap.put("button.updateJuvenileRecord", "updateJuvenile");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.loadFamMem", "loadFamilyMember");
	keyMap.put("button.update", "updateGuardian");
	keyMap.put("button.next", "next");
	keyMap.put("button.go", "changeSchools");
	keyMap.put("button.addNewGuardian", "showAddGuradian");
	keyMap.put("button.addToList", "addGuardianToList");
	keyMap.put("button.createReferral", "createReferral");
	keyMap.put("button.overrideAssignment", "overrideAssignment");
	keyMap.put("button.manageAssignment", "manageAssignment");
	keyMap.put("button.updateReferral", "updateReferral");
	keyMap.put("button.createReseal", "createReseal");
	keyMap.put("button.printMasterDisplay", "printMasterDisplay");
	return keyMap;
    }

    /**
     * manageAssignment - redirected to displayManageAssignment
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return MANAGE_ASSIGNMENT
     */

    public ActionForward manageAssignment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	// Not clearing as expected rry
	form.setOverrideOTHComments("");
	form.setOverrideReasonStr("");
	form.setAction("manageAssignment");
	return aMapping.findForward(UIConstants.MANAGE_ASSIGNMENT);
    }

    /**
     * Update Juvenile.
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */

    public ActionForward updateJuvenile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.clear();
	//get the form from the briefing details page.
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	JuvenileReferralHelper.populateReferralForm(briefingDetailsForm, form);
	if (briefingDetailsForm.getGuardians() != null)
	{
	    List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList = JuvenileReferralHelper.populateFromGuardianBeanToMemBean(briefingDetailsForm.getGuardians());
	    form.setMemberDetailsBeanList(memberDetailsBeanList);
	    if (briefingDetailsForm.getProfileDetail().getFromM204Flag() != null && briefingDetailsForm.getProfileDetail().getFromM204Flag().equalsIgnoreCase("Y"))
	    {
		form.setJuvFromM204Flag("Y");
	    }
	}
	form.setAction("updateJuvenile");
	form.setGuardianEditFlag("N");
	form.setAddGuradianFlag("N");
	form.setSelectedMemberBean(null);

	//populateCodeTables()
	form.setRaces(CodeHelper.getRaces());
	form.setSexes(CodeHelper.getJJSSexCodes());
	form.setStreetNumSuffixList(CodeHelper.getCodes(PDCodeTableConstants.STREET_SUFFIX, true));
	form.setStreetTypeList(CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE, true));
	form.setStateList(CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR, true));
	form.setAddressTypeList(CodeHelper.getCodes(PDCodeTableConstants.ADDRESS_TYPE, true));
	form.setCountyList(CodeHelper.getCodes(PDCodeTableConstants.COUNTY, true));
	form.setSchoolCollections(UIJuvenileHelper.fetchSchoolDistricts(), form);//please recheck this - NM
	Collection schoolAttendanceStatus = CodeHelper.getSchoolAttendanceStatusCodes();
	ArrayList activeSchoolAttendanceStatus = new ArrayList();
	Iterator i = schoolAttendanceStatus.iterator();
	while (i.hasNext())
	{
	    CodeResponseEvent codeRespEvent = (CodeResponseEvent) i.next();
	    if (codeRespEvent.getStatus().toUpperCase() != null && codeRespEvent.getStatus().toUpperCase().equals(PDCodeTableConstants.INACT))
	    {
		continue;
	    }
	    else
	    {
		activeSchoolAttendanceStatus.add(codeRespEvent);
	    }
	}
	form.setAttendanceStatus(activeSchoolAttendanceStatus);
	form.setProgramAttending(CodeHelper.getProgramAttendingCodes());
	form.setGradeLevels(CodeHelper.getGradeLevelsCodes());
	if (form.getMemberDetailsBeanList().size() < 3)
	{
	    form.setAddGuradianFlag("Y");
	}

	//populate dateOutTo current Date 
	/*if(form.getDateOut()==null){ //bug fix: 89372 //89530
	    form.setDateOut(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	}*/
	//form.setCheckedOutTo(SecurityUIHelper.getLogonId()); Bug #75083
	form.setLastActionBy(SecurityUIHelper.getLogonId());
	form.setLastUpdate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setOperator(UIUtil.getCurrentUserID());
	//if JuvAddress is null (when no address provided at create juvenile)
	if (form.getJuvAddress() == null)
	{
	    Address a = new Address();
	    form.setJuvAddress(a);
	}
	return aMapping.findForward(UIConstants.UPDATE);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateGuardian(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	JuvenileReferralMemberDetailsBean selectedMemberBean = form.getSelectedMemberBean();

	//84790
	JuvenileReferralMemberDetailsBean memberBean = new JuvenileReferralMemberDetailsBean();
	try
	{
	    BeanUtils.copyProperties(memberBean, selectedMemberBean);
	}
	catch (IllegalAccessException e)
	{
	    e.printStackTrace();
	}
	catch (InvocationTargetException e)
	{
	    e.printStackTrace();
	}

	if (form.getMemberDetailsBeanList() != null && !form.getMemberDetailsBeanList().isEmpty())
	{
	    int sameRelationshipCount = 0;
	    List<JuvenileReferralMemberDetailsBean> memDetails = form.getMemberDetailsBeanList();
	    Iterator<JuvenileReferralMemberDetailsBean> memDetailsItr = memDetails.iterator();
	    while (memDetailsItr.hasNext())
	    {
		JuvenileReferralMemberDetailsBean memberDetailsBean = memDetailsItr.next();
		if (memberDetailsBean != null)
		{
		    if (memberDetailsBean.getRelationshipId() != null && memberDetailsBean.getRelationshipId().equalsIgnoreCase(memberBean.getRelationshipId()))
		    {
			sameRelationshipCount++;
		    }
		}
	    }
	    if (sameRelationshipCount > 1)
	    {
		form.getSelectedMemberBean().setRelationshipId("");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The same relationship cannot be used for more than one family member.  Please modify entry"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	//84970

	Name fullName = new Name(selectedMemberBean.getFirstName(), selectedMemberBean.getMiddleName(), selectedMemberBean.getLastName());
	StringBuffer formattedName = new StringBuffer(fullName.getFormattedName());
	selectedMemberBean.setFormattedName(formattedName.toString());
	selectedMemberBean.getMemberAddress().setAddressType(CodeHelper.getCodeDescription(PDCodeTableConstants.ADDRESS_TYPE, selectedMemberBean.getMemberAddress().getAddressTypeId()));
	selectedMemberBean.getMemberAddress().setStreetNumSuffix(CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_SUFFIX, selectedMemberBean.getMemberAddress().getStreetNumSuffixId()));
	selectedMemberBean.getMemberAddress().setStreetType(CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_TYPE, selectedMemberBean.getMemberAddress().getStreetTypeId()));
	selectedMemberBean.getMemberAddress().setState(CodeHelper.getCodeDescription(PDCodeTableConstants.STATE_ABBR, selectedMemberBean.getMemberAddress().getStateId()));
	selectedMemberBean.getMemberAddress().setCounty(CodeHelper.getCodeDescription(PDCodeTableConstants.COUNTY, selectedMemberBean.getMemberAddress().getCountyId()));
	selectedMemberBean.setFormattedAddress(JuvenileHelper.formatAddress(form.getSelectedMemberBean().getMemberAddress()));
	selectedMemberBean.setFormattedPhone(JuvenileHelper.formatPhone(form.getSelectedMemberBean().getContactPhoneNumber()));
	selectedMemberBean.setRelationshipDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.RELATIONSHIP_JUVENILE_REFERRAL, selectedMemberBean.getRelationshipId()));
	selectedMemberBean.setFormattedSSN(selectedMemberBean.getSSN().getFormattedSSN());
	selectedMemberBean.setCompleteSSN(selectedMemberBean.getSSN().getSSN());
	//BUG 80520 changes
	if (form.getIncarceratedFlag() != null && !form.getIncarceratedFlag().isEmpty())
	{
	    if (form.getIncarceratedFlag().equalsIgnoreCase("N"))
	    {
		selectedMemberBean.setIncarcerated(false);
		selectedMemberBean.setIncarceratedOrDeceased("");
		selectedMemberBean.setIncarceratedOrDeceasedDesc("");
		selectedMemberBean.setDeceased(false);
	    }
	}
	//BUG 80520 changes ENDS
	//BUG 80712 Changes
	if (selectedMemberBean.getIncarceratedOrDeceased() != null && !selectedMemberBean.getIncarceratedOrDeceased().isEmpty())
	{
	    if (selectedMemberBean.getIncarceratedOrDeceased().equalsIgnoreCase("I"))
	    {
		selectedMemberBean.setIncarceratedOrDeceasedDesc("Incarcerated");
		selectedMemberBean.setIncarcerated(true);
		selectedMemberBean.setMemberAddress(new Address());
		selectedMemberBean.setFormattedAddress("");
		selectedMemberBean.setFormattedPhone("");
		selectedMemberBean.setPhoneType("");
		selectedMemberBean.setContactPhoneNumber(new PhoneNumber());
		selectedMemberBean.setDeceased(false);
	    }
	    else
		if (selectedMemberBean.getIncarceratedOrDeceased().equalsIgnoreCase("D"))
		{
		    selectedMemberBean.setIncarceratedOrDeceasedDesc("Deceased");
		    selectedMemberBean.setDeceased(true);
		    selectedMemberBean.setIncarcerated(false);
		    selectedMemberBean.setMemberAddress(new Address());
		    selectedMemberBean.setFormattedAddress("");
		    selectedMemberBean.setFormattedPhone("");
		    selectedMemberBean.setContactPhoneNumber(new PhoneNumber());
		    selectedMemberBean.setPhoneType("");
		}
	}
	//BUG 80712 Changes END
	/*   if(selectedMemberBean.isIncarcerated()){
	selectedMemberBean.setIncarceratedOrDeceasedDesc("Incarcerated");
	selectedMemberBean.setIncarceratedOrDeceasedDesc("I");
	selectedMemberBean.setMemberAddress(new Address());
	   }else  if(selectedMemberBean.isDeceased()){
	selectedMemberBean.setIncarceratedOrDeceasedDesc("D");
	selectedMemberBean.setIncarceratedOrDeceasedDesc("Deceased");
	selectedMemberBean.setMemberAddress(null);
	   }*/

	if (selectedMemberBean.getPhoneInd() != null && selectedMemberBean.getPhoneInd().equals("P"))
	{
	    selectedMemberBean.setPhoneIndDesc("Primary");
	}
	else
	    if (selectedMemberBean.getPhoneInd() != null && selectedMemberBean.getPhoneInd().equals("U"))
	    {
		selectedMemberBean.setPhoneIndDesc("Unknown");
	    }
	    else
		selectedMemberBean.setPhoneIndDesc("");

	if (selectedMemberBean.getPhoneType() != null && selectedMemberBean.getPhoneType().equals("HM"))
	{
	    selectedMemberBean.setPhoneTypeDesc("Home");
	}
	else
	    if (selectedMemberBean.getPhoneType() != null && selectedMemberBean.getPhoneType().equals("MO"))
	    {
		selectedMemberBean.setPhoneTypeDesc("Mobile");
	    }
	    else
	    {
		selectedMemberBean.setPhoneTypeDesc("");
	    }

	/*String streetNumber = selectedMemberBean.getMemberAddress().getStreetNum();
	StringBuffer buffer = new StringBuffer();
	
	for (int i = 0; i < streetNumber.length(); i++)
	{
	    char ch = streetNumber.charAt(i);
	    if (Character.isDigit(ch) == false)
	    {
		selectedMemberBean.getMemberAddress().setValidated("N");
		break;
	    }
	    else
	    {
		if (ch >= '0' && ch <= '9')
		{
		    buffer.append(ch);
		}
		AddressHelper.validateAddress(selectedMemberBean.getMemberAddress());
	    }
	}*/
	selectedMemberBean.setGuardianEditedFlag("Y");
	form.setGuardianEditFlag("N");
	if (form.getMemberDetailsBeanList().size() == 3)
	{
	    form.setAddGuradianFlag("N");
	}
	else
	{
	    form.setAddGuradianFlag("Y");
	}
	return aMapping.findForward(UIConstants.UPDATE_CONITNUE);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward loadFamilyMember(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	String famMemberId = (String) aRequest.getParameter("famMemId");
	List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList = form.getMemberDetailsBeanList();
	if (memberDetailsBeanList != null && memberDetailsBeanList.size() > 0)
	{
	    Iterator memBeanListItr = memberDetailsBeanList.iterator();
	    while (memBeanListItr.hasNext())
	    {
		JuvenileReferralMemberDetailsBean memBean = (JuvenileReferralMemberDetailsBean) memBeanListItr.next();
		if (memBean.getMemberNum().equals(famMemberId))
		{
		    //set in the form
		    memBean.setSSN(UIUtil.convertSSN(memBean.getFormattedSSN()));
		    if (memBean.getIncarceratedOrDeceased() != null && !memBean.getIncarceratedOrDeceased().isEmpty())
		    {
			if (memBean.getIncarceratedOrDeceased().equalsIgnoreCase("I"))
			{
			    memBean.setIncarceratedOrDeceasedDesc("Incarcerated");
			    memBean.setIncarcerated(true);
			    form.setIncarceratedFlag("I");
			}
			else
			    if (memBean.getIncarceratedOrDeceased().equalsIgnoreCase("D"))
			    {
				memBean.setIncarceratedOrDeceasedDesc("Deceased");
				memBean.setDeceased(true);
			    }
		    }
		    form.setSelectedMemberBean(memBean);
		    form.setGuardianEditFlag("Y");
		    form.setAddGuradianFlag("N");//to do check
		    break;
		}
	    }
	}
	form.getSelectedMemberBean().setRelationships(CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE_REFERRAL, true));
	form.setStreetNumSuffixList(CodeHelper.getCodes(PDCodeTableConstants.STREET_SUFFIX, true));
	form.setStreetTypeList(CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE, true));
	form.setStateList(CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR, true));
	form.setAddressTypeList(CodeHelper.getCodes(PDCodeTableConstants.ADDRESS_TYPE, true));
	form.setCountyList(CodeHelper.getCodes(PDCodeTableConstants.COUNTY, true));
	return aMapping.findForward(UIConstants.UPDATE);
    }

    /*
     * cancel
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward(UIConstants.CANCEL));
    }

    /**
     * Back
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.BACK);
	return forward;
    }

    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setSchoolNDistrictList(null);
	form.setSchoolName("");
	ActionForward forward = aMapping.findForward("refresh");
	return forward;
    }

    /**
     * Next (NEXT in Update Juvenile FLOW)
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("updateJuvenile");
	if (form.getDateOfBirth() != null)
	{ // Get age based on year
	    boolean isJuvenile = false;
	    int age = UIUtil.getAgeInYears(DateUtil.stringToDate(form.getDateOfBirth(), DateUtil.DATE_FMT_1));
	    if (age > 0)
	    {
		if (age >= 10 && age <= 19)
		{
		    isJuvenile = true;
		}
	    }
	    if (!isJuvenile)
	    {
		form.setAction("updateJuvenile");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile must be between 10 to 19 years of age. Please contact Data Corrections for support"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	Name fullName = new Name(form.getFirstName(), form.getMiddleName(), form.getLastName(), form.getNameSuffix());
	form.setFormattedName(fullName.getFormattedName());
	form.setSex(CodeHelper.getCodeDescription(PDCodeTableConstants.JJS_SEX, form.getSexId()));
	form.setRace(CodeHelper.getCodeDescription(PDCodeTableConstants.RACE, form.getOriginalRaceId()));
	if (form.isHispanic())
	{
	    form.setHispanicStr("Y");
	    form.setHispanicDesc("Yes");
	}
	else
	{
	    form.setHispanicStr("N");
	    form.setHispanicDesc("No");
	}

	//checkoutTODesc
	if (form.getCheckedOutTo() != null && !form.getCheckedOutTo().equals(""))
	{
	    if (form.getCheckedOutTo().trim().length() < 4)
	    {
		IName name = SecurityUIHelper.getUserName("UV" + form.getCheckedOutTo());
		if (name != null)
		{
		    form.setCheckedOutToDesc(name.getFormattedName());
		}
	    }
	    else
	    {
		IName name = SecurityUIHelper.getUserName(form.getCheckedOutTo());
		if (name != null)
		{
		    form.setCheckedOutToDesc(name.getFormattedName());
		}
	    }
	}
	//Last Action By
	IName name = SecurityUIHelper.getUserName(form.getLastActionBy());
	if (name != null)
	{
	    form.setLastActionByDesc(name.getFormattedName());
	}

	//operator
	name = SecurityUIHelper.getUserName(form.getOperator());
	if (name != null)
	{
	    form.setOperatorDesc(name.getFormattedName());
	}

	//validate Juvenile address
	StringBuffer buffer = new StringBuffer();
	if (form.getJuvAddress() != null)
	{
	    Address juvAddress = form.getJuvAddress();
	    for (int i = 0; i < juvAddress.getStreetNumber().length(); i++)
	    {
		char ch = juvAddress.getStreetNumber().charAt(i);
		if (Character.isDigit(ch) == false)
		{
		    form.getJuvAddress().setValidated("N");
		    break;
		}
		else
		{
		    if (ch >= '0' && ch <= '9')
		    {
			buffer.append(ch);
		    }
		    AddressHelper.validateAddress(form.getJuvAddress());
		}
	    }
	}
	form.setJuvFormattedAddress(JuvenileHelper.formatAddress(form.getJuvAddress()));
	//school information
	Iterator schoonNamesItr = form.getSchools().iterator();
	while (schoonNamesItr.hasNext())
	{
	    JuvenileSchoolDistrictCodeResponseEvent schoolwithDistResp = (JuvenileSchoolDistrictCodeResponseEvent) schoonNamesItr.next();
	    if (schoolwithDistResp.getSchoolCode().equalsIgnoreCase(form.getSchoolId()))
	    {
		form.setSchoolName(schoolwithDistResp.getSchoolDescription());
		form.setSchoolDistrictDescription(schoolwithDistResp.getDistrictDescription());
		form.setTeacode(schoolwithDistResp.getTeaCode());
		break;
	    }
	}
	Iterator gradesItr = form.getGradeLevels().iterator();
	form.setGradeLevelDescription("");
	while (gradesItr.hasNext())
	{
	    CodeResponseEvent gradeLevelsRespEvt = (CodeResponseEvent) gradesItr.next();
	    if (gradeLevelsRespEvt.getCode().equalsIgnoreCase(form.getGradeLevelId()))
	    {
		form.setGradeLevelDescription(gradeLevelsRespEvt.getDescription());
		break;
	    }
	}
	Iterator pgmItr = form.getProgramAttending().iterator();
	form.setProgramAttendingDescription("");
	while (pgmItr.hasNext())
	{
	    CodeResponseEvent pgmAttdngRespEvt = (CodeResponseEvent) pgmItr.next();
	    if (pgmAttdngRespEvt.getCode().equalsIgnoreCase(form.getProgramAttendingId()))
	    {
		form.setProgramAttendingDescription(pgmAttdngRespEvt.getDescription());
		break;
	    }
	}
	Iterator schlAttStatusItr = form.getAttendanceStatus().iterator();
	form.setAttendanceStatusDescription("");
	while (schlAttStatusItr.hasNext())
	{
	    CodeResponseEvent schoolAttStatus = (CodeResponseEvent) schlAttStatusItr.next();
	    if (schoolAttStatus.getCode().equalsIgnoreCase(form.getAttendanceStatusId()))
	    {
		form.setAttendanceStatusDescription(schoolAttStatus.getDescription());
	    }
	}
	/*	Iterator enrollStatItr = form.getExitTypes().iterator();
		while(enrollStatItr.hasNext()){
		    CodeResponseEvent enrollStatRespEvt = (CodeResponseEvent)enrollStatItr.next();
		    if(enrollStatRespEvt.getCode().equalsIgnoreCase(form.getExitTypeId())){
			form.setExitTypeDescription(enrollStatRespEvt.getDescription());
		    }
		}
		*///above commented for BUG 76348
	ActionForward forward = aMapping.findForward(UIConstants.NEXT);
	return forward;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward showAddGuradian(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setGuardianEditFlag("Y");
	form.setSubsequentMessage("");
	form.setSelectedMemberBean(new JuvenileReferralMemberDetailsBean());
	form.getSelectedMemberBean().setRelationships(CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE_REFERRAL, true));
	return (aMapping.findForward("addNewGuradian"));
    }

    /**
     * create Referral
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward createReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.CREATE);
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	
	form.setReferralList(new ArrayList());
	form.setOriginalChargeReferrals(new ArrayList());
	form.setAdminReferralFlag("");
	form.setDisableAddRefBtn(false);
	//get the form from the briefing details page.
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	JuvenileReferralHelper.populateReferralForm(briefingDetailsForm, form);
	form.setOperator(UIUtil.getCurrentUserID());
	form.setOperatorDesc(UIUtil.getCurrentUserName());
	form.setOffenseList(new ArrayList());
	//get the latest referral number
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
	event.setJuvenileNum(form.getJuvenileNum());

	List<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt = MessageUtil.postRequestListFilter(event, JuvenileProfileReferralListResponseEvent.class);

	//sorts in descending order by Referral Num. 
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) juvProfRefListEvt, new Comparator<JuvenileProfileReferralListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
	    {
		if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
		    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
		else
		    return -1;
	    }
	});

	if (juvProfRefListEvt.iterator().hasNext())
	{
	    JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent) juvProfRefListEvt.iterator().next();
	    int refNum = 0;
	    if (Integer.valueOf(resp.getReferralNumber()) % 10 == 0)
	    {
		refNum = (Integer.valueOf(resp.getReferralNumber())) + 10;

	    }
	    else
	    {
		StringBuffer sb = new StringBuffer(resp.getReferralNumber().substring(0, 3)).append(0);
		refNum = (Integer.valueOf(sb.toString())) + 10;
	    }

	    form.setNewRefNum(String.valueOf(refNum));
	}
	else
	{
	    form.setNewRefNum("1010");
	}

	form.setNewRefDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	//Get the Intake decisions	    
	form.setIntakeDecisions(JuvenileCaseHelper.getReferralDecisions());
	Collections.sort((List) form.getIntakeDecisions(), JuvenileReferralDispositionCode.CodeComparator);
	form.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
	form.setRefSources(JuvenileReferralHelper.getAllReferralSources());
	form.clearRefDetails();
	form.setReferralList(new ArrayList());
	form.setAction("createReferral");
	form.setActivateSupervision("N");
	return forward;
    }

    /**
     * override Assignment
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */

    public ActionForward overrideAssignment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.OVERRIDE_ASSIGNMENT);
	JuvenileReferralForm referralForm = (JuvenileReferralForm) aForm;
	//get the form from the briefing details page.

	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	JuvenileReferralHelper.populateReferralForm(briefingDetailsForm, referralForm);
	referralForm.setOffenseList(new ArrayList());
	String juvNum = referralForm.getJuvenileNum();
	List<JuvenileProfileReferralListResponseEvent> openReferralsOnlyList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	GetActiveCasefileReferralsEvent disEvent = (GetActiveCasefileReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETACTIVECASEFILEREFERRALS);
	disEvent.setJuvenileNum(juvNum);

	openReferralsOnlyList = (List<JuvenileProfileReferralListResponseEvent>) MessageUtil.postRequestListFilter(disEvent, JuvenileProfileReferralListResponseEvent.class);

	//Map<String, JuvenileProfileReferralListResponseEvent> referralsMap = new HashMap<String, JuvenileProfileReferralListResponseEvent>();
	//Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(juvNum);

	//Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	/*while (referralIterator.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    if (referral != null && referral.getCloseDate() == null)
	    {
		String sevSubType="";
		//if (referral.getMostSevereOffense() != null && !("R").equalsIgnoreCase(referral.getMostSevereOffense().getSeveritySubtype()))
		//referral is not an administrative level offense, check the severitySubType of offense with SEQNUM = 1
		if (referral.getOffenses() != null)
		{ 
		    Iterator<JJSOffense> offenseItr2 = referral.getOffenses().iterator();
		    while (offenseItr2.hasNext())
		    {
			JJSOffense offense = offenseItr2.next();
			if (offense.getSequenceNum().equalsIgnoreCase("1"))
			{
			    sevSubType = offense.getOffenseCode().getSeveritySubtype();
			    break;
			}
			
		    }
		    if (sevSubType != null && !sevSubType.equalsIgnoreCase("R") && !sevSubType.equalsIgnoreCase("Z")){ //US 86851
		 
		    //locate the petition record with the highest sequence number (JUVENILE_PETITION. PetitionSequenceNumber) associated to the Juvenile Number and Referral Number from the setting record.
		    //If there is an associated PETITION record, display the petition allegation. //of the highest seq Num 
		    List<PetitionResponseEvent> petitionResponses = InterviewHelper.getPetitionsRespForReferral(juvNum, referral.getReferralNumber());
		    if (petitionResponses != null && !petitionResponses.isEmpty())
		    {
			Collections.sort((List<PetitionResponseEvent>) petitionResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
			    @Override
			    public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
			    {
				if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
				    return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
				else
				    return -1;
			    }
			}));	
			Iterator<PetitionResponseEvent> petitionRespItr = petitionResponses.iterator();
			while (petitionRespItr.hasNext())
			{
			    PetitionResponseEvent petitionResp = petitionRespItr.next();
			    if (petitionResp != null)
			    {
				referral.setPetitionNumber(petitionResp.getPetitionNum());
				referral.setOffense(petitionResp.getOffenseCodeId());
				referral.setOffenseDesc(petitionResp.getOffenseShortDesc());
				if (petitionResp.getOffenseCodeId() != null)
				{
				   // Code copied from HandleJuvenileProfileTransferredOffensesSelectionAction
				    GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
				    jocEvent.setAlphaCode(petitionResp.getOffenseCodeId());
				    List events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
				    if (events != null & !events.isEmpty())
				    {
					for (int x = 0; x < events.size(); x++)
					{
					    JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);
					   
					    if (!"Y".equals(respEvent.getInactiveInd()) && petitionResp.getOffenseCodeId().equalsIgnoreCase(respEvent.getOffenseCode()) && !(respEvent.getDpsOffenseCode().isEmpty()))
					    {
						referral.setOffenseCategory(respEvent.getCategory());
						break;
					    }
					}
				    }
				}
				break;
			    }
			}
		    }
		    else
			// if there isn’t an associated PETITION record, display the short description of the offense associated to the referral.
			// if multiple offenses, take offense with seqNum = 1 (oldest offense)
		    {
			Collection<JJSOffense> offenses = referral.getOffenses();
			if (offenses != null)
			{
			    Iterator<JJSOffense> offenseItr = offenses.iterator();
			    while (offenseItr.hasNext())
			    {
				JJSOffense offense = offenseItr.next();
				if (offense.getSequenceNum().equalsIgnoreCase("1"))
				{
				    referral.setOffense(offense.getOffenseCodeId());
				    referral.setOffenseDesc(offense.getOffenseDescription());
				    referral.setOffenseCategory(offense.getCatagory());
				    break;
				}
			    }
			}
		    }
		    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvNum, referral.getReferralNumber());
		    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();
		    
		    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
			@Override
			public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
			{
			 //89766 User-story
			    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
			    {
				  int seq1 = Integer.parseInt(evt1.getRefSeqNum());
				    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
				    Integer seq1Int = new Integer(seq1);
				    Integer seq2Int = new Integer(seq2);
				return seq1Int.compareTo(seq2Int);
			    }
			    else
				return -1;
			}
		    }));
		    
		   //RRY-- Needed to group by assignment date first-- RRY
		    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
			@Override
			public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
			{
			    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
				    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
			    else
				return -1;
			    }
		    }));


		    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
		    if (!caseFileRefItr.hasNext())
			{
			    referral.setAssignmentType(refCasefileList.getAssignmentType());
			    referral.setAssignmentDate(refCasefileList.getAssignmentDate());
			    referral.setSupervisionCategoryId(refCasefileList.getSupervisionCategoryId());
			    referral.setSupervisionTypeId(refCasefileList.getSupervisionTypeId());
			    referral.setSupervisionCategory(refCasefileList.getSupervisionCategory());
			    referral.setSupervisionType(refCasefileList.getSupervisionType());
			    referral.setJpoId(refCasefileList.getJpo());
			}
		    while (caseFileRefItr.hasNext())
		    {
			JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
			
			referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
			referral.setSupervisionType(caseFileReferral.getSupervisionType());
			referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
			referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
			OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
			if (officerProfileResponse != null)
			{
			    referral.setJpoId(officerProfileResponse.getUserId());
			    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
			    referral.setJpo(officerFullName);
			}
			break;
		    }
		    if (referral.getFinalDisposition() == null)
		    {
			referral.setCourtDate(null); //court date is same as the final disposition date, but only when there is a final disposition 
		    }
		    openReferralsOnlyList.add(referral);
		    referralsMap.put(referral.getReferralNumber(), referral);//putting all the referrals that can be overridden in a Map
		    }
		}
	    }
	}*/
	if (openReferralsOnlyList.size() > 0)
	{
	    //sort referrals by ref num desc
	    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) openReferralsOnlyList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			return Integer.valueOf(evt1.getReferralNumber()).compareTo(Integer.valueOf(evt2.getReferralNumber()));
		    else
			return -1;
		}
	    }));
	    //referralForm.setReferralListMap(referralsMap);
	    referralForm.setReferralList(openReferralsOnlyList);
	    referralForm.setIntakeDecisions(JuvenileCaseHelper.getReferralDecisions());
	    //Collections.sort((List) referralForm.getIntakeDecisions(), JuvenileReferralDispositionCode.CodeComparator);
	    referralForm.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
	    referralForm.setRefSources(JuvenileReferralHelper.getAllReferralSources());
	    //referralForm.clearRefDetails();
	    referralForm.setOverrideReason(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.OVERRIDE_REASON, true));
	    /*  if (referralForm.getAction()!= null && referralForm.getAction().equalsIgnoreCase("overrideSuccess")){
	     referralForm.setAction("overrideSuccess");
	      }else {
	      referralForm.setAction("overrideAssignmentfromRefBrief");
	      }*///commented for BUG 85682 reopened
	    referralForm.setAction("overrideAssignmentfromRefBrief");
	    //clearing out the below fields for the REFRESH button on the Override page
	    referralForm.setAssignmentType("");
	    referralForm.setSupervisionCat("");
	    referralForm.setSupervisionType("");
	    referralForm.setJpo("");
	    referralForm.setDisbleAssignment("N");
	    referralForm.setOverrideReasonStr("");
	    referralForm.setOverrideOTHComments("");
	    referralForm.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	    referralForm.setOperator(UIUtil.getCurrentUserID());
	    referralForm.setOperatorDesc(UIUtil.getCurrentUserName());
	    
	    return forward;
	   
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "There is no active casefile. The override is not allowed"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward("briefing");
	}
	
	 
    }

    /**
     * add Guardian To List
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addGuardianToList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	//form.setAction("addToList");
	JuvenileReferralMemberDetailsBean memberBean = new JuvenileReferralMemberDetailsBean();
	try
	{
	    BeanUtils.copyProperties(memberBean, form.getSelectedMemberBean());
	}
	catch (IllegalAccessException e)
	{
	    e.printStackTrace();
	}
	catch (InvocationTargetException e)
	{
	    e.printStackTrace();
	}

	
	if( form.getMemberDetailsBeanList()!=null && form.getMemberDetailsBeanList().isEmpty() ){ //added for BUG 166050 STARTS
		memberBean.setPrimaryContact("true");
	 } //added for BUG 166050 ENDS
	// bug #84970
	if (form.getMemberDetailsBeanList() != null && !form.getMemberDetailsBeanList().isEmpty())
	{
	    List<JuvenileReferralMemberDetailsBean> memDetails = form.getMemberDetailsBeanList();
	    Iterator<JuvenileReferralMemberDetailsBean> memDetailsItr = memDetails.iterator();
	    while (memDetailsItr.hasNext())
	    {
		JuvenileReferralMemberDetailsBean memberDetailsBean = memDetailsItr.next();
		if (memberDetailsBean != null)
		{
		    if (memberDetailsBean.getRelationshipId() != null && memberDetailsBean.getRelationshipId().equalsIgnoreCase(memberBean.getRelationshipId()))
		    {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The same relationship cannot be used for more than one family member.  Please modify entry"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    
		    if(!"true".equalsIgnoreCase( memberDetailsBean.getPrimaryContact())){
			
			int size = memDetails.size();
		    }
		}
	    }
	}
	//84970

	Name fullName = new Name(memberBean.getFirstName(), memberBean.getMiddleName(), memberBean.getLastName());
	StringBuffer formattedName = new StringBuffer(fullName.getFormattedName());
	memberBean.setFormattedName(formattedName.toString());

	memberBean.setFormattedAddress(JuvenileHelper.formatAddress(form.getSelectedMemberBean().getMemberAddress()));
	memberBean.setRelationshipDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.RELATIONSHIP_JUVENILE_REFERRAL, memberBean.getRelationshipId()));
	memberBean.setFormattedPhone(JuvenileHelper.formatPhone(form.getSelectedMemberBean().getContactPhoneNumber()));
	memberBean.setFormattedSSN(memberBean.getSSN().getFormattedSSN());

	if (memberBean.getIncarceratedOrDeceased() != null && !memberBean.getIncarceratedOrDeceased().isEmpty())
	{
	    if (memberBean.getIncarceratedOrDeceased().equalsIgnoreCase("I"))
	    {
		memberBean.setIncarceratedOrDeceasedDesc("Incarcerated");
		memberBean.setIncarcerated(true);
	    }
	    else
	    {
		memberBean.setIncarceratedOrDeceasedDesc("Deceased");
		memberBean.setDeceased(true);
	    }
	}

	if (memberBean.getPhoneInd() != null && memberBean.getPhoneInd().equals("P"))
	{
	    memberBean.setPhoneIndDesc("Primary");
	}
	else
	    if (memberBean.getPhoneInd() != null && memberBean.getPhoneInd().equals("U"))
	    {
		memberBean.setPhoneIndDesc("Unknown");
	    }

	if (memberBean.getPhoneType() != null && memberBean.getPhoneType().equals("HM"))
	{
	    memberBean.setPhoneTypeDesc("Home");
	}
	else
	{
	    memberBean.setPhoneTypeDesc("Mobile");
	}

	String streetNumber = memberBean.getMemberAddress().getStreetNum();
	StringBuffer buffer = new StringBuffer();

	for (int i = 0; i < streetNumber.length(); i++)
	{
	    char ch = streetNumber.charAt(i);
	    if (Character.isDigit(ch) == false)
	    {
		memberBean.getMemberAddress().setValidated("N");
		break;
	    }
	    else
	    {
		if (ch >= '0' && ch <= '9')
		{
		    buffer.append(ch);
		}
		AddressHelper.validateAddress(memberBean.getMemberAddress());
	    }
	}
	if (form.getMemberDetailsBeanList() != null && !form.getMemberDetailsBeanList().isEmpty())
	{
	    if (form.getMemberDetailsBeanList().size() >= 3)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A maximum of three parent/guardian entries are allowed. Please modify entry"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	memberBean.setNewMemflag("Y");

	//BUG# 79455 generate memberNum [so when user try to update before submitting the form],  to figure out which mem is the user trying to edit
	if (memberBean.getRelationshipId().equalsIgnoreCase("BF"))
	{
	    memberBean.setMemberNum("1");
	}
	else
	    if (memberBean.getRelationshipId().equalsIgnoreCase("BM"))
	    {
		memberBean.setMemberNum("2");
	    }
	    else
		if (memberBean.getRelationshipId().equalsIgnoreCase("OR"))
		{
		    memberBean.setMemberNum("3");
		}//ends changes for BUG #79455

	if (memberBean != null)
	{
	    if (form.getMemberDetailsBeanList() == null)
	    {
		form.setMemberDetailsBeanList(new ArrayList<JuvenileReferralMemberDetailsBean>());
	    }
	    form.getMemberDetailsBeanList().add(memberBean);
	}
	form.getSelectedMemberBean().clear();
	form.setGuardianEditFlag("N");
	if (form.getMemberDetailsBeanList() != null && !form.getMemberDetailsBeanList().isEmpty())
	{
	    if (form.getMemberDetailsBeanList().size() == 3)
	    {
		form.setAddGuradianFlag("N");
	    }
	}
	return (aMapping.findForward(UIConstants.ADD_SUCCESS));
    }

    /*
     * 
     */
    private boolean notNullNotEmptyCollection(Collection col)
    {
	return (col != null && !col.isEmpty());
    }

    /**
     * notNullNotEmptyString
     * 
     * @param str
     * @return boolean
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.length() > 0));
    }

    public ActionForward printMasterDisplay(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	//JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;

	//get the form from the briefing details page.
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	JuvenileReferralHelper.populateReferralForm(briefingDetailsForm, form);

	TEAStudentDataReportBean reportBean = new TEAStudentDataReportBean();

	if(briefingDetailsForm != null){
	    
        	reportBean.setJuvenileName(briefingDetailsForm.getProfileDetail().getFormattedName());
        	reportBean.setJuvenileNumber(briefingDetailsForm.getJuvenileNum());
        	reportBean.setDateOfBirth(DateUtil.dateToString(briefingDetailsForm.getProfileDetail().getDateOfBirth(), DateUtil.DATE_FMT_1));
        	reportBean.setCurrentAge(briefingDetailsForm.getAge());
        	reportBean.setHairColor(briefingDetailsForm.getProfileDetail().getNaturalHairColor());
        	reportBean.setEyeColor(briefingDetailsForm.getProfileDetail().getNatualEyeColor());
        	reportBean.setEthnicityDesc(briefingDetailsForm.getEthnicity());
        	reportBean.setGender(briefingDetailsForm.getProfileDetail().getSex());
        	reportBean.setRace(briefingDetailsForm.getProfileDetail().getRace());
        	if ("Y".equalsIgnoreCase(briefingDetailsForm.getProfileDetail().getHispanic()))
        	{
        	    reportBean.setHispanicDesc("YES");
        	}
        	else
        	{
        	    reportBean.setHispanicDesc("NO");
        	}
        	if (briefingDetailsForm.getProfileDetail().isMultiracial())
        	{
        
        	    reportBean.setMultiracialDesc("YES");
        	}
        	else
        	{
        
        	    reportBean.setMultiracialDesc("NO");
        	}
        	
	}
	
	reportBean.setFamilyInformation(new ArrayList());
	reportBean.setSchoolHistory(new ArrayList());
	
	if(briefingDetailsForm != null && briefingDetailsForm.getMemberAddress() != null){
	    
	    if(briefingDetailsForm.getMemberAddress().getCounty() != null){
		reportBean.setCounty(briefingDetailsForm.getMemberAddress().getCounty());
	    }
	    
	    if(briefingDetailsForm.getMemberAddress().getStreetAddress() != null){
		reportBean.setFullAddress(briefingDetailsForm.getMemberAddress().getStreetAddress());
	    }
	    
	    if(briefingDetailsForm.getMemberAddress().getCityStateZip() != null){
		reportBean.setCityStateZip(briefingDetailsForm.getMemberAddress().getCityStateZip());
	    }
	}
	
	if(briefingDetailsForm != null && briefingDetailsForm.getMemberContact() != null){
	    
	    if(briefingDetailsForm.getMemberContact().getContactPhoneNumber() != null){
		reportBean.setPhoneNum(briefingDetailsForm.getMemberContact().getContactPhoneNumber().getFormattedPhoneNumber());
	    }
	    
	    if(briefingDetailsForm.getMemberContact().getContactType() != null){
		reportBean.setPhoneType(briefingDetailsForm.getMemberContact().getContactType());
	    }
	}
		

	reportBean.setGuardians((List<GuardianBean>) briefingDetailsForm.getGuardians());
	
	if(briefingDetailsForm != null && briefingDetailsForm.getSchool() != null){
	    reportBean.setCurrentSchool(briefingDetailsForm.getSchool());
	}
	

	aRequest.getSession().setAttribute("reportInfo", reportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	//let the pdfManager know that the report should be saved in the request during report creation
	aRequest.setAttribute("isPdfSaveNeeded", "true");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.REFERRAL_BRIEFING_MASTER_DISPLAY_REPORT);

	return null;

    }

    /**
     * update Referral
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	JuvenileReferralForm form = (JuvenileReferralForm) aForm;

	form.setMessage("");
	form.setUpdateMessage("");
	form.setSubsequentMessage("");

	String updateReferralAction = null;
	if ((form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral")))
	{
	    updateReferralAction = form.getAction();
	}

	String referralAssignmentType = null;
	if ((form.getAssignmentType() != null && !form.getAssignmentType().equals("")) && form.getAssignmentType() != null)
	{
	    referralAssignmentType = form.getAssignmentType();
	}

	String refNum = form.getNewRefNum();

	//check if user has update referral status feature
	String feature = "JCW-UPDATEREFSTAT";
	boolean grantedFeature = false;
	try
	{

	    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    if (securityManager != null)
	    {
		grantedFeature = securityManager.isAllowed(feature);
		if (grantedFeature)
		    form.setUpdateRefStatFeature("Y");
		else
		    form.setUpdateRefStatFeature("N");
	    }
	}
	catch (Throwable e)
	{
	    // ignore any exception as this is not visible to the user        
	}
	//get the form from the briefing details page.
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	JuvenileReferralHelper.populateReferralForm(briefingDetailsForm, form);
	form.setOperator(UIUtil.getCurrentUserID());
	form.setOperatorDesc(UIUtil.getCurrentUserName());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNum());
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	ArrayList<JuvenileProfileReferralListResponseEvent> openReferralsOnlyList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(form.getJuvenileNum());
	while (referralIterator.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    if (referral != null)
	    {

		List<PetitionResponseEvent> petitionResponses = InterviewHelper.getPetitionsRespForReferral(form.getJuvenileNum(), referral.getReferralNumber());
		if (petitionResponses != null && !petitionResponses.isEmpty())
		{
		    Collections.sort((List<PetitionResponseEvent>) petitionResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
			@Override
			public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
			{
			    if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
				return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
			    else
				return -1;
			}
		    }));
		    Iterator<PetitionResponseEvent> petitionRespItr = petitionResponses.iterator();
		    while (petitionRespItr.hasNext())
		    {
			PetitionResponseEvent petitionResp = petitionRespItr.next();
			if (petitionResp != null)
			{
			    referral.setPetitionNumber(petitionResp.getPetitionNum());
			    referral.setOffense(petitionResp.getOffenseCodeId());
			    referral.setOffenseDesc(petitionResp.getOffenseShortDesc());

			    if ("TRNDSP".equals(petitionResp.getOffenseCodeId()) || "TRNSIN".equals(petitionResp.getOffenseCodeId()) || "REGION".equals(petitionResp.getOffenseCodeId()) || "ISCOIN".equals(petitionResp.getOffenseCodeId()))
			    {
				for (JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses)
				{
				    if (referral.getReferralNumber().equals(transferredOffense.getReferralNum()))
				    {
					referral.setOffenseDesc(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
				    }
				}

			    }
			    //referral.setOffenseCategory(petitionResp.get)
			    if (petitionResp.getOffenseCodeId() != null)
			    {
				// JuvenileOffenseCodeResponseEvent jpEvent = null;
				GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
				jocEvent.setAlphaCode(petitionResp.getOffenseCodeId());
				List events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
				if (events != null & !events.isEmpty())
				{
				    for (int x = 0; x < events.size(); x++)
				    {
					JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);

					//if (!"Y".equals(respEvent.getInactiveInd()) && petitionResp.getOffenseCodeId().equalsIgnoreCase(respEvent.getOffenseCode()) && !(respEvent.getDpsOffenseCode().isEmpty()))
					if (petitionResp.getOffenseCodeId().equalsIgnoreCase(respEvent.getOffenseCode()))
					{
					    referral.setOffenseCategory(respEvent.getCategory());
					    break;
					}
				    }
				}
			    }
			    //insert above
			    break;
			}
		    }
		}
		else
		// if there isn’t an associated PETITION record, display the short description of the offense associated to the referral.
		{
		    // get the offense code for that juv,ref and highest seq Num
		    GetJuvenileCasefileOffensesEvent getOffenses = new GetJuvenileCasefileOffensesEvent();
		    getOffenses.setJuvenileNum(form.getJuvenileNum());
		    getOffenses.setReferralNum(referral.getReferralNumber());
		    CompositeResponse replyEvent = MessageUtil.postRequest(getOffenses);
		    Collection<JJSOffenseResponseEvent> offenses = MessageUtil.compositeToCollection(replyEvent, JJSOffenseResponseEvent.class);

		    // sorts in descending order by seq num.
		    Collections.sort((List<JJSOffenseResponseEvent>) offenses, new Comparator<JJSOffenseResponseEvent>() {
			@Override
			public int compare(JJSOffenseResponseEvent evt1, JJSOffenseResponseEvent evt2)
			{
			    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
			}
		    });
		    if (offenses != null)
		    {
			while (offenses.iterator().hasNext())
			{
			    JJSOffenseResponseEvent offense = offenses.iterator().next();
			    if (offense != null)
			    {
				referral.setOffense(offense.getOffenseCode());
				referral.setOffenseCategory(offense.getCatagory());
				referral.setOffenseDesc(offense.getOffenseDescription());
				if ("TRNDSP".equals(offense.getOffenseCode()) || "TRNSIN".equals(offense.getOffenseCode()) || "REGION".equals(offense.getOffenseCode()) || "ISCOIN".equals(offense.getOffenseCode()))
				{
				    for (JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses)
				    {
					if (referral.getReferralNumber().equals(transferredOffense.getReferralNum()))
					{
					    referral.setOffenseDesc(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
					}
				    }

				}
				break;
			    }
			    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode", offense.getOffenseCode());
			    if (offCode.getSeveritySubtype() != null && !offCode.getSeveritySubtype().equalsIgnoreCase("T"))
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "This assignment type requires an out of state or out of county level offense: TRNSIN, TRNDSP, ISCOIN or REGION.  Please check assignment type."));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			}
		    }
		}
		//JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(form.getOffenseCode()); this event has the severitySubType

		JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(form.getJuvenileNum(), referral.getReferralNumber());
		Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

		Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		    @Override
		    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		    {
			//89766 User-story
			if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
			{
			    int seq1 = Integer.parseInt(evt1.getRefSeqNum());
			    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
			    Integer seq1Int = new Integer(seq1);
			    Integer seq2Int = new Integer(seq2);
			    return seq1Int.compareTo(seq2Int);
			}
			else
			    return -1;
		    }
		}));
		Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
		if (!caseFileRefItr.hasNext())
		{
		    referral.setAssignmentType(refCasefileList.getAssignmentType());
		    referral.setAssignmentDate(refCasefileList.getAssignmentDate());
		    referral.setSupervisionCategoryId(refCasefileList.getSupervisionCategoryId());
		    referral.setSupervisionTypeId(refCasefileList.getSupervisionTypeId());
		    referral.setSupervisionCategory(refCasefileList.getSupervisionCategory());
		    referral.setSupervisionType(refCasefileList.getSupervisionType());
		    referral.setJpoId(refCasefileList.getJpo());
		}
		while (caseFileRefItr.hasNext())
		{
		    JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
		    referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
		    referral.setSupervisionType(caseFileReferral.getSupervisionType());
		    referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
		    referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
		    referral.setAssignmentDate(DateUtil.dateToString(caseFileReferral.getAssignmentDate(), DateUtil.DATE_FMT_1));
		    referral.setAssignmentType(caseFileReferral.getAssignmentType());
		    referral.setCasefileId(caseFileReferral.getCaseFileId());
		    referral.setCaseStatus(caseFileReferral.getCaseStatusCd());
		    OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
		    if (officerProfileResponse != null)
		    {
			referral.setJpoId(officerProfileResponse.getUserId());
			String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
			referral.setJpo(officerFullName);
		    }
		    break;
		}
		//US 71177 - if no assignment rec
		if (referral.getFinalDisposition() == null)
		{
		    referral.setCourtDate(null); //court date is same as the final disposition date, but only when there is a final disposition 
		}
		openReferralsOnlyList.add(referral);

	    }
	}
	//sorts in descending order by Referral Num. 
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) openReferralsOnlyList, new Comparator<JuvenileProfileReferralListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
	    {
		if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
		    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
		else
		    return -1;
	    }
	});

	form.setReferralList(openReferralsOnlyList);
	form.setNewRefDate("");
	form.setOffenseDate("");
	form.setUpdateRefStat("");
	form.setUpdateRefCloseDt("");
	form.setLoadAssmntFlag("N");
	form.setKeyMapLocation("");
	form.setInvestigationNum("");
	
	//reset DA info - these values get loaded - SubmitCreateJuvenileReferralAction (loadReferral)
	form.setDaLogNum(null);
	form.setLogStatus(null);
	form.setDaDateOut(null);
	form.setCJIS(null);
	
	form.setJotInfo(new JOTTO());
	if (form.getIntakeDecisions() == null || form.getIntakeDecisions().size() == 0)
	{
	    form.setIntakeDecisions(JuvenileCaseHelper.getReferralDecisions());
	    Collections.sort((List) form.getIntakeDecisions(), JuvenileReferralDispositionCode.CodeComparator);
	}
	if (form.getAssignmentTypes() == null || form.getAssignmentTypes().size() == 0)
	    form.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
	if (form.getRefSources() == null || form.getRefSources().size() == 0)
	    form.setRefSources(JuvenileReferralHelper.getAllReferralSources());
	form.setUpdateOffenseFlag("N");
	form.setUpdateAsmntTypeFlag("N");
	form.clearRefDetails();
	form.setAction("updateReferral");

	if (form.getUpdateAction() != null && form.getUpdateAction().equalsIgnoreCase("updateReferral"))
	{
	    form.setNewRefNum("");
	    form.setUpdateMessage("");
	    form.setSubsequentMessage("");
	    form.setMessage("");
	}
	else
	    if (form.getUpdateAction() != null && form.getUpdateAction().equalsIgnoreCase("confirmReferralUpdate"))
	    {
		loadReferral(form);
	    }

	if ((form.getUpdateAction() != null && form.getUpdateAction().equalsIgnoreCase("updateReferral")) && referralAssignmentType != null && updateReferralAction != null)
	{

	    form.setUpdateMessage("Referral " + refNum + " was successfully updated.");
	}
	
	//Bug 173955 - reset the intakeHistory list - this get populated when a referral radio button is clicked
	if(form.getIntakeHistoryList() != null && form.getIntakeHistoryList().size() > 0){
	    List<JJSSVIntakeHistory> intakeHistory = new ArrayList<JJSSVIntakeHistory>();
	    form.setIntakeHistoryList(intakeHistory);
	}	

	return (aMapping.findForward("updateReferral"));
    }
    
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateJuvenileSsn(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("updateJuvenileSsn");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward createReseal(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward("createReseal");
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setUpdateMessage("");
	form.setSubsequentMessage("");
	form.setMessage("");
	form.clearRefDetails();
	form.setReferralList(new ArrayList());
	//get the form from the briefing details page.
	
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	JuvenileReferralHelper.populateReferralForm(briefingDetailsForm, form);
	form.setOperator(UIUtil.getCurrentUserID());
	form.setOperatorDesc(UIUtil.getCurrentUserName());
	
	GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
	event.setJuvenileNum(form.getJuvenileNum());

	List<JuvenileProfileReferralListResponseEvent> currRefListResp = MessageUtil.postRequestListFilter(event, JuvenileProfileReferralListResponseEvent.class);

	//sorts in descending order by Referral Num. 
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) currRefListResp, new Comparator<JuvenileProfileReferralListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
	    {
		if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
		    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
		else
		    return -1;
	    }
	});

	if (currRefListResp.iterator().hasNext())
	{
	    JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent) currRefListResp.iterator().next();
	    int refNum = 0;
	    if (Integer.valueOf(resp.getReferralNumber()) % 10 == 0)
	    {
		refNum = (Integer.valueOf(resp.getReferralNumber())) + 10;

	    }
	    else
	    {
		StringBuffer sb = new StringBuffer(resp.getReferralNumber().substring(0, 3)).append(0);
		refNum = (Integer.valueOf(sb.toString())) + 10;
	    }

	    form.setNewRefNum(String.valueOf(refNum));
	}
	else
	{
	    form.setNewRefNum("1010");
	}

	form.setNewRefDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	//Get the Intake decisions	    
	form.setIntakeDecisions(JuvenileCaseHelper.getReferralDecisions());
	Collections.sort((List) form.getIntakeDecisions(), JuvenileReferralDispositionCode.CodeComparator);
	form.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
	form.setRefSources(JuvenileReferralHelper.getAllReferralSources());

	form.setNewRefDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setNewRefIntakeDecision("PIP");
	form.setNewRefIntakeDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setFormalReferralType("PA");
	form.setAssignmentType("ADM");
	form.setSupervisionCat("PP");
	form.setSupervisionType("ISS");
	
	List categories = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_CATEGORY");
	Iterator<JuvenileCodeTableChildCodesResponseEvent> catIter = categories.iterator();
	ArrayList tempCategories = new ArrayList();
	while (catIter.hasNext())
	{
	    JuvenileCodeTableChildCodesResponseEvent catResp = catIter.next();
	    if("PP".equalsIgnoreCase( catResp.getCode() )){
		
		catResp.setDescriptionWithCode(catResp.getCode() + "-" + catResp.getDescription());
		tempCategories.add(catResp);
		 break;
	    }
	}
	
	form.setSupervisionCategories(tempCategories);
	List types = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_TYPE");
	Iterator<JuvenileCodeTableChildCodesResponseEvent> typesIter = types.iterator();
	ArrayList tempTypes = new ArrayList();
	while (typesIter.hasNext())
	{
	    JuvenileCodeTableChildCodesResponseEvent supTypeResp = typesIter.next();
	    if (supTypeResp.getParentId() != null && supTypeResp.getParentId().equals(form.getSupervisionCat()))
	    {
		supTypeResp.setDescriptionWithCode(supTypeResp.getCode() + "-" + supTypeResp.getDescription());
		tempTypes.add(supTypeResp);
		form.setSupervisionType(supTypeResp.getCode());
		break;
	    }
	}
	Collections.sort(tempTypes);
	form.setSupervisionTypes(tempTypes);
	
	List<JuvenileCasefileOffenseCodeResponseEvent> offenseCodes = new ArrayList<JuvenileCasefileOffenseCodeResponseEvent>();
	GetAllOffenseCodesEvent requestEvent = (GetAllOffenseCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLOFFENSECODES);
	List codes = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);
	
	Iterator codeIter = codes.iterator();
	while( codeIter.hasNext()){
	    
	    JuvenileCasefileOffenseCodeResponseEvent codeResp = (JuvenileCasefileOffenseCodeResponseEvent) codeIter.next();
	    if("99".equals(codeResp.getSeverityType())){
		
		offenseCodes.add(codeResp);
	    }
	    
	}

	Collections.sort(offenseCodes);
	form.setOffenseCodes(offenseCodes);
	
	form.setJpo("UVANC");
	form.setOffenseDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setOffenseCode("RESEAL");
	form.setNewRefSource("72");

	List<JuvenileProfileReferralListResponseEvent> addReferralsList = new ArrayList<JuvenileProfileReferralListResponseEvent>();

	GetJJSReferralDetailsEvent request = (GetJJSReferralDetailsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSREFERRALDETAILS);
	request.setJuvenileNum(form.getJuvenileNum());

	List<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt = MessageUtil.postRequestListFilter(request, JuvenileProfileReferralListResponseEvent.class);

	//sorts in descending order by Referral Num. 
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) juvProfRefListEvt, new Comparator<JuvenileProfileReferralListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
	    {
		if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
		    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
		else
		    return -1;
	    }
	});

	if (juvProfRefListEvt.size() > 0)
	{

	    JuvenileProfileReferralListResponseEvent topResp = juvProfRefListEvt.get(0);
	    form.setMaxCrimReferral(topResp.getReferralNumber());
	}

	for (int x = 0; x < juvProfRefListEvt.size(); x++)
	{

	    JuvenileProfileReferralListResponseEvent resp = juvProfRefListEvt.get(x);
	    if ( Integer.valueOf(resp.getReferralNumber()) % 10 == 0 && !"AC".equalsIgnoreCase(resp.getOffenseCategory()))
	    {
		addReferralsList.add(resp);

	    }else 
		if("AC".equalsIgnoreCase( resp.getOffenseCategory()) && StringUtils.isEmpty(resp.getInactiveInd()) 
						&& ("T".equalsIgnoreCase(resp.getSeveritySubtype()) || "P".equalsIgnoreCase(resp.getSeveritySubtype()))){
		    addReferralsList.add(resp);
		}
	}
	    form.setOriginalChargeReferrals(addReferralsList);
	    form.setAdminReferralFlag("Y");

	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The associated Criminal Charge Referral must be selected below to create the Admin Referral"));
	    saveErrors(aRequest, errors);

	return forward;
    }
    
    /**
     * load Referral
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    private void loadReferral(JuvenileReferralForm form)
    {

	Iterator iter = form.getReferralList().iterator();
	form.setOffenseList(null);
	while (iter.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent) iter.next();
	    if (resp != null && resp.getReferralNumber().equals(form.getNewRefNum()))
	    {
		form.setNewRefDate(DateUtil.dateToString(resp.getReferralDate(), DateUtil.DATE_FMT_1));
		form.setNewRefSource(resp.getReferralSource());
		form.setNewRefIntakeDecision(resp.getIntakeDecisionId());
		form.setNewRefIntakeDate(resp.getIntakeDecDate());
		form.setTjjdDateStr(resp.getTJJDDate());
		form.setProbationStartDate(DateUtil.dateToString(resp.getProbationStartDate(), DateUtil.DATE_FMT_1));
		form.setProbationEndDate(DateUtil.dateToString(resp.getProbationEndDate(), DateUtil.DATE_FMT_1));
		form.setLcUser(resp.getLcUser());
		form.setInvestigationNum("");
		form.setKeyMapLocation("");
		if (form.getJotInfo() != null)
		{
		    form.getJotInfo().setAdultCoActors(new ArrayList());
		    form.getJotInfo().setJuvenileCoActors("");
		}
		IName name = SecurityUIHelper.getUserName(resp.getLcUser());
		form.setLcUserName(name.getFormattedName());
		form.setUpdateRefStatFlag("loadRef");
		JuvenileReferralOffenseBean offense = new JuvenileReferralOffenseBean();
		Collection sortedOffenses = resp.getOffenses();
		Collections.sort((List<JJSOffense>) sortedOffenses, new Comparator<JJSOffense>() {
		    @Override
		    public int compare(JJSOffense evt1, JJSOffense evt2)
		    {
			return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
		    }
		});

		Iterator offenseIter = resp.getOffenses().iterator();
		ArrayList tempOffenses = new ArrayList();
		Date mostRecentOffenseDate = new Date();
		if (offenseIter.hasNext())
		{
		    JJSOffense off = (JJSOffense) offenseIter.next();
		    offense.setOffenseID(off.getOID());
		    offense.setOffenseCode(off.getOffenseCodeId());
		    offense.setOffenseDescription(off.getOffenseDescription());
		    offense.setInvestigationNum(off.getInvestigationNumber());
		    offense.setKeyMapLocation(off.getKeyMapLocation());
		    offense.setOffenseDate(off.getOffenseDate());
		    offense.setSequenceNum(off.getSequenceNum());
		    offense.setOldOffenseCode(off.getOffenseCodeId());
		    if (off.getOffenseDate() != null && off.getOffenseDate().before(mostRecentOffenseDate))
			mostRecentOffenseDate = off.getOffenseDate();
		    tempOffenses.add(offense);
		    offense = new JuvenileReferralOffenseBean();
		}
		form.setEarliestOffenseDate(DateUtil.dateToString(mostRecentOffenseDate, DateUtil.DATE_FMT_1));
		form.setOffenseList(tempOffenses);
		//get the next seq num
		// sorts in descending order by seq num.
		Collection offenses = resp.getOffenses();
		Collections.sort((List<JuvenileReferralOffenseBean>) tempOffenses, new Comparator<JuvenileReferralOffenseBean>() {
		    @Override
		    public int compare(JuvenileReferralOffenseBean evt1, JuvenileReferralOffenseBean evt2)
		    {
			return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
		    }
		});
		offenseIter = tempOffenses.iterator();
		if (offenseIter.hasNext())
		{
		    JuvenileReferralOffenseBean tempOffense = (JuvenileReferralOffenseBean) offenseIter.next();
		    form.setNextOffenseSeqNum(Integer.parseInt(tempOffense.getSequenceNum()) + 1 + "");

		}
		form.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
		form.setUpdateRefStat(resp.getReferralStatus());
		form.setUpdateRefCloseDt(DateUtil.dateToString(resp.getCloseDate(), DateUtil.DATE_FMT_1));
		form.setCurrentCasefileId(resp.getCasefileId());
		//get the assignment details  
		if (resp.getAssignmentDate() == null)
		{
		    form.setAssignmentType("REC");
		    form.setSupervisionCat("RC");
		    form.setSupervisionType("RCV");
		    form.setJpo("UVREC");
		    form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
		    List categories = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_CATEGORY");
		    Iterator<JuvenileCodeTableChildCodesResponseEvent> catIter = categories.iterator();
		    ArrayList tempCategories = new ArrayList();
		    while (catIter.hasNext())
		    {
			JuvenileCodeTableChildCodesResponseEvent catResp = catIter.next();
			catResp.setDescriptionWithCode(catResp.getCode() + "-" + catResp.getDescription());
			if (catResp.getParentId() != null && catResp.getParentId().equals(form.getAssignmentType()))
			{
			    tempCategories.add(catResp);
			}
		    }
		    form.setSupervisionCategories(tempCategories);
		    List types = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_TYPE");
		    Iterator<JuvenileCodeTableChildCodesResponseEvent> typesIter = types.iterator();
		    ArrayList tempTypes = new ArrayList();
		    while (typesIter.hasNext())
		    {
			JuvenileCodeTableChildCodesResponseEvent supTypeResp = typesIter.next();
			if (supTypeResp.getParentId() != null && supTypeResp.getParentId().equals(form.getSupervisionCat()))
			{
			    supTypeResp.setDescriptionWithCode(supTypeResp.getCode() + "-" + supTypeResp.getDescription());
			    tempTypes.add(supTypeResp);
			    form.setSupervisionType(supTypeResp.getCode());
			    break;
			}
		    }
		    Collections.sort(tempTypes);
		    form.setSupervisionTypes(tempTypes);
		}
		else
		{
		    form.setSupervisionType(resp.getSupervisionTypeId());
		    form.setSupervisionCat(resp.getSupervisionCategoryId());
		    form.setAssignmentDateStr(resp.getAssignmentDate());
		    form.setJpo(resp.getJpoId());
		    form.setAssignmentType(resp.getAssignmentType());
		    //Supervision Category
		    CodeResponseEvent supervisionResp = new CodeResponseEvent();
		    supervisionResp.setCode(resp.getSupervisionCategoryId());
		    supervisionResp.setDescription(resp.getSupervisionCategory());
		    supervisionResp.setDescriptionWithCode(resp.getSupervisionCategoryId() + "-" + resp.getSupervisionCategory());
		    ArrayList temp = new ArrayList();
		    temp.add(supervisionResp);
		    form.setSupervisionCategories(temp);

		    supervisionResp = new CodeResponseEvent();
		    supervisionResp.setCode(resp.getSupervisionTypeId());
		    supervisionResp.setDescription(resp.getSupervisionType());
		    supervisionResp.setDescriptionWithCode(resp.getSupervisionTypeId() + "-" + resp.getSupervisionType());
		    temp = new ArrayList();
		    temp.add(supervisionResp);
		    form.setSupervisionTypes(temp);
		}
		if (resp.getDaLogNum() != null)
		{
		    //get the corespondents
		    form.setJotInfo(getCoActorsandCorespondents(resp.getDaLogNum()));
		}

	    }

	}

	return;
    }

    private JOTTO getCoActorsandCorespondents(String daLogNum)
    {
	JOTTO jotto = new JOTTO();
	JuvenileOffenderTracking jot = JuvenileOffenderTracking.find(daLogNum);
	if (jot != null)
	    jotto.setJuvenileCoActors(jot.getCoDefendants());
	List adultCoActors = new ArrayList();
	Iterator<JuvenileOffenderTrackingCoActor> coActors = JuvenileOffenderTrackingCoActor.findAllByDaLogNum(daLogNum);
	while (coActors.hasNext())
	{
	    JuvenileOffenderTrackingCoActor actor = (JuvenileOffenderTrackingCoActor) coActors.next();
	    adultCoActors.add(actor);
	}

	jotto.setAdultCoActors(adultCoActors);
	return jotto;

    }

    private static int getAgeInYears(Date ageDate)
    {
	if (ageDate == null)
	{
	    return 0;
	}
	Calendar birthdate = Calendar.getInstance();
	birthdate.setTime(ageDate);
	Calendar now = Calendar.getInstance();

	int age = 0;
	age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
	birthdate.add(Calendar.YEAR, age);
	if (now.before(birthdate))
	{
	    age--;
	}
	return age;
    }

}
