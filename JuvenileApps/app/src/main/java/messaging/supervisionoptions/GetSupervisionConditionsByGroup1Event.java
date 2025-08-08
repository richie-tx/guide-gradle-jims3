package messaging.supervisionoptions;

import java.util.List;

import mojo.km.messaging.RequestEvent;

public class GetSupervisionConditionsByGroup1Event extends RequestEvent
{
	private String group1ID;
	private List conditionIdsList;
	
	
	/**
	 * @return the group1ID
	 */
	public String getGroup1ID() {
		return group1ID;
	}
	/**
	 * @param group1ID the group1ID to set
	 */
	public void setGroup1ID(String group1ID) {
		this.group1ID = group1ID;
	}
	/**
	 * @return the conditionIdsList
	 */
	public List getConditionIdsList() {
		return conditionIdsList;
	}
	/**
	 * @param conditionIdsList the conditionIdsList to set
	 */
	public void setConditionIdsList(List conditionIdsList) {
		this.conditionIdsList = conditionIdsList;
	}
}
