package pd.juvenilecase;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.casefile.SubmitCasefileForActivationEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCompositeKeyEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCompositeKeyNoOfficerEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.caseplan.CasePlan;

/**
 * @author asrvastava
 * This class represents a Casefile that is created for a Juvenile and gets assigned to a JPO.
 * JPO's user Id, juvenile number and supervision type make a unique key for this entity.
 */
public class JuvenileCasefile extends PersistentObject
{
	private String supervisionTypeId;
	private String juvenileId;
	private Date supervisionEndDate;
	private String sequenceNumber;
	private Date courtOrderedProbationStartDate;
	
	/**
	 * Properties for casefile closing evaluation and supervision outcome 
	 */
	private String closingEvaluation;
	private String supervisionOutcome;
	private String supervisionOutcomeDescriptionId;
	private Date closingDate;
	//from casefile closing: uncommented as Bug 12703 - MJCW: Controlling Referral Number is Missing From Several Screens
	//Bug 12703first look at controlling ref from closing, if value not found, check controlling ref from activating the casefile
	private String controllingReferral;
	//From activate Casefile
	private String casefileControllingReferralId;
	
	/**
	 * Properties for juvenile search by activity
	 */
	private String	activityCodeId;
	private Date activityDate;
	private String activityTime;
	//add create user from activity	
	private String typeId;	
	private String categoryId;	
	private String permissionId;	
	protected String juvenilefirstName;	
	protected String juvenilelastName;	
	protected String juvenilemiddleName;
	private String juvLocationId;
	private String logonId;
	private String createUser;
	private String latitude;
	private String longitude;
	private String activityEndTime;
	private Date createDate;
	private String daysDiff;
	
	/**
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey SUPERVISION_TYPE
	*/
	private Code supervisionType = null;
	private boolean isMAYSINeeded;
	private boolean school;
	private boolean riskNeed;
	private boolean isBenefitsAssessmentNeeded;
	private boolean subabuse;
	private boolean hispanic;
	private boolean vop;
	// RISK ASSESSMENT FLAGS
	private boolean isReferralRiskNeeded;
	private boolean isInterviewRiskNeeded;
	private boolean isTestingRiskNeeded;
	private boolean isResidentialRiskNeeded;
	private boolean isCommunityRiskNeeded;
	private boolean isProgressRiskNeeded;
	private boolean isResProgressRiskNeeded;
	private String juvLocation;
	private String zipCode; //#32659 changes
	private boolean isPrimaryContact;//#32659 hot fix changes.
	private Date famMemCreateDate;//#32659 hot fix changes.
	private boolean isInHomeStatus; //#32659 hot fix changes.
	
	/**
	* Properties for assignments
	* @detailerDoNotGenerate false
	* @referencedType pd.juvenilecase.Assignment
	* @associationType composition
	*/
	private Collection assignments = null;
	private String caseStatusId;
	private Date activationDate;
	
	/**
	* Properties for probationOfficer
	* @detailerDoNotGenerate false
	* @referencedType pd.contact.user.UserProfile
	*/
	private OfficerProfile probationOfficer = null;
	private String probationOfficerId;
	
	private String officerFirstNameData;
	private String officerMiddleNameData;
	private String officerLastNameData;
	private String officerLogonIdData;
	
	/**
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey JUV_CASE_STATUS
	*/
	private Code caseStatus = null;
	/**
	* Properties for juvenile
	* @detailerDoNotGenerate false
	* @referencedType pd.juvenilecase.Juvenile
	*/
	//private Juvenile juvenile = null;
	private JuvenileCore juvenile = null;
	private Juvenile nonCoreJuvenile = null;

	/**
	* Properties for caseplan
	* @detailerDoNotGenerate false
	* @referencedType pd.juvenilecase.caseplan.CasePlan
	*/
	private CasePlan caseplan = null;
	private String caseplanId;

	private String juvenileNameType;
	
	private Date assignmentAddDate;
	private Date jpoAssignmentDate;
	private String casefileId;
	//added for US 87986
	private String rectype;
	//added for task 126756
	private String probationFlag;
	
	
	/**
	* @param casefileId
	* @return JuvenileCasefile
	*/
	static public JuvenileCasefile find(String casefileId)
	{
		IHome home = new Home();
		JuvenileCasefile casefile = (JuvenileCasefile) home.find(casefileId, JuvenileCasefile.class);
		return casefile;
	}
	/**
	* @param jpoUserId
	* @param juvenileNum
	* @param supervisionTypeId
	* @return JuvenileCasefile
	*/
		
	static public JuvenileCasefile find(String jpoUserId, String juvenileNum, String supervisionTypeId)
	{
		JuvenileCasefile casefile = null;
		GetJuvenileCasefileByCompositeKeyEvent queryEvent = new GetJuvenileCasefileByCompositeKeyEvent();
		queryEvent.setJpoUserId(jpoUserId);
		queryEvent.setJuvenileNum(juvenileNum);
		queryEvent.setSupervisionTypeId(supervisionTypeId);
		Iterator casefiles = findAll(queryEvent);
		if (casefiles.hasNext())
		{
			casefile = (JuvenileCasefile) casefiles.next();
			if(casefile.getJuvenileId() == null || casefile.getJuvenileId().equals("")){
				casefile.setJuvenileId(juvenileNum);
			}
		}
		return casefile;
	}
	
