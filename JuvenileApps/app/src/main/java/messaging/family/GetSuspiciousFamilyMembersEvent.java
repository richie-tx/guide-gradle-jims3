package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetSuspiciousFamilyMembersEvent extends RequestEvent
{
	private String memberFirstName;
	private String memberLastName;
	private String memberSsn;
	private String juvenileNum;
	private String famMemberId;
	
	public String getMemberFirstName() {
		return memberFirstName;
	}
	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}
	public String getMemberLastName() {
		return memberLastName;
	}
	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}
	public String getMemberSsn() {
		return memberSsn;
	}
	public void setMemberSsn(String memberSsn) {
		this.memberSsn = memberSsn;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public String getFamMemberId()
	{
	    return famMemberId;
	}
	public void setFamMemberId(String famMemberId)
	{
	    this.famMemberId = famMemberId;
	}
}
