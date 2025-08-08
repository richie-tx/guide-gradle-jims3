package mojo.km.security;

import java.util.List;

/** @author sthyagarajan */

public class DepartmentEntityBean 
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String departmentid;
    private String departmentdescription;
    private String orgcode;
    private String departmentstatus;
    private AgencyEntityBean agency;
    private List<DepartmentContactEntityBean> departmentcontacts;
  
    
    /** @return the departmentdescription */
    public String getDepartmentdescription()
    {
	return departmentdescription;
    }

    /** @param departmentdescription
     *            the departmentdescription to set */
    public void setDepartmentdescription(String departmentdescription)
    {
	this.departmentdescription = departmentdescription;
    }

    /** @return the orgcode */
    public String getOrgcode()
    {
	return orgcode;
    }

    /** @param orgcode
     *            the orgcode to set */
    public void setOrgcode(String orgcode)
    {
	this.orgcode = orgcode;
    }

    /**
     * @return the departmentid
     */
    public String getDepartmentid()
    {
	return departmentid;
    }

    /**
     * @param departmentid the departmentid to set
     */
    public void setDepartmentid(String departmentid)
    {
	this.departmentid = departmentid;
    }

    /**
     * @return the agency
     */
    public AgencyEntityBean getAgency()
    {
        return agency;
    }

    /**
     * @param agency the agency to set
     */
    public void setAgency(AgencyEntityBean agency)
    {
        this.agency = agency;
    }

    /**
     * @return the departmentcontacts
     */
    public List<DepartmentContactEntityBean> getDepartmentcontacts()
    {
        return departmentcontacts;
    }

    /**
     * @param departmentcontacts the departmentcontacts to set
     */
    public void setDepartmentcontacts(List<DepartmentContactEntityBean> departmentcontacts)
    {
        this.departmentcontacts = departmentcontacts;
    }

    /**
     * @return the departmentStatus
     */
    public String getDepartmentstatus()
    {
	return departmentstatus;
    }

    /**
     * @param departmentStatus the departmentStatus to set
     */
    public void setDepartmentstatus(String departmentstatus)
    {
	this.departmentstatus = departmentstatus;
    }

    
}
