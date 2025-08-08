//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveJuvenileTraitsCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import messaging.referral.GetJJSReferralEvent;
import messaging.referral.SaveTransferredOffenseReferralsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;

public class SaveTransferredOffenseReferralsCommand implements ICommand
{

	/**
	 * @roseuid 42A75896000B
	 */
	public SaveTransferredOffenseReferralsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A731DC0318
	 */
	public void execute(IEvent event)
	{
		SaveTransferredOffenseReferralsEvent saveEvent = (SaveTransferredOffenseReferralsEvent) event;

		if(saveEvent != null && saveEvent.getTransOffenseReferralId() != null && !saveEvent.getTransOffenseReferralId().isEmpty())
		{
		    JJSTransferredOffenseReferral offRef = JJSTransferredOffenseReferral.find(saveEvent.getTransOffenseReferralId());
		    
		    if(offRef != null)
		    {
			offRef.setJuvenileNumber(saveEvent.getJuvenileNum());
			offRef.setReferralNumber(saveEvent.getReferralNum());
			offRef.setFromCountyCode(saveEvent.getCountyId());
			offRef.setOffenseCode(saveEvent.getOffenseId());
			offRef.setCategoryCode(saveEvent.getOffenseCategory());
			offRef.setDpsCode(saveEvent.getDpsCode());
			offRef.setOffenseDate(saveEvent.getOffenseDate());
			offRef.setAdjudicationDate(saveEvent.getAdjudicationDate());
			offRef.setPersonId(saveEvent.getPersonId()); 
			IHome home = new Home();
			home.bind(offRef);
		    }
		}
		else
		{
		
		JJSTransferredOffenseReferral offRef = new JJSTransferredOffenseReferral();
		offRef.setJuvenileNumber(saveEvent.getJuvenileNum());
		offRef.setReferralNumber(saveEvent.getReferralNum());
		offRef.setFromCountyCode(saveEvent.getCountyId());
		offRef.setOffenseCode(saveEvent.getOffenseId());
		offRef.setCategoryCode(saveEvent.getOffenseCategory());
		offRef.setDpsCode(saveEvent.getDpsCode());
		offRef.setOffenseDate(saveEvent.getOffenseDate());
		offRef.setAdjudicationDate(saveEvent.getAdjudicationDate());
		offRef.setPersonId(saveEvent.getPersonId()); //U.S #11081
		
		//U.S # 122011
		//if( saveEvent.getProbationStartDate() != null || saveEvent.getProbationEndDate() != null){
		    
		    IHome home = new Home();
		    GetJJSReferralEvent jjsRefEvt = new GetJJSReferralEvent();
		    jjsRefEvt.setJuvenileNum(saveEvent.getJuvenileNum());
		    jjsRefEvt.setReferralNum(saveEvent.getReferralNum());
			
		    Iterator iter = JJSReferral.findAll(jjsRefEvt);
		    if( iter.hasNext())
		    {			
			JJSReferral ref = (JJSReferral) iter.next();
			if( saveEvent.getProbationStartDate() != null || saveEvent.getProbationEndDate() != null)
			{
        			ref.setProbationStartDate(saveEvent.getProbationStartDate());
        			ref.setProbationEndDate(saveEvent.getProbationEndDate());
			}
			ref.setCountyREFD(saveEvent.getCountyId());
			home.bind(ref);
		    }
		//}
	     }
	}

	/**
	 * @param event
	 * @roseuid 42A731DC031A
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A731DC0325
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A731DC0327
	 */
	public void update(Object anObject)
	{

	}

}
