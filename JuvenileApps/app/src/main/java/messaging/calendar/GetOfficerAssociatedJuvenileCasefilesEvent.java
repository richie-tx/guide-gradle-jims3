/*
 * Created on Apr 20, 2007
 *
 */
package messaging.calendar;

import mojo.km.messaging.RequestEvent;

/**
 * @author p_alcocer
 */
public class GetOfficerAssociatedJuvenileCasefilesEvent extends RequestEvent
{

	private String juvenileFirstName;

	private String juvenileMiddleName;

	private String juvenileLastName;

	private String jpoUserId;
	
	private int index;

	public boolean hasJuvenileName()
	{
		return !((this.juvenileFirstName == null || this.juvenileFirstName.equals("") == true)
				|| (this.juvenileMiddleName == null || this.juvenileMiddleName.equals("") == true)
				|| (this.juvenileLastName == null || this.juvenileLastName.equals("") == true));
	}

	/**
	 * @return Returns the juvenileFirstName.
	 */
	public String getJuvenileFirstName()
	{
		return juvenileFirstName;
	}

	/**
	 * @param juvenileFirstName
	 *            The juvenileFirstName to set.
	 */
	public void setJuvenileFirstName(String juvenileFirstName)
	{
		this.juvenileFirstName = juvenileFirstName;
	}

	/**
	 * @return Returns the juvenileLastName.
	 */
	public String getJuvenileLastName()
	{
		return juvenileLastName;
	}

	/**
	 * @param juvenileLastName
	 *            The juvenileLastName to set.
	 */
	public void setJuvenileLastName(String juvenileLastName)
	{
		this.juvenileLastName = juvenileLastName;
	}

	/**
	 * @return Returns the juvenileMiddleName.
	 */
	public String getJuvenileMiddleName()
	{
		return juvenileMiddleName;
	}

	/**
	 * @param juvenileMiddleName
	 *            The juvenileMiddleName to set.
	 */
	public void setJuvenileMiddleName(String juvenileMiddleName)
	{
		this.juvenileMiddleName = juvenileMiddleName;
	}

	/**
	 * @return Returns the jpoUserId.
	 */
	public String getJpoUserId()
	{
		return jpoUserId;
	}

	/**
	 * @param jpoUserId
	 *            The jpoUserId to set.
	 */
	public void setJpoUserId(String jpoUserId)
	{
		this.jpoUserId = jpoUserId;
	}

	/**
	 * @return Returns the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 * 			The index to set.
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
}
