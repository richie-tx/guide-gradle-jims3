package ui.juvenilecase.caseplan.action;

import java.util.HashMap ;
import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import naming.UIConstants ;

import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import org.apache.struts.actions.LookupDispatchAction ;

import ui.juvenilecase.UIJuvenileCaseplanHelper ;
import ui.juvenilecase.UIJuvenileHelper ;
import ui.juvenilecase.caseplan.form.CaseplanForm ;
import ui.juvenilecase.form.JuvenileCasefileForm ;
import ui.juvenilecase.helper.JuvenileCaseworkAlertsHelper ;

/**
 * 
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SubmitCLMReviewAction extends LookupDispatchAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 * 
	 * This method will be called during Create Goal use-case
	 */
	public ActionForward acceptCaseplan( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		String caseplanID = form.getCurrentCaseplan( ).getCaseplanId( ) ;
		UIJuvenileCaseplanHelper.submitCLMReview( true, caseplanID ) ;
		UIJuvenileCaseplanHelper.fetchCaseplanDetails( form, true ) ;
		form.setAction( "CLMACCEPT" ) ;
		aRequest.setAttribute( "displayConfirmation", "yes" ) ;
		// code added for sending CasePlanNotificationEvent notice to JPO
		JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper( ) ;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm( aRequest, true ) ;
		
		String officerId = casefileForm.getProbationOfficerLogonId( ) ;
		if( form.getJuvenileNum( ) == null || form.getJuvenileNum( ).equals( "" ) )
		{
			form.setJuvenileNum( casefileForm.getJuvenileNum( ) ) ;
		}
		
		helper.sendPOCaseplanNotSignedNotification( form, officerId, casefileForm.getJuvenileFullName( ) ) ;
		helper.clmAcceptNotification( form, officerId, casefileForm.getJuvenileFullName( ) ) ;

		return aMapping.findForward( "accept" ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 * 
	 * This method will be called during Create Goal use-case
	 */
	public ActionForward rejectCaseplan( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		form.setComments( "" ) ;
		
		return aMapping.findForward( "reject" ) ;
	}

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
		CaseplanForm form = (CaseplanForm)aForm ;
		form.setAction( "DONE" ) ;
		
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
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward notification( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.NOTIFICATION ) ;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap( )
	{
		Map keyMap = new HashMap( ) ;
		keyMap.put( "button.accept", "acceptCaseplan" ) ;
		keyMap.put( "button.reject", "rejectCaseplan" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.notification", "notification" ) ;
		return keyMap ;
	}

}