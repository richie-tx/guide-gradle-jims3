package messaging.interviewinfo.to;


/**
 *
 */
public class ExcludedTO
{
	private boolean excluded = false;	
	
	/**
	 * @return Returns the excluded.
	 */
	public boolean isExcluded() {
		return excluded;
	}
	/**
	 * @param excluded The excluded to set.
	 */
	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}
	
	/**
	 * @return Returns the excluded.
	 */
	public boolean isIncluded() {
		return ! excluded;
	}
	
	public void setIncluded(boolean included) {
		this.excluded = !included;
	}

}
