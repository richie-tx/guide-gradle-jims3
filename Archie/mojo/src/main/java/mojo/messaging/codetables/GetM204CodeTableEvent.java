//Source file: C:\\views\\dev\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\messaging\\codetables\\M204CodeTableQueryEvent.java

package mojo.messaging.codetables;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;


public class GetM204CodeTableEvent extends RequestEvent 
{
   private String fileName;
   private String recordType;
   private String code;
   
   /**
   @roseuid 4107E23A0369
    */
   public GetM204CodeTableEvent() 
   {
    
   }
/**
 * @return
 */
public String getCode()
{
	return code;
}

/**
 * @return
 */
public String getFileName()
{
	return fileName;
}

/**
 * @return
 */
public String getRecordType()
{
	return recordType;
}

/**
 * @param string
 */
public void setCode(String string)
{
	code = string;
}

/**
 * @param string
 */
public void setFileName(String string)
{
	fileName = string;
}

/**
 * @param string
 */
public void setRecordType(String string)
{
	recordType = string;
}

}
