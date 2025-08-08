/*
 * Created on Nov 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.juvenilecase.Rule;
import ui.security.SecurityUIHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;

/**
 * @author athorat
 *
 */
public class SupervisionConditionForm extends ActionForm
{
	// Default field
	private String action = "";

	private Collection conditions = new ArrayList();			// Conditions from search results.
	private Collection selectedConditions = new ArrayList(); 	// Conditions selected to become rules.
	private Collection rules = new ArrayList();					// Rules created from conditions.
	
	private String value;
	private String group1Id;
	private String group2Id;
	private String group3Id;
	private boolean standard;

	private Collection groups = new ArrayList();
	private String agencyId;

	private Collection monitorFreqCodes = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.MONITOR_FREQUENCY,true);
	private Collection ruleTypeCodes = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SUPERVISION_RULES_TYPE,true);
	
	//added for US 22839
	private Collection selectedRules = new ArrayList();
	private Collection activeCasefiles = new ArrayList();
	private String selectedCasefile;
	private JuvenileCasefileSearchResponseEvent rulesTransferCaseile = new JuvenileCasefileSearchResponseEvent();

	/**
	 * 
	 */
	public SupervisionConditionForm()
	{
		
		super();
		UIUtil.sortCodesByCodeId(monitorFreqCodes);
	}

	public void setValue(String key, Object value)
	{
		
		String ruleId = null;
		String propName = null;
	
		// parse key into rule id and element name		
		StringTokenizer tokenizer = new StringTokenizer(key,"_");
		if ( tokenizer.hasMoreTokens() )
		{
			ruleId = tokenizer.nextToken();
		}
		
		if ( tokenizer.hasMoreTokens() )
		{
			propName = tokenizer.nextToken();
		}

		/// find rule
		Rule rule = null;
		Iterator ruleIter = rules.iterator();
		while ( ruleIter.hasNext() )
		{
			rule = (Rule)ruleIter.next();
			if ( rule.getConditionId().equals(ruleId) )
			{
				break;
			}
		}
		
		// set value if FREQ or element
		if ( rule != null )
		{
			if ( "FREQ".equals(propName) )
			{
				rule.setMonitorFreqId( value.toString() );
			}
			else if ( "RULETYPE".equals(propName) )
			{
				rule.setRuleTypeId( value.toString() );
			}
			else if ( "ADDITIONALINFO".equals(propName) )
			{
				rule.setAdditionalInformation( value.toString() );
			}
			else
			{
				Iterator valIter = rule.getVariables().iterator();
				while ( valIter.hasNext() )
				{
					VariableElementResponseEvent var = (VariableElementResponseEvent)valIter.next();
					if ( var.getName().equals(propName) )
					{
						if ( var.isEnumeration() )
						{
							var.setValueId( value.toString() );
							
							Collection codes = CodeHelper.getCodes(var.getCodeTableName());
							String desc = CodeHelper.getCodeDescriptionByCode( codes, value.toString() );
							var.setValue( desc );
						}
						else
						{
							var.setValue( value.toString() );
						}
					}
				}
			}
		}
	}

	public Object getValue(String key)
	{
		// Not used.
		return "";
	}
	
	
