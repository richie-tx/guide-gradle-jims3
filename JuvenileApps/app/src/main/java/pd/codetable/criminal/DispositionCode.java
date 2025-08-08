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
 * DispositionCode entity
 */
public class DispositionCode extends PersistentObject
{

	
	private String dispositionDescription;
	private String dispositionCode;
	
	
	/**
	* @roseuid 418FD7CE0356
	*/
	public DispositionCode()
	{
	}	
	
	
	
	public String getDispositionDescription() {
		fetch();
		return dispositionDescription;
	}

	public void setDispositionDescription(String dispositionDescription) {
		if (this.dispositionDescription == null || !this.dispositionDescription.equals(dispositionDescription))
		{
			markModified();
		}
		this.dispositionDescription = dispositionDescription;
	}

	public String getDispositionCode() {
		fetch();
		return dispositionCode;
	}

	public void setDispositionCode(String dispositionCode) {
		if (this.dispositionCode == null || !this.dispositionCode.equals(dispositionCode))
		{
			markModified();
		}
		this.dispositionCode = dispositionCode;
	}

	/**
	* @roseuid 418FCF8E022E
	* Find OffenseCode by codeId
	* @param ncicCode
	* @return OffenseCode object
	*/
	
	public static Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(DispositionCode.class);
		return iter;

	}
	
	/*
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue,DispositionCode.class);
	}
	/**
	 * Find all OffenseCode objects that match search criteria
	 * @return all OffenseCode objects
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, DispositionCode.class);
		return iter;

	}
	
	public static MetaDataResponseEvent findMeta(IEvent anEvent) {
		IHome home = new Home();
		MetaDataResponseEvent iter = home.findMeta(anEvent, DispositionCode.class);
		return iter;
	}	

}
