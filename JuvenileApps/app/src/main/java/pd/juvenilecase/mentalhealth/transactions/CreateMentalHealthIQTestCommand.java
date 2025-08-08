//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\CreateMentalHealthIQTestCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import messaging.mentalhealth.CreateMentalHealthIQTestEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.mentalhealth.JuvenileIQResults;

/**
 * @author cc_vnarsingoju
 * 
 */
public class CreateMentalHealthIQTestCommand implements ICommand {

	/**
	 * @roseuid 45D4AD0802A9
	 */
	public CreateMentalHealthIQTestCommand() {

	}

	/**
	 * @param event
	 * @roseuid 45D49C8A0262
	 */
	public void execute(IEvent event) {
		CreateMentalHealthIQTestEvent requestEvent = (CreateMentalHealthIQTestEvent) event;
		JuvenileIQResults juvIQResults = new JuvenileIQResults();
		juvIQResults.setTestSessId(requestEvent.getTestSessId());		
		juvIQResults.setTestNameId(requestEvent.getTestName());
		juvIQResults.setTestDate(requestEvent.getTestDate());
		juvIQResults.setFullScore(requestEvent.getFullScore());
		juvIQResults.setPerformanceScore(requestEvent.getPerformanceScore());
		juvIQResults.setVerbalScore(requestEvent.getVerbalScore());
		juvIQResults.setRecommendation(requestEvent.getRecommendations());
		juvIQResults.setVerbalComprehension(requestEvent.getVerbalComprehension());
		juvIQResults.setPerceptualReasoning(requestEvent.getPerceptualReasoning());
		juvIQResults.setNonVerbalIQ(requestEvent.getNonVerbalIQ());
		juvIQResults.setProcessingSpeed(requestEvent.getProcessingSpeed());
		juvIQResults.setWorkingMemory(requestEvent.getWorkingMemory());
		juvIQResults.setPictorialIQ(requestEvent.getPictorialIQ());
		juvIQResults.setGeometricIQ(requestEvent.getGeometricIQ());		
	}

	/**
	 * @param event
	 * @roseuid 45D49C8A026D
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 45D49C8A026F
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 45D49C8A0271
	 */
	public void update(Object anObject) {

	}

}
