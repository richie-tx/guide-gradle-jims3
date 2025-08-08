package ui.juvenilewarrant.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.codetable.GetCodesEvent;
import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.GetScarsMarksTattoosCodesEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.ICode;
import messaging.common.domintf.IIdentity;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.juvenilewarrant.reply.ProcessReturnOfServiceResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.LengthBean;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.common.form.AddressValidationForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberAddress;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.helper.JuvenileWarrantFormDirector;
import ui.security.SecurityUIHelper;

/**
 * @author ldeen This form contains all the attributes needed to create, update,
 *         and retrieve a juvenile warrant.
 */
public class JuvenileWarrantForm extends AddressValidationForm
{

    private String action;

    private String addressId;

    private Collection addressTopics;

    private String addressType;

    private String affidavitStatement;

    private String agencyId;

    private String aliasName;

    private String apartmentNum;

    private String arrestAddress;

    private Date arrestDate;

    private String associateAddress;

    private MemberAddress familyMemberAddress;

    private String associateId;

    private Name associateName = new Name();

    private List associates;

    private Collection associateServiceAddresses;

    private boolean associateUpdatable;

    private String asstDistrictAttorneyName;

    private String backForwardString;

    private String backToWarrantUrl;

    private String buildId;

    private String cautionComments;

    private PhoneNumber cellPhone = new PhoneNumber("");

    private String chargeCode;

    private String chargeCodeId;

    private String chargeCourt;

    private String chargeDescription;

    private String chargeId;

    private String chargeNcicNum;

    private List charges;

    private String chargeSeqNum;

    private List chargesSelected;

    private String city;

    private String comments;

    private String complexionId;

    private String costAirFare;

    private String costMileage;

    private String costPerDiem;

    private String county;

    private String courtId;

    private String currentServiceAddress;

    private String currentServiceAttemptComments;

    private String currentServiceBadAddress;

    private Date currentServiceDate;

    private String currentServiceDateString;

    private String currentServiceHour;

    private String currentServiceMinute;

    private String currentServiceTime;

    private String currentWarrantServiceStatus;

    private String daLogNum;

    private Date dateOfBirth;

    private String dateOfBirthSource;

    private Date dateOfIssue;

    private Date decisionDate;

    private String degree;

    //department collection
    Collection departments = new ArrayList();

    private int departmentSize = 0;

    private boolean details;

    private Map districtMap;

    private Map criminalMap;

    private String email;

    private IPhoneNumber executorCellNum = new PhoneNumber("");

    private String executorDepartmentName;

    private String executorEmail;

    private String executorId;

    private String executorIdType;

    private IName executorName = new Name();

    private String executorOriginatingAgencyId;

    private IPhoneNumber executorPager = new PhoneNumber("");

    private IPhoneNumber executorPhoneNum = new PhoneNumber("");

    private String eyeColorId;

    private String faxLocation;

    private PhoneNumber faxNumber = new PhoneNumber("");

    private String fbiNum;

    private Date fileStampDate;

    private String fileStampLogonId;

    private Name fileStampName = new Name();

    private String hairColorId;

    // DPA: Per defect 24261, there should be separate fields for
    // feet and inches
    private String heightFeet;

    private String heightInch;

    private PhoneNumber homePhone = new PhoneNumber("");

    private IName jpOfficerName = new Name();

    private String jpOfficerEmail;

    private IName juvenileName = new Name();

    private String juvenileNum;

    private String leaOriNum;

    private String level;

    private String nameSuffix;

    private String ncicNum;

    private String newAddress;

    private JuvenileAssociateBean newReleaseToAssociate = new JuvenileAssociateBean();

    private String officerAgencyId;

    private String officerBadgeNumber;

    private PhoneNumber officerCellNum = new PhoneNumber("");

    private String officerId;

    private String officerIdType;

    private String officerIdTypeId;

    private String officerLogonId;

    private IName officerName = new Name();

    private String officerOID;

    // adding to help create officer with both other id number
    // and badge number
    private String officerOtherIdNumber;

    private PhoneNumber officerPager = new PhoneNumber("");

    private PhoneNumber officerPhoneNum = new PhoneNumber("");

    private boolean onlyCharge = false;

    private String[] originalCautions;

    private String originalCharge;

    private String[] originalCharges;

    private String[] originalScars;

    private String originalScarsString;

    private String[] originalTattoos;

    private String originalTattoosString;

    private PhoneNumber pager = new PhoneNumber("");

    private String petitionNum;

    private PhoneNumber phoneNum = new PhoneNumber("");

    private Collection priorServiceAddresses;

    private String probationOfficerOfRecordId;

    private String probationOfficerOfRecordName;

    private String raceId;

    private Date recallDate;

    private String recallLogonId;

    private Name recallName = new Name();

    private String recallReasonId;

    private String referralNum;

    private String refreshButton; //search criteria (hidden field)

    private String releaseAssociateNum;

    private Date releaseDecisionDate;

    private String releaseDecisionId;

    private String releaseDecisionLogonId;

    private String releaseDecisionName;

    private String releaseDecisionUserId;

    private String releaseDecisionUserName;

    private String releaseRelationshipToJuvenile;

    private String schoolCodeId;

    private String schoolDistrictId;

    private String schoolDistrictName;

    private List schoolDistricts;

    private String schoolName;

    private List schools;

    private String search = "";

    private String searchDate1;

    private String searchDate2;

    private String searchDepartmentId;

    private String searchDepartmentName;

    private String searchResultSize;

    private String secondaryAction;

    private String selectedAssociateId;

    private String selectedAssociateAddressId;

    private String selectedBadAddress;

    private String[] selectedCautions;

    private String selectedCharge;

    private String[] selectedCharges;

    private String[] selectedScars;

    private String[] selectedTattoos;

    private String selectedValue = "";

    private String serviceAddress;

    private String serviceAddressType;

    private String serviceAttemptComments;

    private String serviceNumber;

    private String serviceReturnGeneratedStatus;

    private String serviceReturnGeneratedStatusId;

    private String serviceReturnSignatureStatus;

    private String serviceReturnSignatureStatusId;

    private Collection services;

    private String serviceStatus;

    private String sexId;

    private String sid;

    private String signatureCommandId;

    private SocialSecurity ssn = new SocialSecurity("");

    private String state;

    private String stateIdNumber;

    private String streetName;

    private String streetNum;

    private String streetType;

    private boolean successfulWarrant;

    private List summaryOfFacts;

    private String summOfFactsDisplaySize;

    private String title;

    private String transactionNum;

    private Date transferCustodyDate;

    private String transferCustodyDateString;

    private String transferCustodyTimeString;

    private String transferLocationId;

    private String transferOfficerDepartmentId;

    private String transferOfficerId;

    private String transferOfficerIdType;

    private String transferOfficerLogonId;

    private String unsendNotSignedReason;

    private String userId;

    private Date warrantAcknowledgementDate;

    private String warrantAcknowledgeStatusId;

    private Date warrantActivationDate;

    private String warrantActivationDateString;

    private String warrantActivationStatusId;

    private Name warrantActivationStatusName = new Name();

    private String warrantNum;

    private String warrantOriginatorAgencyId;

    private String warrantOriginatorAgencyName;

    private String warrantOriginatorId;

    private String warrantOriginatorJudge;

    private String warrantOriginatorName;

    private Collection warrants;

    private Collection warrantsSearchResults;

    private String warrantsSearchResultsSize;

    private Name warrantSignatureStatusName = new Name();

    private String warrantSignedStatusId;

    private String warrantStatusId;

    private String warrantTypeId;

    private String warrantTypeUI;

    private String weight;

    private PhoneNumber workPhone = new PhoneNumber("");

    private String zipCode;

    private String flowInd;

    private String zipCode2;

    private Map scarsMarksMap;

    private Map tattoosMap;

    private String juvRectype;

    private boolean validDeptCode;

    /**
     *  
     */
    public JuvenileWarrantForm()
    {
	super();
    }

    /**
     * Clear
     */
    public void clear()
    {
	this.action = "";
	this.refreshButton = "";
	this.userId = "";
	this.addressType = "";
	this.addressId = "";
	this.associateId = "";
	this.affidavitStatement = "";
	this.aliasName = "";
	this.agencyId = null;
	this.arrestAddress = "";
	this.asstDistrictAttorneyName = "";
	this.arrestDate = null;
	this.buildId = null;
	this.cellPhone = new PhoneNumber("");
	this.chargeCode = "";
	this.chargeCodeId = "";
	this.chargeId = null;
	this.city = "";
	this.comments = "";
	this.complexionId = "";
	this.costAirFare = null;
	this.costMileage = null;
	this.costPerDiem = null;
	this.courtId = null;
	this.currentServiceAddress = "";
	this.serviceAddressType = "";
	this.currentServiceBadAddress = "";
	this.currentServiceDate = null;
	this.currentServiceDateString = "";
	this.currentServiceHour = "";
	this.currentServiceMinute = "";
	this.currentServiceTime = "";
	this.currentServiceAttemptComments = "";
	this.currentWarrantServiceStatus = "";
	this.daLogNum = "";
	this.dateOfBirth = null;
	this.dateOfBirthSource = "";
	this.dateOfIssue = null;
	this.decisionDate = null;
	this.email = "";
	this.eyeColorId = null;
	this.executorId = "";
	this.executorName.clear();
	this.executorOriginatingAgencyId = "";
	this.executorDepartmentName = null;
	this.executorIdType = "";
	this.executorCellNum = new PhoneNumber("");
	this.executorPager = new PhoneNumber("");
	this.executorPhoneNum = new PhoneNumber("");
	this.executorEmail = "";
	this.faxLocation = "";
	this.faxNumber = new PhoneNumber("");
	this.fbiNum = "";
	this.fileStampDate = null;
	this.fileStampLogonId = "";
	this.fileStampName.clear();
	this.hairColorId = "";
	this.heightFeet = "";
	this.heightInch = "";
	this.homePhone = new PhoneNumber("");
	this.jpOfficerName.clear();
	this.jpOfficerEmail = "";
	this.juvenileName.clear();
	this.associateName.clear();
	this.juvenileNum = "";
	this.leaOriNum = "";
	this.nameSuffix = "";
	this.officerName = new Name();
	this.officerLogonId = null;
	this.officerOID = null;
	this.officerId = null;
	this.officerAgencyId = null;
	this.officerIdTypeId = "";
	this.officerCellNum = new PhoneNumber("");
	this.officerPager = new PhoneNumber("");
	this.officerPhoneNum = new PhoneNumber("");
	this.cautionComments = "";
	this.pager = new PhoneNumber("");
	this.petitionNum = "";
	this.phoneNum = new PhoneNumber("");
	this.probationOfficerOfRecordName = "";
	this.raceId = null;
	this.recallDate = null;
	this.recallReasonId = "";
	this.recallLogonId = "";
	this.recallName.clear();
	this.referralNum = "";

	this.releaseDecisionId = null;
	this.releaseDecisionDate = null;
	this.releaseDecisionLogonId = "";
	this.releaseDecisionName = "";

	this.selectedCautions = null;
	this.selectedScars = null;
	this.selectedCharges = null;
	this.selectedTattoos = null;

	this.originalCautions = null;
	this.originalScars = null;
	this.originalScarsString = "";
	this.originalCharges = null;
	this.originalCharge = null;
	this.originalTattoos = null;
	this.originalTattoosString = "";

	this.schoolDistrictId = "";
	this.schoolDistrictName = "";
	this.schoolCodeId = "";
	this.schoolName = "";
	this.searchDepartmentId = "";
	this.searchDepartmentName = "";
	this.secondaryAction = "";
	this.selectedAssociateId = "";
	this.selectedAssociateAddressId = "";
	this.selectedBadAddress = "";
	this.serviceAddress = "";
	this.serviceAttemptComments = "";
	this.serviceNumber = "";
	this.serviceStatus = "";
	this.sexId = "";
	ssn.clear();
	this.state = "";
	this.stateIdNumber = "";
	this.streetName = "";
	this.streetNum = "";
	this.streetType = "";
	this.successfulWarrant = false;
	this.summOfFactsDisplaySize = "";

	this.title = "";
	this.transactionNum = "";
	this.transferCustodyDate = null;
	this.transferCustodyDateString = null;
	this.transferCustodyTimeString = null;
	this.transferLocationId = "";
	this.transferOfficerId = "";
	this.transferOfficerIdType = "";
	this.transferOfficerDepartmentId = null;
	this.transferOfficerLogonId = null;
	this.unsendNotSignedReason = "";
	this.warrantAcknowledgementDate = null;
	this.warrantAcknowledgeStatusId = "";
	this.warrantActivationDate = null;
	this.warrantActivationStatusId = "";
	this.warrantActivationStatusName.clear();
	this.warrantNum = "";
	this.warrantOriginatorId = "";
	this.warrantOriginatorJudge = null;
	this.signatureCommandId = null;
	this.warrantSignedStatusId = "";
	this.warrantSignatureStatusName.clear();
	this.warrantStatusId = "";
	this.warrantTypeId = null;
	this.weight = "";
	this.workPhone = new PhoneNumber("");
	this.zipCode = "";
	this.zipCode2 = "";

	this.onlyCharge = false;

	//added the following lines per defect JIMS200021927
	this.serviceReturnGeneratedStatus = "";
	this.serviceReturnGeneratedStatusId = "";
	this.serviceReturnSignatureStatus = "";
	this.serviceReturnSignatureStatusId = "";
	this.releaseDecisionName = "";

	this.level = "";
	this.degree = "";

	this.clearOfficerInfo();
	this.departmentSize = 0;
	this.selectedValue = "";
	super.setAddressStatus(null);
	super.setValidAddressMessage(null);

    }

