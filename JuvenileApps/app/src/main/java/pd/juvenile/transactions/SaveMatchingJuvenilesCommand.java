/*
 * Created on May 9, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenile.transactions;

import java.util.Date;
import java.util.Iterator;

import pd.juvenile.JuvenileMatch;

import messaging.juvenile.GetSpecificMatchingJuvenilesEvent;
import messaging.juvenile.SaveMatchingJuvenilesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;


/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveMatchingJuvenilesCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
			SaveMatchingJuvenilesEvent juvMatchEvent = (SaveMatchingJuvenilesEvent) event;
			if(juvMatchEvent.getJuvA()!=null && juvMatchEvent.getJuvB()!=null){
				
			}
			else{
				return;
			}
			JuvenileMatch juvenileMatch;
			GetSpecificMatchingJuvenilesEvent myMatchingEvent=new GetSpecificMatchingJuvenilesEvent();
			myMatchingEvent.setJuvA(juvMatchEvent.getJuvA());
			myMatchingEvent.setJuvB(juvMatchEvent.getJuvB());
			Iterator iter=JuvenileMatch.findAll(myMatchingEvent);
			if(iter==null || !iter.hasNext()){
				myMatchingEvent=new GetSpecificMatchingJuvenilesEvent();
				myMatchingEvent.setJuvA(juvMatchEvent.getJuvB());
				myMatchingEvent.setJuvB(juvMatchEvent.getJuvA());
				iter=JuvenileMatch.findAll(myMatchingEvent);
				if(iter==null || !iter.hasNext()){
					juvenileMatch=new JuvenileMatch(); 
					juvenileMatch.setEntryDate(new Date());
					juvenileMatch.setCreateUserID(juvMatchEvent.getCreateUser());
					if(juvMatchEvent.getJuvA().compareTo(juvMatchEvent.getJuvB())<=0){
						juvenileMatch.setJuvA(juvMatchEvent.getJuvA());
						juvenileMatch.setJuvB(juvMatchEvent.getJuvB());
					}
					else{  // ensure order is always the same, smallest first then larger
						juvenileMatch.setJuvA(juvMatchEvent.getJuvB());
						juvenileMatch.setJuvB(juvMatchEvent.getJuvA());
					}
					juvenileMatch.setNotes(juvMatchEvent.getNotes());
					juvenileMatch.setStatus(juvMatchEvent.getStatus());
					juvenileMatch.setUpdateDate(new Date());
					IHome home = new Home();
					home.bind(juvenileMatch);
				}
				
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

