package pd.codetable.criminal;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.ICodetable;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import java.util.Iterator;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author dnikolis
 TODO To change the template for this generated type comment go to Window -
 Preferences - Java - Code Style - Code Templates
 */
public class JuvenileCupsUnitAssignment extends PersistentObject implements ICodetable
{
	/**
	 * Properties for juvLocUnit
	 * @referencedType pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 * @detailerDoNotGenerate false
	 */
	private JuvLocationUnit juvLocUnit = null;
	private String juvLocUnitId;
	private String keymap;
	private String zip;
	

	/**
	 * @return Returns the zip.
	 */
	public String getZip() {
		fetch();
		return zip;
	}
	
	/**
	 * @param zip The zip to set.
	 */
	public void setZip(String zip) {
		if (this.zip == null || !this.zip.equals(zip))
		{
			markModified();
		}
		this.zip = zip;
	}
	
	public Iterator findAll()
	{
		fetch();
		IHome home = new Home();
		return home.findAll(JuvenileCupsUnitAssignment.class);
	}

	/**
	 * Gets referenced type pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 */
	public JuvLocationUnit getJuvLocUnit()
	{
		initJuvLocUnit();
		return juvLocUnit;
	}

	/**
	 * Get the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 */
	public String getJuvLocUnitId()
	{
		fetch();
		return juvLocUnitId;
	}

	/**
	 * 
	 * @return Returns the keymap.
	 */
	public String getKeymap()
	{
		fetch();
		return keymap;
	}

	public void inActivate()
	{
		markModified();
	}

	/**
	 * Initialize class relationship to class pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 */
	private void initJuvLocUnit()
	{
		if (juvLocUnit == null)
		{
			juvLocUnit = (JuvLocationUnit) new mojo.km.persistence.Reference(
					juvLocUnitId, JuvLocationUnit.class)
					.getObject();
		}
	}

	/**
	 * set the type reference for class member juvLocUnit
	 */
	public void setJuvLocUnit(JuvLocationUnit juvLocUnit)
	{
		if (this.juvLocUnit == null || !this.juvLocUnit.equals(juvLocUnit))
		{
			markModified();
		}
		if (juvLocUnit.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(juvLocUnit);
		}
		setJuvLocUnitId("" + juvLocUnit.getOID());
		this.juvLocUnit = (JuvLocationUnit) new mojo.km.persistence.Reference(
				juvLocUnit).getObject();
	}



	/**
	 * Set the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 */
	public void setJuvLocUnitId(String juvLocUnitId)
	{
		if (this.juvLocUnitId == null || !this.juvLocUnitId.equals(juvLocUnitId))
		{
			markModified();
		}
		juvLocUnit = null;
		this.juvLocUnitId = juvLocUnitId;
	}
	 
	/**
	 * 
	 * @param keymap
	 The keymap to set.
	 */
	public void setKeymap(String keymap)
	{
		if (this.keymap == null || !this.keymap.equals(keymap))
		{
			markModified();
		}
		this.keymap = keymap;
	}
	
	/**
	* Finds all JuvenileCupsUnitAssignment
	* @param juvenileCupsUnitAssignment
	* @return 
	*/
	static public JuvenileCupsUnitAssignment find(String juvenileCupsUnitAssignmentId) {
		return (JuvenileCupsUnitAssignment) new Home().find(juvenileCupsUnitAssignmentId, JuvenileCupsUnitAssignment.class);
	}

	static public Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, JuvenileCupsUnitAssignment.class);
	}
	
	/**
	* Finds all JuvenileCupsUnitAssignment by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, JuvenileCupsUnitAssignment.class);
	}

}
