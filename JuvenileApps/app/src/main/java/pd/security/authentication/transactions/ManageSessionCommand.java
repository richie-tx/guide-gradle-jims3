/*
 * Created on Jan 13, 2006
 *
 */
package pd.security.authentication.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.authentication.ManageSessionEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import messaging.security.authentication.reply.NoFeaturesErrorResponseEvent;
import mojo.km.config.SecurityProperties;
import mojo.km.context.ContextManager;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.security.RolesEntityBean;
import mojo.km.security.SecurityUser;
import mojo.km.security.UsergroupRoleEntityBean;
import mojo.km.utilities.Name;
import mojo.messaging.securitytransactionsevents.reply.LoginResponseEvent;
import mojo.messaging.securitytransactionsevents.reply.UserInfoResponseEvent;
import naming.PDAdministerServiceProviderConstants;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.security.authentication.PDSecurityAuthenticationHelper;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.PDAdminsterServiceProviderHelper;
import pd.supervision.administerserviceprovider.SP_Profile;

/**
 * @author Rcooper To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ManageSessionCommand implements ICommand
{
    private static final String SECURITY_MANAGER = "ISecurityManager";

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
	ManageSessionEvent requestEvent = (ManageSessionEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	LoginResponseEvent responseEvent = new LoginResponseEvent();
	ISecurityManager securityManager = null;
	String securityManagerImpl = SecurityProperties.getInstance().getSecurityManagerImpl();
	if (securityManagerImpl == null)
	{
	    throw new RuntimeException("No SecurityManager found. You must register an SecurityManager in the Security section in mojo.xml");
	}
	try
	{
	    securityManager = (ISecurityManager) ContextManager.getSession().get(SECURITY_MANAGER);
	    if (securityManager == null)
	    {
		securityManager = (ISecurityManager) Class.forName(securityManagerImpl).newInstance();
	    }

	}
	catch (Exception e)
	{
	    e.printStackTrace(System.out);
	    throw new RuntimeException("Could not create instance of ISecurityManager. Exception is " + e);
	}
	//U.S #79250
	String JIMS2LogonId = requestEvent.getJIMS2LogonId();
	//String JIMS2Password = requestEvent.getJIMS2Password(); //#86318
	String JIMSLogonId = requestEvent.getJIMSLogonId();
	//String JIMSPassword = requestEvent.getJIMSPassword(); //86318
	String accountType = requestEvent.getJIMS2AccountTypeId();
	String accountOID = requestEvent.getJIMS2AccountTypeOID();
	String firstName = requestEvent.getFirstName();
	String middleName = requestEvent.getMiddleName();
	String lastName = requestEvent.getLastName();

	//add User Profile, Officer or Service Provider to the session
	IUserInfo iUser = null;
	SecurityUser upf = requestEvent.getLoginResponse().getSecUser();//UserProfile.find(JIMSLogonId); //TODO replace it with webservice call.
	if (accountType.equals("L"))
	{
	    OfficerProfile op = OfficerProfile.find(accountOID);
	    if (op != null)
	    {
		iUser = PDOfficerProfileHelper.getOfficerProfileResponseEvent(op);
		if (upf != null)
		{
		    iUser.setUserTypeId(upf.getUserTypeId()); //TODO
		}
	    }
	    else
	    {
		if (JIMS2LogonId != null)
		{ //covered for service provider
		    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
		    error.setMessage("Officer Profile not found for user " + JIMS2LogonId + "officer OID " + accountOID);
		    dispatch.postEvent(error);
		    return;
		}
		else
		{
		    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
		    error.setMessage("Officer Profile not found for user " + JIMSLogonId + "officer OID " + accountOID);
		    dispatch.postEvent(error);
		    return;
		}
	    }
	}
	else
	{
	    if (accountType.equals("S"))
	    {
		//SP_Profile sp = SP_Profile.find(accountOID);
		//if (sp != null)
		//{
		   // iUser = PDAdminsterServiceProviderHelper.getSPContactResponseEvent(sp);
		boolean isRecFound=false;
		if (upf != null)
		{
		    Iterator<JuvenileServiceProvider> juv_spIter = JuvenileServiceProvider.findAll("adminUserProfileId", upf.getJIMSLogonId());
		    if (juv_spIter != null)
		    {
		    //HOT FIX BUG #88775
			if (juv_spIter.hasNext())
			{
			        ServiceProviderContactResponseEvent cResp = new ServiceProviderContactResponseEvent();
				cResp.setJuvServProviderProfileId(juv_spIter.next().getOID());
				cResp.setLastName(upf.getLastName());
				cResp.setFirstName(upf.getFirstName());
				cResp.setMiddleName(upf.getMiddleName());
				//cResp.setId(in.getOID().toString());
				iUser = cResp;
				iUser.setUserTypeId(upf.getUserTypeId());
	    			iUser.setAgencyId(upf.getAgencyId());
	    			iUser.setDepartmentId(upf.getDepartmentId());
	    			iUser.setAgencyName(upf.getAgencyName());
	    			iUser.setDepartmentName(upf.getDepartmentName());
	    			iUser.setOrgCode(upf.getOrgCode());
	    			//iUser.setAccountType("S");
	    			
			   /* Iterator<SP_Profile> spIter = SP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, juv_spIter.next().getOID());
			    while (spIter.hasNext())
			    {
				SP_Profile sp = spIter.next();
				if (sp != null)
				{
				    if (sp.getEmail().equalsIgnoreCase(JIMS2LogonId))
				    {
					iUser = PDAdminsterServiceProviderHelper.getSPContactResponseEvent(sp);
					isRecFound=true;
					break;
				    }
				}
			    }//(2)
*/			}//(1)
			else
			{
			    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			    error.setMessage("A Service Provider is not associated to this User ID "+upf.getJIMSLogonId()+", please contact the Juvenile Probation Department to obtain a valid generic User ID.");
			    dispatch.postEvent(error);
			    return;
			}
			/*if (!isRecFound)
			{
			    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			    error.setMessage("A Service Provider is not associated to this User ID, please contact the Juvenile Probation Department to obtain a valid generic User ID.");
			    dispatch.postEvent(error);
			    return;
			}*/
		    }
		    else
		    {
			LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			error.setMessage("A Service Provider is not associated to this User ID "+upf.getJIMSLogonId()+", please contact the Juvenile Probation Department to obtain a valid generic User ID.");
			dispatch.postEvent(error);
			return;
		    }
		}
		//}
		//
	    }
	    else
	    {
		if (accountType.equals("N"))
		{
		    if (upf != null)
		    {
			iUser = new UserInfoResponseEvent();
			iUser.setFirstName(upf.getFirstName());
			iUser.setMiddleName(upf.getMiddleName());
			iUser.setLastName(upf.getLastName());
			iUser.setUserTypeId(upf.getUserTypeId());
			iUser.setAgencyId(upf.getAgencyId());
			iUser.setDepartmentId(upf.getDepartmentId());
			iUser.setAgencyName(upf.getAgencyName());
			iUser.setDepartmentName(upf.getDepartmentName());
			iUser.setOrgCode(upf.getOrgCode());
			//iUser.setAccountType("N");
		    }
		    else
		    {
			if (JIMS2LogonId != null) //covered for service provider to be removed in phase2. 79250
			{
			    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			    error.setMessage("User Profile not found for user " + JIMS2LogonId + "JIMS logonId " + JIMS2LogonId);
			    dispatch.postEvent(error);
			    return;
			}
			else
			{
			    LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			    error.setMessage("User Profile not found for user " + JIMSLogonId + "JIMS logonId " + JIMSLogonId);
			    dispatch.postEvent(error);
			    return;
			}
		    }
		}
		else
		{
		    if (JIMS2LogonId != null) //covered for service provider to be removed in phase2. 79250
		    {
			LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			error.setMessage("User Profile not found for user " + JIMS2LogonId + "JIMS logonId " + JIMS2LogonId);
			dispatch.postEvent(error);
			return;
		    }
		    else
		    {
			LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			error.setMessage("Invalid Account type for user " + JIMSLogonId);
			dispatch.postEvent(error);
			return;
		    }
		}
	    }
	}
	
	iUser.setJIMS2LogonId(JIMS2LogonId);
	//iUser.setJIMS2Password(JIMS2Password); //86318
	iUser.setJIMSLogonId(JIMSLogonId);
	//iUser.setPassword(JIMSPassword);//86318
	securityManager.setIUserInfo(iUser);

	// build ACL list
	//User user = User.find(JIMSLogonId); no longer in use. Migrated to SM. Refer US #87188. 
	securityManager.buildAcl(upf);

	Name userName = new Name(iUser.getFirstName(), null, iUser.getLastName());
	responseEvent.setUserDisplayName(userName.getCompleteFullNameFirst());
	responseEvent.setSecurityManager(securityManager);
	ContextManager.getSession().put("ISecurityManager", securityManager);
	dispatch.postEvent(responseEvent);	
	

	//no longer in use. Migrated to SM. Refer US #87188. 
	String userType = upf.getUserTypeId(); 
	if (upf.getUserAccess() != null)
	{
	    Collection<RolesEntityBean> roles = upf.getUserAccess().getRoles();
	    Collection<UsergroupRoleEntityBean> uGroupsRoles = upf.getUserAccess().getUsergrouproles();

	    //Collection uGRoles = null;
	    boolean uGRolesExist = false;
	    if (uGroupsRoles != null && uGroupsRoles.iterator().hasNext())
	    {
		uGRolesExist = true;

	    }

	    if (roles!=null && (roles.isEmpty()) && (!uGRolesExist) && (!(userType.equals("MA"))) && (!(userType.equals("SA"))) && (!(userType.equals("ASA"))))
	    {
		NoFeaturesErrorResponseEvent error = new NoFeaturesErrorResponseEvent();
		String message = PDSecurityAuthenticationHelper.getNoFeaturesMessage(iUser.getDepartmentId());
		error.setMessage(message);
		dispatch.postEvent(error);
	    }
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
    }

}