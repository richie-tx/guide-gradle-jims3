// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\transactions\\GetServiceEventsByProgramReferralCommand.java

package pd.supervision.programreferral.transactions;

import java.util.Iterator;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.km.util.Name;
import pd.supervision.programreferral.JuvenileProgramReferral;

import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetProgramReferralsByServiceEventCommand implements ICommand {

	/**
	 * @roseuid 463BA52501D0
	 */
	public GetProgramReferralsByServiceEventCommand() {

	}

	/**
	 * @param event
	 * @roseuid 463A437A02A4
	 */
	public void execute(IEvent event) {
		
		Iterator iter = JuvenileProgramReferral.findAll(event);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		if (iter!=null){
			while (iter.hasNext()){
				JuvenileProgramReferral ref = (JuvenileProgramReferral)iter.next();
				ProgramReferralResponseEvent resp = new ProgramReferralResponseEvent();
				resp.setReferralId(ref.getReferralId());
				resp.setAcknowledgementDate(ref.getAcknowledgementDate());
				resp.setSentDate(ref.getAcknowledgementDate());
				if (ref.getJuvenileId()!=null && !"".equals(ref.getJuvenileId())){
				    	//// Profile stripping fix 97547
					//Juvenile juv = ref.getJuvenile();
					JuvenileCore juv = ref.getJuvenile();
					//
					//Defect Fix: JIMS200076767 starts.
					if(juv!=null){ //Dispatching the events only for juvenile non-sealed records(with data),hence juv details cannot be null.
						resp.setJuvenileFirstName(juv.getFirstName());
						resp.setJuvenileMiddleName(juv.getMiddleName());
						resp.setJuvenileLastName(juv.getLastName());
					}else{
						continue; //skip the records that are null(sealed records)
					}
					//Defect Fix: JIMS200076767 ends.
				}
				
				if( ref.getJuvenileId() != null && (ref.getJuvenileId().length() > 0) )
				{
					resp.setJuvenileId(ref.getJuvenileId()) ;
				}
				
				if (ref.getReferralStatusCd()!=null && !"".equals(ref.getReferralStatusCd())){
					resp.setReferralStatusCd(ref.getReferralStatusCd());
					resp.setReferralStatusDescription(ref.getReferralStatus().getDescription());
				}

				if (ref.getReferralSubStatusCd()!=null && !"".equals(ref.getReferralSubStatusCd())){
					resp.setReferralSubStatusCd(ref.getReferralSubStatusCd());
					resp.setReferralSubStatusDescription(ref.getReferralSubStatus().getDescription());
				}
				resp.setOfficerFullName("NOT AVAILABLE");
				if (ref.getReferralId() != null) {
					JuvenileProgramReferral juvProgRef = JuvenileProgramReferral.find(ref.getReferralId());
                    if (juvProgRef != null) {
                    	String casefileId = juvProgRef.getCasefileId();
                    	if (casefileId != null) {
                    		JuvenileCasefile casefile = JuvenileCasefile.find(casefileId);
                    		if (casefile != null) {
                    			resp.setOfficerFullName(casefile.getProbationOfficerFullName());
							}
						}
                    	
					}					
					
				}
				
				dispatch.postEvent(resp);
			}
		}

	}

	/**
	 * @param event
	 * @roseuid 463A437A02A6
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 463A437A02B2
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 463A437A02B4
	 */
	public void update(Object anObject) {

	}

}
