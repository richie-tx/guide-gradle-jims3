package messaging.logintransactionsevents;

import mojo.km.messaging.RequestEvent;
/**
 * Responsible for housing data that will be sent to control command AddFavoritesCommand
 *@author Noblestar
 *@version 1.0
 * @modelguid {7B487756-CA06-481A-AB72-06BD0C5FEFFE}
 */
public class AddFavoritesEvent extends RequestEvent {
	/** @modelguid {60EF5011-65C4-491D-A648-AA05563E2342} */
    public AddFavoritesEvent() { }

	/** @modelguid {38142271-A1F1-48AD-8DDE-12B33C4ED560} */
    public String getPreferencesOID(){
            return preferencesOID;
        }

	/** @modelguid {977A0D2E-DA08-4723-A385-C4998515C829} */
    public void setPreferencesOID(String preferencesOID){
            this.preferencesOID = preferencesOID;
        }

	/** @modelguid {A499719B-AAFF-4F92-94CB-19975A69F79C} */
    public String getFavoritesName(){
            return favoritesName;
        }

	/** @modelguid {0DD49AB2-0796-4EBF-B100-BD12F5B3F856} */
    public void setFavoritesName(String favoritesName){
            this.favoritesName = favoritesName;
        }

	/** @modelguid {640F3E84-3CDF-41BF-BBC2-C5207840B2BD} */
    private String preferencesOID;
	/** @modelguid {3CD5A7DC-F1A5-4570-A755-4595ACA0C342} */
    private String favoritesName;
}
