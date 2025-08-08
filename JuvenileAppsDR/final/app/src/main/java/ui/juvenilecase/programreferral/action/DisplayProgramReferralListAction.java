// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\juvenilecase\\programreferral\\action\\DisplayProgramReferralListAction.java

package ui.juvenilecase.programreferral.action;

import java.util.Collections ;
import java.util.List ;
import java.util.Map ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import naming.UIConstants ;

import org.apache.struts.action.ActionMessage ;
import org.apache.struts.action.ActionErrors ;
import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import ui.action.JIMSBaseAction ;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileHelper ;
import ui.juvenilecase.form.JuvenileCasefileForm ;
import ui.juvenilecase.programreferral.UIProgramReferralHelper ;
import ui.juvenilecase.programreferral.form.ProgramReferralForm ;
import ui.security.SecurityUIHelper ;

public class DisplayProgramReferralListAction extends JIMSBaseAction
{

	/**
	 * @roseuid 463BA5730398
	 */
	public DisplayProgramReferralListAction( )
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward displayReferralList( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		String forward = null ;
		form.clearAll( ) ;
		
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm( aRequest ) ;
		if( casefileForm == null )
		{
			this.saveErrors( aRequest, "error.serviceProvider.invalidUser" ) ;
			forward = UIConstants.FAILURE ;
		}
		else
		{
			String casefileId = casefileForm.getSupervisionNum( ) ;
			List activeReferrals = UIProgramReferralHelper.getActiveCasefileProgramReferrals( casefileId ) ;
			if( activeReferrals.size( ) > 1 )
			{
				Collections.sort( activeReferrals ) ;
			}
			form.setActiveReferralList( activeReferrals ) ;

			List closedReferrals = UIProgramReferralHelper.getClosedCasefileProgramReferrals( casefileId ) ;
			if( closedReferrals.size( ) > 1)
			{
				Collections.sort( closedReferrals ) ;
			}
			form.setClosedReferralList( closedReferrals ) ;
			
			//boolean allowUpdates = UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest);
			//form.setAllowCreate(UIJuvenileCasefileStatusHelper.supervisionCategoryCheck(casefileForm.getSupervisionTypeId(), allowUpdates ));
			
			//Allow creation of referral only if case file is active
			if(UIJuvenileCasefileStatusHelper.isActiveCaseFile(casefileForm)){
			   form.setAllowCreate(true); 
			}
			
			forward = UIConstants.SUCCESS ;
		}

		return aMapping.findForward( forward ) ;
	}

	/**
	 * @param aRequest
	 * @param errorkey
	 */
	private void saveErrors( HttpServletRequest aRequest, String errorKey )
	{
		ActionErrors errors = new ActionErrors( ) ;
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( errorKey, SecurityUIHelper.getLogonId( ) ) ) ;
		saveErrors( aRequest, errors ) ;
	}
	
	/**
	 *    
	 */
	public ActionForward cancel(
	   		ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	   {
		    return aMapping.findForward(UIConstants.CANCEL);
	   }  

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.link", "displayReferralList" ) ;
		keyMap.put("button.cancel","cancel");
	}

}
