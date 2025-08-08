package mojo.km.service.Socket;

import java.net.*;
import java.io.*;
import mojo.km.service.IService;

/**
 * Defines an interface to be implemented by any class that requires a socket
 based service
 @author Eric A Amundson
 * @modelguid {A7619100-2A58-4654-A340-E766391FAC9C}
 */
public abstract class SocketService implements IService {
    
    /**
 *     Start processing requests.
 * @modelguid {E0BDBB17-9156-4224-A561-3BBEDB8346A6}
 */
    public abstract void start();
    
    /**
 *     Stop processing requests.
 * @modelguid {8C25CB33-B063-4F3E-8A43-1E78FEDF54D2}
 */
    public abstract void stop();
    
    /**
 *     Returns the current socket for reply messages to use to access a object output stream.
 *     
 *     @return object output stream.
 * @modelguid {221E490D-AEF4-4B50-A033-9B40A98504C5}
 */
    public static ObjectOutputStream getCurrent() {
        SocketHelper helper = (SocketHelper) Thread.currentThread();
        return helper.getOutput();
    }
    
    
};