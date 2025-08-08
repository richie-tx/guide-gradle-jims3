package mojo.km.caching.generic;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.AllQueryEvent;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.OIDEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.Reflection;

/**
 * @author jmcnabb
 * 
 * Helper for converting those types handled by cache to and from a String
 * representaion.
 */
public class StringConversionUtility
{
    /*
     * Returns a string representation of the properties of the event. @return
     * String representation of the properties of the event
     */
    public static String eventToString(IEvent event)
    {
        StringBuilder retVal;        
     
        if(event instanceof OIDEvent)
        {
            retVal = new StringBuilder();
            oidEventToString(retVal, (OIDEvent) event);
        }
        else if(event instanceof AttributeEvent)
        {
            retVal = new StringBuilder(100);                      
            attributeEventToString(retVal, (AttributeEvent) event);
        }
        else if(event instanceof AllQueryEvent)
        {
            retVal = new StringBuilder(100);                       
            allQueryEventToString(retVal, (AllQueryEvent) event);
        }
        else
        {
            retVal = new StringBuilder(200);
            genericEventToString(retVal, event);
        }
        
        return retVal.toString();
    }

    private static void oidEventToString(StringBuilder retVal, OIDEvent anEvent)
    {        
        retVal.append(anEvent.getOID());
    }
    
    private static void attributeEventToString(StringBuilder retVal, AttributeEvent anEvent)
    {
        retVal.append(anEvent.getClass().getName());
        retVal.append(anEvent.getAttributeName());        
        retVal.append(anEvent.getAttributeValue());
    }
    
    public static void allQueryEventToString(StringBuilder retVal, AllQueryEvent anEvent)
    {
        retVal.append(anEvent.getClass().getName());
    }
    
    private static void genericEventToString(StringBuilder retVal, IEvent anEvent)
    {
        for (Iterator i = Reflection.getAccessorMethods(anEvent.getClass()); i.hasNext();)
        {
            Method aMethod = (Method) i.next();
            String propName = Reflection.getPropertyName(aMethod);
            
                retVal.append(propName);
                retVal.append("=");
                if ("SessionID".equals(propName))
                {
                    retVal.append("null");
                }
                else
                {
                    retVal.append("" + Reflection.invokeAccessorMethod(anEvent, propName));
                }
                if (i.hasNext())
                {
                    retVal.append(",");
                }            
        }
    }

    public static IEvent stringToEvent(String eventContent)
    {
        IEvent event = null;
        String className = eventContent.substring(0, eventContent.indexOf("::"));
        if (className != null)
        {
            StringTokenizer tok = new StringTokenizer(eventContent.substring(eventContent.indexOf("::") + 2,
                    eventContent.length()), ",");
            Map propertyValues = new HashMap();
            while (tok.hasMoreTokens())
            {
                String pair = tok.nextToken();
                String name = pair.substring(0, pair.indexOf("="));
                String value = pair.substring(pair.indexOf("=") + 1, pair.length());
                propertyValues.put(name, value);
            }
            try
            {
                Class pType = Class.forName(className);
                event = (IEvent) pType.newInstance();
                for (Iterator i = Reflection.getMutatorMethods(pType); i.hasNext();)
                {
                    Method aMethod = (Method) i.next();
                    String propName = Reflection.getPropertyName(aMethod);
                    String propValue = (String) propertyValues.get(propName);
                    if (propValue != null && !propValue.equals("null"))
                    {
                        Reflection.invokeMutatorMethod(event, propName, propValue);
                    }
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return event;
    }

    /**
     * Returns a string representation of the properties of the given
     * PersistentObject.
     * 
     * @return String representation of the properties of the PersistentObject
     */
    public static String persistentObjectToString(PersistentObject object)
    {
        StringBuilder retVal = new StringBuilder(object.getClass().getName());
        retVal.append("::");

        for (Iterator i = Reflection.getAccessorMethods(object.getClass()); i.hasNext();)
        {
            Method aMethod = (Method) i.next();
            String propName = Reflection.getPropertyName(aMethod);
            retVal.append(propName);
            retVal.append("=");
            retVal.append(Reflection.invokeAccessorMethod(object, propName).toString());
            if (i.hasNext())
            {
                retVal.append(",");
            }
        }
        return retVal.toString();
    }

    /**
     * Returns a PersistentObject constructed from the serialized String
     * content.
     */
    public static PersistentObject stringToPersistentObject(String objectContent)
    {
        PersistentObject pObj = null;
        String className = objectContent.substring(0, objectContent.indexOf("::"));
        if (className != null)
        {
            StringTokenizer tok = new StringTokenizer(objectContent.substring(objectContent.indexOf("::") + 2,
                    objectContent.length()), ",");
            Map propertyValues = new HashMap();
            while (tok.hasMoreTokens())
            {
                String pair = tok.nextToken();
                String name = pair.substring(0, pair.indexOf("="));
                String value = pair.substring(pair.indexOf("=") + 1, pair.length());
                propertyValues.put(name, value);
            }
            try
            {
                Class pType = Class.forName(className);
                pObj = (PersistentObject) pType.newInstance();
                // this will prevent the markModified behavior from being
                // triggered
                pObj.setModified(true);
                for (Iterator i = Reflection.getMutatorMethods(pType); i.hasNext();)
                {
                    Method aMethod = (Method) i.next();
                    String propName = Reflection.getPropertyName(aMethod);
                    String propValue = (String) propertyValues.get(propName);
                    if (propValue != null && !propValue.equals("null"))
                    {
                        Reflection.invokeMutatorMethod(pObj, propName, propValue);
                    }
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            pObj.setNotNew();
        }

        return pObj;
    }

}
