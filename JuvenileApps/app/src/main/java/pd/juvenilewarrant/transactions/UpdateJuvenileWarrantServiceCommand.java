package pd.juvenilewarrant.transactions;

import naming.PDJuvenileWarrantConstants;
import pd.juvenilewarrant.JuvenileAssociateAddress;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantService;
import messaging.juvenilewarrant.UpdateJuvenileWarrantServiceEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * 
 * This commnd is used to create a Juvenile Warrant Service attemp.
 *  
 */
public class UpdateJuvenileWarrantServiceCommand implements ICommand
{

    /**
     * @roseuid 41FFDACC0280
     */
    public UpdateJuvenileWarrantServiceCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41F950A201E5
     */
    public void execute(IEvent event)
    {
        UpdateJuvenileWarrantServiceEvent requestEvent = (UpdateJuvenileWarrantServiceEvent) event;

        JuvenileWarrantService warrantService = this.createWarrantService(requestEvent);

        JuvenileWarrant warrant = warrantService.getJuvenileWarrant();

        if (PDJuvenileWarrantConstants.WARRANT_SERVICE_UNSUCCESSFUL.equals(requestEvent.getServiceStatus()))
        {
            this.handleUnsuccessfulService(requestEvent, warrantService);
        }
        else
        {
            this.handleSuccessfulService(requestEvent, warrant, warrantService);
        }
                
        JuvenileWarrantResponseEvent response = new JuvenileWarrantResponseEvent();
        response.setWarrantActivationStatusId(warrant.getWarrantActivationStatusId());
        response.setWarrantStatusId(warrant.getWarrantStatusId());
        
        MessageUtil.postReply(response);
    }

    /**
     * @param requestEvent
     * @param warrant
     * @param warrantService
     */
    private void handleSuccessfulService(UpdateJuvenileWarrantServiceEvent requestEvent, JuvenileWarrant warrant, JuvenileWarrantService warrantService)
    {
        warrant.setArrestTimeStamp(warrantService.getServiceTimeStamp());
        warrant.setOfficer(warrantService.getExecutorOfficer());
        warrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED);
        warrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE);
        warrant.setServiceReturnSignatureStatusId(PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_RETURNED);
        warrant.setServiceReturnGeneratedStatusId(PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_NOTPRINTED);
        warrantService.sendSuccessfulServiceNotification(warrant);
    }

    /**
     * @param requestEvent
     * @param warrantService
     */
    private void handleUnsuccessfulService(UpdateJuvenileWarrantServiceEvent requestEvent, JuvenileWarrantService warrantService)
    {
        if (requestEvent.isBadAddress())
        {
            String addressId = requestEvent.getAddressId();
            if (addressId != null)
            {
                JuvenileAssociateAddress associateAddress = JuvenileAssociateAddress.find(addressId);
                associateAddress.setBadAddress(true);
            }
            warrantService.sendBadAddressNotification();
        }
    }

    private JuvenileWarrantService createWarrantService(UpdateJuvenileWarrantServiceEvent requestEvent)
    {
        JuvenileWarrantService warrantService = new JuvenileWarrantService();
        warrantService.setJuvenileWarrantId(requestEvent.getWarrantNumber());
        warrantService.setApartmentNum(requestEvent.getApartmentNumber());
        warrantService.setAirFare(requestEvent.getAirFare());
        warrantService.setMileage(requestEvent.getMileage());
        warrantService.setPerDiem(requestEvent.getPerDiem());
        warrantService.setCity(requestEvent.getCity());
        warrantService.setCountyId(requestEvent.getCounty());
        warrantService.setComments(requestEvent.getComments());
        warrantService.setIsBadAddress(requestEvent.isBadAddress());
        warrantService.setServiceStatusId(requestEvent.getServiceStatus());
        warrantService.setStateId(requestEvent.getState());
        warrantService.setStreetName(requestEvent.getStreetName());
        warrantService.setStreetNum(requestEvent.getStreetNumber());
        warrantService.setStreetTypeId(requestEvent.getStreetType());
        warrantService.setZipCode(requestEvent.getZipCode());
        warrantService.setAdditionalZipCode(requestEvent.getAdditionalZipCode());
        warrantService.setAddressTypeId(requestEvent.getAddressType());
        warrantService.setExecutorOfficerId(requestEvent.getOfficerId());
        warrantService.setExecutorOfficerDepartmentId(requestEvent.getOfficerDepartmentId());
        warrantService.setServiceTimeStamp(requestEvent.getServiceTimeStamp());
        return warrantService;
    }

    /**
     * @param event
     * @roseuid 41F950A201E7
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41F950A201E9
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41FFDACC0290
     */
    public void update(Object updateObject)
    {

    }
}
