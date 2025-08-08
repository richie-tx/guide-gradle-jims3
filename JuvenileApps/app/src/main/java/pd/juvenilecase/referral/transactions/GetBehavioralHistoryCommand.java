// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\referral\\transactions\\GetBehavioralHistoryCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import messaging.referral.GetBehavioralHistoryEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.reply.JuvenileBehaviorHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;

public class GetBehavioralHistoryCommand implements ICommand
{
	static final long SECONDS_IN_A_DAY = (24 * 60 * 60) ;
	
	/**
	 * @roseuid 453E37640197
	 */
	public GetBehavioralHistoryCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 453E2E2003BC
	 */
	@SuppressWarnings("static-access")
	public void execute( IEvent event )
	{
		GetBehavioralHistoryEvent reqEvent = (GetBehavioralHistoryEvent)event;

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY );

		JuvenileBehaviorHistoryResponseEvent resp = this.getBehaviorHistory( reqEvent );
		dispatch.postEvent( resp );
	}

	/**
	 * @param event
	 * @roseuid 453E2E2003BE
	 */
	public void onRegister( IEvent event )
	{
	}

	/**
	 * @param event
	 * @roseuid 453E2E2003C0
	 */
	public void onUnregister( IEvent event )
	{
	}

	/**
	 * @param anObject
	 * @roseuid 453E2E2003CC
	 */
	public void update( Object anObject )
	{
	}

	public static JuvenileBehaviorHistoryResponseEvent getBehaviorHistory( GetBehavioralHistoryEvent reqEvent )
	{
		/* 09june2009 - mjt - if you need to alter the functionality 
		 * of this function, be sure you visit getDelinquencyHistory
		 * found in CheckReferralPreconditionsCommand.java, because 
		 * it has much of the same functionality there. 
		 */
		JuvenileBehaviorHistoryResponseEvent resp = new JuvenileBehaviorHistoryResponseEvent();
		String juvNumber = reqEvent.getJuvenileNum() ;

		JJSJuvenile juvenile = JJSJuvenile.find( juvNumber );
		if( juvenile != null )
		{
			HashSet hashAdjudicationEvents = new HashSet();
			SortedSet setReferralDate = new TreeSet();
			HashSet hashDeferredAdjudicationEvents = new HashSet();
			HashSet hashTYCCommitments = new HashSet();

			// Calculation for Referral History
			HashSet hashReferralNums = new HashSet();
			HashSet hashReferrals = new HashSet();
			Date courtDate ;
			
			for( Iterator<JJSReferral> iter = JJSReferral.findAll( "juvenileNum", juvNumber );
					iter.hasNext(); /*empty*/ )
			{
				JJSReferral aRefferal = iter.next();
				
				if( StringUtils.isNotEmpty( aRefferal.getReferralNum() ) )
				{
					hashReferralNums.add( aRefferal.getReferralNum() );
					hashReferrals.add(aRefferal);
				}

				if( (courtDate = aRefferal.getCourtDate() ) != null )
				{
					//if( aRefferal.getPIACode()!= null && aRefferal.getPIACode().equals( "P" ) )
				    	if( aRefferal.getCourtResult() != null
				    		&& "99".equals( aRefferal.getCourtResult().getCodeNum() ) )
					{
				    	    if ( !hashAdjudicationEvents.contains( courtDate ) ) {
				    		hashAdjudicationEvents.add( courtDate );
				    	    }
					}
					else if( aRefferal.getPIACode()!= null 
							&& aRefferal.getPIACode().equals( "I" ) )
					{
						hashDeferredAdjudicationEvents.add( courtDate );
					}

					if( aRefferal.getCourtDecisionSubgroupIndicator()!=null 
							&& (aRefferal.getCourtDecisionSubgroupIndicator().equals( "B" )
								|| aRefferal.getCourtDecisionSubgroupIndicator().equals( "D" ) ) )
					{
					    if ( !hashTYCCommitments.contains( courtDate ) ){
						hashTYCCommitments.add( courtDate );
					    }
					}
				}
				
				if( aRefferal.getReferralDate() != null )
				{
					setReferralDate.add( aRefferal.getReferralDate() );
				}
			}
			

			// Calculation for ageFirstReferred
			double age = 0.0 ;
			{ Date dob = juvenile.getBirthDate();
				if( dob != null  &&  !setReferralDate.isEmpty() )
				{
					long juvDob = dob.getTime();
					/* Calculation for ageFirstReferred: get the
					 * very first referral and subtract out their DOB
					 * to get their age at the time of the first referral
					 */
					long timeOfReferralDate = (((Date)setReferralDate.first()).getTime());
					long intAge = ((timeOfReferralDate - (juvDob)) / (SECONDS_IN_A_DAY * 1000)) / 365;
					age = intAge ;
				}
			}

			// Offense data
			int countFelony1 = 0;
			int countFelony2 = 0;
			int countFelony3 = 0;
			int countFelonyCapital = 0;
			int countFelonyStateJail = 0;
			int countNumberOfRunaways = 0;
			int countTotalCityOrd = 0;
			int countTotalStatus = 0;
			int countTotalMisdemeanorA = 0;
			int countTotalMisdemeanorB = 0;
			int countTotalMisdemeanorC = 0;
			int countTotalOffenses = 0;
			int countViolationOfProbations = 0;
			String offenseCat = UIConstants.EMPTY_STRING ;
			
			 Iterator<JJSOffense> offenseIter = JJSOffense.findAll( "juvenileNum", juvNumber );
			 List<JJSOffense>offenses = new ArrayList<JJSOffense>();
			 List<String> referrals = new ArrayList<String>();
			 
			 //If there are duplicate referrals, get the referral with highest sequence
			 while ( offenseIter.hasNext() ){
			     JJSOffense offense = offenseIter.next();
			     if ( !referrals.contains(offense.getReferralNum())){
				 referrals.add(offense.getReferralNum());
				 offenses.add(offense);
			     } else {
				 for ( JJSOffense off : offenses ) {
				     if (off.getReferralNum().equals(offense.getReferralNum())
					     && Integer.parseInt(off.getSequenceNum()) < Integer.parseInt(offense.getSequenceNum()) ){
					 offenses.remove(off);
					 offenses.add(offense);
					 break;
					 
				     }
				 }
				 
				 
			     }
			     
			     
			 }

			for( JJSOffense offense : offenses)
			{
			    //System.out.println("Referral number: " + offense.getReferralNum() );
			    //System.out.println("Offense code: " + offense.getOffenseCodeId());
			   // System.out.println("Sequence number:" + offense.getSequenceNum());
			   // System.out.println("Numeric code: " + offense.getOffenseNumericCode());
				offenseCat = offense.getOffenseCategory() ;
				if (Integer.parseInt(offense.getSeverity()) > 3) {
				    countTotalOffenses++ ;
				}
				
				if( offenseCat.equals( "F1" ) )
				{
					countFelony1++ ;
				}
				else if( offenseCat.equals( "F2" ) )
				{
					countFelony2++ ;
				}
				else if( offenseCat.equals( "F3" ) )
				{
					countFelony3++ ;
				}
				else if( offenseCat.equals( "CF" ) )
				{
					countFelonyCapital++ ;
				}
				else if( offenseCat.equals( "JF" ) )
				{
					countFelonyStateJail++ ;
				}
				else if( offenseCat.equals( "CO" ) )
				{
					countTotalCityOrd++ ;
				}
				else if( offenseCat.equals( "SO" ) )
				{
					countTotalStatus++ ;
				}
				else if(offenseCat.equals( "MA" ) )
				{
					countTotalMisdemeanorA++ ;
				}
				else if( offenseCat.equals( "MB" ) )
				{
					countTotalMisdemeanorB++ ;
				}
				else if( offenseCat.equals( "MC" ) )
				{
					countTotalMisdemeanorC++ ;
				}
				
				//change made from 17 to 20 for Bug 30291
				if( offense.getOffenseNumericCode().equals("23") )
				{
					countViolationOfProbations++;
				}
				else if( offense.getOffenseNumericCode().equals("24")
					|| offense.getOffenseNumericCode().equals("26"))
				{
					countNumberOfRunaways++;
				}
			}

			// Calculation for SeriousnessIndex
			double offenseSeverityTypeTotal = 0.0;
			double offenseSeverityTotal = 0.0;
			double offenseCount = 0;
			double offenseSeverityTypeCount = 0;
			//int totalReferralNonAdmin	= 0;
			int diversionTotal		= 0;
			SortedSet referralNonAdminSet 	= new TreeSet();
			SortedSet referralByDateNonAdminSet 	= new TreeSet();
			
			GetJuvenileCasefileOffensesEvent off = new GetJuvenileCasefileOffensesEvent();

			for( Iterator<JJSReferral> iterReferral= hashReferrals.iterator();
				iterReferral.hasNext(); /*empty*/ )
			{
			    	JJSReferral referral = iterReferral.next();
				off.setJuvenileNum( juvNumber );
				off.setReferralNum( referral.getReferralNum() );
				for( Iterator<JJSOffense> iOffenses = JJSOffense.findAll( off );
						iOffenses.hasNext(); /*empty*/ )
				{
					JJSOffense offenseSeverity = iOffenses.next();
					// <Kishore>JIMS200057032 : MJCW - Number Format Exception on Briefing Page
					if( StringUtils.isNotEmpty( offenseSeverity.getSeverity()))   
					{
						offenseSeverityTotal += Double.parseDouble( offenseSeverity.getSeverity() );
						if (!offenseSeverity.getSeverity().equals("0")) {
							offenseCount++ ;							
						}
					}
					JuvenileOffenseCode juvOffenseCode = offenseSeverity.getOffenseCode();
					if( StringUtils.isNotEmpty( juvOffenseCode.getSeverityType()) && Double.parseDouble( juvOffenseCode.getSeverityType()) <= 13)   
					{
						offenseSeverityTypeTotal += Double.parseDouble( juvOffenseCode.getSeverityType());
						if (!juvOffenseCode.getSeverityType().equals("0")) {
							offenseSeverityTypeCount++ ;							
						}
					}
					
					if (!"AC".equals(offenseSeverity.getCatagory()) ) {
					    referralNonAdminSet.add(referral.getReferralNum());
					    referralByDateNonAdminSet.add(referral.getReferralDate());
					}
				}
			}

			double seriousnessIndex = 0.0;

			if( offenseSeverityTotal != 0.0 && offenseCount != 0.0 )
			{
				seriousnessIndex = offenseSeverityTotal / offenseCount;
			}

			seriousnessIndex = Math.round( seriousnessIndex * 10.0 ) / 10.0;
			
			//calculate severity index
			double severityIndex = 0.0;

			if( offenseSeverityTypeTotal != 0.0 && offenseSeverityTypeCount != 0.0 )
			{
				severityIndex = offenseSeverityTypeTotal / offenseSeverityTypeCount;
			}

			severityIndex = Math.round( severityIndex * 10.0 ) / 10.0;
			
			//calculate diversion events
			Iterator<JuvenileCasefile>casefileIter = JuvenileCasefile.findAll("juvenileId", juvNumber );
			while(casefileIter.hasNext()) {
			    JuvenileCasefile casefile = casefileIter.next();
			    String specialtyCatId = casefile.getSpecialityCategoryId();
			    //System.out.println("specialtyCatId" + specialtyCatId);
			    if ("DP".equals(specialtyCatId)){
				diversionTotal++;
			    }
			}

			resp.setAgeFirstReferred( Integer.toString( (int)age ) );
			resp.setDeferredAdjudicationEvents( Integer.toString( hashDeferredAdjudicationEvents.size() ) );
			resp.setAdjudicationEvents( Integer.toString( hashAdjudicationEvents.size() ) );
			resp.setTYCCommitments( Integer.toString( hashTYCCommitments.size() ) );
			resp.setSeriousnessIndex( Double.toString( seriousnessIndex ) );
			resp.setSeverityIndex( Double.toString( severityIndex ) );
			resp.setReferralByDateNonAdminEvents( Integer.toString( referralByDateNonAdminSet.size() )  );
			resp.setReferralEvents( Integer.toString( setReferralDate.size() ) );
			resp.setTotalOffenses( Integer.toString( countTotalOffenses ) );
			resp.setFelonyCapital( Integer.toString( countFelonyCapital ) );
			resp.setMisdemeanorA( Integer.toString( countTotalMisdemeanorA ) );
			resp.setFelony1( Integer.toString( countFelony1 ) );
			resp.setMisdemeanorB( Integer.toString( countTotalMisdemeanorB ) );
			resp.setFelony2( Integer.toString( countFelony2 ) );
			resp.setMisdemeanorC( Integer.toString( countTotalMisdemeanorC ) );
			resp.setFelony3( Integer.toString( countFelony3 ) );
			resp.setCityOrdinance( Integer.toString( countTotalCityOrd ) );
			resp.setTotalStatus( Integer.toString( countTotalStatus ) );
			resp.setFelonyStateJail( Integer.toString( countFelonyStateJail ) );
			resp.setRunaways( Integer.toString( countNumberOfRunaways ) );
			resp.setViolationsOfProbation( Integer.toString( countViolationOfProbations ) );
			resp.setReferralNums( hashReferralNums );
			resp.setTotalReferralNonAdmin(Integer.toString(referralNonAdminSet.size()));
			resp.setDiversionEvents(Integer.toString(diversionTotal));
			
			
		}

		return resp;
	}
}
