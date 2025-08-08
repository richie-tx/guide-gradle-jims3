/*
 * Created on Apr 25, 2005
 */

package messaging.juvenile.reply;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import naming.UIConstants  ;

import mojo.km.messaging.ResponseEvent;

/**
 * Returns a thinly populated version of a casefile referral for displaying
 * only needed data required data.  
 * 
 * @author glyons
 */
public class JJSOffenseResponseEvent extends ResponseEvent implements Comparable
{
	private String juvenileNum;
	private String referralNum;
	private String sequenceNum;
	private Date offenseDate;
	private String offenseDescription;
	private String catagory;
	private String citationCode;
	private String citationSource;
	private String offenseCodeId;
	private String offenseCode;   //added for user story 14257 get the offense code
	private String offenseLevelId;
	private String petitionAllegationId;
	private String referralAndCategoryDescription ;
	
	//Fields added for updated Assigned Referrals pages
	private String investigationNum;
	//Fields added for School Adjudication Notification
	private String severitySubtype;
	private String offenseCategoryDescription;
	private String petitionNumber;

	private String offenseReportGroup;
	
	private int offenseLevel = (-1) ;
	
	//Added for common app user-story 11029
	private String dispositionCode="";
	private Date dispositionDate;
	
	//US 71173
	private String keyMapLocation;
	//US 70523
	private String offDate;
	private String offenseStreetNum;
	private String offenseStreetName;
	private String offenseAptNum;
	private String offenseCity;
	private String offenseState;
	private String offenseZip;
	private String weaponType;
	private String cjisNum;	
	private String arrestDate;
	private String arrestTime;
	private String lcUser;	
	private Date lcDate;
	private String lcTime;
	private String chargeSequenceNum;
	private String offenseSeverity;	
	private String OID;
	private String oldoffenseCode;
	private String onCampOffense;
	private String onCampDistrict;
	private String onCampSchool;
	private String recType;
	private String ncicCode;
	

	

	//
	/**
	 * @return Returns the category.
	 */
	public String getCatagory() {
		return catagory;
	}
	
	/**
	 * @param catagory The catagory to set.
	 */
	public void setCatagory(String catagory) 
	{
		this.catagory = catagory;
		
		/* since the category is getting set, 
		 * let's go ahead and do the level enum
		 */
		if( catagory != null  && catagory.length() > 0 )
		{ 
			for( int i = 0; i < UIConstants.OFFENSE_LEVEL_STR_ARRAY.length; i++ )
			{
				if( catagory.equalsIgnoreCase( UIConstants.OFFENSE_LEVEL_STR_ARRAY[i] ))
				{
					offenseLevel = i ;
					break ;
				}
			}
		}
	}

	/**
	 * @return Returns the citationCode.
	 */
	public String getCitationCode() {
		return citationCode;
	}
	/**
	 * @param citationCode The citationCode to set.
	 */
	public void setCitationCode(String citationCode) {
		this.citationCode = citationCode;
	}
	/**
	 * @return Returns the citationSource.
	 */
	public String getCitationSource() {
		return citationSource;
	}
	/**
	 * @param citationSource The citationSource to set.
	 */
	public void setCitationSource(String citationSource) {
		this.citationSource = citationSource;
	}
	/**
	 * @return string
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return offenseDate
	 */
	public Date getOffenseDate()
	{
		return offenseDate;
	}
	
	public String getOffenseDateFormatted()
	{
		if (this.offenseDate != null) 
		{			
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			return formatter.format(this.offenseDate);
		}
		return "";
	}

	/**
	 * @return offenseDescription
	 */
	public String getOffenseDescription()
	{
		return offenseDescription;
	}

	/**
	 * @return referralNum
	 */
	public String getReferralNum()
	{
		return referralNum;
	}

