/*
 * Created on Nov 12, 2004
 *
 */
package messaging.juvenilewarrant.reply;

import java.util.Date;

import messaging.contact.domintf.IName;
import mojo.km.messaging.ResponseEvent;

/**
 * @author Jim Fisher
 * 
 */
public class JuvenileJusticeSystemResponseEvent extends ResponseEvent
{
    private String juvenileNumber;

    private String petitionNum;

    private String referralNum;

    private String juvenileFirstName;

    private String juvenileMiddleName;

    private String juvenileLastName;

    private String juvenileTitle;

    private Date dateOfBirth;

    private String juvenileSSN;

    private String probationOfficerOfRecordId;

    private IName probationOfficerOfRecordName;

    private String race;

    private String raceId;

    private String sex;

    private String sexId;

    private String schoolDistrict;

    private String schoolDistrictId;

    private String schoolName;

    private String schoolCodeId;

    private String fathersFirstName;

    private String fathersMiddleName;

    private String fathersLastName;

    private String mothersFirstName;

    private String mothersMiddleName;

    private String mothersLastName;

    private String othersFirstName;

    private String othersMiddleName;

    private String othersLastName;
    
    private String altersFirstName;

    private String altersMiddleName;

    private String altersLastName;

    private String aliasName;

    /**
     * @return aliasName
     */
    public String getAliasName()
    {
        return aliasName;
    }

    /**
     * set aliasName
     */
    public void setAliasName(String aAliasName)
    {
        this.aliasName = aAliasName;
    }

    /**
     * @return
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @return
     */
    public String getJuvenileFirstName()
    {
        return juvenileFirstName;
    }

    /**
     * @return
     */
    public String getJuvenileLastName()
    {
        return juvenileLastName;
    }

    /**
     * @return
     */
    public String getJuvenileMiddleName()
    {
        return juvenileMiddleName;
    }

