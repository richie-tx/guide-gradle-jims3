package mojo.km.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.km.caching.generic.CacheManager;
import mojo.km.security.helper.SecurityManagerWebServiceHelper;
import mojo.naming.CommonConstants;

/** @author sthyagarajan */
public class SecurityManagerAuthenticator implements IAuthenticator
{
    Date curDate = new Date();
    String message = "";

    @Override
    public Map<String, SecurityUser> authenticate(String userId, String password, String credentialType, String noOfAttempts) throws AuthenticationFailedException
    {
	//Token token = (Token) CacheManager.get(Token.class, "Bearer");
	//if (token == null)
	//{
	 Token  token = SecurityManagerWebServiceHelper.getAuthTokenURLConnection(); // token authentication
	    System.out.println("======Token from service:SecurityManagerAuthenticator======="+token.getAccess_token());
	  //Commented as it may be a problem in PROD as there are more than one server handled in the PROD
	//    CacheManager.add(token, "Bearer");
	//}else{
	//    System.out.println("=======Token from cache:SecurityManagerAuthenticator========="+token.getAccess_token());
	//}
	if (token != null)
	{
	    SecurityManagerBaseResponse<UserAuthenticationResponse> userInfo = SecurityManagerWebServiceHelper.getUserAuthentication(token, userId, password, credentialType, noOfAttempts); // email authentication

	    if (userInfo != null)
	    {
		UserAuthenticationResponse userLoginDetails = userInfo.getData();

		if (userInfo.isIsSuccess())// will be false only if there is a runtime exception.
		{
		    if (userInfo.isRecFound()) //will be false when the user not found.
		    {
			if (userLoginDetails.isLogonSuccessful())
			{
			    Map<String, SecurityUser> userInfoMap = new HashMap<String, SecurityUser>();
			    SecurityUser securityUser = populateSecurityUser(userLoginDetails); // secUserProfile
			    userInfoMap.put("securityUser", securityUser);
			    //add the user-profile to the cache.
			    CacheManager.add(securityUser, securityUser.getUserOID());
			    return userInfoMap;
			}
			else if (userLoginDetails.isLogonFailed())
			{
			    curDate = new Date();
			    System.err.println("[" + curDate + "] JIMS2 Distributed ::LOGON FAILED:: " + userId);
			    message = CommonConstants.INCORRECT_PASSWORD_ERROR;
			    throw new AuthenticationFailedException(message, "Incorrect password supplied. Supply the correct password or your account will be suspended.");
			}
			else if (userLoginDetails.isLogonSuspended())
			{
			    curDate = new Date();
			    System.out.println("[" + curDate + "] JIMS2 Distributed ::LOGIN SUSPENDED:: " + userId);
			    message = CommonConstants.PROFILE_SUSPENDED_ERROR;
			    throw new AuthenticationFailedException(message,
				    "User ID has been suspended for password violations. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator");
			}
		    }
		    else
		    {
			curDate = new Date();
			message = "";
			System.err.println("[" + curDate + "] JIMS2 Distributed ::UNHANDLED ERROR IS SUCCESS FLAG FALSE:: " + userId);
			message = CommonConstants.INCORRECT_USER_ERROR;
			throw new AuthenticationFailedException(message, "User Not Found. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator");
		    }
		}
		else
		{
		    curDate = new Date();
		    message = "";
		    System.err.println("[" + curDate + "] JIMS2 Distributed ::UNHANDLED ERROR IS SUCCESS FLAG FALSE:: " + userId);
		    message = CommonConstants.INCORRECT_USER_ERROR;
		    throw new AuthenticationFailedException(message, "Service Error. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator");
		}
	    }
	}
	curDate = new Date();
	message = "";
	System.err.println("[" + curDate + "] JIMS2 Distributed ::UNHANDLED ERROR IS SUCCESSFLAG FALSE:: " + userId);
	message = CommonConstants.INCORRECT_USER_ERROR;
	throw new AuthenticationFailedException(message, "Service Error. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator");
    }

