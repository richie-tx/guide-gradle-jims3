package messaging.juvenile;

import mojo.km.messaging.RequestEvent;
/**
 * 
 * @author sthyagarajan
 * added for task 43956
 */
public class GetJuvenilePactSubjectDetailsEvent extends RequestEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String juvenileId;
	
	/**
	 * @roseuid 42A9C41F002E
	 */
	public GetJuvenilePactSubjectDetailsEvent()
	{

		
	}

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
}
