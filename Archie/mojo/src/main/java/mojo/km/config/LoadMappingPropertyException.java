package mojo.km.config;

/**
 * @author mchowdhury This class is used for catching loading xml file Exception.
 */
public class LoadMappingPropertyException extends RuntimeException
{

    /**
     * Constructor.
     */
    public LoadMappingPropertyException()
    {
        super();
    }

    public LoadMappingPropertyException(String message)
    {
        super(message);
    }

    /**
     * Constructor.
     * 
     * @param message
     */
    public LoadMappingPropertyException(String message, Exception e)
    {
        super(message, e);
    }
}
