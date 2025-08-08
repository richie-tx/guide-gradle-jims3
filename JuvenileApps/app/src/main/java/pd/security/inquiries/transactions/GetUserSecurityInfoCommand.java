//Source file: C:\\views\\Security\\app\\src\\pd\\security\\transactions\\GetFeatureSecurityInfoCommand.java

package pd.security.inquiries.transactions;

import java.util.Iterator;

import naming.ResponseLocatorConstants;
import messaging.inquiries.GetUserSecurityInfoEvent;
import messaging.security.inquiries.reply.UserSecurityInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.user.UserProfile;
import pd.exception.ReflectionException;
import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.ServiceProvider;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetUserSecurityInfoCommand implements ICommand 
{
   
   /**
    * @roseuid 44E9D2D600F5
    */
   public GetUserSecurityInfoCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44E9C8340272
    */
   public void execute(IEvent event) 
   {
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   GetUserSecurityInfoEvent aEvent = (GetUserSecurityInfoEvent) event;
	   UserProfile user = null;
	   if(aEvent.getEmployeeId() != null && (aEvent.getLogonId() == null || aEvent.getLogonId().equals(""))){
	   	   Iterator iter = InhouseSP_Profile.findAll("employeeId", aEvent.getEmployeeId());
	   	   if(iter.hasNext()){
	   	      InhouseSP_Profile in = (InhouseSP_Profile) iter.next();
	   	      if(in != null){
	   	          // added by RAC 6/4/2007
	   	          boolean admin = in.getIsAdminContact();
	   	          String spId = in.getServiceProviderId();
	              ServiceProvider sp = ServiceProvider.find(spId); 
	              String logon = null;
	   	          if(admin){
	   	              logon = sp.getAdminUserProfileId();
	   	              
	   	          }else{
	   	              logon = sp.getContactUserProfileId();
	   	               
	   	          }
	   	          if(logon != null){
	   	           user = UserProfile.find(logon);
	   	          }
	   	      
	   	      }else{
	   	      	  user = in.getUserProfile();
	   	      }
	   	   }
	   }else if((aEvent.getLogonId() != null && !aEvent.getLogonId().equals(""))) {
		   user = UserProfile.find(aEvent.getLogonId());
	   }
  		
  	   if(user != null){
  	   	   ResponseContextFactory respFac = new ResponseContextFactory();
	   	   ResponseCreator srCreator =  null;
	   	   try {
			  srCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.USER_SECURITY_INFO_RESPONSE_LOCATOR);
		   } catch (ReflectionException e) {
				e.printStackTrace();
		   }
		   UserSecurityInfoResponseEvent resp = (UserSecurityInfoResponseEvent) srCreator.create(user);
	       dispatch.postEvent(resp);
  	   }
   }
   
   /**
    * @param event
    * @roseuid 44E9C8340274
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44E9C8340276
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44E9C8340278
    */
   public void update(Object anObject) 
   {
    
   }
}
