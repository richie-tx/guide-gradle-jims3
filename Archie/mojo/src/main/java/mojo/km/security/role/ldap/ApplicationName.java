/*
 * Class ApplicationName.java
 * Created on May 24, 2002, 1:03:49 PM
 */

package mojo.km.security.role.ldap;

/** @author Chad Oliver 
 * @modelguid {3BC9BF2E-A2B8-4FAA-AEA6-4E9F193701D4}
 */
class ApplicationName {
	/** @modelguid {B7D73F30-D4E9-4706-9B14-CE043601D9AA} */
    public static String getApplicationName(String distinguishedName) {
        StringBuffer newDn = new StringBuffer();
        int distinguishedNameLen = 0;
        int i = 0;

        if (distinguishedName.charAt(2) != '=') {
            return "";
        }

        distinguishedName = distinguishedName.substring(3).trim();
        distinguishedNameLen = distinguishedName.length();
        i = 0;

        try {
            while (i < distinguishedNameLen) {
                if (distinguishedName.charAt(i) == ',') {
                    do {
                        i++;
                    } while (i < distinguishedNameLen && distinguishedName.charAt(i) != '=');

                    newDn.append(".");
                }
                else if (distinguishedName.charAt(i) != ' ') {
                    newDn.append(new Character(distinguishedName.charAt(i)));
                }
                i++;
            }

            return newDn.toString();
        }
        catch (Exception e) {
            return "";
        }
    }
}
