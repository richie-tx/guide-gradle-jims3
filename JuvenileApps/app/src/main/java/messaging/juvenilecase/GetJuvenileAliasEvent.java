package messaging.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import mojo.km.messaging.RequestEvent;

/**
 * class GetJuvenileAliasEvent.
 *  
 * @author  parumbakkam
 */
public class GetJuvenileAliasEvent extends RequestEvent
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
	public String dateOfBirth;
	public String statusId;
	public String juvenileNum;
	public String supervisionNumber;


	public String searchType;

	public String sexId;
	private String juvenileId;
	private String juvenileType;
	private Date aliasEntryDate;
	
	private String aliasNotes;
	private Collection juvenileProfiles;

	public String getJuvenileType() {
		return juvenileType;
	}

	public void setJuvenileType(String juvenileType) {
		this.juvenileType = juvenileType;
	}

	public Date getAliasEntryDate() {
		return aliasEntryDate;
	}

	public void setAliasEntryDate(Date aliasEntryDate) {
		this.aliasEntryDate = aliasEntryDate;
	}

	public String getAliasNotes() {
		return aliasNotes;
	}

	public void setAliasNotes(String aliasNotes) {
		this.aliasNotes = aliasNotes;
	}

	/**
	 * @roseuid 42A74E1900AB
	 */
	public GetJuvenileAliasEvent()
	{
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent

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

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setAlienNumber


	public String getAlienNumber()
	{
		return alienNumber;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getAlienNumber


	public void setFirstName(String firstName)
	{
		this.firstName = firstName;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setFirstName


	public String getFirstName()
	{
		return firstName;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getFirstName


	public void setLastName(String lastName)
	{
		this.lastName = lastName;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setLastName


	public String getLastName()
	{
		return lastName;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getLastName


	public void setRaceId(String raceId)
	{
		this.raceId = raceId;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setRaceId


	public String getRaceId()
	{
		return raceId;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getRaceId


	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setDateOfBirth


	public String getDateOfBirth()
	{
		return dateOfBirth;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getDateOfBirth


	public void setStatusId(String statusId)
	{
		if(statusId == null){
			this.statusId = "";
		}else{
			this.statusId = statusId;
		}

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setStatusId


	public String getStatusId()
	{
		return statusId;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getStatusId


	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setJuvenileNum


	public String getJuvenileNum()
	{
		return juvenileNum;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getJuvenileNum


	public void setSupervisionNumber(String supervisionNumber)
	{
		this.supervisionNumber = supervisionNumber;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setSupervisionNumber


	public String getSupervisionNumber()
	{
		return supervisionNumber;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getSupervisionNumber


	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setMiddleName


	public String getMiddleName()
	{
		return middleName;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getMiddleName


	public void setSearchType(String searchType)
	{
		this.searchType = searchType;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setSearchType


	public String getSearchType()
	{
		return searchType;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getSearchType


	public void setSexId(String sexId)
	{
		this.sexId = sexId;

	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setSexId


	public String getSexId()
	{
		return sexId;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getSexId


	public Collection getJuvenileProfiles()
	{
		return juvenileProfiles;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.getJuvenileProfiles


	public void setJuvenileProfiles(Collection collection)
	{
		juvenileProfiles = collection;
	} //end of messaging.juvenilecase.GetJuvenileAliasEvent.setJuvenileProfiles


	public String getJuvenileId() {
		return juvenileId;
	}

	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
} // end GetJuvenileAliasEvent
