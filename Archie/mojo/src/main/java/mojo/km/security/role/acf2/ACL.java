package mojo.km.security.role.acf2;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/*package mojo.km.security.role.acf2;

import java.util.Iterator;
import java.util.ArrayList;

*//**
 * @author James McNabb
 *
 *//*
public class ACL
{
	public ArrayList getPermissions(String userid)
	{
		ArrayList permissions = new ArrayList();
		
		// get all the SystemActivities associated with the user
		User user = User.findUser(userid);
		Iterator roleItr = (Iterator)user.getRoles();
		while(roleItr.hasNext())
		{
			Role role = (Role)roleItr.next();
			
			Iterator permissionItr = role.getPermissions().iterator();
			while(permissionItr.hasNext())
			{
				Permission permission = (Permission)permissionItr.next();
				permissions.add(permission);
			}
		}
		
		return permissions;
	}
}*/