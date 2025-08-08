package pd.codetable.criminal;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * 
 *
 * SpecialAttentionReason entity
 */
public class SpecialAttentionReason extends PersistentObject
{

	
	private String description;
	private String code;
	
	
	/**
	* @roseuid 418FD7CE0356
	*/
	public SpecialAttentionReason()
	{
	}	
	
	
	
	/**
	* @roseuid 418FCF8E022E
	* Find SpecialAttentionReason by codeId
	* @param ncicCode
	* @return OffenseCode object
	*/
	
	public static Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(SpecialAttentionReason.class);
		return iter;

	}
	
	/*
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue,SpecialAttentionReason.class);
	}
	/**
	 * Find all OffenseCode objects that match search criteria
	 * @return all OffenseCode objects
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, SpecialAttentionReason.class);
		return iter;

	}
	
	public static MetaDataResponseEvent findMeta(IEvent anEvent) {
		IHome home = new Home();
		MetaDataResponseEvent iter = home.findMeta(anEvent, SpecialAttentionReason.class);
		return iter;
	}

	public void setDescription(String description) {
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}

	public String getDescription() {
		fetch();
		return description;
	}

	public void setCode(String code) {
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}

	public String getCode() {
		fetch();
		return code;
	}	

}
