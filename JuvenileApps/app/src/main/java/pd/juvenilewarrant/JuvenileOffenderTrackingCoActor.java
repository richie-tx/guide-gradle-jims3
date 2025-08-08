package pd.juvenilewarrant;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* 
*/
public class JuvenileOffenderTrackingCoActor extends PersistentObject
{
	private String age;
	private String daLogNum;
	private String name;
	private String race;
	private String sequenceNum;
	private String sex;
	private String transactionNum;
	
	
	/**
	* @return Iterator JuvenileOffenderTrackingCharge
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileOffenderTrackingCoActor.class);
	}
	
	/**
	* @return Iterator JuvenileOffenderTrackingCharge
	*/
	static public Iterator findAllByDaLogNum( String daLogNum )
	{
		return new Home().findAll( "daLogNum", daLogNum, JuvenileOffenderTrackingCoActor.class);
	}
	
	/**
	* 
	*/
	public JuvenileOffenderTrackingCoActor()
	{
	}
	
	/**
	* 
	*/
	public String getDaLogNum()
	{
		fetch();
		return daLogNum;
	}
	
	/**
	* Sets the value of the daLogNum property.
	* @param aDaLogNum the new value of the daLogNum property
	*/
	public void setDaLogNum( String aDaLogNum )
	{
		if ( this.daLogNum == null || ! this.daLogNum.equals(aDaLogNum) )
		{
			markModified();
		}
		daLogNum = aDaLogNum;
	}
	

	/**
	 * @return Returns the age.
	 */
	public String getAge() 
	{
		fetch();
		return age;
	}
	
	/**
	 * @param age The age to set.
	 */
	public void setAge(String age) 
	{
		if ( this.age == null || ! this.age.equals(age) )
		{
			markModified();
		}
		this.age = age;
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() 
	{
		fetch();
		return name;
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) 
	{
		if ( this.name == null || ! this.name.equals(name) )
		{
			markModified();
		}
		this.name = name;
	}
	
	/**
	 * @return Returns the race.
	 */
	public String getRace() 
	{
		fetch();
		return race;
	}
	
	/**
	 * @param race The race to set.
	 */
	public void setRace(String race) 
	{
		if ( this.race == null || ! this.race.equals(race) )
		{
			markModified();
		}
		this.race = race;
	}
	
	/**
	 * @return Returns the sequenceNum.
	 */
	public String getSequenceNum() 
	{
		fetch();
		return sequenceNum;
	}
	
	/**
	 * @param sequenceNum The sequenceNum to set.
	 */
	public void setSequenceNum(String sequenceNum) 
	{
		if ( this.sequenceNum == null || ! this.sequenceNum.equals(sequenceNum) )
		{
			markModified();
		}
		this.sequenceNum = sequenceNum;
	}
	
	/**
	 * @return Returns the sex.
	 */
	public String getSex() 
	{
		fetch();
		return sex;
	}
	
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(String sex) 
	{
		if ( this.sex == null || ! this.sex.equals(sex) )
		{
			markModified();
		}
		this.sex = sex;
	}
	
	/**
	 * @return Returns the transactionNum.
	 */
	public String getTransactionNum() 
	{
		fetch();
		return transactionNum;
	}
	
	/**
	 * @param transactionNum The transactionNum to set.
	 */
	public void setTransactionNum(String transactionNum) 
	{
		if ( this.transactionNum == null || ! this.transactionNum.equals(transactionNum) )
		{
			markModified();
		}
		this.transactionNum = transactionNum;
	}
}
