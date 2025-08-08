package pd.appshell;

import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.persistence.ObjectNotFoundException;

/** @stereotype Entity 
 * @modelguid {868F653D-479E-4FD4-9982-434983D07E52}
 */
public class Parameter extends PersistentObject {
	/** @modelguid {A83F614D-C8B6-4107-9CFC-4DD193AD6472} */
    private String name;
	/** @modelguid {20DF77EF-AF0D-49F9-A3A2-B1DEFCF49AA6} */
    private String value;
	/** @modelguid {484D51BE-305F-46AB-911A-2210131A500A} */
    private String feature;

	/** @modelguid {4A312469-E84F-4823-B106-083FD2E79332} */
    public String getName() {
        fetch();
        return name;
    }

	/** @modelguid {AF5A9586-82E9-4CA8-A0D7-A90B5BF09199} */
    public void setName(String name) {
        markModified();
        this.name = name;
    }

	/** @modelguid {D3E41938-AA5C-450D-A047-569D2FA0BF0D} */
    public String getValue() {
        fetch();
        return value;
    }

	/** @modelguid {E629704F-88F5-4FCD-A3C4-B8F356E81293} */
    public void setValue(String value) {
        markModified();
        this.value = value;
    }

	/** @modelguid {912E609C-D82A-4862-A1BF-28F3A6257EC1} */
    public Feature getFeature() throws ObjectNotFoundException {
        fetch();
        if(feature == null) {
            return null;
        }
        IHome lHome = new Home();
        return (Feature)lHome.find(feature, Feature.class);
    }

	/** @modelguid {7125E982-6416-4485-A0BF-15DBEF302F94} */
    public void setFeature(Feature aFeature) {
        markModified();
		if(aFeature == null) {
        	this.feature = null;
        } else {
            this.feature = aFeature.getOID().toString();
        }
    }
}
