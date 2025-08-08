//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveTransferredOffenseReferralsCommand.java
package pd.juvenilecase.referral.transactions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import naming.JuvenileCaseControllerServiceNames;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JCVOP;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.security.SecurityUIHelper;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetCasefileAssignmentHistoryEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryViewResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetVOPDetailsEvent;
import messaging.referral.SaveVOPDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;


/**
 * @author nmathew
 */
public class SaveVOPDetailsCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	SaveVOPDetailsEvent saveEvent = (SaveVOPDetailsEvent) event;
	//IHome home = new Home();

	JCVOP vop = new JCVOP();
	vop.setJuvenileNumber(saveEvent.getJuvenileNum());
	vop.setReferralNumber(saveEvent.getReferralNum());
	vop.setInCCountyOrigPetitionedRefNum(saveEvent.getInCCountyOrigPetitionedRefNum());
	//BUG 163195 changes starts
	if (saveEvent.getAdultIndicatorStr() != null
		&& !saveEvent.getAdultIndicatorStr().isEmpty())
	{
	    vop.setAdultIndicatorStr(saveEvent.getAdultIndicatorStr());
	}
	else
	{
	    vop.setAdultIndicatorStr(null);
	} //BUG 163195 changes ends
	vop.setAdultIndicator(saveEvent.getAdultIndicator());
	vop.setOffenseCharge(saveEvent.getOffenseCharge());
	vop.setOffenseChargeDate(saveEvent.getOffenseChargeDate());
	vop.setLocationIndicator(saveEvent.getLocationIndicator());
	vop.setReferralDate(saveEvent.getReferralDate());
	vop.setVOPOffenseCode(saveEvent.getVopOffenseCode());
	IHome home = new Home();
	home.bind(vop);

	if (saveEvent.getCasefileIds() != null && saveEvent.getCasefileIds().size() > 0)
	{
	    for (int i = 0; i < saveEvent.getCasefileIds().size(); i++)
	    {
		if (!vopEntryNeeded(saveEvent.getJuvenileNum(), saveEvent.getCasefileIds().get(i)))
		{
		    JuvenileCasefile casefile = JuvenileCasefile.find(saveEvent.getCasefileIds().get(i));
		    if (casefile != null)
		    {
			casefile.setVop(false);
			IHome home1 = new Home();
			home1.bind(casefile);
		    }
		}
	    }
	}

	//US Changes STARTS 187922 
	//Create a Record for a JJS_MS_REFERRAL and a JJS_MS_OFFENSE tables for JCVOP table entry with a chargeoffense=�ATTSCH�, 

	if (saveEvent.getOffenseCharge() != null && saveEvent.getOffenseCharge().equalsIgnoreCase("ATTSCH"))
	{
	    JJSReferral ref = new JJSReferral();
	    ref.setActionDate(new Timestamp(DateUtil.getCurrentDate().getTime())); //ref.setActionDate(DateUtil.stringToDate(vop.getCreateDate(), DateUtil.DATE_FMT_1));
	    ref.setActionOperator(vop.getCreateUserID());
	    ref.setAssignDate(vop.getReferralDate());
	    ref.setCirDate(vop.getReferralDate());
	    ref.setCloseDate(vop.getReferralDate());
	    ref.setJpoid("ANC");//INASSIGNJPOID
	    ref.setLevel("S");
	    ref.setUnit("1");
	    ref.setIntakeDate(vop.getReferralDate());
	    ref.setIntakeDecisionId("REC");
	    ref.setJuvenileNum(vop.getJuvenileNumber());
	    ref.setReferralDate(vop.getReferralDate());
	    ref.setReferralSevTotal("3");
	    ref.setReferralSource("88");
	    ref.setReferralTypeInd("PA"); ref.setTjpcSeqNum("1");
	    ref.setTotalPetitions("0");
	    ref.setProbationViolation("N");//VIOLATIONPROBATION
	    ref.setSequenceNum("1");
	    ref.setRefExcludedReporting(1);
	    ref.setCountyREFD("101");
	    ref.setLcDate(new Timestamp(DateUtil.getCurrentDate().getTime()));
	    ref.setLcUser(vop.getCreateUserID());
	    ref.setLcTime(Calendar.getInstance().getTime());
	    ref.setRecType("S.REFERRAL"); //BUG 189992
	    //get all active referrals for the Juvenile						
	    Iterator<JJSReferral> refIter = JJSReferral.findAll("juvenileNum", vop.getJuvenileNumber());
	    ArrayList<JJSReferral> refNums = new ArrayList<JJSReferral>();
	    while (refIter.hasNext())
	    {
		JJSReferral aRefferal = refIter.next();
		refNums.add(aRefferal);

	    }
	    Collections.sort((List<JJSReferral>) refNums, Collections.reverseOrder(new Comparator<JJSReferral>() {
		@Override
		public int compare(JJSReferral evt1, JJSReferral evt2)
		{
		    return Integer.valueOf(evt1.getReferralNum()).compareTo(Integer.valueOf(evt2.getReferralNum()));
		}
	    }));
	    if (refNums.iterator().hasNext())
	    {
		JJSReferral resp = (JJSReferral) refNums.iterator().next();
		int refNum = 0;
		if (Integer.valueOf(resp.getReferralNum()) % 10 == 0)
		{
		    refNum = (Integer.valueOf(resp.getReferralNum())) + 10;

		}
		else
		{
		    StringBuffer sb = new StringBuffer(resp.getReferralNum().substring(0, 3)).append(0);
		    refNum = (Integer.valueOf(sb.toString())) + 10;
		}

		ref.setReferralNum(String.valueOf(refNum));
	    }
	    else
	    {
		ref.setReferralNum("1010");
	    }
	
	    home.bind(ref);//ENTRY INTO JJS_MS_REFERRAL table

	    JJSOffense offense = new JJSOffense();
	    offense.setChargeSequenceNum("1");
	    offense.setJuvenileNum(saveEvent.getJuvenileNum());
	    offense.setLcDate(DateUtil.getCurrentDate());
	    offense.setLcUser(PDSecurityHelper.getLogonId());
	    offense.setOffenseCodeId(vop.getOffenseCharge());
	    offense.setOffenseDate(vop.getOffenseChargeDate());
	    offense.setSeverity("3");
	    offense.setReferralNum(ref.getReferralNum());
	    offense.setSequenceNum("1");
	    offense.setTjpcseqnum("1");
	    offense.setCreateUserID(SecurityUIHelper.getJIMSLogonId());
	    offense.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
	    offense.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
	    offense.setLcTime(Calendar.getInstance().getTime());
	    home = new Home();
	    home.bind(offense);

	}
	//US Changes ENDS 187922

    }
	/**
	 * @param event
	 * @roseuid 4306266C0232
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266C0234
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4306266C023F
	 */
	public void update(Object anObject)
	{

	}
	
	private boolean vopEntryNeeded(String juvNum, String casefileId)
	{
	    boolean vopNeeded = false;
	    GetCasefileAssignmentHistoryEvent jpoAssignmentHistoriesEvent = (GetCasefileAssignmentHistoryEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEASSIGNMENTHISTORY);
	    jpoAssignmentHistoriesEvent.setCasefileId(casefileId);
	    List<JPOAssignmentHistoryViewResponseEvent>assignments = new ArrayList<>();
	    assignments = MessageUtil.postRequestListFilter(jpoAssignmentHistoriesEvent, JPOAssignmentHistoryViewResponseEvent.class );
	    if ( assignments != null
		    && assignments.size() > 0 ) {
		
		//for ( JPOAssignmentHistoryViewResponseEvent assignment : assignments ){
		Iterator assignmentIter = assignments.iterator();
		while ( assignmentIter.hasNext() ) {
		    JPOAssignmentHistoryViewResponseEvent assignment = (JPOAssignmentHistoryViewResponseEvent)assignmentIter.next();
		    GetVOPDetailsEvent getVopEvent = new GetVOPDetailsEvent();
		    getVopEvent.setJuvenileNumber( juvNum );
		    getVopEvent.setReferralNumber(  assignment.getReferralNumber()  );
		    Iterator<JCVOP> vopReferralsItr  = JCVOP.findAll(getVopEvent);
		    List<JCVOP> vopResponse  = new ArrayList<>();
		    while ( vopReferralsItr.hasNext() ){
			JCVOP vop = (JCVOP) vopReferralsItr.next();
			vopResponse.add(vop);
		    }
	
		    	if (vopResponse.size() == 0) {
		    	    GetJuvenileCasefileOffensesEvent getOffenses = new GetJuvenileCasefileOffensesEvent();
		    	    getOffenses.setJuvenileNum(juvNum);
		    	    getOffenses.setReferralNum(assignment.getReferralNumber());
		    	    List<JJSOffenseResponseEvent> offenses = new ArrayList<>();
		    	    offenses = MessageUtil.postRequestListFilter(getOffenses, JJSOffenseResponseEvent.class);
        		    if ( offenses != null
        			    && offenses.size() > 0 ) {
        			for ( JJSOffenseResponseEvent offense : offenses ) {
        			    JuvenileOffenseCodeResponseEvent offenseCode = JuvenileReferralHelper.getOffenseCd(offense.getOffenseCodeId());
        			    String numericCode = offenseCode.getNumericCode();
        			    if (numericCode != null && numericCode.equalsIgnoreCase("23")) {
        				vopNeeded = true;
        				break;
        			    }
        			}
        		    }
		    	}
		}
	    }
	    return vopNeeded;
	}
   
}
