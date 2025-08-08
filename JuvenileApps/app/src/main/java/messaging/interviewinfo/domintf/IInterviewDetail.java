package messaging.interviewinfo.domintf;

import java.util.List;

import messaging.contact.domintf.IAddress;

/**
 *
 */
public interface IInterviewDetail extends IInterviewSummary
{
	/**
	 * @return
	 */
	public String getLocationId();

	/**
	 * @return
	 */
	public void setLocationId( String id );
	
	/**
	 * @return
	 */
	public String getJuvLocUnitId();

	/**
	 * @return
	 */
	public void setJuvLocUnitId( String id );

	/**
	 * @return
	 */
	public List getInventoryRecordsIds();

	/**
	 * @return
	 */
	public String getOtherInventoryRecords();

	/**
	 * @return
	 */
	public void setOtherInventoryRecords( String string );

	/**
	 * @return
	 */
	public String getSummaryNotes();

	/**
	 * @return
	 */
	public void setSummaryNotes( String notes );

	/**
	 * @return
	 */
	public IAddress getAddress();

	/**
	 * @param string
	 */
	public void setAddress( IAddress anAddress );

	/**
	 * List of messaging.interviewinfo.domintf.InterviewPerson
	 */
	public List getInterviewPersons();
	
	public void setInterviewStatusCd(String string);
	
	public String getInterviewStatusCd();
	
	public void setInterviewStatusDescription(String string);
	
	public String getInterviewStatusDescription();
	
	public String getCalEventId();
	
	public void setCalEventId(String string);
}
