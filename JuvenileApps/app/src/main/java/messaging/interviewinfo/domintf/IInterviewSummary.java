package messaging.interviewinfo.domintf;

import java.util.Date;

/**
 * 
 *
 */
public interface IInterviewSummary
{

	/**
	 * @return
	 */
	public String getCasefileId();

	/**
	 * @return
	 */
	public void setCasefileId( String id );

	/**
	 * @return
	 */
	public Date getInterviewDate();

	/**
	 * @return
	 */
	public String getInterviewId();

	/**
	 * @return
	 */
	public String getInterviewTypeId();

	/**
	 * @return
	 */
	public String getLocationDescription();

	/**
	 * @return
	 */
	public String getInterviewStatusCd();


	/**
	 * @param date
	 */
	public void setInterviewDate(Date date);

	/**
	 * @param string
	 */
	public void setInterviewId(String string);

	/**
	 * @param string
	 */
	public void setInterviewTypeId(String string);

	/**
	 * @param string
	 */
	public void setLocationDescription(String string);

	/**
	 * @param string
	 */
	public void setInterviewStatusCd(String string);

	public void setInterviewStatusDescription(String string);
	
	public String getInterviewStatusDescription();
	
	public String getSummaryNotes();
	public void setSummaryNotes(String summaryNotes);
	public String getCalEventId();
	public void setCalEventId( String id );
}
