package messaging.administercaseload.reply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.contact.domintf.IName;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author Jim Fisher
 */
public class CaseAssignmentResponseEvent extends ResponseEvent
{

    private List caseAssignments;

    private ICaseAssignment currentAssignment;

    private String daysLeft;

    private String defendantId;

    private IName defendantName;
    
    private String defendantFullName;

    private String levelOfSupervision;

    private String jailIndicator;
    
    private String warrantIndicator;
    
    private Date lastFaceToFaceDate;
    
    private Date nextOfficeVisitDate;
    
    private String ssn;
    
    private String sexId;
    
    private Date dob;
    
    private String courtid;
    
    private String zipCode;
    
    public CaseAssignmentResponseEvent()
    {
        caseAssignments = new ArrayList();
    }

    public void addCaseAssignment(ICaseAssignment aCaseAssignment)
    {
        caseAssignments.add(aCaseAssignment);
    }

    public List getCaseAssignments()
    {
        return this.caseAssignments;
    }
    
    public void setCaseAssignments(List caseAssignments) {
    	this.caseAssignments = caseAssignments;
    }

    /**
     * @return Returns the currentAssignment.
     */
    public ICaseAssignment getCurrentAssignment()
    {
        return currentAssignment;
    }

    /**
     * @return Returns the daysLeft.
     */
    public String getDaysLeft()
    {
        return daysLeft;
    }

    /**
     * @return The absolute value of daysLeft.
     */
    public String getDaysLeftAbsValue() {
    	return String.valueOf(Math.abs(Integer.parseInt(daysLeft)));
    }
    
    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
     * @return Returns the defendantName.
     */
    public IName getDefendantName()
    {
        return defendantName;
    }

    /**
     * @return Returns the levelOfSupervision.
     */
    public String getLevelOfSupervision()
    {
        return levelOfSupervision;
    }

    /**
     * @param currentAssignment
     *        The currentAssignment to set.
     */
    public void setCurrentAssignment(ICaseAssignment currentAssignment)
    {
        this.currentAssignment = currentAssignment;
    }

    /**
     * @param daysLeft
     *        The daysLeft to set.
     */
    public void setDaysLeft(String daysLeft)
    {
        this.daysLeft = daysLeft;
    }

    /**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }

    /**
     * @param defendantName
     *        The defendantName to set.
     */
    public void setDefendantName(IName defendantName)
    {
        this.defendantName = defendantName;
    }

    /**
     * @param levelOfSupervision
     *        The levelOfSupervision to set.
     */
    public void setLevelOfSupervision(String levelOfSupervision)
    {
        this.levelOfSupervision = levelOfSupervision;
    }

	/**
	 * @return Returns the jailIndicator.
	 */
	public String getJailIndicator() {
		return jailIndicator;
	}
	/**
	 * @param jailIndicator The jailIndicator to set.
	 */
	public void setJailIndicator(String jailIndicator) {
		this.jailIndicator = jailIndicator;
	}
	/**
	 * @return Returns the lastFaceToFaceDate.
	 */
	public Date getLastFaceToFaceDate() {
		return lastFaceToFaceDate;
	}
	
	public String getFormattedLastFaceToFaceDate() {
		String retVal = "";
		if (lastFaceToFaceDate != null) {
			retVal = DateUtil.dateToString(lastFaceToFaceDate, DateUtil.DATE_FMT_1);
		}
		return retVal;
	}
	/**
	 * @param lastFaceToFaceDate The lastFaceToFaceDate to set.
	 */
	public void setLastFaceToFaceDate(Date lastFaceToFaceDate) {
		this.lastFaceToFaceDate = lastFaceToFaceDate;
	}
	/**
	 * @return Returns the nextOfficeVisitDate.
	 */
	public Date getNextOfficeVisitDate() {
		return nextOfficeVisitDate;
	}
	
	public String getFormattedNextOfficeVisitDate() {
		String retVal = "";
		if (nextOfficeVisitDate != null) {
			retVal = DateUtil.dateToString(nextOfficeVisitDate, DateUtil.DATE_FMT_1);
		}
		return retVal;		
	}
	/**
	 * @param nextOfficeVisitDate The nextOfficeVisitDate to set.
	 */
	public void setNextOfficeVisitDate(Date nextOfficeVisitDate) {
		this.nextOfficeVisitDate = nextOfficeVisitDate;
	}
	/**
	 * @return Returns the warrantIndicator.
	 */
	public String getWarrantIndicator() {
		return warrantIndicator;
	}
	/**
	 * @param warrantIndicator The warrantIndicator to set.
	 */
	public void setWarrantIndicator(String warrantIndicator) {
		this.warrantIndicator = warrantIndicator;
	}
	
	public String getDefendantFullName() {
		return defendantFullName;
	}

	public void setDefendantFullName(String defendantFullName) {
		this.defendantFullName = defendantFullName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}



