/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mojo.km.security.AgencyEntityBean;
import mojo.km.security.DepartmentContactEntityBean;
import mojo.km.security.DepartmentEntityBean;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;
import pd.security.PDSecurityHelper;


/**
 * @author cc_mdsouza
 */
public class DepartmentHelper
{
    //87191 not used anywhere

   /* public DepartmentTO initializeTransferObjectFromPersistentObject(PersistentObject persistentObject) throws Exception
    {

	DepartmentTO departmentTO = new DepartmentTO();

	try
	{

	    Department department = (Department) persistentObject;

	    departmentTO.setOID((String) department.getOID());
	    departmentTO.setSID(department.getSID());
	    departmentTO.setSTP(department.getSTP());
	    departmentTO.setUserID(department.getUserID());
	    departmentTO.setCreateUserID(department.getCreateUserID());
	    departmentTO.setCreateTimestamp(department.getCreateTimestamp());
	    departmentTO.setCreateJIMS2UserID(department.getCreateJIMS2UserID());
	    departmentTO.setUpdateUserID(department.getUpdateUserID());
	    departmentTO.setUpdateTimestamp(department.getUpdateTimestamp());
	    departmentTO.setUpdateJIMS2UserID(department.getUpdateJIMS2UserID());
	    departmentTO.setEXP(department.getEXP());
	    departmentTO.setWorkflowID(department.getWorkflowID());

	    departmentTO.setSubscriberCivilTerminationDate(department.getSubscriberCivilTerminationDate());
	    departmentTO.setSetcicInactiveDate(department.getSetcicInactiveDate());
	    departmentTO.setSetcicDate(department.getSetcicDate());
	    departmentTO.setComments(department.getComments());
	    departmentTO.setSetcicAccessId(department.getSetcicAccessId());
	    departmentTO.setDepartmentId(department.getDepartmentId());
	    departmentTO.setFax(department.getFax());
	    departmentTO.setMailingAddressId(department.getMailingAddressId());
	    departmentTO.setAgencyName(department.getAgencyName());
	    departmentTO.setOriginatingAgencyId(department.getOriginatingAgencyId());
	    departmentTO.setWarrantConfirmationPhoneExt(department.getWarrantConfirmationPhoneExt());
	    departmentTO.setStatusId(department.getStatusId());
	    departmentTO.setDepartmentName(department.getDepartmentName());
	    departmentTO.setSetcicRenewDate(department.getSetcicRenewDate());
	    departmentTO.setTerminationDate(department.getTerminationDate());
	    departmentTO.setLabelInd(department.getLabelInd());
	    departmentTO.setGritsInd(department.getGritsInd());
	    departmentTO.setAddressId(department.getAddressId());
	    departmentTO.setActivationDate(department.getActivationDate());
	    departmentTO.setAgencyId(department.getAgencyId());
	    departmentTO.setWarrantConfirmationPhone(department.getWarrantConfirmationPhone());
	    departmentTO.setSubscriberCivilActivationDate(department.getSubscriberCivilActivationDate());
	    departmentTO.setSubscriberCriminalActivationDate(department.getSubscriberCriminalActivationDate());
	    departmentTO.setAgencyTypeId(department.getAgencyTypeId());
	    departmentTO.setCreateOfficerProfileInd(department.getCreateOfficerProfileInd());
	    departmentTO.setSubscriberCriminalTerminationDate(department.getSubscriberCriminalTerminationDate());
	    departmentTO.setBillingAddressId(department.getBillingAddressId());
	    departmentTO.setOrgCode(department.getOrgCode());
	    departmentTO.setInUseInd(department.getInUseInd());
	    departmentTO.setAccessTypeId(department.getAccessTypeId());
	    departmentTO.setDepartmentUserCount(department.getDepartmentUserCount());

	    //	  	if (department.getContacts().size() > 0 && this.contacts.size() != department.getContacts().size())
	    //	  	{
	    //	  		// TODO : typecast list of histories and populate the relevant transfer object. 
	    //	  		// Right now the histories collection is just being set to an empty linked list 
	    //	  		// so no histories are available
	    //	  		this.contacts = new LinkedList() ; 
	    //	  	}
	    //
	    //	  	if (this.agency == null && department.getAgency()!= null )
	    //	  	{
	    //	  		this.agencyTO = new AgencyTO() ; 
	    //	  		agencyTO.initializeTransferObjectFromPersistentObject( department.getAgency()  ) ; 
	    //	  	}
	    //	  	
	    //	  	if (this.agencyType == null && department.getAgencyType()!= null )
	    //	  	{
	    //	  		this.codeTO = new CodeTO() ; 
	    //	  		codeTO.initializeTransferObjectFromPersistentObject( department.getAgencyType()  ) ; 
	    //	  	}
	    //
	    //	  	if (this.accessType == null && department.getAccessType()!= null )
	    //	  	{
	    //	  		this.codeTO = new CodeTO() ; 
	    //	  		codeTO.initializeTransferObjectFromPersistentObject( department.getAccessType()  ) ; 
	    //	  	}
	    //	  	
	    //	  	if (this.setcicAccess == null && department.getSetcicAccess()!= null )
	    //	  	{
	    //	  		this.codeTO = new CodeTO() ; 
	    //	  		codeTO.initializeTransferObjectFromPersistentObject( department.getSetcicAccess()  ) ; 
	    //	  	}
	    //	  	
	    //	  	if (this.status == null && department.getStatus()!= null )
	    //	  	{
	    //	  		this.codeTO = new CodeTO() ; 
	    //	  		codeTO.initializeTransferObjectFromPersistentObject( department.getSetcicAccess()  ) ; 
	    //	  	}
	    //
	    //	  	if (this.mailingAddress == null && department.getMailingAddress()!= null )
	    //	  	{
	    //	  		this.addressTO = new AddressTO() ; 
	    //	  		addressTO.initializeTransferObjectFromPersistentObject( department.getMailingAddress()  ) ; 
	    //	  	}
	    //
	    //	  	if (this.address == null && department.getAddress()!= null )
	    //	  	{
	    //	  		this.addressTO = new AddressTO() ; 
	    //	  		addressTO.initializeTransferObjectFromPersistentObject( department.getAddress()  ) ; 
	    //	  	}
	    //	  	
	    //	  	if (this.county == null && department.getCounty()!= null )
	    //	  	{
	    //	  		this.codeTO = new CodeTO() ; 
	    //	  		codeTO.initializeTransferObjectFromPersistentObject( department.getCounty()  ) ; 
	    //	  	}
	    //
	    //	  	if (this.billingAddress == null && department.getBillingAddress()!= null )
	    //	  	{
	    //	  		this.addressTO = new AddressTO() ; 
	    //	  		addressTO.initializeTransferObjectFromPersistentObject( department.getBillingAddress()  ) ; 
	    //	  	}

	}
	catch (Exception e)
	{
	    e.printStackTrace();
	    throw new Exception(e);
	}
	return departmentTO;
    }

    public void putTransferObjectToPersistentObject(TransferObjectInterface transferObjectInterface) throws Exception
    {
	try
	{
	    throw new UnsupportedOperationException();
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	    throw new Exception(e);
	}

    }*/
    
