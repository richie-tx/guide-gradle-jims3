
package pd.juvenilecase.riskanalysis;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/** 
 * @author palcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class RiskResultGroup extends PersistentObject 
{

	private String description;
	private String displayDescription;
	
	public RiskResultGroup() 
	{
		
	}
	
	public static RiskResultGroup find(String riskResultsGrp_ID) 
	{
		IHome home = new Home();
		return (RiskResultGroup) home.find(riskResultsGrp_ID, RiskResultGroup.class);		
	}
	
	public static Iterator findAll() 
	{
		IHome home = new Home();
		return home.findAll(RiskResultGroup.class);
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, RiskResultGroup.class);
	}
	
	public static Iterator findAllByAttributeName(String attributeName, String attributeValue) 
    {
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, RiskResultGroup.class);
    }

	public String getDescription() 
	{
		fetch();
		return description;
	}

	public void setDescription(String string) 
	{
		if (this.description == null || !this.description.equals(string)) {
			markModified();
		}
		description = string;
	}

	public void setDisplayDescription(String displayDescription) {
		if (this.displayDescription == null || !this.displayDescription.equals(displayDescription)) {
			markModified();
		}
		this.displayDescription = displayDescription;
	}

	public String getDisplayDescription() {
		fetch();
		return displayDescription;
	}
}