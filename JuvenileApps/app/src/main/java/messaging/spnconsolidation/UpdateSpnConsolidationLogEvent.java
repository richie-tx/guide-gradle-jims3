//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\spnconsolidation\\UpdateSpnConsolidationLogEvent.java

package messaging.spnconsolidation;

import mojo.km.messaging.RequestEvent;

public class UpdateSpnConsolidationLogEvent extends RequestEvent 
{
	private String baseSpn;
	private String aliasSpn;
	private String status;
	private String logDetail;
	private String invocSource;
	
	/**
	 * @return Returns the aliasSpn.
	 */
	public String getAliasSpn() {
		return aliasSpn;
	}
	/**
	 * @param aliasSpn The aliasSpn to set.
	 */
	public void setAliasSpn(String aliasSpn) {
		this.aliasSpn = aliasSpn;
	}
	/**
	 * @return Returns the baseSpn.
	 */
	public String getBaseSpn() {
		return baseSpn;
	}
	/**
	 * @param baseSpn The baseSpn to set.
	 */
	public void setBaseSpn(String baseSpn) {
		this.baseSpn = baseSpn;
	}
	/**
	 * @return Returns the invocSource.
	 */
	public String getInvocSource() {
		return invocSource;
	}
	/**
	 * @param invocSource The invocSource to set.
	 */
	public void setInvocSource(String invocSource) {
		this.invocSource = invocSource;
	}
	/**
	 * @return Returns the logDetail.
	 */
	public String getLogDetail() {
		return logDetail;
	}
	/**
	 * @param logDetail The logDetail to set.
	 */
	public void setLogDetail(String logDetail) {
		this.logDetail = logDetail;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
    * @roseuid 452BA1C60311
    */
   public UpdateSpnConsolidationLogEvent() 
   {
    
   }
}
