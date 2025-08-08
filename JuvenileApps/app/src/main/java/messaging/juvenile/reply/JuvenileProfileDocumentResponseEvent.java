/*
 * Created on Jun 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author cShimek
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileProfileDocumentResponseEvent extends ResponseEvent implements Comparable
{
	private String documentTypeDesc;
	private String juvenileNum;
	private String documentTypeId;
	private String documentId;
	private Date entryDate;
	private Object document;

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	public long getEntryDateAsLong()
		{
			if(entryDate!=null)
				return entryDate.getTime();
			else
				return 0;
		}

	/**
	 * @return
	 */
	public String getEntryDateAsString()
	{
		String dateStr = "";
		if(this.entryDate != null){
			dateStr = DateUtil.dateToString(entryDate, "MM/dd/yyyy HH:mm");
		}
		return dateStr;
	}
	
	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}
	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}
	
	/**
	 * @return the documentTypeDesc
	 */
	public String getDocumentTypeDesc() {
		return documentTypeDesc;
	}

	/**
	 * @param documentTypeDesc the documentTypeDesc to set
	 */
	public void setDocumentTypeDesc(String documentTypeDesc) {
		this.documentTypeDesc = documentTypeDesc;
	}

	/**
	 * @return the documentTypeId
	 */
	public String getDocumentTypeId() {
		return documentTypeId;
	}

	/**
	 * @param documentTypeId the documentTypeId to set
	 */
	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	/**
	 * @return the document
	 */
	public Object getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Object document) {
		this.document = document;
	}

	public int compareTo(Object obj) throws ClassCastException
	{
		if(obj==null)
			return -1;
		if(this.entryDate==null)
			return 1;
		JuvenileProfileDocumentResponseEvent evt = (JuvenileProfileDocumentResponseEvent) obj;
		if(evt.getEntryDate() == null)
			return -1;
		return evt.getEntryDate().compareTo(entryDate);
	}	

}