/*
 * Created on Jul 6, 2004
 *
 */
package mojo.km.context.multidatasource;

/**
 * @author Rcooper
 *
 */
public class M204Exception extends RuntimeException {
	
    public M204Exception(Throwable e)
    {
        super(e);
    }
    
    public M204Exception(String msg)
    {
        super(msg);
    }

    public M204Exception(String msg, Throwable e)
    {
        super(msg, e);
    }

}
