package pd.juvenilewarrant;

import java.util.Date;

/**
 * @stereotype control
 */
public class JuvenileWarrantController
{

    /**
     * @roseuid 419BB82C0266
     */
    public JuvenileWarrantController()
    {

    }

    /**
     * @stereotype design
     * @param warrantAcknowledgeDate
     * @param warrantAcknowledgeStatus
     * @param warrantNum
     * @roseuid 419BB8690298
     */
    public void acknowledgeDirectiveToApprehend(Date warrantAcknowledgeDate, String warrantAcknowledgeStatus, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param status
     * @param warrantSignatureStatus
     * @param warrantActivationStatus
     * @param warrantActivationDate
     * @param warrantNum
     * @roseuid 419BB86D02E6
     */
    public void activateDirectiveToApprehend(String status, String warrantSignatureStatus, String warrantActivationStatus,
            Date warrantActivationDate, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantSignedStatus
     * @param status
     * @param warrantActivationStatus
     * @param warrantActivationDate
     * @param warrantNum
     * @roseuid 41E692CB03A6
     */
    public void activateJuvenileArrestWarrant(String warrantSignedStatus, String status, String warrantActivationStatus,
            Date warrantActivationDate, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantActivationStatus
     * @param unSendNotSignedReason
     * @param warrantNum
     * @roseuid 4210F860002E
     */
    public void activateViolationOfProbation(String warrantActivationStatus, String unSendNotSignedReason, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param associateZipCode
     * @param associateStreetSuffix
     * @param associateStreetNum
     * @param associateStreetName
     * @param associateStateId
     * @param associateCounty
     * @param associateCity
     * @param associateApartmentNum
     * @param associateAddressTypeId
     * @param warrantNum
     * @param email3
     * @param email2
     * @param email1
     * @param pager
     * @param workPhone
     * @param homePhone
     * @param faxLocation
     * @param fax
     * @param cellPhone
     * @param asscoiateRace
     * @param associateSex
     * @param relationshipToJuvenile
     * @param associateSsn
     * @param associateId
     * @param associateMiddleName
     * @param associateFirstName
     * @param associateLastName
     * @roseuid 4216049C02B4
     */
    public void createJuvenileAssociate(String associateZipCode, String associateStreetSuffix, String associateStreetNum,
            String associateStreetName, String associateStateId, String associateCounty, String associateCity,
            String associateApartmentNum, String associateAddressTypeId, String warrantNum, String email3, String email2,
            String email1, String pager, String workPhone, String homePhone, String faxLocation, String fax, String cellPhone,
            String asscoiateRace, String associateSex, String relationshipToJuvenile, String associateSsn, int associateId,
            String associateMiddleName, String associateFirstName, String associateLastName)
    {

    }

    /**
     * @stereotype design
     * @param sidNum
     * @param status
     * @param warrantSignatureStatus
     * @param warrantActivationStatus
     * @param warrantActivationDate
     * @param warrantAcknowledgeStatus
     * @param warrantAcknowledgeDate
     * @param warrantNum
     * @param probationOfficerOfRecordId
     * @param warrantType
     * @param officerIdType
     * @param officerId
     * @param fileStampLogonId
     * @param fileStampDate
     * @param fbiNum
     * @param dateOfBirth
     * @param phone
     * @param comments
     * @param statement
     * @param referralNum
     * @param dateOfIssue
     * @param warrantOriginatorId
     * @param schoolName
     * @param schoolDistrict
     * @param juvenileNum
     * @param transactionNum
     * @param daLogNum
     * @param weight
     * @param sex
     * @param race
     * @param ssn
     * @param nameSuffix
     * @param middleName
     * @param lastName
     * @param firstName
     * @param height
     * @param hairColor
     * @param eyeColor
     * @param complexion
     * @param build
     * @param alias
     * @roseuid 419BA625018E
     */
    public void createJuvenileWarrant(String sidNum, String status, String warrantSignatureStatus,
            String warrantActivationStatus, Date warrantActivationDate, String warrantAcknowledgeStatus,
            Date warrantAcknowledgeDate, String warrantNum, String probationOfficerOfRecordId, String warrantType,
            String officerIdType, String officerId, String fileStampLogonId, Date fileStampDate, String fbiNum, Date dateOfBirth,
            String phone, String comments, String statement, String referralNum, Date dateOfIssue, String warrantOriginatorId,
            String schoolName, String schoolDistrict, String juvenileNum, String transactionNum, String daLogNum, String weight,
            String sex, String race, String ssn, String nameSuffix, String middleName, String lastName, String firstName,
            String height, String hairColor, String eyeColor, String complexion, String build, String alias)
    {

    }
    
    /**
     * @stereotype design
     * @roseuid 434551EA012D
     */
    public void saveJuvenilePetitionInformation()
    {

    }
    /**
     * @stereotype design
     * @roseuid 434551EA012D
     */
    public void updateJuvenilePetitionInformation(String juvenileNum,String referralNum, String petitionNum)
    {

    }

    
    /**
     * @stereotype design
     * @roseuid 434551EA012D
     */
    public void deleteJuvenilePetitionInformation()
    {

    }

    
    /**
     * @stereotype design
     * @param referralNum
     * @param petitionNum
     * @roseuid 419BA62600C0
     */
    public void getJJSData(String referralNum, String petitionNum)
    {

    }

    /**
     * @stereotype design
     *  
     */
    public void getJJSPetitions(String referralNum, String juvenileNum, String petitionNum)
    {

    }
    
    /**
     * @stereotype design
     *  
     */
    public void getPetitionBySequence(String referralNum, String juvenileNum, String sequenceNum)
    {

    }
    /**
     * @stereotype design
     *  
     */
    public void getJuvenileCasefileDispositionList(String petitionNum)
    {

    }

    /**
     * @stereotype design
     *  
     */
    public void getJuvenileCasefilePropertyLossDetails(String daLogNum)
    {

    }
    
    /**
     * @stereotype design
     *  
     */
    public void getJuvenileCasefileVictimWitnessDetails(String daLogNum, String sequenceNum)
    {

    }
    /**
     * @stereotype design
     * @param daLogNum
     * @param transactionNum
     * @roseuid 419BA62400E2
     */
    public void getJOTData(String daLogNum, String transactionNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param associateNumber
     * @roseuid 4208F245038E
     */
    public void getJuvenileAssociateData(String warrantNum, String associateNumber)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     */
    public void getJuvenileAssociatesAddressesForWarrant(String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @roseuid 421A433E00BE
     */
    public void getJuvenileWarrantAssociates(String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @roseuid 41F950A2032C
     */
    public void getJuvenileWarrantServiceInfo(String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantStatus
     * @param warrantActivationStatus
     * @param lastName
     * @param firstName
     * @param warrantTypeId
     * @param warrantNum
     * @roseuid 41F90413005D
     */
    public void getJuvenileWarrantsForAcknowledge(int warrantStatus, String warrantActivationStatus, String lastName,
            String firstName, String warrantTypeId, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantSignedStatus
     * @param warrantStatus
     * @param warrantActivationStatus
     * @param warrantAcknowledgeStatus
     * @param warrantTypeId
     * @param warrantNum
     * @roseuid 41F9041701F0
     */
    public void getJuvenileWarrantsForActivate(String warrantSignedStatus, int warrantStatus, String warrantActivationStatus,
            String warrantAcknowledgeStatus, String warrantTypeId, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param lastName
     * @param firstName
     * @param warrantNum
     * @roseuid 41F9057B02EA
     */
    public void getJuvenileWarrantsForInactivate(String lastName, String firstName, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param releaseDecision
     * @param juvenileLastName
     * @param juvenileFirstName
     * @param warrantNum
     * @roseuid 4213705403DD
     */
    public void getJuvenileWarrantsForManageReleaseDecision(String releaseDecision, int juvenileLastName, int juvenileFirstName,
            String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param releaseDecision
     * @param juvenileLastName
     * @param juvenileFirstName
     * @param warrantNum
     * @roseuid 41FFC64D00A2
     */
    public void getJuvenileWarrantsForManageReleaseTo(String releaseDecision, int juvenileLastName, int juvenileFirstName,
            String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param releaseDecision
     * @param juvenileLastName
     * @param juvenileFirstName
     * @param warrantNum
     * @roseuid 41FFD9A6013E
     */
    public void getJuvenileWarrantsForManageReleaseToJP(String releaseDecision, int juvenileLastName, int juvenileFirstName,
            String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param releaseDecision
     * @param juvenileLastName
     * @param juvenileFirstName
     * @param warrantNum
     * @roseuid 41FFD9A4039F
     */
    public void getJuvenileWarrantsForManageReleaseToPerson(String releaseDecision, int juvenileLastName, int juvenileFirstName,
            String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param juvenileLastName
     * @param juvenileFirstName
     * @roseuid 41FFC5E6037C
     */
    public void getJuvenileWarrantsForManageService(String warrantNum, int juvenileLastName, int juvenileFirstName)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param juvenileLastName
     * @param juvenileFirstName
     * @roseuid 41FFC5E700FF
     */
    public void getJuvenileWarrantsForProcessService(String warrantNum, int juvenileLastName, int juvenileFirstName)
    {

    }

    /**
     * @stereotype design
     * @param lastName
     * @param firstName
     * @param warrantNum
     * @roseuid 41F906390202
     */
    public void getJuvenileWarrantsForRecall(String lastName, String firstName, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param juvenileLastName
     * @param juvenileFirstName
     * @roseuid 41FFC64E00A2
     */
    public void getJuvenileWarrantsForRelease(String warrantNum, int juvenileLastName, int juvenileFirstName)
    {

    }

    /**
     * @stereotype design
     * @param juvenileLastName
     * @param juvenileFirstName
     * @param juvenileNum
     * @roseuid 41FFC66A0331
     */
    public void getJuvenileWarrantsForServiceSignatureUpdate(int juvenileLastName, int juvenileFirstName, String juvenileNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantTypeId
     * @param warrantOriginatorId
     * @param warrantNum
     * @param agencyId
     * @param lastName
     * @param firstName
     * @roseuid 421B755D0235
     */
    public void getJuvenileWarrantsForView(String warrantTypeId, String warrantOriginatorId, String warrantNum, String agencyId,
            String lastName, String firstName)
    {

    }

    /**
     * @stereotype design
     * @param lastName
     * @param firstName
     * @param warrantType
     * @param warrantNum
     */
    public void getJuvenileWarrantsList(String lastName, String firstName, String warrantType, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @roseuid 4210F85F025E
     */
    public void getVOPJuvenileWarrantForActivate()
    {

    }

    /**
     * @stereotype design
     * @param recallReason
     * @param warrantNum
     * @roseuid 41F55B250364
     */
    public void inactivateJuvenileWarrant(String recallReason, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param warrantActivationDate
     * @param petitionNum
     * @param juvenileNum
     * @param middleName
     * @param lastName
     * @param firstName
     * @param courtId
     * @roseuid 419BA62602B4
     */
    public void notifyJuvenileWantedOnOIC(String warrantNum, Date warrantActivationDate, String petitionNum, String juvenileNum,
            String middleName, String lastName, String firstName, String courtId)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param comments
     * @param warrantActivationStatus
     * @param status
     * @roseuid 419BA62602E6
     */
    public void notifyOICUpdatesRequired(String warrantNum, String comments, String warrantActivationStatus, String status)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param comments
     * @param status
     * @param warrantActivationStatus
     * @roseuid 419BA6260324
     */
    public void notifyToNotIssueOIC(String warrantNum, String comments, String status, String warrantActivationStatus)
    {

    }

    /**
     * @stereotype design
     * @roseuid 41F950A30205
     */
    public void processWarrantReturnService()
    {

    }

    /**
     * @stereotype design
     * @param warrantActivationDate
     * @param warrantActivationStatus
     * @param warrantStatus
     * @param recallReason
     * @param recallLogonId
     * @param recallDate
     * @param warrantNum
     * @roseuid 41F798D5026B
     */
    public void recallJuvenileWarrant(Date warrantActivationDate, String warrantActivationStatus, int warrantStatus,
            String recallReason, String recallLogonId, Date recallDate, String warrantNum)
    {

    }

    /**
     * @stereotype design
     * @param judgeEmailAddres
     * @param districtClerkEmailAddress
     * @param warrantNum
     * @param middleName
     * @param lastName
     * @param firstName
     * @roseuid 419BA6240039
     */
    public void requestOICSignature(String judgeEmailAddres, String districtClerkEmailAddress, String warrantNum,
            String middleName, String lastName, String firstName)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @roseuid 434551EA012D
     */
    public void sendReleaseDecisionTimeExpiredNotification(String warrantNum, String emailTo, String emailFrom,
            Date releaseDecisionDate, String releaseDecision, String juvenileFirstName, String juvenileLastName,
            String officerFirstName, String officerLastName, String managerFirstName, String managerLastName, String contactPhone)
    {

    }

    /**
     * @stereotype design
     * @param status
     * @param warrantSignatureStatus
     * @param warrantActivationDate
     * @param warrantActivationStatus
     * @roseuid 419BA6260279
     */
    public void setOICWarrantStatus(String status, String warrantSignatureStatus, Date warrantActivationDate,
            String warrantActivationStatus)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param relationshipToJuvenile
     * @param juvenileAssociateAddressId
     * @param associateZipCode
     * @param associateStreetSuffix
     * @param associateStreetNum
     * @param associateStreetName
     * @param associateStateId
     * @param associateNumber
     * @param associateMiddleName
     * @param associateLastName
     * @param associateFirstName
     * @param associateCity
     * @param associateApartmentNum
     * @param associateAddressTypeId
     * @roseuid 41E59AEE0011
     */
    public void updateJuvenileAssociate(String warrantNum, String relationshipToJuvenile, String juvenileAssociateAddressId,
            String associateZipCode, String associateStreetSuffix, String associateStreetNum, String associateStreetName,
            String associateStateId, String associateNumber, String associateMiddleName, String associateLastName,
            String associateFirstName, String associateCity, String associateApartmentNum, String associateAddressTypeId)
    {

    }

    /**
     * @stereotype design
     * @param logonId
     * @param warrantNum
     * @param releaseDecisionTime
     * @param releaseDecisionDate
     * @param releaseDecision
     * @roseuid 41F95F450300
     */
    public void updateJuvenileReleaseInfo(String logonId, String warrantNum, int releaseDecisionTime, Date releaseDecisionDate,
            String releaseDecision)
    {

    }

    /**
     * @stereotype design
     * @param agencyId
     * @param officerIdType
     * @param otherIdNum
     * @param badgeNum
     * @param warrantNum
     * @param transferTime
     * @param transferLocation
     * @param transferDate
     * @roseuid 41F95F4602F0
     */
    public void updateJuvenileReleaseToJPInfo(String agencyId, String officerIdType, String otherIdNum, String badgeNum,
            String warrantNum, int transferTime, int transferLocation, int transferDate)
    {

    }

    /**
     * @stereotype design
     * @param relationshipToJuvenile
     * @param associateZipCode
     * @param associateStreetSuffix
     * @param associateStreetNum
     * @param associateStreetName
     * @param associateStateId
     * @param associateNumber
     * @param associateMiddleName
     * @param associateLastName
     * @param associateId
     * @param associateFirstName
     * @param associateCity
     * @param associateApartmentNum
     * @param associateAddressTypeId
     * @param associateId
     * @param warrantNum
     * @param releaseToStatus
     * @roseuid 41F95F44034F
     */
    public void updateJuvenileReleaseToPersonInfo(String relationshipToJuvenile, String associateZipCode,
            String associateStreetSuffix, String associateStreetNum, String associateStreetName, String associateStateId,
            String associateNumber, String associateMiddleName, String associateLastName, int associateId,
            String associateFirstName, String associateCity, String associateApartmentNum, String associateAddressTypeId,
            String warrantNum, int releaseToStatus)
    {

    }

    /**
     * @stereotype design
     * @param sidNum
     * @param status
     * @param warrantSignedStatus
     * @param warrantActivationDate
     * @param warrantActivationStatus
     * @param warrantAcknowledgeStatus
     * @param warrantActivationDate
     * @param warrantNum
     * @param probationOfficerOfRecordId
     * @param warrantTypeId
     * @param officerIdType
     * @param officerId
     * @param fileStampLogonId
     * @param fileStampDate
     * @param fbiNum
     * @param dateOfBirth
     * @param comments
     * @param dateOfIssue
     * @param warrantOriginatorId
     * @param schoolName
     * @param schoolDistrict
     * @param juvenileNum
     * @param transactionNum
     * @param daLogNum
     * @param weight
     * @param sex
     * @param race
     * @param height
     * @param hairColor
     * @param eyeColor
     * @param complexion
     * @param build
     * @param nameSuffix
     * @param lastName
     * @param firstName
     * @param middleName
     * @param alias
     * @roseuid 41DAB53A0286
     */
    public void updateJuvenileWarrant(String sidNum, String status, String warrantSignedStatus, Date warrantActivationDate,
            String warrantActivationStatus, String warrantAcknowledgeStatus, String warrantNum,
            String probationOfficerOfRecordId, String warrantTypeId, String officerIdType, String officerId,
            String fileStampLogonId, Date fileStampDate, String fbiNum, Date dateOfBirth, String comments, Date dateOfIssue,
            String warrantOriginatorId, String schoolName, String schoolDistrict, String juvenileNum, String transactionNum,
            String daLogNum, String weight, String sex, String race, String height, String hairColor, String eyeColor,
            String complexion, String build, String nameSuffix, String lastName, String firstName, String middleName, String alias)
    {

    }

    /**
     * @stereotype design
     * @param officerId
     * @param logonId
     * @param serviceZipCode
     * @param serviceStreetNum
     * @param serviceStreetName
     * @param serviceStatus
     * @param serviceDate
     * @param serviceCity
     * @param perDiem
     * @param mileage
     * @param comments
     * @param airFare
     * @roseuid 41F950A20265
     */
    public void updateJuvenileWarrantService(String officerId, String logonId, String serviceZipCode, String serviceStreetNum,
            String serviceStreetName, String serviceStatus, Date serviceDate, String serviceCity, String perDiem, String mileage,
            int comments, String airFare)
    {

    }

    /**
     * @stereotype design
     * @param warrantNum
     * @param signatureOption
     * @param status
     * @param warrantActivationDate
     * @param warrantSignatureStatus
     * @param warrantActivationStatus
     * @roseuid 41AF5ED4037A
     */
    public void updateOICSignatureStatus(String warrantNum, String signatureOption, String status, Date warrantActivationDate,
            String warrantSignatureStatus, String warrantActivationStatus)
    {

    }

    /**
     * @stereotype design
     * @param serviceReturnSignatureStatus
     * @param warrantNumber
     * @roseuid 41F1732A034B
     */
    public void updateWarrantServiceSignatureStatus(String serviceReturnSignatureStatus, String warrantNumber)
    {

    }
    
    /**
     * @stereotype design
     * @param warrantNum
     * @roseuid 41F950A2032C
     */
    public void getJuvenileWarrantCharge(String warrantNum)
    {

    }
    
    /**
     * @stereotype design
     *  
     */
    public void getJJSPetitionsByJuvNum(String juvenileNum)
    {

    }
    
    /**
     * @stereotype design
     *  
     */
    public void updateJJSPetitionsTerminationDate(String juvenileNum)
    {

    }
}