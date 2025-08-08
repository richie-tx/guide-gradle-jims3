/*
 * Created on Oct 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetBenefitsAssessmentGuardiansEvent;
import messaging.family.GetFamilyConstellationMembersEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentGuardianResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.family.BenefitsAssessmentHelper;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetBenefitsAssessmentGuardiansCommand implements ICommand, ReadOnlyTransactional
{

	/**
	 * 
	 */
	public GetBenefitsAssessmentGuardiansCommand()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetBenefitsAssessmentGuardiansEvent requestEvent = (GetBenefitsAssessmentGuardiansEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		// Profile stripping fix task 97539
		//Juvenile juv = Juvenile.find( requestEvent.getJuvenileId() );
		JuvenileCore juv = JuvenileCore.findCore( requestEvent.getJuvenileId() );
		//
		Iterator iter = BenefitsAssessmentHelper.getGuardiansForJuvenile(juv).iterator();
		while ( iter.hasNext() )
		{
			dispatch.postEvent( ((BenefitsAssessmentGuardianResponseEvent)iter.next()) );
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
