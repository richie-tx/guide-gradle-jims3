package ui.juvenilecase.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.juvenile.GetSchoolNameSearchEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.juvenile.JuvenileHelper;
import pd.km.util.Name;
import ui.common.Address;
import ui.common.AddressHelper;
import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.juvenilecase.referral.form.JuvenileReferralForm;
import ui.security.SecurityUIHelper;

public class DisplayCreateJuvenileAction extends LookupDispatchAction
{

    protected Map getKeyMethodMap()
    {
	Map keyMap = new HashMap();
	keyMap.put("button.createJuvenile", "createJuvenile");
	keyMap.put("button.addToList", "addAddress");
	keyMap.put("button.remove", "removeAddress");
	keyMap.put("button.next", "next");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.go", "changeSchools");
	keyMap.put("button.search", "searchBySchoolName");
	keyMap.put("button.find", "findSchool");
	keyMap.put("button.select", "addSelectedSchoolInCreate");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.refreshCreate", "refreshCreate");
	return keyMap;
    }
    
    /**
     * Create Juvenile.
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward createJuvenile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.clear();
	form.setAction("create");
	
	HttpSession session = aRequest.getSession();
	session.setAttribute("richBean", new JuvenileProfileDetailResponseEvent());
	
	//populateCodeTables()
	form.setRaces(CodeHelper.getRaces());
	form.setSexes(CodeHelper.getJJSSexCodes());
	form.setStreetNumSuffixList(CodeHelper.getCodes(PDCodeTableConstants.STREET_SUFFIX,true));
	form.setStreetTypeList(CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE,true));
	form.setStateList(CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR,true));
	form.setAddressTypeList(CodeHelper.getCodes(PDCodeTableConstants.ADDRESS_TYPE,true));
	form.setCountyList(CodeHelper.getCodes(PDCodeTableConstants.COUNTY,true));
	form.getMemberBean().setRelationships(CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE_REFERRAL,true));
	//form.setSchoolDistricts();
	//this.setSchoolCollections(UIJuvenileHelper.fetchSchoolDistricts(), form);
	form.setSchoolCollections(UIJuvenileHelper.fetchSchoolDistricts(), form);
	//form.setExitTypes(CodeHelper.getExitTypes()); //Bug 76349
	//form.setAttendanceStatus(CodeHelper.getSchoolAttendanceStatusCodes());//ONLY active Attendance status
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
	form.setAttendanceStatus(activeSchoolAttendanceStatus);
	form.setProgramAttending(CodeHelper.getProgramAttendingCodes());
	form.setGradeLevels(CodeHelper.getGradeLevelsCodes());
	
	//populate dateOutTo current Date
	//form.setDateOut(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1)); //bug no 89530
	//form.setCheckedOutTo(SecurityUIHelper.getLogonId()); Bug #75083
	form.setLastActionBy(SecurityUIHelper.getLogonId());
	form.setLastUpdate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setOperator(SecurityUIHelper.getLogonId());
	form.setCheckedOutTo(""); //Bug #87965
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * Next
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
        JuvenileReferralForm form = (JuvenileReferralForm) aForm;
        form.setAction("next");
        if (form.getDateOfBirth() != null)
	{ // Get age based on year
            boolean isJuvenile= false;
	    int age = UIUtil.getAgeInYears(DateUtil.stringToDate(form.getDateOfBirth(),DateUtil.DATE_FMT_1));
	    if (age > 0)
	    {
		if (age >= 10 && age <= 19)
		{
		    isJuvenile=true;
		}
	    }
	    if(!isJuvenile){
		 form.setAction("next");
		// form.setCursorPosition("dateOfBirth");
		 ActionErrors errors = new ActionErrors();
		 errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile must be between 10 to 19 years of age. Please contact Data Corrections for support"));
		 saveErrors(aRequest, errors);
		 return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
        Name fullName = new Name(form.getFirstName(), form.getMiddleName(), form.getLastName(),form.getNameSuffix());
	StringBuffer formattedName = new StringBuffer(fullName.getFormattedName());
	form.setFormattedName(formattedName.toString());
        
	form.setSex(CodeHelper.getCodeDescription(PDCodeTableConstants.JJS_SEX, form.getSexId()));
	form.setRace(CodeHelper.getCodeDescription(PDCodeTableConstants.RACE, form.getOriginalRaceId()));
	if(form.isHispanic()){
	    form.setHispanicStr("Y");
	    form.setHispanicDesc("Yes");
	}else{
	    form.setHispanicStr("N");
	    form.setHispanicDesc("No");
	}
	
	//checkoutTODesc
	if(form.getCheckedOutTo() != null && !form.getCheckedOutTo().equals(""))
	{
        	if(form.getCheckedOutTo().trim().length()<4){
        		IName name =SecurityUIHelper.getUserName("UV"+form.getCheckedOutTo());
        		if(name!=null){
        			form.setCheckedOutToDesc(name.getFormattedName());
        		}
        	}else{
        	    IName name =SecurityUIHelper.getUserName(form.getCheckedOutTo());
        		if(name!=null){
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
	
	form.setJuvFormattedAddress(JuvenileHelper.formatAddress(form.getJuvAddress()));
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
	//school information
	Iterator schoonNamesItr = form.getSchools().iterator();
	while (schoonNamesItr.hasNext()){
	    JuvenileSchoolDistrictCodeResponseEvent schoolwithDistResp = (JuvenileSchoolDistrictCodeResponseEvent)schoonNamesItr.next();
	    if(schoolwithDistResp.getSchoolCode().equalsIgnoreCase(form.getSchoolId())){
		form.setSchoolName(schoolwithDistResp.getSchoolDescription());
		form.setSchoolDistrictDescription(schoolwithDistResp.getDistrictDescription());
		form.setTeacode(schoolwithDistResp.getTeaCode());
		break;
	    }
	}
	Iterator gradesItr = form.getGradeLevels().iterator();
	while(gradesItr.hasNext()){
	   CodeResponseEvent  gradeLevelsRespEvt  = (CodeResponseEvent)gradesItr.next();
	    if(gradeLevelsRespEvt.getCode().equalsIgnoreCase(form.getGradeLevelId()))
	    {
		form.setGradeLevelDescription(gradeLevelsRespEvt.getDescription());
		break;
	    }
	}
	Iterator pgmItr = form.getProgramAttending().iterator();
	while(pgmItr.hasNext()){
	    CodeResponseEvent pgmAttdngRespEvt = (CodeResponseEvent)pgmItr.next();
	    if(pgmAttdngRespEvt.getCode().equalsIgnoreCase(form.getProgramAttendingId())){
		form.setProgramAttendingDescription(pgmAttdngRespEvt.getDescription());
		break;
	    }
	} 
	Iterator schlAttStatusItr = form.getAttendanceStatus().iterator();
	while(schlAttStatusItr.hasNext()){
	    CodeResponseEvent schoolAttStatus = (CodeResponseEvent)schlAttStatusItr.next();
	    if(schoolAttStatus.getCode().equalsIgnoreCase(form.getAttendanceStatusId())){
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
	*/ //above commented for BUG 76348
	//member Bean Details 
	form.getMemberBean().setFormattedAddress(JuvenileHelper.formatAddress(form.getMemberBean().getMemberAddress()));
	form.getMemberBean().setFormattedPhone(JuvenileHelper.formatPhone(form.getMemberBean().getContactPhoneNumber()));
	
