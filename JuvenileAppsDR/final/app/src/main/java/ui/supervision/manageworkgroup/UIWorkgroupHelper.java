/*
 * Created on Mar 19, 2007
 */
package ui.supervision.manageworkgroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.cscdstaffposition.GetStaffPositionsByUserIdEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.manageworkgroup.form.WorkgroupForm;
import ui.supervision.manageworkgroup.form.WorkgroupUserBean;

/**
 * @author hrodriguez
 */
public class UIWorkgroupHelper {

	/**
	 * Builds map of response events setting key as topic.
	 * 
	 * @param responseEvents
	 * @return
	 */
	public static Map buildResponseEventMap(Collection responseEvents) {
		Map map = new HashMap();

		if (responseEvents != null) {
		    WorkgroupUserBean re = null;
			Iterator iter = responseEvents.iterator();
			while (iter.hasNext()) {
				re = (WorkgroupUserBean) iter.next();
				map.put(re.getUserId(), re);
			}
		}
		return map;
	}

	/**
	 * Creates a new collection from an existing collection.
	 * 
	 * @param coll1
	 * @return
	 */
	public static Collection buildNewCollection(Collection coll1) {

		Collection coll2 = new ArrayList();
		Object obj = null;
		Iterator iter = coll1.iterator();
		while (iter.hasNext()) {
			obj = (Object) iter.next();
			coll2.add(obj);
		}
		return coll2;

	}

	public static void populateFormFromResponseEvent(WorkGroupResponseEvent wgre, WorkgroupForm wgForm){
	    wgForm.setWorkgroupId(wgre.getWorkgroupId());
	    wgForm.setWorkgroupName(wgre.getWorkgroupName());
	    wgForm.setWorkgroupDescription(wgre.getWorkgroupDescription());
	    wgForm.setWorkgroupTypeId(wgre.getWorkgroupTypeId());
	    
	    String typeDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WORKGROUP_TYPE, wgre.getWorkgroupTypeId()); 
	    wgForm.setWorkgroupTypeDesc(typeDesc);
	    // users
//	    Collection users = wgre.getUsers();
//	    if(users != null){
//	        for(Iterator iter = users.iterator(); iter.hasNext(); ){
//	            SecurityUserResponseEvent responseEvent = (SecurityUserResponseEvent)iter.next();
//		        WorkgroupUserBean wgUserBean = populateUserBean(responseEvent);
//		        wgForm.addUserSelected(wgUserBean);	
//	        }
//	    }
		if (wgre.getUsers() != null && wgre.getUsers().size() > 0){
		    SecurityUserResponseEvent userResponse = null;
		    GetStaffPositionsByUserIdEvent getStaffEvent = (GetStaffPositionsByUserIdEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONSBYUSERID);
		    getStaffEvent.setAgencyId(wgForm.getAgencyId());
		    Iterator iter = wgre.getUsers().iterator();
		    while(iter.hasNext()){
		        userResponse = (SecurityUserResponseEvent)iter.next();
		        getStaffEvent.addLogonId(userResponse.getLogonId());
		    }
		    List aList = MessageUtil.postRequestListFilter(getStaffEvent, CSCDSupervisionStaffResponseEvent.class);
		    if (aList != null && aList.size() > 0){
			    CSCDSupervisionStaffResponseEvent staffResponse = null;
			    WorkgroupUserBean wguBean = null;
		        iter = aList.iterator();
		        while (iter.hasNext()){
		            staffResponse = (CSCDSupervisionStaffResponseEvent) iter.next();
		            wguBean = UIWorkgroupHelper.populateWorkgroupUserBean(staffResponse);
		            wgForm.addUserSelected(wguBean);
		        }
		    
		    }
		}

	}
	
	public static WorkgroupUserBean populateUserBean(SecurityUserResponseEvent cre){
        WorkgroupUserBean wgUserBean = new WorkgroupUserBean();
		wgUserBean.setUserId(cre.getLogonId());
		StringBuffer full = new StringBuffer();
		full.append(cre.getLastName());
		full.append(", " + cre.getFirstName());
		full.append(" " + cre.getMiddleName());
    	wgUserBean.setFormattedName(full.toString());
    	
    	return wgUserBean;
	}
	
	public static WorkgroupUserBean populateWorkgroupUserBean(CSCDSupervisionStaffResponseEvent cre) {
        WorkgroupUserBean wgUserBean = new WorkgroupUserBean();
		wgUserBean.setUserId(cre.getAssignedLogonId());
//		StringBuffer full = new StringBuffer(); 
//		full.append(cre.getAssignedName().getLastName());
//		full.append(", " + cre.getAssignedName().getFirstName());
//		full.append(" " + cre.getAssignedName().getMiddleName());
//    	wgUserBean.setFormattedName(full.toString());
		wgUserBean.setFormattedName(cre.getAssignedName().getFormattedName());
   	
    	List jobTitlesList = ComplexCodeTableHelper.getSupervisionStaffJobTitles(SecurityUIHelper.getUserAgencyId());
    	String jobTitleDesc = ComplexCodeTableHelper.getDescrByCode(jobTitlesList, cre.getJobTitleId());
    	wgUserBean.setJobTitleDesc(jobTitleDesc);
    	
    	Collection organizations = new ArrayList();
    	
    	//Organization names may be populated depending on path that was taken prior to executing this method.
    	if (cre.getDivisionName() !=  null && !cre.getDivisionName().equals(PDConstants.BLANK)){
    	    wgUserBean.setDivisionDesc(cre.getDivisionName());
    	} else {
    	    organizations = UIAdminStaffHelper.getActiveOrganizationalHeirarchy();
        	String divisionDesc = UIAdminStaffHelper.getOrganizationName(cre.getDivisionId(), organizations);
        	wgUserBean.setDivisionDesc(divisionDesc);
    	}
    	if (cre.getProgramUnitName() == null || (cre.getProgramUnitName().equals(PDConstants.BLANK) && !cre.getProgramUnitId().equals("0"))) {
    	    String programUnitDesc=UIAdminStaffHelper.getOrganizationName(cre.getProgramUnitId(), organizations);
    	    wgUserBean.setProgramUnitDesc(programUnitDesc);
    	} else {
    	    wgUserBean.setProgramUnitDesc(cre.getProgramUnitName());
    	}

    	List positionTypeList = ComplexCodeTableHelper.getSupervisionStaffPositionTypes(SecurityUIHelper.getUserAgencyId());
    	String positionTypeDesc = ComplexCodeTableHelper.getDescrByCode(positionTypeList, cre.getPositionTypeId());
    	wgUserBean.setPositionTypeDesc(positionTypeDesc);    	 
    	    	
    	return wgUserBean;
		
	}
}
