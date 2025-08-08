package messaging.logintransactionsevents;

import mojo.km.messaging.RequestEvent;
/**
 * Responsible for housing data that will be sent to control command DeleteFeatureCommand
 *@author Noblestar
 *@version 1.0
 * @modelguid {52B1A799-CC79-4D7E-91E7-46D8B8F48665}
 */
public class DeleteFeatureEvent extends RequestEvent {
	/** @modelguid {F510BE7B-04CF-47F3-81A4-C797CB391193} */
    public DeleteFeatureEvent() { }

	/** @modelguid {382A7823-A66D-47F4-A8AF-959DAA400369} */
    public String getFeatureOID(){
            return featureOID;
        }

	/** @modelguid {4BEDC921-4F30-415E-B640-4F8141163181} */
    public void setFeatureOID(String featureOID){
            this.featureOID = featureOID;
        }

	/** @modelguid {26D43278-857F-46B9-B5CD-401DF5D5CA1D} */
    public String getFavoritesOID(){
            return favoritesOID;
        }

	/** @modelguid {C726EDD8-7503-42E9-8A2D-CDB3CEC916A7} */
    public void setFavoritesOID(String favoritesOID){
            this.favoritesOID = favoritesOID;
        }

	/** @modelguid {8B3A0E41-8C19-4DF8-AFCC-600127AF6E8B} */
    private String featureOID;
	/** @modelguid {5EB12AE2-899F-4250-ACDB-FA1A2362908C} */
    private String favoritesOID;
}
