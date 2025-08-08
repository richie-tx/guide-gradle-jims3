package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import messaging.cscdstaffposition.GetStaffPositionsEvent;
import messaging.cscdstaffposition.GetUsersForCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrgStaffWorkgroup;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;

public class GetUsersForCSCDSupervisionStaffCommand implements ICommand {
    private static String BLANK = "";
    /**
     * @roseuid 460BFAA300C9
     */
    public GetUsersForCSCDSupervisionStaffCommand() {

    }

    /**
     * @param event
     * @roseuid 460BD5490224
     */
    public void execute(IEvent event) {
        GetUsersForCSCDSupervisionStaffEvent reqEvent = (GetUsersForCSCDSupervisionStaffEvent) event;
        if (reqEvent.getAgencyId() == null || reqEvent.getAgencyId().equals(PDConstants.BLANK)){
        	reqEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        }
        if (reqEvent.getStaffLogonId() != null
                && !reqEvent.getStaffLogonId().trim().equals(BLANK)
                && !otherSearchCriteriaEntered(reqEvent)) {
            this.searchByUserId(reqEvent);
        } else {
            this.processMultipleSearchCriteria(reqEvent);
        }
    }

    /**
     * @param reqEvent
     * @return
     */
    private boolean otherSearchCriteriaEntered(GetUsersForCSCDSupervisionStaffEvent reqEvent) {
        boolean otherSearchCriteriaEntered = false;
        if (reqEvent.getCjadNum() != null && !reqEvent.getCjadNum().trim().equals(BLANK)
                && reqEvent.getPositionName() != null && !reqEvent.getPositionName().trim().equals(BLANK)
                && reqEvent.getStaffLogonId() != null && !reqEvent.getStaffLogonId().trim().equals(BLANK)
                && reqEvent.getStaffLastName() != null && !reqEvent.getStaffLastName().trim().equals(BLANK)
                && reqEvent.getCstsOfficerTypeId() != null && !reqEvent.getCstsOfficerTypeId().trim().equals(BLANK)){
            otherSearchCriteriaEntered = true;
        }
        return otherSearchCriteriaEntered;
    }
    /**
     * @param reqEvent
     * @return
     */
    private boolean nonLogonIdSearchCriteriaEntered(GetUsersForCSCDSupervisionStaffEvent reqEvent) {
        boolean otherSearchCriteriaEntered = false;
//        if (reqEvent.getCjadNum() != null && !reqEvent.getCjadNum().trim().equals(BLANK)
//                || (reqEvent.getPositionName() != null && !reqEvent.getPositionName().trim().equals(BLANK))
//                || (reqEvent.getStaffLastName() != null && !reqEvent.getStaffLastName().trim().equals(BLANK))
//                || (reqEvent.getCstsOfficerTypeId() != null && !reqEvent.getCstsOfficerTypeId().trim().equals(BLANK))){
//            otherSearchCriteriaEntered = true;
//        }
        if((reqEvent.getStaffLastName() != null && reqEvent.getStaffLastName().trim().length() > 0) ||
        	(reqEvent.getStaffFirstName() != null && reqEvent.getStaffFirstName().trim().length() > 0) ||
        	(reqEvent.getStaffMiddleName() != null && reqEvent.getStaffMiddleName().trim().length() > 0) ||
	        (reqEvent.getCjadNum() != null && reqEvent.getCjadNum().trim().length() > 0) ||
	        (reqEvent.getPositionName() != null && reqEvent.getPositionName().trim().length() > 0) || 
	        (reqEvent.getCstsOfficerTypeId() != null && reqEvent.getCstsOfficerTypeId().trim().length() > 0) ||
	        (reqEvent.getWorkgroupName() != null && reqEvent.getWorkgroupName().trim().length() > 0)){
        	
	            otherSearchCriteriaEntered = true;
        }
        return otherSearchCriteriaEntered;
    }

