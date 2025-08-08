package mojo.km.util;

import java.lang.reflect.*;
import java.util.*;

/**
 * This class encapsulate refection operations on a bean
 * 
 * <pre>
 * 
 *  
 *   
 *    
 *     
 *      
 *       
 *        
 *         
 *          
 *           
 *            Change History:
 *             Author          	Date        	Explanation
 *             Saleem Shafi		8/29/2001		Added the getXXXMethods() methods.
 *            
 *           
 *          
 *         
 *        
 *       
 *      
 *     
 *    
 *   
 *  
 * </pre>
 * 
 * @author Matt Pomroy
 */
public class Reflection
{
    private static Map accessorMap = new WeakHashMap();

    private static Map mutatorMap = new WeakHashMap();

    private static Map accessors = new WeakHashMap();

    private static Map mutators = new WeakHashMap();

    
    private static Map methods = Collections.synchronizedMap(new WeakHashMap());

    /**
     * Returns whether or not the given method can be defined as an accessor.
     * 
     * @modelguid {CA8E84DB-07E5-49A8-A0B6-6243605738F4}
     */
    protected static boolean isAccessor(Method aMethod)
    {
        if (aMethod == null)
            return false;
        String lMethodName = aMethod.getName();
        if (lMethodName.equals("getClass") || lMethodName.equals("hashCode"))
            return false;
        return Modifier.isPublic(aMethod.getModifiers()) && aMethod.getParameterTypes().length == 0
                && (lMethodName.startsWith("get") || lMethodName.startsWith("is") || lMethodName.startsWith("has"));
    }

    /**
     * Returns whether or not the given method can be defined as a mutator.
     * 
     * @modelguid {F2636D94-AA5E-4A5D-973E-03ACF3B7FFB6}
     */
    protected static boolean isMutator(Method aMethod)
    {
        if (aMethod == null)
            return false;
        String lMethodName = aMethod.getName();
        return Modifier.isPublic(aMethod.getModifiers()) && aMethod.getParameterTypes().length == 1
                && (lMethodName.startsWith("set") || lMethodName.startsWith("insert") || lMethodName.startsWith("add"));
    }
    
    protected static boolean compareParamTypes(Class aClass, Class anOtherClass)
    {
        if (aClass == null || anOtherClass == null)
        {
            return false;
        }

        String lClassName = aClass.getName();
        String lOtherClassName = anOtherClass.getName();

        if (lClassName.equals(lOtherClassName))
        {
            return true;
        }

        if (lClassName.equals("int"))
        {
            lClassName = "java.lang.Integer";
        }
        else if (lClassName.equals("double"))
        {
            lClassName = "java.lang.Double";
        }
        else if (lClassName.equals("boolean"))
        {
            lClassName = "java.lang.Boolean";
        }
        else if (lClassName.equals("char"))
        {
            lClassName = "java.lang.Character";
        }
        else if (lClassName.equals("short"))
        {
            lClassName = "java.lang.Short";
        }
        else if (lClassName.equals("float"))
        {
            lClassName = "java.lang.Float";
        }

        if (lOtherClassName.equals("int"))
        {
            lOtherClassName = "java.lang.Integer";
        }
        else if (lOtherClassName.equals("double"))
        {
            lOtherClassName = "java.lang.Double";
        }
        else if (lOtherClassName.equals("boolean"))
        {
            lOtherClassName = "java.lang.Boolean";
        }
        else if (lOtherClassName.equals("char"))
        {
            lOtherClassName = "java.lang.Character";
        }
        else if (lOtherClassName.equals("short"))
        {
            lOtherClassName = "java.lang.Short";
        }
        else if (lOtherClassName.equals("float"))
        {
            lOtherClassName = "java.lang.Float";
        }

        if (lClassName.equals(lOtherClassName))
        {
            return true;
        }

        // catch class inheritence
        if (aClass.isAssignableFrom(anOtherClass))
		{
            return true;
		}
			
        return false;
    }

