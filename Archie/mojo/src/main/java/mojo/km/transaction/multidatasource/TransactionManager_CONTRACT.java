package mojo.km.transaction.multidatasource;

class TransactionManager_CONTRACT extends TransactionManager
{
    protected boolean addRetriever_Precondition(String methodName, Object callbackObject,
            mojo.km.messaging.IEvent event, Class persistentClass)
    {
        boolean retVal = true;
        if (!(hasRetriever(callbackObject, methodName)))
        {
            System.err.println("addRetriever precondition: hasRetriever(callbackObject, methodName) :violated");
            retVal = false;
        }
        return retVal;
    }

    protected boolean addSaver_Precondition(String methodName, Object callbackObject, Class persistentClass)
    {
        boolean retVal = true;
        if (!(hasSaver(callbackObject, methodName)))
        {
            System.err.println("addSaver precondition: hasSaver(callbackObject, methodName) :violated");
            retVal = false;
        }
        return retVal;
    }
}
