package pd.supervision.legacyupdates.entities;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * This attribute represents the legacy POI code.
 */
public class AssessmentData extends PersistentObject
{
	public static Iterator findAll(IEvent event){
		return new Home().findAll(event, AssessmentData.class);		
	}
	private String action;

	private Date assessmentDate;
	/**
	 * This attribute represents the legacy POI code.
	 */
	private String assessmentType;
	private String caseNum;
	private String legacyAssessmentId;
	private String needsScore;
	private String opId;
	private String recordType;
	private String riskScore;

	private String scsClassification;
	private String seqNumber;
	private String spn;
	public String bind() {
		IHome home = new Home();
		home.bind(this);
		return this.getSeqNumber();
	}
	public String getAction() {
		fetch();
		return action;
	}
	/**
	 * @return the assessmentDate
	 */
	public Date getAssessmentDate() {
		fetch();
		return assessmentDate;
	}
	
	/**
	 * @return the assessmentType
	 */
	public String getAssessmentType() {
		fetch();
		return assessmentType;
	}
	public String getCaseNum() {
		return caseNum;
	}
	/**
	 * @return the legacyAssessmentId
	 */
	public String getLegacyAssessmentId() {
		fetch();
		return legacyAssessmentId;
	}
	/**
	 * @return the needsScore
	 */
	public String getNeedsScore() {
		fetch();
		return needsScore;
	}
	public String getOpId() {
		return opId;
	}
	/**
	 * @return the cdi
	 */
	public String getRecordType() {
		fetch();
		return recordType;
	}
	/**
	 * @return the riskScore
	 */
	public String getRiskScore() {
		fetch();
		return riskScore;
	}
	public String getScsClassification() {
		fetch();
		return scsClassification;
	}
	public String getSeqNumber() {
		fetch();
		return seqNumber;
	}
	/**
	 * @return the caseNumber
	 */
	public String getSpn() {
		fetch();
		return spn;
	}
	public void setAction(String action) {
		if (this.action == null || !this.action.equals(action))
		{
			markModified();
		}
		this.action = action;
	}
	/**
	 * @param assessmentDate the assessmentDate to set
	 */
	public void setAssessmentDate(Date assessmentDate) {
		if (this.assessmentDate == null || !this.assessmentDate.equals(assessmentDate))
		{
			markModified();
		}
		this.assessmentDate = assessmentDate;
	}
	/**
	 * @param assessmentType the assessmentType to set
	 */
	public void setAssessmentType(String assessmentType) {
		if (this.assessmentType == null || !this.assessmentType.equals(assessmentType))
		{
			markModified();
		}
		this.assessmentType = assessmentType;
	}
	public void setCaseNum(String caseNumber) {
		this.caseNum = caseNumber;
	}
	/**
	 * @param legacyAssessmentId the legacyAssessmentId to set
	 */
	public void setLegacyAssessmentId(String legacyAssessmentId) {
		if (this.legacyAssessmentId == null || !this.legacyAssessmentId.equals(legacyAssessmentId))
		{
			markModified();
		}
		this.legacyAssessmentId = legacyAssessmentId;
	}
	/**
	 * @param needsScore the needsScore to set
	 */
	public void setNeedsScore(String needsScore) {
		if (this.needsScore == null || !this.needsScore.equals(needsScore))
		{
			markModified();
		}
		this.needsScore = needsScore;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(String recordType) {
		if (this.recordType == null || !this.recordType.equals(recordType))
		{
			markModified();
		}
		this.recordType = recordType;
	}
	/**
	 * @param riskScore the riskScore to set
	 */
	public void setRiskScore(String riskScore) {
		if (this.riskScore == null || !this.riskScore.equals(riskScore))
		{
			markModified();
		}
		this.riskScore = riskScore;
	}
	public void setScsClassification(String scsClassification) {
		if (this.scsClassification == null || !this.scsClassification.equals(scsClassification))
		{
			markModified();
		}
		this.scsClassification = scsClassification;
	}
	public void setSeqNumber(String seqNumber) {
		if (this.seqNumber == null || !this.seqNumber.equals(seqNumber))
		{
			markModified();
		}
		this.seqNumber = seqNumber;
	}
	
	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		if (this.spn == null || !this.spn.equals(spn))
		{
			markModified();
		}
		this.spn = spn;
	}
}
