package messaging.calendar;
import mojo.km.messaging.RequestEvent;

public class SaveMonthlySummaryEvent extends RequestEvent{
	public String serviceEventId;
	public String juvenileId;
	public String monthlySummary;

	/**
	 * @roseuid 456F33E603CA
	 */
	public SaveMonthlySummaryEvent() {

	}

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

	public String getJuvenileId() {
		return juvenileId;
	}

	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	public String getMonthlySummary() {
		return monthlySummary;
	}

	public void setMonthlySummary(String monthlySummary) {
		this.monthlySummary = monthlySummary;
	}
}
