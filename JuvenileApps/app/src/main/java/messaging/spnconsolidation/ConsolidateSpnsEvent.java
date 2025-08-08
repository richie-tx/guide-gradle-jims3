//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\spnconsolidation\\ConsolidateSpnsEvent.java

package messaging.spnconsolidation;

import mojo.km.messaging.PersistentEvent;

public class ConsolidateSpnsEvent extends PersistentEvent 
{
   
	public String aliasSpn;
	public String baseSpn;
	
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
    * @roseuid 452BA16B00DD
    */
   public ConsolidateSpnsEvent() 
   {
    
   }
}
