package pd.criminalcase;

import java.util.Date;

import naming.PDConstants;
import naming.UIConstants;
import pd.common.util.NumberFormatter;
import pd.supervision.supervisionorder.SupervisionOrderReferenceVariableHelper;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;

/**
 * @roseuid 442984BD0201
 */
public class CostBillSummary extends PersistentObject {
	/**
	 * @roseuid 4429830900C9
	 */
	static public CostBillSummary find(String criminalCaseId) {
		IHome home = new Home();
		return (CostBillSummary) home.find(criminalCaseId,
				CostBillSummary.class);
	}

	private String courtCostsInitialAmount;

	private String courtCostsInitialPaymentDate;

	private String courtCostsTotal;

	/**
	 * Properties for criminalCase
	 */
	private CriminalCase criminalCase = null;

	private String criminalCaseId;

	private String fineAmountTotal;

	private String fineAndCostsRate;

	private String fineAndCourtCostsBeginDate;

	private String fineAndCourtCostsTimePeriod;

	private String fineAndCourtCostsTotal;

	private String hcJailBeginDate;

	private String hcJailNum;

	private String hcJailTimePeriod;

	private String jailCreditNum;

	private String jailCreditTimePeriod;

	private String paymentLocation;

	//private CostBillPaymentSchedule [] costBillPaymentSchedules;

	/**
	 * @roseuid 442984BD0201
	 */
	public CostBillSummary() {
	}

	/**
	 * @return
	 */
	public String getCourtCostsInitialAmount() {
		fetch();
		return courtCostsInitialAmount;
	}

	/**
	 * @return
	 */
	public String getCourtCostsInitialPaymentDate() {
		fetch();
		return courtCostsInitialPaymentDate;
	}

