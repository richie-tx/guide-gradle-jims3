/*
 * Created on Nov 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;



import naming.PDCodeTableConstants;

import messaging.caseplan.reply.GoalListResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ldeen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RuleResponseEvent extends ResponseEvent implements Comparable
{
	  //Fields for ruleList.jsp for pd
	  private Date ruleEntryDate = null;
	  private String condCategoryId = "";
	  private String condTypeId = "";
	  private String condSubTypeId = "";
	  private Date ruleCompletionDate = null;
	  private String ruleCompletionStatusId = "";
	  private String ruleCompletionStatus = "";
	  private String ruleMonitorFreqId;
	  private String ruleMonitorFreqDesc;
	  private String ConditionId;
	  private String ruleTypeId = "";
		private String ruleType = "";
		private String  ruleTypeDesc="";
	  private String ruleId;
	  private String unformattedDesc;
		private String resolvedDesc;
		private String ruleName;
		private boolean standard;
	
	  //Collections & Drop Down Lists;
	  private Collection assignedRulesList= new ArrayList();
	  
	  // being used in caseplan.
	  private boolean currentlySelected;

	  	  
	/**
	 * @return
	 */
	public Collection getAssignedRulesList()
	{
		return assignedRulesList;
	}

	/**
	 * @return
	 */
	public String getCondCategoryId()
	{
		return condCategoryId;
	}

	/**
	 * @return
	 */
	public Date getRuleCompletionDate()
	{
		return ruleCompletionDate;
	}

	/**
	 * @return
	 */
	public String getRuleCompletionStatusId()
	{
		return ruleCompletionStatusId;
	}

	/**
	 * @return
	 */
	public String getCondSubTypeId()
	{
		return condSubTypeId;
	}

	/**
	 * @return
	 */
	public String getCondTypeId()
	{
		return condTypeId;
	}

	/**
	 * @param collection
	 */
	public void setAssignedRulesList(Collection collection)
	{
		assignedRulesList = collection;
	}

	/**
	 * @param string
	 */
	public void setCondCategoryId(String string)
	{
		condCategoryId = string;
	}

	/**
	 * @param date
	 */
	public void setRuleCompletionDate(Date date)
	{
		ruleCompletionDate = date;
	}

	/**
	 * @param string
	 */
	public void setRuleCompletionStatusId(String string)
	{
		ruleCompletionStatusId = string;
	}

	/**
	 * @param string
	 */
	public void setCondSubTypeId(String string)
	{
		condSubTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setCondTypeId(String string)
	{
		condTypeId = string;
	}

	/**
	 * @return
	 */
	public Date getRuleEntryDate()
	{
		return ruleEntryDate;
	}

	/**
	 * @param date
	 */
	public void setRuleEntryDate(Date date)
	{
		ruleEntryDate = date;
	}

	/**
	 * @return
	 */
	public String getRuleId()
	{
		return ruleId;
	}

	/**
	 * @param string
	 */
	public void setRuleId(String string)
	{
		ruleId = string;
	}

	/**
	 * @return
	 */
	public String getRuleMonitorFreqId()
	{
		return ruleMonitorFreqId;
	}

	/**
	 * @param string
	 */
	public void setRuleMonitorFreqId(String string)
	{
		ruleMonitorFreqId = string;
		
	}
	
	
	/**
	 * @return
	 */
	public String getConditionId()
	{
		return ConditionId;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		ConditionId = string;
	}
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		RuleResponseEvent evt = (RuleResponseEvent) obj;
		return this.ruleId.compareToIgnoreCase(evt.getRuleId());		
	}	
	/**
	 * @return Returns the currentlySelected.
	 */
	public boolean isCurrentlySelected() {
		return currentlySelected;
	}
	/**
	 * @param currentlySelected The currentlySelected to set.
	 */
	public void setCurrentlySelected(boolean currentlySelected) {
		this.currentlySelected = currentlySelected;
	}
	
	/**
	 * @return
	 */
	public String getRuleType()
	{
		return ruleType;
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
	public void setRuleType(String string)
	{
		ruleType = string;
	}

	/**
	 * @param string
	 */
	public void setRuleTypeId(String string)
	{
		ruleTypeId = string;
	
	}
	/**
	 * @return Returns the ruleMonitorFreqDesc.
	 */
	public String getRuleMonitorFreqDesc() {
		return ruleMonitorFreqDesc;
	}
	/**
	 * @param ruleMonitorFreqDesc The ruleMonitorFreqDesc to set.
	 */
	public void setRuleMonitorFreqDesc(String ruleMonitorFreqDesc) {
		this.ruleMonitorFreqDesc = ruleMonitorFreqDesc;
	}
		/**
		 * @return Returns the ruleTypeDesc.
		 */
		public String getRuleTypeDesc() {
			return ruleTypeDesc;
		}
		/**
		 * @param ruleTypeDesc The ruleTypeDesc to set.
		 */
		public void setRuleTypeDesc(String ruleTypeDesc) {
			this.ruleTypeDesc = ruleTypeDesc;
		}
	/**
	 * @return Returns the ruleCompletionStatus.
	 */
	public String getRuleCompletionStatus() {
		return ruleCompletionStatus;
	}
	/**
	 * @param ruleCompletionStatus The ruleCompletionStatus to set.
	 */
	public void setRuleCompletionStatus(String ruleCompletionStatus) {
		this.ruleCompletionStatus = ruleCompletionStatus;
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
		 * @return Returns the ruleName.
		 */
		public String getRuleName() {
			return ruleName;
		}
		/**
		 * @param ruleName The ruleName to set.
		 */
		public void setRuleName(String ruleName) {
			this.ruleName = ruleName;
		}
		/**
		 * @return Returns the standard.
		 */
		public boolean isStandard() {
			return standard;
		}
		/**
		 * @param standard The standard to set.
		 */
		public void setStandard(boolean standard) {
			this.standard = standard;
		}
}