    /**
     * @param reqEvent
     */
    private void processMultipleSearchCriteria(GetUsersForCSCDSupervisionStaffEvent reqEvent) {
        
    	Map userProfileMap = null;
    	Collection logonIds = new ArrayList();
        CSCDOrgStaffWorkgroup staffPosition = null;
        CSCDSupervisionStaffResponseEvent cre = null;
        Iterator iter = null;
        UserProfile userProfile = null;
        boolean M204SearchMade = false;
    	
        GetStaffPositionsEvent getStaffPositionsEvent = new GetStaffPositionsEvent();
        getStaffPositionsEvent.setAgencyId(reqEvent.getAgencyId());
        getStaffPositionsEvent.setCstsOfficerTypeId(reqEvent.getCstsOfficerTypeId());

    	// If last name is a search parameter, do a search on 204
        if (reqEvent.getStaffLastName() != null && reqEvent.getStaffLastName().trim().length() > 1) {
            userProfileMap = this.getMatchingUserProfiles(reqEvent);
            M204SearchMade = true;
            if(userProfileMap != null){
            	logonIds = userProfileMap.keySet();
            } 
        }
        // if 204 returns nothing, no need to perform additional searches
        if ((M204SearchMade == true && logonIds.size() > 0) || (M204SearchMade == false)){
	        if (reqEvent.getStaffLogonId() != null && reqEvent.getStaffLogonId().trim().length() > 1){
	            logonIds.add(reqEvent.getStaffLogonId().toUpperCase());
	        }
	        getStaffPositionsEvent.setLogonIds(logonIds);
	
	        if (reqEvent.getCjadNum() != null){
	            getStaffPositionsEvent.setCjadNum(reqEvent.getCjadNum().toUpperCase());
	        }
	        
	        if (reqEvent.getPositionName() != null){
	            getStaffPositionsEvent.setPositionName(reqEvent.getPositionName().toUpperCase());
	        }
	        
	        if (reqEvent.getWorkgroupName() != null){
	            getStaffPositionsEvent.setWorkgroupName(reqEvent.getWorkgroupName().toUpperCase());
	        }
	
	        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(reqEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
	        getStaffPositionsEvent.setStatusId(aCode.getOID());
	        
	        if (nonLogonIdSearchCriteriaEntered(reqEvent) || logonIds.size() > 0){
	        	
	            iter = CSCDOrgStaffWorkgroup.findAll(getStaffPositionsEvent);
	            
	            if (iter != null && iter.hasNext()){
	                while (iter.hasNext()){
	                    staffPosition = (CSCDOrgStaffWorkgroup) iter.next();
	                    if (staffPosition.getUserProfileId() != null && !staffPosition.getUserProfileId().equals(BLANK)){
	                    	cre = CSCDStaffPositionHelper.getBaseStaffResponseEvent(staffPosition, null, true);
	                    	EventManager.getSharedInstance(EventManager.REPLY).postEvent(cre);
	                    	if (staffPosition.getUserProfileId() != null && !staffPosition.getUserProfileId().trim().equals(BLANK)){
	                    		if (userProfileMap != null && userProfileMap.get(staffPosition.getUserProfileId())!= null){
	                    			userProfileMap.remove(staffPosition.getUserProfileId());
	                    		}
	                    	}
	                    }
	                }
	                if(userProfileMap != null && userProfileMap.size() > 0){
	                	Collection userProfiles = userProfileMap.values();
	                	iter = userProfiles.iterator();
	                	while (iter.hasNext()){
	                		userProfile = (UserProfile) iter.next();
	                		cre = CSCDStaffPositionHelper.getStaffResponseEvent(userProfile, true);
	                		EventManager.getSharedInstance(EventManager.REPLY).postEvent(cre);
	                	}
	                }
	            } else if(userProfileMap != null && userProfileMap.size() > 0) {
	            	//Users match search criteria and do not currently occupy a position.
	            	Collection userProfiles = userProfileMap.values();
	            	iter = userProfiles.iterator();
	            	while (iter.hasNext()){
	            		userProfile = (UserProfile) iter.next();
	            		cre = CSCDStaffPositionHelper.getStaffResponseEvent(userProfile, true);
	            		EventManager.getSharedInstance(EventManager.REPLY).postEvent(cre);
	            	}
	            }
	        }
        }     
    }

    /**
     * @param reqEvent
     * @return
     */
    private Map getMatchingUserProfiles(GetUsersForCSCDSupervisionStaffEvent reqEvent) {
        GetUsersEvent getUsersEvent = new GetUsersEvent();
        getUsersEvent.setAgencyId(reqEvent.getAgencyId());
        getUsersEvent.setFirstName(reqEvent.getStaffFirstName());
        getUsersEvent.setMiddleName(reqEvent.getStaffMiddleName());
        getUsersEvent.setLastName(reqEvent.getStaffLastName());
        getUsersEvent.setAgencyId(reqEvent.getAgencyId());
        Map userMap = CSCDStaffPositionHelper.getMatchingUserProfiles(getUsersEvent); 
        return userMap;
    }

    /**
     * @param staffLogonId
     */
    private void searchByUserId(GetUsersForCSCDSupervisionStaffEvent requestEvent) {
        
        GetStaffPositionsEvent reqEvent = new GetStaffPositionsEvent();
        if (requestEvent.getStaffLogonId() != null){
            reqEvent.addLogonId(requestEvent.getStaffLogonId().toUpperCase());
        }
        if (requestEvent.getAgencyId() != null && !requestEvent.getAgencyId().equals(BLANK)){
            reqEvent.setAgencyId(requestEvent.getAgencyId());
        } else {
            reqEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        }
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(reqEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
        reqEvent.setStatusId(aCode.getOID());

        Iterator iter = CSCDOrgStaffWorkgroup.findAll(reqEvent);
        CSCDOrgStaffWorkgroup staffPos = null;
        CSCDSupervisionStaffResponseEvent cre = null;
        
        if (iter != null && iter.hasNext()){
            while (iter.hasNext()){
                staffPos = (CSCDOrgStaffWorkgroup) iter.next();
                cre = CSCDStaffPositionHelper.getBaseStaffResponseEvent(staffPos, null, true);
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(cre);
            }
        } else {
            GetUsersEvent getUsersEvent = new GetUsersEvent();
            getUsersEvent.setAgencyId(requestEvent.getAgencyId());
            getUsersEvent.setLogonId(requestEvent.getStaffLogonId());
            Map userMap = CSCDStaffPositionHelper.getMatchingUserProfiles(getUsersEvent);
            if (userMap.size() > 0){
                UserProfile userProfile = (UserProfile) userMap.get(requestEvent.getStaffLogonId().toUpperCase());
                cre = CSCDStaffPositionHelper.getStaffResponseEvent(userProfile, true);
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(cre);
            }
        }
    }

    /**
     * @param event
     * @roseuid 460BD5490231
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD5490233
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 460BFAA300E8
     */
    public void update(Object updateObject) {

    }
}