    /**
     * Returns the property name in the given method.
     * 
     * @modelguid {447BE9FF-0CAF-45D6-A8E2-DA86E047700F}
     */
    public static String getPropertyName(Method aMethod)
    {
        if (aMethod == null)
            return null;
        String lMethodName = aMethod.getName();
        if (lMethodName.startsWith("add") || lMethodName.startsWith("get") || lMethodName.startsWith("has")
                || lMethodName.startsWith("set"))
        {
            return lMethodName.substring(3);
        }
        else if (lMethodName.startsWith("is"))
        {
            return lMethodName.substring(2);
        }
        else if (lMethodName.startsWith("insert"))
        {
            return lMethodName.substring(6);
        }
        else
            return null;
    }

    /**
     * Saves a accessor in the cache.
     * 
     * @modelguid {6F5C5E85-F269-4AFB-BE19-E0D6E9E87AB4}
     */
    private synchronized static void saveAccessor(Method aMethod)
    {
        if (aMethod == null)
            return;
        String lPropertyName = getPropertyName(aMethod);
        String lClassName = aMethod.getDeclaringClass().getName();
        String lSignature = new StringBuffer(lClassName).append(".get").append(lPropertyName).toString();
        methods.put(lSignature, aMethod);
    }

    /**
     * Saves an mutator in the cache.
     * 
     * @modelguid {9BCFCD32-B8BC-45BB-9A7A-C789F4B2753A}
     */
    private synchronized static void saveMutator(Method aMethod)
    {
        if (aMethod == null)
            return;
        String lPropertyName = getPropertyName(aMethod);
        String lClassName = aMethod.getDeclaringClass().getName();
        String lParamTypeName = aMethod.getParameterTypes()[0].getName();
        if (lParamTypeName.equals("int"))
            lParamTypeName = "java.lang.Integer";
        if (lParamTypeName.equals("double"))
            lParamTypeName = "java.lang.Double";
        if (lParamTypeName.equals("boolean"))
            lParamTypeName = "java.lang.Boolean";
        String lSignature = new StringBuffer(lClassName).append(".set").append(lPropertyName).append("(").append(
                lParamTypeName).append(")").toString();
        methods.put(lSignature, aMethod);
    }

    /**
     * Returns an iterator to a list of accessor methods for the given class.
     * 
     * @param aClass
     *            is the class to get the methods for
     * @return Iterator to the list of methods
     * @deprecated
     */
    public synchronized static Iterator getAccessorMethods(Class aClass)
    {
        List lGetters = (List) accessors.get(aClass);
        if (lGetters == null)
        {
            lGetters = new Vector();
            Method[] lMethods = aClass.getMethods();
            for (int i = 0; i < lMethods.length; i++)
            {
                Method lMethod = lMethods[i];
                if (isAccessor(lMethod))
                {
                    lGetters.add(lMethod);
                    saveAccessor(lMethod);
                }
            }
            accessors.put(aClass, lGetters);
        }
        return lGetters.iterator();
    }

    public synchronized static List getAccessors(Class aClass)
    {
        List getters = (List) accessorMap.get(aClass.getName());
        if (getters == null)
        {
            getters = new ArrayList();
            Method[] methods = aClass.getMethods();
            for (int i = 0; i < methods.length; i++)
            {
                Method method = methods[i];

                String methodName = method.getName();

                if (((methodName.equals("getClass") || methodName.equals("hashCode")) == false)
                        && Modifier.isPublic(method.getModifiers())
                        && method.getParameterTypes().length == 0
                        && ((methodName.startsWith("get") || methodName.startsWith("is") || methodName
                                .startsWith("has"))))
                {
                    getters.add(method);
                }
            }
            accessors.put(aClass.getName(), getters);
        }
        return getters;
    }

    /**
     * Returns an iterator to a list of accessor methods for the given class.
     * 
     * @param aClass
     *            is the class to get the methods for
     * @return Iterator to the list of methods
     * @deprecated
     */
    public synchronized static Iterator getMutatorMethods(Class aClass)
    {
        List lSetters = (List) mutators.get(aClass);
        if (lSetters == null)
        {
            lSetters = new Vector();
            Method[] lMethods = aClass.getMethods();
            for (int i = 0; i < lMethods.length; i++)
            {
                Method lMethod = lMethods[i];
                if (isMutator(lMethod))
                {
                    lSetters.add(lMethod);
                    saveMutator(lMethod);
                }
            }
            mutators.put(aClass, lSetters);
        }
        return lSetters.iterator();
    }

