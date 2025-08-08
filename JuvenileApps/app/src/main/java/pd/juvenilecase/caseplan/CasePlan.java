package pd.juvenilecase.caseplan;

import java.util.Date;
import java.util.Iterator;

import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.GetCaseplansByJuvenileNumberEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * @roseuid 4533C96C00AB
 */
public class CasePlan extends PersistentObject
{
	private Date reviewDate;
	private String supervisionNumber;
	private String caseplanID;
	   private Object report;
	   private String priorServices;
	   private String supLevelId;
	   private String contactInfo;
	   
	   
	   //added for User story 11191 Add Title IV in caseplan
	   
	   private boolean juvFosterCareCandidate;
		private Date socialHistDated;
		private Date psychologicalRepDated;
		private Date riskAssesmentDated;
		private String titleIVEComment;
		private Date dtDeterminationMade;
		private Date otherDated;
		private String explanation;
		//
		
		//added for User Story 11146 Add participation in development of caseplan and Distribution
		private String othername;
	    private Date childDtNotified;
	    private Date familyDtNotified;
	    private Date caregiverDtNotified;
	    private Date otherDtNotified;
	    private String childNotifMethod;
	    private String familyNotifMethod;
	    private String caregiverNotifMethod;
	    private String otherNameNotifMethod;
	    private Date childDtOfParticipation;
	    private Date familyDtOfParticipation;
	    private Date caregiverDtOfParticipation;
	    private Date otherNameDtOfParticipation;
	    private Date childMailedDt;
	    private Date familyMailedDt;
	    private Date caregiverMailedDt;
	    private Date otherNameMailedDt;
	    
	/**
	 * Properties for status
	 * @contextKey CASEPLAN_STATUS
	 */
	private Code status = null;
	/**
	 * Properties for theGoal
	 * @referencedType pd.juvenilecase.caseplan.Goal
	 */
	private java.util.Collection theGoal = null;
	/**
	 * Properties for thePlacement
	 * @referencedType pd.juvenilecase.caseplan.Placement
	 */
	private Placement thePlacement = null;
	/**
	 * Properties for theCasePlanDocument
	 * @referencedType pd.juvenilecase.caseplan.CasePlanDocumentMetadata
	 */
	private java.util.Collection theCasePlanDocument = null;
	/**
	 * Properties for theJPOReviewDocMetadata
	 * @referencedType pd.juvenilecase.caseplan.JPOReviewDocMetadata
	 */
	private java.util.Collection theJPOReviewDocMetadata = null;
	private String statusId;
	private String thePlacementId;

	/**
	 * @roseuid 4533C96C00AB
	 */
	public CasePlan()
	{
	}

	/**
	 * @roseuid 45119A640088
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public void find()
	{
		fetch();
	}

	/**
	 * @roseuid 45119A640089
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void generateCasePlanDocumentVersion()
	{
		markModified();
	}

	/**
	 * @roseuid 45119A64008A
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public void getCaseplanDocumentData()
	{
		fetch();
	}

	/**
	 * @roseuid 45119A640092
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void updateStatus()
	{
		markModified();
	}

	/**
	 * @roseuid 45119A650026
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void None()
	{
		markModified();
	}

	/**
	 * @roseuid 452FE428001C
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setStatusId(String statusId)
	{
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getStatusId()
	{
		fetch();
		return statusId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initStatus()
	{
		if (status == null)
		{
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class,
					"CASEPLAN_STATUS").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initStatus
	 * @methodInvocation fetch
	 * @methodInvocation initStatus
	 * @methodInvocation fetch
	 * @methodInvocation initStatus
	 * @methodInvocation fetch
	 * @methodInvocation initStatus
	 * @methodInvocation initStatus
	 */
	public Code getStatus()
	{
		fetch();
		initStatus();
		return status;
	}

