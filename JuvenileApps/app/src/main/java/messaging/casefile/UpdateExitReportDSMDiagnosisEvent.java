//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\UpdateJuvenileCasefileEvent.java

package messaging.casefile;

import mojo.km.messaging.RequestEvent;

public class UpdateExitReportDSMDiagnosisEvent extends RequestEvent 
{
   public String dsmDiagnosisId;
   public String testSessId;
   public String casefileCosingInfoId;
   public String diagnosisCd;
   public String conditionCd;
   public String severityCd;
  
   
   /**
    * @roseuid 44CF771F026F
    */
   public UpdateExitReportDSMDiagnosisEvent() 
   {
    
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
 * @return Returns the casefileCosingInfoId.
 */
public String getCasefileCosingInfoId() {
	return casefileCosingInfoId;
}
/**
 * @param casefileCosingInfoId The casefileCosingInfoId to set.
 */
public void setCasefileCosingInfoId(String casefileCosingInfoId) {
	this.casefileCosingInfoId = casefileCosingInfoId;
}
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
 * @return Returns the severityCd.
 */
public String getSeverityCd() {
	return severityCd;
}
/**
 * @param severityCd The severityCd to set.
 */
public void setSeverityCd(String severityCd) {
	this.severityCd = severityCd;
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
}
