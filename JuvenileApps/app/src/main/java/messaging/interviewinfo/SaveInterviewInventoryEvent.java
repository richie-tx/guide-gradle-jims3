package messaging.interviewinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IAddress;
import messaging.interviewinfo.domintf.IInterviewDetail;
import mojo.km.messaging.RequestEvent;

public class SaveInterviewInventoryEvent extends RequestEvent implements IInterviewDetail
{
	// Summary
	private String interviewId;	
	private String interviewStatusCd;
	private List recordsInventoryIds = new ArrayList();	// ids as Strings 
	private String otherInventoryRecords;
		
   /**
    * @roseuid 448ECBC60139
    */
   public SaveInterviewInventoryEvent() 
   {
    
   }

   /**
	* @return
	*/
   public String getInterviewId()
   {
	   return interviewId;
   }
	/**
	 * @return
	 */
	public List getInventoryRecordsIds()
	{
		return recordsInventoryIds;
	}
	
   /**
	* @return
	*/
   public String getOtherInventoryRecords()
   {
	   return otherInventoryRecords;
   }

	/**
	 * @return
	 */
	public void setOtherInventoryRecords( String inventoryRecords )
	{
		otherInventoryRecords = inventoryRecords;
	}

	// None of the following methods from IInterviewDetail are used in the command.

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#getLocationId()
	 */
	public String getLocationId() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#setLocationId(java.lang.String)
	 */
	public void setLocationId(String id) {
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#getJuvLocUnitId()
	 */
	public String getJuvLocUnitId() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#setJuvLocUnitId(java.lang.String)
	 */
	public void setJuvLocUnitId(String id) {
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#getSummaryNotes()
	 */
	public String getSummaryNotes() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#setSummaryNotes(java.lang.String)
	 */
	public void setSummaryNotes(String notes) {
		
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#getAddress()
	 */
	public IAddress getAddress() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#setAddress(messaging.contact.domintf.IAddress)
	 */
	public void setAddress(IAddress anAddress) {
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#getInterviewPersons()
	 */
	public List getInterviewPersons() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#hasTaskList()
	 */
	public boolean hasTaskList() {
		return false;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#setHasTaskList(boolean)
	 */
	public void setHasTaskList(boolean b) {
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#getCasefileId()
	 */
	public String getCasefileId() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#setCasefileId(java.lang.String)
	 */
	public void setCasefileId(String id) {
		
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#getInterviewDate()
	 */
	public Date getInterviewDate() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#getInterviewTypeId()
	 */
	public String getInterviewTypeId() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#getLocationDescription()
	 */
	public String getLocationDescription() {
		return null;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#setInterviewDate(java.util.Date)
	 */
	public void setInterviewDate(Date date) {
		
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#setInterviewId(java.lang.String)
	 */
	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#setInterviewTypeId(java.lang.String)
	 */
	public void setInterviewTypeId(String string) {
		
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#setLocationDescription(java.lang.String)
	 */
	public void setLocationDescription(String string) {
		
	}

	/**
	 * @return Returns the interviewStatusCd.
	 */
	public String getInterviewStatusCd() {
		return interviewStatusCd;
	}
	/**
	 * @param interviewStatusCd The interviewStatusCd to set.
	 */
	public void setInterviewStatusCd(String interviewStatusCd) {
		this.interviewStatusCd = interviewStatusCd;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#setInterviewStatusDescription(java.lang.String)
	 */
	public void setInterviewStatusDescription(String string) {
		
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#getInterviewStatusDescription()
	 */
	public String getInterviewStatusDescription() {
		return null;
	}
	
	public void setCalEventId(String string) {
		
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewDetail#getInterviewStatusDescription()
	 */
	public String getCalEventId() {
		return null;
	}
	
	
}
