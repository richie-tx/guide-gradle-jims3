/*
 * Created on October 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.casefile.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DSMDiagnosisResponseEvent extends ResponseEvent{

	private String diagnosisCd;
	private String	diagnosis ;
	private String	severityCd ;
	private String severity;
	private String conditionCd;
	private String condition;
	private String closingInfoId;
	private String testSessId;
	public String dsmDiagnosisId;
	
	/**
	 * @return Returns the conditionCd.
	 */
	public String getConditionCd() {
		return conditionCd;
	}
	/**
	 * @param conditionCd The conditionCd to set.
	 */
	public void setConditionCd(String conditionCd) {
		this.conditionCd = conditionCd;	
	}
	/**
	 * @return Returns the diagnosis.
	 */
	public String getDiagnosis() {
		return diagnosis;
	}
	
	/**
	 * @return Returns the severityCd.
	 */
	public String getSeverityCd() {
		return severityCd;
	}
	/**
	 * @return Returns the diagnosisCd.
	 */
	public String getDiagnosisCd() {
		return diagnosisCd;
	}
	/**
	 * @param diagnosisCd The diagnosisCd to set.
	 */
	public void setDiagnosisCd(String diagnosisCd) {
		this.diagnosisCd = diagnosisCd;
		
	}
	/**
	 * @param severityCd The severityCd to set.
	 */
	public void setSeverityCd(String severityCd) {
		this.severityCd = severityCd;	
	}
	/**
	 * @return Returns the closingInfoId.
	 */
	public String getClosingInfoId() {
		return closingInfoId;
	}
	/**
	 * @param closingInfoId The closingInfoId to set.
	 */
	public void setClosingInfoId(String closingInfoId) {
		this.closingInfoId = closingInfoId;
	}
	/**
	 * @return Returns the condition.
	 */
	public String getCondition() {
		return condition;
	}
	
	/**
	 * @return Returns the severity.
	 */
	public String getSeverity() {
		return severity;		
	}
	
	/**
	 * @return Returns the testSessId.
	 */
	public String getTestSessId() {
		return testSessId;
	}
	/**
	 * @param testSessId The testSessId to set.
	 */
	public void setTestSessId(String testSessId) {
		this.testSessId = testSessId;
	}
	/**
	 * @return Returns the dsmDiagnosisId.
	 */
	public String getDsmDiagnosisId() {
		return dsmDiagnosisId;
	}
	/**
	 * @param dsmDiagnosisId The dsmDiagnosisId to set.
	 */
	public void setDsmDiagnosisId(String dsmDiagnosisId) {
		this.dsmDiagnosisId = dsmDiagnosisId;
	}
	/**
	 * @param condition The condition to set.
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @param diagnosis The diagnosis to set.
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	/**
	 * @param severity The severity to set.
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}
}
