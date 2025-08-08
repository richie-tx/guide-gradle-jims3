//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetComplianceConditionsEvent.java

package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeComplianceIndicatorEvent extends RequestEvent 
{
   private String defendantId;
	
   /**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {

		if (defendantId != null  && !defendantId.equals("")){
			StringBuffer sb = new StringBuffer(defendantId);
			while (sb.length() < 8){
				sb.insert(0, 0);
			}
			this.defendantId = sb.toString();
		} else {
			defendantId = "";
		}
	}
   /**
    * @roseuid 473B899601FC
    */
   public GetSuperviseeComplianceIndicatorEvent() 
   {
    
   }
}
