package mojo.km.transaction.multidatasource;

public class SaveException extends TransactionException
{
    public SaveException(String aMsg)
    {
        super(aMsg);
    }
    
    public SaveException(String aMsg, Throwable aT)
    {
        super(aMsg, aT);
    }

}
