package mojo.messaging.securityadministrationinterfaceevents;

import mojo.km.messaging.ResponseEvent;

/**
 * Responsible for housing data that will be returned to boundry command MessageCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {6B718609-8803-4C87-A024-8DEB7F750B3D}
 */
public class MessageEvent extends ResponseEvent {
	/** @modelguid {56C070BA-3EB1-476C-823B-E72F6D5C6C97} */
    private String message;

	/** @modelguid {7408FAE0-7C75-47CA-A0A1-A9B0C8F39236} */
    public MessageEvent() { }

	/** @modelguid {B8C0D9F9-414C-46E2-809D-421A9F00E40B} */
    public String getMessage() {
        return message;
    }

	/** @modelguid {1D7A0310-5E41-456E-BD14-1D3AC3F7D660} */
    public void setMessage(String message) {
        this.message = message;
    }
}
