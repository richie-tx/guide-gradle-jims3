package pd.productionsupport.transactions;

import java.util.Iterator;

import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.mentalhealth.JuvenileAchievementResults;
import pd.juvenilecase.mentalhealth.JuvenileAdaptiveBehaviorResults;
import pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults;
import pd.juvenilecase.mentalhealth.JuvenileDSMIVResults;
import pd.juvenilecase.mentalhealth.JuvenileIQResults;
import pd.juvenilecase.mentalhealth.JuvenileTestingResults;
import messaging.productionsupport.GetProductionSupportTestingSessionInfoEvent;
import messaging.productionsupport.reply.ProductionSupportTestingSessionInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetProductionSupportTestingSessionInfoCommand implements ICommand
{
    public void execute(IEvent event) {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetProductionSupportTestingSessionInfoEvent getEvent = (GetProductionSupportTestingSessionInfoEvent) event;
	
	if ( getEvent.testingSessionId != null
		&& getEvent.testingSessionId.length() > 0 ) {
	    JuvenileTestingResults testingResult = JuvenileTestingResults.find(getEvent.testingSessionId);
	    if ( testingResult != null ) {
		ProductionSupportTestingSessionInfoResponseEvent testingSessionRsp = getTestingSessionInfo( testingResult );
		dispatch.postEvent(testingSessionRsp);
	    }
	} else {
	    Iterator<JuvenileTestingResults> testingResultIter = JuvenileTestingResults.findAll("juvenileNum", getEvent.getJuvenileId() );
	    while ( testingResultIter.hasNext() ){
		JuvenileTestingResults juvenileTestingResult = (JuvenileTestingResults) testingResultIter.next();
		ProductionSupportTestingSessionInfoResponseEvent testingSessionRsp = getTestingSessionInfo( juvenileTestingResult  );
		dispatch.postEvent(testingSessionRsp);
	    }
	}
}
    
    
    private ProductionSupportTestingSessionInfoResponseEvent getTestingSessionInfo( JuvenileTestingResults testingResult) {
	ProductionSupportTestingSessionInfoResponseEvent testingSessionResp = new ProductionSupportTestingSessionInfoResponseEvent();
	testingSessionResp.setTestingSessionId( testingResult.getOID());
	testingSessionResp.setTestSessionDate( testingResult.getSessionDate() );
	JJSJuvenile juvenileInfo = JJSJuvenile.find( testingResult.getJuvenileNum() );
	if ( juvenileInfo != null ){
	    String fullName = juvenileInfo.getLastName() + ", " + juvenileInfo.getFirstName();
	    testingSessionResp.setFullName(fullName);
	    testingSessionResp.setJuvenileId(juvenileInfo.getJuvenileNum());
	}
	
	
	Iterator<JuvenileDSMIVResults> juvenileDSMIVResultIter = JuvenileDSMIVResults.findAll("testSessId", testingResult.getOID());
	StringBuffer jCMHDSMLISTIdBuff = new StringBuffer();
	int jCMHDSMLISTIdIndex = 0;
	while ( juvenileDSMIVResultIter.hasNext() ) {
	    JuvenileDSMIVResults juvenileDSMIVResult = (JuvenileDSMIVResults)juvenileDSMIVResultIter.next();
	    if ( jCMHDSMLISTIdIndex > 0 ) {
		jCMHDSMLISTIdBuff.append( ", " +  juvenileDSMIVResult.getOID() );
	    } else {
		jCMHDSMLISTIdBuff.append( juvenileDSMIVResult.getOID() );
	    }
	    jCMHDSMLISTIdIndex++;
	}
	
	testingSessionResp.setjCMHDSMLISTId( jCMHDSMLISTIdBuff.toString() );
	
	Iterator<JuvenileIQResults> juvenileIQResultIter = JuvenileIQResults.findAll("testSessId", testingResult.getOID());
	StringBuffer jCMHIQTESTLISTIdBuff = new StringBuffer();
	int jCMHIQTESTLISTIdIndex = 0;
	while ( juvenileIQResultIter.hasNext() ) {
	    JuvenileIQResults juvenileIQResult = (JuvenileIQResults) juvenileIQResultIter.next();
	    if ( jCMHIQTESTLISTIdIndex > 0 ){
		jCMHIQTESTLISTIdBuff.append(", " + juvenileIQResult.getOID() );
	    } else {
		jCMHIQTESTLISTIdBuff.append(juvenileIQResult.getOID() );
	    }
	    jCMHIQTESTLISTIdIndex++;
	}
	
	testingSessionResp.setjCMHIQTESTLISTId( jCMHIQTESTLISTIdBuff.toString() );
	
	Iterator<JuvenileAchievementResults> juvenileAchievementResultIter = JuvenileAchievementResults.findAll("testSessId", testingResult.getOID());
	StringBuffer jCMHACHLISTIdBuff = new StringBuffer();
	int jCMHACHLISTIdIndex = 0;
	while ( juvenileAchievementResultIter.hasNext() ) {
	    JuvenileAchievementResults juvenileAchievementResult = (JuvenileAchievementResults) juvenileAchievementResultIter.next();
	    if ( jCMHACHLISTIdIndex > 0 ) {
		jCMHACHLISTIdBuff.append(", " + juvenileAchievementResult.getOID() );
	    } else {
		jCMHACHLISTIdBuff.append( juvenileAchievementResult.getOID() );
	    }
	    jCMHACHLISTIdIndex++;
	} 
	
	testingSessionResp.setjCMHACHLISTId(jCMHACHLISTIdBuff.toString());
	
	Iterator<JuvenileAdaptiveFunctioningResults> JuvenileAdaptiveFunctioningResultIter = JuvenileAdaptiveFunctioningResults.findAll("testSessId", testingResult.getOID());
	StringBuffer jCMHADFNCLISTIdBuff =  new StringBuffer();
	int jCMHADFNCLISTIdIndex = 0;
	
	while ( JuvenileAdaptiveFunctioningResultIter.hasNext() ) {
	    JuvenileAdaptiveFunctioningResults juvenileAdaptiveFunctioningResult = (JuvenileAdaptiveFunctioningResults) JuvenileAdaptiveFunctioningResultIter.next();
	    if ( jCMHADFNCLISTIdIndex > 0 ) {
		jCMHADFNCLISTIdBuff.append(", " + juvenileAdaptiveFunctioningResult.getOID() );
	    } else {
		jCMHADFNCLISTIdBuff.append( juvenileAdaptiveFunctioningResult.getOID() );
	    }
	    jCMHADFNCLISTIdIndex++;
	}
	testingSessionResp.setjCMHADFNCLISTId( jCMHADFNCLISTIdBuff.toString() );
	
	Iterator<JuvenileAdaptiveBehaviorResults>juvenileAdaptiveBehaviorResultIter = JuvenileAdaptiveBehaviorResults.findAll("testSessId", testingResult.getOID());
	StringBuffer jCMHABSLISTIdBuff = new StringBuffer();
	int jCMHABSLISTIdIndex = 0;
	
	while ( juvenileAdaptiveBehaviorResultIter.hasNext() ){
	    JuvenileAdaptiveBehaviorResults juvenileAdaptiveBehaviorResult = (JuvenileAdaptiveBehaviorResults) juvenileAdaptiveBehaviorResultIter.next();
	    if ( jCMHABSLISTIdIndex > 0 ){
		jCMHABSLISTIdBuff.append(", " + juvenileAdaptiveBehaviorResult.getOID()  );
	    } else {
		jCMHABSLISTIdBuff.append( juvenileAdaptiveBehaviorResult.getOID()  );
	    }
	    jCMHABSLISTIdIndex++;
	}
	
	testingSessionResp.setjCMHABSLISTId(jCMHABSLISTIdBuff.toString());
	
	return testingSessionResp;
    }

}
