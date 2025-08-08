/*
 * Project: JIMS
 * Class:   ui.juvenilecase.form.JuvenileMainForm
 *
 * Date:    2005-06-29
 *
 */

package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.SocialSecurity;
import ui.common.UIUtil;


/**
 * 
 * @author athorat
 */
public class JuvenileMainForm extends org.apache.struts.action.ActionForm
{
	// ------------------------------------------------------------------------
	// --- fields ---
	// ------------------------------------------------------------------------

	private boolean isAdopted = false ;
	private String adoptionComments = "" ;

	private String failedPlacements = "" ;

	private String firstName = "" ;
	private String middleName = "" ;
	private String lastName = "" ;
	private String nameSuffix = "" ;
	private String preferredFirstName="";
	
	private String countryId ;
	private String cityId ;
	private String stateId ;
	
	private String raceId ;
	private String originalRaceId;
	private String race ;

	private String hispanic ;
	private String multiracial ;

	private String dateOfBirth ;
	private String verifiedDOB = "N" ;
	private String birthCountyId ;
	private String isUSCitizenId ;
	private String USCitizenId ;

	private String ethnicityId ;
	private String nationalityId ;

	private String secondaryLanguageId ;
	private String primaryLanguageId ;

	private String driverLicenseNum ;
	private String driverLicenseStateId ;
	private String driverLicenseClass ;
	private String driverLicenseClassId ;
	private String driverLicenseExpireDate ;

	private SocialSecurity SSN ;
	private SocialSecurity currentSSN ;
	private boolean matchDetectedSSN = false ; // controls the pop up window

	private String SID ;
	private String SONum ;
	private String educationId ;
	//Changes for JIMS200077276 MJCW:  Add new field Student ID to TEA Rpt(UI)
	private String studentId;
	private String DHSNum ;
	private String PEIMSId ;
	private String TSDSId;
	private String alienNum ;
	private String FBINum ;

	private String naturalEyeColorId ;
	private String naturalHairColorId ;
	private String complexionId ;

	private Collection selectedTattoos ;

	private String [ ] selectedScarsAndMarks ;

	private String religionId ;

	private String DNASampleNum ;

	private String dateSenttoDPS ;

	private String sexId ;

	private String action ;

	private String detentionFacility ;
	private String detentionFacilityId ;
	private String detentionStatus ;
	private String detentionStatusId ;

	private boolean updatable = false ;
	private String selectedValue = "" ;

	private Collection matchingJuvs = new ArrayList( ) ;

	private boolean hasCasefile = false ;
	//Changes for JIMS200077276 
	private boolean hasFeature=false;
	
	//added for User Story #37391
	private Collection JISInfoList = new ArrayList();
	private JISInformation currentJISInfo = new JISInformation();
	private String[] selectedJISAgencies;
	private String otherAgencyComment;
	private List<String> selectedJISAgenciesList;
	/** Added for 39094**/
	private JuvenileFacilityHeaderResponseEvent juvHeaderDetails;
	private String facilityCodeDesc;
	private String facilityStatusDesc;
	/** Added for 39094**/
	
	//Added for US 39892
	private SocialSecurity completeSSN ;
	private String passportNumber;
	private String countryOfIssuance;
	private String countryOfIssuanceDesc;
	private String passportExpirationDate;
	private static List nationalityList;
	

	// ------------------------------------------------------------------------
	// --- methods ---
	// ------------------------------------------------------------------------

	public String getPassportExpirationDate()
	{
	    return passportExpirationDate;
	}

	public void setPassportExpirationDate(String passportExpirationDate)
	{
	    this.passportExpirationDate = passportExpirationDate;
	}

	public String getPassportNumber()
	{
	    return passportNumber;
	}

	public void setPassportNumber(String passportNumber)
	{
	    this.passportNumber = passportNumber;
	}

	public String getCountryOfIssuance()
	{
	    return countryOfIssuance;
	}

	public void setCountryOfIssuance(String countryOfIssuance)
	{
	    this.countryOfIssuance = countryOfIssuance;
	}
	
