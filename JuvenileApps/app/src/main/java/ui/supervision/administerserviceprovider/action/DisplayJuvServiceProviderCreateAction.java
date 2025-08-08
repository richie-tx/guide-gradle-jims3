//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvCreateServiceProviderAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import naming.UIConstants;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class DisplayJuvServiceProviderCreateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351CB0175
	 */
	public DisplayJuvServiceProviderCreateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 446A2E3A03E2
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm providerForm = (ServiceProviderForm)aForm;
		providerForm.clear();
		providerForm.clearAddress();
		providerForm.clearProgram();
		providerForm.clearContact();
		providerForm.clearLists();
		UIServiceProviderHelper.getAddressCodes(providerForm);
		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
		IUserInfo userInfo = securityManager.getIUserInfo();
		System.out.println("---------DJNDJN-----Retrieving available logons -----------");
		//87191
		/*Collection userProfiles = UIServiceProviderHelper.getAvailableLogons(userInfo.getDepartmentId());
		System.out.println("---------DJNDJN-----User Profile Collection  --------------");
		System.out.println("---------DJNDJN-----" + userProfiles);
		System.out.println("---------DJNDJN-----User Profile collection size = " + userProfiles.size());
		if(userProfiles.size() != 0)
		{
			Collections.sort((List)userProfiles);
			System.out.println("---------DJNDJN-----User Profile collection size after sort = " + userProfiles.size());
			providerForm.setGenericLogonIds(userProfiles);
		}*/
		Date currentDate = new Date();
		providerForm.setStartDate(DateUtil.dateToString(currentDate, DateUtil.DATE_FMT_1));
		providerForm.setActionType("createProvider");
		providerForm.setStatusId("P");
		System.out.println("---------DJNDJN-----forwarding to JSP -----------");
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();				
		return keyMap;
	}
	
	
}
