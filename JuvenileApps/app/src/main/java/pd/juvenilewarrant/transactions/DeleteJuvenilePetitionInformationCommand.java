package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilewarrant.DeleteJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;
import pd.security.PDSecurityHelper;

/**
 * 
 * @author sthyagarajan
 * 
 */
public class DeleteJuvenilePetitionInformationCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	boolean isPetDeleted = false;
	DeleteJuvenilePetitionInformationEvent evt = (DeleteJuvenilePetitionInformationEvent) event;
	PetitionResponseEvent respEvt = new PetitionResponseEvent();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
	petitionEvent.setJuvenileNum(evt.getJuvenileNum());
	petitionEvent.setReferralNum(evt.getReferralNum());

	Iterator<JJSPetition> petition = new ArrayList().iterator(); // empty   iterator
	petition = JJSPetition.findAll(petitionEvent); // regular find.
	
	String sequenceNum =evt.getSeqNum();
	
	if(petition!=null){
        	while (petition.hasNext())
        	{
        	    try
        	    {
        		JJSPetition pet = (JJSPetition) petition.next();
        		if (pet.getJuvenileNum().equalsIgnoreCase(evt.getJuvenileNum()) && pet.getSequenceNum().equalsIgnoreCase(evt.getSeqNum()))
        		{
        		    pet.delete();
        		    new Home().bind(pet);
        		    isPetDeleted = true;
        		    break;
        		}
        	    } catch (Exception e)
        	    {
        		isPetDeleted = false;
        	    }
        	}
	}
	// Delete the court record
	if (isPetDeleted)
	{
	    GetJJSCourtEvent courtEvt = new GetJJSCourtEvent();
	    courtEvt.setJuvenileNumber(evt.getJuvenileNum());
	    courtEvt.setReferralNumber(evt.getReferralNum());

	    Iterator<JJSCourt> courtItr = JJSCourt.findAll(courtEvt);
	    while (courtItr.hasNext())
	    {
		JJSCourt court = courtItr.next();
		if (court.getChainNumber().equalsIgnoreCase(evt.getChainNum()))
		{
		    try
		    {
			court.delete();
			new Home().bind(court);
			break;
		    } catch (Exception e)
		    {
			e.printStackTrace();
			isPetDeleted = false;
		    }
		}
	    }
	}

	// update Referral
	if (isPetDeleted)
	{
	    GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
	    jjsEvt.setJuvenileNum(evt.getJuvenileNum());
	    jjsEvt.setReferralNum(evt.getReferralNum());

	    Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);

	    if (refIter.hasNext())
	    {
		JJSReferral ref = refIter.next();
		if (ref != null)
		{
		    if (sequenceNum != null && sequenceNum.equals("1"))
		    {
			ref.setProbationStartDate(null);
			ref.setProbationEndDate(null);
			ref.setPIACode(null);
			ref.setIntakeDate(null);
			ref.setIntakeDecision(null);
			ref.setLcDate(DateUtil.getCurrentDate());
			ref.setLcTime(Calendar.getInstance().getTime());
			ref.setLcUser(PDSecurityHelper.getLogonId());
			new Home().bind(ref);
		    }
		}
	    }
	}
	respEvt.setDeleteFlag(isPetDeleted ? "true" : "false");
	dispatch.postEvent(respEvt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.Ipetition)
     */
    public void onRegister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
	// TODO Auto-generated method stub

    }
}
