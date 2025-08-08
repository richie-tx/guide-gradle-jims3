package pd.codetable.criminal;

import messaging.codetable.reply.ICode;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Iterator;

/**
 * @author dgibler
 *
 * JuvenileCourt entity
 */
public class JuvenileCourt extends PersistentObject implements ICode
{
	private String code;
	private String tycCourtCode;
	private String description;
	private String judgesName;
	private String inactiveInd;
	private String refereesCourtInd;

	/**
	* @roseuid 41ACCAD2037F
	*/
	public JuvenileCourt()
	{
	}
	/**
	* Access method for the code property.
	* @return the current value of the code property
	*/
	public String getCode()
	{
		fetch();
		return code;
	}
	/**
	* Sets the value of the code property.
	* @param aCode the new value of the code property
	*/
	public void setCode(String aCode)
	{
		if (this.code == null || !this.code.equals(aCode))
		{
			markModified();
		}
		code = aCode;
	}

	/**
	* Access method for the refereesCourtInd property.
	* @return the current value of the refereesCourtInd property
	*/
	public String getRefereesCourtInd()
	{
		fetch();
		return refereesCourtInd;
	}
	/**
	* Sets the value of the refereesCourtInd property.
	* @param aRefereesCourtInd the new value of the refereesCourtInd property
	*/
	public void setRefereesCourtInd(String aRefereesCourtInd)
	{
		if (this.refereesCourtInd == null || !this.refereesCourtInd.equals(aRefereesCourtInd))
		{
			markModified();
		}
		refereesCourtInd = aRefereesCourtInd;
	}
	/**
	* Access method for the tycCourtCode property.
	* @return the current value of the tycCourtCode property
	*/
	public String getTycCourtCode()
	{
		fetch();
		return this.tycCourtCode;
	}
	/**
	* Sets the value of the tycCourtCode property.
	* @param aTycCourtCode the new value of the tycCourtCode property
	*/
	public void setTycCourtCode(String aTycCourtCode)
	{
		if (this.tycCourtCode == null || !this.tycCourtCode.equals(aTycCourtCode))
		{
			markModified();
		}
		this.tycCourtCode = aTycCourtCode;
	}
	/**
	* Access method for the judgesName property.
	* @return the current value of the judgesName property
	*/
	public String getJudgesName()
	{
		fetch();
		return judgesName;
	}
	/**
	* Sets the value of the judgesName property.
	* @param aJudgesName the new value of the judgesName property
	*/
	public void setJudgesName(String aJudgesName)
	{
		if (this.judgesName == null || !this.judgesName.equals(aJudgesName))
		{
			markModified();
		}
		judgesName = aJudgesName;
	}
	/**
	* Access method for the inactiveInd property.
	* @return the current value of the inactiveInd property
	*/
	public String getInactiveInd()
	{
		fetch();
		return inactiveInd;
	}
	/**
	* Sets the value of the inactiveInd property.
	* @param aInactiveInd the new value of the inactiveInd property
	*/
	public void setInactiveInd(String aInactiveInd)
	{
		if (inactiveInd != aInactiveInd)
		{
			markModified();
		}
		inactiveInd = aInactiveInd;
	}
	/**
	* Access method for the description property.
	* @return the current value of the description property
	*/
	public String getDescription()
	{
		fetch();
		return description;
	}
	/**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*/
	public void setDescription(String aDescription)
	{
		if (this.description == null || !this.description.equals(aDescription))
		{
			markModified();
		}
		description = aDescription;
	}

	/**
	* @roseuid 41AC81DE0186
	* @param courtCode
	* @return a JuvenileCourt object
	*/
	public static JuvenileCourt find(String courtCode)
	{
		JuvenileCourt jc = null;
		IHome home = new Home();

		jc = (JuvenileCourt) home.find(courtCode, JuvenileCourt.class);

		return jc;
	}

	/**
	 * Find all Juvenile Courts.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileCourt.class);
	}

	/**
	 * Find all Juvenile Courts.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, JuvenileCourt.class);
	}
}