    public void clearCautionCheckBoxes()
    {
	this.selectedCautions = null;
    }

    public void clearCharges()
    {
	this.charges = new ArrayList();
    }

    public void clearNewAddres()
    {

	this.associateId = "";
	this.state = "";
	this.stateIdNumber = "";
	this.streetName = "";
	this.streetNum = "";
	this.streetType = "";
	this.apartmentNum = "";
	this.zipCode = "";
	this.zipCode2 = "";
	this.addressType = "";
    }

    /**
     * clearNewReleaseAssociate
     */
    public void clearNewReleaseAssociate()
    {
	newReleaseToAssociate.clearAssociateAddresses();
	newReleaseToAssociate.clearAssociateInfo();
    }

    /**
     * Clears the officer fields.
     */
    public void clearOfficerInfo()
    {
	officerId = "";
	officerIdType = "";
	officerOtherIdNumber = "";
	officerBadgeNumber = "";
	officerName = new Name();
	workPhone.clear();
	cellPhone.clear();
	pager.clear();
	officerPhoneNum.clear();
	officerCellNum.clear();
	officerPager.clear();
	email = "";
    }

    /**
     * Clears the officer name only
     */
    public void clearOfficerName()
    {
	officerName = new Name();
    }

    /**
     * Clear Search Criteria for Recall warrant
     */
    public void clearSearchCriteria()
    {
	juvenileName.setFirstName("");
	juvenileName.setLastName("");
	warrantNum = "";
	associateName.setFirstName("");
	associateName.setLastName("");
    }

    /**
     * Clear Warrants
     */
    public void clearWarrants()
    {
	if (warrants != null)
	{
	    warrants.clear();
	}
    }

    public void clearWarrantTypeUI()
    {
	this.warrantTypeUI = "";
    }

    public void fillIdentity(IIdentity event)
    {
	if (this.daLogNum != null)
	{
	    this.setDaLogNum(this.daLogNum.trim());
	}

	event.setId(this.getDaLogNum());
    }

    /**
     * @return String
     */
    public String getAction()
    {
	return action;
    }

    /**
     * @return String addressId
     */
    public String getAddressId()
    {
	return addressId;
    }

    /**
     * @return Collection
     */
    public Collection getAddressTopics()
    {
	return addressTopics;
    }

    /**
     * @return addressType
     */
    public String getAddressType()
    {
	return addressType;
    }

    /**
     * @return String
     */
    public String getAffidavitStatement()
    {
	return affidavitStatement;
    }

    /**
     * @return agency
     */
    public String getAgency()
    {
	DepartmentResponseEvent departmentResponse = UIJuvenileWarrantHelper.fetchDepartment(this.agencyId);
	return departmentResponse.getDepartmentName();
    }

    /**
     * @return String
     */
    public String getAgencyId()
    {
	return agencyId;
    }

    /**
     * @return aliasName
     */
    public String getAliasName()
    {
	return aliasName;
    }

    /**
     * @return apartmentNum
     */
    public String getApartmentNum()
    {
	return apartmentNum;
    }

    /**
     * @return String
     */
    public String getArrestAddress()
    {
	return arrestAddress;
    }

    /**
     * @return
     */
    public Date getArrestDate()
    {
	return arrestDate;
    }

    /**
     * @return associateAddress
     */
    public String getAssociateAddress()
    {
	return associateAddress;
    }

    public MemberAddress getFamilyMemberAddress()
    {

	return familyMemberAddress;
    }

    public void setFamilyMemberAddress(MemberAddress memberAddress)
    {
	familyMemberAddress = memberAddress;
    }

    /**
     * @param associateNum
     * @return JuvenileAssociateBean
     */
    public JuvenileAssociateBean getAssociateByAssociateNumber(String associateNum)
    {
	JuvenileAssociateBean assoc = null;
	int len = this.associates.size();
	for (int i = 0; i < len; i++)
	{
	    assoc = (JuvenileAssociateBean) associates.get(i);
	    if (assoc.getAssociateNum().equals(associateNum))
	    {
		return assoc;
	    }
	}
	return assoc;
    }

    /**
     * @param id
     * @return JuvenileAssociateBean
     */
    public JuvenileAssociateBean getAssociateByRelationshipId(String id)
    {
	JuvenileAssociateBean assoc = null;
	int len = associates.size();
	for (int i = 0; i < len; i++)
	{
	    assoc = (JuvenileAssociateBean) associates.get(i);
	    if (assoc.getRelationshipToJuvenileId().equals(id))
	    {
		return assoc;
	    }
	}
	return assoc;
    }

    /**
     * @return
     */
    public String getAssociateFirstName()
    {
	return associateName.getFirstName();
    }

    /**
     * @return String
     */
    public String getAssociateId()
    {
	return associateId;
    }

    /**
     * @return
     */
    public String getAssociateLastName()
    {
	return associateName.getLastName();
    }

    /**
     * @return
     */
    public Name getAssociateName()
    {
	return associateName;
    }

    /**
     * @return Collection
     */
    public List getAssociates()
    {
	return associates;
    }

    /**
     * @return associateServiceAddresses
     */
    public Collection getAssociateServiceAddresses()
    {
	return associateServiceAddresses;
    }

    /**
     * @return
     */
    public boolean getAssociateUpdatable()
    {
	return associateUpdatable;
    }

    /**
     * @return String
     */
    public String getAsstDistrictAttorneyName()
    {
	return asstDistrictAttorneyName;
    }

    /**
     * @return Returns the backForwardString.
     */
    public String getBackForwardString()
    {
	return backForwardString;
    }

    public boolean isJJS()
    {
	return PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(this.warrantTypeId) || PDJuvenileWarrantConstants.WARRANT_TYPE_VOP.equals(this.warrantTypeId);
    }

    public boolean isJOT()
    {
	return PDJuvenileWarrantConstants.WARRANT_TYPE_PCW.equals(this.warrantTypeId) || PDJuvenileWarrantConstants.WARRANT_TYPE_ARR.equals(this.warrantTypeId) || PDJuvenileWarrantConstants.WARRANT_TYPE_DTA.equals(this.warrantTypeId);
    }

    /**
     * @return
     */
    public String getBackToWarrantUrl()
    {
	// TODO This was Raj's idea for fix last minute crisis before MJW
	// delivery to production
	// make change ASAP
	return backToWarrantUrl;
    }

    /**
     * @return build
     */
    public String getBuild()
    {
	String build = CodeHelper.getCodeDescription(PDCodeTableConstants.BUILD, this.buildId);
	return build;
    }

    /**
     * @return String
     */
    public String getBuildId()
    {
	return buildId;
    }

    /**
     * @return String
     */
    public String getCautionComments()
    {
	return cautionComments;
    }

    /**
     * @return cautionsSelected
     */
    public List getCautionsSelected()
    {
	List codeList = new ArrayList();
	if (this.selectedCautions != null && this.selectedCautions.length > 0)
	{
	    GetCodesEvent requestEvent = (GetCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODES);
	    requestEvent.setCodeTableName(PDCodeTableConstants.CAUTIONS);
	    requestEvent.setThin(true);
	    requestEvent.setCodes(this.selectedCautions);

	    CompositeResponse response = MessageUtil.postRequest(requestEvent);

	    codeList = (List) MessageUtil.compositeToCollection(response, ICode.class);
	    Collections.sort(codeList);
	}
	return codeList;
    }

    /**
     * @return PhoneNumber
     */
    public PhoneNumber getCellPhone()
    {
	return cellPhone;
    }

    /**
     * @return chargeCode
     */
    public String getChargeCode()
    {
	return chargeCode;
    }

    /**
     * @return chargeCodeId
     */
    public String getChargeCodeId()
    {
	return chargeCodeId;
    }

    /**
     * @return
     */
    public String getChargeCourt()
    {
	return chargeCourt;
    }

    /**
     * @return chargeDescription
     */
    public String getChargeDescription()
    {
	return chargeDescription;
    }

    /**
     * @return chargeId
     */
    public String getChargeId()
    {
	return chargeId;
    }

    /**
     * @return chargeNcicNum
     */
    public String getChargeNcicNum()
    {
	return chargeNcicNum;
    }

    /**
     * @return charges
     */
    public List getCharges()
    {
	return charges;
    }

    /**
     * @return chargeSeqNum
     */
    public String getChargeSeqNum()
    {
	return chargeSeqNum;
    }

    /**
     * @return ChargesSelected
     */
    public List getChargesSelected()
    {
	return this.chargesSelected;
    }

    /**
     * @return String
     */
    public String getCity()
    {
	return city;
    }

    /**
     * @return String
     */
    public String getComments()
    {
	return comments;
    }

    /**
     * @return String
     */
    public String getCompleteSummaryOfFact()
    {
	StringBuffer sb = new StringBuffer();
	int len = this.summaryOfFacts.size();
	for (int i = 0; i < len; i++)
	{
	    String text = (String) this.summaryOfFacts.get(i);
	    sb.append(text);
	    sb.append(" ");
	}
	return sb.toString();
    }

    /**
     * @return complexion
     */
    public String getComplexion()
    {
	String complexion = CodeHelper.getCodeDescription(PDCodeTableConstants.SKIN_TONE, this.complexionId);
	return complexion;
    }

    /**
     * @return String
     */
    public String getComplexionId()
    {
	return complexionId;
    }

    /**
     * @return costAirFare
     */
    public String getCostAirFare()
    {
	return costAirFare;
    }

    /**
     * @return costMileage
     */
    public String getCostMileage()
    {
	return costMileage;
    }

    /**
     * @return costAirFare
     */
    public String getCostPerDiem()
    {
	return costPerDiem;
    }

    /* Begin Collections for Code Tables */
    /**
     * @return counties
     */
    public Collection getCounties()
    {
	return CodeHelper.getCountyCodes();
    }

    /**
     * @return county
     */
    public String getCounty()
    {
	return county;
    }

    /**
     * @return court
     */
    public String getCourt()
    {
	GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
	courtEvent.setCourt(this.courtId);
	CompositeResponse response = MessageUtil.postRequest(courtEvent);
	ICode courtCode = (ICode) MessageUtil.filterComposite(response, ICode.class);
	return courtCode.getDescription();
    }

    /**
     * @return String
     */
    public String getCourtId()
    {
	return courtId;
    }

    /**
     * @return currentServiceAddress
     */
    public String getCurrentServiceAddress()
    {
	return currentServiceAddress;
    }

    /**
     * @return currentServiceAttemptComments
     */
    public String getCurrentServiceAttemptComments()
    {
	return currentServiceAttemptComments;
    }

    /**
     * @return String currentServiceBadAddress
     */
    public String getCurrentServiceBadAddress()
    {
	return currentServiceBadAddress;
    }

    /**
     * @return
     */
    public Date getCurrentServiceDate()
    {
	return currentServiceDate;
    }

    /**
     * @return
     */
    public String getCurrentServiceDateString()
    {
	return currentServiceDateString;
    }

    /**
     * @return currentServiceHour
     */
    public String getCurrentServiceHour()
    {
	return currentServiceHour;
    }

    /**
     * @return currentServiceMinute
     */
    public String getCurrentServiceMinute()
    {
	return currentServiceMinute;
    }

    /**
     * @return Returns the currentServiceTime.
     */
    public String getCurrentServiceTime()
    {
	return currentServiceTime;
    }

    /**
     * @return currentWarrantServiceStatus
     */
    public String getCurrentWarrantServiceStatus()
    {
	return currentWarrantServiceStatus;
    }

    /**
     * @return currentWarrantServiceStatusDescription
     */
    public String getCurrentWarrantServiceStatusDescription()
    {
	return CodeHelper.getCodeDescription(PDCodeTableConstants.SERVICE_STATUS, this.currentWarrantServiceStatus);
    }

    /**
     * @return String
     */
    public String getDaLogNum()
    {
	return daLogNum;
    }

    /**
     * @return String
     */
    public Date getDateOfBirth()
    {
	return dateOfBirth;
    }

    /**
     * @return
     */
    public String getDateOfBirthSource()
    {
	return dateOfBirthSource;
    }

    /**
     * @return String
     */
    public Date getDateOfIssue()
    {
	return dateOfIssue;
    }

    /**
     * @return decisionDate
     */
    public Date getDecisionDate()
    {
	return decisionDate;
    }

    /**
     * @return
     */
    public String getDegree()
    {
	return degree;
    }

    /**
     * @return Returns the departments.
     */
    public Collection getDepartments()
    {
	return departments;
    }

    /**
     * @return Returns the departmentSize.
     */
    public int getDepartmentSize()
    {
	return departmentSize;
    }

    /**
     * @return String
     */
    public String getEmail()
    {
	return email;
    }

    /**
     * @return executorCellNum
     */
    public IPhoneNumber getExecutorCellNum()
    {
	return executorCellNum;
    }

    /**
     * @return String
     */
    public String getExecutorDepartmentName()
    {
	return executorDepartmentName;
    }

    /**
     * @return executorEmail
     */
    public String getExecutorEmail()
    {
	return executorEmail;
    }

