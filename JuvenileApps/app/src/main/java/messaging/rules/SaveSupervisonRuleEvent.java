/*
 * Created on Nov 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.rules;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveSupervisonRuleEvent extends CompositeRequest
{
	private String conditionId;
	private String monitorFrequencyId;
	private String completionStatusId;
	private Date   completionDate;
	
	private String ruleTypeId = "";
	private String additionalInformation;
	private String unformattedDesc;
	private String resolvedDesc;
	private boolean statusChanged=false;
	
	/**
	 * 
	 */
	public SaveSupervisonRuleEvent()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public String getCompletionStatusId()
	{
		return completionStatusId;
	}

	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @return
	 */
	public String getMonitorFrequencyId()
	{
		return monitorFrequencyId;
	}


	/**
	 * @param string
	 */
	public void setCompletionStatusId(String string)
	{
		completionStatusId = string;
	}

	/**
	 * @param i
	 */
	public void setConditionId(String i)
	{
		conditionId = i;
	}

	/**
	 * @param string
	 */
	public void setMonitorFrequencyId(String string)
	{
		monitorFrequencyId = string;
	}

	/**
	 * @return
	 */
	public Date getCompletionDate()
	{
		return completionDate;
	}

	/**
	 * @param date
	 */
	public void setCompletionDate(Date date)
	{
		completionDate = date;
	}


	/**
	 * @return
	 */
	public String getAdditionalInformation()
	{
		return additionalInformation;
	}

	/**
	 * @return
	 */
	public String getRuleTypeId()
	{
		return ruleTypeId;
	}

	/**
	 * @param string
	 */
	public void setAdditionalInformation(String string)
	{
		additionalInformation = string;
	}

	/**
	 * @param string
	 */
	public void setRuleTypeId(String string)
	{
		ruleTypeId = string;
	}

	/**
	 * @return Returns the resolvedDesc.
	 */
	public String getResolvedDesc() {
		return resolvedDesc;
	}
	/**
	 * @param resolvedDesc The resolvedDesc to set.
	 */
	public void setResolvedDesc(String resolvedDesc) {
		this.resolvedDesc = resolvedDesc;
	}
	/**
	 * @return Returns the unformattedDesc.
	 */
	public String getUnformattedDesc() {
		return unformattedDesc;
	}
	/**
	 * @param unformattedDesc The unformattedDesc to set.
	 */
	public void setUnformattedDesc(String unformattedDesc) {
		this.unformattedDesc = unformattedDesc;
	}
	/**
	 * @return Returns the statusChanged.
	 */
	public boolean isStatusChanged() {
		return statusChanged;
	}
	/**
	 * @param statusChanged The statusChanged to set.
	 */
	public void setStatusChanged(boolean statusChanged) {
		this.statusChanged = statusChanged;
	}
}
