package pd.supervision.suggestedorder.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import pd.security.PDSecurityHelper;
import pd.supervision.suggestedorder.SuggestedOrder;
import pd.supervision.suggestedorder.SuggestedOrderCondition;
import pd.supervision.suggestedorder.SuggestedOrderCourt;
import pd.supervision.suggestedorder.SuggestedOrderOffense;
import messaging.suggestedorder.SuggestedOrderConditionRequestEvent;
import messaging.suggestedorder.SuggestedOrderCourtRequestEvent;
import messaging.suggestedorder.SuggestedOrderOffenseRequestEvent;
import messaging.suggestedorder.UpdateSuggestedOrderEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *  
 */
public class UpdateSuggestedOrderCommand implements ICommand {

    /**
     * Handles creates and updates of SuggestedOrder and associated child
     * objects.
     * 
     * @roseuid 433AF3C600F2
     */
    public UpdateSuggestedOrderCommand() {

    }

    /**
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     * @param event
     * @roseuid 433AF04F033A
     */
    public void execute(IEvent event) {
        UpdateSuggestedOrderEvent updateEvent = (UpdateSuggestedOrderEvent) event;
        String suggestedOrderId = updateEvent.getSuggestedOrderId();
        SuggestedOrder suggestedOrder = null;
        if (suggestedOrderId != null && !suggestedOrderId.equals("")) {
            suggestedOrder = SuggestedOrder.find(suggestedOrderId);
        } else {
            suggestedOrder = new SuggestedOrder();
            String userAgency = null;
            if (updateEvent.getAgencyId() != null) {
                userAgency = updateEvent.getAgencyId();
            } else {
                userAgency = PDSecurityHelper.getUserAgencyId();
            }
            suggestedOrder.setAgencyId(userAgency);
        }
        suggestedOrder.setIncludedConditionsId(updateEvent.getIncludedConditionsInd());
        suggestedOrder.setOrderDescription(updateEvent.getDescription());
        suggestedOrder.setOrderName(updateEvent.getSuggestedOrderName());

        IHome home = new Home();
        home.bind(suggestedOrder);

        SuggestedOrderResponseEvent sore = suggestedOrder.getResponseEvent();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        dispatch.postEvent(sore);

        this.processChildObjects(updateEvent, suggestedOrder);

    }

    /**
     * Updates child objects associated to SuggestedOrder.
     * 
     * @param updateEvent
     * @param suggestedOrder
     */
    private void processChildObjects(UpdateSuggestedOrderEvent updateEvent, SuggestedOrder suggestedOrder) {
        this.updateOffenses(updateEvent, suggestedOrder);
        this.updateCourts(updateEvent, suggestedOrder);
        this.updateConditions(updateEvent, suggestedOrder);
    }

