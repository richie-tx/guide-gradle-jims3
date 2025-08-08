package pd.juvenilewarrant;

import java.util.Iterator;

import naming.PDCodeTableConstants;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Reference;
import pd.codetable.Code;

/**
 * @author ryoung
 *  
 */
public class JuvenileWarrantCautionsCode extends mojo.km.persistence.PersistentObject implements IPersistentObjectAssociation
{
    private JuvenileWarrant parent;

    private String childId;

    private String parentId;

    private Code child;

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param lchildId
     */
    public void setChildId(String lchildId)
    {
        if (this.childId == null || !this.childId.equals(lchildId))
        {
            markModified();
        }
        child = null;
        this.childId = lchildId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String childId
     */
    public String getChildId()
    {
        fetch();
        return childId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initChild()
    {
        if (child == null)
        {
            Reference ref = new Reference(this.childId, Code.class, PDCodeTableConstants.CAUTIONS);
            if (ref != null)
            {
                child = (Code) ref.getObject();
            }
        }
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code child
     */
    public Code getChild()
    {
        fetch();
        initChild();
        return child;
    }

    /**
     * set the type reference for class member child
     * 
     * @param child
     */
    public void setChild(Code lchild)
    {
        if (this.child == null || !this.child.equals(lchild))
        {
            markModified();
        }
        if (lchild.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lchild);
        }
        setChildId(lchild.getCode());
        this.child = (Code) new mojo.km.persistence.Reference(lchild).getObject();
    }

    /**
     * Set the reference value to class :: pd.juvenilewarrant.JuvenileWarrant
     * 
     * @param parentId
     */
    public void setParentId(String lparentId)
    {
        if (this.parentId == null || !this.parentId.equals(lparentId))
        {
            markModified();
        }
        parent = null;
        this.parentId = lparentId;
    }

    /**
     * Get the reference value to class :: pd.juvenilewarrant.JuvenileWarrant
     * 
     * @return String parentId
     */
    public String getParentId()
    {
        fetch();
        return parentId;
    }

    /**
     * Initialize class relationship to class pd.juvenilewarrant.JuvenileWarrant
     */
    private void initParent()
    {
        if (parent == null)
        {
            parent = (JuvenileWarrant) new mojo.km.persistence.Reference(parentId,
                    JuvenileWarrant.class).getObject();
        }
    }

    /**
     * Gets referenced type pd.juvenilewarrant.JuvenileWarrant
     * 
     * @return JuvenileWarrant parent
     */
    public JuvenileWarrant getParent()
    {
        fetch();
        initParent();
        return parent;
    }

    /**
     * set the type reference for class member parent
     * 
     * @param parent
     */
    public void setParent(JuvenileWarrant lparent)
    {
        if (this.parent == null || !this.parent.equals(lparent))
        {
            markModified();
        }
        if (lparent.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lparent);
        }
        setParentId("" + lparent.getOID());
        this.parent = (JuvenileWarrant) new mojo.km.persistence.Reference(lparent).getObject();
    }
    
    static public Iterator findAll(String attrName, String attrValue) {
	IHome home = new Home();
	return (Iterator) home.findAll(attrName, attrValue, JuvenileWarrantCautionsCode.class);
}
}
