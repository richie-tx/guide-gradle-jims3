package pd.juvenile;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author Anpillai This entity is to find the master status of a juvenile for view JCJUVENILEMASTERSTATUS 
 */
public class JuvenileMasterStatus extends PersistentObject
{
	private String statusId;

	/**
	 * @roseuid 42A882800157
	 */
	public JuvenileMasterStatus()
	{
	}

	/**
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this view JuvenileMasterStatus.
	 * @param juvNum
	 * @return JuvenileMasterStatus.
	 */
	static public JuvenileMasterStatus find(String juvNum)
	{
		IHome home = new Home();
		JuvenileMasterStatus juvenile = (JuvenileMasterStatus) home.find(juvNum, JuvenileMasterStatus.class);
		return juvenile;
	}

	/**
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this view JuvenileMasterStatus.
	 * @return Iterator
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(event, JuvenileMasterStatus.class);
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		return this.getOID();
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.setOID(juvenileNum);
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @param statusId
	 *            The statusId to set.
	 */
	public void setStatusId(String statusId)
	{
		this.statusId = statusId;
	}
}
