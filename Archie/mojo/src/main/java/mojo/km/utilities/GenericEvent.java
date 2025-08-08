package mojo.km.utilities;

/** @modelguid {935626F8-DE6E-42DB-9258-25B70E33A82F} */
public final class GenericEvent
{
	/** @modelguid {B60A3BC3-03AB-4A9E-8C60-8ED79A2F68A1} */
    private final Object mSource;
	/** @modelguid {8E32A8CC-5D55-4DDB-9D09-7734F7A195F3} */
    private final Object mObject;
    
    /** Constructor 
     * @modelguid {982F12FD-E41F-4A02-81B9-5CF2736EC1D6}
     */
    public GenericEvent( Object source )
    {
        mSource = source;
        mObject = null;
    }
    
    /** Constructor 
     * @modelguid {4FF1F3BC-138F-4230-BFF6-CFF200DAA56D}
     */
    public GenericEvent( Object source, Object object )
    {
        mSource = source;
        mObject = object;
    }
    
    /** Get event source 
     * @modelguid {8D921223-7458-4760-869D-DC0F747F144D}
     */
    public Object getSource()
    {
        return mSource;
    }
    
    /** Get event object 
     * @modelguid {2CB7351E-1E62-4320-9501-48A7443D14BA}
     */
    public Object getObject()
    {
        return mObject;
    }
}