package messaging.appshell;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import mojo.km.messaging.RequestEvent;
import com.cc.framework.ui.model.TreeGroupDataModel;
import com.cc.framework.ui.model.TreeNodeDataModel;


public class CCFavoritesEvent extends RequestEvent implements TreeGroupDataModel{

    private Vector favoritesFeatures = new java.util.Vector();
    private String displayName;
    public static final String type = "folder";
    
	public CCFavoritesEvent() { }

	public CCFavoritesEvent(String name) {
		displayName = name;
	}
	
	public void insertFeatureList(CCFavoritesFeatureListEvent aFeatureList) {
        favoritesFeatures.add(aFeatureList);
    }

	public Iterator getFeatureLists() {
        return favoritesFeatures.iterator();
    }
    
	/**
		 * @see com.cc.framework.ui.model.TreeGroupDataModel#addChild(TreeNodeDataModel)
		 */
		public void addChild(TreeNodeDataModel child) {

			favoritesFeatures.add(child);

			// set Parent
			child.setParent(this);
		}

		
		/**
		 * @see com.cc.framework.ui.model.TreeGroupDataModel#getChild(int)
		 */
		public TreeNodeDataModel getChild(int index) {
			return (TreeNodeDataModel) favoritesFeatures.elementAt(index);
		}
	
		/**
		 * @see com.cc.framework.ui.model.TreeNodeDataModel#getParentKey()
		 */
		public String getParentKey() {
			return "-11";
		}

		/**
		 * @see com.cc.framework.ui.model.TreeNodeDataModel#getUniqueKey()
		 */
		public String getUniqueKey() {
			return "-1";
		}
	
		public int size() {
			int count = -1;
			if(favoritesFeatures != null) {
				count = favoritesFeatures.size(); 
			}
			return count;
		}
		
		/**
		 * @return
	 	*/
		public TreeGroupDataModel getParent() {
			return null;
		}
		
		public void setParent(TreeGroupDataModel parent) {
			// do nothing.. does not have a parent
		}


	/**
	 * @return
	 */
	public static String getType() {
		return type;
	}

}
