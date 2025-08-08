/*
 * Created on Oct 15, 2004
 */
package messaging.juvenilewarrant.reply;

import java.util.Date;

import messaging.contact.domintf.IName;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

import messaging.contact.domintf.INameKey;

/**
 * @author dnikolis
 */
public class JuvenileWarrantResponseEvent extends ResponseEvent implements INameKey, Comparable
{
    private String affadivitStatement;

    private String aliasName;

    private String build;

    private String buildId;

    private String[] cautions;

    private String chargeCourt;

    private String[] charges;

    private String comments;

    private String complexion;

    private String complexionId;

    private String daLogNum;

    private Date dateOfBirth;

    private String dateOfBirthSource;

    private Date dateOfIssue;

    private String eyeColor;

    private String eyeColorId;

    private String fbiNum;

    private Date fileStampDate;

    private String fileStampFirstName;

    private String fileStampLastName;

    private String fileStampLogonId;

    private String fileStampMiddleName;

    private String firstName;

    private String hairColor;

    private String hairColorId;

    private String height;

    private IName juvenileName;

    private Integer juvenileNum;

    private String lastName;

    private String leaOriNum;

    private String middleName;

    private String nameSuffix;

    private String nameKey;

    private String officerId;

    private String otherCautionComments;

    private String petitionNum;

    private String phoneNum;

    private String probationOfficerOfRecordName;

    private String race;

    private String raceId;

    private Date recallDate;

    private String recallFirstName;

    private String recallLastName;

    private String recallLogonId;

    private String recallMiddleName;

    private String recallReason;

    private String recallReasonId;

    private Integer referralNumber;

    private String releaseAssociateNum;

    private String releaseDecision;

    private Date releaseDecisionDate;

    private String releaseDecisionId;

    private String releaseFirstName;

    private String releaseLastName;

    private String releaseMiddleName;

    private String releaseOfficerLogonId;

    private String[] scarsMarks;

    private String schoolDistrict;

    private String schoolDistrictId;

    private String schoolId;

    private String schoolName;

    private String serviceReturnGeneratedStatus;

    private String serviceReturnGeneratedStatusId;

    private String serviceReturnSignatureStatus;

    private String serviceReturnSignatureStatusId;

    private String sex;

    private String sexId;

    private String sid;

    private String ssn;

    private String[] tattoos;

    private String transactionNum;

    private Date transferDate;

    private String transferLocationId;

    private String transferLocationName;

    private String transferOfficerDepartmentId;

    private String transferOfficerId;

    private String unsendNotSignedReason;

    private Date warrantAcknowledgementDate;

    private String warrantAcknowledgeStatus;

    private String warrantAcknowledgeStatusId;

    private Date warrantActivationDate;

    private String warrantActivationStatus;

    private String warrantActivationStatusId;

    private String warrantNum;

    private String warrantOriginatorAgencyId;

    private String warrantOriginatorAgencyName;

    private String warrantOriginatorCourt;

    private String warrantOriginatorId;

    private String warrantOriginatorJudge;

    private String warrantOriginatorName;

    private String warrantSignedStatus;

    private String warrantSignedStatusId;

    private String warrantStatus;

    private String warrantStatusId;

    private String warrantType;

    private String warrantTypeId;

    private String weight;

    private String juvRectype;
    

    /**
     * @return
     */
    public String getAffadivitStatement()
    {
        return affadivitStatement;
    }

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
     * @return Returns the cautions.
     */
    public String[] getCautions()
    {
        return cautions;
    }

    /**
     * @return
     */
    public String getChargeCourt()
    {
        return chargeCourt;
    }

    /**
     * @return Returns the charges.
     */
    public String[] getCharges()
    {
        return charges;
    }

