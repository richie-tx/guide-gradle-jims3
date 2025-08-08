package pd.supervision.supervisionoptions;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class IncarcerationReferral extends PersistentObject implements Comparable
{
	private String supervisionOrderId;
	private String supervisionOrderCondId;
	private String conditionId;
	private String sprvOrderCondRelId;
	private String varElementTypeId;
	private String dataValue;
	private String varElementTypeName;
	
	
	public static IncarcerationReferral find(String oid)
	{
		IHome home = new Home();

		IncarcerationReferral incarcReferral = (IncarcerationReferral) home.find(oid, IncarcerationReferral.class);
		return incarcReferral;
	}//end of find()
    
   
	public static Iterator findAll()
	{
	    IHome home = new Home();
		Iterator iter = home.findAll(IncarcerationReferral.class);
		return iter;
	}//end of findAll()
	
    
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, IncarcerationReferral.class);
	}//end of findAll()

    
	static public Iterator findAll(String attrName, String attrValue)
	{
	    IHome home = new Home();
		return home.findAll(attrName, attrValue, IncarcerationReferral.class);
	}


	
	/**
	 * @return the supervisionOrderId
	 */
	public String getSupervisionOrderId() {
		fetch();
		return supervisionOrderId;
	}
	/**
	 * @param supervisionOrderId the supervisionOrderId to set
	 */
	public void setSupervisionOrderId(String supervisionOrderId) {
		if (this.supervisionOrderId == null || !this.supervisionOrderId.equals(supervisionOrderId))
		{
			markModified();
		}
		this.supervisionOrderId = supervisionOrderId;
	}
	/**
	 * @return the supervisionOrderCondId
	 */
	public String getSupervisionOrderCondId() {
		fetch();
		return supervisionOrderCondId;
	}
	/**
	 * @param supervisionOrderCondId the supervisionOrderCondId to set
	 */
	public void setSupervisionOrderCondId(String supervisionOrderCondId) {
		if (this.supervisionOrderCondId == null || !this.supervisionOrderCondId.equals(supervisionOrderCondId))
		{
			markModified();
		}
		this.supervisionOrderCondId = supervisionOrderCondId;
	}
	/**
	 * @return the conditionId
	 */
	public String getConditionId() {
		fetch();
		return conditionId;
	}


	/**
	 * @param conditionId the conditionId to set
	 */
	public void setConditionId(String conditionId) {
		if (this.conditionId == null || !this.conditionId.equals(conditionId))
		{
			markModified();
		}
		this.conditionId = conditionId;
	}


	/**
	 * @return the sprvOrderCondRelId
	 */
	public String getSprvOrderCondRelId() {
		fetch();
		return sprvOrderCondRelId;
	}
	/**
	 * @param sprvOrderCondRelId the sprvOrderCondRelId to set
	 */
	public void setSprvOrderCondRelId(String sprvOrderCondRelId) {
		if (this.sprvOrderCondRelId == null || !this.sprvOrderCondRelId.equals(sprvOrderCondRelId))
		{
			markModified();
		}
		this.sprvOrderCondRelId = sprvOrderCondRelId;
	}
	/**
	 * @return the varElementTypeId
	 */
	public String getVarElementTypeId() {
		fetch();
		return varElementTypeId;
	}
	/**
	 * @param varElementTypeId the varElementTypeId to set
	 */
	public void setVarElementTypeId(String varElementTypeId) {
		if (this.varElementTypeId == null || !this.varElementTypeId.equals(varElementTypeId))
		{
			markModified();
		}
		this.varElementTypeId = varElementTypeId;
	}
	/**
	 * @return the dataValue
	 */
	public String getDataValue() {
		fetch();
		return dataValue;
	}
	/**
	 * @param dataValue the dataValue to set
	 */
	public void setDataValue(String dataValue) {
		if (this.dataValue == null || !this.dataValue.equals(dataValue))
		{
			markModified();
		}
		this.dataValue = dataValue;
	}
	/**
	 * @return the varElementTypeName
	 */
	public String getVarElementTypeName() {
		fetch();
		return varElementTypeName;
	}
	/**
	 * @param varElementTypeName the varElementTypeName to set
	 */
	public void setVarElementTypeName(String varElementTypeName) {
		if (this.varElementTypeName == null || !this.varElementTypeName.equals(varElementTypeName))
		{
			markModified();
		}
		this.varElementTypeName = varElementTypeName;
	}


	public int compareTo(Object o) {

		if ( o == null ){
			return -1;
		}
		IncarcerationReferral c = ( IncarcerationReferral )o;
		
		if (c.getDataValue() == null){
			return -1;
		}		
		if (this.getDataValue() == null){
			return 1;
		}
		return this.getDataValue().compareToIgnoreCase(c.getDataValue());
	}
}
