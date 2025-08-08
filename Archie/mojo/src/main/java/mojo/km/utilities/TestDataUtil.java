package mojo.km.utilities;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import mojo.km.config.EventQueryProperties;
import mojo.km.config.FieldMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.TestClassificationRangeProperties;
import mojo.km.config.TestDataClassificationProperties;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.PersistentObject;

/**
 * Utility to provide test data.
 */
public class TestDataUtil
{
    private static final int MIN_ALPHA_SIZE = 1;

    private static final int MAX_ALPHA_SIZE = 50;

    private static final int MIN_INT = -10000000;

    private static final int MAX_INT = 10000000;

    static private Random randomVal = new Random();

    static private final String ALPHA_CHARS = "ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";

    static private final String TEXT_CHARS = "ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";

    static private final String NUMBER_CHARS = "0123456789";

    public static String createAlpha(int size)
    {
        String retVal = "";
        while (retVal.length() < size)
        {
            int val = randomVal.nextInt();
            if (val < 0)
            {
                val = val * -1;
            }
            while (val > 0)
            {
                int mod = val % 63;
                val = val / 63;
                retVal = retVal + ALPHA_CHARS.substring(mod, mod + 1);
            }
        }
        return retVal.substring(0, size);
    }

    public static String createDate()
    {
        return "" + (new Date());
    }

    public static Date createDate(Date beginDate, Date endDate)
    {
        long beginTime = beginDate.getTime();
        long endTime = endDate.getTime();

        long difference = (long) endTime - (long) beginTime;

        long timeDifference = (long) TestDataUtil.createInt((int) difference);
        Date date = new Date();
        date.setTime(timeDifference);
        return date;
    }
    
    public static String serializeEvent(IEvent anEvent)
    {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(anEvent.getClass().getName());
        
        Iterator m = Reflection.getAccessorMethods(anEvent.getClass());
        
        while(m.hasNext())
        {
            Method method = (Method) m.next();
            buffer.append(method.getName());
            buffer.append(",");
            try
            {
                Object obj = method.invoke(anEvent, (Object[]) null);
                if(obj != null)
                {
                    buffer.append(obj);
                }
                else
                {
                    buffer.append("null");
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(m.hasNext())
            {
                buffer.append("||");
            }
        }
        
        
        return buffer.toString();
    }

    public static Date createDate(Date beginDate, int difference, String increment)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);

        if ("year".equalsIgnoreCase(increment))
        {
            cal.add(Calendar.YEAR, difference);
        }
        else if ("month".equalsIgnoreCase(increment))
        {
            cal.add(Calendar.MONTH, difference);
        }
        else if ("day".equalsIgnoreCase(increment))
        {
            cal.add(Calendar.DATE, difference);
        }
        else if ("hour".equalsIgnoreCase(increment))
        {
            cal.add(Calendar.HOUR, difference);
        }
        else if ("minute".equalsIgnoreCase(increment))
        {
            cal.add(Calendar.MINUTE, difference);
        }
        else if ("second".equalsIgnoreCase(increment))
        {
            cal.add(Calendar.SECOND, difference);
        }

        Date date = cal.getTime();
        if (difference > 0)
        {
            date = TestDataUtil.createDate(beginDate, date);
        }
        else if (difference == 0)
        {
            date = beginDate;
        }
        else
        {
            date = TestDataUtil.createDate(date, beginDate);
        }
        return date;
    }

    public static String createFloat()
    {
        return "" + randomVal.nextFloat();
    }

    public static String createIntString()
    {
        return String.valueOf(randomVal.nextInt());
    }

    public static String createText(int size)
    {
        String retVal = "";
        while (retVal.length() < size)
        {
            int val = randomVal.nextInt();
            if (val < 0)
            {
                val = val * -1;
            }
            while (val > 0)
            {
                int mod = val % 53;
                val = val / 53;
                retVal = retVal + TEXT_CHARS.substring(mod, mod + 1);
            }
        }
        return retVal.substring(0, size);
    }

    public static void fill(PersistentObject object, EventQueryProperties eqProps)
    {
        Iterator i = eqProps.getFieldsIterator();
        while (i.hasNext())
        {
            FieldMappingProperties fProps = (FieldMappingProperties) i.next();
            Object value = TestDataUtil.createByClassification(fProps.getClassification());
            if (value != null)
            {
                Reflection.invokeMutatorMethod(object, fProps.getPropertyName(), value);
            }
            else
            {
                TestDataUtil.createByType(fProps.getPropertyType());
            }
        }
    }

