//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileTraitsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileTraitsEvent extends RequestEvent
{
	public String juvenileNum;
	//Added for U.S. #42660
	public String facilityAdmitOID;
	public boolean isUIFacility;

	/**
	 * @roseuid 42A7336D0279
	 */
	public GetJuvenileTraitsEvent()
	{

	}

	
	
	/**
	 * @return the isUIFacility
	 */
	public boolean getIsUIFacility() {
		return isUIFacility;
	}

	
	/**
	 * @return the isUIFacility
	 */
	public boolean isUIFacility() {
		return isUIFacility;
	}



	/**
	 * @param isUIFacility the isUIFacility to set
	 */
	public void setUIFacility(boolean isUIFacility) {
		this.isUIFacility = isUIFacility;
	}



	/**
	 * @param juvenileNum
	 * @roseuid 42A732AF03E1
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 42A732AF03E3
	 */
	public String getJuvenileNum()
	{
		return this.juvenileNum;
	}

	/**
	 * @return the facilityAdmitOID
	 */
	public String getFacilityAdmitOID() {
		return facilityAdmitOID;
	}

	/**
	 * @param facilityAdmitOID the facilityAdmitOID to set
	 */
	public void setFacilityAdmitOID(String facilityAdmitOID) {
		this.facilityAdmitOID = facilityAdmitOID;
	}
}
