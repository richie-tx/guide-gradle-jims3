//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\CreateServiceProviderContactCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import naming.PDAdministerServiceProviderConstants;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import pd.juvenilecase.JJSCourt;
import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.OutSourcedSP_Profile;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.administerserviceprovider.Service;
import messaging.administerserviceprovider.CreateServiceProviderContactEvent;
import messaging.administerserviceprovider.UpdateServiceProviderContactEvent;
import messaging.administerserviceprovider.UpdateServiceProviderStatusRequestEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
//TODO
public class UpdateServiceProviderContactCommand implements ICommand
{

	/**
	 * @roseuid 4473538C00C6
	 */
	public UpdateServiceProviderContactCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FED01BF
	 */
	public void execute(IEvent event)
	{
	    	UpdateServiceProviderContactEvent updateEvent = (UpdateServiceProviderContactEvent) event;
	    	String updateFlg = "false";
	    	
	    	if(updateEvent.isInHouse()) 
		{
	    	    		Iterator<InhouseSP_Profile> spItr = null;
	    	    		InhouseSP_Profile inSP = null;
	    	    		spItr =InhouseSP_Profile.findAll("employeeId", updateEvent.getEmployeeId());
	    	    		inSP = spItr.next();
                	    	if (inSP != null)                	    	
                	    	{
                    	    	    	if(updateEvent.getFullName().getFirstName()!=null)
                    	    	    	{
                    	    	    	    if (!updateEvent.getFullName().getFirstName().equalsIgnoreCase(inSP.getFirstName()))
                    	    	    	    {
                                    	    	inSP.setFirstName(updateEvent.getFullName().getFirstName());
                                    	    	updateFlg = "true";                        	    	    
                    	    	    	    }
                    	    	    	}
                        	    	if(updateEvent.getFullName().getMiddleName()!=null)
                    	    	    	{
                                	    	if (!updateEvent.getFullName().getMiddleName().equalsIgnoreCase(inSP.getMiddleName()))
                                		{
                                        	    	inSP.setMiddleName(updateEvent.getFullName().getMiddleName());
                                        	    	updateFlg = "true";                        	    	    
                                		}
                    	    	    	}
                        	    	if(updateEvent.getFullName().getLastName()!=null)
                    	    	    	{
                                	    	if (!updateEvent.getFullName().getLastName().equalsIgnoreCase(inSP.getLastName()))
                                		{
                                        	    	inSP.setLastName(updateEvent.getFullName().getLastName());
                                        	    	updateFlg = "true";                        	    	    
                                		}
                    	    	    	}
                        	    	if(updateEvent.getLogonId()!=null)
                    	    	    	{
                                	    	if (!updateEvent.getLogonId().equalsIgnoreCase(inSP.getLogonId()))
                                		{
                                        	    	inSP.setLogonId(updateEvent.getLogonId());
                                        	    	updateFlg = "true";                        	    	    
                                		}
                    	    	    	}
                        	    	if(updateEvent.getServiceProviderId()!=null)
                    	    	    	{
                                	    	if (!updateEvent.getServiceProviderId().equals(inSP.getServiceProviderId()))
                                		{
                                        	    	inSP.setServiceProviderId(updateEvent.getServiceProviderId());
                                        	    	updateFlg = "true";                        	    	    
                                		}
                    	    	    	}
                        	    	if(updateEvent.getEmail()!=null)
                    	    	    	{
                                	    	if (!updateEvent.getEmail().equalsIgnoreCase(inSP.getEmail()))
                                		{
                                        	    	inSP.setEmail(updateEvent.getEmail());
                                        	    	updateFlg = "true";                        	    	    
                                		}
                    	    	    	}
                        	    	if(updateEvent.getStatusId()!=null)
                    	    	    	{
                                	    	if (!updateEvent.getStatusId().equalsIgnoreCase(inSP.getStatusId()))
                                		{
                                        	    	inSP.setStatusId(updateEvent.getStatusId());
                                        	    	updateFlg = "true";                        	    	    
                                		}
                    	    	    	} 
                        	    	String newPhone=null;
                	    		if(updateEvent.getWorkPhone()!=null)
                	    		    newPhone=updateEvent.getWorkPhone().getAreaCode()+updateEvent.getWorkPhone().getPrefix()+updateEvent.getWorkPhone().getLast4Digit();
                        	    	if(newPhone!=null)  
                                        {
                        	    	    if (!newPhone.equalsIgnoreCase(inSP.getPhoneNum()))
                                        	{
                        	    			inSP.setPhoneNum(newPhone);
                                                	updateFlg = "true";                        	    	    
                                        	}
                                        }                    	    	    	                    	
                        	    	if (updateFlg.equalsIgnoreCase("true"))
                        		{
                                	    	IHome home = new Home();
                				home.bind(inSP);
                        		}
                	    	}				
		}
		else 
		{
		    	Iterator<OutSourcedSP_Profile> spItr = null;
		    	OutSourcedSP_Profile outSP = null;
	    		spItr =OutSourcedSP_Profile.findAll("employeeId", updateEvent.getEmployeeId());
	    		outSP = spItr.next();
	    		if (outSP != null)                	    	
	    		{
				//OutSourcedSP_Profile outSourcedSPProfile =(OutSourcedSP_Profile) OutSourcedSP_Profile.findAll("employeeId", updateEvent.getEmployeeId());				
    	    			if(updateEvent.getFullName().getFirstName()!=null)
        	    	    	{
        	    			if (!updateEvent.getFullName().getFirstName().equalsIgnoreCase(outSP.getFirstName()))
        	    			{
        				    	outSP.setFirstName(updateEvent.getFullName().getFirstName());
                                	    	updateFlg = "true";                        	    	    
        	    			}
        	    	    	}
        	    		if(updateEvent.getFullName().getMiddleName()!=null)
        	    	    	{
                        	    	if (!updateEvent.getFullName().getMiddleName().equalsIgnoreCase(outSP.getMiddleName()))
                        		{
                        	    	    	outSP.setMiddleName(updateEvent.getFullName().getMiddleName());
                                	    	updateFlg = "true";                        	    	    
                        		}
        	    	    	}
        	    		if(updateEvent.getFullName().getLastName()!=null)
            	    	    	{
                        	    	if (!updateEvent.getFullName().getLastName().equalsIgnoreCase(outSP.getLastName()))
                        		{
                        	    	    	outSP.setLastName(updateEvent.getFullName().getLastName());
                                	    	updateFlg = "true";                        	    	    
                        		}
            	    	    	}
        	    		if(updateEvent.getServiceProviderId()!=null)
        	    		{
                        	    	if (!updateEvent.getServiceProviderId().equals(outSP.getServiceProviderId()))
                        		{
                        	    	    	outSP.setServiceProviderId(updateEvent.getServiceProviderId());
                                	    	updateFlg = "true";                        	    	    
                        		}
        	    		}
        	    		/*if(updateEvent.getLogonId()!=null)
            	    	    	{
                        	    	if (!updateEvent.getLogonId().equals(outSP.getInHouseLogonId()))
                        		{
                        	    	    	outSP.setInHouseLogonId(updateEvent.getLogonId());
                                	    	updateFlg = "true";                        	    	    
                        		}
            	    	    	}
        	    		*/
        	    		if(updateEvent.getEmail()!=null)
            	    	    	{
                        	    	if (!updateEvent.getEmail().equalsIgnoreCase(outSP.getEmail()))
                        		{
                        	    	    	outSP.setEmail(updateEvent.getEmail());
                                	    	updateFlg = "true";                        	    	    
                        		}
            	    	    	}
        	    		if(updateEvent.getStatusId()!=null)
            	    	    	{
                        	    	if (!updateEvent.getStatusId().equalsIgnoreCase(outSP.getStatusId()))
                        		{
                        	    	    	outSP.setStatusId(updateEvent.getStatusId());
                                	    	updateFlg = "true";                        	    	    
                        		}
            	    	    	}
        	    		String newPhone=null;
        	    		if(updateEvent.getWorkPhone()!=null)
        	    		    newPhone=updateEvent.getWorkPhone().getAreaCode()+updateEvent.getWorkPhone().getPrefix()+updateEvent.getWorkPhone().getLast4Digit();
                	    	if(newPhone!=null)  
                        	{
                        	    	if (!newPhone.equalsIgnoreCase(outSP.getPhoneNum()))
                        		{
                        	    	    	outSP.setPhoneNum(newPhone);
                                	    	updateFlg = "true";                        	    	    
                        		}
                        	}
                	    	/*if(newPhone!=null)  
                        	{
                        	    	if (!newPhone.equals(outSP.getWorkPhoneNum()))
                        		{
                        	    	    	outSP.setWorkPhoneNum(newPhone);
                                	    	updateFlg = "true";                        	    	    
                        		}
                        	}*/	    		
        	    		if (updateFlg.equalsIgnoreCase("true"))
                		{
                        	    	IHome home = new Home();
        				home.bind(outSP);
                		}
	    		}
		}
	}
	
	
	/**
	 * @param event
	 * @roseuid 44734FED01C1
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FED01C3
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 44734FED01CD
	 */
	public void update(Object anObject)
	{

	}
}