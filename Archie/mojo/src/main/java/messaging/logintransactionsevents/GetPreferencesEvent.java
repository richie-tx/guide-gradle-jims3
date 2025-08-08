package messaging.logintransactionsevents;

import mojo.km.messaging.RequestEvent;
/**
 * Responsible for housing data that will be sent to control command GetPreferencesCommand
 *@author Noblestar
 *@version 1.0
 * @modelguid {79FA783F-9EF2-458F-904C-BF83B98098FD}
 */
public class GetPreferencesEvent extends RequestEvent {
	/** @modelguid {45637036-1A34-4D07-9981-D6FADCE8E7E3} */
    private int personOID;

	/** @modelguid {204B3746-BB9D-45EC-8144-4DFBCDAACCB9} */
    public GetPreferencesEvent(){
    }

	/** @modelguid {F6878850-0D2F-480D-9BCB-4C9886D0CE3A} */
    public GetPreferencesEvent( int personOID )
    {
        this.personOID = personOID;

    }

	/** @modelguid {F5B8F9AF-E8DF-4176-8DCB-F6DF9481F16B} */
    public void setPersonOID(int personOID){
		this.personOID = personOID;
    }

	/** @modelguid {2F22D815-0306-477A-8B92-B801C7A31FB5} */
    public int getPersonOID(){
		return this.personOID;
    }
}
