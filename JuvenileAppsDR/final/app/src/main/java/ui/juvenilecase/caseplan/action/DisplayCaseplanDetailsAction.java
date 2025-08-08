package ui.juvenilecase.caseplan.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CasePlanConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileCaseplanHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * 
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DisplayCaseplanDetailsAction extends LookupDispatchAction
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
		
		// this action is called when the user clicks on the caseplan tab
		// If the CLM is doing a caseplan review and he clicks on the
		// caseplan tab, he should the CLM review screen
		String action = form.getAction() ;

		if( notNullEqualsString( action, CasePlanConstants.CLM_REVIEW_IN_PROGRESS ) )
		{
			return aMapping.findForward( CasePlanConstants.CLM_REVIEW ) ;
		}
		
		// if called from the juv profile tab, the casefileId
		// is already coming through the request
		boolean inReviewForCLM = false ;
		
		String actionParameter = aRequest.getParameter( UIConstants.ACTION ) ;
		if( notNullEqualsString( actionParameter, CasePlanConstants.JUV_PROFILE_VIEW ) )
		{
			//added this code to get the casefile details from the profile tab 
			GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent)
			EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE ) ;
			getCasefileEvent.setSupervisionNumber( form.getCasefileId()) ;
		
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			dispatch.postEvent( getCasefileEvent ) ;
		
			CompositeResponse response = (CompositeResponse)dispatch.getReply( ) ;
		
			JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent)
					MessageUtil.filterComposite( response, JuvenileCasefileResponseEvent.class ) ;
			// Populate casefile found
			if( casefile != null )
			{
				form.setSupervisionType(casefile.getSupervisionTypeId( ));
				form.setJuvProfile( true ) ; 
			}
			
		}
		else
		{
			form.setJuvProfile( false ) ; // casefile view

			/* reset action since this action is the entry to
				 other casefile/profile mixups */
			form.setAction( UIConstants.VIEW_DETAIL ) ; 
			
			// get casefile Id from session
			HttpSession session = aRequest.getSession() ;
			JuvenileCasefileForm casefileForm = (JuvenileCasefileForm)
					session.getAttribute( UIConstants.CASEFILE_HEADER_FORM ) ;
			if( casefileForm != null )
			{
				form.setSupervisionType( casefileForm.getSupervisionTypeId() ) ;
				form.setCasefileId( casefileForm.getSupervisionNum() ) ;
				form.setJuvenileNum( casefileForm.getJuvenileNum() ) ;
//Activity 61212 - New business Rule to expand CLM process
//changed the following line to check to see if the user is a CLM and not necessarily
//the CLM for the casefile.
				if( casefileForm.isLoggedInUserACLM() )
				{
					inReviewForCLM = true ;
				}
			}
		}
		
		form.clear() ;
		form.setPlacementInfoExist( UIConstants.NO ) ;
		form.setCaseplanExist( UIConstants.NO ) ;
		form.setSecondaryAction("");
		form.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest, form.getCasefileId()));

		UIJuvenileCaseplanHelper.fetchCaseplanDetails( form ) ;
		if( inReviewForCLM )
		{
			String str = form.getCurrentCaseplan().getStatus() ;
			if( notNullEqualsString( str, CasePlanConstants.IN_REVIEW ) )
			{
				form.setInReviewForCLM( true ) ;
				form.setAction( CasePlanConstants.CLM_REVIEW_IN_PROGRESS ) ;
			}
		}
		
		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
	}

	/* given one String, return true if it's not null
	 * and it compares equal to the second input String
	 */
	private boolean notNullEqualsString( String strToCheck, String strToCompare )
	{
		return( strToCheck != null  && strToCompare != null && strToCheck.equalsIgnoreCase(strToCompare) ) ;
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
		return( aMapping.findForward( UIConstants.CANCEL ) ) ;
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
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap() ;
		keyMap.put( "button.link", "displayCaseplanDetails" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.returnToCaseplanDetails", "displayCaseplanDetails" ) ;
		
		return keyMap ;
	}

}