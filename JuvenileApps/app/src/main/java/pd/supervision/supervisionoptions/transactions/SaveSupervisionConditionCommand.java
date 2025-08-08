// Source file:
// C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\SaveSupervisionConditionCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.supervision.SupervisionCode;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.ConditionSupervisionOption;
import pd.supervision.supervisionoptions.StringSet;
import pd.supervision.supervisionoptions.VariableElementHelper;
import messaging.supervisionoptions.SaveSupervisionConditionEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class SaveSupervisionConditionCommand implements ICommand {

    /**
     * @roseuid 42F7C44F02AF
     */
    public SaveSupervisionConditionCommand() {

    }

    /**
     * @param event
     * @roseuid 42F79A3A030F
     */
    public void execute(IEvent event) {
        SaveSupervisionConditionEvent evt = (SaveSupervisionConditionEvent) event;
        Condition cond = null;

        if (evt.getConditionId() == null) {
            // new condition
            cond = new Condition();
        } else {
            // existing condition
            cond = Condition.find(evt.getConditionId());
        }

        // check for duplicate name
        if (cond.isDuplicate(evt.getConditionId(), evt.getName(), evt.getAgencyId())) {
            DuplicationNameErrorEvent errorEvent = new DuplicationNameErrorEvent();
            errorEvent.setName(evt.getName());
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            dispatch.postEvent(errorEvent);
        } else {
            cond.setAgencyId(evt.getAgencyId());
            cond.setDescription(evt.getDescription());
            cond.setUnformattedDesc(evt.getUnformattedDesc());
            cond.setSpanishDescription(evt.getSpanishDescription());
            cond.setIsStandard(evt.isStandard());
            cond.setName(evt.getName());
            cond.setNotes(evt.getNotes());
            cond.setSeverityLevelId(evt.getSeverityLevelId());
            cond.setJurisdictionId(evt.getJurisdictionId());
            cond.setEventCount(evt.getEventCountValue());
            cond.setPeriodValue(evt.getEventPeriodValue());
            cond.setPeriodId(evt.getEventPeriodTypeId());
            cond.setEffectiveDate(evt.getEffectiveDate());
            cond.setInactiveDate(evt.getInactiveDate());
            cond.validateAssociationsForGroups(evt.getGroupId());
            cond.setGroupId(evt.getGroupId());

            //TODO Out of scope
            //   private Collection evt.taskRecipients;
            //   private String evt.taskSubject;
            //   private int evt.taskDueBy;
            //   private String evt.emailNotificationTo;
            
            //-------------documents--------------------------------//
            StringSet exDocuments = createCondSet(cond.getDocuments());
            StringSet passedDocuments = createCondSet(evt.getDocuments());
            Set newDocuments = passedDocuments.complement(exDocuments);
            Set removedDocuments = exDocuments.complement(passedDocuments);
            //new documents
            for(Iterator iter = newDocuments.iterator(); iter.hasNext(); ) {
                String docId = (String)iter.next();
                if (!docId.equals("")) {
                    SupervisionCode code = SupervisionCode.find(docId);
                    cond.insertDocuments(code);
                }
            }
            // removed documents
            for(Iterator iter = removedDocuments.iterator(); iter.hasNext(); ) {
                String docId = (String)iter.next();
                if (!docId.equals("")) {
                    SupervisionCode code = SupervisionCode.find(docId);
                    cond.removeDocuments(code);
                }
            }
            
            //--------------------------EventTypes--------------------------------//
            StringSet exEventTypes = createCondSet(cond.getSupervisionEventTypes());
            StringSet passedEventTypes = createCondSet(evt.getEventTypes());
            Set newEventTypes = passedEventTypes.complement(exEventTypes);
            Set removedEventTypes = exEventTypes.complement(passedEventTypes);
            //new EventTypes
            for(Iterator iter = newEventTypes.iterator(); iter.hasNext(); ) {
                String eventTypeId = (String)iter.next();
                if (!eventTypeId.equals("")) {
                    SupervisionCode code = SupervisionCode.find(eventTypeId);
                    if (code != null) {
                        cond.insertSupervisionEventTypes(code);
                    }
                }
            }
            // removed EventTypes
            for(Iterator iter = removedEventTypes.iterator(); iter.hasNext(); ) {
                String eventTypeId = (String)iter.next();
                if (!eventTypeId.equals("")) {
                    SupervisionCode code = SupervisionCode.find(eventTypeId);
                    cond.removeSupervisionEventTypes(code);
                }
            }
            // 
            // ER#JIMS200025776 Enhance Supervision Type into Conditions
            cond.clearSupervisionTypes();
            Iterator supervisionTypes = evt.getSupervisionTypes().iterator();
            while (supervisionTypes.hasNext()) {
                String supervisionTypeId = (String) supervisionTypes.next();
                if (!supervisionTypeId.equals("")) {
                    Code code = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, supervisionTypeId);
                    if (code != null) {
                        cond.insertSupervisionTypes(code);
                    }
                }

            }
            //

            if (VariableElementHelper.saveConditionVariableElements(cond, evt.getVariableElements(), ConditionSupervisionOption.class)) {
                cond.validateAssociationsForCourts();
            }
            // POST RESP EVENT BACK
            ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent();
            reply.setConditionId(cond.getOID().toString());
            reply.setName(cond.getName());
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            dispatch.postEvent(reply);
        }

    }

    static private StringSet createCondSet(Collection objectIds) {
        StringSet set = new StringSet(objectIds, new StringSet.Stringizer() {
            public String toString(Object obj) {
                return obj.toString();
            }
        });
        return set;
    }

    /**
     * @param event
     * @roseuid 42F79A3A0311
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 42F79A3A0313
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param anObject
     * @roseuid 42F79A3A031D
     */
    public void update(Object anObject) {

    }

}