    public synchronized static List getMutators(Class aClass)
    {
        List setters = (List) mutators.get(aClass.getName());
        if (setters == null)
        {
            setters = new ArrayList();
            Method[] methods = aClass.getMethods();
            for (int i = 0; i < methods.length; i++)
            {
                Method method = methods[i];
                String methodName = method.getName();
                if (Modifier.isPublic(method.getModifiers())
                        && method.getParameterTypes().length == 1
                        && (methodName.startsWith("set") || methodName.startsWith("insert") || methodName
                                .startsWith("add")))
                {
                    setters.add(method);
                }
            }
            mutators.put(aClass.getName(), setters);
        }
        return setters;
    }

    /**
     * Returns the accessor method on the given class for the given property. Null if not found.
     * 
     * @modelguid {3F66C050-9B37-4487-9D1B-6FD9EDB03435}
     */
    public synchronized static Method getAccessorMethod(Class aClass, String aPropertyName)
    {
        if (aClass == null || aPropertyName == null)
            return null;
        String lClassName = aClass.getName();
        aPropertyName = new StringBuffer(aPropertyName.substring(0, 1).toUpperCase())
                .append(aPropertyName.substring(1)).toString();
        String lMethodSignature = new StringBuffer(lClassName).append(".get").append(aPropertyName).toString();
        Method lAccessor = (Method) methods.get(lMethodSignature);
        if (lAccessor == null)
        {
            for (Iterator i = getAccessorMethods(aClass); i.hasNext() && lAccessor == null;)
            {
                Method lCandidate = (Method) i.next();
                String lCandidateName = lCandidate.getName();
                String lPropertyName = getPropertyName(lCandidate);
                if (lPropertyName != null && lPropertyName.equalsIgnoreCase(aPropertyName))
                {
                    lAccessor = lCandidate;
                }
            }
            saveAccessor(lAccessor);
        }
        return lAccessor;
    }

    /** @modelguid {70746B41-BF35-4E58-AE66-1C4C1E048870} */
    public static Object invokeAccessorMethod(Object aBean, String aPropertyName)
    {
        if (aBean == null || aPropertyName == null)
            return null;
        return invokeAccessorMethod(aBean.getClass(), aBean, aPropertyName);
    }

    /**
     * Calls a getter method for a given property name on an object that adheres to the property
     * pattern. A getter for a property is assumed to begin with "get", "is" or "has".
     * 
     * @param aClass
     *            is the class of the bean
     * @param aBean
     *            is the target instance
     * @param aPropertyName
     *            is the name of the property
     * @param aValue
     *            is the arguement to the setter method
     * @modelguid {53DA8091-07AA-4551-B83F-CBCE151A289F}
     */
    public static Object invokeAccessorMethod(Class aClass, Object aBean, String aPropertyName)
    {
        if (aClass == null || aBean == null || aPropertyName == null)
            return null;
        Method lAccessor = getAccessorMethod(aClass, aPropertyName);
        if (lAccessor != null)
        {
            try
            {
                return lAccessor.invoke(aBean, new Object[0]);
            }
            catch (IllegalAccessException e)
            {
                System.out.println("Could not access " + lAccessor.getName() + ":");
                e.printStackTrace(System.out);
            }
            catch (InvocationTargetException e)
            {
                System.out.println("Could not invoke " + lAccessor.getName() + ":");
                e.printStackTrace(System.out);
            }
            //} else {
            //System.out.println("Couldn't find a accessor method for " +
            // aPropertyName + " on " + aClass.getName());
        }
        return null;
    }

