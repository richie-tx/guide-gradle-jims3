//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetComplianceConditionsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

public class GetComplianceConditionsEvent extends RequestEvent 
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
		String id = "";
		if(defendantId.length() < 8){
			for(int i=defendantId.length(); i < 8; i++){
				id += "0";
			}
			this.defendantId = id + defendantId;
		}else{
			this.defendantId = defendantId;
		}
	}
   /**
    * @roseuid 473B899601FC
    */
   public GetComplianceConditionsEvent() 
   {
    
   }
}
