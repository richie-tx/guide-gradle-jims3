/*
 * Created on Nov 3, 2006
 *
 */
package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;

import pd.supervision.supervisionorder.SupervisionPeriod;
import messaging.supervisionorder.GetPriorSupervisionPeriodsEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *  
 */
public class GetPriorSupervisionPeriodsCommand implements ICommand {

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) {

        GetPriorSupervisionPeriodsEvent theEvent = (GetPriorSupervisionPeriodsEvent) event;
        if (theEvent.getSpn().length() < 8){
            StringBuffer sb = new StringBuffer(theEvent.getSpn());
            while (sb.length() < 8){
                sb.insert(0, "0");
            }
            theEvent.setSpn(sb.toString());
        } else if (theEvent.getSpn().length() > 8){
            //spn length greater than 8 causes jdbc exception.
            return;
        }
        
        Iterator iter = SupervisionPeriod.findAll(theEvent);
        if (iter != null && iter.hasNext()) {
            SupervisionPeriod supPer = null;
            SupervisionPeriodResponseEvent responseEvent = null;
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

            while (iter.hasNext()) {
                supPer = (SupervisionPeriod) iter.next();
                responseEvent = this.getResponseEvent(supPer);
                dispatch.postEvent(responseEvent);
            }
        }
    }

    /**
     * @param supPer
     * @return
     */
    private SupervisionPeriodResponseEvent getResponseEvent(SupervisionPeriod supPer) {
        SupervisionPeriodResponseEvent spre = new SupervisionPeriodResponseEvent();
        spre.setAgencyId(supPer.getAgencyId());
        spre.setSupervisionPeriodId((String)supPer.getOID());
        spre.setSupervisionBeginDate(supPer.getSupervisionBeginDate());
        spre.setSupervisionEndDate(supPer.getSupervisionEndDate());
        return spre;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {

    }

}
