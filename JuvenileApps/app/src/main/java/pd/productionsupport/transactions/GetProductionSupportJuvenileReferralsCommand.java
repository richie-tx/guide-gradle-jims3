package pd.productionsupport.transactions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.productionsupport.GetProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;
import pd.security.PDSecurityHelper;

public class GetProductionSupportJuvenileReferralsCommand implements ICommand
{
    /**
     * @roseuid 4278CAAA00AA
     */
    public GetProductionSupportJuvenileReferralsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4278C7B80346
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetProductionSupportJuvenileReferralsEvent getJuvenileReferralsEvent = (GetProductionSupportJuvenileReferralsEvent) event;
	String OID 							     = getJuvenileReferralsEvent.getReferralOID();
	Iterator referralRespItr  				             = null;

	if ( StringUtils.isEmpty( OID ) ) {
	    GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
	    
	    refEvent.setJuvenileNum(getJuvenileReferralsEvent.getJuvenileId());
	    refEvent.setReferralNum(getJuvenileReferralsEvent.getReferralNum());
	    referralRespItr = JJSReferral.findAll(refEvent);
	    
	} else {
	    referralRespItr = JJSReferral.findAll("OID", OID);
	}
	
	
	

	
	while (referralRespItr.hasNext())
	{

	    ProductionSupportJuvenileReferralResponseEvent referralResp = new ProductionSupportJuvenileReferralResponseEvent();
	    JJSReferral referral = (JJSReferral) referralRespItr.next();
	    referralResp.setReferralOID(referral.getOID());
	    referralResp.setCourtId(referral.getCourtId());
	    referralResp.setReferralDate(DateUtil.dateToString(referral.getReferralDate(), DateUtil.DATE_FMT_1));
	    referralResp.setReferralSource(referral.getReferralSource());
	    referralResp.setCourtResult(referral.getCourtResultId());
	    referralResp.setCourtDisposition(referral.getCourtDispositionId());
	    referralResp.setReferralOfficer(referral.getReferralOfficer());
	    referralResp.setIntakeDecision(referral.getIntakeDecisionId());
	    referralResp.setIntakeDate(DateUtil.dateToString(referral.getIntakeDate(), DateUtil.DATE_FMT_1));
	    referralResp.setJuvenileNum(referral.getJuvenileNum());
	    referralResp.setViolationProbation(referral.getProbationViolation());
	    referralResp.setReferralNum(referral.getReferralNum());
	    referralResp.setCloseDate(DateUtil.dateToString(referral.getCloseDate(), DateUtil.DATE_FMT_1));
	    referralResp.setDaLogNum(referral.getDaLogNum());
	    referralResp.setTransNum(referral.getTransactionNum());
	    referralResp.setDispositionDate(DateUtil.dateToString(referral.getCourtDate(), DateUtil.DATE_FMT_1));
	    referralResp.setPiaStatus(referral.getPIACode());
	    referralResp.setLcuser( referral.getLcUser() );
	    referralResp.setLcdate(DateUtil.dateToString( referral.getLcDate(), DateUtil.DATE_FMT_1) + "/ ");
	    referralResp.setCtAssignJPOId(referral.getCtAssignJPOId());
	    referralResp.setReferralTypeInd(referral.getReferralTypeInd());
	    referralResp.setProbationStartDate(DateUtil.dateToString(referral.getProbationStartDate(),DateUtil.DATE_FMT_1));
	    referralResp.setProbationEndDate(DateUtil.dateToString(referral.getProbationEndDate(),DateUtil.DATE_FMT_1));
	    referralResp.setPdaDate(DateUtil.dateToString(referral.getPDADate(),DateUtil.DATE_FMT_1));
	    /// task 172857
	    referralResp.setCountyREFD(referral.getCountyREFD());
	    referralResp.setTJJDreferralDate(DateUtil.dateToString(referral.getTJJDReferralDate(), DateUtil.DATE_FMT_1));
	    //Bug #93012
	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
	    if(referral.getLcTime() != null)
		 {
			Calendar cal=Calendar.getInstance();
	        	cal.setTime(referral.getLcTime());	        	
	        	String time = dateFormat.format(cal.getTime());		  
		     referralResp.setLcTime(time);
		 }

	    GetJJSPetitionsEvent jjsevent = (GetJJSPetitionsEvent) new GetJJSPetitionsEvent();
	    	jjsevent.setJuvenileNum( referral.getJuvenileNum() );
		jjsevent.setReferralNum( referral.getReferralNum() );
		Iterator iter = JJSPetition.findAll( jjsevent );
	    if ( iter.hasNext() )
	    {
		JJSPetition jjspet = (JJSPetition) iter.next();
		referralResp.setOffenseCode( jjspet.getOffenseCodeId() );

	    }
	    else
	    {
		GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
		offEvent.setJuvenileNum(referral.getJuvenileNum());
		offEvent.setReferralNum(referral.getReferralNum());
		
		Iterator<JJSOffense> offenseIter = JJSOffense.findAll( offEvent );
		if (offenseIter.hasNext())
		{ //taking the first offense code 
		    JJSOffense offense = (JJSOffense) offenseIter.next();

		    referralResp.setOffenseCode( offense.getOffenseCodeId() );
		}
	    }
	    
	    referralResp.setJpoId( referral.getJpoid() );
	    referralResp.setOffenseTotal(  referral.getOffenseTotal() );
	    referralResp.setProbationJpoId( referral.getProbJPOId() );
	    referralResp.setDecisionType( referral.getDecisionType() );
	    
	    
	    

	    dispatch.postEvent(referralResp);
	}

    }

    /**
     * @param event
     * @roseuid 4278C7B8034F
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4278C7B80359
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4278C7B80364
     */
    public void update(Object anObject)
    {

    }
}
