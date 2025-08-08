package messaging.juvenile;

import mojo.km.messaging.RequestEvent;


public class GetJuvenileSchoolByIDEvent extends RequestEvent
{
	public String schoolId;

	/**
	 * @roseuid 42B196870000
	 */
	public GetJuvenileSchoolByIDEvent()
	{

	}

	/**
	 * @return Returns the schoolId.
	 */
	public String getSchoolId() {
		return schoolId;
	}
	/**
	 * @param schoolId The schoolId to set.
	 */
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
}
