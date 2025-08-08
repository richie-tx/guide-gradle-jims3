/*
 * Created on Oct 19, 2004
 */
package messaging.administerlocation.reply;


import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class JuvLocationUnitResponseEvent extends ResponseEvent implements Comparable
{
	private String locationUnitName;
	private String juvLocationUnitId;
	private String juvUnitCd;
	private String locationId;
	private String statusId;	
	private String phoneNumber;	
	
	
	/**
	 * @return
	 */
	public String getLocationUnitName()
	{
		return locationUnitName;
	}
	
	/**
	 * @param string
	 */
	public void setLocationUnitName(String string)
	{
		locationUnitName = string;
	}

	
	/**
	 * @return
	 */
	public String getLocationId()
	{
		return locationId;
	}

	/**
	 * @param string
	 */
	public void setLocationId(String string)
	{
		locationId = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		JuvLocationUnitResponseEvent l = (JuvLocationUnitResponseEvent)o;		
		return locationUnitName.compareToIgnoreCase(l.getLocationUnitName());	
	}
	
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	/**
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	/**
	 * @return Returns the juvUnitCd.
	 */
	public String getJuvUnitCd() {
		return juvUnitCd;
	}
	/**
	 * @param juvUnitCd The juvUnitCd to set.
	 */
	public void setJuvUnitCd(String juvUnitCd) {
		this.juvUnitCd = juvUnitCd;
	}
	/**
	 * @return Returns the juvLocationUnitId.
	 */
	public String getJuvLocationUnitId() {
		return juvLocationUnitId;
	}
	/**
	 * @param juvLocationUnitId The juvLocationUnitId to set.
	 */
	public void setJuvLocationUnitId(String juvLocationUnitId) {
		this.juvLocationUnitId = juvLocationUnitId;
	}
}
