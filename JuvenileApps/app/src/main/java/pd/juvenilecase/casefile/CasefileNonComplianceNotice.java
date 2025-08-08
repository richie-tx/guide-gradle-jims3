package pd.juvenilecase.casefile;

import pd.codetable.Code;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * 
 * @stereotype entity
 * @author dnikolis
 */
public class CasefileNonComplianceNotice extends mojo.km.persistence.PersistentObject
{ 
	private String casefileId;
	private Date nonComplianceDate;
	private Date sanctionAssignedDate;
	private Date completeSanctionByDate;
	private boolean parentInformed;
	private Date signedDate;
	private Date completionDate;
	private String completionComments;
	private String actionTakenComments;
	private String actionTakenOtherText;
	/**
	 * 
	 * @contextKey NCVIOLATION_LEVEL
	 * @referencedType pd.codetableCode
	 * @detailerDoNotGenerate false
	 */
	private Code violationLevel = null;
	/**
	 * 
	 * @contextKey NCSANCTION_LEVEL
	 * @referencedType pd.codetableCode
	 * @detailerDoNotGenerate false
	 */
	private Code sanctionLevel = null;
	/**
	 * 
	 * @referencedType pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation
	 * @detailerDoNotGenerate false
	 */
	private Collection casefileNonComplianceNoticeProbationViolations;
	/**
	 * 
	 * @referencedType pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction
	 * @detailerDoNotGenerate false
	 */
	private Collection casefileNonComplianceNoticeSanctions;
	/**
	 * 
	 * @contextKey NCSIGNATURE_STATUS
	 * @referencedType pd.codetableCode
	 * @detailerDoNotGenerate false
	 */
	private Code signatureStatus = null;
	/**
	 * 
	 * @contextKey NCCOMPLETION_STATUS
	 * @referencedType pd.codetableCode
	 * @detailerDoNotGenerate false
	 */
	private Code completionStatus = null;
	/**
	 * 
	 * @referencedType pd.juvenilecase.casefile.CasefileDocument
	 * @detailerDoNotGenerate false
	 */
	private CasefileDocument casefileDocument = null;
	/**
	 * 
	 * @contextKey NCACTION_TAKEN
	 * @referencedType pd.codetableCode
	 * @detailerDoNotGenerate false
	 */
	private Code actionTaken = null;
	private String violationLevelId;
	private String sanctionLevelId;
	private String signatureStatusId;
	private String completionStatusId;
	private String casefileDocumentId;
	private String actionTakenId;

	public Date getNonComplianceDate()
	{
		fetch();
		return nonComplianceDate;
	}

	public void setNonComplianceDate(Date nonComplianceDate)
	{
		if (this.nonComplianceDate == null || !this.nonComplianceDate.equals(nonComplianceDate))
		{
			markModified();
		}
		this.nonComplianceDate = nonComplianceDate;
	}

	public Date getSanctionAssignedDate()
	{
		fetch();
		return sanctionAssignedDate;
	}

	public void setSanctionAssignedDate(Date sanctionAssignedDate)
	{
		if (this.sanctionAssignedDate == null || !this.sanctionAssignedDate.equals(sanctionAssignedDate))
		{
			markModified();
		}
		this.sanctionAssignedDate = sanctionAssignedDate;
	}

	public Date getCompleteSanctionByDate()
	{
		fetch();
		return completeSanctionByDate;
	}

	public void setCompleteSanctionByDate(Date completeSanctionByDate)
	{
		if (this.completeSanctionByDate == null || !this.completeSanctionByDate.equals(completeSanctionByDate))
		{
			markModified();
		}
		this.completeSanctionByDate = completeSanctionByDate;
	}

	public boolean isParentInformed()
	{
		fetch();
		return parentInformed;
	}

	public void setParentInformed(boolean parentInformed)
	{
		if (this.parentInformed != parentInformed)
		{
			markModified();
		}
		this.parentInformed = parentInformed;
	}

	public Date getSignedDate()
	{
		fetch();
		return signedDate;
	}

	public void setSignedDate(Date signedDate)
	{
		if (this.signedDate == null || !this.signedDate.equals(signedDate))
		{
			markModified();
		}
		this.signedDate = signedDate;
	}

	public Date getCompletionDate()
	{
		fetch();
		return completionDate;
	}

