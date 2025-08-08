package pd.contact.party;

import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;

/**
* @author dgibler
*/
public class MiscellaneousId extends PersistentObject {
	/**
	* Properties for stateAgency
	* @referencedType pd.codetable.Code
	* @contextKey AGENCY
	*/
	private Code stateAgency = null;
	private String miscellaneousIdId;
	/**
	* Properties for idNumType
	* @referencedType pd.codetable.Code
	* @contextKey MISCELLANEOUS_NUMBER_TYPE
	*/
	private Code idNumType = null;
	private String idNumTypeId;
	private String partyId;
	private String stateAgencyId;
	private String idNum;
	/**
	* @roseuid 41C9C81C02B7
	*/
	public MiscellaneousId() {
	}
	/**
	* @roseuid 41C9C71C0353
	*/
	public void bind() {
		markModified();
	}
	/**
	* Access method for the idNum property.
	* @return the current value of the idNum property
	*/
	public String getIdNum() {
		fetch();
		return idNum;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code
	*/
	public Code getIdNumType() {
		fetch();
		initIdNumType();
		return idNumType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return idNumTypeId
	*/
	public String getIdNumTypeId() {
		fetch();
		return idNumTypeId;
	}
	/**
	* @return java.lang.String
	*/
	public String getMiscellaneousIdId() {
		return "" + getOID();
	}
	/**
	* @return java.lang.String
	*/
	public String getPartyId() {
		fetch();
		return partyId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code
	
	*/
	public Code getStateAgency() {
		fetch();
		initStateAgency();
		return stateAgency;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return stateAgencyId;
	
	*/
	public String getStateAgencyId() {
		fetch();
		return stateAgencyId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initIdNumType() {
		if (idNumType == null) {
			try {
				idNumType =
					(Code) new mojo.km.persistence.Reference(idNumTypeId, Code.class, "MISCELLANEOUS_NUMBER_TYPE").getObject();
			} catch (Throwable t) {
				idNumType = null;
			}
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStateAgency() {
		if (stateAgency == null) {
			try {
				stateAgency = (Code) new mojo.km.persistence.Reference(stateAgencyId, Code.class, PDCodeTableConstants.STATE_AGENCY).getObject();
			} catch (Throwable t) {
				stateAgency = null;
			}
		}
	}
	/**
	* Sets the value of the idNum property.
	* @param aIdNum the new value of the idNum property
	*/
	public void setIdNum(String aIdNum) {
		if (this.idNum == null || !this.idNum.equals(aIdNum)) {
			markModified();
		}
		idNum = aIdNum;
	}
	/**
	* set the type reference for class member idNumType
	* @param aIdNumType
	*/
	public void setIdNumType(Code aIdNumType) {
		if (this.idNumType == null || !this.idNumType.equals(aIdNumType)) {
			markModified();
		}
		if (aIdNumType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aIdNumType);
		}
		setIdNumTypeId("" + aIdNumType.getOID());
		this.idNumType = (Code) new mojo.km.persistence.Reference(aIdNumType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param aIdNumTypeId
	*/
	public void setIdNumTypeId(String aIdNumTypeId) {
		if (this.idNumTypeId == null || !this.idNumTypeId.equals(aIdNumTypeId)) {
			markModified();
		}
		idNumType = null;
		this.idNumTypeId = aIdNumTypeId;
	}
	/**
	* @param aMiscellaneousIdId
	*/
	public void setMiscellaneousIdId(String aMiscellaneousIdId) {
		if (this.miscellaneousIdId == null || !this.miscellaneousIdId.equals(aMiscellaneousIdId)) {
			markModified();
		}
		this.miscellaneousIdId = aMiscellaneousIdId;
	}
	/**
	* @param aPartyId
	*/
	public void setPartyId(String aPartyId) {
		if (this.partyId == null || !this.partyId.equals(aPartyId)) {
			markModified();
		}
		this.partyId = aPartyId;
	}
	/**
	* set the type reference for class member stateAgency
	* @param aStateAgency
	*/
	public void setStateAgency(Code aStateAgency) {
		if (this.stateAgency == null || !this.stateAgency.equals(aStateAgency)) {
			markModified();
		}
		if (aStateAgency.getOID() == null) {
			new mojo.km.persistence.Home().bind(aStateAgency);
		}
		setStateAgencyId("" + aStateAgency.getOID());
		this.stateAgency = (Code) new mojo.km.persistence.Reference(aStateAgency).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param aStateAgencyId
	*/
	public void setStateAgencyId(String aStateAgencyId) {
		if (this.stateAgencyId == null || !this.stateAgencyId.equals(aStateAgencyId)) {
			markModified();
		}
		stateAgency = null;
		this.stateAgencyId = aStateAgencyId;
	}
}
