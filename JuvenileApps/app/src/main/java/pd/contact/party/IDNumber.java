package pd.contact.party;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import naming.PDCodeTableConstants;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import pd.codetable.Code;

public class IDNumber extends PersistentObject {
	/**
	 * Properties for driversLicenseClass
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey DRIVERS_LICENSE_CLASS
	 */
	private Code driversLicenseClass = null;

	private String driversLicenseClassId;

	private String driversLicenseExpirationYear;

	private String driversLicenseNum;
	private String lcDate;
	private String lcTime;

	private String spn;

	/**
	 * Properties for driversLicenseState
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey STATE_ABBR
	 */
	private Code driversLicenseState = null;

	private String driversLicenseStateId;
	private String ssn;

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getDriversLicenseClass() {
		fetch();
		initDriversLicenseClass();
		return driversLicenseClass;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getDriversLicenseClassId() {
		fetch();
		return driversLicenseClassId;
	}

	/**
	 * Access method for the driversLicenseExpirationYear property.
	 * 
	 * @return the current value of the driversLicenseExpirationYear property
	 */
	public String getDriversLicenseExpirationYear() {
		fetch();
		return driversLicenseExpirationYear;
	}

	/**
	 * Access method for the driversLicenseNum property.
	 * 
	 * @return the current value of the driversLicenseNum property
	 */
	public String getDriversLicenseNum() {
		fetch();
		return driversLicenseNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getDriversLicenseState() {
		fetch();
		initDriversLicenseState();
		return driversLicenseState;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getDriversLicenseStateId() {
		fetch();
		return driversLicenseStateId;
	}

	public String getLcDate() {
		fetch();
		return lcDate;
	}

	public String getLcTime() {
		fetch();
		return lcTime;
	}

	/**
	 * Access method for the spn property.
	 * 
	 * @return the current value of the spn property
	 */
	public String getSpn() {
		fetch();
		return spn;
	}

	/**
	 * Access method for the ssn property.
	 * 
	 * @return the current value of the ssn property
	 */
	public String getSsn() {
		fetch();
		return ssn;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDriversLicenseClass() {
		if (driversLicenseClass == null) {
			try {
				driversLicenseClass = (Code) new mojo.km.persistence.Reference(
						driversLicenseClassId, Code.class,
						"DRIVERS_LICENSE_CLASS").getObject();
			} catch (Throwable t) {
				driversLicenseClass = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDriversLicenseState() {
		if (driversLicenseState == null) {
			try {
				driversLicenseState = (Code) new mojo.km.persistence.Reference(
						driversLicenseStateId, Code.class,
						PDCodeTableConstants.STATE_ABBR).getObject();
			} catch (Throwable t) {
				driversLicenseState = null;
			}
		}
	}

	/**
	 * set the type reference for class member driversLicenseClass
	 * 
	 * @param theDriversLicenseClass
	 */
	public void setDriversLicenseClass(Code theDriversLicenseClass) {
		if (this.driversLicenseClass == null
				|| !this.driversLicenseClass.equals(theDriversLicenseClass)) {
			markModified();
		}
		if (theDriversLicenseClass.getOID() == null) {
			new mojo.km.persistence.Home().bind(theDriversLicenseClass);
		}
		setDriversLicenseClassId("" + theDriversLicenseClass.getOID());
		this.driversLicenseClass = (Code) new mojo.km.persistence.Reference(
				theDriversLicenseClass).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param theDriversLicenseClassId
	 */
	public void setDriversLicenseClassId(String theDriversLicenseClassId) {
		if (this.driversLicenseClassId == null
				|| !this.driversLicenseClassId.equals(theDriversLicenseClassId)) {
			markModified();
		}
		driversLicenseClass = null;
		this.driversLicenseClassId = theDriversLicenseClassId;
	}

	/**
	 * Sets the value of the driversLicenseExpirationYear property.
	 * 
	 * @param aDriversLicenseExpirationYear
	 *            the new value of the driversLicenseExpirationYear property
	 */
	public void setDriversLicenseExpirationYear(
			String aDriversLicenseExpirationYear) {
		if (this.driversLicenseExpirationYear == null
				|| !this.driversLicenseExpirationYear
						.equals(aDriversLicenseExpirationYear)) {
			markModified();
		}
		driversLicenseExpirationYear = aDriversLicenseExpirationYear;
	}

	/**
	 * Sets the value of the driversLicenseNum property.
	 * 
	 * @param aDriversLicenseNum
	 *            the new value of the driversLicenseNum property
	 */
	public void setDriversLicenseNum(String aDriversLicenseNum) {
		if (this.driversLicenseNum == null
				|| !this.driversLicenseNum.equals(aDriversLicenseNum)) {
			markModified();
		}
		driversLicenseNum = aDriversLicenseNum;
	}

	/**
	 * set the type reference for class member driversLicenseState
	 * 
	 * @param aDriversLicenseState
	 */
	public void setDriversLicenseState(Code aDriversLicenseState) {
		if (this.driversLicenseState == null
				|| !this.driversLicenseState.equals(aDriversLicenseState)) {
			markModified();
		}
		if (aDriversLicenseState.getOID() == null) {
			new mojo.km.persistence.Home().bind(aDriversLicenseState);
		}
		setDriversLicenseStateId("" + aDriversLicenseState.getOID());
		this.driversLicenseState = (Code) new mojo.km.persistence.Reference(
				aDriversLicenseState).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aDriversLicenseStateId
	 */
	public void setDriversLicenseStateId(String aDriversLicenseStateId) {
		if (this.driversLicenseStateId == null
				|| !this.driversLicenseStateId.equals(aDriversLicenseStateId)) {
			markModified();
		}
		driversLicenseState = null;
		this.driversLicenseStateId = aDriversLicenseStateId;
	}

	public void setLcDate(String lcDate) {
		if (this.lcDate == null || !this.lcDate.equals(lcDate)) {
			markModified();
		}
		this.lcDate = lcDate;
	}

	public void setLcTime(String lcTime) {
		if (this.lcTime == null || !this.lcTime.equals(lcTime)) {
			markModified();
		}
		this.lcTime = lcTime;
	}

	/**
	 * Sets the value of the spn property.
	 * 
	 * @param spn
	 *            the new value of the spn property
	 */
	public void setSpn(String spn) {
		if (this.spn == null || !this.spn.equals(spn)) {
			markModified();
		}
		spn = spn;
	}

	/**
	 * Sets the value of the ssn property.
	 * 
	 * @param aSsn
	 *            the new value of the ssn property
	 */
	public void setSsn(String aSsn) {
		if (this.ssn == null || !this.ssn.equals(aSsn)) {
			markModified();
		}
		ssn = aSsn;
	}
	
	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public static List findAll(String attributeName, String attributeValue){
		IHome home = new Home();
		return CollectionUtil.iteratorToList(home.findAll(attributeName, attributeValue, IDNumber.class));
	}

}
