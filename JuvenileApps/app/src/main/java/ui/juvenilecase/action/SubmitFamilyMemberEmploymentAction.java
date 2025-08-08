package ui.juvenilecase.action;

import java.util.ArrayList ;
import java.util.Collection ;
import java.util.HashMap ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import messaging.family.SaveFamilyMemberAdditionalInfoEvent ;
import messaging.family.SaveFamilyMemberEmploymentInfoEvent ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.EventFactory ;
import mojo.km.utilities.MessageUtil ;
import naming.JuvenileFamilyControllerServiceNames ;
import naming.PDCodeTableConstants ;
import naming.UIConstants ;

import org.apache.struts.action.ActionMessage ;
import org.apache.struts.action.ActionErrors ;
import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import org.apache.struts.actions.LookupDispatchAction ;

import ui.common.UIUtil ;
import ui.juvenilecase.UIJuvenileHelper ;
import ui.juvenilecase.form.JuvenileMemberForm ;

/**
 * @author jjose
 */
public class SubmitFamilyMemberEmploymentAction extends LookupDispatchAction
{
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.CANCEL ) ;
		return forward ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.BACK ) ;
		return forward ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward removeEmployment( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileMemberForm myForm = (JuvenileMemberForm)aForm ;
		String myEmploymentPos = myForm.getSelectedValue() ;
		
		if( notNullNotEmptyString( myEmploymentPos ) )
		{
			if( notNullNotEmptyCollection( myForm.getEmploymentInfoList() ) )
			{
				( (List)myForm.getEmploymentInfoList() ).remove( (Integer.valueOf( myEmploymentPos )).intValue() ) ;
			}
		}
		myForm.clearEmployment() ;

		ActionForward forward = aMapping.findForward( UIConstants.ADD_SUCCESS ) ;
		return forward ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addEmployment( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileMemberForm myForm = (JuvenileMemberForm)aForm ;
		JuvenileMemberForm.MemberEmployment mbrEmployment = myForm.getCurrentEmployment() ;

		String annualNetIncome = computeAnnualNetIncome( mbrEmployment ) ;
		if( nullOrEmptyString(annualNetIncome) )
		{
			annualNetIncome = "0" ;
		}

		if( Double.parseDouble( annualNetIncome ) > 999999999.99 )
		{
			sendToErrorPage( aRequest, "error.annualGrossIncomeTooLarge" ) ;
			myForm.setAction( "" ) ;
			return aMapping.findForward( UIConstants.FAILURE ) ;
		}

		if( mbrEmployment != null )
		{
			if( notNullNotEmptyString( mbrEmployment.getEmploymentStatusId() ) )
			{
				if( myForm.getEmploymentInfoList() == null )
				{
					myForm.setEmploymentInfoList( new ArrayList() ) ;
				}

				mbrEmployment.setAnnualNetIncome( annualNetIncome ) ;
				myForm.getEmploymentInfoList().add( mbrEmployment ) ;
			}
		}
		
		myForm.setEmploymentInfoList( 
				UIJuvenileHelper.sortMemberEmploymentList( (ArrayList)myForm.getEmploymentInfoList() ) ) ;
		myForm.clearEmployment() ;
		myForm.setAction( UIConstants.UPDATE ) ;

		ActionForward forward = aMapping.findForward( UIConstants.ADD_SUCCESS ) ;
		return forward ;
	}

	/*
	 * @param myEmployment
	 * @return
	 */
	private String computeAnnualNetIncome( JuvenileMemberForm.MemberEmployment myEmployment )
	{
		if( myEmployment == null ||  nullOrEmptyString( myEmployment.getSalary() ) )
		{
			return "" ;
		}
		
		double annualIncomeDbl = 0 ;
		String annualIncomeStr = "" ;
		
		String salary = myEmployment.getSalary() ;
		if( nullOrEmptyString(salary) )
		{
			salary = "0" ;
		}

		String salaryRate = myEmployment.getSalaryRateId() ;
		if( notNullNotEmptyString( salaryRate ) )
		{
			double salaryDbl = UIUtil.getDoubleFromString( salary ) ;
			if( salaryRate.trim().equalsIgnoreCase( PDCodeTableConstants.SALARY_RATE_HOURLY ) )
			{
				String hoursWorked = myEmployment.getWorkHours() ;
				if( nullOrEmptyString( hoursWorked ) )
				{
					return "" ;
				}

				double hoursWorkedDbl = UIUtil.getDoubleFromString( hoursWorked ) ;
				annualIncomeDbl = 52 * hoursWorkedDbl * salaryDbl ;
			}
			else if( salaryRate.equalsIgnoreCase( PDCodeTableConstants.SALARY_RATE_WEEKLY ) )
			{
				annualIncomeDbl = 52 * salaryDbl ;
			}
			else if( salaryRate.equalsIgnoreCase( PDCodeTableConstants.SALARY_RATE_BIWEEKLY ) )
			{
				annualIncomeDbl = 26 * salaryDbl ;
			}
			else if( salaryRate.equalsIgnoreCase( PDCodeTableConstants.SALARY_RATE_MONTHLY ) )
			{
				annualIncomeDbl = 12 * salaryDbl ;
			}
			else
			{ // must be yearly
				annualIncomeDbl = salaryDbl ;
			}
			annualIncomeStr = UIUtil.formatCurrency( 
					new Double( annualIncomeDbl ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT ) ;
		}

		return annualIncomeStr ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward update( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileMemberForm myForm = (JuvenileMemberForm)aForm ;

		// Sending PD Request Event
		SaveFamilyMemberAdditionalInfoEvent event = (SaveFamilyMemberAdditionalInfoEvent)EventFactory.getInstance( JuvenileFamilyControllerServiceNames.SAVEFAMILYMEMBERADDITIONALINFO ) ;
		event.setMemberId( myForm.getMemberNumber() ) ;
		SaveFamilyMemberEmploymentInfoEvent saveEmploymentEvent ;

		Collection<JuvenileMemberForm.MemberEmployment> employmentList = myForm.getEmploymentInfoList() ;
		if( notNullNotEmptyCollection( employmentList ) )
		{
			for( JuvenileMemberForm.MemberEmployment anEmployment : employmentList )
			{
				if( nullOrEmptyString( anEmployment.getMemberEmploymentId() ) )
				{
					saveEmploymentEvent = UIJuvenileHelper.getSaveFamilyEmploymentEvent( anEmployment ) ;
					event.addRequest( saveEmploymentEvent ) ;
				}
			}
		}

		MessageUtil.postRequest( event ) ;

		myForm.setAction( "createMemberConfirmation" ) ;
		ActionForward forward = aMapping.findForward( UIConstants.SUCCESS ) ;
		return forward ;
	}

	/**
	 * @param aRequest
	 */
	private void sendToErrorPage( HttpServletRequest aRequest, String msg )
	{
		ActionErrors errors = new ActionErrors() ;
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( msg ) ) ;
		saveErrors( aRequest, errors ) ;
	}

	/* given a collection, return true if it's not null and not empty
	 * @param coll
	 * @return
	 */
	boolean notNullNotEmptyCollection( Collection coll )
	{
		return( coll != null &&  ! coll.isEmpty() ) ;
	}

	/* given a string, return true if it's not null and not empty
	 * @param str
	 * @return
	 */
	boolean notNullNotEmptyString( String str )
	{
		return( str != null &&  (str.trim().length() > 0) ) ;
	}

	/* given a string, return true if it's not null and not empty
	 * @param str
	 * @return
	 */
	boolean nullOrEmptyString( String str )
	{
		return( str == null ||  (str.trim().length() == 0) ) ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap() ;
		keyMap.put( "button.addToList", "addEmployment" ) ;
		keyMap.put( "button.remove", "removeEmployment" ) ;
		keyMap.put( "button.update", "update" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ;
		return keyMap ;
	}

}// END CLASS

