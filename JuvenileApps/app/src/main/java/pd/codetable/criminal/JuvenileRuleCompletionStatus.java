package pd.codetable.criminal;

import java.util.Iterator;

import messaging.codetable.reply.ICode;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileRuleCompletionStatus extends PersistentObject implements ICode{
	private String code;
	private String type;
	private String description;
	private String status;
	
	public JuvenileRuleCompletionStatus()
	{
	}
	/**
	* Access method for the code property.
	* @return the current value of the code property
	*/
	public String getCode()
	{
		fetch();
		return code;
	}
	/**
	* Sets the value of the code property.
	* @param aCode the new value of the code property
	*/
	public void setCode(String aCode)
	{
		if (this.code == null || !this.code.equals(aCode))
		{
			markModified();
		}
		code = aCode;
	}

	/**
	* Access method for the tycCourtCode property.
	* @return the current value of the tycCourtCode property
	*/
	public String getType()
	{
		fetch();
		return this.type;
	}
	/**
	* Sets the value of the tycCourtCode property.
	* @param aTycCourtCode the new value of the tycCourtCode property
	*/
	public void setType(String aType)
	{
		if (this.type == null || !this.type.equals(aType))
		{
			markModified();
		}
		this.type = aType;
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
	* Access method for the status property.
	* @return the current value of the status property
	*/
	public String getStatus() {
		fetch();
		return status;
	}
	/**
	* Sets the value of the status property.
	* @param aDescription the new value of the status property
	*/
	public void setStatus(String aStatus) {
		if( this.status == null || !this.status.equals( aStatus ))
		{
			markModified();
		}
		this.status = aStatus;
	}
	/**
	* @roseuid 41AC81DE0186
	* @param courtCode
	* @return a JuvenileRuleCompletionStatus object
	*/
	public static JuvenileRuleCompletionStatus find(String statusCode)
	{
		JuvenileRuleCompletionStatus jrcs = null;
		IHome home = new Home();

		jrcs = (JuvenileRuleCompletionStatus) home.find(statusCode, JuvenileRuleCompletionStatus.class);

		return jrcs;
	}

	/**
	 * Find all JuvenileRuleCompletionStatuses.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileRuleCompletionStatus.class);
	}

	/**
	 * Find all JuvenileRuleCompletionStatuses.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, JuvenileRuleCompletionStatus.class);
	}
}