    /**
     * Returns the mutator method on the given class for the given property. Null if not found.
     * 
     * @modelguid {A249D5C5-74B5-48EB-8520-F86AECB10587}
     */
    public synchronized static Method getMutatorMethod(Class aClass, String aPropertyName, Class aParamType)
    {
        if (aClass == null || aPropertyName == null || aParamType == null)
            return null;
        String lClassName = aClass.getName();
        String lParamTypeName = aParamType.getName();
        aPropertyName = new StringBuffer(aPropertyName.substring(0, 1).toUpperCase())
                .append(aPropertyName.substring(1)).toString();
        String lMethodSignature = new StringBuffer(lClassName).append(".set").append(aPropertyName).append("(").append(
                lParamTypeName).append(")").toString();
        Method lMutator = (Method) methods.get(lMethodSignature);
        if (lMutator == null)
        {
            for (Iterator i = getMutatorMethods(aClass); i.hasNext() && lMutator == null;)
            {
                Method lCandidate = (Method) i.next();
                String lCandidateName = lCandidate.getName();
                String lPropertyName = getPropertyName(lCandidate);
                //System.out.println( lPropertyName + " vs " + aPropertyName);
                if (lPropertyName != null && lPropertyName.equalsIgnoreCase(aPropertyName)
                        && compareParamTypes(lCandidate.getParameterTypes()[0], aParamType))
                {
                    // Egan if (lPropertyName != null &&
                    // lPropertyName.equalsIgnoreCase(aPropertyName)) {
                    //System.out.println( "Candidate = " + lCandidate );
                    lMutator = lCandidate;
                }
            }
            if (lMutator != null)
            {
                saveMutator(lMutator);
            }
        }
        return lMutator;
    }

    /** @modelguid {F5759144-58AD-440B-B633-44818907F8A6} */
    public synchronized static Iterator getMutatorMethods(Class aClass, String aPropertyName)
    {
        if (aClass == null || aPropertyName == null)
            return null;
        String lClassName = aClass.getName();
        aPropertyName = new StringBuffer(aPropertyName.substring(0, 1).toUpperCase())
                .append(aPropertyName.substring(1)).toString();
        String lMethodSignature = new StringBuffer(lClassName).append(".set").append(aPropertyName).toString();
        List lMutators = (List) methods.get(lMethodSignature);
        if (lMutators == null)
        {
            lMutators = new Vector();
            for (Iterator i = getMutatorMethods(aClass); i.hasNext();)
            {
                Method lCandidate = (Method) i.next();
                String lCandidateName = lCandidate.getName();
                String lPropertyName = getPropertyName(lCandidate);
                if (lPropertyName != null && lPropertyName.equalsIgnoreCase(aPropertyName))
                {
                    lMutators.add(lCandidate);
                }
            }
            methods.put(lMethodSignature, lMutators);
        }
        return lMutators.iterator();
    }

    /** @modelguid {DD064966-97E7-49E9-A068-62A974FB3E3A} */
    public static void invokeMutatorMethod(Object aBean, String aPropertyName, Object anArgument)
    {
        if (aBean == null || aPropertyName == null)
            return;
        invokeMutatorMethod(aBean.getClass(), aBean, aPropertyName, anArgument);
    }

