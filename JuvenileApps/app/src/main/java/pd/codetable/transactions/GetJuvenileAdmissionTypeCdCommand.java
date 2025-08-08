/*
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetJuvenileAdmissionTypeCdEvent;
import messaging.codetable.criminal.reply.HospitalizationAdmissionTypeCdResponseEvent;
import messaging.mentalhealth.reply.HospitalizationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileHospitalizationAdmissionTypeCode;
import pd.juvenilecase.mentalhealth.Hospitalization;


/**
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileAdmissionTypeCdCommand implements ICommand
{

	/**
	 * 
	 */
	public GetJuvenileAdmissionTypeCdCommand()
	{

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetJuvenileAdmissionTypeCdEvent getCodesEvent = (GetJuvenileAdmissionTypeCdEvent) event;

		Iterator i = JuvenileHospitalizationAdmissionTypeCode.findAdmissionTypeCodes(getCodesEvent);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		int count = 0;
		while (i.hasNext())
		{
			count++;
			JuvenileHospitalizationAdmissionTypeCode juv = (JuvenileHospitalizationAdmissionTypeCode)i.next();
			HospitalizationAdmissionTypeCdResponseEvent replyEvent = new HospitalizationAdmissionTypeCdResponseEvent();
			replyEvent.setAdmissionTypeCode(juv.getCode());
			replyEvent.setDescription(juv.getDescription());
			replyEvent.setCategoryId(juv.getCategoryId());
			dispatch.postEvent(replyEvent);
		}		
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	
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
