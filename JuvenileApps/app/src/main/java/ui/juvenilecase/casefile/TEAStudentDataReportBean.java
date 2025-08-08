/*
 * Created on Dec 20, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile;

import java.util.List;

import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import ui.juvenilecase.casefile.form.GuardianBean;

/**
 * @author jjose To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TEAStudentDataReportBean
{
    private String blank = "";
    // heading values
    private String juvenileName;
    private String juvenileNumber;
    private String dateOfBirth;
    private String verifiedDOB;
    private String currentAge;
    private String gender;
    private String ssnPEIMSId;
    private String race;
    private String ethnicityDesc;
    private String multiracialDesc;
    private String hispanicDesc;
    private String detentionAdmitDate;
    //ER changes for JIMS200077279
    private String eligibilityEnrollmentDate;
    //ER Changes JIMS200074578 Starts
    private String dyslexia = "NO";
    private String military = "NO";
    private String fosterParent = "NO";
    //ER Changes JIMS200074578 ends

    private String fullAddress;
    private String cityStateZip;
    private String county;
    private String phoneNum;
    private String phoneType;
    private String hairColor;
    private String eyeColor;

    // body values
    private List<GuardianBean> guardians;
    private List familyInformation;
    private List<JuvenileSchoolHistoryResponseEvent> schoolHistory;
    private JuvenileSchoolHistoryResponseEvent currentSchool;
    private List detentionFacilityHistory;
    private boolean included;

    //add EDUCATION ID for TEA report
    private String educationId;

    //ER Changes for JIMS200077276
    private String studentId;

    /**
     * @return
     */
    public String getBlank()
    {
	return blank;
    }

    /**
     * @param string
     */
    public void setBlank(String string)
    {
	blank = string;
    }

    /**
     * @return the juvenileName
     */
    public String getJuvenileName()
    {
	return juvenileName;
    }

    /**
     * @param juvenileName
     *            the juvenileName to set
     */
    public void setJuvenileName(String juvenileName)
    {
	this.juvenileName = juvenileName;
    }

    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    /**
     * @param juvenileNumber
     *            the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }

    /**
     * @return the verifiedDOB
     */
    public String getVerifiedDOB()
    {
	return verifiedDOB;
    }

    /**
     * @param verifiedDOB
     *            the verifiedDOB to set
     */
    public void setVerifiedDOB(String verifiedDOB)
    {
	this.verifiedDOB = verifiedDOB;
    }

    /**
     * @return the currentAge
     */
    public String getCurrentAge()
    {
	return currentAge;
    }

    /**
     * @param currentAge
     *            the currentAge to set
     */
    public void setCurrentAge(String currentAge)
    {
	this.currentAge = currentAge;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth()
    {
	return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     *            the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the gender
     */
    public String getGender()
    {
	return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(String gender)
    {
	this.gender = gender;
    }

    /**
     * @return the ssnPEIMSId
     */
    public String getSsnPEIMSId()
    {
	return ssnPEIMSId;
    }

    /**
     * @param ssnPEIMSId
     *            the ssnPEIMSId to set
     */
    public void setSsnPEIMSId(String ssnPEIMSId)
    {
	this.ssnPEIMSId = ssnPEIMSId;
    }

    /**
     * @return the race
     */
    public String getRace()
    {
	return race;
    }

    /**
     * @param race
     *            the race to set
     */
    public void setRace(String race)
    {
	this.race = race;
    }

    /**
     * @return the ethnicityDesc
     */
    public String getEthnicityDesc()
    {
	return ethnicityDesc;
    }

    /**
     * @param ethnicityDesc
     *            the ethnicityDesc to set
     */
    public void setEthnicityDesc(String ethnicityDesc)
    {
	this.ethnicityDesc = ethnicityDesc;
    }

    /**
     * @return the multiracialDesc
     */
    public String getMultiracialDesc()
    {
	return multiracialDesc;
    }

    /**
     * @param multiracialDesc
     *            the multiracialDesc to set
     */
    public void setMultiracialDesc(String multiracialDesc)
    {
	this.multiracialDesc = multiracialDesc;
    }

    /**
     * @return the hispanicDesc
     */
    public String getHispanicDesc()
    {
	return hispanicDesc;
    }

    /**
     * @param hispanicDesc
     *            the hispanicDesc to set
     */
    public void setHispanicDesc(String hispanicDesc)
    {
	this.hispanicDesc = hispanicDesc;
    }

    /**
     * @return the detentionAdmitDate
     */
    public String getDetentionAdmitDate()
    {
	return detentionAdmitDate;
    }

    /**
     * @param detentionAdmitDate
     *            the detentionAdmitDate to set
     */
    public void setDetentionAdmitDate(String detentionAdmitDate)
    {
	this.detentionAdmitDate = detentionAdmitDate;
    }

    /**
     * @return the familyInformation
     */
    public List getFamilyInformation()
    {
	return familyInformation;
    }

    /**
     * @param familyInformation
     *            the familyInformation to set
     */
    public void setFamilyInformation(List familyInformation)
    {
	this.familyInformation = familyInformation;
    }

    /**
     * @return the schoolHistory
     */
    public List<JuvenileSchoolHistoryResponseEvent> getSchoolHistory()
    {
	return schoolHistory;
    }

    /**
     * @param schoolHistory
     *            the schoolHistory to set
     */
    public void setSchoolHistory(List<JuvenileSchoolHistoryResponseEvent> schoolHistory)
    {
	this.schoolHistory = schoolHistory;
    }

    /**
     * @return the detentionFacilityHistory
     */
    public List getDetentionFacilityHistory()
    {
	return detentionFacilityHistory;
    }

    /**
     * @param detentionFacilityHistory
     *            the detentionFacilityHistory to set
     */
    public void setDetentionFacilityHistory(List detentionFacilityHistory)
    {
	this.detentionFacilityHistory = detentionFacilityHistory;
    }

    /**
     * @return the included
     */
    public boolean isIncluded()
    {
	return included;
    }

    /**
     * @param included
     *            the included to set
     */
    public void setIncluded(boolean included)
    {
	this.included = included;
    }

    /**
     * @return the educationId
     */
    public String getEducationId()
    {
	return educationId;
    }

    /**
     * @param educationId
     *            the educationId to set
     */
    public void setEducationId(String educationId)
    {
	this.educationId = educationId;
    }

    /**
     * @param dyslexia
     *            the dyslexia to set
     */
    public void setDyslexia(String dyslexia)
    {
	this.dyslexia = dyslexia;
    }

    /**
     * @return the dyslexia
     */
    public String getDyslexia()
    {
	return dyslexia;
    }

    /**
     * @param military
     *            the military to set
     */
    public void setMilitary(String military)
    {
	this.military = military;
    }

    /**
     * @return the military
     */
    public String getMilitary()
    {
	return military;
    }

    /**
     * @param fosterParent
     *            the fosterParent to set
     */
    public void setFosterParent(String fosterParent)
    {
	this.fosterParent = fosterParent;
    }

    /**
     * @return the fosterParent
     */
    public String getFosterParent()
    {
	return fosterParent;
    }

    /**
     * @param eligibilityEnrollmentDate
     *            the eligibilityEnrollmentDate to set
     */
    public void setEligibilityEnrollmentDate(String eligibilityEnrollmentDate)
    {
	this.eligibilityEnrollmentDate = eligibilityEnrollmentDate;
    }

    /**
     * @return the eligibilityEnrollmentDate
     */
    public String getEligibilityEnrollmentDate()
    {
	return eligibilityEnrollmentDate;
    }

    /**
     * @param studentId
     *            the studentId to set
     */
    public void setStudentId(String studentId)
    {
	this.studentId = studentId;
    }

    /**
     * @return the studentId
     */
    public String getStudentId()
    {
	return studentId;
    }

    public String getFullAddress()
    {
	return fullAddress;
    }

    public void setFullAddress(String streetName)
    {
	this.fullAddress = streetName;
    }

    public String getCounty()
    {
	return county;
    }

    public void setCounty(String county)
    {
	this.county = county;
    }

    public String getPhoneNum()
    {
	return phoneNum;
    }

    public void setPhoneNum(String phoneNum)
    {
	this.phoneNum = phoneNum;
    }

    public String getPhoneType()
    {
	return phoneType;
    }

    public void setPhoneType(String phoneType)
    {
	this.phoneType = phoneType;
    }

    public String getHairColor()
    {
	return hairColor;
    }

    public void setHairColor(String hairColor)
    {
	this.hairColor = hairColor;
    }

    public String getEyeColor()
    {
	return eyeColor;
    }

    public void setEyeColor(String eyeColor)
    {
	this.eyeColor = eyeColor;
    }

    public String getCityStateZip()
    {
	return cityStateZip;
    }

    public void setCityStateZip(String cityStateZip)
    {
	this.cityStateZip = cityStateZip;
    }

    public List<GuardianBean> getGuardians()
    {
	return guardians;
    }

    public void setGuardians(List<GuardianBean> guardians)
    {
	this.guardians = guardians;
    }

    public JuvenileSchoolHistoryResponseEvent getCurrentSchool()
    {
	return currentSchool;
    }

    public void setCurrentSchool(JuvenileSchoolHistoryResponseEvent currentSchool)
    {
	this.currentSchool = currentSchool;
    }

}