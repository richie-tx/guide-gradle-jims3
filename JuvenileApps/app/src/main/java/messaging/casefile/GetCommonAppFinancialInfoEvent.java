package messaging.casefile;

import mojo.km.messaging.RequestEvent;


public class GetCommonAppFinancialInfoEvent extends RequestEvent 
{
	private String juvenileNum;
	
	/**
	* 
	*/
	public GetCommonAppFinancialInfoEvent() 
	{
    
	}
	

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
}
