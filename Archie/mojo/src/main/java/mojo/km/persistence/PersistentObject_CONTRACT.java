package mojo.km.persistence;

class PersistentObject_CONTRACT extends PersistentObject
{
    protected boolean _Invariant()
    {
        boolean retVal = true;
        if (!(mojo.km.context.ContextManager.isSet()))
        {
            System.err.println("PersistentObject invariant: mojo.km.context.ContextManager.isSet() :violated");
            retVal = false;
        }
        return retVal;
    }
}
