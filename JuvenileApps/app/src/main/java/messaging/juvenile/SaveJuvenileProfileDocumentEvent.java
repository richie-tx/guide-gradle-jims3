//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\SaveJuvenilePhysicalAttributesEvent.java

package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileProfileDocumentEvent extends RequestEvent  
{
	private String juvenileNum;
	private String documentTypeCodeId;
	private Date entryDate;
	private Object document;
   
   /**
    * @roseuid 42B1968D001F
    */
   public  SaveJuvenileProfileDocumentEvent() 
   {
    
   }
	public Object getDocument() {
		return document;
	}
	public void setDocument(Object document) {
		this.document = document;
	}  

	public String getDocumentTypeCodeId() {
		return documentTypeCodeId;
	}
	public void setDocumentTypeCodeId(String documentTypeCodeId) {
		this.documentTypeCodeId = documentTypeCodeId;
	}

	public Date getEntryDate()
	{
		return entryDate;
	}
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}
	
	public String getJuvenileNum()
	{
		return juvenileNum;
	}
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

}