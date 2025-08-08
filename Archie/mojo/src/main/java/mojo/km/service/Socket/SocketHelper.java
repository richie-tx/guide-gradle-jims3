package mojo.km.service.Socket;

import mojo.km.dispatch.*;
import java.net.*;
import java.io.*;
import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;

/**
 * Defines an interface to be implemented by any class that requires a socket based service
 * @author Eric A Amundson
 * @modelguid {DD58F1DC-A74E-4DCC-AA59-70B5D9582B05}
 */
public class SocketHelper extends Thread {
    /**
     *    Constructor
     *    @param socket - the network socket being managed.
     *    @param cManager - the mojo context manager to post event to
     * @modelguid {AA7BAD07-FC20-4EC1-A940-0C4D5BE4AAB9}
     */
    public SocketHelper(Socket socket, ContextManager cManager) {
        this.socket = socket;
        context = cManager;
    }

    /**
    @return Socket the is being used by Helper
	 * @modelguid {B06D5987-50B0-46A5-B16F-24E367A02B07}
    */
    public Socket getSocket() {
        return socket;
    }

    /**
    @return the ObjectOutputStream being used by Context
	 * @modelguid {A75D645D-38CC-49FE-B800-6E00FBF779DF}
    */
    public ObjectOutputStream getOutput() {
        return oStream;
    }

	/** @modelguid {53E7A2E4-5AAF-46EF-B67B-73C689631DA6} */
    public void run() {
        try {
            byte[] buffer = new byte[1000000];
            while (true) {
                //if (iStream.available() > 0) {
                InputStream iSStream = socket.getInputStream();
                iStream = new DataInputStream(iSStream);
                int size = iStream.readInt();
                if (size > 0) {
                    iStream.readFully(buffer, 0, size);
                    ByteArrayInputStream aInStream = new ByteArrayInputStream(buffer);
                    ObjectInputStream aOStream = new ObjectInputStream(aInStream);
                    String hashKey = aOStream.readUTF();
                    IEvent event = (IEvent)aOStream.readObject();
                    context.postEvent(event);
                } else {
                    socket.close();
                    break;
                }
                //iSStream.reset();
                //iSStream.close();
                //System.out.println("CurrentPriceService read object.");
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException z) {
            z.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	/** @modelguid {C8EA3F1A-C686-4345-8D93-052FEC75A59C} */
    protected Socket socket = null;
	/** @modelguid {7AF034E4-BD76-4C4C-A1B4-96285D8B5BAB} */
    protected ContextManager context = null;
	/** @modelguid {ABE15220-F11B-442A-860B-D96E0ABD97A3} */
    protected DataInputStream iStream = null;
	/** @modelguid {28E02CA7-F67D-441F-B789-6E6AFCA13959} */
    protected ObjectOutputStream oStream = null;
}