    /**
     * Inserts SuggestedOrderCondition onto SuggestedOrder.
     * 
     * @param updateEvent
     * @param suggestedOrder
     */
    private void updateConditions(UpdateSuggestedOrderEvent updateEvent, SuggestedOrder suggestedOrder) {
        Collection selectedConditions = MessageUtil.compositeToCollection(updateEvent,
                SuggestedOrderConditionRequestEvent.class);
        Collection existingConditions = suggestedOrder.getConditions();
        HashMap existingConditionsMap = new HashMap();
        SuggestedOrderCondition re = null;

        if (existingConditions != null) {
            Iterator iter = existingConditions.iterator();
            while (iter.hasNext()) {
                re = (SuggestedOrderCondition) iter.next();
                existingConditionsMap.put(re.getConditionId(), re);
            }
        }

        HashMap selectedConditionsMap = new HashMap();

        if (selectedConditions != null) {
            SuggestedOrderConditionRequestEvent selectedCondition = null;

            Iterator iter = selectedConditions.iterator();
            while (iter.hasNext()) {
                selectedCondition = (SuggestedOrderConditionRequestEvent) iter.next();
                selectedConditionsMap.put(selectedCondition.getConditionId(), selectedCondition);
            }
        }

        Collection conditionsToBeRemoved = new ArrayList();

        if (existingConditions != null) {
            SuggestedOrderCondition existingCondition = null;
            Iterator iter = existingConditions.iterator();
            while (iter.hasNext()) {
                existingCondition = (SuggestedOrderCondition) iter.next();
                if (selectedConditionsMap.get(existingCondition.getConditionId()) == null) {
                    conditionsToBeRemoved.add(existingCondition);
                }
            }
        }

        Collection conditionsToBeInserted = new ArrayList();

        if (selectedConditions != null) {
            SuggestedOrderConditionRequestEvent selectedCondition = null;
            SuggestedOrderCondition existingCondition = null;
            Iterator iter = selectedConditions.iterator();
            Integer selectedCondSeqNum = null;
            Integer existingCondSeqNum = null;
            while (iter.hasNext()) {
                selectedCondition = (SuggestedOrderConditionRequestEvent) iter.next();
                existingCondition = (SuggestedOrderCondition) existingConditionsMap.get(selectedCondition
                        .getConditionId());
                if (existingCondition == null) {
                    conditionsToBeInserted.add(selectedCondition);
                } else {
                    selectedCondSeqNum = new Integer(selectedCondition.getSeqNum());
                    existingCondSeqNum = new Integer(existingCondition.getSeqNum());
                    if (!existingCondSeqNum.equals(selectedCondSeqNum)) {
                        existingCondition.setSeqNum(selectedCondSeqNum.intValue());
                    }
                }
            }
        }

        if (conditionsToBeInserted != null) {
            SuggestedOrderConditionRequestEvent conditionToBeInserted = null;
            SuggestedOrderCondition soCondition = null;
            Integer conditionToBeInsertedSeqNum = null;
            Iterator iter = conditionsToBeInserted.iterator();
            while (iter.hasNext()) {
                conditionToBeInserted = (SuggestedOrderConditionRequestEvent) iter.next();
                soCondition = new SuggestedOrderCondition();
                soCondition.setSuggestedOrderId(suggestedOrder.getSuggestedOrderId());
                soCondition.setConditionId(conditionToBeInserted.getConditionId());
                conditionToBeInsertedSeqNum = new Integer(conditionToBeInserted.getSeqNum());
                soCondition.setSeqNum(conditionToBeInsertedSeqNum.intValue());
                suggestedOrder.insertConditions(soCondition);
            }
        }

        if (conditionsToBeRemoved != null) {
            SuggestedOrderCondition conditionToBeRemoved = null;
            Iterator iter = conditionsToBeRemoved.iterator();
            while (iter.hasNext()) {
                conditionToBeRemoved = (SuggestedOrderCondition) iter.next();
                suggestedOrder.removeConditions(conditionToBeRemoved);
            }
        }
    }

    /**
     * Inserts SuggestedOrderCourt onto SuggestedOrder.
     * 
     * @param updateEvent
     * @param suggestedOrder
     */
    private void updateCourts(UpdateSuggestedOrderEvent updateEvent, SuggestedOrder suggestedOrder) {
        Collection selectedCourts = MessageUtil.compositeToCollection(updateEvent,
                SuggestedOrderCourtRequestEvent.class);
        Collection existingCourts = suggestedOrder.getCourts();
        HashMap existingCourtsMap = new HashMap();

        if (existingCourts != null) {
            SuggestedOrderCourt re = null;
            Iterator iter = existingCourts.iterator();
            while (iter.hasNext()) {
                re = (SuggestedOrderCourt) iter.next();
                existingCourtsMap.put(re.getCourtId(), re);
            }
        }

        HashMap selectedCourtsMap = new HashMap();

        if (selectedCourts != null) {
            SuggestedOrderCourtRequestEvent selectedCourt = null;
            Iterator iter = selectedCourts.iterator();
            while (iter.hasNext()) {
                selectedCourt = (SuggestedOrderCourtRequestEvent) iter.next();
                selectedCourtsMap.put(selectedCourt.getCourtId(), selectedCourt);
            }
        }

        Collection courtsToBeRemoved = new ArrayList();

        if (existingCourts != null) {
            SuggestedOrderCourt existingCourt = null;
            Iterator iter = existingCourts.iterator();
            while (iter.hasNext()) {
                existingCourt = (SuggestedOrderCourt) iter.next();
                if (selectedCourtsMap.get(existingCourt.getCourtId()) == null) {
                    courtsToBeRemoved.add(existingCourt);
                }
            }
        }

        Collection courtsToBeInserted = new ArrayList();

        if (selectedCourts != null) {
            SuggestedOrderCourtRequestEvent selectedCourt = null;
            Iterator iter = selectedCourts.iterator();
            while (iter.hasNext()) {
                selectedCourt = (SuggestedOrderCourtRequestEvent) iter.next();
                if (existingCourtsMap.get(selectedCourt.getCourtId()) == null) {
                    courtsToBeInserted.add(selectedCourt);
                }
            }
        }

        if (courtsToBeInserted != null) {
            SuggestedOrderCourtRequestEvent courtToBeInserted = null;
            SuggestedOrderCourt soCourt = null;
            Iterator iter = courtsToBeInserted.iterator();
            while (iter.hasNext()) {
                courtToBeInserted = (SuggestedOrderCourtRequestEvent) iter.next();
                soCourt = new SuggestedOrderCourt();
                soCourt.setSuggestedOrderId(suggestedOrder.getSuggestedOrderId());
                soCourt.setCourtId(courtToBeInserted.getCourtId());
                suggestedOrder.insertCourts(soCourt);
            }
        }
        if (courtsToBeRemoved != null) {
            SuggestedOrderCourt courtToBeRemoved = null;
            Iterator iter = courtsToBeRemoved.iterator();
            while (iter.hasNext()) {
                courtToBeRemoved = (SuggestedOrderCourt) iter.next();
                suggestedOrder.removeCourts(courtToBeRemoved);
            }
        }
    }

