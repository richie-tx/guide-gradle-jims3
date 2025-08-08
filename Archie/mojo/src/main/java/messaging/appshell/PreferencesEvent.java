package messaging.appshell;

import mojo.km.messaging.ResponseEvent;
/** @modelguid {03360FD0-18F1-4BE0-BFE1-D4EA8129BC99} */
public class PreferencesEvent extends ResponseEvent {
    /**
     * @clientCardinality 1
     * @supplierCardinality 1 
     * @modelguid {8CC055DB-FC77-4439-880D-0922BA1FC085}
     */
    private FavoritesEvent favorites;
	/** @modelguid {E3571625-DF43-4B55-B123-718CE0E36093} */
    private String BLOB;
	/** @modelguid {2AEA3B69-2FF0-41FF-9838-37565AD6B60C} */
    private String OID;
	/** @modelguid {95A9BEB9-CFB3-4B4C-B475-02DADEAEBF13} */
    private int personOID;
	/** @modelguid {A746C94C-4C4F-429A-91D3-2C1C500A8B55} */
    private boolean isNew = false;

	/** @modelguid {699BF526-DC99-4211-91E8-5EF86C73ECA9} */
    public PreferencesEvent() {
        setServer(mojo.naming.ServerNames.SWING);
    	setTopic(new Integer(personOID).toString());

    }

	/** @modelguid {61823433-2CEA-4383-8149-B288585650FC} */
    public FavoritesEvent getFavorites(){ return favorites; }

	/** @modelguid {0D1DC0D4-67D1-415E-99CB-16391FC1FBCD} */
    public void setFavorites(FavoritesEvent favorites){ this.favorites = favorites; }

	/** @modelguid {A11F9A88-ACC9-4A99-BE6B-171889436FAB} */
    public String getBLOB(){ return BLOB; }

	/** @modelguid {E697D2AF-47A8-4933-9B2F-617301B5FF9F} */
    public void setBLOB(String BLOB){ this.BLOB = BLOB; }

	/** @modelguid {E8F45344-4E60-4AA7-86A6-0DCB195C72DE} */
    public void setOID( String OID){
		this.OID = OID;
    }

	/** @modelguid {A9AFA0C8-B941-4A21-BA74-C59E4C00715B} */
    public String getOID(){
		return this.OID;
    }

	/** @modelguid {11E9F8EE-02FC-418A-B57F-5C88073B9305} */
    public void setPersonOID(int personOID){
		this.personOID = personOID;
        setTopic(new Integer(personOID).toString());
    }

	/** @modelguid {91A3E91A-1CD8-4C68-B45A-EE4505366226} */
    public int getPersonOID(){
		return this.personOID;
    }

	/** @modelguid {DF2B94E5-DF07-4656-8877-5C0A523B3FD0} */
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

	/** @modelguid {5404AD52-4771-43D1-9F61-9D258A77D551} */
    public boolean getIsNew() {
        return isNew;
    } 
}
