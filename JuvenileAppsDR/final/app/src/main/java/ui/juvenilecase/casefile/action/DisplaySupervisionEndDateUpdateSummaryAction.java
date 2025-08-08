// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplaySupervisionEndDateUpdateSummaryAction.java

package ui.juvenilecase.casefile.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenileCasefileForm;

public class DisplaySupervisionEndDateUpdateSummaryAction extends LookupDispatchAction
{
	/**
	 * @roseuid 44CF775900CA
	 */
	public DisplaySupervisionEndDateUpdateSummaryAction()
	{
	}

	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
	 * 
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm form = (JuvenileCasefileForm)aForm;
		
		if( form.getSupervisionEndDateStr() == null  ||
				(form.getSupervisionEndDateStr(). trim().length() == 0) )
		{
			form.setExpectedSupervisionEndDate(null);
		}
		
		form.setAction(UIConstants.SUMMARY);

		return ( aMapping.findForward(UIConstants.SUCCESS) ) ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}
}
