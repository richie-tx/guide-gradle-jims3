//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\JuvenileInsurance.java

package pd.juvenile;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

public class JuvenileInsurance extends PersistentObject
{
	/**
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 * @contextKey INSURANCE_TYPE
	 */
	private Code insuranceType = null;

	private String juvenileNum;

	private String insuranceTypeId;

	private String carrier;

	private String policyNum;

	/**
	 * @return
	 */
	public String getCarrier()
	{
		fetch();
		return carrier;
	}

	/**
	 * @return
	 */
	public String getPolicyNum()
	{
		fetch();
		return policyNum;
	}

	/**
	 * @param string
	 */
	public void setCarrier(String string)
	{
		if (this.carrier == null || !this.carrier.equals(string))
		{
			markModified();
		}
		carrier = string;
	}

	/**
	 * @param string
	 */
	public void setPolicyNum(String string)
	{
		if (this.policyNum == null || !this.policyNum.equals(string))
		{
			markModified();
		}
		policyNum = string;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setInsuranceTypeId(String insuranceTypeId)
	{
		if (this.insuranceTypeId == null || !this.insuranceTypeId.equals(insuranceTypeId))
		{
			markModified();
		}
		insuranceType = null;
		this.insuranceTypeId = insuranceTypeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getInsuranceTypeId()
	{
		fetch();
		return insuranceTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initInsuranceType()
	{
		if (insuranceType == null)
		{
			insuranceType = (Code) new mojo.km.persistence.Reference(insuranceTypeId,
					Code.class, "INSURANCE_TYPE").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getInsuranceType()
	{
		initInsuranceType();
		return insuranceType;
	}

	/**
	 * set the type reference for class member insuranceType
	 */
	public void setInsuranceType(Code insuranceType)
	{
		if (this.insuranceType == null || !this.insuranceType.equals(insuranceType))
		{
			markModified();
		}
		if (insuranceType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(insuranceType);
		}
		setInsuranceTypeId("" + insuranceType.getOID());
		insuranceType.setContext("INSURANCE_TYPE");
		this.insuranceType = (Code) new mojo.km.persistence.Reference(insuranceType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.family.FamilyMember
	 */
	public void setJuvenileNum(String aJuvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(aJuvenileNum))
		{
			markModified();
		}
		this.juvenileNum = aJuvenileNum;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.family.FamilyMember
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * Finds all insurances by an attribute value
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator insurances = home.findAll(attributeName, attributeValue, JuvenileInsurance.class);
		return insurances;
	}
}
