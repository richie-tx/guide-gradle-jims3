// Source file:
// C:\\views\\MSA\\app\\src\\pd\\contact\\user\\transactions\\DeleteUserProfileCommand.java

package pd.contact.user.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.PDContactConstants;

import messaging.user.DeleteUserProfileEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import mojo.km.security.User;
import mojo.km.security.UserGroup;
 
import pd.contact.user.UserProfile;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;

public class DeleteUserProfileCommand implements ICommand {

    /**
     * @roseuid 43F4F691001A
     */
    public DeleteUserProfileCommand() {

    }

    /**
     * @param event
     * @roseuid 43EA4DE50375
     */
    public void execute(IEvent event) {
        DeleteUserProfileEvent deleteEvent = (DeleteUserProfileEvent) event;

        UserProfile userProfile = UserProfile.find(deleteEvent.getLogonId());

        if (userProfile != null) {

            // delete the user profile
            String userType = userProfile.getGenericUserTypeId();
            Iterator iter = JIMS2AccountType.findAll(
                    PDContactConstants.LOGON_ID, userProfile.getLogonId());
            userProfile.delete();
            // for NonGeneric User, delete the JIMS2Account record
            //		if(userType != null && userType.equals(PDContactConstants.NO)){

            JIMS2AccountType jimsAccType = null;
            while (iter.hasNext()) {
                jimsAccType = (JIMS2AccountType) iter.next();
                JIMS2Account jims = JIMS2Account.find(jimsAccType
                        .getJIMS2AccountId());
                if (jims != null) {
                    //						jims.delete();
                    jims.setInactivation();
                }
                //				if(jimsAccType != null){
                //				JIMS2Account jims =
                // JIMS2Account.find(jimsAccType.getJIMS2AccountId());
                //					if(jims != null){
                //						jims.delete();
                //						jims.setInactivation();
                //					}
                //				}
            }
            
            //87191
/*            User user = User.find(userProfile.getLogonId());
            Collection ugc = user.getUserGroups();
            if (ugc != null) {
                Iterator ugs = ugc.iterator();
                UserGroup usergroup = null;
                while (ugs.hasNext()) {
                    usergroup = (UserGroup) ugs.next();
                    usergroup.removeUsers(user);
                }
            }
            Collection rls = user.getRoles();
            if (rls != null) {
                Iterator roles = rls.iterator();
                Role role = null;
                while (roles.hasNext()) {
                    role = (Role) roles.next();
                    role.removeUsers(user);
                }
            }*/
        }
    }

    /**
     * @param event
     * @roseuid 43EA4DE5037E
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 43EA4DE50388
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param anObject
     * @roseuid 43EA4DE50392
     */
    public void update(Object anObject) {

    }

}