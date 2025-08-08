package messaging.productionsupport;

import mojo.km.messaging.ResponseEvent;

public class DeleteProductionSupportJuvenileSchoolHistoryEvent extends ResponseEvent 
{
   
   /**
    * @roseuid 42BC4E0203B4
    */
   public DeleteProductionSupportJuvenileSchoolHistoryEvent() 
   {
    
	   
   }
   
	private String schoolHistoryId;
	
	/**
	 * 
	 * @return
	 */
	public String getSchoolHistoryId() {
		return schoolHistoryId;
	}
	
	/**
	 * 
	 * @param schoolHistoryId
	 */
	public void setSchoolHistoryId(String schoolHistoryId) {
		this.schoolHistoryId = schoolHistoryId;
	}
	
}
