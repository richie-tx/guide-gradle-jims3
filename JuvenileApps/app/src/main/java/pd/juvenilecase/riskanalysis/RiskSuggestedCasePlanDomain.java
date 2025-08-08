
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
public class RiskSuggestedCasePlanDomain extends PersistentObject 
{

	private String name;
	private int numericRule;
	
	public RiskSuggestedCasePlanDomain() 
	{
		
	}
	
	public static RiskSuggestedCasePlanDomain find(String riskSuggestedCasePlanDomain_ID) 
	{
		IHome home = new Home();
		return (RiskSuggestedCasePlanDomain) home.find(riskSuggestedCasePlanDomain_ID, RiskSuggestedCasePlanDomain.class);		
	}
	
	public Iterator findAll() 
	{
		IHome home = new Home();
		return home.findAll(RiskSuggestedCasePlanDomain.class);
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, RiskSuggestedCasePlanDomain.class);
	}
	
	public static Iterator findAllByAttributeName(String attributeName, String attributeValue) 
    {
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, RiskSuggestedCasePlanDomain.class);
    }

	public String getName() 
	{
		fetch();
		return name;
	}

	public void setName(String string) 
	{
		if (this.name == null || !this.name.equals(string)) {
			markModified();
		}
		name = string;
	}

	public void setNumericRule(int i) {
		if (this.numericRule != i) 
		{
			markModified();
		}
		numericRule = i;
	}

	public int getNumericRule() {
		fetch();
		return numericRule;
	}
}