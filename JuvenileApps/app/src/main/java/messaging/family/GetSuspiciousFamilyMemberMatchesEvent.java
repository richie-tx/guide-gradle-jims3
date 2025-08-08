package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetSuspiciousFamilyMemberMatchesEvent extends RequestEvent
{
	private String familyMemberId;

	public void setFamilyMemberId(String familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	public String getFamilyMemberId() {
		return familyMemberId;
	}
}
