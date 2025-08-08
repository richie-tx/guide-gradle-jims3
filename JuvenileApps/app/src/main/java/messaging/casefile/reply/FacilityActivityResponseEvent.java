/**
 * Added for journal entries for facility. under activities.
 * U.S. #27342
 */

package messaging.casefile.reply;

import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class FacilityActivityResponseEvent extends ResponseEvent implements Comparable{
	
    private Date activityDate;
    
    private Date inactiveDate;

    private String activityDesc;

    private String activityId;

    private String casefileId;

    private String categoryDesc;

    private String categoryId;

    private String codeId;

    private String comments;

    private String title;

    private String typeDesc;

    private String typeId;
    
    private String createdBy;
    
    private String activityTime;
    
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	
	 /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object ob)
    {
        if (ob == null)
            return 1; // this makes any null objects go to the bottom change this to -1 if you want
        // the top of the list
        if (this.activityDate == null)
            return -1; // this makes any null objects go to the bottom change this to 1 if you want
        // the top of the list
        FacilityActivityResponseEvent facilityEvt = (FacilityActivityResponseEvent) ob;
        return facilityEvt.getActivityDate().compareTo(activityDate);
    }
	/**
	 * @return the activityDate
	 */
	public Date getActivityDate() {
		return activityDate;
	}
	/**
	 * @param activityDate the activityDate to set
	 */
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	/**
	 * @return the inactiveDate
	 */
	public Date getInactiveDate() {
		return inactiveDate;
	}
	/**
	 * @param inactiveDate the inactiveDate to set
	 */
	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}
	/**
	 * @return the activityDesc
	 */
	public String getActivityDesc() {
		return activityDesc;
	}
	/**
	 * @param activityDesc the activityDesc to set
	 */
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	/**
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	/**
	 * @return the categoryDesc
	 */
	public String getCategoryDesc() {
		return categoryDesc;
	}
	/**
	 * @param categoryDesc the categoryDesc to set
	 */
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * @return the codeId
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * @param codeId the codeId to set
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the typeDesc
	 */
	public String getTypeDesc() {
		return typeDesc;
	}
	/**
	 * @param typeDesc the typeDesc to set
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	/**
	 * @return the typeId
	 */
	public String getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}
	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}
	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}
	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}
	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	public String getActivityTime()
	{
	    return activityTime;
	}
	public void setActivityTime(String activityTime)
	{
	    this.activityTime = activityTime;
	}
	//code added for  US 35963, task: 43840
	public static Comparator CaseReviewJournalSummaryFacilityActivityComparator = new Comparator() {
		public int compare(Object facilityActivity, Object otherFacilityActivity) {
			
		  int result = 0;
		  String facActivityDescription = ((FacilityActivityResponseEvent)facilityActivity).getActivityDesc();
		  String otherFacActivityDescription = ((FacilityActivityResponseEvent)otherFacilityActivity).getActivityDesc();
		  Date facActivityDate = ((FacilityActivityResponseEvent)facilityActivity).getActivityDate();
		  Date otherFacActivityDate = ((FacilityActivityResponseEvent)otherFacilityActivity).getActivityDate();
		  //below edits/commented for bug 51303, the list is sorted only chronologically now 
		/*  if(facActivityDescription == null || facActivityDescription.equals("")){
			  result = -1;
		  } else if(otherFacActivityDescription == null || otherFacActivityDescription.equals("")){
			  result = 1;		  }else{
			  result = facActivityDescription.compareTo(otherFacActivityDescription);
		  }*/ 
		  
		 // if(result == 0){
			  if(facActivityDate == null)
			  {
				  result = -1;
			  }else if(otherFacActivityDate == null)
			  {
				  result = 1;
			  }
			  else 
			  {
				  result = otherFacActivityDate.compareTo(facActivityDate);
			  }
		//  }
		  return result;
		}	
	};
	
}
