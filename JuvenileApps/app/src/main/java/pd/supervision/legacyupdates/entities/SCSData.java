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
public class SCSData extends PersistentObject
{
	private String spn;
	private String recordType;
	private String assessmentCode;
	private Date assessmentDate;
	private String legacyAssessmentId;
	
	/**
	 * @return the assessmentCode
	 */
	public String getAssessmentCode() {
		fetch();
		return assessmentCode;
	}
	/**
	 * @return the assessmentDate
	 */
	public Date getAssessmentDate() {
		fetch();
		return assessmentDate;
	}
	
	public String getLegacyAssessmentId() {
		fetch();
		return legacyAssessmentId;	
	}
	/**
	 * @param assessmentCode the assessmentCode to set
	 */
	public void setAssessmentCode(String assessmentCode) {
		if (this.assessmentCode == null || !this.assessmentCode.equals(assessmentCode))
		{
			markModified();
		}
		this.assessmentCode = assessmentCode;
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
	
	public void setLegacyAssessmentId(String aLegacyAssessmentId) {
		if (this.legacyAssessmentId == null || !this.legacyAssessmentId.equals(aLegacyAssessmentId))
		{
			markModified();
		}
		this.legacyAssessmentId = aLegacyAssessmentId;	
	}
	
	public String bind() {
		IHome home = new Home();
		home.bind(this);
		return this.getOID();		
	}
	public static Iterator findAll(IEvent event) {
		return new Home().findAll(event, SCSData.class);	
	}
	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		fetch();
		return recordType;
	}
	/**
	 * @return the spn
	 */
	public String getSpn() {
		fetch();
		return spn;
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
