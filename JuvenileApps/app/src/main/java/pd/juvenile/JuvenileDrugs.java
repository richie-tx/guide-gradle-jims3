package pd.juvenile;

import java.util.Iterator;
import java.util.Date;

import messaging.juvenile.JuvenileDrugRequestEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * @roseuid 42B18E4A0271
 */
public class JuvenileDrugs extends PersistentObject
{
	private String frequencyId;

	/**
	 * Properties for degree
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey DRUG_DEGREE
	 * @detailerDoNotGenerate false
	 */
	private Code degree = null;

	private String amountSpent;

	private String juvenileId;

	private String degreeId;

	private Date entryDate;

	private String locationOfUseId;

	private Code locationOfUse;

	private String drugTypeId;

	/**
	 * Properties for frequency
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey DRUG_FREQUENCY
	 * @detailerDoNotGenerate false
	 */
	private Code frequency = null;

	/**
	 * Properties for drugType
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey DRUG_TYPE
	 * @detailerDoNotGenerate false
	 */
	private Code drugType = null;

	private int onsetAge;

	private String supervisionNum;

	/**
	 * @roseuid 42B18E4A0271
	 */
	public JuvenileDrugs()
	{
	}

	/**
	 * @return JuvenileDrugs
	 * @param drug
	 */
	static public JuvenileDrugs find(String drugId)
	{
		IHome home = new Home();
		JuvenileDrugs drug = (JuvenileDrugs) home.find(drugId, JuvenileDrugs.class);
		return drug;
	}

	/**
	 * Finds juvenile drug list by an event
	 * 
	 * @return Iterator of drugs list
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator drugs = home.findAll(event, JuvenileDrugs.class);
		return drugs;
	}

	/**
	 * @return Iterator drygs
	 * @param attrName
	 *            name fo the attribute for where clause
	 * @param attrValue
	 *            value to be checked in the where clause
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator drugs = home.findAll(attrName, attrValue, JuvenileDrugs.class);
		return drugs;
	}

	static public void create(JuvenileDrugRequestEvent saveEvent)
	{
		JuvenileDrugs drug = new JuvenileDrugs();
		drug.setDegreeId(saveEvent.getDegree());
		drug.setDrugTypeId(saveEvent.getDrugType());
		drug.setFrequencyId(saveEvent.getFrequency());
		drug.setJuvenileId(saveEvent.getJuvenileNum());
		drug.setOnsetAge(saveEvent.getOnsetAge());
		drug.setAmountSpent(saveEvent.getAmountSpent());
		drug.setLocationOfUseId(saveEvent.getLocationOfUse());

	}

	/**
	 * Access method for the onsetAge property.
	 * 
	 * @return the current value of the onsetAge property
	 */
	public int getOnsetAge()
	{
		fetch();
		return onsetAge;
	}

	/**
	 * Sets the value of the onsetAge property.
	 * 
	 * @param aOnsetAge
	 *            the new value of the onsetAge property
	 */
	public void setOnsetAge(int aOnsetAge)
	{
		if (this.onsetAge != aOnsetAge)
		{
			markModified();
		}
		onsetAge = aOnsetAge;
	}

