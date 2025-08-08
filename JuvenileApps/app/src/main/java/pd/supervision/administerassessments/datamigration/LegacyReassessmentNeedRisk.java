package pd.supervision.administerassessments.datamigration;

import java.util.Date;
import java.util.Map;

import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;
import naming.CSCAssessmentConstants;
import naming.PDConstants;

public class LegacyReassessmentNeedRisk {
	private static String DATE_FORMAT = "yyyyMMdd";
	private static final int EIGHT = 8;
	private static final int ZERO = 0;
	private static final String ZERO_STRING = "0";
	public static LegacyReassessmentNeedRisk getData(String inputString, Map needsSchemaMap) throws ParseRuntimeException {
		LegacyReassessmentNeedRisk lan = new LegacyReassessmentNeedRisk();
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
		lan.setRiskCommunityResource(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_COMMUNITY_RESOUR, needsSchemaMap));
		lan.setRiskDrugs(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_DRUG, needsSchemaMap));
		lan.setRiskFirstAdjAge(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_FIRST_ADJ_AGE, needsSchemaMap));
		lan.setRiskPriorFelonyAdj(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_PRIOR_FELONY_ADJ, needsSchemaMap));
		lan.setRiskPriorParoleRevoc(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_PRIOR_PAROLE_REV, needsSchemaMap));
		lan.setRiskProbRelationship(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_PROB_RELATIONSHIP, needsSchemaMap));
		lan.setRiskResponseCourtCond(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_RESPONSE_COURT_COND, needsSchemaMap));
		lan.setRiskScoreTotal(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_SCORE_TOT, needsSchemaMap));
		lan.setRiskTimeEmployed(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RISK_TIME_EMPLOYED, needsSchemaMap));
		lan.setRecordCount(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RECORD_COUNT, needsSchemaMap));
		
		return lan;
	}
	private String academic;
	private String alcohol;
	private Date assessmentDate;
	private String companions;
	private Date completeDate;
	private String cstsSnu;
	private String cstsTst;
	private String drug;
	private Date dueDate;
	private String employment;
	private String entryClerk;
	private Date entryDate;
	private String financial;
	private String health;
	private Date lastChangeDate;
	private String lastChangeUser;
	private String marital;
	private String mental;
	private String poImpression;
	private String recordCount;
	private String riskAddressChange;
	private String riskAdultJuvAdj;
	private String riskAdultJuvAssault;
	private String riskAlcohol;
	private String riskCommunityResource;
	private String riskDrugs;
	private String riskFirstAdjAge;
	private String riskPriorFelonyAdj;
	private String riskPriorParoleRevoc;
	private String riskProbRelationship;
	private String riskResponseCourtCond;
	private String riskScoreTotal;
	private String riskSocialId;
	private String riskTimeEmployed;
	private String scoreTotal;
	private String sexBehavior;
	private String spn;
	private String stability;
	public String getAcademic() {
		return academic;
	}
	public String getAlcohol() {
		return alcohol;
	}
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	public String getCompanions() {
		return companions;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public String getCstsSnu() {
		return cstsSnu;
	}
	public String getCstsTst() {
		return cstsTst;
	}
	public String getDrug() {
		return drug;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public String getEmployment() {
		return employment;
	}
	public String getEntryClerk() {
		return entryClerk;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public String getFinancial() {
		return financial;
	}
	public String getHealth() {
		return health;
	}
	public Date getLastChangeDate() {
		return lastChangeDate;
	}
	public String getLastChangeUser() {
		return lastChangeUser;
	}
	public String getMarital() {
		return marital;
	}
	public String getMental() {
		return mental;
	}
	public String getPoImpression() {
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
	public String getRiskCommunityResource() {
		return riskCommunityResource;
	}
	public String getRiskDrugs() {
		return riskDrugs;
	}
	public String getRiskFirstAdjAge() {
		return riskFirstAdjAge;
	}
	public String getRiskPriorFelonyAdj() {
		return riskPriorFelonyAdj;
	}
	public String getRiskPriorParoleRevoc() {
		return riskPriorParoleRevoc;
	}
	public String getRiskProbRelationship() {
		return riskProbRelationship;
	}
	public String getRiskResponseCourtCond() {
		return riskResponseCourtCond;
	}
	public String getRiskScoreTotal() {
		return riskScoreTotal;
	}
	public String getRiskSocialId() {
		return riskSocialId;
	}
	public String getRiskTimeEmployed() {
		return riskTimeEmployed;
	}
	public String getScoreTotal() {
		return scoreTotal;
	}
	public String getSexBehavior() {
		return sexBehavior;
	}
	public String getSpn() {
		return spn;
	}
	public String getStability() {
		return stability;
	}
	public void setAcademic(String academic) {
		this.academic = academic;
	}
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	public void setCompanions(String companions) {
		this.companions = companions;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public void setCstsSnu(String cstsSnu) {
		this.cstsSnu = cstsSnu;
	}
	public void setCstsTst(String cstsTst) {
		this.cstsTst = cstsTst;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
	public void setEntryClerk(String entryClerk) {
		this.entryClerk = entryClerk;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public void setFinancial(String financial) {
		this.financial = financial;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}
	public void setMarital(String marital) {
		this.marital = marital;
	}
	public void setMental(String mental) {
		this.mental = mental;
	}
	public void setPoImpression(String poImpression) {
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
	public void setRiskCommunityResource(String riskCommunityResource) {
		this.riskCommunityResource = riskCommunityResource;
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
	public void setRiskProbRelationship(String riskProbRelationship) {
		this.riskProbRelationship = riskProbRelationship;
	}
	public void setRiskResponseCourtCond(String riskResponseCourtCond) {
		this.riskResponseCourtCond = riskResponseCourtCond;
	}
	public void setRiskScoreTotal(String riskScoreTotal) {
		this.riskScoreTotal = riskScoreTotal;
	}
	public void setRiskSocialId(String riskSocialId) {
		this.riskSocialId = riskSocialId;
	}
	public void setRiskTimeEmployed(String riskTimeEmployed) {
		this.riskTimeEmployed = riskTimeEmployed;
	}
	public void setScoreTotal(String scoreTotal) {
		this.scoreTotal = scoreTotal;
	}
	public void setSexBehavior(String sexBehavior) {
		this.sexBehavior = sexBehavior;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}
	public boolean isLsir(){
		boolean isLsir = false;
		if ((this.academic == null || this.academic.equals(ZERO) || this.academic.equals(PDConstants.BLANK))
				&& (this.alcohol == null || this.alcohol.equals(ZERO) || this.alcohol.equals(PDConstants.BLANK))
				&& (this.companions == null || this.companions.equals(ZERO) || this.companions.equals(PDConstants.BLANK))
				&& (this.drug == null || this.drug.equals(ZERO) || this.drug.equals(PDConstants.BLANK)) 
				&& (this.employment == null || this.employment.equals(ZERO) || this.employment.equals(PDConstants.BLANK)) 
				&& (this.financial == null || this.financial.equals(ZERO) || this.financial.equals(PDConstants.BLANK))
				&& (this.health == null || this.health.equals(ZERO) || this.health.equals(PDConstants.BLANK))
				&& (this.marital == null || this.marital.equals(ZERO) || this.marital.equals(PDConstants.BLANK)) 
				&& (this.mental == null || this.mental.equals(ZERO) || this.mental.equals(PDConstants.BLANK))
				&& (this.poImpression == null || this.poImpression.equals(ZERO) || this.poImpression.equals(PDConstants.BLANK))
				&& (this.sexBehavior == null || this.sexBehavior.equals(ZERO) || this.sexBehavior.equals(PDConstants.BLANK))
				&& (this.riskAddressChange == null || this.riskAddressChange.equals(ZERO) || this.riskAddressChange.equals(PDConstants.BLANK))
				&& (this.riskAdultJuvAdj == null || this.riskAdultJuvAdj.equals(ZERO) || this.riskAdultJuvAdj.equals(PDConstants.BLANK))
				&& (this.riskAdultJuvAssault == null || this.riskAdultJuvAssault.equals(ZERO) || this.riskAdultJuvAssault.equals(PDConstants.BLANK))
				&& (this.riskAlcohol == null || this.riskAlcohol.equals(ZERO) || this.riskAlcohol.equals(PDConstants.BLANK))
				&& (this.riskCommunityResource == null || this.riskCommunityResource.equals(ZERO) || this.riskCommunityResource.equals(PDConstants.BLANK))
				&& (this.riskDrugs == null || this.riskDrugs.equals(ZERO) || this.riskDrugs.equals(PDConstants.BLANK))
				&& (this.riskFirstAdjAge == null || this.riskFirstAdjAge.equals(ZERO) || this.riskFirstAdjAge.equals(PDConstants.BLANK))
				&& (this.riskPriorFelonyAdj == null || this.riskPriorFelonyAdj.equals(ZERO) || this.riskPriorFelonyAdj.equals(PDConstants.BLANK))
				&& (this.riskPriorParoleRevoc == null || this.riskPriorParoleRevoc.equals(ZERO) || this.riskPriorParoleRevoc.equals(PDConstants.BLANK))
				&& (this.riskProbRelationship == null || this.riskProbRelationship.equals(ZERO) || this.riskProbRelationship.equals(PDConstants.BLANK))
				&& (this.riskResponseCourtCond == null || this.riskResponseCourtCond.equals(ZERO) || this.riskResponseCourtCond.equals(PDConstants.BLANK))
				&& (this.riskSocialId == null || this.riskSocialId.equals(ZERO) || this.riskSocialId.equals(PDConstants.BLANK))
				&& (this.riskTimeEmployed == null || this.riskTimeEmployed.equals(ZERO) || this.riskTimeEmployed.equals(PDConstants.BLANK))){
			isLsir = true;
		}
		return isLsir;
	}
	private static final int FOURTEEN = 14;
	private static final int FIFTEEN = 15;
	private static final int TWENTYNINE = 29;
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
}
