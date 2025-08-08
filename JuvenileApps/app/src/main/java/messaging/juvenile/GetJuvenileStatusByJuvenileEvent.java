package messaging.juvenile;

/**
 * GetJuvenileStatusEvent.
 *  
 * @author  mchowdhury
 */
public class GetJuvenileStatusByJuvenileEvent extends mojo.km.messaging.RequestEvent
{
	private String juvenileNum;

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42A74E1900AB
	 */
	public GetJuvenileStatusByJuvenileEvent()
	{
	} 

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() 
	{
		return juvenileNum;
	}
	
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) 
	{
		this.juvenileNum = juvenileNum;
	}
}
