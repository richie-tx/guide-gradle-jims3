//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\supervisionorder\\SupervisionOrderController.java

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\SupervisionOrderController.java

package pd.supervision.supervisionorder;

import java.util.Date;

/**
 * @stereotype control
 */
public class SupervisionOrderController
{

	/**
	 * @roseuid 43B2E73C0128
	 */
	public SupervisionOrderController()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 438F22CB024C
	 */
	public void updateSupervisionOrder()
	{

	}

	/**
	 * @stereotype design
	 * @param spn
	 * @param caseNum
	 * @param courtDivision
	 * @roseuid 438F22CC00C2
	 */
	public void getSupervisionOrders(String spn, String caseNum, String courtDivision)
	{

	}

	/**
	 * @stereotype design
	 * @param spn
	 * @param caseNum
	 * @param courtDivision
	 * @roseuid 438F22CC00C2
	 */
	public void getDefendantSupervisionOrders(String spn)
	{

	}
	
	/**
	 * @stereotype design
	 * @param notes
	 * @param group1
	 * @param effectiveDate
	 * @param description
	 * @roseuid 43B2B6EC0302
	 */
	public void createSpecialCondition(String notes, String group1, Date effectiveDate, String description)
	{

	}
	/**
	 * @stereotype design
	 */
	public void createSpecialConditions()
	{

	}
	/**
	 * @stereotype design
	 * @param group3
	 * @param group2
	 * @param group1
	 * @param status
	 * @param description
	 * @param conditionName
	 * @param isStandard
	 * @param effectiveDate
	 * @param courtId
	 * @roseuid 43B2B6ED0119
	 */
	public void getConditionsForSupervisionOrder(
		String group3,
		String group2,
		String group1,
		String status,
		String description,
		String conditionName,
		boolean isStandard,
		Date effectiveDate,
		String courtId)
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 43B2B6ED0267
	 */
	public void getImpactedOrders()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 43B2B6ED0267
	 */
	public void getSupervisionOrderVersions()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 43B2B6F100AB
	 */
	public void updateSupervisionOrderStatus()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 43BECD350102
	 */
	public void createSupervisionOrder()
	{

	}
	/**
	* @stereotype design
	*/
	public void getConditionDetailsForSupervisionOrder()
	{
	}
	/**
	* @stereotype design
	*/
	public void prepareToFileSupervisionOrder()
	{
	}
	/**
	* @stereotype design
	*/
	public void getSupervisionOrderDetails()
	{
	}
	/**
	* @stereotype design
	*/

	public void deleteSupervisionOrder(String anOid)
	{

	}
	/**
	* @stereotype design
	*/
	public void reinstateSupervisionOrder()
	{
	}
	/**
	* @stereotype design
	*/
	public void withdrawSupervisionOrder()
	{
	}
	/**
	* @stereotype design
	*/
	public void updateSuperviseeSignature()
	{
	}
	/**
	* @stereotype design
	*/
	public void getSupervisionOrderVariableElementReferences()
	{
	}
	
	/**
	* @stereotype design
	*/
	public void getPrintTemplates()
	{
	}
	/**
	* @stereotype design
	*/
	public void getPriorSupervisionPeriods()
	{
	}	
	
	/**
	* @stereotype design
	*/
	public void getSupervisionPeriodForCompliance()
	{
	}
	
	/**
	* @stereotype design
	*/
	public void getSprOrderConditionLiteral()
	{
	}
	
	/**
	  * @stereotype design
	  * 
    */
    public void getSuperviseeCaseOrders() 
    {
    
    }
   
   /**
    * @stereotype design
    * @roseuid 47DEA68A030D
    */
   public void getSuperviseeComplianceIndicator() 
   {
    
   }
   /**
    * @stereotype design
    */
   public void prepareSignaturePage() 
   {
    
   }
   /**
    * @stereotype design
    */
   public void updateSupervisionOrderWithSuggestedOrderConditions() 
   {
    
   }
   /**
    * @stereotype design
    */
   public void GetCurrentSupervisionOrderStartEndDates() 
   {
    
   }
   /**
    * @stereotype design
    */
   public void GetUnfinishedOrders() 
   {
    
   }
   /**
   * @stereotype design
   * @roseuid 43821DD30188
   */
	public void validateConditionInUse( String conditionId )
	{
		
	}
	
 /**
   * @stereotype design
   * @roseuid 43821DD30188
   */
    public void updateOrderStatusToPending( String anOid )
	{}
    
    /**
	* @stereotype design
	*/
	public void getLightSupervisionOrders(String defendantId, String agencyId)
	{
	}
}
