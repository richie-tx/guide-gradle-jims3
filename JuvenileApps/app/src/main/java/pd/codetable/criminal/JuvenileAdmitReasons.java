package pd.codetable.criminal;

import java.util.Iterator;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author sdaripa
 JuvenileFacility entity
 */
public class JuvenileAdmitReasons extends PersistentObject implements ICode
{
	private String code;
	private String description;
	private String juvenileAdmitReasonsId;
	private String genDetHearingChain;
	private String probCauseHearingDays; 
	private String detentionType;	
	private String inactiveInd;
	private String tjjdContinueDet;
	
	
	//private Code levelOfCare = null;
	//private String levelOfCareId;
	
	//private String facilityPhone;
	//private String juvenilePlacementType;
	
	/**
	 * @roseuid 41ACCAD2037F
	 */
	public JuvenileAdmitReasons()
	{
	}

	/**
	 * @return a JuvenileCourt object
	 * @param juvenileFacilityId
	 * @roseuid 41AC81DE0186
	 */
	static public JuvenileAdmitReasons find(String juvenileAdmitReasonsId)
	{
		return (JuvenileAdmitReasons) new Home().find(juvenileAdmitReasonsId, JuvenileAdmitReasons.class);
	}

	/**
	 * Find all Juvenile Admit Reasons.
	 * @return java.util.Iterator
	 */
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileAdmitReasons.class);
	}

	/**
	* @return 
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileAdmitReasons.class);
	}
	/**
	* Finds all JuvenileAdmitReasons by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, JuvenileAdmitReasons.class);
	}
	
	/**
	* Finds all JuvenileAdmitReasons by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public JuvenileAdmitReasons find(String attributeName, String attributeValue)
	    {
		return (JuvenileAdmitReasons) new Home().find(attributeName, attributeValue, JuvenileAdmitReasons.class);
	    }

	

	/**
	 * @return Returns the code.
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
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
	 * @return Returns the genDetHearingChain.
	 */
	public String getGenDetHearingChain()
	{
		fetch();
		return genDetHearingChain;
	}

	/**
	 * @param genDetHearingChain The genDetHearingChain to set.
	 */
	public void setGenDetHearingChain(String genDetHearingChain)
	{
		if (this.genDetHearingChain == null || !this.genDetHearingChain.equals(genDetHearingChain))
		{
			markModified();
		}
		this.genDetHearingChain = genDetHearingChain;
	}
	

	/**
	 * @return Returns the probCauseHearingDays.
	 */
	public String getProbCauseHearingDays()
	{
		fetch();
		return probCauseHearingDays;
	}

	/**
	 * @param probCauseHearingDays The probCauseHearingDays to set.
	 */
	public void setProbCauseHearingDays(String probCauseHearingDays)
	{
		if (this.probCauseHearingDays == null || !this.probCauseHearingDays.equals(probCauseHearingDays))
		{
			markModified();
		}
		this.probCauseHearingDays = probCauseHearingDays;
	}
	
	/**
	 * @return Returns the detentionType.
	 */
	public String getDetentionType()
	{
		fetch();
		return detentionType;
	}

	/**
	 * @param detentionType The detentionType to set.
	 */
	public void setDetentionType(String detentionType)
	{
		if (this.detentionType == null || !this.detentionType.equals(detentionType))
		{
			markModified();
		}
		this.detentionType = detentionType;
	}
	

	/**
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
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
	 * @return Returns the juvenileAdmitReasonsId.
	 */
	public String getJuvenileAdmitReasonsId() {
		fetch();
		return this.getOID().toString();
	}
	/**
	 * @param juvenileAdmitReasonsId The juvenileAdmitReasonsId to set.
	 */
	public void setJuvenileAdmitReasonsId(String juvenileAdmitReasonsId) {
		if (this.juvenileAdmitReasonsId == null || !this.juvenileAdmitReasonsId.equals(juvenileAdmitReasonsId))
		{
			markModified();
		}
		this.setOID(juvenileAdmitReasonsId);
		this.juvenileAdmitReasonsId = juvenileAdmitReasonsId;
	}
	

	/**
	 * @return Returns the inactiveInd.
	 */
	public String getInactiveInd()
	{
		fetch();
		return inactiveInd;
	}

	/**
	 * @param department The department to set.
	 */
	public void setInactiveInd(String inactiveInd)
	{
		if (this.inactiveInd == null || !this.inactiveInd.equals(inactiveInd))
		{
			markModified();
		}
		this.inactiveInd = inactiveInd;
	}

	public String getTjjdContinueDet()
	{
	    fetch();
	    return tjjdContinueDet;
	}

	public void setTjjdContinueDet(String tjjdContinueDet)
	{
	    if (this.tjjdContinueDet == null || !this.tjjdContinueDet.equals(tjjdContinueDet))
		{
			markModified();
		}
	    
	    this.tjjdContinueDet = tjjdContinueDet;
	}
	
	
}

