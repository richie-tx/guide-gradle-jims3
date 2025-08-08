/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.security.inquiries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import naming.PDSecurityConstants;
import naming.ResponseLocatorConstants;

import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.security.inquiries.reply.AgencySecurityInfoResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import messaging.user.GetSALAASAUsersEvent;
import mojo.km.security.Constraint;
import mojo.km.security.Role;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;
import pd.contact.user.UserProfile;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgencySecurityInfoResponseCreatorImpl extends ResponseCommonUtil implements ResponseCreator{
     
	/**
	 * Creates Agency response event from entity.
	 * @param object
	 * @return object AgencyResponseEvent
	 */
	public Object create(Object object){
		Agency agency = (Agency) object;
		ResponseContextFactory respFac = new ResponseContextFactory();
     	AgencySecurityInfoResponseEvent asiRespEvent = new AgencySecurityInfoResponseEvent();
     	asiRespEvent.setAgencyName(agency.getAgencyName());
     	asiRespEvent.setAgencyId(agency.getAgencyId());
		
		Collection departments = agency.getDepartments();
		// set departments in AgencySecurityInfoResponseEvent
		this.setDepartments(asiRespEvent,departments,respFac);
				
		/*Collection constraints = Constraint.findByConstrainerId(agency.getAgencyId(),
				PDSecurityConstants.AGENCY,PDSecurityConstants.ROLE);
		// set roles in AgencySecurityInfoResponseEvent
		this.setRoles(asiRespEvent,constraints,respFac);*/ //87191
		
		return asiRespEvent;
	}

	/**
	 * @param asiRespEvent
	 * @param constraints
	 * @param respFac
	 */
	private void setRoles(AgencySecurityInfoResponseEvent asiRespEvent, Collection constraints, ResponseContextFactory respFac) {
		/*ResponseCreator rCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.ROLE_RESPONSE_LOCATOR,respFac);
		Iterator constraintIter = constraints.iterator();
		while(constraintIter.hasNext()){
			Constraint constraint = (Constraint) constraintIter.next();
			if(constraint != null){
				Role role = Role.find(constraint.getConstrainsId());
				if(role != null){
					RoleResponseEvent rResp = (RoleResponseEvent) rCreator.create(role);
					asiRespEvent.addRole(rResp);
				}
			}
		}*/ //87191
		
	}

	/**
	 * @param asiRespEvent
	 * @param departments
	 * @param respFac
	 */
	private void setDepartments(AgencySecurityInfoResponseEvent asiRespEvent, Collection departments, ResponseContextFactory respFac) {
		ResponseCreator cCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.DEPARTMENT_RESPONSE_LOCATOR,respFac);
		ResponseCreator uCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.USER_RESPONSE_LOCATOR,respFac);
		Iterator departmentIter = departments.iterator();
		HashMap saUserMap = new HashMap();
		while(departmentIter.hasNext()){
			Department department = (Department) departmentIter.next();
			if(department != null){
				DepartmentResponseEvent dResp = (DepartmentResponseEvent) cCreator.createThinest(department);
				// get the users
				/*GetSALAASAUsersEvent saEvent = new GetSALAASAUsersEvent();
				saEvent.setDepartmentId(department.getDepartmentId());
				Iterator userIterator = UserProfile.findAll(saEvent);
				while(userIterator.hasNext()){
					UserProfile up = (UserProfile) userIterator.next();
					if(up != null){
						UserResponseEvent uResp = (UserResponseEvent) uCreator.createThinest(up);
						String userType = uResp.getUserTypeId();
						if(userType != null && userType.equalsIgnoreCase(PDSecurityConstants.USER_TYPE_SA)){
							if(!saUserMap.containsKey(uResp.getLogonId())){
								saUserMap.put(uResp.getLogonId(),uResp);
							}
						}else if(userType != null && userType.equalsIgnoreCase(PDSecurityConstants.USER_TYPE_ASA)){
							dResp.addAsaUser(uResp);
						}else if(userType != null && userType.equalsIgnoreCase(PDSecurityConstants.USER_TYPE_LIASON)){
							dResp.addLiasonUser(uResp);
						}
					}
				}*/ //87191
				asiRespEvent.addDepartment(dResp);
			}			
		}
		asiRespEvent.setSAUsers(new ArrayList(saUserMap.values()));
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}

