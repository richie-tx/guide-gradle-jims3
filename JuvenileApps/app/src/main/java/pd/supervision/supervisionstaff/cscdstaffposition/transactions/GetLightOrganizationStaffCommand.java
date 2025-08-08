// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\GetOrganizationsCommand.java
// Richard Young
package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;

import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import messaging.cscdstaffposition.GetLightOrganizationStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;

public class GetLightOrganizationStaffCommand implements ICommand {

	/**
	 * @roseuid 460BFAA203A7
	 */
	public GetLightOrganizationStaffCommand() {
	}

	/**
	 * @param event
	 * @roseuid 460BD54802FE
	 */
	public void execute(IEvent event) {
		
		GetLightOrganizationStaffEvent divMgrEvent = (GetLightOrganizationStaffEvent)event;
		
		Iterator orgIter = CSCDOrganizationStaffPosition.findAll(divMgrEvent);

		if (orgIter != null && orgIter.hasNext()){
            CSCDOrganizationStaffPosition staffPos = null;
            CSCDSupervisionStaffResponseEvent responseEvent = null;
            while (orgIter.hasNext()){
                staffPos = (CSCDOrganizationStaffPosition) orgIter.next();
                responseEvent = new CSCDSupervisionStaffResponseEvent();
                responseEvent.setPositionTypeId(staffPos.getPositionTypeCode());
                responseEvent.setProgramUnitId(staffPos.getProgramUnitId());
                responseEvent.setOfficerTypeDesc(staffPos.getOfficerTypeDesc());
                responseEvent.setAssignedLogonId(staffPos.getUserProfileId());
                responseEvent.setDivisionName(staffPos.getDivisionName());
                responseEvent.setPositionName(staffPos.getPositionName());
                responseEvent.setPositionTypeDesc(staffPos.getPositionTypeDesc());
                responseEvent.setPositionStatusDesc(staffPos.getPositionStatusDesc());;
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
