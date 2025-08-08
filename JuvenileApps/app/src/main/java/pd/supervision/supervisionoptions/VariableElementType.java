package pd.supervision.supervisionoptions;

import java.util.Collection;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.GetAssociationEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.codetable.CodeTable;
import pd.codetable.ICodetable;

/**
 * @roseuid 42F7C402032C
 */
public class VariableElementType extends PersistentObject implements ICodetable {
	static public VariableElementType find(String vetId) {
		Home home = new Home();
		return (VariableElementType) home
				.find(vetId, VariableElementType.class);
	}

	/**
	 * @param event
	 * @return Iterator Condition
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, VariableElementType.class);
	}

	/**
	 */
	static public Iterator findAll(String agencyId) {
		Home home = new Home();
		return home.findAll("agencyId", agencyId, VariableElementType.class);
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
	    IHome home = new Home();
		return home.findAll(attrName, attrValue, VariableElementType.class);
	}

	
	/**
	 * Properties for agencies
	 * 
	 * @detailerDoNotGenerate false
	 * @referencedType pd.contact.agency.Agency
	 * @associationType simple
	 */
	private Collection agencies;

	private String description;

	/**
	 * Properties for elementCodeTable
	 * 
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.CodeTable
	 */
	private CodeTable elementCodeTable = null;

	private String elementCodeTableId;

	/**
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 * @contextKey UI_TYPE
	 */
	private Code enumerationType = null;

	private String enumerationTypeId;

	private boolean isEnumeration;

	private boolean isReference;

	private boolean isVolatile;
	
	private boolean isCalculated;
	
	private String dataType;  //used with isCalculated

	/**
	 * Properties for variableElementTypeCodeTable
	 * 
	 * @referencedType pd.supervision.supervisionoptions.VariableElementTypeEnumerationInfo
	 * @detailerDoNotGenerate true
	 */
	//	private VariableElementTypeEnumerationInfo variableElementTypeCodeTable =
	// null;
	private String name;

	private String sampleValue;

	//	private String variableElementTypeCodeTableId;
	private String type;

	/**
	 * @roseuid 42F7C402032C
	 */
	public VariableElementType() {
	}

	/**
	 * Clears all pd.contact.agency.Agency from class relationship collection.
	 */
	public void clearAgencies() {
		initAgencies();
		agencies.clear();
	}