    /**
     * Calls a setter method for a given property name on an object that adheres to the property
     * pattern
     * 
     * @param aClass
     *            is the class of the bean
     * @param aBean
     *            is the target instance
     * @param aPropertyName
     *            is the name of the property
     * @param aValue
     *            is the arguement to the setter method
     * @modelguid {776E7BCD-F674-4E0C-8AA1-06AF9BC14B65}
     */
    public static void invokeMutatorMethod(Class aClass, Object aBean, String aPropertyName, Object anArgument)
    {
        //System.out.println("Inside invokeMutatorMethod");
        if (aClass == null || aBean == null || aPropertyName == null)
            return;
        Object lValue = anArgument;
        //System.out.println( anArgument );
        Method lMutator = null;
        if (anArgument == null)
        {
            Iterator lMutators = getMutatorMethods(aClass, aPropertyName);
            if (lMutators.hasNext())
            {
                lMutator = (Method) lMutators.next();
            }
        }
        else
        {
            lMutator = getMutatorMethod(aClass, aPropertyName, lValue.getClass());
            if (lMutator == null)
            {
                if (lValue instanceof String)
                {
                    for (Iterator i = getMutatorMethods(aClass, aPropertyName); i.hasNext() && lMutator == null;)
                    {
                        Method lCandidate = (Method) i.next();
                        Class lParamType = lCandidate.getParameterTypes()[0];
                        Object lTempValue = convertValue(lParamType, (String) lValue);
                        if (lTempValue != null)
                        {
                            lValue = lTempValue;
                            lMutator = lCandidate;
                        }
                    }
                }
                else if (lValue instanceof mojo.km.messaging.IEvent)
                {
                    lMutator = getMutatorMethod(aClass, aPropertyName, mojo.km.messaging.IEvent.class);

                }
                else
                {
                    lMutator = getMutatorMethod(aClass, aPropertyName, String.class);
                    lValue = lValue.toString();
                }
            }
            else
            {
                if (aPropertyName.equals("OID"))
                {
                    //System.out.println("This is an OID");
                }
            }
        }
        if (lMutator != null)
        {
            try
            {
                lMutator.invoke(aBean, new Object[] { lValue });
            }
            catch (IllegalAccessException e)
            {
                System.err.println("Could not access " + lMutator.getName() + ":");
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                System.err.println("Could not invoke " + lMutator.getName() + ":");
                e.printStackTrace();
            }
            catch (Throwable e)
            {
                System.err.println("Could not invoke mutator for " + aPropertyName
                        + ".\n Please ensure that the argument type of the source matches that of the target.");
                e.printStackTrace();
            }
            // } else {
            //     System.out.println("Couldn't find a mutator method for " +
            // aPropertyName + " on " + aClass.getName());
        }
    }

    /** @modelguid {C6DC726C-574F-44CD-83D6-47282727D952} */
    public static Object invokeMethod(Method aMethod, Object anObject, Object aParameter)
    {
        if (aMethod == null)
            return null;
        try
        {
            if (aParameter == null)
            {
                return aMethod.invoke(anObject, new Object[0]);
            }
            else
            {
                return aMethod.invoke(anObject, new Object[] { aParameter });
            }
        }
        catch (IllegalAccessException e)
        {
            System.out.println("Could not access " + aMethod.getName() + ":");
            e.printStackTrace(System.out);
        }
        catch (InvocationTargetException e)
        {
            System.out.println("Could not invoke " + aMethod.getName() + ":");
            e.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * Converts a string value to an object of a given class
     * 
     * @param aClass
     *            is the type of object to create
     * @param anArgument
     *            is the string value used to create the object
     * @return Object is the newly created object
     * @modelguid {632D3F92-CE82-4095-9D00-4C744AD74D90}
     */
    private static Object convertValue(Class aClass, String anValue)
    {
        String lClassName = aClass.getName();
        lClassName = lClassName.substring(lClassName.lastIndexOf(".") + 1);
        try
        {
            if (lClassName.equalsIgnoreCase("string"))
            {
                return anValue;
            }
            else if (lClassName.equalsIgnoreCase("string;"))
            {
                // handle string arrays
                return new String[] { anValue };
            }
            else if (lClassName.equalsIgnoreCase("date"))
            {
                return java.sql.Date.valueOf(anValue);
            }
            else if (lClassName.equalsIgnoreCase("timestamp"))
            {
                return java.sql.Timestamp.valueOf(anValue);
            }
            else if (lClassName.equalsIgnoreCase("integer") || lClassName.equalsIgnoreCase("int"))
            {
                return new Integer(anValue);
            }
            else if (lClassName.equalsIgnoreCase("double"))
            {
                return new Double(anValue);
            }
            else if (lClassName.equalsIgnoreCase("float"))
            {
                return new Float(anValue);
            }
            else if (lClassName.equalsIgnoreCase("boolean"))
            {
                return new Boolean(anValue);
            }
            else if (lClassName.equalsIgnoreCase("long"))
            {
                return new Long(anValue);
            }
            else if (lClassName.equalsIgnoreCase("short"))
            {
                return new Short(anValue);
            }
            else if (lClassName.equalsIgnoreCase("char"))
            {
                return new Character(anValue.charAt(0));
            }
            else
            {
				// TODO throw an exception?
                //System.err.println("Unexpected type: " + lClassName);
            }
        }
        catch (Exception e)
        {
            System.err.println("Reflection error trying to convert <" + anValue + "> to a " + lClassName);
        }
        return null;
    }
}
