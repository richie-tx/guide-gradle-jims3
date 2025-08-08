/*
 * Created on Sep 8, 2006
 */
package pd.supervision.administercasenotes;

import java.util.Iterator;

import messaging.manageassociate.IsAssociateReferencedEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.supervision.manageassociate.SuperviseeAssociate;

/**
 * @author jmcnabb
 *
 */
public class CasenoteSuperviseeAssociates extends PersistentObject
{

	private Casenote parent;
	private String childId;
	private String parentId;
	private SuperviseeAssociate child;
	/**
	 * Set the reference value to class :: Code
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
	 * Get the reference value to class :: Code
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
//		if (child == null)
//		{
//			try
//			{
//				child = (Code)new Reference(this.childId, Code.class,  "CASENOTE_SUBJECT").getObject();
//			}
//			catch (Throwable t)
//			{
//				child = null;
//			}
//		}
	       if (child == null) {
	            child = (SuperviseeAssociate) new mojo.km.persistence.Reference(childId,
	            		SuperviseeAssociate.class).getObject();
	        }
	}

	/**
	* Gets referenced type SupervisionCode
	* @return SupervisionCode child
	*/
	public SuperviseeAssociate getChild()
	{
		fetch();
		initChild();
		return child;
	}
	/**
	 * set the type reference for class member child
	 * @param child
	 */
	public void setChild(SuperviseeAssociate lchild)
	{
		if (this.child == null || !this.child.equals(lchild))
		{
			markModified();
		}
		setChildId("" + lchild.getOID());
		this.child = (SuperviseeAssociate) new mojo.km.persistence.Reference(lchild).getObject();
	}
	/**
	 * Set the reference value to class :: Casenote
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
	* Get the reference value to class :: Casenote
	* @return String parentId
	*/
	public String getParentId()
	{
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class Casenote
	*/
	private void initParent()
	{
		if (parent == null)
		{
			parent =
				(Casenote) new mojo
					.km
					.persistence
					.Reference(parentId, Casenote.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.juvenilewarrant.JuvenileWarrant
	* @return JuvenileWarrant parent 
	*/
	public Casenote getParent()
	{
		fetch();
		initParent();
		return parent;
	}
	/**
	 * set the type reference for class member parent
	 * @param parent
	 */
	public void setParent(Casenote lparent)
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
		this.parent = (Casenote) new mojo.km.persistence.Reference(lparent).getObject();
	}
	
	static public boolean isAssociateReferencedInCasenote(IsAssociateReferencedEvent isAssocRefEvent)
	{
		boolean result = false;
		
		Iterator assocIter = CasenoteSuperviseeAssociates.findAll(isAssocRefEvent);
		if (assocIter.hasNext())
			result = true;
		
		return result;
	}
	
	/**
	 * @return 
	 * @param event
	 * @roseuid 438F22CA0277
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CasenoteSuperviseeAssociates.class);
	}
	
}
