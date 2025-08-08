//Source file: C:\\views\\dev\\app\\src\\messaging\\codetable\\GetCodeEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetCodeEvent extends RequestEvent 
{
   public String codeTableName;
   public String code;
   
   /**
    * @roseuid 41642A51022D
    */
   public GetCodeEvent() 
   {
    
   }
   
   /**
    * @param codeTableName
    * @roseuid 41642A200105
    */
   public void setCodeTableName(String aCodeTableName) 
   {
    this.codeTableName = aCodeTableName;
   }
   
   /**
    * @return String
    * @roseuid 41642A200107
    */
   public String getCodeTableName() 
   {
    return this.codeTableName;
   }
   
   /**
    * @param code
    * @roseuid 41642A200114
    */
   public void setCode(String aCode) 
   {
    this.code = aCode;
   }
   
   /**
    * @return String
    * @roseuid 41642A200116
    */
   public String getCode() 
   {
    return this.code;
   }
}
/**
 * void GetCodeEvent.setCodeTableId(java.lang.String){
 *     
 *    }
 * void GetCodeEvent.getCodeTableId(){
 *     return null;
 *    }
 */
