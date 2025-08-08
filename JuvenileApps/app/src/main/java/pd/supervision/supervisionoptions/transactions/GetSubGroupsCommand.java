package pd.supervision.supervisionoptions.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.supervision.Group;
import messaging.supervisionoptions.GetSubGroupsEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class GetSubGroupsCommand implements ICommand 
{

	public void execute(IEvent event) throws Exception 
	{
		GetSubGroupsEvent get_subgroups_event = (GetSubGroupsEvent)event;
			
			//retrieve parent group
		List<Group> parent_group_list =
			CollectionUtil.iteratorToList(
				Group.findAll("groupName", 
						get_subgroups_event.getParentGroupName() ));
		
		if (parent_group_list != null & parent_group_list.size() > 0)
		{
				//retrieve parent group
			Group parent_group = parent_group_list.get(0);
			
				//set parent group ID to retrieve subgroups
			get_subgroups_event.setParentGroupId(parent_group.getOID());
			
				//retrieve subgroups
			List<Group> sub_group_list = 
				CollectionUtil.iteratorToList(Group.findAll(get_subgroups_event));
			
			List sub_group_responses = new ArrayList<GroupResponseEvent>();
			for (int i=0;i<sub_group_list.size();i++)
			{
				sub_group_responses.add(
						convertGroupToResponseEvent(sub_group_list.get(i)));
			}
			
				//post responses 
			MessageUtil.postReplies(sub_group_responses);			
		}

	}//end of execute()
	
	/**
	 * Convert group to response
	 * @param group
	 * @return
	 */
	public GroupResponseEvent convertGroupToResponseEvent(Group group)
	{
		GroupResponseEvent group_response = new GroupResponseEvent();
		
			//set properties of response object
		group_response.setAgencyId(group.getAgencyId());
		group_response.setGroupId(group.getOID());
		group_response.setName(group.getGroupName());
		group_response.setParentGroupId(group.getParentGroupId());
		
		return group_response;
	}//end of convertGroupToResponseEvent()

}
