//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\spnsplit\\GetCaseSupervisionPeriodsEvent.java

package messaging.spnsplit;

import mojo.km.messaging.RequestEvent;

public class GetCaseSupervisionPeriodsEvent extends RequestEvent 
{
   private String validSpn = "";
   private String erroneousSpn = "";
   
   /**
    * @roseuid 4561E2B0010E
    */
   public GetCaseSupervisionPeriodsEvent() 
   {
    
   }
    /**
     * @return Returns the erroneousSpn.
     */
    public String getErroneousSpn() {
    	return erroneousSpn;
    }
    /**
     * @param erroneousSpn The erroneousSpn to set.
     */
    public void setErroneousSpn(String erroneousSpn) {
    	this.erroneousSpn = erroneousSpn;
    }
    /**
     * @return Returns the validSpn.
     */
    public String getValidSpn() {
    	return validSpn;
    }
    /**
     * @param validSpn The validSpn to set.
     */
    public void setValidSpn(String validSpn) {
    	this.validSpn = validSpn;
    }
}
