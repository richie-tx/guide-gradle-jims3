package pd.codetable.criminal;

import java.util.Iterator;

import pd.codetable.ICodetable;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class ServiceDeliveryWithoutFeeCode extends PersistentObject implements ICode{

	private String code;
	private String description;
	private String category;
	private String inactiveInd;
	
	
	public ServiceDeliveryWithoutFeeCode(){
		
	}
	
	
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(ServiceDeliveryWithoutFeeCode.class);
	}
	
	/*
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue,ServiceDeliveryWithoutFeeCode.class);
	}
	/**
	 * Find all OffenseCode objects that match search criteria
	 * @return all OffenseCode objects
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, ServiceDeliveryWithoutFeeCode.class);
		return iter;

	}
	
//	public static MetaDataResponseEvent findMeta(IEvent anEvent) {
//		IHome home = new Home();
//		MetaDataResponseEvent iter = home.findMeta(anEvent, ServiceDeliveryWithoutFeeCode.class);
//		return iter;
//	}	
	
	static public ServiceDeliveryWithoutFeeCode find(String oid)
	{
		IHome home = new Home();
		ServiceDeliveryWithoutFeeCode code = (ServiceDeliveryWithoutFeeCode) home.find(oid, ServiceDeliveryWithoutFeeCode.class);
		return code;
	}
    
	/**
	 *binds the attribute with the property. 
	 */
	public void bind()
	{
		markModified();
	}
	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		fetch();
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		fetch();
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		fetch();
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		if (this.category == null || !this.category.equals(category))
		{
			markModified();
		}
		this.category = category;
	}
	/**
	 * @return Returns the inactiveInd.
	 */
	public String getInactiveInd()
	{
		fetch();
		return inactiveInd;
	}

	/**
	 * @param department The department to set.
	 */
	public void setInactiveInd(String inactiveInd)
	{
		if (this.inactiveInd == null || !this.inactiveInd.equals(inactiveInd))
		{
			markModified();
		}
		this.inactiveInd = inactiveInd;
	}

}


