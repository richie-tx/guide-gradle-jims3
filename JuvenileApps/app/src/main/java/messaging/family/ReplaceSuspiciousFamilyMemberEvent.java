package messaging.family;

import mojo.km.messaging.RequestEvent;

public class ReplaceSuspiciousFamilyMemberEvent extends RequestEvent
{
	String familyMemberIdToBeReplaced;
	String targetFamilyMemberId;
	public String getFamilyMemberIdToBeReplaced() {
		return familyMemberIdToBeReplaced;
	}
	public void setFamilyMemberIdToBeReplaced(String familyMemberIdToBeReplaced) {
		this.familyMemberIdToBeReplaced = familyMemberIdToBeReplaced;
	}
	public String getTargetFamilyMemberId() {
		return targetFamilyMemberId;
	}
	public void setTargetFamilyMemberId(String targetFamilyMemberId) {
		this.targetFamilyMemberId = targetFamilyMemberId;
	}

}
