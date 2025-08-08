package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.programreferral.JuvenileProgramReferral;
import messaging.administerserviceprovider.GetProgramServicesEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralsEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;

public class GetProgramServicesCommand implements ICommand
{
    public GetProgramServicesCommand(){}
    
    public void execute(IEvent event){
	
	GetProgramServicesEvent getProgramServicesEvent = (GetProgramServicesEvent)event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	String casefileId = getProgramServicesEvent.getCasefileId();
	RetrieveJuvenileProgramReferralsEvent getJuvProgramReferralEvent = new RetrieveJuvenileProgramReferralsEvent();
	getJuvProgramReferralEvent.setCasefileId(casefileId);
	ArrayList<JuvenileProgramReferral>juvenileProgramReferralList = ( ArrayList )CollectionUtil.iteratorToList(JuvenileProgramReferral.findAll(getJuvProgramReferralEvent));
	
	
	if ( juvenileProgramReferralList != null
			&& juvenileProgramReferralList.size() > 0 ) {
	    for (JuvenileProgramReferral programReferral : juvenileProgramReferralList ) {
		
		if ( !"2777".equals( programReferral.getProvProgramId() ) ) {
        		ProgramReferralResponseEvent programServicesResp = new ProgramReferralResponseEvent();
        		programServicesResp.setProvProgramId(programReferral.getProvProgramId());
        		programServicesResp.setBeginDate(programReferral.getBeginDate());
        		programServicesResp.setEndDate(programReferral.getEndDate());
        		if ( programReferral.getReferralStatusCd() != null
        			&& programReferral.getReferralStatusCd().length() > 0  ) {
        		    programServicesResp.setReferralStatusDescription(programReferral.getReferralStatus().getDescription());
        		}
        		if ( programReferral.getReferralSubStatusCd() != null 
        			&&  programReferral.getReferralSubStatusCd() .length() > 0  ) {
        		    programServicesResp.setReferralSubStatusDescription(programReferral.getReferralSubStatus().getDescription());
        		}
        		
        		
        		
        		if (programReferral.getProvProgramId() != null
        			&& programReferral.getProvProgramId().length() > 0) {
        		    ProviderProgram providerProgram = ProviderProgram.find(programReferral.getProvProgramId());
        		    if (providerProgram != null ) {
        			programServicesResp.setProviderProgramName(providerProgram.getProgramName());
        			JuvenileServiceProvider serviceProvider = JuvenileServiceProvider.find(providerProgram.getJuvenileServiceProviderId());
        			if ( serviceProvider != null ) {
        			    programServicesResp.setJuvServiceProviderName(serviceProvider.getServiceProviderName());
        			    programServicesResp.setJuvServiceProviderId(serviceProvider.getOID());
        			}
        			
        		    }
        		  }
        		dispatch.postEvent(programServicesResp);
		}
	    }
	}
    }

}
