package messaging.districtCourtHearings;
import mojo.km.messaging.RequestEvent;

/**
 * @author nemathew
 *
 */
public class GetJJSCLAncillarySettingDisplayEvent extends RequestEvent {
	
	private String courtId;
	private String courtDate;
	public String getCourtId() {
		return courtId;
	}
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	public String getCourtDate() {
		return courtDate;
	}
	public void setCourtDate(String courtDate) {
		this.courtDate = courtDate;
	}
	
	

}