	/**
	 * returns a collection of pd.contact.agency.Agency
	 */
	public Collection getAgencies() {
		initAgencies();
		java.util.ArrayList retVal = new java.util.ArrayList();
		Iterator i = agencies.iterator();
		while (i.hasNext()) {
			VariableElementTypeAgenciesAgency actual = (VariableElementTypeAgenciesAgency) i
					.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}

	/**
	 * Access method for the description property.
	 * 
	 * @return the current value of the description property
	 */
	public String getDescription() {
		fetch();
		return description;
	}

	/**
	 * Gets referenced type pd.codetable.CodeTable
	 */
	public CodeTable getElementCodeTable() {
		fetch();
		initElementCodeTable();
		return elementCodeTable;
	}

	/**
	 * Get the reference value to class :: pd.codetable.CodeTable
	 */
	public String getElementCodeTableId() {
		fetch();
		return elementCodeTableId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getEnumerationType() {
		initEnumerationType();
		return enumerationType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getEnumerationTypeId() {
		fetch();
		return enumerationTypeId;
	}

	/**
	 * Mapping Hack!
	 */
	public boolean getIsEnumeration() {
		fetch();
		return isEnumeration;
	}

	/**
	 * Mapping Hack!
	 */
	public boolean getIsReference() {
		fetch();
		return isReference;
	}

	/**
	 * @return Returns the isVolatile.
	 */
	public boolean getIsVolatile() {
		fetch();
		return isVolatile;
	}

	/**
	 * Access method for the name property.
	 * 
	 * @return the current value of the name property
	 */
	public String getName() {
		fetch();
		return name;
	}

	/**
	 * Access method for the sampleValue property.
	 * 
	 * @return the current value of the sampleValue property
	 */
	public String getSampleValue() {
		fetch();
		return sampleValue;
	}

	/**
	 * @return
	 */
	public String getType() {
		fetch();
		return type;
	}

	//	/**
	//	* Set the reference value to class ::
	// pd.supervision.supervisionoptions.VariableElementTypeEnumerationInfo
	//	*/
	//	public void setVariableElementTypeCodeTableId(String
	// variableElementTypeCodeTableId) {
	//		if (this.variableElementTypeCodeTableId == null ||
	// !this.variableElementTypeCodeTableId.equals(variableElementTypeCodeTableId))
	// {
	//			markModified();
	//		}
	//		variableElementTypeCodeTable = null;
	//		this.variableElementTypeCodeTableId = variableElementTypeCodeTableId;
	//	}
	//	/**
	//	* Get the reference value to class ::
	// pd.supervision.supervisionoptions.VariableElementTypeEnumerationInfo
	//	*/
	//	public String getVariableElementTypeCodeTableId() {
	//		fetch();
	//		return variableElementTypeCodeTableId;
	//	}
	//	/**
	//	* Initialize class relationship to class
	// pd.supervision.supervisionoptions.VariableElementTypeEnumerationInfo
	//	*/
	//	private void initVariableElementTypeCodeTable() {
	//		//
	//		if (variableElementTypeCodeTable == null) {
	//			// get VariableElementTypeEnumerationInfo from VariableElementTypeId
	//			Iterator variableElementTypes =
	// VariableElementTypeEnumerationInfo.findAll("variableElementTypeId",
	// this.getOID().toString());
	//			if(variableElementTypes.hasNext()){
	//				variableElementTypeCodeTable =
	// (VariableElementTypeEnumerationInfo)variableElementTypes.next();
	//			}
	//		}

	//		if (variableElementTypeCodeTable == null) {
	//			variableElementTypeCodeTable =
	//				(pd.supervision.supervisionoptions.VariableElementTypeEnumerationInfo)
	// new
	// mojo
	//					.km
	//					.persistence
	//					.Reference(variableElementTypeCodeTableId,
	// pd.supervision.supervisionoptions.VariableElementTypeEnumerationInfo.class)
	//					.getObject();
	//		}
	//	}
	//	/**
	//	* Gets referenced type
	// pd.supervision.supervisionoptions.VariableElementTypeEnumerationInfo
	//	*/
	//	public VariableElementTypeEnumerationInfo
	// getVariableElementTypeCodeTable() {
	//		fetch();
	//		initVariableElementTypeCodeTable();
	//		return variableElementTypeCodeTable;
	//	}
	//	/**
	//	* set the type reference for class member variableElementTypeCodeTable
	//	*/
	//	public void setVariableElementTypeCodeTable(
	// VariableElementTypeEnumerationInfo variableElementTypeCodeTable )
	//	{
	//		if (this.variableElementTypeCodeTable == null ||
	// !this.variableElementTypeCodeTable.equals(variableElementTypeCodeTable))
	// {
	//			markModified();
	//		}
	//		if (variableElementTypeCodeTable.getOID() == null) {
	//			new mojo.km.persistence.Home().bind(variableElementTypeCodeTable);
	//		}
	//		setVariableElementTypeCodeTableId("" +
	// variableElementTypeCodeTable.getOID());
	//		this.variableElementTypeCodeTable =
	//			(VariableElementTypeEnumerationInfo) new
	// mojo.km.persistence.Reference(variableElementTypeCodeTable).getObject();
	//	}
	/**
	 * Initialize class relationship implementation for pd.contact.agency.Agency
	 */
	private void initAgencies() {
		if (agencies == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			agencies = new mojo.km.persistence.ArrayList(
					VariableElementTypeAgenciesAgency.class,
					"parentId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.CodeTable
	 */
	private void initElementCodeTable() {
		if (elementCodeTable == null) {
			elementCodeTable = (CodeTable) new mojo.km.persistence.Reference(
					elementCodeTableId, CodeTable.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initEnumerationType() {
		if (enumerationType == null) {
			enumerationType = (Code) new mojo.km.persistence.Reference(
					enumerationTypeId, Code.class, "UI_TYPE")
					.getObject();
		}
	}

	/**
	 * insert a pd.contact.agency.Agency into class relationship collection.
	 */
	public void insertAgencies(pd.contact.agency.Agency anObject) {
		initAgencies();
		VariableElementTypeAgenciesAgency actual = new VariableElementTypeAgenciesAgency();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		agencies.add(actual);
	}

	/**
	 * @return
	 */
	public boolean isEnumeration() {
		fetch();
		return isEnumeration;
	}

	/**
	 * Access method for the type property.
	 * 
	 * @return the current value of the type property
	 */
	public boolean isReference() {
		fetch();
		return isReference;
	}

	/**
	 * Removes a pd.contact.agency.Agency from class relationship collection.
	 */
	public void removeAgencies(pd.contact.agency.Agency anObject) {
		initAgencies();
		GetAssociationEvent assocEvent = new GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		VariableElementTypeAgenciesAgency actual = (VariableElementTypeAgenciesAgency) new mojo.km.persistence.Reference(
				assocEvent, VariableElementTypeAgenciesAgency.class)
				.getObject();
		agencies.remove(actual);
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param aDescription
	 *            the new value of the description property
	 */
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}

	/**
	 * set the type reference for class member elementCodeTable
	 */
	public void setElementCodeTable(CodeTable elementCodeTable) {
		if (this.elementCodeTable == null
				|| !this.elementCodeTable.equals(elementCodeTable)) {
			markModified();
		}
		if (elementCodeTable.getOID() == null) {
			new mojo.km.persistence.Home().bind(elementCodeTable);
		}
		setElementCodeTableId("" + elementCodeTable.getOID());
		this.elementCodeTable = (CodeTable) new mojo.km.persistence.Reference(
				elementCodeTable).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.CodeTable
	 */
	public void setElementCodeTableId(String elementCodeTableId) {
		if (this.elementCodeTableId == null
				|| !this.elementCodeTableId.equals(elementCodeTableId)) {
			markModified();
		}
		elementCodeTable = null;
		this.elementCodeTableId = elementCodeTableId;
	}

	/**
	 * @param b
	 */
	public void setEnumeration(boolean b) {
		if (this.isEnumeration != b) {
			markModified();
		}
		isEnumeration = b;
	}

	/**
	 * set the type reference for class member enumerationType
	 */
	public void setEnumerationType(Code enumerationType) {
		if (this.enumerationType == null
				|| !this.enumerationType.equals(enumerationType)) {
			markModified();
		}
		if (enumerationType.getOID() == null) {
			new mojo.km.persistence.Home().bind(enumerationType);
		}
		setEnumerationTypeId("" + enumerationType.getOID());
		enumerationType.setContext("UI_TYPE");
		this.enumerationType = (Code) new mojo.km.persistence.Reference(
				enumerationType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setEnumerationTypeId(String enumerationTypeId) {
		if (this.enumerationTypeId == null
				|| !this.enumerationTypeId.equals(enumerationTypeId)) {
			markModified();
		}
		enumerationType = null;
		this.enumerationTypeId = enumerationTypeId;
	}

	/**
	 * Mapping Hack!
	 */
	public void setIsEnumeration(boolean b) {
		if (this.isEnumeration != b) {
			markModified();
		}
		isEnumeration = b;
	}

	/**
	 * Mapping Hack!
	 */
	public void setIsReference(boolean aIsReference) {
		if (this.isReference != aIsReference) {
			markModified();
		}
		isReference = aIsReference;
	}

	/**
	 * @param isVolatile
	 *            The isVolatile to set.
	 */
	public void setIsVolatile(boolean aIsVolatile) {
		if (this.isVolatile != aIsVolatile) {
			markModified();
		}
		isVolatile = aIsVolatile;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param aName
	 *            the new value of the name property
	 */
	public void setName(String aName) {
		if (this.name == null || !this.name.equals(aName)) {
			markModified();
		}
		name = aName;
	}

	/**
	 * @param b
	 */
	public void setReference(boolean b) {
		if (this.isReference != b) {
			markModified();
		}
		isReference = b;
	}

	/**
	 * Sets the value of the sampleValue property.
	 * 
	 * @param aSampleValue
	 *            the new value of the sampleValue property
	 */
	public void setSampleValue(String aSampleValue) {
		if (this.sampleValue == null || !this.sampleValue.equals(aSampleValue)) {
			markModified();
		}
		sampleValue = aSampleValue;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		if (this.type == null || !this.type.equals(string)) {
			markModified();
		}
		type = string;
	}
	public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(VariableElementType.class);
	}

	public void inActivate() {
		// TODO Auto-generated method stub
		
	}

	public String getDataType() {
		fetch();
		return dataType;
	}

	public boolean getIsCalculated() {
		fetch();
		return isCalculated;
	}

	public void setDataType(String dataType) {
		if (this.dataType == null || !this.dataType.equals(dataType)) {
			markModified();
		}
		this.dataType = dataType;
	}

	public void setIsCalculated(boolean isCalculated) {
		if (this.isCalculated != isCalculated) {
			markModified();
		}
		this.isCalculated = isCalculated;
	}
}