    /** populateSecurityUser
     * 
     * @param userLoginDetails
     * @return */
    private SecurityUser populateSecurityUser(UserAuthenticationResponse userLoginDetails)
    {
	SecurityUser user = new SecurityUser();
	if (userLoginDetails.getDept() != null && userLoginDetails.getDept().getAgency() != null)
	{
	    user.setAgencyId(userLoginDetails.getDept().getAgency().getAgencyid());
	    user.setAgencyName(userLoginDetails.getDept().getAgency().getAgencyname());
	    user.setDepartmentId(userLoginDetails.getDept().getDepartmentid());
	    user.setDepartmentName(userLoginDetails.getDept().getDepartmentdescription());
	    user.setOrgCode(userLoginDetails.getDept().getOrgcode());
	}
	user.setFirstName(userLoginDetails.getFirstname());
	user.setLastName(userLoginDetails.getLastname());
	user.setMiddleName("");
	user.setUserAccess(userLoginDetails.getUseraccesses());
	user.setUserOID(String.valueOf(userLoginDetails.getUserid()));

	//userProfile.setPh
	String userTypeId = "";
	AllUserAccessInfoBean useraccess = userLoginDetails.getUseraccesses();
	if (useraccess != null)
	{
	    if (useraccess.getRoles() != null)
	    {
		List<RolesEntityBean> roles = useraccess.getRoles();
		if (roles != null)
		{
		    Iterator<RolesEntityBean> rolesItr = roles.iterator();
		    while (rolesItr.hasNext())
		    {
			RolesEntityBean role = rolesItr.next();
			if (role != null)
			{
			    if (role.getRoletype() != null)
			    {
					if (role.getRoletype().equalsIgnoreCase("MA"))
					{
					    userTypeId = "MA";
					    break;
					}
					else if (role.getRoletype().equalsIgnoreCase("SA") && role.getRoledesc().equalsIgnoreCase("SECURITY ADMINISTRATOR"))
					{
					    userTypeId = "SA";
					    break;
					}
					else if (role.getRoletype().equalsIgnoreCase("ASA"))
					{
					    userTypeId = "ASA";
					    break;
					}
					else if (role.getRoletype().equalsIgnoreCase("LA"))
					{
					    userTypeId = "LA";
					    break;
					}	
					else if (role.getRoletype().equalsIgnoreCase("BA"))
					{
					    userTypeId = "BA";
					    break;
					}				
			    }
			}

		    }
		}
	    }
	    user.setUserTypeId(userTypeId);
	}
	List<CredentialStoreEntityBean> credentials = userLoginDetails.getSecuritycredentials();
	if (credentials != null && !credentials.isEmpty())
	{
	    Iterator<CredentialStoreEntityBean> credentialItr = credentials.iterator();
	    while (credentialItr.hasNext())
	    {
		CredentialStoreEntityBean credential = credentialItr.next();
		if (credential != null)
		{
		    if (credential.getCredentialtypeDescription() != null && credential.getCredentialtypeDescription().equalsIgnoreCase("JUCODE"))
		    {
			System.out.println("*** UVCODE FROM SECURITY MANAGER SERVICE*****" + credential.getCredentialtypeValue());
			String uvCode = credential.getCredentialtypeValue();
			user.setJIMSLogonId(uvCode);
			//	break;
		    }

		    if (credential.getCredentialtypeDescription() != null && credential.getCredentialtypeDescription().equalsIgnoreCase("EMAIL"))
		    {
			System.out.println("*** UVCODE FROM SECURITY MANAGER SERVICE*****" + credential.getCredentialtypeValue());
			String uvCode = credential.getCredentialtypeValue();
			user.setJIMS2LogonId(uvCode);
			//break;
		    }
		}
	    }
	}
	return user;

    }

}
