//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetTattoosScarsPhysicalAttributesEvent.java
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetTattoosScarsPhysicalAttributesEvent extends RequestEvent {

	private String juvenileNum;

	public GetTattoosScarsPhysicalAttributesEvent() {

	}

	/**
	 * @return
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string) {
		juvenileNum = string;
	}

}
