//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.posttrial.transactions;

import java.util.Iterator;

import messaging.posttrial.GetCaseAssignmentOfficersEvent;
import messaging.posttrial.reply.CaseAssignmentOfficerResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.contact.user.UserProfile;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCaseAssignmentOfficersCommand implements ICommand 
{   
   /**
    * @roseuid 473B887E0371
    */
   public GetCaseAssignmentOfficersCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(IEvent event) 
   { 
       GetCaseAssignmentOfficersEvent gEvent = (GetCaseAssignmentOfficersEvent) event;
	   Iterator iterator = CSCDStaffPosition.findAll("organizationId", gEvent.getOrganizationId());
	   
	   CaseAssignmentOfficerResponseEvent resp = null;
	   while(iterator.hasNext()){
		   CSCDStaffPosition staff = (CSCDStaffPosition) iterator.next();
		   resp = new CaseAssignmentOfficerResponseEvent();
		   if(staff.getUserProfileId() != null && !staff.getUserProfileId().equals("")){
			   UserProfile userProfile = staff.getUserProfile();
			   if(userProfile != null){
				   resp.setOfficerLastName(userProfile.getLastName());
				   resp.setOfficerFirstName(userProfile.getFirstName());
				   resp.setOfficerMiddleName(userProfile.getMiddleName());
			   }
		   }
		   resp.setOfficerPosition(staff.getPositionName());
		   resp.setStaffPositionId(staff.getOID());		
		   MessageUtil.postReply(resp);
	   }
   }
  
   /**
    * @param event
    * @roseuid 473B75560240
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75560242
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B75560244
    */
   public void update(Object anObject) 
   {
    
   }
}
