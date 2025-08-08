package pd.juvenilecase.riskanalysis;

import java.util.Iterator;
import pd.codetable.ICodetable;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 433D86EB02FB
*/
public class RiskWeightedResponse extends PersistentObject implements ICodetable {
	private String response;
	private int weight;
	private String riskQuestionId;
	private RiskQuestions riskQuestion;
	private int sortOrder;
	private String subordinateQuestionId;
	private RiskQuestions subordinateQuestion;
	private String action;
	private String suggestedCasePlanDomainId;
	private RiskSuggestedCasePlanDomain suggestedCasePlanDomain;

	/**
	* @roseuid 433D86EB02FB
	*/
	public RiskWeightedResponse() {
	}
	
	public RiskWeightedResponse(RiskWeightedResponse rwe){
		this.setAction(rwe.getAction());
		this.setResponse(rwe.getResponse());
		this.setSubordinateQuestionId(rwe.getSubordinateQuestionId());
		this.setSuggestedCasePlanDomainId(rwe.getSuggestedCasePlanDomainId());
		this.setWeight(rwe.getWeight());
		this.setSortOrder(rwe.getSortOrder());
	}
	/**
	* @return CourtPolicy
	* @param courtPolicyId
	*/
	static public RiskWeightedResponse find(String weightedResponseID) {
		IHome home = new Home();
		return (RiskWeightedResponse) home.find(weightedResponseID, RiskWeightedResponse.class);
	}
	/**
	* Finds courtPolicies by an event
	* @return Iterator of CourtPolicies
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();

		return home.findAll(event, RiskWeightedResponse.class);
	}
	/**
	* Finds all courtPolicies by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();

		return home.findAll(attributeName, attributeValue, RiskWeightedResponse.class);
	}
	
	
	/**
	* @return 
	*/
	public String getResponse() {
		fetch();
		return response;
	}
	/**
	* @return 
	*/
	public int getWeight() {
		fetch();
		return weight;
	}
	/**
	* @return 
	*/
	public int getSortOrder() {
		fetch();
		return sortOrder;
	}
	/**
	* @param string
	*/
	public void setResponse(String string) {
		if (this.response == null || !this.response.equals(string)) {
			markModified();
		}
		response = string;
	}
	/**
	* @param i
	*/
	public void setWeight(int i) {
		if (this.weight != i) {
			markModified();
		}
		weight = i;
	}
	
	/**
	* @param i
	*/
	public void setSortOrder(int i) {
		if (this.sortOrder != i) {
			markModified();
		}
		sortOrder = i;
	}

	public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(RiskWeightedResponse.class);
	}

	public void inActivate() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param subordinateQuestionId
	 */
	public void setSubordinateQuestionId(String subordinateQuestionId) {
		if (this.subordinateQuestionId == null || !this.subordinateQuestionId.equals(subordinateQuestionId)) {
			markModified();
		}

		this.subordinateQuestionId = subordinateQuestionId;
	}
	
	private void initSubordinateQuestion() 
	{
		if (subordinateQuestion == null) 
		{
			subordinateQuestion = (RiskQuestions)
				new mojo.km.persistence.Reference(String.valueOf(subordinateQuestionId), RiskQuestions.class).getObject();
		}
	}

	/**
	 * @return
	 */
	public String getSubordinateQuestionId() {
		return subordinateQuestionId;
	}
	
	public RiskQuestions getSubordinateQuestion() 
	{
		fetch();
		initSubordinateQuestion();
		return subordinateQuestion;
	}
	
	public void setSubordinateQuestion(RiskQuestions subordinateQuestion)
	{
		if (this.subordinateQuestion == null || !this.subordinateQuestion.equals(subordinateQuestion)) 
		{
			markModified();
		}
		if (subordinateQuestion.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(subordinateQuestion);
		}
		setSubordinateQuestionId(subordinateQuestion.getOID());
		this.subordinateQuestion = (RiskQuestions) new mojo.km.persistence.Reference(subordinateQuestion).getObject();
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	
	private void initSuggestedCasePlainDomiain() 
	{
		if (suggestedCasePlanDomain == null) 
		{
			suggestedCasePlanDomain = (RiskSuggestedCasePlanDomain) new mojo.km.persistence.Reference(String.valueOf(suggestedCasePlanDomainId), RiskSuggestedCasePlanDomain.class).getObject();
		}
	}
	
	public String getSuggestedCasePlanDomainId() 
	{
		fetch();
		return suggestedCasePlanDomainId;
	}
	
	public void setSuggestedCasePlanDomainId(String suggestedCasePlanDomainId) 
	{
		if (this.suggestedCasePlanDomainId == null || !this.suggestedCasePlanDomainId.equals(suggestedCasePlanDomainId)) 
		{
			markModified();
		}
		suggestedCasePlanDomain = null;
		this.suggestedCasePlanDomainId = suggestedCasePlanDomainId;
	}

	public RiskSuggestedCasePlanDomain getSuggestedCasePlainDomiain() 
	{
		fetch();
		initSuggestedCasePlainDomiain();
		return suggestedCasePlanDomain;
	}
	
	public void setSuggestedCasePlanDomain(RiskSuggestedCasePlanDomain suggestedCasePlanDomain)
	{
		if (this.suggestedCasePlanDomain == null || !this.suggestedCasePlanDomain.equals(suggestedCasePlanDomain)) 
		{
			markModified();
		}
		if (suggestedCasePlanDomain.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(suggestedCasePlanDomain);
		}
		setSuggestedCasePlanDomainId(suggestedCasePlanDomain.getOID());
		this.suggestedCasePlanDomain = (RiskSuggestedCasePlanDomain) new mojo.km.persistence.Reference(suggestedCasePlanDomain).getObject();
	}
	
	private void initRiskQuestion() 
	{
		if (riskQuestion == null) 
		{
			riskQuestion = (RiskQuestions)
				new mojo.km.persistence.Reference(String.valueOf(riskQuestionId), RiskQuestions.class).getObject();
		}
	}
	
	public String getRiskQuestionId() 
	{
		fetch();
		return riskQuestionId;
	}
	
	public void setRiskQuestionId(String riskQuestionId) 
	{
		if (this.riskQuestionId == null || !this.riskQuestionId.equals(riskQuestionId)) 
		{
			markModified();
		}
		riskQuestion = null;
		this.riskQuestionId = riskQuestionId;
	}

	public RiskQuestions getRiskQuestion() 
	{
		fetch();
		initRiskQuestion();
		return riskQuestion;
	}
	
	public void setRiskQuestion(RiskQuestions riskQuestion)
	{
		if (this.riskQuestion == null || !this.riskQuestion.equals(riskQuestion)) 
		{
			markModified();
		}
		if (riskQuestion.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(riskQuestion);
		}
		setRiskQuestionId(riskQuestion.getOID());
		this.riskQuestion = (RiskQuestions) new mojo.km.persistence.Reference(riskQuestion).getObject();
	}
	
}
