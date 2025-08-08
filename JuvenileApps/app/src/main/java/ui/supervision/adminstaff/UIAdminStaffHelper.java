/*
 * Created on Mar 13, 2007
 *
 */
package ui.supervision.adminstaff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import naming.CSCDStaffPositionControllerServiceNames;
import naming.OrganizationControllerServiceNames;
import naming.UIConstants;

import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.GetOrganizationHierarchyEvent;
import messaging.cscdstaffposition.GetOrganizationsEvent;
import messaging.cscdstaffposition.GetStaffPositionHistoryEvent;
import messaging.cscdstaffposition.GetSupervisorsEvent;
import messaging.cscdstaffposition.GetUsersForCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.UpdateStaffPositionEvent;
import messaging.cscdstaffposition.ValidateStaffPositionEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.cscdstaffposition.reply.StaffPositionHistoryResponseEvent;
import messaging.organization.GetDivisionForAgencyEvent;
import messaging.organization.GetDivisionForProgramUnitEvent;
import messaging.organization.reply.GetDivisionForAgencyResponseEvent;
import messaging.organization.reply.GetDivisionForProgramUnitResponseEvent;
import messaging.supervisionoptions.CourtRequestEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.transferobjects.OrganizationTO;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;

import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.User;
import ui.security.SecurityUIHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.adminstaff.form.AdminStaffReportForm;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;

/**
 * @author jjose
 *
 */
public class UIAdminStaffHelper {
	
	public static String REPORT_TYPE_RETIRE="retire";
	public static String REPORT_TYPE_POSITION="position";
	public static String REPORT_TYPE_PU="pu";
	public static String REPORT_TYPE_STAFF="staff";
	public static String VACATE="vacate";
	public static String ASSIGN="assign";
	public static String REASSIGN="reassign";
	public static String REPORT="report";
	public static String RETIRE="retire";
	
	public static Collection getCourts(){
		Collection allCourts=UISupervisionOptionHelper.getUnFilteredCourtBeans();
		Collection filteredCourts=UISupervisionOptionHelper.filterCourtBeansFor2CrtCategories(UIConstants.COUNTY_CRIMINAL_COURT_CATEGORY,UIConstants.DISTRICT_COURT_CATEGORY,allCourts);
		return filteredCourts;
	}
	
	 
	
	public static ArrayList  processReportResults(CompositeResponse myResp){
		Collection staffPositions = MessageUtil.compositeToCollection(myResp, CSCDSupervisionStaffResponseEvent.class);	
		ArrayList list=new ArrayList();
		
		// added to improve performance			
		List locationCodes = ComplexCodeTableHelper.getLocationCodes();
		
		if (staffPositions!=null && !staffPositions.isEmpty())
		{
			Iterator iter = staffPositions.iterator();
			while (iter.hasNext())
			{
				Object myRespEvt=iter.next();
				if(myRespEvt instanceof StaffPositionHistoryResponseEvent){
					StaffPositionHistoryResponseEvent staffPosRespEvt = (StaffPositionHistoryResponseEvent)myRespEvt;
					Position myPos=(UIAdminStaffHelper.getPositionFromStaffRespEvt(staffPosRespEvt, locationCodes));
					myPos.setPositionAssignedDate(staffPosRespEvt.getUpdateDate());
					
//					String updateUserId=staffPosRespEvt.getUpdateUserId();
					User myUpdatedByUser=new User();
//					if(updateUserId!=null && !updateUserId.equals("")){
//						try{
//							UserResponseEvent ure = SecurityUIHelper.getUser(updateUserId);
//						}
//						catch(Exception e){
//							// ignore errors.
//						}
//					}
					if(staffPosRespEvt.getUpdateUserName()!=null){
					    myUpdatedByUser.getUserName().setFirstName(staffPosRespEvt.getUpdateUserName().getFirstName());
					    myUpdatedByUser.getUserName().setMiddleName(staffPosRespEvt.getUpdateUserName().getMiddleName());
					    myUpdatedByUser.getUserName().setLastName(staffPosRespEvt.getUpdateUserName().getLastName());
					}
					myPos.setCreatedBy(myUpdatedByUser);
					list.add(myPos);
				}
				else if(myRespEvt instanceof CSCDSupervisionStaffResponseEvent){
					CSCDSupervisionStaffResponseEvent staffPosRespEvt = (CSCDSupervisionStaffResponseEvent)myRespEvt;
					list.add(UIAdminStaffHelper.getPositionFromStaffRespEvt(staffPosRespEvt, locationCodes));
				}
			}
		}
		return list;
	}
	
	
	
