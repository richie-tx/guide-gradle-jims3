package pd.supervision.administercaseload.transactions;

import messaging.administercaseload.GetAdditionalCaseInfoEvent;
import messaging.administercaseload.reply.AdditionalCaseInfoResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import pd.contact.user.UserProfile;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

public class GetAdditionalCaseInfoCommand implements ICommand
{
	/**
     * @roseuid 464DAC3F0391
     */
    public GetAdditionalCaseInfoCommand()
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202EA
     */
    public void execute(IEvent anEvent) {
    	GetAdditionalCaseInfoEvent event = (GetAdditionalCaseInfoEvent) anEvent;     	  	    	
    	if (event.getOfficerPositionId() != null && !"".equals(event.getOfficerPositionId())) {
        	CSCDStaffPosition staff = CSCDStaffPosition.find(event.getOfficerPositionId());
        	AdditionalCaseInfoResponseEvent resp = new AdditionalCaseInfoResponseEvent();  
			resp.setStaffPositionId(event.getOfficerPositionId());
        	if(staff != null){
        		UserProfile userProfile = staff.getUserProfile();
        		if(userProfile != null) {        			            		
            		resp.setOfficerLogonId(userProfile.getLogonId());
			        IName name = new NameBean();
			        name.setFirstName(userProfile.getFirstName());
			        name.setLastName(userProfile.getLastName());
			        resp.setOfficerName(name);			        
    			}else{
    		    	IName name = new NameBean();
    		    	name.setFirstName(PDConstants.BLANK);
                    name.setMiddleName(PDConstants.BLANK);
                    name.setLastName("No Officer Assigned"); 
                    resp.setOfficerName(name);
                }
        	}
        	MessageUtil.postReply(resp); 
        }		
    }

    /**
     * @param event
     * @roseuid 464DA69202F9
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202FB
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 464DA6920308
     */
    public void update(Object anObject)
    {

    }

}
