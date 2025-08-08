/* SequenceGenerator.java
 */

package mojo.km.utilities;

import mojo.km.properties.IResourceService;
import java.lang.reflect.InvocationTargetException;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** @modelguid {A3EDA427-EEEC-455C-B8D7-AB10D0F98503} */
abstract public class SequenceGenerator
{
    /**
     * Value that a SequenceGenerator factory class might use to decide
     * what derived class to create.
     * @modelguid {CF118DF8-D7CE-4751-AE2D-A0E81EE81042}
     */
    final public    static String CLASS_NAME_PROP  = "seqGenClass";
	/** @modelguid {667A914F-DD47-4302-BF55-24E20BF6693E} */
    final public    static String RESET_DAILY_PROP = "resetDaily";
	/** @modelguid {191DD866-65B4-4DD4-B2FA-DDD9ACA9FDEE} */
    final public    static String RESET_VALUE_PROP = "resetValue";
	/** @modelguid {94331C55-50E5-4677-B393-89006B3E696B} */
    final protected static String EMPTY_STR        = "";

	/** @modelguid {E30B6ADD-40BD-4994-93FB-7EF500A213BD} */
    private static Class[] parameterTypes;
    static {
        parameterTypes = new Class[1];
        parameterTypes[0] = IResourceService.class;
    }

    //================================================================================
    //                         PUBLIC METHODS
    //================================================================================
    
	/** @modelguid {DF8A4407-2C43-4B2A-8EFF-8F4381336942} */
    public SequenceGenerator( IResourceService resourceService )
    {
        this.resourceService = resourceService;
        initFromResources();
    }

    /** Factory method 
     * @modelguid {3E6B4DF9-8ED0-4A99-8FEF-862EACD467C1}
     */
    public static SequenceGenerator create( IResourceService resourceService )
        throws ClassNotFoundException, NoSuchMethodException, 
               InstantiationException, IllegalAccessException,
               InvocationTargetException
    {
        String seqGenClassName = 
            resourceService.getResourceString(SequenceGenerator.CLASS_NAME_PROP);
        Class clazz = Class.forName(seqGenClassName);
        java.lang.reflect.Constructor ctor = clazz.getConstructor(parameterTypes);
        Object[] args = new Object[1];
        args[0] = resourceService ;
        return (SequenceGenerator)ctor.newInstance(args);
    }

    //------------------------- Base class interfaces -------------------//

    /**
     * Manipulate a file, query a database, do whatever it takes to return the
     * next number within a unique sequence. It is the responsibility of the
     * derived class to change its lastModified date.
     * @modelguid {A982966F-A80E-494C-8BE4-10EB4AAD943D}
     */
    synchronized public long nextNum()
    {
        if(debug) System.out.println( "SequenceGenerator, calling nextNumSpecific()" );
        // Be sure to ask the question below BEFORE asking for the next number!
        if(debug) System.out.println("[nextNum] resetDaily = " + resetDaily );
        if( resetDaily ) {
            Calendar lastModDate = getLastModified();
            Calendar today = Calendar.getInstance();
            if(debug) {
                System.out.println("[nextNum] lastModDate = " + lastModDate.getTime() );
                System.out.println("[nextNum] today       = " +       today.getTime() );
            }
            if( (today.get(Calendar.DAY_OF_YEAR) > lastModDate.get(Calendar.DAY_OF_YEAR)) ||
                (today.get(Calendar.YEAR       ) > lastModDate.get(Calendar.YEAR       )) )
            {
                if(debug) System.out.println("[nextNum] calling reset with resetValue = " + resetValue);
                reset(resetValue);
            }
        }
        long ret = nextNumSpecific();
        if(debug) System.out.println("[nextNum] returning ret = " + ret );
        return ret;
    }

    /**
     * Reset the sequence.
     * @modelguid {352D3B7B-54D1-45C6-B86E-DFF1DFD13EE0}
     */
    synchronized public void reset(long val)
    {
        if(debug) System.out.println( "SequenceGenerator, calling resetSpecific()" );
        resetSpecific(val);
        lastModified = getLastModified();
    }

    /** turn on/off debugging 
     * @modelguid {A10ABDE6-8031-41A1-BD6C-A144F16740DF}
     */
    public void setDebug(boolean val)
    {
        debug = val;
    }

    //================================================================================
    //                      PROTECTED ATTRIBUTES and METHODS
    //================================================================================

	/** @modelguid {8DD6DDEE-36C1-4FE8-AA99-3BE01F640694} */
    protected boolean          debug           = false;
	/** @modelguid {1217A479-B52F-4B1D-8E95-212A96CA944D} */
    protected IResourceService resourceService = null;
	/** @modelguid {40047176-6DB9-453F-9C29-9762C838EE32} */
    protected boolean          resetDaily      = false;
	/** @modelguid {E7E2A926-9980-4433-AA74-904020712FBA} */
    protected long             resetValue      = 1;
	/** @modelguid {B08460DC-70C0-4D50-B489-97259E5FFAC2} */
    protected Calendar         lastModified    = null;

	/** @modelguid {728A5C9F-BEFB-4850-915C-1461C30165A6} */
    private void initFromResources()
    {
        if( resourceService != null ) {
            String temp = resourceService.getResourceString(RESET_DAILY_PROP);
            if( (temp != null) && "true".equals(temp) ) {
                resetDaily = true;
            }
            temp = resourceService.getResourceString(RESET_VALUE_PROP);
            if( temp != null ) {
                try {
                    resetValue = Long.parseLong(temp);
                }
                catch( NumberFormatException nfe )
                {}
            }
        }
    }

	/** @modelguid {FE98EBCA-630A-4EC6-87C9-F15E2F4D5694} */
    protected long nextNumSpecific()
    {
        return -1;
    }

	/** @modelguid {64C0D48F-9A4E-4173-ACA2-CA5346323175} */
    protected void resetSpecific(long value)
    {
    }

    // ASSUMPTION: derived class knows how and when to
    //             set its last modified date.
	/** @modelguid {C3378D92-FE52-46F9-8C0B-B48B26D85CFC} */
    protected Calendar getLastModified()
    {
        return Calendar.getInstance();  // dummy code
    }
}
