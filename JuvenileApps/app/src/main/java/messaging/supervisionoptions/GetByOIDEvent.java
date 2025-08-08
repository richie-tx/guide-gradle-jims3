/*
 * Created on Jun 22, 2006
 *
 */
package messaging.supervisionoptions;

/**
 * @author dgibler
 *
 */
public class GetByOIDEvent extends GetByCourtEvent
{
	private String theOid;
	/**
	 * @return
	 */
	public String getTheOid()
	{
		return theOid;
	}

	/**
	 * @param string
	 */
	public void setTheOid(String string)
	{
		theOid = string;
	}

}
