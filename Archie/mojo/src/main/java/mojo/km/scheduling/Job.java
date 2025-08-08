package mojo.km.scheduling;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;

/**
* @author mchowdhury
*/
public class Job extends PersistentObject
{

	private String jobId;
	private int processId;
	private Timestamp startTime ;
	private Timestamp endTime ;
	private int skippedCount;
	private int processedCount;
	private int errorCount;
	
	/**
	* Properties for logs
	* @referencedType mojo.km.scheduling.Log
	* @detailerDoNotGenerate false
	*/
	private Collection logs = null;

	/**
	* @roseuid 4107BF8402AF
	*/
	public Job()
	{
	}

	/**
	* Clears all mojo.km.scheduling.Log from class relationship collection.
	* @roseuid 4107DFB603E7
	*/
	public void clearLogs()
	{
		initLogs();
		logs.clear();
	}
	
	/**
	* returns a collection of mojo.km.scheduling.Log
	* @return java.util.Collection
	* @roseuid 4107DFB603A1
	*/
	public Collection getLogs()
	{
		fetch();
		initLogs();
		return logs;
	}

	
	/**
	* Initialize class relationship implementation for mojo.km.scheduling.Log
	* @roseuid 4107DFB60397
	*/
	private void initLogs()
	{
		if (logs == null)
		{
			if (this.getOID() == null)
			{
				new Home().bind(this);
			}
			try
			{
				logs =
					new mojo.km.persistence.ArrayList(Log.class, "jobId", "" + getOID());
			}
			catch (Throwable t)
			{
				logs = new java.util.ArrayList();
			}
		}
	}

	/**
	* insert a mojo.km.scheduling.Log into class relationship collection.
	* @param anObject
	* @roseuid 4107DFB603B5
	*/
	public void insertLogs(Log anObject)
	{
		initLogs();
		logs.add(anObject);
	}

	/**
	* Removes a mojo.km.scheduling.Log from class relationship collection.
	* @param anObject
	* @roseuid 4107DFB603C9
	*/
	public void removeLogs(Log anObject)
	{
		initLogs();
		logs.remove(anObject);
	}

	/**
	* @return mojo.km.scheduling.Job
	* @param jobId
	* @roseuid 4107B06D01B5
	*/
	static public Job find(String jobId)
	{
		return (Job) new Home().find(jobId, Job.class);
	}
	/**
	* @return java.util.Iterator
	* @roseuid 41123AE00111
	*/
	static public Iterator findAll()
	{
		return new Home().findAll(Job.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		return new Home().findAll(attrName,attrValue,Job.class);
	}
		
		
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, Job.class);
	}
	
	/**
	 * @return Returns the endTime.
	 */
	public Timestamp getEndTime() {
		fetch();
		return endTime;
	}
	/**
	 * @param endTime The endTime to set.
	 */
	public void setEndTime(Timestamp endTime) {
		if (this.endTime == null || !this.endTime.equals(endTime))
		{
			markModified();
		}
		this.endTime = endTime;
	}
	/**
	 * @return Returns the errorCount.
	 */
	public int getErrorCount() {
		fetch();
		return errorCount;
	}
	/**
	 * @param errorCount The errorCount to set.
	 */
	public void setErrorCount(int errorCount) {
		if (this.errorCount != errorCount)
		{
			markModified();
		}
		this.errorCount = errorCount;
	}
	/**
	 * @return Returns the LogId.
	 */
	public String getJobId() {
		fetch();
		return this.getOID().toString();
	}
	/**
	 * @param jobId The jobId to set.
	 */
	public void setJobId(String jobId) {
		if (this.jobId == null || !this.jobId.equals(jobId))
		{
			markModified();
		}
		this.setOID(jobId);
		this.jobId = jobId;
	}
	/**
	 * @return Returns the processedCount.
	 */
	public int getProcessedCount() {
		fetch();
		return processedCount;
	}
	/**
	 * @param processedCount The processedCount to set.
	 */
	public void setProcessedCount(int processedCount) {
		if (this.processedCount != processedCount)
		{
			markModified();
		}
		this.processedCount = processedCount;
	}
	/**
	 * @return Returns the skippedCount.
	 */
	public int getSkippedCount() {
		fetch();
		return skippedCount;
	}
	/**
	 * @param skippedCount The skippedCount to set.
	 */
	public void setSkippedCount(int skippedCount) {
		if (this.skippedCount != skippedCount)
		{
			markModified();
		}
		this.skippedCount = skippedCount;
	}
	/**
	 * @return Returns the startTime.
	 */
	public Timestamp getStartTime() {
		fetch();
		return startTime;
	}
	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(Timestamp startTime) {
		if (this.startTime == null || !this.startTime.equals(startTime))
		{
			markModified();
		}
		this.startTime = startTime;
	}
	/**
	 * @return Returns the processId.
	 */
	public int getProcessId() {
		fetch();
		return processId;
	}
	/**
	 * @param processId The processId to set.
	 */
	public void setProcessId(int processId) {
		if (this.processId != processId)
		{
			markModified();
		}
		this.processId = processId;
	}
	
	public void setJob(int skippedCount, int processedCount, int errorCount, Timestamp endTime){
		this.setEndTime(endTime);
		this.setErrorCount(errorCount);
		this.setSkippedCount(skippedCount);
		this.setProcessedCount(processedCount);
	}
	
	/**
	* @roseuid 4107B06D01BB
	*/
	public static Job createJob(int skippedCount, int processedCount, int errorCount, int processId, Timestamp startTime, Timestamp endTime, String creatorUserId, String jims2CreatorUserId) throws RuntimeException, Exception{
		Job job = new Job();
		job.setJob(skippedCount,processedCount,errorCount, endTime);
		job.setStartTime(startTime);
		job.setProcessId(processId);
		job.setCreateUserID(creatorUserId);
		job.setCreateJIMS2UserID(jims2CreatorUserId);
		job.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		return (Job) new Home().bind(job);
	}
}