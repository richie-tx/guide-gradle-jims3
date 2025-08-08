package pd.criminalcase;

import java.util.Date;

import pd.common.util.DateFormatter;
import pd.supervision.supervisionorder.SupervisionOrderReferenceVariableHelper;
import naming.PDConstants;
import naming.UIConstants;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;

/**
 * @roseuid 442984B3027E
 */
public class Supervision extends PersistentObject {
    /**
     * @roseuid 442983080378
     */
    public static Supervision find(String criminalCaseId) {
        IHome home = new Home();
        return (Supervision) home.find(criminalCaseId, Supervision.class);
    }

    /**
     * Properties for criminalCase
     */
    private CriminalCase criminalCase = null;

    private String criminalCaseId;

    private String deferredSupervisionLength;

    private String deferredTermPeriod;

    private String jailTime;

    private String prisonTime;

    private String probationEndDate;

    private String probationStartDate;

    private String stateJailFelonyConfinementBeginDate;
    private String stateJailFelonyConfinementDays;
    
    private String stateJailFelonyConfinementMonths;

    private String stateJailFelonyConfinementNum;

    private String stateJailFelonyConfinementTimePeriod;
    private String stateJailFelonyConfinementYears;

    public String supervisionLengthNum;

    public String supervisionLengthTimePeriod;

    /**
     * Properties for supervisionOrders
     * 
     * @referencedType pd.supervision.supervisionorder.SupervisionOrder
     */
    private java.util.Collection supervisionOrders = null;

    /**
     * @roseuid 442984B3027E
     */
    public Supervision() {
    }

    /**
     * Clears all pd.supervision.supervisionorder.SupervisionOrder from class
     * relationship collection.
     */
    public void clearSupervisionOrders() {
        initSupervisionOrders();
        supervisionOrders.clear();
    }

    /**
     * Gets referenced type pd.criminalcase.CriminalCase
     */
    public CriminalCase getCriminalCase() {
        initCriminalCase();
        return criminalCase;
    }

    /**
     * Get the reference value to class :: pd.criminalcase.CriminalCase
     */
    public String getCriminalCaseId() {
        fetch();
        return criminalCaseId;
    }

    /**
     * @return
     */
    public String getDeferredSupervisionLength() {
        fetch();
        return deferredSupervisionLength;
    }

    /**
     * @return
     */
    public String getDeferredTermPeriod() {
        fetch();
        return deferredTermPeriod;
    }

    /**
     * @return
     */
    public String getFormattedDeferredSupervisionLength() {
        fetch();
        String formattedNum = PDConstants.BLANK;
        if (!deferredSupervisionLength.equals(PDConstants.NONE)
                && !deferredSupervisionLength.equals(PDConstants.FOUR_ZEROES)) {
            formattedNum = deferredSupervisionLength;
        }
        return formattedNum;
    }

    public String getFormattedDeferredSupervisionLength2() {
        return getFormattedDeferredSupervisionLength();
    }

    /**
     * @return
     */
    public String getFormattedDeferredTermPeriod() {
        fetch();
        return SupervisionOrderReferenceVariableHelper.getFormattedTimePeriod(deferredTermPeriod,
                deferredSupervisionLength);
    }

    public String getFormattedDeferredTermPeriod2() {
        return getFormattedDeferredTermPeriod();
    }

    /**
     * @return
     */
    public String getFormattedJailTime() {
        fetch();
        String formattedNum = PDConstants.BLANK;
        if (!jailTime.equals(PDConstants.NONE) && !jailTime.equals(PDConstants.BLANK)
                && !jailTime.equals(PDConstants.FOUR_ZEROES)) {
            String derivedJailTime = SupervisionOrderReferenceVariableHelper.getDerivedTime(jailTime);
            formattedNum = derivedJailTime;
        }
        return formattedNum;
    }

    public String getFormattedJailTime2() {
        return getFormattedJailTime();
    }

    /**
     * @return
     */
    public String getFormattedJailTimePeriod() {
        fetch();
        String formattedNum = PDConstants.BLANK;
        if (!jailTime.equals(PDConstants.NONE) && !jailTime.equals(PDConstants.BLANK)
                && !jailTime.equals(PDConstants.FOUR_ZEROES)) {
            String derivedJailTimePeriod = SupervisionOrderReferenceVariableHelper.getDerivedTimePeriod(jailTime);
            formattedNum = derivedJailTimePeriod;
        }
        return formattedNum;
    }

    public String getFormattedJailTimePeriod2() {
        return getFormattedJailTimePeriod();
    }

    public String getFormattedJailTimePeriod3() {
        return getFormattedJailTimePeriod();
    }