	/**
	 * @roseuid 42B1830702DE
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 */
	public void setJuvenileId(String juvenileId)
	{
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId))
		{
			markModified();
		}
		this.juvenileId = juvenileId;
	}

	/**
	 * Get the reference value to class :: pd.juvenile.Juvenile
	 */
	public String getJuvenileId()
	{
		fetch();
		return juvenileId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setDegreeId(String degreeId)
	{
		if (this.degreeId == null || !this.degreeId.equals(degreeId))
		{
			markModified();
		}
		degree = null;
		this.degreeId = degreeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDegreeId()
	{
		fetch();
		return degreeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDegree()
	{
		if (degree == null)
		{
			try
			{
				degree = (Code) new mojo.km.persistence.Reference(degreeId, Code.class,
						"DRUG_DEGREE").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getDegree()
	{
		fetch();
		initDegree();
		return degree;
	}

	/**
	 * set the type reference for class member degree
	 */
	public void setDegree(Code degree)
	{
		if (this.degree == null || !this.degree.equals(degree))
		{
			markModified();
		}
		if (degree.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(degree);
		}
		setDegreeId("" + degree.getOID());
		this.degree = (Code) new mojo.km.persistence.Reference(degree).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setLocationOfUseId(String aLocationOfUseId)
	{
		if (this.locationOfUseId == null || !this.locationOfUseId.equals(aLocationOfUseId))
		{
			markModified();
		}
		locationOfUse = null;
		this.locationOfUseId = aLocationOfUseId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getLocationOfUseId()
	{
		fetch();
		return locationOfUseId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initLocationOfUse()
	{
		if (locationOfUse == null)
		{
			try
			{
				locationOfUse = (Code) new mojo.km.persistence.Reference(locationOfUseId, Code.class, "LOCATION_OF_USE")
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getLocationOfUse()
	{
		fetch();
		initLocationOfUse();
		return locationOfUse;
	}

	/**
	 * set the type reference for class member usedAmount
	 */
	public void setLocationOfUse(Code aLocationOfUse)
	{
		if (this.locationOfUse == null || !this.locationOfUse.equals(aLocationOfUse))
		{
			markModified();
		}
		if (aLocationOfUse.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aLocationOfUse);
		}
		setLocationOfUseId("" + aLocationOfUse.getOID());
		this.locationOfUse = (Code) new mojo.km.persistence.Reference(aLocationOfUse).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setFrequencyId(String frequencyId)
	{
		if (this.frequencyId == null || !this.frequencyId.equals(frequencyId))
		{
			markModified();
		}
		frequency = null;
		this.frequencyId = frequencyId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getFrequencyId()
	{
		fetch();
		return frequencyId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initFrequency()
	{
		if (frequency == null)
		{
			try
			{
				frequency = (Code) new mojo.km.persistence.Reference(frequencyId, Code.class,
						"DRUG_FREQUENCY").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getFrequency()
	{
		fetch();
		initFrequency();
		return frequency;
	}

	/**
	 * set the type reference for class member frequency
	 */
	public void setFrequency(Code frequency)
	{
		if (this.frequency == null || !this.frequency.equals(frequency))
		{
			markModified();
		}
		if (frequency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(frequency);
		}
		setFrequencyId("" + frequency.getOID());
		this.frequency = (Code) new mojo.km.persistence.Reference(frequency).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setDrugTypeId(String drugTypeId)
	{
		if (this.drugTypeId == null || !this.drugTypeId.equals(drugTypeId))
		{
			markModified();
		}
		drugType = null;
		this.drugTypeId = drugTypeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDrugTypeId()
	{
		fetch();
		return drugTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDrugType()
	{
		if (drugType == null)
		{
			try
			{
				drugType = (Code) new mojo.km.persistence.Reference(drugTypeId, Code.class,
						"DRUG_TYPE").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getDrugType()
	{
		fetch();
		initDrugType();
		return drugType;
	}

	/**
	 * set the type reference for class member drugType
	 */
	public void setDrugType(Code drugType)
	{
		if (this.drugType == null || !this.drugType.equals(drugType))
		{
			markModified();
		}
		if (drugType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(drugType);
		}
		setDrugTypeId("" + drugType.getOID());
		this.drugType = (Code) new mojo.km.persistence.Reference(drugType).getObject();
	}

	/**
	 * Access method for the entryDate property.
	 * 
	 * @return the current value of the entryDate property
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}

	/**
	 * Sets the value of the entryDate property.
	 * 
	 * @param aEntryDate
	 *            the new value of the entryDate property
	 */
	public void setEntryDate(Date aEntryDate)
	{
		if (this.entryDate == null || !this.entryDate.equals(aEntryDate))
		{
			markModified();
		}
		entryDate = aEntryDate;
	}

	/**
	 * @return
	 */
	public String getAmountSpent()
	{
		fetch();
		return amountSpent;
	}

	/**
	 * @param string
	 */
	public void setAmountSpent(String string)
	{
		markModified();
		amountSpent = string;
	}

	/**
	 * @return Returns the supervisionNum.
	 */
	public String getSupervisionNum()
	{
		fetch();
		return supervisionNum;
	}

	/**
	 * @param supervisionNum
	 *            The supervisionNum to set.
	 */
	public void setSupervisionNum(String supervisionNum)
	{
		if (this.supervisionNum == null || !this.supervisionNum.equals(supervisionNum))
		{
			markModified();
		}
		this.supervisionNum = supervisionNum;
	}
}
