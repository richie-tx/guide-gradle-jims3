package pd.juvenilewarrant;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenilewarrant.GetJuvenileWarrantByActivationStatusEvent;
import messaging.juvenilewarrant.SearchJuvenileWarrantActiveOrPendingEvent;
import messaging.juvenilewarrant.SearchJuvenileWarrantEvent;
import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantStageErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;
import messaging.notification.CreateNotificationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.pattern.IBuilder;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import pd.codetable.Code;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.contact.agency.Department;
import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;

/**
 * @author ryoung
 */
public class JuvenileWarrant extends PersistentObject {

	/**
	 * @return JuvenileWarrant juvenileWarrant
	 * @param warrantNum
	 */
	static public JuvenileWarrant find(String warrantNum) {
		IHome home = new Home();
		JuvenileWarrant juvenileWarrant = (JuvenileWarrant) home.find(warrantNum, JuvenileWarrant.class);
		return juvenileWarrant;
	}

	/**
	 * 
	 * @param warrantNum
	 * @return
	 */
	static public JuvenileWarrant findByContext(String warrantNum, String context) {

		Reference reference = new Reference(warrantNum, JuvenileWarrant.class, context);
		JuvenileWarrant juvWarrant = (JuvenileWarrant) reference.getObject();
		return juvWarrant;
	}

	/**
	 * @return Iterator JuvenileWarrant
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileWarrant.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, JuvenileWarrant.class);
	}

	/**
	 * @param Event
	 * @param class
	 * @return
	 */
	public static MetaDataResponseEvent findMeta(IEvent event ) {
		IHome home = new Home();
		MetaDataResponseEvent iter = home.findMeta(event, JuvenileWarrant.class);
		return iter;
	}

	/**
	 * Taking in an instance of the GetJuvenileWarrantByStatusEvent it will
	 * return a boolean if any warrants exist by juvenileNum and warrant
	 * activation status values of the event.
	 * 
	 * @return boolean
	 * @param event
	 */
	static public boolean findWarrantsExistForActivationStatus(IEvent event) {
	    boolean returnBoolean = false;
		if ((event instanceof SearchJuvenileWarrantEvent)
				|| (event instanceof GetJuvenileWarrantByActivationStatusEvent)) {
			Iterator warrants = JuvenileWarrant.findAll(event);
			if (warrants.hasNext()) {
			    returnBoolean = true;
			}
		}
		return returnBoolean;
	}
	
	/**
	 * Used to find active warrants for create
	 * If found, cannot create a new warrant
	 * @param event
	 * @return
	 */
	static public boolean findActivePendingWarrantsExist(IEvent event) {
	    boolean returnBoolean = false;
		if ((event instanceof SearchJuvenileWarrantActiveOrPendingEvent)
				|| (event instanceof GetJuvenileWarrantByActivationStatusEvent)) {
			Iterator warrants = JuvenileWarrant.findAll(event);
			if (warrants.hasNext()) {
			    returnBoolean = true;
			}
		}
		return returnBoolean;
	}

	private String affidavitStatement;

	private String aliasName;

	private Date arrestTimeStamp;

	/**
	 * Properties for build
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey BUILD
	 * @detailerDoNotGenerate true
	 */
	private Code build = null;

	private String buildId;

	/**
	 * Properties for cautions
	 * 
	 * @associationType simple
	 * @referencedType pd.codetable.Code
	 * @contextKey CAUTIONS
	 * @detailerDoNotGenerate true
	 */
	private java.util.Collection cautions = null;

	/**
	 * Properties for charges
	 * 
	 * @referencedType pd.juvenilewarrant.Charge
	 * @detailerDoNotGenerate true
	 */
	private java.util.Collection charges = null;

	/**
	 * Properties for complexion
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey SKIN_TONE
	 * @detailerDoNotGenerate true
	 */
	private Code complexion = null;

	private String complexionId;

	private int daLogNumber;

	private Date dateOfBirth;

	private String dateOfBirthSource;

	private Date dateOfIssue;

	/**
	 * Properties for eyeColor
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey EYE_COLOR
	 * @detailerDoNotGenerate true
	 */
	private Code eyeColor = null;

	private String eyeColorId;

	private String fbiNum;

	private Date fileStampDate;
	private Date createDate;

	/**
	 * Properties for fileStampUser
	 * 
	 * @referencedType pd.contact.user.UserProfile
	 * @detailerDoNotGenerate true
	 */
	private UserProfile fileStampUser = null;

	private String fileStampUserId;

	private String firstName;

	/**
	 * Properties for hairColor
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey HAIR_COLOR
	 * @detailerDoNotGenerate true
	 */
	private Code hairColor = null;

	private String hairColorId;

	private String height;

	/**
	 * Properties for juvenileAssociates
	 * 
	 * @referencedType pd.juvenilewarrant.JuvenileAssociate
	 * @detailerDoNotGenerate true
	 */
	private java.util.Collection juvenileAssociates = null;

	private Integer juvenileNum;

	private String lastName;

	private String middleName;

	/**
	 * Properties for officer
	 * 
	 * @referencedType pd.contact.officer.OfficerProfile
	 * @detailerDoNotGenerate true
	 */
	private OfficerProfile officer = null;

	/**
	 * Properties for officerDepartment
	 * 
	 * @referencedType pd.contact.agency.Department
	 * @detailerDoNotGenerate true
	 */
	private Department officerDepartment = null;

	private String officerDepartmentId;

	private String officerId;

	private String otherCautionComments;

	private String phoneNum;

	/**
	 * Properties for probationOfficerOfrecord
	 * 
	 * @referencedType pd.contact.user.UserProfile
	 * @detailerDoNotGenerate true
	 */
	private UserProfile probationOfficerOfRecord = null;

	private String probationOfficerOfRecordId;

	/**
	 * Properties for race
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey RACE
	 * @detailerDoNotGenerate true
	 */
	private Code race = null;

	private String raceId;

	private Date recallDate;

	/**
	 * Properties for recallReason
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey RECALL_REASON
	 * @detailerDoNotGenerate true
	 */
	private Code recallReason = null;
	
	private String recallReasonId;

	/**
	 * Properties for recallUser
	 * 
	 * @referencedType pd.contact.user.UserProfile
	 * @detailerDoNotGenerate true
	 */
	private UserProfile recallUser = null;

	private String recallUserId;

	private Integer referralNum;

	private String releaseAssociateNum;

	/**
	 * Juvenile release attributes
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey RELEASE_DECISION
	 * @detailerDoNotGenerate true
	 */
	private Code releaseDecision = null;

	private String releaseDecisionId;

	private Date releaseDecisionTimeStamp;

	/**
	 * Properties for releaseDecisionUser
	 * 
	 * @referencedType pd.contact.user.UserProfile
	 * @detailerDoNotGenerate true
	 */
	private UserProfile releaseDecisionUser = null;

	private String releaseDecisionUserId;

	/**
	 * Properties for scarsMarks
	 * 
	 * @associationType simple
	 * @referencedType pd.codetable.person.ScarsMarksTattoosCode
	 * @detailerDoNotGenerate true
	 */
	private java.util.Collection scarsMarks = null;

	/**
	 * Properties for schoolCode
	 * 
	 * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
	 * @detailerDoNotGenerate true
	 */
	private JuvenileSchoolDistrictCode schoolCode = null;

	private String schoolCodeId;

	/**
	 * Properties for serviceReturnGeneratedStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey SERVICE_RETURNGENERATED_STATUS
	 * @detailerDoNotGenerate true
	 */
	private Code serviceReturnGeneratedStatus = null;

	private String serviceReturnGeneratedStatusId;

	/**
	 * Properties for serviceReturnSignatureStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey SERVICE_RETURNSIGNATURE_STATUS
	 * @detailerDoNotGenerate true
	 */
	private Code serviceReturnSignatureStatus = null;

	private String serviceReturnSignatureStatusId;

	/**
	 * Properties for services
	 * 
	 * @referencedType pd.juvenilewarrant.JuvenileService
	 * @detailerDoNotGenerate true
	 */
	private java.util.Collection services = null;

	/**
	 * Properties for sex
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey SEX
	 * @detailerDoNotGenerate true
	 */
	private Code sex = null;

	private String sexId;

	private String sidNum;

	private String ssn;

	private String suffix;

	/**
	 * Properties for tattoos
	 * 
	 * @associationType simple
	 * @referencedType pd.codetable.person.ScarsMarksTattoosCode
	 * @detailerDoNotGenerate true
	 */
	private java.util.Collection tattoos = null;

	private int transactionNum;

	/**
	 * Properties for transferLocation
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey TRANSFER_LOCATION
	 * @detailerDoNotGenerate true
	 */
	private Code transferLocation = null;

	private String transferLocationId;

	/**
	 * Properties for transferOfficer
	 * 
	 * @referencedType pd.contact.officer.OfficerProfile
	 * @detailerDoNotGenerate true
	 */
	private OfficerProfile transferOfficer = null;

	/**
	 * Properties for transferOfficerDepartment
	 * 
	 * @referencedType pd.contact.agency.Department
	 * @detailerDoNotGenerate true
	 */
	private Department transferOfficerDepartment = null;

	private String transferOfficerDepartmentId;

	private String transferOfficerId;

	private Date transferTimeStamp;

	private String unsendNotSignedReason;

	private Date warrantAcknowledgementDate;

	/**
	 * Properties for AcknowledgeStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey _ACKNOWLEDGE_STATUS
	 * @detailerDoNotGenerate true
	 */
	private Code warrantAcknowledgeStatus = null;

	private String warrantAcknowledgeStatusId;

	private Date warrantActivationDate;

	/**
	 * Properties for warrantActivationStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey WARRANT_ACTIVATION_STATUS
	 * @detailerDoNotGenerate true
	 */
	private Code warrantActivationStatus = null;

	private String warrantActivationStatusId;

	private String warrantNum;
	
	private String associateWarrantNum;
	
	private String associateId;
	
	private String warrantServeId;

	private String warrantOriginatorCourt;

	private String warrantOriginatorName;

	/**
	 * Properties for warrantOriginatorUser
	 * 
	 * @referencedType pd.contact.user.UserProfile
	 * @detailerDoNotGenerate true
	 */
	private UserProfile warrantOriginatorUser = null;

	private String warrantOriginatorUserId;

	/**
	 * Properties for warrantSignedStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey WARRANT_SIGNED_STATUS
	 * @detailerDoNotGenerate true
	 */
	private Code warrantSignedStatus = null;

	private String warrantSignedStatusId;

	/**
	 * Properties for warrantStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey WARRANT_STATUS
	 * @detailerDoNotGenerate true
	 */
	private Code warrantStatus = null;

	private String warrantStatusId;

	/**
	 * Properties for warrantType
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey WARRANT_TYPE
	 * @detailerDoNotGenerate true
	 */
	private Code warrantType = null;

	private String warrantTypeId;

	private int weight;

