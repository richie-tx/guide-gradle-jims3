package pd.security;

import java.util.List;

import mojo.km.security.AllUserAccessInfoBean;
import mojo.km.security.JIMS2FeaturesEntityBean;
import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;

public class SecurityService
{

    private static SecurityService securityService;

    private SecurityService()
    {
    }

    public static SecurityService getSecurityService()
    {
	try
	{
	    if (SecurityService.securityService == null)
	    {
		SecurityService.securityService = new SecurityService();
	    }
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	finally
	{
	}
	return SecurityService.securityService;
    }
    
    //87191
    /*public HashMap getFeaturesForUser(String jimsLogonId )
    {
      HashMap featureMap = null ; 
      try
      {
      	IHome home = new Home() ; 
      	User user = (User)home.find( jimsLogonId , CaseloadConstants.USER_JIMS_LOGON_ID_PROPERTY, User.class);
      	featureMap = user.getFeatures() ; 
      }
      catch (Exception e) 
      {
      	e.printStackTrace() ; 
      }
      return featureMap ; 
    }
    */
    public boolean isFeatureAllowed(String jimsLogonId, String featureIdentifier)
    {
	boolean isFeatureAllowed = false;
	try
	{
	    //87191
	    /*HashMap featureMap = this.getFeaturesForUser(jimsLogonId ) ;
	    isFeatureAllowed = featureMap.containsKey(featureIdentifier) ; */
	    UserProfile userProfile = UserProfileHelper.getUserProfileFromJUCode(jimsLogonId);
	    AllUserAccessInfoBean useraccess = userProfile.getUserAccess();
	    if (useraccess != null)
	    {
		List<JIMS2FeaturesEntityBean> features = useraccess.getFeatures();
		if (features != null)
		{
		    while (features.iterator().hasNext())
		    {
			JIMS2FeaturesEntityBean feature = features.iterator().next();
			if (feature != null)
			{
			    if (feature.getFeaturename().equalsIgnoreCase(featureIdentifier))
			    {
				return true;
			    }
			}
		    }
		}
	    }
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	return isFeatureAllowed;
    }

}
