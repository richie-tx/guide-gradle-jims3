/*
 * Created on Jun 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import naming.PDJuvenileConstants;

/**
 * @author athorat
 *
 */
public class JuvenileProfilesResponseEvent extends ResponseEvent
{
	private String firstName;
	private String middleName;
	private String lastName;
	private String juvenileNum;
	private String race;
	private String sex;
	private String raceDesc;
	private String sexDesc;
	private String status;
	private String dateOfBirth;
	private String SSN;
	private Date dateOfBirthAsDate;
	private String nationalityCd;
	private String nationalityDesc;
	private String ethnicityCd;
	private String ethnicityDesc;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJims2User;
	private String updateJims2User;

	public Date getDateOfBirthAsDate() {
		return dateOfBirthAsDate;
	}

	public void setDateOfBirthAsDate(Date dateOfBirthAsDate) {
		this.dateOfBirthAsDate = dateOfBirthAsDate;
	}

	/**
	 * 
	 */
	public JuvenileProfilesResponseEvent()
	{
		super();
		// TODO Auto-generated constructor stub
		this.setTopic(PDJuvenileConstants.SEARCH_JUVENILE_PROFILES_RESULTS_TOPIC);
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getRace()
	{
		return race;
	}

	/**
	 * @return
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @param string
	 */
	public void setRace(String string)
	{
		race = string;
	}

	/**
	 * @param string
	 */
	public void setSex(String string)
	{
		sex = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
		status = string;
	}

	/**
	 * @return
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @param string
	 */
	public void setDateOfBirth(String string)
	{
		dateOfBirth = string;
	}

	/**
	 * @return Returns the sSN.
	 */
	public String getSSN() {
		return SSN;
	}
	/**
	 * @param ssn The sSN to set.
	 */
	public void setSSN(String ssn) {
		SSN = ssn;
	}
	/**
	 * @return Returns the raceDesc.
	 */
	public String getRaceDesc() {
		return raceDesc;
	}
	/**
	 * @param raceDesc The raceDesc to set.
	 */
	public void setRaceDesc(String raceDesc) {
		this.raceDesc = raceDesc;
	}
	/**
	 * @return Returns the sexDesc.
	 */
	public String getSexDesc() {
		return sexDesc;
	}
	/**
	 * @param sexDesc The sexDesc to set.
	 */
	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public String getNationalityCd() {
		return nationalityCd;
	}

	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
	}

	public String getNationalityDesc() {
		return nationalityDesc;
	}

	public void setNationalityDesc(String nationalityDesc) {
		this.nationalityDesc = nationalityDesc;
	}

	public void setEthnicityCd(String ethnicityCd) {
		this.ethnicityCd = ethnicityCd;
	}

	public String getEthnicityCd() {
		return ethnicityCd;
	}

	public void setEthnicityDesc(String ethnicityDesc) {
		this.ethnicityDesc = ethnicityDesc;
	}

	public String getEthnicityDesc() {
		return ethnicityDesc;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateJims2User() {
		return createJims2User;
	}

	public void setCreateJims2User(String createJims2User) {
		this.createJims2User = createJims2User;
	}

	public String getUpdateJims2User() {
		return updateJims2User;
	}

	public void setUpdateJims2User(String updateJims2User) {
		this.updateJims2User = updateJims2User;
	}
}
