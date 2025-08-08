package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileMemberByIdEvent extends RequestEvent{
	private String memberNum;
	private String juvenileId;
	
	/**
	 * @return the memberNum
	 */
	public String getMemberNum() {
		return memberNum;
	}
	/**
	 * @param memberNum the memberNum to set
	 */
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
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
