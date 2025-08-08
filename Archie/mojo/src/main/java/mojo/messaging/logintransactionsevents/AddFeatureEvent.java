package mojo.messaging.logintransactionsevents;

import mojo.km.messaging.RequestEvent;
/**
 * Responsible for housing data that will be sent to control command AddFeatureCommand
 *@author Noblestar
 *@version 1.0
 * @modelguid {215010B3-CAD4-4A47-A54B-6159888EC2CE}
 */
public class AddFeatureEvent extends RequestEvent {
	/** @modelguid {F6DBBF4C-540C-45A6-BD11-19031C7BDCAA} */
    public AddFeatureEvent() { }

	/** @modelguid {5C3ED09A-7BE1-4D35-B47F-C27A04B4AC32} */
    public String getName(){
            return name;
        }

	/** @modelguid {FE1F19DF-DBBC-43E9-8780-67A716716BFD} */
    public void setName(String name){
            this.name = name;
        }

	/** @modelguid {0C287AF6-952C-4AA4-A476-9E0AE578C317} */
    public String getFavoritesOID(){
            return favoritesOID;
        }

	/** @modelguid {3BD31371-647E-442B-809D-2773FD81FBE0} */
    public void setFavoritesOID(String favoritesOID){
            this.favoritesOID = favoritesOID;
        }

	/** @modelguid {185472CF-8BEC-44C2-8426-FFAB55C1F979} */
    private String name;
	/** @modelguid {9FCFA4F7-0E6F-42BB-89EB-E2F81FA36641} */
    private String favoritesOID;
}
