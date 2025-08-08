package pd.appshell;

import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.persistence.ObjectNotFoundException;
import java.util.Collection;
import java.util.Iterator;

/** @stereotype Entity 
 * @modelguid {CE2EFF01-AFDA-4BDD-B054-91CD9381B42D}
 */
public class Favorites extends PersistentObject {
    /**
     * @associates <{Feature}>
     * @supplierCardinality 0..*
     * @clientCardinality 1
     * @label features
     * @modelguid {21C72776-A600-4D2C-9E2E-00460E6371B9}
     */
    private Collection features = new java.util.Vector();
	/** @modelguid {84A5BCD5-4010-4F01-A0D3-90CB43EF7438} */
    private String name;
	/** @modelguid {767F612D-042D-4515-AA76-CC13B37F11B0} */
    private String preferences;

	/** @modelguid {F0868930-4EC2-4E20-B93C-54F4611ABD70} */
    public Iterator getFeatures() {
        fetch();
        return features.iterator();
    }

	/** @modelguid {07239509-F5DB-497F-85DD-2AB856942DAA} */
    public void insertFeature(Feature feature) {
        //if(!featureExists(feature)){
	        markModified();
//	        feature.insertFavoritesList(this);
	        features.add(feature);
        //}
    }

	/** @modelguid {E3033F56-20DE-479B-B7FB-830ACCDA3300} */
    public void deleteFeature(Feature feature) {
        markModified();
        features.remove(feature);
    }

	/** @modelguid {92B39493-BF4D-4638-9958-694BEC02DB90} */
    public void clearFeatures() {
        markModified();
        features.clear();
    }

	/** @modelguid {ED00D60A-D2FB-4D16-A00C-1319C2C4A94B} */
    public Feature featureExists(Feature feature){
        Iterator i = getFeatures();
        while(i.hasNext()){
            Feature lFeature = (Feature)i.next();
        	if(lFeature.getName().equalsIgnoreCase(feature.getName())){
				return lFeature;
            }
        }
		return null;
    }
	/** @modelguid {62992A5F-56BD-47C6-8ACE-658D1693EDF0} */
    public String getName() {
        fetch();
        return name;
    }

	/** @modelguid {0396EAE4-A5FE-4E13-804B-08A9D73701D8} */
    public void setName(String name) {
        markModified();
        this.name = name;
    }

	/** @modelguid {8219A674-8A07-4B89-BF35-DF6B3F529B15} */
    public Preferences getPreferences() throws ObjectNotFoundException {
        if(preferences == null) {
            return null;
        }
        IHome lHome = new Home();
        return (Preferences)lHome.find(preferences, Preferences.class);
    }

	/** @modelguid {6414264F-DD86-41C0-8611-4EE085919096} */
    public void setPreferences(Preferences aPreference){
        if(aPreference == null) {
			this.preferences = null;
        } else {
            this.preferences = aPreference.getOID().toString();
        }
    }
}
