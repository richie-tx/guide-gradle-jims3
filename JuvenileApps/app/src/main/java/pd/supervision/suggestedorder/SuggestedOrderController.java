//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\suggestedorder\\SuggestedOrderController.java

package pd.supervision.suggestedorder;

/**
 * @stereotype control
 */
public class SuggestedOrderController
{

	/**
	 * @roseuid 433AF1370027
	 */
	public SuggestedOrderController()
	{

	}

	/**
	 * @stereotype design
	 * @param suggestedOrderId
	 * @roseuid 433AF05200F5
	 */
	public void deleteSuggestedOrder(String suggestedOrderId)
	{

	}
	/**
		* @stereotype design
		* @param courts
		* @param conditionName
		* @param conditionLiteral
		* @param conditionType
		* @param conditionSubType
		* @param conditionSubTypeDetail
		* @param jurisdiction
		* @roseuid 433AF0510384
		*/
	public void getConditionsForSuggestedOrder(
		java.util.Collection courts,
		String conditionName,
		String conditionLiteral,
		String code,
		int conditionType,
		String conditionSubType,
		int conditionSubTypeDetail,
		String jurisdiction)
	{

	}

	/**
	 * @stereotype design
	 * @param suggestedOrderId
	 * @roseuid 433AF05001D3
	 */
	public void getSuggestedOrder(String suggestedOrderId)
	{

	}
	/**
		* @stereotype design
		* @param suggestedOrderName
		* @roseuid 433AF0510384
		*/
	public void getSuggestedOrderByName(
		String suggestedOrderName)
	{

	}

	/**
	 * @stereotype design
	 * @param suggestedOrderId
	 * @roseuid 433AF051000B
	 */
	public void getSuggestedOrderConditions(String suggestedOrderId)
	{

	}

	/**
	 * @stereotype design
	 * @param suggestedOrderId
	 * @roseuid 433AF0500344
	 */
	public void getSuggestedOrderCourts(String suggestedOrderId)
	{

	}

	/**
	 * @stereotype design
	 * @param suggestedOrderId
	 * @roseuid 433AF0520049
	 */
	public void getSuggestedOrderData(String suggestedOrderId)
	{

	}

	/**
	 * @stereotype design
	 * @param suggestedOrderId
	 * @roseuid 433AF050023F
	 */
	public void getSuggestedOrderOffenses(String suggestedOrderId)
	{

	}

	/**
	 * @stereotype design
	 * @param courts
	 * @param penalCode
	 * @param literal
	 * @param code
	 * @param conditionName
	 * @param courtDivision
	 * @param description
	 * @param suggestedOrderName
	 * @roseuid 433AF0510384
	 */
	public void getSuggestedOrders(
		java.util.Collection courts,
		String penalCode,
		String literal,
		String code,
		int conditionName,
		String courtDivision,
		int description,
		String suggestedOrderName)
	{

	}

	/**
	 * @stereotype design
	 * @param suggestedOrderName
	 * @param includedConditionsInd
	 * @param suggestedOrderId
	 * @param description
	 * @roseuid 433AF04F03C2
	 */
	public void updateSuggestedOrder(
		String suggestedOrderName,
		String includedConditionsInd,
		String suggestedOrderId,
		int description)
	{

	}
	/**
	 * @stereotype design
	 * @roseuid 433AF04F03C2
	 */
	
	public void getSuggestedOrdersForCourt(){
	}
	/**
	 * @stereotype design
	 * @roseuid 433AF04F03C2
	 */
	
	public void validateSuggestedOrderDelete(){
	}
}
