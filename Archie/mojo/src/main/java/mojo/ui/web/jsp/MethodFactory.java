package mojo.ui.web.jsp;

import javax.servlet.jsp.PageContext;
import javax.servlet.http.HttpSession;
import java.lang.reflect.*;
import java.util.*;

/**
 * This class is responsible for resolving the path values used in the MSP tags.
 * It uses the <pre>pageContext</pre>, the responses sent by the executed commands and the path String to construct a value.
 * The path string takes the shape of "name.name.name.name", and so on until the
 * desired event is reached.  The actual value of "name" will be the attribute on
 * the event with the corresponding "get" method.  The list of attributes will
 * start with a name that references an event on the page.  These names are set
 * by various MSP tags.  The important thing to remember when trying to
 * understand how to access an object is that the object are accessed via
 * methods, not the attribute.  So, if there is a getUsername() method on an
 * event, "username" will be the name in the <pre>object</pre> value no matter
 * what attribute is actually reference by the getUsername() method.
 * For example, if we have an event on a page that has a reference named "root"
 * and that event has a getUser() method that returns an event that, in turn, has
 * a getFirstName() method, the way to retrieve that information would be: <pre> <msp:get object="root.user.firstName" />
 * </pre> Notice that the attribute may start with a lower-case letter, but the rest of the attribute is case-sensitive.
 * The path string can also be used to retrieve data from the session.  In that
 * case, the initial "name" is simply "session" and the remaining part of the
 * path is used as the key to the attribute map in the session. Example: <pre>
 * <msp:get object="session.JSESSIONID" /> </pre>
 * @author Saleem Shafi
 * @date 6/4/2002
 * @modelguid {D473ADBD-68A2-4F8C-9E33-B391468220E7}
 */
public class MethodFactory {
    /**
     * Returns the object represented by the given path with respective to the
     * current PageContext.  If an error occurs while trying to access the data
     * in the manner specified, an null value is returned.
     * @return Object
     * @param PageContext the context of the page being rendered
     * @param String the page string to the desired value
     * @modelguid {887CA207-30C1-422E-93B9-F1AA5B38E67E}
     */
    static public Object invokeGetter(PageContext context, String aPathString) {
        if (context == null || aPathString == null) return null;
        Object lElement = context.getRequest().getAttribute("Event");
        if (lElement == null) return null;

        // get the first element in the path string
        String[] lPath = splitPath(aPathString);
        String lAttributeName = lPath[0];
        String lTheRest = lPath[1];
        // if we're access the session
        if (lAttributeName.startsWith("session.")) {
            return context.getSession().getAttribute(lTheRest);
            // otherwise, we're looking for a value on the context
        } else {
            // get the attribute
            lElement = context.getAttribute(lAttributeName);
            // return the value
            return invokeGetter(lElement, lTheRest);
        }
    }

    /**
     * Returns the value represented by the given path String with respect to
     * the given reference object.  The attributes in the path String are
     * expected to be relative to the given object.  If there are errors
     * accessing the value, null is returned.
     * @param Object the root object of the path string
     * @param String the path string to the desired value
     * @return Object
     * @modelguid {3EDF9506-5F3A-4AB2-8D4D-05C4DF8ED557}
     */
    static private Object invokeGetter(Object anObj, String aPathString) {
        if (anObj == null) return null;
        if (aPathString == null) return anObj;
        // get the first element in the path string
        String[] lPath = splitPath(aPathString);
        String lAttributeName = lPath[0];
        String lTheRest = lPath[1];
        // get the attribute
        Object lResult = getValue(anObj, lAttributeName);
        // return the value
        return invokeGetter(lResult, lTheRest);
    }

