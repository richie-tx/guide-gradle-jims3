//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\GetJournalEntriesEvent.java

package messaging.casefile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


public class GetJournalEntriesEvent extends RequestEvent 
{
   private String juvenileId="";
   private String casefileId="";
   private String journalCategoryCd="";
   private Date fromDate;
   private Date endDate;
   private String lastName="";
   private String firstName="";
   private String middleName="";
   private String userId="";
   private String referralNumber = "";
   
   //added for User Story 26444
   private String supervisionCategory;
   
   /**
    * @roseuid 47E25878000D
    */
   public GetJournalEntriesEvent() 
   {
    
   }

	public String getCasefileId() {
		return casefileId;
	}
	
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getJournalCategoryCd() {
		return journalCategoryCd;
	}
	
	public void setJournalCategoryCd(String journalCategoryCd) {
		this.journalCategoryCd = journalCategoryCd;
	}
	
	public String getJuvenileId() {
		return juvenileId;
	}
	
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	/**
	 * @return the referralNumber
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	
	/**
	 * @param referralNumber the referralNumber to set
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}

	public String getSupervisionCategory() {
		return supervisionCategory;
	}

	public void setSupervisionCategory(String supervisionCategory) {
		this.supervisionCategory = supervisionCategory;
	}

}
