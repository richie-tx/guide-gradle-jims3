//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\spnconsolidation\\UpdateSupervisionPeriodRedirectEvent.java

package messaging.spnconsolidation;

import mojo.km.messaging.RequestEvent;

public class UpdateSupervisionPeriodRedirectEvent extends RequestEvent 
{
	
	public String spvnPeriodReDirectId ;
	public String spvnPeriodId;
	public String target;
	
   
	/**
	 * @return Returns the spvnPeriodId.
	 */
	public String getSpvnPeriodId() {
		return spvnPeriodId;
	}
	/**
	 * @param spvnPeriodId The spvnPeriodId to set.
	 */
	public void setSpvnPeriodId(String spvnPeriodId) {
		this.spvnPeriodId = spvnPeriodId;
	}
	/**
	 * @return Returns the spvnPeriodReDirectId.
	 */
	public String getSpvnPeriodReDirectId() {
		return spvnPeriodReDirectId;
	}
	/**
	 * @param spvnPeriodReDirectId The spvnPeriodReDirectId to set.
	 */
	public void setSpvnPeriodReDirectId(String spvnPeriodReDirectId) {
		this.spvnPeriodReDirectId = spvnPeriodReDirectId;
	}
	/**
	 * @return Returns the target.
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * @param target The target to set.
	 */
	public void setTarget(String target) {
		this.target = target;
	}
   /**
    * @roseuid 452BA1C80236
    */
   public UpdateSupervisionPeriodRedirectEvent() 
   {
    
   }
}
