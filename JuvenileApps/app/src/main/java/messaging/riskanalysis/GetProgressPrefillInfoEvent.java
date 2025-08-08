package messaging.riskanalysis;

public class GetProgressPrefillInfoEvent extends CheckProgressPreConditionsEvent {
	private String riskAnalysisId;
	private boolean updateRisk;

	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}

	public void setUpdateRisk(boolean updateRisk) {
		this.updateRisk = updateRisk;
	}

	public boolean isUpdateRisk() {
		return updateRisk;
	}
}
