/*
 * Created on Jul 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.addressValidation;
import java.util.Collection;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.ObjectNotFoundException;
import mojo.km.persistence.PersistentObject;

/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Address extends PersistentObject
{
	private int streetNum;
	private String streetName;
	private int zipCode;
	private String city;
	private String keyMap;
	
	//ER# 75491 - Populate Lat/Long from GIS Validation
	private String latitude;
	private String longitude;
	

	/**
	 * @return
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @return
	 */
	public String getKeyMap()
	{
		return keyMap;
	}

	/**
	 * @return
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/**
	 * @return
	 */
	public int getStreetNum()
	{
		return streetNum;
	}

	/**
	 * @return
	 */
	public int getZipCode()
	{
		return zipCode;
	}

	/**
	 * @return
	 */
	public String getLatitude()
	{
		return latitude;
	}
	
	/**
	 * @return
	 */
	public String getLongitude()
	{
		return longitude;
	}
	/**
	 * @param string
	 */
	public void setCity(String string)
	{
		city = string;
	}

	/**
	 * @param string
	 */
	public void setKeyMap(String string)
	{
		keyMap = string;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string)
	{
		streetName = string;
	}

	/**
	 * @param i
	 */
	public void setStreetNum(int i)
	{
		streetNum = i;
	}

	/**
	 * @param i
	 */
	public void setZipCode(int i)
	{
		zipCode = i;
	}
	
	/**
	 * @param latitude
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	/**
	 * @param longitude
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
//	static public Address find(String agencyId)
//		{
//			Address address = null;
//			IHome home = new Home();
//			try
//			{
//				address = (Address) home.find(addressId, Address.class);
//			}
//			catch (ObjectNotFoundException e)
//			{
//				address = null;
//			}
//			return address ;
//		}
		/**
		* @return java.util.Iterator
		* @roseuid 41123AE00111
		*/
//		static public Iterator findAll()
//		{
//			IHome home = new Home();
//			Iterator iter = home.findAll(Address .class);
//			return iter;
//		}
//	
//		/**
//		* @return java.util.Iterator
//		* @param attrName
//		* @param attrValue
//		* @roseuid 4236ED950316
//		*/
//		static public Iterator findAll(String attrName, String attrValue) {
//			IHome home = new Home();
//			return (Iterator) home.findAll(attrName,attrValue,Address .class);
//		}
//		
//		
		/**
		* @return java.util.Iterator
		* @param event
		* @roseuid 4107B06D01BB
		*/
		static public Iterator findAll(IEvent event)
		{
			IHome home = new Home();
			Iterator iter = home.findAll(event, Address.class);
			return iter;
		}
	
}
