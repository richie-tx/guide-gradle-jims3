//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\suggestedorder\\transactions\\GetSuggestedOrdersForCourtCommand.java

package pd.supervision.suggestedorder.transactions;

import java.util.Iterator;

import pd.security.PDSecurityHelper;
import pd.supervision.suggestedorder.SuggestedOrderSOCourt;
import pd.supervision.suggestedorder.SuggestedOrderSOOffenseSOCourt;
import messaging.suggestedorder.GetSuggestedOrdersForCourtEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *  
 */
public class GetSuggestedOrdersForCourtCommand implements ICommand {

    /**
     * @roseuid 4395E86F03D0
     */
    public GetSuggestedOrdersForCourtCommand() {

    }

    /**
     * @param event
     * @roseuid 4395BC740018
     */
    public void execute(IEvent event) {
        GetSuggestedOrdersForCourtEvent requestEvent = (GetSuggestedOrdersForCourtEvent) event;
        SuggestedOrderSOOffenseSOCourt soOffenseSoCourt = null;
        SuggestedOrderSOCourt soCourt = null;
        SuggestedOrderResponseEvent sore = null;
        String userAgencyId = requestEvent.getAgencyId();
        if (userAgencyId == null || userAgencyId.equals("")) {
            userAgencyId = PDSecurityHelper.getUserAgencyId();
        }
        StringBuffer strippedOffense = new StringBuffer();
        if (requestEvent.getOffenseId() != null) {
            boolean finishedWithStrip = false;
            for (int i = 0; i < requestEvent.getOffenseId().length(); i++) {
                char theChar = requestEvent.getOffenseId().charAt(i);
                if (theChar == '0' && !finishedWithStrip) {
                    continue;
                } else {
                    finishedWithStrip = true;
                    strippedOffense.append(theChar);
                }
            }
        } 
        requestEvent.setOffenseId(strippedOffense.toString());
        requestEvent.setAgencyId(userAgencyId);

        //Retrieve suggested orders matching offense and court.
        Iterator iter = SuggestedOrderSOOffenseSOCourt.findAll(requestEvent);
        if (iter != null && iter.hasNext()) {
            while (iter.hasNext()) {
                soOffenseSoCourt = (SuggestedOrderSOOffenseSOCourt) iter.next();
                sore = new SuggestedOrderResponseEvent();
                sore.setOrderDescription(soOffenseSoCourt.getDescription());
                sore.setOrderName(soOffenseSoCourt.getName());
                sore.setSuggestedOrderId(soOffenseSoCourt.getOID().toString());
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(sore);
            }
        }
        //Retrieve default suggested order(s) for court.
        iter = SuggestedOrderSOCourt.findAll(requestEvent);
        if (iter != null && iter.hasNext()) {
            while (iter.hasNext()) {
                soCourt = (SuggestedOrderSOCourt) iter.next();
                sore = new SuggestedOrderResponseEvent();
                sore.setOrderDescription(soCourt.getDescription());
                sore.setOrderName(soCourt.getName());
                sore.setSuggestedOrderId(soCourt.getOID().toString());
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(sore);
            }
        }
    }

    /**
     * @param aString
     * @return
     */
    private String stripPreceedingZeroes(String aString) {
        String newString = aString;
        String aChar = null;
        boolean finished = false;
        for (int i = 0; i < aString.length(); i++) {
            aChar = aString.substring(i, i + 1);
            if (!aChar.equals("0")) {
                newString = aString.substring(i);
                break;
            }
        }
        return newString;
    }

    /**
     * @param event
     * @roseuid 4395BC74001A
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 4395BC74001C
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param anObject
     * @roseuid 4395BC740027
     */
    public void update(Object anObject) {

    }

}