    public static void fill(Object aBean)
    {
        Method[] methods = aBean.getClass().getDeclaredMethods();
        Iterator i = getModifiers(methods);
        while (i.hasNext())
        {
            Method seedMethod = (Method) i.next();
            Class[] parmTypes = seedMethod.getParameterTypes();
            Object[] parms = new Object[parmTypes.length];
            TestDataUtil.fillParms(parmTypes, parms);

            try
            {
                seedMethod.invoke(aBean, parms);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void fillParms(Class[] theParmTypes, Object[] theParms)
    {
        for (int i = 0; i < theParmTypes.length; i++)
        {
            Class type = theParmTypes[i];
            if (type.equals(String.class))
            {
                theParms[i] = TestDataUtil.createByType("String");
            }
            else if (type.equals(Integer.class))
            {
                theParms[i] = TestDataUtil.createByType("int");
            }
            else if (type.equals(Date.class))
            {
                theParms[i] = TestDataUtil.createByType("Date");
            }
            else
            {
                theParms[i] = null;
            }
        }
    }

    // TODO Support levels of inheritance
    private static Iterator getModifiers(Method[] theMethods)
    {
        List seedMethods = new ArrayList();
        for (int i = 0; i < theMethods.length; i++)
        {
            String methodName = theMethods[i].getName();
            if (methodName.startsWith("set") || methodName.startsWith("add"))
            {
                seedMethods.add(theMethods[i]);
            }
        }
        return seedMethods.iterator();
    }

    /**
     * @param class1
     * @return
     */
    private static Object createByClass(Class aClass)
    {
        Object data = null;
        if (String.class.getName().equalsIgnoreCase(aClass.getName()))
        {
            data = createByType("String");
        }
        else if (Integer.class.getName().equalsIgnoreCase(aClass.getName()))
        {
            data = createByType("int");
        }
        else if (Date.class.getName().equalsIgnoreCase(aClass.getName()))
        {
            data = createByType("Date");
        }
        return data;
    }

    /**
     * @param string
     */
    private static Object createByType(String type)
    {
        Object data = null;
        if ("String".equalsIgnoreCase(type))
        {
            data = TestDataUtil.createAlphaNumeric(MIN_ALPHA_SIZE, MAX_ALPHA_SIZE);
        }
        else if ("int".equalsIgnoreCase(type))
        {
            int intValue = TestDataUtil.createInt(MIN_INT, MAX_INT);
            data = new Integer(intValue);
        }
        else if ("Date".equalsIgnoreCase(type))
        {
            data = new Date();
        }
        return data;
    }

    static private boolean trackValue(Map dataMap, String property, Object value)
    {
        boolean unique = false;
        Set propertySet = (Set) dataMap.get(property);
        if (propertySet == null)
        {
            propertySet = new HashSet();
            dataMap.put(property, propertySet);
            unique = true;
        }
        else
        {
            unique = !propertySet.contains(value);
            if (unique == true)
            {
                propertySet.add(value);
            }
        }
        return unique;
    }

    public static Object createByClassification(String classification)
    {
        Object data = null;
        if (classification != null)
        {
            classification = classification.trim();
            TestDataClassificationProperties dProps = MojoProperties.getInstance().getTestDataClassification(
                    classification);
            if (dProps != null)
            {
                List records = dProps.getRecords();
                TestClassificationRangeProperties rProps = dProps.getGenerateProperties();
                if (records != null && records.size() > 0)
                {
                    // Pick a random index from the records enumeration
                    int index = TestDataUtil.createInt(records.size());
                    data = records.get(index);
                }
            }
        }

        return data;
    }

    private static int getSize(String testString)
    {
        int size = 32;
        if (testString.indexOf("(") > -1)
        {
            String values = testString.substring(testString.indexOf("(") + 1);
            values = values.substring(0, values.indexOf(")"));
            try
            {
                size = Integer.parseInt(values);
            } catch (Exception e)
            {
            }
        }
        return size;
    }

    private static String selectValue(String enums)
    {
        String retVal = "";
        String values = enums.substring(enums.indexOf("(") + 1);
        values = values.substring(0, values.indexOf(")"));
        StringTokenizer sTok = new StringTokenizer(values, ",");
        int count = sTok.countTokens();
        int val = randomVal.nextInt();
        if (val < 0)
        {
            val = val * -1;
        }
        int remainder = val % count;
        for (int i = 0; i < remainder + 1; i++)
        {
            retVal = sTok.nextToken();
        }
        return retVal;
    }

    public static String createNumber(int size)
    {
        String retVal = "";
        while (retVal.length() < size)
        {
            int val = randomVal.nextInt();
            if (val < 0)
            {
                val = val * -1;
            }
            while (val > 0)
            {
                int mod = val % 10;
                val = val / 10;
                retVal = retVal + NUMBER_CHARS.substring(mod, mod + 1);
            }
        }
        return retVal.substring(0, size);
    }

    static public void populateEvent(IEvent anEvent)
    {
        Iterator methods = Reflection.getMutatorMethods(anEvent.getClass());
        while (methods.hasNext())
        {
            Method aMethod = (Method) methods.next();
            Class[] parmTypes = aMethod.getParameterTypes();
            if (parmTypes.length == 0 || Reflection.getPropertyName(aMethod).equals("Topic")
                    || Reflection.getPropertyName(aMethod).equals("WorkflowID")
                    || Reflection.getPropertyName(aMethod).equals("UserID")
                    || Reflection.getPropertyName(aMethod).equals("Server"))
            {
                continue;
            }
            Class parmType = parmTypes[0];
            String lClassName = parmType.getName();
            lClassName = lClassName.substring(lClassName.lastIndexOf(".") + 1);
            try
            {
                if (lClassName.equalsIgnoreCase("string"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, createAlpha(10));
                }
                else if (lClassName.equalsIgnoreCase("string;"))
                { // handle string arrays
                    Reflection.invokeMethod(aMethod, anEvent, new String[]
                    { createAlpha(10) });
                }
                else if (lClassName.equalsIgnoreCase("date"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, new Date());
                }
                else if (lClassName.equalsIgnoreCase("timestamp"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, new Date());
                }
                else if (lClassName.equalsIgnoreCase("integer") || lClassName.equalsIgnoreCase("int"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, new Integer(randomVal.nextInt()));
                }
                else if (lClassName.equalsIgnoreCase("double"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, new Double(randomVal.nextDouble()));
                }
                else if (lClassName.equalsIgnoreCase("float"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, new Float(randomVal.nextFloat()));
                }
                else if (lClassName.equalsIgnoreCase("boolean"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, new Boolean(randomVal.nextBoolean()));
                }
                else if (lClassName.equalsIgnoreCase("long"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, new Long(randomVal.nextLong()));
                }
                else if (lClassName.equalsIgnoreCase("short"))
                {
                    Reflection.invokeMethod(aMethod, anEvent, new Short((short) randomVal.nextInt()));
                }
                else if (lClassName.equalsIgnoreCase("char"))
                {
                    char[] chars = createAlpha(1).toCharArray();
                    Reflection.invokeMethod(aMethod, anEvent, chars);
                }
                else
                { //System.err.println("Unexpected type: " + lClassName);
                }
            } catch (Exception e)
            {
                System.err.println("Error populating  " + anEvent);
            }
        }
    }

    public static char createChar()
    {
        int pos = createInt(1, ALPHA_CHARS.length());
        char ch = ALPHA_CHARS.charAt(pos);
        return ch;
    }

    public static String createAlphaNumeric(int sizeMin, int sizeMax)
    {
        String value = null;
        if (sizeMin > 0)
        {
            int size = createInt(sizeMin, sizeMax);
            char[] chars = new char[size];
            for (int i = 0; i < size; i++)
            {
                chars[i] = createChar();
            }
            value = String.valueOf(chars);
        }

        return value;
    }

    public static String createInt(String min, String max)
    {
        int minInt = Integer.parseInt(min);
        int maxInt = Integer.parseInt(max);
        return String.valueOf(TestDataUtil.createInt(minInt, maxInt));
    }

    public static int createInt(int min, int max)
    {
        int diff = max - min;
        int value = randomVal.nextInt(diff);
        value += min;
        return value;
    }

    public static int createInt(int max)
    {
        return randomVal.nextInt(max);
    }
}
