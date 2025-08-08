package messaging.juvenile;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import mojo.km.messaging.RequestEvent;

/**
 * Class GetActiveJuvenileProfilesEvent.
 *  
 * @author  mchowdhury
 */
public class GetActiveJuvenileProfilesEvent extends mojo.km.messaging.RequestEvent
{

	// ------------------------------------------------------------------------
	// --- final static field                                               ---
	// ------------------------------------------------------------------------

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	// ------------------------------------------------------------------------
	// --- fields                                                           ---
	// ------------------------------------------------------------------------

	public String alienNumber;

	public String firstName;
	public String middleName;
	public String lastName;

	public String raceId;
	public Date dateOfBirth;
	public String statusId;
	public String juvenileNum;
	public String supervisionNumber;

	public String searchType;

	public String sexId;
	
	private String pendingCloseStatusId;
	private String closingApprovedStatusId;
	private String closingSubmitStatusId;

	private Collection juvenileProfiles;

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42A74E1900AB
	 */
	public GetActiveJuvenileProfilesEvent()
	{
	}

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param alienNumber @roseuid 42A5DD8F033C
	 */
	public void setAlienNumber(String alienNumber)
	{
		this.alienNumber = alienNumber;

	} //.setAlienNumber

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F033E
	 */
	public String getAlienNumber()
	{
		return alienNumber;
	} //.getAlienNumber

	/**
	 *  
	 * @param firstName @roseuid 42A5DD8F034B
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;

	} //.setFirstName

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F034D
	 */
	public String getFirstName()
	{
		return firstName;
	} //.getFirstName

	/**
	 *  
	 * @param lastName @roseuid 42A5DD8F034F
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;

	} //.setLastName

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F035C
	 */
	public String getLastName()
	{
		return lastName;
	} //.getLastName

	/**
	 *  
	 * @param raceId @roseuid 42A5DD8F035E
	 */
	public void setRaceId(String raceId)
	{
		this.raceId = raceId;

	} //.setRaceId

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F036B
	 */
	public String getRaceId()
	{
		return raceId;
	} //.getRaceId

	/**
	 *  
	 * @param dateOfBirth @roseuid 42A5DD8F036D
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;

	} //.setDateOfBirth

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F036F
	 */
	public String getDateOfBirth()
	{
		String date = "";
		if (dateOfBirth != null)
		{
			date = dateFormat.format(dateOfBirth);

		}
		return date;
	} //.getDateOfBirth
	
	/**
	 *  
	 * @return  Date
	 * @roseuid 42A5DD8F036F
	 */
	public Date getDOB()
	{
		return this.dateOfBirth;
	} //.getDateOfBirth

	/**
	 *  
	 * @param statusId @roseuid 42A5DD8F037B
	 */
	public void setStatusId(String statusId)
	{
		this.statusId = statusId;

	} //.setStatusId

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F037D
	 */
	public String getStatusId()
	{
		return statusId;
	} //.getStatusId

	/**
	 *  
	 * @param juvenileNum @roseuid 42A5DD8F038A
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;

	} //.setJuvenileNum

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F038C
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	} //.getJuvenileNum

	/**
	 *  
	 * @param supervisionNumber @roseuid 42A5DD8F0399
	 */
	public void setSupervisionNumber(String supervisionNumber)
	{
		this.supervisionNumber = supervisionNumber;

	} //.setSupervisionNumber

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F039B
	 */
	public String getSupervisionNumber()
	{
		return supervisionNumber;
	} //.getSupervisionNumber

	/**
	 *  
	 * @param middleName @roseuid 42A5DD8F039D
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;

	} //.setMiddleName

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F03AA
	 */
	public String getMiddleName()
	{
		return middleName;
	} //.getMiddleName

	/**
	 *  
	 * @param searchType @roseuid 42A5DD8F03AC
	 */
	public void setSearchType(String searchType)
	{
		this.searchType = searchType;

	} //.setSearchType

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F03C8
	 */
	public String getSearchType()
	{
		return searchType;
	} //.getSearchType

	/**
	 *  
	 * @param sexId @roseuid 42A5DD8F03CA
	 */
	public void setSexId(String sexId)
	{
		this.sexId = sexId;

	} //.setSexId

	/**
	 *  
	 * @return  String
	 * @roseuid 42A5DD8F03CC
	 */
	public String getSexId()
	{
		return sexId;
	} //.getSexId

	/**
	 *  
	 * @return  The juvenile profiles.
	 */
	public Collection getJuvenileProfiles()
	{
		return juvenileProfiles;
	} //.getJuvenileProfiles

	/**
	 *  
	 * @param collection The juvenile profiles.
	 */
	public void setJuvenileProfiles(Collection collection)
	{
		juvenileProfiles = collection;
	} //.setJuvenileProfiles

	/**
	 * @return
	 */
	public String getClosingApprovedStatusId()
	{
		return closingApprovedStatusId;
	}

	/**
	 * @return
	 */
	public String getClosingSubmitStatusId()
	{
		return closingSubmitStatusId;
	}

	/**
	 * @return
	 */
	public String getPendingCloseStatusId()
	{
		return pendingCloseStatusId;
	}

	/**
	 * @param string
	 */
	public void setClosingApprovedStatusId(String string)
	{
		closingApprovedStatusId = string;
	}

	/**
	 * @param string
	 */
	public void setClosingSubmitStatusId(String string)
	{
		closingSubmitStatusId = string;
	}

	/**
	 * @param string
	 */
	public void setPendingCloseStatusId(String string)
	{
		pendingCloseStatusId = string;
	}

} // end SearchJuvenileProfilesEvent
