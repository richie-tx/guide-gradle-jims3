package mojo.km.utilities;

/** @modelguid {0A1C3BAE-B365-40F8-81E3-9075CA56B76C} */
public class GenericEventMulticaster implements GenericListener
{
	/** @modelguid {E28AC39E-D58D-42FF-8440-89C67963AE4F} */
    private final GenericListener mFirst;
	/** our attributes 
	 * @modelguid {B0D0B4E8-408C-44A7-A4F6-D65C8CE33F37}
	 */
    private final GenericListener mSecond;
    
    /** construct a multicaster 
     * @modelguid {D69FE434-47F3-42A1-BD63-B079FB48B562}
     */
    private GenericEventMulticaster( GenericListener first, GenericListener second )
    {
        if ((first == null) || (second == null))
        {
            throw new IllegalArgumentException("Null passed to GenericEventMulticaster");
        }
        
        mFirst  = first;
        mSecond = second;
    }
    
    /** add a listener to a multicaster 
     * @modelguid {9D170856-A990-43EE-9ED6-3403A6529A22}
     */
    static public GenericListener add( GenericListener first, GenericListener second )
    {
        GenericListener retval;
        
        if (first == null)
        {
            retval = second;
        }
        else if (second == null)
        {
            retval = first;
        }
        else
        {
            retval = new GenericEventMulticaster(first,second);
        }
        
        return retval;
    }
    
    /** remove a listener from a multicaster 
     * @modelguid {DD6BBE82-DE5A-4861-8D46-7079B8543ADB}
     */
    static public GenericListener remove( GenericListener chain, GenericListener old )
    {
        GenericListener retval;
        
        //***** if chain is empty, or we're removing the entire chain
        if ((chain == null) || (chain == old))
        {
            retval = null;
        }
        
        //***** if chain is another multicaster
        else if (chain instanceof GenericEventMulticaster)
        {
            GenericEventMulticaster multi = (GenericEventMulticaster) chain;
            
            if (multi.mFirst == old)
            {
                retval = multi.mSecond;
            }
            else if (multi.mSecond == old)
            {
                retval = multi.mFirst;
            }
            else
            {
                GenericListener newFirst  = remove(multi.mFirst,  old);
                GenericListener newSecond = remove(multi.mSecond, old);
                
                retval = new GenericEventMulticaster( newFirst, newSecond );
            }
        }
        
        //***** otherwise, there's nothing to remove
        else
        {
            retval = chain;
        }
        
        return retval;
    }
    
    /** fire our events  AWTEventMulticaster 
     * @modelguid {F4462DD8-1F92-4267-BEE5-AA577CC25B71}
     */
    public void onGenericEvent(GenericEvent e)
    {
        mFirst.onGenericEvent(e);
        mSecond.onGenericEvent(e);
    }
}