/*
 * Created on May 6, 2004
 *
 */
package messaging.appshell;

import java.util.Iterator;
import java.util.Vector;

import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;

/**
 * @author dapte
 *
 * Denotes a group of Features. 
 * Represents a folder node in the features tree.
 */
public class CCFeaturesEvent extends CCSystemActivityEvent implements TreeGroupDataModel {
	private TreeGroupDataModel parent = null;
	private Vector children; // = new Vector(10, 5);

	public CCFeaturesEvent() { }
	
	public CCFeaturesEvent(String name, String oid, String url) {
		displayName = name;
		type = "folder";
		children = new Vector(10, 5);
		this.url = url;
		OID = oid;
	}
	
	public CCFeaturesEvent(String name, String oid) {
		displayName = name;
		type = "folder";
		children = new Vector(10, 5);
		OID = oid;
	}
	
	/**
	 * @see com.cc.framework.ui.model.TreeGroupDataModel#addChild(TreeNodeDataModel)
	 */
	public void addChild(TreeNodeDataModel child) {

		children.add(child);

		// set Parent
		child.setParent(this);
	}

		
	/**
	 * @see com.cc.framework.ui.model.TreeGroupDataModel#getChild(int)
	 */
	public TreeNodeDataModel getChild(int index) {
		return (TreeNodeDataModel) children.elementAt(index);
	}
	
	/**
	 * @return
	 */
	public Vector getChildren() {
		return children;
	}
	
	/**
	 * @see com.cc.framework.ui.model.TreeNodeDataModel#getParentKey()
	 */
	public String getParentKey() {
		return parent.getUniqueKey();
	}

	/**
	 * @see com.cc.framework.ui.model.TreeNodeDataModel#getUniqueKey()
	 */
	public String getUniqueKey() {
		return this.OID;
	}
	
	public int size() {
		int count = -1;
		if(children != null) {
			count = children.size(); 
		}
		return count;
	}


	/**
	 * Removes the Childnodes
	 */
	public void removeChildren() {
		if (null != children) {
			children.clear();
		}
	}


	/**
	 * @return
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return
	 */
	public TreeGroupDataModel getParent() {
		return parent;
	}

	/**
	 * @param model
	 */
	public void setParent(TreeGroupDataModel model) {
		parent = model;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}


/**
 * Returns the application URL of the tree node with the given OID
 * @param oid The OID of thr selected node
 * @return String The URL corresponding to the oid.
 */
	public String getAppURL(String oid) {
		String nodenameAndURL = null;
		if(oid.equals(this.OID)) {
			return url;
		}
		Iterator ch = children.iterator();
		while(ch.hasNext()) {
			CCSystemActivityEvent evt = (CCSystemActivityEvent)ch.next();
			if(oid.equals(evt.getOID())) {
				return evt.getUrl();
			} 
			if(evt.getType().equals("folder")) {
				String ret = ((CCFeaturesEvent)evt).getAppURL(oid); 
				if(ret.equals("none")) {
					continue;
				} else {
					return ret;
				}	
			}
		}
		return "none";
	}
	/**
	 * Returns the displayName of the tree node with the given OID
	 * @param oid The OID of thr selected node
	 * @return String The displayname corresponding to the oid.
	 */
	public String getAppName(String oid) {
		String nodenameAndURL = null;
		if(oid.equals(this.OID)) {
			return displayName;
		}
		Iterator ch = children.iterator();
		while(ch.hasNext()) {
			CCSystemActivityEvent evt = (CCSystemActivityEvent)ch.next();
			if(oid.equals(evt.getOID())) {
				return evt.getDisplayName();
			} 
			if(evt.getType().equals("folder")) {
				String ret = ((CCFeaturesEvent)evt).getAppName(oid); 
				if(ret.equals("none")) {
					continue;
				} else {
					return ret;
				}	
			}
		}
		return "none";
	}
	
}