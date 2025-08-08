package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetSubGroupsEvent extends RequestEvent 
{
	private String parentGroupName;
	private String parentGroupId;
	private int groupLevel;

	public String getParentGroupName() {
		return parentGroupName;
	}
	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}
	public int getGroupLevel() {
		return groupLevel;
	}
	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}
	public String getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	
	
}