    /**
     * @return
     */
    public String getFormattedPrisonTime() {
        fetch();
        String formattedNum = PDConstants.BLANK;
        if (!prisonTime.equals(PDConstants.NONE) && !prisonTime.equals(PDConstants.TWELVE_ZEROES)) {
            formattedNum = prisonTime;
        }
        return formattedNum;
    }

    /**
     * @return
     */
    public String getFormattedProbationEndDate() {
        fetch();
        Date aDate = null;
        String aDateString = PDConstants.BLANK;
        if (probationStartDate != null && !probationEndDate.equals(PDConstants.SIX_ZEROES)
                && !probationEndDate.equals(PDConstants.NONE)) {
            aDate = DateUtil.stringToDate(probationEndDate, PDConstants.DATE_FORMAT_MMDDYY);
            if (aDate != null) {
                aDateString = DateUtil.dateToString(aDate, UIConstants.DATE_FMT_1);
            }
        }
        return aDateString;
    }

    /**
     * @return
     */
    public String getFormattedProbationEndDay() {
        return getFormattedProbationEndDate();
    }

    /**
     * @return
     */
    public String getFormattedProbationEndMonth() {
        return getFormattedProbationEndDate();
    }

    /**
     * @return
     */
    public String getFormattedProbationEndYear() {
        return getFormattedProbationEndDate();
    }

    /**
     * @return
     */
    public String getFormattedProbationStartDate() {
        fetch();
        Date aDate = null;
        String aDateString = PDConstants.BLANK;

        if (probationStartDate != null && !probationStartDate.equals(PDConstants.SIX_ZEROES)
                && !probationStartDate.equals(PDConstants.NONE)) {
            aDate = DateUtil.stringToDate(probationStartDate, PDConstants.DATE_FORMAT_MMDDYY);
            if (aDate != null) {
                aDateString = DateUtil.dateToString(aDate, UIConstants.DATE_FMT_1);
            }
        }
        return aDateString;
    }

    public String getFormattedProbationStartDate2() {
        return getFormattedProbationStartDate();
    }

    /**
     * @return
     */
    public String getFormattedProbationStartDay() {
        return getFormattedProbationStartDate();
    }

    /**
     * @return
     */
    public String getFormattedProbationStartMonth() {
        return getFormattedProbationStartDate();
    }

    /**
     * @return
     */
    public String getFormattedProbationStartYear() {
        return getFormattedProbationStartDate();
    }

    /**
     * @return
     */
    public String getFormattedStateJailFelonyConfinementBeginDate() {
        fetch();
        Date aDate = null;
        String aDateString = PDConstants.BLANK;
        if (stateJailFelonyConfinementBeginDate != null
                && !stateJailFelonyConfinementBeginDate.equals(PDConstants.SIX_ZEROES)
                && !stateJailFelonyConfinementBeginDate.equals(PDConstants.NONE)) {
            aDate = DateUtil.stringToDate(stateJailFelonyConfinementBeginDate, PDConstants.DATE_FORMAT_MMDDYY);
            if (aDate != null) {
                aDateString = DateUtil.dateToString(aDate, UIConstants.DATE_FMT_1);
            }
        }
        return aDateString;
    }

    /**
     * @return
     */
    public String getFormattedStateJailFelonyConfinementNum() {
        fetch();
//        String formattedNum = PDConstants.BLANK;
//        if (!stateJailFelonyConfinementNum.equals(PDConstants.NONE)
//                && !stateJailFelonyConfinementNum.equals(PDConstants.FOUR_ZEROES)) {
//            formattedNum = stateJailFelonyConfinementNum;
//        }
//        return formattedNum;
        String confineNum = PDConstants.BLANK;;
        if (this.getStateJailFelonyConfinementNum() == null 
                || this.stateJailFelonyConfinementNum.trim().equals(PDConstants.BLANK)
                || this.stateJailFelonyConfinementNum.equals(PDConstants.FOUR_ZEROES)){
            if (this.stateJailFelonyConfinementYears != null 
                    && !this.stateJailFelonyConfinementYears.trim().equals(PDConstants.BLANK) 
                    && !this.getStateJailFelonyConfinementYears().equals(PDConstants.FOUR_ZEROES)){
                confineNum = this.stateJailFelonyConfinementYears;
            } else if (this.stateJailFelonyConfinementMonths != null 
                    && !this.stateJailFelonyConfinementMonths.trim().equals(PDConstants.BLANK) 
                    && !this.stateJailFelonyConfinementMonths.equals(PDConstants.FOUR_ZEROES)){
                confineNum = this.stateJailFelonyConfinementMonths;
            } else {
                confineNum = this.stateJailFelonyConfinementDays;
            }
        } else {
            confineNum = this.getStateJailFelonyConfinementNum();
        }
        return confineNum;
    }

