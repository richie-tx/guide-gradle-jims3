/*
 * Created on May 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.agency;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetDepartmentsEvent extends RequestEvent
{
	private String agencyId;
	private String agencyName;
	private String departmentName;
	private String departmentId;
	private String statusId;
	private String originatingAgencyId;
	private String createOfficerProfileInd;
	private String setcicAccessId;
	

	/**
	@roseuid 4107BEE20035
	 */
	public GetDepartmentsEvent()
	{

	}

	/**
	@param agencyId
	@roseuid 4107B06D008E
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	@return String
	@roseuid 4107B06D0090
	 */
	public String getAgencyId()
	{
		return this.agencyId;
	}
	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @return
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}

	/**
	 * @param string
	 */
	public void setDepartmentName(String string)
	{
		departmentName = string;
	}

	/**
	 * @return
	 */
	public String getOriginatingAgencyId()
	{
		return originatingAgencyId;
	}

	/**
	 * @return
	 */
	public String getSetcicAccessId()
	{
		return setcicAccessId;
	}

	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @param string
	 */
	public void setOriginatingAgencyId(String string)
	{
		originatingAgencyId = string;
	}

	/**
	 * @param string
	 */
	public void setSetcicAccessId(String string)
	{
		setcicAccessId = string;
	}

	/**
	 * @param string
	 */
	public void setStatusId(String string)
	{
		statusId = string;
	}

	/**
	 * @return
	 */
	public String getCreateOfficerProfileInd()
	{
		return createOfficerProfileInd;
	}

	/**
	 * @param string
	 */
	public void setCreateOfficerProfileInd(String string)
	{
		createOfficerProfileInd = string;
	}

}
