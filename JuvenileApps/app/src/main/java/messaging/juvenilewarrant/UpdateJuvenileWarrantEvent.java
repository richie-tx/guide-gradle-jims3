package messaging.juvenilewarrant;

import mojo.km.messaging.Composite.CompositeRequest;

import java.util.Date;

public class UpdateJuvenileWarrantEvent extends CompositeRequest
{
    private String aliasName;

    private String build;

    private String[] cautions;

    private String[] charges;

    private String comments;

    private String complexion;

    private String daLogNum;

    private Date dateOfBirth;

    private Date dateOfIssue;

    private String eyeColor;

    private String fbiNum;

    private Date fileStampDate;

    private String fileStampLogonId;

    private String firstName;

    private String hairColor;

    private String height;

    public Integer juvenileNum;

    private String lastName;

    private String middleName;

    private String nameSuffix;

    private String officerId;

    private String officerIdType;

    private String phone;

    private String probationOfficerOfRecordId;

    private String probationOfficerOfRecordName;

    private String race;

    private Integer referralNum;

    private String[] scarsMarks;
    
    private String schoolDistrict;

    private String schoolName;

    private String sex;

    private String sidNum;

    private String ssn;

    private String statement;

    private String status;

    private String[] tattoos;

    private String transactionNum;

    private Date warrantAcknowledgeDate;

    private String warrantAcknowledgeStatus;

    private Date warrantActivationDate;

    private String warrantActivationStatus;

    private String warrantNum;

    private String warrantOriginatorCourt;

    private String warrantOriginatorId;

    private String warrantOriginatorName;

    private String warrantSignedStatus;

    private String warrantType;

    private String weight;

    private String[] deletedCautions;

    private String[] deletedScarsMarks;

    private String[] deletedTattoos;

    private String[] deletedCharges;

    /**
     * @roseuid 421B8C0403C8
     */
    public UpdateJuvenileWarrantEvent()
    {

    }

    /**
     * Access method for the aliasName property.
     * 
     * @return the current value of the aliasName property
     */
    public String getAliasName()
    {
        return aliasName;
    }

    /**
     * Access method for the build property.
     * 
     * @return the current value of the build property
     */
    public String getBuild()
    {
        return build;
    }

    /**
     * @return Returns the cautions.
     */
    public String[] getCautions()
    {
        return cautions;
    }

    /**
     * @return
     */
    public String[] getCharges()
    {
        return charges;
    }

    /**
     * Access method for the comments property.
     * 
     * @return the current value of the comments property
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * Access method for the complexion property.
     * 
     * @return the current value of the complexion property
     */
    public String getComplexion()
    {
        return complexion;
    }

    /**
     * Access method for the daLogNum property.
     * 
     * @return the current value of the daLogNum property
     */
    public String getDaLogNum()
    {
        return daLogNum;
    }

    /**
     * Access method for the dateOfBirth property.
     * 
     * @return the current value of the dateOfBirth property
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Access method for the dateOfIssue property.
     * 
     * @return the current value of the dateOfIssue property
     */
    public Date getDateOfIssue()
    {
        return dateOfIssue;
    }

    /**
     * Access method for the eyeColor property.
     * 
     * @return the current value of the eyeColor property
     */
    public String getEyeColor()
    {
        return eyeColor;
    }

    /**
     * Access method for the fbiNum property.
     * 
     * @return the current value of the fbiNum property
     */
    public String getFbiNum()
    {
        return fbiNum;
    }

    /**
     * Access method for the fileStampDate property.
     * 
     * @return the current value of the fileStampDate property
     */
    public Date getFileStampDate()
    {
        return fileStampDate;
    }

    /**
     * Access method for the fileStampLogonId property.
     * 
     * @return the current value of the fileStampLogonId property
     */
    public String getFileStampLogonId()
    {
        return fileStampLogonId;
    }

    /**
     * Access method for the firstName property.
     * 
     * @return the current value of the firstName property
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Access method for the hairColor property.
     * 
     * @return the current value of the hairColor property
     */
    public String getHairColor()
    {
        return hairColor;
    }

