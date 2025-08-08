/*
 * Created on Oct 29, 2004
*/

package messaging.juvenilewarrant.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 */
public class JuvenileOffenderTrackingResponseEvent extends ResponseEvent
{
	private String aliasName;
	private Date arrestDate;
	private String arrestTime;
	private int daDateOut;
	private String build;
	private String buildId;
	private String coDefendants;
	private String complexion;
	private String complexionId;
	private String daLogNum;	
	private int dateOfBirth;
	private String eyeColor;
	private String eyeColorId;
	private String fathersEmployer;
	private String fathersEmployerPhoneNum;
	private String fathersFirstName;
	private String fathersLastName;
	private String fathersMiddleName;
	private String fathersPhoneNum;
	private String fathersSsn;
	private String fbiNum;
	private String firstName;
	private String filingAgency;
	private String filingAgencyDesc;
	private String hairColor;
	private String hairColorId;
	private String height;
	private String juvenileNum;
	private String lastName;
	private String middleName;
	private String mothersEmployer;
	private String mothersEmployerPhoneNum;
	private String mothersFirstName;
	private String mothersLastName;
	private String mothersMiddleName;
	private String mothersPhoneNum;
	private String mothersSsn;
	private String otherEmployer;
	private String otherEmployerPhoneNum;
	private String otherFirstName;
	private String otherLastName;
	private String otherMiddleName;
	private String otherPhoneNum;
	private String otherSsn;
	private String phoneNum;
	private String race;
	private String raceId;
	private String scarsMarks;
	private String scarsMarksId;
	private String schoolName;
	private String schoolCodeId;
	private String schoolDistrict;
	private String schoolDistrictId;
	private String sex;
	private String sexId;
	private String sid;
	private String ssn;
	private String transactionNum;
	private String weight;
	private String logStatus;
	
	//for Assigned Referrals
	private String identificationType;
	private String cjisNum;
	private String sentToDaInd;
	/**
	 * @return
	 */
	public String getAliasName()
	{
		return aliasName;
	}

	/**
	 * @return
	 */
	public String getBuild()
	{
		return build;
	}

	/**
	 * @return
	 */
	public String getBuildId()
	{
		return buildId;
	}
	/**
	 * @return
	 */
	public String getComplexion()
	{
		return complexion;
	}

	/**
	 * @return
	 */
	public String getComplexionId()
	{
		return complexionId;
	}

	/**
	 * @return
	 */
	public String getDaLogNum()
	{
		return daLogNum;
	}

	/**
	 * @return
	 */
	public int getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public String getEyeColor()
	{
		return eyeColor;
	}

	/**
	 * @return
	 */
	public String getEyeColorId()
	{
		return eyeColorId;
	}


	/**
	 * @return
	 */
	public String getFathersEmployer()
	{
		return fathersEmployer;
	}

	/**
	 * @return
	 */
	public String getFathersEmployerPhoneNum()
	{
		return fathersEmployerPhoneNum;
	}

	/**
	 * @return
	 */
	public String getFathersFirstName()
	{
		return fathersFirstName;
	}

	/**
	 * @return
	 */
	public String getFathersLastName()
	{
		return fathersLastName;
	}

	/**
	 * @return
	 */
	public String getFathersMiddleName()
	{
		return fathersMiddleName;
	}

	/**
	 * @return
	 */
	public String getFathersPhoneNum()
	{
		return fathersPhoneNum;
	}

	/**
	 * @return
	 */
	public String getFathersSsn()
	{
		return fathersSsn;
	}


