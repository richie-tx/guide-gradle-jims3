package pd.juvenilecase;

import java.util.Iterator;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 42A71B8C01B4
*/
public class TraitType extends PersistentObject
{
	private String riskPoints;
	private String name;
	private String parentTypeId;
	private TraitType parentType;
	private String parentName;
	private String status;
	
	/**
	* @roseuid 42A71B8C01B4
	*/
	public TraitType()
	{
	}
	
	static public Iterator findByType(IEvent event)
	{
		IHome home = new Home();
		Iterator responses = home.findAll(event, TraitType.class);
		return responses;
	}
	
	/**
	* Finds TraitRisk by an event
	* @return Iterator of TRaitRisk
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator responses = home.findAll(event, TraitType.class);
		return responses;
	}
	
	static public TraitType findByAttributeName(String attributeName, String attributeValue)
	{
			IHome home = new Home();
			return (TraitType)home.find(attributeName, attributeValue, TraitType.class);
	}
		
		
	static public TraitType find(String traitTypeId)
	{
		TraitType traitType = null;
		IHome home = new Home();
		Object traitTypeObj = home.find(traitTypeId, TraitType.class);
		if (traitTypeObj instanceof TraitType)
		{
			traitType = (TraitType) traitTypeObj;
		}
		return traitType;
	}

	/**
	* @return 
	*/
	public String getRiskPoints()
	{
		fetch();
		return riskPoints;
	}
	/**
	* @return 
	*/
	public String getName()
	{
		fetch();
		return name;
	}
	/**
	* @param i
	*/
	public void setRiskPoints(String i)
	{
		if (this.riskPoints == null || !this.riskPoints.equals(i))
		{
			markModified();
		}
		riskPoints = i;
	}
	/**
	* @param string
	*/
	public void setName(String string)
	{
		if (this.name == null || !this.name.equals(string))
		{
			markModified();
		}
		name = string;
	}

	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return super.getOID().toString();
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		super.setOID(string);
	}

	/**
	 * @return
	 */
	public String getParentTypeId()
	{
		return parentTypeId;
	}

	/**
	 * @param string
	 */
	public void setParentTypeId(String string)
	{
		if (this.parentTypeId == null || ! this.parentTypeId.equals(string))
		{
			markModified();
		}
		parentType = null;
		parentTypeId = string;
	}

	/**
	* Initialize class relationship to class pd.juvenilecase.TraitType
	*/
	private void initParentType()
	{
		if (parentType == null)
		{
			try
			{
				parentType =
					(TraitType) new mojo
						.km
						.persistence
						.Reference(getParentTypeId(), TraitType.class)
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.TraitType
	*/
	public TraitType getParentType()
	{
		initParentType();
		return parentType;
	}
	
	
	/**
	 * @return
	 */
	public TraitTypeResponseEvent getValueObject()
	{
		TraitTypeResponseEvent event = new TraitTypeResponseEvent();
		event.setTraitTypeId(this.getTraitTypeId());
		event.setTraitName(this.getName());
		event.setParentTraitId(this.getParentTypeId());
		event.setRiskPoints(this.getRiskPoints());
		event.setStatus(this.getStatus());
		return event;
	}

	public static Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(TraitType.class);
		return iter;
	}
	
	/**
	 * @return
	 */
	public static Iterator findAll(String attrName, String attrVal)
	{
		IHome home = new Home();
		return home.findAll(attrName,attrVal,TraitType.class);
	}

	/**
	 * @return
	 */
	public String getParentName()
	{
		fetch();
		return parentName;
	}

	/**
	 * @param string
	 */
	public void setParentName(String string)
	{
		if (this.parentName == null || !this.parentName.equals(string))
		{
			markModified();
		}
		this.parentName = string;
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
}
