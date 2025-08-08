package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;

import naming.PDJuvenileWarrantConstants;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForManageReleaseDecisionEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import pd.juvenilewarrant.JuvenileAssociate;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantBuilder;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;

/**
 * 
 * @author asrvastava
 * 
 * This command is used to get a list of warrants for managing the release
 * decision like Release to Person or Release to Juvenile Probation.
 *  
 */
public class GetJuvenileWarrantsForManageReleaseDecisionCommand implements ICommand
{

    /**
     * @roseuid 421371F402CE
     */
    public GetJuvenileWarrantsForManageReleaseDecisionCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42137054034D
     */
    public void execute(IEvent event)
    {
        GetJuvenileWarrantsForManageReleaseDecisionEvent warEvent = (GetJuvenileWarrantsForManageReleaseDecisionEvent) event;

        String warrantNum = warEvent.getWarrantNum();
        List juvWarrants = new ArrayList();
        
        if (warrantNum.length() <10)
        {
	        if (warrantNum != null && warrantNum.trim().length() > 0)
	        {
	            List warrantList = new ArrayList();
	            JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);
	            if (warrant != null)
	            {
	
	                warrantList.add(warrant);
	                juvWarrants = getQualifiedWarrantList(warrantList.iterator(), warEvent.getReleaseDecision());
	            }
	        }
	        else
	        {
	            MetaDataResponseEvent metaData = (MetaDataResponseEvent) JuvenileWarrant.findMeta(event);
	            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	            if(metaData.getCount()> 2000)
	            {
	                CountInfoMessage infoEvent = new CountInfoMessage();
	                infoEvent.setMessage("Record count exeeded - total records found = " + metaData.getCount());
	                infoEvent.setCount(metaData.getCount());
	                dispatch.postEvent(infoEvent);
	            }
	            else
	            {
	                Iterator iter = JuvenileWarrant.findAll(warEvent);
	                juvWarrants = getQualifiedWarrantList(iter, warEvent.getReleaseDecision());
	            }
	        }
        }    
        if (juvWarrants.size() == 1)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) juvWarrants.get(0);
            this.processSingleResult(warrant, warEvent);
        }
        else if (juvWarrants.size() > 1)
        {
            this.processMultipleResults(juvWarrants);
        }
    }

    /**
     * @param dispatch
     * @param juvWarrants
     * @param warEvent
     */
    private void processSingleResult(JuvenileWarrant warrant, GetJuvenileWarrantsForManageReleaseDecisionEvent warEvent)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        JuvenileWarrantBuilder builder = new JuvenileWarrantBuilder(warrant);
        builder.build();

        JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) builder.getResult();

        dispatch.postEvent(warrantEvent);

        if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_JUVENILE_PROBATION.equals(warEvent.getReleaseDecision()))
        {
            PDJuvenileWarrantHelper.postResponses(warrant.getChargeResponses());
        }
        else if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_PERSON.equals(warEvent.getReleaseDecision()))
        {
            this.sendAssociatesFields(dispatch, warrant);
        }
    }

    private void sendAssociatesFields(IDispatch dispatch, JuvenileWarrant juvWarrant)
    {
        Collection juvAssociates = juvWarrant.getJuvenileAssociates();
        if (juvAssociates != null)
        {
            Iterator i = juvAssociates.iterator();
            while (i.hasNext())
            {
                JuvenileAssociate associate = (JuvenileAssociate) i.next();
                JuvenileAssociateResponseEvent associateEvent = PDJuvenileWarrantHelper
                        .getSimpleJuvenileAssociateResponseEvent(associate);

                dispatch.postEvent(associateEvent);
            }
        }
    }

    /**
     * @param juvWarrants
     */
    private void processMultipleResults(List juvWarrants)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        int len = juvWarrants.size();

        for (int j = 0; j < len; j++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) juvWarrants.get(j);

            JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder(warrant);
            builder.build();
            builder.setWarrantType();
            JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) builder.getResult();

            dispatch.postEvent(warrantEvent);
        }
    }

    /**
     * @param warrantList
     * @param string
     * @return
     * 
     * 
     * PERSON <pre-condition>
     *    
     *  Release Decision status = Released to Person, 
     *      Warrant Status is Executed.  
     *      Juvenile Released to Juvenile Probation Intake.  
     *      ServiceReturnSignatureStatus = Filed; 
     *      Service Return Generated Status = Printed; 
     *      Warrant Acknowledge Status = Printed; 
     *      Warrant Signed Status = Signed;  
     *      Warrant activation status = inactive;  
     *      Service status = successful;  
     *      Transfer related elements = not null; Released To status = No
     *      &lt;/pre-condition&gt;
     *  
     *  
     *  JP
     *  
     *  &lt;pre-condition&gt;
     *      The warrant status is executed.
     *      ServiceReturnSignatureStatus = Filed; 
     *      Service Return Generated Status = Printed; 
     *      Warrant Acknowledge Status = Printed; 
     *      Warrant Signed Status = Signed;  
     *      Warrant activation status = inactive;  
     *      Service status = successful;  
     *      Release decision = Released to Juvenile Probation; 
     *      transfer custody date, time and location are blank.
     *      &lt;/pre-condition&gt;
     *  
     * 
     * 
     *
     */
    private List getQualifiedWarrantList(Iterator warrantsIterator, String requestedReleaseDecision)
    {
        List qualifiedWarrantList = new ArrayList();
        
        if (warrantsIterator != null)
        {
            JuvenileWarrant warrant = null;
            String releaseDecision = null;
            String warrantStatusId = null;
            String warrantActivationStatusId = null;
/*          String serviceReturnSignatureStatusId = null;
            String serviceReturnGeneratedStatusId = null;
*/          while (warrantsIterator.hasNext())
            {
                warrant = (JuvenileWarrant) warrantsIterator.next();
                releaseDecision = warrant.getReleaseDecisionId();
                warrantStatusId = warrant.getWarrantStatusId();
                warrantActivationStatusId = warrant.getWarrantActivationStatusId();
/*              serviceReturnSignatureStatusId = warrant.getServiceReturnSignatureStatusId();
                serviceReturnGeneratedStatusId = warrant.getServiceReturnGeneratedStatusId();
*/              if (releaseDecision != null && requestedReleaseDecision.equalsIgnoreCase(releaseDecision)
                        && PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED.equals(warrantStatusId)
                        && PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE.equals(warrantActivationStatusId))
/*                      && PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_FILED.equals(serviceReturnSignatureStatusId)
                        && PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_PRINTED.equals(serviceReturnGeneratedStatusId)
*/              {
                    if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_PERSON.equals(requestedReleaseDecision)
                            && warrant.getReleaseAssociateNum() == null)
                    {
                        qualifiedWarrantList.add(warrant); // person
                    }
                    else if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_JUVENILE_PROBATION.equals(requestedReleaseDecision)
                            && (warrant.getTransferOfficerId() == null && warrant.getTransferLocationId() == null && warrant
                                    .getTransferTimeStamp() == null))
                    {
                        qualifiedWarrantList.add(warrant); // jp
                    }
                }
            }
        }
        return qualifiedWarrantList;
    }

    /**
     * @param event
     * @roseuid 42137054034F
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 421370540351
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 421370540353
     */
    public void update(Object anObject)
    {

    }
}
