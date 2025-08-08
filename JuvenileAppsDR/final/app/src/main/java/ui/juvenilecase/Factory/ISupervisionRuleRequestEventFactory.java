/*
 * Created on Dec 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.Factory;

import messaging.rules.GetJuvenileCasefileSupervisionRuleDetailsEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRulesEvent;
import messaging.rules.GetJuvenileSupervisionRulesEvent;
import messaging.rules.SaveJuvenileCasefileSupervisionRulesEvent;
import messaging.supervisionoptions.SearchSupervisionConditionsDetailsEvent;
import ui.juvenilecase.form.SupervisionConditionForm;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ISupervisionRuleRequestEventFactory
{
	// 
	SaveJuvenileCasefileSupervisionRulesEvent buildSaveSupervisionRulesEvent(SupervisionConditionForm conditionForm, String supervisionNum);
	SearchSupervisionConditionsDetailsEvent buildSearchSupervisionConditionsDetailsEvent(SupervisionConditionForm conditionForm,String supervisionTypeId, boolean standard);
	GetJuvenileCasefileSupervisionRulesEvent buildGetJuvenileCasefileSupervisionRulesEvent(String supervisionNum);
	GetJuvenileCasefileSupervisionRuleDetailsEvent buildGetJuvenileCasefileSupervisionRuleDetailsEvent(String ruleId);
	GetJuvenileSupervisionRulesEvent buildGetJuvenileSupervisionRulesEvent(String juvenileNum);
}
