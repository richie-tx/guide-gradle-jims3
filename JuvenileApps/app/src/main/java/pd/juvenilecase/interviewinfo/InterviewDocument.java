package pd.juvenilecase.interviewinfo;

import messaging.interviewinfo.GetInterviewReportsByJuvenileEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

import pd.codetable.Code;

public class InterviewDocument extends PersistentObject 
{
   private Date creationDate;
   private Object document;
   private String casefileId;
   private String documentTypeCodeId;
   private Code documentTypeCode;
   
   /**
    * @roseuid 4522C3AC01DC
    */
   public InterviewDocument() 
   {
    
   }
   
   /**
    * @roseuid 4522BCA601F1
    */
	public static InterviewDocument find( String documentId )
	{
		return (InterviewDocument)new Home().find( documentId, InterviewDocument.class );
	}
	
	/**
	 *
	 */
	public static Iterator findAllForCasefile( String casefileId )
	{
		return new Home().findAll( "casefileId", casefileId, InterviewDocument.class );
	}

	/**
	 *
	 */
	public static Iterator findAllForJuvenile( String juvenileId )
	{
		GetInterviewReportsByJuvenileEvent evt = new GetInterviewReportsByJuvenileEvent();
		evt.setJuvenileId(juvenileId);
		return new Home().findAll( evt, InterviewDocument.class );
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
		if ( casefileId == null || ! casefileId.equals(aCasefileId) ) 
		{
			casefileId = aCasefileId;
			markModified();
		}
	}

	/**
	* Set the reference value to class :: pd.juvenile.Juvenile
	*/
	public void setDocumentTypeCodeId( String aDocTypeCodeId ) 
	{
		if (documentTypeCodeId == null || ! documentTypeCodeId.equals(aDocTypeCodeId)) {
			documentTypeCodeId = aDocTypeCodeId;
			documentTypeCode = null;
			markModified();
		}
	}
	
	/**
	* Get the reference value to class :: pd.juvenile.Juvenile
	*/
	public String getDocumentTypeCodeId() 
	{
		fetch();
		return documentTypeCodeId;
	}
	
	/**
	* CODE_TABLE_NAME = INTERVIEW_DOCTYPE
	*/
	private void initDocumentTypeCode() 
	{
		if (documentTypeCode == null) {
			try {
				documentTypeCode = (Code) new mojo.km.persistence.Reference(documentTypeCodeId, Code.class, "INTERVIEW_DOCTYPE").getObject();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
	
	/**
	* 
	*/
	public Code getDocumentTypeCode() 
	{
		initDocumentTypeCode();
		return documentTypeCode;
	}
	
	/**
	* 
	*/
	public void setDocumentTypeCode( Code aDocTypeCode ) 
	{
		if (documentTypeCode == null || ! documentTypeCode.equals(aDocTypeCode)) {
			markModified();
		}
		if ( aDocTypeCode.getOID() == null ) 
		{
			new mojo.km.persistence.Home().bind(aDocTypeCode);
		}
		setDocumentTypeCodeId( "" + aDocTypeCode.getOID() );
		documentTypeCode = aDocTypeCode;
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
			document = aDocument;
			markModified();
		}
	}

   
   
}
