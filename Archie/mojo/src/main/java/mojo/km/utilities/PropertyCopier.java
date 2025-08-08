package mojo.km.utilities;

//import mojo.km.messaging.*;
//import mojo.km.persistence.*;
import java.lang.reflect.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.Map;
import java.util.Iterator;
import mojo.km.messaging.IEvent;

/**
 * This class will copy state from one object to another. Its uses include moving state from an Event to an Entity that is
 * required for persistence or from Entity to Event to facilitate presentation.
 * @author James McNabb
 * @modelguid {FC0EF34E-E08F-4984-8331-26D68BB90F71}
 */
public class PropertyCopier {
    /** PropertyCopier constructor comment. 
     * @modelguid {ED475CA5-E296-4EF5-9733-D2083CD87F8A}
     */
    public PropertyCopier() {
    }

    /**
     *  Builds an Object and sets its state for all properties shared with the inbound Map.
     * <P>Rules followed: <UL> <LI>Assumes that   <LI>The returned Object conforms to the JavaBean spec, ie. has public
     * default constructor and public accessors/mutators for all attributes. </UL>
     *     @param map - <CODE>Map</CODE> Contains the properties/values to be copied to the outbound object.
     *     @param className - <CODE>String</CODE> Fully-qualified classname of the object to create
     * and copy the properties to.
     *     @return <CODE>Object</CODE>.
     * @modelguid {743F4204-DE42-4AF5-8693-6B7599447A74}
     */
    public static Object copyProperties(Map map, String className) {
        // first check the entity parm
        if ((map == null) | (className == null)) {
            return null;
        }
        Class outClass = null;
        try {
            outClass = Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            System.out.println(className + " is not a valid class. " + cnfe.getMessage());
            return null;
        }
        // create the instance to be returned
        Object returnObj = null;
        try {
            returnObj = outClass.newInstance();
        } catch (Exception e) {
            System.out.println("error creating an instance of " + outClass.getName() + ". " + e.getMessage());
            return null;
        }
        copyProperties(map, returnObj);
		return returnObj;
    }

    /**
     *  Sets the state of all properties in the given Object shared with the inbound Map.
     * <P>Rules followed: <UL> <LI>Assumes that   <LI>The returned Object conforms to the JavaBean spec, ie. has public
     * default constructor and public accessors/mutators for all attributes. </UL>
     *     @param map - <CODE>Map</CODE> Contains the properties/values to be copied to the outbound object.
     *     @param inObj - <CODE>Object</CODE> The object to copy the properties to.
     * @modelguid {B9CFE534-56BE-45B5-B570-306DD6DA61FF}
     */
    public static void copyProperties(Map map, Object toObj) {
        // now parse out the individual properties
        Iterator e = map.keySet().iterator();//getParameterNames();
        Method[] methods = toObj.getClass().getMethods();
        while (e.hasNext()) {
            String property = (String)e.next();
            //debug System.out.println("property = "+property);
            Object value = map.get(property);
            //debug System.out.println("value = "+value);
            // get the setter method for the field
            Method setter = null;
            for (int i = 0; i < methods.length; i++) {
                String methodName = methods[i].getName();
                // only process public mutator methods
                if (Modifier.isPublic(methods[i].getModifiers())) {
                    if (methodName.equals("set" + property.substring(0, 1).toUpperCase() + property.substring(1))) {
                        setter = methods[i];
                        break;
                    }
                }
            } //end for
            if (setter != null) {
                // determine type of parameter necessary for setter
                Class type = setter.getParameterTypes() [0];
                String typeName = getClassName(type);
                // create the correct type of object to call setter
                Object[] parms = new Object[1];
                // catch any casting exceptions
                try {
                    if(value instanceof byte[]) {
						parms[0] = value;
                    } else if (typeName.equalsIgnoreCase("string")) {
                        parms[0] = value;
                    } else if (typeName.equalsIgnoreCase("date")) {
                        parms[0] = Date.valueOf((String)value);
                    } else if (typeName.equalsIgnoreCase("timestamp")) {
                        parms[0] = Timestamp.valueOf((String)value);
                    } else if (typeName.equalsIgnoreCase("integer") || typeName.equalsIgnoreCase("int")) {
                        parms[0] = new Integer((String)value);
                    } else if (typeName.equalsIgnoreCase("double")) {
                        parms[0] = new Double((String)value);
                    } else if (typeName.equalsIgnoreCase("float")) {
                        parms[0] = new Float((String)value);
                    } else if (typeName.equalsIgnoreCase("boolean")) {
                        parms[0] = new Boolean((String)value);
                    } else if (typeName.equalsIgnoreCase("long")) {
                        parms[0] = new Long((String)value);
                    } else if (typeName.equalsIgnoreCase("short")) {
                        parms[0] = new Short((String)value);
                    } else if (typeName.equalsIgnoreCase("char")) {
                        parms[0] = new Character(((String)value).charAt(0));
                    } else {
                        System.err.println("Unexpected type: " + typeName + " found in request.");
                        continue;
                    }
                } catch (Exception ce) {
                    System.err.println("Error trying to cast <" + value + "> as a " + typeName + ". Property " + property +
                        "'s value was not set.");
                        continue;
                }
                try {
                    setter.invoke(toObj, parms);
                } catch (IllegalAccessException iae1) {
                    System.err.println("Caught an IllegalAccessException exception: " + iae1.getMessage());
                } catch (InvocationTargetException ite) {
                    System.err.println("Caught an InvocationTargetException exception: " + ite.getMessage());
                } //end try
            } else {
                System.err.println("Method set" + property + " not found.");
            } //end if/else
        } //end while
    }

