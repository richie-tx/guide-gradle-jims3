package pd.supervision.supervisionorder;

import java.util.Iterator;

import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.administercompliance.reply.LikeComplianceConditionResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import naming.SupervisionConstants;
/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
		
/**
 * @roseuid 43B2E727030D
 */
public class SupervisionOrderConditionView extends PersistentObject {
	
	private String defendantId;
    private String courtId;
    private boolean nonCompliant;
    private int ncCount;
    private String complianceReasonId;
	private String groupId;
	private String orderConditionName;
	private String orderConditionDescription;
	private String caseNumber;
	private int sprOrderId;
	private int conditionId;
	private int orderChainNum;
	private int sequenceNum;

	
	/**
	 * @return Returns the conditionId.
	 */
	public int getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * @roseuid 43B2E727030D
	 */
	public SupervisionOrderConditionView() {
	}

	/**
	 * Finds all SupervisionOrderCondition by an attribute value
	 * 
	 * @return
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue,SupervisionOrderConditionView.class);
	}
	
	/**
	 * Finds all SupervisionOrderCondition by an attribute value
	 * 
	 * @return
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(IEvent event) {
		return new Home().findAll(event,SupervisionOrderConditionView.class);
	}
	
	
	/**
	 * @return Returns the complianceReasonId.
	 */
	public String getComplianceReasonId() {
		return complianceReasonId;
	}
	/**
	 * @param complianceReasonId The complianceReasonId to set.
	 */
	public void setComplianceReasonId(String complianceReasonId) {
		this.complianceReasonId = complianceReasonId;
	}
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return Returns the isCompliant.
	 */
	public boolean isNonCompliant() {
		return nonCompliant;
	}
	/**
	 * @param isCompliant The isCompliant to set.
	 */
	public void setNonCompliant(boolean isNonCompliant) {
		this.nonCompliant = isNonCompliant;
	}

	/**
	 * @return Returns the ncCount.
	 */
	public int getNcCount() {
		return ncCount;
	}
	/**
	 * @param ncCount The ncCount to set.
	 */
	public void setNcCount(int ncCount) {
		this.ncCount = ncCount;
	}
	/**
	 * @return Returns the orderConditionName.
	 */
	public String getOrderConditionName() {
		return orderConditionName;
	}
	/**
	 * @param orderConditionName The orderConditionName to set.
	 */
	public void setOrderConditionName(String orderConditionName) {
		this.orderConditionName = orderConditionName;
	}
	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber.substring(0, 3) + " " + caseNumber.substring(3, 15);
	}
	
	public ComplianceConditionResponseEvent getResonseEvent(){
		ComplianceConditionResponseEvent resp = new ComplianceConditionResponseEvent();
		resp.setCaseNumber(this.getCaseNumber());
		resp.setCompliant(!this.isNonCompliant());
		resp.setCourtId(this.getCourtId());
		resp.setNcCount(this.getNcCount());
		resp.setOrderConditionName(this.getOrderConditionName());
		resp.setSprOrderConditionId(this.getOID());
		resp.setComplianceReasonId(this.getComplianceReasonId());
		resp.setDefendantId(this.getDefendantId());
		resp.setGroupId(this.getGroupId());	
		resp.setSprOrderId(new StringBuffer("").append(this.getSprOrderId()).toString());
		resp.setConditionId(new StringBuffer("").append(this.getConditionId()).toString());
		resp.setOffenseIndicator(this.getOffenseIndicator(this.courtId));
		resp.setOrderChainNumber(this.getOrderChainNum());
		return resp;
	}
	
	/**
	 * @param courtId
	 * @return
	 */
	private String getOffenseIndicator(String courtId) {
		String courtIdXXX = this.getCourtId().substring(0,2);
		String offenseIndicator = "";
		if(courtIdXXX.equalsIgnoreCase("CC")){
			offenseIndicator = SupervisionConstants.MISDEMEANOR_INDICATOR;
		}else if(courtIdXXX.equalsIgnoreCase("CR")){
			offenseIndicator = SupervisionConstants.FELONY_INDICATOR;
		}else if(courtIdXXX.equalsIgnoreCase("JP")){
			offenseIndicator = SupervisionConstants.MISDEMEANOR_INDICATOR;
		}else if(courtIdXXX.equalsIgnoreCase("OC")){
			offenseIndicator = this.courtId.substring(this.courtId.length() -1, this.courtId.length());
		}
		return offenseIndicator;
	}
	/**
	 * @return Returns the orderConditionDescription.
	 */
	public String getOrderConditionDescription() {
		return orderConditionDescription;
	}
	/**
	 * @param orderConditionDescription The orderConditionDescription to set.
	 */
	public void setOrderConditionDescription(String orderConditionDescription) {
		this.orderConditionDescription = orderConditionDescription;
	}
	/**
	 * @return Returns the sprOrderId.
	 */
	public int getSprOrderId() {
		return sprOrderId;
	}
	/**
	 * @param sprOrderId The sprOrderId to set.
	 */
	public void setSprOrderId(int sprOrderId) {
		this.sprOrderId = sprOrderId;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public int getOrderChainNum()
	{
		fetch();
		return orderChainNum;
	}
	
	/**
	 * @param aOrderChainNum
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderChainNum(int aOrderChainNum)
	{
		
		orderChainNum = aOrderChainNum;
	}
	/**
	 * @return the sequenceNum
	 */
	public int getSequenceNum() {
		return sequenceNum;
	}
	/**
	 * @param sequenceNum the sequenceNum to set
	 */
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	/**
	 * @return
	 */
	public LikeComplianceConditionResponseEvent getLikeConditionsResponseEvent() {
		LikeComplianceConditionResponseEvent resp = new LikeComplianceConditionResponseEvent();
		resp.setCaseNumber(this.getCaseNumber());
		resp.setCompliant(!this.isNonCompliant());
		resp.setCourtId(this.getCourtId());
		resp.setNcCount(this.getNcCount());
		resp.setOrderConditionName(this.getOrderConditionName());
		resp.setSprOrderConditionId(this.getOID());
		resp.setComplianceReasonId(this.getComplianceReasonId());
		resp.setDefendantId(this.getDefendantId());
		resp.setGroupId(this.getGroupId());	
		resp.setSprOrderId(new StringBuffer("").append(this.getSprOrderId()).toString());
		resp.setConditionId(new StringBuffer("").append(this.getConditionId()).toString());
		resp.setOffenseIndicator(this.getOffenseIndicator(this.courtId));
		resp.setOrderChainNumber(this.getOrderChainNum());
		return resp;
	}
}
