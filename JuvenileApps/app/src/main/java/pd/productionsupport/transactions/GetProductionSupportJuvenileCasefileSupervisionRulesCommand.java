package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportJuvenileCasefileSupervisionRulesEvent;
import messaging.productionsupport.reply.ProductionSupportSupervisionRulesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;

public class GetProductionSupportJuvenileCasefileSupervisionRulesCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportJuvenileCasefileSupervisionRulesCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportJuvenileCasefileSupervisionRulesEvent getJuvenileCasefileSupervisionRulesEvent = (GetProductionSupportJuvenileCasefileSupervisionRulesEvent) event;
		Iterator JuvenileCasefileSupervisionRulesIter = JuvenileCaseSupervisionRule.findAll("casefileId",getJuvenileCasefileSupervisionRulesEvent.getCasefileId());
		
		while(JuvenileCasefileSupervisionRulesIter != null && JuvenileCasefileSupervisionRulesIter.hasNext()){
			JuvenileCaseSupervisionRule casefileSupervisionRules = (JuvenileCaseSupervisionRule)JuvenileCasefileSupervisionRulesIter.next();	
			ProductionSupportSupervisionRulesResponseEvent casefileSupervisionRulesResponseEvent = new ProductionSupportSupervisionRulesResponseEvent();
			
			casefileSupervisionRulesResponseEvent.setSupervisionRuleId(casefileSupervisionRules.getOID());
			casefileSupervisionRulesResponseEvent.setCompletionStatusId(casefileSupervisionRules.getCompletionStatusId());
			casefileSupervisionRulesResponseEvent.setMonitorFrequencyId(casefileSupervisionRules.getMonitorFrequencyId());
			casefileSupervisionRulesResponseEvent.setConditionId(casefileSupervisionRules.getConditionId());
			casefileSupervisionRulesResponseEvent.setCompletionDate(casefileSupervisionRules.getCompletionDate());
			casefileSupervisionRulesResponseEvent.setRuleTypeCd(casefileSupervisionRules.getRuleTypeId());
			//production support 
			if(casefileSupervisionRules.getCreateUserID() != null){
				casefileSupervisionRulesResponseEvent.setCreateUserID(casefileSupervisionRules.getCreateUserID());
			}
			if(casefileSupervisionRules.getCreateTimestamp() != null){
				casefileSupervisionRulesResponseEvent.setCreateDate(new Date(casefileSupervisionRules.getCreateTimestamp().getTime()));
			}
			if(casefileSupervisionRules.getUpdateUserID() != null){
				casefileSupervisionRulesResponseEvent.setUpdateUser(casefileSupervisionRules.getUpdateUserID());
			}
			if(casefileSupervisionRules.getUpdateTimestamp() != null){
				casefileSupervisionRulesResponseEvent.setUpdateDate(new Date(casefileSupervisionRules.getUpdateTimestamp().getTime()));
			}
			if(casefileSupervisionRules.getCreateJIMS2UserID() != null){
				casefileSupervisionRulesResponseEvent.setCreateJIMS2UserID(casefileSupervisionRules.getCreateJIMS2UserID());
			}
			if(casefileSupervisionRules.getUpdateJIMS2UserID() != null){
				casefileSupervisionRulesResponseEvent.setUpdateJIMS2UserID(casefileSupervisionRules.getUpdateJIMS2UserID());
			}
			
			dispatch.postEvent(casefileSupervisionRulesResponseEvent);
		}

	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}
