package pd.juvenile;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Reference;
import mojo.km.security.ISecurityManager;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;

import org.apache.commons.collections.FastArrayList;

import pd.codetable.Code;
import pd.codetable.person.ScarsMarksTattoosCode;
import pd.codetable.person.SocialElementCode;
import pd.contact.Employer;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.family.FamilyConstellation;
import pd.km.util.Formatter;
import pd.km.util.Name;

/**
 * Represents Juvenile data new to JIMS2 mapped to DB/2
 */
public class Juvenile extends JuvenileCore 
{
	private String birthCountryId;
	private String actualJuvenileNum;
	private String FBINumber;
	private String PEIMSId;
	private String TSDSId;

	private String juvenileCaseId;
	private Collection caseFiles;

	/**
	 * Properties for familyConstellations
	 * 
	 * @detailerDoNotGenerate false
	 * @referencedType pd.juvenileFamily.FamilyConstellation
	 */
	private Collection familyConstellationList;

	private String complexionId;

	private String alienNumber;
	private String driverLicenseNumber;
	private String driverLicenseClassId;
	private Date driverLicenseExpireDate;
	private String passportNum = ""; //added for passport
	private String countryOfIssuanceId; //added for passport
	private Date passportExpirationDate; //added for passport

	private Collection juvenileContactList;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey ETHNICITY
	 */
	public Code ethnicity = null;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey CITY
	 */
	public Code birthCity = null;

	/**
	 * Properties for currentEmployer
	 */
	private Employer currentEmployer = null;
	private String currentEmployerId;

	private JJSJuvenile jjsJuvInfo = null;

	private String birthCountyId;
	private String birthCityId;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey PLACE_OF_BIRTH_COUNTRY
	 */
	public Code birthCountry = null;

	private String natualEyeColorId;

	private String detentionFacilityId;
	private String detentionStatusId;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.person.SocialElementCode
	 * @contextKey pd.codetable.person.SocialElementCode
	 */
	public SocialElementCode religion = null;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey COUNTY
	 */
	public Code birthCounty = null;
	private String birthStateId;

	private boolean multiracial;
	private String religionId;

	private String naturalHairColorId;

	private String DHSNumber;
	private String SONumber;
	private String educationId;
	private String studentId; //Changes for Changes for JIMS200077276 
	private String SIDNumber;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey EYE_COLOR
	 */
	public Code natualEyeColor = null;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey LANGUAGE
	 */
	public Code secondaryLanguage = null;
	public Code isUSCitizen;
	private String isUSCitizenId;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey LANGUAGE
	 */
	public Code primaryLanguage = null;

	/**
	 * Properties for scarsAndMarks
	 * 
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.person.ScarsMarksTattoosCode
	 * @associationType simple
	 */
	private Collection scarsAndMarks = null;

	// private String juvenileNum;
	private String supervisionNumber;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey HAIR_COLOR
	 */
	public Code naturalHairColor = null;

	private String primaryLanguageId;

	private String DNASampleNumber;

	private boolean verifiedDOB;
	private String secondaryLanguageId;
	private String driverLicenseStateId;

	/**
	 * Properties for employers
	 */
	private Employer employers = null;
	private String employersId;

	private String hispanic; //U.S 88526

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey PLACE_OF_BIRTH
	 */
	public Code birthState = null;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey CITIZENSHIP
	 */
	public Code nationality = null;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey JUVENILE_PROFILE_STATUS
	 */
	// public Code status = null;
	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey MARITAL_STATUS
	 */
	private String ethnicityId;

	private Date dateSenttoDPS;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey STATE_ABBR
	 */
	public Code driverLicenseState = null;

	private String nationalityId;

	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey SKIN_TONE
	 */
	public Code complexion = null;

	private Collection benefits;
	private Collection insurances;

	private String birthCertficateRecordCD;
	private boolean adopted;

	private String failedPlacements;
	private String adoptionComments;
	private String aliasEntryDate;
	private String aliasNotes;
	
	private String juvenileType;
	private Collection aliasList;
	private boolean mentalHealthServices;
	
	//US 40492
	
	private String jpoOfRecId;

	public Collection getAliasList() {
	    fetch();
	    return aliasList;
	}

	public void setAliasList(Collection aliasList) {
		this.aliasList = aliasList;
	}
	
	/**
	 * @return the acutualJuvenileNum
	 */
	public String getActualJuvenileNum() {
	     fetch();
	     return actualJuvenileNum;
	}

	/**
	 * @param acutualJuvenileNum the acutualJuvenileNum to set
	 */
	public void setActualJuvenileNum(String acutualJuvenileNum) {
		this.actualJuvenileNum = acutualJuvenileNum;
	}
	
	/**
	 * @roseuid 42A882800157
	 */
	public Juvenile() {
	}

	/**
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this table.
	 * @param juvNum
	 * @return Juvenile.
	 * @return pd.juvenilecase.Juvenile
	 */
	static public Juvenile find(String juvNum) 
	{
		Juvenile juvenile = null;
		SearchJuvenileProfilesEvent pEvent = new SearchJuvenileProfilesEvent();

		pEvent.setJuvenileNum(juvNum);
		
		/*Iterator iter = Juvenile.findAll(pEvent);
		if (iter.hasNext()) 
		{
			juvenile = (Juvenile) iter.next();
		}*/
		
		
		    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    	    boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	    	    Iterator iter;
	    	    if(grantedFeature)
	    		iter = Juvenile.findAll(pEvent);
	    	    else
	    		iter =  filterSealedPurged(Juvenile.findAll(pEvent));
        	    if (iter.hasNext()) 
        	    {
        		juvenile = (Juvenile) iter.next();
        	    }
		
		return juvenile;
	}
	
	/**
	 * @return
	 * @param Juvenile
	 */
	static public Juvenile findDb2Juvenile(String juvNum)
	{
		return (Juvenile) new Home().find(juvNum, Juvenile.class);
	}

	/**
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this table.
	 * @param juvNum
	 * @return Juvenile.
	 * @return pd.juvenilecase.Juvenile
	 */
	/*static public Juvenile findJCJuvenile(String juvNum) {
		
		Juvenile juv = (Juvenile) new Home().find(juvNum, Juvenile.class);
		return juv;
	}*/
	

