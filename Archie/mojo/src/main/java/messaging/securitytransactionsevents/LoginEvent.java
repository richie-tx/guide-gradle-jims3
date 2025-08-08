package messaging.securitytransactionsevents;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command LoginCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {07BF70E4-B5DA-463A-973E-D194FAC9910D}
 */
public class LoginEvent extends RequestEvent {
	/** @modelguid {7A11E23D-3C74-4C33-A18A-406DC7BB6B30} */
    private String username;
	/** @modelguid {06C79FCE-9ED4-4375-970B-FDD067B5725B} */
    private String password;

	/** @modelguid {BEAEE55B-774B-40B2-AE7E-743DBE178ACC} */
    public LoginEvent() { }

	/** @modelguid {D550CCE0-8E57-4C00-BFFD-037227E5B077} */
    public String getUsername() {
        return username;
    }

	/** @modelguid {1C67B7BC-246A-48D8-B4C2-40B281334244} */
    public void setUsername(String username) {
        this.username = username;
    }

	/** @modelguid {73621A63-DC70-42AD-B515-6ABD97373F94} */
    public String getPassword() {
        return password;
    }

	/** @modelguid {9994F10B-7C74-4962-96DE-6EB8B0F2C45E} */
    public void setPassword(String password) {
        this.password = password;
    }
}
