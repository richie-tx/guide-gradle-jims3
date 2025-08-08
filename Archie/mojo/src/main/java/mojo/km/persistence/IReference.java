/*
 * Created on Jan 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.persistence;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface IReference
{
	/** 
	 * Get the persistent object being managed by this reference class.
	 **/
	public abstract PersistentObject getObject();
	/**
	 * Get persistent object's ID.
	 * @return OID
	 */
	public abstract String getOID();
}