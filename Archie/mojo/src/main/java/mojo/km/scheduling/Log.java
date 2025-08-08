package mojo.km.scheduling;

import java.sql.Timestamp;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;

/**
* @author mchowdhury
*/
public class Log extends PersistentObject
{
	private static final int MAX_MSG_LENGTH = 3000;
	private String logId;
	private int jobId;
	private Timestamp logTime ;
	private String text ;
	private String type;
	
	/**
	* @roseuid 4107BF8402AF
	*/
	public Log()
	{
	}

	/**
	* @return pd.common.Log
	* @param logId
	* @roseuid 4107B06D01B5
	*/
	static public Log find(String logId)
	{
		return (Log) new Home().find(logId, Log.class);
	}
	/**
	* @return java.util.Iterator
	* @roseuid 41123AE00111
	*/
	static public Iterator findAll()
	{
		return new Home().findAll(Log.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		return new Home().findAll(attrName,attrValue,Log.class);
	}		
		
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, Log.class);
	}
	
	/**
	* @roseuid 4107B06D01BB
	*/
	public void create(){
		new Home().bind(this);
	}

	/**
	 * @return Returns the logId.
	 */
	public String getLogId() {
		fetch();
		return this.getOID().toString();
	}
	/**
	 * @param logId The logId to set.
	 */
	public void setLogId(String logId) {
		if (this.logId == null || !this.logId.equals(logId))
		{
			markModified();
		}
		this.setOID(logId);
		this.logId = logId;
	}
	/**
	 * @return Returns the logTime.
	 */
	public Timestamp getLogTime() {
		fetch();
		return logTime;
	}
	/**
	 * @param logTime The logTime to set.
	 */
	public void setLogTime(Timestamp logTime) {
		if (this.logTime == null || !this.logTime.equals(logTime))
		{
			markModified();
		}
		this.logTime = logTime;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		fetch();
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		if (this.text == null || !this.text.equals(text))
		{
			markModified();
		}
		if (text != null && text.length() > MAX_MSG_LENGTH){
			text.substring(0,MAX_MSG_LENGTH);
		}
		this.text = text;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		fetch();
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		this.type = type;
	}
	
	public static void createLog(int jobId, Timestamp logTime, String text, String type, String creatorUserId) throws RuntimeException, Exception{
		Log log = new Log();
		log.jobId = jobId;
		log.logTime = logTime;
		log.text = text;
		log.type = type;
		log.setCreateUserID(creatorUserId);
		log.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		new Home().bind(log);
	}	
	/**
	 * @return Returns the jobId.
	 */
	public int getJobId() {
		fetch();
		return jobId;
	}
	/**
	 * @param jobId The jobId to set.
	 */
	public void setJobId(int jobId) {
		if (this.jobId != jobId)
		{
			markModified();
		}
		this.jobId = jobId;
	}
}