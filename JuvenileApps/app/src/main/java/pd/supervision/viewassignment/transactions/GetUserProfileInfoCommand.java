package pd.supervision.viewassignment.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import messaging.contact.to.NameBean;
import messaging.cscdstaffposition.GetStaffPositionsWithoutWorkGroupEvent;
import messaging.user.GetMatchingUserProfilesEvent;
import messaging.viewassignment.GetUserProfileInfoEvent;
import messaging.viewassignment.GetUserProfileInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;

public class GetUserProfileInfoCommand implements ICommand {

	public void execute(IEvent event) throws Exception {
		HashMap results = getStaffNameInfo(event);
		Iterator positionResults = getStaffPositionInfo(results.keySet());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		for (; positionResults != null && positionResults.hasNext();) {
			CSCDOrganizationStaffPosition staffPosition = (CSCDOrganizationStaffPosition) positionResults.next();
			String userProfileId = staffPosition.getUserProfileId();
			GetUserProfileInfoResponseEvent responseEvent = (GetUserProfileInfoResponseEvent) results.get(userProfileId);
			if (responseEvent != null) {
				String poi = staffPosition.getProbationOfficerInd();
				if (poi == null || poi.trim().length() == 0) {
					poi = "";
				}
				String positionName = staffPosition.getPositionName();
				if (positionName == null || positionName.trim().length() == 0) {
					positionName = "No Position Name";
				}
				String divisionName = staffPosition.getDivisionName();
				if (divisionName == null || divisionName.trim().length() == 0) {
					divisionName = "No Division Name";
				}
				String programUnitName = staffPosition.getProgramUnitName();
				if (programUnitName == null || programUnitName.trim().length() == 0) {
					programUnitName = "No Program Unit Name";
				}
				responseEvent.setPoi(poi);
				responseEvent.setPositionName(positionName);
				responseEvent.setDivisionName(divisionName);
				responseEvent.setProgramUnitName(programUnitName);
				dispatch.postEvent(responseEvent);				
			}
		}
	}
	
	private HashMap getStaffNameInfo(IEvent event) {
		GetUserProfileInfoEvent getUserProfileInfoEvent = (GetUserProfileInfoEvent) event;
		GetMatchingUserProfilesEvent requestEvent = new GetMatchingUserProfilesEvent();
		requestEvent.setLastName(getUserProfileInfoEvent.getLastName());
		requestEvent.setFirstName(getUserProfileInfoEvent.getFirstName());
		HashMap results = new HashMap();
		/*Iterator iter = UserProfile.findAll(requestEvent);
		NameBean staffName = new NameBean();
		while (iter.hasNext()) {
			UserProfile userProfile = (UserProfile) iter.next();
			GetUserProfileInfoResponseEvent responseEvent = new GetUserProfileInfoResponseEvent();
			staffName.clear();
			staffName.setLastName(userProfile.getLastName());
			staffName.setFirstName(userProfile.getFirstName());
			responseEvent.setOfficerName(staffName.getFormattedName());
			responseEvent.setUserId(userProfile.getLogonId());
			results.put(userProfile.getLogonId(), responseEvent);
		}		*/
		return results;
	}
	
	private Iterator getStaffPositionInfo(Set userIds) {
		String agencyId = PDSecurityHelper.getUserAgencyId();
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, 
        		PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
		GetStaffPositionsWithoutWorkGroupEvent getStaffEvent = new GetStaffPositionsWithoutWorkGroupEvent();
		getStaffEvent.setAgencyId(agencyId);
		getStaffEvent.setLogonIds(userIds);	
        getStaffEvent.setStatusId(aCode.getOID().toString());
       	Iterator results = CSCDOrganizationStaffPosition.findAll(getStaffEvent);
		return results;
	}
}