    /**
     * Access method for the height property.
     * 
     * @return the current value of the height property
     */
    public String getHeight()
    {
        return height;
    }

    /**
     * Access method for the juvenileNum property.
     * 
     * @return the current value of the juvenileNum property
     */
    public Integer getJuvenileNum()
    {
        return juvenileNum;
    }

    /**
     * Access method for the lastName property.
     * 
     * @return the current value of the lastName property
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Access method for the middleName property.
     * 
     * @return the current value of the middleName property
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * Access method for the nameSuffix property.
     * 
     * @return the current value of the nameSuffix property
     */
    public String getNameSuffix()
    {
        return nameSuffix;
    }

    /**
     * Access method for the officerId property.
     * 
     * @return the current value of the officerId property
     */
    public String getOfficerId()
    {
        return officerId;
    }

    /**
     * Access method for the officerIdType property.
     * 
     * @return the current value of the officerIdType property
     */
    public String getOfficerIdType()
    {
        return officerIdType;
    }

    /**
     * Access method for the phone property.
     * 
     * @return the current value of the phone property
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @return
     */
    public String getProbationOfficerOfRecordId()
    {
        return probationOfficerOfRecordId;
    }

    /**
     * Access method for the probationOfficerOfRecordName property.
     * 
     * @return the current value of the probationOfficerOfRecordName property
     */
    public String getProbationOfficerOfRecordName()
    {
        return probationOfficerOfRecordName;
    }

    /**
     * Access method for the race property.
     * 
     * @return the current value of the race property
     */
    public String getRace()
    {
        return race;
    }

    /**
     * Access method for the referralNum property.
     * 
     * @return the current value of the referralNum property
     */
    public Integer getReferralNum()
    {
        return referralNum;
    }

    /**
     * @return Returns the scarsMarks.
     */
    public String[] getScarsMarks()
    {
        return scarsMarks;
    }
 
    /**
     * Access method for the schoolDistrict property.
     * 
     * @return the current value of the schoolDistrict property
     */
    public String getSchoolDistrict()
    {
        return schoolDistrict;
    }

    /**
     * Access method for the schoolName property.
     * 
     * @return the current value of the schoolName property
     */
    public String getSchoolName()
    {
        return schoolName;
    }

    /**
     * Access method for the sex property.
     * 
     * @return the current value of the sex property
     */
    public String getSex()
    {
        return sex;
    }

    /**
     * Access method for the sidNum property.
     * 
     * @return the current value of the sidNum property
     */
    public String getSidNum()
    {
        return sidNum;
    }

    /**
     * Access method for the ssn property.
     * 
     * @return the current value of the ssn property
     */
    public String getSsn()
    {
        return ssn;
    }

    /**
     * Access method for the statement property.
     * 
     * @return the current value of the statement property
     */
    public String getStatement()
    {
        return statement;
    }

    /**
     * Access method for the status property.
     * 
     * @return the current value of the status property
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @return Returns the tattoos.
     */
    public String[] getTattoos()
    {
        return tattoos;
    }

    /**
     * Access method for the transactionNum property.
     * 
     * @return the current value of the transactionNum property
     */
    public String getTransactionNum()
    {
        return transactionNum;
    }

    /**
     * @return
     */
    public Date getWarrantAcknowledgeDate()
    {
        return warrantAcknowledgeDate;
    }

    /**
     * Access method for the warrantAcknowledgeStatus property.
     * 
     * @return the current value of the warrantAcknowledgeStatus property
     */
    public String getWarrantAcknowledgeStatus()
    {
        return warrantAcknowledgeStatus;
    }

    /**
     * Access method for the warrantActivationDate property.
     * 
     * @return the current value of the warrantActivationDate property
     */
    public Date getWarrantActivationDate()
    {
        return warrantActivationDate;
    }

    /**
     * Access method for the warrantActivationStatus property.
     * 
     * @return the current value of the warrantActivationStatus property
     */
    public String getWarrantActivationStatus()
    {
        return warrantActivationStatus;
    }

    /**
     * Access method for the warrantNum property.
     * 
     * @return the current value of the warrantNum property
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @return
     */
    public String getWarrantOriginatorCourt()
    {
        return warrantOriginatorCourt;
    }

