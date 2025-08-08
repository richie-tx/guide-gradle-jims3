//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJOTDataEvent.java

package messaging.referral;

import messaging.common.domintf.IIdentity;
import mojo.km.messaging.RequestEvent;

public class GetJOTDetailsEvent extends RequestEvent implements IIdentity
{
	private String daLogNum;

	/**
	 * @roseuid 416C279B02B5
	 */
	public GetJOTDetailsEvent()
	{

	}

	/**
	 * Access method for the daLogNum property.
	 * 
	 * @return   the current value of the daLogNum property
	 */
	public String getDaLogNum()
	{
		return daLogNum;
	}

	/**
	 * Sets the value of the daLogNum property.
	 * 
	 * @param aDaLogNum the new value of the daLogNum property
	 */
	public void setDaLogNum(String aDaLogNum)
	{
		daLogNum = aDaLogNum;
	}

	/* (non-Javadoc)
	 * @see messaging.IIdentity#getIdentity()
	 */
	public String getId()
	{
		// TODO Auto-generated method stub
		return this.getDaLogNum();
	}

	/* (non-Javadoc)
	 * @see messaging.IIdentity#setIdentity(java.lang.String)
	 */
	public void setId(String identity)
	{
		this.setDaLogNum(identity);
	}

}
