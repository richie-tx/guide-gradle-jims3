package mojo.messaging;

import mojo.km.messaging.RequestEvent;

import java.util.*;

/**
 * Responsible for housing data that will be sent to control command GetKeyValuesCommand
 *@author Design detail addin
 *@version 1.0
 */
public class KeyListEvent extends RequestEvent {
    public KeyListEvent() { }

    public Iterator getKeyValueListEvents(String aKeyName){
		Object lObject = null;
		Iterator lIterator = null;
    	lObject = keyValueListEvents.get(aKeyName);
        if (lObject != null) {
			lIterator = ((KeyValueListEvent)lObject).getKeyValueEvents();
		}
		return lIterator;

    }

	/**
	 * Method getKeyValueListEvents.
	 */
	public Iterator getKeyValueListEvents() {
		return keyValueListEvents.values().iterator();
	}

    public void insertKeyValueListEvent(String aKey, KeyValueListEvent keyValues){ keyValueListEvents.put(aKey, keyValues) ; }

    /**
     *@link aggregation
     *      @associates <{KeyValueListEvent}>
     * @supplierCardinality 0..*
     * @directed
     * @supplierRole keyValueListEvents
     */
    private HashMap keyValueListEvents = new HashMap();
    

}
