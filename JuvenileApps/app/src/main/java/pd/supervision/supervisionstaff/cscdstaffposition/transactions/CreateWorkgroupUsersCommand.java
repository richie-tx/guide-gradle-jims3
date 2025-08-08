/*
 * Created on Jun 13, 2007
 */
package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import messaging.cscdstaffposition.CreateWorkgroupUsersEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.Name;
import pd.contact.user.UserProfile;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;

/**
 * @author cc_rbhat
 */
public class CreateWorkgroupUsersCommand implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		CreateWorkgroupUsersEvent requestEvent = (CreateWorkgroupUsersEvent) event;
		filterUsers(requestEvent);
		return;
	}

	private void filterUsers(CreateWorkgroupUsersEvent requestEvent) {
		Collection staffPositions = new ArrayList();
		Collection filteredStaffPositions = new ArrayList();

		Iterator staffPositionIter = CSCDOrganizationStaffPosition.findAll(requestEvent);
		CSCDOrganizationStaffPosition staff = null;
		if (staffPositionIter != null) {
			while (staffPositionIter.hasNext()) {
				staff = (CSCDOrganizationStaffPosition) staffPositionIter.next();
				staffPositions.add(staff);
			}
		}

		Map userProfilesMap = null;
		if (requestEvent.getUserLastName() != null && !requestEvent.getUserLastName().equals("")) {
			userProfilesMap = getUsersByName(requestEvent);
			filteredStaffPositions = this.filterByName(userProfilesMap, staffPositions);
		} else {
			filteredStaffPositions = staffPositions;
		}

		CSCDSupervisionStaffResponseEvent sre = null;
		Iterator iter = filteredStaffPositions.iterator();
		if (iter != null && iter.hasNext()) {
			while (iter.hasNext()) {
				staff = (CSCDOrganizationStaffPosition) iter.next();
				//                sre = CSCDStaffPositionHelper.getStaffResponseEvent(staff,
				// userProfilesMap);
				sre = getStaffResponseEvent(staff, userProfilesMap);
				EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
			}
		}
	}

	public CSCDSupervisionStaffResponseEvent getStaffResponseEvent(CSCDOrganizationStaffPosition staffPosition,
			Map userProfileMap) {
		CSCDSupervisionStaffResponseEvent sre = null;
		if (staffPosition != null) {
			sre = new CSCDSupervisionStaffResponseEvent();
			sre.setAssignedLogonId(staffPosition.getUserProfileId());
			if (userProfileMap != null && staffPosition.getUserProfileId() != null
					&& !staffPosition.getUserProfileId().equals("")) {
				Name aName = new Name();
				UserProfile userProfile = (UserProfile) userProfileMap.get(staffPosition.getUserProfileId());
				if (userProfile != null) {
					aName.setFirstName(userProfile.getFirstName());
					aName.setMiddleName(userProfile.getMiddleName());
					aName.setLastName(userProfile.getLastName());
					sre.setAssignedName(aName);
				}
			} else {
				sre.setAssignedName(CSCDStaffPositionHelper.getUserName(staffPosition.getUserProfileId()));
			}
			sre.setJobTitleId(staffPosition.getJobTitleId());
			sre.setDivisionId(staffPosition.getDivisionId());
			sre.setProgramUnitId(staffPosition.getProgramUnitId());
			sre.setPositionTypeId(staffPosition.getPositionTypeId());
		}
		return sre;
	}

	private Map getUsersByName(CreateWorkgroupUsersEvent reqEvent) {
		GetUsersEvent usersEvent = new GetUsersEvent();
		usersEvent.setAgencyId(reqEvent.getAgencyId());
		usersEvent.setFirstName(reqEvent.getUserFirstName());
		//        getUsersEvent.setMiddleName(reqEvent.getStaffMiddleName());
		usersEvent.setLastName(reqEvent.getUserLastName());

		return CSCDStaffPositionHelper.getMatchingUserProfiles(usersEvent);
	}

	private Collection filterByName(Map userProfilesMap, Collection staffPositions) {
		Collection filteredStaffPositions = new ArrayList();
		Iterator iter = staffPositions.iterator();
		if (iter != null && iter.hasNext()) {
			CSCDOrganizationStaffPosition staff = null;
			while (iter.hasNext()) {
				staff = (CSCDOrganizationStaffPosition) iter.next();
				if (staff.getUserProfileId() != null && !staff.getUserProfileId().equals("")) {
					if (userProfilesMap.get(staff.getUserProfileId()) != null) {
						filteredStaffPositions.add(staff);
					}
				}
			}
		}
		return filteredStaffPositions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		return;
	}

}
