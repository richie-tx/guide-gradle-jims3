// C:\\views\\CommonSupervision\\app\\src\\pd\\criminalcase\\CaseParty.java

package pd.criminalcase;

import mojo.km.persistence.PersistentObject;
import pd.contact.party.Party;

public class CaseParty extends PersistentObject
{
	/**
	 * Properties for criminalCase
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	protected CriminalCase criminalCase;
	private String criminalCaseId;
	private String connectionId;
	/**
	 * Properties for party
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private Party party;
	private String partyId;

	/**
	 * @roseuid 4507099A023C
	 */
	public CaseParty() {

	}

	/**
	 * @return Returns the connectionId.
	 */
	public String getConnectionId()
	{
		fetch();
		return connectionId;
	}

	/**
	 * @return Returns the criminalCase.
	 */
	public CriminalCase getCriminalCase()
	{
		fetch();
		initCriminalCase();
		return criminalCase;
	}

	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId()
	{
		fetch();
		return criminalCaseId;
	}

	/**
	 * Initialize class relationship to class pd.criminalcase.CriminalCase
	 */
	protected void initCriminalCase()
	{
		if (criminalCase == null)
		{
			criminalCase = (CriminalCase) new mojo.km.persistence.Reference(
					criminalCaseId,
						CriminalCase.class,
						(mojo.km.persistence.PersistentObject) this,
						"criminalCase").getObject();
		}
	}
	
	/**
	 * @return Returns the party.
	 */
	public Party getParty()
	{
		fetch();
		initParty();
		return party;
	}

	/**
	 * @return Returns the partyId.
	 */
	public String getPartyId()
	{
		fetch();
		return partyId;
	}

	/**
	 * Initialize class relationship to class pd.contact.party.Party
	 */
	private void initParty()
	{
		if (party == null)
		{
			party = (Party) new mojo.km.persistence.Reference(
					partyId,
						Party.class,
						(mojo.km.persistence.PersistentObject) this,
						"party").getObject();
		}
	}

	/**
	 * @param connectionId The connectionId to set.
	 */
	public void setConnectionId(String connectionId)
	{
		this.connectionId = connectionId;
	}

	/**
	 * @param aCriminalCase The criminalCase to set.
	 */
	public void setCriminalCase(CriminalCase aCriminalCase)
	{
		if (this.criminalCase == null || !this.criminalCase.equals(aCriminalCase))
		{
			markModified();
		}
		if (aCriminalCase.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCriminalCase);
		}
		setCriminalCaseId(""+aCriminalCase.getOID());
		this.criminalCase = (CriminalCase) new mojo.km.persistence.Reference(aCriminalCase).getObject();
	}

	/**
	 * @param aCriminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String aCriminalCaseId)
	{
		if (criminalCaseId == null || !criminalCaseId.equals(aCriminalCaseId))
		{
			markModified();
		}
		criminalCase = null;
		this.criminalCaseId = aCriminalCaseId;
	}

	/**
	 * @param aParty The party to set.
	 */
	public void setParty(Party aParty)
	{
		if (this.party == null || !this.party.equals(aParty))
		{
			markModified();
		}
		setPartyId(""+aParty.getOID());
		this.party = (Party) new mojo.km.persistence.Reference(aParty).getObject();
	}

	/**
	 * @param aPartyId  The partyId to set.
	 */
	public void setPartyId(String aPartyId)
	{
		if (partyId == null || !partyId.equals(aPartyId))
		{
			markModified();
		}
		party = null;
		this.partyId = aPartyId;
	}

}
