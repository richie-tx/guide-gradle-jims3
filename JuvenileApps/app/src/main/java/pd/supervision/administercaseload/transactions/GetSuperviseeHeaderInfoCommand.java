package pd.supervision.administercaseload.transactions;

import java.util.ArrayList;
import java.util.List;

import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.party.GetPartyDataEvent;
import messaging.supervisionorder.GetSupervisionOrderDetailsEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import pd.contact.party.Party;
import pd.contact.user.UserProfile;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

public class GetSuperviseeHeaderInfoCommand implements ICommand
{

    /**
     * @roseuid 4643602E010A
     */
    public GetSuperviseeHeaderInfoCommand()
    {

    }

    /**
     * @param event
     * @roseuid 464342310396
     */
    public void execute(IEvent anEvent)
    {
    	GetSuperviseeHeaderInfoEvent event = (GetSuperviseeHeaderInfoEvent) anEvent;
    	SuperviseeInfoResponseEvent resp = new SuperviseeInfoResponseEvent();
    	
    	Supervisee se = Supervisee.findByDefendantId(event.getDefendantId());
		if(se != null){
			resp.setSuperviseeFound(true);
			resp.setDefendantId(event.getDefendantId());
			resp.setPositionId(se.getAssignedStaffPositionId());
			String sprLevelId = se.getSupervisionLevelId();
			if(sprLevelId != null && !sprLevelId.equals("")){
				SupervisionLevelOfServiceCode code = SupervisionLevelOfServiceCode.find(sprLevelId);
				resp.setSupervisionLevel(code.getDescription());
			}
			
			// Get probation Officer
			CSCDStaffPosition sp = CSCDStaffPosition.find(se.getAssignedStaffPositionId());
			if(sp != null){
				String probationOfficerId = sp.getUserProfileId();
				resp.setProbationOfficerInd(sp.getProbationOfficerInd());
				UserProfile officer = null;
				if(probationOfficerId != null && !probationOfficerId.trim().equals("") && probationOfficerId.indexOf("*") < 0){
				    officer = UserProfile.find(probationOfficerId);
                    if (officer != null) {
                        resp.setOfficerName(officer.getName().getFormattedName());
                    }
				}else{
					resp.setOfficerName("No Officer Assigned");
				}
				
				// Get Program Unit: we may need to parse Program Unit from the description
				Organization org = sp.getOrganization();
				if(org != null){
					resp.setProgramUnit(org.getDescription());
				}
			}
			// Get disposition
			//retrieve orders for defendant SPN
	        List<SupervisionOrder> defendantOrders = 
	        	CollectionUtil.iteratorToList(
	        			SupervisionOrder.findAll("defendantId", 
	        					event.getDefendantId()));
	     	//convert orders to response objects
	        GetSupervisionOrderDetailsEvent getDetails = new GetSupervisionOrderDetailsEvent();
	        getDetails.setDeleteAction(false);
	        
	        int numOrders = defendantOrders.size();
	        List<SupervisionOrderDetailResponseEvent> 
	        	defendantOrdersResponses = 
	        		new ArrayList<SupervisionOrderDetailResponseEvent>(numOrders);
	        for (int i=0; i<numOrders;i++)
	        {
	        	defendantOrdersResponses.add(SupervisionOrderHelper.
	        			getLightSupervisionOrderResponseEvent(
	        				defendantOrders.get(i)));
	        }
	        	//return response objects
	        MessageUtil.postReplies(defendantOrdersResponses);
		}
		
		GetPartyDataEvent getPartyData = new GetPartyDataEvent();
		getPartyData.setSpn(event.getDefendantId());
		getPartyData.setCurrentNameInd("Y");

		Party party = Party.find(getPartyData);
		if(party != null){
			resp.setDefendantName(party.getFullNameWithLastNameFirst());			
		} 	
		MessageUtil.postReply(resp);
    }

    /**
     * @param event
     * @roseuid 4643423103C3
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4643423103D2
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4643423103D4
     */
    public void update(Object anObject)
    {

    }
}
