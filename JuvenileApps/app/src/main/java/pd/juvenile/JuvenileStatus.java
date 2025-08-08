package pd.juvenile;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author mchowdhury This entity reprents a Juvenile master Core record (only core data from M204)
 */
public class JuvenileStatus extends PersistentObject
{
	private String statusId;

	/**
	 * @roseuid 42A882800157
	 */
	public JuvenileStatus()
	{
	}

	/**
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this view JuvenileStats.
	 * @param juvNum
	 * @return JuvenileStatus.
	 */
	static public JuvenileStatus find(String juvNum)
	{
		IHome home = new Home();
		JuvenileStatus juvenile = (JuvenileStatus) home.find(juvNum, JuvenileStatus.class);
		return juvenile;
	}

	/**
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this view JuvenileStats.
	 * @return Iterator
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(event, JuvenileStatus.class);
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
