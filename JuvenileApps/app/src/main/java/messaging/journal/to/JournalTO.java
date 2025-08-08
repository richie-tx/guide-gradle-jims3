package messaging.journal.to;

import java.util.ArrayList;
import java.util.List;

public class JournalTO
{
	// The following attributes are implemented as strings for the
	// benefit of the reporting tools where they are used.
	private String juvenileName = "";
	private String juvenileId = "";
	private String probationOfficer;
	private String caseFileId = "";
	private String supervisionType = "";
	private List journalEntries = new ArrayList();

	/**
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName()
	{
		return juvenileName;
	}

	/**
	 * @param juvenileName
	 *          The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName)
	{
		this.juvenileName = juvenileName;
	}

	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId()
	{
		return juvenileId;
	}

	/**
	 * @param juvenileId
	 *          The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId)
	{
		this.juvenileId = juvenileId;
	}

	/**
	 * 
	 */
	public String getProbationOfficer()
	{
		return this.probationOfficer;
	}

	/**
	 * 
	 */
	public void setProbationOfficer(String aName)
	{
		this.probationOfficer = aName;
	}

	/**
	 * @return Returns the caseFileId.
	 */
	public String getCaseFileId()
	{
		return caseFileId;
	}

	/**
	 * @param caseFileId
	 *          The caseFileId to set.
	 */
	public void setCaseFileId(String caseFileId)
	{
		this.caseFileId = caseFileId;
	}

	public String getSupervisionType()
	{
		return this.supervisionType;
	}

	public void setSupervisionType(String aName)
	{
		this.supervisionType = aName;
	}

	public List getJournalEntries() {
		return journalEntries;
	}

	public void setJournalEntries(List journalEntries) {
		this.journalEntries = journalEntries;
	}
}
