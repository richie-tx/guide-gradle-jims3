package pd.juvenile.transactions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import messaging.error.reply.ErrorResponseEvent;
import messaging.family.SaveFamilyMemberContactEvent;
import messaging.family.SaveFamilyMemberFinancialEvent;
import messaging.juvenile.SaveJuvenileProfileMainEvent;
import messaging.juvenile.SaveJuvenileSchoolHistoryEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;
import pd.address.Address;
import pd.codetable.person.ScarsMarksTattoosCode;
import pd.contact.Phone;
import pd.contact.officer.OfficerProfile;
import pd.exception.InvalidProbationOfficerException;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileAlias;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.security.PDSecurityHelper;
import ui.common.PhoneNumber;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.security.SecurityUIHelper;

/**
 * Class SaveJuvenileProfileMainCommand.
 * 
 * @author Anand Thorat
 */
public class SaveJuvenileProfileMainCommand /*extends CasefileExtractionUtility // no longer in use. Migrated to SM. Refer US #87188.*/ implements mojo.km.context.ICommand
{

    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42A9C0F90109
     */
    public SaveJuvenileProfileMainCommand()
    {

    } //end of pd.juvenile.transactions.SaveJuvenileProfileMainCommand.SaveJuvenileProfileMainCommand

    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     * @param event
     * @roseuid 42A9B4880263
     */
    public void execute(IEvent event)
    {
	SaveJuvenileProfileMainEvent request = (SaveJuvenileProfileMainEvent) event;
	
	JuvenileCore juv = JuvenileCore.findCore(request.getJuvenileNum());
	Timestamp createDate =  new Timestamp(DateUtil.getCurrentDate().getTime());
	IUserInfo user = SecurityUIHelper.getUser( ) ;
	
	if (juv != null)
	{
	    IHome home = new Home();
	    if ( request.getDateOfBirth() != null ){
		juv.setDateOfBirth(request.getDateOfBirth());
	    }
	    
	    juv.setRealDOB(request.getRealDOB());
	    
	    if( request.getFirstName() != null 
		    && request.getFirstName().length() > 0 ) {
		juv.setFirstName(request.getFirstName());
	    }
	    if( request.getJuvenileNum() != null
		    && request.getJuvenileNum().length() > 0 ){
		juv.setJuvenileNum(request.getJuvenileNum());
	    }
	    if ( request.getLastName() != null
		    && request.getLastName().length() > 0){
		juv.setLastName(request.getLastName());
	    }
	    if ( request.getMiddleName() != null
		    && request.getMiddleName().length() > 0 ) {
		juv.setMiddleName(request.getMiddleName());
	    }
	    if ( request.getNameSuffix() != null
		    && request.getNameSuffix().length() > 0){
		juv.setNameSuffix(request.getNameSuffix());
	    }
        	juv.setPreferredFirstName(request.getPreferredFirstName());

	    if ( request.getSexId() != null
		    && request.getSexId().length() > 0){
		 juv.setSexId(request.getSexId());
	    }
	    if ( request.getComments() != null
		    && request.getComments().length() >0){
		juv.setComments(request.getComments());//US 71171
	    }
	    
	    if(request.getSSN()!=null && !request.getSSN().equals("")){//Bug 87978
		juv.setSSN(request.getSSN());//US 71171
	    	juv.setLcuser(request.getLcuser()); //US 71171
	    	juv.setSSNUpdateUser(user.getJIMSLogonId());
		juv.setSSNUpdateDate( DateUtil.getCurrentDate() );
	    }
	    
	    //Bug# 172537
	    if(request.getJuvAddress() != null && request.getJuvAddress().getZipCode() != null){
		juv.setZip(request.getJuvAddress().getZipCode());
	    }
	    
	    Juvenile juvenile = Juvenile.findJCJuvenile(request.getJuvenileNum());
	    
	    if (juvenile != null && (request.getAction() == null || !request.getAction().equalsIgnoreCase("updateJuvenileFinish")) )
	    {
		if ( request.getPassportNum() != null
			&& request.getPassportNum().length() > 0 ){
		    juvenile.setPassportNum(request.getPassportNum());
		} else {
		    juvenile.setPassportNum(null);
		}
		
		if ( request.getCountryOfIssuanceId() != null
			&& request.getCountryOfIssuanceId().length() > 0 ) {
		    juvenile.setCountryOfIssuanceId( request.getCountryOfIssuanceId() );
		} else {
		    juvenile.setCountryOfIssuanceId( null );
		}
		
		if ( request.getPassportExpirationDate() != null ){
		    juvenile.setPassportExpirationDate(request.getPassportExpirationDate());
		} else {
		    juvenile.setPassportExpirationDate( null );
		}
		
		juvenile.setPassportExpirationDate(request.getPassportExpirationDate());
		juvenile.setAlienNumber(request.getAlienNumber());
		juvenile.setBirthCityId(request.getBirthCityId());
		juvenile.setBirthCountryId(request.getBirthCountryId());
		juvenile.setBirthCountyId(request.getBirthCountyId());
		juvenile.setBirthStateId(request.getBirthStateId());
		juvenile.setComplexionId(request.getComplexionId());
		juvenile.setDateSenttoDPS(request.getDateSenttoDPS());
		juvenile.setDHSNumber(request.getDHSNumber());
		juvenile.setDNASampleNumber(request.getDNASampleNumber());
		juvenile.setDriverLicenseNumber(request.getDriverLicenseNumber());
		juvenile.setDriverLicenseStateId(request.getDriverLicenseStateId());
		juvenile.setDriverLicenseClassId(request.getDriverLicenseClassId());
		juvenile.setDriverLicenseExpireDate(request.getDriverLicenseExpireDate());
		juvenile.setEthnicityId(request.getEthnicityId());
		juvenile.setFBINumber(request.getFBINumber());
		if(request.getHispanic()!=null && !request.getHispanic().equals(""))
		    juvenile.setHispanic(request.getHispanic());
		juvenile.setMultiracial(request.getMultiracial());
		juvenile.setNationalityId(request.getNationalityId());
		juvenile.setNatualEyeColorId(request.getNatualEyeColorId());
		juvenile.setNaturalHairColorId(request.getNaturalHairColorId());
		juvenile.setPEIMSId(request.getTSDSId());
		juvenile.setIsUSCitizenId(request.getIsUSCitizenId());
		juvenile.setPrimaryLanguageId(request.getPrimaryLanguageId());
		juvenile.setReligionId(request.getReligionId());
		juvenile.setSecondaryLanguageId(request.getSecondaryLanguageId());
		juvenile.setSIDNumber(request.getSID());
		juvenile.setSONumber(request.getSONumber());
		juvenile.setEducationId(request.getEducationId());
		//ER-76951 changes
		if (request.getStudentId() != null && !request.getStudentId().isEmpty())
		{
		    juvenile.setStudentId(request.getStudentId());
		}
		else
		{
		    juvenile.setStudentId(request.getJuvenileNum());
		}
		juvenile.setVerifiedDOB(request.getVerifiedDOB());
		juvenile.setAdopted(request.isAdopted());
		juvenile.setFailedPlacements(request.getFailedPlacements());
		juvenile.setAdoptionComments(request.getAdoptionComments());
		
		//			    this.updateScarsMarks(request.getScarsAndMarks(), juvenile);
		if ( juvenile.getHispanic() != null
			&&  juvenile.getHispanic().length() > 0 
			&& juvenile.getIsUSCitizenId() != null
			&& juvenile.getIsUSCitizenId().length() > 0 ) {
		    Iterator<JuvenileCasefile>casefileIter = JuvenileCasefile.findAll("juvenileId", juvenile.getJuvenileNum());
		    while ( casefileIter.hasNext() ){
			JuvenileCasefile casefile = (JuvenileCasefile)casefileIter.next();
			casefile.setHispanic(false);
			home.bind(casefile);
		    }
		}
		
	    }
	    else if("Y".equalsIgnoreCase(request.getJuvFromM204Flag())){
		createDate = new Timestamp(DateUtil.getCurrentDate().getTime());
		//create the kid in JCJUVENILE
		Juvenile jcJuvenile = new Juvenile();
		jcJuvenile.setJuvenileNum(request.getJuvenileNum());
		jcJuvenile.setHispanic(request.getHispanic());
		jcJuvenile.setStudentId(request.getJuvenileNum());
		jcJuvenile.setCreateUserID(SecurityUIHelper.getLogonId());
		jcJuvenile.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		jcJuvenile.setCreateTimestamp( createDate);
		new Home().bind(jcJuvenile);
		juvenile = jcJuvenile;
		
	    }
	    //Added for #89144 - hotFix
	    if(juvenile!=null && juvenile.getHispanic()!=null && juvenile.getHispanic().equalsIgnoreCase("Y")){
		juv.setRaceIdForHispanic("L");
	    }else{
		if (request.getRaceId() != null
			&& request.getRaceId().length() > 0 ) {
		    juv.setRaceId(request.getRaceId());
		}
	    }
	    //added for US 90441
	    if (request.getRaceId() != null
		    && request.getRaceId().length() > 0){
		juv.setOriginalRaceId(request.getRaceId());
	    }
	    
	    //Added for #89144 - hotFix
	    if (juvenile != null &&  juvenile.getHispanic()!=null && juvenile.getHispanic().equalsIgnoreCase("Y"))
	    {
		juv.setHispanicInd("Y");
	    }
	    
	  //Added for #89144 - hotFix
	    home.bind(juv); 
	    home.bind(juvenile);
	  //Added for #89144 - hotFix
	    
	    // Save to Alias table if preferred Name entered.
	    if( request.getPreferredFirstName() != null && StringUtils.isNotEmpty( request.getPreferredFirstName() )){
		
		this.savePreferredNameToAlias(juv);
	    }
	   
	    //changes for Update Juvenile (Referral US 71171)starts
	    if(request.getAction() != null && request.getAction().equalsIgnoreCase("updateJuvenileFinish")){
		
		/** 89263 started**/
		juv.setCheckedOutDate(DateUtil.stringToDate(request.getCheckedOutDate(), DateUtil.DATE_FMT_1));//Bug #87695
		juv.setCheckOutTo(request.getCheckedOutTo());
		juv.setLastActionBy(request.getLastActionBy());
		//home.bind(juv); //89530
		/** 89263 ended **/
		    //juv.setSSN(request.getSSN());	US 39892	
		//update Juvenile address
		if(request.getJuvAddress()!=null && ! request.getJuvAddress().getAddressId().equalsIgnoreCase(""))
		{  //already existing address
		    Address address = Address.find(Integer.parseInt(request.getJuvAddress().getAddressId()));
		    address.setStreetNum(request.getJuvAddress().getStreetNum());
		    address.setStreetNumSuffixId(request.getJuvAddress().getStreetNumSuffixId());
		    address.setStreetName(request.getJuvAddress().getStreetName());
		    address.setStreetTypeId(request.getJuvAddress().getStreetTypeId());
		    address.setAptNum(request.getJuvAddress().getAptNum());
		    address.setCity(request.getJuvAddress().getCity());
		    address.setStateId(request.getJuvAddress().getStateId());
		    address.setZipCode(request.getJuvAddress().getZipCode());
		    address.setAdditionalZipCode(request.getJuvAddress().getAdditionalZipCode());
		    address.setAddressTypeId(request.getJuvAddress().getAddressTypeId());
		    address.setCountyId(request.getJuvAddress().getCountyId());
		    address.setUpdateUserID(SecurityUIHelper.getLogonId());
		    address.setUpdateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		    address.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		    address.setValidated(request.getJuvAddress().getValidated());
		} 
		else if (request.getJuvAddress().getStreetNum() != null && !request.getJuvAddress().getStreetNum().equalsIgnoreCase(""))
		{   // need to add new address //code copied from create juv
		    Address address = new Address();
		    address.setStreetNum(request.getJuvAddress().getStreetNum());
		    address.setStreetNumSuffixId(request.getJuvAddress().getStreetNumSuffixId());
		    address.setStreetName(request.getJuvAddress().getStreetName());
		    address.setStreetTypeId(request.getJuvAddress().getStreetTypeId());
		    address.setAptNum(request.getJuvAddress().getAptNum());
		    address.setStateId(request.getJuvAddress().getStateId());
		    address.setCity(request.getJuvAddress().getCity());
		    address.setStateId(request.getJuvAddress().getStateId());
		    address.setZipCode(request.getJuvAddress().getZipCode());
		    address.setAdditionalZipCode(request.getJuvAddress().getAdditionalZipCode());
		    address.setAddressTypeId(request.getJuvAddress().getAddressTypeId());
		    address.setCountyId(request.getJuvAddress().getCountyId());
		    new Home().bind(address);
		    juv.setAddressId(Integer.parseInt(address.getOID()));
		}
		//Added for #93461 - hotFix
		new Home().bind(juv);
		//update school info
/*		if( request.isAllowSchoolCreate() ){
		    
			GetJuvenileSchoolEvent schoolevent = new GetJuvenileSchoolEvent();
			schoolevent.setJuvenileNum(request.getJuvenileNum());
			Iterator<JuvenileSchoolHistory> schoolHistItr = JuvenileSchoolHistory.findJuvenileSchoolHistory(schoolevent);
			if (schoolHistItr != null)
			{
			    while (schoolHistItr.hasNext())
			    {
				JuvenileSchoolHistory school = schoolHistItr.next();
				if (school != null)
				{
				    school.setJuvenileNum(request.getJuvenileNum());
				    school.setSchoolId(request.getSchoolId());
				    school.setSchoolDistrictId(request.getSchoolDistId());
				    school.setProgramAttendingId(request.getPgmAttdngId());
				    school.setGradeLevelId(request.getGradeLvlId());
				    school.setSchoolAttendanceStatusId(request.getSchoolAttendanceStatusId());
				    school.setTeaSchoolNumber(request.getSchoolteaCode());
				    break;
				}
			    }
			    //need to add school if there is no record in JCSCJOOL
			    Collection schoolHistories = UIJuvenileHelper.fetchSchoolHistory(request.getJuvenileNum());
			    if (schoolHistories.size() <= 0 && request.getSchoolDistId()!=null && !request.getSchoolDistId().equals(""))
			    {
				JuvenileSchoolHistoryResponseEvent schoolHistoryRespEvt = new JuvenileSchoolHistoryResponseEvent();
				schoolHistoryRespEvt.setJuvenileNum(request.getJuvenileNum());
				schoolHistoryRespEvt.setSchoolId(request.getSchoolId());
				schoolHistoryRespEvt.setSchoolDistrictId(request.getSchoolDistId());
				//schoolHistoryRespEvt.setExitTypeCode(request.getExitTypeId());
				schoolHistoryRespEvt.setProgramAttendingCode(request.getPgmAttdngId());
				schoolHistoryRespEvt.setGradeLevelCode(request.getGradeLvlId());
				schoolHistoryRespEvt.setSchoolAttendanceStatusCode(request.getSchoolAttendanceStatusId());
				schoolHistoryRespEvt.setTEASchoolNumber(request.getSchoolteaCode());

				SaveJuvenileSchoolHistoryEvent saveEvent = (SaveJuvenileSchoolHistoryEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILESCHOOLHISTORY);
				saveEvent.addRequest(schoolHistoryRespEvt);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(saveEvent);
				// Get PD Response Event
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				// Perform Error handling
				MessageUtil.processReturnException(response);
			    }
			}
		}*/

		
		//update legal parents info
		if (request.getMemberDetailsBeanList() != null && !request.getMemberDetailsBeanList().isEmpty())
		{
		    List<JuvenileReferralMemberDetailsBean> juvFamilyList = request.getMemberDetailsBeanList();
		    FamilyConstellation familyConstellation = null;
		    Collection<FamilyConstellationListResponseEvent> constellationList = UIJuvenileHelper.getCurrentActiveConstellation(request.getJuvenileNum());
		    Iterator<JuvenileReferralMemberDetailsBean> familyMemberList = juvFamilyList.iterator();
		    while (familyMemberList.hasNext())
			{
			FamilyMember member = null;
			JuvenileReferralMemberDetailsBean memberBean = (JuvenileReferralMemberDetailsBean) familyMemberList.next();
			//check if new family Member
			if (memberBean.getNewMemflag() != null)
			{
			    if (memberBean.getNewMemflag().equalsIgnoreCase("Y"))
			    {
				//add the new family member to JCFAMMEMBER
				// check if member exists (to mark as suspicious members)
				ArrayList<String> suspMemberIds = new ArrayList<String>();
				if ((memberBean.getFirstName() != null && memberBean.getFirstName().trim().length() > 0) || (memberBean.getMiddleName() != null && memberBean.getMiddleName().trim().length() > 0) || (memberBean.getLastName() != null && memberBean.getLastName().trim().length() > 0))
				{
				    if (memberBean.getSSN() != null && !memberBean.getSSN().getSSN().equals("") && !memberBean.getSSN().getSSN().equals("666666666") && !memberBean.getSSN().getSSN().equals("777777777") && !memberBean.getSSN().equals("888888888") && !memberBean.getSSN().getSSN().equals("999999999"))
				    {
					//check if any other family member has the same ssn
					Iterator<FamilyMember> familyMembersSSN = JuvenileCaseHelper.checkFamilyMemberSSN(memberBean.getSSN().getSSN());
					while (familyMembersSSN.hasNext())
					{
					    FamilyMember memberSSN = (FamilyMember) familyMembersSSN.next();
					    //check if they have the same name
					    if (memberSSN != null && memberSSN.getFirstName() != null && !memberSSN.getFirstName().equalsIgnoreCase(memberBean.getFirstName()) && memberSSN.getLastName() != null && !memberSSN.getLastName().equalsIgnoreCase(memberBean.getLastName()))
					    {
						suspMemberIds.add(memberSSN.getFamilyMemberId());
					    }
					}
				    }
				}
				boolean isGuardianAvailable = false;

				Address address = null;
				PhoneNumber phoneNumber = null;

				if (memberBean.getIncarceratedOrDeceased() == null || !memberBean.getIncarceratedOrDeceased().equalsIgnoreCase("Y"))
				{
				    address = new Address();
				    address.setStreetNum(memberBean.getMemberAddress().getStreetNum());
				    address.setStreetNumSuffixId(memberBean.getMemberAddress().getStreetNumSuffixId());
				    address.setStreetName(memberBean.getMemberAddress().getStreetName());
				    address.setStreetTypeId(memberBean.getMemberAddress().getStreetTypeId());
				    address.setAptNum(memberBean.getMemberAddress().getAptNum());
				    address.setCity(memberBean.getMemberAddress().getCity());
				    address.setStateId(memberBean.getMemberAddress().getStateId());
				    address.setZipCode(memberBean.getMemberAddress().getZipCode());
				    address.setAdditionalZipCode(memberBean.getMemberAddress().getAdditionalZipCode());
				    address.setAddressTypeId(memberBean.getMemberAddress().getAddressTypeId());
				    address.setCountyId(memberBean.getMemberAddress().getCountyId());
				    address.setCreateUserID(SecurityUIHelper.getLogonId());
				    address.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
				    address.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
				    address.setValidated("N");
				    phoneNumber = memberBean.getContactPhoneNumber();
				}
				try
				{
				    member = getFamilyMember(memberBean, address, phoneNumber);
				}
				catch (RuntimeException e)
				{
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
				catch (Exception e)
				{
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
				//suspicious
				if (suspMemberIds != null && !suspMemberIds.isEmpty())
				{
				    Iterator<String> iter = suspMemberIds.iterator();
				    while (iter.hasNext())
				    {
					String memberId = (String) iter.next();
					JuvenileCaseHelper.markMembersSuspicious(memberId, member.getFamilyMemberId());
				    }
				}
				if (!constellationList.isEmpty())
				{
				    // means we need not create a new constellation
				    familyConstellation = FamilyConstellation.find(constellationList.iterator().next().getFamilyNum());
				    familyConstellation.setJuvenileId(request.getJuvenileNum());
				    familyConstellation.setEntryDate(new Date());
				    familyConstellation.setActive(true);
				    familyConstellation.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
				    /*familyConstellation.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);*/
				    familyConstellation.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
				    // Only 1 active constellation at a time, so get the first in the collection
				    //FamilyConstellationListResponseEvent activeConstellation = constellationList.iterator().next();
				}
				if (memberBean.getRelationshipId().equalsIgnoreCase("BM"))
				{
				    if (familyConstellation == null)
				    {
					familyConstellation = createFamilyConstellation(request.getJuvenileNum());
				    }
				    FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "BM", memberBean.getPrimaryContact());
				    familyConstellationMember.setGuardian(true);
				    isGuardianAvailable = true;
				    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);//insert into jcconstelltion happens with null values
				    SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
				    aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
				    aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
				    JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);//insert into jcconsrelation

				}
				// add member to constellation
				if (memberBean.getRelationshipId().equalsIgnoreCase("BF"))
				{
				    if (familyConstellation == null)
					familyConstellation = createFamilyConstellation(request.getJuvenileNum());
				    FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "BF", memberBean.getPrimaryContact());
				    familyConstellationMember.setGuardian(true);
				    isGuardianAvailable = true;
				    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
				    SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
				    aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
				    aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
				    JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
				}
				// add member to constellation
				if (memberBean.getRelationshipId().equalsIgnoreCase("OR"))
				{
				    FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "OR", memberBean.getPrimaryContact());
				    if (isGuardianAvailable == false)
				    {
					if (familyConstellation == null)
					    familyConstellation = createFamilyConstellation(request.getJuvenileNum());
					familyConstellationMember.setGuardian(true);
					familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
					SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
					aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
					aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
					JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
				    }
				}
				if (familyConstellation != null)
				{
				    juvenile.insertFamilyConstellationList(familyConstellation);
				}
				//add the member to the current constellation
			    }
			}
			else
			{
			    if ((memberBean.getGuardianEditedFlag()!=null) && memberBean.getGuardianEditedFlag().equalsIgnoreCase("Y"))
			    {	// get the FAMILY MEMBER and update the existing JCFAMMEMBER
				 FamilyMember pdMember = FamilyMember.find(memberBean.getMemberNum());
				 if (pdMember!=null){
				 pdMember.setFirstName(memberBean.getFirstName());
				 pdMember.setLastName(memberBean.getLastName());
				 pdMember.setMiddleName(memberBean.getMiddleName());
				 pdMember.setSsn(memberBean.getCompleteSSN());
				 if(memberBean.getIncarceratedOrDeceased()!= null){
					if (memberBean.getIncarceratedOrDeceased().equalsIgnoreCase("I"))
					{
					    pdMember.setIncarcerated(true);
					    pdMember.setDeceased(false);
					}
					else
					    if (memberBean.getIncarceratedOrDeceased().equalsIgnoreCase("D"))
					    {
						pdMember.setDeceased(true);
						pdMember.setIncarcerated(false);
					    }else {
						    //Changes for BUG 80520
						    pdMember.setIncarcerated(false);
						    pdMember.setDeceased(false);
						}
					} 

				 	//if incarcerated or deceased - dont worry about the Mem adress, Email from Carla: 12/6/2018
				    if (memberBean.getMemberAddress() != null && !memberBean.isIncarcerated() && !memberBean.isDeceased())
				    {
					if (memberBean.getMemberAddress().getAddressId() != null && !memberBean.getMemberAddress().getAddressId().equals(""))
					{   //we need to update the current mem address
					    Address address = Address.find(Integer.parseInt(memberBean.getMemberAddress().getAddressId()));
					    address.setStreetNum(memberBean.getMemberAddress().getStreetNum());
					    address.setStreetNumSuffixId(memberBean.getMemberAddress().getStreetNumSuffixId());
					    address.setStreetName(memberBean.getMemberAddress().getStreetName());
					    address.setStreetTypeId(memberBean.getMemberAddress().getStreetTypeId());
					    address.setAptNum(memberBean.getMemberAddress().getAptNum());
					    address.setCity(memberBean.getMemberAddress().getCity());
					    address.setStateId(memberBean.getMemberAddress().getStateId());
					    address.setZipCode(memberBean.getMemberAddress().getZipCode());
					    address.setAdditionalZipCode(memberBean.getMemberAddress().getAdditionalZipCode());
					    address.setAddressTypeId(memberBean.getMemberAddress().getAddressTypeId());
					    address.setCountyId(memberBean.getMemberAddress().getCountyId());
					    address.setUpdateUserID(SecurityUIHelper.getLogonId());
					    address.setUpdateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
					    address.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
					    address.setValidated(memberBean.getMemberAddress().getValidated());
					}
					else
					{   //no current address, need to add new address	
					   
						Address address = new Address();
						address.setStreetNum(memberBean.getMemberAddress().getStreetNumber());
						address.setStreetNumSuffixId(memberBean.getMemberAddress().getStreetNumSuffixId());
						address.setStreetName(memberBean.getMemberAddress().getStreetName());
						address.setStreetTypeId(memberBean.getMemberAddress().getStreetTypeId());
						address.setAptNum(memberBean.getMemberAddress().getAptNum());
						address.setCity(memberBean.getMemberAddress().getCity());
						address.setStateId(memberBean.getMemberAddress().getStateId());
						address.setZipCode(memberBean.getMemberAddress().getZipCode());
						address.setAdditionalZipCode(memberBean.getMemberAddress().getAdditionalZipCode());
						address.setAddressTypeId(memberBean.getMemberAddress().getAddressTypeId());
						address.setCountyId(memberBean.getMemberAddress().getCountyId());
						address.setUpdateUserID(SecurityUIHelper.getLogonId());
						address.setUpdateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
						address.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
						address.setValidated(memberBean.getMemberAddress().getValidated());
						pdMember.insertAddresses(address); //INSERT INTO JIMS2.JCMEMADDRESS
					}
				    }
				    PhoneNumber memPhoneNum = memberBean.getContactPhoneNumber();
				    if (memPhoneNum != null && memPhoneNum.getPhoneNumber() != null && memPhoneNum.getPhoneNumber().trim().length() > 0)
				    {
					Iterator iteratr = FamilyMemberPhone.findAll("familyMemberId", memberBean.getMemberNum());
					if (iteratr.hasNext())
					{
					    FamilyMemberPhone phone = (FamilyMemberPhone) iteratr.next();//retrieves from JCMEMPHONE
					    //FamilyMemberPhone pdPhone = FamilyMemberPhone.find(phone.getOID());
					    PhoneNumber contactPhoneNumber = memberBean.getContactPhoneNumber();
					    Phone newPhone = null;
					    Phone phoneMaster = Phone.find(phone.getPhoneMasterId());
					    if (phoneMaster != null)
					    {
						phoneMaster.setPhoneNumber(memberBean.getContactPhoneNumber().getPhoneNumber());
						phoneMaster.setPhoneExt(memberBean.getContactPhoneNumber().getExt());
						if (memberBean.getPhoneInd() != null && memberBean.getPhoneInd().equalsIgnoreCase("P"))
						    phoneMaster.setPrimaryInd(true);
						else
						    phoneMaster.setPrimaryInd(false);
						
						phone.setPhoneTypeId(memberBean.getPhoneType());
					    }
					}else
					    {   //NO existing phone num associated with the family ->add Phone to the member
						PhoneNumber contactPhoneNumber = memberBean.getContactPhoneNumber();
						//Create in GLPhone first
						Phone phone = new Phone();
						phone.setPhoneNumber(memberBean.getContactPhoneNumber().getPhoneNumber());
						phone.setPhoneExt(memberBean.getContactPhoneNumber().getExt());
						if(memberBean.getPhoneInd()!=null && memberBean.getPhoneInd().equalsIgnoreCase("P"))
						    phone.setPrimaryInd(true);
						else
						    phone.setPrimaryInd(false);

						home = new Home();
						home.bind(phone);
						//add to memphone
						FamilyMemberPhone memberPhone = null;
						if (contactPhoneNumber != null && contactPhoneNumber.getPhoneNumber() != null && contactPhoneNumber.getPhoneNumber().trim().length() > 0)
						{
						    memberPhone = JuvenileFamilyHelper.createMemberPhone(contactPhoneNumber.getPhoneNumber(), contactPhoneNumber.getExt(), memberBean.getPhoneType(), (String) pdMember.getOID());
						}

						//add phonenum to member
						if (memberPhone != null)
						{
						    pdMember.insertPhoneNumbers(memberPhone);
						}
					}
				    }
				    home = new Home();
				    home.bind(pdMember);
				    //FamilyConstellationMember constellationMember = FamilyConstellationMember.find(memberBean.getMemberNum());
				    Iterator iterator = FamilyConstellationMember.findAll("theFamilyMemberId", memberBean.getMemberNum());
				    while (iterator.hasNext())
					{
						FamilyConstellationMember constellationMember = (FamilyConstellationMember) iterator.next();
						constellationMember.setRelationshipToJuvenileId(memberBean.getRelationshipId());
						constellationMember.setUpdateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
						constellationMember.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
						constellationMember.setUpdateUserID(SecurityUIHelper.getLogonId());
						if("true".equalsIgnoreCase(memberBean.getPrimaryContact())){
						    
						    constellationMember.setPrimaryContact(true);
						}
					}
				}
			    }
			}
		    }
		}
	    } //changes for Update Juvenile (Referral US 71171)ENDS
	}
	else
	{
	    // For new kid.. first time referral.
	    if (request.getAction() != null && request.getAction().equalsIgnoreCase("createJuvenile"))
	    {
		Home home = new Home();
		juv = new JuvenileCore();
		//create a new record.
		juv.setRecType("JUVENILE");  //Bug #77723
		juv.setLastName(request.getLastName());
		juv.setFirstName(request.getFirstName());
		juv.setMiddleName(request.getMiddleName());
		juv.setNameSuffix(request.getNameSuffix());
		juv.setRaceId(request.getRaceId());
		// bug fix for 97750
		juv.setOriginalRaceId(request.getRaceId());
		//
		juv.setSexId(request.getSexId());
		juv.setDateOfBirth(request.getDateOfBirth());
		juv.setSSN(request.getSSN());
		juv.setStatusId("N"); //no casefile
		juv.setJuvenileNum(request.getJuvenileNum());
		juv.setComments(request.getComments());
		juv.setCheckOutTo(request.getCheckedOutTo());
		juv.setCheckedOutDate(DateUtil.stringToDate(request.getCheckedOutDate(), DateUtil.DATE_FMT_1));
		juv.setLastActionBy(request.getLastActionBy());
		juv.setLastUpdate(DateUtil.stringToDate(request.getLastUpdate(), DateUtil.DATE_FMT_1));
		juv.setOperator(request.getOperator());		
		juv.setJuvExcludedReporting(request.getJuvExcludedReporting());
		juv.setLiveWithTjjd("UNKN"); //Default to unknown USER STORY 174504

		if (request.getJuvAddress().getStreetNum() != null && !request.getJuvAddress().getStreetNum().equalsIgnoreCase(""))
		{
		    Address address = new Address();
		    address.setStreetNum(request.getJuvAddress().getStreetNum());
		    address.setStreetNumSuffixId(request.getJuvAddress().getStreetNumSuffixId());
		    address.setStreetName(request.getJuvAddress().getStreetName());
		    address.setStreetTypeId(request.getJuvAddress().getStreetTypeId());
		    address.setAptNum(request.getJuvAddress().getAptNum());
		    address.setStateId(request.getJuvAddress().getStateId());
		    address.setCity(request.getJuvAddress().getCity());
		    address.setStateId(request.getJuvAddress().getStateId());
		    address.setZipCode(request.getJuvAddress().getZipCode());
		    address.setAdditionalZipCode(request.getJuvAddress().getAdditionalZipCode());
		    address.setAddressTypeId(request.getJuvAddress().getAddressTypeId());
		    address.setCountyId(request.getJuvAddress().getCountyId());
		    new Home().bind(address);
		    juv.setAddressId(Integer.parseInt(address.getOID()));
		}
		home.bind(juv);

		// add it as part of JCJUVENILE TABLE
		Juvenile juvenile = new Juvenile();
		juvenile.setJuvenileNum(request.getJuvenileNum());
		juvenile.setHispanic(request.getHispanic());
		juvenile.setStudentId(request.getJuvenileNum());
		juvenile.setCreateUserID(SecurityUIHelper.getLogonId());
		juvenile.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		juvenile.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		new Home().bind(juvenile);
		if (request.getMemberDetailsBeanList() != null && !request.getMemberDetailsBeanList().isEmpty())
		{
		    FamilyConstellation familyConstellation;
		    try
		    {
			familyConstellation = createFamilyConstellation(request.getMemberDetailsBeanList(), request.getJuvenileNum());
			if (familyConstellation != null)
			{
			    juvenile.insertFamilyConstellationList(familyConstellation);
			}
		    }
		    catch (RuntimeException e)
		    {
			e.printStackTrace();
		    }
		    catch (Exception e)
		    {
			e.printStackTrace();
		    }

		}
		//add school info to JCSCHOOL US 71172
		if(request.getSchoolDistId()!=null && !request.getSchoolDistId().equals(""))  //Bug # ?
		{
        		JuvenileSchoolHistoryResponseEvent schoolHistoryRespEvt = new JuvenileSchoolHistoryResponseEvent();
        		schoolHistoryRespEvt.setJuvenileNum(request.getJuvenileNum());
        		schoolHistoryRespEvt.setSchoolId(request.getSchoolId());
        		schoolHistoryRespEvt.setSchoolDistrictId(request.getSchoolDistId());
        		//schoolHistoryRespEvt.setExitTypeCode(request.getExitTypeId());
        		schoolHistoryRespEvt.setProgramAttendingCode(request.getPgmAttdngId());
        		schoolHistoryRespEvt.setGradeLevelCode(request.getGradeLvlId());
        		schoolHistoryRespEvt.setSchoolAttendanceStatusCode(request.getSchoolAttendanceStatusId());
        		schoolHistoryRespEvt.setTEASchoolNumber(request.getSchoolteaCode());
        		schoolHistoryRespEvt.setLastAttendedDate(new Date(createDate.getTime()) );
        		
        		SaveJuvenileSchoolHistoryEvent saveEvent = (SaveJuvenileSchoolHistoryEvent)
        			EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILESCHOOLHISTORY);
        		saveEvent.addRequest(schoolHistoryRespEvt);
        		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        		dispatch.postEvent(saveEvent);
        		// Get PD Response Event
        		CompositeResponse response = (CompositeResponse)dispatch.getReply();
        		// Perform Error handling
        		MessageUtil.processReturnException(response);
		}

	    }
	    else
	    {
		ErrorResponseEvent errRespEvt = new ErrorResponseEvent();
		errRespEvt.setMessage("Error: Unable to find Juvenile Core record");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errRespEvt);
	    }
	}
    }//end of pd.juvenile.transactions.SaveJuvenileProfileMainCommand.execute

    /**
     * @param event
     * @roseuid 42A9B4880265
     */
    public void onRegister(IEvent event)
    {

    } //end of pd.juvenile.transactions.SaveJuvenileProfileMainCommand.onRegister

    /**
     * @param event
     * @roseuid 42A9B4880272
     */
    public void onUnregister(IEvent event)
    {

    } //end of pd.juvenile.transactions.SaveJuvenileProfileMainCommand.onUnregister

    /**
     * @param anObject
     * @roseuid 42A9B4880274
     */
    public void update(Object anObject)
    {

    } //end of pd.juvenile.transactions.SaveJuvenileProfileMainCommand.update

    /*
     * This method will examine what ScarsMarksRequestEvent have been posted.  Any that 
     * have been posted are the complete list of scarsmarks for this juvenile.  This 
     * list should be compared against the existing scarsmarks to remove any that
     * are no longer scarsmarks and add ones that are new scarsmarks.
     */
    private void updateScarsMarks(Collection scarsMarks, Juvenile juv)
    {
	//Collection scarsMarks = MessageUtil.compositeToCollection(createJWEvent, ScarsMarksRequestEvent.class);
	if (scarsMarks != null)
	{
	    Collection existing = juv.getScarsAndMarks();

	    // Create a new list that will contain all the codes that
	    // should be associated with this warrant. 
	    ArrayList inserts = new ArrayList();
	    Iterator i = scarsMarks.iterator();
	    while (i.hasNext())
	    {
		//ScarsMarksRequestEvent scarsEvent = (ScarsMarksRequestEvent) i.next();
		String scarCode = (String) i.next();
		ScarsMarksTattoosCode code = ScarsMarksTattoosCode.find(scarCode);
		if (code != null)
		{
		    //jw.insertScarsMarks(code);
		    // Add  the found caution to the newCautions List
		    inserts.add(code);
		    // If this Code does not exist in the current list
		    // of cautions, insert it as a new caution
		    if (existing.contains(code.getCode()) == false)
		    {
			juv.insertScarsAndMarks(code);
		    }
		}
	    }
	    // Take the existing or pre-update list of codes and compare it against the
	    // the codes that should be associated.  Return any codes that should be 
	    // removed (were unselected by the user).
	    Collection remove = this.getRemovedPersistantObjects(existing, inserts);
	    Iterator delete = remove.iterator();
	    while (delete.hasNext())
	    {
		// Remove the code off the codes collection of JuvenileWarrant
		// This will delete the code
		juv.removeScarsAndMarks((ScarsMarksTattoosCode) delete.next());
	    }
	}
    }

    /*
     * Will compare two lists of collections that contain persistent objects and return a
     * collection of objects that are not contained in the first list, but are contained in
     * the second list.  Each list must contain PersistentObjects.  Otherwise an empty Collection
     * will be returned.
     * 
     * @parm currentList
     * @parm newList
     * @return Collection
     */
    private Collection getRemovedPersistantObjects(Collection currentList, Collection newList)
    {
	ArrayList removed = new ArrayList();
	Iterator i = currentList.iterator();
	while (i.hasNext())
	{

	    Object obj = i.next();
	    if (obj instanceof ScarsMarksTattoosCode)
	    {
		ScarsMarksTattoosCode po = (ScarsMarksTattoosCode) obj;
		if (newList.contains(po.getCode()) == false)
		{
		    removed.add(po);
		}
	    }
	}

	return (Collection) removed;
    }

    /**
     * @param juvfamily
     * @param juvNum
     */

    private static FamilyConstellation createFamilyConstellation(List<JuvenileReferralMemberDetailsBean> juvFamilyList, String juvNum) throws RuntimeException, Exception
    {
	FamilyConstellation familyConstellation = null;
	Iterator<JuvenileReferralMemberDetailsBean> familyMemberList = juvFamilyList.iterator();
	while (familyMemberList.hasNext())
	{
	    FamilyMember member = null;
	    JuvenileReferralMemberDetailsBean memberBean = (JuvenileReferralMemberDetailsBean) familyMemberList.next();

	    // check if member exists
	    ArrayList<String> suspMemberIds = new ArrayList<String>();
	    if ((memberBean.getFirstName() != null && memberBean.getFirstName().trim().length() > 0) || (memberBean.getMiddleName() != null && memberBean.getMiddleName().trim().length() > 0) || (memberBean.getLastName() != null && memberBean.getLastName().trim().length() > 0))
	    {
		if (memberBean.getSSN() != null && !memberBean.getSSN().getSSN().equals("") && !memberBean.getSSN().getSSN().equals("666666666") && !memberBean.getSSN().getSSN().equals("777777777") && !memberBean.getSSN().equals("888888888") && !memberBean.getSSN().getSSN().equals("999999999"))
		{
		    //check if any other family member has the same ssn
		    Iterator<FamilyMember> familyMembersSSN = JuvenileCaseHelper.checkFamilyMemberSSN(memberBean.getSSN().getSSN());
		    while (familyMembersSSN.hasNext())
		    {
			FamilyMember memberSSN = (FamilyMember) familyMembersSSN.next();
			//check if they have the same name
			if (memberSSN != null && memberSSN.getFirstName() != null && !memberSSN.getFirstName().equalsIgnoreCase(memberBean.getFirstName()) && memberSSN.getLastName() != null && !memberSSN.getLastName().equalsIgnoreCase(memberBean.getLastName()))
			{
			    suspMemberIds.add(memberSSN.getFamilyMemberId());
			    memberSSN.setSuspiciousMemberMatch("SSN");
			}
		    }
		}
	    }
	    boolean isGuardianAvailable = false;

	    Address address = null;
	    PhoneNumber phoneNumber = null;

	    if (memberBean.getIncarceratedOrDeceased() == null || !memberBean.getIncarceratedOrDeceased().equalsIgnoreCase("Y"))
	    {
		address = new Address();
		address.setStreetNum(memberBean.getMemberAddress().getStreetNum());
		address.setStreetNumSuffixId(memberBean.getMemberAddress().getStreetNumSuffixId());
		address.setStreetName(memberBean.getMemberAddress().getStreetName());
		address.setStreetTypeId(memberBean.getMemberAddress().getStreetTypeId());
		address.setAptNum(memberBean.getMemberAddress().getAptNum());
		address.setCity(memberBean.getMemberAddress().getCity());
		address.setStateId(memberBean.getMemberAddress().getStateId());
		address.setZipCode(memberBean.getMemberAddress().getZipCode());
		address.setAdditionalZipCode(memberBean.getMemberAddress().getAdditionalZipCode());
		address.setAddressTypeId(memberBean.getMemberAddress().getAddressTypeId());
		address.setCountyId(memberBean.getMemberAddress().getCountyId());

		address.setCreateUserID(SecurityUIHelper.getLogonId());
		address.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		address.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		address.setValidated("N");
		phoneNumber = memberBean.getContactPhoneNumber();
	    }
	    member = getFamilyMember(memberBean, address, phoneNumber);

	    //suspicious
	    if (suspMemberIds != null && !suspMemberIds.isEmpty())
	    {
		Iterator<String> iter = suspMemberIds.iterator();
		while (iter.hasNext())
		{
		    String memberId = (String) iter.next();
		    JuvenileCaseHelper.markMembersSuspicious(memberId, member.getFamilyMemberId());
		}
	    }
	    if (memberBean.getRelationshipId().equalsIgnoreCase("BM"))
	    {
		if (familyConstellation == null)
		    familyConstellation = createFamilyConstellation(juvNum);
		FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "BM", memberBean.getPrimaryContact());
		familyConstellationMember.setGuardian(true);
		isGuardianAvailable = true;
		familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
		SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
		aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
		aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
		JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
	    }
	    // add member to constellation
	    if (memberBean.getRelationshipId().equalsIgnoreCase("BF"))
	    {
		if (familyConstellation == null)
		    familyConstellation = createFamilyConstellation(juvNum);
		FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "BF", memberBean.getPrimaryContact());
		familyConstellationMember.setGuardian(true);
		isGuardianAvailable = true;
		familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
		SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
		aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
		aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
		JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
	    }
	    // add member to constellation
	    if (memberBean.getRelationshipId().equalsIgnoreCase("OR"))
	    {
		FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "OR", memberBean.getPrimaryContact());
		if (isGuardianAvailable == false)
		{
		    if (familyConstellation == null)
			familyConstellation = createFamilyConstellation(juvNum);
		    familyConstellationMember.setGuardian(true);
		    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
		    SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
		    aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
		    aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
		    JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
		}
	    }
	}
	return familyConstellation;
    }

    /**
     * @param firstName
     * @param middleName
     * @param lastName
     * @param memberAddress
     * @param memberPhoneNum
     * @param ssn
     * @return
     */
    private static FamilyMember getFamilyMember(JuvenileReferralMemberDetailsBean memberBean, Address memberAddress, PhoneNumber contactPhoneNumber) throws RuntimeException, Exception
    {
	if (memberBean != null)
	{
	    String firstName = memberBean.getFirstName();
	    String middleName = memberBean.getMiddleName();
	    String lastName = memberBean.getLastName();
	    String ssn = memberBean.getSSN().getSSN();

	    FamilyMember member = null;
	    if ((firstName != null && firstName.trim().length() > 0) || (middleName != null && middleName.trim().length() > 0) || (lastName != null && lastName.trim().length() > 0))
	    {
		//TODO need to check if member exists
		member = new FamilyMember();
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setMiddleName(middleName);
		if (ssn == null || ssn.trim().equals(""))
		{
		    ssn = "666666666";
		}
		member.setSsn(ssn);
		/*member.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);*/
		member.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
		// create address
		if (memberAddress != null && !memberAddress.getStreetName().equals(""))
		{
		    Address address = null;
		    //Bug #77597 - section below taken out
		    // check if address exists 
		    /* AddressValidationEvent requestEvent = new AddressValidationEvent();
		    requestEvent.setStreetNumStr(memberAddress.getStreetNum());
		    requestEvent.setStreetName(memberAddress.getStreetName());
		    if (memberAddress.getZipCode() != null && memberAddress.getZipCode().equals("") == false) {
		    	requestEvent.setZipCodeStr(memberAddress.getZipCode());
		    }

		     Iterator iter = Address.findAll(requestEvent);
		     //Iterator<Address> iter = Address.findAll("streetName", memberAddress.getStreetName());
		     if (iter.hasNext())
		     {
		    address = (Address) iter.next();
		     }
		     else
		     {*/
		    address = memberAddress;
		    // }
		    member.insertAddresses(address);
		}

		FamilyMemberPhone memberPhone = null;
		if (contactPhoneNumber != null && contactPhoneNumber.getPhoneNumber() != null && contactPhoneNumber.getPhoneNumber().trim().length() > 0)
		{
		    SaveFamilyMemberContactEvent fmPhoneEvent = new SaveFamilyMemberContactEvent();
		    fmPhoneEvent.setPhoneNum(contactPhoneNumber.getPhoneNumber());
		    fmPhoneEvent.setExtentionNum(contactPhoneNumber.getExt());
		    fmPhoneEvent.setConstellationMemberPhoneId(contactPhoneNumber.getPhoneId());
		    fmPhoneEvent.setPhoneTypeId(memberBean.getPhoneType());
		    fmPhoneEvent.setPrimaryInd("P".equalsIgnoreCase(memberBean.getPhoneInd()) ? true : false);
		    memberPhone = JuvenileFamilyHelper.createMemberPhone( fmPhoneEvent, (String) member.getOID());
		}

		//add phonenum to member
		if (memberPhone != null)
		{
		    member.insertPhoneNumbers(memberPhone);
		}

		if (member.getOID() == null)
		{
		    member.setIncarcerated(memberBean.isIncarcerated());
		    member.setDeceased(memberBean.isDeceased());
		    IHome home = new Home();
		    home.bind(member);
		}
	    }
	    return member;
	}
	return null;
    }

    /*
     * @param juvNum
     */
    private static FamilyConstellation createFamilyConstellation(String juvNum)
    {
	FamilyConstellation familyConstellation = new FamilyConstellation();
	familyConstellation.setEntryDate(new Date());
	familyConstellation.setJuvenileId(juvNum);
	familyConstellation.setActive(true);
	familyConstellation.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	/*familyConstellation.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);*/
	familyConstellation.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
	return familyConstellation;
    }

    /**
     * @param mother
     * @param relationshipId
     * @return FamilyConstellationMember
     */
    private static FamilyConstellationMember createCostellationMember(FamilyMember member, String relationshipId, String primaryGuardian)
    {
	FamilyConstellationMember familyConstellationMember = null;
	if (member != null)
	{
	    familyConstellationMember = new FamilyConstellationMember();
	    familyConstellationMember.setTheFamilyMemberId((String) member.getOID());
	    familyConstellationMember.setRelationshipToJuvenileId(relationshipId);
	    familyConstellationMember.setInHomeStatus(false); // default to No as per requrement
	    familyConstellationMember.setInvolvementLevelId(PDJuvenileCaseConstants.FAMILY_MEMBER_INVOLVEMENT_LVL_LOW); // default to LOW as per requirements
	    familyConstellationMember.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	    /*familyConstellationMember.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);*/
	    familyConstellationMember.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
	    if(primaryGuardian != null && primaryGuardian.equalsIgnoreCase("true") ){ //added for US 157561 STARTs, added primaryGUardian variable in the method definition
		familyConstellationMember.setPrimaryContact(true);
	    } //added for US 157561 ENDS
	    
	}
	return familyConstellationMember;
    }

    /**
     * @param jjsService
     * @return
     */
    private static OfficerProfile getOfficer(String logonId) throws InvalidProbationOfficerException, RuntimeException, Exception
    {
	OfficerProfile officer = null;
	String jpoUserId = logonId;

	if (jpoUserId == null || jpoUserId.equals(UIConstants.EMPTY_STRING))
	{
	    throw new InvalidProbationOfficerException("Invalid Probation Officer Exception");
	}
	else
	{
	    Iterator<OfficerProfile> officers = OfficerProfile.findAll("logonId", jpoUserId);
	    if (officers.hasNext())
	    {
		while (officers.hasNext())
		{
		    officer = officers.next();
		    String status = officer.getStatusId();
		    if (status != null && status.equalsIgnoreCase("A"))
		    {
			return officer;
		    }
		}
	    }
	    else
	    {
		throw new InvalidProbationOfficerException("Invalid Probation Officer Exception");
	    }
	}
	return officer;
    }
    
    /**
     * 
     * @param coreObj
     */
    private void savePreferredNameToAlias( JuvenileCore coreObj ){
	
	JuvenileAlias juvAlias = new JuvenileAlias();
	juvAlias.setJuvenileNum(coreObj.getJuvenileNum());
	juvAlias.setJuvenileType("P");
	juvAlias.setAliasNotes( coreObj.getPreferredFirstName() );
	juvAlias.setRaceId(coreObj.getRaceId());
	juvAlias.setSexId(coreObj.getSexId());
	juvAlias.setFirstName(coreObj.getFirstName());
	juvAlias.setLastName(coreObj.getLastName());
	juvAlias.setMiddleName(coreObj.getMiddleName());
	juvAlias.setDateOfBirth(coreObj.getDateOfBirth());
	juvAlias.setAliasEntryDate(new Timestamp(DateUtil.getCurrentDate().getTime()));
	
    }

} // end SaveJuvenileProfileMainCommand