    /**
     * @return
     */
    public String getWarrantOriginatorId()
    {
        return warrantOriginatorId;
    }

    /**
     * Access method for the warrantOriginatorName property.
     * 
     * @return the current value of the warrantOriginatorName property
     */
    public String getWarrantOriginatorName()
    {
        return warrantOriginatorName;
    }

    /**
     * Access method for the warrantSignedStatus property.
     * 
     * @return the current value of the warrantSignedStatus property
     */
    public String getWarrantSignedStatus()
    {
        return warrantSignedStatus;
    }

    /**
     * Access method for the warrantType property.
     * 
     * @return the current value of the warrantType property
     */
    public String getWarrantType()
    {
        return warrantType;
    }

    /**
     * Access method for the weight property.
     * 
     * @return the current value of the weight property
     */
    public String getWeight()
    {
        return weight;
    }

    /**
     * Sets the value of the aliasName property.
     * 
     * @param aAliasName
     *            the new value of the aliasName property
     */
    public void setAliasName(String aAliasName)
    {
        aliasName = aAliasName;
    }

    /**
     * Sets the value of the build property.
     * 
     * @param aBuild
     *            the new value of the build property
     */
    public void setBuild(String aBuild)
    {
        build = aBuild;
    }

    /**
     * @param cautions
     *            The cautions to set.
     */
    public void setCautions(String[] cautions)
    {
        this.cautions = cautions;
    }

