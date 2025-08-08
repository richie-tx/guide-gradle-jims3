//Source file: C:\\views\\dev\\app\\src\\messaging\\codetable\\DeleteCodeEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class DeleteCodeEvent extends RequestEvent 
{
   public String codeTableName;
   public String dataSource;
   public String code;
   public String codeId;
   
   /**
    * @roseuid 416446B90172
    */
   public DeleteCodeEvent() 
   {
    
   }
   
   /**
    * @param codeTableName
    * @roseuid 416446900317
    */
   public void setCodeTableName(String codeTableName) 
   {
    this.codeTableName = codeTableName;
   }
   
   /**
    * @return String
    * @roseuid 416446900319
    */
   public String getCodeTableName() 
   {
    return this.codeTableName;
   }
   
   /**
    * @param code
    * @roseuid 416446900328
    */
   public void setCode(String code) 
   {
    this.code = code;
   }
   
   /**
    * @return String
    * @roseuid 41644690032A
    */
   public String getCode() 
   {
    return this.code;
   }

   /**
	* @param codeId
	*/
   public void setCodeId(String codeId) 
   {
	this.codeId = codeId;
   }
   
   /**
	* @return String
	*/
   public String getCodeId() 
   {
	return codeId;
   }
   
}