    /**
     * @return String
     */
    public String getExecutorId()
    {
	return executorId;
    }

    /**
     * @return executorIdType
     */
    public String getExecutorIdType()
    {
	return executorIdType;
    }

    /**
     * @return executorName
     */
    public IName getExecutorName()
    {
	return executorName;
    }

    /**
     * @return String
     */
    public String getExecutorOriginatingAgencyId()
    {
	return executorOriginatingAgencyId;
    }

    /**
     * @return executorPager
     */
    public IPhoneNumber getExecutorPager()
    {
	return executorPager;
    }

    /**
     * @return executorPhoneNum
     */
    public IPhoneNumber getExecutorPhoneNum()
    {
	return executorPhoneNum;
    }

    /**
     * @return eyeColor
     */
    public String getEyeColor()
    {
	String eyeColor = CodeHelper.getCodeDescription(PDCodeTableConstants.EYE_COLOR, this.eyeColorId);
	return eyeColor;
    }

    /**
     * @return String
     */
    public String getEyeColorId()
    {
	return eyeColorId;
    }

    /**
     * @return faxLocation
     */
    public String getFaxLocation()
    {
	return faxLocation;
    }

    /**
     * @return faxNumber
     */
    public PhoneNumber getFaxNumber()
    {
	return faxNumber;
    }

    /**
     * @return String
     */
    public String getFbiNum()
    {
	return fbiNum;
    }

    /**
     * @return String
     */
    public Date getFileStampDate()
    {
	return fileStampDate;
    }

    /**
     * Used for the Reporting functionality
     * 
     * @return string
     */
    public String getFileStampDateforReport()
    {
	// TODO Put formats in naming
	return DateUtil.dateToString(this.fileStampDate, "MMMM dd, yyyy");
    }

    /**
     * @return FileStampFirstName
     */
    public String getFileStampFirstName()
    {
	return fileStampName.getFirstName();
    }

    /**
     * @return FileStampLastName
     */
    public String getFileStampLastName()
    {
	return fileStampName.getLastName();
    }

    /**
     * @return String
     */
    public String getFileStampLogonId()
    {
	return fileStampLogonId;
    }

    /**
     * @return fileStampName
     */
    public String getFileStampMiddleName()
    {
	return fileStampName.getMiddleName();
    }

    /**
     * @return fileStampName
     */
    public Name getFileStampName()
    {
	return fileStampName;
    }

    /** end on temporary getters and setters */

    /**
     * @return String
     */
    public String getFirst4SummaryOfFact()
    {
	int len = this.summaryOfFacts.size();
	StringBuffer sb = new StringBuffer();

	for (int i = 0; i < len && i < 4; i++)
	{
	    String text = (String) this.summaryOfFacts.get(i);
	    sb.append(text);
	    sb.append(" ");
	}
	return sb.toString();
    }

    /**
     * @return FirstName
     */
    public String getFirstName()
    {
	return juvenileName.getFirstName();
    }

    /**
     * @return String
     */
    public String getFormattedJuvenileName()
    {
	return juvenileName.getFormattedName();
    }

    /**
     * @return String
     */
    public String getFormattedOfficerName()
    {
	return officerName.getFormattedName();
    }

    /**
     * @return String
     */
    public String getFormattedRecallName()
    {
	return recallName.getFormattedName();
    }

    /**
     * @return String
     */
    public String getFormattedSSN()
    {
	return ssn.getFormattedSSN();
    }

    /**
     * @return String
     */
    public String getMaskedSSN()
    {
	return ssn.getMaskedSSN();
    }

    /**
     * @return String
     */
    public String getFormattedWarrantActivationStatusName()
    {
	return warrantActivationStatusName.getFormattedName();
    }

    /**
     * @return String
     */
    public String getFormattedWarrantSignatureStatusName()
    {
	return warrantSignatureStatusName.getFormattedName();
    }

    /**
     * @return hairColor
     */
    public String getHairColor()
    {
	String hairColor = CodeHelper.getCodeDescription(PDCodeTableConstants.HAIR_COLOR, this.hairColorId);
	return hairColor;
    }

    /**
     * @return String
     */
    public String getHairColorId()
    {
	return hairColorId;
    }

    /**
     * @return String
     */
    public String getHeight()
    {
	LengthBean lengthBean = new LengthBean();
	lengthBean.setLength(this.getHeightFeet(), this.getHeightInch());
	return lengthBean.getTotalInchesString();
    }

    /**
     * @return
     */
    public String getHeightFeet()
    {
	return heightFeet;
    }

    /**
     * @return
     */
    public String getHeightInch()
    {
	return heightInch;
    }

    /**
     * @return homePhone
     */
    public PhoneNumber getHomePhone()
    {
	return homePhone;
    }

    /**
     * @return faxLocation
     */
    public IName getJpOfficerName()
    {
	return jpOfficerName;
    }

    public String getJpOfficerEmail()
    {
	return this.jpOfficerEmail;
    }

    public void setJpOfficerEmail(String officerEmail)
    {

	this.jpOfficerEmail = officerEmail;
    }

    /**
     * @return Name
     */
    public IName getJuvenileName()
    {
	return juvenileName;
    }

    /**
     * @return String
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    public Integer getJuvenileNumInteger()
    {
	Integer juvenileNumInteger = null;
	if (this.juvenileNum != null && "".equals(this.juvenileNum) == false)
	{
	    juvenileNumInteger = new Integer(this.juvenileNum);
	}
	return juvenileNumInteger;
    }

    /**
     * @return LastName
     */
    public String getLastName()
    {
	return juvenileName.getLastName();
    }

    public String getLawEnforcementDepartment()
    {
	String department = null;

	DepartmentResponseEvent departmentResponse = UIJuvenileWarrantHelper.fetchDepartment(this.agencyId);
	if (departmentResponse != null)
	{
	    department = departmentResponse.getDepartmentName();
	}
	return department;
    }

    /**
     * @return String
     */
    public String getLeaOriNum()
    {
	return leaOriNum;
    }

    /**
     * @return
     */
    public String getLevel()
    {
	return level;
    }

    /**
     * @return MiddleName
     */
    public String getMiddleName()
    {
	return juvenileName.getMiddleName();
    }

    /**
     * @return String
     */
    public String getNameSuffix()
    {
	return nameSuffix;
    }

    /**
     * @return ncicNum
     */
    public String getNcicNum()
    {
	return ncicNum;
    }

    /**
     * @return newAddress
     */
    public String getNewAddress()
    {
	return newAddress;
    }

    /**
     * @return newReleaseToAssociate
     */
    public JuvenileAssociateBean getNewReleaseToAssociate()
    {
	return newReleaseToAssociate;
    }

    /**
     * @return officerAgencyId
     */
    public String getOfficerAgencyId()
    {
	return officerAgencyId;
    }

    /**
     * @return officerAgencyName
     */
    public String getOfficerAgencyName()
    {
	String returnString = "";
	if (officerAgencyId == null || officerAgencyId.trim().equals(""))
	{
	    returnString = "DEPARTMENT NAME NOT FOUND";
	}
	else
	{
	    DepartmentResponseEvent departmentResponse = UIJuvenileWarrantHelper.fetchDepartment(this.officerAgencyId);
	    if (departmentResponse != null)
	    {
		returnString = departmentResponse.getDepartmentName();
	    }
	}
	return returnString;
    }

    /**
     * @return
     */
    public String getOfficerBadgeNumber()
    {
	return officerBadgeNumber;
    }

    /**
     * @return PhoneNumber
     */
    public PhoneNumber getOfficerCellNum()
    {
	return officerCellNum;
    }

    /**
     * @return officerId
     */
    public String getOfficerId()
    {
	return officerId;
    }

    /**
     * @return officerIdType
     */
    public String getOfficerIdType()
    {
	return officerIdType;
    }

    /**
     * @return String
     */
    public String getOfficerIdTypeId()
    {
	return officerIdTypeId;
    }

    /**
     * @return
     */
    public String getOfficerLogonId()
    {
	return officerLogonId;
    }

    /**
     * @return Name
     */
    public IName getOfficerName()
    {
	return officerName;
    }

    /**
     * @return officerOID
     */
    public String getOfficerOID()
    {
	return officerOID;
    }

    /**
     * @return
     */
    public String getOfficerOtherIdNumber()
    {
	return officerOtherIdNumber;
    }

    /**
     * @return officerPager
     */
    public PhoneNumber getOfficerPager()
    {
	return officerPager;
    }

    /**
     * @return officerPhoneNum
     */
    public PhoneNumber getOfficerPhoneNum()
    {
	return officerPhoneNum;
    }

    /**
     * @return Returns the originalCautions.
     */
    public String[] getOriginalCautions()
    {
	return originalCautions;
    }

    /**
     * @return Returns the originalCharge.
     */
    public String getOriginalCharge()
    {
	return originalCharge;
    }

    /**
     * @return Returns the originalCharges.
     */
    public String[] getOriginalCharges()
    {
	return originalCharges;
    }

    /**
     * @return Returns the originalScars.
     */
    public String[] getOriginalScars()
    {
	return originalScars;
    }

    /**
     * @return the originalScarsString
     */
    public String getOriginalScarsString()
    {
	return originalScarsString;
    }

    /**
     * @return Returns the originalTattoos.
     */
    public String[] getOriginalTattoos()
    {
	return originalTattoos;
    }

    /**
     * @return the originalTattoosString
     */
    public String getOriginalTattoosString()
    {
	return originalTattoosString;
    }

    /**
     * @return PhoneNumber
     */
    public PhoneNumber getPager()
    {
	return pager;
    }

    /**
     * @return petitionNum
     */
    public String getPetitionNum()
    {
	return petitionNum;
    }

    /**
     * @return String
     */
    public PhoneNumber getPhoneNum()
    {
	return phoneNum;
    }

    /**
     * @return priorServiceAddresses
     */
    public Collection getPriorServiceAddresses()
    {
	return priorServiceAddresses;
    }

    /**
     * @return
     */
    public String getProbationOfficerOfRecordId()
    {
	return probationOfficerOfRecordId;
    }

    /**
     * @return probationOfficerOfRecordName
     */
    public String getProbationOfficerOfRecordName()
    {
	return this.probationOfficerOfRecordName;
    }

    /**
     * @return race
     */
    public String getRace()
    {
	String race = CodeHelper.getFastCodeDescription(PDCodeTableConstants.RACE, this.raceId);
	return race;
    }

    /**
     * @return String
     */
    public String getRaceId()
    {
	return raceId;
    }

    /**
     * @return String
     */
    public Date getRecallDate()
    {
	return recallDate;
    }

    /**
     * @return RecallFirstName
     */
    public String getRecallFirstName()
    {
	return recallName.getFirstName();
    }

    /**
     * @return RecallLastName
     */
    public String getRecallLastName()
    {
	return recallName.getLastName();
    }

    /**
     * @return String
     */
    public String getRecallLogonId()
    {
	return recallLogonId;
    }

    /**
     * @return RecallMiddleName
     */
    public String getRecallMiddleName()
    {
	return recallName.getMiddleName();
    }

    /**
     * @return Name
     */
    public Name getRecallName()
    {
	return recallName;
    }

    /**
     * @return recallReason
     */
    public String getRecallReason()
    {
	String recallReason = CodeHelper.getCodeDescription(PDCodeTableConstants.RECALL_REASON, this.recallReasonId);
	return recallReason;
    }

    /**
     * @return String
     */
    public String getRecallReasonId()
    {
	return recallReasonId;
    }

    /**
     * @return String
     */
    public String getReferralNum()
    {
	return referralNum;
    }

    public Integer getReferralNumInteger()
    {
	Integer referralNumInteger = null;
	if (this.referralNum != null && "".equals(this.referralNum) == false)
	{
	    referralNumInteger = new Integer(this.referralNum);
	}
	return referralNumInteger;
    }

    /**
     * @return
     */
    public String getRefreshButton()
    {
	return refreshButton;
    }

    /**
     * @return releaseAssociateNum
     */
    public String getReleaseAssociateNum()
    {
	return releaseAssociateNum;
    }

    /**
     * @return String
     */
    public String getReleaseDecision()
    {
	String releaseDecision = CodeHelper.getCodeDescription(PDCodeTableConstants.RELEASE_DECISION, this.releaseDecisionId);
	return releaseDecision;
    }

    /**
     * @return String
     */
    public Date getReleaseDecisionDate()
    {
	return releaseDecisionDate;
    }

    /**
     * @return releaseDecisionId
     */
    public String getReleaseDecisionId()
    {
	return releaseDecisionId;
    }

    /**
     * @return String
     */
    public String getReleaseDecisionLogonId()
    {
	return releaseDecisionLogonId;
    }

    /**
     * @return releaseDecisionName
     */
    public String getReleaseDecisionName()
    {
	return releaseDecisionName;
    }

    /**
     * @return releaseDecisionUserId
     */
    public String getReleaseDecisionUserId()
    {
	return releaseDecisionUserId;
    }

    /**
     * @return releaseDecisionUserName
     */
    public String getReleaseDecisionUserName()
    {
	String decisionId = this.getReleaseDecisionId();
	String decisionUserId = this.getReleaseDecisionLogonId();
	if (decisionId != null && decisionUserId != null && decisionId.length() > 0 && decisionUserId.length() > 0)
	{

	    UserResponseEvent relUser = SecurityUIHelper.getUser(this.getReleaseDecisionLogonId());
	    if (relUser != null)
	    {
		Name name = new Name(relUser.getFirstName(), relUser.getMiddleName(), relUser.getLastName());
		releaseDecisionUserName = name.getFormattedName();
		setReleaseDecisionUserName(releaseDecisionUserName);
	    }
	}
	return releaseDecisionUserName;
    }

