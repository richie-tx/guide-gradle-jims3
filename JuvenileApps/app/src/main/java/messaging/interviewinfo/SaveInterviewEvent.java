package messaging.interviewinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IAddress;
import messaging.contact.to.Address;
import messaging.interviewinfo.domintf.IInterviewDetail;
//import messaging.interviewinfo.domintf.InterviewDetail;
import mojo.km.messaging.RequestEvent;

public class SaveInterviewEvent extends RequestEvent implements IInterviewDetail
{
	// Summary
	private String interviewId;
	private String interviewStatusCd;
	private Date interviewDate;		// Time only
	
	// Detail
	private String juvLocUnitId;
	private IAddress address = new Address();
	
	private List recordsInventoryIds = new ArrayList();	// ids as Strings 
	private String otherInventoryRecords;
	private String summaryNotes;
	private String userComments;
	private String progressReport;	
	private String calEventId;
	
	//juvenileNum is needed to mark attendance = ATTENDED
	private String juvenileNum;
	

	
   /**
    * @roseuid 448ECBC60139
    */
   public SaveInterviewEvent() 
   {
    
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
   public String getInterviewId()
   {
	   return interviewId;
   }

   /**
	* @return
	*/
   public String getLocationId()
   {
	   return "";
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
   public void setInterviewId(String string)
   {
	   interviewId = string;
   }

   /**
	* @param string
	*/
   public void setLocationId(String string)
   {
	   //
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

   //
   // -- The following not required for save operation --
   //
   
	/**
	 * @return
	 */
	public String getLocationDescription()
	{
		return null;
	}
   
	/**
	 * @param string
	 */
	public void setLocationDescription(String string)
	{
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
	 * @return
	 */
	public String getCasefileId()
	{
		return null;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
	}
   
	/**
	 * 
	 */
	public List getInterviewPersons()
	{
		return null;
	}

	/**
	 * @return
	 */
	public String getInterviewTypeId()
	{
		return null;
	}

	/**
	 * @param string
	 */
	public void setInterviewTypeId(String anInterviewTypeId)
	{
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
	public String getCalEventId() {
		return calEventId;
	}
	public void setCalEventId(String calEventId) {
		this.calEventId = calEventId;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
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
