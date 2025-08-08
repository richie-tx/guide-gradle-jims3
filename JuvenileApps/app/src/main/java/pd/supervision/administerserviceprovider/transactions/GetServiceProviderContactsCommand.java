package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.administerserviceprovider.GetServiceProviderContactsEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.SecurityManagerBaseResponse;
import mojo.km.security.Token;
import mojo.km.security.UserEntityBean;
import mojo.km.security.helper.SecurityManagerWebServiceHelper;
import pd.security.PDSecurityHelper;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.SP_Profile;

public class GetServiceProviderContactsCommand implements ICommand
{

    /**
     * @roseuid 450ACDB1009C
     */
    public GetServiceProviderContactsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 450AA17902C3
     */
    public void execute(IEvent event)
    {
	GetServiceProviderContactsEvent getSerProvEvt = (GetServiceProviderContactsEvent) event;
	String adminUserId = getSerProvEvt.getAdminUserId();
	Token token = PDSecurityHelper.getToken(); //87191
	SecurityManagerBaseResponse<List<UserEntityBean>> userInfo = SecurityManagerWebServiceHelper.getServiceProviderContacts(token, adminUserId);
	if (userInfo != null)
	{
	    if (userInfo.isIsSuccess() && userInfo.isRecFound())
	    {
		List<UserEntityBean> userProfiles =  userInfo.getData();
		if (userProfiles != null)
		{
		    Iterator<UserEntityBean> userProfileItr = userProfiles.iterator();
		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		    while (userProfileItr.hasNext())
		    {
			UserEntityBean userProfileResp = (UserEntityBean) userProfileItr.next();
			ServiceProviderContactResponseEvent respEvent = new ServiceProviderContactResponseEvent();
			if (userProfileResp != null && userProfileResp.getEmail()!=null && !userProfileResp.getEmail().isEmpty())
			{
			    respEvent.setFirstName(userProfileResp.getFirstname());
			    respEvent.setLastName(userProfileResp.getLastname());
			    respEvent.setWorkPhone(userProfileResp.getPhone());
			    respEvent.setEmail(userProfileResp.getEmail());
			    respEvent.setContactEmail(userProfileResp.getContactemail());
			    respEvent.setSmUserOID(userProfileResp.getUserid());
			    Iterator<SP_Profile> spIter = SP_Profile.findAll("employeeId", String.valueOf(userProfileResp.getUserid()));
			    if (spIter!=null && spIter.hasNext())
			    {
				respEvent.setJuvServProviderProfileId(spIter.next().getOID());
			    }
			    respEvent.setEmployeeId(String.valueOf(userProfileResp.getUserid())); //88615
			    respEvent.setLogonId(adminUserId); //88615
			    if(userProfileResp.isDisabled())
				respEvent.setInactivated(true);
			    else
				respEvent.setInactivated(false);
			    			    /*
			    respEvent.setMiddleName(userProfileResp.getMiddleName());
			    
			    respEvent.setAdminContact(spProfile.getIsAdminContact());
			    respEvent.setWorkPhone(spProfile.getPhoneNum());		
			    respEvent.setExtnNum(spProfile.getExtnNum());
			    respEvent.setPrefix(spProfile.getPrefix());
			    respEvent.setSuffix(spProfile.getSuffix());
			    respEvent.setCellPhone(spProfile.getCellPhone());
			    respEvent.setFaxNum(spProfile.getFaxNum());
			    respEvent.setNotes(spProfile.getNotes());
			    respEvent.setPager(spProfile.getPager());
			    respEvent.setLogonId(spProfile.getInHouseLogonId());
			    respEvent.setJobTitle(spProfile.getJobTitle());
			    respEvent.setStatusId(spProfile.getStatusId());*/
			    dispatch.postEvent(respEvent);
			}
		    }
		}
	    }
	}
    }
}