	static public Juvenile findJCJuvenile(String juvNum) 
	{
	    	IHome home = new Home();
		Juvenile juv = (Juvenile) new Home().find(juvNum, Juvenile.class);
		if (juv != null && (juv.getRecType() == null || juv.getRecType().equalsIgnoreCase("JUVENILE")))
		    return juv;
		else
		{
		    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		    boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
		    if (grantedFeature)
			return juv;
		    else
			return null;
		}		
	}
	/**
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this table.
	 * @param juvNum
	 * @return Juvenile.
	 * @return pd.juvenilecase.Juvenile
	 */
	public static JuvenileProfileDetailResponseEvent findDetailJuvenile(String juvNum) 
	{
		Juvenile juvenile = null;
		JuvenileProfileDetailResponseEvent response = null;
		SearchJuvenileProfilesEvent pEvent = new SearchJuvenileProfilesEvent();
		pEvent.setJuvenileNum(juvNum);
		
		Iterator iter = Juvenile.findAll(pEvent);
		if (iter != null && iter.hasNext()) 
		{
			juvenile = (Juvenile) iter.next();
			response = juvenile.getCoreJuvenileProfileResponse();
		}
		
		/*if (juvenile != null) 
		{
			Juvenile juv = (Juvenile) Juvenile.findJCJuvenile(juvNum);
			
			if (juv != null) 
			{
				juv.setFirstName(juvenile.getFirstName());
				juv.setLastName(juvenile.getLastName());
				juv.setMiddleName(juvenile.getMiddleName());
				juv.setSexId(juvenile.getSexId());
				juv.setRaceId(juvenile.getRaceId());
				juv.setHispanic(juvenile.getHispanic()); //U.S 88526
				juv.setDateOfBirth(juvenile.getDateOfBirth());
				juv.setSSN(juvenile.getSSN());
				juv.setRecType( juvenile.getRecType() );
				juvenile = juv;
			}
		}*/
		
		return response;
	}

	
	
	
	public String getAliasEntryDate() {
		fetch();
		return aliasEntryDate;
	}

	public void setAliasEntryDate(String aliasEntryDate) {
		if (this.aliasEntryDate == null) {
			markModified();
		}
		this.aliasEntryDate = aliasEntryDate;
	}

	public String getAliasNotes() {
		fetch();
		return aliasNotes;
	}

	public void setAliasNotes(String aliasNotes) {
		if (this.aliasNotes == null || !this.aliasNotes.equals(aliasNotes)) {
			markModified();
		}
		this.aliasNotes = aliasNotes;
	}

	public String getJuvenileType() {
		fetch();
		return juvenileType;
	}

	public void setJuvenileType(String juvenileType) {
		if (this.juvenileType == null || !this.juvenileType.equals(juvenileType)) {
			markModified();
		}
		this.juvenileType = juvenileType;
	}

	/**
	 * Access method for the juvenileNum property.
	 * 
	 * @roseuid 42A882800204
	 * @return the current value of the juvenileNum property
	 */
	public String getJuvenileNum() {
	    return getOID();
	}

	/**
	 * Sets the value of the juvenileNum property.
	 * 
	 * @roseuid 42A882800213
	 * @param aJuvenileNum
	 *            the new value of the juvenileNum property
	 */
	public void setJuvenileNum(String aJuvenileNum) {
		setOID(aJuvenileNum);
	}

	/**
	 * @param searchEvent
	 * @return
	 */
        static public Iterator findAll(IEvent event)
            {
        	/*IHome home = new Home();
        	return home.findAll(event, Juvenile.class);*/
        	//BUG 174317 changes STARTS (commented above code as well)
        	Juvenile juvenile = null;
        	IHome home = new Home();
        	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
        	Iterator iter;
        	if (grantedFeature)
        	    return home.findAll(event, Juvenile.class);
        	else
        	    return filterSealedPurged(home.findAll(event, Juvenile.class));
            }
            //BUG 174317 changes ENDS

	/**
	 * @return
	 */
	public String getAlienNumber() {
		fetch();
		return alienNumber;
	}

	/**
	 * @return
	 */
	public Date getDateSenttoDPS() {
		fetch();
		return dateSenttoDPS;
	}

	/**
	 * @return
	 */
	public String getDHSNumber() {
		fetch();
		return DHSNumber;
	}

	/**
	 * @return
	 */
	public String getDNASampleNumber() {
		fetch();
		return DNASampleNumber;
	}

	/**
	 * @return
	 */
	public String getFBINumber() {
		fetch();
		return FBINumber;
	}


	

	/**
	 * @return
	 */
	public boolean isMultiracial() {
		fetch();
		return multiracial;
	}

	/**
	 * @return
	 */
	public String getPEIMSId() {
		fetch();
		return PEIMSId;
	}

	/**
	 * @return
	 */
	public String getSIDNumber() {
		fetch();
		return SIDNumber;
	}

	/**
	 * @return
	 */
	public String getSONumber() {
		fetch();
		return SONumber;
	}

	/**
	 * @return the educationId
	 */
	public String getEducationId() {
		fetch();
	    return educationId;
	}

	/**
	 * @return
	 */
	public String getSupervisionNumber() {
		fetch();
		return supervisionNumber;
	}

	/**
	 * @return
	 */
	public boolean isVerifiedDOB() {
		fetch();
		return verifiedDOB;
	}

	/**
	 * @param date
	 */
	public void setDateSenttoDPS(Date date) {
		if (this.dateSenttoDPS == null || !this.dateSenttoDPS.equals(date)) {
			markModified();
		}
		dateSenttoDPS = date;
	}

	/**
	 * @param string
	 */
	public void setDHSNumber(String string) {
		if (this.DHSNumber == null || !this.DHSNumber.equals(string)) {
			markModified();
		}
		DHSNumber = string;
	}

	/**
	 * @param string
	 */
	public void setDNASampleNumber(String string) {
		if (this.DNASampleNumber == null
				|| !this.DNASampleNumber.equals(string)) {
			markModified();
		}
		DNASampleNumber = string;
	}

	/**
	 * @param educationId the educationId to set
	 */
	public void setEducationId(String string) {
		if (this.educationId == null || !this.educationId.equals(string)) {
			markModified();
		}
		this.educationId = string;
	}

	/**
	 * @param string
	 */
	public void setFBINumber(String string) {
		if (this.FBINumber == null || !this.FBINumber.equals(string)) {
			markModified();
		}
		FBINumber = string;
	}

	/**
	 * @param b
	 */
	public void setHispanic(String b) { //U.S 88526
		if (this.hispanic != b) {
			markModified();
		}
		hispanic = b;
	}

	/**
	 * @return the hispanic
	 * //U.S 88526
	 */
	public String getHispanic()
	{
	    fetch();
	    return hispanic;
	}

	/**
	 * @param b
	 */
	public void setMultiracial(boolean b) {
		if (this.multiracial != b) {
			markModified();
		}
		multiracial = b;
	}

	/**
	 * @param string
	 */
	public void setPEIMSId(String string) {
		if (this.PEIMSId == null || !this.PEIMSId.equals(string)) {
			markModified();
		}
		PEIMSId = string;
	}

	/**
	 * @param string
	 */
	public void setSIDNumber(String string) {
		if (this.SIDNumber == null || !this.SIDNumber.equals(string)) {
			markModified();
		}
		SIDNumber = string;
	}

