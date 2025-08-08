package pd.codetable.criminal;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

import java.util.Date;
import java.util.Iterator;

/**
 * @stereotype entity
 * @author cshimek
 */
public class JuvenileCodeTableChildCodes extends PersistentObject
{
	private String code;
	private String description;
	private String parentId;
	private String codeTableName;
	private String parentTable;
	private String status;
	
	//testing for a Bug
	private Date inactivateDate;
	private Date activateDate;

	/**
	 * 
	 * @return the code
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
	 * 
	 * @param code the code to set
	 */
	public void setCode(String code)
	{
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}

	/**
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setParentId(String parentId)
	{
		if (this.parentId == null || !this.parentId.equals(parentId))
		{
			markModified();
		}
//		level = null;
		this.parentId = parentId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getParentId()
	{
		fetch();
		return parentId;
	}

	/**
	* @return JuvenileCodeTableChildCodes
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileCodeTableChildCodes find(String juvenileCodeTableChildCodeId) {
		
		JuvenileCodeTableChildCodes juvenileCodeTableChildCode = null;
		IHome home = new Home();
		 juvenileCodeTableChildCode = (JuvenileCodeTableChildCodes) home.find(juvenileCodeTableChildCodeId, JuvenileCodeTableChildCodes.class);
		return  juvenileCodeTableChildCode;
		
	}

	static public Iterator findAll()
	{
	  	Home home = new Home();
	  	Iterator codes = home.findAll(JuvenileCodeTableChildCodes.class);
	    return codes;
	}
	
	static public Iterator findAll( IEvent event )
	{
		IHome home = new Home();
		return home.findAll( event, JuvenileCodeTableChildCodes.class );
	}
	
	static public Iterator findAll(String attrName, String attrValue) {
        IHome home = new Home();
        return home.findAll(attrName, attrValue, JuvenileCodeTableChildCodes.class);
	}

	public String getCodeTableName() {
		fetch();
		return codeTableName;
	}

	public void setCodeTableName(String codeTableName) {
		if (this.codeTableName == null || !this.codeTableName.equals(codeTableName))
		{
			markModified();
		}
		this.codeTableName = codeTableName;
	}

	public String getParentTable() {
		fetch();
		return parentTable;
	}

	public void setParentTable(String parentTable) {
		if (this.parentTable == null || !this.parentTable.equals(parentTable))
		{
			markModified();
		}
		this.parentTable = parentTable;
	}

	public String getStatus() {
		fetch();
		return status;
	}

	public void setStatus(String status) {
		if (this.status == null || !this.status.equals(status))
		{
			markModified();
		}
		this.status = status;
	}

	public void setInactivateDate(Date inactivateDate) {
		this.inactivateDate = inactivateDate;
	}

	public Date getInactivateDate() {
		return inactivateDate;
	}
	/**
	 * @return the activateDate
	 */
	public Date getActivateDate() {
		fetch();
		return activateDate;
	}

	/**
	 * @param activeEffectDate the activateDate to set
	 */
	public void setActivateDate(Date activateDate) {
		if(this.activateDate == null || !this.activateDate.equals(activateDate))
		{
			markModified();
		}
		this.activateDate = activateDate;
	}

}
