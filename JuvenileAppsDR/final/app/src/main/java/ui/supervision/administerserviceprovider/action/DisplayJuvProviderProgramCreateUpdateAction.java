//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvCreateUpdateProviderProgramAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class DisplayJuvProviderProgramCreateUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D20359
	 */
	public DisplayJuvProviderProgramCreateUpdateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44734FE402CA
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		return null;
	}
}
