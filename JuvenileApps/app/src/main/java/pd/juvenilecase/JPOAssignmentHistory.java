package pd.juvenilecase;

import pd.contact.officer.OfficerProfile;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * @stereotype design
 * @author dnikolis
 */
public class JPOAssignmentHistory extends PersistentObject {
	private String casefileId;
	private Date jpoAssignmentDate;
	private String officerProfileId;
	/**
	 * 
	 * @referencedType pd.contact.officer.OfficerProfile
	 * @detailerDoNotGenerate false
	 */
	private OfficerProfile officerProfile = null;

	/**
	 * 
	 */
	public JPOAssignmentHistory() {
	}

	/**
	 * 
	 * @return the casefileId
	 */
	public String getCasefileId() {
		fetch();
		return casefileId;
	}

	/**
	 * 
	 * @return the jpoAssignmentDate
	 */
	public Date getJpoAssignmentDate() {
		fetch();
		return jpoAssignmentDate;
	}

	/**
	 * Access method for the officer profile property.
	 * @return the current value of the officer profile property
	 */
	public OfficerProfile getOfficerProfile() {
		fetch();
		initOfficerProfile();
		return officerProfile;
	}

	/**
	* @return 
	*/
	public String getOfficerProfileId()
	{
		fetch();
		return officerProfileId;
	}	
	
	/**
	* Initialize class relationship to class pd.juvenilecase.CaseFile
	*/
	private void initOfficerProfile()
	{
		if (officerProfile == null)
		{
			try
			{
				officerProfile =
					(OfficerProfile) new mojo.km.persistence.Reference(officerProfileId, OfficerProfile.class).getObject();
			}
			catch (Throwable t)
			{
				officerProfile = null;
			}
		}
	}
	
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		if (this.casefileId == null || !this.casefileId.equals(casefileId)) {
			markModified();
		}
		this.casefileId = casefileId;
	}

	/**
	 * @param jpoAssignmentDate the jpoAssignmentDate to set
	 */
	public void setJpoAssignmentDate(Date jpoAssignmentDate) {
		if (this.jpoAssignmentDate == null
				|| !this.jpoAssignmentDate.equals(jpoAssignmentDate)) {
			markModified();
		}
		this.jpoAssignmentDate = jpoAssignmentDate;
	}

	/**
	 * set the type reference for class member officerProfile
	 */
	public void setOfficerProfile(OfficerProfile theOfficerProfile)
	{
		if (this.officerProfile == null || !this.officerProfile.equals(theOfficerProfile))
		{
			markModified();
		}
		if (theOfficerProfile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(theOfficerProfile);
		}
		setOfficerProfileId("" + theOfficerProfile.getOID());
		this.officerProfile = (OfficerProfile) new mojo.km.persistence.Reference(theOfficerProfile).getObject();
	}
	
	/**
	* @param string
	*/
	public void setOfficerProfileId(String theOfficerProfileId)
	{
		if (this.officerProfileId == null || !this.officerProfileId.equals(theOfficerProfileId))
		{
			markModified();
		}
		this.officerProfileId = theOfficerProfileId;
	}
	
	/**
	* @return JPOAssignmentHistory
	* @param jpoAssignmentHistoryId
	*/
	static public JPOAssignmentHistory find(String jpoAssignmentHistoryId)
	{
		IHome home = new Home();
		JPOAssignmentHistory jpoAssignmentHistory = (JPOAssignmentHistory) home.find(jpoAssignmentHistoryId, JPOAssignmentHistory.class);
		return jpoAssignmentHistory;
	}
	/**
	* @return Iterator jpoAssignmentHistories
	* @param attrName name fo the attribute for where clause
	* @param attrValue value to be checked in the where clause
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator jpoAssignmentHistories = home.findAll(attrName, attrValue, JPOAssignmentHistory.class);
		return jpoAssignmentHistories;
	}
	
	/**
	* Finds casefiles by a certain event
	* @param event
	* @return Iterator of casefiles
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator histories = home.findAll(event, JPOAssignmentHistory.class);
		return histories;
	}
}
