package pd.juvenilecase.casefile;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Iterator;
import pd.codetable.Code;
import mojo.km.messaging.IEvent;

/**
 * @roseuid 45D4B49E02BC
 */
public class JuvenileExitReportDSMDiagnosis extends PersistentObject
{
	private String diagnosisCd;
	private String conditionCd;
	private String severityCd;
	private String closingInfoId;
	private String testSessId;
	private String diagnosisId;
	/**
	 * 
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 Properties for facilityType
	 * @contextKey HEALTH_CONDITION_LEVEL
	 */
	private Code condition = null;
	/**
	 * 
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 Properties for facilityType
	 * @contextKey HEALTH_CONDITION_SEVERITY
	 */
	private Code severity = null;
	private String conditionId;
	private String severityId;

	/**
	 * @roseuid 45D4B49E02BC
	 */
	public JuvenileExitReportDSMDiagnosis()
	{
	}

	/**
	 * @return Returns the closingInfoId.
	 */
	public String getClosingInfoId()
	{
		fetch();
		return closingInfoId;
	}

	/**
	 * @param closingInfoId The closingInfoId to set.
	 */
	public void setClosingInfoId(String closingInfoId)
	{
		if (this.closingInfoId == null || !this.closingInfoId.equals(closingInfoId))
		{
			markModified();
		}
		this.closingInfoId = closingInfoId;
	}

	/**
	 * @return Returns the conditionCd.
	 */
	public String getConditionCd()
	{
		fetch();
		return conditionCd;
	}

	/**
	 * @param conditionCd The conditionCd to set.
	 */
	public void setConditionCd(String conditionCd)
	{
		if (this.conditionCd == null || !this.conditionCd.equals(conditionCd))
		{
			markModified();
		}
		this.conditionCd = conditionCd;
	}

	/**
	 * @return Returns the diagnosisCd.
	 */
	public String getDiagnosisCd()
	{
		fetch();
		return diagnosisCd;
	}

	/**
	 * @param diagnosisCd The diagnosisCd to set.
	 */
	public void setDiagnosisCd(String diagnosisCd)
	{
		if (this.diagnosisCd == null || !this.diagnosisCd.equals(diagnosisCd))
		{
			markModified();
		}
		this.diagnosisCd = diagnosisCd;
	}

	/**
	 * @return Returns the diagnosisId.
	 */
	public String getDiagnosisId()
	{
		fetch();
		return diagnosisId;
	}

	/**
	 * @param diagnosisId The diagnosisId to set.
	 */
	public void setDiagnosisId(String diagnosisId)
	{
		if (this.diagnosisId == null || !this.diagnosisId.equals(diagnosisId))
		{
			markModified();
		}
		this.diagnosisId = diagnosisId;
	}

	/**
	 * @return Returns the severityCd.
	 */
	public String getSeverityCd()
	{
		fetch();
		return severityCd;
	}

	/**
	 * @param severityCd The severityCd to set.
	 */
	public void setSeverityCd(String severityCd)
	{
		if (this.severityCd == null || !this.severityCd.equals(severityCd))
		{
			markModified();
		}
		this.severityCd = severityCd;
	}

	/**
	 * @return Returns the testSessId.
	 */
	public String getTestSessId()
	{
		fetch();
		return testSessId;
	}

	/**
	 * @param testSessId The testSessId to set.
	 */
	public void setTestSessId(String testSessId)
	{
		if (this.testSessId == null || !this.testSessId.equals(testSessId))
		{
			markModified();
		}
		this.testSessId = testSessId;
	}

	/**
	 * 
	 * @return Returns the condition.
	 */
	public Code getCondition()
	{
		fetch();
		initCondition();
		return condition;
	}

	
	/**
	 * 
	 * @return Returns the severity.
	 */
	public Code getSeverity()
	{
		fetch();
		initSeverity();
		return severity;
	}


	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setConditionId(String conditionId)
	{
		if (this.conditionId == null || !this.conditionId.equals(conditionId))
		{
			markModified();
		}
		condition = null;
		this.conditionId = conditionId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getConditionId()
	{
		fetch();
		return conditionId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCondition()
	{
		if (condition == null)
		{
			condition = (Code) new mojo.km.persistence.Reference(conditionId, Code.class,
					"HEALTH_CONDITION_LEVEL").getObject();
		}
	}

	/**
	 * set the type reference for class member condition
	 */
	public void setCondition(Code condition)
	{
		if (this.condition == null || !this.condition.equals(condition))
		{
			markModified();
		}
		if (condition.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(condition);
		}
		setConditionId("" + condition.getOID());
		condition.setContext("HEALTH_CONDITION_LEVEL");
		this.condition = (Code) new mojo.km.persistence.Reference(condition).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setSeverityId(String severityId)
	{
		if (this.severityId == null || !this.severityId.equals(severityId))
		{
			markModified();
		}
		severity = null;
		this.severityId = severityId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSeverityId()
	{
		fetch();
		return severityId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSeverity()
	{
		if (severity == null)
		{
			severity = (Code) new mojo.km.persistence.Reference(severityId, Code.class,
					"HEALTH_CONDITION_SEVERITY").getObject();
		}
	}

	/**
	 * set the type reference for class member severity
	 */
	public void setSeverity(Code severity)
	{
		if (this.severity == null || !this.severity.equals(severity))
		{
			markModified();
		}
		if (severity.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(severity);
		}
		setSeverityId("" + severity.getOID());
		severity.setContext("HEALTH_CONDITION_SEVERITY");
		this.severity = (Code) new mojo.km.persistence.Reference(severity).getObject();
	}
	
	/** 
	 * @roseuid 45AF7A0A0192
	 * @return Iterator of JuvenileDSMDiagnosis
	* @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileExitReportDSMDiagnosis.class);
	}
	
	/**
	* @return JuvenileDSMDiagnosis
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileExitReportDSMDiagnosis find(String aDiagnosisId)
	{
		JuvenileExitReportDSMDiagnosis diagnosis = null;
		IHome home = new Home();
		diagnosis = (JuvenileExitReportDSMDiagnosis) home.find(aDiagnosisId, JuvenileExitReportDSMDiagnosis.class);
		return diagnosis;
	}	
	
	
}
