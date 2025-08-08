package pd.juvenilecase.family.transactions;

import java.util.Date;

import messaging.family.ConnectSuspiciousFamilyMemberEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.family.FamilyMemberMatch;

public class ConnectSuspiciousFamilyMemberCommand implements ICommand
{
    public void execute(IEvent anEvent) throws Exception
    {
	ConnectSuspiciousFamilyMemberEvent reqEvent = (ConnectSuspiciousFamilyMemberEvent) anEvent;

	
	// The OID is needed from this class to add onto Suspicious
	try
	{
	    FamilyMemberMatch object = new FamilyMemberMatch();
	    object.setMemberA(reqEvent.getMemberA_Id());
	    object.setMemberB(reqEvent.getMemberB_Id());
	    object.setStatus("UNRESOLVED");
	    object.setNotes("SYSTEM GENERATED NOTES: Manually Created Match ");
	    object.setEntryDate(new Date());
	    object.setMatchType("USER"); //added for US 181437

	    IHome home = new Home();
	    home.bind(object);

	}
	catch (Exception e)
	{
	    // TODO Auto-generated catch block
	   // e.printStackTrace();
	}
	
/*	FamilyMember memberA = null;
	if ( StringUtils.isNotEmpty(reqEvent.getMemberA_Id()) )
	{
	    memberA = FamilyMember.find(reqEvent.getMemberA_Id());
	    if( isInValidSsn( reqEvent.getMemberA().getSsn()) ){
		
		 memberA.setSsn("123456789");
	    }

	}
	
	FamilyMember memberB = null;

	if ( StringUtils.isNotEmpty(reqEvent.getMemberB_Id()) )
	{
	    memberB = FamilyMember.find(reqEvent.getMemberB_Id());
	    if( isInValidSsn( reqEvent.getMemberB().getSsn()) ){
		
		 memberB.setSsn("123456789");
	    }

	}*/

    }

//    private static boolean isInValidSsn(String ssn){
//	    
//	return (("666666666").equals(ssn) || ("777777777").equals(ssn) || ("888888888").equals(ssn) || ("999999999").equals(ssn));
//		    
//    }
    
    public void onRegister(IEvent anEvent)
    {
    }

    public void onUnregister(IEvent anEvent)
    {
    }

    public void update(Object anObject)
    {
    }

}
