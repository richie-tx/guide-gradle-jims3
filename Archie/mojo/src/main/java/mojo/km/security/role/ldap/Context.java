/*
 * Class Context.java
 * Created on May 24, 2002, 12:07:09 PM
 */

package mojo.km.security.role.ldap;

/** @author Chad Oliver 
 * @modelguid {6CD029AD-A697-440E-B6BB-21C87D0182F8}
 */
class Context {
	/** @modelguid {1D006D38-0ABC-4314-B5C4-E2C6D73AAAB8} */
    public static String getContext(String distinguishedName) {
        String newDistinguishedName = distinguishedName;
        String context = null;
        int start = 0;
        int end = 0;

        if (distinguishedName.charAt(2) == '=') {
            newDistinguishedName = ApplicationName.getApplicationName(distinguishedName);
        }

        start = newDistinguishedName.indexOf(".") + 1;
        end = newDistinguishedName.length();

        try {
            context = newDistinguishedName.substring(start, end);
        }
        catch (Exception e) {
            context = "";
        }

        return context;
    }
}
