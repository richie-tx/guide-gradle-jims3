package pd.juvenilecase.casefile;

import java.util.Date;
import java.util.Iterator;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * 
 * @stereotype entity
 * @author dnikolis
 */
public class CasefileDocument extends mojo.km.persistence.PersistentObject
{
	private Date creationDate;
	private String casefileId;
	private String casefileNonComplianceNoticeId;
	private Object document;

	public Date getCreationDate()
	{
		fetch();
		return creationDate;
	}

	public void setCreationDate(Date creationDate)
	{
		if (this.creationDate == null || !this.creationDate.equals(creationDate))
		{
			markModified();
		}
		this.creationDate = creationDate;
	}

	public String getCasefileId()
	{
		fetch();
		return casefileId;
	}

	public void setCasefileId(String casefileId)
	{
		if (this.casefileId == null || !this.casefileId.equals(casefileId))
		{
			markModified();
		}
		this.casefileId = casefileId;
	}

	public String getCasefileNonComplianceNoticeId()
	{
		fetch();
		return casefileNonComplianceNoticeId;
	}

	public void setCasefileNonComplianceNoticeId(String casefileNonComplianceNoticeId)
	{
		if (this.casefileNonComplianceNoticeId == null
				|| !this.casefileNonComplianceNoticeId.equals(casefileNonComplianceNoticeId))
		{
			markModified();
		}
		this.casefileNonComplianceNoticeId = casefileNonComplianceNoticeId;
	}

	public Object getDocument()
	{
		fetch();
		return document;
	}

	public void setDocument(Object document)
	{
		if (this.document == null || !this.document.equals(document))
		{
			markModified();
		}
		this.document = document;
	}
	/**
	 *
	 */
	public static Iterator findAllForCasefile( String casefileId )
	{
		return new Home().findAll( "casefileId", casefileId, CasefileDocument.class );
	}
	
	static public CasefileDocument find( String casefileDocumentId )
	{
		IHome home = new Home();
		CasefileDocument casefileDocument = (CasefileDocument)home.find( casefileDocumentId, CasefileDocument.class );
		return casefileDocument;
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator notices = home.findAll(attrName, attrValue, CasefileDocument.class);
		return notices;
	}
}
