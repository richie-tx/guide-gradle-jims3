/*
 * Created on May 9, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Date;
import messaging.family.UpdateFamilyMemberMatchesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.family.FamilyMemberMatch;


/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateFamilyMemberMatchesCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
			UpdateFamilyMemberMatchesEvent addMemberMatch = (UpdateFamilyMemberMatchesEvent) event;
			FamilyMemberMatch familyMemberMatch;
			if(addMemberMatch.getMemberMatchId()==null || addMemberMatch.getMemberMatchId().equalsIgnoreCase(""))
			{	
				familyMemberMatch=new FamilyMemberMatch(); 
				familyMemberMatch.setEntryDate(new Date());
			}
			else
			{
				familyMemberMatch=FamilyMemberMatch.find(addMemberMatch.getMemberMatchId());
			}
			familyMemberMatch.setCreateUserID(addMemberMatch.getCreateUser());
			
			if(addMemberMatch.getMemberA()!=null && addMemberMatch.getMemberB()!=null){
				if(addMemberMatch.getMemberA().compareTo(addMemberMatch.getMemberB())<=0){
					familyMemberMatch.setMemberA(addMemberMatch.getMemberA());
					familyMemberMatch.setMemberB(addMemberMatch.getMemberB());
				}
				else{  // ensure order is always the same, smallest first then larger
					familyMemberMatch.setMemberA(addMemberMatch.getMemberB());
					familyMemberMatch.setMemberB(addMemberMatch.getMemberA());
				}
				familyMemberMatch.setNotes(addMemberMatch.getNotes());
				familyMemberMatch.setStatus(addMemberMatch.getStatus());
				familyMemberMatch.setUpdateDate(new Date());
				familyMemberMatch.setMatchType("SSN"); //added for US 181437
				IHome home = new Home();
				home.bind(familyMemberMatch);
			}
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}

