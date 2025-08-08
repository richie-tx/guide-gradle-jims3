package pd.supervision.administercaseload.transactions;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;

public class GetLightCSCDStaffForUserCommand implements ICommand
{
	/**
     * @roseuid 464DAC3F0391
     */
    public GetLightCSCDStaffForUserCommand()
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202EA
     */
    public void execute(IEvent anEvent)
    {
    	GetLightCSCDStaffForUserEvent event = (GetLightCSCDStaffForUserEvent) anEvent;
    	
    	LightCSCDStaffResponseEvent resp = CSCDStaffPositionHelper.getLightCSCDStaffForUser(event);
    	if (resp != null){
    		MessageUtil.postReply(resp);
    	}
    	/* moved code to helper method 
    	Iterator staffIter = null;
    	if(event.getLogonId() != null && !"".equals(event.getLogonId())){
    		staffIter = CSCDOrganizationStaffPosition.findAll("userProfileId",event.getLogonId());
    	}else if(event.getStaffPositionId() != null && !"".equals(event.getStaffPositionId())){
    		staffIter = CSCDOrganizationStaffPosition.findAll("staffPositionId",event.getStaffPositionId());
    	}else{
    		return;
    	}
        while (staffIter.hasNext()){
        	CSCDOrganizationStaffPosition staffPosition = (CSCDOrganizationStaffPosition) staffIter.next();
        	if(staffPosition != null && PDSecurityHelper.getUserAgencyId().equals(staffPosition.getAgencyId())){
        		LightCSCDStaffResponseEvent resp = new LightCSCDStaffResponseEvent();
	    		resp.setDivisionId(staffPosition.getDivisionId());
	    		resp.setDivisionName(staffPosition.getDivisionName());
	    		resp.setJobTitleCD(staffPosition.getJobTitleCode()); 
	    		resp.setStaffPositionType(staffPosition.getPositionTypeCode());
	    		resp.setStaffPositionId(staffPosition.getStaffPositionId());
	    		resp.setSPPhoneNumber( staffPosition.getPhoneNum() );
	    		resp.setOfficerLogonId(staffPosition.getUserProfileId());
	    		resp.setSupervisorPositionId(staffPosition.getParentPositionId());
	    		resp.setStaffPositionName(staffPosition.getPositionName());
	    		resp.setParentPositionId(staffPosition.getParentPositionId());
	    		resp.setProgramUnitId(staffPosition.getProgramUnitId());
	    		resp.setProbationOfficerInd(staffPosition.getProbationOfficerInd());
	    		
	    		if(event.isOfficerNameNeeded() && (staffPosition.getUserProfileId() != null && !"".equals(staffPosition.getUserProfileId()))){
	    			UserProfile user = UserProfile.find(staffPosition.getUserProfileId());
	    			if(user != null){
	    				StringBuffer name = new StringBuffer();
	    				if(user.getLastName() != null && !"".equals(user.getLastName())){
		    				name.append(user.getLastName());
		    				if(user.getFirstName() != null && !"".equals(user.getFirstName())){
			    				name.append(", ");
			    				name.append(user.getFirstName());
		    				}
		    				if(user.getMiddleName() != null && !"".equals(user.getMiddleName())){
			    				name.append(" ");
			    				name.append(user.getMiddleName());
		    				}
	    				}else{
		    				name.append("NO OFFICER ASSIGNED");
	    				}
	    				resp.setOfficerName(name.toString());
	    				resp.setOfficerNameQualifiedByPosition(name.append(" || ").append(staffPosition.getPositionName()).toString());
	    			}
	    		}
	    		
	    		if(event.isSupervisorNameNeeded() && (staffPosition.getParentPositionId() != null && !"".equals(staffPosition.getParentPositionId()))){
	    			CSCDStaffPosition supervisor = CSCDStaffPosition.find(staffPosition.getParentPositionId());
	    			if(supervisor != null){
		    			UserProfile user = supervisor.getUserProfile();
		    			Name name = new Name();
		    			if(user != null && (user.getLastName() != null && !"".equals(user.getLastName()))){		    				
		    				name.setLastName(user.getLastName());
		    				name.setFirstName(user.getFirstName());
		    				name.setMiddleName(user.getMiddleName());    				
		    			}else{
		    				name.setLastName("NO SUPERVISOR ASSIGNED");
	    				}
		    			resp.setSupervisorName(name);
		    			resp.setSupervisorNameQualifiedByPosition(new StringBuffer(name.getFormattedName()).append(" || ").append(staffPosition.getParentPositionDesc()).toString());
		    			resp.setSupervisorPositionId(supervisor.getOID());
		    			
		    			if(event.isSupervisorSupervisorNeeded()){
			    			CSCDStaffPosition supervisorSupervisor = CSCDStaffPosition.find(supervisor.getParentPositionId());
			    			if(supervisorSupervisor != null){
				    			user = supervisorSupervisor.getUserProfile();
				    			Name name1 = new Name();
				    			if(user != null && (user.getLastName() != null && !"".equals(user.getLastName()))){		    				
				    				name1.setLastName(user.getLastName());
				    				name1.setFirstName(user.getFirstName());
				    				name1.setMiddleName(user.getMiddleName());    				
				    			}else{
				    				name1.setLastName("NO SUPERVISOR ASSIGNED");
			    				}
				    			resp.setSupervisorSupervisorName(name1);
				    			resp.setSupervisorSupervisorPositionId(supervisorSupervisor.getOID());
			    			}
		    			}
	    			}
	    		}
	    		
	    		if(event.isCourtsNeeded()){
	    			Iterator iterator = CSCDStaffPositionCourt.findAll("staffPositionId",resp.getStaffPositionId());
	    			Court court = null;
                    while (iterator.hasNext()){
                        court = (Court) iterator.next();
                        resp.addCourt(court.getOID());
                    }
	    		}
	        	MessageUtil.postReply(resp);
    		}        	
        } */
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
