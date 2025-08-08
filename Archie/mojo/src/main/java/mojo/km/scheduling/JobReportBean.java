package mojo.km.scheduling;


/**
* @author mchowdhury
*/
public class JobReportBean
{
	private int errorCount;
	private int skippedCount;
	private int processedCount;
	
	/**
	* @roseuid 4107BF8402AF
	*/
	public JobReportBean()
	{
	}
	
	/**
	* @roseuid 4107BF8402AF
	*/
	public JobReportBean(int errorCount, int skippedCount, int processedCount)
	{
		this.errorCount = errorCount;
		this.skippedCount = skippedCount;
		this.processedCount = processedCount;
	}
	
	
	/**
	 * @return Returns the errorCount.
	 */
	public int getErrorCount() {
		return errorCount;
	}
	/**
	 * @param errorCount The errorCount to set.
	 */
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	/**
	 * @return Returns the processedCount.
	 */
	public int getProcessedCount() {
		return processedCount;
	}
	/**
	 * @param processedCount The processedCount to set.
	 */
	public void setProcessedCount(int processedCount) {
		this.processedCount = processedCount;
	}
	/**
	 * @return Returns the skippedCount.
	 */
	public int getSkippedCount() {
		return skippedCount;
	}
	/**
	 * @param skippedCount The skippedCount to set.
	 */
	public void setSkippedCount(int skippedCount) {
		this.skippedCount = skippedCount;
	}
}