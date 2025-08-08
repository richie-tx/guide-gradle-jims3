/*
 * Created on December 20, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.security.authentication;

import java.util.Iterator;

import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import pd.common.ResponseCreator;
import pd.contact.agency.Department;
import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.security.JIMS2AccountType;
import pd.security.JIMS2AccountView;
import ui.common.Name;

/**
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JIMS2AccountCreatorImpl implements ResponseCreator {

	/**
	 * @param object
	 */
	public Object create(Object object) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		JIMS2AccountView jims2AccountView = (JIMS2AccountView) object;
		JIMS2AccountResponseEvent event = (JIMS2AccountResponseEvent) this.createThin(jims2AccountView);
		//U.S #79250
		//TO be removed later. kept for service provider flow
		event.setJIMS2Password(jims2AccountView.getPassword());
		//event.setPasswordAnswer(jims2AccountView.getAnswer());
		event.setActivateDate(jims2AccountView.getActivatedDate());
		event.setInactivateDate(jims2AccountView.getInactiveDate());
		event.setActivatedBy(jims2AccountView.getActivatedBy());
		event.setInactivatedBy(jims2AccountView.getInactivatedBy());

		/*if ((jims2AccountView.getPasswordQuestionId() != null) && (!(jims2AccountView.getPasswordQuestionId().equals("")))) {
			Code passwordQuestion = jims2AccountView.getPasswordQuestion();
			if (passwordQuestion != null) {
				event.setForgottenPasswdPhraseId(jims2AccountView.getPasswordQuestionId());
				event.setForgottenPasswdPhrase(passwordQuestion.getDescription());
			}
		}*/
		if ((jims2AccountView.getDepartmentId() != null) && (!(jims2AccountView.getDepartmentId().equals("")))) {
			Department dept = jims2AccountView.getDepartment();
			if (dept != null) {
				event.setDepartmentName(dept.getDepartmentName());
			}
		}

		//TODO Change to use IUser interface when that's been coded
		String logonId = null;
		String userAccountOID = null;
		String userType = null;
		Iterator<JIMS2AccountType> iter = jims2AccountView.getJIMS2AccountType().iterator();
		while (iter.hasNext()) {
			JIMS2AccountType jims2AccountType = (JIMS2AccountType) iter.next();
			if (jims2AccountType != null) {
				logonId = jims2AccountType.getLogonId();
				userAccountOID = jims2AccountType.getUserAccountOID();
				userType = jims2AccountType.getUserAccountTypeId();
				event.setJimsLogonId(jims2AccountType.getLogonId());
				event.setJIMS2AccountTypeId(userType);
				event.setJIMS2AccountTypeOID(userAccountOID);
			} else {
				LoginErrorResponseEvent error = new LoginErrorResponseEvent();
				error.setMessage("JIMS2 Account Type not found for user " + jims2AccountView.getJIMS2LogonId()
						+ ", please contact your Security Administrator to create one.");
				dispatch.postEvent(error);
				return event;
			}
		}
		//if generic, get JIMS password
		if ((userType.equals("L")) || (userType.equals("S"))) {
			if ((userType.equals("L"))) {
				OfficerProfile officer = OfficerProfile.find(userAccountOID);
				if (officer != null) {
					event.setBadgeNum(officer.getBadgeNum());
					event.setOtherIdNum(officer.getOtherIdNum());
					event.setJIMS2AccountTypeOID(officer.getOID().toString());
				}
			} else if (userType.equals("S")) {
				/*GetSPProfileEvent reqEvent =
					(GetSPProfileEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSPPROFILE);
				reqEvent.setEmployeeId(userAccountOID);
				Iterator<SP_Profile> profiles = SP_Profile.findAll(reqEvent);

				while (profiles.hasNext()) {
					SP_Profile spProfile = (SP_Profile) profiles.next();
					if (spProfile != null) {
						event.setEmployeeId(spProfile.getEmployeeId());
						event.setServiceProviderName(spProfile.getServiceProviderName());
						event.setJIMS2AccountTypeOID(spProfile.getOID().toString());
					}
				}*/
			    event.setServiceProviderName(new Name(event.getFirstName(),event.getMiddleName(),event.getLastName()).getFormattedName());
			}
			//86318
			/*JIMSGenericAccount genericAccount = JIMSGenericAccount.find(logonId);
			if (genericAccount != null) {
				event.setJimsPassword(genericAccount.getPassword());
			} else {
				LoginErrorResponseEvent error = new LoginErrorResponseEvent();
				error.setMessage("JIMS2 Generic Account not found for user " + jims2AccountView.getJIMS2LogonId()
						+ ", please contact your Security Administrator to create one.");
				dispatch.postEvent(error);
				return event;
			}*/
		}

		if ((userAccountOID != null) && (!(userAccountOID.equals("")))) {
			OfficerProfile officer = OfficerProfile.find(userAccountOID);
			if (officer != null) {
				event.setEmail(officer.getEmail());
				event.setPhoneExt(officer.getWorkPhoneExtn());
				event.setPhoneNum(officer.getWorkPhoneNum());
			}
		} else {
			if (logonId != null) {
				UserProfile user = UserProfile.find(logonId);
				if (user != null) {
					event.setEmail(user.getEmail());
					event.setPhoneExt(user.getPhoneExt());
					event.setPhoneNum(user.getPhoneNum());
					event.setServer(user.getServerRegion());
				}
			}

		}
		return event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.security.SecurityResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object) {
		JIMS2AccountView jAcc = (JIMS2AccountView) object;
		JIMS2AccountResponseEvent event = new JIMS2AccountResponseEvent();
		event.setJIMS2AccountId(jAcc.getOID().toString());
		event.setFirstName(jAcc.getFirstName());
		event.setLastName(jAcc.getLastName());
		event.setMiddleName(jAcc.getMiddleName());
		event.setJIMS2LogonId(jAcc.getJIMS2LogonId());
		event.setJimsLogonId(jAcc.getLogonId());
		event.setStatus(jAcc.getStatus());
		event.setDepartmentId(jAcc.getDepartmentId());
		return event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
