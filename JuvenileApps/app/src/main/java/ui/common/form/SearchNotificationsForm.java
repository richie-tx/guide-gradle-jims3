/*
 * Created on Oct 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchNotificationsForm extends ActionForm {
	
	boolean allowOnlyYours=true;
	String notificationTypeId;
	String notificationType;
	String notificationStatusId;
	String taskStatusId;
	String taskStatus;
	String notificationStatus;
	String notificationCatId;
	String notificationCat;
	String beginDateRange;
	Date beginDate;
	Date endDate;
	String endDateRange;
	String ownerUserId;
	String notificationId;
	String noticeMessage;
	
	Collection searchResults=new ArrayList();
	

	public void clear(){
		notificationTypeId="";
		notificationType="";
		notificationStatusId="";
		notificationStatus="";
		notificationCatId="";
		notificationCat="";
		beginDateRange="";
		beginDate=null;
		endDate=null;
		endDateRange="";
		ownerUserId="";
		String taskStatusId="";
		String taskStatus="";
		notificationId = "";
		noticeMessage = "";
		
		Collection searchResults=new ArrayList();
	}
	

	
	public void setDates(){
		Date today=new Date();
		if(endDate==null){
			setEndDate(new Date());
		}
		else{
			if(endDate.after(today)){
				setEndDate(today);
			}
		}
		GregorianCalendar myCal=new GregorianCalendar();
		myCal.setTime(endDate);
		myCal.add(Calendar.MONTH, -1);
		if(beginDate==null || beginDate.after(endDate)){
			setBeginDate(myCal.getTime());  // move date back one month
		}
	}
	
	/**
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate The beginDate to set.
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
		beginDateRange="";
		if(beginDate!=null){
			beginDateRange=DateUtil.dateToString(beginDate,UIConstants.DATE_FMT_1);
		}
	}
	/**
	 * @return Returns the beginDateRange.
	 */
	public String getBeginDateRange() {
		return beginDateRange;
	}
	/**
	 * @param beginDateRange The beginDateRange to set.
	 */
	public void setBeginDateRange(String beginDateRange) {
		this.beginDateRange = beginDateRange;
		beginDate=null;
		if(beginDateRange!=null && !beginDateRange.trim().equals("")){
			beginDate=DateUtil.stringToDate(beginDateRange,UIConstants.DATE_FMT_1);
		}
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		endDateRange="";
		if(endDate!=null){
			endDateRange=DateUtil.dateToString(endDate,UIConstants.DATE_FMT_1);
		}
	}
	/**
	 * @return Returns the endDateRange.
	 */
	public String getEndDateRange() {
		return endDateRange;
	}
	/**
	 * @param endDateRange The endDateRange to set.
	 */
	public void setEndDateRange(String endDateRange) {
		this.endDateRange = endDateRange;
		endDate=null;
		if(endDateRange!=null && !endDateRange.trim().equals("")){
			endDate=DateUtil.stringToDate(endDateRange,UIConstants.DATE_FMT_1);
		}
	}
	/**
	 * @return Returns the notificationCat.
	 */
	public String getNotificationCat() {
		return notificationCat;
	}
	/**
	 * @param notificationCat The notificationCat to set.
	 */
	public void setNotificationCat(String notificationCat) {
		this.notificationCat = notificationCat;
	}
	/**
	 * @return Returns the notificationCatId.
	 */
	public String getNotificationCatId() {
		return notificationCatId;
	}
	/**
	 * @param notificationCatId The notificationCatId to set.
	 */
	public void setNotificationCatId(String notificationCatId) {
		this.notificationCatId = notificationCatId;
	}
	/**
	 * @return Returns the notificationStatus.
	 */
	public String getNotificationStatus() {
		return notificationStatus;
	}
	/**
	 * @param notificationStatus The notificationStatus to set.
	 */
	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}
	/**
	 * @return Returns the notificationStatusId.
	 */
	public String getNotificationStatusId() {
		return notificationStatusId;
	}
	/**
	 * @param notificationStatusId The notificationStatusId to set.
	 */
	public void setNotificationStatusId(String notificationStatusId) {
		this.notificationStatusId = notificationStatusId;
	}
	/**
	 * @return Returns the notificationType.
	 */
	public String getNotificationType() {
		return notificationType;
	}
	/**
	 * @param notificationType The notificationType to set.
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	/**
	 * @return Returns the notificationTypeId.
	 */
	public String getNotificationTypeId() {
		return notificationTypeId;
	}
	/**
	 * @param notificationTypeId The notificationTypeId to set.
	 */
	public void setNotificationTypeId(String notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}
	/**
	 * @return Returns the ownerUserId.
	 */
	public String getOwnerUserId() {
		return ownerUserId;
	}
	/**
	 * @param ownerUserId The ownerUserId to set.
	 */
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	/**
	 * @return Returns the searchResults.
	 */
	public Collection getSearchResults() {
		return searchResults;
	}
	/**
	 * @param searchResults The searchResults to set.
	 */
	public void setSearchResults(Collection searchResults) {
		this.searchResults = searchResults;
	}
	/**
	 * @return Returns the allowOnlyYours.
	 */
	public boolean isAllowOnlyYours() {
		return allowOnlyYours;
	}
	/**
	 * @param allowOnlyYours The allowOnlyYours to set.
	 */
	public void setAllowOnlyYours(boolean allowOnlyYours) {
		this.allowOnlyYours = allowOnlyYours;
	}
	/**
	 * @return Returns the taskStatus.
	 */
	public String getTaskStatus() {
		return taskStatus;
	}
	/**
	 * @param taskStatus The taskStatus to set.
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	/**
	 * @return Returns the taskStatusId.
	 */
	public String getTaskStatusId() {
		return taskStatusId;
	}
	/**
	 * @param taskStatusId The taskStatusId to set.
	 */
	public void setTaskStatusId(String taskStatusId) {
		this.taskStatusId = taskStatusId;
	}
	/**
	 * @return Returns the notificationId.
	 */
	public String getNotificationId() {
		return notificationId;
	}
	/**
	 * @param notificationId The notificationId to set.
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	
	/**
	 * @return Returns the noticeMessage.
	 */
	public String getNoticeMessage() {
		return noticeMessage;
	}
	/**
	 * @param noticeMessage The noticeMessage to set.
	 */
	public void setNoticeMessage(String noticeMessage) {
		this.noticeMessage = noticeMessage;
	}
}// END CLASS
