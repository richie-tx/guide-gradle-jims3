package pd.juvenilecase.referral;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 43319902003F
*/
public class JJSTransferredOffenseReferral extends PersistentObject
{
	private String juvenileNumber;
	private String referralNumber;
	private String fromCountyCode;
	private String offenseCode;
	private String categoryCode;
	private String dpsCode;
	private Date offenseDate;
	private Date adjudicationDate;
	private String personId;

	/**
	* @roseuid 43319902003F
	*/
	public JJSTransferredOffenseReferral()
	{
	}
	/**
	* @roseuid 433194DF00FF
	*/
	public void find()
	{
		fetch();
	}

	static public JJSTransferredOffenseReferral find(String transId)
	{
		IHome home = new Home();
		JJSTransferredOffenseReferral transOffense = (JJSTransferredOffenseReferral) home.find(transId, JJSTransferredOffenseReferral.class);
		return transOffense;
	}
	/**
	* @roseuid 42A99B980107
	* @return iterator
	*/
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JJSTransferredOffenseReferral.class);
	}
	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,JJSTransferredOffenseReferral.class);
	}	
	
	/**
	* Access method for the juvenileNum property.
	* @return the current value of the juvenileNum property
	*/
	public String getJuvenileNumber()
	{
		fetch();
		return juvenileNumber;
	}
	/**
	* Sets the value of the juvenileNum property.
	* @param aJuvenileNum the new value of the juvenileNum property
	*/
	public void setJuvenileNumber(String aJuvenileNumber)
	{
		if (this.juvenileNumber == null || !this.juvenileNumber.equals(aJuvenileNumber))
		{
			markModified();
		}
		juvenileNumber = aJuvenileNumber;
	}
	/**
	* Access method for the referralNum property.
	* @return the current value of the referralNum property
	*/
	public String getReferralNumber()
	{
		fetch();
		return referralNumber;
	}
	/**
	* Sets the value of the referralNum property.
	* @param aReferralNum the new value of the referralNum property
	*/
	public void setReferralNumber(String aReferralNumber)
	{
		if (this.referralNumber == null || !this.referralNumber.equals(aReferralNumber))
		{
			markModified();
		}
		referralNumber = aReferralNumber;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public String getFromCountyCode()
	{
		fetch();
		return fromCountyCode;
	}	
	/**
	* Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public void setFromCountyCode(String aFromCountyCode)
	{
		if (this.fromCountyCode == null || !this.fromCountyCode.equals(aFromCountyCode))
		{
			markModified();
		}
		this.fromCountyCode = aFromCountyCode;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public String getOffenseCode()
	{
		fetch();
		return offenseCode;
	}	
	/**
	* Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public void setOffenseCode(String aOffenseCode)
	{
		if (this.offenseCode == null || !this.offenseCode.equals(aOffenseCode))
		{
			markModified();
		}
		this.offenseCode = aOffenseCode;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public String getCategoryCode()
	{
		fetch();
		return categoryCode;
	}	
	/**
	* Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public void setCategoryCode(String aCategoryCode)
	{
		if (this.categoryCode == null || !this.categoryCode.equals(aCategoryCode))
		{
			markModified();
		}
		this.categoryCode = aCategoryCode;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public String getDpsCode()
	{
		fetch();
		return dpsCode;
	}	
	/**
	* Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public void setDpsCode(String aDpsCode)
	{
		if (this.dpsCode == null || !this.dpsCode.equals(aDpsCode))
		{
			markModified();
		}
		this.dpsCode = aDpsCode;
	}	
	
	/**
	 * PersonId - US.11081
	 * @return
	 */
	public String getPersonId()
	{
		fetch();
		return personId;
	}	
	
	/**
	 * Person ID - US.11081
	 * @param personId
	 */
	public void setPersonId(String personId)
	{
		if (this.personId == null || !this.personId.equals(personId))
		{
			markModified();
		}
		this.personId = personId;
	}
	
	/**
	* Access method for the offenseDate property.
	* @return the current value of the offenseDate property
	*/
	public Date getOffenseDate()
	{
		fetch();
		return offenseDate;
	}
	/**
	* Sets the value of the offenseDate property.
	* @param aOffenseDate the new value of the offenseDate property
	*/
	public void setOffenseDate(Date aOffenseDate)
	{
		if (this.offenseDate == null || !this.offenseDate.equals(aOffenseDate))
		{
			markModified();
		}
		offenseDate = aOffenseDate;
	}
	/**
	* Access method for the adjudicationtDate property.
	* @return the current value of theadjudicationDate property
	*/
	public Date getAdjudicationDate()
	{
		fetch();
		return adjudicationDate;
	}
	/**
	* Sets the value of the adjudicationDate property.
	* @param aAdjudicationDate the new value of the adjudicationDate property
	*/
	public void setAdjudicationDate(Date aAdjudicationDate)
	{
		if (this.adjudicationDate == null || !this.adjudicationDate.equals(aAdjudicationDate))
		{
			markModified();
		}
		adjudicationDate = aAdjudicationDate;
	}
	
}
