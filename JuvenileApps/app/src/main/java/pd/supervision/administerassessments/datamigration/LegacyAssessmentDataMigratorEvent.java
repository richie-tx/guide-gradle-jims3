package pd.supervision.administerassessments.datamigration;

public class LegacyAssessmentDataMigratorEvent {
	private int restartKey;
	private boolean isUpdate;
	private String defendantId;
	public String getDefendantId() {
		return defendantId;
	}
	public boolean getIsUpdate() {
		return isUpdate;
	}
	public int getRestartKey() {
		return restartKey;
	}
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	public void setIsUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public void setRestartKey(int restartKey) {
		this.restartKey = restartKey;
	}
}