	/**
	 * @roseuid 41C1AFB70109
	 */
	public JuvenileWarrant() {
	}

	/**
	 * Clears all pd.codetable.Code from class relationship collection.
	 */
	public void clearCautions() {
		initCautions();
		cautions.clear();
	}

	/**
	 * Clears all pd.juvenilewarrant.Charge from class relationship collection.
	 */
	public void clearCharges() {
		initCharges();
		charges.clear();
	}

	/**
	 * Clears all pd.juvenilewarrant.JuvenileAssociate from class relationship
	 * collection.
	 */
	public void clearJuvenileAssociates() {
		initJuvenileAssociates();
		juvenileAssociates.clear();
	}

	/**
	 * Clears all pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 */
	public void clearScarsMarks() {
		initScarsMarks();
		scarsMarks.clear();
	}

	/**
	 * Clears all pd.juvenilewarrant.JuvenileWarrantService from class
	 * relationship collection.
	 */
	public void clearServices() {
		initServices();
		services.clear();
	}

	/**
	 * Clears all pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 */
	public void clearTattoos() {
		initTattoos();
		tattoos.clear();
	}

	/**
	 * Access method for the affidavitStatement property.
	 * 
	 * @return the current value of the affidavitStatement property
	 */
	public String getAffidavitStatement() {
		fetch();
		return affidavitStatement;
	}

	/**
	 * Access method for the aliasName property.
	 * 
	 * @return the current value of the aliasName property
	 */
	public String getAliasName() {
		fetch();
		return aliasName;
	}

	/**
	 * @return Date arrestTimeStamp
	 */
	public Date getArrestTimeStamp() {
		fetch();
		return arrestTimeStamp;
	}
	
