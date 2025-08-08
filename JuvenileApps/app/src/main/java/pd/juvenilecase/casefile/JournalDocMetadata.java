/*
 * Created on Oct 15, 2007
 *
 * 
 */
package pd.juvenilecase.casefile;

import java.util.Date;
import java.util.Iterator;

import messaging.casefile.reply.JournalDocResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author rcarter
 *
*/

public class JournalDocMetadata extends PersistentObject 
{
   private Date creationDate;
   private Object document;
   private String casefileId;
   private String docTypeCd;
   
   
   /**
    * @roseuid 4522C3AC01DC
    */
   public JournalDocMetadata() 
   {
    
   }
   
   /**
    * @roseuid 4522BCA601F1
    */
	public static JournalDocMetadata find( String journalAppDocId )
	{
		return (JournalDocMetadata)new Home().find( journalAppDocId, JournalDocMetadata.class );
	}
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JournalDocMetadata.class);
	}
	
	/**
	 * 
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator notices = home.findAll(attrName, attrValue, JournalDocMetadata.class);
		return notices;
	}
	
	/**
	* 
	*/
	public Date getCreationDate() 
	{
		fetch();
		return creationDate;
	}
	
	/**
	* Not for client use. This exists for persistence only. 
	*/
	public void setCreationDate( Date aDate ) 
	{
		creationDate = aDate;
		// no markModified
	}
	
	/**
	* 
	*/
	public String getCasefileId() 
	{
		fetch();
		return casefileId;
	}
	
	
	/**
	* 
	*/
	public void setCasefileId( String aCasefileId ) 
	{
		if ( casefileId == null ) 
		{
			markModified();
		}
		casefileId = aCasefileId;
	}
	
	public String getDocTypeCd() 
	{
		fetch();
		return docTypeCd;
	}
	
	
	/**
	* 
	*/
	public void setDocTypeCd( String aDocTypeCd ) 
	{
		if ( docTypeCd == null || ! docTypeCd.equals(aDocTypeCd) ) 
		{
			markModified();
		}
		docTypeCd = aDocTypeCd;
	}

	/**
	* 
	*/
	public Object getDocument() 
	{
		fetch();
		return document;
	}
	
	/**
	* 
	*/
	public void setDocument( Object aDocument ) 
	{
		if ( document == null || ! document.equals(aDocument) ) 
		{
			markModified();
		}
		document = aDocument;
	}
	
	public JournalDocResponseEvent getRespEvt() {
		JournalDocResponseEvent resp = new JournalDocResponseEvent();
		resp.setJournalDocId(this.getOID());
		resp.setCasefileId("" + this.getCasefileId());
		resp.setDocument(this.getDocument());
		resp.setCreateDate(this.getCreationDate());
		resp.setDocTypeCd(this.getDocTypeCd());
		return resp;
	}
	
}