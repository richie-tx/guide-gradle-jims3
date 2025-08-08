/*
 * Class Dn.java
 * Created on May 24, 2002, 12:06:10 PM
 */

package mojo.km.security.role.ldap;

import java.util.Hashtable;
import javax.naming.NamingException;

/** @author Chad Oliver 
 * @modelguid {1B5284EE-C360-4661-879B-A0EE7EA6126E}
 */
class Dn {
    /**
     * @param distinguishedName - Is an for attribute whose value is the name of an
     * object in a LDAP tree tree.
     * @modelguid {F52AF864-D5DE-4ECC-8C76-D0A07B2D6DA9}
     */
    public static String getDistinguishedName(Object distinguishedName) throws NamingException {
        String newDistinguishedName = null;

        if (distinguishedName instanceof String) {
            newDistinguishedName = (String)distinguishedName;
        }
        else if (distinguishedName instanceof Hashtable) {
            Hashtable tempHash = (Hashtable)distinguishedName;

            if (tempHash.contains("java.naming.security.principal")) {
                newDistinguishedName = tempHash.get("java.naming.security.principal").toString();
            }
        }

        if (newDistinguishedName == null) {
            throw new NamingException("Invaild DN object.");
        }

        return stripSpaces(newDistinguishedName);
    }

    /**
     * Remove all spaces which may be in the DN.  For example cn = coliver,
     * cn=coliver, ou= the401k, o=the401k
     * @param distinguishedName - Is an for attribute whose value is the name of an
     * object in a LDAP tree tree.
     * @modelguid {02285A26-888C-4366-826B-13471DDE23A7}
     */
    private static String stripSpaces(String distinguishedName) {
        StringBuffer newDistinguishedName = new StringBuffer();
        int distinguishedNameLen = 0;

        distinguishedNameLen = distinguishedName.length();

        for (int i = 0; i < distinguishedNameLen; i++) {
            if (distinguishedName.charAt(i) != ' ') {
                newDistinguishedName.append(distinguishedName.charAt(i));
            }
        }

        return newDistinguishedName.toString();
    }
}
