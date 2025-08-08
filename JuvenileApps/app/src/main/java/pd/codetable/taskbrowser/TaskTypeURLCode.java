package pd.codetable.taskbrowser;

import java.util.Iterator;
import messaging.codetable.reply.ICode;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author dapte
 *
 * TaskTypeURLCode entity
 */
public class TaskTypeURLCode extends PersistentObject implements ICode
{
	private String URL;
	//private String code;
	private String description;

	/**
	* @roseuid 418FD7D9021D
	*/
	public TaskTypeURLCode()
	{
	}


	/**
	* Access method for the category property.
	* @return the current value of the category property
	*/
	public String getURL()
	{
		fetch();
		return URL;
	}
	/**
	* Sets the value of the category property.
	* @param aCategory the new value of the category property
	*/
	public void setURL(String aURL)
	{
		if (this.URL == null || !this.URL.equals(aURL))
		{
			markModified();
		}
		URL = aURL;
	}
	/**
	* Access method for the code property.
	* @return the current value of the code property
	*/
	public String getCode()
	{
		return getOID().toString();
	}
	/**
	* Sets the value of the code property.
	* @param aCode the new value of the code property
	*/
	public void setCode(String aCode)
	{
		setOID(aCode);
	}
	/**
	* Access method for the description property.
	* @return the current value of the description property
	*/
	public String getDescription()
	{
		fetch();
		return description;
	}
	/**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*/
	public void setDescription(String aDescription)
	{
		if (this.description == null || !this.description.equals(aDescription))
		{
			markModified();
		}
		description = aDescription;
	}
	/**
	* @roseuid 418FCF8E026F
	*/
	public void find()
	{
		fetch();
	}
	/**
	 * @return pd.codetable.ScarsMarksTattoosCode
	 * @param code
	 * @roseuid 410FA34B0329
	 */
	static public TaskTypeURLCode find(String code)
	{
		TaskTypeURLCode ttucode = null;
		IHome home = new Home();
		ttucode = (TaskTypeURLCode) home.find(code, TaskTypeURLCode.class);
		return ttucode;
	}
	/**
	 * Find all ScarsMarksTattoosCode objects
	 * @return java.util.Iterator
	 */
	static public Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(TaskTypeURLCode.class);
		return iter;
	}

}