	/**
	 * set the type reference for class member status
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setStatusId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setStatusId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setStatusId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setStatusId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setStatusId
	 */
	public void setStatus(Code status)
	{
		if (this.status == null || !this.status.equals(status))
		{
			markModified();
		}
		if (status.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.caseplan.Goal
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initTheGoal()
	{
		if (theGoal == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			
			try {
				theGoal = new mojo.km.persistence.ArrayList(Goal.class, "casePlanId", ""
					+ getOID());
			}
			catch(Throwable t) {
				theGoal = new java.util.ArrayList();
			}
		}
		
	}

	/**
	 * returns a collection of pd.juvenilecase.caseplan.Goal
	 * @methodInvocation fetch
	 * @methodInvocation initTheGoal
	 * @methodInvocation fetch
	 * @methodInvocation initTheGoal
	 * @methodInvocation fetch
	 * @methodInvocation initTheGoal
	 * @methodInvocation fetch
	 * @methodInvocation initTheGoal
	 * @methodInvocation initTheGoal
	 */
	public java.util.Collection getTheGoal()
	{
		fetch();
		initTheGoal();
		return theGoal;
	}

	/**
	 * insert a pd.juvenilecase.caseplan.Goal into class relationship collection.
	 * @methodInvocation initTheGoal
	 * @methodInvocation add
	 * @methodInvocation initTheGoal
	 * @methodInvocation add
	 * @methodInvocation initTheGoal
	 * @methodInvocation add
	 * @methodInvocation initTheGoal
	 * @methodInvocation add
	 * @methodInvocation initTheGoal
	 * @methodInvocation add
	 */
	public void insertTheGoal(Goal anObject)
	{
		initTheGoal();
		theGoal.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.caseplan.Goal from class relationship collection.
	 * @methodInvocation initTheGoal
	 * @methodInvocation remove
	 * @methodInvocation initTheGoal
	 * @methodInvocation remove
	 * @methodInvocation initTheGoal
	 * @methodInvocation remove
	 * @methodInvocation initTheGoal
	 * @methodInvocation remove
	 * @methodInvocation initTheGoal
	 * @methodInvocation remove
	 */
	public void removeTheGoal(Goal anObject)
	{
		initTheGoal();
		theGoal.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.caseplan.Goal from class relationship collection.
	 * @methodInvocation initTheGoal
	 * @methodInvocation clear
	 * @methodInvocation initTheGoal
	 * @methodInvocation clear
	 * @methodInvocation initTheGoal
	 * @methodInvocation clear
	 * @methodInvocation initTheGoal
	 * @methodInvocation clear
	 * @methodInvocation initTheGoal
	 * @methodInvocation clear
	 */
	public void clearTheGoal()
	{
		initTheGoal();
		theGoal.clear();
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.caseplan.Placement
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setThePlacementId(String thePlacementId)
	{
		if (this.thePlacementId == null || !this.thePlacementId.equals(thePlacementId))
		{
			markModified();
		}
		thePlacement = null;
		this.thePlacementId = thePlacementId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.caseplan.Placement
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getThePlacementId()
	{
		fetch();
		return thePlacementId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.caseplan.Placement
	 */
	private void initThePlacement()
	{
		if (thePlacement == null)
		{
			thePlacement = (Placement) new mojo.km.persistence.Reference(thePlacementId,
					Placement.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.caseplan.Placement
	 * @methodInvocation fetch
	 * @methodInvocation initThePlacement
	 * @methodInvocation fetch
	 * @methodInvocation initThePlacement
	 * @methodInvocation fetch
	 * @methodInvocation initThePlacement
	 * @methodInvocation fetch
	 * @methodInvocation initThePlacement
	 * @methodInvocation initThePlacement
	 */
	public Placement getThePlacement()
	{
		fetch();
		initThePlacement();
		return thePlacement;
	}

	/**
	 * set the type reference for class member thePlacement
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setThePlacementId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setThePlacementId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setThePlacementId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setThePlacementId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setThePlacementId
	 */
	public void setThePlacement(Placement thePlacement)
	{
		if (this.thePlacement == null || !this.thePlacement.equals(thePlacement))
		{
			markModified();
		}
		if (thePlacement.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(thePlacement);
		}
		setThePlacementId("" + thePlacement.getOID());
		this.thePlacement = (Placement) new mojo.km.persistence.Reference(thePlacement)
				.getObject();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.caseplan.CasePlanDocumentMetadata
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initTheCasePlanDocument()
	{
		if (theCasePlanDocument == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			theCasePlanDocument = new mojo.km.persistence.ArrayList(
					CasePlanDocumentMetadata.class, "casePlanId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenilecase.caseplan.CasePlanDocumentMetadata
	 * @methodInvocation fetch
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation fetch
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation fetch
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation fetch
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation initTheCasePlanDocument
	 */
	public java.util.Collection getTheCasePlanDocument()
	{
		fetch();
		initTheCasePlanDocument();
		return theCasePlanDocument;
	}

	/**
	 * insert a pd.juvenilecase.caseplan.CasePlanDocumentMetadata into class relationship collection.
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation add
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation add
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation add
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation add
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation add
	 */
	public void insertTheCasePlanDocument(CasePlanDocumentMetadata anObject)
	{
		initTheCasePlanDocument();
		theCasePlanDocument.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.caseplan.CasePlanDocumentMetadata from class relationship collection.
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation remove
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation remove
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation remove
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation remove
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation remove
	 */
	public void removeTheCasePlanDocument(CasePlanDocumentMetadata anObject)
	{
		initTheCasePlanDocument();
		theCasePlanDocument.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.caseplan.CasePlanDocumentMetadata from class relationship collection.
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation clear
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation clear
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation clear
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation clear
	 * @methodInvocation initTheCasePlanDocument
	 * @methodInvocation clear
	 */
	public void clearTheCasePlanDocument()
	{
		initTheCasePlanDocument();
		theCasePlanDocument.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.caseplan.JPOReviewDocMetadata
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initTheJPOReviewDocMetadata()
	{
		if (theJPOReviewDocMetadata == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			theJPOReviewDocMetadata = new mojo.km.persistence.ArrayList(
					JPOReviewDocMetadata.class, "casePlanId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenilecase.caseplan.JPOReviewDocMetadata
	 * @methodInvocation fetch
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation fetch
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation fetch
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation fetch
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation initTheJPOReviewDocMetadata
	 */
	public java.util.Collection getTheJPOReviewDocMetadata()
	{
		fetch();
		initTheJPOReviewDocMetadata();
		return theJPOReviewDocMetadata;
	}

	/**
	 * insert a pd.juvenilecase.caseplan.JPOReviewDocMetadata into class relationship collection.
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation add
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation add
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation add
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation add
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation add
	 */
	public void insertTheJPOReviewDocMetadata(JPOReviewDocMetadata anObject)
	{
		initTheJPOReviewDocMetadata();
		theJPOReviewDocMetadata.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.caseplan.JPOReviewDocMetadata from class relationship collection.
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation remove
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation remove
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation remove
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation remove
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation remove
	 */
	public void removeTheJPOReviewDocMetadata(JPOReviewDocMetadata anObject)
	{
		initTheJPOReviewDocMetadata();
		theJPOReviewDocMetadata.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.caseplan.JPOReviewDocMetadata from class relationship collection.
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation clear
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation clear
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation clear
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation clear
	 * @methodInvocation initTheJPOReviewDocMetadata
	 * @methodInvocation clear
	 */
	public void clearTheJPOReviewDocMetadata()
	{
		initTheJPOReviewDocMetadata();
		theJPOReviewDocMetadata.clear();
	}
	
	public Object getReport() 
	{
		fetch();
		return report;
	}
	
	/**
	* 
	*/
	public void setReport( Object aReport ) 
	{
		if ( report == null || ! report.equals(aReport) ) 
		{
			report = aReport;
			markModified();
		}
	}

	/**
	 * @return Returns the caseplanID.
	 * @methodInvocation fetch
	 */
	public String getCaseplanID()
	{
		fetch();
		return getOID().toString();
	}

	/**
	 * @param caseplanID The caseplanID to set.
	 * @methodInvocation setOID
	 * @methodInvocation setOID
	 * @methodInvocation setOID
	 * @methodInvocation markModified
	 */
	public void setCaseplanID(String caseplanID)
	{
		if (this.caseplanID == null || !this.caseplanID.equals(caseplanID))
		{
			markModified();
		}
		setOID(caseplanID);
	}

	/**
	 * @return Returns the reviewDate.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getReviewDate()
	{
		fetch();
		return reviewDate;
	}

	/**
	 * @param reviewDate The reviewDate to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setReviewDate(Date reviewDate)
	{
		if (this.reviewDate == null || !this.reviewDate.equals(reviewDate))
		{
			markModified();
		}
		this.reviewDate = reviewDate;
	}

	/**
	 * @return Returns the supervisionNumber.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSupervisionNumber()
	{
		fetch();
		return supervisionNumber;
	}

	/**
	 * @param supervisionNumber The supervisionNumber to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSupervisionNumber(String supervisionNumber)
	{
		if (this.supervisionNumber == null || !this.supervisionNumber.equals(supervisionNumber))
		{
			markModified();
		}
		this.supervisionNumber = supervisionNumber;
	}
	

	/**
	 * @return Returns the priorServices.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getPriorServices()
	{
		fetch();
		return priorServices;
	}

	/**
	 * @param priorServices The priorServices to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setPriorServices(String priorServices)
	{
		if (this.priorServices == null || !this.priorServices.equals(priorServices))
		{
			markModified();
		}
		this.priorServices = priorServices;
	}
	
	/**
	 * @return Returns the supLevelId.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSupLevelId()
	{
		fetch();
		return supLevelId;
	}

	/**
	 * @param supLevelId The supLevelId to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSupLevelId(String supLevelId)
	{
		if (this.supLevelId == null || !this.supLevelId.equals(supLevelId))
		{
			markModified();
		}
		this.supLevelId = supLevelId;
	}
	
	/**
	 * @return Returns the contactInfo.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getContactInfo()
	{
		fetch();
		return contactInfo;
	}

	/**
	 * @param contactInfo The contactInfo to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setContactInfo(String contactInfo)
	{
		if (this.contactInfo == null || !this.contactInfo.equals(contactInfo))
		{
			markModified();
		}
		this.contactInfo = contactInfo;
	}
	
	/**
	 * @return
	 */
	public boolean isJuvFosterCareCandidate() {
		fetch();
		return juvFosterCareCandidate;
	}
	/**
	 * @param b
	 */
	public void setJuvFosterCareCandidate(boolean juvFosterCareCandidate) {
		if (this.juvFosterCareCandidate != juvFosterCareCandidate) {
			markModified();
		}
		this.juvFosterCareCandidate = juvFosterCareCandidate;
	}
	
	/**
	 * @return
	 */
	public Date getSocialHistDated() {
		fetch();
		return this.socialHistDated;
	}
	/**
	 * @param date
	 */
	public void setSocialHistDated(Date date) {
		if (this.socialHistDated == null
				|| !this.socialHistDated.equals(date)) {
			markModified();
		}
		this.socialHistDated = date;
	}
	
	/**
	 * @return
	 */
	public Date getPsychologicalRepDated() {
		fetch();
		return this.psychologicalRepDated;
	}
	/**
	 * @param date
	 */
	public void setPsychologicalRepDated(Date date) {
		if (this.psychologicalRepDated == null
				|| !this.psychologicalRepDated.equals(date)) {
			markModified();
		}
		this.psychologicalRepDated = date;
	}
	
	/**
	 * @return
	 */
	public Date getRiskAssesmentDated() {
		fetch();
		return this.riskAssesmentDated;
	}
	/**
	 * @param date
	 */
	public void setRiskAssesmentDated(Date date) {
		if (this.riskAssesmentDated == null
				|| !this.riskAssesmentDated.equals(date)) {
			markModified();
		}
		this.riskAssesmentDated = date;
	}
	
	/**
	 * @return
	 */
	public Date getOtherDated() {
		fetch();
		return this.otherDated;
	}
	/**
	 * @param date
	 */
	public void setOtherDated(Date date) {
		if (this.otherDated == null
				|| !this.otherDated.equals(date)) {
			markModified();
		}
		this.otherDated = date;
	}
	/**
	 * @return Returns the titleIVEComment.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getTitleIVEComment()
	{
		fetch();
		return titleIVEComment;
	}

	/**
	 * @param titleIVEComment The titleIVEComment to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setTitleIVEComment(String titleIVEComment)
	{
		if (this.titleIVEComment == null || !this.titleIVEComment.equals(titleIVEComment))
		{
			markModified();
		}
		this.titleIVEComment = titleIVEComment;
	}
	
	/**
	 * @return Returns the explanation.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getExplanation()
	{
		fetch();
		return explanation;
	}

	/**
	 * @param explanation The explanation to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setExplanation(String explanation)
	{
		if (this.explanation == null || !this.explanation.equals(explanation))
		{
			markModified();
		}
		this.explanation = explanation;
	}
	/**
	 * @return
	 */
	public Date getDtDeterminationMade() {
		fetch();
		return this.dtDeterminationMade;
	}
	/**
	 * @param date
	 */
	public void setDtDeterminationMade(Date date) {
		if (this.dtDeterminationMade == null
				|| !this.dtDeterminationMade.equals(date)) {
			markModified();
		}
		this.dtDeterminationMade = date;
	}
	
	/**
	 * @return
	 */
	public String getOthername() {
		fetch();
		return this.othername;
	}
	/**
	 * @param String
	 */
	public void setOthername(String othername)
	{
		if (this.othername == null || !this.othername.equals(othername))
		{
			markModified();
		}
		this.othername = othername;
	}
	/**
	 * @return
	 */
	public Date getChildDtNotified() {
		fetch();
		return this.childDtNotified;
	}
	/**
	 * @param date
	 */
	public void setChildDtNotified(Date date) {
		if (this.childDtNotified == null
				|| !this.childDtNotified.equals(date)) {
			markModified();
		}
		this.childDtNotified = date;
	}
	/**
	 * @return
	 */
	public Date getFamilyDtNotified() {
		fetch();
		return this.familyDtNotified;
	}
	/**
	 * @param date
	 */
	public void setFamilyDtNotified(Date date) {
		if (this.familyDtNotified == null
				|| !this.familyDtNotified.equals(date)) {
			markModified();
		}
		this.familyDtNotified = date;
	}
	/**
	 * @return
	 */
	public Date getCaregiverDtNotified() {
		fetch();
		return this.caregiverDtNotified;
	}
	/**
	 * @param date
	 */
	public void setCaregiverDtNotified(Date date) {
		if (this.caregiverDtNotified == null
				|| !this.caregiverDtNotified.equals(date)) {
			markModified();
		}
		this.caregiverDtNotified = date;
	}
	/**
	 * @return
	 */
	public Date getOtherDtNotified() {
		fetch();
		return this.otherDtNotified;
	}
	/**
	 * @param date
	 */
	public void setOtherDtNotified(Date date) {
		if (this.otherDtNotified == null
				|| !this.otherDtNotified.equals(date)) {
			markModified();
		}
		this.otherDtNotified = date;
	}
	/**
	 * @return
	 */
	public String getChildNotifMethod() {
		fetch();
		return this.childNotifMethod;
	}
	/**
	 * @param String
	 */
	public void setChildNotifMethod(String childNotifMethod)
	{
		if (this.childNotifMethod == null || !this.childNotifMethod.equals(childNotifMethod))
		{
			markModified();
		}
		this.childNotifMethod = childNotifMethod;
	}
	/**
	 * @return
	 */
	public String getFamilyNotifMethod() {
		fetch();
		return this.familyNotifMethod;
	}
	/**
	 * @param String
	 */
	public void setFamilyNotifMethod(String familyNotifMethod)
	{
		if (this.familyNotifMethod == null || !this.familyNotifMethod.equals(familyNotifMethod))
		{
			markModified();
		}
		this.familyNotifMethod = familyNotifMethod;
	}
	/**
	 * @return
	 */
	public String getCaregiverNotifMethod() {
		fetch();
		return this.caregiverNotifMethod;
	}
	/**
	 * @param String
	 */
	public void setCaregiverNotifMethod(String caregiverNotifMethod)
	{
		if (this.caregiverNotifMethod == null || !this.caregiverNotifMethod.equals(caregiverNotifMethod))
		{
			markModified();
		}
		this.caregiverNotifMethod = caregiverNotifMethod;
	}
	/**
	 * @return
	 */
	public String getOtherNameNotifMethod() {
		fetch();
		return this.otherNameNotifMethod;
	}
	/**
	 * @param String
	 */
	public void setOtherNameNotifMethod(String otherNameNotifMethod)
	{
		if (this.otherNameNotifMethod == null || !this.otherNameNotifMethod.equals(otherNameNotifMethod))
		{
			markModified();
		}
		this.otherNameNotifMethod = otherNameNotifMethod;
	}
	/**
	 * @return
	 */
	public Date getChildDtOfParticipation() {
		fetch();
		return this.childDtOfParticipation;
	}
	/**
	 * @param date
	 */
	public void setChildDtOfParticipation(Date date) {
		if (this.childDtOfParticipation == null
				|| !this.childDtOfParticipation.equals(date)) {
			markModified();
		}
		this.childDtOfParticipation = date;
	}
	/**
	 * @return
	 */
	public Date getFamilyDtOfParticipation() {
		fetch();
		return this.familyDtOfParticipation;
	}
	/**
	 * @param date
	 */
	public void setFamilyDtOfParticipation(Date date) {
		if (this.familyDtOfParticipation == null
				|| !this.familyDtOfParticipation.equals(date)) {
			markModified();
		}
		this.familyDtOfParticipation = date;
	}
	/**
	 * @return
	 */
	public Date getCaregiverDtOfParticipation() {
		fetch();
		return this.caregiverDtOfParticipation;
	}
	/**
	 * @param date
	 */
	public void setCaregiverDtOfParticipation(Date date) {
		if (this.caregiverDtOfParticipation == null
				|| !this.caregiverDtOfParticipation.equals(date)) {
			markModified();
		}
		this.caregiverDtOfParticipation = date;
	}/**
	 * @return
	 */
	public Date getOtherNameDtOfParticipation() {
		fetch();
		return this.otherNameDtOfParticipation;
	}
	/**
	 * @param date
	 */
	public void setOtherNameDtOfParticipation(Date date) {
		if (this.otherNameDtOfParticipation == null
				|| !this.otherNameDtOfParticipation.equals(date)) {
			markModified();
		}
		this.otherNameDtOfParticipation = date;
	}
	/**
	 * @return
	 */
	public Date getChildMailedDt() {
		fetch();
		return this.childMailedDt;
	}
	/**
	 * @param date
	 */
	public void setChildMailedDt(Date date) {
		if (this.childMailedDt == null
				|| !this.childMailedDt.equals(date)) {
			markModified();
		}
		this.childMailedDt = date;
	}
	/**
	 * @return
	 */
	public Date getFamilyMailedDt() {
		fetch();
		return this.familyMailedDt;
	}
	/**
	 * @param date
	 */
	public void setFamilyMailedDt(Date date) {
		if (this.familyMailedDt == null
				|| !this.familyMailedDt.equals(date)) {
			markModified();
		}
		this.familyMailedDt = date;
	}
	/**
	 * @return
	 */
	public Date getCaregiverMailedDt() {
		fetch();
		return this.caregiverMailedDt;
	}
	/**
	 * @param date
	 */
	public void setCaregiverMailedDt(Date date) {
		if (this.caregiverMailedDt == null
				|| !this.caregiverMailedDt.equals(date)) {
			markModified();
		}
		this.caregiverMailedDt = date;
	}
	/**
	 * @return
	 */
	public Date getOtherNameMailedDt() {
		fetch();
		return this.otherNameMailedDt;
	}
	/**
	 * @param date
	 */
	public void setOtherNameMailedDt(Date date) {
		if (this.otherNameMailedDt == null
				|| !this.otherNameMailedDt.equals(date)) {
			markModified();
		}
		this.otherNameMailedDt = date;
	}
	
	
	/**
	 * Finds Caseplans by a certain event
	 * @return Iterator of Caseplans
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CasePlan.class);
	}

	/**
	 * @roseuid 433C3D3E00AA
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 */
	static public CasePlan findByCasefileID(GetCaseplanDetailsEvent event)
	{
		IHome home = new Home();
		CasePlan cp = null;
		Iterator caseplans = home.findAll(event, CasePlan.class);
		if (caseplans.hasNext())
		{
			cp = (CasePlan) caseplans.next();
		}
		return cp;
	}

	/**
	 * @roseuid 433C3D3E00AA
	 * @methodInvocation findAll
	 */
	static public Iterator findByJuvenileNum(GetCaseplansByJuvenileNumberEvent event)
	{
		IHome home = new Home();
		
		Iterator caseplans = home.findAll(event, CasePlan.class);
		return caseplans;
	}
	static public CasePlan find(String caseplanID)
	{
		CasePlan cp = null;
		IHome home = new Home();
		cp = (CasePlan) home.find(caseplanID, CasePlan.class);
		return cp;
	}
}

