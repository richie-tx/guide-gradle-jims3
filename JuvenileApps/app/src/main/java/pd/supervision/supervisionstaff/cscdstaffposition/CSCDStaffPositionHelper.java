/*
 * Created on Apr 11, 2007
 *
 */
package pd.supervision.supervisionstaff.cscdstaffposition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.CasenoteStatus;
import naming.UIConstants;

import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.Court;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.CaseAssignmentBuilder;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.supervision.legacyupdates.ILegacyUpdates;
import pd.supervision.legacyupdates.LegacyUpdatesImpl;
import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;
import messaging.administercaseload.GetCaseloadEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.UpdateCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.cscdstaffposition.GetOrganizationStaffPositionByPOIEvent;
import messaging.cscdstaffposition.GetStaffPositionEvent;
import messaging.cscdstaffposition.VacateStaffPositionEvent;
import messaging.cscdstaffposition.VerifyCjadNumEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.cscdstaffposition.reply.DuplicateCjadNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.legacycscdstaff.UpdateLegacyStaffAssignmentEvent;
import messaging.legacyupdates.UpdateAssignmentDataEvent;
import messaging.manageworkgroup.GetWorkGroupsByUserEvent;
import messaging.user.GetUsersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 *  
 */
public class CSCDStaffPositionHelper
{
    private static String ALL = "ALL";
    private static String NO_OFFICER_ASSIGNED = "NO OFFICER ASSIGNED";
    private static int ZERO = 0;
    private static String NO_USER = "NO USER PROFILE FOUND";
    /**
     * @param courts
     * @return
     */
    public static Map buildCourtsMap(Collection courts)
    {
        Map courtsMap = new HashMap();
        if (courts != null && courts.size() > 0)
        {
            Iterator iter = courts.iterator();
            Court court = null;
            while (iter.hasNext())
            {
                court = (Court) iter.next();
                courtsMap.put(court.getOID(), court.getOID());
            }
        }
        return courtsMap;
    }
    /**
     * Creates a casenote stating change in officer for supervisee.
     * @param defendantId
     * @param notes
     */
    public static void createCasenote(String agencyId, String defendantId, String notes) {
        SupervisionPeriod supPer = SupervisionPeriod.findActiveSupervisionPeriod(defendantId, agencyId);
        
        if (supPer != null){
            UpdateCasenoteEvent updateEvent = new UpdateCasenoteEvent();
            updateEvent.setAgencyId(agencyId);
            updateEvent.setCasenoteStatusId(CasenoteStatus.COMPLETE);
            updateEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
            updateEvent.setCasenoteStatusId(CasenoteStatus.COMPLETE);
            updateEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
            updateEvent.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
            updateEvent.setEntryDate(new Date());
            updateEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
            updateEvent.setNotes(notes);
            Collection subjects = new ArrayList();
            subjects.add(PDCodeTableConstants.CASENOTE_SUBJ_ID_ASSIGNMENT);
            updateEvent.setSubjects(subjects);
            updateEvent.setSupervisionPeriodId(supPer.getOID());
            updateEvent.setSuperviseeId(defendantId);
            Casenote casenote = new Casenote();
            casenote.updateCasenote(updateEvent);
        }
    }

    /**
     * @param staffProfile
     */
    public static void deleteStaffProfile(CSCDStaffProfile staffProfile) {
        if (staffProfile != null) {
        	staffProfile.delete();
         }
    }
    /**
     * @param staffPosition
     * @param parentPosition
     * @return
     */
    public static CSCDSupervisionStaffResponseEvent getBaseStaffResponseEvent(CSCDStaffPosition staffPosition, CSCDStaffPosition parentPosition)
    {
        CSCDSupervisionStaffResponseEvent sre = null;
        if (staffPosition != null)
        {
            sre = new CSCDSupervisionStaffResponseEvent();
            CSCDStaffProfile staffProfile = staffPosition.getStaffProfile();
            if (staffProfile != null)
            {
                sre.setCjadNum(staffProfile.getCjadNum());
            }
            sre.setEffectiveDate(staffPosition.getEffectiveDate());                            
            sre.setAssignedLogonId(staffPosition.getUserProfileId());
            sre.setAssignedName(CSCDStaffPositionHelper.getUserName(staffPosition.getUserProfileId()));
            sre.setHasCaseload(staffPosition.getHasCaseload());
            SupervisionCode aCode = null;
            
            if (staffPosition.getJobTitleCode() == null){ //jobtitleCode coming back null in attribute query getChildren().
            	aCode = staffPosition.getJobTitle();
            	if (aCode != null){
            		sre.setJobTitleId(aCode.getCode());
            		sre.setJobTitleCode(aCode.getCode());
            		sre.setJobTitleDesc(aCode.getDescription());
            	} else {
            		}
            	}
            else{
                sre.setJobTitleId(staffPosition.getJobTitleCode());  
                sre.setJobTitleCode(staffPosition.getJobTitleCode());
                sre.setJobTitleDesc(staffPosition.getJobTitleDesc());
            }

            if (staffPosition.getPositionTypeDesc() == null){
            	aCode = staffPosition.getPositionType();
            	if (aCode != null){
            		sre.setPositionTypeId(aCode.getCode());
            		sre.setPositionTypeDesc(aCode.getDescription());
            	} else {
            		}
            	}
            else {
            	sre.setPositionTypeId(staffPosition.getPositionTypeCode());
            	sre.setPositionTypeDesc(staffPosition.getPositionTypeDesc());
            }
            sre.setCstsOfficerTypeId(staffPosition.getCstsOfficerTypeId());
            if (staffPosition.getOfficerTypeDesc() != null){
            	sre.setOfficerTypeDesc(staffPosition.getOfficerTypeDesc());
            } else {
            	aCode = staffPosition.getCstsOfficerType();
            	if (aCode != null){
            		sre.setOfficerTypeDesc(aCode.getDescription());
            	}
            }
            
            sre.setLocationDetails(staffPosition.getLocationDetails());
            sre.setLocationId(staffPosition.getLocationId());
            //sre.setOrganizationId(staffPosition.getOrganizationId());
            if(staffPosition instanceof CSCDOrgStaffWorkgroup){
            	CSCDOrgStaffWorkgroup myOrgStaffPos=(CSCDOrgStaffWorkgroup)staffPosition;
            	if (myOrgStaffPos.getSectionId()!=null && !myOrgStaffPos.getSectionId().equals("") 
            	   && !myOrgStaffPos.getSectionId().equals("0")){
            	    sre.setOrganizationId(myOrgStaffPos.getSectionId());
            	} else if (myOrgStaffPos.getProgramUnitId()!=null && !myOrgStaffPos.getProgramUnitId().equals("") && !myOrgStaffPos.getProgramUnitId().equals("0")){
            	           sre.setOrganizationId(myOrgStaffPos.getProgramUnitId());
            	} else 
            	    sre.setOrganizationId(staffPosition.getOrganizationId()); 
            } else if (staffPosition instanceof CSCDOrganizationStaffPosition){
            	CSCDOrganizationStaffPosition myOrgStaffPos=(CSCDOrganizationStaffPosition)staffPosition;
            	if (myOrgStaffPos.getProgramSectionId()!=null && !myOrgStaffPos.getProgramSectionId().equals("") 
            	   && !myOrgStaffPos.getProgramSectionId().equals("0")){
            	    sre.setOrganizationId(myOrgStaffPos.getProgramSectionId());
            	} else if (myOrgStaffPos.getProgramUnitId()!=null && !myOrgStaffPos.getProgramUnitId().equals("") && !myOrgStaffPos.getProgramUnitId().equals("0")){
            	           sre.setOrganizationId(myOrgStaffPos.getProgramUnitId());
            	} else 
            	    sre.setOrganizationId(staffPosition.getOrganizationId()); 
           	
            } else {
                sre.setOrganizationId(staffPosition.getOrganizationId());
            }

            sre.setParentPositionId(staffPosition.getParentPositionId());
            sre.setPhoneNum(staffPosition.getPhoneNum());
            sre.setPositionName(staffPosition.getPositionName());
           
            sre.setProbationOfficerInd(staffPosition.getProbationOfficerInd());
            sre.setStaffPositionId(staffPosition.getOID());
            
            if (staffPosition.getPositionStatusCode() != null){
                sre.setPositionStatusId(staffPosition.getPositionStatusCode());
                sre.setPositionStatusDesc(staffPosition.getPositionStatusDesc());
            } else {
            	aCode = staffPosition.getStatus();
            	if (aCode != null){
            		sre.setPositionStatusDesc(aCode.getDescription());
            		sre.setPositionStatusId(aCode.getCode());
            	}
            }
            
            sre.setSupervisorId(staffPosition.getParentPositionId());
            sre.setParentPositionDesc(staffPosition.getParentPositionDesc());
            if (parentPosition != null){
                sre.setSupervisorName(CSCDStaffPositionHelper.getUserName(parentPosition.getUserProfileId()));
                sre.setSupervisorLogonId(parentPosition.getUserProfileId());
                sre.setSupervisorPoi(parentPosition.getProbationOfficerInd());
                sre.setParentPositionDesc(parentPosition.getPositionName());
            }
            if (staffPosition.getCourts() != null && staffPosition.getCourts().size() > 0){
                Iterator iter = staffPosition.getCourts().iterator();
                Court court = null;
                while (iter.hasNext()){
                    court = (Court) iter.next();
                    if ( court!= null ){
                    	
                        sre.addCourt(court.getOID());
                    }
                }
            }
        }
        return sre;
    }
    /**
     * @param staffPosition
     * @param parentPosition
     * @param needWorkgroups
     * @return
     */
    public static CSCDSupervisionStaffResponseEvent getBaseStaffResponseEvent(CSCDStaffPosition staffPosition, CSCDStaffPosition parentPosition, boolean needWorkgroups){
        CSCDSupervisionStaffResponseEvent sre = CSCDStaffPositionHelper.getBaseStaffResponseEvent(staffPosition, parentPosition);
        if (needWorkgroups){
            Collection workGroups = CSCDStaffPositionHelper.getWorkGroupsByUser(staffPosition.getUserProfileId());
            sre.setWorkGroups(workGroups);
        }
        return sre;
    }
    /**
     * @param staffPos
     */
    public static String getCourtNumFromStaffPosition(CSCDStaffPosition staffPos) {
        List courtList = (List) staffPos.getCourts();
        String courtNum = null;
        if (courtList.size() == 0){
            SupervisionCode jobTitle = staffPos.getJobTitle();
            if (jobTitle.getCode().equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER)){
                courtNum = ALL;
            } 
        }

