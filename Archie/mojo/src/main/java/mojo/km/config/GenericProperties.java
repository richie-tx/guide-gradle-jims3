package mojo.km.config;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/** @modelguid {6EC7C7CC-9C6C-42EF-906A-E66CA28E7611} */
public class GenericProperties implements java.io.Serializable {
	/** @modelguid {105BEDB1-66B3-4DD8-AF02-FF07EFA82591} */
	private Map properties = new TreeMap();

	/** @modelguid {AC299D7D-F7E8-4DE8-ADA2-B8C1C9E3457E} */
    public String getProperty(String aKey) {
		if (!properties.containsKey( aKey )) {
			aKey = aKey.substring(0,1).toLowerCase() + aKey.substring(1);
        }
        return (String)properties.get(aKey);
    }

	/** @modelguid {29820CD3-B78C-4745-8D16-2F8063C71AAC} */
    public void setProperty(String aKey, String aValue) {
		properties.put(aKey, aValue);
    }

	/** @modelguid {DBF0496B-B6EF-4369-B55C-66E21A65FEAC} */
    public Iterator getProperties() {
    	return properties.keySet().iterator();
    }
    
	/** @modelguid {4FEF028C-F631-400B-A9C1-4352726085B7} */
    public boolean hasProperties() {
    	return !properties.isEmpty();
    }

	/** @modelguid {0355C865-5495-4F5E-8D10-5880B9E5AB4F} */
	public void clearProperties() {
    	properties.clear();
    }

	/** @modelguid {CE09A2BA-DFB7-47AB-90C3-34FED1BDA099} */
	public void removeProperty(String aKey) {
		properties.remove(aKey);
	}
	
	/** @modelguid {74809429-37E9-4F7A-93F7-676BC653219D} */
	public String toString() {
		return getProperty("name");
	}
}
