/*
 * Created on Feb 10 , 2021
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * @author ryoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveJuvenilePurgeEvent extends RequestEvent {

	private String juvenileNum;	
	private String recType;	
	
	
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
	public String getRecType()
	{
	    return recType;
	}
	public void setRecType(String recType)
	{
	    this.recType = recType;
	}

	
}// END CLASS
