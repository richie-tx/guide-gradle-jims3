package messaging.administersupervisionplan.query;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetSupervisionPlansEvent extends RequestEvent
{
	private String defendantId;
	private Date beginDate;
	private Date endDate;
	
	
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public String getDefendantId() {
		return defendantId;
	}
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
