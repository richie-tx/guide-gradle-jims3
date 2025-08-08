//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileTraitsByTypeEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileTraitsByParentTypeOIDEvent extends RequestEvent
{
	private String traitParent;	
	private String juvenileNum;
	//private String facilityAdmitOID;
	

	/**
	 * @roseuid 42A733740102
	 */
	public GetJuvenileTraitsByParentTypeOIDEvent()
	{

	}
	
	public String getTraitParent()
	{
	    return traitParent;
	}


	public void setTraitParent(String traitParent)
	{
	    this.traitParent = traitParent;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}
	
	/*public String getFacilityAdmitOID()
	{
	    return facilityAdmitOID;
	}

	public void setFacilityAdmitOID(String facilityAdmitOID)
	{
	    this.facilityAdmitOID = facilityAdmitOID;
	}*/
	
}
