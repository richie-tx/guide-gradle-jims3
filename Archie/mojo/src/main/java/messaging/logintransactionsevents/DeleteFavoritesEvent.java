package messaging.logintransactionsevents;

import mojo.km.messaging.RequestEvent;
/**
 * Responsible for housing data that will be sent to control command DeleteFavoritesCommand
 *@author Noblestar
 *@version 1.0
 * @modelguid {1DD5E42A-A708-4A75-BB43-5F9DF40BBEC2}
 */
public class DeleteFavoritesEvent extends RequestEvent {

	/** @modelguid {18C207F1-63D1-4449-8931-E1CA6D202CD2} */
    public DeleteFavoritesEvent() {
    }

	/** @modelguid {C1D0C642-55C5-427F-A477-0F840FAAD291} */
    public DeleteFavoritesEvent( String favoritesOID )
    {
        this.favoritesOID = favoritesOID;
    }

	/** @modelguid {ACE1F7F4-FF5E-47A4-82A0-E72DD56C4220} */
    public String getFavoritesOID(){
            return favoritesOID;
        }

	/** @modelguid {A1BE98EC-D9F9-4AD6-A300-3F044B3CC5E1} */
    public void setFavoritesOID(String favoritesOID){
            this.favoritesOID = favoritesOID;
        }

	/** @modelguid {8744A46A-5ABD-4CC3-9BAB-52438792CCA1} */
    private String favoritesOID;
}
