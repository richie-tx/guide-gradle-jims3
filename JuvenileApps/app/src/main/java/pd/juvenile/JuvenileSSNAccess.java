package pd.juvenile;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileSSNAccess extends PersistentObject 
{
	
	private String juvenileNum;
	
	private String ssn;
	 
	private String accessedBy;
	 

	/**
	 * @roseuid 42B062E7022B
	 */
	public JuvenileSSNAccess()
	{
	}


	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		fetch();
		return juvenileNum;
	}
	
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		if (this.ssn == null || !this.ssn.equals(ssn))
		{
			markModified();
		}
		this.ssn = ssn;
	}

	/**
	 * @return the ssn
	 */
	public String getSsn() {
		fetch();
		return ssn;
	}
	
	/**
	 * @param accessedBy the accessedBy to set
	 */
	public void setAccessedBy(String accessedBy) {
		if (this.accessedBy == null || !this.accessedBy.equals(accessedBy))
		{
			markModified();
		}
		this.accessedBy = accessedBy;
	}

	/**
	 * @return the accessedBy
	 */
	public String getAccessedBy() {
		fetch();
		return accessedBy;
	}

	
	/**
	 * Finds all jis records by an attribute value
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator benefits = home.findAll(attributeName, attributeValue, JuvenileJIS.class);
		return benefits;
	}
	
	

}	