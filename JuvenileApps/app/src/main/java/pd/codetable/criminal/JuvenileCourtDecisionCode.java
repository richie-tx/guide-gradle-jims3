package pd.codetable.criminal;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

import java.util.Date;
import java.util.Iterator;

/**
 * @stereotype entity
 * @author ryoung
 */
public class JuvenileCourtDecisionCode extends PersistentObject
{
	/**
     * 
     */
    	private static final long serialVersionUID = 1L;
	private String code;
	private String codeAlpha; //OID
	private String description;
	private String decision;
	private String action;
	private String status;
	private String resetAllowed;
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
	     * @return codeAlpha
	     */
	    public String getCodeAlpha()
	    {
		fetch();
		return codeAlpha;
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
	public void setDecision(String parentId)
	{
		if (this.decision == null || !this.decision.equals(parentId))
		{
			markModified();
		}
//		level = null;
		this.decision = parentId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDecision()
	{
		fetch();
		return decision;
	}

	/**
	* @return JuvenileCodeTableChildCodes
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileCourtDecisionCode find(String juvenileCodeTableChildCodeId) {
		
		JuvenileCourtDecisionCode juvenileCodeTableChildCode = null;
		IHome home = new Home();
		 juvenileCodeTableChildCode = (JuvenileCourtDecisionCode) home.find(juvenileCodeTableChildCodeId, JuvenileCourtDecisionCode.class);
		return  juvenileCodeTableChildCode;
		
	}

	static public Iterator findAll()
	{
	  	Home home = new Home();
	  	Iterator codes = home.findAll(JuvenileCourtDecisionCode.class);
	    return codes;
	}
	
	static public Iterator findAll( IEvent event )
	{
		IHome home = new Home();
		return home.findAll( event, JuvenileCourtDecisionCode.class );
	}
	
	/**
	 * Find all Juvenile Courts.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, JuvenileCourtDecisionCode.class);
	}

	public String getAction() {
		fetch();
		return action;
	}

	public void setAction(String codeTableName) {
		if (this.action == null || !this.action.equals(codeTableName))
		{
			markModified();
		}
		this.action = codeTableName;
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
	    if (this.inactivateDate == null || !this.inactivateDate.equals(inactivateDate))
		{
			markModified();
		}
	    
	    this.inactivateDate = inactivateDate;
	}

	public Date getInactivateDate() {
		fetch();
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

	public String getResetAllowed()
	{
	    fetch();
	    return resetAllowed;
	}

	public void setResetAllowed(String resetAllowed)
	{
	    if (this.resetAllowed == null || !this.resetAllowed.equals(resetAllowed))
		{
			markModified();
		}
	    this.resetAllowed = resetAllowed;
	}

}
