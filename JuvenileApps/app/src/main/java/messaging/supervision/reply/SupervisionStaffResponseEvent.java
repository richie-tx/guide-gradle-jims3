/*
 * Created on Feb 17, 2006
 *
 */
package messaging.supervision.reply;

import naming.PDConstants;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 *
 */
public class SupervisionStaffResponseEvent extends ResponseEvent implements Comparable
{
	private String courtNum;
	private String firstName;
	private String lastName;
	private String logonId;
	private String middleName;
	private String positionName;
	private String probationOfficerInd;
	private String supervisionStaffId;
	private String unit;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		SupervisionStaffResponseEvent re = (SupervisionStaffResponseEvent) arg0;
		
		this.initializeAttributes(re);
		
		int comparisonResult = 0;
		
		if (this.getLastName().compareTo(re.getLastName()) == 0)
		{
			if (this.getFirstName().compareTo(re.getFirstName()) == 0)
			{
				comparisonResult = this.getMiddleName().compareTo(re.getMiddleName());
			}
			else
			{
				comparisonResult = this.getFirstName().compareTo(re.getFirstName());
			}
		}
		else
		{
			comparisonResult = this.getLastName().compareTo(re.getLastName());
		}
		
		return comparisonResult;
	}

	/**
     * @return Returns the courtNum.
     */
    public String getCourtNum() {
        return courtNum;
    }

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

    /**
	 * @return
	 */
	public String getFormattedName()
	{
		Name fullName = new Name(this.firstName, this.middleName, this.lastName);
		StringBuffer formattedName = new StringBuffer(fullName.getFormattedName());
		if (this.getPositionName() != null && !this.getPositionName().equals(PDConstants.BLANK)){
			formattedName.append(" | ");
			formattedName.append(this.getPositionName());
		}
		return (formattedName.toString());
	}
	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * @return
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @return
	 */
	public String getSupervisionStaffId()
	{
		return supervisionStaffId;
	}

	/**
     * @return Returns the unit.
     */
    public String getUnit() {
        return unit;
    }
    /**
	 * @param re
	 */
	private void initializeAttributes(SupervisionStaffResponseEvent re)
	{
		String newString = "";
		if (this.getFirstName() == null)
		{
			this.setFirstName(newString);
		}
		if (this.getLastName() == null)
		{
			this.setLastName(newString);
		}
		if (this.getMiddleName() == null)
		{
			this.setMiddleName(newString);
		}
		if (re.getFirstName() == null)
		{
			re.setFirstName(newString);
		}
		if (re.getLastName() == null)
		{
			re.setLastName(newString);
		}
		if (re.getMiddleName() == null)
		{
			re.setMiddleName(newString);
		}
	}

	/**
     * @param courtNum The courtNum to set.
     */
    public void setCourtNum(String courtNum) {
        this.courtNum = courtNum;
    }
    /**
	 * @param aFirstName
	 */
	public void setFirstName(String aFirstName)
	{
		firstName = aFirstName;
	}

	/**
	 * @param aLastName
	 */
	public void setLastName(String aLastName)
	{
		lastName = aLastName;
	}

	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

	/**
	 * @param aMiddleName
	 */
	public void setMiddleName(String aMiddleName)
	{
		middleName = aMiddleName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @param aSupervisionStaffId
	 */
	public void setSupervisionStaffId(String aSupervisionStaffId)
	{
		supervisionStaffId = aSupervisionStaffId;
	}
    /**
     * @param unit The unit to set.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

	public String getProbationOfficerInd() {
		return probationOfficerInd;
	}

	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}
}