        ActionForward forward = aMapping.findForward(UIConstants.NEXT);
        return forward;
    }
    

    /**
     * Add address
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addAddress(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("addToList");
	
	
	
	JuvenileReferralMemberDetailsBean memberBean = new JuvenileReferralMemberDetailsBean();
	try
	{
	    BeanUtils.copyProperties(memberBean, form.getMemberBean());
	}
	catch (IllegalAccessException e)
	{
	    e.printStackTrace();
	}
	catch (InvocationTargetException e)
	{
	    e.printStackTrace();
	}
	
	// bug #84934
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
		}
	    }
	}
	//84934
	
	Name fullName = new Name(memberBean.getFirstName(), memberBean.getMiddleName(), memberBean.getLastName());
	StringBuffer formattedName = new StringBuffer(fullName.getFormattedName());
	memberBean.setFormattedName(formattedName.toString());
	
	memberBean.setFormattedAddress(JuvenileHelper.formatAddress(form.getMemberBean().getMemberAddress()));
	memberBean.setRelationshipDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.RELATIONSHIP_JUVENILE_REFERRAL, memberBean.getRelationshipId()));
	memberBean.setFormattedPhone(JuvenileHelper.formatPhone(form.getMemberBean().getContactPhoneNumber()));
	memberBean.setFormattedSSN(memberBean.getSSN().getFormattedSSN());
	
	
	if(memberBean.getIncarceratedOrDeceased()!=null && !memberBean.getIncarceratedOrDeceased().isEmpty()){
	    if(memberBean.getIncarceratedOrDeceased().equalsIgnoreCase("I")){
		memberBean.setIncarceratedOrDeceasedDesc("Incarcerated");
		memberBean.setIncarcerated(true);
	    }else{
		memberBean.setIncarceratedOrDeceasedDesc("Deceased");
		memberBean.setDeceased(true);
	    }
	}
	
	if(memberBean.getPhoneInd()!=null && memberBean.getPhoneInd().equals("P")){
	    memberBean.setPhoneIndDesc("Primary");
	}else{
	    memberBean.setPhoneIndDesc("Unknown");
	}
	
	if(memberBean.getPhoneType()!=null && memberBean.getPhoneType().equals("HM")){
	    memberBean.setPhoneTypeDesc("Home");
	}else{
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
	if(form.getMemberDetailsBeanList()!=null && !form.getMemberDetailsBeanList().isEmpty()){
	    if(form.getMemberDetailsBeanList().size()>=3){
		 ActionErrors errors = new ActionErrors();
		 errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A maximum of three parent/guardian entries are allowed. Please modify entry"));
		 saveErrors(aRequest, errors);
		 return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	if (memberBean != null)
	{
	    if (form.getMemberDetailsBeanList() == null)
	    {
		form.setMemberDetailsBeanList(new ArrayList<JuvenileReferralMemberDetailsBean>());
	    }
	    if(form.getMemberDetailsBeanList()!=null && form.getMemberDetailsBeanList().isEmpty()){ //added for US 157561 STARTS
		memberBean.setPrimaryContact("true");
	    } //added for US 157561 ENDS
	    form.getMemberDetailsBeanList().add(memberBean);
	 }
	form.getMemberBean().clear();
	return (aMapping.findForward(UIConstants.ADD_SUCCESS));
    }
    
    /**
     * removeAddress
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward removeAddress(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm myForm = (JuvenileReferralForm) aForm;
	
	String myAddressPos = myForm.getSelectedValue();
	if (notNullNotEmptyString(myAddressPos))
	{
	    if (myForm.getMemberDetailsBeanList() != null && myForm.getMemberDetailsBeanList().size() > 0 && myForm.getMemberDetailsBeanList().size() <4)
	    {
		if(!(myForm.getMemberDetailsBeanList().size() <= Integer.parseInt(myAddressPos))){
		    ((List<JuvenileReferralMemberDetailsBean>) myForm.getMemberDetailsBeanList()).remove(Integer.parseInt(myAddressPos));
		}
	    }
	    myForm.getMemberBean().clear();
	}
	
	return (aMapping.findForward(UIConstants.ADD_SUCCESS));
    }
  
    
    /**
     * set Formatted Address
     * @param memberBean
     */
    private void setFormattedAddress(JuvenileReferralMemberDetailsBean memberBean)
    {
	StringBuffer buf= new StringBuffer();
	buf.append(memberBean.getMemberAddress().getStreetNumber());
	if(memberBean.getMemberAddress().getStreetNumSuffix()!=null){
	    buf.append(memberBean.getMemberAddress().getStreetNumSuffix());
	}
	buf.append(memberBean.getMemberAddress().getStreetName());   
	if(memberBean.getMemberAddress().getStreetType()!=null && memberBean.getMemberAddress().getStreetType()!=""){
	    buf.append(memberBean.getMemberAddress().getStreetType()); 
	    buf.append(",  "); 
	}
	if(memberBean.getMemberAddress().getAptNumber()!=null && memberBean.getMemberAddress().getAptNumber()!=""){
	    buf.append(memberBean.getMemberAddress().getAptNumber());  
	    buf.append(",  ");  
	}
	if(memberBean.getMemberAddress().getCity()!=null && memberBean.getMemberAddress().getCity()!=""){
	    buf.append(memberBean.getMemberAddress().getCity());  
	    buf.append(", ");  
	}
	if(memberBean.getMemberAddress().getState()!=null && memberBean.getMemberAddress().getState()!=""){
	    buf.append(memberBean.getMemberAddress().getState());  
	    buf.append(", ");  
	}
	if(memberBean.getMemberAddress().getZipCode()!=null && memberBean.getMemberAddress().getZipCode()!=""){
	    buf.append(memberBean.getMemberAddress().getZipCode());  
	    buf.append(", ");
	    if(memberBean.getMemberAddress().getAdditionalZipCode()!=null && memberBean.getMemberAddress().getAdditionalZipCode()!=""){
		    buf.append(memberBean.getMemberAddress().getAdditionalZipCode());  
		}
	}
	
	memberBean.setFormattedAddress(buf.toString());
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
    
    public ActionForward refreshCreate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setMemberBean(new JuvenileReferralMemberDetailsBean());
	form.getMemberBean().setRelationships(CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE_REFERRAL,true));
	return aMapping.findForward(UIConstants.SUCCESS);
	
    }
    
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward searchBySchoolName(ActionMapping aMapping,
		ActionForm aForm, HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	JuvenileReferralForm form = 	(JuvenileReferralForm)aForm;
	form.setSchoolName("");
	form.setSchoolName("");
	form.setSchoolDescription("");
	form.setSchoolNDistrictList(null);
	return( aMapping.findForward("searchschool") ) ;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward changeSchools(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	JuvenileReferralForm form = 	(JuvenileReferralForm)aForm;
	form.setSchoolId(null);
	if( form.getSchoolDistrictId().equals("") )
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Please select a school district from the drop down menu and click GO button"));
		saveErrors(aRequest, errors);
	}
	if(form.getAction()!=null && form.getAction().equalsIgnoreCase("updateJuvenile")){
	    return(aMapping.findForward("displaySchoolInUpdate"));
	}else {
	    return(aMapping.findForward("displaySchool")) ;
	}
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward findSchool(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
{
	ActionForward forward = aMapping.findForward("schoolDistricts");
	JuvenileReferralForm form = 	(JuvenileReferralForm)aForm;
	GetSchoolNameSearchEvent searchEvent = new GetSchoolNameSearchEvent();
	searchEvent.setSchoolDescription(form.getSchoolName());
	if (form.getSchoolName().equals("")){
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Please enter a school name to search"));
	    saveErrors(aRequest, errors);
	    return forward;
	}
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(searchEvent);
	CompositeResponse response = (CompositeResponse)dispatch.getReply();
	Collection schoolNameswithDistList = MessageUtil.compositeToCollection(response, JuvenileSchoolDistrictCodeResponseEvent.class);
	
	form.setSchoolNDistrictList(schoolNameswithDistList);
	if( form.getSchoolNDistrictList().size() == 0 )
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
	 * @return
	 */
	public ActionForward addSelectedSchoolInCreate(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
{
	ActionForward forward = aMapping.findForward("displaySchoolInCreate");

	JuvenileReferralForm form = (JuvenileReferralForm)aForm;
	setShowAddSchoolsForm(form);

	if( StringUtils.isNotEmpty(form.getSelectedSchoolId()) )
	{
		String schoolId = form.getSelectedSchoolId();
		Collection<JuvenileSchoolDistrictCodeResponseEvent> coll = form.getSchoolNDistrictList();
		
		if( notNullNotEmptyCollection(coll) )
		{
			for( JuvenileSchoolDistrictCodeResponseEvent newScDt : coll )
			{
				if( schoolId.equalsIgnoreCase(newScDt.getOid()) )
				{
						form.setSchoolId(newScDt.getSchoolCode());
						form.setSchoolDistrictId(newScDt.getDistrictCode());
						form.setSchoolDescription(newScDt.getSchoolDescription());
						form.setSchoolName(newScDt.getSchoolDisplayLiteral());
						form.setTeacode(newScDt.getTeaCode());
						
					break;
				}
			}
		}
	}
	if(form.getAction()!=null && form.getAction().equalsIgnoreCase("updateJuvenile")){
	    forward = aMapping.findForward("displaySchoolInUpdate");
	}
	return forward;
}
	
	/** setShowAddSchoolsForm
	 * @param form
	 */
	private void setShowAddSchoolsForm(JuvenileReferralForm form)
	{
		if( form.getGradeLevels().isEmpty() )
		{
			Collection gradeLevels = CodeHelper.getGradeLevelsCodes();
			form.setGradeLevels(gradeLevels);
		}

		/*if( form.getExitTypes().isEmpty() )
		{
			Collection exitTypes = CodeHelper.getExitTypes();
			form.setExitTypes(exitTypes);
		}*/

		if( form.getSchoolDistricts().isEmpty() )
		{
			Collection schoolDistricts = UIJuvenileHelper.fetchSchoolDistricts();
			//form.setSchoolDistrictList(schoolDistricts);
			form.setSchoolCollections(schoolDistricts, form); 
		}

		if( form.getProgramAttending().isEmpty() )
		{
			Collection programAttending = CodeHelper.getProgramAttendingCodes();
			form.setProgramAttending(programAttending);
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
     * notNullNotEmptyString
     * @param str
     * @return boolean
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.length() > 0));
    }
}
