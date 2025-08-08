/*
 * Created on Apr 13, 2007
 *
 */
package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import naming.PDConstants;

import pd.security.PDSecurityHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import messaging.cscdstaffposition.GetStaffPositionEvent;
import messaging.cscdstaffposition.GetSupervisorsEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *  
 */
public class GetSupervisorsCommand implements ICommand {

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) {
        GetSupervisorsEvent reqEvent = (GetSupervisorsEvent) event;

        GetStaffPositionEvent getDivMgrEvent = new GetStaffPositionEvent();
        getDivMgrEvent.setOrganizationId(reqEvent.getDivisionId());
        if (reqEvent.getAgencyId() != null && !reqEvent.getAgencyId().equals(PDConstants.BLANK)){
            getDivMgrEvent.setAgencyId(reqEvent.getAgencyId());
        } else {
            getDivMgrEvent.setAgencyId(PDSecurityHelper.getUserAgencyId()); 
        }
        CSCDStaffPosition divisionMgrPos = CSCDStaffPositionHelper.getDivisionManager(getDivMgrEvent);
        if (divisionMgrPos != null) {
            CSCDStaffPosition staffPosition = null;
            if (reqEvent.getStaffPositionId() != null && !reqEvent.getStaffPositionId().equals(PDConstants.BLANK)){
                staffPosition = CSCDStaffPosition.find(reqEvent.getStaffPositionId());
            }
            CSCDSupervisionStaffResponseEvent sre = CSCDStaffPositionHelper.getStaffResponseEvent(divisionMgrPos, null, true);
            if (staffPosition != null && this.isSupervisoryPosition(staffPosition.getPositionTypeId())){
                this.filterOutStaffPositionBeingProcessed(reqEvent.getStaffPositionId(), sre);
            }
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
        }
    }
    /**
     * @param staffPosition
     * @return
     */
    private boolean isSupervisoryPosition(String positionTypeId){
        
        boolean isSupervisoryPosition = false;
        Map supCodeMap = CSCDStaffPositionHelper.getSupervisoryPositionTypeMapByOID();
        
        if (supCodeMap.get(positionTypeId) != null){
            isSupervisoryPosition = true;
        }
        
        return isSupervisoryPosition;
    }
   /**
     * @param staffPositionId
     * @param sre
     */
    private void filterOutStaffPositionBeingProcessed(String staffPositionId, CSCDSupervisionStaffResponseEvent sre) {
       if (sre.getChildren() != null && sre.getChildren().size() > 0){
           Iterator iter = sre.getChildren().iterator();
           ArrayList filteredChildren = new ArrayList();
           CSCDSupervisionStaffResponseEvent childRe = null;
           while (iter.hasNext()){
              childRe = (CSCDSupervisionStaffResponseEvent) iter.next();
              if (!childRe.getStaffPositionId().equals(staffPositionId)){
                  filteredChildren.add(childRe);
                  this.filterOutStaffPositionBeingProcessed(staffPositionId, childRe);
              }
            }
           sre.setChildren(filteredChildren);
       }
        
    }
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {
        // TODO Auto-generated method stub

    }

}
