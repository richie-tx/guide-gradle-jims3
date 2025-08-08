//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\suggestedorder\\UpdateSuggestedOrderEvent.java

package messaging.suggestedorder;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateSuggestedOrderEvent extends CompositeRequest 
{
   private String agencyId;
   private String description;
   private String includedConditionsInd;
   private String suggestedOrderId;
   private String suggestedOrderName;
   
   /**
    * @roseuid 433AF2DD0131
    */
   public UpdateSuggestedOrderEvent() 
   {
    
   }
/**
 * @return Returns the agencyId.
 */
    public String getAgencyId() {
    return agencyId;
}
   
   /**
    * Access method for the description property.
    * 
    * @return   the current value of the description property
    */
   public String getDescription() 
   {
      return description;
   }
   
   /**
    * Access method for the includedConditionsInd property.
    * 
    * @return   the current value of the includedConditionsInd property
    */
   public String getIncludedConditionsInd()
   {
      return includedConditionsInd;
   }
   
   /**
    * Access method for the suggestedOrderId property.
    * 
    * @return   the current value of the suggestedOrderId property
    */
   public String getSuggestedOrderId()
   {
      return suggestedOrderId;
   }
   
   /**
    * Access method for the suggestedOrderName property.
    * 
    * @return   the current value of the suggestedOrderName property
    */
   public String getSuggestedOrderName()
   {
      return suggestedOrderName;
   }
/**
 * @param agencyId The agencyId to set.
 */
public void setAgencyId(String agencyId) {
    this.agencyId = agencyId;
}
   
   /**
    * Sets the value of the description property.
    * 
    * @param aDescription the new value of the description property
    */
   public void setDescription(String aDescription) 
   {
      description = aDescription;
   }
   
   /**
    * Sets the value of the includedConditionsInd property.
    * 
    * @param aIncludedConditionsInd the new value of the includedConditionsInd property
    */
   public void setIncludedConditionsInd(String aIncludedConditionsInd)
   {
      includedConditionsInd = aIncludedConditionsInd;
   }
   
   /**
    * @param aSuggestedOrderId
    * @roseuid 433AF04F0382
    */
   public void setSuggestedOrderId(String aSuggestedOrderId) 
   {
		suggestedOrderId = aSuggestedOrderId;    
   }
   
   /**
    * @param aSuggestedOrderName
    * @roseuid 433AF04F03A4
    */
   public void setSuggestedOrderName(String aSuggestedOrderName) 
   {
 		suggestedOrderName = aSuggestedOrderName;   
   }
}
