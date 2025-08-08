/*
 * Created on Jul 25, 2005
 *
 */
package mojo.km.transaction.multidatasource;

public class TransactionException extends RuntimeException
{
    public TransactionException(String aMsg)
    {
        super(aMsg);
    }
    
    public TransactionException(String aMsg, Throwable aT)
    {
        super(aMsg, aT);
    }
}
