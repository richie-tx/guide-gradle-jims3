/*
 * Created on Sep 7, 2005
 *
 */
package pd.pattern;

/**
 * @author Jim Fisher
 *
 * Encapsulates the creation of simple and complex object structures
 * for when the object class representation is consistent.  In contrast
 * to IBuilder where the representation can change (i.e. DTA warrant vs. OIC warrant). 
 * 
 */
public interface IAssembler
{
	public void assemble();
	public Object getResult();
}
