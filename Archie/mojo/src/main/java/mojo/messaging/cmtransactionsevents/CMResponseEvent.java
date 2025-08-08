package mojo.messaging.cmtransactionsevents;

import java.util.ResourceBundle;
import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command ...
 * @modelguid {41ADDE74-9BF6-4888-9CF4-86C2E3A7AFDC}
 */
public class CMResponseEvent extends RequestEvent {
	/** @modelguid {5702A073-B296-4F3F-8577-BA5657AEC54A} */
    private String replyTopic;
	/** @modelguid {2A6F1208-BAF3-4699-9DD8-B308B98583E3} */
	private ResourceBundle resourceBundle;

	/** @modelguid {64A22003-41D5-4E08-ABAA-3D59A5DD3476} */
    public CMResponseEvent() {
    }

	/** @modelguid {2A21F564-656F-4094-BD91-4AB4F8928FAC} */
    public String getReplyTopic() {
        return replyTopic;
    }

	/** @modelguid {1B65BAFA-9B1D-489E-A640-F8E327860C1A} */
    public void setReplyTopic(String replyTopic) {
        this.replyTopic = replyTopic;
    }

	/** @modelguid {EF535896-EF9A-490B-921F-BA3351A782A4} */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
    }

	/** @modelguid {E08BE6A7-2637-4A08-A0E1-D2E420822FED} */
    public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
    }



}
