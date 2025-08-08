package mojo.messaging.logintransactionsevents;

import mojo.km.messaging.RequestEvent;
import messaging.appshell.*;
/**
 * Responsible for housing data that will be sent to control command SavePreferencesCommand
 *@author Noblestar
 *@version 1.0
 * @modelguid {E37427DD-69E5-409C-B0EB-3BD3E1676808}
 */
public class SavePreferencesEvent extends RequestEvent {
	/** @modelguid {56FCAF9D-0D7F-4347-9053-113BABA8D066} */
    private PreferencesEvent preferencesEvent;

	/** @modelguid {6945906E-0F61-4399-9AC4-CC63E69D4D65} */
    public SavePreferencesEvent()
        { }

	/** @modelguid {E883363F-71A0-4C91-803A-3F9920287FE9} */
    public void setPreferencesEvent(PreferencesEvent preferencesEvent){
    	this.preferencesEvent = preferencesEvent;
    }

	/** @modelguid {E9AD060E-2DB3-4FA2-83F9-222E13E0A761} */
    public PreferencesEvent getPreferencesEvent(){
		return this.preferencesEvent;
    }
}
