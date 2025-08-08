package mojo.km.context.multidatasource;

/**
 * @author Jim Fisher
 *
 */
public class ConnectionException extends RuntimeException
{
    public ConnectionException(Throwable e)
    {
        super(e);
    }
    
    public ConnectionException(String msg)
    {
        super(msg);
    }

    public ConnectionException(String msg, Throwable e)
    {
        super(msg, e);
    }
}
