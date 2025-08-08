// Source file:
// C:\\views\\MSA\\app\\src\\pd\\contact\\user\\transactions\\UpdateUserProfileCommand.java

package pd.contact.user.transactions;

import java.util.Iterator;

import naming.PDContactConstants;

import messaging.user.ProcessUserProfileTransferEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import mojo.km.security.User;
import pd.contact.user.UserProfile;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;

public class ProcessUserProfileTransferCommand implements ICommand {

	/**
	 *  
	 */
	public ProcessUserProfileTransferCommand() {

	}

	/**
	 * @param event
	 */
	public void execute(IEvent event) {
		// cast the event properly
		ProcessUserProfileTransferEvent transferEvent = (ProcessUserProfileTransferEvent) event;

		// lookup the user profile using the logon ID
		UserProfile userProfile = UserProfile.find(transferEvent.getLogonId());

		String oldDept = userProfile.getDepartmentId();
		String newDept = transferEvent.getNewDepartmentId();
		if (!newDept.equals(oldDept)) {
			userProfile.setDepartmentId(transferEvent.getNewDepartmentId());
			userProfile.setDeptTransferDate(transferEvent.getTransferDate());
			userProfile.setDeptTransferTime(transferEvent.getTransferTime());
			userProfile.setUserTypeId("");

			//for NonGeneric User, update the JIMS2Account record
			String userType = userProfile.getGenericUserTypeId();
			if (userType != null && userType.equals(PDContactConstants.NO)) {
				Iterator jims2AccountTypes = JIMS2AccountType.findAll(PDContactConstants.LOGON_ID, userProfile
						.getLogonId());
				while (jims2AccountTypes.hasNext()) {
					JIMS2AccountType j2Account = (JIMS2AccountType) jims2AccountTypes.next();
					String acctId = j2Account.getJIMS2AccountId();
					JIMS2Account j2acct = JIMS2Account.find(acctId);
					if (j2acct != null) {
						j2acct.setDepartmentId(userProfile.getDepartmentId());
					}
				}
			}

			// remove all associated Roles for the user in Security //87191
			/*User securityUser = User.find(userProfile.getLogonId());
			if (securityUser != null) {
				for (Iterator roles = securityUser.getRoles().iterator(); roles.hasNext();) {
					Role aRole = (Role) roles.next();
					securityUser.removeRoles(aRole);
				}
			}*/ // all done in security manager . 87191
		}
	}

	/**
	 * @param event
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 */
	public void update(Object updateObject) {

	}

}
