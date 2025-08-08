package messaging.juvenile.reply;

import java.util.Date;
/**
 * 
 * @author sthyagarajan
 * added for task 43956
 *
 */
public class JuvenilePactDetailResponseEvent extends mojo.km.messaging.ResponseEvent implements Comparable<JuvenilePactDetailResponseEvent>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String juvenileId;
	private String guid;
	private String lastName;
	private String firstName;
	private String zipCode;
	private String city;
	private String county;
	private Date dateOfBirth;
	private String gender;
	private String raceId;
	private String raceDesc;
	private String age;
	private String riskLevel;
	private String riskLevelOverride;
	private Date riskLevelOverrideDate;
	private Date pactLastDate;
	private Date onaLastDate;
	private Date rPactLastDate;
	private Date sraLastDate;
	private Date casePlanLastDate;
	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the raceId
	 */
	public String getRaceId() {
		return raceId;
	}
	/**
	 * @param raceId the raceId to set
	 */
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	/**
	 * @return the raceDesc
	 */
	public String getRaceDesc() {
		return raceDesc;
	}
	/**
	 * @param raceDesc the raceDesc to set
	 */
	public void setRaceDesc(String raceDesc) {
		this.raceDesc = raceDesc;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the riskLevel
	 */
	public String getRiskLevel() {
		return riskLevel;
	}
	/**
	 * @param riskLevel the riskLevel to set
	 */
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	/**
	 * @return the riskLevelOverride
	 */
	public String getRiskLevelOverride() {
		return riskLevelOverride;
	}
	/**
	 * @param riskLevelOverride the riskLevelOverride to set
	 */
	public void setRiskLevelOverride(String riskLevelOverride) {
		this.riskLevelOverride = riskLevelOverride;
	}
	/**
	 * @return the riskLevelOverrideDate
	 */
	public Date getRiskLevelOverrideDate() {
		return riskLevelOverrideDate;
	}
	/**
	 * @param riskLevelOverrideDate the riskLevelOverrideDate to set
	 */
	public void setRiskLevelOverrideDate(Date riskLevelOverrideDate) {
		this.riskLevelOverrideDate = riskLevelOverrideDate;
	}
	/**
	 * @return the pactLastDate
	 */
	public Date getPactLastDate() {
		return pactLastDate;
	}
	/**
	 * @param pactLastDate the pactLastDate to set
	 */
	public void setPactLastDate(Date pactLastDate) {
		this.pactLastDate = pactLastDate;
	}
	/**
	 * @return the onaLastDate
	 */
	public Date getOnaLastDate() {
		return onaLastDate;
	}
	/**
	 * @param onaLastDate the onaLastDate to set
	 */
	public void setOnaLastDate(Date onaLastDate) {
		this.onaLastDate = onaLastDate;
	}
	/**
	 * @return the rPactLastDate
	 */
	public Date getrPactLastDate() {
		return rPactLastDate;
	}
	/**
	 * @param rPactLastDate the rPactLastDate to set
	 */
	public void setrPactLastDate(Date rPactLastDate) {
		this.rPactLastDate = rPactLastDate;
	}
	/**
	 * @return the sraLastDate
	 */
	public Date getSraLastDate() {
		return sraLastDate;
	}
	/**
	 * @param sraLastDate the sraLastDate to set
	 */
	public void setSraLastDate(Date sraLastDate) {
		this.sraLastDate = sraLastDate;
	}
	/**
	 * @return the casePlanLastDate
	 */
	public Date getCasePlanLastDate() {
		return casePlanLastDate;
	}
	/**
	 * @param casePlanLastDate the casePlanLastDate to set
	 */
	public void setCasePlanLastDate(Date casePlanLastDate) {
		this.casePlanLastDate = casePlanLastDate;
	}
	
	@Override
	public int compareTo(JuvenilePactDetailResponseEvent o)
	{
	    if (o == null)
           return 1; // this makes any null objects go to the bottom change this to -1 if you want
     
	   // the top of the list
       if (this.pactLastDate == null)
           return -1; // this makes any null objects go to the bottom change this to 1 if you want

       // the top of the list
	   JuvenilePactDetailResponseEvent evt = (JuvenilePactDetailResponseEvent) o;
	   return evt.getPactLastDate().compareTo(pactLastDate);
	}
}
