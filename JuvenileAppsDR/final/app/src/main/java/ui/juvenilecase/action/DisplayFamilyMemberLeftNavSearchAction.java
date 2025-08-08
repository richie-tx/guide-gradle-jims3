/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayJuvenileProfileSearchAction
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileMemberSearchForm;

/**
 * Class DisplayFamilyMemberLeftNavSearchAction.
 * 
 * @author CShimek
 */
public class DisplayFamilyMemberLeftNavSearchAction extends Action
{

    /**
     * @roseuid 42A898CE034B
     */
    public DisplayFamilyMemberLeftNavSearchAction()
    {

    }
    
    /**
     * 
     */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
    {
		JuvenileMemberSearchForm jmSearchForm = (JuvenileMemberSearchForm) aForm; 
		jmSearchForm.clear();
		jmSearchForm.clearSearchResults();
		return aMapping.findForward(UIConstants.SUCCESS);
    }

}
