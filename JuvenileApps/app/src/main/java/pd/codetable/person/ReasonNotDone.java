package pd.codetable.person;

import java.util.Iterator;
import pd.codetable.ICodetable;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 4279069203C8
*/
public class ReasonNotDone extends PersistentObject
//public class AliasNameTypeCode extends PersistentObject 
{	
	private String code;
	private String description;
	private String finalReason;
	private String status;
	
	/**
	* @roseuid 4279069203C8
	*/
	public ReasonNotDone() {
	}
	/**
	* @roseuid 427295FF01E4
	*/
	public static ReasonNotDone find(String oid) {
		return (ReasonNotDone) new Home().find(oid, ReasonNotDone.class);
	}
	/**
	* @return java.lang.String
	*/
	public String getCode() {
		fetch();
		return code;
	}
	
	/**
	* @param theCode
	*/
	public void setCode(String theCode) {
		if (this.code == null || !this.code.equals(theCode)) {
			markModified();
		}
		this.code = theCode;
	}
	public String getStatus()
	{
	    fetch();
	    return status;
	}
	public void setStatus(String status)
	{
	    if (this.status == null || !this.status.equals(status)) {
		markModified();
	    }
	    this.status = status;
	}
	public String getFinalReason()
	{
	    fetch();
	    return finalReason;
	}
	public void setFinalReason(String finalReason)
	{
	    if (this.finalReason == null || !this.finalReason.equals(finalReason)) {
		markModified();
	    }
	    this.finalReason = finalReason;
	}

	public String getDescription()
	{
	    fetch();
	    return description;
	}
	public void setDescription(String description)
	{
	    if (this.description == null || !this.description.equals(description)) {
		markModified();
	    }
	    this.description = description;
	}
	
	
	
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator codes = home.findAll(attributeName, attributeValue, ReasonNotDone.class);
		return codes;
	}
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#findAll()
	 */
	static public Iterator findAll() {
		return new Home().findAll(ReasonNotDone.class);
	}
	
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
}
