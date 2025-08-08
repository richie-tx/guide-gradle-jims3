/*
 * Project: JIMS
 * Class:   messaging.juvenile.SearchJuvenileProfilesEvent
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenile;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import naming.UIConstants;

/**
 * Class SearchJuvenileProfilesEvent.
 * 
 * @author Anand Thorat
 */
public class SearchJuvenileProfilesEvent extends mojo.km.messaging.RequestEvent
{
	// ------------------------------------------------------------------------
	// --- final static field ---
	// ------------------------------------------------------------------------
	private static final SimpleDateFormat	dateFormat	= new SimpleDateFormat( "yyyy-MM-dd" );

	// ------------------------------------------------------------------------
	// --- fields ---
	// ------------------------------------------------------------------------
	public String													alienNumber;

	public String													firstName;
	public String													middleName;
	public String													lastName;
	public String													nameSuffix;

	public String													raceId;
	public Date														dateOfBirth;

	public String													statusId;
	public String													juvenileNum;
	public String													supervisionNumber;

	// empty string: dont include alias records; 
	// "alias" string: include alias records in result set  
	public String													searchType = UIConstants.EMPTY_STRING ;

	public String													sexId;
	private String												juvenileId;
	private Collection										juvenileProfiles;
	private String												juvenileType;

	private Date													aliasEntryDate;

	private String												aliasNotes;
	private String 												ssn;
	private String 												from;
	//task 171828
	private String 												dalogNum;

	// ------------------------------------------------------------------------
	// --- constructor ---
	// ------------------------------------------------------------------------

	

	

	/**
	 * @roseuid 42A74E1900AB
	 */
	public SearchJuvenileProfilesEvent()
	{
	} // end of
		// messaging.juvenile.SearchJuvenileProfilesEvent.SearchJuvenileProfilesEvent

	public String getJuvenileType()
	{
		return juvenileType;
	}

	public void setJuvenileType( String juvenileType )
	{
		this.juvenileType = juvenileType;
	}

	public Date getAliasEntryDate()
	{
		return aliasEntryDate;
	}

	public void setAliasEntryDate( Date aliasEntryDate )
	{
		this.aliasEntryDate = aliasEntryDate;
	}

	public String getAliasNotes()
	{
		return aliasNotes;
	}

	public void setAliasNotes( String aliasNotes )
	{
		this.aliasNotes = aliasNotes;
	}

	// ------------------------------------------------------------------------
	// --- methods ---
	// ------------------------------------------------------------------------

	/**
	 * @param alienNumber
	 *          @roseuid 42A5DD8F033C
	 */
	public void setAlienNumber( String alienNumber )
	{
		this.alienNumber = alienNumber;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setAlienNumber

	/**
	 * @return String
	 * @roseuid 42A5DD8F033E
	 */
	public String getAlienNumber()
	{
		return alienNumber;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getAlienNumber

	/**
	 * @param firstName
	 *          @roseuid 42A5DD8F034B
	 */
	public void setFirstName( String firstName )
	{
		this.firstName = firstName;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setFirstName

	/**
	 * @return String
	 * @roseuid 42A5DD8F034D
	 */
	public String getFirstName()
	{
		return firstName;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getFirstName

	/**
	 * @param lastName
	 *          @roseuid 42A5DD8F034F
	 */
	public void setLastName( String lastName )
	{
		this.lastName = lastName;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setLastName

	/**
	 * @return String
	 * @roseuid 42A5DD8F035C
	 */
	public String getLastName()
	{
		return lastName;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getLastName

	/**
	 * @param raceId
	 *          @roseuid 42A5DD8F035E
	 */
	public void setRaceId( String raceId )
	{
		this.raceId = raceId;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setRaceId

	/**
	 * @return String
	 * @roseuid 42A5DD8F036B
	 */
	public String getRaceId()
	{
		return raceId;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getRaceId

	/**
	 * @param dateOfBirth
	 *          @roseuid 42A5DD8F036D
	 */
	public void setDateOfBirth( Date dateOfBirth )
	{
		this.dateOfBirth = dateOfBirth;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setDateOfBirth

	/**
	 * @return String
	 * @roseuid 42A5DD8F036F
	 */
	public String getDateOfBirth()
	{
		String date = "";
		if( dateOfBirth != null )
		{
			date = dateFormat.format( dateOfBirth );

		}
		return date;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getDateOfBirth

	/**
	 * @param statusId
	 *          @roseuid 42A5DD8F037B
	 */
	public void setStatusId( String statusId )
	{
		if( statusId == null )
		{
			this.statusId = "";
		}
		else
		{
			this.statusId = statusId;
		}

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setStatusId

	/**
	 * @return String
	 * @roseuid 42A5DD8F037D
	 */
	public String getStatusId()
	{
		return statusId;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getStatusId

	/**
	 * @param juvenileNum
	 *          @roseuid 42A5DD8F038A
	 */
	public void setJuvenileNum( String juvenileNum )
	{
		this.juvenileNum = juvenileNum;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setJuvenileNum

	/**
	 * @return String
	 * @roseuid 42A5DD8F038C
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getJuvenileNum

	/**
	 * @param supervisionNumber
	 *          @roseuid 42A5DD8F0399
	 */
	public void setSupervisionNumber( String supervisionNumber )
	{
		this.supervisionNumber = supervisionNumber;

	} // end of
		// messaging.juvenile.SearchJuvenileProfilesEvent.setSupervisionNumber

	/**
	 * @return String
	 * @roseuid 42A5DD8F039B
	 */
	public String getSupervisionNumber()
	{
		return supervisionNumber;
	} // end of
		// messaging.juvenile.SearchJuvenileProfilesEvent.getSupervisionNumber

	/**
	 * @param middleName
	 *          @roseuid 42A5DD8F039D
	 */
	public void setMiddleName( String middleName )
	{
		this.middleName = middleName;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setMiddleName

	/**
	 * @return String
	 * @roseuid 42A5DD8F03AA
	 */
	public String getMiddleName()
	{
		return middleName;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getMiddleName

	/**
	 * @param searchType
	 *          @roseuid 42A5DD8F03AC
	 */
	public void setSearchType( String searchType )
	{
		this.searchType = searchType;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setSearchType

	/**
	 * @return String
	 * @roseuid 42A5DD8F03C8
	 */
	public String getSearchType()
	{
		return searchType;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getSearchType

	/**
	 * @param sexId
	 *          @roseuid 42A5DD8F03CA
	 */
	public void setSexId( String sexId )
	{
		this.sexId = sexId;

	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setSexId

	/**
	 * @return String
	 * @roseuid 42A5DD8F03CC
	 */
	public String getSexId()
	{
		return sexId;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getSexId

	/**
	 * @return The juvenile profiles.
	 */
	public Collection getJuvenileProfiles()
	{
		return juvenileProfiles;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.getJuvenileProfiles

	/**
	 * @param collection
	 *          The juvenile profiles.
	 */
	public void setJuvenileProfiles( Collection collection )
	{
		juvenileProfiles = collection;
	} // end of messaging.juvenile.SearchJuvenileProfilesEvent.setJuvenileProfiles

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
	public void setJuvenileId( String juvenileId )
	{
		this.juvenileId = juvenileId;
	}

	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @param nameSuffix the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getFrom()
	{
	    return from;
	}

	public void setFrom(String from)
	{
	    this.from = from;
	}
	public String getDalogNum()
	{
	    return dalogNum;
	}

	public void setDalogNum(String dalogNum)
	{
	    this.dalogNum = dalogNum;
	}

} // end SearchJuvenileProfilesEvent
