package pd.contact;

import java.util.Iterator;

import pd.address.Address;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 431F09BD01C5
*/
public class Phone extends PersistentObject {
	private String phoneExt;
	private String phoneNumber;
	private boolean primaryInd;
	/**
	* @roseuid 431F09BD01C5
	*/
	public Phone() {
	}
	/**
	* @roseuid 4266C56302E3
	*/
	public void bind() {
		markModified();
	}
	/**
	* @return 
	*/
	public String getPhoneExt() {
		fetch();
		return phoneExt;
	}
	/**
	* @return 
	*/
	public String getPhoneNumber() {
		fetch();
		return phoneNumber;
	}
	/**
	* @param string
	*/
	public void setPhoneExt(String string) {
		if (this.phoneExt == null || !this.phoneExt.equals(string)) {
			markModified();
		}
		phoneExt = string;
	}
	/**
	* @param string
	*/
	public void setPhoneNumber(String string) {
		if (this.phoneNumber == null || !this.phoneNumber.equals(string)) {
			markModified();
		}
		phoneNumber = string;
	}
	
	/**
	* Finds all phone by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator casefiles = home.findAll(attributeName, attributeValue, Phone.class);
		return casefiles;
	}
	

	/**
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this table.
	 * @param juvNum
	 * @return Juvenile.
	 * @return pd.juvenilecase.Juvenile
	 */
	static public Phone find(String oid) 
	{
	    Integer i = new Integer(oid);		
	    Phone phone = null;
	    IHome home = new Home();
	    phone = (Phone) home.find(oid, Phone.class);
	    return phone;
	}
	
	/**
	 * @param searchEvent
	 * @return
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, Phone.class);
	}
	/**
	 * @return the primaryInd
	 */
	public boolean isPrimaryInd() {
		fetch();
		return primaryInd;
	}
	/**
	 * @param primaryInd the primaryInd to set
	 */
	public void setPrimaryInd(boolean b) {
		if (this.primaryInd != b) {
			markModified();
		}
		primaryInd = b;
	}
}
