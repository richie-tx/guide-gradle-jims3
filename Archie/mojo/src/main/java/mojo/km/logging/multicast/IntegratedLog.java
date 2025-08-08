//Source file: Z:/java.vob/BusinessServiceLayer/BusinessContext/BCLog.java

package mojo.km.logging.multicast;

import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Logging.LogEvent;

/**
 *Responsible for posting log events to a central logging service.  It implements the log
 *context interface.
 *
 *@author Eric A Amundson
 *@version 1.0
 * @modelguid {CC10419F-4CF3-4CA4-86DC-B4302F3CFE0A}
 */
public class IntegratedLog extends ContextLog {
	/** @modelguid {31AD0D19-FA96-47E5-AE4C-407E1758D934} */
    public IntegratedLog() {
    }

    /**
     *	Writes a byte buffer as a new log event.
     *
     *	@param buffer - the byte stream that house info to be logged.
     *
     *    @modelguid {D0C09218-D283-4644-8D9E-6F3052D75989}
     */
	public void write(byte[] buffer) {
        LogEvent event = new LogEvent(buffer);
        mEventManager.postEvent(event);
        //TuxedoClient.getInstance().contextMessage(buffer);
    }

	/** @modelguid {97C4E50D-ADE7-4F64-9744-CD209049C45A} */
	IDispatch mEventManager = new mojo.km.dispatch.Logging.LoggingMessageStrategy();
}
