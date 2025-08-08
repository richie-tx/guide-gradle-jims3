package pd.juvenilecase.interviewinfo;

import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
* @roseuid 448EC361018A
*/
public class InterviewInventoryRecord extends PersistentObject 
{
	private static final String RECORD_CODETABLE = "INTERVIEW_RECORD";
	
	private String interviewId;
	
	/**
	* Properties for recordsInventory
	*/
	private Code recordsInventory = null;
	private String recordsInventoryId;
	
	/**
	* @roseuid 448EC361018A
	*/
	public InterviewInventoryRecord() 
	{
	}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.interviewinfo.Interview
	*/
	public void setInterviewId(String interviewId) 
	{
		if (this.interviewId == null || !this.interviewId.equals(interviewId)) 
		{
			markModified();
		}
		this.interviewId = interviewId;
	}
	
	/**
	* Get the reference value to class :: pd.juvenilecase.interviewinfo.Interview
	*/
	public String getInterviewId() 
	{
		fetch();
		return interviewId;
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setRecordsInventoryId(String recordsInventoryId) 
	{
		if (this.recordsInventoryId == null || !this.recordsInventoryId.equals(recordsInventoryId)) 
		{
			markModified();
		}
		recordsInventory = null;
		this.recordsInventoryId = recordsInventoryId;
	}
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getRecordsInventoryId() 
	{
		fetch();
		return recordsInventoryId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initRecordsInventory() 
	{
		if (recordsInventory == null) 
		{
			recordsInventory = (Code) new mojo.km.persistence.Reference(recordsInventoryId, Code.class, RECORD_CODETABLE).getObject();
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getRecordsInventory()
	{
		initRecordsInventory();
		return recordsInventory;
	}
	
	/**
	* set the type reference for class member recordsInventory
	*/
	public void setRecordsInventory(Code recordsInventory)
	{
		if (this.recordsInventory == null || !this.recordsInventory.equals(recordsInventory)) 
		{
			markModified();
		}
		if (recordsInventory.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(recordsInventory);
		}
		setRecordsInventoryId("" + recordsInventory.getOID());
		this.recordsInventory = (Code) new mojo.km.persistence.Reference(recordsInventory).getObject();
	}
}
