//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\JuvenileController.java

package pd.juvenile;

import java.util.Date;

/**
 * @stereotype control
 */
public class JuvenileController
{

    /**
     * @roseuid 42B18E480242
     */
    public JuvenileController()
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B1830600BB
     */
    public void getJuvenileContacts(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B1830600BB
     */
    public void validateJuvSSNName(String lastName, String firstName, String ssn)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     */
    public void getJuvenileContactNamesForGoal(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B1830603D8
     */
    public void getJuvenilePhysicalAttributes(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B18307015A
     */
    public void getJuvenilePhotos(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B183070206
     */
    public void getJuvenileDrugs(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B183070206
     */
    public void getJuvenileGangs(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B18307038D
     */
    public void getJuvenileJobs(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param jobId
     */
    public void getJuvenileJob(String jobId)
    {
    }

    /**
     * @stereotype design
     * @param zipCode
     * @param workPhone
     * @param streetType
     * @param streetNum
     * @param streetName
     * @param state
     * @param previousAgencyInvolvement
     * @param pager
     * @param middleName
     * @param lastName
     * @param juvenileNum
     * @param firstName
     * @param fax
     * @param entryDate
     * @param currentAgencyInvolvement
     * @param city
     * @param cellPhone
     * @param apartmentNum
     * @param addressType
     * @roseuid 42B1830802CE
     */
    public void saveJuvenileContact(int zipCode, int workPhone, int streetType, int streetNum, int streetName, int state, int previousAgencyInvolvement, int pager, int middleName, int lastName, int juvenileNum, int firstName, int fax, int entryDate, int currentAgencyInvolvement, int city, int cellPhone, int apartmentNum, int addressType)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B18308039B
     */
    public void saveJuvenileDrugs(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     */
    public void saveJuvenileGangs(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param workHrsFrom
     * @param juvenileNum
     * @param juvenileJobId
     * @param jobDescription
     * @param entryDate
     * @param employmentPlace
     * @param juvenileNum
     * @roseuid 42B1830900EA
     */
    public void saveJuvenileJob(int workHrsFrom, int juvenileNum, int juvenileJobId, int jobDescription, int entryDate, int employmentPlace)
    {
    }

    /**
     * @stereotype design
     * @param weight
     * @param tattoos
     * @param juvenileNum
     * @param height
     * @param hairColor
     * @param eyeColor
     * @param entryDate
     * @param build
     * @roseuid 42B183090254
     */
    public void saveJuvenilePhysicalAttributes(int weight, int tattoos, int juvenileNum, int height, int hairColor, int eyeColor, int entryDate, int build)
    {
    }

    /**
     * @stereotype design
     * @param schoolId
     */
    public void getJuvenileSchoolByID(String schoolId)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B18B3E035F
     */
    public void getJuvenileSchool(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B18B40015B
     */
    public void getJuvenileAbuseList(String juvenileNum)
    {
    }
    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B18B40015B
     */
    public void getJuvenileDualStatusList(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param sexId
     * @param middleName
     * @param searchType
     * @param middleName
     * @param supervisionNumber
     * @param juvenileNum
     * @param statusId
     * @param dateOfBirth
     * @param raceId
     * @param lastName
     * @param firstName
     * @param alienNumber
     * @roseuid 42B198060242
     */
    public void searchJuvenileProfiles(String sexId, String searchType, String middleName, String supervisionNumber, String juvenileNum, String statusId, Date dateOfBirth, String raceId, String lastName, String firstName, String alienNumber)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B1980602FD
     */
    public void getJuvenileProfileMain(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B1980602FD
     */
    public void getJuvenilePactSubjectDetails(String juvenileNum)
    {
    } //added for task #43956

    /**
     * @stereotype design
     * @param sexId
     * @param dateSenttoDPS
     * @param DNASampleNumber
     * @param religionId
     * @param parentsMaritalStatusId
     * @param scarsAndMarksId
     * @param tattoosId
     * @param complexionId
     * @param naturalHairColorId
     * @param natualEyeColorId
     * @param FBINumber
     * @param alienNumber
     * @param PEIMSId
     * @param DHSNumber
     * @param SONumber
     * @param SID
     * @param SSN
     * @param driverLicenseStateId
     * @param driverLicenseNumber
     * @param primaryLanguageId
     * @param secondaryLanguageId
     * @param nationalityId
     * @param ethnicityId
     * @param secondaryCitizenshipId
     * @param primaryCitizenshipId
     * @param birthCountyId
     * @param verifiedDOB
     * @param dateOfBirth
     * @param multiracial
     * @param hispanic
     * @param raceId
     * @param birthStateId
     * @param birthCityId
     * @param birthCountryId
     * @roseuid 42B19808009E
     */
    public void saveJuvenileProfileMain(String sexId, Date dateSenttoDPS, String DNASampleNumber, String religionId, String parentsMaritalStatusId, String scarsAndMarksId, String tattoosId, String complexionId, String naturalHairColorId, String natualEyeColorId, String FBINumber, String alienNumber, String PEIMSId, String DHSNumber, String SONumber, String SID, String SSN, String driverLicenseStateId, String driverLicenseNumber, String primaryLanguageId, String secondaryLanguageId, String nationalityId, String ethnicityId, String secondaryCitizenshipId, String primaryCitizenshipId, String birthCountyId, boolean verifiedDOB, Date dateOfBirth, boolean multiracial, boolean hispanic, String raceId, String birthStateId, String birthCityId, String birthCountryId)
    {
    }

    /**
     * @stereotype design
     * @roseuid 4786842E010E
     */
    public void saveJuvenileTattooAndScars()
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B8889202CE
     */
    public void searchJuvenileProfileCasefileList(String juvenileNum)
    {
    }
    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B8889202CE
     */
    public void searchJuvenileCasefileList(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     */
    public void saveJuvenileSchoolHistory()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42BC4BE40376
     */
    public void saveJuvenileAbuse()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42C181B30169
     */
    public void getSchoolDistricts()
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B18307015A
     */
    public void validateJuvDOBDocument(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B18307015A
     */
    public void getJuvenileMedicalHealthIssuesList()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void getMedicalHealthIssueData()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void createMedicalHealthIssue()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void createMedicalMedication()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void getJuvenileMedicalMedicationList()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void getMedicalMedicationData()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void getJuvenileMedicalHospitalizationList()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void createMedicalHospitalization()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void getMedicalHospitalizationData()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void getSchoolNameSearch()
    {
    }

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void saveMatchingJuveniles(String juvA, String juvB, String status, String notes, String createUser)
    {
    };

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void getTattoosScarsPhysicalAttributes()
    {
    };

    /**
     * @stereotype design
     * @roseuid 42B18307015A
     */
    public void addJuvenileAlias()
    {
    };

    /**
     * @stereotype design
     * @generated 
     *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
     */
    public void getEducationCharterDetails()
    {
	// begin-user-code
	// TODO Auto-generated method stub

	// end-user-code
    }

    /**
     * @stereotype design
     * @generated 
     *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
     */
    public void getCharterGEDDetails()
    {
	// begin-user-code
	// TODO Auto-generated method stub

	// end-user-code
    }

    /**
     * @stereotype design
     * @generated 
     *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
     */
    public void saveCharterGED()
    {
	// begin-user-code
	// TODO Auto-generated method stub

	// end-user-code
    }

    /**
     * @stereotype design
     * @generated 
     *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
     */
    public void saveCharterVEP()
    {
	// begin-user-code
	// TODO Auto-generated method stub

	// end-user-code
    }

    /**
     * @stereotype design
     * @generated 
     *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
     */
    public void saveCharterPostRelease()
    {
	// begin-user-code
	// TODO Auto-generated method stub

	// end-user-code
    }

    /**
     * @stereotype design
     * @param juvenileNum
     */
    public void getJuvenileGEDProgram(String juvenileNum)
    {

    }

    /**
     * @stereotype design
     * @generated 
     *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
     */
    public void saveJuvenileGEDProgram()
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B183070206
     */
    public void getJuvenileProfileDocuments(String juvenileNum)
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 42B183070206
     */
    public void saveJuvenileProfileDocument()
    {
    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getFacilityAdmitReasonPop()
    {
    }
    

    /**
     * @stereotype design
     * @roseuid 46821A0E03BA
     */
    public void saveJuvenileJIS()
    {
    }

    /**
     * @stereotype design
     * @roseuid 43821A0E03AA
     */
    public void getJuvenileJIS()
    {
    }

    /**
     * @stereotype design
     * @roseuid 49921C0E03BA
     */

    public void saveJuvenileAbusePerp()
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 48C183070206
     */
    public void GetJuvenileAbusePerpsList()
    {
    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 48C183070206
     */
    public void updateTrackingControlNumber()
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 48C183070206
     */
    public void updateJuvenileNumControl()
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 48C183070206
     */
    public void updateJuvenileService()
    {

    }

    /**
     * @stereotype design
     * @param juvenileNum
     * @roseuid 48C183070206
     */
    public void updateJuvenileServiceCTSInfo()
    {

    }

    /**
     * @stereotype design
     * @roseuid 4786842E010E
     */
    public void saveJuvSSN()
    {
    }

    /**
     * @stereotype design
     * @roseuid 4786842E010E
     */
    public void saveJuvSSNUserAccess()
    {
    }

    /**
     * @stereotype design
     * @roseuid 4786842E010E
     */
    public void saveJuvJPOOfRec()
    {
    }
    /**
     * @stereotype design
     * @roseuid 49921C0E03BA
     */

    public void saveJuvenileAbuserRelationship()
    {
    }
    /**
     * @stereotype design
     * @roseuid 49921C0E03BA
     */

    public void getJuvenileAbuserRelationshipList()
    {
    }
    
    /**
     * @stereotype design
     * @param juvenileNum
     */
    public void saveJuvenilePurge(String juvenileNum)
    {
    }
    /**
     * @stereotype design
     * @roseuid 42BC4BE40376
     */
    public void saveJuvenileDualStatus()
    {
    }
    /**
     * @stereotype design
     * @roseuid 49921C0E03BA
     */

    public void saveJuvenileDSCPSServices()
    {
    }
    /**
     * @stereotype design
     * @roseuid 49921C0E03BA
     */

    public void getJuvenileDSservicesList()
    {
    }
    /**
     * @stereotype design
     * @roseuid 49921C0E03BA
     */

    public void saveJuvenileDSPlacements()
    {
    }
    /**
     * @stereotype design
     * @roseuid 49921C0E03BA
     */

    public void getJuvenileDSPlacementList()
    {
    }
    /**
     * @stereotype design
     */
    public void createJuvenileSsnViewLog(){
	
    }
    
    /**
     * @stereotype design
     */
    public void createDrugTestingInfo(){
	
    }
    
    /**
     * @stereotype design
     */
    public void getDrugTestingInfo(){
	
    }
    /**
     * @stereotype design
     */
    public void getJuvenileMasterStatus(String Juvenile_id){
	
    }
    /**
     * @stereotype design
     */
    public void getJuvenileInfoLight(String juvenileNum){
	
    }
    

    /**
     * @stereotype design
     */
    public void createSubstanceAbuse(){
	
    }
    
    /**
     * @stereotype design
     */
    public void getSubstanceAbuseInfo(){
	
    }
}