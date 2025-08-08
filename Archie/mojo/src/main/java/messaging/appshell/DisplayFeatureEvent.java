package messaging.appshell;

import java.util.Collection;
import java.util.Iterator;
import mojo.km.messaging.RequestEvent;

/**
 * Tree.
 *
 * @author Egan Royal
 * @modelguid {47739E1D-8EBB-457A-ABAA-05E5AE0BB0AE}
 */
public class DisplayFeatureEvent extends RequestEvent {
    /**
     * @clientCardinality 1
     * @supplierCardinality 0..1
     * @label children
     * @modelguid {6860BD28-CAF6-4FDF-BA26-21D57773E31E}
     */
    private DisplayFeatureListEvent children;
	/** @modelguid {1023A5E4-5FE1-4ACA-A6C2-C37BE2019E5C} */
    private String type;
    //private String label;
	/** @modelguid {B879E888-556C-4ACD-AFB9-DC561993400E} */
    private int orderNumber;
	/** @modelguid {7288B25D-B87B-4B64-895F-AD4BC1BA48D2} */
    private String topic;
	/** @modelguid {AC24D019-F236-4A45-BE5D-841281B58805} */
    private boolean treeFeature;
	/** @modelguid {9D41CA18-07F0-4646-9986-7979FB106013} */
    private int personOID;
	/** @modelguid {DD630EC3-E6E7-45E1-B254-6A40C6285240} */
    private String OID;


	//replacement for label
	/** @modelguid {3F1B2EAE-759A-430D-A41D-833DE8BA3DBB} */
    private String name;

    /**
     * @associates <{ParameterEvent}>
     * @supplierCardinality 0..*
     * @clientCardinality 1
     * @label parameters
     * @modelguid {2B87DBEA-FAC5-4913-A510-D8075519F182}
     */
    private Collection parameters = new java.util.Vector();

	/** @modelguid {87AA1573-060F-4BC7-BCFE-8E2ED1A1D4AC} */
    public DisplayFeatureEvent() { setServer(mojo.naming.ServerNames.LOGINCONTROLLER); }

	/** @modelguid {AE72C1ED-E754-4933-A079-81B239C9E2BE} */
    public Iterator getParameters() {
        return parameters.iterator();
    }

	/** @modelguid {72521E5A-0F37-4C77-8FFD-DD973A118B02} */
    public void insertParameter(ParameterEvent aParameter) {
        parameters.add(aParameter);
    }

	/** @modelguid {3CAFDCF7-AF6A-42CB-9F86-64C9CE7D6FCE} */
    public DisplayFeatureListEvent getChildren(){ return children; }

	/** @modelguid {87D1E2BB-AF23-42D3-8228-F40B125501F4} */
    public void setChildren(DisplayFeatureListEvent children){ this.children = children; }

	/** @modelguid {F9C5A226-C5A9-4C06-8264-040ECA32D146} */
    public String getType(){ return type; }

	/** @modelguid {E6D3B189-8628-4164-9FAD-DD9821A1A93A} */
    public void setType(String type){ this.type = type; }
    /*
    public String getLabel(){ return label; }

    public void setLabel(String label){ this.label = label; }
	 * @modelguid {28D16E04-2BBE-4A8A-8BCE-9F27A4831BDB}
    */
    public String getName(){ return name; }

	/** @modelguid {DD171CF0-1B4B-40E8-B7E0-CA4B2DB5A2C4} */
    public void setName(String name){ this.name = name; }

	/** @modelguid {694A396F-3AB0-44CB-8754-6D9242E86978} */
    public int getPersonOID(){ return personOID; }

	/** @modelguid {044D7B9E-3FC3-4D57-8813-D39B82BA1D77} */
    public void setPersonOID(int personOID){ this.personOID = personOID; }

	/** @modelguid {CAAB9EEA-4C33-4F54-965E-D401D7799A4D} */
    public void setOID( String OID){
		this.OID = OID;
    }

	/** @modelguid {DD5D0200-B0DC-452C-9E82-2D083748BCB4} */
    public String getOID(){
		return this.OID;
    }

	/** @modelguid {23E55177-B5EF-4A20-A50E-5AD85534FB5E} */
    public int getOrderNumber(){ return orderNumber; }

	/** @modelguid {12642366-5FAD-4DF2-AF38-C42547772A91} */
    public void setOrderNumber(int orderNumber){ this.orderNumber = orderNumber; }

	/** @modelguid {4A55625A-7873-4D76-9C57-8BE392073B18} */
    public String getTopic(){ return topic; }

	/** @modelguid {15C3FFA0-2143-4398-A04F-968ED6E16DC4} */
    public void setTopic(String topic){ this.topic = topic; }

	/** @modelguid {5972788D-ADA2-46AE-990D-E0C3D5133636} */
    public boolean getTreeFeature(){
        return treeFeature;
    }

	/** @modelguid {5BE23D74-3C1C-4CCF-A696-90AF90198B8B} */
    public void setTreeFeature(boolean treeFeature){
        this.treeFeature = treeFeature;
    }

	/** @modelguid {09A76F13-F9D1-4FE1-91FC-7055A688AF52} */
    private Collection favoritesFeatureLists = new java.util.Vector();

	/** @modelguid {BDBCF029-BB8A-480C-9829-38B9681D0231} */
    public void insertFeatureList(FavoritesFeatureListEvent aFeatureList) {
        favoritesFeatureLists.add(aFeatureList);
    }

	/** @modelguid {43FD04F7-9B38-4494-A105-73EB01697AA3} */
    public Iterator getFeatureLists() {
        return favoritesFeatureLists.iterator();
    }
}
