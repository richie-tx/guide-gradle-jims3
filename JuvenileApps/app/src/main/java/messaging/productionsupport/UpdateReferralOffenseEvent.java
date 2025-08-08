/*
 * Created on Apr 25, 2005
 */

package messaging.productionsupport;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import naming.UIConstants  ;
import mojo.km.messaging.RequestEvent;
import pd.codetable.Code;

public class UpdateReferralOffenseEvent extends RequestEvent
{
    private static final long serialVersionUID = 1L;
	private String juvenileNum;
	private String referralNum;
	private String offenseCode; 
	private String offenseSeverity;
	private String offDate;
	private String keyMapLocation;
	private String investigationNum;	
	private String offenseStreetNum;
	private String offenseStreetName;
	private String offenseAptNum;
	private String offenseCity;
	private String offenseState;
	private String offenseZip;
	private String weaponType;
	private String cjisNum;	
	private String arrestDate;
	private String arrestTime;
	private String sequenceNum;
	private String chargeSequenceNum;
	private String lcUser;	
	private Date lcDate;
	private String lcTime;	
	private String OID;
	private String onCampOffense;
	private String onCampDistrict;
	private String onCampSchool;
	
	/**
	 * @return string
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}
	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @return sequenceNum
	 */
	public String getSequenceNum()
	{
		return sequenceNum;
	}
	/**
	 * @param sequenceNum
	 */
	public void setSequenceNum(String string)
	{
		sequenceNum = string;
	}
	/**
	 * @param referralNum
	 */
	public String getReferralNum()
	{
	    return referralNum;
	}
	public void setReferralNum(String referralNum)
	{
	    this.referralNum = referralNum;
	}


	/**
	 * @return Returns the investigationNum.
	 */
	public String getInvestigationNum() {
		return investigationNum;
	}
	/**
	 * @param investigationNum The investigationNum to set.
	 */
	public void setInvestigationNum(String investigationNum) {
		this.investigationNum = investigationNum;
	}
	
	/**
	 * @return the offenseCode
	 */
	public String getOffenseCode() {
		return offenseCode;
	}

	/**
	 * @param offenseCode the offenseCode to set
	 */
	public void setOffenseCode(String offenseCode) {
		this.offenseCode = offenseCode;
	}	
	
	/**
	 * @return the keyMapLocation
	 */
	public String getKeyMapLocation()
	{
	    return keyMapLocation;
	}

	/**
	 * @param keyMapLocation the keyMapLocation to set
	 */
	public void setKeyMapLocation(String keyMapLocation)
	{
	    this.keyMapLocation = keyMapLocation;
	}
	
	public String getOffenseStreetNum()
	{
	    return offenseStreetNum;
	}

	public void setOffenseStreetNum(String offenseStreetNum)
	{
	    this.offenseStreetNum = offenseStreetNum;
	}
	public String getOffenseStreetName()
	{
	    return offenseStreetName;
	}

	public void setOffenseStreetName(String offenseStreetName)
	{
	    this.offenseStreetName = offenseStreetName;
	}
	public String getOffenseAptNum()
	{
	    return offenseAptNum;
	}

	public void setOffenseAptNum(String offenseAptNum)
	{
	    this.offenseAptNum = offenseAptNum;
	}
	public String getOffenseCity()
	{
	    return offenseCity;
	}

	public void setOffenseCity(String offenseCity)
	{
	    this.offenseCity = offenseCity;
	}
	public String getOffenseState()
	{
	    return offenseState;
	}

	public void setOffenseState(String offenseState)
	{
	    this.offenseState = offenseState;
	}
	public String getOffenseZip()
	{
	    return offenseZip;
	}

	public void setOffenseZip(String offenseZip)
	{
	    this.offenseZip = offenseZip;
	}
	public String getWeaponType()
	{
	    return weaponType;
	}

	public void setWeaponType(String weaponType)
	{
	    this.weaponType = weaponType;
	}
	public String getCjisNum()
	{
	    return cjisNum;
	}

	public void setCjisNum(String cjisNum)
	{
	    this.cjisNum = cjisNum;
	}
	
	public String getArrestTime()
	{
	    return arrestTime;
	}

	public void setArrestTime(String arrestTime)
	{
	    this.arrestTime = arrestTime;
	}
	public String getLcUser()
	{
	    return lcUser;
	}

	public void setLcUser(String lcUser)
	{
	    this.lcUser = lcUser;
	}
	public Date getLcDate()
	{
	    return lcDate;
	}

	public void setLcDate(Date lcDate)
	{
	    this.lcDate = lcDate;
	}
	public String getLcTime()
	{
	    return lcTime;
	}

	public void setLcTime(String lcTime)
	{
	    this.lcTime = lcTime;
	}
	public String getChargeSequenceNum()
	{
	    return chargeSequenceNum;
	}

	public void setChargeSequenceNum(String chargeSequenceNum)
	{
	    this.chargeSequenceNum = chargeSequenceNum;
	}
	public String getOffDate()
	{
	    return offDate;
	}

	public void setOffDate(String offDate)
	{
	    this.offDate = offDate;
	}
	public String getArrestDate()
	{
	    return arrestDate;
	}

	public void setArrestDate(String arrestDate)
	{
	    this.arrestDate = arrestDate;
	}
	public String getOffenseSeverity()
	{
	    return offenseSeverity;
	}

	public void setOffenseSeverity(String offenseSeverity)
	{
	    this.offenseSeverity = offenseSeverity;
	}
	public String getOID()
	{
	    return OID;
	}
	public void setOID(String oID)
	{
	    OID = oID;
	}
	
	public String getOnCampOffense()
	{
	    return onCampOffense;
	}

	public void setOnCampOffense(String onCampOffense)
	{
	    this.onCampOffense = onCampOffense;
	}

	public String getOnCampDistrict()
	{
	    return onCampDistrict;
	}

	public void setOnCampDistrict(String onCampDistrict)
	{
	    this.onCampDistrict = onCampDistrict;
	}

	public String getOnCampSchool()
	{
	    return onCampSchool;
	}

	public void setOnCampSchool(String onCampSchool)
	{
	    this.onCampSchool = onCampSchool;
	}

}