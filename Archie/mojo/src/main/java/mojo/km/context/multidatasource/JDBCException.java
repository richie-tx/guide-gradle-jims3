/*
 * Created on Sep 30, 2004
 *
 */
package mojo.km.context.multidatasource;

/**
 * @author eamundson
 * 
 */
public class JDBCException extends RuntimeException
{
    public JDBCException(Throwable e)
    {
        super(e);
    }
    
    public JDBCException(String msg)
    {
        super(msg);
    }

    public JDBCException(String msg, Throwable e)
    {
        super(msg, e);
    }
}