	static public JuvenileCasefile find(String juvenileNum, String supervisionTypeId)
	{
		JuvenileCasefile casefile = null;
		GetJuvenileCasefileByCompositeKeyNoOfficerEvent queryEvent = new GetJuvenileCasefileByCompositeKeyNoOfficerEvent();
		queryEvent.setJuvenileNum(juvenileNum);
		queryEvent.setSupervisionTypeId(supervisionTypeId);
		Iterator casefiles = findAll(queryEvent);
		if (casefiles.hasNext())
		{
			casefile = (JuvenileCasefile) casefiles.next();
		}
		return casefile;
	}

	
	/**
	* Finds casefiles by a certain event
	* @param event
	* @return Iterator of casefiles
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator casefiles = home.findAll(event, JuvenileCasefile.class);
		return casefiles;
	}
	/**
	* Finds all casefiles by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator casefiles = home.findAll(attributeName, attributeValue, JuvenileCasefile.class);
		return casefiles;
	}
	/**
	* @roseuid 4276845901E4
	*/
	public JuvenileCasefile()
	{
	}
	/**
	* @roseuid 427136D903A9
	* @param theSupervisionType
	* @param theProbationOfficerId
	* @param theJuvenileNumber
	* @param theServiceUnit
	* @param theReferralNumber
	* @param theAssignmentLevel
	* @param theAssignmentAddDate
	*/
	public void associateAssignmentToCaseFile(
		int theSupervisionType,
		int theProbationOfficerId,
		int theJuvenileNumber,
		int theServiceUnit,
		int theReferralNumber,
		int theAssignmentLevel,
		int theAssignmentAddDate)
	{
		markModified();
	}


	/**
	 * Activate the casefile from the Pending state. If the state is not Pending then
	 * no change is made.
	 */
	public void activateCasefile(SubmitCasefileForActivationEvent evt)
	{
		if ( getCaseStatusId().equals("P") )
		{
			this.setCaseStatusId( "A" );
			this.setActivationDate( new Date() );
			this.setSupervisionEndDate(evt.getSupervisionEndDate());
			this.setRectype("CASEFILE");
			this.setSubabuse(true);
			this.setCasefileControllingReferralId(evt.getCasefileControllingReferralId());
			this.setCourtOrderedProbationStartDate(evt.getCourtOrderedProbationStartDate());
			this.initNonCoreJuvenile();
			if ( this.nonCoreJuvenile != null ) {
			    if ( this.nonCoreJuvenile.getHispanic() != null
				    && this.nonCoreJuvenile.getHispanic().length() > 0 
				    && this.nonCoreJuvenile.getIsUSCitizenId() != null
				    && this.nonCoreJuvenile.getIsUSCitizenId().length() > 0 ) {
				this.setHispanic( false );
			    } else {
				this.setHispanic( true );
			    }
			}
		}
	}

