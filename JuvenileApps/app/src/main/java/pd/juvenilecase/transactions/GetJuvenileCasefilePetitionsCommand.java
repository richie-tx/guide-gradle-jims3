//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefilePetitionsCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilewarrant.JJSPetition;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefilePetitionsCommand implements ICommand
{

	/**
	 * @roseuid 42A9A305034F
	 */
	public GetJuvenileCasefilePetitionsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B98032F
	 */
	public void execute(IEvent event)
	{
		GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		

		GetJJSPetitionsEvent jjsevent = (GetJJSPetitionsEvent) new GetJJSPetitionsEvent();
		jjsevent.setJuvenileNum(pet.getJuvenileNum());
		jjsevent.setReferralNum(pet.getReferralNum());
		Iterator i = JJSPetition.findAll(jjsevent);
		
		
		GetJJSCourtEvent jjsCourtevent = new GetJJSCourtEvent();
		jjsCourtevent.setJuvenileNumber(pet.getJuvenileNum());
		jjsCourtevent.setReferralNumber(pet.getReferralNum());
		
		while (i.hasNext())
		{
			JJSPetition jjspet = (JJSPetition) i.next();
			JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",jjspet.getOffenseCodeId());			
			JJSChargeResponseEvent resp = PDJuvenileWarrantHelper.getJJSChargeResponseEvent(jjspet);
			resp.setPenalCategory(offenseCode.getCitationCode());
			resp.setLevelDegree(offenseCode.getCategory());
			resp.setDpsCode(offenseCode.getDpsOffenseCode()); //added dps code
			resp.setTerminationDate( jjspet.getTerminationDate());
			
			Iterator<JJSCourt> courtIter = JJSCourt.findAll(jjsCourtevent);
			while ( courtIter.hasNext() ) {
			    JJSCourt court = (JJSCourt) courtIter.next();
			    
			    if ( nvl( jjspet.getAmend() ) == 0 
				    || ( nvl( jjspet.getAmend() ) == nvl( court.getPetitionAmendment())
					    && nvl( jjspet.getAmend() ) == 1 )
				    ) {
				resp.setFiledAmendDate(court.getFilingDate());
			    } 
			    
			    if ( nvl( jjspet.getAmend() ) == nvl( court.getPetitionAmendment())
				    && nvl( jjspet.getAmend() ) >1
				    && court.getAmendmentDate() != null ){
				resp.setFiledAmendDate(court.getAmendmentDate());
			    }
			    
			}
			
			//Added for Common App, since citation source is combined with citation code (aka penalCategory here
			//to make Penal Code
			resp.setCitationSource(offenseCode.getCitationSource());
			//System.out.println("Send response - filedAmendDate: " + resp.getFiledAmendDate());
			dispatch.postEvent(resp);
		}
	}
	
	int nvl(String value) {
	    return (value != null && value.length() > 0 ) ? Integer.parseInt(value) : 0;
	}

	/**
	 * @param event
	 * @roseuid 42A99B980331
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B980338
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A99B98033A
	 */
	public void update(Object anObject)
	{

	}
}
