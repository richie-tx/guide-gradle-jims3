package messaging.appshell;

import java.util.Iterator;

import com.cc.framework.ui.model.Checkable;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;

public class FavoritesFeatureListEvent implements TreeNodeDataModel, Checkable {

    private String OID;	
    private String displayName;
	public static final String type = "leaf";
	/**
	 * ParentNode
	 */
	private TreeGroupDataModel parent = null;

	/**
	 * Determines if the CheckBox for this
	 * Item is checked
	 */
	private int checkState = 0;

	public FavoritesFeatureListEvent() {  }
	
	public FavoritesFeatureListEvent(String name, String oid) {
		displayName = name;
		OID = oid;
	}
	public String getDisplayName(){ return displayName; }

	public void setDisplayName(String name){ this.displayName = name; }

	public void setOID( String OID){
		this.OID = OID;
    }

	public String getOID(){
		return this.OID;
    }
    
	/**
	 * @return
	 */
	public static String getType() {
		return type;
	}

	/**
	 * @see TreeNodeDataModel#getParent()
	 */
	public TreeGroupDataModel getParent() {
		return parent;
	}

	/**
	 * @see TreeNodeDataModel#setParent(TreeGroupDataModel)
	 */
	public void setParent(TreeGroupDataModel parent) {
		this.parent = parent;
	}

	/**
	 * @see TreeNodeDataModel#getParentKey()
	 */
	public String getParentKey() {
		return parent.getUniqueKey();
	}

	/**
	 * returns a unique Productkey
	 * @see TreeNodeDataModel#getUniqueKey()
	 */
	public String getUniqueKey() {
		return this.OID;
	}

	/**
	 * @see Checkable#getCheckState()
	 */
	public int getCheckState() {
		return checkState;
	}

	/**
	 * @see Checkable#setCheckState(int)
	 */
	public void setCheckState(int newState) {
		this.checkState = newState;
	}
	
	
	/************** stubbed the old implementations 
	 ************** so as not to break the build ****/
	public void insertFeature(DisplayFeatureEvent anEvent) {
		
	}
	
	public Iterator getFeatures() {
		return null;	
	}
		
	public String getName() {
		return displayName;
	}
	/*********** end stub ***************/
	

}
