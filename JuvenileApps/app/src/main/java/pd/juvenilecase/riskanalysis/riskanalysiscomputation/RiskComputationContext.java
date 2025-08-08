package pd.juvenilecase.riskanalysis.riskanalysiscomputation;

import pd.exception.ComputationValidationException;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import mojo.km.messaging.IEvent;

/** 
 * @author PAlcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class RiskComputationContext {
	
	private RiskComputation riskComputation;
	private RiskComputationReponseEvent riskComputationReponseEvent;

	/** 
	 * @param event
	 * @return
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public RiskComputationReponseEvent computeRiskAnalysis(IEvent event) throws ComputationValidationException {

		riskComputation = RiskComputation.getRiskComputation(event); 
		riskComputationReponseEvent =  riskComputation.compute(); 

		return riskComputationReponseEvent;

	}
}