        if (courtList.size() > 1){
            courtNum = ALL;
        } else if (courtList.size() > ZERO) {
            Court court = (Court) courtList.get(ZERO);
            courtNum = CSCDStaffPositionHelper.padIt(court.getCourtNumber(), 3);
        }
        return courtNum;
    }
    /**
     * @param requestEvent
     * @return
     */
    public static Iterator getCourtStaffPositions(GetCourtStaffPositionEvent requestEvent){
        
	    GetCourtStaffPositionEvent evtWithResolvedCodes = new GetCourtStaffPositionEvent();
	    evtWithResolvedCodes.setCourtId(requestEvent.getCourtId());
	    evtWithResolvedCodes.setReturnAssignedPositionsOnly(requestEvent.isReturnAssignedPositionsOnly());
	    String agencyId = null;
	    
	    if (requestEvent.getAgencyId() == null || requestEvent.getAgencyId().equals(PDConstants.BLANK)){
	        agencyId = PDSecurityHelper.getUserAgencyId();
	    } else {
	        agencyId = requestEvent.getAgencyId();
	    }
	    evtWithResolvedCodes.setAgencyId(agencyId);
	    SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
	    evtWithResolvedCodes.setStatusId((String) aCode.getOID());
	    aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, PDCodeTableConstants.STAFF_JOB_TITLE, requestEvent.getJobTitleId());
	    evtWithResolvedCodes.setJobTitleId((String) aCode.getOID());
	    
		return CourtStaffPosition.findAll(evtWithResolvedCodes);
       
    }
    /**
     * Returns division manager (highest level) for a given organizationId
     * 
     * @param organizationId
     * @return
     */
    public static CSCDStaffPosition getDivisionManager(GetStaffPositionEvent getDivisionManagerEvent)
    {

        CSCDOrganizationStaffPosition orgStaffPosition = null;
        CSCDStaffPosition staffPosition = null;
        
        if (getDivisionManagerEvent.getAgencyId() != null && getDivisionManagerEvent.getAgencyId().equals(PDConstants.BLANK))
        {
            getDivisionManagerEvent.setAgencyId(getDivisionManagerEvent.getAgencyId());
        }
        else
        {
            getDivisionManagerEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        }
        SupervisionCode divMgrCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(getDivisionManagerEvent.getAgencyId(), PDCodeTableConstants.STAFF_POSITION_TYPE, PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR);
        if (getDivisionManagerEvent.getStatusId() == null || getDivisionManagerEvent.getStatusId().equals(PDConstants.BLANK)){
            SupervisionCode statusCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(getDivisionManagerEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
            getDivisionManagerEvent.setStatusId(statusCode.getOID());
        }
        
        if (divMgrCode != null){
            getDivisionManagerEvent.setPositionTypeId(divMgrCode.getOID());

            Iterator iter = CSCDOrganizationStaffPosition.findAll(getDivisionManagerEvent);
            if (iter != null && iter.hasNext())
            {
                orgStaffPosition = (CSCDOrganizationStaffPosition) iter.next();
                staffPosition = orgStaffPosition.getStaffPosition();
            }
        }
        return staffPosition;
    }

    /**
     * @param staffPos
     * @param cjadNum
     * @return
     */
    public static UpdateLegacyStaffAssignmentEvent getLegacyStaffUpdateEvent(
    		CSCDStaffPosition staffPos, String cjadNum, boolean vacating){
        UpdateLegacyStaffAssignmentEvent reqEvent = new UpdateLegacyStaffAssignmentEvent();
        if (cjadNum == null && !vacating){
            CSCDStaffProfile staffProfile = staffPos.getStaffProfile();
            if (staffProfile != null){
                cjadNum = staffProfile.getCjadNum();
            }
        }
        reqEvent.setCjadNum(cjadNum);
        reqEvent.setCourtNum(CSCDStaffPositionHelper.getCourtNumFromStaffPosition(staffPos));
        Iterator iter = CSCDOrganizationStaffPosition.findAll("staffPositionId", staffPos.getOID());

        if (iter.hasNext()){
        	CSCDOrganizationStaffPosition staffPosOrg = (CSCDOrganizationStaffPosition) iter.next();
        	Organization programUnitOrg = staffPosOrg.getProgramUnit();
        	if (programUnitOrg != null){
        		reqEvent.setLegacyProgramUnit(programUnitOrg.getLegacyProgramUnit());
        	}
        }
        if (staffPos.getLocationId() != null){
        	Location loc = staffPos.getLocation();
        	if (loc != null){
        		reqEvent.setLocation(loc.getLocationName());
        	}
        }
        String phoneNum = staffPos.getPhoneNum();
        if (phoneNum != null && phoneNum.length() == 10){
        		phoneNum = phoneNum.substring(3);
        		reqEvent.setPhoneNum(phoneNum);
        } else {
        	reqEvent.setPhoneNum(phoneNum);
        }
        reqEvent.setPoi(staffPos.getProbationOfficerInd());   
        
        UserProfile userProfile = staffPos.getUserProfile();
        Name officerName = new Name();
        if (userProfile != null){
            officerName.setFirstName(userProfile.getFirstName());
            officerName.setLastName(userProfile.getLastName());
            officerName.setMiddleName(userProfile.getMiddleName());
            reqEvent.setOfficerName(officerName);
        } else if (staffPos.getUserProfileId() == null || staffPos.getUserProfileId().equals(PDConstants.BLANK)){
            officerName.setLastName(NO_OFFICER_ASSIGNED);
            reqEvent.setOfficerName(officerName);
        } else {
        	officerName.setLastName(NO_USER);
        	reqEvent.setOfficerName(officerName);
        }
        
        return reqEvent;
    }

    /**
     * @param getUsersEvent
     * @return
     */
    public static Map getMatchingUserProfiles(GetUsersEvent getUsersEvent)
    {

        if (getUsersEvent.getAgencyId() == null || getUsersEvent.getAgencyId().equals(PDConstants.BLANK))
        {
            getUsersEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        }

      //  Iterator iter = UserProfile.findAll(getUsersEvent);
        Map userMap = new HashMap();
     /*   UserProfile userProfile = null;
        if (iter != null && iter.hasNext())
        {
            while (iter.hasNext())
            {
                userProfile = (UserProfile) iter.next();
                if (userMap.get(userProfile.getLogonId()) == null)
                {
                    userMap.put(userProfile.getLogonId(), userProfile);
                }
            }
        }*/ //87191
        return userMap;
    }
    /**
     * @param orgStaffWorkgroup
     * @return
     */
    public static CSCDSupervisionStaffResponseEvent getSlimStaffResponseEvent(CSCDOrgStaffWorkgroup orgStaffWorkgroup){
        CSCDSupervisionStaffResponseEvent sre = new CSCDSupervisionStaffResponseEvent();
        sre.setAssignedLogonId(orgStaffWorkgroup.getUserProfileId());
        sre.setCjadNum(orgStaffWorkgroup.getCjadNum());
        sre.setAssignedName(CSCDStaffPositionHelper.getUserName(orgStaffWorkgroup.getUserProfileId()));
        sre.setJobTitleId(orgStaffWorkgroup.getJobTitleId());
        sre.setPositionTypeId(orgStaffWorkgroup.getPositionTypeId());
        sre.setPositionName(orgStaffWorkgroup.getPositionName());
        sre.setDivisionId(orgStaffWorkgroup.getOrganizationId());
        sre.setDivisionName(orgStaffWorkgroup.getOrganizationName());
        sre.setProgramUnitId(orgStaffWorkgroup.getProgramUnitId());
        sre.setProgramUnitName(orgStaffWorkgroup.getUnitName());
        sre.setSectionId(orgStaffWorkgroup.getSectionId());
        sre.setSectionName(orgStaffWorkgroup.getSectionName());
        return sre;
    }
    /**
     * @param poi
     * @return
     */
    public static CSCDOrganizationStaffPosition getStaffPositionByPOI(String poi){
        GetOrganizationStaffPositionByPOIEvent getByPOIEvent = new GetOrganizationStaffPositionByPOIEvent();
        getByPOIEvent.setAgencyId(UIConstants.CSC);
        getByPOIEvent.setProbationOfficerId(poi);
        CSCDOrganizationStaffPosition staffPos = null;
        List probOffList = CollectionUtil.iteratorToList(CSCDOrganizationStaffPosition.findAll(getByPOIEvent));
        if (probOffList != null && probOffList.size() > 0){
            staffPos = (CSCDOrganizationStaffPosition)probOffList.get(0);
        }
        return staffPos;
    }

    /**
     * Creates staff response event from staff position entity.
     * 
     * @param staffPosition
     * @param parentPosition
     * @return
     */
    public static CSCDSupervisionStaffResponseEvent getStaffResponseEvent(CSCDStaffPosition staffPosition, CSCDStaffPosition parentPosition)
    {
        CSCDSupervisionStaffResponseEvent sre = null;
        if (staffPosition != null)
        {
            sre = CSCDStaffPositionHelper.getBaseStaffResponseEvent(staffPosition, parentPosition);
            if (staffPosition.getChildStaffPositions() != null && staffPosition.getChildStaffPositions().size() > 0)
            {
                Iterator iter = staffPosition.getChildStaffPositions().iterator();
                CSCDStaffPosition childStaffPos = null;
                CSCDSupervisionStaffResponseEvent childResponse = null;
                while (iter.hasNext())
                {
                    childStaffPos = (CSCDStaffPosition) iter.next();
                    //Application problem was creating staff positions with parent position of itself.  The problem
                    //has been fixed, but this check was put in as a safeguard.
                    if (!childStaffPos.getOID().equals(staffPosition.getOID())){
                    	childResponse = CSCDStaffPositionHelper.getStaffResponseEvent(childStaffPos, staffPosition);
                    	sre.addChild(childResponse);
                    }
                }
            }
        }
        return sre;
    }

    public static CSCDSupervisionStaffResponseEvent getLightStaffResponseEvent(CSCDStaffPosition staffPosition){
    	 CSCDSupervisionStaffResponseEvent sre = null;
         if (staffPosition != null)
         {
             sre = new CSCDSupervisionStaffResponseEvent();
             sre.setAssignedLogonId(staffPosition.getUserProfileId());
             if (staffPosition.getOfficerTypeDesc() == null){
            	 SupervisionCode aCode = staffPosition.getCstsOfficerType();
            	 if (aCode != null){
            		 sre.setCstsOfficerTypeId(aCode.getCode());
            		 sre.setOfficerTypeDesc(aCode.getDescription());
            	 }else {
            	 }
         	} else {
                sre.setOfficerTypeDesc(staffPosition.getOfficerTypeDesc());
         	}
             sre.setStaffPositionId(staffPosition.getOID());
             
             	//added to improvate search positions performance issues
             if (staffPosition.getJobTitleCode() == null){
                 SupervisionCode aCode = staffPosition.getJobTitle();
                 if (aCode != null){
                     sre.setJobTitleId(aCode.getCode());
                	 sre.setJobTitleCode(aCode.getCode());
                	 sre.setJobTitleDesc(aCode.getDescription());
                 } else {}

             } else {
            	 sre.setJobTitleId(staffPosition.getJobTitleCode());
            	 sre.setJobTitleCode(staffPosition.getJobTitleCode());
            	 sre.setJobTitleDesc(staffPosition.getJobTitleDesc());
             }
             SupervisionCode aCode = staffPosition.getPositionType();
             if (staffPosition.getPositionTypeDesc() == null){
                 //SupervisionCode aCode = staffPosition.getPositionType();
                 if (aCode != null){
                     sre.setPositionTypeId(aCode.getCode());
                     sre.setPositionTypeDesc(aCode.getDescription());
                 } else {}
             } else {
            	 sre.setPositionTypeId(aCode.getCode());
            	 sre.setPositionTypeDesc(staffPosition.getPositionTypeDesc());
             }
             
             aCode = staffPosition.getStatus();
             if (aCode != null){
                 sre.setPositionStatusId(aCode.getCode());
                 sre.setPositionStatusDesc(aCode.getDescription());
             }
         }
         return sre;
    }
    
    /**
     * Creates staff response event from staff position entity.
     * 
     * @param staffPosition
     * @param supervisoryPositionsOnly
     * @return
     */
    public static CSCDSupervisionStaffResponseEvent getStaffResponseEvent(CSCDStaffPosition staffPosition, CSCDStaffPosition parentPosition, 
            boolean supervisoryPositionsOnly)
    {
        CSCDSupervisionStaffResponseEvent sre = null;
        String positionTypeId = null;
        if (staffPosition != null)
        {
            sre = CSCDStaffPositionHelper.getBaseStaffResponseEvent(staffPosition, parentPosition);
            
            if (staffPosition.getChildStaffPositions() != null && staffPosition.getChildStaffPositions().size() > 0)
            {
                Iterator iter = staffPosition.getChildStaffPositions().iterator();
                CSCDStaffPosition childStaffPos = null;
                CSCDSupervisionStaffResponseEvent childResponse = null;
                Map supPositionsMap = null;
                if (supervisoryPositionsOnly) {
                    supPositionsMap = CSCDStaffPositionHelper.getSupervisoryPositionTypeMapByOID();   
                }
                while (iter.hasNext())
                {
                    childStaffPos = (CSCDStaffPosition) iter.next();
                    positionTypeId = childStaffPos.getPositionTypeId();

                    if (supervisoryPositionsOnly)
                    {
                    	//Return supervisor, assistant supervisor positions
                         if (PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR.equals(supPositionsMap.get(positionTypeId))
                                || PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP.equals(supPositionsMap.get(positionTypeId))
                                || PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR.equals(supPositionsMap.get(positionTypeId)))
                        {
                        	childResponse = CSCDStaffPositionHelper.getStaffResponseEvent(childStaffPos, staffPosition, true);
                        	//Return active supervisory positions.
                       		if (childResponse != null && childResponse.getPositionStatusId().equals(PDCodeTableConstants.STAFF_STATUS_ACTIVE)){
                       			sre.addChild(childResponse);
                       		} else{} 
                        }else {
                            continue;
                        }
                    } else
                    {
                        childResponse = CSCDStaffPositionHelper.getStaffResponseEvent(childStaffPos, staffPosition);
                        sre.addChild(childResponse);
                    }
                }

            }
        }
        return sre;
    }
 
    /**
     * Creates staff response event from staff position entity.
     * 
     * @param staffPosition
     * @parem parentPosition
     * @param userProfileMap
     * @return
     */
    public static CSCDSupervisionStaffResponseEvent getStaffResponseEvent(CSCDStaffPosition staffPosition, CSCDStaffPosition parentPosition, 
            Map userProfileMap)
    {
        CSCDSupervisionStaffResponseEvent sre = null;
        if (staffPosition != null)
        {
            sre = new CSCDSupervisionStaffResponseEvent();
            if (staffPosition instanceof CSCDOrgStaffWorkgroup)
            {
                CSCDOrgStaffWorkgroup osw = (CSCDOrgStaffWorkgroup) staffPosition;
                sre.setCjadNum(osw.getCjadNum());
                sre.setEffectiveDate(osw.getEffectiveDate());
                sre.setDivisionName(osw.getOrganizationName());
                sre.setProgramUnitName(osw.getUnitName());
                sre.setSectionName(osw.getSectionName());
                if (osw.getSectionId() != null && !osw.getSectionId().equals(PDConstants.BLANK) && !osw.getSectionId().equals("0")){
                    sre.setOrganizationId(osw.getSectionId());
                } else if (osw.getProgramUnitId() != null && !osw.getProgramUnitId().equals(PDConstants.BLANK) && !osw.getProgramUnitId().equals("0")){
                    sre.setOrganizationId(osw.getProgramUnitId());
                } else {
                    sre.setOrganizationId(osw.getOrganizationId());
                }
            } else if (staffPosition instanceof CSCDOrganizationStaffPosition){
            	CSCDOrganizationStaffPosition osw = (CSCDOrganizationStaffPosition) staffPosition;
                sre.setCjadNum(osw.getCjadNum());
                sre.setEffectiveDate(osw.getEffectiveDate());
                sre.setDivisionName(osw.getDivisionName());
                sre.setProgramUnitName(osw.getProgramUnitName());
                sre.setSectionName(osw.getSectionName());
                if (osw.getProgramSectionId() != null && !osw.getProgramSectionId().equals(PDConstants.BLANK) && !osw.getProgramSectionId().equals("0")){
                    sre.setOrganizationId(osw.getProgramSectionId());
                } else if (osw.getProgramUnitId() != null && !osw.getProgramUnitId().equals(PDConstants.BLANK) && !osw.getProgramUnitId().equals("0")){
                    sre.setOrganizationId(osw.getProgramUnitId());
                } else {
                    sre.setOrganizationId(osw.getOrganizationId());
                }
            } else if (staffPosition instanceof CSCDStaffPosition){
                CSCDStaffPosition osw = (CSCDStaffPosition) staffPosition;
                Iterator profileIter = CSCDStaffProfile.findAll("OID", osw.getStaffProfileId() );
                if ( profileIter.hasNext()){
                	
                	CSCDStaffProfile profile = (CSCDStaffProfile) profileIter.next();
                	sre.setCjadNum( profile.getCjadNum() );
                }
                sre.setEffectiveDate(osw.getEffectiveDate());
                sre.setOrganizationId(osw.getOrganizationId());
            }else {
                sre.setOrganizationId(staffPosition.getOrganizationId());
                if (staffPosition.getStaffProfileId() != null && !staffPosition.getStaffProfileId().equals(PDConstants.BLANK)){

                    CSCDStaffProfile staffProfile = staffPosition.getStaffProfile();
                    if (staffProfile != null){
                    	sre.setCjadNum(staffProfile.getCjadNum());
                    }
                }
            }
            sre.setAssignedLogonId(staffPosition.getUserProfileId());
            if (userProfileMap != null && staffPosition.getUserProfileId() != null
                    && !staffPosition.getUserProfileId().equals(PDConstants.BLANK))
            {
                Name aName = new Name();
                UserProfile userProfile = (UserProfile) userProfileMap.get(staffPosition.getUserProfileId());
                if (userProfile != null)
                {
                    aName.setFirstName(userProfile.getFirstName());
                    aName.setMiddleName(userProfile.getMiddleName());
                    aName.setLastName(userProfile.getLastName());
                    sre.setAssignedName(aName);
                }
                else
                {
                    aName.setFirstName(PDConstants.BLANK);
                    aName.setMiddleName(PDConstants.BLANK);
                    aName.setLastName(NO_USER);
                    sre.setAssignedName(aName);
                }
            }
            else
            {
                sre.setAssignedName(CSCDStaffPositionHelper.getUserName(staffPosition.getUserProfileId()));
            }
            SupervisionCode aCode = null;
            
            sre.setCstsOfficerTypeId(staffPosition.getCstsOfficerTypeId());
            if (staffPosition.getOfficerTypeDesc() == null){
            	aCode = staffPosition.getCstsOfficerType();
            	if (aCode != null){
            		sre.setOfficerTypeDesc(aCode.getDescription());
            	} else {}
            } else {
            	sre.setOfficerTypeDesc(staffPosition.getOfficerTypeDesc());
            }

            sre.setHasCaseload(staffPosition.getHasCaseload());
            if (staffPosition.getJobTitleDesc() == null){
            	aCode = staffPosition.getJobTitle();
            	if (aCode != null){
            		sre.setJobTitleCode(aCode.getCode());
            		sre.setJobTitleDesc(aCode.getDescription());
            		sre.setJobTitleId(aCode.getCode());
            	} else {}
            } else {
            	sre.setJobTitleCode(staffPosition.getJobTitleCode());
            	sre.setJobTitleId(staffPosition.getJobTitleCode());
            	sre.setJobTitleDesc(staffPosition.getJobTitleDesc());
            }
            sre.setLocationDetails(staffPosition.getLocationDetails());
            sre.setLocationId(staffPosition.getLocationId());
            sre.setParentPositionId(staffPosition.getParentPositionId());
            sre.setPhoneNum(staffPosition.getPhoneNum());
            sre.setPositionName(staffPosition.getPositionName());
          
            if (staffPosition.getPositionStatusDesc() == null){
                aCode = staffPosition.getStatus();
                if (aCode != null){
                    sre.setPositionStatusId(aCode.getCode());
                    sre.setPositionStatusDesc(aCode.getDescription());
                }
            } else {
            	sre.setPositionStatusId(staffPosition.getPositionStatusCode());
            	sre.setPositionStatusDesc(staffPosition.getPositionStatusDesc());
            }
            if (staffPosition.getPositionTypeDesc() == null){
                aCode = staffPosition.getPositionType();
                if (aCode != null){
                    sre.setPositionTypeId(aCode.getCode());
                    sre.setPositionTypeDesc(aCode.getDescription());
                } else {}
            } else {
            	sre.setPositionTypeId(staffPosition.getPositionTypeCode());
                sre.setPositionTypeDesc(staffPosition.getPositionTypeDesc());
            }
            
            sre.setProbationOfficerInd(staffPosition.getProbationOfficerInd());
            sre.setStaffPositionId(staffPosition.getOID());
            sre.setSupervisorId(staffPosition.getParentPositionId());

            if (parentPosition != null){
                sre.setSupervisorName(CSCDStaffPositionHelper.getUserName(parentPosition.getUserProfileId()));
                sre.setSupervisorLogonId(parentPosition.getUserProfileId());
                sre.setSupervisorPoi(parentPosition.getProbationOfficerInd());
                sre.setParentPositionDesc(parentPosition.getParentPositionDesc());
            } else if (staffPosition.getParentPosition() != null){
                parentPosition = staffPosition.getParentPosition();
                sre.setSupervisorName(CSCDStaffPositionHelper.getUserName(parentPosition.getUserProfileId()));
                sre.setSupervisorLogonId(parentPosition.getUserProfileId());
                sre.setSupervisorPoi(parentPosition.getProbationOfficerInd());
                sre.setParentPositionDesc(parentPosition.getParentPositionDesc());
            }

            Collection child_staff_positions = staffPosition.getChildStaffPositions();
            int num_child_pos = child_staff_positions.size();
            if ( child_staff_positions != null && num_child_pos > 0)
            {
            	List<CSCDStaffPosition> child_position_list = 
            			CollectionUtil.iteratorToList(child_staff_positions.iterator());

                CSCDStaffPosition childStaffPos = null;
                CSCDSupervisionStaffResponseEvent childResponse = null;
                for (int i=0;i<num_child_pos;i++)
                {
                    childStaffPos = child_position_list.get(i);
                    childResponse = CSCDStaffPositionHelper.getStaffResponseEvent(childStaffPos, staffPosition);
                                        sre.addChild(childResponse);
                }
            }
            
            Collection courts = staffPosition.getCourts();
            int num_courts = courts.size();
            if (courts != null && num_courts > 0){
                
            	List<Court> court_list = 
        			CollectionUtil.iteratorToList(courts.iterator());
                Court court = null;
                for (int i=0;i<num_courts;i++)
                {
                    court = court_list.get(i);
                    if (court != null)
                    {
                        sre.addCourt(court.getOID());
                    }
                }
            }
        }
        return sre;
    }

    /**
     * @param userProfile
     * @return
     */
    public static CSCDSupervisionStaffResponseEvent getStaffResponseEvent(UserProfile userProfile, boolean needWorkgroups)
    {
        CSCDSupervisionStaffResponseEvent cre = null;
        if (userProfile != null)
        {
            cre = new CSCDSupervisionStaffResponseEvent();
            cre.setAssignedLogonId(userProfile.getLogonId());
            cre.setAssignedName(CSCDStaffPositionHelper.getUserName(userProfile.getLogonId()));
        } 
        if (needWorkgroups){
            cre.setWorkGroups(CSCDStaffPositionHelper.getWorkGroupsByUser(userProfile.getLogonId()));
        }
        return cre;
    }

     /**
     * @return
     */
    public static Map getSupervisoryPositionTypeMapByOID() {
        Map aMap = new HashMap();
        String agencyId = PDSecurityHelper.getUserAgencyId();
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, PDCodeTableConstants.STAFF_POSITION_TYPE, PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR);
        aMap.put(aCode.getOID(), aCode.getCode());
        aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, PDCodeTableConstants.STAFF_POSITION_TYPE, PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR);
        aMap.put(aCode.getOID(), aCode.getCode());
        aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, PDCodeTableConstants.STAFF_POSITION_TYPE, PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP);
        aMap.put(aCode.getOID(), aCode.getCode());

        return aMap;
    }

    /**
     * Get name from a logonId.
     * 
     * @param aLogonId
     * @return
     */
    public static Name getUserName(String aLogonId)
    {
        Name aName = new Name();
        if (aLogonId != null && !aLogonId.equals(PDConstants.BLANK))
        {
            UserProfile userInfo = UserProfile.find(aLogonId);
            if(userInfo != null){
            	
            	aName.setFirstName(userInfo.getFirstName());
                aName.setMiddleName(userInfo.getMiddleName());
                aName.setLastName(userInfo.getLastName());
            } else {
            	aName.setFirstName(PDConstants.BLANK);
            	aName.setMiddleName(PDConstants.BLANK);
            	aName.setLastName(NO_USER);
            }
        }
        return aName;
    }
    /**
     * @param logonId
     * @return
     */
    public static Collection getWorkGroupsByUser(String logonId){
        Collection workGroups = new ArrayList();
        if (logonId != null && !logonId.equals(PDConstants.BLANK)){
            GetWorkGroupsByUserEvent getEvent = new GetWorkGroupsByUserEvent();
            getEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
            getEvent.setLogonId(logonId);
            Iterator iter = WorkGroup.findAll(getEvent);
            WorkGroup wg = null;
            if (iter != null && iter.hasNext()){
                while (iter.hasNext()){
                    wg = (WorkGroup) iter.next();
                    workGroups.add(wg.getName());
               }
            }
        }
        return workGroups;
    }
    /**
     * @param aString
     * @param strLength
     * @return
     */
    public static String padIt(String aString, int strLength){
        StringBuffer sb = new StringBuffer(aString);
        while (sb.length() < strLength){
            sb.insert(0,ZERO);
        }
        return sb.toString();
    }
    public static List getStaffPositionCaseload(CSCDStaffPosition staffPosition){
    	//Retrieve supervisees actively being supervised by this position
    	GetCaseloadEvent getCaseLoadEvent = new GetCaseloadEvent();
        getCaseLoadEvent.setOfficerPositionId(staffPosition.getOID());
        List caseList = CollectionUtil.iteratorToList(CaseAssignment.findAll(getCaseLoadEvent));
        return caseList;
    }
    /**
     * @param staffPosition
     * @param effectiveDate
     */
    public static List processCaseloadReassignment(CSCDStaffPosition staffPosition, Date effectiveDate) {
        
    	List caseList = getStaffPositionCaseload(staffPosition);

        if (effectiveDate == null){
        	effectiveDate = new Date();
        }
        if (caseList != null && caseList.size() > 0){
            CaseAssignment caseAssignment = null;
            CaseAssignmentTO caseAssignmentTO = null;
            for (int i = 0; i < caseList.size(); i++) {
                caseAssignment = (CaseAssignment)caseList.get(i);
                //Update case assignments upon staff position assignment or reassignment.
                //Need to indicate new officer acknowledgment and acknowledgment status of assumed.
                CaseAssignmentBuilder builder = new CaseAssignmentBuilder(caseAssignment);
                builder.build();
                caseAssignmentTO = (CaseAssignmentTO) builder.getResult();
                CSCDStaffPositionHelper.updateCaseAssignment(caseAssignmentTO, staffPosition, effectiveDate, false);
            }
        }
        return caseList;
    }
   /**
     * @param caseAssignmentOrder
     * @param staffPosition
     */
    public static void updateCaseAssignment( CaseAssignmentTO caseAssignmentBean, 
    										  CSCDStaffPosition staffPosition, 	Date effectiveDate,	boolean vacating) {
        
    	caseAssignmentBean.setAcknowledgeRoleCode(CaseloadConstants.OP_ACKNOWLEDGE_OFFICER_ASSIGNMENT);
        caseAssignmentBean.setOfficerAssignDate(effectiveDate);
        caseAssignmentBean.setAcknowledgeStatusCode(CaseloadConstants.ACKNOWLEDGMENT_STATUS_ASSUMED);
    	caseAssignmentBean.setAllocatedStaffPositionId(staffPosition.getParentPositionId());
    	caseAssignmentBean.setAssignedStaffPositionId(staffPosition.getOID());
    	caseAssignmentBean.setProgramUnitId(staffPosition.getOrganizationId());

        if (vacating){//position was being vacated
        	caseAssignmentBean.setAcknowledgeUserId(null);
        	caseAssignmentBean.setAcknowledgePositionId(null);
        	caseAssignmentBean.setAcknowledgeDate(effectiveDate);
        } else {
        	caseAssignmentBean.setAcknowledgeDate(effectiveDate);
        	caseAssignmentBean.setAcknowledgeUserId(staffPosition.getUserProfileId());
        	caseAssignmentBean.setAcknowledgePositionId(staffPosition.getOID());
        }
        UpdateCaseAssignmentEvent updateEvent = (UpdateCaseAssignmentEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);
        updateEvent.setCaseAssignmentTransactionCode(CaseloadConstants.WF_PAPERFILE_RECEIVED);
        updateEvent.setCaseAssignment(caseAssignmentBean);
        
        updateCaseAssignment(updateEvent);
        /*Not supposed to create the JZP or FAS1 case-RRY
         * But should create a T24
         */
        if ( !vacating ){
        	
        	createT24OnlyRecord( caseAssignmentBean, effectiveDate );    
        }
     }
    
    /**
     * 
     * @param event
     * @return
     */
     public static ICaseAssignment updateCaseAssignment(UpdateCaseAssignmentEvent event){
        ICaseAssignment assignmentBean = event.getCaseAssignment();
        String caseAssignmentId = assignmentBean.getCaseAssignmentId();
        CaseAssignment caseAssignment;
        if (caseAssignmentId == null) {
        	return null;
         } else {
            caseAssignment = CaseAssignment.find(caseAssignmentId);
        }
        caseAssignment.update(assignmentBean);     
        //Moved to updateCaseAssignment(CaseAssignmentTO caseAssignmentBean,CSCDStaffPosition staffPosition,Date effectiveDate,boolean vacating)
        /* if (!vacatingPosition){
        	createLegacyRecords(caseAssignment, effectiveDate);    
        } */
        
       	return assignmentBean;
    } 
	public static void createLegacyRecords(CaseAssignmentTO caseAssignment, Date effectiveDate) {
    	ILegacyUpdates icsts = new LegacyUpdatesImpl();
    	UpdateAssignmentDataEvent reqEvent = new UpdateAssignmentDataEvent();
    	String recordType = "JZP";        	
    	//reqEvent.setJims2SourceId(caseAssignment.getOID());
    	reqEvent.setJims2SourceId(caseAssignment.getCaseAssignmentId());
    	reqEvent.setRecType(recordType);
    	/* if (caseAssignment.getAcknowledgeUserId() != null){
    		reqEvent.setLogonId(caseAssignment.getAcknowledgeUserId());
    	} else {
    		reqEvent.setLogonId(PDSecurityHelper.getLogonId());
    	} */
    	reqEvent.setLogonId(PDSecurityHelper.getLogonId());
    	reqEvent.setSpn(caseAssignment.getDefendantId());        	
    	reqEvent.setCriminalCaseId(caseAssignment.getCriminalCaseId());	    	
		//CSCDStaffPosition staffPosition = caseAssignment.getAssignedStaffPosition();
		CSCDStaffPosition staffPosition = CSCDStaffPosition.find(caseAssignment.getAssignedStaffPositionId());
   		reqEvent.setProbationOfficerInd(staffPosition.getProbationOfficerInd());//POI from staff position 
		reqEvent.setTransactionDate(effectiveDate);
     	
    	try {
			icsts.create(reqEvent, recordType);
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException("Failed to create JZP type of record for officer assignment.", e);
		}

		boolean createT24 = false;
		CSCDStaffProfile staffProfile = staffPosition.getStaffProfile();
		if (staffProfile != null) {
			String cjadNumber = staffProfile.getCjadNum();
			if (cjadNumber != null && cjadNumber.trim().length() > 0) {
				createT24 = true;
			}
		}
		if (createT24) {
			recordType = "T24";	
			reqEvent.setRecType(recordType);
			reqEvent.setCjadNum(staffPosition.getStaffProfile().getCjadNum());
			//reqEvent.setAssignmentDate(caseAssignment.getOfficerAssignDate());
			reqEvent.setAssignmentDate(effectiveDate);
			try {
				icsts.create(reqEvent, recordType);
			} catch (Exception e) {		
				throw new RuntimeException("Failed to create T24/FAS1CASE types of records for officer assignment.", e);
			}    	 
		}
	    recordType = "CAS1";        	
	    reqEvent.setRecType(recordType);
	    reqEvent.setTransactionDate(null);//do not need this field for FAS1CASE
	    try {
			icsts.create(reqEvent, recordType);
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException("Failed to create FAS1CASE type of record for officer assignment.", e);
		}   
	}
	
	public static void createT24OnlyRecord( CaseAssignmentTO caseAssignment, Date effectiveDate ) {
    	ILegacyUpdates icsts = new LegacyUpdatesImpl();
    	UpdateAssignmentDataEvent reqEvent = new UpdateAssignmentDataEvent();
    	String recordType = "";
    	
		boolean createT24 = false;
		CSCDStaffPosition staffPosition = CSCDStaffPosition.find(caseAssignment.getAssignedStaffPositionId());
		CSCDStaffProfile staffProfile = staffPosition.getStaffProfile();
		if ( staffProfile != null ) {
			String cjadNumber = staffProfile.getCjadNum();
			if (cjadNumber != null && cjadNumber.trim().length() > 0) {
				createT24 = true;
			}
		}
		if ( createT24 ) {
			recordType = "T24";	
			reqEvent.setRecType(recordType);
			reqEvent.setCjadNum(staffPosition.getStaffProfile().getCjadNum());
			reqEvent.setAssignmentDate( effectiveDate );
			reqEvent.setJims2SourceId( caseAssignment.getCaseAssignmentId() );
			reqEvent.setLogonId(PDSecurityHelper.getLogonId());
	    	reqEvent.setSpn( caseAssignment.getDefendantId() );        	
	    	reqEvent.setCriminalCaseId( caseAssignment.getCriminalCaseId() );
	    	reqEvent.setProbationOfficerInd( staffPosition.getProbationOfficerInd() );//POI from staff position 
			try {
				icsts.create( reqEvent, recordType );
			} 	catch (Exception e) {		
				throw new RuntimeException("Failed to create T24 record for officer assignment.", e);
			}    	 
		} // end create t24
	}

    public static LightCSCDStaffResponseEvent getLightCSCDStaffForUser(GetLightCSCDStaffForUserEvent event){
       	Iterator staffIter = null;
       	LightCSCDStaffResponseEvent resp = null;
    	if(event.getLogonId() != null && !"".equals(event.getLogonId())){
    		staffIter = CSCDOrganizationStaffPosition.findAll("userProfileId",event.getLogonId());
    	}else if(event.getStaffPositionId() != null && !"".equals(event.getStaffPositionId())){
    		staffIter = CSCDOrganizationStaffPosition.findAll("staffPositionId",event.getStaffPositionId());
    	}else{
    		return null;
    	}
        while (staffIter != null && staffIter.hasNext()){
        	CSCDOrganizationStaffPosition staffPosition = (CSCDOrganizationStaffPosition) staffIter.next();
        	if(staffPosition != null && PDSecurityHelper.getUserAgencyId().equals(staffPosition.getAgencyId())){
        		resp = new LightCSCDStaffResponseEvent();
	    		resp.setDivisionId(staffPosition.getDivisionId());
	    		resp.setDivisionName(staffPosition.getDivisionName());
	    		resp.setJobTitleCD(staffPosition.getJobTitleCode()); 
	    		resp.setStaffPositionType(staffPosition.getPositionTypeCode());
	    		resp.setStaffPositionId(staffPosition.getStaffPositionId());
	    		resp.setSPPhoneNumber( staffPosition.getPhoneNum() );
	    		resp.setOfficerLogonId(staffPosition.getUserProfileId());
	    		resp.setSupervisorPositionId(staffPosition.getParentPositionId());
	    		resp.setStaffPositionName(staffPosition.getPositionName());
	    		resp.setParentPositionId(staffPosition.getParentPositionId());
	    		resp.setProgramUnitId(staffPosition.getProgramUnitId());
	    		resp.setProbationOfficerInd(staffPosition.getProbationOfficerInd());
	    		
	    		if(event.isOfficerNameNeeded() && (staffPosition.getUserProfileId() != null && !"".equals(staffPosition.getUserProfileId()))){
	    			UserProfile user = UserProfile.find(staffPosition.getUserProfileId());
	    			if(user != null){
	    				StringBuffer name = new StringBuffer();
	    				if(user.getLastName() != null && !"".equals(user.getLastName())){
		    				name.append(user.getLastName());
		    				if(user.getFirstName() != null && !"".equals(user.getFirstName())){
			    				name.append(", ");
			    				name.append(user.getFirstName());
		    				}
		    				if(user.getMiddleName() != null && !"".equals(user.getMiddleName())){
			    				name.append(" ");
			    				name.append(user.getMiddleName());
		    				}
	    				}else{
		    				name.append("NO OFFICER ASSIGNED");
	    				}
	    				resp.setOfficerName(name.toString());
	    				resp.setOfficerNameQualifiedByPosition(name.append(" | ").append(staffPosition.getPositionName()).toString());
	    			}
	    		}
	    		
	    		if(event.isSupervisorNameNeeded() && (staffPosition.getParentPositionId() != null && !"".equals(staffPosition.getParentPositionId()))){
	    			CSCDStaffPosition supervisor = CSCDStaffPosition.find(staffPosition.getParentPositionId());
	    			if(supervisor != null){
		    			UserProfile user = supervisor.getUserProfile();
		    			Name name = new Name();
		    			if(user != null && (user.getLastName() != null && !"".equals(user.getLastName()))){		    				
		    				name.setLastName(user.getLastName());
		    				name.setFirstName(user.getFirstName());
		    				name.setMiddleName(user.getMiddleName());    				
		    			}else{
		    				name.setLastName("NO SUPERVISOR ASSIGNED");
	    				}
		    			resp.setSupervisorName(name);
		    			resp.setSupervisorNameQualifiedByPosition(new StringBuffer(name.getFormattedName()).append(" | ").append(staffPosition.getParentPositionDesc()).toString());
		    			resp.setSupervisorPositionId(supervisor.getOID());
		    			
		    			if(event.isSupervisorSupervisorNeeded()){
			    			CSCDStaffPosition supervisorSupervisor = CSCDStaffPosition.find(supervisor.getParentPositionId());
			    			if(supervisorSupervisor != null){
				    			user = supervisorSupervisor.getUserProfile();
				    			Name name1 = new Name();
				    			if(user != null && (user.getLastName() != null && !"".equals(user.getLastName()))){		    				
				    				name1.setLastName(user.getLastName());
				    				name1.setFirstName(user.getFirstName());
				    				name1.setMiddleName(user.getMiddleName());    				
				    			}else{
				    				name1.setLastName("NO SUPERVISOR ASSIGNED");
			    				}
				    			resp.setSupervisorSupervisorName(name1);
				    			resp.setSupervisorSupervisorPositionId(supervisorSupervisor.getOID());
			    			}
		    			}
	    			}
	    		}
	    		
	    		if(event.isCourtsNeeded()){
	    			Iterator iterator = CSCDStaffPositionCourt.findAll("staffPositionId",resp.getStaffPositionId());
	    			Court court = null;
                    while (iterator.hasNext()){
                        court = (Court) iterator.next();
                        resp.addCourt(court.getOID());
                    }
	    		}
        	}
        }
        return resp;
    }
    /**
     * @param staffPosition
     */
    /*public static void updateLegacyStaffPosition(CSCDStaffPosition staffPosition) {
        CSCDStaffProfile profile = staffPosition.getStaffProfile();
        UpdateLegacyStaffAssignmentEvent updateEvent = null;
        if (profile != null){
            updateEvent = getLegacyStaffUpdateEvent(staffPosition, profile.getCjadNum());
        } else { //position is being vacated or position does not have a cjadNum.
            updateEvent = getLegacyStaffUpdateEvent(staffPosition, null);
        }
        LegacySupervisionStaff.update(updateEvent);
    }*/
    /**
     * @param staffPosition
     */
    public static void createLegacyStaffPosition(CSCDStaffPosition staffPosition, Date effectiveDate, boolean vacating) {
        CSCDStaffProfile profile = staffPosition.getStaffProfile();
        UpdateLegacyStaffAssignmentEvent updateEvent = null;
        if (profile != null && !vacating){
            updateEvent = getLegacyStaffUpdateEvent(staffPosition, profile.getCjadNum(), vacating);
        } else {
        	updateEvent = getLegacyStaffUpdateEvent(staffPosition, null, vacating);
        }
        updateEvent.setEffectiveDate(effectiveDate);
        LegacySupervisionStaff.create(updateEvent);

    }


    /**
     * @param reqEvent
     */
    public static CSCDStaffPosition vacateStaffPosition(VacateStaffPositionEvent reqEvent){
        CSCDStaffPosition staffPosition = CSCDStaffPosition.find(reqEvent.getSupervisionStaffId());

        if (staffPosition != null) {
        	
            staffPosition.setUserProfileId(null);
            
            if (staffPosition.getHasCaseload()){
            	//Create CRM26 reflecting no officer assigned.
            	CSCDStaffPositionHelper.createLegacyStaffPosition(staffPosition, reqEvent.getEffectiveDate(), true);
            	//Update acknowledgement data on case assignments
            	CSCDStaffPositionHelper.processCaseAssignments(staffPosition, reqEvent.getEffectiveDate(), true);
            } 
            staffPosition.setEffectiveDate(reqEvent.getEffectiveDate());

            CSCDStaffProfile profile = staffPosition.getStaffProfile();
            CSCDStaffPositionHelper.deleteStaffProfile(profile);
            staffPosition.setStaffProfileId(null);
            staffPosition.bind();
            CSCDStaffPosition.createHistory(staffPosition);
        } else {
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(new ErrorResponseEvent());
        }
        return staffPosition;
    }
    
    private static void processCaseAssignments(CSCDStaffPosition staffPosition, Date effectiveDate, boolean vacating){
       	String agencyId = PDSecurityHelper.getUserAgencyId();
    	List caseList = CSCDStaffPositionHelper.getStaffPositionCaseload(staffPosition);
    	Map spnMap = new HashMap();
    	CaseAssignment caseAssignment = null;
    	String defendantId = null;
    	CaseAssignmentTO caseAssignmentTO = null;
    	
    	for (int i = 0; i < caseList.size(); i++) {
    		caseAssignment = (CaseAssignment)caseList.get(i);
            CaseAssignmentBuilder builder = new CaseAssignmentBuilder(caseAssignment);
            builder.build();
            caseAssignmentTO = (CaseAssignmentTO) builder.getResult();
            //Record acknowledgement data
            CSCDStaffPositionHelper.updateCaseAssignment(caseAssignmentTO, staffPosition, effectiveDate, vacating);

    		defendantId = (String) spnMap.get(caseAssignment.getDefendantId());
    		if (defendantId == null){
    			//Create one casenote for each defendant supervised by position being vacated.
    			spnMap.put(caseAssignment.getDefendantId(), caseAssignment.getDefendantId());
    			CSCDStaffPositionHelper.createCasenote(agencyId, caseAssignment.getDefendantId(), NO_OFFICER_ASSIGNED);
    		}
    	}
    }
    /**
     * @param reqEvent
     * @return
     */
    public static boolean verifyCjadNum(VerifyCjadNumEvent reqEvent)
    {
        Iterator iter = null;
        boolean duplicateCjadNum = false;

        if (reqEvent.getCjadNum() != null && !reqEvent.getCjadNum().equals(PDConstants.BLANK)){
            iter = CSCDOrganizationStaffPosition.findAll(reqEvent);
            List staff_position_list = CollectionUtil.iteratorToList(iter);

            if (staff_position_list.size() > 0)
            {
            	CSCDOrganizationStaffPosition staffPos = null;
            	for (int i = 0; i < staff_position_list.size(); i++) {
					staffPos = (CSCDOrganizationStaffPosition) staff_position_list.get(i);
					//Ignore if matching cjadNum belongs to person being reassigned.
					if (staffPos.getUserProfileId() != null 
							&& staffPos.getUserProfileId().equals(reqEvent.getUserProfileId())){
						continue;
					} else {
						EventManager.getSharedInstance(EventManager.REPLY).postEvent(new DuplicateCjadNumEvent());
						duplicateCjadNum = true;
						break;
					}
                }
            }
        }
        return duplicateCjadNum;
    }
    /**
     * Determine whether job title is CLO or CLO FLOATER
     * @param jobTitleId
     * @return
     */
    public static boolean isCLO(String jobTitleId){
    	SupervisionCode jobTitle = SupervisionCode.find(jobTitleId);
    	boolean isCLO = false;
    	if (PDCodeTableConstants.STAFF_JOB_TITLE_CLO.equals(jobTitle.getDescription())
    			|| PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER.equals(jobTitle.getDescription())){
    		isCLO = true;
    	}
    	return isCLO;
    }
    /**
     * Determine whether job title is CLO or CLO FLOATER
     * @param jobTitleId
     * @return
     */
    public static boolean isCSO(String jobTitleId){
    	SupervisionCode jobTitle = SupervisionCode.find(jobTitleId);
    	boolean isCSO = false;
    	if (PDCodeTableConstants.STAFF_JOB_TITLE_CSO.equals(jobTitle.getDescription())){
    		isCSO = true;
    	}
    	return isCSO;
    }
}
