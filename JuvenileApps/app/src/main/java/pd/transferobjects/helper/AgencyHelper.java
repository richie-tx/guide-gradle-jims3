/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.transferobjects.AgencyTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.AgencyEntityBean;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;
import pd.security.PDSecurityHelper;

/**
 * @author cc_mdsouza Needs to be embedded within the transfer object or the pd
 *         object , preferably on the transfer object
 */
public class AgencyHelper
{

    public AgencyTO initializeTransferObjectFromPersistentObject(PersistentObject persistentObject) throws Exception
    {
	AgencyTO agencyTO = new AgencyTO();
	try
	{
	    Agency agency = (Agency) persistentObject;

	    agencyTO.setOID((String) agency.getOID());
	    agencyTO.setSID(agency.getSID());
	    agencyTO.setSTP(agency.getSTP());
	    agencyTO.setUserID(agency.getUserID());
	    agencyTO.setCreateUserID(agency.getCreateUserID());
	    agencyTO.setCreateTimestamp(agency.getCreateTimestamp());
	    agencyTO.setCreateJIMS2UserID(agency.getCreateJIMS2UserID());
	    agencyTO.setUpdateUserID(agency.getUpdateUserID());
	    agencyTO.setUpdateTimestamp(agency.getUpdateTimestamp());
	    agencyTO.setUpdateJIMS2UserID(agency.getUpdateJIMS2UserID());
	    agencyTO.setEXP(agency.getEXP());
	    agencyTO.setWorkflowID(agency.getWorkflowID());

	    agencyTO.setAgencyTypeId(agency.getAgencyTypeId());
	    agencyTO.setAgencyId(agency.getAgencyId());
	    agencyTO.setAgencyName(agency.getAgencyName());
	  //  agencyTO.setProjectAnalystInd(agency.getProjectAnalystInd()); //87191

	    //	  	if (agency.getAgencyType() != null  && this.agencyType == null )
	    //	  	{
	    //	  		this.agencyType = new CodeTO() ; 
	    //	  		this.agencyType.initializeTransferObjectFromPersistentObject( agency.getAgencyType()) ; 
	    //	  	}
	    //
	    //	  	if (agency.getDepartments().size() > 0 && this.departments.size() != agency.getDepartments().size())
	    //	  	{
	    //	  		Iterator departmentsIterator = agency.getDepartments().iterator() ;
	    //	  		Organization department= null ;
	    //	  		OrganizationTO departmentTO = null ; 
	    //	  		while ( departmentsIterator.hasNext() ) 
	    //	  		{
	    //	  			department = (Organization)departmentsIterator.next() ; 
	    //	  			departmentTO = new OrganizationTO() ; 
	    //	  			departmentTO.initializeTransferObjectFromPersistentObject(department) ; 
	    //	  			this.departments.add(departmentTO) ; 
	    //	  		}
	    //	  	}
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	    throw new Exception(e);
	}
	return agencyTO;
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

    }

    
    /**
     * getAgencyById
     * @param agencyId
     * @return Agency
     */
    public static Agency getAgencyById(String agencyId){
	if (agencyId != null && !agencyId.isEmpty())
	{
	    AgencyEntityBean agencyEBean = PDSecurityHelper.getAgencyByAgencyId(agencyId);
	    if (agencyEBean != null)
	    {
		Agency agency = populateAgencyFromAgencyEntityBean(agencyEBean);
		return agency;
	    }
	}
	return null;
    }
    
    /**
     * Get Departments 
     * @param departmentId
     * @param departmentName
     * @return <T>
     */
    public static <T> Object getAgencies(String agencyId, String agencyName)
    {
	Object obj =  PDSecurityHelper.getAgencies(agencyId, agencyName);
	if (obj instanceof List<?>)
	    {
	    	List<AgencyEntityBean> agencyEBeans = (List<AgencyEntityBean>) obj;
	    	return populateAgenciesFromAgencyEntityBeans(agencyEBeans);
	    }
	    else
	    {
		if (obj instanceof Agency)
		{
		    AgencyEntityBean agency = (AgencyEntityBean) obj;
		    return populateAgencyFromAgencyEntityBean(agency);
		}
	    }
	return null;
    }
    
    /**
     * populateAgencyFromAgencyEntityBean
     * @param agencyEBean
     * @return Agency
     */
    public static Agency populateAgencyFromAgencyEntityBean(AgencyEntityBean agencyEBean)
    {
	if (agencyEBean != null)
	{
	    Agency agency = new Agency();
	    MetaDataResponseEvent respEvent = new MetaDataResponseEvent();
	    respEvent.setCount(agencyEBean.getMaxRecCount());
	    agency.setMetaDataRespEvent(respEvent);
	    agency.setAgencyId(agencyEBean.getAgencyid());
	    agency.setAgencyName(agencyEBean.getAgencyname());
	    List<Department> departments =  DepartmentHelper.populateDepartmentsFromDeptEntityBeans(agencyEBean.getDepartments());
	    agency.setDepartments(departments);
	    return agency;
	}
	return null;
    }
    /**
     * populateDepartmentFromDeptEntityBean
     * @param deptBean
     * @return Department
     */
    public static List<Agency> populateAgenciesFromAgencyEntityBeans(List<AgencyEntityBean> agencyEBeans)
    {
	List<Agency> agencies = new ArrayList<Agency>();
	if (agencyEBeans != null)
	{
	    Iterator<AgencyEntityBean> agenciesEBeansItr = agencyEBeans.iterator();
	    while (agenciesEBeansItr.hasNext())
	    {
		AgencyEntityBean agencyEBean = agenciesEBeansItr.next();
		if (agencyEBean != null)
		{
		    Agency agency = populateAgencyFromAgencyEntityBean(agencyEBean);
		    agencies.add(agency);
		}
	    }
	}
	return agencies;
    }
}
