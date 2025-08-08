package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
/**
 * 
 * @author sthyagarajan
 * added for task 43956
 *
 */
public class JuvenilePactSubject extends PersistentObject{

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
	 * @roseuid 42A882800158
	 * @param juvNum.
	 *            juvenile number is the unique primary key of this table.
	 * @param juvNum
	 * @return Juvenile.
	 * @return pd.juvenilecase.Juvenile
	 */
	static public JuvenilePactSubject findJuvenilePactSubject(String juvNum) {
		
		JuvenilePactSubject juv = (JuvenilePactSubject) new Home().find(juvNum, JuvenilePactSubject.class);
		return juv;
	}
	
	/**
	 * @param searchEvent
	 * @return
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenilePactSubject.class);
	}
	
	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
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
		fetch();
		return casePlanLastDate;
	}
	/**
	 * @param casePlanLastDate the casePlanLastDate to set
	 */
	public void setCasePlanLastDate(Date casePlanLastDate) {
		this.casePlanLastDate = casePlanLastDate;
	}
}
