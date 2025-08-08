package pd.codetable.criminal;

import java.util.Iterator;

import messaging.codetable.reply.ICode;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * 
 * @roseuid 467FBB940205
 */
public class JuvenileDSM5Code extends PersistentObject implements ICode
{
	private String code;
	private String description;	
	private String tjjdDSMId;
	private String status;

	/**
	 * 
	 * @roseuid 467FBB940205
	 */
	public JuvenileDSM5Code()
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
	 * @return Returns the tjjdDSMId.
	 */
	public String getTjjdDSMId()
	{
		fetch();
		return tjjdDSMId;
	}

	/**
	 * 
	 * @param columnNum The tjjdDSMId to set.
	 */
	public void setTjjdDSMId(String tjjdDSMId)
	{
		if (this.tjjdDSMId == null || !this.tjjdDSMId.equals(tjjdDSMId))
		{
			markModified();
		}
		this.tjjdDSMId = tjjdDSMId;
	}
	/**
	 * 
	 * @return JuvenileDSM5Code object
	 * @param code
	 * @roseuid 41ACA9680353
	 *Find JuvenileDSM5Code object by codeId
	 */
	public static JuvenileDSM5Code find(String code)
	{
		return (JuvenileDSM5Code) new Home().find(code, JuvenileDSM5Code.class);
	}
	
	/**
	 * @roseuid 45771BE501A2
	 */
	public static Iterator findAll()
	{
		return new Home().findAll(JuvenileDSM5Code.class);
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
		return new Home().findAll(attributeName, attributeValue, JuvenileDSM5Code.class);
	}


}
