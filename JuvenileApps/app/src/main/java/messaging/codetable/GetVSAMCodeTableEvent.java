//Source file: C:\\views\\dev\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\messaging\\codetables\\VSAMCodeTableQueryEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;


public class GetVSAMCodeTableEvent extends RequestEvent 
{
   private String fileName;
   private String recordType;
   private String code;
   private String OID;
   
   /**
   @roseuid 4107E23A03A5
    */
   public GetVSAMCodeTableEvent() 
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

/**
 * @return
 */
public String getOID()
{
	return OID;
}

/**
 * @param string
 */
public void setOID(String string)
{
	OID = string;
}

}