    /**
     * @return releaseRelationshipToJuvenile
     */
    public String getReleaseRelationshipToJuvenile()
    {
	return releaseRelationshipToJuvenile;
    }

    /**
     * @return scarsAndMarksSelected
     */
    public Collection getScarsAndMarksSelected()
    {
	List codeList = new ArrayList();
	if (this.selectedScars != null && this.selectedScars.length > 0)
	{
	    if (this.scarsMarksMap != null)
	    {
		for (int i = 0; i < selectedScars.length; i++)
		{
		    Object scarsMarksObj = this.scarsMarksMap.get(selectedScars[i]);
		    codeList.add(scarsMarksObj);
		}
	    }
	    else
	    {
		GetScarsMarksTattoosCodesEvent requestEvent = (GetScarsMarksTattoosCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETSCARSMARKSTATTOOSCODES);

		requestEvent.setCodes(this.selectedScars);
		CompositeResponse response = MessageUtil.postRequest(requestEvent);
		codeList = MessageUtil.compositeToList(response, ICode.class);
	    }
	    Collections.sort(codeList);
	}
	return codeList;
    }

    /**
     * @return String
     */
    public String getSchoolCodeId()
    {
	return schoolCodeId;
    }

    /**
     * @return String
     */
    public String getSchoolDistrictId()
    {
	return schoolDistrictId;
    }

    /**
     * @return
     */
    public String getSchoolDistrictName()
    {
	return this.schoolDistrictName;
    }

    /**
     * @return Returns the schoolDistricts.
     */
    public List getSchoolDistricts()
    {
	return schoolDistricts;
    }

    /**
     * @return
     */
    public String getSchoolName()
    {
	return this.schoolName;
    }

    /**
     * @return Returns the schools.
     */
    public List getSchools()
    {
	return schools;
    }

    /**
     * @return Returns the search.
     */
    public String getSearch()
    {
	return search;
    }

    /**
     * @return String
     */
    public String getSearchResultSize()
    {
	if (this.getWarrants() != null)
	{
	    searchResultSize = Integer.toString(this.getWarrants().size());
	}
	else
	{
	    searchResultSize = "0";
	}
	return searchResultSize;
    }

    /**
     * @return String secondaryAction
     */
    public String getSecondaryAction()
    {
	return secondaryAction;
    }

    public String getSelectedAssociateId()
    {
	return selectedAssociateId;
    }

    public void setSelectedAssociateId(String associateId)
    {

	this.selectedAssociateId = associateId;
    }

    /**
     * @return String selectedAssociateAddressId
     */
    public String getSelectedAssociateAddressId()
    {
	return selectedAssociateAddressId;
    }

    /**
     * @return String selectedBadAddress
     */
    public String getSelectedBadAddress()
    {
	return selectedBadAddress;
    }

    /**
     * @return selectedCautions
     */
    public String[] getSelectedCautions()
    {
	return selectedCautions;
    }

    /**
     * @return
     */
    public String getSelectedCharge()
    {
	return selectedCharge;
    }

    /**
     * @return selectedCharges
     */
    public String[] getSelectedCharges()
    {
	return selectedCharges;
    }

    /**
     * @return String[]
     */
    public String[] getSelectedScars()
    {
	return selectedScars;
    }

    /**
     * @return String[]
     */
    public String[] getSelectedTattoos()
    {
	return selectedTattoos;
    }

    /**
     * @return Returns the selectedValue.
     */
    public String getSelectedValue()
    {
	return selectedValue;
    }

    /**
     * @return serviceAddress
     */
    public String getServiceAddress()
    {
	return serviceAddress;
    }

    /**
     * @return serviceAddressType
     */
    public String getServiceAddressType()
    {
	return serviceAddressType;
    }

    public String getServiceAddressTypeDesc()
    {
	if (serviceAddressType == null || serviceAddressType.equals(""))
	{
	    return "";

	}
	else
	{
	    return CodeHelper.getCodeDescription(PDCodeTableConstants.ADDRESS_TYPE, serviceAddressType);
	}
    }

    /**
     * @return serviceAttemptComments
     */
    public String getServiceAttemptComments()
    {
	return serviceAttemptComments;
    }

    /**
     * @return String
     */
    public String getServiceNumber()
    {
	return serviceNumber;
    }

    /**
     * @return serviceReturnGeneratedStatus
     */
    public String getServiceReturnGeneratedStatus()
    {
	return serviceReturnGeneratedStatus;
    }

    /**
     * @return serviceReturnGeneratedStatusId
     */
    public String getServiceReturnGeneratedStatusId()
    {
	return serviceReturnGeneratedStatusId;
    }

    /**
     * @return serviceReturnSignatureStatus
     */
    public String getServiceReturnSignatureStatus()
    {
	return serviceReturnSignatureStatus;
    }

    /**
     * @return serviceReturnSignatureStatusId
     */
    public String getServiceReturnSignatureStatusId()
    {
	return serviceReturnSignatureStatusId;
    }

    /**
     * @return
     */
    public Collection getServices()
    {
	return services;
    }

    /**
     * @return String
     */
    public String getServiceStatus()
    {
	return serviceStatus;
    }

    /**
     * @return String
     */
    public String getServiceStatusDescription()
    {
	String serviceStatusDescription = CodeHelper.getCodeDescription(PDCodeTableConstants.SERVICE_STATUS, this.serviceStatus);
	return serviceStatusDescription;
    }

    /**
     * @return sex
     */
    public String getSex()
    {
	String sex = CodeHelper.getCodeDescription(PDCodeTableConstants.SEX, this.sexId);
	return sex;
    }

    /**
     * @return String
     */
    public String getSexId()
    {
	return sexId;
    }

    /**
     * @return String
     */
    public String getSid()
    {
	return sid;
    }

    /**
     * @return String
     */
    public String getSignatureCommand()
    {
	// TODO Confirm which signed status codes to use.
	// CodeHelper.warrantSignedStatusCodes(false);
	String warrantSignedStatus = CodeHelper.getCodeDescriptionByCode(CodeHelper.getSignatureCommands(false), this.signatureCommandId);
	return warrantSignedStatus;
    }

    /**
     * @return String
     */
    public String getSignatureCommandId()
    {
	return signatureCommandId;
    }

    /**
     * @return String
     */
    public String getSSN1()
    {
	return ssn.getSSN1();
    }

    /**
     * @return String
     */
    public String getSSN2()
    {
	return ssn.getSSN2();
    }

    /**
     * @return String
     */
    public String getSSN3()
    {
	return ssn.getSSN3();
    }

    /**
     * @return state
     */
    public String getState()
    {
	return state;
    }

    /**
     * @return String
     */
    public String getStateIdNumber()
    {
	return stateIdNumber;
    }

    /**
     * @return streetName
     */
    public String getStreetName()
    {
	return streetName;
    }

    /**
     * @return streetNum
     */
    public String getStreetNum()
    {
	return streetNum;
    }

    /**
     * @return streetType
     */
    public String getStreetType()
    {
	return streetType;
    }

    /**
     * @return summaryOfFacts
     */
    public Collection getSummaryOfFacts()
    {
	return summaryOfFacts;
    }

    /**
     * @return summOfFactsDisplaySize
     */
    public String getSummOfFactsDisplaySize()
    {
	return summOfFactsDisplaySize;
    }

    /**
     * @return tattoosSelected
     */
    public Collection getTattoosSelected()
    {
	List codeList = new ArrayList();
	if (this.selectedTattoos != null && this.selectedTattoos.length > 0)
	{
	    if (this.tattoosMap != null)
	    {
		for (int i = 0; i < selectedTattoos.length; i++)
		{
		    Object tattooObj = this.tattoosMap.get(selectedTattoos[i]);
		    codeList.add(tattooObj);
		}
	    }
	    else
	    {
		GetScarsMarksTattoosCodesEvent requestEvent = (GetScarsMarksTattoosCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETSCARSMARKSTATTOOSCODES);
		requestEvent.setCodes(this.selectedTattoos);
		CompositeResponse response = MessageUtil.postRequest(requestEvent);
		codeList = (List) MessageUtil.compositeToCollection(response, ICode.class);
	    }
	    Collections.sort(codeList);
	}
	return codeList;
    }

    /**
     * @return String
     */
    public String getTitle()
    {
	return title;
    }

    /**
     * @return String
     */
    public String getTransactionNum()
    {
	return transactionNum;
    }

    /**
     * @return String
     */
    public Date getTransferCustodyDate()
    {
	return transferCustodyDate;
    }

    /**
     * @return
     */
    public String getTransferCustodyDateString()
    {
	return transferCustodyDateString;
    }

    /**
     * @return
     */
    public String getTransferCustodyTimeString()
    {
	return transferCustodyTimeString;
    }

    /**
     * @return transferLocation
     */
    public String getTransferLocation()
    {
	String transferLocation = CodeHelper.getCodeDescription(PDCodeTableConstants.TRANSFER_LOCATION, this.transferLocationId);
	return transferLocation;
    }

    /**
     * @return vtransferLocationId
     */
    public String getTransferLocationId()
    {
	return transferLocationId;
    }

    /**
     * @return
     */
    public String getTransferOfficerDepartmentId()
    {
	return transferOfficerDepartmentId;
    }

    /**
     * @return transferOfficerAgency
     */
    public String getTransferOfficerDepartmentName()
    {
	String returnString = "";
	if (transferOfficerDepartmentId == null || transferOfficerDepartmentId.trim().equals(""))
	{
	    returnString = "DEPARTMENT NAME NOT FOUND";
	}
	else
	{
	    DepartmentResponseEvent departmentResponse = UIJuvenileWarrantHelper.fetchDepartment(this.transferOfficerDepartmentId);
	    if (departmentResponse != null)
	    {
		returnString = departmentResponse.getDepartmentName();
	    }
	    else
	    {
		returnString = "DEPARTMENT NAME NOT FOUND";
	    }
	}
	return returnString;
    }

    /**
     * @return transferOfficerId
     */
    public String getTransferOfficerId()
    {
	return transferOfficerId;
    }

    /**
     * @return transferOfficerIdType
     */
    public String getTransferOfficerIdType()
    {
	return transferOfficerIdType;
    }

    /**
     * @return
     */
    public String getTransferOfficerLogonId()
    {
	return transferOfficerLogonId;
    }

    /**
     * @return String
     */
    public String getUnsendNotSignedReason()
    {
	return this.unsendNotSignedReason;
    }

    /**
     * @return String
     */
    public String getUserId()
    {
	return userId;
    }

    /**
     * @return String
     */
    public Date getWarrantAcknowledgementDate()
    {
	return warrantAcknowledgementDate;
    }

    /**
     * @return warrantAcknowledgeStatus
     */
    public String getWarrantAcknowledgeStatus()
    {
	String warrantAcknowledgeStatus = CodeHelper.getCodeDescription(PDCodeTableConstants.WARRANT_ACKNOWLEDGE_STATUS, this.warrantAcknowledgeStatusId);
	return warrantAcknowledgeStatus;
    }

    /**
     * @return String
     */
    public String getWarrantAcknowledgeStatusId()
    {
	return warrantAcknowledgeStatusId;
    }

    /**
     * @return String
     */
    public Date getWarrantActivationDate()
    {
	return warrantActivationDate;
    }

    /**
     * Used for the Reporting functionality
     * 
     * @return string
     */
    public String getWarrantActivationDateforReport()
    {
	// TODO Put formats in naming
	return DateUtil.dateToString(this.warrantActivationDate, "MMMM dd, yyyy");
    }

    /**
     * @return String
     */
    public String getWarrantActivationStatus()
    {
	String warrantActivationStatus = CodeHelper.getCodeDescription(PDCodeTableConstants.WARRANT_ACTIVATION_STATUS, this.warrantActivationStatusId);
	return warrantActivationStatus;
    }

    /**
     * @return String
     */
    public String getWarrantActivationStatusId()
    {
	return warrantActivationStatusId;
    }

    /**
     * @return Name
     */
    public Name getWarrantActivationStatusName()
    {
	return warrantActivationStatusName;
    }

    /**
     * @return String
     */
    public String getWarrantNum()
    {
	return warrantNum;
    }

    /**
     * @return warrantOriginatorAgencyId
     */
    public String getWarrantOriginatorAgencyId()
    {
	return warrantOriginatorAgencyId;
    }

    /**
     * @return warrantOriginatorAgencyName
     */
    public String getWarrantOriginatorAgencyName()
    {
	return warrantOriginatorAgencyName;
    }

    /**
     * @return String
     */
    public String getWarrantOriginatorCourt()
    {
	String warrantOriginatorCourt = null;
	if (this.courtId != null && courtId.equals("") == false)
	{
	    GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
	    courtEvent.setCourt(this.courtId);
	    CompositeResponse response = MessageUtil.postRequest(courtEvent);
	    JuvenileCourtResponseEvent courtResponse = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(response, JuvenileCourtResponseEvent.class);
	    if (courtResponse != null)
	    {
		warrantOriginatorCourt = courtResponse.getDescription();
	    }
	}
	return warrantOriginatorCourt;
    }

