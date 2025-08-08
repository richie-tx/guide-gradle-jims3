package pd.juvenilecase.casefile;

import java.util.Iterator;

import messaging.casefile.reply.CasefileNonComplianceNoticeProbationViolationResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileTechnicalVOPCodes;

/**
 * @stereotype entity
 * @author dnikolis
 */
public class CasefileNonComplianceNoticeProbationViolation extends mojo.km.persistence.PersistentObject
{
	/**
	 * @referencedType pd.codetable.criminal.JuvenileTechnicalVOPCodes
	 * @detailerDoNotGenerate false
	 */
	private JuvenileTechnicalVOPCodes juvenileTechnicalVOPCodes = null;
	private String casefileNonComplianceNoticeId;
	private String juvenileTechnicalVOPCodesId;

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
	 * Set the reference value to class :: pd.codetable.criminal.JuvenileTechnicalVOPCodes
	 */
	public void setJuvenileTechnicalVOPCodesId(String juvenileTechnicalVOPCodesId)
	{
		if (this.juvenileTechnicalVOPCodesId == null
				|| !this.juvenileTechnicalVOPCodesId.equals(juvenileTechnicalVOPCodesId))
		{
			markModified();
		}
		juvenileTechnicalVOPCodes = null;
		this.juvenileTechnicalVOPCodesId = juvenileTechnicalVOPCodesId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.JuvenileTechnicalVOPCodes
	 */
	public String getJuvenileTechnicalVOPCodesId()
	{
		fetch();
		return juvenileTechnicalVOPCodesId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.JuvenileTechnicalVOPCodes
	 */
	private void initJuvenileTechnicalVOPCodes()
	{
		if (juvenileTechnicalVOPCodes == null)
		{
			juvenileTechnicalVOPCodes = (JuvenileTechnicalVOPCodes) new mojo.km.persistence.Reference(
					juvenileTechnicalVOPCodesId, JuvenileTechnicalVOPCodes.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.criminal.JuvenileTechnicalVOPCodes
	 */
	public JuvenileTechnicalVOPCodes getJuvenileTechnicalVOPCodes()
	{
		initJuvenileTechnicalVOPCodes();
		return juvenileTechnicalVOPCodes;
	}

	/**
	 * set the type reference for class member juvenileTechnicalVOPCodes
	 */
	public void setJuvenileTechnicalVOPCodes(JuvenileTechnicalVOPCodes juvenileTechnicalVOPCodes)
	{
		if (this.juvenileTechnicalVOPCodes == null || !this.juvenileTechnicalVOPCodes.equals(juvenileTechnicalVOPCodes))
		{
			markModified();
		}
		setJuvenileTechnicalVOPCodesId("" + juvenileTechnicalVOPCodes.getOID());
		this.juvenileTechnicalVOPCodes = (JuvenileTechnicalVOPCodes) new mojo.km.persistence.Reference(
				juvenileTechnicalVOPCodes).getObject();
	}
	
	public CasefileNonComplianceNoticeProbationViolationResponseEvent getResponseEvent(){
		CasefileNonComplianceNoticeProbationViolationResponseEvent myRespEvt=new CasefileNonComplianceNoticeProbationViolationResponseEvent();
		myRespEvt.setCasefileNonComplianceNoticeId(this.getOID());
		myRespEvt.setJuvenileTechnicalVOPCodesId(this.getJuvenileTechnicalVOPCodesId());
		return myRespEvt;
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator violations = home.findAll(attrName, attrValue, CasefileNonComplianceNoticeProbationViolation.class);
		return violations;
	}
}
