// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\GetCSCDSupervisionStaffCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrgStaffWorkgroup;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.GetStaffPositionsEvent;
import messaging.cscdstaffposition.GetStaffPositionsWithoutWorkGroupEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;

public class GetCSCDSupervisionStaffCommand implements ICommand {
    private static String BLANK = "";
    /**
     * @roseuid 460BFAA2032A
     */
    public GetCSCDSupervisionStaffCommand() {

    }

    /**
     * @param event
     * @roseuid 460BD54800EB
     */
    public void execute(IEvent event) {
        GetCSCDSupervisionStaffEvent requestEvent = (GetCSCDSupervisionStaffEvent) event;
        if(requestEvent.isUseStaffLogonId()){
        	this.searchByUserIdOnly(requestEvent);
        }
        else{
	        if (requestEvent.getStaffPositionId() != null
	                && !requestEvent.getStaffPositionId().trim().equals(BLANK)) {
	            CSCDStaffPosition staff = CSCDStaffPosition.find(requestEvent.getStaffPositionId());
	            if (staff != null)
	            {
		            CSCDSupervisionStaffResponseEvent sre = CSCDStaffPositionHelper
		                    .getStaffResponseEvent(staff, null);
		            EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
	            }
	        } else if (this.searchByUserInfoOnly(requestEvent)){
	            this.searchByUserInfo(requestEvent);
	        } else {
	        	this.searchByStaffAndUserInfo(requestEvent);
	        }
        }
    }
    
    private  void searchByUserIdOnly(GetCSCDSupervisionStaffEvent requestEvent){
    	GetStaffPositionsEvent getStaffEvent = new GetStaffPositionsEvent();
    	getStaffEvent.addLogonId(requestEvent.getStaffLogonId());
    	//getStaffEvent.setAgencyId(requestEvent.getAgencyId());
    	Iterator staffIter = CSCDStaffPosition.findAll(getStaffEvent);
        if (staffIter != null && staffIter.hasNext()){
            while (staffIter.hasNext()){
            	CSCDStaffPosition staffPosition = (CSCDStaffPosition) staffIter.next();
            	CSCDSupervisionStaffResponseEvent sre=CSCDStaffPositionHelper.getLightStaffResponseEvent(staffPosition);
            	if(sre!=null){
            		EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
            	}
            }

        }
    }

    /**
     * @param requestEvent
     */
    private void searchByUserInfo(GetCSCDSupervisionStaffEvent requestEvent) {
        Map userProfilesMap = this.getUsersByName(requestEvent);
        Collection userProfiles = userProfilesMap.values();
        Iterator iter = userProfiles.iterator();
        if (iter != null && iter.hasNext()){
            UserProfile userProfile = null;
            Iterator staffIter = null;
            CSCDStaffPosition staffPosition = null;
            GetStaffPositionsEvent getStaffEvent = new GetStaffPositionsEvent();
            CSCDSupervisionStaffResponseEvent sre = null;

            while (iter.hasNext()){
                userProfile = (UserProfile) iter.next();
                getStaffEvent.addLogonId(userProfile.getLogonId());
            }
            staffIter = CSCDStaffPosition.findAll(getStaffEvent);
            if (staffIter != null && staffIter.hasNext()){
                while (staffIter.hasNext()){
                    staffPosition = (CSCDStaffPosition) staffIter.next();
                    sre = CSCDStaffPositionHelper.getStaffResponseEvent(staffPosition, null, userProfilesMap);
                    EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
                }

            }
        }
    }

