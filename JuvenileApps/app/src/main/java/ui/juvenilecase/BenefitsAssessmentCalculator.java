package ui.juvenilecase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.codetable.GetCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;

/**
 *
 */
public class BenefitsAssessmentCalculator
{
	private int totalInCertifiedGroup = 0;						// calculated	
	private int parentsInCertifiedGroup = 0;					// calculated
	private double monthlyGrossForCertifiedGroup = 0;			// calculated
	private double incomeLimitForCertifiedGroup = 0;			// from table
	private double stepparentsMonthlyGrossIncome = 0;			// entered from form
	private double stepparentWorkRelatedExpenses = 0;			// entered from form
	private double stepparentOtherMonthlyIncome = 0;			// entered from form 		
	private double stepparentMonthlyDependentPayments = 0;		// entered from form 		
	private double stepparentMonthlyChildSupportPayments = 0;	// entered from form 		
	private int stepparentNoncertifiedCount = 0; 				// entered from form

	private static Map afdcIncomeLimitTable;
	private static Map underemployedParentLimitTable;
	private static Map stepparentNoncertifiedAllowanceTable;
	
	
	static {
		GetCodesEvent codeEvent;
		Collection codes;
		Iterator iter;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		codeEvent = new GetCodesEvent();
		codeEvent.setCodeTableName( "AFDC_INCOME_LIMITS" );
		dispatch.postEvent(codeEvent);
		codes =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CodeResponseEvent.class );

