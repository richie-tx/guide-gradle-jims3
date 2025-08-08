package ui.juvenilecase;

import gnu.regexp.RE;
import gnu.regexp.REException;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.GroupHelper;

/**
 * @author athorat
 *
 */
public class Rule implements Comparable
{
	private static Collection monitorFreqCodes = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.MONITOR_FREQUENCY,true);
	//<KISHORE>JIMS200060662 : Modify Casefile Closing Rules reqs and data(PD)-KK
	private static Collection completionStatusCodes = CodeHelper.getCompletionStatusCodes();
	
	private String 	ruleId;
	private String 	ruleLiteral;
	private String 	monitorFreqId;
	private String 	completionStatusId;
	private String 	originalStatusId;
	private Date 	completionDate;
	private Date    entryDate;
	private String  condCategoryId;
	private String 	condTypeId;
	private String  condSubtypeId; 
	private String  conditionId; 
	private Map variables = new HashMap();
	private String 	ruleTypeId;
	private String ruleTypeDesc;
	private String 	additionalInformation;
	private String ruleName;
	private boolean standard;

	// Cached values.	
	private String 	monitorFreq;
	private String ruleMonitorFreqDesc;
	private String  condCategory;
	private String 	condType;
	private String  condSubtype; 
	private String 	completionStatus;
	
	private String unformattedDesc;
	private String resolvedDesc;
	
	// edit status previous value
	
	
	/**
	 * 
	 */
	public Rule()
	{
		super();
	}

	/**
	 * @return
	 */
	public String getMonitorFreqId()
	{
		return monitorFreqId;
	}

	/**
	 * @param string
	 */
	public void setMonitorFreqId(String string)
	{
		monitorFreqId = string;
		monitorFreq="";
		if(string!=null && !string.equals(""))
			monitorFreq = CodeHelper.getCodeDescriptionByCode( monitorFreqCodes, monitorFreqId );
		ruleMonitorFreqDesc= monitorFreq;
	}

	/**
	 * @param string
	 */
	public void setRuleLiteral(String string)
	{
		ruleLiteral = string;
	}

	/**
	 * @return
	 */
	public Date getCompletionDate()
	{
		return completionDate;
	}

	/**
	 * @return
	 */
	public String getCompletionStatusId()
	{
		if(completionStatusId==null)
			return  "";
		return completionStatusId;
	}

	/**
	 * @param date
	 */
	public void setCompletionDate(Date date)
	{
		completionDate = date;
	}

	public boolean isEditable(){
		boolean returnType=true;
		if(originalStatusId!=null && !originalStatusId.equals("")){
			if(originalStatusId.equals(UIConstants.RULE_STATUS_COMPLETE)
					|| originalStatusId.equals(UIConstants.RULE_STATUS_INACTIVE)
					|| originalStatusId.equals(UIConstants.RULE_STATUS_NON_COMPLIANT)){
				returnType=false;
			}
		}
		return returnType;
	}
	
	/**
	 * @param string
	 */
	public void setCompletionStatusId(String string)
	{
		completionStatusId = string;
		completionStatus= CodeHelper.getCodeDescriptionByCode( completionStatusCodes, completionStatusId );
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
	public String getCondCategoryId()
	{
		return condCategoryId;
	}

	/**
	 * @return
	 */
	public String getCondSubtypeId()
	{
		return condSubtypeId;
	}

	/**
	 * @return
	 */
	public String getCondTypeId()
	{
		return condTypeId;
	}

	/**
	 * @param string
	 */
	public void setCondCategoryId(String string)
	{
		condCategoryId = string;
		condCategory = GroupHelper.getGroupName( condCategoryId );
	}

	/**
	 * @param string
	 */
	public void setCondSubtypeId(String string)
	{
		condSubtypeId = string;
		condSubtype = GroupHelper.getSubTypeName( condSubtypeId );
	}

	/**
	 * @param string
	 */
	public void setCondTypeId(String string)
	{
		condTypeId = string;
		condType = GroupHelper.getTypeName( condTypeId );
	}

	/**
	 * @return
	 */
	public String getCondCategory()
	{
		return condCategory;
	}

	/**
	 * @return
	 */
	public String getCondSubtype()
	{
		return condSubtype;
	}

	/**
	 * @return
	 */
	public String getCondType()
	{
		return condType;
	}

	/**
	 * @return
	 */
	public String getMonitorFreq()
	{
		return monitorFreq;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj)
	{
		Rule rule = (Rule) obj;
		int result = 0;
		if(ruleId != null && rule.getRuleId() != null )
		{
			try{
				result = Integer.valueOf(ruleId).compareTo(Integer.valueOf(rule.getRuleId()));
			}
			catch(NumberFormatException e){
				result = 0;
			}
		}
		return result;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @return
	 */
	public String getCompletionStatus()
	{
		return completionStatus;
	}

	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

	public Collection getVariables()
	{
		return variables.values();
	}
	
	public void addVariable( VariableElementResponseEvent evt )
	{
		variables.put( evt.getName(), evt );
	}


	/**
	 * @return The rule literal with the reference variables resolved.
	 */
	public String getRuleLiteral()
	{
		String literal = ruleLiteral;
		
		Iterator iter = getVariables().iterator();
		while ( iter.hasNext() )
		{
			VariableElementResponseEvent evt = (VariableElementResponseEvent)iter.next();
			
			if ( evt.isReference() )
			{
				String token = "\\[" + evt.getName() + "\\]";
				
				RE regex = null;
				try
				{
					regex = new RE( token, RE.REG_ICASE );
					literal = regex.substituteAll( literal, evt.getValue() );
				}
				catch (REException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return literal;
	}
	
	
	/**
	 * @return The rule literal with non of the variables resolved.
	 */
	public String getRuleLiteralUnresolved()
	{
		return ruleLiteral;
	}

	/**
	 * @return The rule literal with all of the variables resolved.
	 */
	public String getRuleLiteralResolved()
	{
		String name = null;
		String literal = ruleLiteral;
		
		Iterator iter = getVariables().iterator();
		while ( iter.hasNext() )
		{
			VariableElementResponseEvent evt = (VariableElementResponseEvent)iter.next();
			
			if ( evt.isReference() )
			{
				name = "\\[" + evt.getName() + "\\]";
			}
			else
			{
				name = "{{" + evt.getName() + "}}";
			}
			RE regex = null;
			try
			{
				String value = null;
				if ( evt.isEnumeration() )
				{
					Collection codes = CodeHelper.getCodes( evt.getCodeTableName() );
					value = CodeHelper.getCodeDescriptionByCode( codes, evt.getValueId() );
					if((value==null || value.trim().equals("")) && evt.getValue()!=null && !evt.getValue().trim().equals("")){
						try{
							value = CodeHelper.getCodeDescriptionByCode( codes, evt.getValue() );
							if((value==null || value.trim().equals(""))) {
								value=evt.getValue();
							}
						}
						catch (Exception e){
							value=evt.getValue();
						}
					}
				}
				else
				{
					value = evt.getValue();
				}

				regex = new RE( name, RE.REG_ICASE );
				if(value!=null){
					literal = regex.substituteAll( literal, value );
				}
			}
			catch (REException e)
			{
				e.printStackTrace();
			}
		}
		
		return literal;
	}

	/**
	 * @return
	 */
	public String getAdditionalInformation()
	{
		return additionalInformation;
	}

	/**
	 * @param string
	 */
	public void setAdditionalInformation(String string)
	{
		additionalInformation = string;
	}

	/**
	 * @return
	 */
	public String getRuleTypeId()
	{
		return ruleTypeId;
	}

	/**
	 * @return
	 */
	public String getRuleTypeDesc()
	{
		return ruleTypeDesc;
	}

	/**
	 * @param string
	 */
	public void setRuleTypeId(String string)
	{
		if(string==null || string.equals(""))
		{
			ruleTypeDesc="";
		}
		else{
			ruleTypeDesc =	CodeHelper.getCodeDescriptionByCode(CodeHelper.getCodes(PDCodeTableConstants.SUPERVISION_RULES_TYPE), string);
		}
		
		ruleTypeId = string;
	}

	public String getFreqKeyId()
	{
		return this.getConditionId() + "_" + "FREQ";
	}

	public String getRuleTypeKeyId()
	{
		return this.getConditionId() + "_" + "RULETYPE";
	}
	
	public String getAdditInfoKeyId()
	{
		return this.getConditionId() + "_" + "ADDITIONALINFO";
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
	 * @return Returns the originalStatusId.
	 */
	public String getOriginalStatusId() {
		return originalStatusId;
	}
	/**
	 * @param originalStatusId The originalStatusId to set.
	 */
	public void setOriginalStatusId(String originalStatusId) {
		this.originalStatusId = originalStatusId;
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
	
	public boolean hasStatusChanged(){
		boolean retVal=false;
		if(getCompletionStatusId()!=null && getOriginalStatusId()!=null){
			if(getCompletionStatusId().equals(getOriginalStatusId())){
			}
			else{
				retVal=true;
			}
		}
		else if(getCompletionStatusId()!=null && getOriginalStatusId()==null){
			retVal=true;
		}
		else if(getCompletionStatusId()==null && getOriginalStatusId()!=null){
			retVal=true;
		}
		return retVal;
	}
}