    /**
     * Inserts SuggestedOrderOffense onto SuggestedOrder.
     * 
     * @param updateEvent
     * @param suggestedOrder
     */
    private void updateOffenses(UpdateSuggestedOrderEvent updateEvent, SuggestedOrder suggestedOrder) {
        Collection selectedOffenses = MessageUtil.compositeToCollection(updateEvent,
                SuggestedOrderOffenseRequestEvent.class);
        Collection existingOffenses = suggestedOrder.getOffenses();
        HashMap existingOffensesMap = new HashMap();

        if (existingOffenses != null) {
            SuggestedOrderOffense re = null;
            Iterator iter = existingOffenses.iterator();
            while (iter.hasNext()) {
                re = (SuggestedOrderOffense) iter.next();
                existingOffensesMap.put(re.getOffenseId(), re);
            }
        }

        HashMap selectedOffensesMap = new HashMap();

        if (selectedOffenses != null) {
            SuggestedOrderOffenseRequestEvent selectedOffense = null;
            Iterator iter = selectedOffenses.iterator();
            while (iter.hasNext()) {
                selectedOffense = (SuggestedOrderOffenseRequestEvent) iter.next();
                selectedOffensesMap.put(selectedOffense.getOffenseId(), selectedOffense);
            }
        }

        Collection offensesToBeRemoved = new ArrayList();

        if (existingOffenses != null) {
            SuggestedOrderOffense existingOffense = null;
            Iterator iter = existingOffenses.iterator();
            while (iter.hasNext()) {
                existingOffense = (SuggestedOrderOffense) iter.next();
                if (selectedOffensesMap.get(existingOffense.getOffenseId()) == null) {
                    offensesToBeRemoved.add(existingOffense);
                }
            }
        }

        Collection offensesToBeInserted = new ArrayList();

        if (selectedOffenses != null) {
            SuggestedOrderOffenseRequestEvent selectedOffense = null;
            Iterator iter = selectedOffenses.iterator();
            while (iter.hasNext()) {
                selectedOffense = (SuggestedOrderOffenseRequestEvent) iter.next();
                if (existingOffensesMap.get(selectedOffense.getOffenseId()) == null) {
                    offensesToBeInserted.add(selectedOffense);
                }
            }
        }

        if (offensesToBeInserted != null) {
            SuggestedOrderOffenseRequestEvent offenseToBeInserted = null;
            SuggestedOrderOffense soOffense = null;
            Iterator iter = offensesToBeInserted.iterator();
            while (iter.hasNext()) {
                offenseToBeInserted = (SuggestedOrderOffenseRequestEvent) iter.next();
                soOffense = new SuggestedOrderOffense();
                soOffense.setSuggestedOrderId(suggestedOrder.getSuggestedOrderId());
                soOffense.setOffenseId(offenseToBeInserted.getOffenseId());
                suggestedOrder.insertOffenses(soOffense);
            }
        }
        if (offensesToBeRemoved != null) {
            SuggestedOrderOffense offenseToBeRemoved = null;
            Iterator iter = offensesToBeRemoved.iterator();
            while (iter.hasNext()) {
                offenseToBeRemoved = (SuggestedOrderOffense) iter.next();
                suggestedOrder.removeOffenses(offenseToBeRemoved);
            }
        }
    }

    /**
     * @param event
     * @roseuid 433AF04F0344
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 433AF04F0346
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 433AF3C60111
     */
    public void update(Object updateObject) {

    }
}
