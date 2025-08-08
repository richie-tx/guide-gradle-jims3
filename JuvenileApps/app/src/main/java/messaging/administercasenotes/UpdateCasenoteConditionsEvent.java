//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\UpdateCasenoteEvent.java

package messaging.administercasenotes;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateCasenoteConditionsEvent extends CompositeRequest
{
   	private int[] sprOrderConditionIds;
	/**
	 * @return Returns the caseNumbers.
	 */
	public String[] getCaseNumbers() {
		return caseNumbers;
	}
	/**
	 * @param caseNumbers The caseNumbers to set.
	 */
	public void setCaseNumbers(String[] caseNumbers) {
		this.caseNumbers = caseNumbers;
	}
	/**
	 * @return Returns the conditionIds.
	 */
	public int[] getConditionIds() {
		return conditionIds;
	}
	/**
	 * @param conditionIds The conditionIds to set.
	 */
	public void setConditionIds(int[] conditionIds) {
		this.conditionIds = conditionIds;
	}
	/**
	 * @return Returns the orderChainNumbers.
	 */
	public int[] getOrderChainNumbers() {
		return orderChainNumbers;
	}
	/**
	 * @param orderChainNumbers The orderChainNumbers to set.
	 */
	public void setOrderChainNumbers(int[] orderChainNumbers) {
		this.orderChainNumbers = orderChainNumbers;
	}
   	private String[] caseNumbers;
   	private int[] conditionIds;
   	private int [] orderChainNumbers;
   	
	/**
	 * @return Returns the sprOrderConditionIds.
	 */
	public int[] getSprOrderConditionIds() {
		return sprOrderConditionIds;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionIds to set.
	 */
	public void setSprOrderConditionIds(int[] sprOrderConditionIds) {
		this.sprOrderConditionIds = sprOrderConditionIds;
	}
}