    /**
     * @return
     */
    public String getFormattedStateJailFelonyConfinementTimePeriod() {
        fetch();
        String confineNum = PDConstants.BLANK;;
        String timePeriod = PDConstants.BLANK;;
        if (this.stateJailFelonyConfinementTimePeriod == null || 
                this.getStateJailFelonyConfinementTimePeriod().trim().equals(PDConstants.BLANK)
                ){
            if (this.stateJailFelonyConfinementYears != null 
                    && !this.stateJailFelonyConfinementYears.trim().equals(PDConstants.BLANK) 
                    && !this.getStateJailFelonyConfinementYears().equals(PDConstants.FOUR_ZEROES)){
                timePeriod = PDConstants.YEAR;
                confineNum = this.stateJailFelonyConfinementYears;
            } else if (this.stateJailFelonyConfinementMonths != null 
                    && !this.stateJailFelonyConfinementMonths.trim().equals(PDConstants.BLANK) 
                    && !this.stateJailFelonyConfinementMonths.equals(PDConstants.FOUR_ZEROES)){
                timePeriod = PDConstants.MONTH;
                confineNum = this.stateJailFelonyConfinementMonths;
            } else {
                timePeriod = PDConstants.DAY;
                confineNum = this.stateJailFelonyConfinementDays;
            }
        } else {
            timePeriod = this.getStateJailFelonyConfinementTimePeriod();
            confineNum = this.getStateJailFelonyConfinementNum();
        }
//        return SupervisionOrderReferenceVariableHelper.getFormattedTimePeriod(stateJailFelonyConfinementTimePeriod,
//                stateJailFelonyConfinementNum);
        return SupervisionOrderReferenceVariableHelper.getFormattedTimePeriod(timePeriod, confineNum);
    }

    /**
     * @return
     */
    public String getFormattedSupervisionLengthNum() {
        fetch();
        String formattedNum = PDConstants.BLANK;
        this.getSupervisionLengthNum();
        if (!supervisionLengthNum.equals(PDConstants.NONE)) {
            formattedNum = supervisionLengthNum;
        }
        return formattedNum;
    }

    /**
     * @return
     */
    public String getFormattedSupervisionLengthTimePeriod() {
        fetch();
        this.getSupervisionLengthTimePeriod();
        return SupervisionOrderReferenceVariableHelper.getFormattedTimePeriod(supervisionLengthTimePeriod,
                supervisionLengthNum);
    }

    /**
     * @return
     */
    public String getJailTime() {
        fetch();
        return jailTime;
    }

    /**
     * @return
     */
    public String getPrisonTime() {
        fetch();
        return prisonTime;
    }

    /**
     * @return
     */
    public String getProbationEndDate() {
        fetch();
        return probationEndDate;
    }

    /**
     * @return
     */
    public String getProbationStartDate() {
        fetch();
        return probationStartDate;
    }

    /**
     * @return
     */
    public String getStateJailFelonyConfinementBeginDate() {
        fetch();
        return stateJailFelonyConfinementBeginDate;
    }

	/**
	 * @return Returns the stateJailFelonyConfinementDays.
	 */
	public String getStateJailFelonyConfinementDays() {
		fetch();
		return stateJailFelonyConfinementDays;
	}
	/**
	 * @return Returns the stateJailFelonyConfinementMonths.
	 */
	public String getStateJailFelonyConfinementMonths() {
		fetch();
		return stateJailFelonyConfinementMonths;
	}

    /**
     * @return
     */
    public String getStateJailFelonyConfinementNum() {
        fetch();
        return stateJailFelonyConfinementNum;
    }

    /**
     * @return
     */
    public String getStateJailFelonyConfinementTimePeriod() {
        fetch();
        return stateJailFelonyConfinementTimePeriod;
    }
	/**
	 * @return Returns the stateJailFelonyConfinementYears.
	 */
	public String getStateJailFelonyConfinementYears() {
		fetch();
		return stateJailFelonyConfinementYears;
	}

