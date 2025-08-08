package pd.codetable.person;

import java.util.Iterator;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author dgibler
 *
 * SchoolDistrictCode entity
 */
public class SchoolDistrictCode extends PersistentObject
{
	private String districtName;
	private String districtNum;
	private String magnetSchoolInd;
	private String schoolName;
	private String schoolNum;
	/**
	* @roseuid 418FD7E100A6
	*/
	public SchoolDistrictCode()
	{
	}
	/**
	* Access method for the districtName property.
	* @return the current value of the districtName property
	*/
	public String getDistrictName()
	{
		fetch();
		return districtName;
	}
	/**
	* Sets the value of the districtName property.
	* @param aDistrictName the new value of the districtName property
	*/
	public void setDistrictName(String aDistrictName)
	{
		if (this.districtName == null || !this.districtName.equals(aDistrictName))
		{
			markModified();
		}
		districtName = aDistrictName;
	}
	/**
	* Access method for the districtNum property.
	* @return the current value of the districtNum property
	*/
	public String getDistrictNum()
	{
		fetch();
		return districtNum;
	}
	/**
	* Sets the value of the districtNum property.
	* @param aDistrictNum the new value of the districtNum property
	*/
	public void setDistrictNum(String aDistrictNum)
	{
		if (this.districtNum == null || !this.districtNum.equals(aDistrictNum))
		{
			markModified();
		}
		districtNum = aDistrictNum;
	}
	/**
	* Access method for the magnetSchoolInd property.
	* @return the current value of the magnetSchoolInd property
	*/
	public String getMagnetSchoolInd()
	{
		fetch();
		return magnetSchoolInd;
	}
	/**
	* Sets the value of the magnetSchoolInd property.
	* @param aMagnetSchoolInd the new value of the magnetSchoolInd property
	*/
	public void setMagnetSchoolInd(String aMagnetSchoolInd)
	{
		if (this.magnetSchoolInd == null || !this.magnetSchoolInd.equals(aMagnetSchoolInd))
		{
			markModified();
		}
		magnetSchoolInd = aMagnetSchoolInd;
	}
	/**
	* Access method for the schoolName property.
	* @return the current value of the schoolName property
	*/
	public String getSchoolName()
	{
		fetch();
		return schoolName;
	}
	/**
	* Sets the value of the schoolName property.
	* @param aSchoolName the new value of the schoolName property
	*/
	public void setSchoolName(String aSchoolName)
	{
		if (this.schoolName == null || !this.schoolName.equals(aSchoolName))
		{
			markModified();
		}
		schoolName = aSchoolName;
	}
	/**
	* Access method for the schoolNum property.
	* @return the current value of the schoolNum property
	*/
	public String getSchoolNum()
	{
		fetch();
		return schoolNum;
	}
	/**
	* Sets the value of the schoolNum property.
	* @param aSchoolNum the new value of the schoolNum property
	*/
	public void setSchoolNum(String aSchoolNum)
	{
		if (this.schoolNum == null || !this.schoolNum.equals(aSchoolNum))
		{
			markModified();
		}
		schoolNum = aSchoolNum;
	}
	/**
	* @roseuid 418FCF8E02AA
	* Finds SchoolDistrictCode by OID
	* @param oid
	* @return SchoolDistrictCode
	*/
	public static SchoolDistrictCode find(String oid)
	{
		SchoolDistrictCode schoolDistrictCode = null;
		IHome home = new Home();
		schoolDistrictCode = (SchoolDistrictCode) home.find(oid, SchoolDistrictCode.class);
		return schoolDistrictCode;
	}

	/**
	 * Returns all SchoolDistrictCode objects
	 * @return java.util.Iterator
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(SchoolDistrictCode.class);
		return iter;

	}
}
