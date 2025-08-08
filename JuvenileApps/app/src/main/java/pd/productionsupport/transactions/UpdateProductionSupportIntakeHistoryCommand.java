package pd.productionsupport.transactions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.security.PDSecurityHelper;
import messaging.productionsupport.UpdateProductionSupportIntakeHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class UpdateProductionSupportIntakeHistoryCommand implements ICommand
{
    public void execute(IEvent event) throws Exception{
	UpdateProductionSupportIntakeHistoryEvent updateEvent = (UpdateProductionSupportIntakeHistoryEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if ( updateEvent.getIntakeHistoryId() != null ){
	    Iterator<JJSSVIntakeHistory>intakeHistoryIter = JJSSVIntakeHistory.findAll("OID", updateEvent.getIntakeHistoryId());
	    
	    if ( intakeHistoryIter.hasNext() ) {
		JJSSVIntakeHistory intakeHistory = (JJSSVIntakeHistory) intakeHistoryIter.next();
		intakeHistory.setAssignmentType(updateEvent.getAssignmentType());
		intakeHistory.setSupervisionTypeId(updateEvent.getSupervisionTypeCode());
		intakeHistory.setAssignmentDate(updateEvent.getAssignmentDate());
		intakeHistory.setIntakeDate(updateEvent.getIntakeDate() );
		intakeHistory.setIntakeDecisionId(updateEvent.getIntakeDecision());
		intakeHistory.setJpoId(updateEvent.getIntakeJPO());
		intakeHistory.setJuvenileNum(updateEvent.getJuvenileNumber());
		intakeHistory.setReferralNumber(updateEvent.getReferralNumber());
		//intakeHistory.setUpdateUserID(PDSecurityHelper.getLogonId());
		intakeHistory.setUpdateTimestamp(new Timestamp( new Date().getTime()) );
		IHome home = new Home();
		home.bind(intakeHistory);
		
		
;	    }
	}
    }
    
}
