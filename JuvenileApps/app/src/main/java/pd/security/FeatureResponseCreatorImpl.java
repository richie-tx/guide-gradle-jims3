/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.security.reply.FeaturesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.naming.SecurityConstants;
import mojo.km.security.Feature;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseCreator;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FeatureResponseCreatorImpl{ /*extends ResponseCommonUtil implements ResponseCreator{*/
     
	/**
	 * @param features
	 */
	public void sendFeaturesResponseEvent(Iterator features)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		HashMap map = new HashMap();
	   /* while (features.hasNext())
	    {
		     Feature feature = (Feature) features.next();
		     if(feature != null){
			     FeaturesResponseEvent featureResponseEvent = (FeaturesResponseEvent) create(feature);	
					
				 if(featureResponseEvent.getParentId() != null){
					Feature pFeature = Feature.find(feature.getParentId());
					// send the parent feature if it has not been sent before
					if(!map.containsKey(pFeature.getOID().toString())){
						FeaturesResponseEvent parentResponseEvent = (FeaturesResponseEvent) create(pFeature);
						dispatch.postEvent(parentResponseEvent);
						map.put(pFeature.getOID().toString(),parentResponseEvent);
					}
					
					// send the child feature if it has not been sent before
					if(!map.containsKey(featureResponseEvent.getFeatureId())){
						dispatch.postEvent(featureResponseEvent);
						map.put(featureResponseEvent.getFeatureId(),featureResponseEvent);
					}
				 }
				 else{
					Collection childFeatureCollection = feature.getChildFeatures();
					if (childFeatureCollection != null)
					{
						SortedMap  childList = new TreeMap();
						Iterator childFeatureIt = childFeatureCollection.iterator();
						while (childFeatureIt.hasNext())
						{
							Feature childFeature = (Feature) childFeatureIt.next();
							FeaturesResponseEvent childResponseEvent = (FeaturesResponseEvent) create(childFeature);
							// send the child feature if it has not been sent before
							if(!map.containsKey(featureResponseEvent.getFeatureId())){
								map.put(childResponseEvent.getFeatureId(),childResponseEvent);
								dispatch.postEvent(childResponseEvent);
							}
						}
						// send the parent feature if it has not been sent before
						if(!map.containsKey(featureResponseEvent.getFeatureId())){
							dispatch.postEvent(featureResponseEvent);
							map.put(featureResponseEvent.getFeatureId(),featureResponseEvent);
						}
					}
				 }
	         }
	    }*/
	}

	/**
	  * @param object
	  * @returns object.
	  */
	/*public Object create(Object object)
	{
		Feature feature = (Feature) object;
		FeaturesResponseEvent responseEvent = new FeaturesResponseEvent();
		responseEvent.setTopic(SecurityConstants.ROLE_FEATURES_EVENT_TOPIC);
		responseEvent.setDescription(feature.getDescription());
		responseEvent.setFeatureName(feature.getName());
		responseEvent.setFeatureType(feature.getFeatureType());
		responseEvent.setParentId(feature.getParentId());
		responseEvent.setFeatureCategory(feature.getFeatureCategory());
		responseEvent.setFeatureId(feature.getOID().toString());
	    return responseEvent;
	}*/

	/* (non-Javadoc)
	 * @see pd.security.SecurityResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
