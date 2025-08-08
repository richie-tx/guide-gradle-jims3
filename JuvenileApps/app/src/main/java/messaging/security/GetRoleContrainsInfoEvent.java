//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetRoleContrainsInfoEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetRoleContrainsInfoEvent extends RequestEvent 
{
	private String constrainsId;
	private String constrainsType;
	
	


	/**
	 * @return
	 */
	public String getConstrainsId() {
		return constrainsId;
	}

	/**
	 * @return
	 */
	public String getConstrainsType() {
		return constrainsType;
	}

	/**
	 * @param string
	 */
	public void setConstrainsId(String string) {
		constrainsId = string;
	}

	/**
	 * @param string
	 */
	public void setConstrainsType(String string) {
		constrainsType = string;
	}
}
