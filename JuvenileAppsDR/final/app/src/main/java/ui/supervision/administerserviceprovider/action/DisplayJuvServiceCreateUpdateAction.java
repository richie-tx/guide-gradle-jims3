//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvCreateUpdateServiceAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class DisplayJuvServiceCreateUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D4003C
	 */
	public DisplayJuvServiceCreateUpdateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44734FDC02B8
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm providerForm = (ServiceProviderForm)aForm;
		providerForm.getCurrentProgram().getProgramService().clear();
		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		return null;
	}
}
