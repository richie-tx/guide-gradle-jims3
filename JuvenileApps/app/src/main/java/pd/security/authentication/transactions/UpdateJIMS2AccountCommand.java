// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\CreateJIMS2AccountCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;
import messaging.administerserviceprovider.GetSPProfileEvent;
import messaging.authentication.UpdateJIMS2AccountEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.contact.officer.OfficerProfile;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.SP_Profile;

public class UpdateJIMS2AccountCommand implements ICommand {

	/**
	 * @roseuid 4399BF210352
	 */
	public UpdateJIMS2AccountCommand() {

	}

	/**
	 * @param event
	 * @roseuid 439711A802BF
	 */
	public void execute(IEvent event) {
		UpdateJIMS2AccountEvent uEvent = (UpdateJIMS2AccountEvent) event;

		JIMS2Account jAcc = JIMS2Account.find(uEvent.getJims2AccountId());
		if (jAcc != null) {
			//update scenerio, otherwise inactivate scenerio
			if ("edit".equalsIgnoreCase(uEvent.getAction())) {
				if (uEvent.getJims2LogonId() != null && !uEvent.getJims2LogonId().equals("")) {
					jAcc.setJIMS2LogonId(uEvent.getJims2LogonId());
				}
				if ("L".equalsIgnoreCase(uEvent.getUserTypeId())) {
					this.setGenericProperties(uEvent, jAcc);
					Iterator iter = jAcc.getJIMS2AccountType().iterator();
					while (iter.hasNext()) {
						JIMS2AccountType jims2AccountType = (JIMS2AccountType) iter.next();
						if (jims2AccountType != null) {
							String userAccountOID = jims2AccountType.getUserAccountOID();
							if (userAccountOID != null) {
								OfficerProfile officer = OfficerProfile.find(userAccountOID);
								if (officer != null) {
									officer.setFirstName(uEvent.getFirstName());
									officer.setLastName(uEvent.getLastName());
									officer.setMiddleName(uEvent.getMiddleName());
								}
							}
						}
					}
				} else if ("S".equalsIgnoreCase(uEvent.getUserTypeId())) {
					this.setGenericProperties(uEvent, jAcc);

//					GetSPProfileEvent sEvent = new GetSPProfileEvent();
//					sEvent.setLogonId(uEvent.getJimsLogonId());

					Iterator iter = jAcc.getJIMS2AccountType().iterator();
					while (iter.hasNext()) {
						JIMS2AccountType jims2AccountType = (JIMS2AccountType) iter.next();
						if (jims2AccountType != null) {
							String userAccountOID = jims2AccountType.getUserAccountOID();
							if (userAccountOID != null) {
								//Using InhouseSP_Profile entity instead of SP_Profile because no save mapping exists for SP_Profile.  Since only
								//name is being updated it doesn't affect anything.
								InhouseSP_Profile sp = (InhouseSP_Profile) InhouseSP_Profile.find(userAccountOID);
								if (sp != null) {
									sp.setFirstName(uEvent.getFirstName());
									sp.setLastName(uEvent.getLastName());
									sp.setMiddleName(uEvent.getMiddleName());
								}
							}
						}
					}
				}
			} else {
				jAcc.setInactivation();
			}
		}
	}

	/**
	 * @param uEvent
	 * @param jAcc
	 */
	private void setGenericProperties(UpdateJIMS2AccountEvent uEvent, JIMS2Account jAcc) {
		if (uEvent.getFirstName() != null && !uEvent.getFirstName().equals("")) {
			jAcc.setFirstName(uEvent.getFirstName());
		}

		if (uEvent.getLastName() != null && !uEvent.getLastName().equals("")) {
			jAcc.setLastName(uEvent.getLastName());
		}

		if (uEvent.getMiddleName() != null && !uEvent.getMiddleName().equals("")) {
			jAcc.setMiddleName(uEvent.getMiddleName());
		}

		if (uEvent.getPassword() != null && !uEvent.getPassword().equals("")) {
			jAcc.setPassword(uEvent.getPassword());
		}

		if (uEvent.getPasswordAnswer() != null && !uEvent.getPasswordAnswer().equals("")) {
			jAcc.setAnswer(uEvent.getPasswordAnswer());
		}

		if (uEvent.getPasswordQuestionId() != null && !uEvent.getPasswordQuestionId().equals("")) {
			jAcc.setPasswordQuestionId(uEvent.getPasswordQuestionId());
		}
	}

	/**
	 * @param event
	 * @roseuid 439711A802C1
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 439711A802C3
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 4399BF210371
	 */
	public void update(Object updateObject) {

	}
}