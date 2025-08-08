package pd.supervision.administerassessments.adhocs;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetAssessmentBySpnAndDate extends RequestEvent {
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	private String spn;
	private Date assessmentDate;

}