    /**
     * @param strings
     */
    public void setCharges(String[] strings)
    {
        charges = strings;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param aComments
     *            the new value of the comments property
     */
    public void setComments(String aComments)
    {
        comments = aComments;
    }

    /**
     * Sets the value of the complexion property.
     * 
     * @param aComplexion
     *            the new value of the complexion property
     */
    public void setComplexion(String aComplexion)
    {
        complexion = aComplexion;
    }

    /**
     * Sets the value of the daLogNum property.
     * 
     * @param aDaLogNum
     *            the new value of the daLogNum property
     */
    public void setDaLogNum(String aDaLogNum)
    {
        daLogNum = aDaLogNum;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param aDateOfBirth
     *            the new value of the dateOfBirth property
     */
    public void setDateOfBirth(Date aDateOfBirth)
    {
        dateOfBirth = aDateOfBirth;
    }

    /**
     * Sets the value of the dateOfIssue property.
     * 
     * @param aDateOfIssue
     *            the new value of the dateOfIssue property
     */
    public void setDateOfIssue(Date aDateOfIssue)
    {
        dateOfIssue = aDateOfIssue;
    }

    /**
     * Sets the value of the eyeColor property.
     * 
     * @param aEyeColor
     *            the new value of the eyeColor property
     */
    public void setEyeColor(String aEyeColor)
    {
        eyeColor = aEyeColor;
    }

    /**
     * Sets the value of the fbiNum property.
     * 
     * @param aFbiNum
     *            the new value of the fbiNum property
     */
    public void setFbiNum(String aFbiNum)
    {
        fbiNum = aFbiNum;
    }

    /**
     * Sets the value of the fileStampDate property.
     * 
     * @param aFileStampDate
     *            the new value of the fileStampDate property
     */
    public void setFileStampDate(Date aFileStampDate)
    {
        fileStampDate = aFileStampDate;
    }

    /**
     * Sets the value of the fileStampLogonId property.
     * 
     * @param aFileStampLogonId
     *            the new value of the fileStampLogonId property
     */
    public void setFileStampLogonId(String aFileStampLogonId)
    {
        fileStampLogonId = aFileStampLogonId;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param aFirstName
     *            the new value of the firstName property
     */
    public void setFirstName(String aFirstName)
    {
        firstName = aFirstName;
    }

    /**
     * Sets the value of the hairColor property.
     * 
     * @param aHairColor
     *            the new value of the hairColor property
     */
    public void setHairColor(String aHairColor)
    {
        hairColor = aHairColor;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param aHeight
     *            the new value of the height property
     */
    public void setHeight(String aHeight)
    {
        height = aHeight;
    }

    /**
     * Sets the value of the juvenileNum property.
     * 
     * @param aJuvenileNum
     *            the new value of the juvenileNum property
     */
    public void setJuvenileNum(Integer aJuvenileNum)
    {
        juvenileNum = aJuvenileNum;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param aLastName
     *            the new value of the lastName property
     */
    public void setLastName(String aLastName)
    {
        lastName = aLastName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param aMiddleName
     *            the new value of the middleName property
     */
    public void setMiddleName(String aMiddleName)
    {
        middleName = aMiddleName;
    }

    /**
     * Sets the value of the nameSuffix property.
     * 
     * @param aNameSuffix
     *            the new value of the nameSuffix property
     */
    public void setNameSuffix(String aNameSuffix)
    {
        nameSuffix = aNameSuffix;
    }

    /**
     * Sets the value of the officerId property.
     * 
     * @param aOfficerId
     *            the new value of the officerId property
     */
    public void setOfficerId(String aOfficerId)
    {
        officerId = aOfficerId;
    }

    /**
     * Sets the value of the officerIdType property.
     * 
     * @param aOfficerIdType
     *            the new value of the officerIdType property
     */
    public void setOfficerIdType(String aOfficerIdType)
    {
        officerIdType = aOfficerIdType;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param aPhone
     *            the new value of the phone property
     */
    public void setPhone(String aPhone)
    {
        phone = aPhone;
    }

    /**
     * @param string
     */
    public void setProbationOfficerOfRecordId(String string)
    {
        probationOfficerOfRecordId = string;
    }

    /**
     * Sets the value of the probationOfficerOfRecordName property.
     * 
     * @param aProbationOfficerOfRecordName
     *            the new value of the probationOfficerOfRecordName property
     */
    public void setProbationOfficerOfRecordName(String aProbationOfficerOfRecordName)
    {
        probationOfficerOfRecordName = aProbationOfficerOfRecordName;
    }

    /**
     * Sets the value of the race property.
     * 
     * @param aRace
     *            the new value of the race property
     */
    public void setRace(String aRace)
    {
        race = aRace;
    }

    /**
     * Sets the value of the referralNum property.
     * 
     * @param aReferralNum
     *            the new value of the referralNum property
     */
    public void setReferralNum(Integer aReferralNum)
    {
        referralNum = aReferralNum;
    }

    /**
     * @param scarsMarks
     *            The scarsMarks to set.
     */
    public void setScarsMarks(String[] scarsMarks)
    {
        this.scarsMarks = scarsMarks;
    }
    
    /**
     * Sets the value of the schoolDistrict property.
     * 
     * @param aSchoolDistrict
     *            the new value of the schoolDistrict property
     */
    public void setSchoolDistrict(String aSchoolDistrict)
    {
        schoolDistrict = aSchoolDistrict;
    }

    /**
     * Sets the value of the schoolName property.
     * 
     * @param aSchoolName
     *            the new value of the schoolName property
     */
    public void setSchoolName(String aSchoolName)
    {
        schoolName = aSchoolName;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param aSex
     *            the new value of the sex property
     */
    public void setSex(String aSex)
    {
        sex = aSex;
    }

    /**
     * Sets the value of the sidNum property.
     * 
     * @param aSidNum
     *            the new value of the sidNum property
     */
    public void setSidNum(String aSidNum)
    {
        sidNum = aSidNum;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param aSsn
     *            the new value of the ssn property
     */
    public void setSsn(String aSsn)
    {
        ssn = aSsn;
    }

    /**
     * Sets the value of the statement property.
     * 
     * @param aStatement
     *            the new value of the statement property
     */
    public void setStatement(String aStatement)
    {
        statement = aStatement;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param aStatus
     *            the new value of the status property
     */
    public void setStatus(String aStatus)
    {
        status = aStatus;
    }

    /**
     * @param tattoos
     *            The tattoos to set.
     */
    public void setTattoos(String[] tattoos)
    {
        this.tattoos = tattoos;
    }

    /**
     * Sets the value of the transactionNum property.
     * 
     * @param aTransactionNum
     *            the new value of the transactionNum property
     */
    public void setTransactionNum(String aTransactionNum)
    {
        transactionNum = aTransactionNum;
    }

    /**
     * @param date
     */
    public void setWarrantAcknowledgeDate(Date date)
    {
        warrantAcknowledgeDate = date;
    }

    /**
     * Sets the value of the warrantAcknowledgeStatus property.
     * 
     * @param aWarrantAcknowledgeStatus
     *            the new value of the warrantAcknowledgeStatus property
     */
    public void setWarrantAcknowledgeStatus(String aWarrantAcknowledgeStatus)
    {
        warrantAcknowledgeStatus = aWarrantAcknowledgeStatus;
    }

    /**
     * Sets the value of the warrantActivationDate property.
     * 
     * @param aWarrantActivationDate
     *            the new value of the warrantActivationDate property
     */
    public void setWarrantActivationDate(Date aWarrantActivationDate)
    {
        this.warrantActivationDate = aWarrantActivationDate;
    }

    /**
     * Sets the value of the warrantActivationStatus property.
     * 
     * @param aWarrantActivationStatus
     *            the new value of the warrantActivationStatus property
     */
    public void setWarrantActivationStatus(String aWarrantActivationStatus)
    {
        warrantActivationStatus = aWarrantActivationStatus;
    }

    /**
     * Sets the value of the warrantNum property.
     * 
     * @param aWarrantNum
     *            the new value of the warrantNum property
     */
    public void setWarrantNum(String aWarrantNum)
    {
        warrantNum = aWarrantNum;
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorCourt(String string)
    {
        warrantOriginatorCourt = string;
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorId(String string)
    {
        warrantOriginatorId = string;
    }

    /**
     * Sets the value of the warrantOriginatorName property.
     * 
     * @param aWarrantOriginatorName
     *            the new value of the warrantOriginatorName property
     */
    public void setWarrantOriginatorName(String aWarrantOriginatorName)
    {
        warrantOriginatorName = aWarrantOriginatorName;
    }

    /**
     * Sets the value of the warrantSignedStatus property.
     * 
     * @param aWarrantSignedStatus
     *            the new value of the warrantSignedStatus property
     */
    public void setWarrantSignedStatus(String aWarrantSignedStatus)
    {
        warrantSignedStatus = aWarrantSignedStatus;
    }

    /**
     * Sets the value of the warrantType property.
     * 
     * @param aWarrantType
     *            the new value of the warrantType property
     */
    public void setWarrantType(String aWarrantType)
    {
        warrantType = aWarrantType;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param aWeight
     *            the new value of the weight property
     */
    public void setWeight(String aWeight)
    {
        weight = aWeight;
    }

    /**
     * @return Returns the deletedCautions.
     */
    public String[] getDeletedCautions()
    {
        return deletedCautions;
    }

    /**
     * @param deletedCautions
     *            The deletedCautions to set.
     */
    public void setDeletedCautions(String[] deletedCautions)
    {
        this.deletedCautions = deletedCautions;
    }

    /**
     * @return Returns the deletedCharges.
     */
    public String[] getDeletedCharges()
    {
        return deletedCharges;
    }

    /**
     * @param deletedCharges
     *            The deletedCharges to set.
     */
    public void setDeletedCharges(String[] deletedCharges)
    {
        this.deletedCharges = deletedCharges;
    }

    /**
     * @return Returns the deletedScars.
     */
    public String[] getDeletedScarsMarks()
    {
        return deletedScarsMarks;
    }

    /**
     * @param deletedScars
     *            The deletedScars to set.
     */
    public void setDeletedScarsMarks(String[] deletedScarsMarks)
    {
        this.deletedScarsMarks = deletedScarsMarks;
    }

    /**
     * @return Returns the deletedTattoos.
     */
    public String[] getDeletedTattoos()
    {
        return deletedTattoos;
    }

    /**
     * @param deletedTattoos
     *            The deletedTattoos to set.
     */
    public void setDeletedTattoos(String[] deletedTattoos)
    {
        this.deletedTattoos = deletedTattoos;
    }
}
