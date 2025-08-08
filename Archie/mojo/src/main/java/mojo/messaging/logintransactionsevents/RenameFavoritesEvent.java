package mojo.messaging.logintransactionsevents;

import mojo.km.messaging.RequestEvent;
/**
 * Responsible for housing data that will be sent to control command RenameFavoritesCommand
 *@author Noblestar
 *@version 1.0
 * @modelguid {1735668E-81AC-4030-925B-7BB814AC7774}
 */
public class RenameFavoritesEvent extends RequestEvent {
	/** @modelguid {1318ADDC-50A5-43C6-A019-72D645348599} */
    public RenameFavoritesEvent() { }

	/** @modelguid {FE8F484C-B9C6-4ED4-AA27-A14B9FB928E3} */
    public String getFavoritesOID(){
            return favoritesOID;
        }

	/** @modelguid {C37C1F82-C5EE-4C75-9A9D-E08681699D3C} */
    public void setFavoritesOID(String favoritesOID){
            this.favoritesOID = favoritesOID;
        }

	/** @modelguid {16A2ABF7-EFFB-4A11-85C1-930F9A00130A} */
    public String getName(){
            return name;
        }

	/** @modelguid {75867729-B8E3-4E34-BF9D-05ADD062F32D} */
    public void setName(String name){
            this.name = name;
        }

	/** @modelguid {20E1DDA4-9668-4756-B2A2-BD312C4FA4BF} */
    private String favoritesOID;
	/** @modelguid {529A8C8F-B2D4-4468-8C36-655BC44C846E} */
    private String name;
}