    /**
     * @return
     */
    public String getComments()
    {
        return comments;
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
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @return
     */
    public String getDateOfBirthSource()
    {
        return dateOfBirthSource;
    }

    /**
     * @return
     */
    public Date getDateOfIssue()
    {
        return dateOfIssue;
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
    public String getFbiNum()
    {
        return fbiNum;
    }

    /**
     * @return
     */
    public Date getFileStampDate()
    {
        return fileStampDate;
    }

    /**
     * @return
     */
    public String getFileStampFirstName()
    {
        return fileStampFirstName;
    }

    /**
     * @return
     */
    public String getFileStampLastName()
    {
        return fileStampLastName;
    }

    /**
     * @return
     */
    public String getFileStampLogonId()
    {
        return fileStampLogonId;
    }

    /**
     * @return
     */
    public String getFileStampMiddleName()
    {
        return fileStampMiddleName;
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
     * @return Returns the juvenileName.
     */
    public IName getJuvenileName()
    {
        return juvenileName;
    }

    /**
     * @return
     */
    public Integer getJuvenileNum()
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
    public String getLeaOriNum()
    {
        return leaOriNum;
    }

    /**
     * @return
     */
    public String getMiddleName()
    {
        return middleName;
    }

    public String getNameKey()
    {
        if (nameKey == null)
        {
            StringBuffer key = new StringBuffer(60);
            if (this.lastName != null)
            {
                key.append(this.lastName);
            }
            if (this.firstName != null)
            {
                key.append(this.firstName);
            }
            if (this.middleName != null)
            {
                key.append(this.middleName);
            }
            if (this.warrantNum != null)
            {
                key.append(this.warrantNum);
            }
            this.nameKey = key.toString();
        }
        return this.nameKey;
    }

    /**
     * @return
     */
    public String getNameSuffix()
    {
        return nameSuffix;
    }

    /**
     * @return
     */
    public String getOfficerId()
    {
        return officerId;
    }

    /**
     * @return
     */
    public String getOtherCautionComments()
    {
        return otherCautionComments;
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
    public String getPhoneNum()
    {
        return phoneNum;
    }

    /**
     * @return
     */
    public String getProbationOfficerOfRecordName()
    {
        return probationOfficerOfRecordName;
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
    public Date getRecallDate()
    {
        return recallDate;
    }

    /**
     * @return
     */
    public String getRecallFirstName()
    {
        return recallFirstName;
    }

    /**
     * @return
     */
    public String getRecallLastName()
    {
        return recallLastName;
    }

    /**
     * @return
     */
    public String getRecallLogonId()
    {
        return recallLogonId;
    }

    /**
     * @return
     */
    public String getRecallMiddleName()
    {
        return recallMiddleName;
    }

    /**
     * @return
     */
    public String getRecallReason()
    {
        return recallReason;
    }

    /**
     * @return
     */
    public String getRecallReasonId()
    {
        return recallReasonId;
    }

    /**
     * @return
     */
    public Integer getReferralNumber()
    {
        return referralNumber;
    }

    /**
     * @return
     */
    public String getReleaseAssociateNum()
    {
        return releaseAssociateNum;
    }

    /**
     * @return
     */
    public String getReleaseDecision()
    {
        return releaseDecision;
    }

    /**
     * @return
     */
    public Date getReleaseDecisionDate()
    {
        return releaseDecisionDate;
    }

    /**
     * @return
     */
    public String getReleaseDecisionId()
    {
        return releaseDecisionId;
    }

    /**
     * @return
     */
    public String getReleaseFirstName()
    {
        return releaseFirstName;
    }

    /**
     * @return
     */
    public String getReleaseLastName()
    {
        return releaseLastName;
    }

    /**
     * @return
     */
    public String getReleaseMiddleName()
    {
        return releaseMiddleName;
    }

    /**
     * @return
     */
    public String getReleaseOfficerLogonId()
    {
        return releaseOfficerLogonId;
    }

    /**
     * @return Returns the scarsMarks.
     */
    public String[] getScarsMarks()
    {
        return scarsMarks;
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
    public String getSchoolId()
    {
        return schoolId;
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
    public String getServiceReturnGeneratedStatus()
    {
        return serviceReturnGeneratedStatus;
    }

    /**
     * @return
     */
    public String getServiceReturnGeneratedStatusId()
    {
        return serviceReturnGeneratedStatusId;
    }

    /**
     * @return
     */
    public String getServiceReturnSignatureStatus()
    {
        return serviceReturnSignatureStatus;
    }

    /**
     * @return
     */
    public String getServiceReturnSignatureStatusId()
    {
        return serviceReturnSignatureStatusId;
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

    // Please talk to Hien/Dhanashree before touching this again!
    public String getStringDateOfBirth()
    {
        String myDOB = "";
        if (dateOfBirth != null)
        {
            myDOB = DateUtil.dateToString(dateOfBirth, "MM/dd/yyyy");
        }
        return myDOB;
    }

    /**
     * @return Returns the tattoos.
     */
    public String[] getTattoos()
    {
        return tattoos;
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
    public Date getTransferDate()
    {
        return transferDate;
    }

    /**
     * @return
     */
    public String getTransferLocationId()
    {
        return transferLocationId;
    }

    /**
     * @return
     */
    public String getTransferLocationName()
    {
        return transferLocationName;
    }

    /**
     * @return
     */
    public String getTransferOfficerDepartmentId()
    {
        return transferOfficerDepartmentId;
    }

    /**
     * @return
     */
    public String getTransferOfficerId()
    {
        return transferOfficerId;
    }

    /**
     * @return
     */
    public String getUnsendNotSignedReason()
    {
        return unsendNotSignedReason;
    }

    /**
     * @return
     */
    public Date getWarrantAcknowledgementDate()
    {
        return warrantAcknowledgementDate;
    }

    /**
     * @return
     */
    public String getWarrantAcknowledgeStatus()
    {
        return warrantAcknowledgeStatus;
    }

    /**
     * @return
     */
    public String getWarrantAcknowledgeStatusId()
    {
        return warrantAcknowledgeStatusId;
    }

    /**
     * @return
     */
    public Date getWarrantActivationDate()
    {
        return warrantActivationDate;
    }

    /**
     * @return
     */
    public String getWarrantActivationStatus()
    {
        return warrantActivationStatus;
    }

    /**
     * @return
     */
    public String getWarrantActivationStatusId()
    {
        return warrantActivationStatusId;
    }

    /**
     * @return
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @return
     */
    public String getWarrantOriginatorAgencyId()
    {
        return warrantOriginatorAgencyId;
    }

    /**
     * @return
     */
    public String getWarrantOriginatorAgencyName()
    {
        return warrantOriginatorAgencyName;
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
     * @return Returns the warrantOriginatorJudge.
     */
    public String getWarrantOriginatorJudge()
    {
        return warrantOriginatorJudge;
    }

    /**
     * @return
     */
    public String getWarrantOriginatorName()
    {
        return warrantOriginatorName;
    }

    /**
     * @return
     */
    public String getWarrantSignedStatus()
    {
        return warrantSignedStatus;
    }

    /**
     * @return
     */
    public String getWarrantSignedStatusId()
    {
        return warrantSignedStatusId;
    }

    /**
     * @return
     */
    public String getWarrantStatus()
    {
        return warrantStatus;
    }

    /**
     * @return
     */
    public String getWarrantStatusId()
    {
        return warrantStatusId;
    }

    /**
     * @return
     */
    public String getWarrantType()
    {
        return warrantType;
    }

    /**
     * @return
     */
    public String getWarrantTypeId()
    {
        return warrantTypeId;
    }

    /**
     * @return
     */
    public String getWeight()
    {
        return weight;
    }

    /**
     * @param affadivitStatement
     */
    public void setAffadivitStatement(String affadivitStatement)
    {
        this.affadivitStatement = affadivitStatement;
    }

    /**
     * @param alias
     */
    public void setAliasName(String aliasName)
    {
        this.aliasName = aliasName;
    }

    /**
     * @param string
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
     * @param cautions
     *            The cautions to set.
     */
    public void setCautions(String[] cautions)
    {
        this.cautions = cautions;
    }

    /**
     * @param string
     */
    public void setChargeCourt(String string)
    {
        chargeCourt = string;
    }

    /**
     * @param charges
     *            The charges to set.
     */
    public void setCharges(String[] charges)
    {
        this.charges = charges;
    }

    /**
     * @param comments
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * @param string
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
     * @param daLogNumber
     */
    public void setDaLogNum(String daLogNum)
    {
        this.daLogNum = daLogNum;
    }

    /**
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @param date
     */
    public void setDateOfBirthSource(String dateOfBirthSource)
    {
        this.dateOfBirthSource = dateOfBirthSource;
    }

    /**
     * @param dateOfIssue
     */
    public void setDateOfIssue(Date dateOfIssue)
    {
        this.dateOfIssue = dateOfIssue;
    }

    /**
     * @param string
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
     * @param fbiNumber
     */
    public void setFbiNum(String fbiNum)
    {
        this.fbiNum = fbiNum;
    }

    /**
     * @param fileStampDate
     */
    public void setFileStampDate(Date fileStampDate)
    {
        this.fileStampDate = fileStampDate;
    }

    /**
     * @param fileStampFirstName
     */
    public void setFileStampFirstName(String fileStampFirstName)
    {
        this.fileStampFirstName = fileStampFirstName;
    }

    /**
     * @param fileStampLastName
     */
    public void setFileStampLastName(String fileStampLastName)
    {
        this.fileStampLastName = fileStampLastName;
    }

    /**
     * @param fileStampLogonId
     */
    public void setFileStampLogonId(String fileStampLogonId)
    {
        this.fileStampLogonId = fileStampLogonId;
    }

    /**
     * @param fileStampMiddleName
     */
    public void setFileStampMiddleName(String fileStampMiddleName)
    {
        this.fileStampMiddleName = fileStampMiddleName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @param string
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
     * @param juvenileName
     *            The juvenileName to set.
     */
    public void setJuvenileName(IName juvenileName)
    {
        this.juvenileName = juvenileName;
    }

    /**
     * @param juvenileNumber
     */
    public void setJuvenileNum(Integer juvenileNum)
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
     * @param leaOriNumber
     */
    public void setLeaOriNum(String leaOriNum)
    {
        this.leaOriNum = leaOriNum;
    }

    /**
     * @param middleName
     */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    /**
     * @param nameSuffix
     */
    public void setNameSuffix(String nameSuffix)
    {
        this.nameSuffix = nameSuffix;
    }

    /**
     * @param string
     */
    public void setOfficerId(String string)
    {
        officerId = string;
    }

    /**
     * /**
     * 
     * @param otherCautionComments
     */
    public void setOtherCautionComments(String otherCautionComments)
    {
        this.otherCautionComments = otherCautionComments;
    }

    /**
     * @param string
     */
    public void setPetitionNum(String string)
    {
        petitionNum = string;
    }

    /**
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    /**
     * @param probationOfficerOfRecordName
     */
    public void setProbationOfficerOfRecordName(String probationOfficerOfRecordName)
    {
        this.probationOfficerOfRecordName = probationOfficerOfRecordName;
    }

    /**
     * @param string
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
     * @param recallDate
     */
    public void setRecallDate(Date recallDate)
    {
        this.recallDate = recallDate;
    }

    /**
     * @param recallFirstName
     */
    public void setRecallFirstName(String recallFirstName)
    {
        this.recallFirstName = recallFirstName;
    }

    /**
     * @param recallLastName
     */
    public void setRecallLastName(String recallName)
    {
        this.recallLastName = recallName;
    }

    /**
     * @param recallLogonId
     */
    public void setRecallLogonId(String recallLogonId)
    {
        this.recallLogonId = recallLogonId;
    }

    /**
     * @param recallMiddleName
     */
    public void setRecallMiddleName(String recallMiddleName)
    {
        this.recallMiddleName = recallMiddleName;
    }

    /**
     * @param recallReason
     */
    public void setRecallReason(String recallReason)
    {
        this.recallReason = recallReason;
    }

    /**
     * @param recallReasonId
     */
    public void setRecallReasonId(String recallReasonId)
    {
        this.recallReasonId = recallReasonId;
    }

    /**
     * @param referralNumber
     */
    public void setReferralNumber(Integer referralNumber)
    {
        this.referralNumber = referralNumber;
    }

    /**
     * @param string
     */
    public void setReleaseAssociateNum(String string)
    {
        releaseAssociateNum = string;
    }

    /**
     * @param releaseDecision
     */
    public void setReleaseDecision(String releaseDecision)
    {
        this.releaseDecision = releaseDecision;
    }

    /**
     * @param releaseDecisionDate
     */
    public void setReleaseDecisionDate(Date releaseDecisionDate)
    {
        this.releaseDecisionDate = releaseDecisionDate;
    }

    /**
     * @param string
     */
    public void setReleaseDecisionId(String string)
    {
        releaseDecisionId = string;
    }

    /**
     * @param releaseFirstName
     */
    public void setReleaseFirstName(String releaseFirstName)
    {
        this.releaseFirstName = releaseFirstName;
    }

    /**
     * @param releaseLastName
     */
    public void setReleaseLastName(String releaseLastName)
    {
        this.releaseLastName = releaseLastName;
    }

    /**
     * @param releaseMiddleName
     */
    public void setReleaseMiddleName(String releaseMiddleName)
    {
        this.releaseMiddleName = releaseMiddleName;
    }

    /**
     * @param releaseOfficerLogonId
     */
    public void setReleaseOfficerLogonId(String releaseOfficerLogonId)
    {
        this.releaseOfficerLogonId = releaseOfficerLogonId;
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
     * @param schoolDistrict
     */
    public void setSchoolDistrict(String schoolDistrictName)
    {
        this.schoolDistrict = schoolDistrictName;
    }

    /**
     * @param schoolDistrictId
     */
    public void setSchoolDistrictId(String schoolDistrictId)
    {
        this.schoolDistrictId = schoolDistrictId;
    }

    /**
     * @param schoolId
     */
    public void setSchoolId(String schoolId)
    {
        this.schoolId = schoolId;
    }

    /**
     * @param schoolName
     */
    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    /**
     * @param string
     */
    public void setServiceReturnGeneratedStatus(String string)
    {
        serviceReturnGeneratedStatus = string;
    }

    /**
     * @param string
     */
    public void setServiceReturnGeneratedStatusId(String string)
    {
        serviceReturnGeneratedStatusId = string;
    }

    /**
     * @param string
     */
    public void setServiceReturnSignatureStatus(String string)
    {
        serviceReturnSignatureStatus = string;
    }

    /**
     * @param string
     */
    public void setServiceReturnSignatureStatusId(String string)
    {
        serviceReturnSignatureStatusId = string;
    }

    /**
     * @param string
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
     * @param tattoos
     *            The tattoos to set.
     */
    public void setTattoos(String[] tattoos)
    {
        this.tattoos = tattoos;
    }

    /**
     * @param transactionNumber
     */
    public void setTransactionNum(String transactionNum)
    {
        this.transactionNum = transactionNum;
    }

    /**
     * @param date
     */
    public void setTransferDate(Date transferDate)
    {
        this.transferDate = transferDate;
    }

    /**
     * @param string
     */
    public void setTransferLocationId(String string)
    {
        transferLocationId = string;
    }

    /**
     * @param string
     */
    public void setTransferLocationName(String string)
    {
        transferLocationName = string;
    }

    /**
     * @param string
     */
    public void setTransferOfficerDepartmentId(String string)
    {
        transferOfficerDepartmentId = string;
    }

    /**
     * @param string
     */
    public void setTransferOfficerId(String string)
    {
        transferOfficerId = string;
    }

    /**
     * @param unsendNotSignedReason
     */
    public void setUnsendNotSignedReason(String unsendNotSignedReason)
    {
        this.unsendNotSignedReason = unsendNotSignedReason;
    }

    /**
     * @param warrantAcknowledgementDate
     */
    public void setWarrantAcknowledgementDate(Date warrantAcknowledgementDate)
    {
        this.warrantAcknowledgementDate = warrantAcknowledgementDate;
    }

    /**
     * @param warrantAcknowledgeStatus
     */
    public void setWarrantAcknowledgeStatus(String warrantAcknowledgeStatus)
    {
        this.warrantAcknowledgeStatus = warrantAcknowledgeStatus;
    }

    /**
     * @param warrantAcknowledgeStatusId
     */
    public void setWarrantAcknowledgeStatusId(String warrantAcknowledgeStatusId)
    {
        this.warrantAcknowledgeStatusId = warrantAcknowledgeStatusId;
    }

    /**
     * @param warrantActivationDate
     */
    public void setWarrantActivationDate(Date warrantActivationDate)
    {
        this.warrantActivationDate = warrantActivationDate;
    }

    /**
     * @param warrantActivationStatus
     */
    public void setWarrantActivationStatus(String warrantActivationStatusName)
    {
        this.warrantActivationStatus = warrantActivationStatusName;
    }

    /**
     * @param warrantActivationStatusId
     */
    public void setWarrantActivationStatusId(String warrantActivationStatusId)
    {
        this.warrantActivationStatusId = warrantActivationStatusId;
    }

    /**
     * @param warrantNum
     */
    public void setWarrantNum(String warrantNumber)
    {
        this.warrantNum = warrantNumber;
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorAgencyId(String string)
    {
        warrantOriginatorAgencyId = string;
    }

    /**
     * @param string
     */
    public void setWarrantOriginatorAgencyName(String string)
    {
        warrantOriginatorAgencyName = string;
    }

    /**
     * @param warrantOriginatorCourt
     */
    public void setWarrantOriginatorCourt(String warrantOriginatorCourt)
    {
        this.warrantOriginatorCourt = warrantOriginatorCourt;
    }

    /**
     * @param warrantOriginatorId
     */
    public void setWarrantOriginatorId(String warrantOriginatorId)
    {
        this.warrantOriginatorId = warrantOriginatorId;
    }

    /**
     * @param warrantOriginatorJudge
     *            The warrantOriginatorJudge to set.
     */
    public void setWarrantOriginatorJudge(String warrantOriginatorJudge)
    {
        this.warrantOriginatorJudge = warrantOriginatorJudge;
    }

    /**
     * @param warrantOriginatorName
     */
    public void setWarrantOriginatorName(String warrantOriginatorName)
    {
        this.warrantOriginatorName = warrantOriginatorName;
    }

    /**
     * @param warrantSignedStatus
     */
    public void setWarrantSignedStatus(String warrantSignedStatus)
    {
        this.warrantSignedStatus = warrantSignedStatus;
    }

    /**
     * @param warrantSignedStatusId
     */
    public void setWarrantSignedStatusId(String warrantSignedStatusId)
    {
        this.warrantSignedStatusId = warrantSignedStatusId;
    }

    /**
     * @param warrantStatus
     */
    public void setWarrantStatus(String warrantStatusName)
    {
        this.warrantStatus = warrantStatusName;
    }

    /**
     * @param warrantStatusId
     */
    public void setWarrantStatusId(String warrantStatusId)
    {
        this.warrantStatusId = warrantStatusId;
    }

    /**
     * @param warrantType
     */
    public void setWarrantType(String warrantType)
    {
        this.warrantType = warrantType;
    }

    /**
     * @param warrantTypeId
     */
    public void setWarrantTypeId(String warrantTypeId)
    {
        this.warrantTypeId = warrantTypeId;
    }

    /**
     * @param weight
     */
    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getJuvRectype()
    {
        return juvRectype;
    }

    public void setJuvRectype(String juvRectype)
    {
        this.juvRectype = juvRectype;
    }
    public int compareTo(Object o) {
        if (o == null)
            return 1; // this makes any null objects go to the bottom change this to -1 if you want
        // the top of the list
        if (this.dateOfIssue == null)
            return -1; // this makes any null objects go to the bottom change this to 1 if you want
        // the top of the list
        JuvenileWarrantResponseEvent evt = (JuvenileWarrantResponseEvent) o;
        return this.dateOfIssue.compareTo(evt.getDateOfIssue());
    }
}