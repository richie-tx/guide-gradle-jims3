package pd.juvenilecase.rules;

import java.util.Date;
import java.util.Iterator;
import pd.supervision.supervisionoptions.Condition;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class RuleGroupConditionView extends PersistentObject {
	private String completionStatusId;
	private String ruleId;
	private int group1Id;
	private int group3Id;
	private String literal;
	private boolean standard;
	private String conditionId;
	public Condition condition = null;
	private String casefileId;
	private String name;
	private int group2Id;
	private Date completionDate;
	private String monitorFreqId;
	private Date createdDate;
	private String ruleTypeId;
	private String unformattedDesc;
	private String resolvedDesc;
	/**
	* @return 
	*/
	public String getCasefileId() {
		fetch();
		return casefileId;
	}
	/**
	* @return 
	*/
	public String getCompletionStatusId() {
		fetch();
		return completionStatusId;
	}
	/**
	* @return 
	*/
	public String getConditionId() {
		fetch();
		return conditionId;
	}
	/**
	* @return 
	*/
	public int getGroup1Id() {
		fetch();
		return group1Id;
	}
	/**
	* @return 
	*/
	public int getGroup2Id() {
		fetch();
		return group2Id;
	}
	/**
	* @return 
	*/
	public int getGroup3Id() {
		fetch();
		return group3Id;
	}
	/**
	* @return 
	*/
	public String getLiteral() {
		fetch();
		return literal;
	}
	/**
	* @return 
	*/
	public String getMonitorFreqId() {
		fetch();
		return monitorFreqId;
	}
	/**
	* @return 
	*/
	public String getName() {
		fetch();
		return name;
	}
	/**
	* @return 
	*/
	public String getRuleId() {
		fetch();
		return ruleId;
	}
	/**
	* @return 
	*/
	public boolean isStandard() {
		fetch();
		return standard;
	}
	/**
	* @param string
	*/
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	/**
	* @param string
	*/
	public void setCompletionStatusId(String completionStatusId) {
		this.completionStatusId = completionStatusId;
	}
	/**
	* @param string
	*/
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	
	/**
	 * Initialize class relationship to class pd.supervision.supervisionoptions.Condition
	 */
	private void initCondition()
	{
		if (condition == null)
		{
			condition = (Condition) new mojo.km.persistence.Reference(conditionId,
					Condition.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.supervision.supervisionoptions.Condition
	 * @methodInvocation fetch
	 * @methodInvocation initCondition
	 */
	public Condition getCondition()
	{
		fetch();
		initCondition();
		return condition;
	}

	/**
	 * set the type reference for class member condition
	 * @methodInvocation bind
	 * @methodInvocation setConditionId
	 */
	public void setCondition(Condition condition)
	{
		setConditionId("" + condition.getOID());
		this.condition = (Condition) new mojo.km.persistence.Reference(condition)
				.getObject();
	}

	
	/**
	* @param i
	*/
	public void setGroup1Id(int i) {
		this.group1Id = i;
	}
	/**
	* @param i
	*/
	public void setGroup2Id(int i) {
		this.group2Id = i;
	}
	/**
	* @param i
	*/
	public void setGroup3Id(int i) {
		this.group3Id = i;
	}
	/**
	* @param string
	*/
	public void setLiteral(String literal) {
		this.literal = literal;
	}
	/**
	* @param string
	*/
	public void setMonitorFreqId(String monitorFreqId) {
		this.monitorFreqId = monitorFreqId;
	}
	/**
	* @param string
	*/
	public void setName(String name) {
		this.name = name;
	}
	/**
	* @param string
	*/
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	* @param b
	*/
	public void setStandard(boolean b) {
		this.standard = b;
	}
	/**
	* Finds all RuleGroupConditionView by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator rules = home.findAll(attributeName, attributeValue, RuleGroupConditionView.class);
		return rules;
	}
	
	static public RuleGroupConditionView find(String id) {
		IHome home = new Home();
		return (RuleGroupConditionView)home.find(id, RuleGroupConditionView.class);
	}
	
	/**
	* @return 
	*/
	public Date getCompletionDate() {
		fetch();
		return completionDate;
	}
	/**
	* @param date
	*/
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		fetch();
		return createdDate;
	}
	/**
	 * @param createdDate The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return Returns the ruleTypeId.
	 */
	public String getRuleTypeId() {
		fetch();
		return ruleTypeId;
	}
	/**
	 * @param ruleTypeId The ruleTypeId to set.
	 */
	public void setRuleTypeId(String ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}
	public String getResolvedDesc() {
		fetch();
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
		fetch();
		return unformattedDesc;
	}
	/**
	 * @param unformattedDesc The unformattedDesc to set.
	 */
	public void setUnformattedDesc(String unformattedDesc) {
		this.unformattedDesc = unformattedDesc;
	}
}
