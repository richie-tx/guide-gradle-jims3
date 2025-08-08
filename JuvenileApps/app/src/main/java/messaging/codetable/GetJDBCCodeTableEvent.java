//Source file: C:\\views\\dev\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\messaging\\codetables\\JDBCCodeTableQueryEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;


public class GetJDBCCodeTableEvent extends RequestEvent
{
   private String fileName;
   private String code;
   private String recordType;
   private String OID;
   
   /**
   @roseuid 4107E23A0323
    */
   public GetJDBCCodeTableEvent() 
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
public String getOID()
{
	return OID;
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
public void setOID(String string)
{
	OID = string;
}

/**
 * @param string
 */
public void setRecordType(String string)
{
	recordType = string;
}

}
