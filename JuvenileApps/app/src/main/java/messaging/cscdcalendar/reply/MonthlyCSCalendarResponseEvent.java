// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\ResponseEvents\\MonthlyCSCalendarResponseEvent.java

package messaging.cscdcalendar.reply;

import java.util.Comparator;

import messaging.calendar.reply.CalendarEventResponse;

public class MonthlyCSCalendarResponseEvent extends CalendarEventResponse implements Comparable {

	private String outcome;

	private String superviseeId;

	private String outcomeDesc;

	private String positionId;

	private String resultUserId;

	private String resultPositionId;

	private String categoryCd;

	private String categoryDesc;

	/**
	 * @roseuid 47A23670030A
	 */
	public MonthlyCSCalendarResponseEvent() {

	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getOutcomeDesc() {
		return outcomeDesc;
	}

	public void setOutcomeDesc(String outcomeDesc) {
		this.outcomeDesc = outcomeDesc;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getResultPositionId() {
		return resultPositionId;
	}

	public void setResultPositionId(String resultPositionId) {
		this.resultPositionId = resultPositionId;
	}

	public String getResultUserId() {
		return resultUserId;
	}

	public void setResultUserId(String resultUserId) {
		this.resultUserId = resultUserId;
	}

	public String getSuperviseeId() {
		return superviseeId;
	}

	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (obj == null)
			return -1;
		MonthlyCSCalendarResponseEvent evt = (MonthlyCSCalendarResponseEvent) obj;
		int result = 0;

		try {
			if (this.getStartDatetime() != null || evt.getStartDatetime() != null) {
				if (this.getStartDatetime() == null)
					return -1; // this makes any null objects go to the bottom
				// change this to 1 if
				// you want the top of the list
				if (evt.getStartDatetime() == null)
					return 1; // this makes any null objects go to the bottom
				// change this to -1 if
				// you want the top of the list
				result = this.getStartDatetime().compareTo(evt.getStartDatetime()); // backwards
				// in order to get list to show up most recent first
			}

		} catch (NumberFormatException e) {
			result = 0;
		}

		return result;
	}
	
	public static Comparator CSCalendarDateComparator = new Comparator() {
		public int compare(Object startDate, Object otherStartDate) {
			
		  int result = 0;
		  java.util.Date bStartDate = ((MonthlyCSCalendarResponseEvent)startDate).getStartDatetime();
		  java.util.Date bOtherStartDate = ((MonthlyCSCalendarResponseEvent)otherStartDate).getStartDatetime();
		  
		  if(bStartDate == null)
		  {
			  result = -1;
		  }
		  else if(bOtherStartDate == null)
		  {
			  result = -1;
		  }
		  else 
		  {
			  result = bStartDate.compareTo(bOtherStartDate);
		  }
		  return result;
		}	
	};

}
