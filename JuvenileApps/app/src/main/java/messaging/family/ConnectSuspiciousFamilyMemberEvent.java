package messaging.family;

import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import mojo.km.messaging.RequestEvent;

public class ConnectSuspiciousFamilyMemberEvent extends RequestEvent
{
	private FamilyMemberDetailResponseEvent memberA;
	
	private FamilyMemberDetailResponseEvent memberB;
	
	private String memberA_Id;
	
	private String memberB_Id;

	public FamilyMemberDetailResponseEvent getMemberA()
	{
	    return memberA;
	}

	public void setMemberA(FamilyMemberDetailResponseEvent memberA)
	{
	    this.memberA = memberA;
	}

	public FamilyMemberDetailResponseEvent getMemberB()
	{
	    return memberB;
	}

	public void setMemberB(FamilyMemberDetailResponseEvent memberB)
	{
	    this.memberB = memberB;
	}

	public String getMemberA_Id()
	{
	    return memberA_Id;
	}

	public void setMemberA_Id(String memberA_Id)
	{
	    this.memberA_Id = memberA_Id;
	}

	public String getMemberB_Id()
	{
	    return memberB_Id;
	}

	public void setMemberB_Id(String memberB_Id)
	{
	    this.memberB_Id = memberB_Id;
	}
	
	
	
}
