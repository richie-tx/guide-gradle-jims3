//Source file:
//C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\referral\\transactions\\GetBehavioralHistoryCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import messaging.referral.GetCommonAppJusticeHistoryEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.reply.CommonAppJusticeHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;

public class GetCommonAppJusticeHistoryCommand implements ICommand {

	/**
	 * @roseuid 453E37640197
	 */
	public GetCommonAppJusticeHistoryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 453E2E2003BC
	 */
	public void execute(IEvent event) {
 	
		GetCommonAppJusticeHistoryEvent reqEvent = (GetCommonAppJusticeHistoryEvent) event;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		CommonAppJusticeHistoryResponseEvent resp = getCommonAppJusticeHistory(reqEvent);
		dispatch.postEvent(resp);
 	
	}

	/**
	 * @param event
	 * @roseuid 453E2E2003BE
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 453E2E2003C0
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 453E2E2003CC
	 */
	public void update(Object anObject) {

	}
	
	public static CommonAppJusticeHistoryResponseEvent getCommonAppJusticeHistory(GetCommonAppJusticeHistoryEvent reqEvent) {

		JJSJuvenile juvenile = JJSJuvenile.find(reqEvent.getJuvenileNum());
		
		CommonAppJusticeHistoryResponseEvent resp = new CommonAppJusticeHistoryResponseEvent();
		if (juvenile != null) {
	    	Iterator<JJSReferral> iter = JJSReferral.findAll("juvenileNum", reqEvent.getJuvenileNum());	
	    	HashSet<Date> hashAdjudicationEvents = new HashSet<Date>();

	    	//Calculation for CINSAdjudicationNumber
	    	HashSet<String> hashReferralNums = new HashSet<String>();
	    	
	    	while (iter.hasNext())
	    	{
	    		JJSReferral refferal = (JJSReferral) iter.next();
	    		if( refferal.getReferralNum() != null  && !refferal.getReferralNum().equals("") && refferal.getCourtDate() != null && !refferal.getCourtDate().equals("") ){
		    		if (refferal.getPIACode()!=null && refferal.getPIACode().equals("P")  && !hashAdjudicationEvents.contains(refferal.getCourtDate())) {
		    				hashAdjudicationEvents.add(refferal.getCourtDate());
	    					hashReferralNums.add(refferal.getReferralNum());
		    		}
	    		}
	    	}
	
	    	//Calculation for CINSAdjudicationNumber
	    	Iterator<String> iterReferralNum = hashReferralNums.iterator();
	    	int cinsAdjudicationCount = 0;
	    	GetJuvenileCasefileOffensesEvent off = new GetJuvenileCasefileOffensesEvent();    	
	    	
	    	while (iterReferralNum.hasNext()) {
	        	off.setJuvenileNum(reqEvent.getJuvenileNum());
	        	off.setReferralNum(iterReferralNum.next().toString());
	
	        	Iterator<JJSOffense> iOffenses = JJSOffense.findAll(off);        	
	    		
	        	while (iOffenses.hasNext()) {
		        	JJSOffense offense = (JJSOffense) iOffenses.next();
		        	JuvenileOffenseCode juvOffenseCode = offense.getOffenseCode();// ER Changes 11457.
		        	if(offense != null && juvOffenseCode!=null) {	// ER Changes 11457.
			        	if(offense.getCatagory() != null && !offense.getCatagory().equals("") && offense.getCatagory().equals("SO")) {
			        		if(juvOffenseCode.getSeverityType()!=null &&(!juvOffenseCode.getSeverityType().equals("3"))||!juvOffenseCode.getSeverityType().equals("")){ // ER Changes 11457. // ER changes 32203
			        			cinsAdjudicationCount++;
			        		}
			    		}
		        	}
	        	}
	    	}
	    	
			resp.setAdjudicationEvents(hashAdjudicationEvents.size()); 
			resp.setCINSAdjudicationNumber(cinsAdjudicationCount);
		}	
		return resp;
	}
}