    /**
     * @return
     */
    public String getSupervisionLengthNum() {
        fetch();
        if (supervisionLengthNum != null && supervisionLengthNum.equals("0000")) {
            if (deferredSupervisionLength != null && !deferredSupervisionLength.equals("0000")) {
                supervisionLengthNum = deferredSupervisionLength;
                supervisionLengthTimePeriod = deferredTermPeriod;
            }
        }
        if ((supervisionLengthNum == null || supervisionLengthNum.equals("0000") || supervisionLengthNum.equals(""))
                && (probationStartDate != null && !probationStartDate.equals("000000") && probationEndDate != null && !probationEndDate
                        .equals("000000"))) {
            Date startDate = DateUtil.stringToDate(probationStartDate, PDConstants.DATE_FORMAT_MMDDYY);
            Date endDate = DateUtil.stringToDate(probationEndDate, PDConstants.DATE_FORMAT_MMDDYY);
            int days = DateFormatter.getDayDiff(startDate, endDate);
            int years = DateFormatter.getYears(days);
            int months = DateFormatter.getMonths(days);
            if (years > 0) {
                supervisionLengthNum = new Integer(years).toString();
                supervisionLengthTimePeriod = "Y";
            } else if (months > 0) {
                supervisionLengthNum = new Integer(months).toString();
                supervisionLengthTimePeriod = "M";
            } else {
                supervisionLengthNum = new Integer(days).toString();
                supervisionLengthTimePeriod = "D";
            }
        }
        return supervisionLengthNum;
    }

    /**
     * @return
     */
    public String getSupervisionLengthTimePeriod() {
        fetch();
        if (supervisionLengthTimePeriod != null && !supervisionLengthTimePeriod.equals("")) {
            if (deferredTermPeriod != null && !deferredTermPeriod.equals("")) {
                supervisionLengthTimePeriod = deferredTermPeriod;
            }
        }
        if ((supervisionLengthTimePeriod == null || supervisionLengthTimePeriod.equals(" ")
                || supervisionLengthTimePeriod.equals("0") || supervisionLengthTimePeriod.equals(""))
                && (probationStartDate != null && !probationStartDate.equals("000000") && probationEndDate != null && !probationEndDate
                        .equals("000000"))) {
            Date startDate = DateUtil.stringToDate(probationStartDate, PDConstants.DATE_FORMAT_MMDDYY);
            Date endDate = DateUtil.stringToDate(probationEndDate, PDConstants.DATE_FORMAT_MMDDYY);
            int days = DateFormatter.getDayDiff(startDate, endDate);
            int years = DateFormatter.getYears(days);
            int months = DateFormatter.getMonths(days);
            if (years > 0) {
                supervisionLengthTimePeriod = "Y";
                supervisionLengthNum = new Integer(years).toString();
            } else if (months > 0) {
                supervisionLengthTimePeriod = "M";
                supervisionLengthNum = new Integer(months).toString();
            } else {
                supervisionLengthTimePeriod = "D";
                supervisionLengthNum = new Integer(days).toString();
            }
        }
        return supervisionLengthTimePeriod;
    }

    /**
     * returns a collection of pd.supervision.supervisionorder.SupervisionOrder
     */
    public java.util.Collection getSupervisionOrders() {
        fetch();
        initSupervisionOrders();
        return supervisionOrders;
    }

    /**
     * Initialize class relationship to class pd.criminalcase.CriminalCase
     */
    private void initCriminalCase() {
        if (criminalCase == null) {
            criminalCase = (CriminalCase) new mojo.km.persistence.Reference(criminalCaseId,
                    CriminalCase.class).getObject();
        }
    }

    /**
     * Initialize class relationship implementation for
     * pd.supervision.supervisionorder.SupervisionOrder
     */
    private void initSupervisionOrders() {
        if (supervisionOrders == null) {
            if (this.getOID() == null) {
                new mojo.km.persistence.Home().bind(this);
            }
            supervisionOrders = new mojo.km.persistence.ArrayList(
                    pd.supervision.supervisionorder.SupervisionOrder.class, "supervisionId", "" + getOID());
        }
    }

    /**
     * insert a pd.supervision.supervisionorder.SupervisionOrder into class
     * relationship collection.
     */
    public void insertSupervisionOrders(pd.supervision.supervisionorder.SupervisionOrder anObject) {
        initSupervisionOrders();
        supervisionOrders.add(anObject);
    }

    /**
     * Removes a pd.supervision.supervisionorder.SupervisionOrder from class
     * relationship collection.
     */
    public void removeSupervisionOrders(pd.supervision.supervisionorder.SupervisionOrder anObject) {
        initSupervisionOrders();
        supervisionOrders.remove(anObject);
    }

