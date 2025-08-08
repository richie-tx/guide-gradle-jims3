package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenile.reply.JuvenileProfileDocumentResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @roseuid 42B19F160000
 */
public class JuvenileDocuments extends PersistentObject
{
    private String juvenileId;
    private String documentId;
    private String documentTypeCodeId;
    private Date entryDate;
    private Object document;

    /**
     * @roseuid 42B19F160000
     */
    public JuvenileDocuments()
    {
    }

    /**
     * @return JuvenileDocuments
     * @param documentId
     */
    static public JuvenileDocuments find(String documentId)
    {
        IHome home = new Home();
        JuvenileDocuments juvenileDocumnet = 
        	(JuvenileDocuments) home.find(documentId, JuvenileDocuments.class);
        return juvenileDocumnet;
    }

    /**
     * Finds juvenile documents list by an event
     * 
     * @return Iterator of juvenileDocuments list
     * @param event
     */
    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        Iterator juvenileDocuments = home.findAll(event, JuvenileDocuments.class);
        return juvenileDocuments;
    }

    /**
     * @return Iterator juvenile documents
     * @param attrName name of the attribute for where clause
     * @param attrValue value to be checked in the where clause
     */
    static public Iterator findAll(String attrName, String attrValue)
    {
        IHome home = new Home();
        Iterator juvenileDocuments = home.findAll(attrName, attrValue, JuvenileDocuments.class);
        return juvenileDocuments;
    }

    /**
     * @roseuid 42B183070090
     */
    public void bind()
    {
        markModified();
    }

    /**
     * Access method for the entryDate property.
     * 
     * @return the current value of the entryDate property
     */
    public Date getEntryDate()
    {
        fetch();
        return entryDate;
    }

    /**
     * Sets the value of the entryDate property.
     * 
     * @param aEntryDate - the new value of the entryDate property
     */
    public void setEntryDate(Date aEntryDate)
    {
        if (this.entryDate == null || !this.entryDate.equals(aEntryDate))
        {
            markModified();
        }
        entryDate = aEntryDate;
    }

  
    /**
     * Sets the value of the documentTypeCodeId property.
     * 
     * @param aDocumentTypeCodeId - the new value of the documentTypeCodeId property
     */
    public void setDocumentTypeCodeId(String aDocumentTypeCodeId)
    {
        if (this.documentTypeCodeId == null || !this.documentTypeCodeId.equals(aDocumentTypeCodeId))
        {
            markModified();
        }
        documentTypeCodeId = aDocumentTypeCodeId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getDocumentTypeCodeId()
    {
        fetch();
        return documentTypeCodeId;
    }

    /**
     * Set the reference value to class :: pd.juvenile.Juvenile
     */
    public void setJuvenileId(String juvenileId)
    {
        if (this.juvenileId == null || !this.juvenileId.equals(juvenileId))
        {
            markModified();
        }
        this.juvenileId = juvenileId;
    }

    /**
     * Get the reference value to class :: pd.juvenile.Juvenile
     */
    public String getJuvenileId()
    {
        fetch();
        return juvenileId;
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
		fetch();
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Object document) {
		this.document = document;
	}

	public JuvenileProfileDocumentResponseEvent getJuvenileDocumentsResponse()
    {
    	JuvenileProfileDocumentResponseEvent respEvent = new JuvenileProfileDocumentResponseEvent();

        respEvent.setJuvenileNum(this.getJuvenileId());
        respEvent.setEntryDate(this.getEntryDate());
        respEvent.setDocumentTypeId(this.getDocumentTypeCodeId());
        respEvent.setDocumentId(this.getDocumentId());
        return respEvent;
    }

}