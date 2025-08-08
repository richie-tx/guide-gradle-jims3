//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import java.sql.Timestamp;

import mojo.km.messaging.Composite.CompositeRequest;


public class UpdateNCResponseEvent extends CompositeRequest 
{
   private String statusId;
   private Timestamp statusChangedDate;
   private Timestamp filledDate;
   private String presentedBy;
   private String signedBy;
   private Timestamp lastContactDate;
   private String addressTypeId;
   private int hoursCompleted;
   private int hoursOrdered;
   private String totalSpecimenAnalyzed;
   private String positionIdOfSignedBy;
   private String positionIdOfPresentedBy;
   private String comments;
   private String requestType;
   private String ncResponseId;
   private String streetNumber;
   private String streetName;
   private String city;
   private String state;
   private String zipcode;
   private int orderId;
   private String reportType;
   private String caseId;
   private boolean filing; 
   private String commentType;
   private String summaryOfCourtActions;
   private String taskId;
   private boolean updatePresentedRequest;
   
   
	
	/**
	 * @return the filing
	 */
	public boolean isFiling() {
		return filing;
	}
	/**
	 * @param filing the filing to set
	 */
	public void setFiling(boolean filing) {
		this.filing = filing;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the requestType.
	 */
	public String getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType The requestType to set.
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return Returns the streetNumber.
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	/**
	 * @param streetNumber The streetNumber to set.
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return Returns the zipcode.
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode The zipcode to set.
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return Returns the addressType.
	 */
	public String getAddressTypeId() {
		return addressTypeId;
	}
	/**
	 * @param addressType The addressType to set.
	 */
	public void setAddressTypeId(String addressType) {
		this.addressTypeId = addressType;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the filledDate.
	 */
	public Timestamp getFilledDate() {
		return filledDate;
	}
	/**
	 * @param filledDate The filledDate to set.
	 */
	public void setFilledDate(Timestamp filledDate) {
		this.filledDate = filledDate;
	}
	/**
	 * @return Returns the hoursCompleted.
	 */
	public int getHoursCompleted() {
		return hoursCompleted;
	}
	/**
	 * @param hoursCompleted The hoursCompleted to set.
	 */
	public void setHoursCompleted(int hoursCompleted) {
		this.hoursCompleted = hoursCompleted;
	}
	/**
	 * @return Returns the hoursOrdered.
	 */
	public int getHoursOrdered() {
		return hoursOrdered;
	}
	/**
	 * @param hoursOrdered The hoursOrdered to set.
	 */
	public void setHoursOrdered(int hoursOrdered) {
		this.hoursOrdered = hoursOrdered;
	}
	/**
	 * @return Returns the lastContactDate.
	 */
	public Timestamp getLastContactDate() {
		return lastContactDate;
	}
	/**
	 * @param lastContactDate The lastContactDate to set.
	 */
	public void setLastContactDate(Timestamp lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
	/**
	 * @return Returns the positionIdOfPresentedBy.
	 */
	public String getPositionIdOfPresentedBy() {
		return positionIdOfPresentedBy;
	}
	/**
	 * @param positionIdOfPresentedBy The positionIdOfPresentedBy to set.
	 */
	public void setPositionIdOfPresentedBy(String positionIdOfPresentedBy) {
		this.positionIdOfPresentedBy = positionIdOfPresentedBy;
	}
	/**
	 * @return Returns the positionIdOfSignedBy.
	 */
	public String getPositionIdOfSignedBy() {
		return positionIdOfSignedBy;
	}
	/**
	 * @param positionIdOfSignedBy The positionIdOfSignedBy to set.
	 */
	public void setPositionIdOfSignedBy(String positionIdOfSignedBy) {
		this.positionIdOfSignedBy = positionIdOfSignedBy;
	}
	/**
	 * @return Returns the presentedBy.
	 */
	public String getPresentedBy() {
		return presentedBy;
	}
	/**
	 * @param presentedBy The presentedBy to set.
	 */
	public void setPresentedBy(String presentedBy) {
		this.presentedBy = presentedBy;
	}
	/**
	 * @return Returns the signedBy.
	 */
	public String getSignedBy() {
		return signedBy;
	}
	/**
	 * @param signedBy The signedBy to set.
	 */
	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}
	/**
	 * @return Returns the statusChangedDate.
	 */
	public Timestamp getStatusChangedDate() {
		return statusChangedDate;
	}
	/**
	 * @param statusChangedDate The statusChangedDate to set.
	 */
	public void setStatusChangedDate(Timestamp statusChangedDate) {
		this.statusChangedDate = statusChangedDate;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return Returns the totalSpecimenAnalyzed.
	 */
	public String getTotalSpecimenAnalyzed() {
		return totalSpecimenAnalyzed;
	}
	/**
	 * @param totalSpecimenAnalyzed The totalSpecimenAnalyzed to set.
	 */
	public void setTotalSpecimenAnalyzed(String totalSpecimenAnalyzed) {
		this.totalSpecimenAnalyzed = totalSpecimenAnalyzed;
	}
   /**
    * @roseuid 47D9BBFC0289
    */
   public UpdateNCResponseEvent() 
   {
    
   }
	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the commentType
	 */
	public String getCommentType() {
		return commentType;
	}
	/**
	 * @param commentType the commentType to set
	 */
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	
	public String getSummaryOfCourtActions() {
		return summaryOfCourtActions;
    }
	
	public void setSummaryOfCourtActions(String summaryOfCourtActions) {
		this.summaryOfCourtActions = summaryOfCourtActions;
	}
	
	public String getTaskId() {
		return taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public boolean isUpdatePresentedRequest() {
		return updatePresentedRequest;
	}
	
	public void setUpdatePresentedRequest(boolean updatePresentedRequest) {
		this.updatePresentedRequest = updatePresentedRequest;
	}	
	
}