	/**
     * Returns the value of the given attribute on the given object.  If there
     * is an error accessing the attribute on this object, a null value is
     * returned.
     * @param Object the root object
     * @param String the attribute name
     * @return Object
     * @modelguid {E8CB8C2B-9FE3-411D-AF33-F6C5BE1E892D}
     */
    static private Object getValue(Object anObj, String anAttrName) {
        // if this is a list
        if (anObj instanceof Iterator) {
            // allow access to the 'next' attribute
            if (anAttrName.equals("next"))
                return ((Iterator)anObj).next();
        } else {
            String lParameter = getParameter(anAttrName);
            String lMethodName = getMethodName(anAttrName);
            Method lMethod = getMethod(anObj, lMethodName, lParameter);
            try {
                if (lMethod != null) {
                    Object[] parameters = new Object[] { };
                    if (lParameter != null) {
                        parameters = new Object[] { lParameter };
                    }
                    return lMethod.invoke(anObj, parameters);
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return null;
    }

    /**
     * Returns the parameter specified in the given String.  The parameter is
     * defined as any text between parentheses in the String.  If no parameter
     * can be found, a null value is returned.
     * @param String an attribute string
     * @return String the parameter of the attribute string
     * @modelguid {EAC3432C-51D8-4461-9DF8-0ADD6FAC9ACB}
     */
    static private String getParameter(String aToken) {
        int lLeftParen = aToken.indexOf("(");
        int lRightParen = aToken.lastIndexOf(")");
        String lParameter = null;
        if (lLeftParen != -1 && lRightParen != -1 && lLeftParen < lRightParen) {
            lParameter = aToken.substring(lLeftParen + 1, lRightParen);
        }
        return lParameter;
    }

    /**
     * Returns the attribute name specified in the given String.  The attribute
     * name is defined as any text before the left paren, or the entire text if
     * if there is not left paren.
     * @param String an attribute string
     * @return String the attribute name of the attribute string
     * @modelguid {64DA0053-8EA1-4BAB-AE3E-42E6F734E27D}
     */
    static private String getMethodName(String aToken) {
        int lLeftParen = aToken.indexOf("(");
        if (lLeftParen > -1) {
            return aToken.substring(0, lLeftParen);
        } else {
            return aToken;
        }
    }

    /**
     * Utility method that splits a path string into two pieces: the token that
     * comes before the first '.' and everything after.  If there is no '.',
     * the first token is the entire string and the second token is null.
     * @param String the path string to split
     * @return String[2], the first element is the first token, the second element is the rest of the path string
     * @modelguid {AB723DB3-F727-467A-A92F-B3E26D076607}
     */
    static private String[] splitPath(String aPathString) {
        String lFirstToken = null;
        String lTheRest = null;
        int lIndexOfTheDot = aPathString.indexOf(".");
        if (lIndexOfTheDot > -1) {
            lFirstToken = aPathString.substring(0, lIndexOfTheDot);
            lTheRest = aPathString.substring(lIndexOfTheDot + 1);
        } else {
            lFirstToken = aPathString;
        }
        return new String[] {lFirstToken, lTheRest};
    }

    /**
     * Returns the Method class that represents the accessor method for the
     * given attribute on the given object with the optional given parameter
     * type.  If the desired method does not have a parameter, the
     * <pre>parameter</pre> should be null.  If not method is found, a null
     * value is returned.
     * @param Object the root object
     * @param String the name of the attribute
     * @param Object the parameter of the desired accessor method
     * @return java.lang.reflect.Method
     * @modelguid {DE6B3E19-394D-4AF6-BD28-5055BF89C36A}
     */
    static private Method getMethod(Object anObj, String anAttr, Object parameter) {
        try {
            Class[] parameters = new Class[] { };
            if (parameter != null) {
                parameters = new Class[] { parameter.getClass() };
            }
            if (anObj instanceof Collection && anAttr.equals("iterator")) {
                return anObj.getClass().getMethod(anAttr, parameters);
            } else {
                return anObj.getClass().getMethod("get" + anAttr.substring(0, 1).toUpperCase() + anAttr.substring(1), parameters);
            }
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
