/*
 * Created on Nov 7, 2006
 *
 */
package messaging.officer;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 */
public class ValidateOfficerEvent extends RequestEvent
{

    private String departmentId;

    private String logonId;

    private String officerId;

    private String officerIdType;

    private boolean validateByUserId;

    /**
     * @return Returns the departmentId.
     */
    public String getDepartmentId()
    {
        return departmentId;
    }

    /**
     * @return Returns the loginId.
     */
    public String getLogonId()
    {
        return logonId;
    }

    /**
     * @return Returns the officerId.
     */
    public String getOfficerId()
    {
        return officerId;
    }

    /**
     * @return Returns the officerTypeId.
     */
    public String getOfficerIdType()
    {
        return officerIdType;
    }

    /**
     * @return Returns the validateByUserId.
     */
    public boolean isValidateByUserId()
    {
        return validateByUserId;
    }

    /**
     * @param departmentId
     *            The departmentId to set.
     */
    public void setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
    }

    /**
     * @param loginId
     *            The loginId to set.
     */
    public void setLogonId(String logonId)
    {
        this.logonId = logonId;
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
     * @param officerTypeId
     *            The officerTypeId to set.
     */
    public void setOfficerIdType(String officerIdType)
    {
        this.officerIdType = officerIdType;
    }

    /**
     * @param validateByUserId
     *            The validateByUserId to set.
     */
    public void setValidateByUserId(boolean validateByUserId)
    {
        this.validateByUserId = validateByUserId;
    }
}