	/**
	 * @return
	 */
	public String getCourtCostsTotal() {
		fetch();
		return courtCostsTotal;
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
	public String getFineAmountTotal() {
		fetch();
		return fineAmountTotal;
	}

	/**
	 * @return
	 */
	public String getFineAndCostsRate() {
		fetch();
		return fineAndCostsRate;
	}

	/**
	 * @return
	 */
	public String getFineAndCourtCostsBeginDate() {
		fetch();
		return fineAndCourtCostsBeginDate;
	}
    /**
     * @return Returns the fineAndCourtCostsTimePeriod.
     */
    public String getFineAndCourtCostsTimePeriod() {
        fetch();
        return fineAndCourtCostsTimePeriod;
    }

	/**
	 * @return
	 */
	public String getFineAndCourtCostsTotal() {
		fetch();
		return fineAndCourtCostsTotal;
	}

	/**
	 * @return
	 */
	public String getFormattedCourtCostsInitialAmount() {
		fetch();
		String formattedNum = PDConstants.BLANK;
		if (!courtCostsInitialAmount.equals(PDConstants.NONE)
				&& !courtCostsInitialAmount.equals(PDConstants.EIGHT_ZEROES)) {
			formattedNum = NumberFormatter.getNumberFromString(
					courtCostsInitialAmount, 2, false);
		}
		return formattedNum;
	}

	/**
	 * @return
	 */
	public String getFormattedCourtCostsInitialPaymentDate() {
		fetch();
		Date aDate = null;
		String aDateString = PDConstants.BLANK;
		if (courtCostsInitialPaymentDate != null
				&& !courtCostsInitialPaymentDate.equals(PDConstants.SIX_ZEROES)
				&& !courtCostsInitialPaymentDate.equals(PDConstants.NONE)) {
			aDate = DateUtil.stringToDate(courtCostsInitialPaymentDate,
					PDConstants.DATE_FORMAT_MMDDYY);
			if (aDate != null) {
				aDateString = DateUtil.dateToString(aDate,
						UIConstants.DATE_FMT_1);
			}
		}
		return aDateString;
	}

	/**
	 * @return
	 */
	public String getFormattedCourtCostsTotal() {
		fetch();
		String formattedNum = PDConstants.BLANK;
		if (!courtCostsTotal.equals(PDConstants.NONE)
				&& !courtCostsTotal.equals(PDConstants.EIGHT_ZEROES)) {
			formattedNum = NumberFormatter.getNumberFromString(courtCostsTotal,
					2, false);
		}
		return formattedNum;
	}

	/**
	 * @return
	 */
	public String getFormattedCourtCostsTotalDCR() {
		return getFormattedCourtCostsTotal();
	}

	/**
	 * @return
	 */
	public String getFormattedFineAmountTotal() {
		fetch();
		String formattedNum = PDConstants.BLANK;
		if (!fineAmountTotal.equals(PDConstants.NONE)
				&& !fineAmountTotal.equals(PDConstants.EIGHT_ZEROES)) {
			formattedNum = NumberFormatter.getNumberFromString(fineAmountTotal, 2, false);
		}
		return formattedNum;
	}
	/**
	 * @return
	 */
	public String getFormattedFineAmountTotalDCR() {
		fetch();
		return getFormattedFineAmountTotal();
	}

	/**
	 * @return
	 */
	public String getFormattedFineAndCostsRate() {
		fetch();
		String formattedNum = PDConstants.BLANK;
		if (!fineAndCostsRate.equals(PDConstants.NONE)
				&& !fineAndCostsRate.equals(PDConstants.EIGHT_ZEROES)) {
			formattedNum = NumberFormatter.getNumberFromString(
					fineAndCostsRate, 2, false);
		}
		return formattedNum;
	}

	/**
	 * @return
	 */
	public String getFormattedFineAndCourtCostsBeginDate() {
		fetch();
		Date aDate = null;
		String aDateString = PDConstants.BLANK;
		if (fineAndCourtCostsBeginDate != null
				&& !fineAndCourtCostsBeginDate.equals(PDConstants.SIX_ZEROES)
				&& !fineAndCourtCostsBeginDate.equals(PDConstants.NONE)) {
			aDate = DateUtil.stringToDate(fineAndCourtCostsBeginDate,
					PDConstants.DATE_FORMAT_MMDDYY);
			if (aDate != null) {
				aDateString = DateUtil.dateToString(aDate,
						UIConstants.DATE_FMT_1);
			}
		}
		return aDateString;
	}
    /**
     * @return Returns the fineAndCourtCostsTimePeriod.
     */
    public String getFormattedFineAndCourtCostsTimePeriod() {
		return SupervisionOrderReferenceVariableHelper.getFormattedTimePeriod(
		        fineAndCourtCostsTimePeriod, "0");

    }

	/**
	 * @return
	 */
	public String getFormattedFineAndCourtCostsTotal() {
		fetch();
		String formattedNum = PDConstants.BLANK;
		if (!fineAndCourtCostsTotal.equals(PDConstants.NONE)
				&& !fineAndCourtCostsTotal.equals(PDConstants.EIGHT_ZEROES)) {
			formattedNum = NumberFormatter.getNumberFromString(
					fineAndCourtCostsTotal, 2, false);
		}
		return formattedNum;
	}

	/**
	 * @return
	 */
	public String getFormattedFineAndCourtCostsTotalDCR() {
		return getFormattedFineAndCourtCostsTotal();
	}

	/**
	 * @return
	 */
	public String getFormattedHcJailBeginDate() {
		fetch();
		Date aDate = null;
		String aDateString = PDConstants.BLANK;
		if (hcJailBeginDate != null
				&& !hcJailBeginDate.equals(PDConstants.SIX_ZEROES)
				&& !hcJailBeginDate.equals(PDConstants.NONE)) {
			aDate = DateUtil.stringToDate(hcJailBeginDate,
					PDConstants.DATE_FORMAT_MMDDYY);
			if (aDate != null) {
				aDateString = DateUtil.dateToString(aDate,
						UIConstants.DATE_FMT_1);
			}
		}
		return aDateString;
	}

	/**
	 * @return
	 */
	public String getFormattedHcJailNum() {
		fetch();
		String formattedNum = PDConstants.BLANK;
		if (!hcJailNum.equals(PDConstants.NONE)
				&& !hcJailNum.equals(PDConstants.FOUR_ZEROES)) {
			formattedNum = hcJailNum;
		}
		return formattedNum;
	}

	/**
	 * @return
	 */
	public String getFormattedHcJailTimePeriod() {
		fetch();
		return SupervisionOrderReferenceVariableHelper.getFormattedTimePeriod(
				hcJailTimePeriod, hcJailNum);
	}

	/**
	 * @return
	 */
	public String getFormattedJailCreditNum() {
		fetch();
		String formattedNum = PDConstants.BLANK;
		if (!jailCreditNum.equals(PDConstants.NONE)
				&& !jailCreditNum.equals(PDConstants.FOUR_ZEROES)) {
			formattedNum = jailCreditNum;
		}
		return formattedNum;
	}

	/**
	 * @return
	 */
	public String getFormattedJailCreditNum2() {

		return getFormattedJailCreditNum();
	}

	/**
	 * @return
	 */
	public String getFormattedJailCreditTimePeriod() {
		fetch();
		if (jailCreditTimePeriod != null && jailCreditTimePeriod.trim().equals("")){
		    jailCreditTimePeriod = PDConstants.MONTH;
		}
		return SupervisionOrderReferenceVariableHelper.getFormattedTimePeriod(
				jailCreditTimePeriod, jailCreditNum);
	}
	/**
	 * @return
	 */
	public String getFormattedJailCreditTimePeriod2() {
		fetch();
		return getFormattedJailCreditTimePeriod();
	}

	/**
	 * @return
	 */
	public String getHcJailBeginDate() {
		fetch();
		return hcJailBeginDate;
	}

	/**
	 * @return
	 */
	public String getHcJailNum() {
		fetch();
		return hcJailNum;
	}

	/**
	 * @return
	 */
	public String getHcJailTimePeriod() {
		fetch();
		return hcJailTimePeriod;
	}

	/**
	 * @return
	 */
	public String getJailCreditNum() {
		fetch();
		return jailCreditNum;
	}

	/**
	 * @return
	 */
	public String getJailCreditTimePeriod() {
		fetch();
		return jailCreditTimePeriod;
	}

	/**
	 * @return
	 */
	public String getPaymentLocation() {
		fetch();
		return paymentLocation;
	}

	/**
	 * Initialize class relationship to class pd.criminalcase.CriminalCase
	 */
	private void initCriminalCase() {
		if (criminalCase == null) {
			criminalCase = (CriminalCase) new mojo.km.persistence.Reference(
					criminalCaseId, CriminalCase.class)
					.getObject();
		}
	}

	/**
	 * @param d
	 */
	public void setCourtCostsInitialAmount(String d) {
		if (this.courtCostsInitialAmount == null
				|| this.courtCostsInitialAmount != d) {
			markModified();
		}
		courtCostsInitialAmount = d;
	}

	/**
	 * @param date
	 */
	public void setCourtCostsInitialPaymentDate(String date) {
		if (this.courtCostsInitialPaymentDate == null
				|| !this.courtCostsInitialPaymentDate.equals(date)) {
			markModified();
		}
		courtCostsInitialPaymentDate = date;
	}

	/**
	 * @param d
	 */
	public void setCourtCostsTotal(String d) {
		if (this.courtCostsTotal == null || this.courtCostsTotal != d) {
			markModified();
		}
		courtCostsTotal = d;
	}

	/**
	 * set the type reference for class member criminalCase
	 */
	public void setCriminalCase(CriminalCase criminalCase) {
		if (this.criminalCase == null
				|| !this.criminalCase.equals(criminalCase)) {
			markModified();
		}
		if (criminalCase.getOID() == null) {
			new mojo.km.persistence.Home().bind(criminalCase);
		}
		setCriminalCaseId("" + criminalCase.getOID());
		this.criminalCase = (CriminalCase) new mojo.km.persistence.Reference(
				criminalCase).getObject();
	}

	/**
	 * Set the reference value to class :: pd.criminalcase.CriminalCase
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		if (this.criminalCaseId == null
				|| !this.criminalCaseId.equals(criminalCaseId)) {
			markModified();
		}
		criminalCase = null;
		this.criminalCaseId = criminalCaseId;
	}

	/**
	 * @param d
	 */
	public void setFineAmountTotal(String d) {
		if (this.fineAmountTotal == null || this.fineAmountTotal != d) {
			markModified();
		}
		fineAmountTotal = d;
	}

	/**
	 * @param d
	 */
	public void setFineAndCostsRate(String d) {
		if (this.fineAndCostsRate == null || this.fineAndCostsRate != d) {
			markModified();
		}
		fineAndCostsRate = d;
	}

	/**
	 * @param date
	 */
	public void setFineAndCourtCostsBeginDate(String date) {
		if (this.fineAndCourtCostsBeginDate == null
				|| !this.fineAndCourtCostsBeginDate.equals(date)) {
			markModified();
		}
		fineAndCourtCostsBeginDate = date;
	}

	/**
	 * @param string
	 */
	public void setFineAndCourtCostsTimePeriod(String string) {
		if (this.fineAndCourtCostsTimePeriod == null
				|| !this.fineAndCourtCostsTimePeriod.equals(string)) {
			markModified();
		}
		fineAndCourtCostsTimePeriod = string;
	}

	/**
	 * @param d
	 */
	public void setFineAndCourtCostsTotal(String d) {
		if (this.fineAndCourtCostsTotal == null
				|| this.fineAndCourtCostsTotal != d) {
			markModified();
		}
		fineAndCourtCostsTotal = d;
	}

	/**
	 * @param date
	 */
	public void setHcJailBeginDate(String date) {
		if (this.hcJailBeginDate == null || !this.hcJailBeginDate.equals(date)) {
			markModified();
		}
		hcJailBeginDate = date;
	}

	/**
	 * @param string
	 */
	public void setHcJailNum(String string) {
		if (this.hcJailNum == null || !this.hcJailNum.equals(string)) {
			markModified();
		}
		hcJailNum = string;
	}

	/**
	 * @param string
	 */
	public void setHcJailTimePeriod(String string) {
		if (this.hcJailTimePeriod == null
				|| !this.hcJailTimePeriod.equals(string)) {
			markModified();
		}
		hcJailTimePeriod = string;
	}

	/**
	 * @param string
	 */
	public void setJailCreditNum(String string) {
		if (this.jailCreditNum == null || !this.jailCreditNum.equals(string)) {
			markModified();
		}
		jailCreditNum = string;
	}

	/**
	 * @param string
	 */
	public void setJailCreditTimePeriod(String string) {
		if (this.jailCreditTimePeriod == null
				|| !this.jailCreditTimePeriod.equals(string)) {
			markModified();
		}
		jailCreditTimePeriod = string;
	}

	/**
	 * @param string
	 */
	public void setPaymentLocation(String string) {
		if (this.paymentLocation == null
				|| !this.paymentLocation.equals(string)) {
			markModified();
		}
		paymentLocation = string;
	}
}
