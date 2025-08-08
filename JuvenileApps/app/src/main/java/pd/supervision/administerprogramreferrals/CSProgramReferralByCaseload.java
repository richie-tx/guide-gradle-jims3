package pd.supervision.administerprogramreferrals;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class CSProgramReferralByCaseload extends PersistentObject 
{
	private String programReferralId;
	private String criminalCaseId;
	private String defendantId;
	private String superviseeName;
	private String referralStatusCode;
	private String referralTypeCode;
	private String programGroupCd;
	private String programTypeCd;
	private String programHierarchyCd;
	private Date referralDate;
	private String programLocationId;
	private Date referralBeginDate;
	private Date referralEndDate;
	
	private String serviceProviderId;
    private String serviceProviderName;
    private String programId;
    private String programIdentifier;
    private String programName;
    private boolean isInHouse;
    private String cstsCode;
    
    private String locationId;
    private String locationName;
    private String locationPhone;
    private String regionCode;
    private String streetNumber;
    private String streetName;
    private String streetTypeCd;
    private String aptNum;
    private String city;
    private String state;
    private String zipCode;
    
    
   
	/**
	 * 
	 * @return
	 */
	public static Iterator findAll()
	{
	    IHome home = new Home();
		Iterator iter = home.findAll(CSProgramReferralByCaseload.class);
		return iter;
	}
	
    /**
     * 
     * @param event
     * @return
     */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CSProgramReferralByCaseload.class);
	}

   /**
    * 
    * @param attrName
    * @param attrValue
    * @return
    */
	public static Iterator findAll(String attrName, String attrValue) 
	{
	    IHome home = new Home();
		return  home.findAll(attrName, attrValue, CSProgramReferralByCaseload.class);
	}
    
    
    
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}
	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
	/**
	 * @return the referralStatusCode
	 */
	public String getReferralStatusCode() {
		return referralStatusCode;
	}
	/**
	 * @param referralStatusCode the referralStatusCode to set
	 */
	public void setReferralStatusCode(String referralStatusCode) {
		this.referralStatusCode = referralStatusCode;
	}
	/**
	 * @return the referralTypeCode
	 */
	public String getReferralTypeCode() {
		return referralTypeCode;
	}
	/**
	 * @param referralTypeCode the referralTypeCode to set
	 */
	public void setReferralTypeCode(String referralTypeCode) {
		this.referralTypeCode = referralTypeCode;
	}
	/**
	 * @return the referralDate
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate the referralDate to set
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}
	/**
	 * @return the programLocationId
	 */
	public String getProgramLocationId() {
		return programLocationId;
	}
	/**
	 * @param programLocationId the programLocationId to set
	 */
	public void setProgramLocationId(String programLocationId) {
		this.programLocationId = programLocationId;
	}
	/**
	 * @return the referralBeginDate
	 */
	public Date getReferralBeginDate() {
		return referralBeginDate;
	}

	/**
	 * @param referralBeginDate the referralBeginDate to set
	 */
	public void setReferralBeginDate(Date referralBeginDate) {
		this.referralBeginDate = referralBeginDate;
	}

	/**
	 * @return the referralEndDate
	 */
	public Date getReferralEndDate() {
		return referralEndDate;
	}

	/**
	 * @param referralEndDate the referralEndDate to set
	 */
	public void setReferralEndDate(Date referralEndDate) {
		this.referralEndDate = referralEndDate;
	}

	/**
	 * @return the serviceProviderId
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}

	/**
	 * @param serviceProviderId the serviceProviderId to set
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return the programIdentifier
	 */
	public String getProgramIdentifier() {
		return programIdentifier;
	}
	/**
	 * @param programIdentifier the programIdentifier to set
	 */
	public void setProgramIdentifier(String programIdentifier) {
		this.programIdentifier = programIdentifier;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return the isInHouse
	 */
	public boolean getIsInHouse() {
		return isInHouse;
	}
	/**
	 * @param isInHouse the isInHouse to set
	 */
	public void setIsInHouse(boolean isInHouse) {
		this.isInHouse = isInHouse;
	}
	/**
	 * @return the cstsCode
	 */
	public String getCstsCode() {
		return cstsCode;
	}
	/**
	 * @param cstsCode the cstsCode to set
	 */
	public void setCstsCode(String cstsCode) {
		this.cstsCode = cstsCode;
	}
	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return the locationPhone
	 */
	public String getLocationPhone() {
		return locationPhone;
	}
	/**
	 * @param locationPhone the locationPhone to set
	 */
	public void setLocationPhone(String locationPhone) {
		this.locationPhone = locationPhone;
	}
	/**
	 * @return the regionCode
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return the streetTypeCd
	 */
	public String getStreetTypeCd() {
		return streetTypeCd;
	}
	/**
	 * @param streetTypeCd the streetTypeCd to set
	 */
	public void setStreetTypeCd(String streetTypeCd) {
		this.streetTypeCd = streetTypeCd;
	}
	/**
	 * @return the aptNum
	 */
	public String getAptNum() {
		return aptNum;
	}
	/**
	 * @param aptNum the aptNum to set
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the programGroupCd
	 */
	public String getProgramGroupCd() {
		return programGroupCd;
	}

	/**
	 * @param programGroupCd the programGroupCd to set
	 */
	public void setProgramGroupCd(String programGroupCd) {
		this.programGroupCd = programGroupCd;
	}

	/**
	 * @return the programTypeCd
	 */
	public String getProgramTypeCd() {
		return programTypeCd;
	}

	/**
	 * @param programTypeCd the programTypeCd to set
	 */
	public void setProgramTypeCd(String programTypeCd) {
		this.programTypeCd = programTypeCd;
	}

	/**
	 * @return the programHierarchyCd
	 */
	public String getProgramHierarchyCd() {
		return programHierarchyCd;
	}

	/**
	 * @param programHierarchyCd the programHierarchyCd to set
	 */
	public void setProgramHierarchyCd(String programHierarchyCd) {
		this.programHierarchyCd = programHierarchyCd;
	}
   
}
