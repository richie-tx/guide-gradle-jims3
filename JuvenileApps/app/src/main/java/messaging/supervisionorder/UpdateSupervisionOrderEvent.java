// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\UpdateSupervisionOrderEvent.java

package messaging.supervisionorder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateSupervisionOrderEvent extends RequestEvent {
	
	private boolean addRemoveCondition;
	private Date caseSupervisionBeginDate;

	private Date caseSupervisionEndDate;

	private String comments;

	private Collection conditions = new ArrayList();
	private int confinementLengthDays;
	private int confinementLengthMonths;
	private int confinementLengthYears;
	private String dispositionTypeId;
	private double fineAmount;
	private boolean isHistoricalOrder;

	private boolean isMigratedOrder;

	private String juvCourtId;
	private int juvSupervisionLengthDays;
	private int juvSupervisionLengthMonths;
	private int juvSupervisionLengthYears;
	private Date juvSupervisionBeginDate;

	private Date limitedSupervisionBeginDate;
	private Date limitedSupervisionEndDate;
	private boolean limitedSupervisionPeriod;

	private String modificationReason; 
	private boolean newVersion;
	private String orderId;
	private String orderStatusId;
	private String orderTitle;
	private String origJudgeFirstName;
	private String origJudgeLastName;
	private String plea;
	private String printedName;
	private String printedOffenseDesc;
	private String specialCourtCd;
	private String summaryChanges;
	private int supervisionLengthDays;
	private int supervisionLengthMonths;
	private int supervisionLengthYears;
	private int versionNum;
	private String versionType;
	/**
	 * @roseuid 43B2E430000F
	 */
	public UpdateSupervisionOrderEvent() {

	}
	/**
	 * @param collection
	 */
	public void addCondition(SupervisionOrderConditionEvent conditionEvent) {
		conditions.add(conditionEvent);
	}
	/**
     * @return Returns the caseSupervisionBeginDate.
     */
    public Date getCaseSupervisionBeginDate() {
        return caseSupervisionBeginDate;
    }
	/**
     * @return Returns the caseSupervisionEndDate.
     */
    public Date getCaseSupervisionEndDate() {
        return caseSupervisionEndDate;
    }

	public String getComments() {
		return comments;
	}

	/**
	 * @return
	 */
	public Collection getConditions() {
		return conditions;
	}

	/**
     * @return Returns the confinementLengthDays.
     */
    public int getConfinementLengthDays() {
        return confinementLengthDays;
    }
	/**
     * @return Returns the confinementLengthMonths.
     */
    public int getConfinementLengthMonths() {
        return confinementLengthMonths;
    }
	/**
     * @return Returns the confinementLengthYears.
     */
    public int getConfinementLengthYears() {
        return confinementLengthYears;
    }
	/**
     * @return Returns the dispositionTypeId.
     */
    public String getDispositionTypeId() {
        return dispositionTypeId;
    }
	/**
     * @return Returns the fineAmount.
     */
    public double getFineAmount() {
        return fineAmount;
    }
	/**
     * @return Returns the isHistoricalOrder.
     */
    public boolean getIsHistoricalOrder() {
        return isHistoricalOrder;
    }
	/**
	 * @return the juvCourtId
	 */
	public String getJuvCourtId() {
		return juvCourtId;
	}
	/**
	 * @return the juvSupervisionLengthDays
	 */
	public int getJuvSupervisionLengthDays() {
		return juvSupervisionLengthDays;
	}
	/**
	 * @return the juvSupervisionLengthMonths
	 */
	public int getJuvSupervisionLengthMonths() {
		return juvSupervisionLengthMonths;
	}
	/**
	 * @return the juvSupervisionLengthYears
	 */
	public int getJuvSupervisionLengthYears() {
		return juvSupervisionLengthYears;
	}
	/**
	 * @return the juvSupervisionBeginDate
	 */
	public Date getJuvSupervisionBeginDate() {
		return juvSupervisionBeginDate;
	}
	/**
	 * @return
	 */
	public Date getLimitedSupervisionBeginDate() {
		return limitedSupervisionBeginDate;
	}
	/**
	 * @return
	 */
	public Date getLimitedSupervisionEndDate() {
		return limitedSupervisionEndDate;
	}
    /**
	 * @return
	 */
	public String getModificationReason() {
		return modificationReason;
	}

	/**
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}
    public String getOrderStatusId() {
		return orderStatusId;
	}

	/**
	 * @return
	 */
	public String getOrderTitle() {
		return orderTitle;
	}
    public String getOrigJudgeFirstName() {
		return origJudgeFirstName;
	}
    public String getOrigJudgeLastName() {
		return origJudgeLastName;
	}
    /**
	 * @return Returns the plea.
	 */
	public String getPlea() {
		return plea;
	}
    public String getPrintedName() {
		return printedName;
	}
    public String getPrintedOffenseDesc() {
		return printedOffenseDesc;
	}
    public String getSpecialCourtCd() {
		return specialCourtCd;
	}

	/**
	 * @return
	 */
	public String getSummaryChanges() {
		return summaryChanges;
	}

	/**
     * @return Returns the supervisionLengthDays.
     */
    public int getSupervisionLengthDays() {
        return supervisionLengthDays;
    }
    /**
     * @return Returns the supervisionLengthMonths.
     */
    public int getSupervisionLengthMonths() {
        return supervisionLengthMonths;
    }
    /**
     * @return Returns the supervisionLengthYears.
     */
    public int getSupervisionLengthYears() {
        return supervisionLengthYears;
    }

	/**
	 * @return Returns the versionNum.
	 */
	public int getVersionNum() {
		return versionNum;
	}

	/**
	 * @return
	 */
	public String getVersionType() {
		return versionType;
	}

	/**
	 * @return Returns the addRemoveCondition.
	 */
	public boolean isAddRemoveCondition() {
		return addRemoveCondition;
	}

	/**
	 * @return
	 */
	public boolean isLimitedSupervisionPeriod() {
		return limitedSupervisionPeriod;
	}
    public boolean isMigratedOrder() {
		return isMigratedOrder;
	}
    /**
	 * @return
	 */
	public boolean isNewVersion() {
		return newVersion;
	}
    /**
	 * @param addRemoveCondition The addRemoveCondition to set.
	 */
	public void setAddRemoveCondition(boolean addRemoveCondition) {
		this.addRemoveCondition = addRemoveCondition;
	}
    /**
     * @param caseSupervisionBeginDate The caseSupervisionBeginDate to set.
     */
    public void setCaseSupervisionBeginDate(Date caseSupervisionBeginDate) {
        this.caseSupervisionBeginDate = caseSupervisionBeginDate;
    }

	/**
     * @param caseSupervisionEndDate The caseSupervisionEndDate to set.
     */
    public void setCaseSupervisionEndDate(Date caseSupervisionEndDate) {
        this.caseSupervisionEndDate = caseSupervisionEndDate;
    }

	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @param collection
	 */
	public void setConditions(Collection collection) {
		conditions = collection;
	}
	/**
     * @param confinementLengthDays The confinementLengthDays to set.
     */
    public void setConfinementLengthDays(int confinementLengthDays) {
        this.confinementLengthDays = confinementLengthDays;
    }
    /**
     * @param confinementLengthMonths The confinementLengthMonths to set.
     */
    public void setConfinementLengthMonths(int confinementLengthMonths) {
        this.confinementLengthMonths = confinementLengthMonths;
    }
    /**
     * @param confinementLengthYears The confinementLengthYears to set.
     */
    public void setConfinementLengthYears(int confinementLengthYears) {
        this.confinementLengthYears = confinementLengthYears;
    }

	/**
     * @param dispositionTypeId The dispositionTypeId to set.
     */
    public void setDispositionTypeId(String dispositionTypeId) {
        this.dispositionTypeId = dispositionTypeId;
    }
    /**
     * @param fineAmount The fineAmount to set.
     */
    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
    /**
	 * @param isHistoricalOrder The isHistoricalOrder to set.
	 */
	public void setHistoricalOrder(boolean isHistoricalOrder) {
		this.isHistoricalOrder = isHistoricalOrder;
	}
    /**
     * @param isHistoricalOrder The isHistoricalOrder to set.
     */
    public void setIsHistoricalOrder(boolean isHistoricalOrder) {
        this.isHistoricalOrder = isHistoricalOrder;
    }
    /**
	 * @param juvCourtId the juvCourtId to set
	 */
	public void setJuvCourtId(String juvCourtId) {
		this.juvCourtId = juvCourtId;
	}
	/**
	 * @param juvSupervisionLengthDays the juvSupervisionLengthDays to set
	 */
	public void setJuvSupervisionLengthDays(int juvSupervisionLengthDays) {
		this.juvSupervisionLengthDays = juvSupervisionLengthDays;
	}
	/**
	 * @param juvSupervisionLengthMonths the juvSupervisionLengthMonths to set
	 */
	public void setJuvSupervisionLengthMonths(int juvSupervisionLengthMonths) {
		this.juvSupervisionLengthMonths = juvSupervisionLengthMonths;
	}
	/**
	 * @param juvSupervisionLengthYears the juvSupervisionLengthYears to set
	 */
	public void setJuvSupervisionLengthYears(int juvSupervisionLengthYears) {
		this.juvSupervisionLengthYears = juvSupervisionLengthYears;
	}
	/**
	 * @param juvSupervisionBeginDate the juvSupervisionBeginDate to set
	 */
	public void setJuvSupervisionBeginDate(Date juvSupervisionBeginDate) {
		this.juvSupervisionBeginDate = juvSupervisionBeginDate;
	}
	/**
	 * @param date
	 */
	public void setLimitedSupervisionBeginDate(Date date) {
		limitedSupervisionBeginDate = date;
	}
    /**
	 * @param date
	 */
	public void setLimitedSupervisionEndDate(Date date) {
		limitedSupervisionEndDate = date;
	}
    /**
	 * @param b
	 */
	public void setLimitedSupervisionPeriod(boolean b) {
		limitedSupervisionPeriod = b;
	}

	public void setMigratedOrder(boolean isMigratedOrder) {
		this.isMigratedOrder = isMigratedOrder;
	}

	/**
	 * @param string
	 */
	public void setModificationReason(String string) {
		modificationReason = string;
	}
    /**
	 * @param b
	 */
	public void setNewVersion(boolean b) {
		newVersion = b;
	}

	/**
	 * @param string
	 */
	public void setOrderId(String string) {
		orderId = string;
	}
    public void setOrderStatusId(String orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	/**
	 * @param string
	 */
	public void setOrderTitle(String string) {
		orderTitle = string;
	}

	public void setOrigJudgeFirstName(String origJudgeFirstName) {
		this.origJudgeFirstName = origJudgeFirstName;
	}

	public void setOrigJudgeLastName(String origJudgeLastName) {
		this.origJudgeLastName = origJudgeLastName;
	}
	/**
	 * @param plea
	 *            The plea to set.
	 */
	public void setPlea(String plea) {
		this.plea = plea;
	}
    public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}
    public void setPrintedOffenseDesc(String printedOffenseDesc) {
		this.printedOffenseDesc = printedOffenseDesc;
	}
    public void setSpecialCourtCd(String specialCourtCd) {
		this.specialCourtCd = specialCourtCd;
	}
    /**
	 * @param string
	 */
	public void setSummaryChanges(String string) {
		summaryChanges = string;
	}
	/**
     * @param supervisionLengthDays The supervisionLengthDays to set.
     */
    public void setSupervisionLengthDays(int supervisionLengthDays) {
        this.supervisionLengthDays = supervisionLengthDays;
    }
	/**
     * @param supervisionLengthMonths The supervisionLengthMonths to set.
     */
    public void setSupervisionLengthMonths(int supervisionLengthMonths) {
        this.supervisionLengthMonths = supervisionLengthMonths;
    }
	/**
     * @param supervisionLengthYears The supervisionLengthYears to set.
     */
    public void setSupervisionLengthYears(int supervisionLengthYears) {
        this.supervisionLengthYears = supervisionLengthYears;
    }

	/**
	 * @param versionNum The versionNum to set.
	 */
	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	/**
	 * @param string
	 */
	public void setVersionType(String string) {
		versionType = string;
	}
}
