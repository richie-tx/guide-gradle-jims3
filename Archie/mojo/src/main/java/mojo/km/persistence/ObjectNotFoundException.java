package mojo.km.persistence;

/**
 * Exception is thrown if any account constraint is not meet.
 * @version 1.0
 * @modelguid {86BF551F-D149-4D32-B090-F449E896689F}
 */
public class ObjectNotFoundException extends RuntimeException {
    /** Default constructor 
     * @modelguid {AA8ED92A-6A62-44D8-80FE-3BE137BE5492}
     */
    public ObjectNotFoundException() {
    }

    /**
     * Constructor with input message
     * @param msg error message.
     * @modelguid {84634D93-0AF3-482B-A1B7-169D70CB42EE}
     */
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
