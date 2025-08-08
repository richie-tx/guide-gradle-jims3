 package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumRefNumEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetPetitionBySequenceEvent;
import messaging.juvenilewarrant.SaveJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.UpdateJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.juvenilewarrant.JJSPetition;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class UpdateJuvenilePetitionInformationCommand implements ICommand
{
    /**
     * execute - called by default.
     */
    public void execute(IEvent event) throws Exception
    {
	// no update always create new record with new sequence number.discussed on mjcw meeting 3/27/2018.
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	IHome home = new Home();	
	//JJSPetition newPetition = new JJSPetition();
		
	PetitionResponseEvent evt = new PetitionResponseEvent();
	UpdateJuvenilePetitionInformationEvent petitionEvent = (UpdateJuvenilePetitionInformationEvent) event;
	
	
	Iterator<JJSPetition> petitionItr = new ArrayList().iterator(); // empty iterator

	
	petitionItr = JJSPetition.findAll(petitionEvent); 
	//order desc seqnum
	    /*List petitionList = CollectionUtil.iteratorToList( petitionItr );
	    Collections.sort((List<JJSPetition>)petitionList,Collections.reverseOrder(new Comparator<JJSPetition>() {
		@Override
		public int compare(JJSPetition evt1, JJSPetition evt2) {
			return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
		}
	    	}));*/
	//change this to pull the filed petition for this juvnum and refnum and then update dpscode
	while (petitionItr != null && petitionItr.hasNext())
	{
	    JJSPetition petition = petitionItr.next();
	    //JJSPetition petition = (JJSPetition) petitionList.get(0);
	    if(petition.getStatus().equalsIgnoreCase("F"))//&&petition.getDPSCode().equalsIgnoreCase("610")
	    {
		petition.setDPSCode(petitionEvent.getDPSCode());		
        	home.bind(petition);
			///
	    }
	}
	

    }

    private void setPetSeverity(JJSPetition newPetition, SaveJuvenilePetitionInformationEvent petitionEvent)
    {
	Collection<JuvenileProfileReferralListResponseEvent> juvProfResEvt = JuvenileFacilityHelper.getJuvReferralDetails(petitionEvent.getJuvenileNum());
	Iterator<JuvenileProfileReferralListResponseEvent> juvProfResItr = juvProfResEvt.iterator();
	List<Integer> severityList = new ArrayList();
		
	while (juvProfResItr.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent ref = juvProfResItr.next();
	    if (ref != null && ref.getReferralTypeInd() != null
		    && ref.getReferralTypeInd().equalsIgnoreCase("TR") && ref.getReferralNumber().equalsIgnoreCase(petitionEvent.getReferralNum()))
	    {
		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
		getEvent.setJuvenileNumber(petitionEvent.getJuvenileNum());
		getEvent.setReferralNumber(ref.getReferralNumber());

		Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);
		
		severityList.add(0);

		while (transOffenseReferralsIter.hasNext())
		{
		    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();

		    if (transOffenseReferral != null
			    && transOffenseReferral.getOffenseCode() != null
			    && transOffenseReferral.getOffenseCode() != "")
		    {
			JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", transOffenseReferral.getOffenseCode());

			if (offenseCode != null && offenseCode.getSeverity() != null && offenseCode.getSeverity() != "")
			{
			    try {
	                            int severity = Integer.parseInt(offenseCode.getSeverity()); 
	                            severityList.add(severity); 
	                        } catch (NumberFormatException e) {
	                        }
			}
		    }
		}
	    }
	}
	
	if (!severityList.isEmpty()) {
	        int maxSeverity = Collections.max(severityList); 
	        newPetition.setSeverity(String.valueOf(maxSeverity)); 
	}

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