	/**
	 * @return Date arrestTimeStamp as a formatted string
	 */
	public String getArrestTimeStampString() {
	    String dateString = DateUtil.dateToString(arrestTimeStamp, "EEEE, MMMM dd, yyyy 'at' hh:mm:ss z");
		return dateString;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code build
	 */
	public Code getBuild() {
		fetch();
		initBuild();
		return build;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String buildId
	 */
	public String getBuildId() {
		fetch();
		return buildId;
	}

	public String[] getCautionCodes() {
		fetch();
		return this.getCodes("parentId", this.warrantNum, JuvenileWarrantCautionsCode.class);
	}

	public List getChargeResponses() {
		List responses = new ArrayList();
		Iterator i = this.getCharges().iterator();
		String referralNum = null;
		if (this.getReferralNum() != null) {
			referralNum = this.getReferralNum().toString();
		}
		while (i.hasNext()) {
			Charge charge = (Charge) i.next();
			ChargeResponseEvent response = charge.valueObject(this.isJJS());
			response.setReferralNum(referralNum);
			responses.add(response);
		}
		return responses;
	}

	/**
	 * returns a collection of pd.juvenilewarrant.Charge
	 * 
	 * @return Collection charges
	 */
	public java.util.Collection getCharges() {
		fetch();
		initCharges();
		return charges;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code complexion
	 */
	public Code getComplexion() {
		fetch();
		initComplexion();
		return complexion;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String complexionId
	 */
	public String getComplexionId() {
		fetch();
		return complexionId;
	}

	/**
	 * Access method for the daLogNumber property.
	 * 
	 * @return the current value of the daLogNumber property
	 */
	public int getDaLogNumber() {
		fetch();
		return daLogNumber;
	}

	/**
	 * Access method for the dateOfBirth property.
	 * 
	 * @return the current value of the dateOfBirth property
	 */
	public Date getDateOfBirth() {
		fetch();
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public String getDateOfBirthSource() {
		fetch();
		return dateOfBirthSource;
	}

	/**
	 * Access method for the dateOfIssue property.
	 * 
	 * @return the current value of the dateOfIssue property
	 */
	public Date getDateOfIssue() {
		fetch();
		return dateOfIssue;
	}
	
	/**
	 * @return the current value of the dateOfIssue property as a formatted string
	 */
	public String getDateOfIssueString() {
	    String dateString = DateUtil.dateToString(dateOfIssue, "EEEE, MMMM dd, yyyy 'at' hh:mm:ss z");
		return dateString;
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code eyeColor
	 */
	public Code getEyeColor() {
		fetch();
		initEyeColor();
		return eyeColor;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String eyeColorId
	 */
	public String getEyeColorId() {
		fetch();
		return eyeColorId;
	}

	/**
	 * Access method for the fbiNum property.
	 * 
	 * @return the current value of the fbiNum property
	 */
	public String getFbiNum() {
		fetch();
		return fbiNum;
	}

	/**
	 * Access method for the fileStampDate property.
	 * 
	 * @return the current value of the fileStampDate property
	 */
	public Date getFileStampDate() {
		fetch();
		return fileStampDate;
	}

	/**
	 * @return the current value of the fileStampDate property as a formatted string
	 */
	public String getFileStampDateString() {
	    String dateString = DateUtil.dateToString(fileStampDate, "EEEE, MMMM dd, yyyy 'at' hh:mm:ss z");
		return dateString;
	}

	/**
	 * Gets referenced type pd.contact.user.UserProfile
	 * 
	 * @return UserProfile fileStampUser
	 */
	public UserProfile getFileStampUser() {
		fetch();
		initFileStampUser();
		return fileStampUser;
	}

	/**
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 * 
	 * @return String fileStampUserId
	 */
	public String getFileStampUserId() {
		fetch();
		return fileStampUserId;
	}

	/**
	 * Access method for the firstName property.
	 * 
	 * @return the current value of the firstName property
	 */
	public String getFirstName() {
		fetch();
		return firstName;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code hairColor
	 */
	public Code getHairColor() {
		fetch();
		initHairColor();
		return hairColor;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String hairColorId
	 */
	public String getHairColorId() {
		fetch();
		return hairColorId;
	}

	/**
	 * Access method for the height property.
	 * 
	 * @return the current value of the height property
	 */
	public String getHeight() {
		fetch();
		return height;
	}

	/**
	 * @return
	 */
	public InvalidWarrantStageErrorEvent getInvalidWarrantStageErrorEvent() {
		// Not going to send back the descriptions because codes are cached
		InvalidWarrantStageErrorEvent errorEvent = new InvalidWarrantStageErrorEvent();
		errorEvent.setWarrantNum(this.getWarrantNum());
		errorEvent.setWarrantAcknowledgeStatus(this.getWarrantAcknowledgeStatusId());
		errorEvent.setWarrantActivationStatus(this.getWarrantActivationStatusId());
		errorEvent.setWarrantSignatureStatus(this.getWarrantSignedStatusId());
		errorEvent.setWarrantStatus(this.getWarrantStatusId());
		return errorEvent;
	}

	/**
	 * returns a collection of pd.juvenilewarrant.JuvenileAssociate
	 * 
	 * @return Collection juvenileAssociates
	 */
	public java.util.Collection getJuvenileAssociates() {
		fetch();
		initJuvenileAssociates();
		return juvenileAssociates;
	}

	/**
	 * Access method for the juvenileNum property.
	 * 
	 * @return the current value of the juvenileNum property
	 */
	public Integer getJuvenileNum() {
		fetch();
		return juvenileNum;
	}

	/**
	 * Access method for the lastName property.
	 * 
	 * @return the current value of the lastName property
	 */
	public String getLastName() {
		fetch();
		return lastName;
	}

	/**
	 * Access method for the middleName property.
	 * 
	 * @return the current value of the middleName property
	 */
	public String getMiddleName() {
		fetch();
		return middleName;
	}
	
	/**Returns the juveniles full name First Middle Last Suffix
	 * @return
	 */
	public String getNameFirstMiddleLastSuffix(){
	    
	    StringBuffer buffer = new StringBuffer(50);
	    if(getFirstName() != null && !getFirstName().equals(""))
	    {
	        buffer.append(getFirstName());
	        buffer.append(" ");
	    }
	    if(getMiddleName() != null && !getMiddleName().equals(""))
	    {
	        buffer.append(getMiddleName());
	        buffer.append(" ");
	    }
	    if(getLastName() != null && !getLastName().equals(""))
	    {
	        buffer.append(getLastName());
	    }
	    if(getSuffix() != null && !getSuffix().equals(""))
	    {
	        buffer.append(" ");
	        buffer.append(getSuffix());
	    }
	    String nameFirstMiddleLastSuffix = buffer.toString();
	    return nameFirstMiddleLastSuffix;
	}
    
	/**Returns the juveniles full name Last, First Middle Suffix
	 * @return
	 */
	public String getNameLastFirstMiddleSuffix(){
	    
	    StringBuffer buffer = new StringBuffer(50);
	    if(getLastName() != null && !getLastName().equals(""))
	    {
	        buffer.append(getLastName());
	    }
	    if(getFirstName() != null && !getFirstName().equals(""))
	    {
	        buffer.append(", ");
	        buffer.append(getFirstName());
	    }
	    if(getMiddleName() != null && !getMiddleName().equals(""))
	    {
	        buffer.append(" ");
	        buffer.append(getMiddleName());
	    }
	    if(getSuffix() != null && !getSuffix().equals(""))
	    {
	        buffer.append(" ");
	        buffer.append(getSuffix());
	    }
	    String nameLastFirstMiddleSuffix = buffer.toString();
	    return nameLastFirstMiddleSuffix;
	}
	
	/**
	 * Gets referenced type pd.contact.officer.OfficerProfile
	 * 
	 * @return OfficerProfile officer
	 */
	public OfficerProfile getOfficer() {
		fetch();
		initOfficer();
		return officer;
	}

	/**
	 * Gets referenced type pd.contact.agency.Department
	 * 
	 * @return Department officerDepartment
	 */
	public Department getOfficerDepartment() {
		fetch();
		initOfficerDepartment();
		return officerDepartment;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Department
	 * 
	 * @return String officerDepartmentId
	 */
	public String getOfficerDepartmentId() {
		fetch();
		return officerDepartmentId;
	}

	/**
	 * Get the reference value to class :: pd.contact.party.Officer
	 * 
	 * @return String officerId
	 */
	public String getOfficerId() {
		fetch();
		return officerId;
	}

	/**
	 * Access method for the otherCautionComments property.
	 * 
	 * @return the current value of the otherCautionComments property
	 */
	public String getOtherCautionComments() {
		fetch();
		return otherCautionComments;
	}

	/**
	 * Access method for the phoneNum property.
	 * 
	 * @return the current value of the phoneNum property
	 */
	public String getPhoneNum() {
		fetch();
		return phoneNum;
	}

	/**
	 * Gets referenced type pd.contact.user.UserProfile
	 */
	public UserProfile getProbationOfficerOfRecord() {
		initProbationOfficerOfRecord();
		return probationOfficerOfRecord;
	}

	/**
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 */
	public String getProbationOfficerOfRecordId() {
		fetch();
		return probationOfficerOfRecordId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code race
	 */
	public Code getRace() {
		fetch();
		initRace();
		return race;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String raceId
	 */
	public String getRaceId() {
		fetch();
		return raceId;
	}

	/**
	 * @return Date recallDate
	 */
	public Date getRecallDate() {
		fetch();
		return recallDate;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code recallReason
	 */
	public Code getRecallReason() {
		fetch();
		initRecallReason();
		return recallReason;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String recallReasonId
	 */
	public String getRecallReasonId() {
		fetch();
		return recallReasonId;
	}

    /**
     * @return Returns the recallReasonDescription.
     */
    public String getRecallReasonDescription()
    {
        Code recallReasonDescription = getRecallReason();
        String recallDescription = "";
        
        if (recallReasonDescription != null)
        {
            recallDescription = recallReasonDescription.getDescription();
        }
        return recallDescription;
     }
    
	/**
	 * Gets referenced type pd.contact.user.UserProfile
	 * 
	 * @return UserProfile recallUser
	 */
	public UserProfile getRecallUser() {
		fetch();
		initRecallUser();
		return recallUser;
	}

	/**
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 * 
	 * @return String recallUserId
	 */
	public String getRecallUserId() {
		fetch();
		return recallUserId;
	}

	/**
	 * Access method for the referralNum property.
	 * 
	 * @return the current value of the referralNum property
	 */
	public Integer getReferralNum() {
		fetch();
		return referralNum;
	}

	/**
	 * @return String releaseAssociateNum
	 */
	public String getReleaseAssociateNum() {
		fetch();
		return releaseAssociateNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code releaseDecision
	 */
	public Code getReleaseDecision() {
		fetch();
		initReleaseDecision();
		return releaseDecision;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String releaseDecisionId
	 */
	public String getReleaseDecisionId() {
		fetch();
		return releaseDecisionId;
	}

	/**
	 * @return Date releaseDecisionTimeStamp
	 */
	public Date getReleaseDecisionTimeStamp() {
		fetch();
		return releaseDecisionTimeStamp;
	}

	/**
	 * Gets referenced type pd.contact.user.UserProfile
	 * 
	 * @return UserProfile releaseDecisionUser
	 */
	public UserProfile getReleaseDecisionUser() {
		fetch();
		initReleaseDecisionUser();
		return releaseDecisionUser;
	}

	/**
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 * 
	 * @return String releaseDecisionUserId
	 */
	public String getReleaseDecisionUserId() {
		fetch();
		return releaseDecisionUserId;
	}

	public JuvenileWarrantResponseEvent getResponseForService() {
		IBuilder builder = new JuvenileWarrantServiceBuilder(this);
		builder.build();
		return (JuvenileWarrantResponseEvent) builder.getResult();
	}

	public String[] getScarsMarksCodes() {
		fetch();
		return this.getCodes("parentId", this.warrantNum, JuvenileWarrantScarsMarksScarsMarksTattoosCode.class);
	}

	/**
	 * Gets referenced type pd.codetable.person.JuvenileSchoolDistrictCode
	 * 
	 * @return JuvenileSchoolDistrictCode schoolCode
	 */
	public JuvenileSchoolDistrictCode getSchoolCode() {
		fetch();
		initSchoolCode();
		return schoolCode;
	}

	/**
	 * Get the reference value to class ::
	 * pd.codetable.person.JuvenileSchoolDistrictCode
	 * 
	 * @return String schoolCodeId
	 */
	public String getSchoolCodeId() {
		fetch();
		return schoolCodeId;
	}

	/**
	 * @return
	 */
	public List getServiceResponses() {
		List responses = new ArrayList();
		Iterator i = this.getServices().iterator();
		while (i.hasNext()) {
			JuvenileWarrantService service = (JuvenileWarrantService) i.next();

			JuvenileWarrantServiceResponseEvent warrantServiceEvent = service.valueObject();
			responses.add(warrantServiceEvent);
		}
		return responses;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code serviceReturnGeneratedStatus
	 */
	public Code getServiceReturnGeneratedStatus() {
		fetch();
		initServiceReturnGeneratedStatus();
		return serviceReturnGeneratedStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String getServiceReturnGeneratedStatusId
	 */
	public String getServiceReturnGeneratedStatusId() {
		fetch();
		return serviceReturnGeneratedStatusId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code serviceReturnSignatureStatus
	 */
	public Code getServiceReturnSignatureStatus() {
		fetch();
		initServiceReturnSignatureStatus();
		return serviceReturnSignatureStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String serviceReturnSignatureStatusId
	 */
	public String getServiceReturnSignatureStatusId() {
		fetch();
		return serviceReturnSignatureStatusId;
	}

	/**
	 * returns a collection of pd.juvenilewarrant.JuvenileWarrantService
	 * 
	 * @return Collection services
	 */
	public java.util.Collection getServices() {
		fetch();
		initServices();
		return services;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code sex
	 */
	public Code getSex() {
		fetch();
		initSex();
		return sex;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String sexId
	 */
	public String getSexId() {
		fetch();
		return sexId;
	}

	/**
	 * Access method for the sidNum property.
	 * 
	 * @return the current value of the sidNum property
	 */
	public String getSidNum() {
		fetch();
		return sidNum;
	}

	/**
	 * Access method for the ssn property.
	 * 
	 * @return the current value of the ssn property
	 */
	public String getSsn() {
		fetch();
		return ssn;
	}

	/**
	 * Access method for the suffix property.
	 * 
	 * @return the current value of the suffix property
	 */
	public String getSuffix() {
		fetch();
		return suffix;
	}

	private String[] getCodes(String parentName, String parentId, Class associationClass) {
		List retVal = new ArrayList();

		IHome home = new Home();

		Iterator i = home.findAll(parentName, parentId, associationClass);

		while (i.hasNext()) {
			IPersistentObjectAssociation actual = (IPersistentObjectAssociation) i.next();
			retVal.add(actual.getChildId());
		}

		int len = retVal.size();
		String[] codes = new String[len];
		for (int j = 0; j < len; j++) {
			codes[j] = (String) retVal.get(j);
		}
		return codes;
	}

	public String[] getTattoosCodes() {
		fetch();
		return this.getCodes("parentId", this.warrantNum, JuvenileWarrantTattoosScarsMarksTattoosCode.class);
	}

	/**
	 * Access method for the transactionNum property.
	 * 
	 * @return the current value of the transactionNum property
	 */
	public int getTransactionNum() {
		fetch();
		return transactionNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code transferLocation
	 */
	public Code getTransferLocation() {
		fetch();
		initTransferLocation();
		return transferLocation;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String tranferLocationId
	 */
	public String getTransferLocationId() {
		fetch();
		return transferLocationId;
	}

	/**
	 * Gets referenced type pd.contact.officer.OfficerProfile Officer
	 * transferOfficer
	 * 
	 * @return Officer transferOfficer
	 */
	public OfficerProfile getTransferOfficer() {
		fetch();
		initTransferOfficer();
		return transferOfficer;
	}

	/**
	 * Gets referenced type pd.contact.agency.Department
	 * 
	 * @return Department transferOfficerDepartment
	 */
	public Department getTransferOfficerDepartment() {
		fetch();
		initTransferOfficerDepartment();
		return officerDepartment;
	}

	/**
	 * @return
	 */
	public String getTransferOfficerDepartmentId() {
		fetch();
		return transferOfficerDepartmentId;
	}

	/**
	 * Get the reference value to class :: pd.contact.party.Officer
	 * 
	 * @return String transferOfficerId
	 */
	public String getTransferOfficerId() {
		fetch();
		return transferOfficerId;
	}

	/**
	 * @return Date transferTimeStamp
	 */
	public Date getTransferTimeStamp() {
		fetch();
		return transferTimeStamp;
	}

	/**
	 * Access method for the unsendNotSignedReason property.
	 * 
	 * @return the current value of the unsendNotSignedReason property
	 */
	public String getUnsendNotSignedReason() {
		fetch();
		return unsendNotSignedReason;
	}

	/**
	 * Access method for the warrantAcknowledgementDate property.
	 * 
	 * @return the current value of the warrantAcknowledgementDate property
	 */
	public Date getWarrantAcknowledgementDate() {
		fetch();
		return warrantAcknowledgementDate;
	}

	/**
	 * @return the current value of the warrantAcknowledgementDate property as a formatted string
	 */
	public String getWarrantAcknowledgementDateString() {
	    String dateString = DateUtil.dateToString(warrantAcknowledgementDate, "EEEE, MMMM dd, yyyy 'at' hh:mm:ss z");
		return dateString;
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code warrantAcknowledgeStatus
	 */
	public Code getWarrantAcknowledgeStatus() {
		fetch();
		initWarrantAcknowledgeStatus();
		return warrantAcknowledgeStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String warrantAcknowledgeStatusId
	 */
	public String getWarrantAcknowledgeStatusId() {
		fetch();
		return warrantAcknowledgeStatusId;
	}

	/**
	 * Access method for the warrantActivationDate property.
	 * 
	 * @return the current value of the warrantActivationDate property
	 */
	public Date getWarrantActivationDate() {
		fetch();
		return warrantActivationDate;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code warrantActivationStatus
	 */
	public Code getWarrantActivationStatus() {
		fetch();
		initWarrantActivationStatus();
		return warrantActivationStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String warrantActivationStatusId
	 */
	public String getWarrantActivationStatusId() {
		fetch();
		return warrantActivationStatusId;
	}

	/**
	 * Access method for the warrantNum property.
	 * 
	 * @return the current value of the warrantNum property
	 */
	public String getWarrantNum() {
		fetch();
		return getOID().toString();
	}

	/**
	 * @return String warrantOriginatorCourt
	 */
	public String getWarrantOriginatorCourt() {
		fetch();
		return this.warrantOriginatorCourt;
	}

	/**
	 * @return String warrantOriginatorName
	 */
	public String getWarrantOriginatorName() {
		fetch();
		return this.warrantOriginatorName;
	}

	/**
	 * Gets referenced type pd.contact.user.UserProfile
	 * 
	 * @return UserProfile warrantOriginatorUser
	 */
	public UserProfile getWarrantOriginatorUser() {
		fetch();
		initWarrantOriginatorUser();
		return warrantOriginatorUser;
	}

	/**
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 * 
	 * @return String warrantOriginatorUserId
	 */
	public String getWarrantOriginatorUserId() {
		fetch();
		return warrantOriginatorUserId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code warrantSignedStatus
	 */
	public Code getWarrantSignedStatus() {
		fetch();
		initWarrantSignedStatus();
		return warrantSignedStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String warrantSignedStatusId
	 */
	public String getWarrantSignedStatusId() {
		fetch();
		return warrantSignedStatusId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code warrantStatus
	 */
	public Code getWarrantStatus() {
		fetch();
		initWarrantStatus();
		return warrantStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String warrantStatusId
	 */
	public String getWarrantStatusId() {
		fetch();
		return warrantStatusId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return Code warrantType
	 */
	public Code getWarrantType() {
		fetch();
		initWarrantType();
		return warrantType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return String warrantTypeId
	 */
	public String getWarrantTypeId() {
		fetch();
		return warrantTypeId;
	}

	/**
	 * Access method for the weight property.
	 * 
	 * @return the current value of the weight property
	 */
	public int getWeight() {
		fetch();
		return weight;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initBuild() {
		if (build == null) {
			build = (Code) new mojo.km.persistence.Reference(buildId, Code.class, PDCodeTableConstants.BUILD)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for pd.codetable.Code
	 */
	private void initCautions() {
		if (cautions == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			cautions = new mojo.km.persistence.ArrayList(JuvenileWarrantCautionsCode.class, "parentId",
					(String) getOID());
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenilewarrant.Charge
	 */
	private void initCharges() {
		if (charges == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			charges = new mojo.km.persistence.ArrayList(Charge.class, "warrantNum", (String) getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initComplexion() {
		if (complexion == null) {
			if (this.complexionId != null) {
				complexion = (Code) new mojo.km.persistence.Reference(complexionId,
						Code.class, PDCodeTableConstants.SKIN_TONE).getObject();
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initEyeColor() {
		if (eyeColor == null) {
			eyeColor = (Code) new mojo.km.persistence.Reference(eyeColorId, Code.class,
				PDCodeTableConstants.EYE_COLOR).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initFileStampUser() {
		if (fileStampUser == null) {
		//87191
			fileStampUser = UserProfile.find(fileStampUserId);
				/*(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(fileStampUserId,
					pd.contact.user.UserProfile.class).getObject();*/
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initHairColor() {
		if (hairColor == null) {
			hairColor = (Code) new mojo.km.persistence.Reference(hairColorId, Code.class,
				PDCodeTableConstants.HAIR_COLOR).getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenilewarrant.JuvenileAssociate
	 */
	private void initJuvenileAssociates() {
		if (juvenileAssociates == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			juvenileAssociates = new mojo.km.persistence.ArrayList(JuvenileAssociate.class, "warrantNum",
					(String) getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.party.Officer
	 */
	private void initOfficer() {
		if (officer == null) {
			officer = (OfficerProfile) new mojo.km.persistence.Reference(officerId,
					OfficerProfile.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Department
	 */
	private void initOfficerDepartment() {
		if (officerDepartment == null) {
			officerDepartment = Department.find(officerDepartmentId);//(pd.contact.agency.Department) new mojo.km.persistence.Reference(officerDepartmentId,
					//pd.contact.agency.Department.class).getObject(); //87191
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initProbationOfficerOfRecord() {
		if (probationOfficerOfRecordId != null && "".equals(probationOfficerOfRecordId.trim()) == false
				&& probationOfficerOfRecord == null) {
				//87191
			probationOfficerOfRecord = UserProfile.find(probationOfficerOfRecordId);/*(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(
					probationOfficerOfRecordId, pd.contact.user.UserProfile.class).getObject();*/
		}
	}

	/**

	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initRace() {
		if (race == null) {
			race = (Code) new mojo.km.persistence.Reference(raceId, Code.class, PDCodeTableConstants.RACE)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initRecallReason() {
		if (recallReason == null) {
			if (this.recallReasonId != null) {
				recallReason = (Code) new mojo.km.persistence.Reference(recallReasonId,
						Code.class, "RECALL_REASON").getObject();
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initRecallUser() {
		if (recallUser == null) {
		//87191
			recallUser =  UserProfile.find(recallUserId);/*(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(recallUserId,
					pd.contact.user.UserProfile.class).getObject();*/
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initReleaseDecision() {
		if (releaseDecision == null) {
			releaseDecision = (Code) new mojo.km.persistence.Reference(releaseDecisionId,
					Code.class, "RELEASE_DECISION").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initReleaseDecisionUser() {
		if (releaseDecisionUser == null) {
		//87191
			releaseDecisionUser = UserProfile.find(releaseDecisionUserId);/*(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(
					releaseDecisionUserId, pd.contact.user.UserProfile.class).getObject();*/
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.codetable.person.ScarsMarksTattoosCode
	 */
	private void initScarsMarks() {
		if (scarsMarks == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			scarsMarks = new mojo.km.persistence.ArrayList(JuvenileWarrantScarsMarksScarsMarksTattoosCode.class,
					"parentId", (String) getOID());
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.person.JuvenileSchoolDistrictCode
	 */
	private void initSchoolCode() {
		if (schoolCode == null) {
		    
		    String sCode = this.schoolCodeId;
		    if( sCode.length() >= 6 ){
			
			String districtid = sCode.substring(0, 3);
			String schoolid   = sCode.substring(3);
			 int dsCode = Integer.parseInt(districtid);
			 int ssCode = Integer.parseInt(schoolid);
			 String derivedOid = String.valueOf(dsCode) + "-" + String.valueOf(ssCode);
				schoolCode = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(
					derivedOid, JuvenileSchoolDistrictCode.class).getObject();
		    }		   
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initServiceReturnGeneratedStatus() {
		if (serviceReturnGeneratedStatus == null) {
			serviceReturnGeneratedStatus = (Code) new mojo.km.persistence.Reference(
					serviceReturnGeneratedStatusId, Code.class, "SERVICE_RETURNGENERATED_STATUS")
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initServiceReturnSignatureStatus() {
		if (serviceReturnSignatureStatus == null) {
			serviceReturnSignatureStatus = (Code) new mojo.km.persistence.Reference(
					serviceReturnSignatureStatusId, Code.class, "SERVICE_RETURNSIGNATURE_STATUS")
					.getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenilewarrant.Charge
	 */
	private void initServices() {
		if (services == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			services = new mojo.km.persistence.ArrayList(JuvenileWarrantService.class, "juvenileWarrantId",
					(String) getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSex() {
		if (sex == null) {
			sex = (Code) new mojo.km.persistence.Reference(sexId, Code.class, "SEX")
					.getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.codetable.person.ScarsMarksTattoosCode
	 */
	private void initTattoos() {
		if (tattoos == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			tattoos = new mojo.km.persistence.ArrayList(JuvenileWarrantTattoosScarsMarksTattoosCode.class, "parentId",
					(String) getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initTransferLocation() {
		if (transferLocation == null) {
			if (this.transferLocationId != null) {
				transferLocation = (Code) new mojo.km.persistence.Reference(transferLocationId,
						Code.class, "TRANSFER_LOCATION").getObject();
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.officer.OfficerProfile
	 */
	private void initTransferOfficer() {
		if (transferOfficer == null) {
			transferOfficer = (OfficerProfile) new mojo.km.persistence.Reference(transferOfficerId,
					OfficerProfile.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Department
	 */
	private void initTransferOfficerDepartment() {
		if (transferOfficerDepartment == null) {
			transferOfficerDepartment = Department.find(transferOfficerDepartmentId);//87191//(pd.contact.agency.Department) new mojo.km.persistence.Reference(
					//transferOfficerDepartmentId, pd.contact.agency.Department.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initWarrantAcknowledgeStatus() {
		if (warrantAcknowledgeStatus == null) {
			warrantAcknowledgeStatus = (Code) new mojo.km.persistence.Reference(
					warrantAcknowledgeStatusId, Code.class, "WARRANT_ACKNOWLEDGE_STATUS").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initWarrantActivationStatus() {
		if (warrantActivationStatus == null) {
			warrantActivationStatus = (Code) new mojo.km.persistence.Reference(warrantActivationStatusId,
					Code.class, "WARRANT_ACTIVATION_STATUS").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initWarrantOriginatorUser() {
		if (warrantOriginatorUser == null) {
		//87191
			warrantOriginatorUser = UserProfile.find(warrantOriginatorUserId);/*(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(
					warrantOriginatorUserId, pd.contact.user.UserProfile.class).getObject()*/;
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initWarrantSignedStatus() {
		if (warrantSignedStatus == null) {
			warrantSignedStatus = (Code) new mojo.km.persistence.Reference(warrantSignedStatusId,
					Code.class, "WARRANT_SIGNED_STATUS").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initWarrantStatus() {
		if (warrantStatus == null) {
			warrantStatus = (Code) new mojo.km.persistence.Reference(warrantStatusId,
					Code.class, "WARRANT_STATUS").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initWarrantType() {
		if (warrantType == null) {
			warrantType = (Code) new mojo.km.persistence.Reference(warrantTypeId, Code.class,
					"WARRANT_TYPE").getObject();
		}
	}

	/**
	 * insert a pd.codetable.Code into class relationship collection.
	 */
	public void insertCautions(Code anObject) {
		initCautions();
		JuvenileWarrantCautionsCode actual = new JuvenileWarrantCautionsCode();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		cautions.add(actual);
	}

	/**
	 * insert a pd.juvenilewarrant.Charge into class relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertCharges(Charge anObject) {
		initCharges();
		charges.add(anObject);
	}

	/**
	 * insert a pd.juvenilewarrant.JuvenileAssociate into class relationship
	 * collection.
	 * 
	 * @param anObject
	 */
	public void insertJuvenileAssociates(JuvenileAssociate anObject) {
		initJuvenileAssociates();
		juvenileAssociates.add(anObject);
	}

	/**
	 * insert a pd.codetable.person.ScarsMarksTattoosCode into class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertScarsMarks(pd.codetable.person.ScarsMarksTattoosCode anObject) {
		initScarsMarks();
		JuvenileWarrantScarsMarksScarsMarksTattoosCode actual = new JuvenileWarrantScarsMarksScarsMarksTattoosCode();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		scarsMarks.add(actual);
	}

	/**
	 * insert a pd.juvenilewarrant.JuvenileWarrantService into class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertServices(JuvenileWarrantService anObject) {
		initServices();
		services.add(anObject);
	}

	/**
	 * insert a pd.codetable.person.ScarsMarksTattoosCode into class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertTattoos(pd.codetable.person.ScarsMarksTattoosCode anObject) {
		initTattoos();
		JuvenileWarrantTattoosScarsMarksTattoosCode actual = new JuvenileWarrantTattoosScarsMarksTattoosCode();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		tattoos.add(actual);
	}

	/**
	 * 
	 * @param warrantAckStatus
	 * @return
	 */
	public boolean isAcknowledgeStatusPrintedOrNot() {
		return PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED.equals(this.warrantAcknowledgeStatusId)
				|| PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED.equals(this.warrantAcknowledgeStatusId);
	}

	public boolean isJJS() {
		return (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(this.warrantTypeId) || (PDJuvenileWarrantConstants.WARRANT_TYPE_VOP
				.equals(this.warrantTypeId)));
	}

	public boolean isJOT() {
		return (PDJuvenileWarrantConstants.WARRANT_TYPE_DTA.equals(this.warrantTypeId)
				|| (PDJuvenileWarrantConstants.WARRANT_TYPE_PCW.equals(this.warrantTypeId)) || (PDJuvenileWarrantConstants.WARRANT_TYPE_ARR
				.equals(this.warrantTypeId)));
	}

	public boolean isRecallableForMultiples() {
		boolean recallable = true;
		// The list has been narrowed by the where clause in the mapping file
		// release decision must not be null if the Warrant Status is Executed
		if (PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED.equals(this.warrantStatusId)) {

			recallable = (this.releaseDecisionId != null);
		}

		return recallable;
	}

	public boolean isViewableByFeature(IUserInfo userInfo, boolean viewActive, boolean viewOpenActive, boolean viewInactive, boolean viewExecuted,
    		boolean viewRecalled, boolean viewPendingNotActive, boolean viewUnsend) {
    	
			boolean isViewableByFeature = false;

			
		if ("MA".equals(userInfo.getUserTypeId()) == false) 
		{
			if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE.equals(warrantActivationStatusId) && (viewActive == true)) 
			{
			    isViewableByFeature = true;
			}else if (PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED.equals(warrantStatusId) && (viewExecuted == true)) {
				isViewableByFeature = true;
			} else if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE.equals(warrantActivationStatusId)
			     && (viewInactive == true)) {
			    isViewableByFeature = true;
			} else if (PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN.equals(warrantStatusId)
			     && PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE.equals(warrantActivationStatusId)
			     && (viewOpenActive == true)) {
			    isViewableByFeature = true;
			} else if (PDJuvenileWarrantConstants.WARRANT_STATUS_RECALL.equals(warrantStatusId)
			     && (viewRecalled == true)) {
			    isViewableByFeature = true;
			} else if (PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING.equals(warrantStatusId)
			     && PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE.equals(warrantActivationStatusId)
			     && (viewPendingNotActive == true)){
			    isViewableByFeature = true;
			}else if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_UNSEND.equals(warrantActivationStatusId)
				     && (viewUnsend == true)) {
			    isViewableByFeature = true;
			}
		} else {
			isViewableByFeature = true;
		}
		return isViewableByFeature;
	}

	/**
	 * 
	 * @param warrantActStatus
	 * @return
	 */
	public boolean isWarrantActivationActiveOrNot() {
		return PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE.equalsIgnoreCase(this.warrantActivationStatusId)
				|| PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE
						.equalsIgnoreCase(this.warrantActivationStatusId);
	}

	public boolean isWarrantInactivatable() {
		return this.isAcknowledgeStatusPrintedOrNot() && this.isWarrantActivationActiveOrNot()
				&& this.isWarrantSignedOrNot() && this.isWarrantStatusOpenOrPending();
	}

	/**
	 * 
	 * @param warrantSignedStatus
	 * @return
	 */
	public boolean isWarrantSignedOrNot() {
		return PDJuvenileWarrantConstants.WARRANT_SIGNED.equals(this.warrantSignedStatusId)
				|| PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED.equals(this.warrantSignedStatusId);
	}

	/**
	 * 
	 * @param warrantStatus
	 * @return
	 */
	public boolean isWarrantStatusOpenOrPending()

	{
		return PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN.equals(this.warrantStatusId)
				|| PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING.equals(this.warrantStatusId);

	}

	/**
	 * Removes a pd.codetable.Code from class relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeCautions(Code anObject) {
		initCautions();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		JuvenileWarrantCautionsCode actual = (JuvenileWarrantCautionsCode) new mojo.km.persistence.Reference(
				assocEvent, JuvenileWarrantCautionsCode.class).getObject();
		cautions.remove(actual);
	}

	/**
	 * Removes a pd.juvenilewarrant.Charge from class relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeCharges(Charge anObject) {
		initCharges();
		charges.remove(anObject);
	}

	/**
	 * Removes a pd.juvenilewarrant.JuvenileAssociate from class relationship
	 * collection.
	 * 
	 * @param anObject
	 */
	public void removeJuvenileAssociates(JuvenileAssociate anObject) {
		initJuvenileAssociates();
		juvenileAssociates.remove(anObject);
	}

	/**
	 * Removes a pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeScarsMarks(pd.codetable.person.ScarsMarksTattoosCode anObject) {
		initScarsMarks();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		JuvenileWarrantScarsMarksScarsMarksTattoosCode actual = (JuvenileWarrantScarsMarksScarsMarksTattoosCode) new mojo.km.persistence.Reference(
				assocEvent, JuvenileWarrantScarsMarksScarsMarksTattoosCode.class).getObject();
		scarsMarks.remove(actual);
	}

	/**
	 * Removes a pd.juvenilewarrant.JuvenileWarrantService from class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeServices(JuvenileWarrantService anObject) {
		initServices();
		services.remove(anObject);
	}

	/**
	 * Removes a pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeTattoos(pd.codetable.person.ScarsMarksTattoosCode anObject) {
		initTattoos();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		JuvenileWarrantTattoosScarsMarksTattoosCode actual = (JuvenileWarrantTattoosScarsMarksTattoosCode) new mojo.km.persistence.Reference(
				assocEvent, JuvenileWarrantTattoosScarsMarksTattoosCode.class).getObject();
		tattoos.remove(actual);
	}

	/**
	 * @author asrvastava This method is used to send notification of Juvenile
	 *         Warrant Release Decision
	 */
	public void sendReleaseDecisionNotification(JuvenileWarrant warrant) {
		markModified();
		//JuvenileWarrant warrant =
		// JuvenileWarrant.find(requestEvent.getWarrantNum());
		OfficerProfile officer = null;

		CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
				.getInstance("CreateNotification");
		if (warrant != null) {
			officer = warrant.getOfficer();

		}

		if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_JUVENILE_PROBATION.equals(warrant.getReleaseDecisionId())) {
			notificationEvent.setNotificationTopic("JW.RELEASE.DECISION.DETENTION");
		} else {
			notificationEvent.setNotificationTopic("JW.RELEASE.DECISION.PERSON");
		}
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(warrant.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrant.getWarrantNum());
    	buffer.append(", ");
    	buffer.append("RELEASE");
    	buffer.append(" for ");
    	buffer.append(warrant.getNameLastFirstMiddleSuffix());

    	notificationEvent.setSubject(buffer.toString());
		//notificationEvent.setSubject("Send notification of release decision");

		notificationEvent.addIdentity("officer", (IAddressable) officer);
		notificationEvent.addContentBean(warrant);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(notificationEvent);
	}

	/**
	 * Sets the value of the affidavitStatement property.
	 * 
	 * @param aAffidavitStatement
	 *            the new value of the affidavitStatement property
	 */
	public void setAffidavitStatement(String aAffidavitStatement) {
		if (this.affidavitStatement == null || !this.affidavitStatement.equals(aAffidavitStatement)) {
			markModified();
		}
		affidavitStatement = aAffidavitStatement;
	}

	/**
	 * Sets the value of the aliasName property.
	 * 
	 * @param aAliasName
	 *            the new value of the aliasName property
	 */
	public void setAliasName(String aAliasName) {
		if (this.aliasName == null || !this.aliasName.equals(aAliasName)) {
			markModified();
		}
		aliasName = aAliasName;
	}

	/**
	 * @param date
	 */
	public void setArrestTimeStamp(Date adate) {
		if (this.arrestTimeStamp == null || !this.arrestTimeStamp.equals(adate)) {
			markModified();
		}
		arrestTimeStamp = adate;
	}

	/**
	 * set the type reference for class member build
	 * 
	 * @param build
	 */
	public void setBuild(Code lbuild) {
		if (this.build == null || !this.build.equals(lbuild)) {
			markModified();
		}
		if (lbuild.getOID() == null) {
			new mojo.km.persistence.Home().bind(lbuild);
		}
		setBuildId((String) lbuild.getOID());
		this.build = (Code) new mojo.km.persistence.Reference(lbuild).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param buildId
	 */
	public void setBuildId(String lbuildId) {
		if (this.buildId == null || !this.buildId.equals(lbuildId)) {
			markModified();
		}
		build = null;
		this.buildId = lbuildId;
	}

	/**
	 * set the type reference for class member complexion
	 * 
	 * @param complexion
	 */
	public void setComplexion(Code acomplexion) {
		if (this.complexion == null || !this.complexion.equals(acomplexion)) {
			markModified();
		}
		if (acomplexion.getOID() == null) {
			new mojo.km.persistence.Home().bind(acomplexion);
		}
		setComplexionId((String) acomplexion.getOID());
		this.complexion = (Code) new mojo.km.persistence.Reference(acomplexion).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param complexionId
	 */
	public void setComplexionId(String acomplexionId) {
		if (this.complexionId == null || !this.complexionId.equals(acomplexionId)) {
			markModified();
		}
		complexion = null;
		this.complexionId = acomplexionId;
	}

	/**
	 * Sets the value of the daLogNumber property.
	 * 
	 * @param aDaLogNumber
	 *            the new value of the daLogNumber property
	 */
	public void setDaLogNumber(int aDaLogNumber) {
		if (this.daLogNumber != aDaLogNumber) {
			markModified();
		}
		daLogNumber = aDaLogNumber;
	}

	/**
	 * Sets the value of the dateOfBirth property.
	 * 
	 * @param aDateOfBirth
	 *            the new value of the dateOfBirth property
	 */
	public void setDateOfBirth(Date aDateOfBirth) {
		if (this.dateOfBirth == null || !this.dateOfBirth.equals(aDateOfBirth)) {
			markModified();
		}
		dateOfBirth = aDateOfBirth;
	}

	/**
	 * @param string
	 */
	public void setDateOfBirthSource(String dateOfBirthSource) {
		if (this.dateOfBirthSource == null || !this.dateOfBirthSource.equals(dateOfBirthSource)) {
			markModified();
		}
		this.dateOfBirthSource = dateOfBirthSource;
	}

	/**
	 * Sets the value of the dateOfIssue property.
	 * 
	 * @param aDateOfIssue
	 *            the new value of the dateOfIssue property
	 */
	public void setDateOfIssue(Date aDateOfIssue) {
		if (this.dateOfIssue == null || !this.dateOfIssue.equals(aDateOfIssue)) {
			markModified();
		}
		dateOfIssue = aDateOfIssue;
	}

	/**
	 * set the type reference for class member eyeColor
	 * 
	 * @param eyeColor
	 */
	public void setEyeColor(Code leyeColor) {
		if (this.eyeColor == null || !this.eyeColor.equals(leyeColor)) {
			markModified();
		}
		if (leyeColor.getOID() == null) {
			new mojo.km.persistence.Home().bind(eyeColor);
		}
		setEyeColorId((String) leyeColor.getOID());
		this.eyeColor = (Code) new mojo.km.persistence.Reference(leyeColor).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param eyeColorId
	 */
	public void setEyeColorId(String leyeColorId) {
		if (this.eyeColorId == null || !this.eyeColorId.equals(leyeColorId)) {
			markModified();
		}
		eyeColor = null;
		this.eyeColorId = leyeColorId;
	}

	/**
	 * Sets the value of the fbiNum property.
	 * 
	 * @param aFbiNum
	 *            the new value of the fbiNum property
	 */
	public void setFbiNum(String aFbiNum) {
		if (this.fbiNum == null || !this.fbiNum.equals(aFbiNum)) {
			markModified();
		}
		fbiNum = aFbiNum;
	}

	/**
	 * Sets the value of the fileStampDate property.
	 * 
	 * @param aFileStampDate
	 *            the new value of the fileStampDate property
	 */
	public void setFileStampDate(Date aFileStampDate) {
		if (this.fileStampDate == null || !this.fileStampDate.equals(aFileStampDate)) {
			markModified();
		}
		fileStampDate = aFileStampDate;
	}

	/**
	 * set the type reference for class member fileStampUser
	 * 
	 * @param fileStampUser
	 */
	 //87191
	public void setFileStampUser(UserProfile afileStampUser) {
		/*if (this.fileStampUser == null || !this.fileStampUser.equals(afileStampUser)) {
			markModified();
		}
		if (afileStampUser.getOID() == null) {
			new mojo.km.persistence.Home().bind(afileStampUser);
		}*/
		setFileStampUserId((String) afileStampUser.getUserID());
		this.fileStampUser = afileStampUser;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(afileStampUser)
				//.getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 * 
	 * @param fileStampUserId
	 */
	public void setFileStampUserId(String afileStampUserId) {
		if (this.fileStampUserId == null || !this.fileStampUserId.equals(afileStampUserId)) {
			markModified();
		}
		fileStampUser = null;
		this.fileStampUserId = afileStampUserId;
	}

	/**
	 * Sets the value of the firstName property.
	 * 
	 * @param aFirstName
	 *            the new value of the firstName property
	 */
	public void setFirstName(String aFirstName) {
		if (this.firstName == null || !this.firstName.equals(aFirstName)) {
			markModified();
		}
		firstName = aFirstName;
	}

	/**
	 * set the type reference for class member hairColor
	 * 
	 * @param hairColor
	 */
	public void setHairColor(Code ahairColor) {
		if (this.hairColor == null || !this.hairColor.equals(ahairColor)) {
			markModified();
		}
		if (ahairColor.getOID() == null) {
			new mojo.km.persistence.Home().bind(ahairColor);
		}
		setHairColorId((String) ahairColor.getOID());
		this.hairColor = (Code) new mojo.km.persistence.Reference(ahairColor).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param hairColorId
	 */
	public void setHairColorId(String ahairColorId) {
		if (this.hairColorId == null || !this.hairColorId.equals(ahairColorId)) {
			markModified();
		}
		hairColor = null;
		this.hairColorId = ahairColorId;
	}

	/**
	 * Sets the value of the height property.
	 * 
	 * @param aHeight
	 *            the new value of the height property
	 */
	public void setHeight(String aHeight) {
		if (this.height == null || !this.height.equals(aHeight)) {
			markModified();
		}
		height = aHeight;
	}

	/**
	 * Sets the value of the juvenileNum property.
	 * 
	 * @param aJuvenileNum
	 *            the new value of the juvenileNum property
	 */
	public void setJuvenileNum(Integer aJuvenileNum) {
		if (this.juvenileNum != aJuvenileNum) {
			markModified();
		}
		this.juvenileNum = aJuvenileNum;
	}

	/**
	 * Sets the value of the lastName property.
	 * 
	 * @param aLastName
	 *            the new value of the lastName property
	 */
	public void setLastName(String aLastName) {
		if (this.lastName == null || !this.lastName.equals(aLastName)) {
			markModified();
		}
		lastName = aLastName;
	}

	/**
	 * Sets the value of the middleName property.
	 * 
	 * @param aMiddleName
	 *            the new value of the middleName property
	 */
	public void setMiddleName(String aMiddleName) {
		if (this.middleName == null || !this.middleName.equals(aMiddleName)) {
			markModified();
		}
		middleName = aMiddleName;
	}

	/**
	 * set the type reference for class member officer
	 * 
	 * @param officer
	 */
	public void setOfficer(OfficerProfile aofficer) {
		if (this.officer == null || !this.officer.equals(aofficer)) {
			markModified();
		}
		if (aofficer.getOID() == null) {
			new mojo.km.persistence.Home().bind(aofficer);
		}
		setOfficerId((String) aofficer.getOID());
		this.officer = (OfficerProfile) new mojo.km.persistence.Reference(aofficer).getObject();
	}

	/**
	 * set the type reference for class member officerDepartment
	 * 
	 * @param department
	 */
	public void setOfficerDepartment(Department department) {
		/*if (this.officerDepartment == null || !this.officerDepartment.equals(department)) {
			markModified();
		}*/
		/*if (officerDepartment.getOID() == null) {
			new mojo.km.persistence.Home().bind(department);
		}*/
		setOfficerDepartmentId((String) department.getDepartmentId());
		this.officerDepartment =department;//(pd.contact.agency.Department) new mojo.km.persistence.Reference(department)
				//.getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.agency.Department
	 * 
	 * @param officerDepartmentId
	 */
	public void setOfficerDepartmentId(String officerDepartmentId) {
		if (this.officerDepartmentId == null || !this.officerDepartmentId.equals(officerDepartmentId)) {
			markModified();
		}
		officerDepartment = null;
		this.officerDepartmentId = officerDepartmentId;
	}

	/**
	 * Set the reference value to class :: pd.contact.officer.OfficerProfile
	 * 
	 * @param officerId
	 */
	public void setOfficerId(String aofficerId) {
		if (this.officerId == null || !this.officerId.equals(aofficerId)) {
			markModified();
		}
		officer = null;
		this.officerId = aofficerId;
	}

	/**
	 * Sets the value of the otherCautionComments property.
	 * 
	 * @param aOtherCautionComments
	 *            the new value of the otherCautionComments property
	 */
	public void setOtherCautionComments(String aOtherCautionComments) {
		if (this.otherCautionComments == null || !this.otherCautionComments.equals(aOtherCautionComments)) {
			markModified();
		}
		otherCautionComments = aOtherCautionComments;
	}

	/**
	 * Sets the value of the phoneNum property.
	 * 
	 * @param aPhoneNum
	 *            the new value of the phoneNum property
	 */
	public void setPhoneNum(String aPhoneNum) {
		if (this.phoneNum == null || !this.phoneNum.equals(aPhoneNum)) {
			markModified();
		}
		phoneNum = aPhoneNum;
	}

	/**
	 * set the type reference for class member probationOfficerOfRecord
	 */
	 //87191
	public void setProbationOfficerOfRecord(UserProfile probationOfficerOfRecord) {
		/*if (this.probationOfficerOfRecord == null || !this.probationOfficerOfRecord.equals(probationOfficerOfRecord)) {
			markModified();
		}
		if (probationOfficerOfRecord.getOID() == null) {
			new mojo.km.persistence.Home().bind(probationOfficerOfRecord);
		}*/
		setProbationOfficerOfRecordId((String) probationOfficerOfRecord.getUserID());
		this.probationOfficerOfRecord = probationOfficerOfRecord;/* (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(
				probationOfficerOfRecord).getObject();*/
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 */
	public void setProbationOfficerOfRecordId(String probationOfficerOfRecordId) {
		if (this.probationOfficerOfRecordId == null
				|| !this.probationOfficerOfRecordId.equals(probationOfficerOfRecordId)) {
			markModified();
		}
		probationOfficerOfRecord = null;
		this.probationOfficerOfRecordId = probationOfficerOfRecordId;
	}

	/**
	 * set the type reference for class member race
	 * 
	 * @param race
	 */
	public void setRace(Code lrace) {
		if (this.race == null || !this.race.equals(lrace)) {
			markModified();
		}
		if (lrace.getOID() == null) {
			new mojo.km.persistence.Home().bind(lrace);
		}
		setRaceId((String) lrace.getOID());
		this.race = (Code) new mojo.km.persistence.Reference(lrace).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param raceId
	 */
	public void setRaceId(String lraceId) {
		if (this.raceId == null || !this.raceId.equals(lraceId)) {
			markModified();
		}
		race = null;
		this.raceId = lraceId;
	}

	/**
	 * @param date
	 */
	public void setRecallDate(Date aRecallDate) {
		if (this.recallDate == null || !this.recallDate.equals(aRecallDate)) {
			markModified();
		}
		recallDate = aRecallDate;
	}

	/**
	 * set the type reference for class member recallReason
	 * 
	 * @param recallReason
	 */
	public void setRecallReason(Code lrecallReason) {
		if (this.recallReason == null || !this.recallReason.equals(lrecallReason)) {
			markModified();
		}
		if (lrecallReason.getOID() == null) {
			new mojo.km.persistence.Home().bind(lrecallReason);
		}
		setRecallReasonId((String) lrecallReason.getOID());
		this.recallReason = (Code) new mojo.km.persistence.Reference(lrecallReason).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param recallReasonId
	 */
	public void setRecallReasonId(String lrecallReasonId) {
		if (this.recallReasonId == null || !this.recallReasonId.equals(lrecallReasonId)) {
			markModified();
		}
		recallReason = null;
		this.recallReasonId = lrecallReasonId;
	}

	/**
	 * set the type reference for class member recallUser
	 * 
	 * @param recallUser
	 */
	 //87191
	public void setRecallUser(UserProfile lrecallUser) {
		/*if (this.recallUser == null || !this.recallUser.equals(lrecallUser)) {
			markModified();
		}
		if (lrecallUser.getOID() == null) {
			new mojo.km.persistence.Home().bind(recallUser);
		}*/
		setRecallUserId((String) lrecallUser.getUserID());
		this.recallUser = lrecallUser;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(lrecallUser).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 * 
	 * @param recallUserId
	 */
	public void setRecallUserId(String lrecallUserId) {
		if (this.recallUserId == null || !this.recallUserId.equals(lrecallUserId)) {
			markModified();
		}
		recallUser = null;
		this.recallUserId = lrecallUserId;
	}

	/**
	 * Sets the value of the referralNum property.
	 * 
	 * @param aReferralNum
	 *            the new value of the referralNum property
	 */
	public void setReferralNum(Integer aReferralNum) {
		if (this.referralNum != aReferralNum) {
			markModified();
		}
		this.referralNum = aReferralNum;
	}

	/**
	 * @param string
	 */
	public void setReleaseAssociateNum(String string) {
		if (this.releaseAssociateNum == null || !this.releaseAssociateNum.equals(string)) {
			markModified();
		}
		releaseAssociateNum = string;
	}

	/**
	 * set the type reference for class member releaseDecision
	 * 
	 * @param releaseDecision
	 */
	public void setReleaseDecision(Code areleaseDecision) {
		if (this.releaseDecision == null || !this.releaseDecision.equals(areleaseDecision)) {
			markModified();
		}
		if (areleaseDecision.getOID() == null) {
			new mojo.km.persistence.Home().bind(areleaseDecision);
		}
		setReleaseDecisionId((String) areleaseDecision.getOID());
		this.releaseDecision = (Code) new mojo.km.persistence.Reference(areleaseDecision).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param releaseDecisionId
	 */
	public void setReleaseDecisionId(String areleaseDecisionId) {
		if (this.releaseDecisionId == null || !this.releaseDecisionId.equals(areleaseDecisionId)) {
			markModified();
		}
		releaseDecision = null;
		this.releaseDecisionId = areleaseDecisionId;
	}

	/**
	 * @param date
	 */
	public void setReleaseDecisionTimeStamp(Date date) {
		if (this.releaseDecisionTimeStamp == null || !this.releaseDecisionTimeStamp.equals(date)) {
			markModified();
		}
		releaseDecisionTimeStamp = date;
	}

	/**
	 * set the type reference for class member releaseDecisionUser
	 * 
	 * @param releaseDecisionUser
	 */
	 //87191
	public void setReleaseDecisionUser(UserProfile areleaseDecisionUser) {
		/*if (this.releaseDecisionUser == null || !this.releaseDecisionUser.equals(areleaseDecisionUser)) {
			markModified();
		}
		if (areleaseDecisionUser.getOID() == null) {
			new mojo.km.persistence.Home().bind(areleaseDecisionUser);
		}*/
		setReleaseDecisionUserId((String) areleaseDecisionUser.getUserID());
		this.releaseDecisionUser = areleaseDecisionUser;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(areleaseDecisionUser)
				//.getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 * 
	 * @param releaseDecisionUserId
	 */
	public void setReleaseDecisionUserId(String areleaseDecisionUserId) {
		if (this.releaseDecisionUserId == null || !this.releaseDecisionUserId.equals(areleaseDecisionUserId)) {
			markModified();
		}
		releaseDecisionUser = null;
		this.releaseDecisionUserId = areleaseDecisionUserId;
	}

	/**
	 * set the type reference for class member schoolCode
	 * 
	 * @param schoolCode
	 */
	public void setSchoolCode(JuvenileSchoolDistrictCode aschoolCode) {
		if (this.schoolCode == null || !this.schoolCode.equals(aschoolCode)) {
			markModified();
		}
		if (aschoolCode.getOID() == null) {
			new mojo.km.persistence.Home().bind(aschoolCode);
		}
		setSchoolCodeId((String) aschoolCode.getOID());
		this.schoolCode = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(aschoolCode).getObject();
	}

	/**
	 * Set the reference value to class ::
	 * pd.codetable.person.JuvenileSchoolDistrictCode
	 * 
	 * @param schoolCodeId
	 */
	public void setSchoolCodeId(String aschoolCodeId) {
		if (this.schoolCodeId == null || !this.schoolCodeId.equals(aschoolCodeId)) {
			markModified();
		}
		schoolCode = null;
		this.schoolCodeId = aschoolCodeId;
	}

	/**
	 * set the type reference for class member serviceReturnGeneratedStatus
	 * 
	 * @param serviceReturnGeneratedStatus
	 */
	public void setServiceReturnGeneratedStatus(Code aserviceReturnGeneratedStatus) {
		if (this.serviceReturnGeneratedStatus == null
				|| !this.serviceReturnGeneratedStatus.equals(aserviceReturnGeneratedStatus)) {
			markModified();
		}
		if (aserviceReturnGeneratedStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(aserviceReturnGeneratedStatus);
		}
		setServiceReturnGeneratedStatusId((String) aserviceReturnGeneratedStatus.getOID());
		this.serviceReturnGeneratedStatus = (Code) new mojo.km.persistence.Reference(
				aserviceReturnGeneratedStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param serviceReturnGeneratedStatusId
	 */
	public void setServiceReturnGeneratedStatusId(String aserviceReturnGeneratedStatusId) {
		if (this.serviceReturnGeneratedStatusId == null
				|| !this.serviceReturnGeneratedStatusId.equals(aserviceReturnGeneratedStatusId)) {
			markModified();
		}
		serviceReturnGeneratedStatus = null;
		this.serviceReturnGeneratedStatusId = aserviceReturnGeneratedStatusId;
	}

	/**
	 * set the type reference for class member serviceReturnSignatureStatus
	 * 
	 * @param serviceReturnSignatureStatus
	 */
	public void setServiceReturnSignatureStatus(Code aserviceReturnSignatureStatus) {
		if (this.serviceReturnSignatureStatus == null
				|| !this.serviceReturnSignatureStatus.equals(aserviceReturnSignatureStatus)) {
			markModified();
		}
		if (aserviceReturnSignatureStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(aserviceReturnSignatureStatus);
		}
		setServiceReturnSignatureStatusId((String) aserviceReturnSignatureStatus.getOID());
		this.serviceReturnSignatureStatus = (Code) new mojo.km.persistence.Reference(aserviceReturnSignatureStatus)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param serviceReturnSignatureStatusId
	 */
	public void setServiceReturnSignatureStatusId(String aserviceReturnSignatureStatusId) {
		if (this.serviceReturnSignatureStatusId == null
				|| !this.serviceReturnSignatureStatusId.equals(aserviceReturnSignatureStatusId)) {
			markModified();
		}
		serviceReturnSignatureStatus = null;
		this.serviceReturnSignatureStatusId = aserviceReturnSignatureStatusId;
	}

	/**
	 * set the type reference for class member sex
	 * 
	 * @param sex
	 */
	public void setSex(Code lsex) {
		if (this.sex == null || !this.sex.equals(lsex)) {
			markModified();
		}
		if (lsex.getOID() == null) {
			new mojo.km.persistence.Home().bind(lsex);
		}
		setSexId((String) lsex.getOID());
		this.sex = (Code) new mojo.km.persistence.Reference(lsex).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param sexId
	 */
	public void setSexId(String lsexId) {
		if (this.sexId == null || !this.sexId.equals(lsexId)) {
			markModified();
		}
		sex = null;
		this.sexId = lsexId;
	}

	/**
	 * Sets the value of the sidNum property.
	 * 
	 * @param aSidNum
	 *            the new value of the sidNum property
	 */
	public void setSidNum(String aSidNum) {
		if (this.sidNum == null || !this.sidNum.equals(aSidNum)) {
			markModified();
		}
		sidNum = aSidNum;
	}

	/**
	 * Sets the value of the ssn property.
	 * 
	 * @param aSsn
	 *            the new value of the ssn property
	 */
	public void setSsn(String aSsn) {
		if (this.ssn == null || !this.ssn.equals(aSsn)) {
			markModified();
		}
		ssn = aSsn;
	}

	/**
	 * Sets the value of the suffix property.
	 * 
	 * @param aSuffix
	 *            the new value of the suffix property
	 */
	public void setSuffix(String aSuffix) {
		if (this.suffix == null || !this.suffix.equals(aSuffix)) {
			markModified();
		}
		suffix = aSuffix;
	}

	/**
	 * Sets the value of the transactionNum property.
	 * 
	 * @param aTransactionNum
	 *            the new value of the transactionNum property
	 */
	public void setTransactionNum(int aTransactionNum) {
		if (this.transactionNum != aTransactionNum) {
			markModified();
		}
		transactionNum = aTransactionNum;
	}

	/**
	 * set the type reference for class member transferLocation
	 * 
	 * @param transferLocation
	 */
	public void setTransferLocation(Code ltransferLocation) {
		if (this.transferLocation == null || !this.transferLocation.equals(ltransferLocation)) {
			markModified();
		}
		if (transferLocation.getOID() == null) {
			new mojo.km.persistence.Home().bind(ltransferLocation);
		}
		setTransferLocationId((String) ltransferLocation.getOID());
		this.transferLocation = (Code) new mojo.km.persistence.Reference(ltransferLocation).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param transferLocationId
	 */
	public void setTransferLocationId(String atransferLocationId) {
		if (this.transferLocationId == null || !this.transferLocationId.equals(atransferLocationId)) {
			markModified();
		}
		transferLocation = null;
		this.transferLocationId = atransferLocationId;
	}

	/**
	 * set the type reference for class member transferOfficer
	 * 
	 * @param transferOfficer
	 */
	public void setTransferOfficer(OfficerProfile atransferOfficer) {
		if (this.transferOfficer == null || !this.transferOfficer.equals(atransferOfficer)) {
			markModified();
		}
		if (transferOfficer.getOID() == null) {
			new mojo.km.persistence.Home().bind(atransferOfficer);
		}
		setTransferOfficerId((String) atransferOfficer.getOID());
		this.transferOfficer = (OfficerProfile) new mojo.km.persistence.Reference(atransferOfficer)
				.getObject();
	}

	/**
	 * @param string
	 */
	public void setTransferOfficerDepartmentId(String string) {
		transferOfficerDepartmentId = string;
		if (this.transferOfficerDepartmentId == null || !this.transferOfficerDepartmentId.equals(string)) {
			markModified();
		}
		transferOfficerDepartment = null;
		this.transferOfficerDepartmentId = string;
	}

	/**
	 * Set the reference value to class :: pd.contact.party.Officer
	 * 
	 * @param transferOfficerId
	 */
	public void setTransferOfficerId(String atransferOfficerId) {
		if (this.transferOfficerId == null || !this.transferOfficerId.equals(atransferOfficerId)) {
			markModified();
		}
		transferOfficer = null;
		this.transferOfficerId = atransferOfficerId;
	}

	/**
	 * @param date
	 */
	public void setTransferTimeStamp(Date ldate) {
		if (this.transferTimeStamp == null || !this.transferTimeStamp.equals(ldate)) {
			markModified();
		}
		transferTimeStamp = ldate;
	}

	/**
	 * Sets the value of the unsendNotSignedReason property.
	 * 
	 * @param aUnsendNotSignedReason
	 *            the new value of the unsendNotSignedReason property
	 */
	public void setUnsendNotSignedReason(String aUnsendNotSignedReason) {
		if (this.unsendNotSignedReason == null || !this.unsendNotSignedReason.equals(aUnsendNotSignedReason)) {
			markModified();
		}
		unsendNotSignedReason = aUnsendNotSignedReason;
	}

	/**
	 * Sets the value of the warrantAcknowledgementDate property.
	 * 
	 * @param aWarrantAcknowledgementDate
	 *            the new value of the warrantAcknowledgementDate property
	 */
	public void setWarrantAcknowledgementDate(Date aWarrantAcknowledgementDate) {
		if (this.warrantAcknowledgementDate == null
				|| !this.warrantAcknowledgementDate.equals(aWarrantAcknowledgementDate)) {
			markModified();
		}
		warrantAcknowledgementDate = aWarrantAcknowledgementDate;
	}

	/**
	 * set the type reference for class member warrantAcknowledgeStatus
	 * 
	 * @param warrantAcknowledgeStatus
	 */
	public void setWarrantAcknowledgeStatus(Code lwarrantAcknowledgeStatus) {
		if (this.warrantAcknowledgeStatus == null || !this.warrantAcknowledgeStatus.equals(lwarrantAcknowledgeStatus)) {
			markModified();
		}
		if (lwarrantAcknowledgeStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(lwarrantAcknowledgeStatus);
		}
		setWarrantAcknowledgeStatusId((String) lwarrantAcknowledgeStatus.getOID());
		this.warrantAcknowledgeStatus = (Code) new mojo.km.persistence.Reference(lwarrantAcknowledgeStatus)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param warrantAcknowledgeStatusId
	 */
	public void setWarrantAcknowledgeStatusId(String lwarrantAcknowledgeStatusId) {
		if (this.warrantAcknowledgeStatusId == null
				|| !this.warrantAcknowledgeStatusId.equals(lwarrantAcknowledgeStatusId)) {
			markModified();
		}
		warrantAcknowledgeStatus = null;
		this.warrantAcknowledgeStatusId = lwarrantAcknowledgeStatusId;
	}

	/**
	 * Sets the value of the warrantActivationDate property.
	 * 
	 * @param aWarrantActivationDate
	 *            the new value of the warrantActivationDate property
	 */
	public void setWarrantActivationDate(Date aWarrantActivationDate) {
		if (this.warrantActivationDate == null || !this.warrantActivationDate.equals(aWarrantActivationDate)) {
			markModified();
		}
		warrantActivationDate = aWarrantActivationDate;
	}

	/**
	 * set the type reference for class member warrantActivationStatus
	 * 
	 * @param warrantActivationStatus
	 */
	public void setWarrantActivationStatus(Code lwarrantActivationStatus) {
		if (this.warrantActivationStatus == null || !this.warrantActivationStatus.equals(lwarrantActivationStatus)) {
			markModified();
		}
		if (warrantActivationStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(lwarrantActivationStatus);
		}
		setWarrantActivationStatusId((String) lwarrantActivationStatus.getOID());
		this.warrantActivationStatus = (Code) new mojo.km.persistence.Reference(lwarrantActivationStatus)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param warrantActivationStatusId
	 */
	public void setWarrantActivationStatusId(String lwarrantActivationStatusId) {
		if (this.warrantActivationStatusId == null
				|| !this.warrantActivationStatusId.equals(lwarrantActivationStatusId)) {
			markModified();
		}
		warrantActivationStatus = null;
		this.warrantActivationStatusId = lwarrantActivationStatusId;
	}

	/**
	 * Sets the value of the warrantNum property.
	 * 
	 * @param aWarrantNum
	 *            the new value of the warrantNum property
	 */
	public void setWarrantNum(String aWarrantNum) {
		if (this.warrantNum == null || !this.warrantNum.equals(aWarrantNum)) {
			markModified();
		}
		warrantNum = aWarrantNum;
	}

	/**
	 * @param aWarrantOriginatorCourt
	 */
	public void setWarrantOriginatorCourt(String aWarrantOriginatorCourt) {
		if (this.warrantOriginatorCourt == null || !this.warrantOriginatorCourt.equals(aWarrantOriginatorCourt)) {
			markModified();
		}
		this.warrantOriginatorCourt = aWarrantOriginatorCourt;
	}

	/**
	 * @param aWarrantOriginatorName
	 */
	public void setWarrantOriginatorName(String aWarrantOriginatorName) {
		if (this.warrantOriginatorName == null || !this.warrantOriginatorName.equals(aWarrantOriginatorName)) {
			markModified();
		}
		this.warrantOriginatorName = aWarrantOriginatorName;
	}

	/**
	 * set the type reference for class member warrantOriginatorUser
	 * 
	 * @param warrantOriginatorUser
	 */
	 //87191
	public void setWarrantOriginatorUser(UserProfile lwarrantOriginatorUser) {
		/*if (this.warrantOriginatorUser == null || !this.warrantOriginatorUser.equals(lwarrantOriginatorUser)) {
			markModified();
		}
		if (lwarrantOriginatorUser.getOID() == null) {
			new mojo.km.persistence.Home().bind(lwarrantOriginatorUser);
		}*/
		setWarrantOriginatorUserId((String) lwarrantOriginatorUser.getUserID());
		this.warrantOriginatorUser = lwarrantOriginatorUser;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(
				//lwarrantOriginatorUser).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 * 
	 * @param warrantOriginatorUserId
	 */
	public void setWarrantOriginatorUserId(String lwarrantOriginatorUserId) {
		if (this.warrantOriginatorUserId == null || !this.warrantOriginatorUserId.equals(lwarrantOriginatorUserId)) {
			markModified();
		}
		warrantOriginatorUser = null;
		this.warrantOriginatorUserId = lwarrantOriginatorUserId;
	}

	/**
	 * set the type reference for class member warrantSignedStatus
	 * 
	 * @param warrantSignedStatus
	 */
	public void setWarrantSignedStatus(Code lwarrantSignedStatus) {
		if (this.warrantSignedStatus == null || !this.warrantSignedStatus.equals(lwarrantSignedStatus)) {
			markModified();
		}
		if (lwarrantSignedStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(lwarrantSignedStatus);
		}
		setWarrantSignedStatusId((String) lwarrantSignedStatus.getOID());
		this.warrantSignedStatus = (Code) new mojo.km.persistence.Reference(lwarrantSignedStatus)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param warrantSignedStatusId
	 */
	public void setWarrantSignedStatusId(String lwarrantSignedStatusId) {
		if (this.warrantSignedStatusId == null || !this.warrantSignedStatusId.equals(lwarrantSignedStatusId)) {
			markModified();
		}
		warrantSignedStatus = null;
		this.warrantSignedStatusId = lwarrantSignedStatusId;
	}

	/**
	 * set the type reference for class member warrantStatus
	 * 
	 * @param warrantStatus
	 */
	public void setWarrantStatus(Code lwarrantStatus) {
		if (this.warrantStatus == null || !this.warrantStatus.equals(lwarrantStatus)) {
			markModified();
		}
		if (lwarrantStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(lwarrantStatus);
		}
		setWarrantStatusId((String) lwarrantStatus.getOID());
		this.warrantStatus = (Code) new mojo.km.persistence.Reference(lwarrantStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param warrantStatusId
	 */
	public void setWarrantStatusId(String lwarrantStatusId) {
		if (this.warrantStatusId == null || !this.warrantStatusId.equals(lwarrantStatusId)) {
			markModified();
		}
		warrantStatus = null;
		this.warrantStatusId = lwarrantStatusId;
	}

	/**
	 * set the type reference for class member warrantType
	 * 
	 * @param warrantType
	 */
	public void setWarrantType(Code lwarrantType) {
		if (this.warrantType == null || !this.warrantType.equals(lwarrantType)) {
			markModified();
		}
		if (warrantType.getOID() == null) {
			new mojo.km.persistence.Home().bind(lwarrantType);
		}
		setWarrantTypeId((String) lwarrantType.getOID());
		this.warrantType = (Code) new mojo.km.persistence.Reference(lwarrantType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param warrantTypeId
	 */
	public void setWarrantTypeId(String lwarrantTypeId) {
		if (this.warrantTypeId == null || !this.warrantTypeId.equals(lwarrantTypeId)) {
			markModified();
		}
		warrantType = null;
		this.warrantTypeId = lwarrantTypeId;
	}

	/**
	 * Sets the value of the weight property.
	 * 
	 * @param aWeight
	 *            the new value of the weight property
	 */
	public void setWeight(int aWeight) {
		if (this.weight != aWeight) {
			markModified();
		}
		weight = aWeight;
	}

	public JuvenileWarrantResponseEvent valueObject() {
		IBuilder builder = new JuvenileWarrantBuilder(this);
		builder.build();
		return (JuvenileWarrantResponseEvent) builder.getResult();
	}

	public JuvenileWarrantResponseEvent valueObject(boolean withCharges) {
		JuvenileWarrantBuilder builder = new JuvenileWarrantBuilder(this);
		builder.build();
		if (withCharges == true) {
			builder.setCharges();
		}
		return (JuvenileWarrantResponseEvent) builder.getResult();
	}

	/**
	 * @return
	 */
	public boolean isRecallable() {
		// warrant status (must be OPEN or PENDING)
		boolean recallable = ((PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN.equals(this.warrantStatusId) || PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING
				.equals(this.warrantStatusId))

				// warrant activation status (must be ACTIVE, NOT_ACTIVE, or
				// INACTIVE)
				&& (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE
						.equalsIgnoreCase(this.warrantActivationStatusId)
						|| PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE
								.equalsIgnoreCase(this.warrantActivationStatusId) || PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE
						.equalsIgnoreCase(this.warrantActivationStatusId))

				// warrant acknowledge status (must be NOT_PRINTED OR PRINTED)
				&& (PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED
						.equalsIgnoreCase(this.warrantAcknowledgeStatusId) || PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED
						.equalsIgnoreCase(this.warrantAcknowledgeStatusId))

		// warrant signed status (must be SIGNED or NOT_SIGNED)
		&& (PDJuvenileWarrantConstants.WARRANT_SIGNED.equalsIgnoreCase(this.warrantSignedStatusId) || PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED
				.equalsIgnoreCase(this.warrantSignedStatusId)));

		// OR this should apply
		if (recallable == false) {
			recallable = ((PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED.equals(this.warrantStatusId))
					&& (PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_FILED
							.equalsIgnoreCase(this.serviceReturnSignatureStatusId))
					&& (PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_PRINTED
							.equalsIgnoreCase(this.serviceReturnGeneratedStatusId))
					&& (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE
							.equalsIgnoreCase(this.warrantActivationStatusId))
					&& (PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED
							.equalsIgnoreCase(this.warrantAcknowledgeStatusId)) && (PDJuvenileWarrantConstants.WARRANT_SIGNED
					.equalsIgnoreCase(this.warrantSignedStatusId)));
		}

		return recallable;
	}

	public Date getCreateDate()
	{
	    fetch();
	    return createDate;
	}

	public void setCreateDate(Date createDate)
	{
	    if (this.createDate == null || !this.createDate.equals(createDate)) {
		markModified();
	    }
	    this.createDate = createDate;
	}

	public String getAssociateId()
	{ 
	    fetch();
	    return associateId;
	}

	public void setAssociateId(String associateId)
	{
	    if (this.associateId == null || !this.associateId.equals(associateId)) {
		markModified();
	    }
	    
	    this.associateId = associateId;
	}

	public String getWarrantServeId()
	{
	    fetch();
	    return warrantServeId;
	}

	public void setWarrantServeId(String warrantServeId)
	{
	    if (this.warrantServeId == null || !this.warrantServeId.equals(warrantServeId)) {
		markModified();
	    }
	    
	    this.warrantServeId = warrantServeId;
	}

	public String getAssociateWarrantNum()
	{
	    fetch();
	    return associateWarrantNum;
	}

	public void setAssociateWarrantNum(String associateWarrantNum)
	{

	    if (this.associateWarrantNum == null || !this.associateWarrantNum.equals(associateWarrantNum)) {
		markModified();
	    }
	    
	    this.associateWarrantNum = associateWarrantNum;
	}
}
