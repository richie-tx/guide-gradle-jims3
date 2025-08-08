package mojo.messaging.logintransactionsevents;

import mojo.km.messaging.RequestEvent;
import mojo.naming.*;
/**
 * Responsible for housing data that will be sent to control command GetNavigationTreeCommand
 *@author Noblestar
 *@version 1.0
 * @modelguid {7B69B326-FE0B-4F7E-83BD-4D18E3D0D2BF}
 */
public class GetFeatureListEvent extends RequestEvent {

	/** @modelguid {730733CC-FD1E-4E34-8164-67D60FDAD6DF} */
    private int personOID;

	/** @modelguid {8182B0D7-8C39-4CCE-BDD3-FC0D59AE2A40} */
    public GetFeatureListEvent() {
    }

	/** @modelguid {55BF6A6F-C604-4D9F-84FF-7FF4E08677BB} */
    public int getPersonOID(){ return personOID; }

	/** @modelguid {0B694BCC-A6C2-4371-99FF-C073D1871D2D} */
    public void setPersonOID(int personOID){ this.personOID = personOID; }



}
