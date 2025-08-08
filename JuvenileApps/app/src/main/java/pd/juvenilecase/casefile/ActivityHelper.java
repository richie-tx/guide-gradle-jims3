/*
 * Created on Jan 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.casefile;

import java.util.Iterator;
import pd.codetable.criminal.JuvenileActivityTypeCode;
import messaging.casefile.CreateActivityEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author dapte
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivityHelper {
	
	/**
	 * createActivity
	 * @param request
	 * @return
	 */
	public Activity createActivity(CreateActivityEvent request) {
		Activity activity = new Activity();
		if (request.getActivityCodeId() != null
				&& !request.getActivityCodeId().equals("")) {
			if (request.isFromAction() || request.isRecordActivity()) {
				activity.setActivityCodeId(request.getActivityCodeId());
				activity.setActivityDate(DateUtil.getCurrentDate());
				activity.setSupervisionNumber(request.getSupervisionNumber());
				activity.setComments(request.getComments());
				if (request.getActivityTime() != null) {
				    activity.setActivityTime(request.getActivityTime());
				}
			} else {
				activity.setSupervisionNumber(request.getSupervisionNumber());
				activity.setActivityCodeId(request.getActivityCodeId());
				activity.setComments(request.getComments());
				activity.setActivityDate(request.getActivityDate());
				activity.setTitle(request.getActivityTitle());
				activity.setDetentionId(request.getDetentionId());
				//add activity time US 105434
				activity.setTime(request.getTime());
				if (request.getActivityTime() != null) {
				    activity.setActivityTime(request.getActivityTime());
				}
			}
		}
		return activity;
	}
	
	/**
	 * Get Category and Type. 
	 * @param activityCode
	 * @return
	 */
	public static Activity getCategoryAndTypeCode(String activityCode) {
		Iterator iter = JuvenileActivityTypeCode.findAll("code", activityCode);
		Activity activity = new Activity();
		while (iter.hasNext()) {
			JuvenileActivityTypeCode juvenileActivityTypeCode = (JuvenileActivityTypeCode) iter.next();
			activity.setActivityCategoryId(juvenileActivityTypeCode.getCategoryId());
			activity.setActivityTypeId(juvenileActivityTypeCode.getTypeId());
		}
		return activity;
	}
	
	/**
	 * GetActivityTypeCode
	 * U.S. #27342
	 */
	  public static JuvenileActivityTypeCode getActivityTypeCode(String activityCode) {
		JuvenileActivityTypeCode juvenileActivityTypeCode = null;
		Iterator<JuvenileActivityTypeCode> iter = JuvenileActivityTypeCode.findAll("code", activityCode);
		while (iter.hasNext()) {
			juvenileActivityTypeCode = (JuvenileActivityTypeCode) iter.next();
			break;
		}
		return juvenileActivityTypeCode;
	}
}
