//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRolesByConstarintsCommand.java

package pd.security.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.agency.GetAgenciesEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.security.GetRolesByConstraintsEvent;
import messaging.security.GetRolesEvent;
import messaging.security.reply.RoleResponseEvent;
import messaging.user.GetUserProfilesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.security.Constraint;
import mojo.km.security.Role;
import pd.contact.agency.Agency;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
/*
 * 
 * 
 * @author aThorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetRolesByConstraintsCommand implements ICommand
{
	/*
	 * Dealing with two diffrent databases. 
	 * User and Agency Information is in M204 and Role Information is in DB2
	 * 
	 */

	/**
	 * @roseuid 425AB11A00CB
	 */
	public GetRolesByConstraintsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4256E9700219
	 */
	public void execute(IEvent event)
	{
	    //87191
/*		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetRolesByConstraintsEvent getRolesEvent = (GetRolesByConstraintsEvent) event;
		String creatorFirstName = getRolesEvent.getCreatorFirstName();
		if(creatorFirstName != null && !(creatorFirstName.equals(""))){
			creatorFirstName = creatorFirstName.trim();
		}
		String creatorLastName = getRolesEvent.getCreatorLastName();
		if(creatorLastName != null && !(creatorLastName.equals(""))){
			creatorLastName = creatorLastName.trim();
		}
		
		HashMap rolesMap = this.getRoles(getRolesEvent);
		
		Collection roles = new ArrayList();
		if(creatorLastName != null && !(creatorLastName.equals("")) || (creatorFirstName != null && !(creatorFirstName.equals("")))){
			GetUserProfilesEvent userProfileEvent = new GetUserProfilesEvent();
			userProfileEvent.setLastName(creatorLastName);
			userProfileEvent.setFirstName(creatorFirstName);
			userProfileEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
			
			Iterator userProfileIter = UserProfile.findAll(userProfileEvent);
			while(userProfileIter.hasNext()){
				UserProfile userProfile = (UserProfile) userProfileIter.next();
				Iterator iter = rolesMap.values().iterator();
				while(iter.hasNext()){
					RoleResponseEvent roleResponseEvent = (RoleResponseEvent) iter.next();
					if(userProfile.getLogonId().equalsIgnoreCase(roleResponseEvent.getCreatorId())){
						dispatch.postEvent(roleResponseEvent);
					}
				}
                
			}
		}else{
        	Iterator iter = rolesMap.values().iterator(); 
        	while(iter.hasNext()){
        		RoleResponseEvent responseEvent = (RoleResponseEvent) iter.next();
        		dispatch.postEvent(responseEvent);
        	}
		}
	}
	
	private HashMap getRoles(GetRolesByConstraintsEvent getRolesEvent){
		HashMap rolesMap = new HashMap();
		String agencyName = getRolesEvent.getAgencyName();
		if(agencyName != null && !agencyName.equals("")){
			agencyName = agencyName.trim();
		}
		String agencyTypeId = getRolesEvent.getAgencyTypeId();
		String roleName = getRolesEvent.getRoleName();
		if(roleName != null && !(roleName.equals(""))){
			roleName = roleName.trim();
		}
		String roleDescription = getRolesEvent.getRoleDescription();
		if(roleDescription != null && !(roleDescription.equals(""))){
			roleDescription = roleDescription.trim();
		}
		String agencyId = "";
		if(PDSecurityHelper.isUserSA()){
			agencyId = PDSecurityHelper.getUserAgencyId();
		}else if(PDSecurityHelper.isUserMA()){
			agencyId = getRolesEvent.getAgencyId();
			if(agencyId != null && !(agencyId.equals(""))){
				agencyId = agencyId.trim();
			}
		}
		if(agencyTypeId != null && !(agencyTypeId.equals("")) || (agencyName != null && !(agencyName.equals(""))) || (agencyId != null && !(agencyId.equals("")))){
			GetAgenciesEvent getAgenciesEvent = new GetAgenciesEvent();
			getAgenciesEvent.setAgencyTypeId(agencyTypeId);
			getAgenciesEvent.setAgencyName(agencyName);
			getAgenciesEvent.setAgencyId(agencyId);
			MetaDataResponseEvent metaData = (MetaDataResponseEvent) Agency.findMeta(getAgenciesEvent);
	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	        if (metaData.getCount() > 2000){
	        	CountInfoMessage infoEvent = new CountInfoMessage();
//	        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
	          	infoEvent.setCount(metaData.getCount());  
	        	dispatch.postEvent(infoEvent);
	        }else{				
	        	Iterator agenciesIter = Agency.findAll(getAgenciesEvent);
	        	while(agenciesIter.hasNext()){
	        		Agency agency = (Agency) agenciesIter.next();
	        		getRolesEvent.setAgencyId(agency.getAgencyId());
	        		Iterator rolesIter = Role.findAll(getRolesEvent);
	        		while(rolesIter.hasNext()){
	        			Role role = (Role) rolesIter.next();
	        			RoleResponseEvent responseEvent = PDSecurityHelper.getRoleResponseEvent(role);
	        			if (!rolesMap.containsKey(role.getRoleId())){
	        				responseEvent.setAgencyId(agency.getAgencyId());
	        				responseEvent.setAgencyName(agency.getAgencyName());
	        				rolesMap.put(responseEvent.getRoleId(),responseEvent);
	        			}
	        		}
	        	}
	        }	
		}else{
			GetRolesEvent rEvent = new GetRolesEvent();
			rEvent.setRoleName(roleName);
			rEvent.setRoleDescription(roleDescription);
			rEvent.setRoleType(getRolesEvent.getRoleType());
			MetaDataResponseEvent metaData = (MetaDataResponseEvent) Role.findMeta(rEvent);
	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	        if (metaData.getCount() > 2000){
	        	CountInfoMessage infoEvent = new CountInfoMessage();
//	        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
	          	infoEvent.setCount(metaData.getCount());  
	        	dispatch.postEvent(infoEvent);
	        }else{				
	        	Iterator roleIterator = Role.findAll(rEvent);	
	        	while(roleIterator.hasNext()){
	        		Role role = (Role) roleIterator.next();
	        		RoleResponseEvent responseEvent = PDSecurityHelper.getRoleResponseEvent(role);
	        		if (!rolesMap.containsKey(role.getRoleId())){
	        			StringBuffer agencyIds = new StringBuffer();
	        			StringBuffer agencyNames = new StringBuffer();
	        			Iterator  agencyIter = role.getConstraintsByConstrainerType(Agency.class).iterator();
	        			while(agencyIter.hasNext()){
	        				Constraint cons = (Constraint) agencyIter.next();
	        				if(cons != null){
	        					Agency agency = Agency.find(cons.getConstrainerId());
	        					if(agency != null){
	        						agencyIds.append(agency.getAgencyId());
	        						agencyNames.append(agency.getAgencyName());
	        						agencyNames.append(" (");
	        						agencyNames.append(agency.getAgencyId());
	        						agencyNames.append(")");
	        						if(agencyIter.hasNext()){
	        							agencyIds.append(",");
	        							agencyNames.append(",");
	        						}
	        					}
	        				}
	        			}
	        			responseEvent.setAgencyId(agencyIds.toString());
	        			responseEvent.setAgencyName(agencyNames.toString());
	        			rolesMap.put(responseEvent.getRoleId(),responseEvent);
	        		}
	        	}	
			}
		}
		return rolesMap;*/
	}
		
	/**
	* @param event
	* @roseuid 4256E9700223
	*/
	public void onRegister(IEvent event)
	{

	}
	/**
	* @param event
	* @roseuid 4256E9700225
	*/
	public void onUnregister(IEvent event)
	{

	}

	/**
	* @param anObject
	* @roseuid 4256E9700227
	*/
	public void update(Object anObject)
	{

	}
}
