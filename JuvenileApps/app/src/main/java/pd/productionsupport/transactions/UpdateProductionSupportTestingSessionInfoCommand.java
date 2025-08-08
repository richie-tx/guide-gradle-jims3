package pd.productionsupport.transactions;

import java.util.Iterator;

import pd.juvenilecase.mentalhealth.JuvenileAchievementResults;
import pd.juvenilecase.mentalhealth.JuvenileAdaptiveBehaviorResults;
import pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults;
import pd.juvenilecase.mentalhealth.JuvenileDSMIVResults;
import pd.juvenilecase.mentalhealth.JuvenileIQResults;
import pd.juvenilecase.mentalhealth.JuvenileTestingResults;
import messaging.productionsupport.UpdateProductionSupportTestingSessionInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;

public class UpdateProductionSupportTestingSessionInfoCommand implements ICommand
{
    public void execute(IEvent event) {
	UpdateProductionSupportTestingSessionInfoEvent updateEvent = (UpdateProductionSupportTestingSessionInfoEvent) event;
	if ( updateEvent.getTestingSessionId() != null
		&& updateEvent.getTestingSessionId().length() > 0
		&& updateEvent.getTestingSessionDate() != null
		&& updateEvent.getTestingSessionDate().length() > 0 ){
	    JuvenileTestingResults testingResult = JuvenileTestingResults.find(updateEvent.getTestingSessionId() );
	    if ( testingResult != null ) {
		testingResult.setSessionDate( DateUtil.stringToDate(updateEvent.getTestingSessionDate(), DateUtil.DATE_FMT_1) );
        	IHome home = new Home();
        	home.bind(testingResult);
        	
	    }
	    
	    Iterator<JuvenileDSMIVResults> juvenileDSMIVResultIter = JuvenileDSMIVResults.findAll("testSessId", updateEvent.getTestingSessionId());
	    while ( juvenileDSMIVResultIter.hasNext() ){
		JuvenileDSMIVResults juvenileDSMIVResult = (JuvenileDSMIVResults)juvenileDSMIVResultIter.next();
		juvenileDSMIVResult.setTestDate( DateUtil.stringToDate(updateEvent.getTestingSessionDate(), DateUtil.DATE_FMT_1) );
		IHome home = new Home();
		home.bind(juvenileDSMIVResult);
	    }
	    
	    Iterator<JuvenileIQResults> juvenileIQResultIter = JuvenileIQResults.findAll("testSessId", updateEvent.getTestingSessionId());
	    while ( juvenileIQResultIter.hasNext() ) {
		JuvenileIQResults juvenileIQResult = (JuvenileIQResults) juvenileIQResultIter.next();
		juvenileIQResult.setTestDate( DateUtil.stringToDate(updateEvent.getTestingSessionDate(), DateUtil.DATE_FMT_1) );
		IHome home = new Home();
		home.bind(juvenileIQResult);
	    }
	    
	    Iterator<JuvenileAchievementResults> juvenileAchievementResultIter = JuvenileAchievementResults.findAll("testSessId", updateEvent.getTestingSessionId());
	    while ( juvenileAchievementResultIter.hasNext() ) {
		JuvenileAchievementResults juvenileAchievementResult = (JuvenileAchievementResults) juvenileAchievementResultIter.next();
		juvenileAchievementResult.setTestDate( DateUtil.stringToDate(updateEvent.getTestingSessionDate(), DateUtil.DATE_FMT_1) );
		IHome home = new Home();
		home.bind(juvenileAchievementResult);
	    }
	    
	    Iterator<JuvenileAdaptiveFunctioningResults> JuvenileAdaptiveFunctioningResultIter = JuvenileAdaptiveFunctioningResults.findAll("testSessId", updateEvent.getTestingSessionId());
	    while ( JuvenileAdaptiveFunctioningResultIter.hasNext() ) {
		JuvenileAdaptiveFunctioningResults juvenileAdaptiveFunctioningResult = (JuvenileAdaptiveFunctioningResults) JuvenileAdaptiveFunctioningResultIter.next();
		juvenileAdaptiveFunctioningResult.setTestDate( DateUtil.stringToDate(updateEvent.getTestingSessionDate(), DateUtil.DATE_FMT_1) );
		IHome home = new Home();
		home.bind( juvenileAdaptiveFunctioningResult );
		
	    }
	    
	    Iterator<JuvenileAdaptiveBehaviorResults>juvenileAdaptiveBehaviorResultIter = JuvenileAdaptiveBehaviorResults.findAll("testSessId", updateEvent.getTestingSessionId());
	    while ( juvenileAdaptiveBehaviorResultIter.hasNext() ){
		JuvenileAdaptiveBehaviorResults juvenileAdaptiveBehaviorResult = (JuvenileAdaptiveBehaviorResults) juvenileAdaptiveBehaviorResultIter.next();
		juvenileAdaptiveBehaviorResult.setTestDate( DateUtil.stringToDate(updateEvent.getTestingSessionDate(), DateUtil.DATE_FMT_1) );
		IHome home = new Home();
		home.bind(juvenileAdaptiveBehaviorResult );
			
	    }
	    
	}
    }

}
