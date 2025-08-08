package pd.security.authentication.registergenericaccount;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
* @roseuid 4399E4160242
*/
public class JIMSGenericAccountAccttypeView extends JIMSGenericAccount
{
	private String jimsAccountTypeId;
	private String genericAccountAccttypeViewId;
		
	/**
	* @roseuid 4399E4160242
	*/
	public JIMSGenericAccountAccttypeView()
	{
	}

	/**
	 * @return Returns the jimsAccountTypeId.
	 */
	public String getJimsAccountTypeId() {
		return jimsAccountTypeId;
	}
	/**
	 * @param jimsAccountTypeId The jimsAccountTypeId to set.
	 */
	public void setJimsAccountTypeId(String jimsAccountTypeId) {
		this.jimsAccountTypeId = jimsAccountTypeId;
	}
	
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, JIMSGenericAccountAccttypeView.class);
		return iter;
	}

	/**
	 * @param deptEvent
	 * @param class1
	 * @return
	 */
	public static MetaDataResponseEvent findMeta(IEvent jgaEvent ) {
		IHome home = new Home();
		MetaDataResponseEvent iter = home.findMeta(jgaEvent, JIMSGenericAccountAccttypeView.class);
		return iter;
	}	
	
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public JIMSGenericAccountAccttypeView findByOID(String genericAccountAccttypeViewId)
	{
		IHome home = new Home();
		return (JIMSGenericAccountAccttypeView) home.find(genericAccountAccttypeViewId, JIMSGenericAccountAccttypeView.class);
	}
	/**
	 * @return Returns the genericAccountAccttypeViewId.
	 */
	public String getGenericAccountAccttypeViewId() {
		return genericAccountAccttypeViewId;
	}
	/**
	 * @param genericAccountAccttypeViewId The genericAccountAccttypeViewId to set.
	 */
	public void setGenericAccountAccttypeViewId(String genericAccountAccttypeViewId) {
		this.genericAccountAccttypeViewId = genericAccountAccttypeViewId;
	}
	
}