    /**
     * @return String
     */
    public String getWarrantOriginatorId()
    {
	return warrantOriginatorId;
    }

    /**
     * @return warrantOriginatorJudge
     */
    public String getWarrantOriginatorJudge()
    {
	return this.warrantOriginatorJudge;
    }

    /**
     * @return warrantOriginatorName
     */
    public String getWarrantOriginatorName()
    {
	if (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(this.warrantTypeId))
	{
	    this.warrantOriginatorName = this.warrantOriginatorJudge;
	}
	return warrantOriginatorName;
    }

    /**
     * @return warrants
     */
    public Collection getWarrants()
    {
	return warrants;
    }

    /**
     * @return Returns the warrantsSearchResults.
     */
    public Collection getWarrantsSearchResults()
    {
	return warrantsSearchResults;
    }

    /**
     * @return Returns the warrantsSearchResultsSize.
     */
    public String getWarrantsSearchResultsSize()
    {
	if (this.getWarrantsSearchResults() != null)
	{
	    warrantsSearchResultsSize = Integer.toString(this.getWarrantsSearchResults().size());
	}
	else
	{
	    warrantsSearchResultsSize = "0";
	}
	return warrantsSearchResultsSize;
    }

    /**
     * Used for the Reporting functionality
     * 
     * @return string
     */
    public String getWarrantServiceDateforReport()
    {
	// TODO Put formats in naming
	return DateUtil.dateToString(this.currentServiceDate, "MMMM dd, yyyy");
    }

    /**
     * @return String
     */
    public Name getWarrantSignatureStatusName()
    {
	return warrantSignatureStatusName;
    }

    /**
     * @return String
     */
    public String getWarrantSignedStatus()
    {
	// TODO Confirm which signed status codes to use.
	// CodeHelper.warrantSignedStatusCodes(false);
	String warrantSignedStatus = CodeHelper.getCodeDescriptionByCode(CodeHelper.getSignatureOptions(false), this.warrantSignedStatusId);
	return warrantSignedStatus;
    }

    /**
     * @return String
     */
    public String getWarrantSignedStatusId()
    {
	return warrantSignedStatusId;
    }

    /**
     * @return String
     */
    public String getWarrantStatus()
    {
	String warrantStatus = CodeHelper.getCodeDescription(PDCodeTableConstants.WARRANT_STATUS, this.warrantStatusId);
	return warrantStatus;
    }

    /**
     * @return String
     */
    public String getWarrantStatusId()
    {
	return warrantStatusId;
    }

    /**
     * @return warrantType
     */
    public String getWarrantType()
    {
	String warrantType = CodeHelper.getCodeDescription(PDCodeTableConstants.WARRANT_TYPE, this.warrantTypeId);
	return warrantType;
    }

    /**
     * @return warrantTypeId
     */
    public String getWarrantTypeId()
    {
	return warrantTypeId;
    }

    /**
     * @return String
     */
    public String getWarrantTypeUI()
    {
	return warrantTypeUI;
    }

    /**
     * @return String
     */
    public String getWeight()
    {
	return weight;
    }

    /**
     * @return PhoneNumber
     */
    public PhoneNumber getWorkPhone()
    {
	return workPhone;
    }

    /**
     * @return getZipcode
     */
    public String getZipCode()
    {
	return zipCode;
    }

    /**
     * @return zipCode2
     */
    public String getZipCode2()
    {
	return zipCode2;
    }

    // TODO Remove once the charge defect has been fixed.
    public void initCharges()
    {
	if (charges != null)
	{
	    int len = this.charges.size();
	    for (int i = 0; i < len; i++)
	    {
		PetitionResponseEvent chargeEvent = (PetitionResponseEvent) this.charges.get(i);
		this.setChargeId(chargeEvent.getChargeId());
		this.setPetitionNum(chargeEvent.getPetitionNum());
		this.setChargeDescription(chargeEvent.getOffense());
		this.setChargeCodeId(chargeEvent.getOffenseCodeId());
		this.setCourtId(chargeEvent.getCourtId());
		this.setDegree(chargeEvent.getDegree());
		this.setLevel(chargeEvent.getLevel());
	    }
	}
    }

    /**
     * @return
     */
    public void initCurrentServiceDate()
    {
	String dateString = this.getCurrentServiceDateString();
	//      String hour = this.getCurrentServiceHour();
	//      String minute = this.getCurrentServiceMinute();
	//      String currentServiceTimestamp = dateString + " " + hour + ":" +
	// minute;
	String time = this.getCurrentServiceTime();

	String currentServiceTimestamp = dateString + " " + time;

	Date date = DateUtil.stringToDate(currentServiceTimestamp, UIConstants.DATETIME24LOOSE_FMT_1);
	this.setCurrentServiceDate(date);
    }

    public void initTransferCustodyDate()
    {
	String dateString = this.getTransferCustodyDateString();
	String timeString = this.getTransferCustodyTimeString();

	String timestamp = dateString + " " + timeString;
	Date date = DateUtil.stringToDate(timestamp, UIConstants.DATETIME24_FMT_1);
	this.setTransferCustodyDate(date);
    }

    /**
     * @return
     */
    public boolean isDetails()
    {
	return details;
    }

    /**
     * @return boolean
     */
    public boolean isOnlyCharge()
    {
	return onlyCharge;
    }

    public boolean isSuccessfulWarrant()
    {
	return successfulWarrant;
    }

    /**
     * @param addresses
     */
    public void populateRealeaseAssociateAddressValues(List addresses)
    {
	// TODO Move to an assembler/director
	int len = addresses.size();
	for (int i = 0; i < len; i++)
	{
	    newReleaseToAssociate.insertAddress((JuvenileAssociateAddressResponseEvent) addresses.get(i));
	}
    }

