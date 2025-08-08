/*
 * Created on Apr 2, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.cscdcalendar;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FieldVisitAssociate extends PersistentObject{

	private String fvEventId;
	
	private String fvAssociateId;
	
	
	/**
	 * @return Returns the fvAssociateId.
	 */
	public String getFvAssociateId() {
		fetch();
		return fvAssociateId;
	}
	/**
	 * @param fvAssociateId The fvAssociateId to set.
	 */
	public void setFvAssociateId(String fvAssociateId) {
		if (this.fvAssociateId == null || !this.fvAssociateId.equals(fvAssociateId))
		{
			markModified();
		}
		this.fvAssociateId = fvAssociateId;
	}
	/**
	 * @return Returns the fvEventId.
	 */
	public String getFvEventId() {
		fetch();
		return fvEventId;
	}
	/**
	 * @param fvEventId The fvEventId to set.
	 */
	public void setFvEventId(String fvEventId) {
		if (this.fvEventId == null || !this.fvEventId.equals(fvEventId))
		{
			markModified();
		}
		this.fvEventId = fvEventId;
	}
	
	/**
	 * 
	 * @roseuid 44D79FFF0028
	 * @return java.util.Iterator
	 */
	static public Iterator findAll()
	{
		return new Home().findAll(FieldVisitAssociate.class);
	}

	/**
	 * 
	 * @roseuid 44D79FFF0028
	 * @return java.util.Iterator
	 */
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, FieldVisitAssociate.class);
	}
	
	/**
	 * 
	 * @roseuid 451840210342
	 * @return java.util.Iterator
	 * @param event
	 */
	static public FieldVisitAssociate find(String oid)
	{
		IHome home = new Home();
		return (FieldVisitAssociate)home.find(oid, FieldVisitAssociate.class);
	}

	
}
