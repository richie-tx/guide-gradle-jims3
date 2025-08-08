package pd.codetable.criminal;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;
import mojo.km.persistence.PersistentObject;
import java.util.Iterator;

/**
 * 
 * @stereotype entity
 * @author cshimek
 */
public class JuvenileVOPSanctionCodes extends PersistentObject
{
	private String code;
	private String description;
	private String status; // bug fix;21770
	/**
	 * @contextKey NCVIOLATION_LEVEL
	 * @referencedType pd.codetableCode
	 * @detailerDoNotGenerate false
	 */
	private Code level = null;
	/**
	 * @contextKey NCSANCTION_LEVEL
	 * @referencedType pd.codetableCode
	 * @detailerDoNotGenerate false
	 */
	private Code sanctionLevel = null;
	private String levelId;
	private String sanctionLevelId;

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
	public String getStatus()
	{
		fetch();
		return status;
	}

	/**
	 * 
	 * @param description the description to set
	 */
	public void setStatus(String status)
	{
		if (this.status == null || !this.status.equals(status))
		{
			markModified();
		}
		this.status = status;
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
	public void setLevelId(String levelId)
	{
		if (this.levelId == null || !this.levelId.equals(levelId))
		{
			markModified();
		}
		level = null;
		this.levelId = levelId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getLevelId()
	{
		fetch();
		return levelId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initLevel()
	{
		if (level == null)
		{
			level = (Code) new mojo.km.persistence.Reference(levelId, Code.class,
					"NCVIOLATION_LEVEL").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getLevel()
	{
		initLevel();
		return level;
	}

	/**
	 * set the type reference for class member level
	 */
	public void setLevel(Code level)
	{
		if (this.level == null || !this.level.equals(level))
		{
			markModified();
		}
		setLevelId("" + level.getOID());
		level.setContext("NCVIOLATION_LEVEL");
		this.level = (Code) new mojo.km.persistence.Reference(level).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setSanctionLevelId(String sanctionLevelId)
	{
		if (this.sanctionLevelId == null || !this.sanctionLevelId.equals(sanctionLevelId))
		{
			markModified();
		}
		sanctionLevel = null;
		this.sanctionLevelId = sanctionLevelId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSanctionLevelId()
	{
		fetch();
		return sanctionLevelId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSanctionLevel()
	{
		if (sanctionLevel == null)
		{
			sanctionLevel = (Code) new mojo.km.persistence.Reference(sanctionLevelId,
					Code.class, "NCSANCTION_LEVEL").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getSanctionLevel()
	{
		initSanctionLevel();
		return sanctionLevel;
	}

	/**
	 * set the type reference for class member sanctionLevel
	 */
	public void setSanctionLevel(Code sanctionLevel)
	{
		if (this.sanctionLevel == null || !this.sanctionLevel.equals(sanctionLevel))
		{
			markModified();
		}
		setSanctionLevelId("" + sanctionLevel.getOID());
		sanctionLevel.setContext("NCSANCTION_LEVEL");
		this.sanctionLevel = (Code) new mojo.km.persistence.Reference(sanctionLevel).getObject();
	}
	
	/**
	* @return JuvenileVOPSanctionCodes
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileVOPSanctionCodes find(String juvenileVOPSanctionCodeId) {
		
		JuvenileVOPSanctionCodes juvenileVOPSanctionCode = null;
		IHome home = new Home();
		juvenileVOPSanctionCode = (JuvenileVOPSanctionCodes) home.find(juvenileVOPSanctionCodeId, JuvenileVOPSanctionCodes.class);
		return juvenileVOPSanctionCode;
		
	}
	
    static public Iterator findAll()
    {
    	Home home = new Home();
    	Iterator codes = home.findAll(JuvenileVOPSanctionCodes.class);
        return codes;
    }
    
	static public Iterator findAll( IEvent event )
	{
		IHome home = new Home();
		return home.findAll( event, JuvenileVOPSanctionCodes.class );
	}
}

