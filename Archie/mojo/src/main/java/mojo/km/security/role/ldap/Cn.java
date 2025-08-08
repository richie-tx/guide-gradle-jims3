/*
 * Class Cn.java
 * Created on May 24, 2002, 12:06:49 PM
 */

package mojo.km.security.role.ldap;

/** @author Chad Oliver 
 * @modelguid {51C895F6-9079-412A-A2C9-34831852158D}
 */
class Cn {
	/** @modelguid {A57FDA31-91B8-4B08-9ECE-A885A738018A} */
    public static String getCn(String distinguishedName) {
        if (distinguishedName.charAt(2) == '=') {
            return getCn(distinguishedName, 3, ',');
        }

        return getCn(distinguishedName, 0, '.');
    }

	/** @modelguid {B4BA3190-A9FD-40A3-8B80-441530BBFDD2} */
    private static String getCn(String distinguishedName, int start, char token) {
        final int length = distinguishedName.length();
        int i = start;

        while (i < length) {
            if (distinguishedName.charAt(i) == token) {
                break;
            }

            i += 1;
        }

        if (i > length) {
            i -= 1;
        }

        return distinguishedName.substring(start, i);
    }
}
