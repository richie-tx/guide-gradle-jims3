/*
 * Created on Nov 16, 2006
 *
 */
package pd.juvenilewarrant;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import naming.PDJuvenileWarrantConstants;

import pd.codetable.Code;
import pd.contact.agency.Department;
import pd.transferobjects.helper.DepartmentHelper;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.juvenilewarrant.GetJuvenileWarrantsForProcessServiceEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantStageErrorEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;

/**
 * @author Jim Fisher
 *  
 */
public class JuvenileWarrantServiceOfficer extends PersistentObject
{

    public static List findByJuvenileName(GetJuvenileWarrantsForProcessServiceEvent anEvent)
    {
        IHome home = new Home();
        Iterator i = home.findAll(anEvent, JuvenileWarrantServiceOfficer.class);
        return CollectionUtil.iteratorToList(i);
    }

    /**
     * @param warrantNum2
     * @return
     */
    public static JuvenileWarrantServiceOfficer findByWarrantNum(String aWarrantNum)
    {
        IHome home = new Home();
        JuvenileWarrantServiceOfficer jwso = null;
        Iterator i = home.findAll("warrantNum", aWarrantNum, JuvenileWarrantServiceOfficer.class);
        if (i.hasNext())
        {
            jwso = (JuvenileWarrantServiceOfficer) i.next();
        }
        return jwso;
    }
    
	/**
	 * @param Event
	 * @param class
	 * @return
	 */
	public static MetaDataResponseEvent findMeta(IEvent event ) {
		IHome home = new Home();
		MetaDataResponseEvent iter = home.findMeta(event, JuvenileWarrantServiceOfficer.class);
		return iter;
	}    

    private String apartmentNum;

    private String badge;

    private String city;

    private String juvFirstName;

    private String juvLastName;

    private String juvMiddleName;

    private String officerCellPhone;

    /**
     * Properties for department
     * 
     * @referencedType pd.contact.agency.Department
     * @detailerDoNotGenerate true
     */
    private Department officerDepartment = null;

    private String officerDepartmentId;

    private String officerEmail;

    private String officerFirstName;

    private String officerId;

    /**
     * Properties for rank
     * 
     * @referencedType pd.codetable.Code
     * @contextKey OFFICER_TYPE
     * @detailerDoNotGenerate true
     */
    private Code officerIdType = null;

    private String officerIdTypeId;

    private String officerLastName;

    private String officerMiddleName;

    private String officerOtherIdNum;

    private String officerPager;

    private String officerWorkPhone;

    private String petitionNum;

    private Date serviceDate;

    private String serviceReturnGeneratedStatusId;

    private String serviceReturnSignatureStatusId;

    private String serviceStatusId;

    private String stateId;

    private String streetAddress2;

    private String streetName;

    private String streetNum;

    private String streetTypeId;

    private String warrantNum;
    
    private String warrantOriginatorCourtId;

    private String warrantServiceId;

    private String warrantStatusId;
    
    private Date warrantActivationDate;

    /**
     * Properties for warrantType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey WARRANT_TYPE
     * @detailerDoNotGenerate true
     */
    private Code warrantType = null;

    private String warrantTypeId;

    private String zipCode;

    private String zipCodeExtended;

    /**
     * @return Returns the apartmentNum.
     */
    public String getApartmentNum()
    {
        fetch();
        return apartmentNum;
    }

    /**
     * @return Returns the badge.
     */
    public String getBadge()
    {
        fetch();
        return badge;
    }

    /**
     * @return Returns the city.
     */
    public String getCity()
    {
        fetch();
        return city;
    }

    public IName getExecutorName()
    {
        IName name = new NameBean();
        name.setFirstName(this.getOfficerFirstName());
        name.setMiddleName(this.getOfficerMiddleName());
        name.setLastName(this.getOfficerLastName());
        return name;
    }

    /**
     * @return
     */
    public InvalidWarrantStageErrorEvent getInvalidWarrantStageErrorEvent()
    {
        // Not going to send back the descriptions because codes are cached
        InvalidWarrantStageErrorEvent errorEvent = new InvalidWarrantStageErrorEvent();
        errorEvent.setWarrantNum(this.getWarrantNum());
        errorEvent.setWarrantStatus(this.getWarrantStatusId());
        return errorEvent;
    }

    public IName getJuvenileName()
    {
        IName name = new NameBean();
        name.setFirstName(this.getJuvFirstName());
        name.setLastName(this.getJuvLastName());
        name.setMiddleName(this.getJuvMiddleName());
        return name;
    }

    /**
     * @return Returns the juvFirstName.
     */
    public String getJuvFirstName()
    {
        return juvFirstName;
    }