	/**
	* @roseuid 427136D9038E
	*/
	public void bind()
	{
		markModified();
	}
	/**
	* Clears all pd.juvenilecase.Assignment from class relationship collection.
	* @roseuid 4277CAAC037A
	*/
	public void clearAssignments()
	{
		initAssignments();
		assignments.clear();
	}
	/**
	* @roseuid 427136D903B9
	* @param supervisionType
	* @param status
	* @param probationOfficerId
	* @param juvenileNumber
	* @return Iterator
	* @return java.util.Iterator
	*/
	public Iterator findAll(int theSupervisionType, int theStatus, int theProbationOfficerId, int theJuvenileNumber)
	{
		fetch();
		return null;
	}
	/**
	* Access method for the activationDate property.
	* @return the current value of the activationDate property
	*/
	public Date getActivationDate()
	{
		fetch();
		return activationDate;
	}
	/**
	* Access method for the assignments property.
	* @return the current value of the assignments property
	*/
	public Collection getAssignments()
	{
		fetch();
		initAssignments();
		return assignments;
	}
	/**
	* Access method for the caseStatus property.
	* @return the current value of the caseStatus property
	*/
	public Code getCaseStatus()
	{
		initCaseStatus();
		fetch();
		return caseStatus;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @roseuid 4277CAAC03A9
	* @return java.lang.String
	*/
	public String getCaseStatusId()
	{
		fetch();
		return caseStatusId;
	}
	/**
	* Determines if the isMAYSINeeded property is true.
	* @return true if the isMAYSINeeded property is true
	*/
	public boolean getIsMAYSINeeded()
	{
		fetch();
		return isMAYSINeeded;
	}
	
	public boolean getSchool()
	{
		fetch();
		return school;
	}
	
	public boolean getRiskNeed()
	{
		fetch();
		return riskNeed;
	}
	
	
	/**
	* Access method for the juvenile property.
	* @return the current value of the juvenile property
	*/
	//public Juvenile getJuvenile()
	public JuvenileCore getJuvenile()
	{
		initJuvenile();
		fetch();
		return juvenile;
	}
	
	/**
	* Access method for the caseplan property.
	* @return the current value of the caseplan property
	*/
	public CasePlan getCaseplan()
	{
		initCaseplan();
		fetch();
		return caseplan;
	}
	
	/**
	* Get the reference value to class :: pd.juvenilecase.Juvenile
	*/
	public String getJuvenileNum()
	{
		fetch();
		return juvenileId;
	}
	/**
	* Access method for the probationOfficer property.
	* @return the current value of the probationOfficer property
	*/
	public OfficerProfile getProbationOfficer()
	{
		initProbationOfficer();
		fetch();
		return probationOfficer;
	}
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	* @roseuid 4277CAAD001F
	* @return java.lang.String
	*/
	public String getProbationOfficerId()
	{
		fetch();
		return probationOfficerId;
	}
	/**
	* Access method for the supervisionType property.
	* @return the current value of the supervisionType property
	*/
	public Code getSupervisionType()
	{
		initSupervisionType();
		fetch();
		return supervisionType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @roseuid 4277CAAD008C
	* @return java.lang.String
	*/
	public String getSupervisionTypeId()
	{
		fetch();
		return supervisionTypeId;
	}
	/**
	* Initialize class relationship implementation for pd.juvenilecase.Assignment
	*/
	private void initAssignments()
	{
		if (assignments == null)
		{
			assignments = new mojo.km.persistence.ArrayList(Assignment.class, "caseFileId", "" + getOID());
		}				
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	* @roseuid 4277CAAC03B9
	*/
	private void initCaseStatus()
	{
		if (caseStatus == null)
		{
			caseStatus =
				(Code) new mojo
					.km
					.persistence
					.Reference(caseStatusId, Code.class, "JUV_CASE_STATUS")
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.Juvenile
	* @roseuid 4277CAAC02EE
	*/
	private void initJuvenile()
	{
		if (juvenile == null)
		{
			//juvenile =
				//  (pd.juvenile.Juvenile) new mojo.km.persistence.Reference(juvenileId, pd.juvenile.Juvenile.class).getObject();
		    	// Profile stripping fix task 97536
			//juvenile = Juvenile.find(juvenileId);
		    	juvenile = JuvenileCore.findCore(juvenileId);
		}
	}
	
	private void initNonCoreJuvenile(){
	    if ( nonCoreJuvenile == null ) {
		nonCoreJuvenile = Juvenile.findJCJuvenile(juvenileId);
	    }
	}
	
	
	/**
	* Initialize class relationship to class pd.juvenilecase.caseplan.CasePlan
	* @roseuid 4277CAAC02EE
	*/
	private void initCaseplan()
	{
		if (caseplan == null)
		{
			caseplan =
				(CasePlan) new mojo.km.persistence.Reference(caseplanId, CasePlan.class).getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	* @roseuid 4277CAAD003E
	*/
	private void initProbationOfficer()
	{
		if (probationOfficer == null)
		{
			probationOfficer =
				(OfficerProfile) new mojo
					.km
					.persistence
					.Reference(probationOfficerId, OfficerProfile.class)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	* @roseuid 4277CAAD009C
	*/
	private void initSupervisionType()
	{
		if (supervisionType == null)
		{
			supervisionType =
				(Code) new mojo
					.km
					.persistence
					.Reference(supervisionTypeId, Code.class, "SUPERVISION_TYPE")
					.getObject();
		}
	}
	/**
	* insert a pd.juvenilecase.Assignment into class relationship collection.
	* @roseuid 4277CAAC033C
	* @param anObject
	*/
	public void insertAssignments(Assignment anObject)
	{
		initAssignments();
		assignments.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.Assignment from class relationship collection.
	* @roseuid 4277CAAC035B
	* @param anObject
	*/
	public void removeAssignments(Assignment anObject)
	{
		initAssignments();
		assignments.remove(anObject);
	}
	/**
	* @roseuid 427136D9039A
	*/
	public void sendCaseFileAssignmentNotification()
	{
		markModified();
	}
	/**
	* @roseuid 427136D9039B
	* @param caseFileId
	* @param notificationType
	*/
	public void sendNotification(String caseFileId, int notificationType)
	{
		markModified();
	}
	/**
	* Sets the value of the activationDate property.
	* @roseuid 42778BB701A5
	* @param aActivationDate the new value of the activationDate property
	*/
	public void setActivationDate(Date aActivationDate)
	{
		if (this.activationDate == null || !this.activationDate.equals(aActivationDate))
		{
			markModified();
		}
		this.activationDate = aActivationDate;
	}
	/**
	* Sets the value of the assignments property.
	* @param aAssignments the new value of the assignments property
	*/
	public void setAssignments(Collection aAssignments)
	{
		if (this.assignments == null || !this.assignments.equals(aAssignments))
		{
			markModified();
		}
		assignments = aAssignments;
	}
	/**
	* set the type reference for class member caseStatus
	* @roseuid 4277CAAC03D8
	* @param caseStatus
	*/
	public void setCaseStatus(Code aCaseStatus)
	{
		if (this.caseStatus == null || !this.caseStatus.equals(aCaseStatus))
		{
			markModified();
		}
		if (aCaseStatus.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCaseStatus);
		}
		setCaseStatusId("" + aCaseStatus.getOID());
		this.caseStatus = (Code) new mojo.km.persistence.Reference(aCaseStatus).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @roseuid 4277CAAC0399
	* @param caseStatusId
	*/
	public void setCaseStatusId(String aCaseStatusId)
	{
		if (this.caseStatusId == null || !this.caseStatusId.equals(aCaseStatusId))
		{
			markModified();
		}
		caseStatus = null;
		this.caseStatusId = aCaseStatusId;
	}
	/**
	* Sets the value of the isMAYSINeeded property.
	* @param aIsMAYSINeeded the new value of the isMAYSINeeded property
	*/
	public void setIsMAYSINeeded(boolean aIsMAYSINeeded)
	{
		if (this.isMAYSINeeded != aIsMAYSINeeded)
		{
			markModified();
		}
		isMAYSINeeded = aIsMAYSINeeded;
	}
	
	public void setSchool(boolean aschool)
	{
		if (this.school != aschool)
		{
			markModified();
		}
		school = aschool;
	}
	
	public void setRiskNeed(boolean ariskNeed)
	{
		if (this.isMAYSINeeded != ariskNeed)
		{
			markModified();
		}
		riskNeed = ariskNeed;
	}
	
	/**
	* set the type reference for class member juvenile
	* @roseuid 4277CAAC02FD
	* @param juvenile
	*/
	public void setJuvenile(Juvenile aJuvenile)
	{
		if (this.juvenile == null || !this.juvenile.equals(aJuvenile))
		{
			markModified();
		}
		if (aJuvenile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aJuvenile);
		}
		setJuvenileId("" + aJuvenile.getOID());
		this.juvenile = (Juvenile) new mojo.km.persistence.Reference(aJuvenile).getObject();
	}
	
	/**
	* set the type reference for class member caseplan
	* @param juvenile
	*/
	public void setJuvenile(CasePlan aCaseplan)
	{
		if (this.caseplan == null || !this.juvenile.equals(aCaseplan))
		{
			markModified();
		}
		if (aCaseplan.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCaseplan);
		}
		setCaseplanId("" + aCaseplan.getOID());
		this.caseplan = (CasePlan) new mojo.km.persistence.Reference(aCaseplan).getObject();
	}
	
	/**
	* set the type reference for class member probationOfficer
	* @roseuid 4277CAAD004E
	* @param probationOfficer
	*/
	public void setProbationOfficer(OfficerProfile aProbationOfficer)
	{
		if (this.probationOfficer == null || !this.probationOfficer.equals(aProbationOfficer))
		{
			markModified();
		}
		if (aProbationOfficer.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aProbationOfficer);
		}
		setProbationOfficerId("" + aProbationOfficer.getOID());
		this.probationOfficer = (OfficerProfile) new mojo.km.persistence.Reference(aProbationOfficer).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	* @roseuid 4277CAAD000F
	* @param probationOfficerId
	*/
	public void setProbationOfficerId(String aProbationOfficerId)
	{
		if (this.probationOfficerId == null || !this.probationOfficerId.equals(aProbationOfficerId))
		{
			markModified();
		}
		probationOfficer = null;
		this.probationOfficerId = aProbationOfficerId;
	}
	/**
	* set the type reference for class member supervisionType
	* @roseuid 4277CAAD00AB
	* @param supervisionType
	*/
	public void setSupervisionType(Code aSupervisionType)
	{
		if (this.supervisionType == null || !this.supervisionType.equals(aSupervisionType))
		{
			markModified();
		}
		if (supervisionType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSupervisionType);
		}
		setSupervisionTypeId("" + aSupervisionType.getOID());
		this.supervisionType = (Code) new mojo.km.persistence.Reference(aSupervisionType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @roseuid 4277CAAD006D
	* @param supervisionTypeId
	*/
	public void setSupervisionTypeId(String aSupervisionTypeId)
	{
		if (this.supervisionTypeId == null || !this.supervisionTypeId.equals(aSupervisionTypeId))
		{
			markModified();
		}
		supervisionType = null;
		this.supervisionTypeId = aSupervisionTypeId;
	}
	/**
	* @roseuid 427136D9039E
	*/
	public void update()
	{
		markModified();
	}
	/**
	* @return 
	*/
	public Date getSupervisionEndDate()
	{
		fetch();
		return supervisionEndDate;
	}
	/**
	* @param date
	*/
	public void setSupervisionEndDate(Date date)
	{
		if (this.supervisionEndDate == null || !this.supervisionEndDate.equals(date))
		{
			markModified();
		}
		supervisionEndDate = date;
	}
	/**
	* @return 
	*/
	public Date getCourtOrderedProbationStartDate()
	{
		fetch();
		return courtOrderedProbationStartDate;
	}
	/**
	* @param date
	*/
	public void setCourtOrderedProbationStartDate(Date date)
	{
		if (this.courtOrderedProbationStartDate == null || !this.courtOrderedProbationStartDate.equals(date))
		{
			markModified();
		}
		courtOrderedProbationStartDate = date;
	}	
	
	/**
	* Set the reference value to class :: pd.juvenile.Juvenile
	*/
	public void setJuvenileId(String juvenileId)
	{
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId))
		{
			markModified();
		}
		juvenile = null;
		this.juvenileId = juvenileId;
	}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.caseplan.CasePlan
	*/
	public void setCaseplanId(String caseplanId)
	{
		if (this.caseplanId == null || !this.caseplanId.equals(caseplanId))
		{
			markModified();
		}
		caseplan = null;
		this.caseplanId = caseplanId;
	}
	/**
	* Get the reference value to class :: pd.juvenile.Juvenile
	*/
	public String getJuvenileId()
	{
		fetch();
		return juvenileId;
	}

	/**
	* Get the reference value to class :: pd.juvenilecase.caseplan.CasePlan
	*/
	public String getCaseplanId()
	{
		fetch();
		return caseplanId;
	}

	public void setJuvenileNameType(String juvenileNameType){
		if (this.juvenileNameType == null || !this.juvenileNameType.equals(juvenileNameType)){
			markModified();
		}
		this.juvenileNameType = juvenileNameType;
		
	}
	
	public String getJuvenileNameType(){
		fetch();
		return juvenileNameType;
	}


	public String getProbationOfficerFullName()
	{
		StringBuffer fullName = new StringBuffer();

		OfficerProfile officer = getProbationOfficer();
		if(officer!=null){
			fullName.append(officer.getFirstName());
			fullName.append(" ");
			//fullName.append( officer.getMiddleName() );
			//fullName.append( " " );
			fullName.append(officer.getLastName());
		}

		return fullName.toString();
	}

	//	/**
	//	* @return 
	//	*/
	//	public String getJuvenileCasefileId() {
	//		return "" + getOID();
	//	}
	
	/**
	 * Derived property from other risk assessment properties.
	 * 
	 * @return true if any of the other risk assessments are required.
	 */
	public boolean getIsRiskAssessmentNeeded()
	{
		//changed for User Story 14459
		/*
		return	getIsCommunityRiskNeeded() ||
				getIsInterviewRiskNeeded() ||
				getIsProgressRiskNeeded() ||
				getIsReferralRiskNeeded() ||
				getIsResidentialRiskNeeded() ||
				getIsTestingRiskNeeded();*/
		
		return	getIsCommunityRiskNeeded() ||
				getIsProgressRiskNeeded() ||
				getIsResProgressRiskNeeded() ||
				getIsReferralRiskNeeded();    //49800 Remove ResidentialRisk
	}

	/**
	 * @return
	 */
	public boolean getIsCommunityRiskNeeded()
	{
		fetch();
		return isCommunityRiskNeeded;
	}

	/**
	 * @return
	 */
	public boolean getIsInterviewRiskNeeded()
	{
		fetch();
		return isInterviewRiskNeeded;
	}

	/**
	 * @return
	 */
	public boolean getIsProgressRiskNeeded()
	{
		fetch();
		return isProgressRiskNeeded;
	}
	/**
	 * @return
	 */
	public boolean getIsResProgressRiskNeeded() {
		fetch();
		return isResProgressRiskNeeded;
	}

	/**
	 * @return
	 */
	public boolean getIsReferralRiskNeeded()
	{
		fetch();
		return isReferralRiskNeeded;
	}

	/**
	 * @return
	 */
	public boolean getIsResidentialRiskNeeded()
	{
		fetch();
		return isResidentialRiskNeeded;
	}

	/**
	 * @return
	 */
	public boolean getIsTestingRiskNeeded()
	{
		fetch();
		return isTestingRiskNeeded;
	}

	/**
	 * @param b
	 */
	public void setIsCommunityRiskNeeded(boolean b)
	{
		if (this.isCommunityRiskNeeded != b)
		{
			markModified();
		}
		isCommunityRiskNeeded = b;
	}

	/**
	 * @param b
	 */
	public void setIsInterviewRiskNeeded(boolean b)
	{
		if (this.isInterviewRiskNeeded != b)
		{
			markModified();
		}
		isInterviewRiskNeeded = b;
	}

	/**
	 * @param b
	 */
	public void setIsProgressRiskNeeded(boolean b)
	{
		if (this.isProgressRiskNeeded != b)
		{
			markModified();
		}
		isProgressRiskNeeded = b;
	}

	

	/**
	 * @param b
	 */
	public void setIsResProgressRiskNeeded(boolean b) {
		if(this.isResProgressRiskNeeded != b)
		{
			markModified();
		}
		this.isResProgressRiskNeeded = b;
	}
	/**
	 * @param b
	 */
	public void setIsReferralRiskNeeded(boolean b)
	{
		if (this.isReferralRiskNeeded != b)
		{
			markModified();
		}
		isReferralRiskNeeded = b;
	}

	/**
	 * @param b
	 */
	public void setIsResidentialRiskNeeded(boolean b)
	{
		if (this.isResidentialRiskNeeded != b)
		{
			markModified();
		}
		isResidentialRiskNeeded = b;
	}

	/**
	 * @param b
	 */
	public void setIsTestingRiskNeeded(boolean b)
	{
		if (this.isTestingRiskNeeded != b)
		{
			markModified();
		}
		isTestingRiskNeeded = b;
	}

	/**
	 * @return
	 */
	public boolean getIsBenefitsAssessmentNeeded()
	{
		fetch();
		return isBenefitsAssessmentNeeded;
	}

	/**
	 * @param b
	 */
	public void setIsBenefitsAssessmentNeeded(boolean b)
	{
		if (this.isBenefitsAssessmentNeeded != b)
		{
			markModified();
		}
		isBenefitsAssessmentNeeded = b;
	}

	/**
	 * @return
	 */
	public String getJuvLocation()
	{
		fetch();
		return juvLocation;
	}

	/**
	 * @return
	 */
	public String getOfficerFirstName()
	{
		fetch();
		if(getProbationOfficer()==null)
			return "";
		return getProbationOfficer().getFirstName();
	}

	/**
	 * @return
	 */
	public String getOfficerLastName()
	{
		fetch();
		if(getProbationOfficer()==null)
					return "";
		return getProbationOfficer().getLastName();
	}

	/**
	 * @return
	 */
	public String getOfficerMiddleName()
	{
		fetch();
		if(getProbationOfficer()==null)
					return "";
		return getProbationOfficer().getMiddleName();
	}

	/**
	 * @param string
	 */
	public void setJuvLocation(String string)
	{
		if (this.juvLocation == null || !this.juvLocation.equals(string))
		{
			markModified();
		}
		this.juvLocation = string;
	}

	/**
	 * @return
	 */
	public String getClosingEvaluation()
	{
		fetch();
		return closingEvaluation;
	}

	/**
	 * @param string
	 */
	public void setClosingEvaluation(String closingEvaluation)
	{
		if (this.closingEvaluation == null || !this.closingEvaluation.equals(closingEvaluation))
		{
			markModified();
		}
		this.closingEvaluation = closingEvaluation;
	}
	/**
	 * @return
	 */
	public String getSupervisionOutcome()
	{
		fetch();
		return supervisionOutcome;
	}

	/**
	 * @param string
	 */
	public void setSupervisionOutcome(String supervisionOutcome)
	{
		if (this.supervisionOutcome == null || !this.supervisionOutcome.equals(supervisionOutcome))
		{
			markModified();
		}
		this.supervisionOutcome = supervisionOutcome;
	}

	/**
	 * @return the supervisionOutcomeDescriptionId
	 */
	public String getSupervisionOutcomeDescriptionId()
	{
		fetch();
		return supervisionOutcomeDescriptionId;
	}
	
	/**
	 * @param supervisionOutcomeDescriptionId the supervisionOutcomeDescriptionId to set
	 */
	public void setSupervisionOutcomeDescriptionId( String supervisionOutcomeDescriptionId)
	{
		if (this.supervisionOutcomeDescriptionId == null || !this.supervisionOutcomeDescriptionId.equals(supervisionOutcomeDescriptionId))
		{
			markModified();
		}
		this.supervisionOutcomeDescriptionId = supervisionOutcomeDescriptionId;
	}
	
	/**
	 * @return Returns the officeFirstNameData.
	 */
	public String getOfficerFirstNameData() {
		fetch();
		return (this.officerFirstNameData == null)?"":this.officerFirstNameData;
	}
	/**
	 * @param officeFirstNameData The officeFirstNameData to set.
	 */
	public void setOfficerFirstNameData(String officerFirstNameData) {
		if (this.officerFirstNameData == null || !this.officerFirstNameData.equals(officerFirstNameData))
		{
			markModified();
		}
		this.officerFirstNameData = officerFirstNameData;
	}
	/**
	 * @return Returns the officerLastNameData.
	 */
	public String getOfficerLastNameData() {
		fetch();
		return (this.officerLastNameData == null)?"":this.officerLastNameData;
	}
	/**
	 * @param officerLastNameData The officerLastNameData to set.
	 */
	public void setOfficerLastNameData(String officerLastNameData) {
		if (this.officerLastNameData == null || !this.officerLastNameData.equals(officerLastNameData))
		{
			markModified();
		}
		this.officerLastNameData = officerLastNameData;
	}
	/**
	 * @return Returns the officerLogonIdData.
	 */
	public String getOfficerLogonIdData() {
		fetch();
		return officerLogonIdData;
	}
	/**
	 * @param officerLogonIdData The officerLogonIdData to set.
	 */
	public void setOfficerLogonIdData(String officerLogonIdData) {
		if (this.officerLogonIdData == null || !this.officerLogonIdData.equals(officerLogonIdData))
		{
			markModified();
		}
		this.officerLogonIdData = officerLogonIdData;
	}
	/**
	 * @return Returns the officerMiddleNameData.
	 */
	public String getOfficerMiddleNameData() {
		return officerMiddleNameData;
	}
	/**
	 * @param officerMiddleNameData The officerMiddleNameData to set.
	 */
	public void setOfficerMiddleNameData(String officerMiddleNameData) {
		if (this.officerMiddleNameData == null || !this.officerMiddleNameData.equals(officerMiddleNameData))
		{
			markModified();
		}
		this.officerMiddleNameData = officerMiddleNameData;
	}
	/**
	 * @return
	 */
	public String getSequenceNumber()
	{
		fetch();
		return sequenceNumber;
	}

	/**
	 * @param string
	 */
	public void setSequenceNumber(String sequenceNum)
	{
		if (this.sequenceNumber == null || !this.sequenceNumber.equals(sequenceNum))
		{
			markModified();
		}
		this.sequenceNumber = sequenceNum;
	}
	
	public String getSupervisionCategoryId(){
		IHome home = new Home();
		Iterator i = home.findAll("supervisionTypeId", supervisionTypeId, SupervisionTypeMap.class);
		String catId = null;
		if(i.hasNext())
		{
		  SupervisionTypeMap typeMap = (SupervisionTypeMap) i.next();
		  catId = typeMap.getSupervisionCatId();
		}
		return catId;
	}
	
	public String getSpecialityCategoryId(){
	    	String specialtyCatId = null;
	    	IHome home = new Home();
		Iterator i = home.findAll("supervisionTypeId", supervisionTypeId, SupervisionTypeTJJDMap.class);
		if(i.hasNext())
		{
		    SupervisionTypeTJJDMap typeTJJMap = (SupervisionTypeTJJDMap) i.next();
		    specialtyCatId = typeTJJMap.getSplCategoryId();
		}
		
		return specialtyCatId;
	    	
	}
	
	public String getFormattedProbationOfficer(){
		fetch();
		OfficerProfile officer = this.getProbationOfficer();
		StringBuffer fullName = new StringBuffer();
		if(officer!=null){
			
			if(officer.getFirstName()!=null){
				fullName.append(officer.getFirstName());
				fullName.append(" ");
			}
			if(officer.getLastName()!=null)
				fullName.append(officer.getLastName());
		}

		return fullName.toString();
	}
	/**
	 * @return the assignmentAddDate
	 */
	public Date getAssignmentAddDate() {
		return assignmentAddDate;
	}
	/**
	 * @param assignmentAddDate the assignmentAddDate to set
	 */
	public void setAssignmentAddDate(Date assignmentAddDate) {
		this.assignmentAddDate = assignmentAddDate;
	}
	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public Date getJpoAssignmentDate() {
		return jpoAssignmentDate;
	}
	public void setJpoAssignmentDate(Date jpoAssignmentDate) {
		this.jpoAssignmentDate = jpoAssignmentDate;
	}
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	public String getControllingReferral() {
		return controllingReferral;
	}
	public void setControllingReferral(String controllingReferral) {
		this.controllingReferral = controllingReferral;
	}

	public String getCasefileControllingReferralId() {
		fetch();
		return casefileControllingReferralId;
	}
	
	public void setCasefileControllingReferralId(String casefileControllingReferralId) {
		if (this.casefileControllingReferralId == null || !this.casefileControllingReferralId.equals(casefileControllingReferralId))
		{
			markModified();
		}
		this.casefileControllingReferralId = casefileControllingReferralId;
	}
	
	/**
	 * override the superclass to support prod support updatinng of createTimestamp (normally audit value that is not updated)
	 * @param timestamp
	 */
	public void setCreateTimestamp(Timestamp timestamp)
	{
		if (timestamp != null)
		{
			markModified();
		}
		super.setCreateTimestamp(timestamp);
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		fetch();
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		if (this.zipCode == null || !this.zipCode.equals(zipCode))
		{
			markModified();
		}
		this.zipCode = zipCode;
	}
	
	/**
	 * @return the famMemCreateDate
	 */
	public Date getFamMemCreateDate() {
		fetch();
		return famMemCreateDate;
	}
	/**
	 * @param famMemCreateDate the famMemCreateDate to set
	 */
	public void setFamMemCreateDate(Date famMemCreateDate) {
		if (this.famMemCreateDate == null || !this.famMemCreateDate.equals(famMemCreateDate))
		{
			markModified();
		}
		this.famMemCreateDate = famMemCreateDate;
	}
	/**
	 * @return
	 */
	public boolean getIsPrimaryContact()
	{
		fetch();
		return isPrimaryContact;
	}

	/**
	 * @param primaryContact
	 */
	public void setIsPrimaryContact(boolean primaryContact)
	{
		if (this.isPrimaryContact != primaryContact)
		{
			markModified();
		}
		isPrimaryContact = primaryContact;
	}

	
	/**
	 * @return
	 */
	public boolean getIsInHomeStatus()
	{
		fetch();
		return isInHomeStatus;
	}

	/**
	 * @param primaryContact
	 */
	public void setIsInHomeStatus(boolean isInHomeStatus)
	{
		if (this.isInHomeStatus != isInHomeStatus)
		{
			markModified();
		}
		this.isInHomeStatus = isInHomeStatus;
	}
	
	public String getRectype()
	{
	    fetch();
	    return rectype;
	}
	
	public void setRectype(String juvRectype)
	{
	    if (this.rectype != juvRectype)
		{
		    markModified();
		}
	    this.rectype = juvRectype;
	}
	public String getProbationFlag()
	{
	    fetch();
	    return probationFlag;
	}
	public void setProbationFlag(String probationFlag)
	{
	    if (this.probationFlag != probationFlag)
		{
		    markModified();
		}
	    this.probationFlag = probationFlag;
	}
	//added for activity search task 154695
	public String getActivityCodeId()
	{
	    return activityCodeId;
	}
	public void setActivityCodeId(String activityCodeId)
	{
	    this.activityCodeId = activityCodeId;
	}
	public Date getActivityDate()
	{
	    return activityDate;
	}
	public void setActivityDate(Date activityDate)
	{
	    this.activityDate = activityDate;
	}
	public String getActivityTime()
	{
	    return activityTime;
	}
	public void setActivityTime(String activityTime)
	{
	    this.activityTime = activityTime;
	}
	public String getTypeId()
	{
	    return typeId;
	}
	public void setTypeId(String typeId)
	{
	    this.typeId = typeId;
	}
	public String getCategoryId()
	{
	    return categoryId;
	}
	public void setCategoryId(String categoryId)
	{
	    this.categoryId = categoryId;
	}
	public String getPermissionId()
	{
	    return permissionId;
	}
	public void setPermissionId(String permissionId)
	{
	    this.permissionId = permissionId;
	}
	public String getJuvenilefirstName()
	{
	    return juvenilefirstName;
	}
	public void setJuvenilefirstName(String juvenilefirstName)
	{
	    this.juvenilefirstName = juvenilefirstName;
	}
	public String getJuvenilelastName()
	{
	    return juvenilelastName;
	}
	public void setJuvenilelastName(String juvenilelastName)
	{
	    this.juvenilelastName = juvenilelastName;
	}
	public String getJuvenilemiddleName()
	{
	    return juvenilemiddleName;
	}
	public void setJuvenilemiddleName(String juvenilemiddleName)
	{
	    this.juvenilemiddleName = juvenilemiddleName;
	}
	public String getJuvLocationId()
	{
	    return juvLocationId;
	}
	public void setJuvLocationId(String juvLocationId)
	{
	    this.juvLocationId = juvLocationId;
	}
	public String getLogonId()
	{
	    return logonId;
	}
	public void setLogonId(String logonId)
	{
	    this.logonId = logonId;
	}
	public String getCreateUser()
	{
	    return createUser;
	}
	public void setCreateUser(String createUser)
	{
	    this.createUser = createUser;
	}
	public String getLatitude()
	{
	    return latitude;
	}
	public void setLatitude(String latitude)
	{
	    this.latitude = latitude;
	}
	public String getLongitude()
	{
	    return longitude;
	}
	public void setLongitude(String longitude)
	{
	    this.longitude = longitude;
	}
	public String getActivityEndTime()
	{
	    return activityEndTime;
	}
	public void setActivityEndTime(String activityEndTime)
	{
	    this.activityEndTime = activityEndTime;
	}
	
	public Date getCreateDate()
	{
	    return this.createDate;
	}
	public void setCreateDate(Date createDate)
	{
	    this.createDate = createDate;
	}
	
	public String getDaysDiff()
	{
	    return this.daysDiff;
	}
	public void setDaysDiff(String daysDiff)
	{
	   this.daysDiff = daysDiff;
	}
	
	
	public boolean getSubabuse()
	{
	    fetch();
	    return subabuse;
	}
	public void setSubabuse(boolean sAbuse)
	{
	    if ( this.subabuse != sAbuse) 
	    {
		markModified();
	    }
	    this.subabuse = sAbuse;
	}
	
	public boolean getHispanic()
	{
	    fetch();
	    return hispanic;
	}
	
	public void setHispanic(boolean hispanic)
	{
	    if ( this.hispanic != hispanic){
		markModified();
	    }
	    this.hispanic = hispanic;
	}
	
	
	public boolean getVop()
	{
	    fetch();
	    return vop;
	}
	
	public void setVop(boolean vop)
	{
	    if ( this.vop != vop ) {
		markModified();
	    }
	    
	    this.vop = vop;
	}
	public Juvenile getNonCoreJuvenile()
	{
	    initNonCoreJuvenile();
	    return nonCoreJuvenile;
	}
	
	public void setNonCoreJuvenile(Juvenile nonCoreJuvenile)
	{
	    this.nonCoreJuvenile = nonCoreJuvenile;
	}
	
	
	
	
	
	
	
}
