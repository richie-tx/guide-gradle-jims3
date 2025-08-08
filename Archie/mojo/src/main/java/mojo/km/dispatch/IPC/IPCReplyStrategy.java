//Source file: z:/java.vob/MessagingLayer/Event/ReplyStrategy.java

package mojo.km.dispatch.IPC;

import java.io.ObjectOutputStream;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.noop.NoReply;
import mojo.km.service.Socket.SocketService;

/**
 *    Responsible for sending a reply back to a requesting service.
 @author Eric A Amundson
 * @modelguid {4EAD47F3-2AA0-4AC9-ABD7-2D9497641181}
 */
public class IPCReplyStrategy extends EventManager {
	/** @modelguid {D1292E04-259D-4C12-A55D-03E0A07AC451} */
    public IPCReplyStrategy() { }

    /**
     * 	Generally not used.  Will send back reply  as event.
     @param event - IEvent to be sent to IPC context.
     * 	   @modelguid {32E5BDB2-3259-4440-A505-6296F3603292}
     */
    public void postEvent(IEvent event) {
        try {
            outStream = SocketService.getCurrent();
            //ZipOutputStream zipOStream = new ZipOutputStream(outStream);
            //zipOStream.putNextEntry(new ZipEntry("service"));
            outStream.writeObject(event);
            outStream.flush();
            outStream.reset();
            //zipOStream.closeEntry();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 	Post a byte array as an event
     * 	This is being used on byte arrays of an IEvent that has already been
     * 	serialized.
     *
     * 	@param service - the service side service name.
     * 	@param event - the server event byte array.
     * @modelguid {FFF7197B-5BF4-469A-95AF-7EEB98C24A10}
     */
    public void postEvent(String service, byte[] event) {
        try {
            outStream.write(event);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 	Strategy return no reply.
     @return IEvent sent back from IPC context.
	 * @modelguid {D6D7401A-591E-450C-90BC-8E10DD2B27CF}
     */
    public IEvent getReply() {
        return new NoReply();
    }

	/** @modelguid {7B6851EE-9D59-4CA8-871C-69C72A96FFE6} */
    ObjectOutputStream outStream = null;

    /*
    public void setStream( OutputStream aStream)
    {
        outStream = aStream;
    }
    */
}
