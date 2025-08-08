package pd.juvenilecase.riskanalysis.transactions;

import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import messaging.riskanalysis.GetProgressPrefillInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class GetProgressPrefillInfoCommand implements ICommand {

	public void execute(IEvent event) {
		GetProgressPrefillInfoEvent preEvent = (GetProgressPrefillInfoEvent) event;
		
		if (preEvent.isUpdateRisk()){
			RiskAnalysis ra = RiskAnalysis.find(preEvent.getRiskAnalysisId());
			preEvent.setCaseFileId(new Integer(ra.getCasefileID()).toString());
			preEvent.setCaseFileId(String.valueOf(ra.getCasefileID()));
		}
		PDRiskAnalysisHelper.sendProgressPrefillInfo(preEvent);
	}

}
