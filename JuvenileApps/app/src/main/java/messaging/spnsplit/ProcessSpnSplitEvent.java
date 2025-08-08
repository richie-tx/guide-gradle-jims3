//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\spnsplit\\ProcessSpnSplitEvent.java

package messaging.spnsplit;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import mojo.km.messaging.RequestEvent;

public class ProcessSpnSplitEvent extends RequestEvent 
{
       private String validSpn;
       private String erroneousSpn;
       private List cases = new ArrayList();
       private String agencyId;
       private String errPeriodId;
       private String spnSplitExceptionId;

       public String getSpnSplitExceptionId() {
		return spnSplitExceptionId;
	}
	public void setSpnSplitExceptionId(String spnSplitExceptionId) {
		this.spnSplitExceptionId = spnSplitExceptionId;
	}
	private Hashtable<String, String> erroneousOrderSupervisionPeriodMap;
       private Hashtable<String, String> validOrderSupervisionPeriodMap;
       
		/**
		 * @return Returns the errPeriodId.
		 */
		public String getErrPeriodId() {
		    return errPeriodId;
		}
		/**
		 * @param errPeriodId The errPeriodId to set.
		 */
		public void setErrPeriodId(String errPeriodId) {
		    this.errPeriodId = errPeriodId;
		}

		/**
		 * @return Returns the agencyId.
		 */
		public String getAgencyId() {
		    return agencyId;
		}
		
		/**
		 * @param agencyId The agencyId to set.
		 */
		public void setAgencyId(String agencyId) {
		    this.agencyId = agencyId;
		}
		
		/**
		 * @return Returns the cases.
		 */
		public List getCases() {
		    return cases;
		}
		/**
		 * @param cases The cases to set.
		 */
		public void setCases(List cases) {
		    this.cases = cases;
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
       /**
        * @roseuid 4561E2B101D9
        */
       public ProcessSpnSplitEvent() 
       {
        
       }
	public Hashtable<String, String> getErroneousOrderSupervisionPeriodMap() {
		return erroneousOrderSupervisionPeriodMap;
	}
	public void setErroneousOrderSupervisionPeriodMap(
			Hashtable<String, String> erroneousOrderSupervisionPeriodMap) {
		this.erroneousOrderSupervisionPeriodMap = erroneousOrderSupervisionPeriodMap;
	}
	public Hashtable<String, String> getValidOrderSupervisionPeriodMap() {
		return validOrderSupervisionPeriodMap;
	}
	public void setValidOrderSupervisionPeriodMap(
			Hashtable<String, String> validOrderSupervisionPeriodMap) {
		this.validOrderSupervisionPeriodMap = validOrderSupervisionPeriodMap;
	}
}
