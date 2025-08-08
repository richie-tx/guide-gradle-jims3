package ui.juvenilecase.caseplan.action;

import java.util.HashMap ;
import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import naming.CasePlanConstants;
import naming.UIConstants ;

import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import org.apache.struts.actions.LookupDispatchAction ;
import ui.juvenilecase.caseplan.form.CaseplanForm ;
import ui.juvenilecase.form.JuvenileCasefileForm ;
import ui.juvenilecase.UIJuvenileCaseplanHelper ;
import ui.juvenilecase.UIJuvenileCaseworkHelper ;

/**
 * 
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DisplayCaseplanForCLMReviewAction extends LookupDispatchAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward displayCaseplanDetails( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		form.clear( ) ;
		String casefileID = form.getCasefileId( ) ;

		JuvenileCasefileForm fileForm = UIJuvenileCaseworkHelper.getHeaderForm( aRequest, true ) ;
		UIJuvenileCaseworkHelper.populateJuvenileCasefileForm( fileForm, casefileID ) ;

		// fire the get details
		UIJuvenileCaseplanHelper.fetchCaseplanDetails( form, true ) ;
		form.setAction( CasePlanConstants.CLM_REVIEW_IN_PROGRESS ) ;
		aRequest.setAttribute( "displayConfirmation", "no" ) ;
		
		return aMapping.findForward( UIConstants.SUCCESS ) ;
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
		CaseplanForm form = (CaseplanForm)aForm ;
		form.setAction( "DONE" ) ;
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
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282 method invoked when user clicked on the hyperlink
	 *          from the task.
	 */
	public ActionForward displayCaseplanDetailsfromTask( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		form.clear( ) ;

		String caseFileId = (String)aRequest.getParameter( "casefileID" ) ;
		String casePlanId = (String)aRequest.getParameter( "caseplanID" ) ;
		String juvenileId = (String)aRequest.getParameter( "juvenileID" ) ;
		
		form.setCasefileId( caseFileId ) ;
		form.setJuvenileNum( juvenileId ) ;
		UIJuvenileCaseworkHelper.populateJuvenileCasefileForm( UIJuvenileCaseworkHelper.getHeaderForm( aRequest, true ), caseFileId ) ;
		
		// fire the get details
		UIJuvenileCaseplanHelper.fetchCaseplanDetails( form, true ) ;
		form.setAction( CasePlanConstants.CLM_REVIEW_IN_PROGRESS ) ;
		aRequest.setAttribute( "displayConfirmation", "no" ) ;
		
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap( )
	{
		Map keyMap = new HashMap( ) ;
		keyMap.put( "button.link", "displayCaseplanDetails" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.view", "displayCaseplanDetailsfromTask" ) ;
		return keyMap ;
	}

}