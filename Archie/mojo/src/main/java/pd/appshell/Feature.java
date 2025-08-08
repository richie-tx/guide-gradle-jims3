package pd.appshell;

import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.persistence.ObjectNotFoundException;
import java.util.Collection;
import java.util.Iterator;

/** @stereotype Entity 
 * @modelguid {9DCC9061-BA94-445E-B686-74B24382FB4E}
 */
public class Feature extends PersistentObject implements Comparable {
    /**
     * @associates <{Parameter}>
     * @supplierCardinality 0..*
     * @clientCardinality 1
     * @label parameters
     * @modelguid {A59A2411-3A55-4F4F-B06D-F1BE5B5B1204}
     */
    private Collection parameters = new java.util.Vector();

    /**
     * @associates <{Feature}>
     * @supplierCardinality 0..*
     * @clientCardinality 1
     * @label children
     * @modelguid {A199DB4D-326D-4EBE-A384-69588B34D59F}
     */
    private Collection children = new java.util.Vector();
	/** @modelguid {EBF07E18-13B1-43CE-B4B7-71C853DEF5D3} */
    private String parent;
	/** @modelguid {B5F2B0C4-912A-4D36-91A0-6FCCC4B0C2A3} */
    private String type;
	/** @modelguid {B6FF16F1-54A6-4340-9025-ABFDF3936055} */
    private int orderNumber;
    //private String label;

    //replacement for label
	/** @modelguid {39552D98-E8EC-4D8D-B21A-CD3CBDAF2DB8} */
    private String name;

    /**
     * @clientCardinality 1
     * @supplierCardinality 1
     * @label toplink 
     * @modelguid {66528256-5F47-49B0-8346-6944ED451AD2}
     */
    //private Favorites favorites;
//    private Collection favoritesList = new java.util.Vector();
    private String topic;
	/** @modelguid {1F8FA364-CB50-4420-B059-69DFA7072574} */
    private boolean treeFeature;
	/** @modelguid {B381770D-7294-4144-A03F-8A7430699D9B} */
    private int personOID;

	/** @modelguid {C17543B4-BECF-4190-A84A-C1C7DEA265AA} */
    public Iterator getChildren() {
        fetch();
        return children.iterator();
    }

	/** @modelguid {D4F81D79-D8BE-40A8-B3B6-5D6F8E60B0D5} */
    public void insertChild(Feature aFeature) {
        markModified();
        aFeature.setParent(this);
        children.add(aFeature);
    }

	/** @modelguid {4C8694C7-3A80-4F11-98A7-F42B4E626808} */
    public void deleteChild(Feature aFeature) {
        markModified();
        children.remove(aFeature);
    }

	/** @modelguid {65468B5F-CB84-409B-9612-6BF2AC784231} */
    public void clearChildren() {
        markModified();
        children.clear();
    }

//    public Iterator getFavoritesList() {
//        fetch();
//        return favoritesList.iterator();
//    }

//    public void insertFavoritesList(Favorites favorites) {
//        markModified();
//        favoritesList.add(favorites);
//    }

//    public void deleteFavorites(Favorites favorites) {
//        markModified();
//        favoritesList.remove(favorites);
//    }

//    public void clearFavorites() {
//        markModified();
//        favoritesList.clear();
//    }

	/** @modelguid {82D73A77-04E5-4822-9889-DFD9A31845AD} */
    public Parameter getParameter(String aName) {
        fetch();
        if (aName != null) {
            for (Iterator i = getParameters(); i.hasNext(); ) {
                Parameter lParameter = (Parameter)i.next();
                if (aName.equals(lParameter.getName())) {
                    return lParameter;
                }
            }
        }
        return null;
    }

	/** @modelguid {E7A88C54-56D7-405E-BE8B-24D3CB7B3792} */
    public Iterator getParameters() {
        fetch();
        return parameters.iterator();
    }

