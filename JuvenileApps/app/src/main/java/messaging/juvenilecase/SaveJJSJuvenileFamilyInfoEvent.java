/*
 * Created on May 21 2024
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

/**
 * @author ryoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveJJSJuvenileFamilyInfoEvent extends RequestEvent {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	
    
    private String juvenileNum;
    private String youthLivesWithId;
    
    
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
	
	public String getYouthLivesWithId()
	{
	    return youthLivesWithId;
	}
	
	public void setYouthLivesWithId(String youthLivesWithId)
	{
	    this.youthLivesWithId = youthLivesWithId;
	}
	
}
