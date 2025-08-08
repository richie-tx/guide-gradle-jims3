package pd.productionsupport.transactions;

import java.util.Date;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.productionsupport.GetProductionSupportActivityEvent;
import messaging.productionsupport.GetProductionSupportRiskAnalysisEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.riskanalysis.RiskAnalysis;

public class GetProductionSupportActivityCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportActivityCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportActivityEvent getActivityEvent = (GetProductionSupportActivityEvent) event;
		Activity activity = Activity.find(getActivityEvent.getActivityId());
			
		ActivityResponseEvent activityResponseEvent = new ActivityResponseEvent();
		
		if(activity != null){
			activityResponseEvent.setActivityId(activity.getOID());
			if(activity.getSupervisionNumber() != null){
				activityResponseEvent.setCasefileId((new Integer(activity.getSupervisionNumber())).toString());
			}
			activityResponseEvent.setCodeId(activity.getActivityCodeId());
			activityResponseEvent.setComments(activity.getComments());
			activityResponseEvent.setUpdateComments(activity.getUpdateComments());
			activityResponseEvent.setInactiveDate(activity.getInactiveDate());
			activityResponseEvent.setTitle(activity.getTitle());
			activityResponseEvent.setActivityDate(activity.getActivityDate());
			activityResponseEvent.setDetentionId(activity.getDetentionId());
			activityResponseEvent.setDetentionTime(activity.getTime());
			activityResponseEvent.setActivityTime(activity.getActivityTime());
			activityResponseEvent.setActivityendTime(activity.getActivityEndTime());
			activityResponseEvent.setLatitude( activity.getLatitude());
			activityResponseEvent.setLongitude( activity.getLongitude());
			
			//production support 
			if(activity.getCreateUserID() != null){
				activityResponseEvent.setCreateUserID(activity.getCreateUserID());
			}
			if(activity.getCreateTimestamp() != null){
				activityResponseEvent.setCreateDate(new Date(activity.getCreateTimestamp().getTime()));
			}
			if(activity.getUpdateUserID() != null){
				activityResponseEvent.setUpdateUser(activity.getUpdateUserID());
			}
			if(activity.getUpdateTimestamp() != null){
				activityResponseEvent.setUpdateDate(new Date(activity.getUpdateTimestamp().getTime()));
			}
			if(activity.getCreateJIMS2UserID() != null){
				activityResponseEvent.setCreateJIMS2UserID(activity.getCreateJIMS2UserID());
			}
			if(activity.getUpdateJIMS2UserID() != null){
				activityResponseEvent.setUpdateJIMS2UserID(activity.getUpdateJIMS2UserID());
			}
			
		dispatch.postEvent(activityResponseEvent);			
		}

	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}
