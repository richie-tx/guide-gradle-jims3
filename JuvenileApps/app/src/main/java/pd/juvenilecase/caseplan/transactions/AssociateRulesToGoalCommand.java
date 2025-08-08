//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\AssociateRulesToGoalCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.util.Iterator;
import java.util.List;

import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import messaging.caseplan.AssociateRulesToGoalEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class AssociateRulesToGoalCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B80B013D
    */
   public AssociateRulesToGoalCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE42701CA
    */
   public void execute(IEvent event) 
   {
   		AssociateRulesToGoalEvent request = (AssociateRulesToGoalEvent)event;
   		String goalID = request.getGoalID();
   		List ruleIDs = request.getRuleIDs();
   		Goal goal = Goal.find(goalID);
   		for(Iterator iter = ruleIDs.iterator(); iter.hasNext(); ) {
   			String ruleId = (String)iter.next();
   			JuvenileCaseSupervisionRule rule = JuvenileCaseSupervisionRule.find(ruleId);
   			goal.insertRules(rule);
   		}
   		
   }
   
   /**
    * @param event
    * @roseuid 452FE42701D3
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE42701D5
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 452FE42701DD
    */
   public void update(Object anObject) 
   {
    
   }
   

}
