/*
 * Created on May 10, 2004
 *
 */
package mojo.km.transaction.multidatasource;

/**
 * @author eamundson
 * 
 */
public class RetrieveException extends TransactionException
{
    public RetrieveException(String aMsg, Throwable aT)
    {
        super(aMsg, aT);
    }
}