	/** @modelguid {12AFD7FA-BE88-495D-8C2D-14AE994962CB} */
    public Feature getParent() throws ObjectNotFoundException {
        fetch();
        if(parent == null) {
            return null;
        }
        IHome lHome = new Home();
        return (Feature)lHome.find(parent, Feature.class);
    }

	/** @modelguid {AD6FF484-0FA1-4757-B357-89FF0E5EBCD6} */
    public void setParent(Feature aParent) {
        markModified();
        if(aParent == null) {
        	this.parent = null;
        } else {
			this.parent = aParent.getOID().toString();
        }
    }

	/** @modelguid {C57B87DB-8F33-4526-B276-83D28C969EA1} */
    public String getType() {
        fetch();
        return type;
    }

	/** @modelguid {A2B81ECA-4D31-4189-9873-A05D238CDFDB} */
    public void setType(String type) {
        markModified();
        this.type = type;
    }

	/** @modelguid {30D20522-F72B-48B5-8036-1252DD94B901} */
    public int getOrderNumber() {
        fetch();
        return orderNumber;
    }

	/** @modelguid {55973330-4A88-4A50-87EC-C00FFB9B4232} */
    public void setOrderNumber(int orderNumber) {
        markModified();
        this.orderNumber = orderNumber;
    }
    /*
    public String getLabel() {
        fetch();
        return label;
    }

    public void setLabel(String label) {
        markModified();
        this.label = label;
    }
	 * @modelguid {93FF42C7-7884-489B-B03C-97353D288FF9}
    */
    public String getName() {
        fetch();
        return name;
    }

	/** @modelguid {3E18B511-BFE7-4FDD-837D-CA7987F9B4F0} */
    public void setName(String name) {
        markModified();
        this.name = name;
    }

	/** @modelguid {49639CA7-275A-432B-B88A-B05E401C8F02} */
    public void insertParameter(Parameter aParameter) {
        markModified();
        aParameter.setFeature(this);
        parameters.add(aParameter);
    }

	/** @modelguid {129F71DA-34FB-4C80-A009-E31C17C43223} */
    public void deleteParameter(Parameter aParameter) {
        markModified();
        parameters.remove(aParameter);
    }

	/** @modelguid {97A0D119-82AA-4451-A27F-B4C87ECDE702} */
    public void clearParameters() {
        markModified();
        parameters.clear();
    }

	/** @modelguid {84C5ADD6-D13C-47F2-8B1A-D2B3B59E8065} */
    public int compareTo(Object anObject) {
        markModified();
        Feature lFeature = (Feature)anObject;
        if (getOrderNumber() < lFeature.getOrderNumber())
            return -1;
        else if (getOrderNumber() > lFeature.getOrderNumber())
            return 1;
        else
            return 0;
    }

    /*
    public Favorites getFavorites(){
        fetch();
        return favorites;
    }

    public void setFavorites(Favorites favorites){
        markModified();
        this.favorites = favorites;
    }
	 * @modelguid {D866080C-ED82-4D15-9B28-1103FB8407F7}
    */
    public String getTopic(){
        fetch();
    	return topic;
    }

	/** @modelguid {3521DAA1-BF0E-43E0-A7B0-C21575E617E4} */
    public void setTopic(String topic){
        markModified();
        this.topic = topic;
    }

	/** @modelguid {D3108747-5F9D-4755-AEF1-6BF256B95CF5} */
    public boolean getTreeFeature(){
        fetch();
    	return treeFeature;
    }

	/** @modelguid {D024E60B-CFD5-4305-97F0-87517A71B59F} */
    public void setTreeFeature(boolean treeFeature){
        markModified();
        this.treeFeature = treeFeature;
    }

	/** @modelguid {D26A8F44-2799-48EC-BD5B-7F07DFE8FBE8} */
    public int getPersonOID(){
        fetch();
    	return personOID;
    }

	/** @modelguid {7C8732F3-C1F9-42E0-B4C4-3986A59AE983} */
    public void setPersonOID(int personOID){
        markModified();
        this.personOID = personOID;
    }
}
