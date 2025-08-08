// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\GetLocationsEvent.java

package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class GetJuvLocationUnitsByAgencyEvent extends RequestEvent {	
	private String agencyId;
	private String unitStatusId;
	private String locStatusCd;
	


	/**
	 * @return Returns the locStatusCd.
	 */
	public String getLocStatusCd() {
		return locStatusCd;
	}
	/**
	 * @param locStatusCd The locStatusCd to set.
	 */
	public void setLocStatusCd(String locStatusCd) {
		this.locStatusCd = locStatusCd;
	}
	/**
	 * 
	 */
	public GetJuvLocationUnitsByAgencyEvent() {
			agencyId="";
			unitStatusId="";
	}
	/**
	 * @param _service
	 *//*
	public GetJuvLocationUnitsByAgencyEvent(String _service) {
		super(_service);
		// TODO Auto-generated constructor stub
	}*/
	/**
	 * @return Returns the unitStatusId.
	 */
	public String getUnitStatusId() {
		return unitStatusId;
	}
	/**
	 * @param unitStatusId The unitStatusId to set.
	 */
	public void setUnitStatusId(String unitStatusId) {
		this.unitStatusId = unitStatusId;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}

	/**
	 * @param agencyId
	 *            The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	
}