    /**
     * set the type reference for class member criminalCase
     */
    public void setCriminalCase(CriminalCase criminalCase) {
        if (this.criminalCase == null || !this.criminalCase.equals(criminalCase)) {
            markModified();
        }
        if (criminalCase.getOID() == null) {
            new mojo.km.persistence.Home().bind(criminalCase);
        }
        setCriminalCaseId("" + criminalCase.getOID());
        this.criminalCase = (CriminalCase) new mojo.km.persistence.Reference(criminalCase).getObject();
    }

    /**
     * Set the reference value to class :: pd.criminalcase.CriminalCase
     */
    public void setCriminalCaseId(String criminalCaseId) {
        if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId)) {
            markModified();
        }
        criminalCase = null;
        this.criminalCaseId = criminalCaseId;
    }

    /**
     * @param string
     */
    public void setDeferredSupervisionLength(String string) {
        if (this.deferredSupervisionLength == null || !this.deferredSupervisionLength.equals(string)) {
            markModified();
        }
        deferredSupervisionLength = string;
    }

    /**
     * @param string
     */
    public void setDeferredTermPeriod(String string) {
        if (this.deferredTermPeriod == null || !this.deferredTermPeriod.equals(string)) {
            markModified();
        }
        deferredTermPeriod = string;
    }

    /**
     * @param string
     */
    public void setJailTime(String string) {
        if (this.jailTime == null || !this.jailTime.equals(string)) {
            markModified();
        }
        jailTime = string;
    }

    /**
     * @param string
     */
    public void setPrisonTime(String string) {
        if (this.prisonTime == null || !this.prisonTime.equals(string)) {
            markModified();
        }
        prisonTime = string;
    }

    /**
     * @param aString
     */
    public void setProbationEndDate(String aString) {
        if (this.probationEndDate == null || !this.probationEndDate.equals(aString)) {
            markModified();
        }
        probationEndDate = aString;
    }

    /**
     * @param aString
     */
    public void setProbationStartDate(String aString) {
        if (this.probationStartDate == null || !this.probationStartDate.equals(aString)) {
            markModified();
        }
        probationStartDate = aString;
    }

    /**
     * @param string
     */
    public void setStateJailFelonyConfinementBeginDate(String string) {
        if (this.stateJailFelonyConfinementBeginDate == null
                || !this.stateJailFelonyConfinementBeginDate.equals(string)) {
            markModified();
        }
        stateJailFelonyConfinementBeginDate = string;
    }
	/**
	 * @param stateJailFelonyConfinementDays The stateJailFelonyConfinementDays to set.
	 */
	public void setStateJailFelonyConfinementDays(
			String aString) {
        if (this.stateJailFelonyConfinementDays == null
                || !this.stateJailFelonyConfinementDays.equals(aString)) {
            markModified();
        }

		this.stateJailFelonyConfinementDays = aString;
	}
	/**
	 * @param stateJailFelonyConfinementMonths The stateJailFelonyConfinementMonths to set.
	 */
	public void setStateJailFelonyConfinementMonths(
			String aString) {
        if (this.stateJailFelonyConfinementMonths == null
                || !this.stateJailFelonyConfinementMonths.equals(aString)) {
            markModified();
        }		
		this.stateJailFelonyConfinementMonths = aString;
	}

     /**
     * @param string
     */
    public void setStateJailFelonyConfinementNum(String string) {
        if (this.stateJailFelonyConfinementNum == null || !this.stateJailFelonyConfinementNum.equals(string)) {
            markModified();
        }
        stateJailFelonyConfinementNum = string;
    }

    /**
     * @param string
     */
    public void setStateJailFelonyConfinementTimePeriod(String string) {
        if (this.stateJailFelonyConfinementTimePeriod == null
                || !this.stateJailFelonyConfinementTimePeriod.equals(string)) {
            markModified();
        }
        stateJailFelonyConfinementTimePeriod = string;
    }
	/**
	 * @param stateJailFelonyConfinementYears The stateJailFelonyConfinementYears to set.
	 */
	public void setStateJailFelonyConfinementYears(
			String aString) {
        if (this.stateJailFelonyConfinementYears == null || !this.stateJailFelonyConfinementYears.equals(aString)) {
            markModified();
        }
		this.stateJailFelonyConfinementYears = aString;
	}

    /**
     * @param string
     */
    public void setSupervisionLengthNum(String string) {
        if (this.supervisionLengthNum == null || !this.supervisionLengthNum.equals(string)) {
            markModified();
        }
        supervisionLengthNum = string;
    }

    /**
     * @param string
     */
    public void setSupervisionLengthTimePeriod(String string) {
        if (this.supervisionLengthTimePeriod == null || !this.supervisionLengthTimePeriod.equals(string)) {
            markModified();
        }
        supervisionLengthTimePeriod = string;
    }
}
