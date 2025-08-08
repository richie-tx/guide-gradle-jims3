package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileFacilityHistoricalReceiptsEvent extends RequestEvent
{	
	public String juvenileNum;
	public String facilityCode;

	/**
	 * @roseuid 42A9A16D0190
	 */
	public GetJuvenileFacilityHistoricalReceiptsEvent()
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

	/**
	 * 
	 * @return
	 */
	public String getFacilityCode() {
		return facilityCode;
	}

	/**
	 * 
	 * @param FacilityCode
	 */
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}
	
	
}
