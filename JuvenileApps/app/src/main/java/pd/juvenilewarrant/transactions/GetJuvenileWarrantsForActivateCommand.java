package pd.juvenilewarrant.transactions;

import java.util.Collection;
import java.util.Iterator;
import pd.address.Address;
import pd.address.PDAddressHelper;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilewarrant.JuvenileAssociate;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import messaging.address.reply.AddressResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsForActivateEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.pattern.IBuilder;
import naming.PDJuvenileWarrantConstants;

/**
 * @author dnikolis Created on Jan 27, 2005
 */
public class GetJuvenileWarrantsForActivateCommand implements ICommand
{
    /**
     * @param event
     */
    public void execute(IEvent event) throws Exception
    {

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        GetJuvenileWarrantsForActivateEvent requestEvent = (GetJuvenileWarrantsForActivateEvent) event;
        
        if (requestEvent.warrantNum.length() < 10)
        {

	        JuvenileWarrant warrant = JuvenileWarrant.find(requestEvent.getWarrantNum());
	
	        if (warrant != null)
	        {
	            if (this.isPreconditionsMatch(warrant) == true)
	            {
	                if (this.checkActiveWarrant(warrant) == false)
	                {
	                    IBuilder builder = new JuvenileWarrantBuilder(warrant);
	                    builder.build();
	                    JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) builder.getResult();                    
	                    
	                    dispatch.postEvent(warrantEvent);
	                    this.sendOfficerFields(dispatch, warrant);
	
	                    PDJuvenileWarrantHelper.postResponses(warrant.getChargeResponses());                    
	
	                    this.sendAssociatesFields(dispatch, warrant);                    
	                }
	            }
            }
        }
    }

    /**
     * @param warrant
     * @return
     */
    private boolean checkActiveWarrant(JuvenileWarrant warrant)
    {
        boolean activeWarrantExists = PDJuvenileWarrantHelper.checkForActiveWarrant(warrant.getJuvenileNum(), String
                .valueOf(warrant.getDaLogNumber()), warrant.getDateOfBirth(), warrant.getFirstName(), warrant.getLastName(),
                warrant.getReferralNum(), "");

        if (activeWarrantExists == true)
        {
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            ActiveWarrantErrorEvent error = new ActiveWarrantErrorEvent();
            error.setMessage("There already exists an active Juvenile Warrant for this juvenile: " + warrant.getJuvenileNum());
            dispatch.postEvent(error);
        }

        return activeWarrantExists;
    }

    private boolean isPreconditionsMatch(JuvenileWarrant warrant)
    {
        String warrantActivationStatus = warrant.getWarrantActivationStatusId();
        String warrantStatus = warrant.getWarrantStatusId();
        String warrantSignedStatus = warrant.getWarrantSignedStatusId();
        String warrantAcknowledgeStatus = warrant.getWarrantAcknowledgeStatusId();
        boolean booleanReturn = false;
        if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE.equals(warrantActivationStatus)
                && PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING.equals(warrantStatus)
                && PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED.equals(warrantSignedStatus)
                && PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED.equals(warrantAcknowledgeStatus))
        {
            booleanReturn = true;
        }

        return booleanReturn;
    }

    /**
     * 
     * @param dispatch
     * @param juvWarrant
     */
    private void sendOfficerFields(IDispatch dispatch, JuvenileWarrant juvWarrant)
    {
        OfficerProfile officer = juvWarrant.getOfficer();
        if (officer != null)
        {
            OfficerProfileResponseEvent officerEvent = PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officer);
            dispatch.postEvent(officerEvent);
        }
    }

    /**
     * 
     * @param dispatch
     * @param juvWarrant
     */
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
                        .getJuvenileAssociateResponseEvent(associate);
                dispatch.postEvent(associateEvent);

                Iterator addresses = associate.getAddresses().iterator();
                while (addresses.hasNext())
                {
                    Address juvAddress = (Address) addresses.next();
                    AddressResponseEvent are = PDAddressHelper.getAddressResponseEvent(juvAddress);
                    dispatch.postEvent(are);
                }
            }
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