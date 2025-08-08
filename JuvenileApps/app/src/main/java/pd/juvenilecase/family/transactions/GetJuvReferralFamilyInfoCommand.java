/**
 * created on Aug 23, 2018
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import pd.juvenilecase.family.JuvReferralFamily;
import messaging.family.GetJuvReferralFamilyInfoEvent;
import messaging.juvenilecase.reply.JuvReferralFamilyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author NEMathew
 * to retrieve the FAMILY info from Juv_REFERRAL_FAMILY table
 * needed ONLY for migrated (legacy) records without a FAMILY info on JIMS2
 */
public class GetJuvReferralFamilyInfoCommand implements mojo.km.context.ICommand, mojo.km.transaction.ReadOnlyTransactional
{
    public GetJuvReferralFamilyInfoCommand()
    {
	super();
	// TODO Auto-generated constructor stub
    }
    
    /**
	 *  
	 * @param event @roseuid 432997A90244
	 */
    public void execute(IEvent event) 
	{
	GetJuvReferralFamilyInfoEvent request = (GetJuvReferralFamilyInfoEvent)event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator juvRefFamItr = JuvReferralFamily.findAll("juvenileNum", request.getJuvenileNum());
	JuvReferralFamilyResponseEvent response = new JuvReferralFamilyResponseEvent();
	while(juvRefFamItr.hasNext()){
	    JuvReferralFamily juveReferralFamily = (JuvReferralFamily) juvRefFamItr.next();
	    response.setFathersAddress(juveReferralFamily.getFathersAddress());
	    response.setFathersName(juveReferralFamily.getFathersName());
	    response.setFathersPhone(juveReferralFamily.getFathersPhone());
	    response.setMothersName(juveReferralFamily.getMothersName());
	    response.setMothersAddress(juveReferralFamily.getMothersAddress());
	    response.setMothersPhone(juveReferralFamily.getMothersPhone());
	    response.setOtherName(juveReferralFamily.getOtherName());
	    response.setOtherAddress(juveReferralFamily.getOtherAddress());
	    response.setOtherPhone(juveReferralFamily.getOtherPhone());
	    response.setJuvRefFamId(juveReferralFamily.getOID());      //Bug 82329
	    break; //expecting only one set of data for a juvenile
	}
	dispatch.postEvent(response);
	}
    			
    //copy pasted below
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
