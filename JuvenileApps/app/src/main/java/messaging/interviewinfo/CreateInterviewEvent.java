package messaging.interviewinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IAddress;
import messaging.contact.to.Address;
import messaging.interviewinfo.domintf.IInterviewDetail;
import mojo.km.messaging.RequestEvent;

/**
 * 
 */
public class CreateInterviewEvent extends RequestEvent implements IInterviewDetail 
{
	private String casefileId;
	private Date interviewDate;
	private List interviewPersons = new ArrayList();
	private List recordsInventoryIds = new ArrayList();	// ids as Strings 
	private String otherInventoryRecords;
	private String interviewTypeId;
	private String locationId;
	private String juvLocUnitId;
	private IAddress address = new Address();
	private boolean hasTaskList;
	private String summaryNotes;
	private String userComments;
	private String progressReport;
	private String interviewStatusCd;
	private String interviewStatusDescription;
	private String calEventId;
	
   /**
    * @roseuid 448ECBC002C0
    */
   public CreateInterviewEvent() 
   {
   }
   
   /**
    * @param caseFileId
    * @roseuid 448D7EED0393
    */
   public void setCaseFileId(String aCasefileId) 
   {
    	casefileId = aCasefileId;
   }
   
   /**
    * @roseuid 448D7EED0395
    */
   public String getCaseFileId() 
   {
    	return casefileId;
   }
   
   /**
	* @return
	*/
   public Date getInterviewDate()
   {
	   return interviewDate;
   }

   /**
	* @return
	*/
   public String getInterviewTypeId()
   {
	   return interviewTypeId;
   }

   /**
	* @return
	*/
   public String getLocationId()
   {
	   return locationId;
   }

   /**
	* @param date
	*/
   public void setInterviewDate(Date date)
   {
	   interviewDate = date;
   }

   /**
	* @param string
	*/
   public void setInterviewTypeId(String string)
   {
	   interviewTypeId = string;
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
	public String getCasefileId()
	{
		return casefileId;
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
	public List getInterviewPersons()
	{
		return interviewPersons;
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
	public String getSummaryNotes()
	{
		return summaryNotes;
	}
	
	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}
	
	/**
	 * @param string
	 */
	public void setOtherInventoryRecords(String string)
	{
		otherInventoryRecords = string;
	}
	
	/**
	 * @param string
	 */
	public void setSummaryNotes(String string)
	{
		summaryNotes = string;
	}
	
	/**
	 * 
	 */
	public String getInterviewId()
	{
		return null;	// not allowed on create.
	}
	
	/**
	 * @param string
	 */
	public void setInterviewId(String string)
	{
		// not allowed on create.
	}
	
	/**
	 * 
	 */
	public String getLocationDescription()
	{
		return null;	// not allowed on create.
	}
	
	/**
	 * 
	 */
	public void setLocationDescription( String none )
	{
		// not allowed on create.
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

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#getInterviewStatusCd()
	 */
	public String getInterviewStatusCd() {	
		return interviewStatusCd;
	}

	/* (non-Javadoc)
	 * @see messaging.interviewinfo.domintf.IInterviewSummary#setInterviewStatusCd(java.lang.String)
	 */
	public void setInterviewStatusCd(String interviewStatusCd) {
		this.interviewStatusCd = interviewStatusCd;
		
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