	public String getCountryOfIssuanceDesc()
	{
	    if ( this.countryOfIssuance != null 
		    && this.countryOfIssuance.length() > 0 ) {
		countryOfIssuanceDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.PLACE_OF_BIRTH, this.countryOfIssuance);
	    }
	    return countryOfIssuanceDesc;
	}

	public void setCountryOfIssuanceDesc(String countryOfIssuanceDesc)
	{
	    this.countryOfIssuanceDesc = countryOfIssuanceDesc;
	}



	public void clear( )
	{
		isAdopted = false ;
		juvHeaderDetails=null;
		facilityCodeDesc="";
		facilityCodeDesc="";
		failedPlacements = "" ;
		adoptionComments = "" ;
		countryId = null ;
		cityId = null ;
		stateId = null ;
		raceId = null ;
		dateOfBirth = null ;
		birthCountyId = null ;
		isUSCitizenId = null ;
		ethnicityId = null ;
		nationalityId = null ;
		secondaryLanguageId = null ;
		primaryLanguageId = null ;
		driverLicenseNum = null ;
		driverLicenseStateId = null ;
		SSN = null ;
		SID = "" ;
		SONum = null ;
		educationId = null;
		studentId=null;
		DHSNum = "" ;
		PEIMSId = null ;
		alienNum = "" ;
		FBINum = null ;
		naturalEyeColorId = null ;
		naturalHairColorId = null ;
		complexionId = null ;
		selectedTattoos = null ;
		selectedScarsAndMarks = null ;
		religionId = null ;
		DNASampleNum = null ;
		dateSenttoDPS = null ;
		sexId = null ;
		USCitizenId = null ;
		driverLicenseClassId = null ;
		driverLicenseExpireDate = null ;
		action = "" ;
		matchDetectedSSN = false ;
		matchingJuvs = new ArrayList( ) ;
		selectedValue = "" ;
		updatable = false ;
		hasCasefile = false ;
		hasFeature=false;
		currentJISInfo.otherAgency="";
		TSDSId = "";
		passportNumber="";
		countryOfIssuance="";
		passportExpirationDate=null;
		countryOfIssuanceDesc="";
		
		
		
	} //end of ui.juvenilecase.form.JuvenileMainForm.clear

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset( ActionMapping mapping, HttpServletRequest request )
	{
		Object obj = request.getAttribute( "clearProfileUpdateCheckBoxes" ) ;
		if( obj != null )
		{
			clearProfileUpdateCheckBoxes( false ) ;
		}

		obj = request.getParameter( "clearProfileUpdateCheckBoxes" ) ;
		if( obj != null )
		{
			clearProfileUpdateCheckBoxes( false ) ;
		}
	}

	/*
	 * @param defaultVal
	 */
	public void clearProfileUpdateCheckBoxes( boolean defaultVal )
	{
		//this.setHispanic( "N" ) ; //88256
		this.setMultiracial( "N" ) ;
		this.setVerifiedDOB( "N" ) ;
		this.setAdopted( false ) ;
	}

	/**
	 * 
	 * @return The country id.
	 */
	public String getCountryId( )
	{
		return countryId ;
	} //end of ui.juvenilecase.form.JuvenileMainForm.getCountryId

	/**
	 * 
	 * @param string
	 *            The country id.
	 */
	public void setCountryId( String string )
	{
		countryId = string ;
	} //end of ui.juvenilecase.form.JuvenileMainForm.setCountryId

	/**
	 * Returns the birth country.
	 * 
	 * @return The birth country.
	 */
	public String getBirthCountry( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.PLACE_OF_BIRTH, this.countryId ) ;
	}

	/**
	 * Returns the birth city.
	 * 
	 * @return The birth city.
	 */
	public String getBirthCity( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.CITY, this.cityId ) ;
	}

	/**
	 * Returns the birth state.
	 * 
	 * @return The birth state.
	 */
	public String getBirthState( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.STATE_ABBR, this.stateId ) ;
	}

	/**
	 * Returns the race.
	 * 
	 * @return The race.
	 */
	public String getRace( )
	{
		return race;
	}

	/**
	 * Returns the birth county.
	 * 
	 * @return The birth county.
	 */
	public String getBirthCounty( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.COUNTY, this.birthCountyId ) ;
	}

	/**
	 * Returns the primary citizenship.
	 * 
	 * @return The primary citizenship.
	 */
	public String getIsUSCitizen( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.ISUSCITIZEN, this.isUSCitizenId ) ;
	}

	/**
	 * Returns the ethnicity.
	 * 
	 * @return The ethnicity.
	 */
	public String getEthnicity( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.JUVENILE_ETHNICITY, this.ethnicityId ) ;
	}

	/**
	 * Returns the nationality.
	 * 
	 * @return The nationality.
	 */
	public String getNationality( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.PLACE_OF_BIRTH, this.nationalityId ) ;
	}

	/**
	 * Returns the secondary language.
	 * 
	 * @return The secondary language.
	 */
	public String getSecondaryLanguage( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.LANGUAGE, this.secondaryLanguageId ) ;
	}

	/**
	 * Returns the primary language.
	 * 
	 * @return The primary language.
	 */
	public String getPrimaryLanguage( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.LANGUAGE, this.primaryLanguageId ) ;
	}

	/**
	 * Returns the driver license state.
	 * 
	 * @return The driver license state.
	 */
	public String getDriverLicenseState( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.STATE_ABBR, this.driverLicenseStateId ) ;
	}

	/**
	 * Returns the natural eye color.
	 * 
	 * @return The natural eye color.
	 */
	public String getNaturalEyeColor( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.EYE_COLOR, this.naturalEyeColorId ) ;
	}

	/**
	 * Returns the natural hair color.
	 * 
	 * @return The natural hair color.
	 */
	public String getNaturalHairColor( )
	{
		return CodeHelper.getCodeDescription( PDCodeTableConstants.HAIR_COLOR, this.naturalHairColorId ) ;
	}

	/**
	 * Returns the complexion.
	 * 
	 * @return The complexion.
	 */
	public String getComplexion( )
	{
		return CodeHelper.getCodeDescription( PDCodeTableConstants.SKIN_TONE, this.complexionId ) ;
	}

	/**
	 * Returns the religion.
	 * 
	 * @return The religion.
	 */
	public String getReligion( )
	{
		return CodeHelper.getCodeDescription( PDCodeTableConstants.RELIGION, this.religionId ) ;
	}

	/**
	 * Returns the sex.
	 * 
	 * @return The sex.
	 */
	public String getSex( )
	{
		return CodeHelper.getCodeDescription( PDCodeTableConstants.JJS_SEX, this.sexId ) ;
	}

	/**
	 * 
	 * @return The cities.
	 */
	public Collection getCities( )
	{
		return CodeHelper.getCityCodes( ) ;
	}

	/**
	 * 
	 * @return The complexions.
	 */
	public Collection getComplexions( )
	{
		return CodeHelper.getComplexionCodes( true ) ;
	}

	/**
	 * 
	 * @return The countries.
	 */
	public Collection getCountries( )
	{
		return CodeHelper.getJuvenileCountryCodes( true ) ;
	}

	/**
	 * 
	 * @return The counties.
	 */
	public Collection getCounties( )
	{
		return CodeHelper.getCountyCodes( true ) ;
	}

	/**
	 * 
	 * @return The ethnicities.
	 */
	public Collection getEthnicities( )
	{
		return CodeHelper.getJuvenileEthnicityCodes( ) ;
	}

	/**
	 * 
	 * @return The eye colors.
	 */
	public Collection getEyeColors( )
	{
		return CodeHelper.getEyeColorCodes( ) ;
	}

	/**
	 * 
	 * @return The hair colors.
	 */
	public Collection getHairColors( )
	{
		return CodeHelper.getHairColorCodes( ) ;
	}

	/**
	 * 
	 * @return The languages.
	 */
	public Collection getLanguages( )
	{
		return CodeHelper.getLanguageCodes( ) ;
	}

	/**
	 * 
	 * @return The marital status types.
	 */
	public Collection getMaritalStatusTypes( )
	{
		return CodeHelper.getMaritalStatusCodes( ) ;
	}

	/**
	 * 
	 * @return The nationalities.
	 */
	public Collection getNationalities( )
	{
		return CodeHelper.getNationalityCodes( ) ;
	}

	/**
	 * 
	 * @return The isUSCitizen list
	 */
	public Collection getIsUSCitizenList( )
	{
		return CodeHelper.getIsUSCitizenList( ) ;
	}

	/**
	 * 
	 * @return The races.
	 */
	public Collection getRaces( )
	{
		return CodeHelper.getRaceCodes( true ) ;
	}

	/**
	 * 
	 * @return The religions.
	 */
	public Collection getReligions( )
	{
		return CodeHelper.getReligionCodes( true ) ;
	}

	/**
	 * 
	 * @return The sexes.
	 */
	public Collection getSexes( )
	{
		return CodeHelper.getJJSSexCodes( true ) ;
	}

	/**
	 * 
	 * @return The states.
	 */
	public Collection getStates( )
	{
		return CodeHelper.getStateCodes( ) ;
	}

	/**
	 * 
	 * @return The alien num.
	 */
	public String getAlienNum( )
	{
		return alienNum ;
	}

	/**
	 * 
	 * @return The city id.
	 */
	public String getCityId( )
	{
		return cityId ;
	}

	/**
	 * 
	 * @return The birth county id.
	 */
	public String getBirthCountyId( )
	{
		return birthCountyId ;
	}

	/**
	 * 
	 * @return The state id.
	 */
	public String getStateId( )
	{
		return stateId ;
	}

	/**
	 * 
	 * @return The complexion id.
	 */
	public String getComplexionId( )
	{
		return complexionId ;
	}

	/**
	 * 
	 * @return The date of birth.
	 */
	public String getDateOfBirth( )
	{
		return dateOfBirth ;
	}

	/**
	 * 
	 * @return The date sentto d p s.
	 */
	public String getDateSenttoDPS( )
	{
		return dateSenttoDPS ;
	}

	/**
	 * 
	 * @return The d h s num.
	 */
	public String getDHSNum( )
	{
		return DHSNum ;
	}

	/**
	 * 
	 * @return The d n a sample num.
	 */
	public String getDNASampleNum( )
	{
		return DNASampleNum ;
	}

	/**
	 * 
	 * @return The driver license num.
	 */
	public String getDriverLicenseNum( )
	{
		return driverLicenseNum ;
	}

	/**
	 * 
	 * @return The driver license state id.
	 */
	public String getDriverLicenseStateId( )
	{
		return driverLicenseStateId ;
	}

	/**
	 * 
	 * @return The ethnicity id.
	 */
	public String getEthnicityId( )
	{
		return ethnicityId ;
	}

	/**
	 * 
	 * @return The f b i num.
	 */
	public String getFBINum( )
	{
		return FBINum ;
	}

	/**
	 * 
	 * @return The first name.
	 */
	public String getFirstName( )
	{
		return firstName ;
	}

	/**
	 * 
	 * @return The hispanic.
	 */
	public String getHispanic( )
	{
		return hispanic ;
	}

	/**
	 * Returns the hispanic description.
	 * 
	 * @return The hispanic description.
	 */
	public String getHispanicDescription( )
	{
	    if(hispanic!=null && !hispanic.isEmpty()){
		return ( PDConstants.YES.equals( hispanic ) ) ? UIConstants.YES_FULL_TEXT : UIConstants.NO_FULL_TEXT ;
	    }else{
		return "";
	    }
	}

	/**
	 * 
	 * @return The last name.
	 */
	public String getLastName( )
	{
		return lastName ;
	}

	/**
	 * 
	 * @return The middle name.
	 */
	public String getMiddleName( )
	{
		return middleName ;
	}

	/**
	 * 
	 * @return The multiracial.
	 */
	public String getMultiracial( )
	{
		return multiracial ;
	}

	/**
	 * 
	 * @return The multiracial description.
	 */
	public String getMultiracialDescription( )
	{
		return ( PDConstants.YES.equals( multiracial ) ) ? UIConstants.YES_FULL_TEXT : UIConstants.NO_FULL_TEXT ;
	}

	/**
	 * 
	 * @return The nationality id.
	 */
	public String getNationalityId( )
	{
		return nationalityId ;
	}

	/**
	 * 
	 * @return The natural eye color id.
	 */
	public String getNaturalEyeColorId( )
	{
		return naturalEyeColorId ;
	}

	/**
	 * 
	 * @return The natural hair color id.
	 */
	public String getNaturalHairColorId( )
	{
		return naturalHairColorId ;
	}

	/**
	 * 
	 * @return The p e i m s id.
	 */
	public String getPEIMSId( )
	{
		return PEIMSId ;
	}

	/**
	 * 
	 * @return The primary citizenship id.
	 */
	public String getIsUSCitizenId( )
	{
		return isUSCitizenId ;
	}

	/**
	 * 
	 * @return The primary language id.
	 */
	public String getPrimaryLanguageId( )
	{
		return primaryLanguageId ;
	}

	/**
	 * 
	 * @return The race id.
	 */
	public String getRaceId( )
	{
		return raceId ;
	}

	/**
	 * 
	 * @return The religion id.
	 */
	public String getReligionId( )
	{
		return religionId ;
	}

	/**
	 * 
	 * @return selectted scars and marks
	 */
	public String [ ] getSelectedScarsAndMarks( )
	{
		return selectedScarsAndMarks ;
	}

	/**
	 * 
	 * @return The secondary language id.
	 */
	public String getSecondaryLanguageId( )
	{
		return secondaryLanguageId ;
	}

	/**
	 * 
	 * @return The sex id.
	 */
	public String getSexId( )
	{
		return sexId ;
	}

	/**
	 * 
	 * @return The s i d.
	 */
	public String getSID( )
	{
		return SID ;
	}

	/**
	 * 
	 * @return The s o num.
	 */
	public String getSONum( )
	{
		return SONum ;
	}

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
	public SocialSecurity getSSN( )
	{
		if( SSN == null )
		{
			return new SocialSecurity( null ) ;
		}
		else
		{
			return SSN ;
		}
	}

	/**
	 * 
	 * @return selected tattoos
	 */
	public Collection getTattoos( )
	{
		return selectedTattoos ;
	}

	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * 
	 * @return The verified d o b.
	 */
	public String getVerifiedDOB( )
	{
		return verifiedDOB ;
	}

	/**
	 * 
	 * @return The verified d o b description.
	 */
	public String getVerifiedDOBDescription( )
	{
		return ( PDConstants.YES.equals( verifiedDOB ) ) ? UIConstants.YES_FULL_TEXT : UIConstants.NO_FULL_TEXT ;
	}

	/**
	 * 
	 * @param string
	 *            The alien num.
	 */
	public void setAlienNum( String string )
	{
		alienNum = ( string != null ) ? string.trim( ) : "" ;
	}

	/**
	 * 
	 * @param string
	 *            The city id.
	 */
	public void setCityId( String string )
	{
		cityId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The birth county id.
	 */
	public void setBirthCountyId( String string )
	{
		birthCountyId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The state id.
	 */
	public void setStateId( String string )
	{
		stateId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The complexion id.
	 */
	public void setComplexionId( String string )
	{
		complexionId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The date of birth.
	 */
	public void setDateOfBirth( String string )
	{
		dateOfBirth = string ;
	}

	/**
	 * 
	 * @param string
	 *            The date sentto d p s.
	 */
	public void setDateSenttoDPS( String string )
	{
		dateSenttoDPS = string ;
	}

	/**
	 * 
	 * @param string
	 *            The d h s num.
	 */
	public void setDHSNum( String string )
	{
		DHSNum = ( string != null ) ? string.trim( ) : "" ;
	}
	
	/**
	 * 
	 * @param string
	 *            The d n a sample num.
	 */
	public void setDNASampleNum( String string )
	{
			DNASampleNum = ( string != null ) ? string.trim( ) : "" ;
	}

	/**
	 * 
	 * @param string
	 *            The driver license num.
	 */
	public void setDriverLicenseNum( String string )
	{
		driverLicenseNum = ( string != null ) ? string.trim( ) : "" ;
	}

	/**
	 * 
	 * @param string
	 *            The driver license state id.
	 */
	public void setDriverLicenseStateId( String string )
	{
		driverLicenseStateId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The ethnicity id.
	 */
	public void setEthnicityId( String string )
	{
		ethnicityId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The f b i num.
	 */
	public void setFBINum( String string )
	{
		FBINum = ( string != null ) ? string.trim( ) : "" ;
	}

	/**
	 * 
	 * @param string
	 *            The first name.
	 */
	public void setFirstName( String string )
	{
		firstName = string ;
	}

	/**
	 * 
	 * @param hispanic
	 *            The hispanic.
	 */
	public void setHispanic( String hispanic )
	{
		this.hispanic = hispanic ;
	}

	/**
	 * 
	 * @param string
	 *            The last name.
	 */
	public void setLastName( String string )
	{
		lastName = string ;
	}

	/**
	 * 
	 * @param string
	 *            The middle name.
	 */
	public void setMiddleName( String string )
	{
		middleName = string ;
	}
	

	public String getPreferredFirstName()
	{
	    return preferredFirstName;
	}

	public void setPreferredFirstName(String preferredFirstName)
	{
	    this.preferredFirstName = preferredFirstName;
	}

	/**
	 * 
	 * @param multiracial
	 *            The multiracial.
	 */
	public void setMultiracial( String multiracial )
	{
		this.multiracial = multiracial ;
	}

	/**
	 * 
	 * @param string
	 *            The nationality id.
	 */
	public void setNationalityId( String string )
	{
		nationalityId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The natural eye color id.
	 */
	public void setNaturalEyeColorId( String string )
	{
		naturalEyeColorId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The natural hair color id.
	 */
	public void setNaturalHairColorId( String string )
	{
		naturalHairColorId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The p e i m s id.
	 */
	public void setPEIMSId( String string )
	{
		PEIMSId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The primary language id.
	 */
	public void setPrimaryLanguageId( String string )
	{
		primaryLanguageId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The race id.
	 */
	public void setRaceId( String string )
	{
		raceId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The religion id.
	 */
	public void setReligionId( String string )
	{
		religionId = string ;
	}

	/**
	 * 
	 * @param collection
	 *            The scars and marks.
	 */
	public void setScarsAndMarks( Collection collection )
	{
		selectedScarsAndMarks = new String [ collection.size( ) ] ;
		Iterator iter = collection.iterator( ) ;
		for( int i = 0; iter.hasNext( ); i++ )
		{
			ScarsMarksTattoosCodeResponseEvent scarEvent = (ScarsMarksTattoosCodeResponseEvent)iter.next( ) ;
			selectedScarsAndMarks[ i ] = scarEvent.getCode( ) ;
		}
	}

	/**
	 * @param isUSCitizenId
	 *            The isUSCitizenId to set.
	 */
	public void setIsUSCitizenId( String isUSCitizenId )
	{
		this.isUSCitizenId = isUSCitizenId ;
	}

	/**
	 * 
	 * @param string
	 *            The secondary language id.
	 */
	public void setSecondaryLanguageId( String string )
	{
		secondaryLanguageId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The sex id.
	 */
	public void setSexId( String string )
	{
		sexId = string ;
	}

	/**
	 * 
	 * @param string
	 *            The s i d.
	 */
	public void setSID( String string )
	{
		SID = string ;
	}

	/**
	 * 
	 * @param string
	 *            The s o num.
	 */
	public void setSONum( String string )
	{
		SONum = (string != null) ? string.trim( ) : "" ;
	}

	/**
	 * @param educationId the educationId to set
	 */
	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}

	/**
	 * 
	 * @param ssn
	 *            The s s n.
	 */
	public void setSSN( SocialSecurity ssn )
	{
		SSN = ssn ;
	}

	/**
	 * 
	 * @param collection
	 *            selected tattos
	 */
	public void setTattoos( Collection collection )
	{
		selectedTattoos = collection ;
	}

	/**
	 * 
	 * @param verifiedDOB
	 *            The verified d o b.
	 */
	public void setVerifiedDOB( String verifiedDOB )
	{
		this.verifiedDOB = verifiedDOB ;
	}

	/**
	 * 
	 * @return ScarsAndMarksDescription
	 */
	public String getScarsAndMarksDescription( )
	{
		String scarsDescription = null ;
		if( this.selectedScarsAndMarks != null && this.selectedScarsAndMarks.length > 0 )
		{
			Collection selectedScars = CodeHelper.getSelectedCodeDescriptions( CodeHelper.getScarMarks( false ), this.selectedScarsAndMarks ) ;
			scarsDescription = UIUtil.separateByDelim( selectedScars, ", " ) ;
		}
		return scarsDescription ;
	}

	/**
	 * 
	 * @return The scars and marks types.
	 */
	public Collection getScarsAndMarksTypes( )
	{
		return CodeHelper.getScarMarks( true ) ;
	}

	/**
	 * 
	 * @param array
	 *            The selected scars and marks array.
	 */
	public void setSelectedScarsAndMarks( String [ ] array )
	{
		selectedScarsAndMarks = array ;
	}

	/**
	 * @param nameSuffix the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * 
	 * @return The tattoos types.
	 */
	public Collection getTattoosTypes( )
	{
		return CodeHelper.getTattoos( true ) ;
	}

	/**
	 * 
	 * @return The action.
	 */
	public String getAction( )
	{
		return action ;
	}

	/**
	 * 
	 * @param string
	 *            The action.
	 */
	public void setAction( String string )
	{
		action = string ;
	}

	/**
	 * 
	 * string juvenile name.
	 */
	public String getName( )
	{
		String fullName = this.getLastName( ) + ", " + this.getFirstName( ) + " " + this.getMiddleName( ) + " " + this.getNameSuffix();
		return fullName.trim();
	}

	/**
	 * @return
	 */
	public String getDriverLicenseClassId( )
	{
		return driverLicenseClassId ;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseClassId( String string )
	{
		driverLicenseClassId = string ;
	}

	/**
	 * @return
	 */
	public String getDriverLicenseClass( )
	{
		return CodeHelper.getFastCodeDescription( PDCodeTableConstants.DRIVERS_LICENSE_CLASS, this.driverLicenseClassId ) ;

	}

	/**
	 * @param string
	 */
	public void setDriverLicenseClass( String string )
	{
		driverLicenseClass = string ;
	}

	public Collection getDriverLicenseClasses( )
	{
		return CodeHelper.getDriverLicenseClassCodes( true ) ;
	}

	/**
	 * @return
	 */
	public String getDriverLicenseExpireDate( )
	{
		return driverLicenseExpireDate ;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseExpireDate( String string )
	{
		driverLicenseExpireDate = string ;
	}

	/**
	 * @return Returns the uSCitizenId.
	 */
	public String getUSCitizenId( )
	{
		return USCitizenId ;
	}

	/**
	 * @param citizenId
	 *            The uSCitizenId to set.
	 */
	public void setUSCitizenId( String citizenId )
	{
		USCitizenId = citizenId ;
	}

	/**
	 * @return Returns the detentionFacility.
	 */
	public String getDetentionFacility( )
	{
		return detentionFacility ;
	}

	/**
	 * @param detentionFacility The detentionFacility to set.
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
	 * @param detentionFacilityId The detentionFacilityId to set.
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
	 * @param detentionStatus The detentionStatus to set.
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
	 * @param detentionStatusId The detentionStatusId to set.
	 */
	public void setDetentionStatusId( String detentionStatusId )
	{
		this.detentionStatusId = detentionStatusId ;
	}

	/**
	 * @return Returns the matchDetectedSSN.
	 */
	public boolean isMatchDetectedSSN( )
	{
		return matchDetectedSSN ;
	}

	/**
	 * @param matchDetectedSSN The matchDetectedSSN to set.
	 */
	public void setMatchDetectedSSN( boolean matchDetectedSSN )
	{
		this.matchDetectedSSN = matchDetectedSSN ;
	}

	/**
	 * @return Returns the matchingJuvs.
	 */
	public Collection getMatchingJuvs( )
	{
		return matchingJuvs ;
	}

	/**
	 * @param matchingJuvs The matchingJuvs to set.
	 */
	public void setMatchingJuvs( Collection matchingJuvs )
	{
		this.matchingJuvs = matchingJuvs ;
	}

	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue( )
	{
		return selectedValue ;
	}

	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue( String selectedValue )
	{
		this.selectedValue = selectedValue ;
	}

	/**
	 * @return Returns the failedPlacements.
	 */
	public String getFailedPlacements( )
	{
		return failedPlacements ;
	}

	/**
	 * @param failedPlacements The failedPlacements to set.
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
	 * @param isAdopted The isAdopted to set.
	 */
	public void setAdopted( boolean isAdopted )
	{
		this.isAdopted = isAdopted ;
	}

	public String getAdoptedAsYesNo( )
	{
		return( isAdopted ? UIConstants.YES_FULL_TEXT : UIConstants.NO_FULL_TEXT ) ;
	}

	/**
	 * @return Returns the adoptionComments.
	 */
	public String getAdoptionComments( )
	{
		return adoptionComments ;
	}

	/**
	 * @param adoptionComments The adoptionComments to set.
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

	/**
	 * @return Returns the hasCasefile.
	 */
	public boolean isHasCasefile( )
	{
		return hasCasefile ;
	}

	/**
	 * @param hasCasefile The hasCasefile to set.
	 */
	public void setHasCasefile( boolean hasCasefile )
	{
		this.hasCasefile = hasCasefile ;
	}

	public SocialSecurity getCurrentSSN( )
	{
		return currentSSN ;
	}

	public void setCurrentSSN( SocialSecurity currentSSN )
	{
		this.currentSSN = currentSSN ;
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
	 * @param hasFeature the hasFeature to set
	 */
	public void setHasFeature(boolean hasFeature) {
		this.hasFeature = hasFeature;
	}

	/**
	 * @return the hasFeature
	 */
	public boolean isHasFeature() {
		return hasFeature;
	}

	public Collection getJISInfoList() {
		return JISInfoList;
	}

	public void setJISInfoList(Collection jISInfoList) {
		JISInfoList = jISInfoList;
	}

	public JISInformation getCurrentJISInfo() {
		return currentJISInfo;
	}

	public void setCurrentJISInfo(JISInformation currentJISInfo) {
		this.currentJISInfo = currentJISInfo;
	}

	public String[] getSelectedJISAgencies() {
		return selectedJISAgencies;
	}

	public void setSelectedJISAgencies(String[] selectedJISAgencies) {
		this.selectedJISAgencies = selectedJISAgencies;
	}

	public String getOtherAgencyComment() {
		return otherAgencyComment;
	}

	public void setOtherAgencyComment(String otherAgencyComment) {
		this.otherAgencyComment = otherAgencyComment;
	}

	public List<String> getSelectedJISAgenciesList() {
		return selectedJISAgenciesList;
	}

	public void setSelectedJISAgenciesList(List<String> selectedJISAgenciesList) {
		this.selectedJISAgenciesList = selectedJISAgenciesList;
	}



	/**
	 * @return the juvHeaderDetails
	 */
	public JuvenileFacilityHeaderResponseEvent getJuvHeaderDetails() {
		return juvHeaderDetails;
	}

	/**
	 * @param juvHeaderDetails the juvHeaderDetails to set
	 */
	public void setJuvHeaderDetails(JuvenileFacilityHeaderResponseEvent juvHeaderDetails) {
		this.juvHeaderDetails = juvHeaderDetails;
	}



	/**
	 * @return the facilityCodeDesc
	 */
	public String getFacilityCodeDesc() {
		return facilityCodeDesc;
	}

	/**
	 * @param facilityCodeDesc the facilityCodeDesc to set
	 */
	public void setFacilityCodeDesc(String facilityCodeDesc) {
		this.facilityCodeDesc = facilityCodeDesc;
	}



	/**
	 * @return the facilityStatusDesc
	 */
	public String getFacilityStatusDesc() {
		return facilityStatusDesc;
	}

	/**
	 * @param facilityStatusDesc the facilityStatusDesc to set
	 */
	public void setFacilityStatusDesc(String facilityStatusDesc) {
		this.facilityStatusDesc = facilityStatusDesc;
	}



	public SocialSecurity getCompleteSSN() {
		return completeSSN;
	}

	public void setCompleteSSN(SocialSecurity completeSSN) {
		this.completeSSN = completeSSN;
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



	public void setRace(String race)
	{
	    this.race = race;
	}
	
	public List getNationalityList()
	{
	    nationalityList = CodeHelper.getCodes(PDCodeTableConstants.PLACE_OF_BIRTH,true);
	    return nationalityList;
	}

	

	//added for User Story #37931
	public static class JISInformation implements Comparable{
		
		 private String entryDate;
		 
		 private String agency;
		 
		 private String otherAgency;
		 
		 private String comments;
		 
		 public String getEntryDate() {
			return entryDate;
		}

		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}

		public String getAgency() {
			return agency;
		}

		public void setAgency(String agency) {
			this.agency = agency;
		}

		public String getOtherAgency() {
			return otherAgency;
		}

		public void setOtherAgency(String otherAgency) {
			this.otherAgency = otherAgency;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}
		
		
		

		public int compareTo(Object obj)
		 {
			 if(obj==null)
				 return -1;
			 JISInformation infoEvt = (JISInformation)obj;
			
			 if(this.entryDate==null || infoEvt.getEntryDate()==null)
				 return 1;
			  Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
              Date incomingDate = DateUtil.stringToDate(infoEvt.getEntryDate(), UIConstants.DATE_FMT_1);
			 return currDate.compareTo(incomingDate);
			
			 
		 }
	}
}
