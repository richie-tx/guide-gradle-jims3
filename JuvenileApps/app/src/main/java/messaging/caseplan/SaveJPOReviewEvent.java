//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\SaveJPOReviewEvent.java

package messaging.caseplan;


import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJPOReviewEvent extends RequestEvent 
{
   private String caseplanId;
   private Date reviewDate;
   private String reviewUserId;
   private String reviewComments;   
   private Object report;
   //added for user story 11146 
   private String recomSupLevelId;												
   private boolean supLevelAppro;
   private boolean jpoMaintainContact;
   private String jpoMaintainExplain;
	
   /**
    * @roseuid 4533BD210295
    */
   public SaveJPOReviewEvent() 
   {
    
   }
   
/**
 * @return Returns the caseplanId.
 */
public String getCaseplanId() {
	return caseplanId;
}
/**
 * @param caseplanId The caseplanId to set.
 */
public void setCaseplanId(String caseplanId) {
	this.caseplanId = caseplanId;
}
/**
 * @return Returns the reviewComments.
 */
public String getReviewComments() {
	return reviewComments;
}
/**
 * @param reviewComments The reviewComments to set.
 */
public void setReviewComments(String reviewComments) {
	this.reviewComments = reviewComments;
}
/**
 * @return the recomSupLevelId
 */
public String getRecomSupLevelId() {
	return recomSupLevelId;
}

/**
 * @param recomSupLevelId the recomSupLevelId to set
 */
public void setRecomSupLevelId(String recomSupLevelId) {
	this.recomSupLevelId = recomSupLevelId;
}

/**
 * @return the supLevelAppro
 */
public boolean isSupLevelAppro() {
	return supLevelAppro;
}

/**
 * @param supLevelAppro the supLevelAppro to set
 */
public void setSupLevelAppro(boolean supLevelAppro) {
	this.supLevelAppro = supLevelAppro;
}

/**
 * @return the jpoMaintainContact
 */
public boolean isJpoMaintainContact() {
	return jpoMaintainContact;
}

/**
 * @param jpoMaintainContact the jpoMaintainContact to set
 */
public void setJpoMaintainContact(boolean jpoMaintainContact) {
	this.jpoMaintainContact = jpoMaintainContact;
}

/**
 * @return the jpoMaintainExplain
 */
public String getJpoMaintainExplain() {
	return jpoMaintainExplain;
}

/**
 * @param jpoMaintainExplain the jpoMaintainExplain to set
 */
public void setJpoMaintainExplain(String jpoMaintainExplain) {
	this.jpoMaintainExplain = jpoMaintainExplain;
}

/**
 * @return Returns the reviewDate.
 */
public Date getReviewDate() {
	return reviewDate;
}
/**
 * @param reviewDate The reviewDate to set.
 */
public void setReviewDate(Date reviewDate) {
	this.reviewDate = reviewDate;
}
/**
 * @return Returns the reviewUserId.
 */
public String getReviewUserId() {
	return reviewUserId;
}
/**
 * @param reviewUserId The reviewUserId to set.
 */
public void setReviewUserId(String reviewUserId) {
	this.reviewUserId = reviewUserId;
}
/**
 * @return Returns the report.
 */
public Object getReport() {
	return report;
}
/**
 * @param report The report to set.
 */
public void setReport(Object report) {
	this.report = report;
}
}