	/**
	 * @param string
	 */
	public void setSONumber(String string) {
		if (this.SONumber == null || !this.SONumber.equals(string)) {
			markModified();
		}
		SONumber = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNumber(String string) {
		if (this.supervisionNumber == null
				|| !this.supervisionNumber.equals(string)) {
			markModified();
		}
		supervisionNumber = string;
	}

	/**
	 * @param b
	 */
	public void setVerifiedDOB(boolean b) {
		if (this.verifiedDOB != b) {
			markModified();
		}
		verifiedDOB = b;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setEthnicityId(String ethnicityId) {
		if (this.ethnicityId == null || !this.ethnicityId.equals(ethnicityId)) {
			markModified();
		}
		ethnicity = null;
		this.ethnicityId = ethnicityId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getEthnicityId() {
		fetch();
		return ethnicityId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initEthnicity() {
		if (ethnicity == null) {
			try {
				ethnicity = (Code) new mojo.km.persistence.Reference(
						ethnicityId, Code.class, PDCodeTableConstants.ETHNICITY)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getEthnicity() {
		fetch();
		initEthnicity();
		return ethnicity;
	}

	/**
	 * set the type reference for class member ethnicity
	 */
	public void setEthnicity(Code ethnicity) {
		if (this.ethnicity == null || !this.ethnicity.equals(ethnicity)) {
			markModified();
		}
		if (ethnicity.getOID() == null) {
			new mojo.km.persistence.Home().bind(ethnicity);
		}
		setEthnicityId("" + ethnicity.getOID());
		ethnicity.setContext(PDCodeTableConstants.ETHNICITY);
		this.ethnicity = (Code) new mojo.km.persistence.Reference(
				ethnicity).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setBirthCityId(String birthCityId) {
		if (this.birthCityId == null || !this.birthCityId.equals(birthCityId)) {
			markModified();
		}
		birthCity = null;
		this.birthCityId = birthCityId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getBirthCityId() {
		fetch();
		return birthCityId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initBirthCity() {
		if (birthCity == null) {
			try {
				birthCity = (Code) new mojo.km.persistence.Reference(
						birthCityId, Code.class, "CITY")
						.getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getBirthCity() {
		fetch();
		initBirthCity();
		return birthCity;
	}

	/**
	 * set the type reference for class member birthCity
	 */
	public void setBirthCity(Code birthCity) {
		if (this.birthCity == null || !this.birthCity.equals(birthCity)) {
			markModified();
		}
		if (birthCity.getOID() == null) {
			new mojo.km.persistence.Home().bind(birthCity);
		}
		setBirthCityId("" + birthCity.getOID());
		birthCity.setContext("CITY");
		this.birthCity = (Code) new mojo.km.persistence.Reference(
				birthCity).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.Employer
	 */
	public void setCurrentEmployerId(String currentEmployerId) {
		if (this.currentEmployerId == null
				|| !this.currentEmployerId.equals(currentEmployerId)) {
			markModified();
		}
		currentEmployer = null;
		this.currentEmployerId = currentEmployerId;
	}

	/**
	 * Get the reference value to class :: pd.contact.Employer
	 */
	public String getCurrentEmployerId() {
		fetch();
		return currentEmployerId;
	}

	private void initJjsJuvInfo() {

		if (jjsJuvInfo == null) {
			jjsJuvInfo = (JJSJuvenile) new Reference(getOID(),
					JJSJuvenile.class).getObject();
		}
	}

	public JJSJuvenile getJjsJuvInfo() {
		fetch();
		initJjsJuvInfo();
		return jjsJuvInfo;
	}

	public void setJjsJuvInfo(JJSJuvenile aJJSInfo) {
		if (this.jjsJuvInfo == null || !this.jjsJuvInfo.equals(aJJSInfo)) {
			markModified();
		}
		if (jjsJuvInfo.getOID() == null) {
			new mojo.km.persistence.Home().bind(aJJSInfo);
		}
		this.jjsJuvInfo = (JJSJuvenile) new mojo.km.persistence.Reference(
				jjsJuvInfo).getObject();
	}

	/**
	 * Initialize class relationship to class pd.contact.Employer
	 */
	private void initCurrentEmployer() {
		if (currentEmployer == null) {
			currentEmployer = (Employer) new mojo.km.persistence.Reference(
					currentEmployerId, Employer.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.contact.Employer
	 */
	public Employer getCurrentEmployer() {
		fetch();
		initCurrentEmployer();
		return currentEmployer;
	}

	/**
	 * set the type reference for class member currentEmployer
	 */
	public void setCurrentEmployer(Employer currentEmployer) {
		if (this.currentEmployer == null
				|| !this.currentEmployer.equals(currentEmployer)) {
			markModified();
		}
		if (currentEmployer.getOID() == null) {
			new mojo.km.persistence.Home().bind(currentEmployer);
		}
		setCurrentEmployerId("" + currentEmployer.getOID());
		this.currentEmployer = (Employer) new mojo.km.persistence.Reference(
				currentEmployer).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setIsUSCitizenId(String isUSCitizenId) {
		if (this.isUSCitizenId == null
				|| !this.isUSCitizenId.equals(isUSCitizenId)) {
			markModified();
		}
		isUSCitizen = null;
		this.isUSCitizenId = isUSCitizenId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getIsUSCitizenId() {
		fetch();
		return isUSCitizenId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initIsUSCitizen() {
		if (isUSCitizen == null) {
			try {
				isUSCitizen = (Code) new mojo.km.persistence.Reference(
						isUSCitizenId, Code.class, "IS_US_CITIZEN")
						.getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getIsUSCitizen() {
		fetch();
		initIsUSCitizen();
		return isUSCitizen;
	}

	/**
	 * set the type reference for class member IsUSCitizen
	 */
	public void setIsUSCitizen(Code isUSCitizen) {
		if (this.isUSCitizen == null || !this.isUSCitizen.equals(isUSCitizen)) {
			markModified();
		}
		if (isUSCitizen.getOID() == null) {
			new mojo.km.persistence.Home().bind(isUSCitizen);
		}
		setIsUSCitizenId("" + isUSCitizen.getOID());
		isUSCitizen.setContext("IS_US_CITIZEN");
		this.isUSCitizen = (Code) new mojo.km.persistence.Reference(
				isUSCitizen).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setBirthCountryId(String birthCountryId) {
		if (this.birthCountryId == null
				|| !this.birthCountryId.equals(birthCountryId)) {
			markModified();
		}
		birthCountry = null;
		this.birthCountryId = birthCountryId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getBirthCountryId() {
		fetch();
		return birthCountryId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initBirthCountry() {
		if (birthCountry == null) {
			try {
				birthCountry = (Code) new mojo.km.persistence.Reference(
						birthCountryId, Code.class,
						PDCodeTableConstants.PLACE_OF_BIRTH).getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getBirthCountry() {
		fetch();
		initBirthCountry();
		return birthCountry;
	}

	/**
	 * set the type reference for class member birthCountry
	 */
	public void setBirthCountry(Code birthCountry) {
		if (this.birthCountry == null
				|| !this.birthCountry.equals(birthCountry)) {
			markModified();
		}
		if (birthCountry.getOID() == null) {
			new mojo.km.persistence.Home().bind(birthCountry);
		}
		setBirthCountryId("" + birthCountry.getOID());
		birthCountry.setContext(PDCodeTableConstants.PLACE_OF_BIRTH);
		this.birthCountry = (Code) new mojo.km.persistence.Reference(
				birthCountry).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setBirthCountyId(String birthCountyId) {
		if (this.birthCountyId == null
				|| !this.birthCountyId.equals(birthCountyId)) {
			markModified();
		}
		birthCounty = null;
		this.birthCountyId = birthCountyId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getBirthCountyId() {
		fetch();
		return birthCountyId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initBirthCounty() {
		if (birthCounty == null) {
			try {
				birthCounty = (Code) new mojo.km.persistence.Reference(
						birthCountyId, Code.class, PDCodeTableConstants.JUVENILE_COUNTY)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getBirthCounty() {
		fetch();
		initBirthCounty();
		return birthCounty;
	}

	/**
	 * set the type reference for class member birthCounty
	 */
	public void setBirthCounty(Code birthCounty) {
		if (this.birthCounty == null || !this.birthCounty.equals(birthCounty)) {
			markModified();
		}
		if (birthCounty.getOID() == null) {
			new mojo.km.persistence.Home().bind(birthCounty);
		}
		setBirthCountyId("" + birthCounty.getOID());
		birthCounty.setContext(PDCodeTableConstants.COUNTY);
		this.birthCounty = (Code) new mojo.km.persistence.Reference(
				birthCounty).getObject();
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getReligionId() {
		fetch();
		return religionId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setNatualEyeColorId(String natualEyeColorId) {
		if (this.natualEyeColorId == null
				|| !this.natualEyeColorId.equals(natualEyeColorId)) {
			markModified();
		}
		natualEyeColor = null;
		this.natualEyeColorId = natualEyeColorId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getNatualEyeColorId() {
		fetch();
		return natualEyeColorId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initNatualEyeColor() {
		if (natualEyeColor == null) {
			try {
				natualEyeColor = (Code) new mojo.km.persistence.Reference(
						natualEyeColorId, Code.class, PDCodeTableConstants.EYE_COLOR)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getNatualEyeColor() {
		fetch();
		initNatualEyeColor();
		return natualEyeColor;
	}

	/**
	 * set the type reference for class member natualEyeColor
	 */
	public void setNatualEyeColor(Code natualEyeColor) {
		if (this.natualEyeColor == null
				|| !this.natualEyeColor.equals(natualEyeColor)) {
			markModified();
		}
		if (natualEyeColor.getOID() == null) {
			new mojo.km.persistence.Home().bind(natualEyeColor);
		}
		setNatualEyeColorId("" + natualEyeColor.getOID());
		natualEyeColor.setContext(PDCodeTableConstants.EYE_COLOR);
		this.natualEyeColor = (Code) new mojo.km.persistence.Reference(
				natualEyeColor).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setSecondaryLanguageId(String secondaryLanguageId) {
		if (this.secondaryLanguageId == null
				|| !this.secondaryLanguageId.equals(secondaryLanguageId)) {
			markModified();
		}
		secondaryLanguage = null;
		this.secondaryLanguageId = secondaryLanguageId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSecondaryLanguageId() {
		fetch();
		return secondaryLanguageId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSecondaryLanguage() {
		if (secondaryLanguage == null) {
			try {
				secondaryLanguage = (Code) new mojo.km.persistence.Reference(
						secondaryLanguageId, Code.class,
						PDCodeTableConstants.LANGUAGE).getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getSecondaryLanguage() {
		fetch();
		initSecondaryLanguage();
		return secondaryLanguage;
	}

	/**
	 * set the type reference for class member secondaryLanguage
	 */
	public void setSecondaryLanguage(Code secondaryLanguage) {
		if (this.secondaryLanguage == null
				|| !this.secondaryLanguage.equals(secondaryLanguage)) {
			markModified();
		}
		if (secondaryLanguage.getOID() == null) {
			new mojo.km.persistence.Home().bind(secondaryLanguage);
		}
		setSecondaryLanguageId("" + secondaryLanguage.getOID());
		secondaryLanguage.setContext(PDCodeTableConstants.LANGUAGE);
		this.secondaryLanguage = (Code) new mojo.km.persistence.Reference(
				secondaryLanguage).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setPrimaryLanguageId(String primaryLanguageId) {
		if (this.primaryLanguageId == null
				|| !this.primaryLanguageId.equals(primaryLanguageId)) {
			markModified();
		}
		primaryLanguage = null;
		this.primaryLanguageId = primaryLanguageId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getPrimaryLanguageId() {
		fetch();
		return primaryLanguageId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initPrimaryLanguage() {
		if (primaryLanguage == null) {
			try {
				primaryLanguage = (Code) new mojo.km.persistence.Reference(
						primaryLanguageId, Code.class, PDCodeTableConstants.LANGUAGE)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getPrimaryLanguage() {
		fetch();
		initPrimaryLanguage();
		return primaryLanguage;
	}

	/**
	 * set the type reference for class member primaryLanguage
	 */
	public void setPrimaryLanguage(Code primaryLanguage) {
		if (this.primaryLanguage == null
				|| !this.primaryLanguage.equals(primaryLanguage)) {
			markModified();
		}
		if (primaryLanguage.getOID() == null) {
			new mojo.km.persistence.Home().bind(primaryLanguage);
		}
		setPrimaryLanguageId("" + primaryLanguage.getOID());
		primaryLanguage.setContext(PDCodeTableConstants.LANGUAGE);
		this.primaryLanguage = (Code) new mojo.km.persistence.Reference(
				primaryLanguage).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setNaturalHairColorId(String naturalHairColorId) {
		if (this.naturalHairColorId == null
				|| !this.naturalHairColorId.equals(naturalHairColorId)) {
			markModified();
		}
		naturalHairColor = null;
		this.naturalHairColorId = naturalHairColorId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getNaturalHairColorId() {
		fetch();
		return naturalHairColorId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initNaturalHairColor() {
		if (naturalHairColor == null) {
			try {
				naturalHairColor = (Code) new mojo.km.persistence.Reference(
						naturalHairColorId, Code.class,
						PDCodeTableConstants.HAIR_COLOR).getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getNaturalHairColor() {
		fetch();
		initNaturalHairColor();
		return naturalHairColor;
	}

	/**
	 * set the type reference for class member naturalHairColor
	 */
	public void setNaturalHairColor(Code naturalHairColor) {
		if (this.naturalHairColor == null
				|| !this.naturalHairColor.equals(naturalHairColor)) {
			markModified();
		}
		if (naturalHairColor.getOID() == null) {
			new mojo.km.persistence.Home().bind(naturalHairColor);
		}
		setNaturalHairColorId("" + naturalHairColor.getOID());
		naturalHairColor.setContext(PDCodeTableConstants.HAIR_COLOR);
		this.naturalHairColor = (Code) new mojo.km.persistence.Reference(
				naturalHairColor).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.Employer
	 */
	public void setEmployersId(String employersId) {
		if (this.employersId == null || !this.employersId.equals(employersId)) {
			markModified();
		}
		employers = null;
		this.employersId = employersId;
	}

	/**
	 * Get the reference value to class :: pd.contact.Employer
	 */
	public String getEmployersId() {
		fetch();
		return employersId;
	}

	/**
	 * Initialize class relationship to class pd.contact.Employer
	 */
	private void initEmployers() {
		if (employers == null) {
			employers = (Employer) new mojo.km.persistence.Reference(
					employersId, Employer.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.contact.Employer
	 */
	public Employer getEmployers() {
		fetch();
		initEmployers();
		return employers;
	}

	/**
	 * set the type reference for class member employers
	 */
	public void setEmployers(Employer employers) {
		if (this.employers == null || !this.employers.equals(employers)) {
			markModified();
		}
		if (employers.getOID() == null) {
			new mojo.km.persistence.Home().bind(employers);
		}
		setEmployersId("" + employers.getOID());
		this.employers = (Employer) new mojo.km.persistence.Reference(
				employers).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setBirthStateId(String birthStateId) {
		if (this.birthStateId == null
				|| !this.birthStateId.equals(birthStateId)) {
			markModified();
		}
		birthState = null;
		this.birthStateId = birthStateId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getBirthStateId() {
		fetch();
		return birthStateId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initBirthState() {
		if (birthState == null) {
			try {
				birthState = (Code) new mojo.km.persistence.Reference(
						birthStateId, Code.class, PDCodeTableConstants.PLACE_OF_BIRTH)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getBirthState() {
		fetch();
		initBirthState();
		return birthState;
	}

	/**
	 * set the type reference for class member birthState
	 */
	public void setBirthState(Code birthState) {
		if (this.birthState == null || !this.birthState.equals(birthState)) {
			markModified();
		}
		if (birthState.getOID() == null) {
			new mojo.km.persistence.Home().bind(birthState);
		}
		setBirthStateId("" + birthState.getOID());
		birthState.setContext(PDCodeTableConstants.PLACE_OF_BIRTH);
		this.birthState = (Code) new mojo.km.persistence.Reference(
				birthState).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setNationalityId(String nationalityId) {
		if (this.nationalityId == null
				|| !this.nationalityId.equals(nationalityId)) {
			markModified();
		}
		nationality = null;
		this.nationalityId = nationalityId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getNationalityId() {
		fetch();
		return nationalityId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initNationality() {
		if (nationality == null) {
			try {
				nationality = (Code) new mojo.km.persistence.Reference(
						nationalityId, Code.class,
						PDCodeTableConstants.PLACE_OF_BIRTH).getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getNationality() {
		fetch();
		initNationality();
		return nationality;
	}

	/**
	 * set the type reference for class member nationality
	 */
	public void setNationality(Code nationality) {
		if (this.nationality == null || !this.nationality.equals(nationality)) {
			markModified();
		}
		if (nationality.getOID() == null) {
			new mojo.km.persistence.Home().bind(nationality);
		}
		setNationalityId("" + nationality.getOID());
		nationality.setContext(PDCodeTableConstants.PLACE_OF_BIRTH);
		this.nationality = (Code) new mojo.km.persistence.Reference(
				nationality).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setDriverLicenseStateId(String driverLicenseStateId) {
		if (this.driverLicenseStateId == null
				|| !this.driverLicenseStateId.equals(driverLicenseStateId)) {
			markModified();
		}
		driverLicenseState = null;
		this.driverLicenseStateId = driverLicenseStateId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDriverLicenseStateId() {
		fetch();
		return driverLicenseStateId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDriverLicenseState() {
		if (driverLicenseState == null) {
			try {
				driverLicenseState = (Code) new mojo.km.persistence.Reference(
						driverLicenseStateId, Code.class,
						PDCodeTableConstants.STATE_ABBR).getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getDriverLicenseState() {
		fetch();
		initDriverLicenseState();
		return driverLicenseState;
	}

	/**
	 * set the type reference for class member driverLicenseState
	 */
	public void setDriverLicenseState(Code driverLicenseState) {
		if (this.driverLicenseState == null
				|| !this.driverLicenseState.equals(driverLicenseState)) {
			markModified();
		}
		if (driverLicenseState.getOID() == null) {
			new mojo.km.persistence.Home().bind(driverLicenseState);
		}
		setDriverLicenseStateId("" + driverLicenseState.getOID());
		driverLicenseState.setContext(PDCodeTableConstants.STATE_ABBR);
		this.driverLicenseState = (Code) new mojo.km.persistence.Reference(
				driverLicenseState).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setComplexionId(String complexionId) {
		if (this.complexionId == null
				|| !this.complexionId.equals(complexionId)) {
			markModified();
		}
		complexion = null;
		this.complexionId = complexionId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getComplexionId() {
		fetch();
		return complexionId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initComplexion() {
		if (complexion == null) {
			try {
				complexion = (Code) new mojo.km.persistence.Reference(
						complexionId, Code.class, PDCodeTableConstants.SKIN_TONE)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getComplexion() {
		fetch();
		initComplexion();
		return complexion;
	}

	/**
	 * set the type reference for class member complexion
	 */
	public void setComplexion(Code complexion) {
		if (this.complexion == null || !this.complexion.equals(complexion)) {
			markModified();
		}
		if (complexion.getOID() == null) {
			new mojo.km.persistence.Home().bind(complexion);
		}
		setComplexionId("" + complexion.getOID());
		complexion.setContext(PDCodeTableConstants.SKIN_TONE);
		this.complexion = (Code) new mojo.km.persistence.Reference(
				complexion).getObject();
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.codetable.person.ScarsMarksTattoosCode
	 */
	private void initScarsAndMarks() {
		if (scarsAndMarks == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				scarsAndMarks = new mojo.km.persistence.ArrayList(
						JuvenileScarsAndMarksScarsMarksTattoosCode.class,
						"parentId", "" + this.getOID());
			} catch (Throwable t) {
				scarsAndMarks = new java.util.ArrayList();
			}
		}
	}

	/**
	 * returns a collection of pd.codetable.person.ScarsMarksTattoosCode
	 */
	public List getScarsAndMarks() {
		fetch();
		initScarsAndMarks();
		List retVal = new FastArrayList();
		Iterator i = scarsAndMarks.iterator();
		while (i.hasNext()) {
			JuvenileScarsAndMarksScarsMarksTattoosCode actual = (JuvenileScarsAndMarksScarsMarksTattoosCode) i
					.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}

	/**
	 * insert a pd.codetable.person.ScarsMarksTattoosCode into class
	 * relationship collection.
	 */
	public void insertScarsAndMarks(
			ScarsMarksTattoosCode anObject) {
		initScarsAndMarks();
		JuvenileScarsAndMarksScarsMarksTattoosCode actual = new JuvenileScarsAndMarksScarsMarksTattoosCode();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParentId(this.getJuvenileNum());
		scarsAndMarks.add(actual);
	}

	/**
	 * Removes a pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 */
	public void removeScarsAndMarks(
			ScarsMarksTattoosCode anObject) {
		initScarsAndMarks();
		try {
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			JuvenileScarsAndMarksScarsMarksTattoosCode actual = (JuvenileScarsAndMarksScarsMarksTattoosCode) new mojo.km.persistence.Reference(
					assocEvent,
					JuvenileScarsAndMarksScarsMarksTattoosCode.class)
					.getObject();
			scarsAndMarks.remove(actual);
		} catch (Throwable t) {
		}
	}

	/**
	 * Clears all pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 */
	public void clearScarsAndMarks() {
		initScarsAndMarks();
		scarsAndMarks.clear();
	}

	/**
	 * @return
	 */
	public String getDriverLicenseNumber() {
		fetch();
		return driverLicenseNumber;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseNumber(String string) {
		if (this.driverLicenseNumber == null
				|| !this.driverLicenseNumber.equals(string)) {
			markModified();
		}
		driverLicenseNumber = string;
	}

	/**
	 * @param string
	 */
	public void setAlienNumber(String string) {
		if (this.alienNumber == null || !this.alienNumber.equals(string)) {
			markModified();
		}
		alienNumber = string;
	}

	/**
	 * @param collection
	 */
	public void setScarsAndMarks(Collection collection) {
		if (this.scarsAndMarks == null
				|| !this.scarsAndMarks.equals(collection)) {
			markModified();
		}
		if (scarsAndMarks != null)
			scarsAndMarks.clear();
		if (collection != null) {
			Iterator iter = collection.iterator();
			while (iter.hasNext()) {
				ScarsMarksTattoosCode scarCode = (ScarsMarksTattoosCode) iter
						.next();
				this.insertScarsAndMarks(scarCode);
			}
		}
	}

	/**
	 * Set the reference value to class :: pd.codetable.person.SocialElementCode
	 */
	public void setReligionId(String religionId) {
		if (this.religionId == null || !this.religionId.equals(religionId)) {
			markModified();
		}
		religion = null;
		this.religionId = religionId;
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.person.SocialElementCode
	 */
	private void initReligion() {
		if (religion == null) {
			try {
				if (religionId != null) {
					StringBuffer derivedOid = new StringBuffer();
					derivedOid.append(Formatter.pad(
							PDCodeTableConstants.SOCIAL_ELEMENT_RELIGION, 4,
							'0', true));
					derivedOid.append(Formatter.pad(religionId, 4, '0', true));
					religion = (SocialElementCode) new mojo.km.persistence.Reference(
							derivedOid.toString(),
							SocialElementCode.class,
							"pd.codetable.person.SocialElementCode")
							.getObject();
				}
			} catch (Throwable t) {
				religionId = null;
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.person.SocialElementCode
	 */
	public SocialElementCode getReligion() {
		fetch();
		initReligion();
		return religion;
	}

	/**
	 * set the type reference for class member religion
	 */
	public void setReligion(SocialElementCode religion) {
		if (this.religion == null || !this.religion.equals(religion)) {
			markModified();
		}
		if (religion.getOID() == null) {
			new mojo.km.persistence.Home().bind(religion);
		}
		setReligionId("" + religion.getOID());
		religion.setContext("pd.codetable.person.SocialElementCode");
		this.religion = (SocialElementCode) new mojo.km.persistence.Reference(
				religion).getObject();
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenileFamily.FamilyConstellation
	 */
	private void initFamilyConstellationList() {
		if (familyConstellationList == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			familyConstellationList = new mojo.km.persistence.ArrayList(
					FamilyConstellation.class,
					"juvenileId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenileFamily.FamilyConstellation
	 * Returns only active family constellation
	 */
	public FamilyConstellation getCurrentFamilyConstellation() {
		Iterator fams = getFamilyConstellationList().iterator();
		while (fams.hasNext()) {
			FamilyConstellation fam = (FamilyConstellation) fams.next();
			if (fam.isActive()) {
				return fam;
			}
		}
		return null;
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenileFamily.FamilyConstellation
	 */
	private void initJuvenileContactList() {
		if (juvenileContactList == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			juvenileContactList = new mojo.km.persistence.ArrayList(
					JuvenileContact.class, "juvenileId", ""
							+ getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenile.JuvenileContact
	 */
	public Collection getJuvenileContactList() {
		initJuvenileContactList();
		return juvenileContactList;
	}

	/**
	 * returns a collection of pd.juvenileFamily.FamilyConstellation
	 * Returns all family constellations
	 */
	public Collection getFamilyConstellationList() {
		initFamilyConstellationList();
		return familyConstellationList;
	}

	/**
	 * insert a pd.juvenileFamily.FamilyConstellation into class relationship
	 * collection.
	 */
	public void insertFamilyConstellationList(
			FamilyConstellation anObject) {
		initFamilyConstellationList();
		anObject.setJuvenileId(this.getJuvenileNum());
		familyConstellationList.add(anObject);
	}

	/**
	 * Removes a pd.juvenileFamily.FamilyConstellation from class relationship
	 * collection.
	 */
	public void removeFamilyConstellationList(
			FamilyConstellation anObject) {
		initFamilyConstellationList();
		anObject.setJuvenileId(this.getJuvenileNum());
		familyConstellationList.remove(anObject);
	}

	/**
	 * Clears all pd.juvenileFamily.FamilyConstellation from class relationship
	 * collection.
	 */
	public void clearFamilyConstellationList() {
		initFamilyConstellationList();
		familyConstellationList.clear();
	}

	/**
	 * @param string
	 * @return
	 */
	private String getRaceCodeDescription(String string) {
		if (string != null && !string.equals("")) {
			Code raceCode = this.getRace();
			if (raceCode != null) {
				return raceCode.getDescription();
			}
		}
		return "";
	}

	/**
	 * @param string
	 * @return
	 */
	private String getSexCodeDescription(String string) {
		if (string != null && !string.equals("")) {
			Code sexCode = this.getSex();
			if (sexCode != null) {
				return sexCode.getDescription();
			}
		}
		return "";
	}

	public CompositeResponse getScarsMarksResponse() {
		CompositeResponse scarsAndMarks = new CompositeResponse();
		List codes = this.getScarsAndMarks();
		int len = codes.size();
		for (int i = 0; i < len; i++) {
			ScarsMarksTattoosCode code = (ScarsMarksTattoosCode) codes.get(i);
			scarsAndMarks.addResponse(code.valueObject());
		}
		return scarsAndMarks;
	}

	public List getScarsMarksForDetailResponse() {
		List scarsAndMarksRet = new FastArrayList();
		List codes = this.getScarsAndMarks();
		int len = codes.size();
		for (int i = 0; i < len; i++) {
			ScarsMarksTattoosCode code = (ScarsMarksTattoosCode) codes.get(i);
			ScarsMarksTattoosCodeResponseEvent scarEvent = new ScarsMarksTattoosCodeResponseEvent();
			scarEvent.setCategory(code.getCategory());
			scarEvent.setCode(code.getCode());
			scarEvent.setDescription(code.getDescription());
			scarsAndMarksRet.add(scarEvent);
		}
		return scarsAndMarksRet;
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenile.JuvenileBenefit
	 */
	private void initBenefits() {
		if (benefits == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			benefits = new mojo.km.persistence.ArrayList(
					JuvenileBenefit.class, "juvenileNum", ""
							+ getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenile.JuvenileBenefit
	 */
	public Collection getBenefits() {
		initBenefits();
		return benefits;
	}

	/**
	 * insert a pd.juvenile.JuvenileBenefit into class relationship collection.
	 */
	public void insertBenefits(JuvenileBenefit anObject) {
		initBenefits();
		benefits.add(anObject);
	}

	/**
	 * Removes a pd.juvenile.JuvenileBenefit from class relationship collection.
	 */
	public void removeBenefits(JuvenileBenefit anObject) {
		initBenefits();
		benefits.remove(anObject);
	}

	/**
	 * Clears all pd.juvenile.JuvenileBenefit from class relationship
	 * collection.
	 */
	public void clearBenefits() {
		initBenefits();
		benefits.clear();
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenile.JuvenileInsurance
	 */
	private void initInsurances() {
		if (insurances == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			insurances = new mojo.km.persistence.ArrayList(
					JuvenileInsurance.class, "juvenileNum", ""
							+ getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenile.JuvenileInsurance
	 */
	public Collection getInsurances() {
		initInsurances();
		return insurances;
	}

	/**
	 * insert a pd.juvenile.JuvenileInsurance into class relationship
	 * collection.
	 */
	public void insertInsurance(JuvenileInsurance anObject) {
		initInsurances();
		insurances.add(anObject);
	}

	/**
	 * Removes a pd.juvenile.JuvenileInsurance from class relationship
	 * collection.
	 */
	public void removeInsurances(JuvenileInsurance anObject) {
		initInsurances();
		insurances.remove(anObject);
	}

	/**
	 * Clears all pd.juvenile.JuvenileInsurance from class relationship
	 * collection.
	 */
	public void clearInsurances() {
		initInsurances();
		insurances.clear();
	}

	/**
	 * @return
	 */
	public String getJuvenileCasesId() {
		fetch();
		return juvenileCaseId;
	}

	/**
	 * @param string
	 */
	public void setJuvenileCasesId(String string) {
		if (this.juvenileCaseId == null || !this.juvenileCaseId.equals(string)) {
			markModified();
		}
		this.juvenileCaseId = string;
	}

	/**
	 * returns a collection of pd.juvenilecase.JuvenileCasefile
	 * 
	 * @return java.util.Collection
	 * @roseuid 4107DFB603A1
	 */
	public Collection getCaseFiles() {
		fetch();
		initCaseFiles();
		return caseFiles;
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenilecase.JuvenileCasefile
	 * 
	 * @roseuid 4107DFB60397
	 */
	private void initCaseFiles() {
		if (caseFiles == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				caseFiles = new mojo.km.persistence.ArrayList(
						pd.juvenilecase.JuvenileCasefile.class, "juvenileId",
						"" + getOID());
			} catch (Throwable t) {
				caseFiles = new java.util.ArrayList();
			}
		}
	}

	/**
	 * @return
	 */
	public String getDriverLicenseClassId() {
		fetch();
		return this.driverLicenseClassId;
	}

	/**
	 * @return
	 */
	public String getPassportNum() {
		fetch();
		return this.passportNum;
	}

	/**
	 * @return
	 */
	public String getCountryOfIssuanceId() {
		fetch();
		return this.countryOfIssuanceId; 
	}
	/**
	 * @return
	 */
	public Date getPassportExpirationDate() {
		fetch();
		return this.passportExpirationDate; 
	}


	/**
	 * @return
	 */
	public Date getDriverLicenseExpireDate() {
		fetch();
		return this.driverLicenseExpireDate;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseClassId(String string) {
		if (this.driverLicenseClassId == null
				|| !this.driverLicenseClassId.equals(string)) {
			markModified();
		}
		this.driverLicenseClassId = string;
	}
	
	/**
	 * @param string
	 */
	public void setPassportNum (String string) {
		if (this.passportNum  == null
				|| !this.passportNum .equals(string)) {
			markModified();
		}
		this.passportNum  = string;
	}

	/**
	 * @param string
	 */
	public void setCountryOfIssuanceId(String string) {
		if (this.countryOfIssuanceId == null
				|| !this.countryOfIssuanceId.equals(string)) {
			markModified();
		}
		this.countryOfIssuanceId = string;
	}
	

	/**
	 * @param date
	 */
	public void setPassportExpirationDate(Date date) {
		if (this.passportExpirationDate == null
				|| !this.passportExpirationDate.equals(date)) {
			markModified();
		}
		this.passportExpirationDate = date;
	}
	
	/**
	 * @param date
	 */
	public void setDriverLicenseExpireDate(Date date) {
		if (this.driverLicenseExpireDate == null
				|| !this.driverLicenseExpireDate.equals(date)) {
			markModified();
		}
		this.driverLicenseExpireDate = date;
	}

	/**
	 * @return Returns the birthCertficateRecordCD.
	 */
	public String getBirthCertficateRecordCD() {
		fetch();
	    return this.birthCertficateRecordCD;
	}

	/**
	 * @param birthCertficateRecordCD
	 *            The birthCertficateRecordCD to set.
	 */
	public void setBirthCertficateRecordCD(String birthCertficateRecordCD) {
		this.birthCertficateRecordCD = birthCertficateRecordCD;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDetentionFacilityId() {
		fetch();
		return this.detentionFacilityId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDetentionStatusId() {
		fetch();
		return this.detentionStatusId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setDetentionFacilityId(String theDetentionFacilityId) {
		if (this.detentionFacilityId == null
				|| !this.detentionFacilityId.equals(theDetentionFacilityId)) {
			markModified();
		}
		this.detentionFacilityId = theDetentionFacilityId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setDetentionStatusId(String theDetentionStatusId) {
		if (this.detentionStatusId == null
				|| !this.detentionStatusId.equals(theDetentionStatusId)) {
			markModified();
		}
		this.detentionStatusId = theDetentionStatusId;
	}

	public JuvenileProfileDetailResponseEvent getJuvenileProfileResponse() {
		JuvenileProfileDetailResponseEvent response = this
				.getCoreJuvenileProfileResponse();
		return getNonCoreJuvenileProfileResponse(response, this);
	}

	public JuvenileProfileDetailResponseEvent getNonCoreJuvenileProfileResponse(
			JuvenileProfileDetailResponseEvent response, Juvenile juv) {
		response.setAlienNumber(juv.getAlienNumber());
		response.setBirthCity(juv.getBirthCityId());
		response.setBirthCountry(juv.getBirthCountryId());
		response.setBirthCounty(juv.getBirthCountyId());
		response.setBirthState(juv.getBirthStateId());
		response.setComplexion(juv.getComplexionId());
		response.setDateSenttoDPS(juv.getDateSenttoDPS());
		response.setDHSNumber(juv.getDHSNumber());
		response.setDNASampleNumber(juv.getDNASampleNumber());
		response.setDriverLicenseNumber(juv.getDriverLicenseNumber());
		response.setDriverLicenseState(juv.getDriverLicenseStateId());
		response.setEthnicity(juv.getEthnicityId());
		response.setFBINumber(juv.getFBINumber());
		response.setHispanic(juv.getHispanic()); //U.S 88526
		response.setMultiracial(juv.isMultiracial());
		response.setNationality(juv.getNationalityId());
		response.setNatualEyeColor(juv.getNatualEyeColorId());
		response.setNaturalHairColor(juv.getNaturalHairColorId());
		response.setPEIMSId(juv.getPEIMSId());
		response.setIsUSCitizen(juv.getIsUSCitizenId());
		response.setPrimaryLanguage(juv.getPrimaryLanguageId());
		response.setReligion(juv.getReligionId());
		response.setSecondaryLanguage(juv.getSecondaryLanguageId());
		response.setSID(juv.getSIDNumber());
		response.setSONumber(juv.getSONumber());
		response.setVerifiedDOB(juv.isVerifiedDOB());
		// response.setStatus(juv.getStatusId());
		response.setDriverLicenseClassId(juv.getDriverLicenseClassId());
		response.setDriverLicenseExpireDate(juv.getDriverLicenseExpireDate());
		response.setPassportNum (juv.getPassportNum ()); //added for passport details
		response.setCountryOfIssuanceId(juv.getCountryOfIssuanceId()); //added for passport details
		response.setPassportExpirationDate(juv.getPassportExpirationDate()); //added for passport details
		response.setAdopted(juv.isAdopted());
		response.setFailedPlacements(juv.getFailedPlacements());
		response.setAdoptionComments(juv.getAdoptionComments());
		response.setScarsAndMarks(getScarsMarksForDetailResponse());
		if (juv != null && juv.getJuvenileNum() != null) {
			JJSJuvenile myJJSInfo = juv.getJjsJuvInfo();
			if (myJJSInfo != null) {
				response.setDetentionStatusId(myJJSInfo.getDetentionStatusId());
				//Defect 53065 - JJS only displays facility if there is a status.  Modified this code to do the same.
				if (myJJSInfo.getDetentionStatusId() != null && !myJJSInfo.getDetentionStatusId().equals("")) {
					response.setDetentionFacilityId(myJJSInfo.getDetentionFacilityId());
				}

			}
		}
		return response;
	}

	public JuvenileProfileDetailResponseEvent getCoreJuvenileProfileResponse() {
		JuvenileProfileDetailResponseEvent response = new JuvenileProfileDetailResponseEvent();
		response.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_DETAIL_TOPIC);
		response.setFirstName(this.getFirstName());
		response.setJuvenileNum(this.getJuvenileNum());
		response.setLastName(this.getLastName());
		response.setMiddleName(this.getMiddleName());
		response.setRaceId(this.getOriginalRaceId());
		response.setRace(this.getRaceCodeDescription(this.getRaceId()));
		response.setSex(this.getSexCodeDescription(this.getSexId()));
		response.setSexId(this.getSexId());
		response.setDateOfBirth(this.getDateOfBirth());
		response.setHispanic(this.getHispanic());
		response.setRecType( this.getRecType());
		//  06/08/2012 - revised max age from 18 to 20 per ER 71590
		if (this.getAgeInYears(this.getDateOfBirth()) >= 21) {
			response.setUpdatable(false);
		} else {
			response.setUpdatable(true);
		}
		response.setSSN(this.getSSN());
		Name name = new Name(response.getFirstName(), response.getMiddleName(),
				response.getLastName());
		response.setFormattedName(name.getFormattedName());
		return response;
	}

	/**
	 * @return Returns the adopted.
	 */
	public boolean isAdopted() {
		fetch();
		return adopted;
	}

	/**
	 * @param adopted
	 *            The adopted to set.
	 */
	public void setAdopted(boolean adopted) {
		if (this.adopted != adopted) {
			markModified();
		}
		this.adopted = adopted;
	}

	/**
	 * @return Returns the failedPlacements.
	 */
	public String getFailedPlacements() {
		fetch();
		return failedPlacements;
	}

	/**
	 * @param failedPlacements
	 *            The failedPlacements to set.
	 */
	public void setFailedPlacements(String failedPlacements) {
		if (this.failedPlacements == null
				|| !this.failedPlacements.equals(failedPlacements)) {
			markModified();
		}
		this.failedPlacements = failedPlacements;
	}

	/**
	 * @return Returns the adoptionComments.
	 */
	public String getAdoptionComments() {
		fetch();
		return adoptionComments;
	}

	/**
	 * @param adoptionComments
	 *            The adoptionComments to set.
	 */
	public void setAdoptionComments(String adoptionComments) {
		if (this.adoptionComments == null
				|| !this.adoptionComments.equals(adoptionComments)) {
			markModified();
		}
		this.adoptionComments = adoptionComments;
	}

	/**
	 * Sets the value of the dateOfBirth property.
	 * 
	 * @roseuid 42A882800196
	 * @param aDateOfBirth
	 *            the new value of the dateOfBirth property
	 */
	public void setDateOfBirth(Date aDateOfBirth) {
		// please do not add markModified method here,
		// also do not remove them, they are overridden to serve some perpose
		dateOfBirth = aDateOfBirth;
	}

	/**
	 * Sets the value of the firstName property.
	 * 
	 * @roseuid 42A8828001B6
	 * @param aFirstName
	 *            the new value of the firstName property
	 */
	public void setFirstName(String aFirstName) {
		// please do not add markModified method here,
		// also do not remove them, they are overridden to serve some perpose
		firstName = aFirstName;
	}

	/**
	 * Sets the value of the lastName property.
	 * 
	 * @roseuid 42A8828001D4
	 * @param aLastName
	 *            the new value of the lastName property
	 */
	public void setLastName(String aLastName) {
		// please do not add markModified method here,
		// also do not remove them, they are overridden to serve some perpose
		lastName = aLastName;
	}

	/**
	 * Sets the value of the middleName property.
	 * 
	 * @roseuid 42A8828001F4
	 * @param aMiddleName
	 *            the new value of the middleName property
	 */
	public void setMiddleName(String aMiddleName) {
		// please do not add markModified method here,
		// also do not remove them, they are overridden to serve some perpose
		middleName = aMiddleName;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @roseuid 42A882800262
	 * @param theRaceId
	 */
	public void setRaceId(String theRaceId) {
		// please do not add markModified method here,
		// also do not remove them, they are overridden to serve some perpose
		if (theRaceId != null && theRaceId.equals("L")) {
		    if(this.originalRaceId==null)          //Bug #92235
			theRaceId = "W";
		    else
			this.raceId = this.originalRaceId; //Bug #92235
		  this.setHispanic("Y"); //U.S 88526
		}
		this.raceId = theRaceId;
	}
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @roseuid 42A882800262
	 * @param originalRaceId
	 * Added for Bug # 92235
	 */
	public void setOriginalRaceId(String originalRaceId) {
		// please do not add markModified method here,
		// also do not remove them, they are overridden to serve some purpose
		if (this.raceId != null && ( this.raceId.equals("L") || (this.raceId.equals("W") && this.hispanic!=null && this.hispanic.equalsIgnoreCase("Y"))
			))		
			this.raceId = originalRaceId;		   
		this.originalRaceId = originalRaceId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @roseuid 42A8828002AF
	 * @param theSexId
	 */
	public void setSexId(String theSexId) {
		// please do not add markModified method here,
		// also do not remove them, they are overridden to serve some perpose
		this.sexId = theSexId;
	}

	public boolean isMentalHealthServices() {
		return mentalHealthServices;
	}

	public void setMentalHealthServices(boolean b) {
		if (this.mentalHealthServices != b) {
			markModified();
		}
		this.mentalHealthServices = b;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		if (this.studentId == null || !this.studentId.equals(studentId)) {
			markModified();
		}
		this.studentId = studentId;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		fetch();
		return studentId;
	}

	public String getJpoOfRecId() {
		fetch();
		return jpoOfRecId;
	}

	public void setJpoOfRecId(String jpoOfRecId) {
		if (this.jpoOfRecId == null || !this.jpoOfRecId.equals(jpoOfRecId)) {
			markModified();
		}
		this.jpoOfRecId = jpoOfRecId;
	}

	public String getTSDSId()
	{
	    return TSDSId;
	}

	public void setTSDSId(String tSDSId)
	{
	    TSDSId = tSDSId;
	}

}
