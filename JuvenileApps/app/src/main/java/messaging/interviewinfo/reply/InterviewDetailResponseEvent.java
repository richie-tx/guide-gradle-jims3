package messaging.interviewinfo.reply;

import java.util.ArrayList;
import java.util.List;

import messaging.contact.domintf.IAddress;
import messaging.contact.to.Address;
import messaging.interviewinfo.domintf.IInterviewDetail;

/**
 * 
 *
 */
public class InterviewDetailResponseEvent extends InterviewResponseEvent implements IInterviewDetail
{
	private String locationId;
	private String juvLocUnitId;
	private IAddress address = new Address();
	private List recordsInventoryIds = new ArrayList();	// ids as Strings 
	private String otherInventoryRecords;
	private String summaryNotes;
	private String userComments;
	private String progressReport;
	private List interviewPersons = new ArrayList();		
	private String interviewStatusCd;
	private String interviewStatusDescription;
	private String calEventId;
	
	/**
	 * @return
	 */
	public String getLocationId()
	{
		return locationId;
	}

	/**
	 * @param string
	 */
	public void setLocationId(String string)
	{
		locationId = string;
	}

	/**
	 * @return
	 */
	public IAddress getAddress()
	{
		return address;
	}

	/**
	 * 
	 */
	public void setAddress( IAddress anAddress )
	{
		address = anAddress;
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

	/**
	 * @return
	 */
	public String getSummaryNotes()
	{
		return summaryNotes;
	}

	/**
	 * 
	 */
	public void setSummaryNotes( String notes )
	{
		summaryNotes = notes;
	}
   
	/**
	 * 
	 */
	public List getInterviewPersons()
	{
		return interviewPersons;
	}
	
	/**
	 * @return Returns the juvLocUnitId.
	 */
	public String getJuvLocUnitId() {
		return juvLocUnitId;
	}
	/**
	 * @param juvLocUnitId The juvLocUnitId to set.
	 */
	public void setJuvLocUnitId(String juvLocUnitId) {
		this.juvLocUnitId = juvLocUnitId;
	}
	/**
	 * @return Returns the interviewStatusDescription.
	 */
	public String getInterviewStatusDescription() {
		return interviewStatusDescription;
	}
	/**
	 * @param interviewStatusDescription The interviewStatusDescription to set.
	 */
	public void setInterviewStatusDescription(String interviewStatusDescription) {
		this.interviewStatusDescription = interviewStatusDescription;
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
	public String getCalEventId() {
		return calEventId;
	}
	public void setCalEventId(String calEventId) {
		this.calEventId = calEventId;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getProgressReport() {
		return progressReport;
	}

	public void setProgressReport(String progressReport) {
		this.progressReport = progressReport;
	}
}
