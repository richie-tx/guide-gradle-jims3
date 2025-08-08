//Source file: C:\\views\\dev\\app\\src\\messaging\\codetable\\GetCodeHistoryEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetCodeHistoryEvent extends RequestEvent 
{
   public String codeId;
   public String code;
   
   /**
    * @roseuid 4164282803C3
    */
   public GetCodeHistoryEvent() 
   {
    
   }
   
   /**
    * @param codeId
    * @roseuid 416427E1033A
    */
   public void setCodeId(String aCodeId) 
   {
   this.codeId = aCodeId;
   }
   
   /**
    * @return String
    * @roseuid 416427E1033C
    */
   public String getCodeId() 
   {
    return this.codeId;
   }
   
   /**
    * @param code
    * @roseuid 416427E10347
    */
   public void setCode(String aCode) 
   {
    this.code = aCode;
   }
   
   /**
    * @return String
    * @roseuid 416427E10349
    */
   public String getCode() 
   {
    return this.code;
   }
}