	public static void populateValidateStaffPositionEvent(Position aPos,ValidateStaffPositionEvent anEvent){
		anEvent.setDivisionId(aPos.getDivisionId());
		anEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		anEvent.setProgramUnitId(aPos.getProgramUnitId());
		anEvent.setSectionId(aPos.getProgramSectionId());
		anEvent.setPositionTypeId(aPos.getPositionTypeId());
		anEvent.setPositionName(aPos.getPositionName());
		anEvent.setHasCaseLoad(aPos.isHasCaseload());
		
		if(aPos.getProbOfficerInd()!=null && aPos.getProbOfficerInd().equals("")){
		}
		else{
			anEvent.setProbationOfficerInd(aPos.getProbOfficerInd());
		}
		if(aPos.getPositionId()!=null && !aPos.getPositionId().equals("")) {
			anEvent.setStaffPositionId(aPos.getPositionId());
		}
		if(aPos.getUser()!=null) {
			anEvent.setCjadNum(aPos.getUser().getCjad());
		}
		if (aPos.getCourts() != null){
		    Collection courts = UISuggestedOrderHelper.buildCourtList(aPos.getSelectedCourts());
		    anEvent.setCourts(courts);
		}
		anEvent.setJobTitleId(aPos.getJobTitleId());
	}
	



    public static void populateGetStaffPosHistoryEvent(AdminStaffReportForm aForm,GetStaffPositionHistoryEvent anEvent){
		anEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
	    anEvent.setBeginDate(aForm.getStartDate());
		anEvent.setEndDate(aForm.getEndDate());
		anEvent.setReportType(aForm.getReportType());
		if(aForm.getReportType().equals(REPORT_TYPE_RETIRE)){
			anEvent.setIsRetired(true);
		}
		else{
			anEvent.setIsRetired(false);
		}
		if(aForm.getReportType().equals(REPORT_TYPE_POSITION)){
			anEvent.setSearchkeyId(aForm.getPositionId());
		}
		else if(aForm.getReportType().equals(REPORT_TYPE_STAFF)){
			anEvent.setSearchkeyId(aForm.getStaffId());
		}
		else if(aForm.getReportType().equals(REPORT_TYPE_PU)){
			anEvent.setSearchkeyId(aForm.getProgramUnitId());
		}
		anEvent.setPositionStatusId(aForm.getPositionStatusId()); 
	}
	
	public static void populateGetSupervisorsEvent(Position aPos, GetSupervisorsEvent anEvent){
		anEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
	    anEvent.setDivisionId(aPos.getDivisionId());
	    anEvent.setProgramUnitId(aPos.getProgramUnitId());
	    anEvent.setProgramSectionId(aPos.getProgramSectionId());
	    anEvent.setStaffPositionId(aPos.getPositionId());
	}
	
	public static void populateGetUsersForStaffEvent(AdminStaffSearchForm aForm, GetUsersForCSCDSupervisionStaffEvent anEvent){
		anEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
	    anEvent.setCjadNum(aForm.getUserSearchCjad());
		if(aForm.getUserSearchName()!=null){
			anEvent.setStaffFirstName(aForm.getUserSearchName().getFirstName());
			anEvent.setStaffLastName(aForm.getUserSearchName().getLastName());
			anEvent.setStaffMiddleName(aForm.getUserSearchName().getMiddleName());
		}
		anEvent.setCstsOfficerTypeId(aForm.getUserSearchOffTypeId());
		anEvent.setStaffLogonId(aForm.getUserSearchUserId());
		anEvent.setWorkgroupName(aForm.getUserSearchWorkgroupName());
	}
	
