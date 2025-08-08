package ui.supervision.supervisionorder;

import java.util.Collection;

public class ConditionBean 
{
	private String conditionNumber = "";
	private String conditionName = "";
	private Collection subConditions = null;
	
	/**
	 * @return the conditionName
	 */
	public String getConditionName() {
		return conditionName;
	}
	/**
	 * @param conditionName the conditionName to set
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	/**
	 * @return the subConditions
	 */
	public Collection getSubConditions() {
		return subConditions;
	}
	/**
	 * @param subConditions the subConditions to set
	 */
	public void setSubConditions(Collection subConditions) {
		this.subConditions = subConditions;
	}
	/**
	 * @return the conditionNumber
	 */
	public String getConditionNumber() {
		return conditionNumber;
	}
	/**
	 * @param conditionNumber the conditionNumber to set
	 */
	public void setConditionNumber(String conditionNumber) {
		this.conditionNumber = conditionNumber;
	}
	
}
