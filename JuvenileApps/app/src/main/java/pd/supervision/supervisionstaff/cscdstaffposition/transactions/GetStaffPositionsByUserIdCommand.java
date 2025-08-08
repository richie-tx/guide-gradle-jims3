/*
 * Created on Aug 23, 2007
 *
 */
package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;

import naming.PDCodeTableConstants;

import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrgStaffWorkgroup;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import messaging.cscdstaffposition.GetStaffPositionsByUserIdEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *
 */
public class GetStaffPositionsByUserIdCommand implements ICommand {

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) {
        GetStaffPositionsByUserIdEvent reqEvent = (GetStaffPositionsByUserIdEvent) event;
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(reqEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
        reqEvent.setStatusId((String) aCode.getOID());
        
        Iterator iter = CSCDOrgStaffWorkgroup.findAll(reqEvent);
        if (iter != null && iter.hasNext()){
            CSCDOrgStaffWorkgroup osw = null;
            CSCDSupervisionStaffResponseEvent sre = null;
            while (iter.hasNext()){
                osw = (CSCDOrgStaffWorkgroup) iter.next();
                sre = CSCDStaffPositionHelper.getSlimStaffResponseEvent(osw);
                MessageUtil.postReply(sre);
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