	public void setCompletionDate(Date completionDate)
	{
		if (this.completionDate == null || !this.completionDate.equals(completionDate))
		{
			markModified();
		}
		this.completionDate = completionDate;
	}

	public String getCompletionComments()
	{
		fetch();
		return completionComments;
	}

	public void setCompletionComments(String completionComments)
	{
		if (this.completionComments == null || !this.completionComments.equals(completionComments))
		{
			markModified();
		}
		this.completionComments = completionComments;
	}

	public String getActionTakenComments()
	{
		fetch();
		return actionTakenComments;
	}

	public void setActionTakenComments(String actionTakenComments)
	{
		if (this.actionTakenComments == null || !this.actionTakenComments.equals(actionTakenComments))
		{
			markModified();
		}
		this.actionTakenComments = actionTakenComments;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setViolationLevelId(String violationLevelId)
	{
		if (this.violationLevelId == null || !this.violationLevelId.equals(violationLevelId))
		{
			markModified();
		}
		violationLevel = null;
		this.violationLevelId = violationLevelId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getViolationLevelId()
	{
		fetch();
		return violationLevelId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initViolationLevel()
	{
		if (violationLevel == null)
		{
			violationLevel = (Code) new mojo.km.persistence.Reference(violationLevelId,
					Code.class, "NCVIOLATIONLEVEL").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getViolationLevel()
	{
		initViolationLevel();
		return violationLevel;
	}

	/**
	 * set the type reference for class member violationLevel
	 */
	public void setViolationLevel(Code violationLevel)
	{
		if (this.violationLevel == null || !this.violationLevel.equals(violationLevel))
		{
			markModified();
		}
		setViolationLevelId("" + violationLevel.getOID());
		violationLevel.setContext("NCVIOLATION_LEVEL");
		this.violationLevel = (Code) new mojo.km.persistence.Reference(violationLevel).getObject();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation
	 */
	private void initCasefileNonComplianceNoticeProbationViolations()
	{
		if (casefileNonComplianceNoticeProbationViolations == null)
		{
			casefileNonComplianceNoticeProbationViolations = new mojo.km.persistence.ArrayList(
					CasefileNonComplianceNoticeProbationViolation.class,
					"casefileNonComplianceNoticeId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation
	 */
	public Collection getCasefileNonComplianceNoticeProbationViolations()
	{
		initCasefileNonComplianceNoticeProbationViolations();
		return casefileNonComplianceNoticeProbationViolations;
	}

	/**
	 * insert a pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation into class relationship collection.
	 */
	public void insertCasefileNonComplianceNoticeProbationViolations(
			CasefileNonComplianceNoticeProbationViolation anObject)
	{
		initCasefileNonComplianceNoticeProbationViolations();
		casefileNonComplianceNoticeProbationViolations.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation from class relationship collection.
	 */
	public void removeCasefileNonComplianceNoticeProbationViolations(
			CasefileNonComplianceNoticeProbationViolation anObject)
	{
		initCasefileNonComplianceNoticeProbationViolations();
		casefileNonComplianceNoticeProbationViolations.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation from class relationship collection.
	 */
	public void clearCasefileNonComplianceNoticeProbationViolations()
	{
		initCasefileNonComplianceNoticeProbationViolations();
		casefileNonComplianceNoticeProbationViolations.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction
	 */
	private void initCasefileNonComplianceNoticeSanctions()
	{
		if (casefileNonComplianceNoticeSanctions == null)
		{
			casefileNonComplianceNoticeSanctions = new mojo.km.persistence.ArrayList(
					CasefileNonComplianceNoticeSanction.class,
					"casefileNonComplianceNoticeId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction
	 */
	public Collection getCasefileNonComplianceNoticeSanctions()
	{
		initCasefileNonComplianceNoticeSanctions();
		return casefileNonComplianceNoticeSanctions;
	}

	/**
	 * insert a pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction into class relationship collection.
	 */
	public void insertCasefileNonComplianceNoticeSanctions(
			CasefileNonComplianceNoticeSanction anObject)
	{
		initCasefileNonComplianceNoticeSanctions();
		casefileNonComplianceNoticeSanctions.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction from class relationship collection.
	 */
	public void removeCasefileNonComplianceNoticeSanctions(
			CasefileNonComplianceNoticeSanction anObject)
	{
		initCasefileNonComplianceNoticeSanctions();
		casefileNonComplianceNoticeSanctions.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction from class relationship collection.
	 */
	public void clearCasefileNonComplianceNoticeSanctions()
	{
		initCasefileNonComplianceNoticeSanctions();
		casefileNonComplianceNoticeSanctions.clear();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setSignatureStatusId(String signatureStatusId)
	{
		if (this.signatureStatusId == null || !this.signatureStatusId.equals(signatureStatusId))
		{
			markModified();
		}
		signatureStatus = null;
		this.signatureStatusId = signatureStatusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSignatureStatusId()
	{
		fetch();
		return signatureStatusId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSignatureStatus()
	{
		if (signatureStatus == null)
		{
			signatureStatus = (Code) new mojo.km.persistence.Reference(signatureStatusId,
					Code.class, "NCSIGNATURESTATUS").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getSignatureStatus()
	{
		initSignatureStatus();
		return signatureStatus;
	}

	/**
	 * set the type reference for class member signatureStatus
	 */
	public void setSignatureStatus(Code signatureStatus)
	{
		if (this.signatureStatus == null || !this.signatureStatus.equals(signatureStatus))
		{
			markModified();
		}
		setSignatureStatusId("" + signatureStatus.getOID());
		signatureStatus.setContext("NCSIGNATURE_STATUS");
		this.signatureStatus = (Code) new mojo.km.persistence.Reference(signatureStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setCompletionStatusId(String completionStatusId)
	{
		if (this.completionStatusId == null || !this.completionStatusId.equals(completionStatusId))
		{
			markModified();
		}
		completionStatus = null;
		this.completionStatusId = completionStatusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getCompletionStatusId()
	{
		fetch();
		return completionStatusId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCompletionStatus()
	{
		if (completionStatus == null)
		{
			completionStatus = (Code) new mojo.km.persistence.Reference(completionStatusId,
					Code.class, "NCCOMPLETIONSTATUS").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCompletionStatus()
	{
		initCompletionStatus();
		return completionStatus;
	}

	/**
	 * set the type reference for class member completionStatus
	 */
	public void setCompletionStatus(Code completionStatus)
	{
		if (this.completionStatus == null || !this.completionStatus.equals(completionStatus))
		{
			markModified();
		}
		setCompletionStatusId("" + completionStatus.getOID());
		completionStatus.setContext("NCCOMPLETION_STATUS");
		this.completionStatus = (Code) new mojo.km.persistence.Reference(completionStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.casefile.CasefileDocument
	 */
	public void setCasefileDocumentId(String casefileDocumentId)
	{
		if (this.casefileDocumentId == null || !this.casefileDocumentId.equals(casefileDocumentId))
		{
			markModified();
		}
		casefileDocument = null;
		this.casefileDocumentId = casefileDocumentId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.casefile.CasefileDocument
	 */
	public String getCasefileDocumentId()
	{
		fetch();
		return casefileDocumentId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.casefile.CasefileDocument
	 */
	private void initCasefileDocument()
	{
		if (casefileDocument == null)
		{
			casefileDocument = (CasefileDocument) new mojo.km.persistence.Reference(
					casefileDocumentId, CasefileDocument.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.casefile.CasefileDocument
	 */
	public CasefileDocument getCasefileDocument()
	{
		initCasefileDocument();
		return casefileDocument;
	}

	/**
	 * set the type reference for class member casefileDocument
	 */
	public void setCasefileDocument(CasefileDocument casefileDocument)
	{
		if (this.casefileDocument == null || !this.casefileDocument.equals(casefileDocument))
		{
			markModified();
		}
		setCasefileDocumentId("" + casefileDocument.getOID());
		this.casefileDocument = (CasefileDocument) new mojo.km.persistence.Reference(
				casefileDocument).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setActionTakenId(String actionTakenId)
	{
		if (this.actionTakenId == null || !this.actionTakenId.equals(actionTakenId))
		{
			markModified();
		}
		actionTaken = null;
		this.actionTakenId = actionTakenId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getActionTakenId()
	{
		fetch();
		return actionTakenId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initActionTaken()
	{
		if (actionTaken == null)
		{
			actionTaken = (Code) new mojo.km.persistence.Reference(actionTakenId, Code.class,
					"NCACTIONTAKEN").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getActionTaken()
	{
		initActionTaken();
		return actionTaken;
	}

	/**
	 * set the type reference for class member actionTaken
	 */
	public void setActionTaken(Code actionTaken)
	{
		if (this.actionTaken == null || !this.actionTaken.equals(actionTaken))
		{
			markModified();
		}
		setActionTakenId("" + actionTaken.getOID());
		actionTaken.setContext("NCACTION_TAKEN");
		this.actionTaken = (Code) new mojo.km.persistence.Reference(actionTaken).getObject();
	}
	
	public String getCasefileId() {
		fetch();
		return casefileId;
	}

	public void setCasefileId(String casefileId) {
		if (this.casefileId == null || !this.casefileId.equals(casefileId))
		{
			markModified();
		}
		this.casefileId = casefileId;
	}
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setSanctionLevelId(String sanctionLevelId)
	{
		if (this.sanctionLevelId == null || !this.sanctionLevelId.equals(sanctionLevelId))
		{
			markModified();
		}
		sanctionLevel = null;
		this.sanctionLevelId = sanctionLevelId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSanctionLevelId()
	{
		fetch();
		return sanctionLevelId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSanctionLevel()
	{
		if (sanctionLevel == null)
		{
			sanctionLevel = (Code) new mojo.km.persistence.Reference(sanctionLevelId,
					Code.class, "NCSANCTIONLEVEL").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getSanctionLevel()
	{
		initSanctionLevel();
		return sanctionLevel;
	}

	/**
	 * set the type reference for class member sanctionLevel
	 */
	public void setSanctionLevel(Code sanctionLevel)
	{
		if (this.sanctionLevel == null || !this.sanctionLevel.equals(sanctionLevel))
		{
			markModified();
		}
		setSanctionLevelId("" + sanctionLevel.getOID());
		sanctionLevel.setContext("NCSANCTION_LEVEL");
		this.sanctionLevel = (Code) new mojo.km.persistence.Reference(sanctionLevel).getObject();
	}
	
	public String getActionTakenOtherText() {
		fetch();
		return actionTakenOtherText;
	}

	public void setActionTakenOtherText(String actionTakenOtherText) {
		if (this.actionTakenOtherText == null || !this.actionTakenOtherText.equals(actionTakenOtherText))
		{
			markModified();
		}
		this.actionTakenOtherText = actionTakenOtherText;
	}
	
	public CasefileNonComplianceNoticeResponseEvent getResponseEvent(){
		CasefileNonComplianceNoticeResponseEvent myRespEvt=new CasefileNonComplianceNoticeResponseEvent();
		myRespEvt.setCasefileId(this.getCasefileId());
		myRespEvt.setCasefileNonComplianceNoticeId(this.getOID());
		myRespEvt.setActionTakenComments(this.getActionTakenComments());
		myRespEvt.setActionTakenId(this.getActionTakenId());
		myRespEvt.setCompleteSanctionByDate(this.getCompleteSanctionByDate());
		myRespEvt.setCompletionStatusId(this.getCompletionStatusId());
		myRespEvt.setCompletionComments(this.getCompletionComments());
		myRespEvt.setCompletionDate(this.getCompletionDate());
		myRespEvt.setNonComplianceDate(this.getNonComplianceDate());
		myRespEvt.setParentInformed(this.isParentInformed());
		myRespEvt.setSanctionAssignedDate(this.getSanctionAssignedDate());
		myRespEvt.setSignedDate(this.getSignedDate());
		myRespEvt.setSignatureStatusId(this.getSignatureStatusId());
		myRespEvt.setViolationLevelId(this.getViolationLevelId());
		myRespEvt.setSanctionLevelId(this.getSanctionLevelId());
		myRespEvt.setGeneratedDate(this.getCreateTimestamp());
		myRespEvt.setActionTakenOtherText(this.getActionTakenOtherText());
		Iterator documents = CasefileDocument.findAll("casefileNonComplianceNoticeId", this.getOID());
		while (documents.hasNext())
		{
			CasefileDocument document = (CasefileDocument) documents.next();
			myRespEvt.setDocumentId(document.getOID());
		}
		return myRespEvt;
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator notices = home.findAll(attrName, attrValue, CasefileNonComplianceNotice.class);
		return notices;
	}

	static public CasefileNonComplianceNotice find( String casefileNonComplianceNoticeId )
	{
		IHome home = new Home();
		CasefileNonComplianceNotice casefileNonComplianceNotice = (CasefileNonComplianceNotice)home.find( casefileNonComplianceNoticeId, CasefileNonComplianceNotice.class );
		return casefileNonComplianceNotice;
	}

}
