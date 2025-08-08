//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\SaveCLMReviewCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.util.Iterator;

import naming.ActivityConstants;
import naming.CasePlanConstants;
import pd.juvenilecase.casefile.ActivityHelper;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.Goal;
import messaging.casefile.CreateActivityEvent;
import messaging.caseplan.SaveCLMReviewEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

public class SaveCLMReviewCommand implements ICommand {

	/**
	 * @roseuid 4533B83B0089
	 */
	public SaveCLMReviewCommand() {

	}

	/**
	 * @param event
	 * @roseuid 452FE430005C
	 */
	public void execute(IEvent event) {
		SaveCLMReviewEvent evt = (SaveCLMReviewEvent) event;
		String caseplanID = evt.getCaseplanID();
		CasePlan cp = CasePlan.find(caseplanID);
		String cpStatus = "";
		String goalStatus = "";
		String activityCode = "";
		String activityTitle = "";
		String activityComment = evt.getActivityComments();
		if (cp != null) {
			if (evt.isAccept()) {
				// status of caseplan "reviewed" and goals "approved"
				cpStatus = CasePlanConstants.CP_STATUS_REVIEWED;
				goalStatus = CasePlanConstants.GOAL_STATUS_APPROVED;
				// create activity for accept
				activityCode = ActivityConstants.CASE_PLAN_ACCEPTED_BY_CLM;
				activityComment = "Caseload Manager review completed.";
			} else {
				// change status of goals and caseplan
				cpStatus = CasePlanConstants.CP_STATUS_PENDING;
				goalStatus = CasePlanConstants.GOAL_STATUS_PENDING;
				// create activity for reject
				activityCode = ActivityConstants.CASE_PLAN_REJECTED_BY_CLM;
			}

			activityTitle = "Caseload Manager review completed.";
			// set statusses
			cp.setStatusId(cpStatus);
			Iterator ite = cp.getTheGoal().iterator();
			// for goals that are locked, flip the statusses
			while (ite.hasNext()) {
				Goal goal = (Goal) ite.next();
				if (goal.getStatusId().equalsIgnoreCase("L")) {
					goal.setStatusId(goalStatus);
				}
			}
			// create the activity
			CreateActivityEvent activityEvt = new CreateActivityEvent();
			activityEvt.setSupervisionNumber(cp.getSupervisionNumber());
			activityEvt
					.setActivityCategoryId(ActivityConstants.ACTIVITY_CATEGORY_REPORTING);
			activityEvt.setActivityCodeId(activityCode);
			activityEvt
					.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_CASEPLAN);
			activityEvt.setComments(activityComment);
			activityEvt.setActivityTitle(activityTitle);
			activityEvt.setActivityDate(DateUtil.getCurrentDate());
			ActivityHelper helper = new ActivityHelper();
			helper.createActivity(activityEvt);
			helper = null;
		}
	}

	/**
	 * @param event
	 * @roseuid 452FE4300065
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 452FE4300067
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 452FE430006F
	 */
	public void update(Object anObject) {

	}

}
