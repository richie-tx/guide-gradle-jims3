package messaging.family;


import mojo.km.messaging.Composite.CompositeRequest;

public class RemoveSuspiciousFamilyMemberFlagsEvent extends CompositeRequest
{
	String familyMemberId;

	public String getFamilyMemberId() {
		return familyMemberId;
	}

	public void setFamilyMemberId(String familyMemberId) {
		this.familyMemberId = familyMemberId;
	}
}
