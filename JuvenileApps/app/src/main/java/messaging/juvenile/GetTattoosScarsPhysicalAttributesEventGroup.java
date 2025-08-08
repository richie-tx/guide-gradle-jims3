//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetTattoosScarsPhysicalAttributesEventGroup.java
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetTattoosScarsPhysicalAttributesEventGroup extends RequestEvent {

	private String juvenileNum;

	public GetTattoosScarsPhysicalAttributesEventGroup() {

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