    /**
     * getDepartmentFromSecurityManager
     * @param deptId
     * @return Department
     */
    public static Department getDepartmentFromSecurityManager(String deptId){
	if (deptId != null && !deptId.isEmpty())
	{
	    DepartmentEntityBean deptEntityBean = PDSecurityHelper.getDepartmentById(deptId);
	    Department department = populateDepartmentFromDeptEntityBean(deptEntityBean);
	    return department;
	}
	return null;
    }
    
    /**
     * Get Departments 
     * @param departmentId
     * @param departmentName
     * @return
     */
    public static <T> Object getDepartments(String departmentId, String departmentName, String agencyId)
    {
	if (agencyId != null && !agencyId.isEmpty())
	{
	    Agency agency = Agency.find(agencyId);

	    if (agency != null)
	    {
		List<Department> departments = (List<Department>) agency.getDepartments();
		return departments;
	    }

	}
	else
	{
	    Object obj = PDSecurityHelper.getDepartmentByCodeOrDesc(departmentId, departmentName);
	    if (obj instanceof DepartmentEntityBean)
	    {
		DepartmentEntityBean deptEBean = (DepartmentEntityBean) obj;
		Department department = populateDepartmentFromDeptEntityBean(deptEBean);
		return department;
	    }
	    if (obj instanceof List<?>)
	    {
		List<DepartmentEntityBean> deptEBeans = (List<DepartmentEntityBean>) obj;
		List<Department> departments = populateDepartmentsFromDeptEntityBeans(deptEBeans);
		return departments;
	    }
	}

	return null;
    }

    /**
     * getDepartmentsByAgencyId
     * @param agencyId
     * @return List<Department>
     */
    public static List<Department> getDepartmentsByAgencyId(String agencyId)
    {
	if(agencyId!=null && !agencyId.isEmpty()){
	    AgencyEntityBean agencyEBean = PDSecurityHelper.getAgencyByAgencyId(agencyId);
	    if (agencyEBean != null)
	    {
		List<Department> departments = populateDepartmentsFromDeptEntityBeans(agencyEBean.getDepartments());
		return departments;
	    }
	}
	return null;
    }
    
    

