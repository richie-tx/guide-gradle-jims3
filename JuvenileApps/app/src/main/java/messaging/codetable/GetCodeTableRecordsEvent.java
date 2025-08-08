//Source file: C:\\views\\dev\\app\\src\\messaging\\codetable\\GetCodeTableRecordsEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetCodeTableRecordsEvent extends RequestEvent 
{
   public String codeTableName;
   
   /**
    * @roseuid 4164282900A6
    */
   public GetCodeTableRecordsEvent() 
   {
    
   }
   
   /**
    * @param codeTableName
    * @roseuid 416427920146
    */
   public void setCodeTableName(String aCodeTableName) 
   {
    this.codeTableName = aCodeTableName;
   }
   
   /**
    * @return String
    * @roseuid 416427920153
    */
   public String getCodeTableName() 
   {
    return this.codeTableName;
   }
}
