/*
 * Created on May 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.appshell;

import com.cc.framework.ui.model.Checkable;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CCASingleFeatureEvent extends CCSystemActivityEvent implements TreeNodeDataModel, Checkable {

	/**
	 * ParentNode
	 */
	private TreeGroupDataModel parent = null;

	/**
	 * Determines if the CheckBox for this
	 * Item is checked
	 */
	private int checkState = 0;

	// ------------------------------------------------
	//                Methods
	// ------------------------------------------------

	public CCASingleFeatureEvent(String name) {
		this.setDisplayName(name);
		type = "leaf";
	}
	
	public CCASingleFeatureEvent(String name, String oid) {
		this.setDisplayName(name);
		type = "leaf";
		OID = oid;
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
}
