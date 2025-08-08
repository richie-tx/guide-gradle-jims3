package messaging.family;

import mojo.km.messaging.RequestEvent;

public class MergeSuspiciousFamilyMembersEvent extends RequestEvent
{
	String fromFamilyMemberId;
	String toFamilyMemberId;
	public String getFromFamilyMemberId() {
		return fromFamilyMemberId;
	}
	public void setFromFamilyMemberId(String fromFamilyMemberId) {
		this.fromFamilyMemberId = fromFamilyMemberId;
	}
	public String getToFamilyMemberId() {
		return toFamilyMemberId;
	}
	public void setToFamilyMemberId(String toFamilyMemberId) {
		this.toFamilyMemberId = toFamilyMemberId;
	}
}
