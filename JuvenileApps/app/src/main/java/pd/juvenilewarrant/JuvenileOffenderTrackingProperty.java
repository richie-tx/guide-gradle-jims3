package pd.juvenilewarrant;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* 
*/
public class JuvenileOffenderTrackingProperty extends PersistentObject
{
	private String daLogNum;
	private String description;
	private String value;
	private String sequenceNum;
	private String transactionNum;
	
	
	/**
	* @return Iterator JuvenileOffenderTrackingCharge
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileOffenderTrackingProperty.class);
	}
	
	/**
	* @return Iterator JuvenileOffenderTrackingCharge
	*/
	static public Iterator findAllByDaLogNum( String daLogNum )
	{
		return new Home().findAll( "daLogNum", daLogNum, JuvenileOffenderTrackingProperty.class);
	}
	
	/**
	* 
	*/
	public JuvenileOffenderTrackingProperty()
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
	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() 
	{
		fetch();
		return description;
	}
	
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) 
	{
		if ( this.description == null || ! this.description.equals(description) )
		{
			markModified();
		}
		this.description = description;
	}
	
	/**
	 * @return Returns the value.
	 */
	public String getValue() 
	{
		fetch();
		return value;
	}
	
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) 
	{
		if ( this.value == null || ! this.value.equals(value) )
		{
			markModified();
		}
		this.value = value;
	}
}
