package pd.juvenilecase.casefile;

import java.util.Iterator;

import messaging.casefile.reply.CasefileNonComplianceNoticeSanctionResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileVOPSanctionCodes;

/**
 * @stereotype entity
 * @author dnikolis
 */
public class CasefileNonComplianceNoticeSanction extends mojo.km.persistence.PersistentObject
{
	private String comments;
	private String otherText;
	private String casefileNonComplianceNoticeId;
	/**
	 * @referencedType pd.codetable.criminal.JuvenileVOPSanctionCodes
	 * @detailerDoNotGenerate false
	 */
	private JuvenileVOPSanctionCodes juvenileVOPSanctionCodes = null;
	private String juvenileVOPSanctionCodesId;

	public String getComments()
	{
		fetch();
		return comments;
	}

	public void setComments(String comments)
	{
		if (this.comments == null || !this.comments.equals(comments))
		{
			markModified();
		}
		this.comments = comments;
	}

	public String getOtherText()
	{
		fetch();
		return otherText;
	}

	public void setOtherText(String otherText)
	{
		if (this.otherText == null || !this.otherText.equals(otherText))
		{
			markModified();
		}
		this.otherText = otherText;
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

	/**
	 * Set the reference value to class :: pd.codetable.criminal.JuvenileVOPSanctionCodes
	 */
	public void setJuvenileVOPSanctionCodesId(String juvenileVOPSanctionCodesId)
	{
		if (this.juvenileVOPSanctionCodesId == null
				|| !this.juvenileVOPSanctionCodesId.equals(juvenileVOPSanctionCodesId))
		{
			markModified();
		}
		juvenileVOPSanctionCodes = null;
		this.juvenileVOPSanctionCodesId = juvenileVOPSanctionCodesId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.JuvenileVOPSanctionCodes
	 */
	public String getJuvenileVOPSanctionCodesId()
	{
		fetch();
		return juvenileVOPSanctionCodesId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.JuvenileVOPSanctionCodes
	 */
	private void initJuvenileVOPSanctionCodes()
	{
		if (juvenileVOPSanctionCodes == null)
		{
			juvenileVOPSanctionCodes = (JuvenileVOPSanctionCodes) new mojo.km.persistence.Reference(
					juvenileVOPSanctionCodesId, JuvenileVOPSanctionCodes.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.criminal.JuvenileVOPSanctionCodes
	 */
	public JuvenileVOPSanctionCodes getJuvenileVOPSanctionCodes()
	{
		initJuvenileVOPSanctionCodes();
		return juvenileVOPSanctionCodes;
	}

	/**
	 * set the type reference for class member juvenileVOPSanctionCodes
	 */
	public void setJuvenileVOPSanctionCodes(JuvenileVOPSanctionCodes juvenileVOPSanctionCodes)
	{
		if (this.juvenileVOPSanctionCodes == null || !this.juvenileVOPSanctionCodes.equals(juvenileVOPSanctionCodes))
		{
			markModified();
		}
		setJuvenileVOPSanctionCodesId("" + juvenileVOPSanctionCodes.getOID());
		this.juvenileVOPSanctionCodes = (JuvenileVOPSanctionCodes) new mojo.km.persistence.Reference(
				juvenileVOPSanctionCodes).getObject();
	}
	
	public CasefileNonComplianceNoticeSanctionResponseEvent getResponseEvent(){
		CasefileNonComplianceNoticeSanctionResponseEvent myRespEvt=new CasefileNonComplianceNoticeSanctionResponseEvent();
		myRespEvt.setCasefileNonComplianceNoticeId(this.getOID());
		myRespEvt.setComments(this.getComments());
		myRespEvt.setOtherText(this.getOtherText());
		myRespEvt.setJuvenileVOPSanctionCodesId(this.getJuvenileVOPSanctionCodesId());
		return myRespEvt;
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator sanctions = home.findAll(attrName, attrValue, CasefileNonComplianceNoticeSanction.class);
		return sanctions;
	}
}
