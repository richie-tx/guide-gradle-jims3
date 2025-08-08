package pd.supervision.administerassessments.datamigration;

import java.util.Date;
import java.util.Map;

import mojo.km.exceptionhandling.ParseRuntimeException;

public class LegacyAssessmentSCS
{
	private String cstsSnu;
	private String cstsTst;
	private String spn;
	private String strategyCode;
	private Date strategyDate;
	private String entryClerk;
	private Date entryDate;
	private Date lastChangeDate;
	private String lastChangeUser;
	private String recordCount;
	public String getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	public String getCstsSnu() {
		return cstsSnu;
	}
	public String getCstsTst() {
		return cstsTst;
	}
	public String getEntryClerk() {
		return entryClerk;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public Date getLastChangeDate() {
		return lastChangeDate;
	}
	public String getLastChangeUser() {
		return lastChangeUser;
	}
	public String getSpn() {
		return spn;
	}
	public String getStrategyCode() {
		return strategyCode;
	}
	public Date getStrategyDate() {
		return strategyDate;
	}
	public void setCstsSnu(String cstsSnu) {
		this.cstsSnu = cstsSnu;
	}
	public void setCstsTst(String cstsTst) {
		this.cstsTst = cstsTst;
	}
	public void setEntryClerk(String entryClerk) {
		this.entryClerk = entryClerk;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public void setStrategyCode(String strategyCode) {
		this.strategyCode = strategyCode;
	}
	public void setStrategyDate(Date strategyDate) {
		this.strategyDate = strategyDate;
	}		

	private static final int EIGHT = 8;
	private static final int ZERO = 0;
	
	public static LegacyAssessmentSCS getData(String inputString, Map scsSchemaMap) throws ParseRuntimeException {
		LegacyAssessmentSCS scs = new LegacyAssessmentSCS();
		scs.setCstsSnu(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.CSTS_SNU, scsSchemaMap));
		scs.setCstsTst(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.CSTS_TST, scsSchemaMap));
		scs.setRecordCount(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.RECORD_COUNT, scsSchemaMap));
		scs.setSpn(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.SPN, scsSchemaMap));
		if (scs.getSpn().length() < EIGHT){
			StringBuffer sb = new StringBuffer(scs.getSpn());
			while (sb.length() < EIGHT){
				sb.insert(0, ZERO);
			}
			scs.setSpn(sb.toString());
		}
		scs.setStrategyCode(AssessmentDataMigrationHelper.getDataValue(inputString, AssessmentDataMigrationConstants.STRATEGY_CODE, scsSchemaMap));
		
		return scs;
	}
}
