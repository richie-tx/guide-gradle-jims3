//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\UpdateNonCompliantEventEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetVRCommentsEvent extends RequestEvent 
{
	private String type;
    private String ncResponseId;
	
    /**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
}