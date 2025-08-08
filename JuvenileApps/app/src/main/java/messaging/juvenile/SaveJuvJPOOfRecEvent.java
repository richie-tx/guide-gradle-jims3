/*
 * Created on Nov 16, 2017
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveJuvJPOOfRecEvent extends RequestEvent {

	private String juvenileNum;	
	private String jpoId;	
	
	
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	public String getJpoId() {
		return jpoId;
	}
	public void setJpoId(String jpoId) {
		this.jpoId = jpoId;
	}
}// END CLASS