    /**
     * @param requestEvent
     */
    private void searchByStaffAndUserInfo(GetCSCDSupervisionStaffEvent requestEvent) {
        if (!this.staffAndUserInfoExists(requestEvent)){
            this.createReturnException();
            return;
        }
//        Collection staffPositions = new ArrayList();
//        Collection filteredStaffPositions = new ArrayList();
 /*       GetStaffPositionsEvent getStaffEvent = new GetStaffPositionsEvent();
        if (requestEvent.getCjadNum() != null){
        	getStaffEvent.setCjadNum(requestEvent.getCjadNum().toUpperCase());
        }
        getStaffEvent.setCstsOfficerTypeId(requestEvent.getCstsOfficerTypeId());
        if (requestEvent.getStaffLogonId() != null && !requestEvent.getStaffLogonId().equals(BLANK)){
            Collection logonIds = new ArrayList();
            logonIds.add(requestEvent.getStaffLogonId().toUpperCase());
            getStaffEvent.setLogonIds(logonIds);
        }
        if (requestEvent.getPositionName() != null){
            getStaffEvent.setPositionName(requestEvent.getPositionName().toUpperCase());
        }
        if (requestEvent.getWorkGroupName() != null){
            getStaffEvent.setWorkgroupName(requestEvent.getWorkGroupName().toUpperCase());
        }
        if (requestEvent.getAgencyId() != null && !requestEvent.getAgencyId().equals(BLANK)){
            getStaffEvent.setAgencyId(requestEvent.getAgencyId());
        } else {
            getStaffEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        }
        getStaffEvent.setStaffPositionId(requestEvent.getStaffPositionId());
        getStaffEvent.setDivisionId(requestEvent.getDivisionId());
        getStaffEvent.setProgramUnitId(requestEvent.getProgramUnitId());
        getStaffEvent.setProgramSectionId(requestEvent.getProgramSectionId());
*/
        if (requestEvent.getWorkGroupName() != null 
        		&& !requestEvent.getWorkGroupName().equals(BLANK)){
        	this.processWorkgroupQuery(requestEvent);
        } else {
        	this.processStaffQueryWithoutWorkgroup(requestEvent);
        }
        
        /* CSCDOrgStaffWorkgroup staff = null;
        CSCDSupervisionStaffResponseEvent sre = null;
        if (iter != null && iter.hasNext()) {
            Map userProfilesMap = null;
            Object obj = null;
            while (iter.hasNext()) {
                staff = (CSCDOrgStaffWorkgroup) iter.next();
                staffPositions.add(staff);
            }
            if (requestEvent.getStaffLastName() != null && !requestEvent.getStaffLastName().equals(BLANK)){
                userProfilesMap = this.getUsersByName(requestEvent);
                filteredStaffPositions = this.filterByName(userProfilesMap, staffPositions);
            } else {
                filteredStaffPositions = staffPositions;
            }
            iter = filteredStaffPositions.iterator();
            if (iter != null && iter.hasNext()){
                while (iter.hasNext()){
                    staff = (CSCDOrgStaffWorkgroup) iter.next();
                    sre = CSCDStaffPositionHelper.getStaffResponseEvent(staff, null, userProfilesMap);
                    EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
                }
            }
        }*/
        
    }

    private void processStaffQueryWithoutWorkgroup(GetCSCDSupervisionStaffEvent requestEvent) {
        GetStaffPositionsWithoutWorkGroupEvent getStaffEvent = new GetStaffPositionsWithoutWorkGroupEvent();
        if (requestEvent.getCjadNum() != null){
        	getStaffEvent.setCjadNum(requestEvent.getCjadNum().toUpperCase());
        }
        getStaffEvent.setCstsOfficerTypeId(requestEvent.getCstsOfficerTypeId());
        if (requestEvent.getStaffLogonId() != null && !requestEvent.getStaffLogonId().equals(BLANK)){
            Collection logonIds = new ArrayList();
            logonIds.add(requestEvent.getStaffLogonId().toUpperCase());
            getStaffEvent.setLogonIds(logonIds);
        }
         if (requestEvent.getAgencyId() != null && !requestEvent.getAgencyId().equals(BLANK)){
            getStaffEvent.setAgencyId(requestEvent.getAgencyId());
        } else {
            getStaffEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        }
        getStaffEvent.setStaffPositionId(requestEvent.getStaffPositionId());
        getStaffEvent.setDivisionId(requestEvent.getDivisionId());
        getStaffEvent.setProgramUnitId(requestEvent.getProgramUnitId());
        getStaffEvent.setProgramSectionId(requestEvent.getProgramSectionId());  
        if (requestEvent.getPositionName() != null){
            getStaffEvent.setPositionName(requestEvent.getPositionName().toUpperCase());
        }
      	Collection filteredStaffPositions = new ArrayList();
       	Collection staffPositions = new ArrayList();
       	CSCDOrganizationStaffPosition staff = null;
       	CSCDSupervisionStaffResponseEvent sre = null;
       	
       	Iterator iter = CSCDOrganizationStaffPosition.findAll(getStaffEvent);
       	if (iter != null && iter.hasNext()) {
      		Map userProfilesMap = null;
      		while (iter.hasNext()) {
      			staff = (CSCDOrganizationStaffPosition) iter.next();
      			staffPositions.add(staff);
      		}
      		if (requestEvent.getStaffLastName() != null && !requestEvent.getStaffLastName().equals(BLANK)){
      			userProfilesMap = this.getUsersByName(requestEvent);
      			filteredStaffPositions = this.filterByName(userProfilesMap, staffPositions);
      		} else {
      			filteredStaffPositions = staffPositions;
      		}
      		iter = filteredStaffPositions.iterator();
      		if (iter != null && iter.hasNext()){
      			while (iter.hasNext()){
      				staff = (CSCDOrganizationStaffPosition) iter.next();
      				sre = CSCDStaffPositionHelper.getStaffResponseEvent(staff, null, userProfilesMap);
      				EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
      			}
      		}
       	}
	}

