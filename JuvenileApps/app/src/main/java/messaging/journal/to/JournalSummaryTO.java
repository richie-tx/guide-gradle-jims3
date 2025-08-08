package messaging.journal.to;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JournalSummaryTO extends JournalTO{
	
	private String fromDateRange = "";
	private String toDateRange = "";
	private String userName = "";
	private Date currentDate;
	private String draft = "";;
	
	private String summaryAssignedReferrals = "";
	private String summaryScheduledCourtDates = "";
	private List summaryActivityEntries= new ArrayList(); 
	private long summaryActivityEntriesCount = 0;
	private List summaryCalendarEntries= new ArrayList(); 
	private long summaryCalendarEntriesCount = 0;
	private List summaryMaysiAssessmentEntries = new ArrayList(); 		
	private String summaryMaysiSubsequentNeeded = "";
	private String summaryMaysiSubsequentDone = "";
	private String summaryMaysiSubsequentNotNeeded = "";
	private String summaryMaysiNone  = "";
	
	private List summaryAcceptedProgramReferrals= new ArrayList();
	private List summaryClosedProgramReferrals= new ArrayList(); 
	private List summaryTentativeReferredProgramReferrals = new ArrayList(); 
	private List summaryAllReferredProgramReferrals = new ArrayList(); 
	private long summaryAcceptedProgramReferralsCount = 0;
	private long summaryClosedProgramReferralsCount = 0;
	private long summaryTentativeReferredProgramReferralsCount = 0;
	private long summaryProgramReferralsTotalCount = 0;
	
	private List summaryRiskAnalysis = new ArrayList();
	private long summaryRiskAnalysisCount = 0;
	
	private String summaryOverviewSupervisionLevel = "";
	private List overviewActivitySummaryEntries;
	private long overviewActivitySummaryEntriesCount = 0;
	private List overviewCalendarEventSummaryEntries;
	private long overviewCalendarEventSummaryEntriesCount = 0;
	private List overviewProgressRiskSummaryEntries;
	private long overviewProgressRiskSummaryEntriesCount = 0;
	
	//added for Bug #37399
	private int numProgressRisks;
	private int numResProgRisks;
	
	//added for task 43840; US 35963 
	private List summaryFacilityActivityEntries= new ArrayList(); 
	private long summaryFacilityActivityEntriesCount = 0;
	private List overviewFacilityActivitySummaryEntries;
	private long overviewFacilityActivitySummaryEntriesCount = 0;
	
	/**
	 * @return the fromDateRange
	 */
	public String getFromDateRange() {
		return fromDateRange;
	}
	/**
	 * @param fromDateRange the fromDateRange to set
	 */
	public void setFromDateRange(String fromDateRange) {
		this.fromDateRange = fromDateRange;
	}
	/**
	 * @return the toDateRange
	 */
	public String getToDateRange() {
		return toDateRange;
	}
	/**
	 * @param toDateRange the toDateRange to set
	 */
	public void setToDateRange(String toDateRange) {
		this.toDateRange = toDateRange;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the currentDate
	 */
	public Date getCurrentDate() {
		return currentDate;
	}
	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	
	/**
	 * @return the draft
	 */
	public String getDraft() {
		return draft;
	}
	/**
	 * @param draft the draft to set
	 */
	public void setDraft(String draft) {
		this.draft = draft;
	}
	/**
	 * @return the summaryActivityEntries
	 */
	public List getSummaryActivityEntries() {
		return summaryActivityEntries;
	}
	/**
	 * @param summaryActivityEntries the summaryActivityEntries to set
	 */
	public void setSummaryActivityEntries(List summaryActivityEntries) {
		this.summaryActivityEntries = summaryActivityEntries;
	}
	/**
	 * @return the summaryActivityEntriesCount
	 */
	public long getSummaryActivityEntriesCount() {
		return summaryActivityEntriesCount;
	}
	/**
	 * @param summaryActivityEntriesCount the summaryActivityEntriesCount to set
	 */
	public void setSummaryActivityEntriesCount(long summaryActivityEntriesCount) {
		this.summaryActivityEntriesCount = summaryActivityEntriesCount;
	}
	/**
	 * @return the summaryMaysiAssessmentEntries
	 */
	public List getSummaryMaysiAssessmentEntries() {
		return summaryMaysiAssessmentEntries;
	}
	/**
	 * @param summaryMaysiAssessmentEntries the summaryMaysiAssessmentEntries to set
	 */
	public void setSummaryMaysiAssessmentEntries(List summaryMaysiAssessmentEntries) {
		this.summaryMaysiAssessmentEntries = summaryMaysiAssessmentEntries;
	}
	/**
	 * @return the summaryAssignedReferrals
	 */
	public String getSummaryAssignedReferrals() {
		return summaryAssignedReferrals;
	}
	/**
	 * @param summaryAssignedReferrals the summaryAssignedReferrals to set
	 */
	public void setSummaryAssignedReferrals(String summaryAssignedReferrals) {
		this.summaryAssignedReferrals = summaryAssignedReferrals;
	}
	/**
	 * @return the summaryScheduledCourtDates
	 */
	public String getSummaryScheduledCourtDates() {
		return summaryScheduledCourtDates;
	}
	/**
	 * @param summaryScheduledCourtDates the summaryScheduledCourtDates to set
	 */
	public void setSummaryScheduledCourtDates(String summaryScheduledCourtDates) {
		this.summaryScheduledCourtDates = summaryScheduledCourtDates;
	}
	/**
	 * @return the summaryCalendarEntries
	 */
	public List getSummaryCalendarEntries() {
		return summaryCalendarEntries;
	}
	/**
	 * @param summaryCalendarEntries the summaryCalendarEntries to set
	 */
	public void setSummaryCalendarEntries(List summaryCalendarEntries) {
		this.summaryCalendarEntries = summaryCalendarEntries;
	}
	/**
	 * @return the summaryCalendarEntriesCount
	 */
	public long getSummaryCalendarEntriesCount() {
		return summaryCalendarEntriesCount;
	}
	/**
	 * @param summaryCalendarEntriesCount the summaryCalendarEntriesCount to set
	 */
	public void setSummaryCalendarEntriesCount(long summaryCalendarEntriesCount) {
		this.summaryCalendarEntriesCount = summaryCalendarEntriesCount;
	}
	/**
	 * @return the summaryMaysiSubsequentNeeded
	 */
	public String getSummaryMaysiSubsequentNeeded() {
		return summaryMaysiSubsequentNeeded;
	}
	/**
	 * @param summaryMaysiSubsequentNeeded the summaryMaysiSubsequentNeeded to set
	 */
	public void setSummaryMaysiSubsequentNeeded(String summaryMaysiSubsequentNeeded) {
		this.summaryMaysiSubsequentNeeded = summaryMaysiSubsequentNeeded;
	}
	/**
	 * @return the summaryMaysiSubsequentDone
	 */
	public String getSummaryMaysiSubsequentDone() {
		return summaryMaysiSubsequentDone;
	}
	/**
	 * @param summaryMaysiSubsequentDone the summaryMaysiSubsequentDone to set
	 */
	public void setSummaryMaysiSubsequentDone(String summaryMaysiSubsequentDone) {
		this.summaryMaysiSubsequentDone = summaryMaysiSubsequentDone;
	}
	
	/**
	 * @return the summaryMaysiSubsequentNotNeeded
	 */
	public String getSummaryMaysiSubsequentNotNeeded() {
		return summaryMaysiSubsequentNotNeeded;
	}
	/**
	 * @param summaryMaysiSubsequentNotNeeded the summaryMaysiSubsequentNotNeeded to set
	 */
	public void setSummaryMaysiSubsequentNotNeeded(
			String summaryMaysiSubsequentNotNeeded) {
		this.summaryMaysiSubsequentNotNeeded = summaryMaysiSubsequentNotNeeded;
	}
	/**
	 * @return the summaryMaysiNone
	 */
	public String getSummaryMaysiNone() {
		return summaryMaysiNone;
	}
	/**
	 * @param summaryMaysiNone the summaryMaysiNone to set
	 */
	public void setSummaryMaysiNone(String summaryMaysiNone) {
		this.summaryMaysiNone = summaryMaysiNone;
	}
	/**
	 * @return the summaryAcceptedProgramReferrals
	 */
	public List getSummaryAcceptedProgramReferrals() {
		return summaryAcceptedProgramReferrals;
	}
	/**
	 * @param summaryAcceptedProgramReferrals the summaryAcceptedProgramReferrals to set
	 */
	public void setSummaryAcceptedProgramReferrals(
			List summaryAcceptedProgramReferrals) {
		this.summaryAcceptedProgramReferrals = summaryAcceptedProgramReferrals;
	}
	/**
	 * @return the summaryClosedProgramReferrals
	 */
	public List getSummaryClosedProgramReferrals() {
		return summaryClosedProgramReferrals;
	}
	/**
	 * @param summaryClosedProgramReferrals the summaryClosedProgramReferrals to set
	 */
	public void setSummaryClosedProgramReferrals(List summaryClosedProgramReferrals) {
		this.summaryClosedProgramReferrals = summaryClosedProgramReferrals;
	}
	/**
	 * @return the summaryAcceptedProgramReferralsCount
	 */
	public long getSummaryAcceptedProgramReferralsCount() {
		return summaryAcceptedProgramReferralsCount;
	}
	/**
	 * @param summaryAcceptedProgramReferralsCount the summaryAcceptedProgramReferralsCount to set
	 */
	public void setSummaryAcceptedProgramReferralsCount(
			long summaryAcceptedProgramReferralsCount) {
		this.summaryAcceptedProgramReferralsCount = summaryAcceptedProgramReferralsCount;
	}
	/**
	 * @return the summaryClosedProgramReferralsCount
	 */
	public long getSummaryClosedProgramReferralsCount() {
		return summaryClosedProgramReferralsCount;
	}
	/**
	 * @param summaryClosedProgramReferralsCount the summaryClosedProgramReferralsCount to set
	 */
	public void setSummaryClosedProgramReferralsCount(
			long summaryClosedProgramReferralsCount) {
		this.summaryClosedProgramReferralsCount = summaryClosedProgramReferralsCount;
	}
	/**
	 * @return the summaryTentativeReferredProgramReferralsCount
	 */
	public long getSummaryTentativeReferredProgramReferralsCount() {
		return summaryTentativeReferredProgramReferralsCount;
	}
	/**
	 * @param summaryTentativeReferredProgramReferralsCount the summaryTentativeReferredProgramReferralsCount to set
	 */
	public void setSummaryTentativeReferredProgramReferralsCount(
			long summaryTentativeReferredProgramReferralsCount) {
		this.summaryTentativeReferredProgramReferralsCount = summaryTentativeReferredProgramReferralsCount;
	}
	/**
	 * @return the summaryProgramReferralsTotalCount
	 */
	public long getSummaryProgramReferralsTotalCount() {
		return summaryProgramReferralsTotalCount;
	}
	/**
	 * @param summaryProgramReferralsTotalCount the summaryProgramReferralsTotalCount to set
	 */
	public void setSummaryProgramReferralsTotalCount(
			long summaryProgramReferralsTotalCount) {
		this.summaryProgramReferralsTotalCount = summaryProgramReferralsTotalCount;
	}
	/**
	 * @return the summaryRiskAnalysis
	 */
	public List getSummaryRiskAnalysis() {
		return summaryRiskAnalysis;
	}
	/**
	 * @param summaryRiskAnalysis the summaryRiskAnalysis to set
	 */
	public void setSummaryRiskAnalysis(List summaryRiskAnalysis) {
		this.summaryRiskAnalysis = summaryRiskAnalysis;
	}
	/**
	 * @return the summaryRiskAnalysisCount
	 */
	public long getSummaryRiskAnalysisCount() {
		return summaryRiskAnalysisCount;
	}
	/**
	 * @param summaryRiskAnalysisCount the summaryRiskAnalysisCount to set
	 */
	public void setSummaryRiskAnalysisCount(long summaryRiskAnalysisCount) {
		this.summaryRiskAnalysisCount = summaryRiskAnalysisCount;
	}
	/**
	 * @return the summaryTentativeReferredProgramReferrals
	 */
	public List getSummaryTentativeReferredProgramReferrals() {
		return summaryTentativeReferredProgramReferrals;
	}
	/**
	 * @param summaryTentativeReferredProgramReferrals the summaryTentativeReferredProgramReferrals to set
	 */
	public void setSummaryTentativeReferredProgramReferrals(
			List summaryTentativeReferredProgramReferrals) {
		this.summaryTentativeReferredProgramReferrals = summaryTentativeReferredProgramReferrals;
	}
	/**
	 * @return the summaryAllReferredProgramReferrals
	 */
	public List getSummaryAllReferredProgramReferrals() {
		return summaryAllReferredProgramReferrals;
	}
	/**
	 * @param summaryAllReferredProgramReferrals the summaryAllReferredProgramReferrals to set
	 */
	public void setSummaryAllReferredProgramReferrals(
			List summaryAllReferredProgramReferrals) {
		this.summaryAllReferredProgramReferrals = summaryAllReferredProgramReferrals;
	}
	/**
	 * @return the summaryOverviewSupervisionLevel
	 */
	public String getSummaryOverviewSupervisionLevel() {
		return summaryOverviewSupervisionLevel;
	}
	/**
	 * @param summaryOverviewSupervisionLevel the summaryOverviewSupervisionLevel to set
	 */
	public void setSummaryOverviewSupervisionLevel(
			String summaryOverviewSupervisionLevel) {
		this.summaryOverviewSupervisionLevel = summaryOverviewSupervisionLevel;
	}
	/**
	 * @return the overviewActivitySummaryEntries
	 */
	public List getOverviewActivitySummaryEntries() {
		return overviewActivitySummaryEntries;
	}
	/**
	 * @param overviewActivitySummaryEntries the overviewActivitySummaryEntries to set
	 */
	public void setOverviewActivitySummaryEntries(
			List overviewActivitySummaryEntries) {
		this.overviewActivitySummaryEntries = overviewActivitySummaryEntries;
	}
	/**
	 * @return the overviewCalendarEventSummaryEntries
	 */
	public List getOverviewCalendarEventSummaryEntries() {
		return overviewCalendarEventSummaryEntries;
	}
	/**
	 * @param overviewCalendarEventSummaryEntries the overviewCalendarEventSummaryEntries to set
	 */
	public void setOverviewCalendarEventSummaryEntries(
			List overviewCalendarEventSummaryEntries) {
		this.overviewCalendarEventSummaryEntries = overviewCalendarEventSummaryEntries;
	}
	/**
	 * @return the overviewProgressRiskSummaryEntries
	 */
	public List getOverviewProgressRiskSummaryEntries() {
		return overviewProgressRiskSummaryEntries;
	}
	/**
	 * @param overviewProgressRiskSummaryEntries the overviewProgressRiskSummaryEntries to set
	 */
	public void setOverviewProgressRiskSummaryEntries(
			List overviewProgressRiskSummaryEntries) {
		this.overviewProgressRiskSummaryEntries = overviewProgressRiskSummaryEntries;
	}
	/**
	 * @return the overviewActivitySummaryEntriesCount
	 */
	public long getOverviewActivitySummaryEntriesCount() {
		return overviewActivitySummaryEntriesCount;
	}
	/**
	 * @param overviewActivitySummaryEntriesCount the overviewActivitySummaryEntriesCount to set
	 */
	public void setOverviewActivitySummaryEntriesCount(
			long overviewActivitySummaryEntriesCount) {
		this.overviewActivitySummaryEntriesCount = overviewActivitySummaryEntriesCount;
	}
	/**
	 * @return the overviewCalendarEventSummaryEntriesCount
	 */
	public long getOverviewCalendarEventSummaryEntriesCount() {
		return overviewCalendarEventSummaryEntriesCount;
	}
	/**
	 * @param overviewCalendarEventSummaryEntriesCount the overviewCalendarEventSummaryEntriesCount to set
	 */
	public void setOverviewCalendarEventSummaryEntriesCount(
			long overviewCalendarEventSummaryEntriesCount) {
		this.overviewCalendarEventSummaryEntriesCount = overviewCalendarEventSummaryEntriesCount;
	}
	/**
	 * @return the overviewProgressRiskSummaryEntriesCount
	 */
	public long getOverviewProgressRiskSummaryEntriesCount() {
		return overviewProgressRiskSummaryEntriesCount;
	}
	/**
	 * @param overviewProgressRiskSummaryEntriesCount the overviewProgressRiskSummaryEntriesCount to set
	 */
	public void setOverviewProgressRiskSummaryEntriesCount(
			long overviewProgressRiskSummaryEntriesCount) {
		this.overviewProgressRiskSummaryEntriesCount = overviewProgressRiskSummaryEntriesCount;
	}
	/**
	 * @return the numProgressRisks
	 */
	/**
	 * @return the numProgressRisks
	 */
	public int getNumProgressRisks() {
		return numProgressRisks;
	}
	/**
	 * @param numProgressRisks the numProgressRisks to set
	 */
	public void setNumProgressRisks(int numProgressRisks) {
		this.numProgressRisks = numProgressRisks;
	}
	/**
	 * @return the numResProgRisks
	 */
	public int getNumResProgRisks() {
		return numResProgRisks;
	}
	/**
	 * @param numResProgRisks the numResProgRisks to set
	 */
	public void setNumResProgRisks(int numResProgRisks) {
		this.numResProgRisks = numResProgRisks;
	}
	public List getSummaryFacilityActivityEntries() {
	    return summaryFacilityActivityEntries;
	}
	public void setSummaryFacilityActivityEntries(
		List summaryFacilityActivityEntries) {
	    this.summaryFacilityActivityEntries = summaryFacilityActivityEntries;
	}
	public long getSummaryFacilityActivityEntriesCount() {
	    return summaryFacilityActivityEntriesCount;
	}
	public void setSummaryFacilityActivityEntriesCount(
		long summaryFacilityActivityEntriesCount) {
	    this.summaryFacilityActivityEntriesCount = summaryFacilityActivityEntriesCount;
	}
	public List getOverviewFacilityActivitySummaryEntries() {
	    return overviewFacilityActivitySummaryEntries;
	}
	public void setOverviewFacilityActivitySummaryEntries(
		List overviewFacilityActivitySummaryEntries) {
	    this.overviewFacilityActivitySummaryEntries = overviewFacilityActivitySummaryEntries;
	}
	public long getOverviewFacilityActivitySummaryEntriesCount() {
	    return overviewFacilityActivitySummaryEntriesCount;
	}
	public void setOverviewFacilityActivitySummaryEntriesCount(
		long overviewFacilityActivitySummaryEntriesCount) {
	    this.overviewFacilityActivitySummaryEntriesCount = overviewFacilityActivitySummaryEntriesCount;
	}

	
}
