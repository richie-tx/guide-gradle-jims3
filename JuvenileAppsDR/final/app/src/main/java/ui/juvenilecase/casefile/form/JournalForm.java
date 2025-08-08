/*
 * Created on May 1, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import org.apache.struts.action.ActionForm;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JournalForm extends ActionForm
{
	
//	Default field
	//private static	Collection emptyColl = new ArrayList();
	
	private String fromDate = "" ;
	private String endDate = "" ;
	private String searchType = "";
	private String journalCategoryCd = "";
	private String lastName = "";
	private String firstName = "";
	private String middleName = "";	
	private String selectedOfficerId = "";
	private String juvenileId= "";
	private String casefileId = "";
	private String officerId="";
	private String listCount="";
	private String [] selectedCasefiles;
	private String selectedValues;
	private Collection activityEntries;
	private Collection riskEntries;
	private Collection closingEntries;
	private Collection goalEntries;
	private Collection progReferralEntries;
	private Collection calEventEntries;
	private Collection traitEntries;
	private Collection nameSearchResults;
	
	// collections for the journal summary report
	private Collection activitySummaryEntries;
	private Collection riskSummaryEntries;
	private Collection closingSummaryEntries;
	private Collection goalSummaryEntries;
	private Collection progReferralSummaryEntries;
	private Collection calEventSummaryEntries;
	private Collection traitSummaryEntries;
	private Collection juvJournalSummaryEntries;
	private Collection juvEventSummaryEntries;
	private Collection spEventSummaryEntries;
	private Collection maysiAssessmentSummaryEntries;
	private Collection historicalAssignmentSummaryEntries;	
	private Collection noMaysiSectionMaysiAndAssignmentSummaryEntries;
	private Collection noMaysiSectionAssignmentWithNoMaysiSummaryEntries;
	private Collection casefileReferralsSummaryEntries;
	private Collection juvJournalEntries;
	//separate calendar event entries into juvenile and provider events journal summary report
	private Collection juvEventEntries;
	private Collection spEventEntries;
	
	// overview information journal summary report
	private String overviewSummarySupervisionLevel;
	private Collection overviewActivitySummaryEntries;
	private Collection overviewCalendarEventSummaryEntries;
	private Collection overviewProgressRiskSummaryEntries;
	private byte[] caseReviewReportContent = null;
	
	//U.S. #27342
	//separate  facility activity entries 
	private Collection facEventEntries;
	
	//Task 43840, US 35963
	private Collection facilityActivitySummaryEntries;
	private Collection overviewFacilityActivitySummaryEntries;
	
	//Drop down of casefiles
	private Collection juvenileProfileCasefileList;
	
	public JournalForm()
	{
		clearAll();
	}

	public void clearAll()
	{
	    	fromDate=null;
		endDate=null;
		searchType="";
		lastName = "";
		firstName = "";
		middleName = "";
		selectedOfficerId = "";
		goalEntries = null;
		activityEntries = null;
		riskEntries = null;
		closingEntries = null;
		progReferralEntries = null;
		calEventEntries = null;
		traitEntries = null;
		nameSearchResults = null;
		juvJournalEntries = null;
		juvEventEntries = null;
		facEventEntries = null;
		spEventEntries = null;
		activitySummaryEntries = null;
		facilityActivitySummaryEntries = null;
		riskSummaryEntries = null;
		closingSummaryEntries = null;
		goalSummaryEntries = null;
		progReferralSummaryEntries = null;
		calEventSummaryEntries = null;
		traitSummaryEntries = null;
		overviewProgressRiskSummaryEntries = null;
		juvJournalSummaryEntries = null;
		juvEventSummaryEntries = null;
		spEventSummaryEntries = null;
		maysiAssessmentSummaryEntries = null;
		casefileReferralsSummaryEntries = null;
		overviewSummarySupervisionLevel = null;
		overviewActivitySummaryEntries = null;
		overviewFacilityActivitySummaryEntries = null;
		overviewCalendarEventSummaryEntries = null;
		journalCategoryCd = "";
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getJournalCategoryCd() {
		return journalCategoryCd;
	}

	public void setJournalCategoryCd(String journalCategoryCd) {
		this.journalCategoryCd = journalCategoryCd;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSelectedOfficerId() {
		return selectedOfficerId;
	}

	public void setSelectedOfficerId(String selectedOfficerId) {
		this.selectedOfficerId = selectedOfficerId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getCasefileId() {
		return casefileId;
	}

	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	public String getJuvenileId() {
		return juvenileId;
	}

	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public Collection getActivityEntries() {
		return activityEntries;
	}

	public void setActivityEntries(Collection activityEntries) {
		this.activityEntries = activityEntries;
	}

	public Collection getNameSearchResults() {
		return nameSearchResults;
	}

	public void setNameSearchResults(Collection nameSearchResults) {
		this.nameSearchResults = nameSearchResults;
	}

	public String getListCount() {
		return listCount;
	}

	public void setListCount(String listCount) {
		this.listCount = listCount;
	}

	public Collection getRiskEntries() {
		return riskEntries;
	}

	public void setRiskEntries(Collection riskEntries) {
		this.riskEntries = riskEntries;
	}

	public static class RiskAnalysisJournal{
		private String title;
		private String comments;
		private Date entryDate;
		private String createdBy;
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Date getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(Date entryDate) {
			this.entryDate = entryDate;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}	
	}
	
	/**
	 * used in the case review report for the overview activity section
	 * @author rcarter
	 *
	 */
	public static class OverviewActivityBean{
		private String activityDescription;
		private String quantity;
		
		
		/**
		 * @return the activityDescription
		 */
		public String getActivityDescription() {
			return activityDescription;
		}

		/**
		 * @param activityDescription the activityDescription to set
		 */
		public void setActivityDescription(String activityDescription) {
			this.activityDescription = activityDescription;
		}

		/**
		 * @return the quantity
		 */
		public String getQuantity() {
			return quantity;
		}

		/**
		 * @param quantity the quantity to set
		 */
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		/**
		 * comparator
		 */
		public static Comparator CaseReviewOverviewActivityComparator = new Comparator() {
			public int compare(Object activityBean, Object otherActivityBean) {

				int result = 0;
			  String activityBeanDescription = ((OverviewActivityBean)activityBean).getActivityDescription();
			  String otherActivityBeanDescription = ((OverviewActivityBean)otherActivityBean).getActivityDescription();
			  
			  if(activityBeanDescription == null || activityBeanDescription.equals("")){
				  result = -1;
			  } else if(otherActivityBeanDescription == null || otherActivityBeanDescription.equals("")){
				  result = 1;
			  }else{
				  result = activityBeanDescription.compareTo(otherActivityBeanDescription);
			  }
			  
			  return result;
			}	
		};	
	}

	//added for US 35963; Task 43840
	public static class OverviewFacilityActivityBean{
		private String activityDescription;
		private String quantity;
		
		
		/**
		 * @return the activityDescription
		 */
		public String getActivityDescription() {
			return activityDescription;
		}

		/**
		 * @param activityDescription the activityDescription to set
		 */
		public void setFacilityActivityDescription(String facilityActivityDescription) {
			this.activityDescription = facilityActivityDescription;
		}

		/**
		 * @return the quantity
		 */
		public String getQuantity() {
			return quantity;
		}

		/**
		 * @param quantity the quantity to set
		 */
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		/**
		 * comparator
		 */
		public static Comparator CaseReviewOverviewFacilityActivityComparator = new Comparator() {
			public int compare(Object facilityActivityBean, Object otherFacilityActivityBean) {

				int result = 0;
			  String facilityActivityBeanDescription = ((OverviewFacilityActivityBean)facilityActivityBean).getActivityDescription();
			  String otherFacilityActivityBeanDescription = ((OverviewFacilityActivityBean)otherFacilityActivityBean).getActivityDescription();
			  
			  if(facilityActivityBeanDescription == null || facilityActivityBeanDescription.equals("")){
				  result = -1;
			  } else if(otherFacilityActivityBeanDescription == null || otherFacilityActivityBeanDescription.equals("")){
				  result = 1;
			  }else{
				  result = facilityActivityBeanDescription.compareTo(otherFacilityActivityBeanDescription);
			  }
			  
			  return result;
			}	
		};	
	}

	
	/**
	 * used in the case review report for the overview calendar event section
	 * @author rcarter
	 *
	 */
	public static class OverviewCalendarEventBean{
		private String eventDescription;
		private long scheduled;
		private long attended;
		private long absent;
		private long excused;
		private int percentAttended;
		private ArrayList<CalendarServiceEventResponseEvent> calendarEvents = new ArrayList<CalendarServiceEventResponseEvent>();

	/**
		 * @return the eventDescription
		 */
		public String getEventDescription() {
			return eventDescription;
		}

		/**
		 * @param eventDescription the eventDescription to set
		 */
		public void setEventDescription(String eventDescription) {
			this.eventDescription = eventDescription;
		}

		/**
		 * @return the scheduled
		 */
		public long getScheduled() {
			return scheduled;
		}

		/**
		 * @param scheduled the scheduled to set
		 */
		public void setScheduled(long scheduled) {
			this.scheduled = scheduled;
		}

		/**
		 * @return the attended
		 */
		public long getAttended() {
			return attended;
		}

		/**
		 * @param attended the attended to set
		 */
		public void setAttended(long attended) {
			this.attended = attended;
		}

		/**
		 * @return the absent
		 */
		public long getAbsent() {
			return absent;
		}

		/**
		 * @param absent the absent to set
		 */
		public void setAbsent(long absent) {
			this.absent = absent;
		}

		/**
		 * @return the excused
		 */
		public long getExcused() {
			return excused;
		}

		/**
		 * @param excused the excused to set
		 */
		public void setExcused(long excused) {
			this.excused = excused;
		}

		/**
		 * @return the percentAttended
		 */
		public int getPercentAttended() {
			return percentAttended;
		}

		/**
		 * @param percentAttended the percentAttended to set
		 */
		public void setPercentAttended(int percentAttended) {
			this.percentAttended = percentAttended;
		}

	/**
		 * @return the calendarEvents
		 */
		public ArrayList<CalendarServiceEventResponseEvent> getCalendarEvents() {
			return calendarEvents;
		}

		/**
		 * @param calendarEvents the calendarEvents to set
		 */
		public void setCalendarEvents(
				ArrayList<CalendarServiceEventResponseEvent> calendarEvents) {
			this.calendarEvents = calendarEvents;
		}

		/**
		 * add calendar event to collection for this bean
		 * @param calendarEvent
		 */
		public void addCalendarEvent(CalendarServiceEventResponseEvent calendarEvent){
			this.calendarEvents.add(calendarEvent);
		}


	/**
	 * comparator
	 */
	public static Comparator CaseReviewOverviewCalendarEventComparator = new Comparator() {
		public int compare(Object calendarEventBean, Object otherCalendarEventBean) {

		  int result = 0;
		  String calendarEventBeanDescription = ((OverviewCalendarEventBean)calendarEventBean).getEventDescription();
		  String otherCalendarEventBeanDescription = ((OverviewCalendarEventBean)otherCalendarEventBean).getEventDescription();
		  
		  if(calendarEventBeanDescription == null || calendarEventBeanDescription.equals("")){
			  result = -1;
		  } else if(otherCalendarEventBeanDescription == null || otherCalendarEventBeanDescription.equals("")){
			  result = 1;
		  }else{
			  result = calendarEventBeanDescription.compareTo(otherCalendarEventBeanDescription);
		  }
		  
		  return result;
		}	
	  };	
	}	
	
	/**
	 * used in the case review report for the overview progress risk section
	 * @author rcarter
	 *
	 */
	public static class OverviewProgressRiskBean{
		private String progressRiskQuestionText;
		private boolean improvement;
		private boolean regression;
		private boolean noChange;
		private long recentWeightScore;
		private long oldestWeightScore;
		private Date recentEntryDate;
		private Date oldestEntryDate;
		private ArrayList<RiskAnswerResponseEvent> riskAnswerEvents = new ArrayList<RiskAnswerResponseEvent>();
		private String questionNumber;
		
		//added for Bug #37399
		private String assessmentType;
		/**
		 * @return the questionNumber
		 */
		public String getQuestionNumber() {
			return questionNumber;
		}

		/**
		 * @param questionNumber the questionNumber to set
		 */
		public void setQuestionNumber(String questionNumber) {
			this.questionNumber = questionNumber;
		}

		/**
		 * @return the recentWeightScore
		 */
		public long getRecentWeightScore() {
			return recentWeightScore;
		}

		/**
		 * @param recentWeightScore the recentWeightScore to set
		 */
		public void setRecentWeightScore(long recentWeightScore) {
			this.recentWeightScore = recentWeightScore;
		}

		/**
		 * @return the oldestWeightScore
		 */
		public long getOldestWeightScore() {
			return oldestWeightScore;
		}

		/**
		 * @param oldestWeightScore the oldestWeightScore to set
		 */
		public void setOldestWeightScore(long oldestWeightScore) {
			this.oldestWeightScore = oldestWeightScore;
		}

		/**
		 * @return the recentEntryDate
		 */
		public Date getRecentEntryDate() {
			return recentEntryDate;
		}

		/**
		 * @param recentEntryDate the recentEntryDate to set
		 */
		public void setRecentEntryDate(Date recentEntryDate) {
			this.recentEntryDate = recentEntryDate;
		}

		/**
		 * @return the oldestEntryDate
		 */
		public Date getOldestEntryDate() {
			return oldestEntryDate;
		}

		/**
		 * @param oldestEntryDate the oldestEntryDate to set
		 */
		public void setOldestEntryDate(Date oldestEntryDate) {
			this.oldestEntryDate = oldestEntryDate;
		}

		/**
		 * @return the progressRiskQuestionText
		 */
		public String getProgressRiskQuestionText() {
			return progressRiskQuestionText;
		}

		/**
		 * @param progressRiskQuestionText the progressRiskQuestionText to set
		 */
		public void setProgressRiskQuestionText(String progressRiskQuestionText) {
			this.progressRiskQuestionText = progressRiskQuestionText;
		}

		/**
		 * @return the improvement
		 */
		public boolean isImprovement() {
			return improvement;
		}

		/**
		 * @param improvement the improvement to set
		 */
		public void setImprovement(boolean improvement) {
			this.improvement = improvement;
		}

		/**
		 * @return the regression
		 */
		public boolean isRegression() {
			return regression;
		}

		/**
		 * @param regression the regression to set
		 */
		public void setRegression(boolean regression) {
			this.regression = regression;
		}

		/**
		 * @return the noChange
		 */
		public boolean isNoChange() {
			return noChange;
		}

		/**
		 * @param noChange the noChange to set
		 */
		public void setNoChange(boolean noChange) {
			this.noChange = noChange;
		}

		/**
		 * @return the riskAnswerEvents
		 */
		public ArrayList<RiskAnswerResponseEvent> getRiskAnswerEvents() {
			return riskAnswerEvents;
		}

		/**
		 * @param riskAnswerEvents the riskAnswerEvents to set
		 */
		public void setRiskAnswerEvents(
				ArrayList<RiskAnswerResponseEvent> riskAnswerEvents) {
			this.riskAnswerEvents = riskAnswerEvents;
		}

		/**
		 * add calendar event to collection for this bean
		 * @param calendarEvent
		 */
		public void addRiskQuestionEvent(RiskAnswerResponseEvent riskEvent){
			this.riskAnswerEvents.add(riskEvent);
		}


	public String getAssessmentType() {
			return assessmentType;
		}

		public void setAssessmentType(String assessmentType) {
			this.assessmentType = assessmentType;
		}


	/**
	 * comparator
	 */
	public static Comparator CaseReviewOverviewProgressRiskComparator = new Comparator() {
		public int compare(Object progressEventBean, Object otherProgressEventBean) {

		  int result = 0;
		  String progressRiskQuestionNumber = ((OverviewProgressRiskBean)progressEventBean).getQuestionNumber();
		  String otherProgressQuestionNumber = ((OverviewProgressRiskBean)otherProgressEventBean).getQuestionNumber();
		  String progressRiskBean = ((OverviewProgressRiskBean)progressEventBean).getProgressRiskQuestionText();
		  String otherProgressRiskBean = ((OverviewProgressRiskBean)otherProgressEventBean).getProgressRiskQuestionText();
		  Long questionNumber = 0l;
		  Long otherQuestionNumber  = 0l;
		  
		  if(progressRiskQuestionNumber == null || progressRiskQuestionNumber.equals("")){
			  result = -1;
		  } else if(otherProgressQuestionNumber == null || otherProgressQuestionNumber.equals("")){
			  result = 1;
		  }else{
			  try{
				  questionNumber = new Long(progressRiskQuestionNumber);
				  otherQuestionNumber = new Long(otherProgressQuestionNumber);
			  }catch(NumberFormatException nfe){
				  questionNumber = 0l;
				  otherQuestionNumber  = 0l;
			  }
			  result = questionNumber.compareTo(otherQuestionNumber);
		  }
		  
		  if(result == 0){		  
			  if(progressRiskBean == null || progressRiskBean.equals("")){
				  result = -1;
			  } else if(otherProgressRiskBean == null || otherProgressRiskBean.equals("")){
				  result = 1;
			  }else{
				  result = progressRiskBean.compareTo(otherProgressRiskBean);
			  }
		  }
		  
		  return result;
		}	
	  };	
	}	
	
	/**
	 * used in the case review report for the risk analysis summary section
	 * @author rcarter
	 *
	 */
	public static class CaseReviewRiskAnalysisBean{
		private String entryDate;
		private String riskAnalysis;
		private String supervisionLevel;		
		private String recommendation;
		
		/**
		 * @return the riskAnalysis
		 */
		public String getRiskAnalysis() {
			return riskAnalysis;
		}
		/**
		 * @param riskAnalysis the riskAnalysis to set
		 */
		public void setRiskAnalysis(String riskAnalysis) {
			this.riskAnalysis = riskAnalysis;
		}
		/**
		 * @return the supervisionLevel
		 */
		public String getSupervisionLevel() {
			return supervisionLevel;
		}
		/**
		 * @param supervisionLevel the supervisionLevel to set
		 */
		public void setSupervisionLevel(String supervisionLevel) {
			this.supervisionLevel = supervisionLevel;
		}
		/**
		 * @return the recommendation
		 */
		public String getRecommendation() {
			return recommendation;
		}
		/**
		 * @param recommendation the recommendation to set
		 */
		public void setRecommendation(String recommendation) {
			this.recommendation = recommendation;
		}
		/**
		 * @return the entryDate
		 */
		public String getEntryDate() {
			return entryDate;
		}
		/**
		 * @param entryDate the entryDate to set
		 */
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		
		public static Comparator CaseReviewJournalRiskAnalysisSummaryComparator = new Comparator() {
			public int compare(Object riskAnalysis, Object otherRiskAnalysis) {
				
			  int result = 0;
			  String riskAnalysisDescription = ((CaseReviewRiskAnalysisBean)riskAnalysis).getRiskAnalysis();
			  String otherRiskAnalysisDescription = ((CaseReviewRiskAnalysisBean)otherRiskAnalysis).getRiskAnalysis();
			  String riskAnalysisDateString = ((CaseReviewRiskAnalysisBean)riskAnalysis).getEntryDate();
			  String otherRiskAnalysisDateString = ((CaseReviewRiskAnalysisBean)otherRiskAnalysis).getEntryDate();
			  
			  if(riskAnalysisDateString == null || riskAnalysisDateString.equals(""))
			  {
				  result = -1;
			  }else if(otherRiskAnalysisDateString == null || otherRiskAnalysisDateString.equals(""))
			  {
				  result = 1;
			  }
			  else 
			  {
				  Date riskAnalysisDate = DateUtil.stringToDate(riskAnalysisDateString, DateUtil.DATE_FMT_1);
				  Date otherRiskAnalysisDate = DateUtil.stringToDate(otherRiskAnalysisDateString, DateUtil.DATE_FMT_1);
				  result = riskAnalysisDate.compareTo(otherRiskAnalysisDate);
			  }
			  
			  if(result == 0){
				  if(riskAnalysisDescription == null || riskAnalysisDescription.equals("")){
					  result = -1;
				  } else if(otherRiskAnalysisDescription == null || otherRiskAnalysisDescription.equals("")){
					  result = 1;
				  }else{
					  result = riskAnalysisDescription.compareTo(otherRiskAnalysisDescription);
				  }
			  }
			  
			  return result;
			}	
		};
		

	}

	public Collection getClosingEntries() {
		return closingEntries;
	}

	public void setClosingEntries(Collection closingEntries) {
		this.closingEntries = closingEntries;
	}

	public Collection getGoalEntries() {
		return goalEntries;
	}

	public void setGoalEntries(Collection goalEntries) {
		this.goalEntries = goalEntries;
	}

	public Collection getProgReferralEntries() {
		return progReferralEntries;
	}

	public void setProgReferralEntries(Collection progReferralEntries) {
		this.progReferralEntries = progReferralEntries;
	}

	public Collection getCalEventEntries() {
		return calEventEntries;
	}

	public void setCalEventEntries(Collection calEventEntries) {
		this.calEventEntries = calEventEntries;
	}

	public Collection getTraitEntries() {
		return traitEntries;
	}

	public void setTraitEntries(Collection traitEntries) {
		this.traitEntries = traitEntries;
	}
	public static class JuvenileJournal{
		private String casefileId;
		private Collection activityEntries;
		private Collection calEventEntries;
		private Collection goalEntries;
		private Collection traitEntries;
		private Collection riskAnalEntries;
		private Collection progRefEntries;
		private Collection closingEntries;
		private Collection facEventEntries;
		
		
		public Collection getActivityEntries() {
			return activityEntries;
		}
		public void setActivityEntries(Collection activityEntries) {
			this.activityEntries = activityEntries;
		}
		public Collection getCalEventEntries() {
			return calEventEntries;
		}
		public void setCalEventEntries(Collection calEventEntries) {
			this.calEventEntries = calEventEntries;
		}
		public String getCasefileId() {
			return casefileId;
		}
		public void setCasefileId(String casefileId) {
			this.casefileId = casefileId;
		}
		public Collection getClosingEntries() {
			return closingEntries;
		}
		public void setClosingEntries(Collection closingEntries) {
			this.closingEntries = closingEntries;
		}
		public Collection getGoalEntries() {
			return goalEntries;
		}
		public void setGoalEntries(Collection goalEntries) {
			this.goalEntries = goalEntries;
		}
		public Collection getProgRefEntries() {
			return progRefEntries;
		}
		public void setProgRefEntries(Collection progRefEntries) {
			this.progRefEntries = progRefEntries;
		}
		public Collection getRiskAnalEntries() {
			return riskAnalEntries;
		}
		public void setRiskAnalEntries(Collection riskAnalEntries) {
			this.riskAnalEntries = riskAnalEntries;
		}
		public Collection getTraitEntries() {
			return traitEntries;
		}
		public void setTraitEntries(Collection traitEntries) {
			this.traitEntries = traitEntries;
		}
		/**
		 * @return the facEventEntries
		 */
		public Collection getFacEventEntries() {
			return facEventEntries;
		}
		/**
		 * @param facEventEntries the facEventEntries to set
		 */
		public void setFacEventEntries(Collection facEventEntries) {
			this.facEventEntries = facEventEntries;
		}
		
	}

	public Collection getJuvJournalEntries() {
		return juvJournalEntries;
	}

	public void setJuvJournalEntries(Collection juvJournalEntries) {
		this.juvJournalEntries = juvJournalEntries;
	}
	public Collection getJuvEventEntries() {
		return juvEventEntries;
	}

	public void setJuvEventEntries(Collection juvEventEntries) {
		this.juvEventEntries = juvEventEntries;
	}
	public Collection getSpEventEntries() {
		return spEventEntries;
	}

	public void setSpEventEntries(Collection spEventEntries) {
		this.spEventEntries = spEventEntries;
	}

	/**
	 * @return the activitySummaryEntries
	 */
	public Collection getActivitySummaryEntries() {
		return activitySummaryEntries;
	}

	/**
	 * @param activitySummaryEntries the activitySummaryEntries to set
	 */
	public void setActivitySummaryEntries(Collection activitySummaryEntries) {
		this.activitySummaryEntries = activitySummaryEntries;
	}

	/**
	 * @return the riskSummaryEntries
	 */
	public Collection getRiskSummaryEntries() {
		return riskSummaryEntries;
	}

	/**
	 * @param riskSummaryEntries the riskSummaryEntries to set
	 */
	public void setRiskSummaryEntries(Collection riskSummaryEntries) {
		this.riskSummaryEntries = riskSummaryEntries;
	}

	/**
	 * @return the closingSummaryEntries
	 */
	public Collection getClosingSummaryEntries() {
		return closingSummaryEntries;
	}

	/**
	 * @param closingSummaryEntries the closingSummaryEntries to set
	 */
	public void setClosingSummaryEntries(Collection closingSummaryEntries) {
		this.closingSummaryEntries = closingSummaryEntries;
	}

	/**
	 * @return the goalSummaryEntries
	 */
	public Collection getGoalSummaryEntries() {
		return goalSummaryEntries;
	}

	/**
	 * @param goalSummaryEntries the goalSummaryEntries to set
	 */
	public void setGoalSummaryEntries(Collection goalSummaryEntries) {
		this.goalSummaryEntries = goalSummaryEntries;
	}

	/**
	 * @return the progReferralSummaryEntries
	 */
	public Collection getProgReferralSummaryEntries() {
		return progReferralSummaryEntries;
	}

	/**
	 * @param progReferralSummaryEntries the progReferralSummaryEntries to set
	 */
	public void setProgReferralSummaryEntries(Collection progReferralSummaryEntries) {
		this.progReferralSummaryEntries = progReferralSummaryEntries;
	}

	/**
	 * @return the calEventSummaryEntries
	 */
	public Collection getCalEventSummaryEntries() {
		return calEventSummaryEntries;
	}

	/**
	 * @param calEventSummaryEntries the calEventSummaryEntries to set
	 */
	public void setCalEventSummaryEntries(Collection calEventSummaryEntries) {
		this.calEventSummaryEntries = calEventSummaryEntries;
	}

	/**
	 * @return the traitSummaryEntries
	 */
	public Collection getTraitSummaryEntries() {
		return traitSummaryEntries;
	}

	/**
	 * @param traitSummaryEntries the traitSummaryEntries to set
	 */
	public void setTraitSummaryEntries(Collection traitSummaryEntries) {
		this.traitSummaryEntries = traitSummaryEntries;
	}

	/**
	 * @return the juvJournalSummaryEntries
	 */
	public Collection getJuvJournalSummaryEntries() {
		return juvJournalSummaryEntries;
	}

	/**
	 * @param juvJournalSummaryEntries the juvJournalSummaryEntries to set
	 */
	public void setJuvJournalSummaryEntries(Collection juvJournalSummaryEntries) {
		this.juvJournalSummaryEntries = juvJournalSummaryEntries;
	}

	/**
	 * @return the juvEventSummaryEntries
	 */
	public Collection getJuvEventSummaryEntries() {
		return juvEventSummaryEntries;
	}

	/**
	 * @param juvEventSummaryEntries the juvEventSummaryEntries to set
	 */
	public void setJuvEventSummaryEntries(Collection juvEventSummaryEntries) {
		this.juvEventSummaryEntries = juvEventSummaryEntries;
	}

	/**
	 * @return the spEventSummaryEntries
	 */
	public Collection getSpEventSummaryEntries() {
		return spEventSummaryEntries;
	}

	/**
	 * @param spEventSummaryEntries the spEventSummaryEntries to set
	 */
	public void setSpEventSummaryEntries(Collection spEventSummaryEntries) {
		this.spEventSummaryEntries = spEventSummaryEntries;
	}

	/**
	 * @return the casefileReferralsSummaryEntries
	 */
	public Collection getCasefileReferralsSummaryEntries() {
		return casefileReferralsSummaryEntries;
	}

	/**
	 * @param casefileReferralsSummaryEntries the casefileReferralsSummaryEntries to set
	 */
	public void setCasefileReferralsSummaryEntries(
			Collection casefileReferralsSummaryEntries) {
		this.casefileReferralsSummaryEntries = casefileReferralsSummaryEntries;
	}

	/**
	 * @return the maysiAssessmentSummaryEntries
	 */
	public Collection getMaysiAssessmentSummaryEntries() {
		return maysiAssessmentSummaryEntries;
	}

	/**
	 * @param maysiAssessmentSummaryEntries the maysiAssessmentSummaryEntries to set
	 */
	public void setMaysiAssessmentSummaryEntries(
			Collection maysiAssessmentSummaryEntries) {
		this.maysiAssessmentSummaryEntries = maysiAssessmentSummaryEntries;
	}

	/**
	 * @return the overviewSummarySupervisionLevel
	 */
	public String getOverviewSummarySupervisionLevel() {
		return overviewSummarySupervisionLevel;
	}

	/**
	 * @param overviewSummarySupervisionLevel the overviewSummarySupervisionLevel to set
	 */
	public void setOverviewSummarySupervisionLevel(
			String overviewSummarySupervisionLevel) {
		this.overviewSummarySupervisionLevel = overviewSummarySupervisionLevel;
	}

	/**
	 * @return the overviewActivitySummaryEntries
	 */
	public Collection getOverviewActivitySummaryEntries() {
		return overviewActivitySummaryEntries;
	}

	/**
	 * @param overviewActivitySummaryEntries the overviewActivitySummaryEntries to set
	 */
	public void setOverviewActivitySummaryEntries(
			Collection overviewActivitySummaryEntries) {
		this.overviewActivitySummaryEntries = overviewActivitySummaryEntries;
	}
	/**
	 * @return the overviewFacilityActivitySummaryEntries
	 */
	public Collection getOverviewFacilityActivitySummaryEntries() {
		return overviewFacilityActivitySummaryEntries;
	}

	/**
	 * @param overviewActivitySummaryEntries the overviewActivitySummaryEntries to set
	 */
	public void setOverviewFacilityActivitySummaryEntries(
			Collection overviewFacilityActivitySummaryEntries) {
		this.overviewFacilityActivitySummaryEntries = overviewFacilityActivitySummaryEntries;
	}
	/**
	 * @return the overviewCalendarEventSummaryEntries
	 */
	public Collection getOverviewCalendarEventSummaryEntries() {
		return overviewCalendarEventSummaryEntries;
	}

	/**
	 * @param overviewCalendarEventSummaryEntries the overviewCalendarEventSummaryEntries to set
	 */
	public void setOverviewCalendarEventSummaryEntries(
			Collection overviewCalendarEventSummaryEntries) {
		this.overviewCalendarEventSummaryEntries = overviewCalendarEventSummaryEntries;
	}

	/**
	 * @return the overviewProgressRiskSummaryEntries
	 */
	public Collection getOverviewProgressRiskSummaryEntries() {
		return overviewProgressRiskSummaryEntries;
	}

	/**
	 * @param overviewProgressRiskSummaryEntries the overviewProgressRiskSummaryEntries to set
	 */
	public void setOverviewProgressRiskSummaryEntries(
			Collection overviewProgressRiskSummaryEntries) {
		this.overviewProgressRiskSummaryEntries = overviewProgressRiskSummaryEntries;
	}

	/**
	 * @return the caseReviewReportContent
	 */
	public byte[] getCaseReviewReportContent() {
		return caseReviewReportContent;
	}

	/**
	 * @param caseReviewReportContent the caseReviewReportContent to set
	 */
	public void setCaseReviewReportContent(byte[] caseReviewReportContent) {
		this.caseReviewReportContent = caseReviewReportContent;
	}

	/**
	 * @return the historicalAssignmentSummaryEntries
	 */
	public Collection getHistoricalAssignmentSummaryEntries() {
		return historicalAssignmentSummaryEntries;
	}

	/**
	 * @param historicalAssignmentSummaryEntries the historicalAssignmentSummaryEntries to set
	 */
	public void setHistoricalAssignmentSummaryEntries(
			Collection historicalAssignmentSummaryEntries) {
		this.historicalAssignmentSummaryEntries = historicalAssignmentSummaryEntries;
	}

	/**
	 * @return the noMaysiSectionMaysiAndAssignmentSummaryEntries
	 */
	public Collection getNoMaysiSectionMaysiAndAssignmentSummaryEntries() {
		return noMaysiSectionMaysiAndAssignmentSummaryEntries;
	}

	/**
	 * @param noMaysiSectionMaysiAndAssignmentSummaryEntries the noMaysiSectionMaysiAndAssignmentSummaryEntries to set
	 */
	public void setNoMaysiSectionMaysiAndAssignmentSummaryEntries(
			Collection noMaysiSectionMaysiAndAssignmentSummaryEntries) {
		this.noMaysiSectionMaysiAndAssignmentSummaryEntries = noMaysiSectionMaysiAndAssignmentSummaryEntries;
	}

	/**
	 * @return the noMaysiSectionAssignmentWithNoMaysiSummaryEntries
	 */
	public Collection getNoMaysiSectionAssignmentWithNoMaysiSummaryEntries() {
		return noMaysiSectionAssignmentWithNoMaysiSummaryEntries;
	}

	/**
	 * @param noMaysiSectionAssignmentWithNoMaysiSummaryEntries the noMaysiSectionAssignmentWithNoMaysiSummaryEntries to set
	 */
	public void setNoMaysiSectionAssignmentWithNoMaysiSummaryEntries(
			Collection noMaysiSectionAssignmentWithNoMaysiSummaryEntries) {
		this.noMaysiSectionAssignmentWithNoMaysiSummaryEntries = noMaysiSectionAssignmentWithNoMaysiSummaryEntries;
	}

	/**
	 * @return the facEventEntries
	 */
	public Collection getFacEventEntries() {
		return facEventEntries;
	}

	/**
	 * @param facEventEntries the facEventEntries to set
	 */
	public void setFacEventEntries(Collection facEventEntries) {
		this.facEventEntries = facEventEntries;
	}

	public Collection getFacilityActivitySummaryEntries() {
	    return facilityActivitySummaryEntries;
	}

	public void setFacilityActivitySummaryEntries(
		Collection facilityActivitySummaryEntries) {
	    this.facilityActivitySummaryEntries = facilityActivitySummaryEntries;
	}

	public Collection getJuvenileProfileCasefileList()
	{
	    return juvenileProfileCasefileList;
	}

	public void setJuvenileProfileCasefileList(Collection juvenileProfileCasefileList)
	{
	    this.juvenileProfileCasefileList = juvenileProfileCasefileList;
	}

	public String[] getSelectedCasefiles()
	{
	    return selectedCasefiles;
	}

	public void setSelectedCasefiles(String[] selectedCasefiles)
	{
	    this.selectedCasefiles = selectedCasefiles;
	}

	public String getSelectedValues()
	{
	    return selectedValues;
	}

	public void setSelectedValues(String selectedValues)
	{
	    this.selectedValues = selectedValues;
	}	

	
}// End Class
