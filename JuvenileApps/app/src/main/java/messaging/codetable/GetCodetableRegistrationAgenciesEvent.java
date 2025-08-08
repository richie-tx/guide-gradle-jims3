//Source file: C:\\views\\commonfunctionality_07\\app\\src\\messaging\\codetable\\GetCodetableRegistrationAgenciesEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetCodetableRegistrationAgenciesEvent extends RequestEvent {
	public String codeTableRegId;

	/**
	 * @roseuid 485BC2EE0083
	 */
	public GetCodetableRegistrationAgenciesEvent() {

	}

	/**
	 * Access method for the codeTableRegId property.
	 * 
	 * @return the current value of the codeTableRegId property
	 */
	public String getCodeTableRegId() {
		return codeTableRegId;
	}

	/**
	 * Sets the value of the codeTableRegId property.
	 * 
	 * @param aCodeTableRegId
	 *            the new value of the codeTableRegId property
	 */
	public void setCodeTableRegId(String aCodeTableRegId) {
		codeTableRegId = aCodeTableRegId;
	}

}
