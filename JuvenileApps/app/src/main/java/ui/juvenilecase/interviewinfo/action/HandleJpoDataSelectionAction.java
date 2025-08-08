package ui.juvenilecase.interviewinfo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.interviewinfo.form.SocialHistoryForm;

/**
 * @author awidjaja TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleJpoDataSelectionAction extends LookupDispatchAction
{
	private final int GENERIC_CHOICE = 0 ;
	private final int COURT_DISPOSITION_CHOICE = 1 ;
	private final int DETENTION_CHOICE = 2 ;
	private final int REPORT_TO_REFEREE_INITIATION_CHOICE = 3 ;
	
	/*
	 * 
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.CANCEL );
		return forward;
	}

	/*
	 * 
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK );
	}

	/*
	 * 
	 */
	public ActionForward link( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		SocialHistoryForm form = (SocialHistoryForm)aForm;
		SocialHistoryReportDataTO socialHistory = form.getSocialHistoryData() ;
		int selectedItem = form.getSelectedJPOData() ;

		socialHistory.setUseCourtDispositionAlternatives( false );
		socialHistory.setUseDetentionReason( false );
		socialHistory.setGeneric( false );
		socialHistory.setReportToRefereeInitiation(false);
		
		String forward = "none";

		if( selectedItem == COURT_DISPOSITION_CHOICE )
		{
			socialHistory.setUseCourtDispositionAlternatives( true );
			socialHistory.setGeneric( false );
			aRequest.setAttribute( "state", "display" );
			forward = "courtDispositionAlternatives";
		}
		else if( selectedItem == DETENTION_CHOICE )
		{
			socialHistory.setUseDetentionReason( true );
			socialHistory.setGeneric( false );
			aRequest.setAttribute( "state", "display" );
			forward = "detentionReasons";
		}
		else if( selectedItem == GENERIC_CHOICE )
		{
			socialHistory.setGeneric( true );
			aRequest.setAttribute( "state", "display" );
		}
		else if( selectedItem == REPORT_TO_REFEREE_INITIATION_CHOICE )
		{
			socialHistory.setReportToRefereeInitiation( true );
			aRequest.setAttribute( "state", "display" );
		}

		return aMapping.findForward( forward );

	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put( "button.link", "link" );
		keyMap.put( "button.cancel", "cancel" );
		keyMap.put( "button.back", "back" );
		return keyMap;
	}
}