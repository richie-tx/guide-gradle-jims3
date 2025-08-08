//Source file: Z:/java.vob/BusinessServiceLayer/BusinessContext/BCLog.java

package mojo.km.logging.mojo;

import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Logging.LogEvent;

/**
 *Responsible for posting log events to a central logging service.  It implements the log
 *context interface.
 *
 *@author Eric A Amundson
 *@version 1.0
 * @modelguid {AFA83431-042D-40AE-9C86-B4A9613C9B46}
 */
public class IntegratedLog extends ContextLog {
	/** @modelguid {9EA7F958-0B48-40F1-BE5F-751BE4A64E7A} */
    public IntegratedLog() {
    }

    /**
     *	Writes a byte buffer as a new log event.
     *
     *	@param buffer - the byte stream that house info to be logged.
     *
     *    @modelguid {BC9BCD0B-A788-4030-AAD4-7E260C0041E4}
     */
	public void write(byte[] buffer) {
        LogEvent event = new LogEvent(buffer);
        mEventManager.postEvent(event);
        //TuxedoClient.getInstance().contextMessage(buffer);
    }

	/** @modelguid {24A0CD4A-2629-46D9-B099-BE320488566E} */
	IDispatch mEventManager = new mojo.km.dispatch.Logging.LoggingMessageStrategy();
}
