package mojo.messaging;

import mojo.km.messaging.RequestEvent;


/**
 * Implementation used for events that have OIDs.<pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 *  paf </pre>
 */
public class OIDEvent extends RequestEvent implements IOIDEvent {
    private String OID;

    public String getOID(){
        return this.OID;
    }


    public void setOID(String OID){
        this.OID = OID;
    }
}