    /**
     * @return
     */
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }

    /**
     * @return
     */
    public String getJuvenileSSN()
    {
        return juvenileSSN;
    }

    /**
     * @return
     */
    public String getJuvenileTitle()
    {
        return juvenileTitle;
    }

    /**
     * @return
     */
    public String getPetitionNum()
    {
        return petitionNum;
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
    public String getReferralNum()
    {
        return referralNum;
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
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @param juvenileFirstName
     */
    public void setJuvenileFirstName(String juvenileFirstName)
    {
        this.juvenileFirstName = juvenileFirstName;
    }

    /**
     * @param juvenileLastName
     */
    public void setJuvenileLastName(String juvenileLastName)
    {
        this.juvenileLastName = juvenileLastName;
    }

    /**
     * @param juvenileMiddleName
     */
    public void setJuvenileMiddleName(String juvenileMiddleName)
    {
        this.juvenileMiddleName = juvenileMiddleName;
    }

    /**
     * @param juvenileNumber
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }

    /**
     * @param juvenileSSN
     */
    public void setJuvenileSSN(String juvenileSSN)
    {
        this.juvenileSSN = juvenileSSN;
    }

    /**
     * @param juvenileTitle
     */
    public void setJuvenileTitle(String juvenileTitle)
    {
        this.juvenileTitle = juvenileTitle;
    }

    public void setNameSuffix(String aNameSuffix)
    {
        this.juvenileTitle = aNameSuffix;
    }

    /**
     * Returns the Juvenile Title. This method retrieves the Title for now.
     * Later can be used to return the suffix.
     * 
     * @return string
     */
    public String getNameSuffix()
    {
        return this.juvenileTitle;
    }

    /**
     * @param petitionNum
     */
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }

    /**
     * @param race
     */
    public void setRace(String race)
    {
        this.race = race;
    }

    /**
     * @param referralNum
     */
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }

    /**
     * @param schoolDistrict
     */
    public void setSchoolDistrict(String schoolDistrict)
    {
        this.schoolDistrict = schoolDistrict;
    }

    /**
     * @param schoolDistrictNum
     */
    public void setSchoolDistrictId(String aschoolDistrictId)
    {
        this.schoolDistrictId = aschoolDistrictId;
    }

    /**
     * @param schoolName
     */
    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    /**
     * @param schoolNum
     */
    public void setSchoolCodeId(String aschoolCodeId)
    {
        this.schoolCodeId = aschoolCodeId;
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
     * @param raceId
     */
    public void setRaceId(String raceId)
    {
        this.raceId = raceId;
    }

    /**
     * @param string
     */
    public void setFathersFirstName(String fathersFirstName)
    {
        this.fathersFirstName = fathersFirstName;
    }

    public String getFathersFirstName()
    {
        return this.fathersFirstName;
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
    public String getOthersFirstName()
    {
        return othersFirstName;
    }

    /**
     * @return
     */
    public String getOthersLastName()
    {
        return othersLastName;
    }

    /**
     * @param string
     */
    public void setFathersLastName(String fathersLastName)
    {
        this.fathersLastName = fathersLastName;
    }

    /**
     * @param string
     */
    public void setMothersFirstName(String mothersFirstName)
    {
        this.mothersFirstName = mothersFirstName;
    }

    /**
     * @param string
     */
    public void setMothersLastName(String mothersLastName)
    {
        this.mothersLastName = mothersLastName;
    }

    /**
     * @param string
     */
    public void setOthersFirstName(String othersFirstName)
    {
        this.othersFirstName = othersFirstName;
    }

    /**
     * @param string
     */
    public void setOthersLastName(String othersLastName)
    {
        this.othersLastName = othersLastName;
    }

    /**
     * @return
     */
    public String getFathersMiddleName()
    {
        return this.fathersMiddleName;
    }

    /**
     * @return
     */
    public String getMothersMiddleName()
    {
        return this.mothersMiddleName;
    }

    /**
     * @return
     */
    public String getOthersMiddleName()
    {
        return this.othersMiddleName;
    }

    /**
     * @param string
     */
    public void setFathersMiddleName(String fathersMiddleName)
    {
        this.fathersMiddleName = fathersMiddleName;
    }

    /**
     * @param string
     */
    public void setMothersMiddleName(String mothersMiddleName)
    {
        this.mothersMiddleName = mothersMiddleName;
    }

    /**
     * @param string
     */
    public void setOthersMiddleName(String othersMiddleName)
    {
        this.othersMiddleName = othersMiddleName;
    }

    /**
     * This is the probation officer of record Name
     * 
     * @return
     */
    public String getProbationOfficerOfRecordId()
    {
        return this.probationOfficerOfRecordId;
    }

    /**
     * This is the probation officer of record Name
     * 
     * @param string
     */
    public void setProbationOfficerOfRecordId(String probationOfficerOfRecordName)
    {
        this.probationOfficerOfRecordId = probationOfficerOfRecordName;
    }

    /**
     * @return Returns the probationOfficerOfRecordName.
     */
    public IName getProbationOfficerOfRecordName()
    {
        return probationOfficerOfRecordName;
    }

    /**
     * @param probationOfficerOfRecordName
     *            The probationOfficerOfRecordName to set.
     */
    public void setProbationOfficerOfRecordName(IName probationOfficerOfRecordName)
    {
        this.probationOfficerOfRecordName = probationOfficerOfRecordName;
    }

    public String getAltersFirstName()
    {
        return altersFirstName;
    }

    public void setAltersFirstName(String altersFirstName)
    {
        this.altersFirstName = altersFirstName;
    }

    public String getAltersMiddleName()
    {
        return altersMiddleName;
    }

    public void setAltersMiddleName(String altersMiddleName)
    {
        this.altersMiddleName = altersMiddleName;
    }

    public String getAltersLastName()
    {
        return altersLastName;
    }

    public void setAltersLastName(String altersLastName)
    {
        this.altersLastName = altersLastName;
    }
    
}
