/*
 * Created on Jun 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.caseplan;

import java.util.Iterator;
import messaging.caseplan.GetGoalRuleDetailsByCaseplanIdEvent;
import messaging.caseplan.reply.GoalRuleDetailsResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GoalRuleDetails extends PersistentObject {

	 private String caseGoalId;
	 private String goalDesc;
	 private String goalTimeFrameCd;
	 private String caseplanId;
	 private String responsibleName;
	 private String intervention; //added for ER JIMS200075816 
	 private String supRuleId;
	 private String caseGoalPrespId;
	 private String unformattedDesc;
	 private String resolveDesc;
	 private String goalDomainTypeCd;
	 private String goalStatusCd;
	 private String ruleMonitorFreqId;	
	 private String explainOtherTxt;
	 
	 
	/**
	 * @return Returns the caseGoalId.
	 */
	public String getCaseGoalId() {
		fetch();
		return caseGoalId;
	}
	
	public static GoalRuleDetailsResponseEvent getRespEvt(
			GoalRuleDetails details) {
		GoalRuleDetailsResponseEvent respEvt = new GoalRuleDetailsResponseEvent();
		respEvt.setCaseGoalId(details.getCaseGoalId());
		respEvt.setGoalDesc(details.getGoalDesc());
		respEvt.setGoalTimeFrameCd(details.getGoalTimeFrameCd());
		respEvt.setExplainOtherText(details.getExplainOtherTxt());
		respEvt.setCaseplanId(details.getCaseplanId());
		respEvt.setResponsibleName(details.getResponsibleName());
		respEvt.setIntervention(details.getIntervention()); //added for ER JIMS200075816 
		respEvt.setSupRuleId(details.getSupRuleId());
		respEvt.setCaseGoalPrespId(details.getCaseGoalPrespId());
		respEvt.setUnformattedDesc(details.getUnformattedDesc());
		respEvt.setResolveDesc(details.getResolveDesc());
		respEvt.setGoalDomainTypeCd(details.getGoalDomainTypeCd());
		respEvt.setGoalStatusCd(details.getGoalStatusCd());
		respEvt.setRuleMonitorFreqId(details.getRuleMonitorFreqId());
		return respEvt;
	}
	
	/**
	 * @param caseGoalId The caseGoalId to set.
	 */
	public void setCaseGoalId(String caseGoalId) {
		this.caseGoalId = caseGoalId;
	}
	/**
	 * @return Returns the caseplanId.
	 */
	public String getCaseplanId() {
		fetch();
		return caseplanId;
	}
	/**
	 * @param caseplanId The caseplanId to set.
	 */
	public void setCaseplanId(String caseplanId) {
		this.caseplanId = caseplanId;
	}
	
	/**
	 * @return Returns the goalDesc.
	 */
	public String getGoalDesc() {
		fetch();
		return goalDesc;
	}
	/**
	 * @param goalDesc The goalDesc to set.
	 */
	public void setGoalDesc(String goalDesc) {
		this.goalDesc = goalDesc;
	}
	/**
	 * @return Returns the goalTimeFrameCd.
	 */
	public String getGoalTimeFrameCd() {
		fetch();
		return goalTimeFrameCd;
	}
	/**
	 * @param goalTimeFrameCd The goalTimeFrameCd to set.
	 */
	public void setGoalTimeFrameCd(String goalTimeFrameCd) {
		this.goalTimeFrameCd = goalTimeFrameCd;
	}
	
	/**
	 * @return Returns the intervention.
	 */
	public String getIntervention() {
		fetch();
		return intervention;
	}
	/**
	 * @param intervention The intervention to set.
	 */
	public void setIntervention(String intervention) {
		this.intervention = intervention;
	}

	/**
	 * @return Returns the responsibleName.
	 */
	public String getResponsibleName() {
		fetch();
		return responsibleName;
	}
	/**
	 * @param responsibleName The responsibleName to set.
	 */
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}
	/**
	 * @return Returns the supRuleId.
	 */
	public String getSupRuleId() {
		fetch();
		return supRuleId;
	}
	/**
	 * @param supRuleId The supRuleId to set.
	 */
	public void setSupRuleId(String supRuleId) {
		this.supRuleId = supRuleId;
	}
	
	
	/**
	 * @return Iterator contacts
	 * @param attrName
	 *            name fo the attribute for where clause
	 * @param attrValue
	 *            value to be checked in the where clause
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
	    return new Home().findAll(attrName, attrValue, GoalRuleDetails.class);
	}
	/**
	 * @param request
	 * @return
	 */
	public static Iterator findAll(GetGoalRuleDetailsByCaseplanIdEvent request) {
		return new Home().findAll(request, GoalRuleDetails.class);
	}
	
	/**
	 * @return Returns the caseGoalPrespId.
	 */
	public String getCaseGoalPrespId() {
		fetch();
		return caseGoalPrespId;
	}
	/**
	 * @param caseGoalPrespId The caseGoalPrespId to set.
	 */
	public void setCaseGoalPrespId(String caseGoalPrespId) {
		this.caseGoalPrespId = caseGoalPrespId;
	}
	/**
	 * @return Returns the resolveDesc.
	 */
	public String getResolveDesc() {
		fetch();
		return resolveDesc;
	}
	/**
	 * @param resolveDesc The resolveDesc to set.
	 */
	public void setResolveDesc(String resolveDesc) {
		this.resolveDesc = resolveDesc;
	}
	/**
	 * @return Returns the unformattedDesc.
	 */
	public String getUnformattedDesc() {
		fetch();
		return unformattedDesc;
	}
	/**
	 * @param unformattedDesc The unformattedDesc to set.
	 */
	public void setUnformattedDesc(String unformattedDesc) {
		this.unformattedDesc = unformattedDesc;
	}
	
	/**
	 * @return Returns the goalDomainTypeCd.
	 */
	public String getGoalDomainTypeCd() {
		fetch();
		return goalDomainTypeCd;
	}
	/**
	 * @param goalDomainTypeCd The goalDomainTypeCd to set.
	 */
	public void setGoalDomainTypeCd(String goalDomainTypeCd) {
		this.goalDomainTypeCd = goalDomainTypeCd;
	}
	/**
	 * @return Returns the goalStatusCd.
	 */
	public String getGoalStatusCd() {
		fetch();
		return goalStatusCd;
	}
	/**
	 * @param goalStatusCd The goalStatusCd to set.
	 */
	public void setGoalStatusCd(String goalStatusCd) {
		this.goalStatusCd = goalStatusCd;
	}

	/**
	 * @return the ruleMonitorFreqId
	 */
	public String getRuleMonitorFreqId() {
		fetch();
		return ruleMonitorFreqId;
	}

	/**
	 * @param ruleMonitorFreqId the ruleMonitorFreqId to set
	 */
	public void setRuleMonitorFreqId(String ruleMonitorFreqId) {
		this.ruleMonitorFreqId = ruleMonitorFreqId;
	}
	/**
	 * @return the explainOtherTxt
	 */
	public String getExplainOtherTxt() {
		fetch();
		return explainOtherTxt;
	}

	/**
	 * @param explainOtherTxt the explainOtherTxt to set
	 */
	public void setExplainOtherTxt(String explainOtherTxt) {
		this.explainOtherTxt = explainOtherTxt;
	}	
}