		afdcIncomeLimitTable = new HashMap();
		iter = codes.iterator();
		while ( iter.hasNext() )
		{
			CodeResponseEvent code = (CodeResponseEvent)iter.next();
			afdcIncomeLimitTable.put( code.getCode(), code.getDescription() );
		}
		
		
		codeEvent = new GetCodesEvent();
		codeEvent.setCodeTableName( "UNDEREMP_PARENT_INC_LIM" );
		dispatch.postEvent(codeEvent);
		codes =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CodeResponseEvent.class );

		underemployedParentLimitTable = new HashMap();
		iter = codes.iterator();
		while ( iter.hasNext() )
		{
			CodeResponseEvent code = (CodeResponseEvent)iter.next();
			underemployedParentLimitTable.put( code.getCode(), code.getDescription() );
		}
	
	
		codeEvent = new GetCodesEvent();
		codeEvent.setCodeTableName( "STEPPAR_ALLOW_DEDUCT" );
		dispatch.postEvent(codeEvent);
		codes =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CodeResponseEvent.class );

		stepparentNoncertifiedAllowanceTable = new HashMap();
		iter = codes.iterator();
		while ( iter.hasNext() )
		{
			CodeResponseEvent code = (CodeResponseEvent)iter.next();
			stepparentNoncertifiedAllowanceTable.put( code.getCode(), code.getDescription() );
		}
	}



	public BenefitsAssessmentCalculator( ProcessBenefitsAssessmentForm form )
	{
		ProcessBenefitsAssessmentForm.BenefitAssessment.TitleIVEQuestion quest =  form.getCurrentAssessment().getQuestion();
		
		//
		// Sum the gross monthy income of the certified group.
		//		
		Collection indIncDets =  quest.getAfdcIncomeWorksheetItems();
		Iterator iter = indIncDets.iterator();
		while ( iter.hasNext() )
		{
			ProcessBenefitsAssessmentForm.IndividualIncomeDetermination indInc = (ProcessBenefitsAssessmentForm.IndividualIncomeDetermination)iter.next();
			
			// exclude the extra individuals if the name is blank
			if ( indInc.getName() == null || indInc.getName().trim().length() == 0 )
			{
				continue;
			}
			
			totalInCertifiedGroup++;
			
			String relationId = indInc.getRelationshipToJuvenileId();
			if ( relationId != null &&  ( 	relationId.equals("AM") || relationId.equals("AF") ||
					relationId.equals("BM") || relationId.equals("BF")
				))
			{
				parentsInCertifiedGroup++;
			}
			
			// don't include Social Security Income 			
			if ( "S".equals( indInc.getIncomeSourceId() ) )
			{
				continue;
			}
			monthlyGrossForCertifiedGroup += indInc.getGrossMonthyIncome();
		}
		
		
		incomeLimitForCertifiedGroup = calculateIncomeLimitForCertifiedGroup( totalInCertifiedGroup, parentsInCertifiedGroup );
		
		if ( quest.isOneParentIsStepparent() )
		{
			stepparentsMonthlyGrossIncome = quest.getAfdcIncomeStepparentsMonthlyGross();
			stepparentWorkRelatedExpenses = quest.getAfdcIncomeStepparentWorkRelatedExpenses();
			stepparentOtherMonthlyIncome = quest.getAfdcIncomeStepparentOtherMonthlyIncome();
			stepparentMonthlyDependentPayments = quest.getAfdcIncomeStepparentMonthyPaymentsToDependent();
			stepparentMonthlyChildSupportPayments= quest.getAfdcIncomeStepparentMonthyAlimonyChildSupport();
			stepparentNoncertifiedCount = quest.getAfdcIncomeStepparentNoncertifiedCount();
		}
		
	}


	/**
	 * NOTE: Change the code table below to the one being created for AFDC Income Limits. 
	 * 
	 * @param groupSize - Must be in range 0 - 15
	 * @param numberOfParents - Must be in range 0 - 2
	 * @return
	 */
	private int calculateIncomeLimitForCertifiedGroup(  int groupSize, int numberOfParents )
	{
		if ( groupSize == 0 )
		{
			return 0;
		}
		
		if ( numberOfParents > 2 )
		{
			numberOfParents = 2;
		}
		
		if ( groupSize > 15 )
		{
			int incVal = Integer.parseInt( (String)afdcIncomeLimitTable.get( "P" + numberOfParents + "-0" ) );
			int total = Integer.parseInt( (String)afdcIncomeLimitTable.get( "P" + numberOfParents + "-15" ) ); 

			for ( int i = groupSize-15; i > 0; i-- )
			{
				total += incVal;
			}

			return total;			
		}
		else
		{
			return Integer.parseInt( (String)afdcIncomeLimitTable.get( "P" + numberOfParents + "-" + Integer.toString(groupSize) ) );
		}
	}
	
	private int calculateStepparentNoncertifiedAllowance(  int noncertifiedGroupSize )
	{
		if ( noncertifiedGroupSize == 0 )
		{
			return 0;
		}
		
		if ( noncertifiedGroupSize > 15 )
		{
			int incVal = Integer.parseInt( (String)stepparentNoncertifiedAllowanceTable.get( "0" ) );
			int total = Integer.parseInt( (String)stepparentNoncertifiedAllowanceTable.get( "15" ) ); 

			for ( int i = noncertifiedGroupSize-15; i > 0; i-- )
			{
				total += incVal;
			}

			return total;			
		}
		else
		{
			return Integer.parseInt( (String)stepparentNoncertifiedAllowanceTable.get( Integer.toString(noncertifiedGroupSize) ) );
		}
	}


	private int calculateUnderemployedParentLimit(  int groupSize )
	{
		if ( groupSize == 0 )
		{
			return 0;
		}
		
		if ( groupSize > 15 )
		{
			int incVal = Integer.parseInt( (String)underemployedParentLimitTable.get( "0" ) );
			int total = Integer.parseInt( (String)underemployedParentLimitTable.get( "15" ) ); 

			for ( int i = groupSize-15; i > 0; i-- )
			{
				total += incVal;
			}

			return total;			
		}
		else
		{
			return Integer.parseInt( (String)underemployedParentLimitTable.get( Integer.toString(groupSize) ) );
		}
	}


	public boolean isUnderemployedParent( double income )
	{
		double limit = calculateUnderemployedParentLimit(  getTotalInCertifiedGroup() );
		return income <= limit;
	}
	

	public int getTotalInCertifiedGroup()
	{
		return totalInCertifiedGroup;
	}
	
	public int getParentsInCertifiedGroup()
	{
		return parentsInCertifiedGroup;
	}

	public double getMonthlyGrossForCertifiedGroup()
	{
		return monthlyGrossForCertifiedGroup;
	}

	public double getIncomeLimitForCertifiedGroup()
	{
		return incomeLimitForCertifiedGroup;
	}
	
	public double getStepparentsMonthlyGrossIncome()
	{
		return stepparentsMonthlyGrossIncome;
	}

	public double getStepparentWorkRelatedExpenses()
	{
		return stepparentWorkRelatedExpenses;
	}

	/**
	 * JUVENILE_TITLE4E_STEPPARENT.CountableMonthlyIncome = 
	 * 		JUVENILE_TITLE4E_STEPPARENT.MonthlyGrossIncome - 
	 * 		JUVENILE_TITLE4E_STEPPARENT.WorkRelatedDeduction 
	 * @return
	 */
	public double getStepparentCountableMonthlyIncome()
	{
		return getStepparentsMonthlyGrossIncome() - getStepparentWorkRelatedExpenses();
	}
 		
	public double getStepparentOtherMonthlyIncome()
	{
		return stepparentOtherMonthlyIncome;
	}

	/**
	 * JUVENILE_TITLE4E_STEPPARENT.TotalMonthlyIncome = 
	 * 		JUVENILE_TITLE4E_STEPPARENT.CountableMonthlyIncome + 
	 * 		JUVENILE_TITLE4E_STEPPARENT.OtherIncome
	 * @return
	 */ 		
	public double getStepparentTotalMonthlyIncome()
	{
		return getStepparentCountableMonthlyIncome() + getStepparentOtherMonthlyIncome();
	}
 		
	public double getStepparentMonthlyDependentPayments()
	{
		return stepparentMonthlyDependentPayments;
	}
 		
	public double getStepparentMonthlyChildSupportPayments()
	{
		return stepparentMonthlyChildSupportPayments;
	}
 		
	public double getStepparentNoncertifiedAllowance()
	{
		return calculateStepparentNoncertifiedAllowance( stepparentNoncertifiedCount );		
	}


	/**
	 * JUVENILE_TITLE_4E_RPT.AppliedStepparentIncome = 
	 * 		JUVENILE_TITLE4E_STEPPARENT.TotalMonthlyIncome - 
	 * 		(	JUVENILE_TITLE4E_STEPPARENT.MonthlyExpense + 
	 * 			JUVENILE_TITLE4E_STEPPARENT.MonthlyExpenseSupport +
	 * 			JUVENILE_TITLE4E_STEPPARENT.AllowanceAmount )
	 * @return
	 */ 		
	public double getStepparentAppliedIncome()
	{
		double amt = getStepparentTotalMonthlyIncome() - 
					(	getStepparentMonthlyDependentPayments() +
						getStepparentMonthlyChildSupportPayments() +
						getStepparentNoncertifiedAllowance()
					);
		return Math.max( amt, 0 );					
	}
 		
	public double getTotalCountableIncome()
	{
		return getMonthlyGrossForCertifiedGroup() + getStepparentAppliedIncome();
	}
 		
	public boolean isGroupUnder10kLimit()
	{
		return getTotalCountableIncome() < 10000;
	}


	public boolean isGrossIncomeBelowAFDCLimit()
	{
		return getTotalCountableIncome() <= incomeLimitForCertifiedGroup;
	}
	
	public boolean childAppearsToMeetAFDCEligibility()
	{
		return isGroupUnder10kLimit() && isGrossIncomeBelowAFDCLimit(); 
	}

}