/*	NOT USING STRUTS FORM VALIDATION
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();

		Iterator ruleIter = rules.iterator();
		while ( ruleIter.hasNext() )
		{
			Rule rule = (Rule)ruleIter.next();
			
			Iterator valIter = rule.getVariables().iterator();
			while ( valIter.hasNext() )
			{
				VariableElementResponseEvent var = (VariableElementResponseEvent)valIter.next();
				if ( ! var.isEnumeration() )
				{
					if ( var.getValue() == null || var.getValue().trim().length() == 0 )
					{
						errors.add( var.getName(), new ActionError("errors.add") );
					}
				}
			}
		}
		
		return errors;
	}
*/
	/**
	 * @return
	 */
	public Collection getConditions()
	{
		return conditions;
	}

	/**
	 * @param collection
	 */
	public void addCondition(ConditionDetailResponseEvent condition)
	{
		if (condition != null)
		{
			conditions.add(condition);
		}
	}

	/**
	 * @return
	 */
	public Collection getRules()
	{
		return rules;
	}

	/**
	 * @return
	 */
	public String getGroup1Id()
	{
		return group1Id;
	}

	/**
	 * @return
	 */
	public String getGroup2Id()
	{
		return group2Id;
	}

	/**
	 * @return
	 */
	public String getGroup3Id()
	{
		return group3Id;
	}

	/**
	 * @param string
	 */
	public void setGroup1Id(String string)
	{
		group1Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup2Id(String string)
	{
		group2Id = string;
	}

	/**
	 * @param string
	 */
	public void setGroup3Id(String string)
	{
		group3Id = string;
	}

	/**
	 * @return
	 */
	public boolean isStandard()
	{
		return standard;
	}

	/**
	 * @param b
	 */
	public void setStandard(boolean b)
	{
		standard = b;
	}

	public void clearAll()
	{
		conditions.clear();
		rules.clear();
		groups.clear();
		agencyId = "";
		action = "";
		value = "";
		group1Id = "";
		group2Id = "";
		group3Id = "";
		standard = false;

	}
	public void clearRules()
	{
		rules.clear();
	}

	// TODO get real agency
	public String getAgencyId()
	{
		if (agencyId == null || agencyId.equals(""))
		{
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}

	/**
	 * @return
	 */
	public Collection getGroups()
	{
		if (groups == null || groups.size() == 0)
		{
			// get groups	
			groups = UISupervisionOptionHelper.fetchGroups(getAgencyId());
		}
		return groups;
	}
	
	/**
	 * This is to get different display depending on which agency the user belongs to
	 */
	public String getConditionLiteralCaption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.literal";

		return "prompt.condition";
	}

	public String getGroup1Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.category";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.conditionType";

		return "prompt.group1";
	}

	public String getGroup2Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.type";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.conditionSubType";

		return "prompt.group2";
	}

	public String getGroup3Caption()
	{
		this.getAgencyId();
		if (agencyId.equalsIgnoreCase(UIConstants.JUV))
			return "prompt.subtype";
		else
			if (agencyId.equalsIgnoreCase(UIConstants.PTR))
				return "prompt.subTypeDetail";

		return "prompt.group3";
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @return
	 */
	public Collection getSelectedConditions()
	{
		return selectedConditions;
	}


	/**
	 * @return
	 */
	public Collection getMonitorFreqCodes()
	{
		return monitorFreqCodes;
	}

	/**
	 * @return
	 */
	public Collection getRuleTypeCodes()
	{
		return ruleTypeCodes;
	}


	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
	}

	/**
	 * @param rules The rules to set.
	 */
	public void setRules(Collection rules) {
		this.rules = rules;
	}

	/**
	 * @return the selectedRules
	 */
	public Collection getSelectedRules() {
		return selectedRules;
	}

	/**
	 * @param selectedRules the selectedRules to set
	 */
	public void setSelectedRules(Collection selectedRules) {
		this.selectedRules = selectedRules;
	}

	/**
	 * @return the activeCasefiles
	 */
	public Collection getActiveCasefiles() {
		return activeCasefiles;
	}

	/**
	 * @param activeCasefiles the activeCasefiles to set
	 */
	public void setActiveCasefiles(Collection activeCasefiles) {
		this.activeCasefiles = activeCasefiles;
	}

	/**
	 * @return the selectedCasefile
	 */
	public String getSelectedCasefile() {
		return selectedCasefile;
	}

	/**
	 * @param selectedCasefile the selectedCasefile to set
	 */
	public void setSelectedCasefile(String selectedCasefile) {
		this.selectedCasefile = selectedCasefile;
	}

	/**
	 * @return the rulesTransferCaseile
	 */
	public JuvenileCasefileSearchResponseEvent getRulesTransferCaseile() {
		return rulesTransferCaseile;
	}

	/**
	 * @param rulesTransferCaseile the rulesTransferCaseile to set
	 */
	public void setRulesTransferCaseile(JuvenileCasefileSearchResponseEvent rulesTransferCaseile) {
		this.rulesTransferCaseile = rulesTransferCaseile;
	}
}