    /**
     * @return Returns the juvLastName.
     */
    public String getJuvLastName()
    {
        fetch();
        return juvLastName;
    }

    /**
     * @return Returns the juvMiddleName.
     */
    public String getJuvMiddleName()
    {
        fetch();
        return juvMiddleName;
    }

    /**
     * @return Returns the officerCellPhone.
     */
    public String getOfficerCellPhone()
    {
        fetch();
        return officerCellPhone;
    }

    public Department getOfficerDepartment()
    {
        fetch();
        initOfficerDepartment();
        return officerDepartment;
    }

    /**
     * @return Returns the departmentId.
     */
    public String getOfficerDepartmentId()
    {
        fetch();
        return officerDepartmentId;
    }

    /**
     * @return Returns the officerEmail.
     */
    public String getOfficerEmail()
    {
        fetch();
        return officerEmail;
    }

    /**
     * @return Returns the officerFirstName.
     */
    public String getOfficerFirstName()
    {
        fetch();
        return officerFirstName;
    }

    /**
     * @return Returns the officerId.
     */
    public String getOfficerId()
    {
        fetch();
        return officerId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getOfficerIdType()
    {
        fetch();
        initOfficerIdType();
        return officerIdType;
    }

    /**
     * @return Returns the officerIdTypeId.
     */
    public String getOfficerIdTypeId()
    {
        fetch();
        return officerIdTypeId;
    }

    /**
     * @return Returns the officerLastName.
     */
    public String getOfficerLastName()
    {
        fetch();
        return officerLastName;
    }

    /**
     * @return Returns the officerMiddleName.
     */
    public String getOfficerMiddleName()
    {
        fetch();
        return officerMiddleName;
    }

    /**
     * @return Returns the officerOtherIdNum.
     */
    public String getOfficerOtherIdNum()
    {
        fetch();
        return officerOtherIdNum;
    }

    /**
     * @return Returns the officerPager.
     */
    public String getOfficerPager()
    {
        fetch();
        return officerPager;
    }

    /**
     * @return Returns the officerWorkPhone.
     */
    public String getOfficerWorkPhone()
    {
        fetch();
        return officerWorkPhone;
    }

    /**
     * @return Returns the petitionNum.
     */
    public String getPetitionNum()
    {
        fetch();
        return petitionNum;
    }

    /**
     * @return Returns the serviceDate.
     */
    public Date getServiceDate()
    {
        fetch();
        return serviceDate;
    }

    /**
     * @return Returns the serviceReturnGeneratedStatusId.
     */
    public String getServiceReturnGeneratedStatusId()
    {
        fetch();
        return serviceReturnGeneratedStatusId;
    }

    /**
     * @return Returns the serviceReturnSignatureStatusId.
     */
    public String getServiceReturnSignatureStatusId()
    {
        fetch();
        return serviceReturnSignatureStatusId;
    }

    /**
     * @return Returns the serviceStatusId.
     */
    public String getServiceStatusId()
    {
        fetch();
        return serviceStatusId;
    }

    /**
     * @return Returns the stateId.
     */
    public String getStateId()
    {
        fetch();
        return stateId;
    }

    /**
     * @return Returns the streetAddress2.
     */
    public String getStreetAddress2()
    {
        fetch();
        return streetAddress2;
    }

    /**
     * @return Returns the streetName.
     */
    public String getStreetName()
    {
        fetch();
        return streetName;
    }

    /**
     * @return Returns the streetNum.
     */
    public String getStreetNum()
    {
        fetch();
        return streetNum;
    }

    /**
     * @return Returns the streetTypeId.
     */
    public String getStreetTypeId()
    {
        fetch();
        return streetTypeId;
    }

    /**
     * @return Returns the warrantNum.
     */
    public String getWarrantNum()
    {
        fetch();
        return warrantNum;
    }

    /**
     * @return Returns the warrantServiceId.
     */
    public String getWarrantServiceId()
    {
        fetch();
        return warrantServiceId;
    }

    /**
     * @return Returns the warrantStatusId.
     */
    public String getWarrantStatusId()
    {
        fetch();
        return warrantStatusId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code warrantType
     */
    public Code getWarrantType()
    {
        fetch();
        initWarrantType();
        return warrantType;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String warrantTypeId
     */
    public String getWarrantTypeId()
    {
        fetch();
        return warrantTypeId;
    }

    /**
     * @return Returns the zipCode.
     */
    public String getZipCode()
    {
        fetch();
        return zipCode;
    }

    /**
     * @return Returns the zipCodeExtended.
     */
    public String getZipCodeExtended()
    {
        fetch();
        return zipCodeExtended;
    }

    /**
     * Initialize class relationship to class pd.contact.agency.Department
     */
    private void initOfficerDepartment()
    {
        if (officerDepartment == null)
        {
            officerDepartment =Department.find(officerDepartmentId); //87191//(pd.contact.agency.Department) new mojo.km.persistence.Reference(officerDepartmentId,
                 //   pd.contact.agency.Department.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initOfficerIdType()
    {
        if (officerIdType == null)
        {
            officerIdType = (Code) new mojo.km.persistence.Reference(officerIdTypeId,
                    Code.class, "OFFICER_TYPE").getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initWarrantType()
    {
        if (warrantType == null)
        {
            warrantType = (Code) new mojo.km.persistence.Reference(warrantTypeId, Code.class,
                    "WARRANT_TYPE").getObject();
        }
    }

    public boolean isReadyForProcessReturnOfService()
    {
        return PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED.equals(this.warrantStatusId)
                && PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_NOTPRINTED
                        .equals(this.serviceReturnGeneratedStatusId)
                && PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_RETURNED
                        .equals(this.serviceReturnSignatureStatusId);
    }

    /**
     * @param apartmentNum
     *            The apartmentNum to set.
     */
    public void setApartmentNum(String apartmentNum)
    {
        this.apartmentNum = apartmentNum;
    }

    /**
     * @param badge
     *            The badge to set.
     */
    public void setBadge(String badge)
    {
        this.badge = badge;
    }

    /**
     * @param city
     *            The city to set.
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @param departmentId
     *            The departmentId to set.
     */
    public void setDepartmentId(String officerDepartmentId)
    {
        this.officerDepartmentId = officerDepartmentId;
    }

    /**
     * @param juvFirstName
     *            The juvFirstName to set.
     */
    public void setJuvFirstName(String juvFirstName)
    {
        this.juvFirstName = juvFirstName;
    }

    /**
     * @param juvLastName
     *            The juvLastName to set.
     */
    public void setJuvLastName(String juvLastName)
    {
        this.juvLastName = juvLastName;
    }

    /**
     * @param juvMiddleName
     *            The juvMiddleName to set.
     */
    public void setJuvMiddleName(String juvMiddleName)
    {
        this.juvMiddleName = juvMiddleName;
    }

    /**
     * @param officerCellPhone
     *            The officerCellPhone to set.
     */
    public void setOfficerCellPhone(String officerCellPhone)
    {
        this.officerCellPhone = officerCellPhone;
    }

    /**
     * @param officerDepartment
     *            The officerDepartment to set.
     */
    public void setOfficerDepartment(Department officerDepartment)
    {
        this.officerDepartment = officerDepartment;
    }

    /**
     * @param officerDepartmentId
     *            The officerDepartmentId to set.
     */
    public void setOfficerDepartmentId(String officerDepartmentId)
    {
        this.officerDepartmentId = officerDepartmentId;
    }

    /**
     * @param officerEmail
     *            The officerEmail to set.
     */
    public void setOfficerEmail(String officerEmail)
    {
        this.officerEmail = officerEmail;
    }

    /**
     * @param officerFirstName
     *            The officerFirstName to set.
     */
    public void setOfficerFirstName(String officerFirstName)
    {
        this.officerFirstName = officerFirstName;
    }

    /**
     * @param officerId
     *            The officerId to set.
     */
    public void setOfficerId(String officerId)
    {
        this.officerId = officerId;
    }

    /**
     * @param officerIdType
     *            The officerIdType to set.
     */
    public void setOfficerIdType(Code officerIdType)
    {
        this.officerIdType = officerIdType;
    }

    /**
     * @param officerIdTypeId
     *            The officerIdTypeId to set.
     */
    public void setOfficerIdTypeId(String officerIdTypeId)
    {
        this.officerIdTypeId = officerIdTypeId;
    }

    /**
     * @param officerLastName
     *            The officerLastName to set.
     */
    public void setOfficerLastName(String officerLastName)
    {
        this.officerLastName = officerLastName;
    }

    /**
     * @param officerMiddleName
     *            The officerMiddleName to set.
     */
    public void setOfficerMiddleName(String officerMiddleName)
    {
        this.officerMiddleName = officerMiddleName;
    }

    /**
     * @param officerOtherIdNum
     *            The officerOtherIdNum to set.
     */
    public void setOfficerOtherIdNum(String officerOtherIdNum)
    {
        this.officerOtherIdNum = officerOtherIdNum;
    }

    /**
     * @param officerPager
     *            The officerPager to set.
     */
    public void setOfficerPager(String officerPager)
    {
        this.officerPager = officerPager;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setOfficerTypeId(String officerIdTypeId)
    {
        if (this.officerIdTypeId == null || !this.officerIdTypeId.equals(officerIdTypeId))
        {
            markModified();
        }
        officerIdType = null;
        this.officerIdTypeId = officerIdTypeId;
    }

    /**
     * @param officerWorkPhone
     *            The officerWorkPhone to set.
     */
    public void setOfficerWorkPhone(String officerWorkPhone)
    {
        this.officerWorkPhone = officerWorkPhone;
    }

    /**
     * @param petitionNum
     *            The petitionNum to set.
     */
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }

    /**
     * @param serviceDate
     *            The serviceDate to set.
     */
    public void setServiceDate(Date serviceDate)
    {
        this.serviceDate = serviceDate;
    }

    /**
     * @param serviceReturnGeneratedStatusId
     *            The serviceReturnGeneratedStatusId to set.
     */
    public void setServiceReturnGeneratedStatusId(String serviceReturnGeneratedStatusId)
    {
        this.serviceReturnGeneratedStatusId = serviceReturnGeneratedStatusId;
    }

    /**
     * @param serviceReturnSignatureStatusId
     *            The serviceReturnSignatureStatusId to set.
     */
    public void setServiceReturnSignatureStatusId(String serviceReturnSignatureStatusId)
    {
        this.serviceReturnSignatureStatusId = serviceReturnSignatureStatusId;
    }

    /**
     * @param serviceStatusId
     *            The serviceStatusId to set.
     */
    public void setServiceStatusId(String serviceStatusId)
    {
        this.serviceStatusId = serviceStatusId;
    }

    /**
     * @param stateId
     *            The stateId to set.
     */
    public void setStateId(String stateId)
    {
        this.stateId = stateId;
    }

    /**
     * @param streetAddress2
     *            The streetAddress2 to set.
     */
    public void setStreetAddress2(String streetAddress2)
    {
        this.streetAddress2 = streetAddress2;
    }

    /**
     * @param streetName
     *            The streetName to set.
     */
    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    /**
     * @param streetNum
     *            The streetNum to set.
     */
    public void setStreetNum(String streetNum)
    {
        this.streetNum = streetNum;
    }

    /**
     * @param streetTypeId
     *            The streetTypeId to set.
     */
    public void setStreetTypeId(String streetTypeId)
    {
        this.streetTypeId = streetTypeId;
    }

    /**
     * @param warrantNum
     *            The warrantNum to set.
     */
    public void setWarrantNum(String warrantNum)
    {
        this.warrantNum = warrantNum;
    }

    /**
     * @param warrantServiceId
     *            The warrantServiceId to set.
     */
    public void setWarrantServiceId(String warrantServiceId)
    {
        this.warrantServiceId = warrantServiceId;
    }

    /**
     * @param warrantStatusId
     *            The warrantStatusId to set.
     */
    public void setWarrantStatusId(String warrantStatusId)
    {
        this.warrantStatusId = warrantStatusId;
    }

    /**
     * @param warrantType
     *            The warrantType to set.
     */
    public void setWarrantType(Code warrantType)
    {
        this.warrantType = warrantType;
    }

    /**
     * @param warrantTypeId
     *            The warrantTypeId to set.
     */
    public void setWarrantTypeId(String warrantTypeId)
    {
        this.warrantTypeId = warrantTypeId;
    }

    /**
     * @param zipCode
     *            The zipCode to set.
     */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * @param zipCodeExtended
     *            The zipCodeExtended to set.
     */
    public void setZipCodeExtended(String zipCodeExtended)
    {
        fetch();
        this.zipCodeExtended = zipCodeExtended;
    }
    /**
     * @return Returns the warrantActivationDate.
     */
    public Date getWarrantActivationDate()
    {
        return warrantActivationDate;
    }
    /**
     * @param warrantActivationDate The warrantActivationDate to set.
     */
    public void setWarrantActivationDate(Date warrantActivationDate)
    {
        this.warrantActivationDate = warrantActivationDate;
    }
	/**
	 * @return Returns the warrantOriginatorCourtId.
	 */
	public String getWarrantOriginatorCourtId() {
		
		return warrantOriginatorCourtId;
	}
	/**
	 * @param warrantOriginatorCourtId The warrantOriginatorCourtId to set.
	 */
	public void setWarrantOriginatorCourtId(String warrantOriginatorCourtId) {
		
		this.warrantOriginatorCourtId = warrantOriginatorCourtId;
	}
}
