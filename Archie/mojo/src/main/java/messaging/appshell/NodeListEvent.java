package messaging.appshell;

import java.util.*;
import mojo.km.messaging.RequestEvent;

/**
 * This Event class encapsulates the information used to build the Tree and FavoritesList. <pre>
 * Change History:
 * Author        Date        	Explanation
 * </pre>
 */
public class NodeListEvent extends RequestEvent {
    private Collection nodes = new Vector();

    /** Constructor */
    public NodeListEvent() {
        super();
    }

    /**
    * Inserts a NodeEvent into the Collection for this TreeEvent.
    * @param node The NodeEvent.
    */
    public void insertNode(NodeEvent node) {
        nodes.add(node);
    }

    /**
    * Returns an Iterator over the Collection of NodeEvents for this TreeEvent.
    * @return Iterator The Iterator.
    */
    public Iterator getNodes() {
        return nodes.iterator();
    }

    /**
    * Removes the given node from the list.
    */
    public void removeNode(NodeEvent node) {
        nodes.remove(node);
    }
    
    /**
     * Returns the Node given the nodeID
     * @param id
     * @return NodeEvent corresponding to the given id
     */
    public NodeEvent getNode(String id) {
    	NodeEvent node = null;
    	Iterator ite = nodes.iterator();
    	while(ite.hasNext()) {
    		node = (NodeEvent)ite.next();
    		if(node.getID().equals(id)) {
    			return node;
    		}
    	}
    	return null;
    }
    
    
	/**
	 * Returns the name of the parent for the node, given the nodeID
	 * @param id
	 * @return String corresponding to the given id
	 */
	public String getParentNodeName(String id) {
		NodeEvent node = null;
		Iterator ite = nodes.iterator();
		while(ite.hasNext()) {
			node = (NodeEvent)ite.next();
			String idToCompare = new StringBuffer(node.getParentID()).append(node.getID()).toString();
			if(idToCompare.equals(id)) {
				System.out.println("Parent Name : " + node.getAlias());
				return node.getAlias();
			}
		}
		return "Error";
	}
	
	/**
	 * Check to see if a node with the given id already exists in the collection.
	 * @param id
	 * @return boolean true if a node with the given id already exists
	 */
	public boolean alreadyExists(String id) {
		NodeEvent node = null;
		Iterator ite = nodes.iterator();
		while(ite.hasNext()) {
			node = (NodeEvent)ite.next();
			if(node.getID().equals(id)) {
				return true;
			}
		}
		return false;
	}		
		
}
