package pd.codetable.criminal;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;
import mojo.km.persistence.PersistentObject;
import java.util.Iterator;

/**
 * @stereotype entity
 * @author cshimek
 */
public class JuvenileTechnicalVOPCodes extends PersistentObject
{
	private String code;
	private String description;
	/**
	 * @contextKey NCVIOLATION_LEVEL
	 * @referencedType pd.codetableCode
	 * @detailerDoNotGenerate false
	 */
	private Code level = null;
	private String levelId;

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
	* @return JuvenileVOPSanctionCodes
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileTechnicalVOPCodes find(String juvenileTechnicalVOPCodeId) {
		
		JuvenileTechnicalVOPCodes juvenileTechnicalVOPCode = null;
		IHome home = new Home();
		juvenileTechnicalVOPCode = (JuvenileTechnicalVOPCodes) home.find(juvenileTechnicalVOPCodeId, JuvenileTechnicalVOPCodes.class);
		return juvenileTechnicalVOPCode;
		
	}

	static public Iterator findAll()
	{
	  	Home home = new Home();
	  	Iterator codes = home.findAll(JuvenileTechnicalVOPCodes.class);
	    return codes;
	}
	
	static public Iterator findAll( IEvent event )
	{
		IHome home = new Home();
		return home.findAll( event, JuvenileTechnicalVOPCodes.class );
	}
}
