package messaging.facility;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileFacilityHeaderEvent extends RequestEvent{

	public GetJuvenileFacilityHeaderEvent(){
		
	}
	
	private String juvenileNum;
	private String courtType;	

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public String getCourtType()
	{
	    return courtType;
	}

	public void setCourtType(String courtType)
	{
	    this.courtType = courtType;
	}
}