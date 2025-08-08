package pd.supervision.supervisionorder;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @roseuid 44D0C391027C
 */
public class SupervisionPeriodRedirect extends PersistentObject
{
	/**
	 * Properties for sourceSupervisinPeriod
	 */
	private SupervisionPeriod sourceSupervisinPeriod = null;
	/**
	 * Properties for targetSupervisionPeriod
	 */
	private SupervisionPeriod targetSupervisionPeriod = null;
	private String sourceSupervisinPeriodId;
	private String targetSupervisionPeriodId;

	/**
	 * @roseuid 44D0C391027C
	 */
	public SupervisionPeriodRedirect()
	{
	}

	static public SupervisionPeriodRedirect create(SupervisionPeriod supervisionPeriod)
	{
		SupervisionPeriodRedirect supervisionPeriodRedirect = new SupervisionPeriodRedirect();
		supervisionPeriodRedirect.setSourceSupervisinPeriod(supervisionPeriod);
		supervisionPeriodRedirect.setTargetSupervisionPeriod(supervisionPeriod);
		return supervisionPeriodRedirect;
	}

	/**
	* @return 
	* @param oid
	*/
	static public SupervisionPeriodRedirect find(String oid)
	{
		IHome home = new Home();
		SupervisionPeriodRedirect periodRedirect = (SupervisionPeriodRedirect) home.find(oid, SupervisionPeriodRedirect.class);
		return periodRedirect;
	}

	/**
	 * @return
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, SupervisionPeriodRedirect.class);
	}

	public static SupervisionPeriodRedirect findBySourcePeriod(String sourceSupervisionPeriodId){
		SupervisionPeriodRedirect supervisionPeriodRedirect = null;
		Iterator iter = findAll("sourceSupervisinPeriodId", sourceSupervisionPeriodId);
		// there will be only one 
		if(iter.hasNext()){
			supervisionPeriodRedirect = (SupervisionPeriodRedirect)iter.next();
		}
		return supervisionPeriodRedirect;
	}

	public static Iterator findByTargetPeriod(String sourceSupervisionPeriodId){
		SupervisionPeriodRedirect supervisionPeriodRedirect = null;
		Iterator iter = findAll("targetSupervisionPeriodId", sourceSupervisionPeriodId);
		return iter;
	}
	
	/**
	 * Access method for the sourceSupervisinPeriod property.
	 * @return the current value of the sourceSupervisinPeriod property
	 */
	public SupervisionPeriod getSourceSupervisinPeriod()
	{
		initSourceSupervisinPeriod();
		return sourceSupervisinPeriod;
	}

	/**
	 * Access method for the targetSupervisionPeriod property.
	 * @return the current value of the targetSupervisionPeriod property
	 */
	public SupervisionPeriod getTargetSupervisionPeriod()
	{
		initTargetSupervisionPeriod();
		return targetSupervisionPeriod;
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionorder.SupervisionPeriod
	 */
	public void setSourceSupervisinPeriodId(String aSourceSupervisionPeriodId)
	{
		if (this.sourceSupervisinPeriodId == null || !this.sourceSupervisinPeriodId.equals(aSourceSupervisionPeriodId))
		{
			markModified();
		}
		sourceSupervisinPeriod = null;
		this.sourceSupervisinPeriodId = aSourceSupervisionPeriodId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionorder.SupervisionPeriod
	 */
	public String getSourceSupervisinPeriodId()
	{
		fetch();
		return sourceSupervisinPeriodId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionorder.SupervisionPeriod
	 */
	private void initSourceSupervisinPeriod()
	{
		if (sourceSupervisinPeriod == null)
		{
			sourceSupervisinPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
					sourceSupervisinPeriodId, SupervisionPeriod.class).getObject();
		}
	}

	/**
	 * set the type reference for class member sourceSupervisinPeriod
	 */
	public void setSourceSupervisinPeriod(SupervisionPeriod aSourceSupervisionPeriod)
	{
		if (this.sourceSupervisinPeriod == null || !this.sourceSupervisinPeriod.equals(aSourceSupervisionPeriod))
		{
			markModified();
		}
		if (aSourceSupervisionPeriod.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSourceSupervisionPeriod);
		}
		setSourceSupervisinPeriodId("" + aSourceSupervisionPeriod.getOID());
		this.sourceSupervisinPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
				aSourceSupervisionPeriod).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionorder.SupervisionPeriod
	 */
	public void setTargetSupervisionPeriodId(String aTargetSupervisionPeriodId)
	{
		if (this.targetSupervisionPeriodId == null || !this.targetSupervisionPeriodId.equals(aTargetSupervisionPeriodId))
		{
			markModified();
		}
		targetSupervisionPeriod = null;
		this.targetSupervisionPeriodId = aTargetSupervisionPeriodId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionorder.SupervisionPeriod
	 */
	public String getTargetSupervisionPeriodId()
	{
		fetch();
		return targetSupervisionPeriodId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionorder.SupervisionPeriod
	 */
	private void initTargetSupervisionPeriod()
	{
		if (targetSupervisionPeriod == null)
		{
			targetSupervisionPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
					targetSupervisionPeriodId, SupervisionPeriod.class).getObject();
		}
	}

	/**
	 * set the type reference for class member targetSupervisionPeriod
	 */
	public void setTargetSupervisionPeriod(SupervisionPeriod aTargetSupervisionPeriod)
	{
		if (this.targetSupervisionPeriod == null || !this.targetSupervisionPeriod.equals(aTargetSupervisionPeriod))
		{
			markModified();
		}
		if (aTargetSupervisionPeriod.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aTargetSupervisionPeriod);
		}
		setTargetSupervisionPeriodId("" + aTargetSupervisionPeriod.getOID());
		this.targetSupervisionPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
				aTargetSupervisionPeriod).getObject();
	}
}