	private Collection processWorkgroupQuery(GetCSCDSupervisionStaffEvent  requestEvent) {
        GetStaffPositionsEvent getStaffEvent = new GetStaffPositionsEvent();
        if (requestEvent.getCjadNum() != null){
        	getStaffEvent.setCjadNum(requestEvent.getCjadNum().toUpperCase());
        }
        getStaffEvent.setCstsOfficerTypeId(requestEvent.getCstsOfficerTypeId());
        if (requestEvent.getStaffLogonId() != null && !requestEvent.getStaffLogonId().equals(BLANK)){
            Collection logonIds = new ArrayList();
            logonIds.add(requestEvent.getStaffLogonId().toUpperCase());
            getStaffEvent.setLogonIds(logonIds);
        }
        if (requestEvent.getPositionName() != null){
            getStaffEvent.setPositionName(requestEvent.getPositionName().toUpperCase());
        }
        if (requestEvent.getWorkGroupName() != null){
            getStaffEvent.setWorkgroupName(requestEvent.getWorkGroupName().toUpperCase());
        }
        if (requestEvent.getAgencyId() != null && !requestEvent.getAgencyId().equals(BLANK)){
            getStaffEvent.setAgencyId(requestEvent.getAgencyId());
        } else {
            getStaffEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        }
        getStaffEvent.setStaffPositionId(requestEvent.getStaffPositionId());
        getStaffEvent.setDivisionId(requestEvent.getDivisionId());
        getStaffEvent.setProgramUnitId(requestEvent.getProgramUnitId());
        getStaffEvent.setProgramSectionId(requestEvent.getProgramSectionId());       	
       	Collection filteredStaffPositions = new ArrayList();
       	Collection staffPositions = new ArrayList();
       	CSCDOrgStaffWorkgroup staff = null;
       	CSCDSupervisionStaffResponseEvent sre = null;
       	
       	Iterator iter = CSCDOrgStaffWorkgroup.findAll(getStaffEvent);
       	if (iter != null && iter.hasNext()) {
      		Map userProfilesMap = null;
      		while (iter.hasNext()) {
      			staff = (CSCDOrgStaffWorkgroup) iter.next();
      			staffPositions.add(staff);
      		}
      		if (requestEvent.getStaffLastName() != null && !requestEvent.getStaffLastName().equals(BLANK)){
      			userProfilesMap = this.getUsersByName(requestEvent);
      			filteredStaffPositions = this.filterByName(userProfilesMap, staffPositions);
      		} else {
      			filteredStaffPositions = staffPositions;
      		}
      		iter = filteredStaffPositions.iterator();
      		if (iter != null && iter.hasNext()){
      			while (iter.hasNext()){
      				staff = (CSCDOrgStaffWorkgroup) iter.next();
      				sre = CSCDStaffPositionHelper.getStaffResponseEvent(staff, null, userProfilesMap);
      				EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
      			}
      		}
       	}
		return filteredStaffPositions;
	}

	/**
     * 
     */
    private void createReturnException() {
        ReturnException re = new ReturnException();
        re.setErrorReason("INSUFFICIENT INFO IN GetCSCDSupervisionStaffEvent");
        re.setTopic(ReturnException.RETURN_EXCEPTION_TOPIC);
        throw(re);
    }

