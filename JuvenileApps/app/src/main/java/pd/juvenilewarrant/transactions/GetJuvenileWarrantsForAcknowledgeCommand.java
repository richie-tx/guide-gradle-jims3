package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import naming.PDJuvenileWarrantConstants;

import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForAcknowledgeEvent;
import messaging.juvenilewarrant.reply.AcknowledgedWarrantErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis Created on Jan 27, 2005
 * 
 *  
 */
public class GetJuvenileWarrantsForAcknowledgeCommand implements ICommand
{
    /**
     * @param event
     */
    public void execute(IEvent event) throws Exception
    {
        List warrants = new ArrayList();

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        GetJuvenileWarrantsForAcknowledgeEvent warEvent = (GetJuvenileWarrantsForAcknowledgeEvent) event;
        if (warEvent.getWarrantNum() != null && !warEvent.getWarrantNum().equals(""))
        {
            String warrantNum = warEvent.getWarrantNum();
            if (warrantNum.length()< 10)
            {
	            JuvenileWarrant warrant = JuvenileWarrant.find(warEvent.getWarrantNum());
	            if (warrant != null)
	            {
	                if (PDJuvenileWarrantConstants.WARRANT_TYPE_DTA.equals(warrant.getWarrantTypeId()))
	                {
	                    if (PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED.equals(warrant.getWarrantAcknowledgeStatusId()))
	                    {
	                        AcknowledgedWarrantErrorEvent ackError = new AcknowledgedWarrantErrorEvent();
	                        ackError.setMessage("Warrant Number '" + warEvent.getWarrantNum() + "' has already been acknowledged.");
	                        dispatch.postEvent(ackError);
	                    }
	                    else if (!PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE.equals(warrant.getWarrantActivationStatusId()))
	                    {
	                    	AcknowledgedWarrantErrorEvent ackError = new AcknowledgedWarrantErrorEvent();
	                        ackError.setMessage("Warrant Number '" + warEvent.getWarrantNum() + "' has been inactviated.");
	                        dispatch.postEvent(ackError);
	                    }
	                    else
	                    {
	                        warrants.add(warrant);
	                        
	                    }
	                }
	                else
	                {
	                    AcknowledgedWarrantErrorEvent ackError = new AcknowledgedWarrantErrorEvent();
	                    ackError.setMessage("Warrant type for this Warrant Number '" + warEvent.getWarrantNum() + "' is not DTA.");
	                    dispatch.postEvent(ackError);
	                }
	            }
            }
            else
            {
                AcknowledgedWarrantErrorEvent ackError = new AcknowledgedWarrantErrorEvent();
                ackError.setMessage("No records found for Warrant Number '" + warEvent.getWarrantNum()
                        + "'.  Please retry search.");
                dispatch.postEvent(ackError);
            }
        }
        else
        {
            // If warrantnum is null or blank then the search is done
            // by the juveniles last name and first name.
            warEvent.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE);
            warEvent.setWarrantStatus(PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING);
            warEvent.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_DTA);
            warEvent.setWarrantAcknowledgeStatus(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED);
            Iterator j = JuvenileWarrant.findAll(warEvent);
            MetaDataResponseEvent metaData = (MetaDataResponseEvent) JuvenileWarrant.findMeta(warEvent);
            if(metaData.getCount() > 2000)
            {
                CountInfoMessage infoEvent = new CountInfoMessage();
                infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
                infoEvent.setCount(metaData.getCount());
                dispatch.postEvent(infoEvent);
            }
            else
            {
                if (j.hasNext())
                { //Iterate through the warrants and post the
                  // JuvenileWarrantResponseEvent for each
                    while (j.hasNext())
                    {
                        warrants.add(j.next());
                    }
                }
                else
                {
                    AcknowledgedWarrantErrorEvent ackError = new AcknowledgedWarrantErrorEvent();
                    StringBuffer buffer = new StringBuffer(30);
                    String lastName = warEvent.getLastName();
        		    String firstName = warEvent.getFirstName();
        		    if(lastName != null && !lastName.equals(""))
        		    {
        		    	buffer.append(" No non-acknowledged warrants were found for Juvenile ");
        		    	if(firstName != null && !firstName.equals(""))
            		    {
            		        buffer.append(firstName);
            		        buffer.append(" ");
            		    }
        		        buffer.append(lastName);
        		        buffer.append(".  Please retry search.");
        		    }
        		    String errorString = buffer.toString();
                    ackError
                    .setMessage(errorString);
                    dispatch.postEvent(ackError);
                }
            }
        }

        if (warrants.size() == 1)
        {
            this.sendSingleWarrant(dispatch, (JuvenileWarrant) warrants.get(0));
        }
        else if (warrants.size() > 1)
        {
            this.sendMultipleWarrants(dispatch, warrants);
        }
    }

    private void sendSingleWarrant(IDispatch dispatch, JuvenileWarrant aWarrant)
    {
        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder(aWarrant);
        builder.build();
        builder.setFileStampValues();
        builder.setOfficerValues();
        builder.setWarrantOriginator();
        builder.setSignatureValues();
        // rry added for Achnowledge affidavit
        builder.setWarrantDetails();
        
        ResponseEvent response = (ResponseEvent) builder.getResult();
        dispatch.postEvent(response);
    }

    private void sendMultipleWarrants(IDispatch dispatch, List warrants)
    {
        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder();
        
        int len = warrants.size();

        for(int i=0;i<len;i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(i);
            builder.setWarrant(warrant);
            builder.build();
            builder.setWarrantOriginator();
            ResponseEvent response = (ResponseEvent) builder.getResult();
            dispatch.postEvent(response);
        }

    }

    /**
     * @param event
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param event
     */
    public void update(Object updateObject)
    {

    }
}