    /**
     * @param aRequest
     */
    public void populateRealeaseAssociateAddressValuesFromRequest(HttpServletRequest aRequest)
    {
	// TODO Move to an assembler
	String[] streetNums = aRequest.getParameterValues("streetNum");
	String[] streetNames = aRequest.getParameterValues("streetName");
	String[] streetTypeIds = aRequest.getParameterValues("streetTypeId");
	String[] aptNums = aRequest.getParameterValues("aptNum");
	String[] cities = aRequest.getParameterValues("city");
	//		String[] stateIds = aRequest.getParameterValues("stateId");
	String[] temp0 = { "" };
	String[] temp1 = { "" };

	if (aRequest.getParameterValues("address[0].stateId") != null)
	{
	    temp0 = aRequest.getParameterValues("address[0].stateId");
	}
	if (aRequest.getParameterValues("address[1].stateId") != null)
	{
	    temp1 = aRequest.getParameterValues("address[1].stateId");
	}
	//		String[] stateIds =
	// {aRequest.getParameterValues("address[0].stateId").toString(),aRequest.getParameterValues("address[1].stateId").toString()
	// };
	String[] stateIds = { temp0[0], temp1[0] };
	String[] zips = aRequest.getParameterValues("zipCode");
	String[] additionalZips = aRequest.getParameterValues("additionalZipCode");
	String[] addressTypeIds = aRequest.getParameterValues("addressTypeId");
	temp0[0] = "";
	temp1[0] = "";
	if (aRequest.getParameterValues("address[0].countyId") != null)
	{
	    temp0 = aRequest.getParameterValues("address[0].countyId");
	}
	if (aRequest.getParameterValues("address[1].countyId") != null)
	{
	    temp1 = aRequest.getParameterValues("address[1].countyId");
	}
	String[] countyIds = { temp0[0], temp1[0] };
	int index = 0;

	java.util.Iterator addrIte = newReleaseToAssociate.getAssociateAddresses().iterator();
	JuvenileAssociateAddressResponseEvent evt;
	while (addrIte.hasNext())
	{

	    evt = (JuvenileAssociateAddressResponseEvent) addrIte.next();
	    if (streetNames[index] != null && !(streetNames[index].equals("")))
	    {

		evt.setStreetNum(streetNums[index]);
		evt.setStreetName(streetNames[index]);
		evt.setStreetTypeId(streetTypeIds[index]);
		if (streetTypeIds[index] != null && !(streetTypeIds[index].equals("")))
		{
		    String streeType = CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_TYPE, streetTypeIds[index]);
		    evt.setStreetType(streeType);
		}
		evt.setAptNum(aptNums[index]);
		evt.setCity(cities[index]);
		evt.setStateId(stateIds[index]);
		if (stateIds[index] != null && !(stateIds[index].equals("")))
		{
		    String state = CodeHelper.getCodeDescription(PDCodeTableConstants.STATE_ABBR, stateIds[index]);
		    evt.setState(state);
		}
		evt.setZipCode(zips[index]);
		evt.setAdditionalZipCode(additionalZips[index]);
		evt.setAddressTypeId(addressTypeIds[index]);
		if (addressTypeIds[index] != null && !(addressTypeIds[index].equals("")))
		{
		    String addressType = CodeHelper.getCodeDescription(PDCodeTableConstants.ADDRESS_TYPE, addressTypeIds[index]);
		    evt.setAddressType(addressType);
		}
		evt.setCountyId(countyIds[index]);
		evt.setCounty("");
		if (countyIds[index] != null && !(countyIds[index].equals("")))
		{
		    String county = CodeHelper.getCodeDescription(PDCodeTableConstants.COUNTY, countyIds[index]);
		    evt.setCounty(county);
		}
		index++;
	    }
	    else
	    {
		addrIte.remove();
	    }

	}

    }

    /**
     *  
     */
    public void populateRealeaseAssociateCodeDescriptionsFromIds()
    {
	String sex = CodeHelper.getCodeDescription(PDCodeTableConstants.SEX, newReleaseToAssociate.getSexId());
	newReleaseToAssociate.setSex(sex);

	String race = CodeHelper.getCodeDescription(PDCodeTableConstants.RACE, newReleaseToAssociate.getRaceId());
	newReleaseToAssociate.setRace(race);

	String relationshipId = newReleaseToAssociate.getRelationshipToJuvenileId();
	String relationship = CodeHelper.getCodeDescription(PDCodeTableConstants.RELATIONSHIP_JUVENILE, relationshipId);
	newReleaseToAssociate.setRelationshipToJuvenile(relationship);

	String dlStateId = newReleaseToAssociate.getDlStateId();
	String dlState = CodeHelper.getCodeDescription(PDCodeTableConstants.STATE_ABBR, dlStateId);
	newReleaseToAssociate.setDlState(dlState);
    }

    /**
     * @param assResponseEvent
     */
    public void populateReleaseAssociateValues(JuvenileAssociateResponseEvent assResponseEvent)
    {
	newReleaseToAssociate.populateFromEventAttributes(assResponseEvent);
    }

    public Collection getScarsMarksCodes()
    {
	this.refreshCriminalCodes();
	List codes = new ArrayList(this.scarsMarksMap.values());
	Collections.sort(codes);
	return codes;
    }

    public Collection getTattoosCodes()
    {
	this.refreshCriminalCodes();
	List codes = new ArrayList(this.tattoosMap.values());
	Collections.sort(codes);
	return codes;
    }

    private void refreshCriminalCodes()
    {
	if (this.criminalMap == null)
	{
	    this.criminalMap = new Hashtable();
	    List codes = CodeHelper.getCriminalCodes("", true);
	    this.scarsMarksMap = new Hashtable();
	    this.tattoosMap = new Hashtable();
	    int len = codes.size();
	    for (int i = 0; i < len; i++)
	    {
		ScarsMarksTattoosCodeResponseEvent code = (ScarsMarksTattoosCodeResponseEvent) codes.get(i);
		if (PDCodeTableConstants.TATTOO_CATEGORY.equals(code.getCategory()))
		{
		    this.tattoosMap.put(code.getCode(), code);
		}
		else
		{
		    this.scarsMarksMap.put(code.getCode(), code);
		}
	    }
	}
    }

    /**
     *  
     */
    public void refreshSchool()
    {
	if (this.districtMap == null)
	{
	    this.schoolDistricts = new ArrayList(30);
	    this.districtMap = new Hashtable();
	    UIJuvenileWarrantHelper.fetchSchoolDistrictMap(this.schoolDistricts, this.districtMap);
	}

	if (this.schoolDistrictId != null)
	{
	    this.schools = (List) this.districtMap.get(this.schoolDistrictId);
	}
    }

    public void refreshSchoolDescriptions()
    {
	if (this.schoolDistrictId != null && !this.schoolDistrictId.equals(""))
	{
	    List schools = (List) this.districtMap.get(this.schoolDistrictId);

	    if (this.schoolCodeId == null && this.schools != null && this.schools.size() > 0)
	    {
		JuvenileSchoolDistrictCodeResponseEvent school = (JuvenileSchoolDistrictCodeResponseEvent) this.schools.get(0);
		if (school != null)
		{
		    this.setSchoolDistrictName(school.getDistrictDescription());
		}
	    }
	    else
		if (this.schoolCodeId != null && this.schools.size() > 0)
		{
		    int len = schools.size();
		    boolean found = false;
		    for (int i = 0; i < len && found == false; i++)
		    {
			JuvenileSchoolDistrictCodeResponseEvent school = (JuvenileSchoolDistrictCodeResponseEvent) schools.get(i);
			if (school.getSchoolCode().equals(this.schoolCodeId))
			{
			    found = true;
			    this.setSchoolDistrictName(school.getDistrictDescription());
			    this.setSchoolName(school.getSchoolDescription());
			}
		    }
		}
	}
	else
	{
	    this.setSchoolDistrictName("");
	    this.setSchoolName("");
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest aRequest)
    {
	Object obj = aRequest.getParameter("cautionCheckboxClear");
	if (obj != null)
	{
	    this.clearCautionCheckBoxes();
	}
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
	action = string;
    }

    /**
     * @param string
     */
    public void setAddressId(String string)
    {
	addressId = string;
    }

    /**
     * @param collection
     */
    public void setAddressTopics(Collection collection)
    {
	addressTopics = collection;
    }

    /**
     * @param string
     */
    public void setAddressType(String string)
    {
	addressType = string;
    }

    /**
     * @param string
     */
    public void setAffidavitStatement(String string)
    {
	affidavitStatement = string;
    }

    /**
     * @param string
     */
    public void setAgencyId(String string)
    {
	agencyId = string;
    }

    /**
     * @param name
     */
    public void setAliasName(String name)
    {
	aliasName = name;
    }

    /**
     * @param string
     */
    public void setApartmentNum(String string)
    {
	apartmentNum = string;
    }

    /**
     * @param string
     */
    public void setArrestAddress(String string)
    {
	arrestAddress = string;
    }

    /**
     * @param date
     */
    private void setArrestDate(Date date)
    {
	this.arrestDate = date;
    }

    /**
     * @param string
     */
    public void setAssociateAddress(String string)
    {
	associateAddress = string;
    }

    /**
     * @param string
     */
    public void setAssociateFirstName(String associateFirstName)
    {
	associateName.setFirstName(associateFirstName.trim());
    }

    /**
     * @param string
     */
    public void setAssociateId(String string)
    {
	associateId = string;
    }

    /**
     * @param string
     */
    public void setAssociateLastName(String associateLastName)
    {
	associateName.setLastName(associateLastName.trim());
    }

    /**
     * @param name
     */
    public void setAssociateName(Name name)
    {
	associateName = name;
    }

    /**
     * @param collection
     */
    public void setAssociates(List collection)
    {
	associates = collection;
    }

    /**
     * @param collection
     */
    public void setAssociateServiceAddresses(Collection collection)
    {
	associateServiceAddresses = collection;
    }

    /**
     * @param b
     */
    public void setAssociateUpdatable(boolean b)
    {
	associateUpdatable = b;
    }

    /**
     * @param string
     */
    public void setAsstDistrictAttorneyName(String string)
    {
	asstDistrictAttorneyName = string;
    }

    /**
     * @param aBackForwardString
     *            The backForwardString to set.
     */
    public void setBackForward(String aBackForwardString)
    {
	this.backForwardString = aBackForwardString;
    }

    /**
     * @param string
     */
    public void setBackToWarrantUrl(String string)
    {
	// TODO This was Raj's idea for fix last minute crisis before MJW
	// delivery to production
	// make change ASAP
	backToWarrantUrl = string;
    }

    /**
     * @param string
     */
    public void setBuildId(String string)
    {
	buildId = string;
    }

    /**
     * @param string
     */
    public void setCautionComments(String string)
    {
	cautionComments = string;
    }

    /**
     * @param number
     */
    public void setCellPhone(PhoneNumber number)
    {
	cellPhone = number;
    }

    /**
     * @param string
     */
    public void setChargeCode(String string)
    {
	chargeCode = string;
    }

    /**
     * @param string
     */
    public void setChargeCodeId(String string)
    {
	chargeCodeId = string;
    }

    /**
     * @param string
     */
    public void setChargeCourt(String string)
    {
	chargeCourt = string;
    }

    /**
     * @param string
     */
    public void setChargeDescription(String string)
    {
	chargeDescription = string;
    }

    /**
     * @param string
     */
    public void setChargeId(String string)
    {
	chargeId = string;
    }

    /**
     * @param string
     */
    public void setChargeNcicNum(String string)
    {
	chargeNcicNum = string;
    }

    /**
     * @param collection
     */
    public void setCharges(List collection)
    {
	charges = collection;
    }

    /**
     * @param string
     */
    public void setChargeSelectedId(String string)
    {
    }

    /**
     * @param string
     */
    public void setChargeSeqNum(String string)
    {
	chargeSeqNum = string;
    }

    /**
     * @param collection
     */
    public void setChargesSelected(List collection)
    {
	chargesSelected = collection;
    }

    /**
     * @param dataMap
     */
    public void setChargesSelected(Map dataMap)
    {
	Collection charges = (Collection) dataMap.get(PDJuvenileWarrantConstants.CHARGE_EVENT_TOPIC);
	charges = MessageUtil.processEmptyCollection(charges);
	this.setChargesSelected((List) charges);

    }

    /**
     * @param string
     */
    public void setCity(String string)
    {
	city = string;
    }

    /**
     * @param string
     */
    public void setComments(String string)
    {
	comments = string;
    }

    /**
     * @param string
     */
    public void setComplexionId(String string)
    {
	complexionId = string;
    }

    /**
     * @param string
     */
    public void setCostAirFare(String string)
    {
	costAirFare = string;
    }

    /**
     * @param string
     */
    public void setCostMileage(String string)
    {
	costMileage = string;
    }

    /**
     * @param string
     */
    public void setCostPerDiem(String string)
    {
	costPerDiem = string;
    }

    /**
     * @param string
     */
    public void setCounty(String string)
    {
	county = string;
    }

    /**
     * @param string
     */
    public void setCourtId(String string)
    {
	courtId = string;
    }

    /**
     * @param string
     */
    public void setCurrentServiceAddress(String string)
    {
	currentServiceAddress = string;
    }

    /**
     * @param string
     */
    public void setCurrentServiceAttemptComments(String string)
    {
	currentServiceAttemptComments = string;
    }

    /**
     * @param string
     */
    public void setCurrentServiceBadAddress(String string)
    {
	currentServiceBadAddress = string;
    }

    /**
     * @param string
     */
    public void setCurrentServiceDate(Date date)
    {
	currentServiceDate = date;
    }

    /**
     * @param string
     */
    public void setCurrentServiceDateString(String string)
    {
	currentServiceDateString = string;
    }

    /**
     * @param string
     */
    public void setCurrentServiceHour(String string)
    {
	currentServiceHour = string;
    }

    /**
     * @param string
     */
    public void setCurrentServiceMinute(String string)
    {
	currentServiceMinute = string;
    }

    /**
     * @param currentServiceTime
     *            The currentServiceTime to set.
     */
    public void setCurrentServiceTime(String currentServiceTime)
    {
	this.currentServiceTime = currentServiceTime;
    }

    /**
     * @param string
     */
    public void setCurrentWarrantServiceStatus(String string)
    {
	currentWarrantServiceStatus = string;
    }

    /**
     * @param string
     */
    public void setDaLogNum(String string)
    {
	daLogNum = string;
    }

    /**
     * @param string
     */
    public void setDateOfBirth(Date date)
    {
	dateOfBirth = date;
    }

    /**
     * @param string
     */
    public void setDateOfBirthSource(String dateOfBirthSource)
    {
	this.dateOfBirthSource = dateOfBirthSource;
    }

    /**
     * @param string
     */
    public void setDateOfIssue(Date date)
    {
	dateOfIssue = date;
    }

    /**
     * @param string
     */
    public void setDecisionDate(Date date)
    {
	decisionDate = date;
    }

    /**
     * @param string
     */
    public void setDegree(String string)
    {
	degree = string;
    }

    /**
     * @param departments
     *            The departments to set.
     */
    public void setDepartments(Collection departments)
    {
	this.departments = departments;
    }

    /**
     * @param departmentSize
     *            The departmentSize to set.
     */
    public void setDepartmentSize(int departmentSize)
    {
	this.departmentSize = departmentSize;
    }

    /**
     * @param b
     */
    public void setDetails(boolean b)
    {
	details = b;
    }

    /**
     * @param string
     */
    public void setEmail(String string)
    {
	email = string;
    }

    /**
     * @param number
     */
    public void setExecutorCellNum(IPhoneNumber number)
    {
	executorCellNum = number;
    }

    /**
     * @param name
     */
    public void setExecutorDepartmentName(String name)
    {
	executorDepartmentName = name;
    }

    /**
     * @param string
     */
    public void setExecutorEmail(String string)
    {
	executorEmail = string;
    }

    /**
     * @param string
     */
    public void setExecutorId(String string)
    {
	executorId = string;
    }

    /**
     * @param string
     */
    public void setExecutorIdType(String string)
    {
	executorIdType = string;
    }

    /**
     * @param name
     */
    public void setExecutorName(IName name)
    {
	executorName = name;
    }

    /**
     * @param string
     */
    public void setExecutorOriginatingAgencyId(String string)
    {
	executorOriginatingAgencyId = string;
    }

    /**
     * @param number
     */
    public void setExecutorPager(IPhoneNumber number)
    {
	executorPager = number;
    }

    /**
     * @param number
     */
    public void setExecutorPhoneNum(IPhoneNumber number)
    {
	executorPhoneNum = number;
    }

    /**
     * @param officerResponseEvent
     */
    public void setExecutorProperties(OfficerProfileResponseEvent officerResponseEvent)
    {
	this.setExecutorName(new Name(officerResponseEvent.getFirstName(), officerResponseEvent.getMiddleName(), officerResponseEvent.getLastName()));

	this.setExecutorDepartmentName(officerResponseEvent.getDepartmentName());
	this.setExecutorPhoneNum(new PhoneNumber(officerResponseEvent.getWorkPhone()));
	this.setExecutorCellNum(new PhoneNumber(officerResponseEvent.getCellPhone()));
	this.setExecutorPager(new PhoneNumber(officerResponseEvent.getPager()));
	this.setExecutorEmail(officerResponseEvent.getEmail());

	/* convert badgeNum or otherIdNum to officerId and set officerIdType */
	if (officerResponseEvent.getBadgeNum() != null && !officerResponseEvent.getBadgeNum().equals(""))
	{
	    this.setExecutorId(officerResponseEvent.getBadgeNum());
	    this.setExecutorIdType("BADGE NUMBER");
	}
	else
	{
	    this.setExecutorId(officerResponseEvent.getOtherIdNum());
	    this.setExecutorIdType("OTHER ID NUMBER");
	}
    }

    /**
     * @param string
     */
    public void setEyeColorId(String string)
    {
	eyeColorId = string;
    }

    /**
     * @param string
     */
    public void setFaxLocation(String string)
    {
	faxLocation = string;
    }

    /**
     * @param number
     */
    public void setFaxNumber(PhoneNumber number)
    {
	faxNumber = number;
    }

    /**
     * @param string
     */
    public void setFbiNum(String string)
    {
	fbiNum = string;
    }

    /**
     * @param string
     */
    public void setFileStampDate(Date date)
    {
	fileStampDate = date;
    }

    /**
     * @param string
     */
    public void setFileStampFirstName(String string)
    {
	fileStampName.setFirstName(string);
    }

    /**
     * @param string
     */
    public void setFileStampLastName(String string)
    {
	fileStampName.setLastName(string);
    }

    /**
     * @param string
     */
    public void setFileStampLogonId(String string)
    {
	fileStampLogonId = string;
    }

    /**
     * @param string
     */
    public void setFileStampMiddleName(String string)
    {
	fileStampName.setMiddleName(string);
    }

    /**
     * @param name
     */
    public void setFileStampName(Name name)
    {
	fileStampName = name;
    }

    /**
     * @param string
     */
    public void setFirstName(String string)
    {
	juvenileName.setFirstName(string.trim());
    }

    /**
     * @param string
     */
    public void setHairColorId(String string)
    {
	hairColorId = string;
    }

    /**
     * @param string
     */
    public void setHeightFeet(String string)
    {
	if (string == null || string.trim().equals(""))
	{
	    heightFeet = "";
	}
	else
	{
	    heightFeet = string;
	}
    }

    /**
     * @param string
     */
    public void setHeightInch(String string)
    {
	if (string == null || string.trim().equals(""))
	{
	    heightInch = "";
	}
	else
	{
	    heightInch = string;
	}
    }

    /**
     * @param number
     */
    public void setHomePhone(PhoneNumber number)
    {
	homePhone = number;
    }

    /**
     * @param dataMap
     */
    public boolean setJJSProperties(CompositeResponse response, ActionErrors errors)
    {
	JuvenileWarrantFormDirector director = new JuvenileWarrantFormDirector(this);
	director.setJJSProperties(response, errors);
	return director.hasUnrecoverableError();
    }

    /**
     * @param jotData
     * @return unrecoverableError
     */
    public boolean setJOTProperties(CompositeResponse jotData, ActionErrors errors)
    {
	JuvenileWarrantFormDirector director = new JuvenileWarrantFormDirector(this);
	director.setJOTProperties(jotData, errors);
	return director.hasUnrecoverableError();
    }

    /**
     * @param name
     */
    public void setJpOfficerName(Name name)
    {
	jpOfficerName = name;
    }

    /**
     * @param string
     */
    public void setJuvenileName(IName name)
    {
	juvenileName = name;
    }

    public void setJuvenileNum(Integer juvenileNum)
    {
	if (juvenileNum == null)
	{
	    this.juvenileNum = null;
	}
	else
	{
	    this.juvenileNum = juvenileNum.toString();
	}
    }

    /**
     * @param string
     */
    public void setJuvenileNum(String string)
    {
	this.juvenileNum = string;
    }

    /**
     * @param string
     */
    public void setLastName(String string)
    {
	juvenileName.setLastName(string.trim());
    }

    /**
     * @param string
     */
    public void setLeaOriNum(String string)
    {
	leaOriNum = string;
    }

    /**
     * @param string
     */
    public void setLevel(String string)
    {
	level = string;
    }

    /**
     * @param string
     */
    public void setMiddleName(String string)
    {
	juvenileName.setMiddleName(string);
    }

    /**
     * @param string
     */
    public void setNameSuffix(String string)
    {
	nameSuffix = string;
    }

    /**
     * @param string
     */
    public void setNcicNum(String string)
    {
	ncicNum = string;
    }

    /**
     * @param string
     */
    public void setNewAddress(String string)
    {
	newAddress = string;
    }

    /**
     * @param bean
     */
    public void setNewReleaseToAssociate(JuvenileAssociateBean bean)
    {
	newReleaseToAssociate = bean;
    }

    /**
     * @param string
     */
    public void setOfficerAgencyId(String string)
    {
	officerAgencyId = string;
    }

    /**
     * @param string
     */
    public void setOfficerAgencyName(String string)
    {
    }

    /**
     * @param string
     */
    public void setOfficerBadgeNumber(String string)
    {
	officerBadgeNumber = string;
    }

    /**
     * @param number
     */
    public void setOfficerCellNum(PhoneNumber number)
    {
	officerCellNum = number;
    }

    /**
     * @param string
     */
    public void setOfficerId(String string)
    {
	officerId = string;
    }

    /**
     * @param string
     */
    public void setOfficerIdType(String string)
    {
	officerIdType = string;
    }

    /**
     * @param string
     */
    public void setOfficerIdTypeId(String string)
    {
	officerIdTypeId = string;
    }

    /**
     * @param string
     */
    public void setOfficerLogonId(String string)
    {
	officerLogonId = string;
    }

    /**
     * @param string
     */
    public void setOfficerName(IName name)
    {
	officerName = name;
    }

    /**
     * @param string
     */
    public void setOfficerOID(String string)
    {
	officerOID = string;
    }

    /**
     * @param string
     */
    public void setOfficerOtherIdNumber(String string)
    {
	officerOtherIdNumber = string;
    }

    /**
     * @param number
     */
    public void setOfficerPager(PhoneNumber number)
    {
	officerPager = number;
    }

    /**
     * @param number
     */
    public void setOfficerPhoneNum(PhoneNumber number)
    {
	officerPhoneNum = number;
    }

    /**
     * @param officerResponseEvent
     */
    public void setOfficerProperties(OfficerProfileResponseEvent ore)
    {
	if (ore != null)
	{
	    this.setOfficerName(new Name(ore.getFirstName(), ore.getMiddleName(), ore.getLastName()));

	    // TODO Research where this is being used
	    this.setAgencyId(ore.getDepartmentId());

	    this.setOfficerAgencyId(ore.getDepartmentId());
	    this.setOfficerAgencyName(ore.getDepartmentName());

	    this.setOfficerPhoneNum(new PhoneNumber(ore.getWorkPhone()));
	    this.setOfficerCellNum(new PhoneNumber(ore.getCellPhone()));
	    this.setOfficerPager(new PhoneNumber(ore.getPager()));
	    this.setOfficerOID(ore.getOfficerProfileId());
	    this.setEmail(ore.getEmail());
	    this.setOfficerBadgeNumber(ore.getBadgeNum());
	    this.setOfficerOtherIdNumber(ore.getOtherIdNum());
	    this.setOfficerLogonId(ore.getUserId());

	    // convert badgeNum or otherIdNum to officerId and set officerIdType
	    if (ore.getBadgeNum() != null && ore.getBadgeNum().equals("") == false)
	    {
		this.setOfficerId(ore.getBadgeNum());
		this.setOfficerIdType("BADGE NUMBER");
	    }
	    else
	    {
		this.setOfficerId(ore.getOtherIdNum());
		this.setOfficerIdType("OTHER ID NUMBER");
	    }
	}
    }

    /**
     * @param b
     */
    public void setOnlyCharge(boolean b)
    {
	onlyCharge = b;
    }

    /**
     * @param originalCautions
     *            The originalCautions to set.
     */
    public void setOriginalCautions(String[] originalCautions)
    {
	this.originalCautions = originalCautions;
    }

    /**
     * @param originalCharge
     *            The originalCharge to set.
     */
    public void setOriginalCharge(String originalCharge)
    {
	this.originalCharge = originalCharge;
    }

    /**
     * @param originalCharges
     *            The originalCharges to set.
     */
    public void setOriginalCharges(String[] originalCharges)
    {
	this.originalCharges = originalCharges;
    }

    /**
     * @param originalScars
     *            The originalScars to set.
     */
    public void setOriginalScars(String[] originalScars)
    {
	this.originalScars = originalScars;
    }

    /**
     * @param originalScarsString
     *            The originalScarsString to set
     */
    public void setOriginalScarsString(String originalScarsString)
    {
	this.originalScarsString = originalScarsString;
    }

    /**
     * @param originalTattoos
     *            The originalTattoos to set.
     */
    public void setOriginalTattoos(String[] originalTattoos)
    {
	this.originalTattoos = originalTattoos;
    }

    /**
     * @param originalTattoosString
     *            The originalTattoosString to set
     */
    public void setOriginalTattoosString(String originalTattoosString)
    {
	this.originalTattoosString = originalTattoosString;
    }

    /**
     * @param number
     */
    public void setPager(PhoneNumber number)
    {
	pager = number;
    }

    /**
     * @param string
     */
    public void setPetitionNum(String string)
    {
	petitionNum = string;
    }

    /**
     * @param string
     */
    public void setPetitionType(String string)
    {
    }

    /**
     * @param number
     */
    public void setPhoneNum(PhoneNumber number)
    {
	phoneNum = number;
    }

    /**
     * @param collection
     */
    public void setPriorServiceAddresses(Collection collection)
    {
	priorServiceAddresses = collection;
    }

    /**
     * @param string
     */
    public void setProbationOfficerOfRecordId(String string)
    {
	probationOfficerOfRecordId = string;
    }

    /**
     * @param string
     */
    public void setProbationOfficerOfRecordName(String string)
    {
	probationOfficerOfRecordName = string;
    }

    /**
     * @param responseEvent
     */
    public void setProcessServiceProperties(ProcessReturnOfServiceResponseEvent aResponse)
    {
	this.setServiceStatus(aResponse.getServiceStatus());
	this.setCurrentServiceDate(aResponse.getServiceTimeStamp());
	this.setWarrantNum(aResponse.getWarrantNum());

	// Only used for printing
	this.setCourtId(aResponse.getWarrantOriginatorCourtId());
	this.setPetitionNum(aResponse.getPetitionNum());
	// executor info
	this.setExecutorName(aResponse.getOfficerName());
	this.setWarrantTypeId(aResponse.getWarrantTypeId());
	this.setJuvenileName(aResponse.getJuvenileName());
	this.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED);
	this.setOfficerId(aResponse.getOfficerId());
	this.setOfficerOtherIdNumber(aResponse.getOfficerOtherIdNum());
	this.setExecutorId(aResponse.getOfficerNum());
	this.setExecutorIdType(aResponse.getOfficerIdType());
	this.setExecutorDepartmentName(aResponse.getOfficerDepartment());
	this.setExecutorPhoneNum(aResponse.getOfficerWorkPhone());
	this.setExecutorCellNum(aResponse.getOfficerCellPhone());
	this.setExecutorPager(aResponse.getOfficerPager());
	this.setExecutorEmail(aResponse.getOfficerEmail());
	this.setServiceStatus(aResponse.getServiceStatus());
	this.setWarrantActivationDate(aResponse.getWarrantActivationDate());

	// service address
	String address = UIUtil.formatAddress(aResponse.getStreetNum(), aResponse.getStreetName(), aResponse.getStreetTypeId(), aResponse.getApartmentNum(), aResponse.getCity(), aResponse.getStateId(), aResponse.getZipCode(), aResponse.getZipCodeExtended(), null);
	this.setServiceAddress(address);
    }

    public void setProperties(CompositeResponse aResponse)
    {
	JuvenileWarrantFormDirector director = new JuvenileWarrantFormDirector(this);
	director.setProperties(aResponse);
    }

    /**
     * @param string
     */
    public void setRaceId(String string)
    {
	raceId = string;
    }

    /**
     * @param string
     */
    public void setRecallDate(Date date)
    {
	recallDate = date;
    }

    /**
     * @param string
     */
    public void setRecallFirstName(String string)
    {
	recallName.setFirstName(string);
    }

    /**
     * @param string
     */
    public void setRecallLastName(String string)
    {
	recallName.setLastName(string);
    }

    /**
     * @param string
     */
    public void setRecallLogonId(String string)
    {
	recallLogonId = string;
    }

    /**
     * @param string
     */
    public void setRecallMiddleName(String string)
    {
	recallName.setMiddleName(string);
    }

    /**
     * @param string
     */
    public void setRecallName(Name name)
    {
	recallName = name;
    }

    /**
     * @param string
     */
    public void setRecallReasonId(String string)
    {
	recallReasonId = string;
    }

    public void setReferralNum(Integer referralNum)
    {
	if (referralNum != null)
	{
	    this.referralNum = referralNum.toString();
	}
    }

    /**
     * @param string
     */
    public void setReferralNum(String string)
    {
	referralNum = string;
    }

    /**
     * @param string
     */
    public void setRefreshButton(String string)
    {
	refreshButton = string;
    }

    /**
     * @param string
     */
    public void setReleaseAssociateNum(String string)
    {
	releaseAssociateNum = string;
    }

    /**
     * @param string
     */
    public void setReleaseDecisionDate(Date date)
    {
	releaseDecisionDate = date;
    }

    /**
     * @param string
     */
    public void setReleaseDecisionId(String string)
    {
	releaseDecisionId = string;
    }

    /**
     * @param string
     */
    public void setReleaseDecisionLogonId(String string)
    {
	releaseDecisionLogonId = string;
    }

    /**
     * @param name
     */
    public void setReleaseDecisionName(String name)
    {
	releaseDecisionName = name;
    }

    /**
     * @param string
     */
    public void setReleaseDecisionUserId(String string)
    {
	releaseDecisionUserId = string;
    }

    /**
     * @param string
     */
    public void setReleaseDecisionUserName(String string)
    {
	releaseDecisionUserName = string;
    }

    /**
     * @param string
     */
    public void setReleaseRelationshipToJuvenile(String string)
    {
	releaseRelationshipToJuvenile = string;
    }

    /**
     * @param string
     */
    public void setSchoolCodeId(String string)
    {
	this.schoolCodeId = string;
    }

    /**
     * @param string
     */
    public void setSchoolDistrictId(String string)
    {
	this.schoolDistrictId = string;
    }

    /**
     * @param string
     */
    public void setSchoolDistrictName(String string)
    {
	this.schoolDistrictName = string;
    }

    /**
     * @param string
     */
    public void setSchoolName(String string)
    {
	this.schoolName = string;
    }

    /**
     * @param search
     *            The search to set.
     */
    public void setSearch(String search)
    {
	this.search = search;
    }

    /**
     * @param searchDate1
     *            the searchDate1 to set
     */
    public void setSearchDate1(String searchDate1)
    {
	this.searchDate1 = searchDate1;
    }

    /**
     * @param searchDate2
     *            the searchDate2 to set
     */
    public void setSearchDate2(String searchDate2)
    {
	this.searchDate2 = searchDate2;
    }

    /**
     * @param string
     */
    public void setSearchResultSize(String string)
    {
	searchResultSize = string;
    }

    /**
     * @param string
     */
    public void setSecondaryAction(String secondaryAction)
    {
	this.secondaryAction = secondaryAction;
    }

    /**
     * @param string
     */
    public void setSelectedAssociateAddressId(String string)
    {
	selectedAssociateAddressId = string;
    }

    /**
     * @param string
     */
    public void setSelectedBadAddress(String string)
    {
	selectedBadAddress = string;
    }

    /**
     * @param strings
     */
    public void setSelectedCautions(String[] strings)
    {

	selectedCautions = strings;
    }

    /**
     * @param string
     */
    public void setSelectedCharge(String string)
    {
	selectedCharge = string;
    }

    /**
     * @param strings
     */
    public void setSelectedCharges(String[] strings)
    {
	selectedCharges = strings;
    }

    /**
     * @param string
     */
    public void setSelectedScars(String[] array)
    {
	selectedScars = array;
    }

    /**
     * @param string
     */
    public void setSelectedTattoos(String[] array)
    {
	selectedTattoos = array;
    }

    /**
     * @param selectedValue
     *            The selectedValue to set.
     */
    public void setSelectedValue(String selectedValue)
    {
	this.selectedValue = selectedValue;
    }

    /**
     * @param string
     */
    public void setServiceAddress(String string)
    {
	serviceAddress = string;
    }

    /**
     * @param string
     */
    public void setServiceAddressType(String string)
    {
	serviceAddressType = string;
    }

    /**
     * @param string
     */
    public void setServiceAttemptComments(String string)
    {
	serviceAttemptComments = string;
    }

    /**
     * @param string
     */
    public void setServiceNumber(String string)
    {
	serviceNumber = string;
    }

    /**
     * @param string
     */
    public void setServiceReturnGeneratedStatus(String string)
    {
	serviceReturnGeneratedStatus = string;
    }

    /**
     * @param string
     */
    public void setServiceReturnGeneratedStatusId(String string)
    {
	serviceReturnGeneratedStatusId = string;
    }

    /**
     * @param string
     */
    public void setServiceReturnSignatureStatus(String string)
    {
	serviceReturnSignatureStatus = string;
    }

    /**
     * @param string
     */
    public void setServiceReturnSignatureStatusId(String string)
    {
	serviceReturnSignatureStatusId = string;
    }

    /**
     * @param services
     */
    public void setServices(Collection services)
    {
	this.services = services;
    }

    /**
     * @param string
     */
    public void setServiceStatus(String string)
    {
	serviceStatus = string;
    }

    /**
     * @param string
     */
    public void setSexId(String string)
    {
	sexId = string;
    }

    /**
     * @param string
     */
    public void setSid(String string)
    {
	sid = string;
    }

    /**
     * @param string
     */
    public void setSignatureCommandId(String string)
    {
	signatureCommandId = string;
    }

    // TODO SocialSecurity needs to be refactored with
    // messaging.domintf.contact.ISocialSecurity

    /**
     * @param security
     */
    public void setSsn(SocialSecurity security)
    {
	ssn = security;
    }

    /**
     * @return String
     */
    public void setSSN1(String string)
    {
	if (string == null || string.trim().equals(""))
	{
	    ssn = new SocialSecurity("");
	}
	else
	{
	    ssn.setSSN1(string);
	}
    }

    /**
     * @return String
     */
    public void setSSN2(String string)
    {
	if (string == null || string.trim().equals(""))
	{
	    ssn = new SocialSecurity("");
	}
	else
	{
	    ssn.setSSN2(string);
	}
    }

    /**
     * @return String
     */
    public void setSSN3(String string)
    {
	if (string == null || string.trim().equals(""))
	{
	    ssn = new SocialSecurity("");
	}
	else
	{
	    ssn.setSSN3(string);
	}
    }

    /**
     * @param string
     */
    public void setState(String string)
    {
	state = string;
    }

    /**
     * @param string
     */
    public void setStateIdNumber(String string)
    {
	stateIdNumber = string;
    }

    /**
     * @param string
     */
    public void setStreetName(String string)
    {
	streetName = string;
    }

    /**
     * @param string
     */
    public void setStreetNum(String string)
    {
	streetNum = string;
    }

    /**
     * @param string
     */
    public void setStreetType(String string)
    {
	streetType = string;
    }

    /**
     * @param collection
     */
    public void setSummaryOfFacts(List facts)
    {
	this.summaryOfFacts = facts;

    }

    /**
     * @param string
     */
    public void setSummOfFactsDisplaySize(String string)
    {
	summOfFactsDisplaySize = string;
    }

    /**
     * @param string
     */
    public void setTitle(String string)
    {
	title = string;
    }

    /**
     * @param string
     */
    public void setTransactionNum(String string)
    {
	transactionNum = string;
    }

    /**
     * @param string
     */
    public void setTransferCustodyDate(Date date)
    {
	transferCustodyDate = date;
    }

    /**
     * @param string
     */
    public void setTransferCustodyDateString(String string)
    {
	transferCustodyDateString = string;
    }

    /**
     * @param string
     */
    public void setTransferCustodyTimeString(String string)
    {
	transferCustodyTimeString = string;
    }

    /**
     * @param string
     */
    public void setTransferLocationId(String string)
    {
	transferLocationId = string;
    }

    /**
     * @param string
     */
    public void setTransferOfficerDepartmentId(String string)
    {
	transferOfficerDepartmentId = string;
    }

    /**
     * @param string
     */
    public void setTransferOfficerDepartmentName(String string)
    {

    }

    /**
     * @param string
     */
    public void setTransferOfficerId(String string)
    {
	transferOfficerId = string;
    }

    /**
     * @param string
     */
    public void setTransferOfficerIdType(String string)
    {
	transferOfficerIdType = string;
    }

    /**
     * @param string
     */
    public void setTransferOfficerLogonId(String string)
    {
	transferOfficerLogonId = string;
    }

    /**
     * @param string
     */
    public void setUnsendNotSignedReason(String string)
    {
	unsendNotSignedReason = string;
    }

    /**
     * @param string
     */
    public void setUserId(String string)
    {
	userId = string;
    }

    /**
     * @param string
     */
    public void setWarrantAcknowledgementDate(Date date)
    {
	warrantAcknowledgementDate = date;
    }

    /**
     * @param string
     */
    public void setWarrantAcknowledgeStatusId(String string)
    {
	warrantAcknowledgeStatusId = string;
    }

    /**
     * @param string
     */
    public void setWarrantActivationDate(Date date)
    {
	warrantActivationDate = date;
    }

    /**
     * @param string
     */
    public void setWarrantActivationStatusId(String string)
    {
	warrantActivationStatusId = string;
    }

    /**
     * @param string
     */
    public void setWarrantActivationStatusName(Name name)
    {
	warrantActivationStatusName = name;
    }

    public void setWarrantFileProperties(JuvenileWarrantResponseEvent anEvent)
    {
	this.setFileStampDate(anEvent.getFileStampDate());
	this.setFileStampLogonId(anEvent.getFileStampLogonId());
	this.setFileStampFirstName(anEvent.getFileStampFirstName());
	this.setFileStampMiddleName(anEvent.getFileStampMiddleName());
	this.setFileStampLastName(anEvent.getFileStampLastName());
    }

    /**
     * @param responseEvent
     */
    public void setWarrantInactivateProperties(JuvenileWarrantResponseEvent jwResponseEvent)
    {
	Date thisRecallDate = jwResponseEvent.getRecallDate();
	this.setRecallDate(thisRecallDate);
	this.setWarrantStatusId(jwResponseEvent.getWarrantStatusId());
	this.setWarrantActivationStatusId(jwResponseEvent.getWarrantActivationStatusId());
	this.setRecallReasonId(jwResponseEvent.getRecallReasonId());
    }

    /**
     * @param string
     */
    public void setWarrantNum(String string)
    {
	warrantNum = string.trim();
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorAgencyId(String string)
    {
	warrantOriginatorAgencyId = string;
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorAgencyName(String string)
    {
	warrantOriginatorAgencyName = string;
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorId(String string)
    {
	warrantOriginatorId = string;
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorJudge(String string)
    {
	warrantOriginatorJudge = string;
    }

    public void setWarrantOriginatorName(String warrantOriginatorName)
    {
	this.warrantOriginatorName = warrantOriginatorName;
    }

    /**
     * @param collection
     */
    public void setWarrants(Collection collection)
    {
	warrants = collection;
    }

    /**
     * @param warrantsSearchResults
     *            The warrantsSearchResults to set.
     */
    public void setWarrantsSearchResults(Collection collection)
    {
	this.warrantsSearchResults = collection;
    }

    /**
     * @param warrantsSearchResultsSize
     *            The warrantsSearchResultsSize to set.
     */
    public void setWarrantsSearchResultsSize(String string)
    {
	this.warrantsSearchResultsSize = string;
    }

    public void setWarrantServiceInfo(CompositeResponse aResponse)
    {
	JuvenileWarrantFormDirector director = new JuvenileWarrantFormDirector(this);
	director.setWarrantServiceInfo(aResponse);
    }

    /**
     * @param responseEvent
     */
    public void setWarrantServiceProperties(JuvenileWarrantServiceResponseEvent responseEvent)
    {
	this.successfulWarrant = true;
	this.setServiceStatus(responseEvent.getServiceStatus());
	this.setServiceAttemptComments(responseEvent.getComments());

	this.setCurrentServiceDate(responseEvent.getServiceTimeStamp());

	this.setServiceNumber(responseEvent.getServiceID());
	this.setServiceAddress(UIUtil.formatAddress(responseEvent.getStreetNum(), responseEvent.getStreetName(), responseEvent.getStreetType(), responseEvent.getAptNumber(), responseEvent.getCity(), responseEvent.getState(), responseEvent.getZipCode(), responseEvent.getAdditionalZipCode(), null));
	this.setServiceAddressType(responseEvent.getAddressType());
	this.setServiceAttemptComments(responseEvent.getComments());

	// move successfull Service Info to Arrest Info
	if (PDJuvenileWarrantConstants.WARRANT_SERVICE_SUCCESSFUL.equals(responseEvent.getServiceStatusId()))
	{
	    this.setArrestDate(this.getCurrentServiceDate());
	    this.setArrestAddress(this.getServiceAddress());
	}
	// executor info
	this.setExecutorName(new Name(responseEvent.getExecutorFirstName(), responseEvent.getExecutorMiddleName(), responseEvent.getExecutorLastName()));
	this.setExecutorId(responseEvent.getExecutorOfficerId());
	this.setExecutorIdType(responseEvent.getExecutorIdType());
	this.setExecutorDepartmentName(responseEvent.getExecutorAgencyName());
	this.setExecutorPhoneNum(new PhoneNumber(responseEvent.getExecutorPhoneNum()));
	this.setExecutorCellNum(new PhoneNumber(responseEvent.getExecutorCellNum()));
	this.setExecutorPager(new PhoneNumber(responseEvent.getExecutorPager()));
	this.setExecutorEmail(responseEvent.getExecutorEmail());
    }

    /**
     * @param string
     */
    public void setWarrantSignatureStatusName(Name name)
    {
	warrantSignatureStatusName = name;
    }

    /**
     * @param string
     */
    public void setWarrantSignedStatusId(String string)
    {
	warrantSignedStatusId = string;
    }

    /**
     * @param string
     */
    public void setWarrantStatusId(String string)
    {
	warrantStatusId = string;
    }

    /**
     * @param string
     */
    public void setWarrantTypeId(String string)
    {
	warrantTypeId = string;
    }

    /**
     * @param string
     */
    public void setWarrantTypeUI(String string)
    {
	warrantTypeUI = string;
    }

    public void setWarrantWarrantAcknowledgementProperties(JuvenileWarrantResponseEvent anEvent)
    {
	this.setWarrantAcknowledgementDate(anEvent.getWarrantAcknowledgementDate());
	this.setWarrantAcknowledgeStatusId(anEvent.getWarrantAcknowledgeStatusId());
    }

    /**
     * @param string
     */
    public void setWeight(String string)
    {
	weight = string;
    }

    /**
     * @param number
     */
    public void setWorkPhone(PhoneNumber number)
    {
	workPhone = number;
    }

    /**
     * @param string
     */
    public void setZipCode(String string)
    {
	zipCode = string;
    }

    /**
     * @param string
     */
    public void setZipCode2(String string)
    {
	zipCode2 = string;
    }

    /**
     * @param request
     * @return
     */
    public String getWarrantNumParameter(HttpServletRequest aRequest)
    {
	String warrantNumParm = aRequest.getParameter(UIConstants.WARRANT_NUM);
	if (warrantNumParm == null || warrantNumParm.equals(""))
	{
	    warrantNumParm = this.warrantNum;
	}
	return warrantNumParm;
    }

    /**
     * @return Returns the warrantActivationDateString.
     */
    public String getWarrantActivationDateString()
    {
	if (warrantActivationDate != null)
	{
	    warrantActivationDateString = DateUtil.dateToString(warrantActivationDate, UIConstants.DATE_FMT_1);
	}
	else
	{
	    warrantActivationDateString = "";
	}
	return warrantActivationDateString;
    }

    /**
     * @param warrantActivationDateString
     *            The warrantActivationDateString to set.
     */
    public void setWarrantActivationDateString(String warrantActivationDateString)
    {
	this.warrantActivationDateString = warrantActivationDateString;
    }

    /**
     * @return the searchDate1
     */
    public String getSearchDate1()
    {
	return searchDate1;
    }

    /**
     * @return the searchDate2
     */
    public String getSearchDate2()
    {
	return searchDate2;
    }

    /**
     * @return Returns the searchDepartmentId.
     */
    public String getSearchDepartmentId()
    {
	return searchDepartmentId;
    }

    /**
     * @param searchDepartmentId
     *            The searchDepartmentId to set.
     */
    public void setSearchDepartmentId(String searchDepartmentId)
    {
	this.searchDepartmentId = searchDepartmentId;
    }

    /**
     * @return Returns the searchDepartmentName.
     */
    public String getSearchDepartmentName()
    {
	return searchDepartmentName;
    }

    /**
     * @param searchDepartmentName
     *            The searchDepartmentName to set.
     */
    public void setSearchDepartmentName(String searchDepartmentName)
    {
	this.searchDepartmentName = searchDepartmentName;
    }

    /**
     * @return Returns the flowInd.
     */
    public String getFlowInd()
    {
	return flowInd;
    }

    /**
     * @param flowInd
     *            The flowInd to set.
     */
    public void setFlowInd(String flowInd)
    {
	this.flowInd = flowInd;
    }

    public String getJuvRectype()
    {
	return juvRectype;
    }

    public void setJuvRectype(String juvRectype)
    {
	this.juvRectype = juvRectype;
    }

    public boolean isValidDeptCode()
    {
	return validDeptCode;
    }

    public void setValidDeptCode(boolean validDeptCode)
    {
	this.validDeptCode = validDeptCode;
    }
}
