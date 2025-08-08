//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\AssociateRulesToGoalEvent.java

package messaging.caseplan;

import java.util.ArrayList;
import java.util.List;

import mojo.km.messaging.RequestEvent;

public class AssociateRulesToGoalEvent extends RequestEvent 
{
	private String goalID;
	private List ruleIDs = new ArrayList();
   
	/**
	 * @return Returns the ruleIDs.
	 */
	public List getRuleIDs() {
		return ruleIDs;
	}
	/**
	 * @param ruleIDs The ruleIDs to set.
	 */
	public void setRuleIDs(List ruleIDs) {
		this.ruleIDs = ruleIDs;
	}
	/**
	 * @param ruleIDs The ruleIDs to set.
	 */
	public void insertRuleID(String ruleID) {
		this.ruleIDs.add(ruleID);
	}
   /**
    * @roseuid 4533BCAC01F2
    */
   public AssociateRulesToGoalEvent() 
   {
    
   }
   
	/**
	 * @return Returns the goalID.
	 */
	public String getGoalID() {
		return goalID;
	}
	/**
	 * @param goalID The goalID to set.
	 */
	public void setGoalID(String goalID) {
		this.goalID = goalID;
	}

}