    /**
     * Method will set the values for all of the properties of the second
     * object to the values of the same properties that are in the first object. <P>Rules followed: <UL>
     * <LI>Assumes that both Objects follow the JavaBeans spec for those properties that are to be copied. </UL>
     * @param entity1 - <CODE>Object</CODE> The object that has the properties/values to be copied to the other object.
     * @param entity2 - <CODE>Object</CODE> The object to copy the properties to.
     * @modelguid {566822AC-1E0B-4394-850B-EA76A6AB94C8}
     */
    public static void copyProperties(Object entity1, Object entity2) {

		IEvent lEvent = null;
		if(entity1 instanceof IEvent) {
            lEvent = (IEvent)entity1;
        } else if(entity2 instanceof IEvent) {
            lEvent = (IEvent)entity2;
        }
		try {
	        if(lEvent != null) {
				mojo.km.properties.PropertyManager.copyProperties(entity1,entity2,lEvent.getTopic(),null);
	        } else {
				mojo.km.properties.PropertyManager.copyProperties(entity1,entity2,null,null);
	        }
        } catch(Throwable e) {
            e.printStackTrace();
            throw new RuntimeException("PropertyCopier failed.  "+e.getMessage());
        }

        /*
        if ((entity1 == null) | (entity2 == null)) {
            return;
        } //end if
        Class c1 = entity1.getClass();
        Class c2 = entity2.getClass();
        // copy process is based on mutator methods, so get all of the public
        // methods for the class to populate
        Method[] methods = c2.getMethods();
        Method[] methods2 = c1.getMethods();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            // only process public mutator methods
            if (!methodName.substring(0, 3).equals("set")) {
                continue;
            }

            //cArray[0] = methods[i].getReturnType();
            Method setter = methods[i];
            // get property name
            String propertyName = methodName.substring(3);
            // get the accessor for the property
            Class[] cArray = new Class[0];
            Method getter = null;
            try {
                getter = c1.getMethod("get" + propertyName, cArray);
            } catch (Throwable t) {
                //System.err.println(t.getMessage());
            }

            if (getter != null) {
	            // construct a call to get the value
	            Object[] args = new Object[0];
	            Object value = null;
                try {
                    value = getter.invoke(entity1, args);
                } catch (Throwable iae) {
                    //System.err.println("Caught an Exception exception: "+iae.getMessage());
                }
                // do not process null values
                if (value == null) {
                    continue;
                }

                Object[] parms = new Object[1];
                parms[0] = value;
                try {
                    setter.invoke(entity2, parms);
                } catch (IllegalAccessException iae1) {
                    System.err.println("Caught an IllegalAccessException exception: " + iae1.getMessage());
                } catch (InvocationTargetException ite) {
                    System.err.println("Caught an InvocationTargetException exception: " + ite.getMessage());
                } catch (IllegalArgumentException iae) {
                    System.err.println("Caught an IllegalArgumentException on property <"+propertyName+"> exception: " + iae.getMessage());
                } //end try
            } //end if
        } //end for
   */ }

