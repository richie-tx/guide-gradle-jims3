package pd.supervision.administerassessments.datamigration;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import naming.CSCAssessmentConstants;
import naming.PDConstants;

import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;

public class LegacyAssessmentNeedRisk
{
	private Date assessmentDate;
	private Date completeDate;
	private Date dueDate;
	private String entryClerk;
	private Date entryDate;
	private Date lastChangeDate;
	private String lastChangeUser;
	private String academic;
	private String alcohol;
	private String companions;
	private String drug;
	private String employment;
	private String financial;
	private String health;
	private String marital;
	private String mental;
	private String poImpression;
	private String scoreTotal;
	private String sexBehavior;
	private String stability;
	private String spn;
	private String cstsSnu;
	private String cstsTst;
	private String riskAddressChange;
	private String recordCount;
	private String riskAdultJuvAdj;
	private String riskAdultJuvAssault;
	private String riskAlcohol;
	private String riskAttitude;
	private String riskDrugs;
	private String riskFirstAdjAge;
	private String riskPriorFelonyAdj;
	private String riskPriorParoleRevoc;
	private String riskPriorParoleSuper;
	private String riskScoreTotal;
	private String riskTimeEmployed;
	private static String DATE_FORMAT = "yyyyMMdd";
	private static int ZERO = 0;
	private static String ZERO_STRING = "0";
	private static int FOURTEEN = 14;
	private static int FIFTEEN = 15;
	private static int TWENTYNINE = 29;
	private static int EIGHT = 8;
	public static LegacyAssessmentNeedRisk getData(String inputString, Map needsSchemaMap) throws ParseRuntimeException {
		LegacyAssessmentNeedRisk lan = new LegacyAssessmentNeedRisk();
		lan.setAcademic(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_ACADEMIC, needsSchemaMap));
		lan.setAlcohol(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_ALCOHOL, needsSchemaMap));
		lan.setCompanions(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_COMPANIONS, needsSchemaMap));
		lan.setCstsSnu(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.CSTS_SNU, needsSchemaMap));
		lan.setDrug(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_DRUG, needsSchemaMap));
		lan.setEmployment(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_EMPLOYMENT, needsSchemaMap));
		lan.setFinancial(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_FINANCIAL, needsSchemaMap));
		lan.setHealth(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_HEALTH, needsSchemaMap));
		lan.setMarital(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_MARITAL, needsSchemaMap));
		lan.setMental(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_MENTAL, needsSchemaMap));
		lan.setPoImpression(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_PO_IMPRESSION, needsSchemaMap));
		lan.setScoreTotal(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_SCORE_TOT, needsSchemaMap));
		lan.setSexBehavior(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_SEX_BEHAVE, needsSchemaMap));
		lan.setStability(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.NEED_STABILITY, needsSchemaMap));
		StringBuffer aSpn = new StringBuffer(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.SPN, needsSchemaMap));
		while (aSpn.length() < EIGHT){
			aSpn.insert(ZERO,ZERO_STRING);
		}
		lan.setSpn(aSpn.toString());
		String aString = AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.DUE_DATE, needsSchemaMap);
		if (!aString.equals(PDConstants.BLANK)){
			try {
				lan.setDueDate(DateUtil.stringToDate(aString, DATE_FORMAT));
			} catch (ParseRuntimeException e) {
				//throw e;
				lan.setDueDate(null);
			}
		} else {
			lan.setDueDate(null);
		}
		lan.setRiskAddressChange(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_ADDRESS_CHANGE, needsSchemaMap));
		lan.setRiskAdultJuvAdj(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_ADULT_JUV_ADJ, needsSchemaMap));
		lan.setRiskAdultJuvAssault(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_ADULT_JUV_ASSAULT, needsSchemaMap));
		lan.setRiskAlcohol(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_ALCOHOL, needsSchemaMap));
		lan.setRiskAttitude(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_ATTITUDE, needsSchemaMap));
		lan.setRiskDrugs(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_DRUG, needsSchemaMap));
		lan.setRiskFirstAdjAge(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_FIRST_ADJ_AGE, needsSchemaMap));
		lan.setRiskPriorFelonyAdj(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_PRIOR_FELONY_ADJ, needsSchemaMap));
		lan.setRiskPriorParoleRevoc(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_PRIOR_PAROLE_REVOC, needsSchemaMap));
		lan.setRiskPriorParoleSuper(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_PRIOR_PAROLE_SUPER, needsSchemaMap));
		lan.setRiskScoreTotal(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_SCORE_TOT, needsSchemaMap));
		lan.setRiskTimeEmployed(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_TIME_EMPLOYED, needsSchemaMap));
		lan.setRecordCount(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RECORD_COUNT, needsSchemaMap));
		
		return lan;
	}
	public String getAcademic()
	{
		return academic;
	}
	public String getAlcohol()
	{
		return alcohol;
	}
	public Date getAssessmentDate()
	{
		return assessmentDate;
	}
	public String getCompanions()
	{
		return companions;
	}
	public Date getCompleteDate()
	{
		return completeDate;
	}
	public String getCstsSnu()
	{
		return cstsSnu;
	}
	public String getCstsTst()
	{
		return cstsTst;
	}
	public String getDrug()
	{
		return drug;
	}
	public Date getDueDate()
	{
		return dueDate;
	}
	public String getEmployment()
	{
		return employment;
	}
	public String getEntryClerk()
	{
		return entryClerk;
	}
	public Date getEntryDate()
	{
		return entryDate;
	}
	public String getFinancial()
	{
		return financial;
	}
	public String getHealth()
	{
		return health;
	}
	public Date getLastChangeDate()
	{
		return lastChangeDate;
	}
	public String getLastChangeUser() {
		return lastChangeUser;
	}
	public String getMarital()
	{
		return marital;
	}
	public String getMental()
	{
		return mental;
	}
	public int getNeedsLevel()
	{
		int needsScore = AssessmentDataMigrationHelper.getIntegerFromString(this.getScoreTotal()).intValue();
		int needsLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD;
		
		if(needsScore <= FOURTEEN)
		{
			needsLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MINIMUM_CD;
		}
		else if((needsScore >= FIFTEEN) && (needsScore <= TWENTYNINE))
		{
			needsLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD;
		}
		else
		{
			needsLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MAXIMUM_CD;
		}
		return needsLevel;
	}
	public String getPoImpression()
	{
		return poImpression;
	}

	public String getRecordCount() {
		return recordCount;
	}
	public String getRiskAddressChange() {
		return riskAddressChange;
	}
	public String getRiskAdultJuvAdj() {
		return riskAdultJuvAdj;
	}
	public String getRiskAdultJuvAssault() {
		return riskAdultJuvAssault;
	}
	public String getRiskAlcohol() {
		return riskAlcohol;
	}
	public String getRiskAttitude() {
		return riskAttitude;
	}
	public String getRiskDrugs() {
		return riskDrugs;
	}
	public String getRiskFirstAdjAge() {
		return riskFirstAdjAge;
	}
	public int getRiskLevel()
	{
		int riskScore = AssessmentDataMigrationHelper.getIntegerFromString(this.getRiskScoreTotal());
		int riskLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD;
		
		if((riskScore >= 0) && (riskScore <= 7 ))
		{
			riskLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MINIMUM_CD;
		}
		else if((riskScore >= 8) && (riskScore <= FOURTEEN))
		{
			riskLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD;
		}
		else if(riskScore >= FIFTEEN)
		{
			riskLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MAXIMUM_CD;
		}
		return riskLevel;
	}
	public String getRiskPriorFelonyAdj() {
		return riskPriorFelonyAdj;
	}
	public String getRiskPriorParoleRevoc() {
		return riskPriorParoleRevoc;
	}
	public String getRiskPriorParoleSuper() {
		return riskPriorParoleSuper;
	}
	public String getRiskScoreTotal() {
		return riskScoreTotal;
	}
	public String getRiskTimeEmployed() {
		return riskTimeEmployed;
	}
	public String getScoreTotal()
	{
		return scoreTotal;
	}
	public String getSexBehavior()
	{
		return sexBehavior;
	}
	public String getSpn()
	{
		return spn;
	}
	public String getStability()
	{
		return stability;
	}
	public boolean isLsir(){
		boolean isLsir = false;
		if ((this.academic.equals(ZERO) || this.academic.equals(PDConstants.BLANK))
				&& (this.alcohol.equals(ZERO) || this.alcohol.equals(PDConstants.BLANK))
				&& (this.companions.equals(ZERO) || this.companions.equals(PDConstants.BLANK))
				&& (this.drug.equals(ZERO) || this.drug.equals(PDConstants.BLANK)) 
				&& (this.employment.equals(ZERO) || this.employment.equals(PDConstants.BLANK)) 
				&& (this.financial.equals(ZERO) || this.financial.equals(PDConstants.BLANK))
				&& (this.health.equals(ZERO) || this.health.equals(PDConstants.BLANK))
				&& (this.marital.equals(ZERO) || this.marital.equals(PDConstants.BLANK)) 
				&& (this.mental.equals(ZERO) || this.mental.equals(PDConstants.BLANK))
				&& (this.poImpression.equals(ZERO) || this.poImpression.equals(PDConstants.BLANK))
				&& (this.sexBehavior.equals(ZERO) || this.sexBehavior.equals(PDConstants.BLANK))
				&& (this.riskAddressChange.equals(ZERO) || this.riskAddressChange.equals(PDConstants.BLANK))
				&& (this.riskAdultJuvAdj.equals(ZERO) || this.riskAdultJuvAdj.equals(PDConstants.BLANK))
				&& (this.riskAdultJuvAssault.equals(ZERO) || this.riskAdultJuvAssault.equals(PDConstants.BLANK))
				&& (this.riskAlcohol.equals(ZERO) || this.riskAlcohol.equals(PDConstants.BLANK))
				&& (this.riskAttitude.equals(ZERO) || this.riskAttitude.equals(PDConstants.BLANK))
				&& (this.riskDrugs.equals(ZERO) || this.riskDrugs.equals(PDConstants.BLANK))
				&& (this.riskFirstAdjAge.equals(ZERO) || this.riskFirstAdjAge.equals(PDConstants.BLANK))
				&& (this.riskPriorFelonyAdj.equals(ZERO) || this.riskPriorFelonyAdj.equals(PDConstants.BLANK))
				&& (this.riskPriorParoleRevoc.equals(ZERO) || this.riskPriorParoleRevoc.equals(PDConstants.BLANK))
				&& (this.riskPriorParoleSuper.equals(ZERO) || this.riskPriorParoleSuper.equals(PDConstants.BLANK))
				&& (this.riskTimeEmployed.equals(ZERO) || this.riskTimeEmployed.equals(PDConstants.BLANK))){
			isLsir = true;
		}
		return isLsir;
	}
	public void setAcademic(String academic)
	{
		this.academic = academic;
	}
	public void setAlcohol(String alcohol)
	{
		this.alcohol = alcohol;
	}
	public void setAssessmentDate(Date assessmentDate)
	{
		this.assessmentDate = assessmentDate;
	}
	public void setCompanions(String companions)
	{
		this.companions = companions;
	}
	public void setCompleteDate(Date completeDate)
	{
		this.completeDate = completeDate;
	}
	public void setCstsSnu(String cstsSnu)
	{
		this.cstsSnu = cstsSnu;
	}
	public void setCstsTst(String cstsTst)
	{
		this.cstsTst = cstsTst;
	}
	public void setDrug(String drug)
	{
		this.drug = drug;
	}
	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}
	public void setEmployment(String employment)
	{
		this.employment = employment;
	}
	public void setEntryClerk(String entryClerk)
	{
		this.entryClerk = entryClerk;
	}
	public void setEntryDate(Date entryDate)
	{
		this.entryDate = entryDate;
	}
	public void setFinancial(String financial)
	{
		this.financial = financial;
	}
	public void setHealth(String health)
	{
		this.health = health;
	}
	public void setLastChangeDate(Date lastChangeDate)
	{
		this.lastChangeDate = lastChangeDate;
	}
	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}
	public void setMarital(String marital)
	{
		this.marital = marital;
	}
	public void setMental(String mental)
	{
		this.mental = mental;
	}
	public void setPoImpression(String poImpression)
	{
		this.poImpression = poImpression;
	}
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	public void setRiskAddressChange(String riskAddressChange) {
		this.riskAddressChange = riskAddressChange;
	}
	public void setRiskAdultJuvAdj(String riskAdultJuvAdj) {
		this.riskAdultJuvAdj = riskAdultJuvAdj;
	}
	public void setRiskAdultJuvAssault(String riskAdultJuvAssault) {
		this.riskAdultJuvAssault = riskAdultJuvAssault;
	}
	public void setRiskAlcohol(String riskAlcohol) {
		this.riskAlcohol = riskAlcohol;
	}
	public void setRiskAttitude(String riskAttitude) {
		this.riskAttitude = riskAttitude;
	}
	public void setRiskDrugs(String riskDrugs) {
		this.riskDrugs = riskDrugs;
	}
	public void setRiskFirstAdjAge(String riskFirstAdjAge) {
		this.riskFirstAdjAge = riskFirstAdjAge;
	}
	public void setRiskPriorFelonyAdj(String riskPriorFelonyAdj) {
		this.riskPriorFelonyAdj = riskPriorFelonyAdj;
	}
	
	public void setRiskPriorParoleRevoc(String riskPriorParoleRevoc) {
		this.riskPriorParoleRevoc = riskPriorParoleRevoc;
	}
	public void setRiskPriorParoleSuper(String riskPriorParoleSuper) {
		this.riskPriorParoleSuper = riskPriorParoleSuper;
	}
	
	public void setRiskScoreTotal(String riskScoreTotal) {
		this.riskScoreTotal = riskScoreTotal;
	}
	public void setRiskTimeEmployed(String riskTimeEmployed) {
		this.riskTimeEmployed = riskTimeEmployed;
	}
	public void setScoreTotal(String scoreTotal)
	{
		this.scoreTotal = scoreTotal;
	}
	public void setSexBehavior(String sexBehavior)
	{
		this.sexBehavior = sexBehavior;
	}
	
	public void setSpn(String spn)
	{
		this.spn = spn;
	}

	public void setStability(String stability)
	{
		this.stability = stability;
	}

}
