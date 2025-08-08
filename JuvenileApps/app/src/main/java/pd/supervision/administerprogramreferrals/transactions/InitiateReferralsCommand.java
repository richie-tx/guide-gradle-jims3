/*
 * Created on Apr 1, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import messaging.administerprogramreferrals.InitiateReferralsEvent;
import messaging.administerprogramreferrals.SaveProgramReferralsEvent;
import messaging.administerprogramreferrals.reply.InitiateReferralsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerprogramreferrals.CSProgramReferral;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InitiateReferralsCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		InitiateReferralsEvent this_event = (InitiateReferralsEvent)event;
		
			//retrieve elements from initiate referrals event
		String defendant_id = this_event.getDefendantId();
		String criminal_case_id = this_event.getCriminalCaseId();
		List referral_types = this_event.getReferralTypes();
		
//		delete previously selected program referrals, if exist
		Collection deleteProgRefIdsList = this_event.getProgRefIdsToDeleteList();
		if(deleteProgRefIdsList!=null && deleteProgRefIdsList.size()>0)
		{
			Iterator deleteIter = deleteProgRefIdsList.iterator();
			while(deleteIter.hasNext())
			{
				String referralId = (String)deleteIter.next();
				CSProgramReferral delProgramReferral = CSProgramReferral.find(referralId);
				delProgramReferral.delete();
			}
		}
		
			//create referrals for each referral type
		Hashtable program_referral_map = new Hashtable();
		for (int i=0;i<referral_types.size();i++)
		{
				//set properties of referral
			SaveProgramReferralsEvent save_referral_event = new SaveProgramReferralsEvent();
			save_referral_event.setDefendantId(defendant_id);
			save_referral_event.setCriminalCaseId(criminal_case_id);
			save_referral_event.setReferralTypeCode((String)referral_types.get(i));
			save_referral_event.setProgramReferralId(this_event.getProgramReferralId());
			save_referral_event.setIncarcerationReferral( this_event.isIncarcerationReferral());
			
				//save new referrals
			CSProgramReferral saved_referral = 
							CSProgramReferralHelper.initiateProgramReferral(save_referral_event);

				//add referral ID and type code to referral map
			program_referral_map.put(saved_referral.getReferralTypeCode(),saved_referral.getOID());
			program_referral_map.put( "REFERRAL_DATE" ,saved_referral.getReferralDate() );
					
		}
		
			//retrieve service providers matching specified referral types
		List matching_sps = 
			CSServiceProviderHelper.getServiceProvidersForReferralTypes(referral_types);
		
			//convert matching service providers to necessary response events
		List sp_reftype_responses = 
				CSServiceProviderHelper.getServiceProviderReftypeResponses(matching_sps);
		
			//set properties of response event
		InitiateReferralsResponseEvent initiate_referrals_response = 
											new InitiateReferralsResponseEvent();
		initiate_referrals_response.setSavedReferralMap(program_referral_map);
		initiate_referrals_response.setServiceProviderRefTypeResponses(sp_reftype_responses);
		
			//post response
		MessageUtil.postReply(initiate_referrals_response);
	}//end of execute()

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
