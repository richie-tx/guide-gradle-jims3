package mojo.km.security;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import mojo.km.messaging.MetaDataResponseEvent;


/**
 * 
 * @author sthyagarajan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyEntityBean
{
    private String agencyid;
    private String agencyname;
    private List<DepartmentEntityBean> departments;
    private int maxRecCount;
    
    /**
     * @return the agencyid
     */
    public String getAgencyid()
    {
        return agencyid;
    }
    /**
     * @param agencyid the agencyid to set
     */
    public void setAgencyid(String agencyid)
    {
        this.agencyid = agencyid;
    }
    /**
     * @return the agencyname
     */
    public String getAgencyname()
    {
        return agencyname;
    }
    /**
     * @param agencyname the agencyname to set
     */
    public void setAgencyname(String agencyname)
    {
        this.agencyname = agencyname;
    }
    /**
     * @return the departments
     */
    public List<DepartmentEntityBean> getDepartments()
    {
	return departments;
    }
    /**
     * @param departments the departments to set
     */
    public void setDepartments(List<DepartmentEntityBean> departments)
    {
	this.departments = departments;
    }

    /**
     * @return the maxRecCount
     */
    public int getMaxRecCount()
    {
	return maxRecCount;
    }
    /**
     * @param maxRecCount the maxRecCount to set
     */
    public void setMaxRecCount(int maxRecCount)
    {
	this.maxRecCount = maxRecCount;
    }
}
