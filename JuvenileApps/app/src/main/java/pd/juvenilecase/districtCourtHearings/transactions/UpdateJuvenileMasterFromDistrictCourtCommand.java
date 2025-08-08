package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import messaging.districtCourtHearings.UpdateJuvenileMasterFromDistrictCourtEvent;
import messaging.juvenilecase.GetAllCasefilesForReferralsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.SupervisionTypeTJJDMap;
import pd.juvenilecase.referral.JJSReferral;

/**
 * @author sthyagarajan
 * 3.4.6.17	Activity: Record Updated Juvenile Master record
 */
public class UpdateJuvenileMasterFromDistrictCourtCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	boolean updateFlag=false;
	IHome home = new Home(); 
	UpdateJuvenileMasterFromDistrictCourtEvent juvenileMasterEvt = (UpdateJuvenileMasterFromDistrictCourtEvent) event;
	

	JJSJuvenile juvenile = JJSJuvenile.find(juvenileMasterEvt.getJuvenileNumber());
	if (juvenile != null)
	{
	    /**
	     * If setting�s disposition has been changed to �PRO� and user has changed the retrieved Action Date to a different date,
	     *  set the juvenile�s master status to �A� (NOTE: Juvenile has come back from AWOL status and setting has been reset). 
	     */
	    if (juvenileMasterEvt.getDisposition()!=null && juvenileMasterEvt.getDisposition().equalsIgnoreCase("PRO") && juvenileMasterEvt.getActionDate()!=null && !juvenileMasterEvt.getActionDate().equalsIgnoreCase(juvenileMasterEvt.getOriginalActionDate()))
	    {
		if (juvenile != null)
		{
		    juvenile.setOldStatusId(juvenile.getStatusId());
		    juvenile.setStatusId("A");
		    updateFlag=true;
		}
	    }
	    
	    /*If setting�s disposition has been changed to �PRO�; 
	     * retrieved ActionDate has been not been changed; and, the juvenile�s current master status is not �H�, 
	     * change master�s �JUVENILE_MASTER.OldMasterStatus� to the master�s current status. Then, set juvenile�s master status to �H� 
	     * (NOTE: Juvenile is AWOL; if juvenile returns do not release).  NOTE: Basicallly, method to track changes to the juvenile�s master status.  From: C002.FD.JUVENILE.REC
	     * */
	    if (juvenileMasterEvt.getDisposition()!=null && juvenileMasterEvt.getDisposition().equalsIgnoreCase("PRO") && juvenileMasterEvt.getActionDate()!=null && juvenileMasterEvt.getActionDate().equalsIgnoreCase(juvenileMasterEvt.getOriginalActionDate()))
	    {
		if (juvenile != null &&juvenile.getStatusId()!=null && !juvenile.getStatusId().equalsIgnoreCase("H"))
		{
		    juvenile.setOldStatusId(juvenile.getStatusId());
		    juvenile.setStatusId("H");
		    updateFlag=true;
		}
	    }
	    /**
	     * [Bug 81721] If the setting�s original disposition was �PRO� but the disposition has been changed or erased, and if the Disposition category = �P� or �C,�
	     *  set the master's current status to  �JUVENILE_MASTER.OldMasterStatus�.  Then, change the juvenile�s master status to �P.� From: C002.FD.JUVENILE.REC
	     */
	    if (juvenileMasterEvt.getOriginalDisposition() != null && juvenileMasterEvt.getOriginalDisposition().equalsIgnoreCase("PRO") && !juvenileMasterEvt.getOriginalDisposition().equalsIgnoreCase(juvenileMasterEvt.getDisposition()))
	    {
		if (juvenileMasterEvt.getDisposition() != null && !juvenileMasterEvt.getDisposition().isEmpty())
		{
		    JuvenileDispositionCode juvDispCode = JuvenileDispositionCode.find("codeAlpha",juvenileMasterEvt.getDisposition());

		    if (juvDispCode != null && juvDispCode.getInactiveInd() != null && !juvDispCode.getInactiveInd().equalsIgnoreCase("Y"))
		    {
			if (juvDispCode.getCategoryCode()!=null && juvDispCode.getCategoryCode().equalsIgnoreCase("P") || juvDispCode.getCategoryCode().equalsIgnoreCase("C"))
			{
			    juvenile.setOldStatusId(juvenile.getStatusId());
			    juvenile.setStatusId("P");
			    updateFlag=true;
			}
		    }
		}
	    }
	    /**
	     * If the setting�s original disposition was �PRO� but has been disposition changed to �VAC�, set the juvenile�s master status to �A�.  
	     */
	    if (juvenileMasterEvt.getOriginalDisposition() != null && juvenileMasterEvt.getOriginalDisposition().equalsIgnoreCase("PRO") && juvenileMasterEvt.getDisposition().equalsIgnoreCase("VAC"))
	    {
		juvenile.setOldStatusId(juvenile.getStatusId());
		juvenile.setStatusId("A");
		updateFlag=true;
	    }
	    /**
	     * Master status= �A� if referrals are open (no closed date on any) and none of the following conditions apply
		Master status= �C� if all referrals are closed
		Master status= �I� if most recent (highest referral number) opened referral has a court decision (jjs90.20) subgroup = �I�
		Master status= �P� if most recent (highest referral number) opened referral has a court decision (jjs90.2) subgroup = �E� or �F�
		Master status= �H� if any open referrals are assigned to Indirect supervision type {supervision type has TJJD Code = INDR on the Supervision Type TJJD Map code table}.

	     */
	    Iterator<JJSReferral> refIter = JJSReferral.findAll("juvenileNum", juvenileMasterEvt.getJuvenileNumber());
	    JJSReferral ref = null;
	    ArrayList<JJSReferral> openReferrals = new ArrayList<JJSReferral>();
	    ArrayList<JJSReferral> closedReferrals = new ArrayList<JJSReferral>();
	    while (refIter.hasNext())
	    {
		ref = refIter.next();
		if (ref != null)
		{
		    if (ref.getCloseDate() == null)
		    {
			openReferrals.add(ref);
			
			GetAllCasefilesForReferralsEvent referralWithCasefiles = new GetAllCasefilesForReferralsEvent();
			referralWithCasefiles.setJuvenileNum(juvenileMasterEvt.getJuvenileNumber());
			referralWithCasefiles.setReferralNum(ref.getReferralNum());
			Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(referralWithCasefiles);
			while (iter.hasNext())
			{
			    JuvenileCasefileReferral juvenileCasefile = (JuvenileCasefileReferral) iter.next();
			    if (juvenileCasefile != null)
			    {
				String supID = juvenileCasefile.getSupervisionTypeCd();
				// call the codetable.
				Iterator<SupervisionTypeTJJDMap> stpItr = SupervisionTypeTJJDMap.findAll("supervisionTypeId", supID);
				while (stpItr.hasNext())
				{
				    SupervisionTypeTJJDMap supervisionType = stpItr.next();
				    if (supervisionType != null)
				    {
					String supervisonTypeId = supervisionType.getSupervisionTypeId();
					if (supID!=null && supID.equalsIgnoreCase(supervisonTypeId))
					{
					    /**
					     * Master status= �H� if any open referrals are assigned to Indirect supervision type {supervision type has TJJD Code = INDR on the Supervision Type TJJD Map code table}.
					     */
					    if (supervisionType.getSupTJJDTypeId()!=null && supervisionType.getSupTJJDTypeId().equalsIgnoreCase("INDR"))
					    {
						juvenile.setOldStatusId(juvenile.getStatusId());
						juvenile.setStatusId("H");
						updateFlag=true;
						break;
					    }
					}
				    }
				}//(2) while
			    }
			    if(juvenile.getStatusId()!=null && juvenile.getStatusId().equals("H")){
				break;
			    }
			}
		    }else{
			closedReferrals.add(ref);
		    }
		}
	    }
	    /**
	     * Master status= �A� if referrals are open (no closed date on any) and none of the following conditions apply
	     */
	    if (closedReferrals!=null && closedReferrals.isEmpty()&& openReferrals!=null && !openReferrals.isEmpty())
	    {
		juvenile.setOldStatusId(juvenile.getStatusId());
		juvenile.setStatusId("A");
		updateFlag=true;
	    }
	    /**
	     * Master status= �C� if all referrals are closed
	     */
	    if (openReferrals!=null && openReferrals.isEmpty()&& closedReferrals!=null && !closedReferrals.isEmpty())
	    {
		juvenile.setOldStatusId(juvenile.getStatusId());
		juvenile.setStatusId("C");
		updateFlag=true;
	    }
	    
	    if (openReferrals != null && !openReferrals.isEmpty())
	    {
		Collections.sort(openReferrals, Collections.reverseOrder(new Comparator<JJSReferral>() {
		    @Override
		    public int compare(JJSReferral evt1, JJSReferral evt2)
		    {
			if (evt1.getReferralNum() != null && evt2.getReferralNum() != null)
			    return Integer.valueOf(evt1.getReferralNum()).compareTo(Integer.valueOf(evt2.getReferralNum()));
			else
			    return -1;
		    }
		}));

		Iterator<JJSReferral> referralIter = openReferrals.iterator();
		JJSReferral openReferral = null;

		while (referralIter.hasNext())
		{
		    openReferral = referralIter.next();
		    if (openReferral != null && openReferral.getCourtResult()!=null)
		    {
			JuvenileDispositionCode juvDispCode = JuvenileDispositionCode.find("codeAlpha",openReferral.getCourtResult().getCodeAlpha());

			if (juvDispCode != null && juvDispCode.getInactiveInd() != null && !juvDispCode.getInactiveInd().equalsIgnoreCase("Y"))
			{
			    /**
			     * Master status= �I� if most recent (highest referral number) opened referral has a court decision (jjs90.20) subgroup = �I�
			     */
			    if (juvDispCode.getSubGroupInd() != null && juvDispCode.getSubGroupInd().equalsIgnoreCase("I"))
			    {
				juvenile.setOldStatusId(juvenile.getStatusId());
				juvenile.setStatusId("I");
				updateFlag=true;
			    }
			    /**
			     * Master status= �P� if most recent (highest referral number) opened referral has a court decision (jjs90.2) subgroup = �E� or �F�
			     */
			    if (juvDispCode.getSubGroupInd() != null && (juvDispCode.getSubGroupInd().equalsIgnoreCase("E") || juvDispCode.getSubGroupInd().equalsIgnoreCase("F")))
			    {
				juvenile.setOldStatusId(juvenile.getStatusId());
				juvenile.setStatusId("P");
				updateFlag=true;
			    }
			}
		    }
		    break;
		}
	    }
	    if(updateFlag){
		home.bind(juvenile);
	    }
	}
    }
}
