//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCCommunityServicesEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class GetNCCommunityServicesEvent extends RequestEvent 
{
   private String caseId;
   private String cdi;
   
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		char[] chars = caseId.toCharArray();
	    int index = 0;
	    for (; index < chars.length; index++) {
	        if (chars[index] != '0') {
	            break;
	        }
	    }
	    this.caseId = (index == 0) ? caseId : caseId.substring(index);
	}
	/**
	 * @return Returns the cdi.
	 */
	public String getCdi() {
		return cdi;
	}
	/**
	 * @param cdi The cdi to set.
	 */
	public void setCdi(String cdi) {
		char[] chars = cdi.toCharArray();
	    int index = 0;
	    for (; index < chars.length; index++) {
	        if (chars[index] != '0') {
	            break;
	        }
	    }
	    this.cdi = (index == 0) ? cdi : cdi.substring(index);
	}
   /**
    * @roseuid 47D9BBEE0029
    */
   public GetNCCommunityServicesEvent() 
   {
    
   }
}
