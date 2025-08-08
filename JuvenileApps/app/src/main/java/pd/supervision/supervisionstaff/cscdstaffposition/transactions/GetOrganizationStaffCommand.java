// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\GetOrganizationsCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;

import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import messaging.cscdstaffposition.GetOrganizationStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;

public class GetOrganizationStaffCommand implements ICommand {

	/**
	 * @roseuid 460BFAA203A7
	 */
	public GetOrganizationStaffCommand() {
	}

	/**
	 * @param event
	 * @roseuid 460BD54802FE
	 */
	public void execute(IEvent event) {
		
		GetOrganizationStaffEvent divMgrEvent = (GetOrganizationStaffEvent)event;
		
		String agency_id = divMgrEvent.getAgencyId();
		Iterator orgIter = CSCDOrganizationStaffPosition.findAll("agencyId",agency_id);

		if (orgIter != null && orgIter.hasNext()){
            CSCDOrganizationStaffPosition staffPos = null;
            CSCDSupervisionStaffResponseEvent responseEvent = null;
            while (orgIter.hasNext()){
                staffPos = (CSCDOrganizationStaffPosition) orgIter.next();
                responseEvent = new CSCDSupervisionStaffResponseEvent();
                responseEvent.setAssignedLogonId(staffPos.getUserProfileId());
                responseEvent.setCjadNum(staffPos.getCjadNum());
                responseEvent.setCstsOfficerTypeId(staffPos.getCstsOfficerTypeId());
                responseEvent.setDivisionName(staffPos.getDivisionName());
                responseEvent.setEffectiveDate(staffPos.getEffectiveDate());
                responseEvent.setProbationOfficerInd(staffPos.getProbationOfficerInd());
                responseEvent.setPositionName(staffPos.getPositionName());
                responseEvent.setPositionTypeId(staffPos.getPositionTypeCode());         
                responseEvent.setOrganizationId(staffPos.getDivisionId());
                responseEvent.setStaffPositionId(staffPos.getStaffPositionId());
                responseEvent.setParentPositionId(staffPos.getParentPositionId());
                if(staffPos.getUserProfile() != null){
                	responseEvent.setAssignedName(
                    	new Name(staffPos.getUserProfile().getFirstName(),
                    	staffPos.getUserProfile().getMiddleName(),
            			staffPos.getUserProfile().getLastName()));
                    }else{
                    	responseEvent.setAssignedName(new Name());
                    }
                responseEvent.setAssignedNameQualifiedByPositionName(staffPos.getPositionName());
                MessageUtil.postReply(responseEvent);
            }
        }

	}


	/**
	 * @param event
	 * @roseuid 460BD5480300
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 460BD548030C
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 460BFAA203C6
	 */
	public void update(Object updateObject) {

	}
}
