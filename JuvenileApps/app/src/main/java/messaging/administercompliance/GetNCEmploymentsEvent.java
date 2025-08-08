//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCEmploymentsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class GetNCEmploymentsEvent extends RequestEvent 
{
   private String defandantId;
	
   /**
	 * @return Returns the defandantId.
	 */
	public String getDefandantId() {
		return defandantId;
	}
	/**
	 * @param defandantId The defandantId to set.
	 */
	public void setDefandantId(String defandantId) {
		this.defandantId = defandantId;
	}
}
