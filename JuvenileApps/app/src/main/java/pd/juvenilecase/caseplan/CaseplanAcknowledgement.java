package pd.juvenilecase.caseplan;

import java.util.Date;
import java.util.Iterator;

import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.GetCaseplansByJuvenileNumberEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * @roseuid 4533C96C00AB
 */
public class CaseplanAcknowledgement extends PersistentObject
{
	private Date entryDate;	
	private String caseplanID;
	private String acknowledgementId;	
	private String signatureStatus;
	private String explanation;

	/**
	 * @roseuid 4533C96C00AB
	 */
	public CaseplanAcknowledgement()
	{
	}	
	/**
	 * @roseuid 4534FE3F0024
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public void find()
	{
		fetch();
	}

	/**
	 * @roseuid 4534FE3F0025
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * @roseuid 4534FE3F0038
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void None()
	{
		markModified();
	}

	static public CaseplanAcknowledgement find(String caseplanID)
	{
		CaseplanAcknowledgement cp = null;
		IHome home = new Home();
		cp = (CaseplanAcknowledgement) home.find(caseplanID, CaseplanAcknowledgement.class);
		return cp;
	}
	
	/**
	 * @roseuid 433C3D3E00AA
	 * @methodInvocation findAll
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator caseplanAcks = home.findAll(attributeName, attributeValue, CaseplanAcknowledgement.class);		
		return caseplanAcks;
	}

	/**
	 * Finds Caseplans by a certain event
	 * @return Iterator of Caseplans
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CaseplanAcknowledgement.class);
	}

	/**
	 * @roseuid 433C3D3E00AA
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 */
	static public CaseplanAcknowledgement findByCaseplanID(GetCaseplanDetailsEvent event)
	{
		IHome home = new Home();
		CaseplanAcknowledgement ack = null;
		Iterator caseplans = home.findAll(event, CaseplanAcknowledgement.class);
		if (caseplans.hasNext())
		{
			ack = (CaseplanAcknowledgement) caseplans.next();
		}
		return ack;
	}

	/**
	 * @return Returns the acknowledgementId.
	 */
	public String getAcknowledgementId()
	{
		return getOID().toString();
	}

	/**
	 * @param acknowledgementId The acknowledgementId to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setAcknowledgementId(String acknowledgementId)
	{
		if (this.acknowledgementId == null || !this.acknowledgementId.equals(acknowledgementId))
		{
			markModified();
		}
		this.setOID(acknowledgementId);
		this.acknowledgementId = acknowledgementId;
	}
	

	
	
	/**
	 * @return Returns the caseplanID.
	 * @methodInvocation fetch
	 */
	public String getCaseplanID()
	{
		fetch();
		return caseplanID;
	}

	/**
	 * @param caseplanID The caseplanID to set.
	 * @methodInvocation setOID
	 * @methodInvocation setOID
	 * @methodInvocation setOID
	 * @methodInvocation markModified
	 */
	public void setCaseplanID(String caseplanID)
	{
		if (this.caseplanID == null || !this.caseplanID.equals(caseplanID))
		{
			markModified();
		}
		this.caseplanID = caseplanID;
	}

	/**
	 * @return Returns the entryDate.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}

	/**
	 * @param entryDate The entryDate to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setEntryDate(Date entryDate)
	{
		if (this.entryDate == null || !this.entryDate.equals(entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}

	/**
	 * @return Returns the explanation.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getExplanation()
	{
		fetch();
		return explanation;
	}

	/**
	 * @param explanation The explanation to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setExplanation(String explanation)
	{
		if (this.explanation == null || !this.explanation.equals(explanation))
		{
			markModified();
		}
		this.explanation = explanation;
	}	
	/**
	 * @return Returns the signatureStatus.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSignatureStatus()
	{
		fetch();
		return signatureStatus;
	}

	/**
	 * @param signatureStatus The signatureStatus to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSignatureStatus(String signatureStatus)
	{
		if (this.signatureStatus == null || !this.signatureStatus.equals(signatureStatus))
		{
			markModified();
		}
		this.signatureStatus = signatureStatus;
	}

}

