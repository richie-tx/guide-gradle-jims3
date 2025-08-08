//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\ServiceProviderController.java

package pd.supervision.administerserviceprovider;

import java.util.Date;

import messaging.contact.domintf.IName;

/**
 * @stereotype control
 */
public class ServiceProviderController
{

    /**
     * @roseuid 447357EC0143
     */
    public ServiceProviderController()
    {

    }

    /**
     * @stereotype design
     * @param subType
     * @param type
     * @param endDate
     * @param startDate
     * @param targetIntervention
     * @param stateProgramCode
     * @param programCode
     * @param name
     * @roseuid 44734FEC01C1
     */
    public void createProviderProgram(String subType, String type, Date endDate, Date startDate, int targetIntervention, String stateProgramCode, String programCode, IName name)
    {

    }

    /**
     * @stereotype design
     * @param locationName
     * @param serviceCost
     * @param maxErollment
     * @param type
     * @param serviceCode
     * @param name
     * @roseuid 44734FE4025D
     */
    public void createService(String locationName, String serviceCost, String maxErollment, String type, String serviceCode, IName name)
    {

    }

    /**
     * @stereotype design
     * @param ftpSite
     * @param webSite
     * @param fax
     * @param phone
     * @param isInHouse
     * @param ifasNum
     * @param contactLogonId
     * @param adminLogonId
     * @param startDate
     * @param zipCode
     * @param streetType
     * @param streetNum
     * @param streetName
     * @param state
     * @param city
     * @param apartmentNum
     * @param mailingStreetType
     * @param mailingAptNum
     * @param mailingZipCode
     * @param mailingStreetNum
     * @param mailingStreetName
     * @param mailingState
     * @param mailingCity
     * @param serviceProviderName
     * @roseuid 446A2E45017E
     */
    public void createServiceProvider(String ftpSite, String webSite, String fax, String phone, String extnNum, boolean isInHouse, String ifasNum, String contactLogonId, String adminLogonId, Date startDate, String zipCode, String streetType, String streetNum, String streetName, String state, String city, String apartmentNum, String mailingStreetType, String mailingAptNum, String mailingZipCode, String mailingStreetNum, String mailingStreetName, String mailingState, String mailingCity, String serviceProviderName)
    {

    }

    /**
     * @stereotype design
     * @param name
     * @param notes
     * @param isAdminContact
     * @param logonId
     * @param workPhone
     * @param email
     * @param cellPhone
     * @param pager
     * @param fax
     * @roseuid 44734FED0298
     */
    public void createServiceProviderContact(IName name, String notes, boolean isAdminContact, String logonId, String workPhone, String extnNum, String email, String cellPhone, String pager, String fax)
    {

    }

    /**
     * @stereotype design
     * @param name
     * @param notes
     * @param isAdminContact
     * @param logonId
     * @param workPhone
     * @param email
     * @param cellPhone
     * @param pager
     * @param fax
     * @roseuid 44734FED0298
     */
    public void updateServiceProviderContact()
    {

    }

    /**
     * @stereotype design
     * @param serviceLocationId
     * @roseuid 44734FEC0056
     */
    public void getEventTypesByLocation(String serviceLocationId)
    {

    }

    /**
     * @stereotype design
     * @param serviceProviderId
     * @roseuid 44734FEC0056
     */
    public void getServiceProviderInstructors(String serviceProviderId)
    {

    }

    /**
     * @stereotype design
     * @param serviceProviderId
     * @roseuid 44734FEC0056
     */
    public void getServiceProviderServiceLocations(String serviceProviderId)
    {

    }

    /**
     * @stereotype design
     * @param serviceProviderId
     * @param serviceLocationId
     * @roseuid 44734FEC0056
     */
    public void getServiceProviderServices(String serviceProviderId, String serviceLocationId)
    {

    }

    /**
     * @stereotype design
     * @param programCode
     * @roseuid 44734FEC0056
     */
    public void validateAdminUserId(String adminUserId)
    {

    }

    /**
     * @stereotype design
     * @param programCode
     * @roseuid 44734FEC0056
     */
    public void validateProgramCode(String programCode)
    {

    }

    /**
     * @stereotype design
     * @param serviceCode
     * @roseuid 44734FE40102
     */
    public void validateServiceCode(String serviceCode)
    {

    }

    /**
     * @stereotype design
     * @param serviceProviderName
     * @param agencyId
     * @roseuid 446A2E44016C
     */
    public void validateServiceProvider(String serviceProviderName, String agencyId)
    {

    }

    /**
     * @stereotype design
     * @param departmentId
     */

    public void getServiceProviderFromDepartmentId(String departmentId)
    {

    }

    /**
     * @stereotype design
     * @param departmentId
     */

    public void getServiceProviderServices(String serviceproviderId, String serviceTypeId, String serviceLocationId, boolean inHouse)
    {

    }

    /**
     * @stereotype design
     * @param serviceproviderId
     * @param serviceProviderId
     * @param isInHouse
     */