	/**
	 * @return sequenceNum
	 */
	public String getSequenceNum()
	{
		return sequenceNum;
	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param offenseDate
	 */
	public void setOffenseDate(Date date)
	{
		offenseDate = date;
	}

	/**
	 * @param offenseDescription
	 */
	public void setOffenseDescription(String string)
	{
		offenseDescription = string;
	}

	/**
	 * @param referralNum
	 */
	public void setReferralNum(String string)
	{
		referralNum = string;
	}

	/**
	 * @param sequenceNum
	 */
	public void setSequenceNum(String string)
	{
		sequenceNum = string;
	}

	/**
	 * @return Returns the investigationNum.
	 */
	public String getInvestigationNum() {
		return investigationNum;
	}
	/**
	 * @param investigationNum The investigationNum to set.
	 */
	public void setInvestigationNum(String investigationNum) {
		this.investigationNum = investigationNum;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o == null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.offenseDate==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
		JJSOffenseResponseEvent evt = (JJSOffenseResponseEvent)o;
		return evt.getOffenseDate().compareTo(offenseDate);
	}
	
	public static Comparator OffenseComparator = new Comparator() {
		public int compare(Object offense, Object otherOffense) {
		  String offenseCategory = ((JJSOffenseResponseEvent)offense).getCatagory();
		  String otherOffenseCategory = ((JJSOffenseResponseEvent)otherOffense).getCatagory();  
		 
		  return offenseCategory.compareTo(otherOffenseCategory);
		}	
	};

	public static Comparator OffenseLevelComparator = new Comparator() 
	{
		public int compare(Object offense, Object otherOffense) 
		{
			int  tOtherOffense = ((JJSOffenseResponseEvent)otherOffense).offenseLevel ;
			int  tOffense = ((JJSOffenseResponseEvent)offense).offenseLevel ;
			return( tOtherOffense -tOffense );
		}	
	};
/**
	 * @return Returns the offenseReportGroup.
	 */
	public String getOffenseReportGroup() {
		return offenseReportGroup;
	}
	/**
	 * @param offenseReportGroup The offenseReportGroup to set.
	 */
	public void setOffenseReportGroup(String offenseReportGroup) {
		this.offenseReportGroup = offenseReportGroup;
	}
	/**
	 * @return Returns the offenseCodeId.
	 */
	public String getOffenseLevelId() {
		return offenseLevelId;
	}
	/**
	 * @param offenseCodeId The offenseCodeId to set.
	 */
	public void setOffenseLevelId(String offenseLevelId) {
		this.offenseLevelId = offenseLevelId;
	}
	/**
	 * @return the petitionAllegationId
	 */
	public String getPetitionAllegationId() {
		return petitionAllegationId;
	}
	/**
	 * @param petitionAllegationId the petitionAllegationId to set
	 */
	public void setPetitionAllegationId(String petitionAllegationId) {
		this.petitionAllegationId = petitionAllegationId;
	}

	/**
	 *  @return the referralAndCategoryDescription
	 */
	public String getReferralAndCategoryDescription()
	{
		return referralAndCategoryDescription;
	}

	/**
	 * @param referralAndCategoryDescription the referralAndCategoryDescription to set
	 */
	public void setReferralAndCategoryDescription( String referralAndCategoryDescription )
	{
		this.referralAndCategoryDescription = referralAndCategoryDescription;
	}

	public int getOffenseLevel()
	{
		return offenseLevel;
	}
	
	/**
	 * @return Returns the offenseCodeId.
	 */
	public String getOffenseCodeId() {
		return offenseCodeId;
	}
	/**
	 * @param offenseCodeId The offenseCodeId to set.
	 */
	public void setOffenseCodeId(String offenseCodeId) {
		this.offenseCodeId = offenseCodeId;
	}

	/**
	 * @return the offenseCode
	 */
	public String getOffenseCode() {
		return offenseCode;
	}

	/**
	 * @param offenseCode the offenseCode to set
	 */
	public void setOffenseCode(String offenseCode) {
		this.offenseCode = offenseCode;
	}

	public String getSeveritySubtype() {
		return severitySubtype;
	}

	public void setSeveritySubtype(String severitySubtype) {
		this.severitySubtype = severitySubtype;
	}

	public String getOffenseCategoryDescription() {
		return offenseCategoryDescription;
	}

	public void setOffenseCategoryDescription(String offenseCategoryDescription) {
		this.offenseCategoryDescription = offenseCategoryDescription;
	}

	public String getPetitionNumber() {
		return petitionNumber;
	}

	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}
	
	/**
	 * @param dispositionCode the dispositionCode to set
	 */
	public void setDispositionCode(String dispositionCode) {
		this.dispositionCode = dispositionCode;
	}

	/**
	 * @return the dispositionCode
	 */
	public String getDispositionCode() {
		return dispositionCode;
	}

	/**
	 * @param dispositionDate the dispositionDate to set
	 */
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	/**
	 * @return the dispositionDate
	 */
	public Date getDispositionDate() {
		return dispositionDate;
	}

