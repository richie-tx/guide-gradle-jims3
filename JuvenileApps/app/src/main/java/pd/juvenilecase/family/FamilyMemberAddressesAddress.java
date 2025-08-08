/*
 * Project: JIMS2
 * Class:   pd.juvenilecase.family.FamilyMemberAddressesAddress
 * Version: 1.0.0
 *
 * Date:    2005-09-19
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.juvenilecase.family;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.address.Address;
import pd.juvenilecase.family.*;

/**
 * Set the reference value to class :: pd.address.Address
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class FamilyMemberAddressesAddress extends mojo.km.persistence.PersistentObject {

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    /**
     * Properties for child
     */
    Address child = null;

    /**
     * Properties for parent
     */
    FamilyMember parent = null;

    private String parentId;

    private String childId;


    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     * Set the reference value to class :: pd.address.Address
     *  
     * @param childId The child id.
     */
    public void setChildId(String childId) {
        if (this.childId == null || !this.childId.equals(childId)) {
        	markModified();
        }
        child = null;
        this.childId = childId;
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.setChildId

    /**
     * Get the reference value to class :: pd.address.Address
     *  
     * @return  The child id.
     */
    public String getChildId() {
        fetch();
        return childId;
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.getChildId

    /**
     * Gets referenced type pd.address.Address
     *  
     * @return  The child.
     */
    public Address getChild() {
        initChild();
        fetch();
        return child;
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.getChild

    /**
     * set the type reference for class member child
     *  
     * @param child The child.
     */
    public void setChild(Address child) {
        if (this.child == null || !this.child.equals(child)) {
        	markModified();
        }
        if (child.getOID() == null) {
        	new mojo.km.persistence.Home().bind(child);
        }
        setChildId("" + child.getOID());
        this.child = (Address) new mojo.km.persistence.Reference(child).getObject();
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.setChild

    /**
     * Set the reference value to class :: pd.juvenilecase.family.FamilyMember
     *  
     * @param parentId The parent id.
     */
    public void setParentId(String parentId) {
        if (this.parentId == null || !this.parentId.equals(parentId)) {
        	markModified();
        }
        parent = null;
        this.parentId = parentId;
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.setParentId

    /**
     * Get the reference value to class :: pd.juvenilecase.family.FamilyMember
     *  
     * @return  The parent id.
     */
    public String getParentId() {
        fetch();
        return parentId;
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.getParentId

    /**
     * Gets referenced type pd.juvenilecase.family.FamilyMember
     *  
     * @return  The parent.
     */
    public FamilyMember getParent() {
        initParent();
        fetch();
        return parent;
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.getParent

    /**
     * set the type reference for class member parent
     *  
     * @param parent The parent.
     */
    public void setParent(FamilyMember parent) {
        if (this.parent == null || !this.parent.equals(parent)) {
        	markModified();
        }
        if (parent.getOID() == null) {
        	new mojo.km.persistence.Home().bind(parent);
        }
        setParentId("" + parent.getOID());
        this.parent = (FamilyMember) new mojo.km.persistence.Reference(parent).getObject();
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.setParent

    /**
     * Initialize class relationship to class pd.address.Address
     */
    private void initChild() {
        if (child == null) {
        	child = (Address) new mojo.km.persistence.Reference(childId, Address.class).getObject();
        }
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.initChild

    /**
     * Initialize class relationship to class pd.juvenilecase.family.FamilyMember
     */
    private void initParent() {
        if (parent == null) {
        	parent = (FamilyMember) new mojo.km.persistence.Reference(parentId, FamilyMember.class).getObject();
        }
    }//end of pd.juvenilecase.family.FamilyMemberAddressesAddress.initParent

	/**
	* Finds all family member address by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator addresses = home.findAll(attributeName, attributeValue, FamilyMemberAddressesAddress.class);
		return addresses;
	}

} // end FamilyMemberAddressesAddress
