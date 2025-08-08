package pd.productionsupport.transactions;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.productionsupport.ListProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;

public class ListProductionSupportJuvenileReferralsCommand implements ICommand
{
    @Override
    public void execute(IEvent arg0) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	ListProductionSupportJuvenileReferralsEvent request = (ListProductionSupportJuvenileReferralsEvent) arg0;
	// TODO Auto-generated method stub
	Iterator<JJSReferral> allReferralsIter = JJSReferral.findAll("juvenileNum", request.getJuvenileId());

	while (allReferralsIter.hasNext())
	{
	    ProductionSupportJuvenileReferralResponseEvent respEvt = new ProductionSupportJuvenileReferralResponseEvent();

	    JJSReferral ref = (JJSReferral) allReferralsIter.next();
	    respEvt.setJuvenileNum(request.getJuvenileId());
	    respEvt.setReferralNum(ref.getReferralNum());
	    respEvt.setReferralOID( ref.getOID() );
	    respEvt.setReferralDate(DateUtil.dateToString(ref.getReferralDate(), DateUtil.DATE_FMT_1));
	    respEvt.setCloseDate(DateUtil.dateToString(ref.getCloseDate(), DateUtil.DATE_FMT_1));
	    respEvt.setIntakeDecision(ref.getIntakeDecisionId());
	    respEvt.setIntakeDate(DateUtil.dateToString(ref.getIntakeDate(), DateUtil.DATE_FMT_1));
	    respEvt.setDispositionDate(DateUtil.dateToString(ref.getDispositionDate(), DateUtil.DATE_FMT_1));
	    respEvt.setCourtResult(ref.getCourtResultId());
	    respEvt.setRectype(ref.getRecType());
	    
	    GetJJSPetitionsEvent petEvent = new GetJJSPetitionsEvent();
	    petEvent.setJuvenileNum(request.getJuvenileId());
	    petEvent.setReferralNum(respEvt.getReferralNum());
	    petEvent.setPetitionNum(null);

	    Iterator iter = JJSPetition.findAll(petEvent);
	    List petitionList = CollectionUtil.iteratorToList( iter );
	    Collections.sort((List<JJSPetition>)petitionList,Collections.reverseOrder(new Comparator<JJSPetition>() {
		@Override
		public int compare(JJSPetition evt1, JJSPetition evt2) {
			return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
		}
	    	}));
	    
	    if (petitionList.size() >0 )
	    {
		JJSPetition petition = (JJSPetition) petitionList.get(0);
		respEvt.setOffenseCode(petition.getOffenseCode().getCode());
	    }else{
		// replace with Juvenile Offense code
		GetJuvenileCasefileOffensesEvent getOffenses = new GetJuvenileCasefileOffensesEvent();
		getOffenses.setJuvenileNum( request.getJuvenileId());
		getOffenses.setReferralNum( respEvt.getReferralNum());
		
		Iterator i = JJSOffense.findAll( getOffenses );	
		 List offenseList = CollectionUtil.iteratorToList( i );
		    Collections.sort((List<JJSOffense>)petitionList,Collections.reverseOrder(new Comparator<JJSOffense>() {
			@Override
			public int compare(JJSOffense evt1, JJSOffense evt2) {
				return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
			}
		    	}));
		    
		if( offenseList.size() >0 ){
		    JJSOffense offense = (JJSOffense) offenseList.get(0);
		    respEvt.setOffenseCode(offense.getOffenseCodeId());
		}
	    }

	    dispatch.postEvent(respEvt);
	}

    }

}