	public static void populateSupervisionStaffGetEvent(AdminStaffSearchForm aForm, GetCSCDSupervisionStaffEvent anEvent){
		anEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());;
	    anEvent.setCjadNum(aForm.getCjad());
		anEvent.setDivisionId(aForm.getDivisionId());
		anEvent.setPositionName(aForm.getPositionName());
		anEvent.setProgramSectionId(aForm.getProgramSectionId());
		anEvent.setProgramUnitId(aForm.getProgramUnitId());
		if(aForm.getName()!=null){
			anEvent.setStaffMiddleName(aForm.getName().getMiddleName());
			anEvent.setStaffFirstName(aForm.getName().getFirstName());
			anEvent.setStaffLastName(aForm.getName().getLastName());
		}
		anEvent.setCstsOfficerTypeId(aForm.getOfficerTypeId());
		anEvent.setStaffLogonId(aForm.getUserId());
		anEvent.setWorkGroupName(aForm.getWorkgroupName());
	}
	
	public static void populateUpdateStaffPositionEvent(Position pos, UpdateStaffPositionEvent aUpdateEvt){
		 aUpdateEvt.setAgencyId(SecurityUIHelper.getUserAgencyId());
		 aUpdateEvt.setCjadNum(pos.getUser().getCjad());
		 aUpdateEvt.setEffectiveDate(
				 DateUtil.stringToDate(pos.getEffectiveDateAsStr(), "MM/dd/yyyy"));
		 aUpdateEvt.setHasCaseload(pos.isHasCaseload());
		 aUpdateEvt.setJobTitleId(pos.getJobTitleId());
		 aUpdateEvt.setLocationDetails(pos.getLocationDetails());
		 aUpdateEvt.setLocationId(pos.getLocationId());
		 aUpdateEvt.setOrganizationId(pos.getOrganizationId());
		 aUpdateEvt.setCstsOfficerTypeId(pos.getOfficerTypeId());
	//	 aUpdateEvt.setParentPositionId(pos.getP);
		 if(pos.getSupervisor()!=null)
		 	aUpdateEvt.setParentPositionId(pos.getSupervisor().getPositionId());
		 aUpdateEvt.setProbationOfficerInd(pos.getProbOfficerInd());
		 aUpdateEvt.setPositionTypeId(pos.getPositionTypeId());
		 aUpdateEvt.setPositionName(pos.getPositionName());
		 if(pos.getPhone()!=null)
		 	aUpdateEvt.setPhoneNum(pos.getPhone().getPhoneNumber());
		 if(pos.getPositionId()!=null && !pos.getPositionId().equals(""))
		 	aUpdateEvt.setPositionId(pos.getPositionId());
		 if(pos.getSelectedCourts()!=null && pos.getSelectedCourts().size()>0){
		 	Iterator iter=pos.getSelectedCourts().iterator();
		 	while(iter.hasNext()){
		 		CourtBean courtBean=(CourtBean)iter.next();
		 		if(courtBean.getCourts()!=null && courtBean.getCourts().size()>0){
		 			Iterator courtListIter=courtBean.getCourts().iterator();
		 			while(courtListIter.hasNext())
		 			{		 			
		 				CourtResponseEvent myRespEvt=(CourtResponseEvent)courtListIter.next();
		 				String courtId=myRespEvt.getCourtId();
				 		if(courtId!=null && !courtId.equals("")){
				 			CourtRequestEvent myCourtEvent=new CourtRequestEvent();
				 			myCourtEvent.setCourtId(courtId);
				 			aUpdateEvt.addRequest(myCourtEvent);
				 		}
		 			}
		 		}
		 	}
		 }
	}
	
	public static Position  getPositionFromStaffRespEvt(CSCDSupervisionStaffResponseEvent aSupStaffRespEvent, List locationCodes){
		Position aPosition = new Position();
		if(aSupStaffRespEvent!=null){
			ArrayList myCourts=new ArrayList();
			aPosition.setSelectedCourts(myCourts);
			if(aSupStaffRespEvent.getCourts()!=null && aSupStaffRespEvent.getCourts().size()>0){
				Collection courtBeans = aPosition.getCourts();
				Map courtMap=UISupervisionOptionHelper.createCourtMap(courtBeans);
	            Map courtBeanMap = new HashMap();
	            Collection selCourts = aSupStaffRespEvent.getCourts();

	            if (selCourts != null && selCourts.size()>0) {
	                Iterator selCourtsIter = selCourts.iterator();
	                while (selCourtsIter.hasNext()) {
	                    String courtNum = (String) selCourtsIter.next();
	                    CourtResponseEvent cre = (CourtResponseEvent) courtMap.get(courtNum);
	                    CourtBean cb = (CourtBean) courtBeanMap.get(cre.getCourtCategory());
	                    if (cb == null) {
	                        cb = new CourtBean();
	                        cb.setCategory(cre.getCourtCategory());
	                        cb.setCategoryDesc(cre.getCourtCategoryDesc());
	                        courtBeanMap.put(cre.getCourtCategory(), cb);
	                    }
	                    cb.insertCourt(cre);
	                }
	                Collection myCol=courtBeanMap.values();
	                Enumeration en=Collections.enumeration(myCol);
	                myCourts=Collections.list(en);
	                aPosition.setSelectedCourts(myCourts);
	            }    
			}
			//aPosition.setCreatedBy(aSupStaffRespEvent.get)
			aPosition.setHasCaseload(aSupStaffRespEvent.isHasCaseload());
			aPosition.setJobTitleId(aSupStaffRespEvent.getJobTitleId());
			 aPosition.setJobTitleDesc(aSupStaffRespEvent.getJobTitleDesc());
			aPosition.setLocationDetails(aSupStaffRespEvent.getLocationDetails());
			aPosition.setLocationId(aSupStaffRespEvent.getLocationId());
			aPosition.setLocationDesc(ComplexCodeTableHelper.getLocationDesc(locationCodes, aSupStaffRespEvent.getLocationId()));
			aPosition.setOfficerTypeId(aSupStaffRespEvent.getCstsOfficerTypeId());
			 aPosition.setOfficerTypeDesc(aSupStaffRespEvent.getOfficerTypeDesc());
			PhoneNumber myPhoneNumber=null;
			if(aSupStaffRespEvent.getPhoneNum()!=null)
				 myPhoneNumber= new PhoneNumber(aSupStaffRespEvent.getPhoneNum());
			else{
				myPhoneNumber= new PhoneNumber("");
			}
			aPosition.setPhone(myPhoneNumber);
			//aPosition.setPositionAssignedDate(aSupStaffRespEvent.get)
			aPosition.setPositionId(aSupStaffRespEvent.getStaffPositionId());
			User myUser=new User();
			aPosition.setUser(myUser);
			myUser.setCjad(aSupStaffRespEvent.getCjadNum());
			aPosition.setEffectiveDateAsStr(
					DateUtil.dateToString(aSupStaffRespEvent.getEffectiveDate(), DateUtil.DATE_FMT_1));
			aPosition.setRetirementDateAsStr(
					DateUtil.dateToString(aSupStaffRespEvent.getRetirementDate(), DateUtil.DATE_FMT_1));
			
			myUser.setUserId(aSupStaffRespEvent.getAssignedLogonId());
			if(aSupStaffRespEvent.getAssignedName()!=null){
				myUser.getUserName().setFirstName(aSupStaffRespEvent.getAssignedName().getFirstName());
				myUser.getUserName().setMiddleName(aSupStaffRespEvent.getAssignedName().getMiddleName());
				myUser.getUserName().setLastName(aSupStaffRespEvent.getAssignedName().getLastName());
			}
			aPosition.setPositionName(aSupStaffRespEvent.getPositionName());
			aPosition.setPositionStatusId(aSupStaffRespEvent.getPositionStatusId());
			 aPosition.setPositionStatusDesc(aSupStaffRespEvent.getPositionStatusDesc());
			aPosition.setPositionTypeId(aSupStaffRespEvent.getPositionTypeId());
			 aPosition.setPositionTypeDesc(aSupStaffRespEvent.getPositionTypeDesc());
			aPosition.setProbOfficerInd(aSupStaffRespEvent.getProbationOfficerInd());
			aPosition.setOrganizationId(aSupStaffRespEvent.getOrganizationId());
			//aPosition.setRetired(aSupStaffRespEvent.get)
			Position supPos= new Position();
			aPosition.setSupervisor(supPos);
			supPos.setPositionId(aSupStaffRespEvent.getSupervisorId());
			if(aSupStaffRespEvent.getSupervisorName()!=null){
			    User supUser = new User();
			    supPos.setUser(supUser);
				supUser.getUserName().setFirstName(aSupStaffRespEvent.getSupervisorName().getFirstName());
				supUser.getUserName().setMiddleName(aSupStaffRespEvent.getSupervisorName().getMiddleName());
				supUser.getUserName().setLastName(aSupStaffRespEvent.getSupervisorName().getLastName());
				supUser.setUserId(aSupStaffRespEvent.getSupervisorLogonId());
				supPos.setProbOfficerInd(aSupStaffRespEvent.getSupervisorPoi());
//				supPos.getUser().getUserName().setFirstName(aSupStaffRespEvent.getSupervisorName().getFirstName());
//				supPos.getUser().getUserName().setMiddleName(aSupStaffRespEvent.getSupervisorName().getMiddleName());
//				supPos.getUser().getUserName().setLastName(aSupStaffRespEvent.getSupervisorName().getLastName());
			}
			aPosition.getUser().setWorkgroupDesc("");
			if (aSupStaffRespEvent.getWorkGroups() != null && aSupStaffRespEvent.getWorkGroups().size() > 0){
			    
			    Iterator iter = aSupStaffRespEvent.getWorkGroups().iterator();
			    StringBuffer sb = new StringBuffer();
			    String COMMA = ", ";
			    boolean firstOne = true;
			    while (iter.hasNext()){
			        String workGroupName = (String) iter.next();
			        if (!firstOne){
			            sb.append(COMMA);
			        } else {
			            firstOne = false;
			        }
			        sb.append(workGroupName);
			    }
			    aPosition.getUser().setWorkgroupDesc(sb.toString());
			} 
			ArrayList myPositionChildren=new ArrayList();
			aPosition.setSubordinates(myPositionChildren);
			if(aSupStaffRespEvent.getChildren()!=null && aSupStaffRespEvent.getChildren().size()>0){
				Iterator iter=aSupStaffRespEvent.getChildren().iterator();
				while(iter.hasNext()){
					CSCDSupervisionStaffResponseEvent myRespEvent=(CSCDSupervisionStaffResponseEvent)iter.next();
					Position myPositionChild = getPositionFromStaffRespEvt(myRespEvent, locationCodes);
					myPositionChildren.add(myPositionChild);
				}
			}
		}
		return aPosition;
	}
	
	public static Position findPositionInList(String aPosId, ArrayList aListOfPositions){
		Position foundPos=null;
		if(aPosId==null || (aPosId != null && aPosId.equals("")) || aListOfPositions==null || aListOfPositions.size()<=0){
			
		}
		else{
			int size=aListOfPositions.size();
			for(int loopX=0; loopX<size && foundPos==null;loopX++){
				Position pos=(Position)aListOfPositions.get(loopX);
				if(pos.getPositionId()!=null && pos.getPositionId().equals(aPosId)){
					foundPos=pos;
					break;
				}
				else if(pos.getSubordinates()!=null && pos.getSubordinates().size()>0){
					foundPos=findPositionInList(aPosId,pos.getSubordinates());
					if(foundPos!=null)
						return foundPos;
				}
				
			}
		}
		return foundPos;
	}
	
	public static Position findPositionFromUserInList(String aUserId, ArrayList aListOfPositions){
		Position foundPos=null;
		if(aUserId==null || (aUserId != null && aUserId.equals("")) || aListOfPositions==null || aListOfPositions.size()<=0){
			
		}
		else{
			int size=aListOfPositions.size();
			for(int loopX=0; loopX<size && foundPos==null;loopX++){
				Position pos=(Position)aListOfPositions.get(loopX);
				if(pos.getUser()!=null && pos.getUser().getUserId().equals(aUserId)){
					foundPos=pos;
					break;
				}
				else if(pos.getSubordinates()!=null && pos.getSubordinates().size()>0){
					foundPos=findPositionInList(aUserId,pos.getSubordinates());
					if(foundPos!=null)
						return foundPos;
				}
				
			}
		}
		return foundPos;
	}
	
	public static Position creatFakePosition(boolean isRetired,boolean isVacated){
		Position position= new Position();
		position.setDivisionId("W");
		position.setHasCaseload(true);
		position.setJobTitleId("A");
		position.setLocationDetails("Loc Details Test");
		position.setLocationId("B");
		position.setOfficerTypeId("M");
		position.setPhone(new PhoneNumber("972","365","4990"));
		position.setPositionAssignedDate(new Date());
		if(isRetired){
			if(isVacated){
				position.setPositionId("1");
			}
			else{
				position.setPositionId("2");
			}
		}
		else{
			if(isVacated){
				position.setPositionId("3");
			}
			else{
				position.setPositionId("4");
			}
		}
		String posName="";
		if(isRetired)
			posName=posName + " retired ";
		if(isVacated)
			posName=posName + "vacated ";
		position.setPositionName("the " + posName + "position name test");
		position.setPositionTypeId("M");
		position.setProbOfficerInd("POI");
		position.setProgramSectionId("I");
		position.setProgramUnitId("W");
		position.setRetired(isRetired);
		position.setSubordinates(null);
		position.setSupervisor(null);
		User user=null;
		if(!isVacated){
			user=new User();
			user.setCjad("cjadTest");
			user.setUserId("55");
			user.setUserName(new Name("Justin","A","Jose"));
			user.setWorkgroupDesc("WorkgroupTest");
		}
		position.setUser(user);
		return position;
	}
	
	public static User findUserInList(String aUserId, ArrayList aListOfUsers){
		User foundUsr=null;
		if(aUserId==null || (aUserId != null && aUserId.equals("")) || aListOfUsers==null || aListOfUsers.size()<=0){
			
		}
		else{
			int size=aListOfUsers.size();
			for(int loopX=0; loopX<size && foundUsr==null;loopX++){
				User usr=(User)aListOfUsers.get(loopX);
				if(usr.getUserId().equals(aUserId)){
					foundUsr=usr;
				}
			}
		}
		return foundUsr;
	}
	
	/*
	 * String agencyId
	 */
	public static Collection fetchDivisionForAgency(String agencyId)
    {
        GetDivisionForAgencyEvent divisionEvent = (GetDivisionForAgencyEvent) EventFactory
                .getInstance(OrganizationControllerServiceNames.GETDIVISIONFORAGENCY);
 
        divisionEvent.setAgencyCode(agencyId);
 
        GetDivisionForAgencyResponseEvent response = 
            (GetDivisionForAgencyResponseEvent) MessageUtil.postRequest(divisionEvent, GetDivisionForAgencyResponseEvent.class);
        
        
       Collection divisionList = new ArrayList(); 
      if (response != null){
       
          divisionList = response.getAgencyDivisionsCollection();
      }
  
        return divisionList;
     }
	
	public static OrganizationTO fetchDivisionForProgramUnit(String pUnitId)
    {
        GetDivisionForProgramUnitEvent divisionEvent = (GetDivisionForProgramUnitEvent) EventFactory
                .getInstance(OrganizationControllerServiceNames.GETDIVISIONFORPROGRAMUNIT);
 
        divisionEvent.setProgramUnitId(pUnitId);
 
        GetDivisionForProgramUnitResponseEvent response = 
            (GetDivisionForProgramUnitResponseEvent) MessageUtil.postRequest(divisionEvent, GetDivisionForProgramUnitResponseEvent.class);
        
        
       OrganizationTO divisionList = null;
       
      if (response != null){
       
          divisionList = response.getOrganizationTO();
      }
  
        return divisionList;
     }
	
	
	public static Collection getOrganizationalHeirarchy(String agencyId){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOrganizationsEvent requestEvent = (GetOrganizationsEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.GETORGANIZATIONS);
		requestEvent.setAgencyId(agencyId);
		dispatch.postEvent(requestEvent);
		CompositeResponse cr = (CompositeResponse) EventManager.getSharedInstance(EventManager.REQUEST).getReply();
		Collection organizations = MessageUtil.compositeToCollection(cr,  OrganizationResponseEvent.class);
		Map organizationMap = new HashMap();
		if ( organizations != null )
		{
			for ( Iterator iter = organizations.iterator(); iter.hasNext(); )
			{
				OrganizationResponseEvent orgEvent = (OrganizationResponseEvent) iter.next();
				organizationMap.put( orgEvent.getOrganizationId(), orgEvent );
			}
		}
		Collection myColl=organizationMap.values();
		Enumeration en=Collections.enumeration(myColl);
		ArrayList myList=Collections.list(en);
		Collections.sort((List)(myList));
        Iterator iterator = myList.iterator();
        while (iterator.hasNext())
        {
            OrganizationResponseEvent or = (OrganizationResponseEvent) iterator.next();
            SortedMap map = new TreeMap();
            if (or.getChildren() != null){
            	Iterator iter = or.getChildren().iterator();
            	while (iter.hasNext())
            	{
            		OrganizationResponseEvent theChild = (OrganizationResponseEvent) iter.next();
            		map.put(theChild.getDescription(), theChild);
            	}
            	or.setChildren(map.values());
            }
       }    
		return myList;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Collection getActiveOrganizationalHeirarchy(){
			
		GetOrganizationHierarchyEvent divisionEvent = new GetOrganizationHierarchyEvent();
 		divisionEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
 		
 		List divisions = MessageUtil.postRequestListFilter(divisionEvent, OrganizationResponseEvent.class);
 		
		Map organizationMap = new HashMap();
		if ( divisions != null )
		{
			for ( Iterator iter = divisions.iterator(); iter.hasNext(); )
			{
				OrganizationResponseEvent orgEvent = (OrganizationResponseEvent) iter.next();
				organizationMap.put( orgEvent.getOrganizationId(), orgEvent );
			}
		}
		Collection myColl=organizationMap.values();
		Enumeration en=Collections.enumeration(myColl);
		ArrayList myList=Collections.list(en);
		Collections.sort((List)(myList));
        Iterator iterator = myList.iterator();
        while (iterator.hasNext())
        {
            OrganizationResponseEvent or = (OrganizationResponseEvent) iterator.next();
            SortedMap map = new TreeMap();
            if (or.getChildren() != null){
            	Iterator iter = or.getChildren().iterator();
            	while (iter.hasNext())
            	{
            		OrganizationResponseEvent theChild = (OrganizationResponseEvent) iter.next();
            		map.put(theChild.getDescription(), theChild);
            	}
            	or.setChildren(map.values());
            }
       }    
		return myList;
	}
	
	public static String getOrganizationName(String aOrgId,Collection aOrganizations)
	{
		if(aOrganizations == null || aOrganizations.size()<1 || aOrgId==null || aOrgId.equals(""))
			return "";
		
			
		Iterator organizationIter = aOrganizations.iterator();
		
		while(organizationIter.hasNext())
		{
			OrganizationResponseEvent eachOrg = (OrganizationResponseEvent) organizationIter.next();

			if(aOrgId.equals(eachOrg.getOrganizationId()))
			{
				return eachOrg.getDescription();
			}
			if(eachOrg.getChildren()!=null && eachOrg.getChildren().size()>0){
				String foundOrgName=getOrganizationName(aOrgId,eachOrg.getChildren());
				if(foundOrgName!=null && !foundOrgName.equals("")){
					return foundOrgName;
				}
			}
		}
		return "";
	}

}// END CLASS
