package pd.codetable.supervision;

import java.util.Iterator;

import pd.codetable.ICodetable;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;

import messaging.supervisionorder.reply.MagistrateResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 43BD3D1102F6
*/
public class Magistrate extends PersistentObject implements ICodetable
{
	private String firstName;
	private String lastName;
	private String middleName;
	/**
	* @roseuid 43BD3D1102F6
	*/
	public Magistrate()
	{
	}
	/**
	* @roseuid 43BD3231028E
	*/
	public void find()
	{
		fetch();
	}
	/**
	* @return 
	*/
	public String getFirstName()
	{
		fetch();
		return firstName;
	}
	/**
	* @return 
	*/
	public String getLastName()
	{
		fetch();
		return lastName;
	}
	/**
	* @return 
	*/
	public String getMiddleName()
	{
		fetch();
		return middleName;
	}
	/**
	* @param aFirstName
	*/
	public void setFirstName(String aFirstName)
	{
		if (this.firstName == null || !this.firstName.equals(aFirstName))
		{
			markModified();
		}
		firstName = aFirstName;
	}
	/**
	* @param aLastName
	*/
	public void setLastName(String aLastName)
	{
		if (this.lastName == null || !this.lastName.equals(aLastName))
		{
			markModified();
		}
		lastName = aLastName;
	}
	/**
	* @param aMiddleName
	*/
	public void setMiddleName(String aMiddleName)
	{
		if (this.middleName == null || !this.middleName.equals(aMiddleName))
		{
			markModified();
		}
		middleName = aMiddleName;
	}
	/**
	 * @param oid
	 * @return
	 */
	public static Magistrate find(String oid)
	{
		IHome home = new Home();
		return (Magistrate) home.find(oid, Magistrate.class);
	}
	/**
	 * @param event
	 * @return
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, Magistrate.class);
	}
	/**
	 * @return
	 */
	public MagistrateResponseEvent getResponseEvent()
	{
		MagistrateResponseEvent response = new MagistrateResponseEvent();
		response.setFirstName(this.getFirstName());
		response.setMiddleName(this.getMiddleName());
		response.setLastName(this.getLastName());
		response.setMagistrateId(this.getOID().toString());
		return response;
	}
	public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(Magistrate.class);
		
	}
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
}