    public void validateServiceProviderByLogonId(String serviceproviderId, int serviceProviderId, boolean isInHouse)
    {

    }

    /**
     * @stereotype design
     * @param serviceproviderId
     * @param serviceProviderId
     * @param isInHouse
     */

    public void getServiceLocation(String locationId)
    {

    }

    public void getServiceProviderStatus(String serviceproviderId)
    {

    }

    /**
     * @stereotype design
     * @param serviceproviderId
     */
    public void getServiceProvider(String serviceproviderId)
    {

    }

    /**
     * @stereotype design
     * @param programName
     * @param endDateFrom
     * @param endDateTo
     * @param stateProgramCd
     * @param targetInterventionId
     * @param programStatusId
     */
    public void getProviderPrograms(String programName, Date endDateFrom, Date endDateTo, String stateProgramCd, String targetInterventionId, String programStatusId)
    {

    }

    /**
     * @stereotype design
     * @param programCode
     */
    public void getProviderProgramsByCode(String programCode)
    {

    }

    /**
     * @stereotype design
     * @param serviceName
     * @param serviceTypeId
     * @param serviceStatusId
     */
    public void getProviderServices(String serviceName, String serviceTypeId, String serviceStatusId)
    {
    }

    /**
     * @stereotype design
     * @param serviceProviderName
     * @param statusId
     * @param inHouse
     */
    public void getServiceProviders(String serviceProviderName, String statusId, boolean inHouse)
    {

    }

    /**
     * @stereotype design
     * @param statusId
     * @param inHouse
     */
    public void getSPNames()
    {
    }

    /**
     * @stereotype design
     * @param serviceProviderId
     */
    public void getContacts(String serviceProviderId)
    {

    }

    /**
     * @stereotype design
     * @param serviceName
     * @param serviceProviderId
     * @roseuid 446A2E44016C
     */
    public void validateServiceName(String serviceName, String serviceProviderId)
    {

    }

    /**
     * @stereotype design
     * @param serviceId
     * @roseuid 446A2E44016C
     */
    public void getServiceProviderContacts(String adminUserId)
    {

    }

    /**
     * @stereotype design
     * @param serviceId
     * @roseuid 446A2E44016C
     */
    public void getServiceByServiceId(String serviceId)
    {

    }

    /**
     * @stereotype design
     * @param programName
     * @param serviceProviderId
     * @roseuid 446A2E44016C
     */
    public void validateProgramName(String programName, String serviceProviderId)
    {

    }

    /**
     * @stereotype design
     * @param programName
     * @param serviceProviderId
     * @roseuid 446A2E44016C
     */
    public void getServicesByProgram(String providerProgramId)
    {

    }

    /**
     * @stereotype design
     * @param serviceId
     * @roseuid 446A2E44016C
     */
    public void getLocationsByServiceId(String serviceId)
    {

    }

    /**
     * @stereotype design
     * @param locationId
     */
    public void getServiceLocationServiceProviders(String locationId)
    {

    }

    /**
     * @stereotype design
     * @param providerProgramId
     * @roseuid 446A2E44016C
     */
    public void getProgramByProgramId(String providerProgramId)
    {

    }

    /**
     * @stereotype design
     * @param providerProgramCode
     * @roseuid 446A2E44016C
     */
    public void getProgramByProgramCode(String programCode)
    {

    }

    /**
     * @stereotype design
     * @param contractId
     * @roseuid 446A2E44016C
     */
    public void getServiceProviderContractServices(String contractid)
    {

    }

    /**
     * @stereotype design
     * @param serviceId
     * @roseuid 446A2E44016C
     */
    public void getServiceProviderServiceByServiceId(String serviceId)
    {

    }

    /**
     * @stereotype design
     * @param employeeId
     * @param logonId
     * @roseuid 446A2E44016C
     */
    public void getSPProfile(String employeeId, String logonId)
    {

    }

    /**
     * @stereotype design
     * @roseuid 446A2E44016C
     */
    public void getServicesByServiceProvider()
    {

    }

    /**
     * @stereotype design
     */
    public void SendJuvenileServiceProviderDocumentAttendanceNotification()
    {

    }

    /**
     * @stereotype design
     * @param serviceProviderId
     * @roseuid 44734FEC0056
     */
    public void getServiceProviderActiveInstructors(String serviceProviderId)
    {

    }

    /**
     * @stereotype design
     */
    public void getProgramsByProviderId(int serviceProviderId)
    {

    }

    /**
     * @stereotype design
     */
    public void createContactFundSource(String programId, String fundEntryDate, String fundStartDate, String fundEndDate, String status, String fundSource, String comments)
    {

    }

    /**
     * @stereotype design
     */

    public void getContactFundSource(String profileId)
    {

    }

    /**
     * @stereotype design
     * @roseuid 4786842E010E
     */
    public void GetProgramServices(String casefileId)
    {

    }

    /**
     * @stereotype design
     * @param serviceLocationId
     * @roseuid 44734FEC0056
     */
    public void GetServiceProvidersByLocationUnit(String LocationUnitId)
    {

    }

}