	/**
	 * @return
	 */
	public String getFbiNum()
	{
		return fbiNum;
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
	public String getHairColor()
	{
		return hairColor;
	}

	/**
	 * @return
	 */
	public String getHairColorId()
	{
		return hairColorId;
	}
	
	/**
	 * @return
	 */
	public String getHeight()
	{
		return height;
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
	public String getMothersEmployer()
	{
		return mothersEmployer;
	}

	/**
	 * @return
	 */
	public String getMothersEmployerPhoneNum()
	{
		return mothersEmployerPhoneNum;
	}

	/**
	 * @return
	 */
	public String getMothersFirstName()
	{
		return mothersFirstName;
	}

	/**
	 * @return
	 */
	public String getMothersLastName()
	{
		return mothersLastName;
	}

	/**
	 * @return
	 */
	public String getMothersMiddleName()
	{
		return mothersMiddleName;
	}

	/**
	 * @return
	 */
	public String getMothersPhoneNum()
	{
		return mothersPhoneNum;
	}

	/**
	 * @return
	 */
	public String getMothersSsn()
	{
		return mothersSsn;
	}



	/**
	 * @return
	 */
	public String getOtherEmployer()
	{
		return otherEmployer;
	}

	/**
	 * @return
	 */
	public String getOtherEmployerPhoneNum()
	{
		return otherEmployerPhoneNum;
	}

	/**
	 * @return
	 */
	public String getOtherFirstName()
	{
		return otherFirstName;
	}

	/**
	 * @return
	 */
	public String getOtherLastName()
	{
		return otherLastName;
	}

	/**
	 * @return
	 */
	public String getOtherMiddleName()
	{
		return otherMiddleName;
	}

	/**
	 * @return
	 */
	public String getOtherPhoneNum()
	{
		return otherPhoneNum;
	}

	/**
	 * @return
	 */
	public String getOtherSsn()
	{
		return otherSsn;
	}

	/**
	 * @return
	 */
	public String getPhoneNum()
	{
		return phoneNum;
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
	public String getRaceId()
	{
		return raceId;
	}

	/**
	 * @return
	 */
	public String getScarsMarks()
	{
		return scarsMarks;
	}

	/**
	 * @return
	 */
	public String getScarsMarksId()
	{
		return scarsMarksId;
	}

	/**
	 * @return
	 */
	public String getSchoolName()
	{
		return schoolName;
	}

	/**
	 * @return
	 */
	public String getSchoolCodeId()
	{
		return schoolCodeId;
	}

	/**
	 * @return
	 */
	public String getSchoolDistrict()
	{
		return schoolDistrict;
	}

	/**
	 * @return
	 */
	public String getSchoolDistrictId()
	{
		return schoolDistrictId;
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
	public String getSexId()
	{
		return sexId;
	}

	/**
	 * @return
	 */
	public String getSid()
	{
		return sid;
	}

	/**
	 * @return
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @return
	 */
	public String getTransactionNum()
	{
		return transactionNum;
	}

	/**
	 * @return
	 */
	public String getWeight()
	{
		return weight;
	}

	/**
	 * @param aliasName
	 */
	public void setAliasName(String aliasName)
	{
		this.aliasName = aliasName;
	}

	/**
	 * @param build
	 */
	public void setBuild(String build)
	{
		this.build = build;
	}

	/**
	 * @param buildId
	 */
	public void setBuildId(String buildId)
	{
		this.buildId = buildId;
	}

	/**
	 * @param complexion
	 */
	public void setComplexion(String complexion)
	{
		this.complexion = complexion;
	}

	/**
	 * @param complexionId
	 */
	public void setComplexionId(String complexionId)
	{
		this.complexionId = complexionId;
	}

	/**
	 * @param daLogNum
	 */
	public void setDaLogNum(String daLogNum)
	{
		this.daLogNum = daLogNum;
	}

	/**
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(int dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param eyeColor
	 */
	public void setEyeColor(String eyeColor)
	{
		this.eyeColor = eyeColor;
	}

	/**
	 * @param eyeColorId
	 */
	public void setEyeColorId(String eyeColorId)
	{
		this.eyeColorId = eyeColorId;
	}

	/**
	 * @param fathersEmployer
	 */
	public void setFathersEmployer(String fathersEmployer)
	{
		this.fathersEmployer = fathersEmployer;
	}

	/**
	 * @param fathersEmployerPhoneNum
	 */
	public void setFathersEmployerPhoneNum(String fathersEmployerPhoneNum)
	{
		this.fathersEmployerPhoneNum = fathersEmployerPhoneNum;
	}

	/**
	 * @param fathersFirstName
	 */
	public void setFathersFirstName(String fathersFirstName)
	{
		this.fathersFirstName = fathersFirstName;
	}

	/**
	 * @param fathersLastName
	 */
	public void setFathersLastName(String fathersLastName)
	{
		this.fathersLastName = fathersLastName;
	}

	/**
	 * @param fathersMiddleName
	 */
	public void setFathersMiddleName(String fathersMiddleName)
	{
		this.fathersMiddleName = fathersMiddleName;
	}

	/**
	 * @param fathersPhoneNum
	 */
	public void setFathersPhoneNum(String fathersPhoneNum)
	{
		this.fathersPhoneNum = fathersPhoneNum;
	}

	/**
	 * @param fathersSsn
	 */
	public void setFathersSsn(String fathersSsn)
	{
		this.fathersSsn = fathersSsn;
	}

	/**
	 * @param fbiNum
	 */
	public void setFbiNum(String fbiNum)
	{
		this.fbiNum = fbiNum;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param hairColor
	 */
	public void setHairColor(String hairColor)
	{
		this.hairColor = hairColor;
	}

	/**
	 * @param hairColorId
	 */
	public void setHairColorId(String hairColorId)
	{
		this.hairColorId = hairColorId;
	}

	/**
	 * @param height
	 */
	public void setHeight(String height)
	{
		this.height = height;
	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @param mothersEmployer
	 */
	public void setMothersEmployer(String mothersEmployer)
	{
		this.mothersEmployer = mothersEmployer;
	}

	/**
	 * @param mothersEmployerPhoneNum
	 */
	public void setMothersEmployerPhoneNum(String mothersEmployerPhoneNum)
	{
		this.mothersEmployerPhoneNum = mothersEmployerPhoneNum;
	}

	/**
	 * @param mothersFirstName
	 */
	public void setMothersFirstName(String mothersFirstName)
	{
		this.mothersFirstName = mothersFirstName;
	}

	/**
	 * @param mothersLastName
	 */
	public void setMothersLastName(String mothersLastName)
	{
		this.mothersLastName = mothersLastName;
	}

	/**
	 * @param mothersMiddleName
	 */
	public void setMothersMiddleName(String mothersMiddleName)
	{
		this.mothersMiddleName = mothersMiddleName;
	}

	/**
	 * @param mothersPhoneNum
	 */
	public void setMothersPhoneNum(String mothersPhoneNum)
	{
		this.mothersPhoneNum = mothersPhoneNum;
	}

	/**
	 * @param mothersSsn
	 */
	public void setMothersSsn(String mothersSsn)
	{
		this.mothersSsn = mothersSsn;
	}

	/**
	 * @param otherEmployer
	 */
	public void setOtherEmployer(String otherEmployer)
	{
		this.otherEmployer = otherEmployer;
	}

	/**
	 * @param otherEmployerPhoneNum
	 */
	public void setOtherEmployerPhoneNum(String otherEmployerPhoneNum)
	{
		this.otherEmployerPhoneNum = otherEmployerPhoneNum;
	}

	/**
	 * @param otherFirstName
	 */
	public void setOtherFirstName(String otherFirstName)
	{
		this.otherFirstName = otherFirstName;
	}

	/**
	 * @param otherLastName
	 */
	public void setOtherLastName(String otherLastName)
	{
		this.otherLastName = otherLastName;
	}

	/**
	 * @param otherMiddleName
	 */
	public void setOtherMiddleName(String otherMiddleName)
	{
		this.otherMiddleName = otherMiddleName;
	}

	/**
	 * @param otherPhoneNum
	 */
	public void setOtherPhoneNum(String otherPhoneNum)
	{
		this.otherPhoneNum = otherPhoneNum;
	}

	/**
	 * @param otherSsn
	 */
	public void setOtherSsn(String otherSsn)
	{
		this.otherSsn = otherSsn;
	}

	/**
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	/**
	 * @param race
	 */
	public void setRace(String race)
	{
		this.race = race;
	}

	/**
	 * @param raceId
	 */
	public void setRaceId(String raceId)
	{
		this.raceId = raceId;
	}

	/**
	 * @param scarsMarks
	 */
	public void setScarsMarks(String distinguishingMarks)
	{
		this.scarsMarks = distinguishingMarks;
	}

	/**
	 * @param scarsMarksId
	 */
	public void setScarsMarksId(String distinguishingMarksId)
	{
		this.scarsMarksId = distinguishingMarksId;
	}

	/**
	 * @param schoolName
	 */
	public void setSchoolName(String schoolCode)
	{
		this.schoolName = schoolCode;
	}

	/**
	 * @param schoolCodeId
	 */
	public void setSchoolCodeId(String schoolCodeId)
	{
		this.schoolCodeId = schoolCodeId;
	}

	/**
	 * @param schoolDistrict
	 */
	public void setSchoolDistrict(String schoolDistrict)
	{
		this.schoolDistrict = schoolDistrict;
	}

	/**
	 * @param schoolDistrictId
	 */
	public void setSchoolDistrictId(String schoolDistrictId)
	{
		this.schoolDistrictId = schoolDistrictId;
	}

	/**
	 * @param sex
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	/**
	 * @param sexId
	 */
	public void setSexId(String sexId)
	{
		this.sexId = sexId;
	}

	/**
	 * @param sid
	 */
	public void setSid(String sid)
	{
		this.sid = sid;
	}

	/**
	 * @param ssn
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @param transactionNum
	 */
	public void setTransactionNum(String transactionNum)
	{
		this.transactionNum = transactionNum;
	}

	/**
	 * @param weight
	 */
	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	/**
	 * @return Returns the arrestDate.
	 */
	public Date getArrestDate() {
		return arrestDate;
	}
	/**
	 * @param arrestDate The arrestDate to set.
	 */
	public void setArrestDate(Date arrestDate) {
		this.arrestDate = arrestDate;
	}
	/**
	 * @return Returns the arrestTime.
	 */
	public String getArrestTime() {
		return arrestTime;
	}
	/**
	 * @param arrestTime The arrestTime to set.
	 */
	public void setArrestTime(String arrestTime) {
		this.arrestTime = arrestTime;
	}
	/**
	 * @return Returns the coDefendants.
	 */
	public String getCoDefendants() {
		return coDefendants;
	}
	/**
	 * @param coDefendants The coDefendants to set.
	 */
	public void setCoDefendants(String coDefendants) {
		this.coDefendants = coDefendants;
	}
	/**
	 * @return Returns the identificationType.
	 */
	public String getIdentificationType() {
		return identificationType;
	}
	/**
	 * @param identificationType The identificationType to set.
	 */
	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public int getDaDateOut()
	{
	    return daDateOut;
	}

	public void setDaDateOut(int daDateOut)
	{
	    this.daDateOut = daDateOut;
	}

	public String getLogStatus()
	{
	    return logStatus;
	}

	public void setLogStatus(String logStatus)
	{
	    this.logStatus = logStatus;
	}
	public String getCjisNum()
	{
	   
	    return cjisNum;
	}

	public void setCjisNum(String cjisNum)
	{
	        this.cjisNum = cjisNum;
	}

	public String getFilingAgency()
	{
	    return filingAgency;
	}

	public void setFilingAgency(String filingAgency)
	{
	    this.filingAgency = filingAgency;
	}

	public String getFilingAgencyDesc()
	{
	    return filingAgencyDesc;
	}

	public void setFilingAgencyDesc(String filingAgencyDesc)
	{
	    this.filingAgencyDesc = filingAgencyDesc;
	}

	public String getSentToDaInd()
	{
	    return sentToDaInd;
	}

	public void setSentToDaInd(String sentToDaInd)
	{
	    this.sentToDaInd = sentToDaInd;
	}
	
	
}