    /**
     * populateDepartmentFromDeptEntityBean
     * @param deptBean
     * @return Department
     */
    public static List<Department> populateDepartmentsFromDeptEntityBeans(List<DepartmentEntityBean> deptBeans)
    {
	List<Department> departments = new ArrayList<Department>();
	if (deptBeans != null)
	{
	    Iterator<DepartmentEntityBean> deptBeansItr = deptBeans.iterator();
	    while (deptBeansItr.hasNext())
	    {
		DepartmentEntityBean deptEBean = deptBeansItr.next();
		if (deptEBean != null/* && deptEBean.getDepartmentstatus()!=null &&deptEBean.getDepartmentstatus().equalsIgnoreCase("A")*/)
		{
		    Department department = new Department();
		    department.setDepartmentId(deptEBean.getDepartmentid());
		    department.setDepartmentName(deptEBean.getDepartmentdescription());
		    department.setOrgCode(deptEBean.getOrgcode());
		    department.setAgencyId(deptEBean.getAgency().getAgencyid());
		    department.setAgencyName(deptEBean.getAgency().getAgencyname());

		    List<DepartmentContact> departmentContacts = new ArrayList<DepartmentContact>();

		    List<DepartmentContactEntityBean> departmentContactEntityBeans = deptEBean.getDepartmentcontacts();
		    if (departmentContactEntityBeans != null)
		    {
			Iterator<DepartmentContactEntityBean> departmentContactEntityBeanItr = departmentContactEntityBeans.iterator();
			while (departmentContactEntityBeanItr.hasNext())
			{
			    DepartmentContactEntityBean contactyEBean = departmentContactEntityBeanItr.next();
			    if (contactyEBean != null)
			    {
				DepartmentContact contact = new DepartmentContact();
				contact.setFirstName(contactyEBean.getContactfirstname());
				contact.setMiddleName(contactyEBean.getContactmiddlename());
				contact.setLastName(contactyEBean.getContactlastname());
				contact.setTitle(contactyEBean.getContactjobtitle());
				contact.setPhoneNum(contactyEBean.getContactphone());
				contact.setPhoneExt(contactyEBean.getContactextension());
				contact.setLogonId(contactyEBean.getContactuserid());
				contact.setEmail(contactyEBean.getContactemail());
				contact.setPrimaryContact(contactyEBean.getContactprimary());
				contact.setLiaisonTrainingInd(contactyEBean.getLiaisontrainingind());
				contact.setContactTypeId(contactyEBean.getContacttype());
				departmentContacts.add(contact);
			    }
			}
		    }
		    department.setContacts(departmentContacts);
		    departments.add(department);
		}
	    }
	}
	return departments;
    }
    
    /**
     * populateDepartmentFromDeptEntityBean
     * 
     * @param deptBean
     * @return Department
     */
    public static Department populateDepartmentFromDeptEntityBean(DepartmentEntityBean deptBean)
    {
	DepartmentEntityBean deptEntityBean = deptBean;
	
	
	if (deptBean != null)//bug: 88340/* &&deptEntityBean.getDepartmentstatus()!=null && deptEntityBean.getDepartmentstatus().equalsIgnoreCase("A"))*/ if needed will add later.
	{
	    Department department = new Department();
	    department.setDepartmentId(deptBean.getDepartmentid());
	    department.setDepartmentName(deptEntityBean.getDepartmentdescription());
	    department.setOrgCode(deptEntityBean.getOrgcode());
	    department.setAgencyId(deptEntityBean.getAgency().getAgencyid());
	    department.setAgencyName(deptEntityBean.getAgency().getAgencyname());

	    List<DepartmentContact> departmentContacts = new ArrayList<DepartmentContact>();

	    List<DepartmentContactEntityBean> departmentContactEntityBeans = deptEntityBean.getDepartmentcontacts();
	    if (departmentContactEntityBeans != null)
	    {
		Iterator<DepartmentContactEntityBean> departmentContactEntityBeanItr = departmentContactEntityBeans.iterator();
		while (departmentContactEntityBeanItr.hasNext())
		{
		    DepartmentContactEntityBean entityBean = departmentContactEntityBeanItr.next();
		    if (entityBean != null)
		    {
			DepartmentContact contact = new DepartmentContact();
			contact.setFirstName(entityBean.getContactfirstname());
			contact.setMiddleName(entityBean.getContactmiddlename());
			contact.setLastName(entityBean.getContactlastname());
			contact.setTitle(entityBean.getContactjobtitle());
			contact.setPhoneNum(entityBean.getContactphone());
			contact.setPhoneExt(entityBean.getContactextension());
			//    contact.setUserID(entityBean.getContactuserid()); //cehck with latha
			contact.setEmail(entityBean.getContactemail());
			contact.setPrimaryContact(entityBean.getContactprimary());
			contact.setLiaisonTrainingInd(entityBean.getLiaisontrainingind());
			contact.setContactTypeId(entityBean.getContacttype());
			departmentContacts.add(contact);
		    }
		}
	    }
	    department.setContacts(departmentContacts);
	   return department;
	}
	return null;
    }
}
