//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\GetTraitChildByCategoryEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetTraitChildByCategoryEvent extends RequestEvent 
{
   public String traitCategoryName;
   
   /**
    * @roseuid 442AE8AD0109
    */
   public GetTraitChildByCategoryEvent() 
   {
    
   }
   
   /**
    * Access method for the traitCategoryName property.
    * 
    * @return   the current value of the traitCategoryName property
    */
   public String getTraitCategoryName()
   {
      return traitCategoryName;
   }
   
   /**
    * Sets the value of the traitCategoryName property.
    * 
    * @param aTraitCategoryName the new value of the traitCategoryName property
    */
   public void setTraitCategoryName(String aTraitCategoryName)
   {
      traitCategoryName = aTraitCategoryName;
   }
 
}
