/*
 * Created on Oct 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import messaging.juvenilecase.GetJuvenileFacilityHistoricalReceiptsEvent;
import messaging.juvenilecase.GetJuvenileInfoEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHistoricalReceiptsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JJSFacility;

/**
 * @author rcarter
 *
 */
public class GetJuvenileFacilityHistoricalReceiptsCommand  implements ICommand{
	
	public GetJuvenileFacilityHistoricalReceiptsCommand() {}

	public void execute(IEvent event)
	{
		GetJuvenileFacilityHistoricalReceiptsEvent facilityHistoricalReceiptEvent = (GetJuvenileFacilityHistoricalReceiptsEvent) event;
		JuvenileProfilesResponseEvent juvenileProfileResp = new JuvenileProfilesResponseEvent();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);	
		Iterator i = JJSFacility.findAll(facilityHistoricalReceiptEvent);
		while (i.hasNext())
		{
			JJSFacility fac = (JJSFacility) i.next();
			JuvenileFacilityHistoricalReceiptsResponseEvent historicalFacilityResp = new JuvenileFacilityHistoricalReceiptsResponseEvent();
			historicalFacilityResp.setFacilityCode(fac.getDetainedFacility());		
			historicalFacilityResp.setAdmitDate(fac.getAdmitDate());
			historicalFacilityResp.setReleaseDate(fac.getReleaseDate());
			historicalFacilityResp.setReceiptNumber(fac.getReceiptNumber());
			historicalFacilityResp.setLockerNumber(fac.getLockerNumber());
			historicalFacilityResp.setReferralNumber(fac.getReferralNumber());
			dispatch.postEvent(historicalFacilityResp);
		}
		getJuvenile(juvenileProfileResp, facilityHistoricalReceiptEvent.getJuvenileNum());	
		dispatch.postEvent(juvenileProfileResp);		
	}
	
	/**
	 * method to retrieve a juvenile from m204 view
	 * @param juvenileProfileResp
	 * @param juvenileNumber
	 */
	private void getJuvenile(JuvenileProfilesResponseEvent juvenileProfileResp, String juvenileNumber){
		GetJuvenileInfoEvent getJuvenile = new GetJuvenileInfoEvent();
		getJuvenile.setJuvenileNum(juvenileNumber);
		Iterator i = JuvenileCore.findAll(getJuvenile);
		while (i.hasNext())
		{
			JuvenileCore juvenile = (JuvenileCore) i.next();
			juvenileProfileResp.setJuvenileNum(juvenileNumber);
			juvenileProfileResp.setFirstName(juvenile.getFirstName());
			juvenileProfileResp.setMiddleName(juvenile.getMiddleName());
			juvenileProfileResp.setLastName(juvenile.getLastName());
			juvenileProfileResp.setRace(determineRaceCode(juvenile));
			juvenileProfileResp.setRaceDesc(juvenile.getRaceCodeDescription());
			juvenileProfileResp.setSex(juvenile.getSexId());
			juvenileProfileResp.setSexDesc(juvenile.getSexCodeDescription());
			juvenileProfileResp.setDateOfBirth(DateUtil.dateToString(juvenile.getDateOfBirth(), DateUtil.DATE_FMT_1));
			juvenileProfileResp.setDateOfBirthAsDate(juvenile.getDateOfBirth());
			break;
		}
	}
	
	/**
	 * calculate race code
	 * @param juvenile
	 * @return
	 */
	private String determineRaceCode(JuvenileCore juvenile){
		String raceCode = "X";
		if(juvenile.getRaceId()!= null){
			// necessary because of business login in JuvenileCore setRaceId()
			if(juvenile.getHispanicInd() != null && juvenile.getHispanicInd().equalsIgnoreCase("Y") && juvenile.getRaceId().equalsIgnoreCase("W")){
				raceCode = "L";
			}else{
				raceCode = juvenile.getRaceId();
			}
		}
		return raceCode;
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}

}
