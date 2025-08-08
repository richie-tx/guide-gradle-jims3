package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetSuspiciousMembersByMemberIdEvent extends RequestEvent {
	
	private String memberA;

	public String getMemberA() {
		return memberA;
	}
	public void setMemberA(String memberA) {
		this.memberA = memberA;
	}

}
