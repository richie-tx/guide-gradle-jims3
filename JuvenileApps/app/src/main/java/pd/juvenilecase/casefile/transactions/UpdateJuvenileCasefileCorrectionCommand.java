/*
 * Created on Nov 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.casefile.transactions;

import java.util.Date;
import java.util.List;

import naming.ActivityConstants;
import naming.PDJuvenileCaseConstants;

import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.casefile.CommonAppDocMetadata;
import messaging.casefile.GetActivitiesByCasefileAndActivityIdEvent;
import messaging.casefile.GetCommonAppDocsEvent;
import messaging.casefile.UpdateJuvenileCasefileCorrectionEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;

/**
 * @author jjose
 *
 */
public class UpdateJuvenileCasefileCorrectionCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		UpdateJuvenileCasefileCorrectionEvent updateEvent = (UpdateJuvenileCasefileCorrectionEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		JuvenileCasefile casefile = null;
		if(updateEvent.getSupervisionNumber() != null && !(updateEvent.getSupervisionNumber().equals(""))) {
			casefile = JuvenileCasefile.find(updateEvent.getSupervisionNumber());
			if(casefile==null){
				ErrorResponseEvent myError=createErrorRespEvt("Casefile could not be found and was not updated");
				dispatch.postEvent(myError);
				myError = null;
			}
//			<KISHORE>JIMS200059286 : Expand: Correct Casefile Data to all casefiles(KK)
//			else if(casefile.getCreateUserID()==null || !casefile.getCreateUserID().trim().equalsIgnoreCase("JIGWP")){  // JIGWP in the Create User Id field indicates this is a migrated casefile
//				ErrorResponseEvent myError=createErrorRespEvt("Casefile is not a migrated casefile and thus cannot be changed");
//				dispatch.postEvent(myError);
//			}
			else{
				
				if(updateEvent.isChangeCasefileStatus() && updateEvent.getCasfileStatusId()!=null && !updateEvent.getCasfileStatusId().equals("")){
					if(casefile.getCaseStatusId().equalsIgnoreCase(PDJuvenileCaseConstants.CASE_STATUS_CLOSE) || casefile.getCaseStatusId().equalsIgnoreCase(PDJuvenileCaseConstants.CASESTATUS_PENDINGCLOSE))
					{
						casefile.setCaseStatusId(updateEvent.getCasfileStatusId());
						this.removeClosingInfo(updateEvent.getSupervisionNumber());
					}
				}
				if(updateEvent.isChangeCasefileType() && updateEvent.getSupTypeId()!=null && !updateEvent.getSupTypeId().equals("")){
					if(casefile.getSupervisionTypeId()!=null && casefile.getSupervisionTypeId().equalsIgnoreCase(updateEvent.getPrevSupTypeId())){
						casefile.setSupervisionTypeId(updateEvent.getSupTypeId());
					}
					else{
						ErrorResponseEvent myError=createErrorRespEvt("Current casefile type does not match requested update of casefile type");  // this indicates user may have pressed the back button
						dispatch.postEvent(myError);
						myError = null;
					}
				}
				JuvenileCasefileResponseEvent casefileResponse =
					JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
				dispatch.postEvent(casefileResponse);
				if ( casefileResponse.getJuvenileNum() != null 
					&& updateEvent.getCasfileStatusId().equals("A")) {
				    JuvenileCore jCore = JuvenileCore.findCore(casefileResponse.getJuvenileNum());
    				    	if ( jCore != null ) {
    				    	    jCore.setYouthDeathReason(null);
    				    	    jCore.setYouthDeathVerification(null);
    				    	    jCore.setDeathDate(null);
    				    	    jCore.setDeathAge(0);
    				    	}
				}
				casefileResponse = null;
			}
		}
		
		updateEvent = null;
		dispatch = null;
		casefile = null;
		
	}
	
	private void removeClosingInfo(String casefileId) {
		
		CasefileClosingInfo closingInfo = JuvenileCaseHelper.getCasefileClosingDetails(casefileId);
		
		if(closingInfo != null)
		{
			closingInfo.delete();	
		}						

		GetActivitiesByCasefileAndActivityIdEvent actEvt = new GetActivitiesByCasefileAndActivityIdEvent();
		actEvt.setCasefileId(casefileId);
		
		this.deleteClosingActivities(actEvt, ActivityConstants.CASE_CLOSING_PENDING);
		this.deleteClosingActivities(actEvt, ActivityConstants.CASEFILE_SUBMITTED_TO_SUPERVISOR_FOR_APPROVAL);
		this.deleteClosingActivities(actEvt, ActivityConstants.CASE_REVIEW_CLOSING_APPROVED);
		this.deleteClosingActivities(actEvt, ActivityConstants.CASE_REVIEWED_FOR_CLOSING);
		this.deleteClosingActivities(actEvt, ActivityConstants.CLOSING_LETTER_GENERATED);
		this.deleteClosingActivities(actEvt, ActivityConstants.CLOSING_PACKET_GENERATED);
		this.deleteClosingActivities(actEvt, ActivityConstants.CASEFILE_CLOSING_OVERRIDE_EXCEPTIONS);
		this.deleteClosingActivities(actEvt, ActivityConstants.EXIT_PLAN_GENERATED);
		this.deleteClosingActivities(actEvt, ActivityConstants.CASEFILE_CLOSING_REJECTED);

		GetCommonAppDocsEvent getDocsEvt = new GetCommonAppDocsEvent();
		getDocsEvt.setCasefileId(casefileId);
		
		List <CommonAppDocMetadata> docs = CollectionUtil.iteratorToList(CommonAppDocMetadata.findAll(getDocsEvt));
		
		CommonAppDocMetadata doc = null;
		
		for (int i = 0; i < docs.size(); i++) {
			doc = docs.get(i);
			if (doc.getDocTypeCd() != null 
					&& (doc.getDocTypeCd().equals(CommonAppDocResponseEvent.COMMUNITY_EXIT_PLAN)
							/*|| doc.getDocTypeCd().equals(CommonAppDocResponseEvent.COMMONAPP_REPORT) US #35740*/
							|| doc.getDocTypeCd().equals(CommonAppDocResponseEvent.RESIDENTIAL_EXIT_PLAN))){
				doc.delete();
			}
		}
		
		getDocsEvt = null;
		docs = null;
		doc = null;
		closingInfo = null;
		actEvt = null;
		
	}

	private void deleteClosingActivities(GetActivitiesByCasefileAndActivityIdEvent actEvt, String activityCodeId) {
		
		actEvt.setActivityCodeId(activityCodeId);
		
		List <Activity> activityList = CollectionUtil.iteratorToList(Activity.findAll(actEvt));
		
		Activity activity = null;
		for (int i = 0; i < activityList.size(); i++) {
			activity = activityList.get(i);
			activity.delete();
		}
		
		activity = null;
		activityList = null;
	}

	private ErrorResponseEvent createErrorRespEvt(String message){
		ErrorResponseEvent myError=new ErrorResponseEvent();
		myError.setDateTimeStamp(new Date());
		myError.setMessage(message);
		return myError;
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
