//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvCreateServiceProviderSummaryAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.user.GetAvailableGenericLogonIdsEvent;
import naming.UIConstants;
import naming.UserControllerServiceNames;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.Address;
import ui.common.AddressHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class DisplayJuvServiceProviderCreateUpdateSummaryAction extends LookupDispatchAction
{
    private final String UPDATE_PROVIDER_STR = "updateProvider";
    private final String UPDATE_SUMMARY_STR = "updateSummary";
    private final String DUPLICATE_NAME_STR = "Duplicate name";

    /**
     * @roseuid 447351D0024F
     */
    public DisplayJuvServiceProviderCreateUpdateSummaryAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 446A2E4400D0
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ServiceProviderForm sp = (ServiceProviderForm) aForm;
	
	int spOID =0;
	String providerID="";
	try
	{
	    spOID = Integer.parseInt(sp.getSelectedValue());
	    providerID = String.valueOf(spOID);
	}
	catch (NumberFormatException e)
	{
	    providerID = sp.getProviderId(); 
	}

	if (sp.getActionType().equalsIgnoreCase(UPDATE_PROVIDER_STR) || sp.getActionType().equalsIgnoreCase(UPDATE_SUMMARY_STR))
	{
	    //check if the user has changed the provider name
	    JuvenileServiceProviderResponseEvent resp = UIServiceProviderHelper.getProvider(providerID);
	    if (resp != null && !"I".equals(resp.getStatusId()) && !resp.getServiceProviderName().equalsIgnoreCase(sp.getProviderName()))
	    {
		ServiceProviderErrorResponseEvent nameError = UIServiceProviderHelper.validateSP(sp.getProviderName());
		if (nameError != null)
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(nameError.getMessage(), DUPLICATE_NAME_STR));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }

	    if (sp.getAdminContactId().equals(UIConstants.EMPTY_STRING))
	    {
		sp.setAdminContactId(sp.getAdminUserIDsString());
	    }

	    //86318
	   /* if (sp.getContactUserId().equals(UIConstants.EMPTY_STRING))
	    {
		sp.setContactUserId(sp.getContactUserIDsString());
	    }*/
	}
	else
	{
	    ServiceProviderErrorResponseEvent serviceError = UIServiceProviderHelper.validateAdminUserId(sp.getAdminContactId());
	    if (serviceError != null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(serviceError.getMessage(), "AdminUserId"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    ServiceProviderErrorResponseEvent nameError = UIServiceProviderHelper.validateSP(sp.getProviderName());
	    if (nameError != null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(nameError.getMessage(), DUPLICATE_NAME_STR));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}

	if (!sp.getWebSite().equals(UIConstants.EMPTY_STRING))
	{
	    StringTokenizer stok = new StringTokenizer(sp.getWebSite(), ":");
	    boolean found = false;
	    while (stok.hasMoreTokens())
	    {
		String str = stok.nextToken();
		if (str.equalsIgnoreCase("http") || str.equalsIgnoreCase("https"))
		{
		    found = true;
		    break;
		}
	    }
	    //String str = sp.getWebSite();

	    if (!found)
	    {
		String str = sp.getWebSite();
		sp.setWebSite("http://" + str);
	    }
	}

	if (sp.getActionType().equalsIgnoreCase(UPDATE_PROVIDER_STR))
	{
	    sp.setActionType(UPDATE_SUMMARY_STR);
	}
	else
	    if (sp.getActionType().equalsIgnoreCase("createProvider"))
	    {
		sp.setActionType(UIConstants.CREATE_SUMMARY);
	    }
	Address billingAddress = sp.getBillingAddress();
	AddressHelper.validateAddress(billingAddress);
	sp.setBillingAddrStatus(billingAddress.getValidated());
	Address mailingAddress = sp.getMailingAddress();
	AddressHelper.validateAddress(mailingAddress);
	sp.setMailingAddrStatus(mailingAddress.getValidated());
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /*
     * 
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.CANCEL);
    }

    /*
     * 
     */
    public ActionForward find(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ServiceProviderForm sp = (ServiceProviderForm) aForm;
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	IUserInfo userInfo = securityManager.getIUserInfo();

	GetAvailableGenericLogonIdsEvent getEvent = (GetAvailableGenericLogonIdsEvent) EventFactory.getInstance(UserControllerServiceNames.GETAVAILABLEGENERICLOGONIDS);

	getEvent.setDepartmentId(userInfo.getDepartmentId());

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(getEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);

	Collection userProfiles = MessageUtil.compositeToCollection(compositeResponse, UserResponseEvent.class);
	if (userProfiles.size() != 0)
	{
	    /* *****************************************
	     * 13apr2009 - mjt - not sure why this iteration 
	     * is happening, so i'm commenting it out 
	    Iterator<UserResponseEvent> iter = userProfiles.iterator();
	    while(iter.hasNext())
	    {
	    	UserResponseEvent userResp = iter.next();				
	    }
	    *********************************************
	     */

	    sp.setGenericLogonIds(userProfiles);
	}

	return aMapping.findForward(UIConstants.CANCEL);
    }

    /*
     * 
     */
    public ActionForward inactivate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ServiceProviderForm sp = (ServiceProviderForm) aForm;

	if ( ( UIConstants.EMPTY_STRING ).equals( sp.getAdminContactId() ) )
	{
	    sp.setAdminContactId(sp.getAdminUserIDsString());
	}

	if ( ( UIConstants.EMPTY_STRING ).equals( sp.getContactUserId() ) )
	{
	    sp.setContactUserId(sp.getContactUserIDsString());
	}

	sp.setActionType("inactivateSummary");

	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /*
     * 
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	return aMapping.findForward(UIConstants.BACK);
    }

    /* (non-Javadoc)
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
	Map keyMap = new HashMap();
	keyMap.put("button.next", "next");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.find", "find");
	keyMap.put("button.back", "back");
	keyMap.put("button.inactivate", "inactivate");
	return keyMap;
    }
}