	/**
	 * @return the keyMapLocation
	 */
	public String getKeyMapLocation()
	{
	    return keyMapLocation;
	}

	/**
	 * @param keyMapLocation the keyMapLocation to set
	 */
	public void setKeyMapLocation(String keyMapLocation)
	{
	    this.keyMapLocation = keyMapLocation;
	}
	
	public String getOffenseStreetNum()
	{
	    return offenseStreetNum;
	}

	public void setOffenseStreetNum(String offenseStreetNum)
	{
	    this.offenseStreetNum = offenseStreetNum;
	}
	public String getOffenseStreetName()
	{
	    return offenseStreetName;
	}

	public void setOffenseStreetName(String offenseStreetName)
	{
	    this.offenseStreetName = offenseStreetName;
	}
	public String getOffenseAptNum()
	{
	    return offenseAptNum;
	}

	public void setOffenseAptNum(String offenseAptNum)
	{
	    this.offenseAptNum = offenseAptNum;
	}
	public String getOffenseCity()
	{
	    return offenseCity;
	}

	public void setOffenseCity(String offenseCity)
	{
	    this.offenseCity = offenseCity;
	}
	public String getOffenseState()
	{
	    return offenseState;
	}

	public void setOffenseState(String offenseState)
	{
	    this.offenseState = offenseState;
	}
	public String getOffenseZip()
	{
	    return offenseZip;
	}

	public void setOffenseZip(String offenseZip)
	{
	    this.offenseZip = offenseZip;
	}
	public String getWeaponType()
	{
	    return weaponType;
	}

	public void setWeaponType(String weaponType)
	{
	    this.weaponType = weaponType;
	}
	public String getCjisNum()
	{
	    return cjisNum;
	}

	public void setCjisNum(String cjisNum)
	{
	    this.cjisNum = cjisNum;
	}
	
	public String getArrestTime()
	{
	    return arrestTime;
	}

	public void setArrestTime(String arrestTime)
	{
	    this.arrestTime = arrestTime;
	}
	public String getLcUser()
	{
	    return lcUser;
	}

	public void setLcUser(String lcUser)
	{
	    this.lcUser = lcUser;
	}
	public Date getLcDate()
	{
	    return lcDate;
	}

	public void setLcDate(Date lcDate)
	{
	    this.lcDate = lcDate;
	}
	public String getLcTime()
	{
	    return lcTime;
	}

	public void setLcTime(String lcTime)
	{
	    this.lcTime = lcTime;
	}
	public String getChargeSequenceNum()
	{
	    return chargeSequenceNum;
	}

	public void setChargeSequenceNum(String chargeSequenceNum)
	{
	    this.chargeSequenceNum = chargeSequenceNum;
	}
	public String getOffDate()
	{
	    return offDate;
	}

	public void setOffDate(String offDate)
	{
	    this.offDate = offDate;
	}
	public String getArrestDate()
	{
	    return arrestDate;
	}

	public void setArrestDate(String arrestDate)
	{
	    this.arrestDate = arrestDate;
	}
	public String getOffenseSeverity()
	{
	    return offenseSeverity;
	}

	public void setOffenseSeverity(String offenseSeverity)
	{
	    this.offenseSeverity = offenseSeverity;
	}
	public String getOID()
	{
	    return OID;
	}

	public void setOID(String oID)
	{
	    OID = oID;
	}
	public String getOldoffenseCode()
	{
	    return oldoffenseCode;
	}

	public void setOldoffenseCode(String oldoffenseCode)
	{
	    this.oldoffenseCode = oldoffenseCode;
	}

	public String getOnCampOffense()
	{
	    return onCampOffense;
	}

	public void setOnCampOffense(String onCampOffense)
	{
	    this.onCampOffense = onCampOffense;
	}

	public String getOnCampDistrict()
	{
	    return onCampDistrict;
	}

	public void setOnCampDistrict(String onCampDistrict)
	{
	    this.onCampDistrict = onCampDistrict;
	}

	public String getOnCampSchool()
	{
	    return onCampSchool;
	}

	public void setOnCampSchool(String onCampSchool)
	{
	    this.onCampSchool = onCampSchool;
	}

	public String getRecType()
	{
	    return recType;
	}

	public void setRecType(String recType)
	{
	    this.recType = recType;
	}
	public String getNcicCode()
	{
	    return ncicCode;
	}

	public void setNcicCode(String ncicCode)
	{
	    this.ncicCode = ncicCode;
	}

}