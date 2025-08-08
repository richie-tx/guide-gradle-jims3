package messaging.appshell;

import java.util.Collection;
import java.util.Iterator;
import mojo.km.messaging.ResponseEvent;
import mojo.naming.*;

/**
 * A list of FeatureEvents.
 *
 * @author Egan Royal
 * @modelguid {C581C135-5D0E-4B49-8B50-3BBEC9240C36}
 */
public class CCDisplayFeatureListEvent extends ResponseEvent {

    /**
     * @associates <{FeatureEvent}>
     * @supplierCardinality 0..*
     * @clientCardinality 1
     * @label features
     * @modelguid {D27F49F1-3627-452E-A52A-8D4E7904FCD7}
     */
    private Collection features = new java.util.Vector();
	/** @modelguid {E3F78C7A-80C1-4DBA-8C9E-A62362C963C4} */
     private static String TOPIC = "default";

	/** @modelguid {9EE871D9-8ADE-49E6-B369-8153FC83C36A} */
     public CCDisplayFeatureListEvent() {
        setServer(mojo.naming.ServerNames.SWING);
     	//setTopic(ServiceNames.FEATURELIST);
        setTopic(TOPIC);
     }

	/** @modelguid {3DB70A1D-2B4F-48BF-87A4-22EF02EF7C3D} */
    public Iterator getFeatures() {
        return features.iterator();
    }

	/** @modelguid {4683A9B9-EE15-4122-9F04-B7F8C6E5F4C1} */
    public void insertFeature(DisplayFeatureEvent aFeature) {
        features.add(aFeature);
    }
    
	// DPA: Added for HC stub implementation
	private CCFeaturesEvent ftEvt;
    
	/**
	 * @return
	 */
	public CCFeaturesEvent getFtEvt() {
		return ftEvt;
	}

	/**
	 * @param event
	 */
	public void setFtEvt(CCFeaturesEvent event) {
		ftEvt = event;
	}

    
}
