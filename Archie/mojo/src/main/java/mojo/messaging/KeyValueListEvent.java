package mojo.messaging;

import mojo.km.messaging.RequestEvent;
import java.util.*;

/**
 * Responsible for housing data that will be sent to control command GetKeyValuesCommand
 *@author Design detail addin
 *@version 1.0
 */
public class KeyValueListEvent extends RequestEvent {
    public KeyValueListEvent() { }

    public String getKeyName(){ return keyName; }
	public Iterator getKeyValueEvents() { return keyValueEvents.iterator(); }

    public void setKeyName(String keyName){ this.keyName = keyName; }
    public void insertKeyValueEvent(KeyValueEvent value) { this.keyValueEvents.add(value); }

    private String keyName;

    /** @associates <{KeyValueEvent}>
     * @link aggregation
     * @supplierCardinality 0..*
     * @supplierRole keyValueEvents*/
    private Vector keyValueEvents = new Vector();
}
