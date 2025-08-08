package mojo.km.security.role;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/*
 * Class UserFactory.java
 * Created on June 20, 2002, 8:53:09 AM
 

package mojo.km.security.role;

import mojo.km.config.AppProperties;
import mojo.km.security.role.acf2.ACF2User;
import mojo.km.security.role.file.FileUser;
import mojo.km.security.role.ldap.LdapUser;

*//**
* @author Chad Oliver
* @modelguid {4BD21594-734A-47A7-A977-39B4756FFC67}
*//*
public class UserFactory {
	*//** @modelguid {05AAFC41-003E-4780-A64D-18A97020E9F4} *//*
    private static final int FILE = 0;
	*//** @modelguid {F70C2A0F-C5F8-473C-8B43-E966FFCED390} *//*
	private static final int ACF2 = 1;
	*//** @modelguid {F70C2A0F-C5F8-473C-8B43-E966FFCED390} *//*
	private static final int LDAP = 2;

	*//** @modelguid {AAEFA1A3-C5A6-40A0-A4FF-60526B01C750} *//*
	private int userType = 0;

	*//** @modelguid {BC7D70D3-6BFC-4CF2-9432-5905A6E80A99} *//*
    public UserFactory() {
		String groupMembership = null;

        groupMembership = AppProperties.getInstance().getProperty("GroupMembership");

        if (groupMembership != null && groupMembership.equalsIgnoreCase("ldap")) {
            userType = LDAP;
		}
		else if (groupMembership != null && groupMembership.equalsIgnoreCase("acf2")) {
			userType = ACF2;
		}
		else if (groupMembership != null && groupMembership.equalsIgnoreCase("file")) {
			userType = FILE;
		}
        else {
            StringBuffer sb = new StringBuffer("Mojo.xml needs to have an element GroupMembership as a child of the Application element.\n")
                              .append("Vaild entries are.\n")
							  .append("<GroupMembership value=\'File\'>\n")
							  .append("<GroupMembership value=\'LDAP\'>\n")
                              .append("<GroupMembership value=\'ACF2\'>");

            new java.lang.Error(sb.toString()).printStackTrace();
            System.exit(1);
        }
    }

	*//** @modelguid {63DEC20E-7DB8-4A35-ADFC-32D7D74F6E05} *//*
    public IUser getUser() {
        if (userType == LDAP) {
            return new LdapUser();
        }
		else if (userType == ACF2) {
			return new ACF2User();
		}
		else {
			return new FileUser();
		}
    }
}
*/