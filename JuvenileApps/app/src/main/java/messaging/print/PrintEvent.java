//Source file: C:\\views\\dev\\app\\src\\messaging\\print\\PrintEvent.java

package messaging.print;

import mojo.km.messaging.RequestEvent;

public class PrintEvent extends RequestEvent 
{
   public String data;
   public String templateName;
   
   /**
    * @roseuid 416C39CE0100
    */
   public PrintEvent() 
   {
    
   }
   
   /**
    * Access method for the data property.
    * 
    * @return   the current value of the data property
    */
   public String getData()
   {
      return data;
   }
   
   /**
    * Sets the value of the data property.
    * 
    * @param aData the new value of the data property
    */
   public void setData(String aData)
   {
      data = aData;
   }
   
   /**
    * Access method for the templateName property.
    * 
    * @return   the current value of the templateName property
    */
   public String getTemplateName()
   {
      return templateName;
   }
   
   /**
    * Sets the value of the templateName property.
    * 
    * @param aTemplateName the new value of the templateName property
    */
   public void setTemplateName(String aTemplateName)
   {
      templateName = aTemplateName;
   }
   
}
