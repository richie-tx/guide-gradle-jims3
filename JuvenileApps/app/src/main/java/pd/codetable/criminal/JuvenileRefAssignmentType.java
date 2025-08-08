package pd.codetable.criminal;

import java.util.Iterator;

import messaging.codetable.reply.ICode;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * 
 * @roseuid 467FBB940205
 */
public class JuvenileRefAssignmentType extends PersistentObject implements ICode
{
	private String code;
	private String description;	
	private String casefileGenerate;
	private String status;

	/**
	 * 
	 * @roseuid 467FBB940205
	 */
	public JuvenileRefAssignmentType()
	{
	}



	/**
	 * 
	 * @return Returns the code.
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
	 * 
	 * @param code The code to set.
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
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	 * 
	 * @param description The description to set.
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
	 * 
	 * @return JuvenileDSM5Code object
	 * @param code
	 * @roseuid 41ACA9680353
	 *Find JuvenileDSM5Code object by codeId
	 */
	public static JuvenileRefAssignmentType find(String code)
	{
		return (JuvenileRefAssignmentType) new Home().find(code, JuvenileRefAssignmentType.class);
	}
	
	/**
	 * @roseuid 45771BE501A2
	 */
	public static Iterator findAll()
	{
		return new Home().findAll(JuvenileRefAssignmentType.class);
	}



	public String getStatus() {
		fetch();
		return status;
	}



	public void setStatus(String status) {
		if(this.status==null || !this.status.equals(status))
		{
			markModified();
		}
		this.status = status;
	}
	
	/**
	* Finds all JuvenileDSM5Code by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, JuvenileRefAssignmentType.class);
	}



	/**
	 * @return the casefileGenerate
	 */
	public String getCasefileGenerate()
	{
	    fetch();
	    return casefileGenerate;
	}



	/**
	 * @param casefileGenerate the casefileGenerate to set
	 */
	public void setCasefileGenerate(String casefileGenerate)
	{
	    if(this.casefileGenerate==null || !this.casefileGenerate.equals(casefileGenerate))
	    {
		markModified();
	    }
	    this.casefileGenerate = casefileGenerate;
	}


}
