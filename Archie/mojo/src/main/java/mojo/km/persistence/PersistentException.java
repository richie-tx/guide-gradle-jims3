package mojo.km.persistence;

import mojo.km.messaging.IEvent;

/** Marks the object as modified so the Persistor knows to store the object. 
 * @modelguid {3879DA4A-D587-45D5-8F8D-C1F316679218}
 */
public class PersistentException extends RuntimeException implements IEvent {
	/** @modelguid {E5606D23-8400-4117-90B7-619175731368} */
    static final long serialVersionUID = -6570778481728870205L;

	/** @modelguid {646E6AA9-7B93-4671-831F-F7405B794E58} */
    public PersistentException() {
    }

    
	/** @modelguid {36235B80-D00E-4F58-9F65-62CAF2A53382} */
    public PersistentException(String text) {
        super(text);
    }

    
	/** @modelguid {B0803F91-F7E9-4F72-BC87-8092A77AF8C1} */
    public String hashKey() {
        return new String("Account." + mService + "::" + this.getClass().getName());
    }

    
	/** @modelguid {7109DD74-25E2-4383-8CC2-99524AC74A65} */
    public String getTopic() {
        return mService;
    }

    
	/** @modelguid {8C4AAE54-8475-41E7-82FA-669943EC9FA5} */
    public void setTopic(String service) {
        mService = service;
    }
    
    /**
     * Return the server context name. 
     * @modelguid {75F7AA76-2316-41D5-A81A-6A947F3C39EB}
     */
    public String getServer() {
        return mServer;
    }
    
    /**
     * Set the value of the server context name. 
     * @modelguid {191D3B05-2D2C-468E-91B4-8CC4D998B709}
     */
    public void setServer(String name) {
        mServer = name;
    }

	/** @modelguid {CB14F326-7BDF-4341-A930-CDD1B31F86CA} */
    private String oid = "";
	/** @modelguid {5C4CF6BB-FA47-42E8-BD9F-9BA01FC33842} */
    private String mService;
	/** @modelguid {DC4E8757-94B8-4412-9CBC-7A15C92C5801} */
    private String mServer = "";
}