    /**
     * @param requestEvent
     * @return
     */
    private boolean staffAndUserInfoExists(GetCSCDSupervisionStaffEvent requestEvent) {
        boolean haveEnoughInfo = true;
        if ((requestEvent.getCjadNum() == null || requestEvent.getCjadNum().equals(BLANK))
            && (requestEvent.getCstsOfficerTypeId() == null || requestEvent.getCstsOfficerTypeId().equals(BLANK))
            && (requestEvent.getStaffLogonId() == null || requestEvent.getStaffLogonId().equals(BLANK))
            && (requestEvent.getPositionName() == null || requestEvent.getPositionName().equals(BLANK))
            && (requestEvent.getWorkGroupName() == null || requestEvent.getWorkGroupName().equals(BLANK))
            && (requestEvent.getStaffPositionId() == null || requestEvent.getStaffPositionId().equals(BLANK))
            && (requestEvent.getDivisionId() == null || requestEvent.getDivisionId().equals(BLANK))
            && (requestEvent.getProgramUnitId() == null || requestEvent.getProgramUnitId().equals(BLANK))
            && (requestEvent.getProgramSectionId() == null || requestEvent.getProgramSectionId().equals(BLANK))
            && (requestEvent.getStaffLastName() == null || requestEvent.getStaffLastName().equals(BLANK))){
            haveEnoughInfo = false;
        }
        return haveEnoughInfo;
    }

    /**
     * @param requestEvent
     * @return
     */
    private boolean searchByUserInfoOnly(GetCSCDSupervisionStaffEvent requestEvent) {
        
        boolean searchByUserOnly = false;
        if ((requestEvent.getCjadNum() == null || requestEvent.getCjadNum().equals(BLANK))
            && (requestEvent.getDivisionId() == null || requestEvent.getDivisionId().equals(BLANK))
            && (requestEvent.getPositionName() ==  null || requestEvent.getPositionName().equals(BLANK))
            && (requestEvent.getProgramSectionId() == null || requestEvent.getProgramSectionId().equals(BLANK))
            && (requestEvent.getProgramUnitId() == null || requestEvent.getProgramUnitId().equals(BLANK))
            && (requestEvent.getCstsOfficerTypeId() == null || requestEvent.getCstsOfficerTypeId().equals(BLANK))
            && (requestEvent.getStaffLogonId() == null || requestEvent.getStaffLogonId().equals(BLANK))
            && (requestEvent.getWorkGroupName() == null || requestEvent.getWorkGroupName().equals(BLANK))
            && (requestEvent.getStaffLastName() !=  null && !requestEvent.getStaffLastName().equals(BLANK))
            && (requestEvent.getStaffPositionId() == null || requestEvent.getStaffPositionId().equals(BLANK))){
            searchByUserOnly = true;
        }
        return searchByUserOnly;
    }

    /**
     * @param userProfilesMap
     * @param staffPositions
     * @return
     */
    private Collection filterByName(Map userProfilesMap, Collection staffPositions) {
        Collection filteredStaffPositions = new ArrayList();
        Iterator iter = staffPositions.iterator();
        if (iter != null && iter.hasNext()){
            CSCDOrgStaffWorkgroup oswStaff = null;
            CSCDOrganizationStaffPosition osStaff = null;
            Object obj = null;
            while (iter.hasNext()){
                obj = iter.next();
                if (obj instanceof CSCDOrgStaffWorkgroup){
                	oswStaff = (CSCDOrgStaffWorkgroup) obj;
                	if (oswStaff.getUserProfileId() != null && !oswStaff.getUserProfileId().equals(BLANK)){
                		if (userProfilesMap.get(oswStaff.getUserProfileId()) != null){
                			filteredStaffPositions.add(oswStaff);
                		}
                	}
                } else{
                	osStaff = (CSCDOrganizationStaffPosition) obj;
                	if (osStaff.getUserProfileId() != null && !osStaff.getUserProfileId().equals(BLANK)){
                		if (userProfilesMap.get(osStaff.getUserProfileId()) != null){
                			filteredStaffPositions.add(osStaff);
                		}
                	}
               	
                }
            }
        }
        return filteredStaffPositions;
    }

    /**
     * @param reqEvent
     * @return
     */
    private Map getUsersByName(GetCSCDSupervisionStaffEvent reqEvent) {
        GetUsersEvent getUsersEvent = new GetUsersEvent();
        getUsersEvent.setAgencyId(reqEvent.getAgencyId());
        getUsersEvent.setFirstName(reqEvent.getStaffFirstName());
        getUsersEvent.setMiddleName(reqEvent.getStaffMiddleName());
        getUsersEvent.setLastName(reqEvent.getStaffLastName());
        
        return CSCDStaffPositionHelper.getMatchingUserProfiles(getUsersEvent);
    }

    /**
     * @param event
     * @roseuid 460BD54800ED
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD54800FA
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 460BFAA2033A
     */
    public void update(Object updateObject) {

    }
}
