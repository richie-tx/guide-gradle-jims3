/*
 * Project: JIMS
 * Class:   messaging.juvenile.reply.JuvenileProfileDetailResponseEvent
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenile.reply;

import java.util.ArrayList ;
import java.util.Collection ;
import java.util.Date ;

import messaging.juvenilecase.reply.JuvenileAliasResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import naming.PDJuvenileConstants ;

/**
 * 
 * @author athorat To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileProfileDetailResponseEvent extends mojo.km.messaging.ResponseEvent
{
	// ------------------------------------------------------------------------
	// --- fields ---
	// ------------------------------------------------------------------------
	private boolean isAdopted = false ;
	private String adoptionComments ;

	private String failedPlacements ;

	private String firstName ;
	private String middleName ;
	private String lastName ;
	private String preferredFirstName ;
	private String nameSuffix;
	private String formattedName ;
	private String masterStatusId;
	private String masterStatus;

	private String juvenileNum ;
	private String juvenileCaseId ;

	private String birthCountry ;
	private String birthCity ;
	private String birthState ;

	private String race ;
	private String originalRace;
	private String hispanic ; ////U.S 88526
	private boolean multiracial ;

	private Date dateOfBirth ;
	private Date realDOB;
	private boolean verifiedDOB ;
	private String birthCounty ;

	private String isUSCitizenId ;
	private String isUSCitizen ;

	private String ethnicity ;
	private String nationality ;

	private String secondaryLanguage ;
	private String primaryLanguage ;

	private String driverLicenseNumber ;
	private String driverLicenseState ;
	private String driverLicenseClassId ;
	private Date driverLicenseExpireDate ;
	private String passportNum ; //added for passport details
	private String countryOfIssuanceId ; //added for passport details
	private Date passportExpirationDate ; //added for passport details


	private String SSN ;
	private String formattedSSN ;

	private String SID ;
	private String SONumber ;
	private String educationId ;
	private String studentId; 	//Changes for ER JIMS200077276 
	private String DHSNumber ;
	private String PEIMSId ;
	private String TSDSId;
	private String alienNumber ;
	private String FBINumber ;

	private String natualEyeColor ;
	private String naturalHairColor ;
	private String complexion ;

	private String religion ;

	private String DNASampleNumber ;
	private Date dateSenttoDPS ;

	private String sex ;
	private String sexId ;
	private String raceId ;
	private String originalRaceId;
	private String statusId ;

	private String detentionFacility ;
	private String detentionFacilityId ;
	private String detentionStatus ;	
	private String detentionStatusId ;
	private String floorNum ;
	private String unitNum ;
	private String roomNum ;
	
	private String purgeFlag;

	private String status ;
	private boolean updatable ;
	private boolean mentalHealthServices;

	private Collection scarsAndMarks = new ArrayList( ) ;
	
	private String juvenileType;
	
	private Collection<JuvenileAliasResponseEvent> aliasList = null;
	
	//Added for US 32107 - Restricted Access marker
	private String restrictedAccess;
	
	//Added for US 37931 - JIS information
	private Collection<JuvenileJISResponseEvent> jisInfo;
	private JuvenileJISResponseEvent mostRecentJISInfo = new JuvenileJISResponseEvent();
	private Collection<JuvenileCasefileResponseEvent>casefiles;
	
	//Added for US 39892
	private String completeSSN;
	
	//added for US 40492
	private String jpoOfRecID;
	
	private String jpoOfRecord;
	
	//Referral Conversion US 70421	
	private String addressId;
	private String juvAddress; //for migrated records without caseFile
	private String juvCounty;//for migrated records without caseFile
	private String juvSchoolDist; //for migrated records to read from JJS_MS_Juvenile when there is no casefile
	private String juvSchoolName;//for migrated records to read from JJS_MS_Juvenile  when there is no casefile
	private String schoolId;//for jims2 NEWLY created records
	private String ssn1;
	private String ssn2;
	private String ssnRel1;
	private String ssnRel2;
	private String recType;
	
	//US 71171
	private String comments; //for comments created in create juvenile //saved in casenotepart1 (pd)
	private String lcuser;
	private String fromM204Flag; //flag to identify Legacy kid with no JCJUVENILE data
	
	//US 71173
	private String operator;
	
	//Bug #87695
	private String checkedOutTo;
	private Date checkedOutDate ; //bug fix: 89372
	
	//US 71582
	private String purgeBoxNum;
	private String purgeDate;
	private String purgeSerNum;
	private String purgeComments;
        

	private String caseNotePart1;
	private String caseNotePart2;
	private Date lcDate;
	private Date lcTime;
	private String lastReferral;
	private String oldStatusId;
	private String sealedDate;
	private String sealComments;
	
	 private String youthDeathReason;
	 private String youthDeathVerification;
	 private String youthDeathReasonDesc;
	 private String youthDeathVerificationDesc;   
	 private Date deathDate;
	 private int deathAge;
	 private String releaseDate;
	 private String sprvsionTypeCd;
	 private String sprvsionType;
	 
	 //User Story 97356
	 private String jpoPhoneNumber;
	 private String locUnitName;
	 private String juvUnitId;
	 private String jpoEmail;
	 
	 private Date nexthearingDate;
	 private String liveWithTjjd;
	 private String liveWithTjjdDesc;
	 private String latestCasefileId;
	
	 private String activityId; //added for US 185889
	
	 private String updateUser;
	 private Date updateDate;
	 private String lastActionBy;
	
	 //US 189272
	
	 private int juvExcluded;
	// ------------------------------------------------------------------------
	// --- constructors ---
	// ------------------------------------------------------------------------

	

	

	

	public String getSprvsionType()
	{
	    return sprvsionType;
	}

	public void setSprvsionType(String sprvsionType)
	{
	    this.sprvsionType = sprvsionType;
	}

	/**
	 * 
	 * @param _service
	 *          The _service.
	 */
	public JuvenileProfileDetailResponseEvent( )
	{
		super( ) ;
		this.setTopic( PDJuvenileConstants.JUVENILE_PROFILE_DETAIL_TOPIC ) ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.JuvenileProfileDetailResponseEvent

	// ------------------------------------------------------------------------
	// --- methods ---
	// ------------------------------------------------------------------------

	/**
	 * 
	 * @return The alien number.
	 */
	public String getAlienNumber( )
	{
		return alienNumber ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getAlienNumber

	public Collection<JuvenileAliasResponseEvent> getAliasList() {
		return aliasList;
	}

	public String getJuvenileType() {
		return juvenileType;
	}

	public void setJuvenileType(String juvenileType) {
		this.juvenileType = juvenileType;
	}

	public void setAliasList(Collection<JuvenileAliasResponseEvent> aliasList) {
		this.aliasList = aliasList;
	}
	/**
	 * 
	 * @return The birth city.
	 */
	public String getBirthCity( )
	{
		return birthCity ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getBirthCity

	/**
	 * 
	 * @return The birth country.
	 */
	public String getBirthCountry( )
	{
		return birthCountry ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getBirthCountry

	/**
	 * 
	 * @return The birth county.
	 */
	public String getBirthCounty( )
	{
		return birthCounty ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getBirthCounty

	/**
	 * 
	 * @return The birth state.
	 */
	public String getBirthState( )
	{
		return birthState ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getBirthState

	/**
	 * 
	 * @return The complexion.
	 */
	public String getComplexion( )
	{
		return complexion ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getComplexion

	/**
	 * 
	 * @return The date of birth.
	 */
	public Date getDateOfBirth( )
	{
		return dateOfBirth ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getDateOfBirth

	/**
	 * 
	 * @return The date sentto d p s.
	 */
	public Date getDateSenttoDPS( )
	{
		return dateSenttoDPS ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getDateSenttoDPS

	/**
	 * 
	 * @return The d h s number.
	 */
	public String getDHSNumber( )
	{
		return DHSNumber ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getDHSNumber

	/**
	 * 
	 * @return The d n a sample number.
	 */
	public String getDNASampleNumber( )
	{
		return DNASampleNumber ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getDNASampleNumber

	/**
	 * 
	 * @return The driver license number.
	 */
	public String getDriverLicenseNumber( )
	{
		return driverLicenseNumber ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getDriverLicenseNumber

	/**
	 * 
	 * @return The driver license state.
	 */
	public String getDriverLicenseState( )
	{
		return driverLicenseState ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getDriverLicenseState

	/**
	 * 
	 * @return The ethnicity.
	 */
	public String getEthnicity( )
	{
		return ethnicity ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getEthnicity

	/**
	 * 
	 * @return The f b i number.
	 */
	public String getFBINumber( )
	{
		return FBINumber ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getFBINumber

	/**
	 * 
	 * @return The boolean.
	 */
	 // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.isHispanic

	/**
	 * 
	 * @return The juvenile num.
	 */
	public String getJuvenileNum( )
	{
		return juvenileNum ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getJuvenileNum

	/**
	 * 
	 * @return The boolean.
	 */
	public boolean isMultiracial( )
	{
		return multiracial ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.isMultiracial

	/**
	 * 
	 * @return The nationality.
	 */
	public String getNationality( )
	{
		return nationality ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getNationality

	/**
	 * 
	 * @return The natual eye color.
	 */
	public String getNatualEyeColor( )
	{
		return natualEyeColor ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getNatualEyeColor

	/**
	 * 
	 * @return The natural hair color.
	 */
	public String getNaturalHairColor( )
	{
		return naturalHairColor ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getNaturalHairColor

	/**
	 * 
	 * @return The p e i m s id.
	 */
	public String getPEIMSId( )
	{
		return PEIMSId ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getPEIMSId

	/**
	 * 
	 * @return The primary citizenship.
	 */
	public String getIsUSCitizen( )
	{
		return isUSCitizen ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getIsUSCitizen

	/**
	 * @return Returns the isUSCitizenId.
	 */
	public String getIsUSCitizenId( )
	{
		return isUSCitizenId ;
	}

	/**
	 * @param isUSCitizenId
	 *          The isUSCitizenId to set.
	 */
	public void setIsUSCitizenId( String isUSCitizenId )
	{
		this.isUSCitizenId = isUSCitizenId ;
	}

	/**
	 * 
	 * @return The primary language.
	 */
	public String getPrimaryLanguage( )
	{
		return primaryLanguage ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getPrimaryLanguage

	/**
	 * 
	 * @return The race.
	 */
	public String getRace( )
	{
		return race ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getRace

	public String getOriginalRace( )
	{
		return originalRace;
	}
	
	/**
	 * 
	 * @return The religion.
	 */
	public String getReligion( )
	{
		return religion ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getReligion

	/**
	 * 
	 * @return The secondary language.
	 */
	public String getSecondaryLanguage( )
	{
		return secondaryLanguage ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getSecondaryLanguage

	/**
	 * 
	 * @return The sex.
	 */
	public String getSex( )
	{
		return sex ;
	} // end of messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getSex

	/**
	 * 
	 * @return The s i d.
	 */
	public String getSID( )
	{
		return SID ;
	} // end of messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getSID

	/**
	 * 
	 * @return The s o number.
	 */
	public String getSONumber( )
	{
		return SONumber ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getSONumber

	/**
	 * @return the educationId
	 */
	public String getEducationId() {
		return educationId;
	}

	/**
	 * 
	 * @return The s s n.
	 */
	public String getSSN( )
	{
		return SSN ;
	} // end of messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getSSN

	/*
	 * @return
	 */
	public String getFormattedSSN( )
	{
		if( SSN != null && SSN.length( ) > 0 )
		{
			StringBuffer ss = new StringBuffer( ) ;
			ss.append( SSN.substring( 0, 3 ) ).append( "-" ).append( SSN.substring( 3, 5 ) ).append( "-" ).append( SSN.substring( 5 ) ) ;
			formattedSSN = ss.toString( ) ;
		}
		else
		{
			formattedSSN = "" ;
		}

		return( formattedSSN ) ;
	}

	/**
	 * 
	 * @return The boolean.
	 */
	public boolean isVerifiedDOB( )
	{
		return verifiedDOB ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.isVerifiedDOB

	/**
	 * 
	 * @param string
	 *          The alien number.
	 */
	public void setAlienNumber( String string )
	{
		alienNumber = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setAlienNumber

	/**
	 * 
	 * @param string
	 *          The birth city.
	 */
	public void setBirthCity( String string )
	{
		birthCity = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setBirthCity

	/**
	 * 
	 * @param string
	 *          The birth country.
	 */
	public void setBirthCountry( String string )
	{
		birthCountry = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setBirthCountry

	/**
	 * 
	 * @param string
	 *          The birth county.
	 */
	public void setBirthCounty( String string )
	{
		birthCounty = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setBirthCounty

	/**
	 * 
	 * @param string
	 *          The birth state.
	 */
	public void setBirthState( String string )
	{
		birthState = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setBirthState

	/**
	 * 
	 * @param string
	 *          The complexion.
	 */
	public void setComplexion( String string )
	{
		complexion = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setComplexion

	/**
	 * 
	 * @param date
	 *          The date of birth.
	 */
	public void setDateOfBirth( Date date )
	{
		dateOfBirth = date ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setDateOfBirth

	/**
	 * 
	 * @param date
	 *          The date sentto d p s.
	 */
	public void setDateSenttoDPS( Date date )
	{
		dateSenttoDPS = date ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setDateSenttoDPS

	/**
	 * 
	 * @param string
	 *          The d h s number.
	 */
	public void setDHSNumber( String string )
	{
		DHSNumber = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setDHSNumber

	/**
	 * 
	 * @param string
	 *          The d n a sample number.
	 */
	public void setDNASampleNumber( String string )
	{
		DNASampleNumber = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setDNASampleNumber

	/**
	 * 
	 * @param string
	 *          The driver license number.
	 */
	public void setDriverLicenseNumber( String string )
	{
		driverLicenseNumber = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setDriverLicenseNumber

	/**
	 * 
	 * @param string
	 *          The driver license state.
	 */
	public void setDriverLicenseState( String string )
	{
		driverLicenseState = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setDriverLicenseState

	/**
	 * 
	 * @param string
	 *          The ethnicity.
	 */
	public void setEthnicity( String string )
	{
		ethnicity = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setEthnicity

	/**
	 * 
	 * @param string
	 *          The f b i number.
	 */
	public void setFBINumber( String string )
	{
		FBINumber = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setFBINumber

	/**
	 * 
	 * @param b
	 *          The hispanic.
	 */
	public void setHispanic( String b )
	{
		hispanic = b ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setHispanic

	/**
	 * 
	 * @param string
	 *          The juvenile num.
	 */
	public void setJuvenileNum( String string )
	{
		juvenileNum = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setJuvenileNum

	/**
	 * @return the hispanic
	 */
	public String getHispanic()
	{
	    return hispanic;
	}

	/**
	 * 
	 * @param b
	 *          The multiracial.
	 */
	public void setMultiracial( boolean b )
	{
		multiracial = b ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setMultiracial

	/**
	 * 
	 * @param string
	 *          The nationality.
	 */
	public void setNationality( String string )
	{
		nationality = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setNationality

	/**
	 * 
	 * @param string
	 *          The natual eye color.
	 */
	public void setNatualEyeColor( String string )
	{
		natualEyeColor = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setNatualEyeColor

	/**
	 * 
	 * @param string
	 *          The natural hair color.
	 */
	public void setNaturalHairColor( String string )
	{
		naturalHairColor = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setNaturalHairColor

	/**
	 * 
	 * @param string
	 *          The p e i m s id.
	 */
	public void setPEIMSId( String string )
	{
		PEIMSId = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setPEIMSId

	/**
	 * 
	 * @param string
	 *          The primary citizenship.
	 */
	public void setIsUSCitizen( String isUSCitizen )
	{
		this.isUSCitizen = isUSCitizen ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setPrimaryCitizenship

	/**
	 * 
	 * @param string
	 *          The primary language.
	 */
	public void setPrimaryLanguage( String string )
	{
		primaryLanguage = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setPrimaryLanguage

	/**
	 * 
	 * @param string
	 *          The race.
	 */
	public void setRace( String string )
	{
		race = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setRace

	public void setOriginalRace( String originalrace )
	{
		originalRace = originalrace;
	}
	
	/**
	 * 
	 * @param string
	 *          The religion.
	 */
	public void setReligion( String string )
	{
		religion = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setReligion

	/**
	 * 
	 * @param string
	 *          The secondary language.
	 */
	public void setSecondaryLanguage( String string )
	{
		secondaryLanguage = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setSecondaryLanguage

	/**
	 * 
	 * @param string
	 *          The sex.
	 */
	public void setSex( String string )
	{
		sex = string ;
	} // end of messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setSex

	/**
	 * 
	 * @param string
	 *          The s i d.
	 */
	public void setSID( String string )
	{
		SID = string ;
	} // end of messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setSID

	/**
	 * @param string
	 *          The s o number.
	 */
	public void setSONumber( String string )
	{
		SONumber = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setSONumber

	/**
	 * @param educationId the educationId to set
	 */
	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}
	
	/**
	 * @param string
	 *          The s s n.
	 */
	public void setSSN( String string )
	{
		SSN = string ;
	} // end of messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setSSN

	/**
	 * @param b
	 *          The verified d o b.
	 */
	public void setVerifiedDOB( boolean b )
	{
		verifiedDOB = b ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setVerifiedDOB

	/**
	 * 
	 * @return The first name.
	 */
	public String getFirstName( )
	{
		return firstName ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getFirstName

	/**
	 * 
	 * @return The last name.
	 */
	public String getLastName( )
	{
		return lastName ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getLastName

	/**
	 * 
	 * @return The middle name.
	 */
	public String getMiddleName( )
	{
		return middleName ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getMiddleName

	/**
	 * 
	 * @param string
	 *          The first name.
	 */
	public void setFirstName( String string )
	{
		firstName = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setFirstName

	/**
	 * 
	 * @param string
	 *          The last name.
	 */
	public void setLastName( String string )
	{
		lastName = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setLastName

	/**
	 * 
	 * @param string
	 *          The middle name.
	 */
	public void setMiddleName( String string )
	{
		middleName = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setMiddleName

	
	/**
	 * 
	 * @return The status.
	 */
	public String getStatus( )
	{
		return status ;
	} // end of
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

	// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.getStatus
	/**
	 * 
	 * @param string
	 *          The status.
	 */
	public void setStatus( String string )
	{
		status = string ;
	} // end of
		// messaging.juvenile.reply.JuvenileProfileDetailResponseEvent.setStatus

	/**
	 * @return
	 */
	public String getFormattedName( )
	{
		return formattedName ;
	}

	/**
	 * @param string
	 */
	public void setFormattedName( String string )
	{
		formattedName = string ;
	}

	/**
	 * @return
	 */
	public String getRaceId( )
	{
		return raceId ;
	}

	/**
	 * @return
	 */
	public String getSexId( )
	{
		return sexId ;
	}

	/**
	 * @return
	 */
	public String getStatusId( )
	{
		return statusId ;
	}

	/**
	 * @param string
	 */
	public void setRaceId( String string )
	{
		raceId = string ;
	}

	/**
	 * @param string
	 */
	public void setSexId( String string )
	{
		sexId = string ;
	}

	/**
	 * @param string
	 */
	public void setStatusId( String string )
	{
		statusId = string ;
	}

	/**
	 * @return
	 */
	public String getJuvenileCaseId( )
	{
		return juvenileCaseId ;
	}

	/**
	 * @param string
	 */
	public void setJuvenileCaseId( String string )
	{
		juvenileCaseId = string ;
	}

	/**
	 * @return
	 */
	public String getDriverLicenseClassId( )
	{
		return driverLicenseClassId ;
	}
	/**
	 * @return
	 */
	public String getPassportNum( )
	{
		return passportNum ;
	}
	
	/**
	 * @return
	 */
	public String getCountryOfIssuanceId( )
	{
		return countryOfIssuanceId ;
	}

	/**
	 * @return
	 */
	public Date getDriverLicenseExpireDate( )
	{
		return driverLicenseExpireDate ;
	}
	/**
	 * @return
	 */
	public Date getPassportExpirationDate( )
	{
		return passportExpirationDate ;
	}


	/**
	 * @param string
	 */
	public void setDriverLicenseClassId( String string )
	{
		driverLicenseClassId = string ;
	}

	/**
	 * @param date
	 */
	public void setDriverLicenseExpireDate( Date date )
	{
		driverLicenseExpireDate = date ;
	}
	/**
	 * @param string
	 */
	public void setPassportNum( String string )
	{
		passportNum = string ;
	}
	/**
	 * @param string
	 */
	public void setCountryOfIssuanceId( String string )
	{
		countryOfIssuanceId = string ;
	}


	/**
	 * @param date
	 */
	public void setPassportExpirationDate( Date date )
	{
		passportExpirationDate = date ;
	}
	/**
	 * @return
	 */
	public Collection getScarsAndMarks( )
	{
		return scarsAndMarks ;
	}

	/**
	 * @param collection
	 */
	public void setScarsAndMarks( Collection collection )
	{
		scarsAndMarks = collection ;
	}

	/**
	 * @return Returns the detentionFacility.
	 */
	public String getDetentionFacility( )
	{
		return detentionFacility ;
	}

	/**
	 * @param detentionFacility
	 *          The detentionFacility to set.
	 */
	public void setDetentionFacility( String detentionFacility )
	{
		this.detentionFacility = detentionFacility ;
	}

	/**
	 * @return Returns the detentionFacilityId.
	 */
	public String getDetentionFacilityId( )
	{
		return detentionFacilityId ;
	}

	/**
	 * @param detentionFacilityId
	 *          The detentionFacilityId to set.
	 */
	public void setDetentionFacilityId( String detentionFacilityId )
	{
		this.detentionFacilityId = detentionFacilityId ;
	}

	/**
	 * @return Returns the detentionStatus.
	 */
	public String getDetentionStatus( )
	{
		return detentionStatus ;
	}

	/**
	 * @param detentionStatus
	 *          The detentionStatus to set.
	 */
	public void setDetentionStatus( String detentionStatus )
	{
		this.detentionStatus = detentionStatus ;
	}

	/**
	 * @return Returns the detentionStatusId.
	 */
	public String getDetentionStatusId( )
	{
		return detentionStatusId ;
	}

	/**
	 * @param detentionStatusId
	 *          The detentionStatusId to set.
	 */
	public void setDetentionStatusId( String detentionStatusId )
	{
		this.detentionStatusId = detentionStatusId ;
	}

	/**
	 * @return Returns the failedPlacements.
	 */
	public String getFailedPlacements( )
	{
		return failedPlacements ;
	}

	/**
	 * @param failedPlacements
	 *          The failedPlacements to set.
	 */
	public void setFailedPlacements( String failedPlacements )
	{
		this.failedPlacements = failedPlacements ;
	}

	/**
	 * @return Returns the isAdopted.
	 */
	public boolean isAdopted( )
	{
		return isAdopted ;
	}

	/**
	 * @param isAdopted
	 *          The isAdopted to set.
	 */
	public void setAdopted( boolean isAdopted )
	{
		this.isAdopted = isAdopted ;
	}

	/**
	 * @return Returns the adoptionComments.
	 */
	public String getAdoptionComments( )
	{
		return adoptionComments ;
	}

	/**
	 * @param adoptionComments
	 *          The adoptionComments to set.
	 */
	public void setAdoptionComments( String adoptionComments )
	{
		this.adoptionComments = adoptionComments ;
	}

	/**
	 * @return Returns the updatable.
	 */
	public boolean isUpdatable( )
	{
		return updatable ;
	}

	/**
	 * @param updatable The updatable to set.
	 */
	public void setUpdatable( boolean updatable )
	{
		this.updatable = updatable ;
	}

	public boolean isMentalHealthServices() {
		return mentalHealthServices;
	}

	public void setMentalHealthServices(boolean mentalHealthServices) {
		this.mentalHealthServices = mentalHealthServices;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param purgeFlag the purgeFlag to set
	 */
	public void setPurgeFlag(String purgeFlag) {
		this.purgeFlag = purgeFlag;
	}

	/**
	 * @return the purgeFlag
	 */
	public String getPurgeFlag() {
		return purgeFlag;
	}

	public String getRestrictedAccess() {
		return restrictedAccess;
	}

	public void setRestrictedAccess(String restrictedAccess) {
		this.restrictedAccess = restrictedAccess;
	}

	public Collection<JuvenileJISResponseEvent> getJisInfo() {
		return jisInfo;
	}

	public void setJisInfo(Collection<JuvenileJISResponseEvent> jisInfo) {
		this.jisInfo = jisInfo;
	}

	public JuvenileJISResponseEvent getMostRecentJISInfo() {
		return mostRecentJISInfo;
	}

	public void setMostRecentJISInfo(JuvenileJISResponseEvent mostRecentJISInfo) {
		this.mostRecentJISInfo = mostRecentJISInfo;
	}

	public String getCompleteSSN() {
		return completeSSN;
	}

	public void setCompleteSSN(String completeSSN) {
		this.completeSSN = completeSSN;
	}

	public String getJpoOfRecID() {
		return jpoOfRecID;
	}

	public void setJpoOfRecID(String jpoOfRecID) {
		this.jpoOfRecID = jpoOfRecID;
	}

	public String getJpoOfRecord() {
		return jpoOfRecord;
	}

	public void setJpoOfRecord(String jpoOfRecord) {
		this.jpoOfRecord = jpoOfRecord;
	}

	public String getAddressId()
	{
	    return addressId;
	}

	public void setAddressId(String addressId)
	{
	    this.addressId = addressId;
	}

	public String getJuvAddress()
	{
	    return juvAddress;
	}

	public void setJuvAddress(String juvAddress)
	{
	    this.juvAddress = juvAddress;
	}

	public String getJuvCounty()
	{
	    return juvCounty;
	}

	public void setJuvCounty(String juvCounty)
	{
	    this.juvCounty = juvCounty;
	}

	public String getJuvSchoolDist()
	{
	    return juvSchoolDist;
	}

	public void setJuvSchoolDist(String juvSchoolDist)
	{
	    this.juvSchoolDist = juvSchoolDist;
	}

	public String getJuvSchoolName()
	{
	    return juvSchoolName;
	}

	public void setJuvSchoolName(String juvSchoolName)
	{
	    this.juvSchoolName = juvSchoolName;
	}

	public String getSchoolId()
	{
	    return schoolId;
	}

	public void setSchoolId(String schoolId)
	{
	    this.schoolId = schoolId;
	}

	public String getSsn1()
	{
	    return ssn1;
	}

	public void setSsn1(String ssn1)
	{
	    this.ssn1 = ssn1;
	}

	public String getSsn2()
	{
	    return ssn2;
	}

	public void setSsn2(String ssn2)
	{
	    this.ssn2 = ssn2;
	}

	public String getSsnRel1()
	{
	    return ssnRel1;
	}

	public void setSsnRel1(String ssnRel1)
	{
	    this.ssnRel1 = ssnRel1;
	}

	public String getSsnRel2()
	{
	    return ssnRel2;
	}

	public void setSsnRel2(String ssnRel2)
	{
	    this.ssnRel2 = ssnRel2;
	}

	public String getRecType()
	{
	    return recType;
	}

	public void setRecType(String recType)
	{
	    this.recType = recType;
	}

	public String getComments()
	{
	    return comments;
	}

	public void setComments(String comments)
	{
	    this.comments = comments;
	}

	/**
	 * @return the operator
	 */
	public String getOperator()
	{
	    return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator)
	{
	    this.operator = operator;
	}

	public String getLcuser()
	{
	    return lcuser;
	}

	public void setLcuser(String lcuser)
	{
	    this.lcuser = lcuser;
	}

	public String getFromM204Flag()
	{
	    return fromM204Flag;
	}

	public void setFromM204Flag(String fromM204Flag)
	{
	    this.fromM204Flag = fromM204Flag;
	}

	/**
	 * @return the checkedOutTo
	 */
	public String getCheckedOutTo()
	{
	    return checkedOutTo;
	}

	/**
	 * @param checkedOutTo the checkedOutTo to set
	 */
	public void setCheckedOutTo(String checkedOutTo)
	{
	    this.checkedOutTo = checkedOutTo;
	}

	/**
	 * @return the checkedOutDate
	 */
	public Date getCheckedOutDate()
	{
	    return checkedOutDate;
	}

	/**
	 * @param checkedOutDate the checkedOutDate to set
	 */
	public void setCheckedOutDate(Date checkedOutDate)
	{
	    this.checkedOutDate = checkedOutDate;
	}

	/**
	 * @return the purgeBoxNum
	 */
	public String getPurgeBoxNum()
	{
	    return purgeBoxNum;
	}

	/**
	 * @param purgeBoxNum the purgeBoxNum to set
	 */
	public void setPurgeBoxNum(String purgeBoxNum)
	{
	    this.purgeBoxNum = purgeBoxNum;
	}

	/**
	 * @return the purgeDate
	 */
	public String getPurgeDate()
	{
	    return purgeDate;
	}

	/**
	 * @param purgeDate the purgeDate to set
	 */
	public void setPurgeDate(String purgeDate)
	{
	    this.purgeDate = purgeDate;
	}

	/**
	 * @return the purgeSerNum
	 */
	public String getPurgeSerNum()
	{
	    return purgeSerNum;
	}

	/**
	 * @param purgeSerNum the purgeSerNum to set
	 */
	public void setPurgeSerNum(String purgeSerNum)
	{
	    this.purgeSerNum = purgeSerNum;
	}

	public String getCaseNotePart1()
	{
	    return caseNotePart1;
	}

	public void setCaseNotePart1(String caseNotePart1)
	{
	    this.caseNotePart1 = caseNotePart1;
	}

	public String getCaseNotePart2()
	{
	    return caseNotePart2;
	}

	public void setCaseNotePart2(String caseNotePart2)
	{
	    this.caseNotePart2 = caseNotePart2;
	}

	public Date getLcDate()
	{
	    return lcDate;
	}

	public void setLcDate(Date lcDate)
	{
	    this.lcDate = lcDate;
	}

	public Date getLcTime()
	{
	    return lcTime;
	}

	public void setLcTime(Date lcTime)
	{
	    this.lcTime = lcTime;
	}

	/**
	 * @return the lastReferral
	 */
	public String getLastReferral()
	{
	    return lastReferral;
	}

	/**
	 * @param lastReferral the lastReferral to set
	 */
	public void setLastReferral(String lastReferral)
	{
	    this.lastReferral = lastReferral;
	}

	/**
	 * @return the oldStatusId
	 */
	public String getOldStatusId()
	{
	    return oldStatusId;
	}

	/**
	 * @param oldStatusId the oldStatusId to set
	 */
	public void setOldStatusId(String oldStatusId)
	{
	    this.oldStatusId = oldStatusId;
	}

	public String getTSDSId()
	{
	    return TSDSId;
	}

	public void setTSDSId(String tSDSId)
	{
	    TSDSId = tSDSId;
	}

	public String getOriginalRaceId()
	{
	    return originalRaceId;
	}

	public void setOriginalRaceId(String originalRaceId)
	{
	    this.originalRaceId = originalRaceId;
	}

	public String getMasterStatusId()
	{
	    return masterStatusId;
	}

	public void setMasterStatusId(String masterStatusId)
	{
	    this.masterStatusId = masterStatusId;
	}

	public String getMasterStatus()
	{
	    return masterStatus;
	}

	public void setMasterStatus(String masterStaus)
	{
	    this.masterStatus = masterStaus;
	}
	public String getSealComments()
	{
	    return sealComments;
	}

	public void setSealComments(String sealComments)
	{
	    this.sealComments = sealComments;
	}

	public String getSealedDate()
	{
	    return sealedDate;
	}

	public void setSealedDate(String sealedDate)
	{
	    this.sealedDate = sealedDate;
	}

	public String getPreferredFirstName()
	{
	    return preferredFirstName;
	}

	public void setPreferredFirstName(String preferredFirstName)
	{
	    this.preferredFirstName = preferredFirstName;
	}

	public String getYouthDeathReason()
	{
	    return youthDeathReason;
	}

	public void setYouthDeathReason(String youthDeathReason)
	{
	    this.youthDeathReason = youthDeathReason;
	}
	
	public String getYouthDeathReasonDesc()
	{
	    return youthDeathReasonDesc;
	}
	
	public void setYouthDeathReasonDesc(String youthDeathReasonDesc)
	{
	    this.youthDeathReasonDesc = youthDeathReasonDesc;
	}
	
	public String getYouthDeathVerificationDesc()
	{
	    return youthDeathVerificationDesc;
	}
	
	public void setYouthDeathVerificationDesc(String youthDeathVerificationDesc)
	{
	    this.youthDeathVerificationDesc = youthDeathVerificationDesc;
	}

	public String getYouthDeathVerification()
	{
	    return youthDeathVerification;
	}

	public void setYouthDeathVerification(String youthDeathVerification)
	{
	    this.youthDeathVerification = youthDeathVerification;
	}

	public Date getDeathDate()
	{
	    return deathDate;
	}

	public void setDeathDate(Date deathDate)
	{
	    this.deathDate = deathDate;
	}

	public int getDeathAge()
	{
	    return deathAge;
	}

	public void setDeathAge(int deathAge)
	{
	    this.deathAge = deathAge;
	}
	public String getReleaseDate()
	{
	    return releaseDate;
	}

	public void setReleaseDate(String releaseDate)
	{
	    this.releaseDate = releaseDate;
	}

	public String getSprvsionTypeCd()
	{
	    return sprvsionTypeCd;
	}

	public void setSprvsionTypeCd(String sprvsionTypeCd)
	{
	    this.sprvsionTypeCd = sprvsionTypeCd;
	}

	public String getJpoPhoneNumber()
	{
	    return jpoPhoneNumber;
	}

	public void setJpoPhoneNumber(String jpoPhoneNumber)
	{
	    this.jpoPhoneNumber = jpoPhoneNumber;
	}

	public String getLocUnitName()
	{
	    return locUnitName;
	}

	public void setLocUnitName(String locUnitName)
	{
	    this.locUnitName = locUnitName;
	}

	public String getJuvUnitId()
	{
	    return juvUnitId;
	}

	public void setJuvUnitId(String juvUnitId)
	{
	    this.juvUnitId = juvUnitId;
	}
	public String getJpoEmail()
	{
	    return jpoEmail;
	}

	public void setJpoEmail(String jpoEmail)
	{
	    this.jpoEmail = jpoEmail;
	}

	public Collection<JuvenileCasefileResponseEvent> getCasefiles()
	{
	    return casefiles;
	}

	public void setCasefiles(Collection<JuvenileCasefileResponseEvent> casefiles)
	{
	    this.casefiles = casefiles;
	}
	
	public Date getRealDOB()
	{
	    return this.realDOB;
	} 
	    
	public void setRealDOB(Date realDOB)
	{
	    this.realDOB = realDOB;

	} 
	//task 173551
	public String getFloorNum()
	{
	    return floorNum;
	}

	public void setFloorNum(String floorNum)
	{
	    this.floorNum = floorNum;
	}
	public String getUnitNum()
	{
	    return unitNum;
	}

	public void setUnitNum(String unitNum)
	{
	    this.unitNum = unitNum;
	}
	public String getRoomNum()
	{
	    return roomNum;
	}

	public void setRoomNum(String roomNum)
	{
	    this.roomNum = roomNum;
	}
	public Date getNexthearingDate()
	{
	    return nexthearingDate;
	}

	public void setNexthearingDate(Date nexthearingDate)
	{
	    this.nexthearingDate = nexthearingDate;
	}

	public String getLiveWithTjjd()
	{
	    return liveWithTjjd;
	}

	public void setLiveWithTjjd(String liveWithTjjd)
	{
	    this.liveWithTjjd = liveWithTjjd;
	}

	public String getLiveWithTjjdDesc()
	{
	    return liveWithTjjdDesc;
	}

	public void setLiveWithTjjdDesc(String liveWithTjjdDesc)
	{
	    this.liveWithTjjdDesc = liveWithTjjdDesc;
	}
	public String getPurgeComments()
	{
	    return purgeComments;
	}

	public void setPurgeComments(String purgeComments)
	{
	    this.purgeComments = purgeComments;
	}

	public String getLatestCasefileId()
	{
	    return latestCasefileId;
	}

	public void setLatestCasefileId(String latestCasefileId)
	{
	    this.latestCasefileId = latestCasefileId;
	}
	
	public String getUpdateUser()
	{
	    return this.updateUser;
	}
	    
	public void setUpdateUser(String updateUser)
	{
	    this.updateUser = updateUser;
	}
	    
	public Date getUpdateDate()
	{
	    return this.updateDate;
	}
	    
	public void setUpdateDate(Date updateDate)
	{
	    this.updateDate = updateDate;
	}

	public String getActivityId()
	{
	    return activityId;
	}

	public void setActivityId(String activityId)
	{
	    this.activityId = activityId;
	}
	
	public String getLastActionBy()
	{
	    return this.lastActionBy;
	}

	public void setLastActionBy(String lastActionBy)
	{
	    this.lastActionBy = lastActionBy;
	}

	public int getJuvExcluded()
	{
	    return juvExcluded;
	}

	public void setJuvExcluded(int juvExcluded)
	{
	    this.juvExcluded = juvExcluded;
	}
	
	
	
} // end JuvenileProfileDetailResponseEvent