    /**
     * Method will set the values for all of the properties of the second
     * event to the values of the same properties that are in the first event.
     * Uses copyProperties(Object, Object) to copy state.  Restores the topic and
     * server atttributes for the second event after copy.
     * @param event1 - <CODE>IEvent</CODE> The event that has the properties/values to be copied to the other object.
     * @param event2 - <CODE>IEvent</CODE> The event to copy the properties to.
     * @modelguid {0831BA2C-324B-4BB5-90F1-E202BC8CDACF}
     */
    public static void copyProperties(IEvent event1, IEvent event2) {
        String server = event2.getServer();
        String topic = event2.getTopic();
        copyProperties((Object)event1, (Object)event2);
        event2.setServer(server);
        event2.setTopic(topic);
    }

    /**
     * Checkss a method name based on the prepend value and the fieldName then checks
     * for the method's existience in the passed Class.
     * @param <CODE>String</CODE> Name of method to get.
     * @param <CODE>Class</CODE> Class that contains the method.
     * @param <CODE>Object</CODE> Used to determine parameter type for method if necessary.
     * @return <CODE>Method</CODE> The class method matching the inbound name or null if none found.
     * @modelguid {BE529CF5-A949-4F05-8102-2210EC62705E}
     */
    private static Method getClassMethod(String methodName, Class c, Object arg) {
        Class[] parms = null;
        if (arg == null) {
            parms = new Class[0];
        } else {
            parms = new Class[] { arg.getClass() };
        }
        //String methodName = prepend+fieldName;
        Method m = null;
        try {
            m = c.getMethod(methodName, parms);
        } catch (NoSuchMethodException nsme) {
            System.err.println("Method " + methodName + " not found in " + getClassName(c) + ".");
        }
        return m;
    }

    /**
     * determines whether the Class passed in is a user-defined type rather than a Java type.
     * @param <CODE>Class</CODE>.
     * @modelguid {5A8AAFBC-9ABA-4A5A-92B8-2D81B2D3A411}
     */
    private static boolean isUserDefinedType(Class type) {
        String typeName = getClassName(type);
        // put non user-defined types into the message
        if (typeName.equalsIgnoreCase("string") || typeName.equalsIgnoreCase("date") ||
            typeName.equalsIgnoreCase("timestamp") || typeName.equalsIgnoreCase("integer") ||
            typeName.equalsIgnoreCase("int") || typeName.equalsIgnoreCase("double") || typeName.equalsIgnoreCase("float") ||
            typeName.equalsIgnoreCase("char") || typeName.equalsIgnoreCase("long") || typeName.equalsIgnoreCase("short") ||
            typeName.equalsIgnoreCase("byte") || typeName.equalsIgnoreCase("boolean")) {
                return false;
        } else {
            return true;
        }
    }

    /**
     * returns the name of the passed Class after stripping off the package.
     * @param <CODE>Class</CODE>.
     * @return <CODE>String</CODE> Name (not including package) of class that was passed in as a parm.
     * @modelguid {102D4AE0-D34A-4419-AEF0-8ACC8A647F02}
     */
    private static String getClassName(Class c) {
        String className = c.getName();
        int index = className.lastIndexOf(".");
        return className.substring(index + 1);
    }

}
