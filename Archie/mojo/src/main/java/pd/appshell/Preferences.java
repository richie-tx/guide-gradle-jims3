package pd.appshell;

import mojo.km.persistence.PersistentObject;
import java.util.Iterator;
import java.util.Collection;

/**
* @author Egan Royal
* @stereotype Entity
* @modelguid {1771D4A8-E8A3-4C38-A2C6-AF359955B13E}
*/
public class Preferences extends PersistentObject {
    /**
     * @associates <{Favorites}>
     * @supplierCardinality 0..*
     * @clientCardinality 1
     * @label favorites
     * @modelguid {5DB5102D-225F-4981-8C20-8D4D69C3D47B}
     */

    public static final int DEFAULT_PERSONOID = 1;

	/** @modelguid {F8368855-9AAD-41E5-92E4-AFF7104CA454} */
    private boolean isNew = false;
	/** @modelguid {7C3CF569-EBF8-4D3C-B246-DD75926A0829} */
    private Collection favorites = new java.util.Vector();
	/** @modelguid {51967EA3-C643-45DC-BCB0-DAA43BE8AA05} */
    private String BLOB;
	/** @modelguid {099159A2-548D-43A2-83E4-517E67E52C9D} */
    private int personOID;

	/** @modelguid {505F5B88-CAEE-4BD0-A563-434D70175938} */
    public Iterator getFavorites() {
        fetch();
        return favorites.iterator();
    }

	/** @modelguid {B6AFFDC6-8A9D-4C8A-9EBC-36351879D452} */
    public String getBLOB() {
        fetch();
        return BLOB;
    }

	/** @modelguid {61BE5443-2FBE-4C59-B485-5C41B1880E4A} */
    public void setBLOB(String BLOB) {
        markModified();
        this.BLOB = BLOB;
    }

	/** @modelguid {A17CB633-3202-4582-BB35-56CC0378EAD2} */
    public int getPersonOID() {
        fetch();
        return personOID;
    }

	/** @modelguid {3EC5009F-6F63-4331-9168-AF88CB7496AE} */
    public void setIsNew(boolean isNew) {
        markModified();
        this.isNew = isNew;
    }

	/** @modelguid {0E90E0AC-0B63-4D72-B611-E18E73B0DC9A} */
    public boolean getIsNew() {
        fetch();
        return isNew;
    }

	/** @modelguid {0686655B-BBCA-47FE-BE81-E38ABB455A6A} */
    public void setPersonOID(int personOID) {
        markModified();
        this.personOID = personOID;
    }

	/** @modelguid {E40D56B5-27E9-4435-872C-2202D1119E8B} */
    public void insertFavorites(Favorites fvrts) {
        //if(favoritesExists(fvrts) == null){
	        markModified();
	        fvrts.setPreferences(this);
	        favorites.add(fvrts);
		//}
    }

	/** @modelguid {626D2BE1-8AA8-47B6-9A8A-513A935F487A} */
    public void deleteFavorites(Favorites fvrts) {
        markModified();
        favorites.remove(fvrts);
    }

	/** @modelguid {FE750526-605E-4FE9-84FB-637AB1AE4B1A} */
    public void clearFavorites() {
        markModified();
        favorites.clear();
    }

	/** @modelguid {F3A248CA-8D6E-49B0-9E34-82AA6CEAF2FF} */
    public Favorites favoritesExists(Favorites fvrts){
        Iterator i = getFavorites();
        while(i.hasNext()){
            Favorites lFavorites = (Favorites)i.next();
			if(lFavorites.getName().equalsIgnoreCase(fvrts.getName())){
				return lFavorites;
            }
        }
		return null;
    }
}
