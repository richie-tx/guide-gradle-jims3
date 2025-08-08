/*
 * Created on Aug 8, 2007
 *
 */
package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;

import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.user.UserProfile;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import messaging.cscdstaffposition.GetStaffPositionsForSupervisionOrderEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;


/**
 * @author dgibler
 *
 */
public class GetStaffPositionsForSupervisionOrderCommand implements ICommand {

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) {
        GetStaffPositionsForSupervisionOrderEvent reqEvent = (GetStaffPositionsForSupervisionOrderEvent) event;
        
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(reqEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);

        //Retrieve all CLOs
        this.retrievePositions(reqEvent.getAgencyId(), PDCodeTableConstants.STAFF_JOB_TITLE_CLO, aCode.getOID());
        
        //Retrieve all CLO Floaters.
        this.retrievePositions(reqEvent.getAgencyId(), PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER, aCode.getOID());
     }

    private void retrievePositions(String agencyId, String positionType, String activeStatusId) {
    	
        GetStaffPositionsForSupervisionOrderEvent getPositionsEvt = new GetStaffPositionsForSupervisionOrderEvent();
        getPositionsEvt.setAgencyId(agencyId);
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(agencyId, PDCodeTableConstants.STAFF_JOB_TITLE, positionType);
        getPositionsEvt.setJobTitleId((String) aCode.getOID());
        getPositionsEvt.setStatusId(activeStatusId);
        
        Iterator iter = CSCDOrganizationStaffPosition.findAll(getPositionsEvt);
        
        if (iter != null && iter.hasNext()){
            CSCDOrganizationStaffPosition staffPos = null;
            SupervisionStaffResponseEvent responseEvent = null;
            while (iter.hasNext()){
                staffPos = (CSCDOrganizationStaffPosition) iter.next();
                responseEvent = new SupervisionStaffResponseEvent();
                responseEvent.setCourtNum(PDConstants.BLANK);
                responseEvent.setLogonId(staffPos.getUserProfileId());
                responseEvent.setPositionName(staffPos.getPositionName());
                UserProfile up = staffPos.getUserProfile();
                if (up != null)
                {
	    		    responseEvent.setFirstName(up.getFirstName());
	    		    responseEvent.setMiddleName(up.getMiddleName());
   	    		    responseEvent.setLastName(up.getLastName());
                } 
                responseEvent.setSupervisionStaffId((String) staffPos.getOID());
                MessageUtil.postReply(responseEvent);
            }
        }
	}

	/* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {
    }


}
