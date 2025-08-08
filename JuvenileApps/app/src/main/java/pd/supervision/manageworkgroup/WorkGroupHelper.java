/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.manageworkgroup;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import pd.contact.PDContactHelper;
import pd.contact.user.UserProfile;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.manageworkgroup.GetWorkGroupByExactNameEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.utilities.CollectionUtil;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WorkGroupHelper {

	public static WorkGroupResponseEvent getWorkGroupResponseEvent( WorkGroup workGroup ){
	    WorkGroupResponseEvent respEvent = new WorkGroupResponseEvent();
	    respEvent.setWorkgroupId(workGroup.getOID().toString());
	    respEvent.setAgencyId(workGroup.getAgencyId());
	    respEvent.setWorkgroupName(workGroup.getName());
	    respEvent.setWorkgroupDescription(workGroup.getDescription());
	    respEvent.setWorkgroupTypeId(workGroup.getTypeId());
//      just for testing its mapping. will be removed later
//	    Code type = workGroup.getType();
	    // get users
	    Collection users = workGroup.getUserProfiles();
	    for(Iterator iter = users.iterator(); iter.hasNext(); ){
	        UserProfile userProfile = (UserProfile)iter.next();
			SecurityUserResponseEvent responseEvent = PDContactHelper.getSecurityUserResponseEvent(userProfile);
	        respEvent.addUser(responseEvent);
	        userProfile = null;
	    }

	    // TODO Task needs to be implemented

	    users.clear();
	    users = null;
	    return respEvent;
	}   
	public static WorkGroup fetchWorkgroupByName(String agencyId, String type, String name)
    {

        GetWorkGroupByExactNameEvent requestEvent = new GetWorkGroupByExactNameEvent();

        requestEvent.setAgencyId(agencyId);
        requestEvent.setName(name);
        requestEvent.setType(type);
        
		Iterator wgIter = WorkGroup.findAll( requestEvent );
		List aList = CollectionUtil.iteratorToList(wgIter);
		WorkGroup wg = null;
		if (aList != null && aList.size() > 0){
			wg = (WorkGroup) aList.get(0);
		}
		
		requestEvent = null;
		aList.clear();
		aList = null;
		
        return wg;

    }

}
