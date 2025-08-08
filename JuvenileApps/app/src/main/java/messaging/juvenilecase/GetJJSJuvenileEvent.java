package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJJSJuvenileEvent extends RequestEvent{

	private String juvenileNum;
	private String detentionStatusId;
	private String detentionFacilityId;

	public String getDetentionFacilityId()
	{
	    return detentionFacilityId;
	}

	public void setDetentionFacilityId(String detentionFacilityId)
	{
	    this.detentionFacilityId = detentionFacilityId;
	}

	public String getJuvenileNum() {
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	public String getDetentionStatusId()
	{
	    return detentionStatusId;
	}

	public void setDetentionStatusId(String detentionStatusId)
	{
	    this.detentionStatusId = detentionStatusId;
	}


	
	
}
