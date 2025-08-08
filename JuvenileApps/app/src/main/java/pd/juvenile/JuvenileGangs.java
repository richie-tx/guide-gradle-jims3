package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenile.JuvenileGangRequestEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
/**
 * 
 * @author sthyagarajan
 * JuvenileGangs Entity
 */
public class JuvenileGangs extends PersistentObject
{
	/**
	 * Properties for associationType
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GANG_ASSOCIATION_TYPE
	 * @detailerDoNotGenerate false
	 */
	private Code associationType=null;
	/**
	 * Properties for gangName
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GANG_NAME
	 * @detailerDoNotGenerate false
	 */
	private Code gangName=null;
	/**
	 * Properties for cliqueSet
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GANG_CLIQUE
	 * @detailerDoNotGenerate false
	 */
	private Code cliqueSet=null;
	/**
	 * Properties for currentStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey GANG_STATUS
	 * @detailerDoNotGenerate false
	 */
	private Code currentStatus=null;
	
	
	private String juvenileId;
	private Date entryDate;

	private String gangNameId;
	private String cliqueSetId;
	private String currentStatusId;
	private String associationTypeId;
	
	private String aliasMoniker;
	private String rank;
	
	//Hidden field for other,on selection of other,show other text box.
	private String otherGangName;
	private String otherCliqueSet;
	
	//Hidden field for describe hybrid on selection of hybrid as the gang name;
	private String descHybrid;

	/**
	 * Default Constructor.
	 */
	public JuvenileGangs()
	{
	}

	/**
	 * @return JuvenileGangs
	 * @param gangNameId
	 */
	static public JuvenileGangs find(String gangId)
	{
		IHome home = new Home();
		JuvenileGangs gang = (JuvenileGangs) home.find(gangId, JuvenileGangs.class);
		return gang;
	}

	/**
	 * Finds juvenile gangs list by an event
	 * 
	 * @return Iterator of drugs list
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator gangs = home.findAll(event, JuvenileGangs.class);
		return gangs;
	}

	/**
	 * @return Iterator gang
	 * @param attrName
	 *            name fo the attribute for where clause
	 * @param attrValue
	 *            value to be checked in the where clause
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator gangs = home.findAll(attrName, attrValue, JuvenileGangs.class);
		return gangs;
	}
	/**
	 * Save Gang.
	 * @param saveEvent
	 */
	static public void create(JuvenileGangRequestEvent saveEvent)
	{
		JuvenileGangs gang = new JuvenileGangs();
		gang.setJuvenileId(saveEvent.getJuvenileNum());
		gang.setEntryDate(saveEvent.getEntryDate());
		gang.setGangNameId(saveEvent.getGangName());
		gang.setCliqueSetId(saveEvent.getCliqueSet());
		gang.setCurrentStatusId(saveEvent.getCurrentStatus());
		gang.setAssociationTypeId(saveEvent.getAssociationType());
		gang.setAliasMoniker(saveEvent.getAliasMoniker());
		gang.setRank(saveEvent.getRank());
		gang.setOtherGangName(saveEvent.getOtherGangName());
		gang.setOtherCliqueSet(saveEvent.getOtherCliqueSet());
		gang.setDescHybrid(saveEvent.getDescHybrid());
	}
	
	/**
	 *binds the attribute with the property. 
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
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setGangNameId(String gangNameId)
	{
		if (this.gangNameId == null || !this.gangNameId.equals(gangNameId))
		{
			markModified();
		}
		this.gangNameId = gangNameId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getGangNameId()
	{
		fetch();
		return gangNameId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initGangName()
	{
		if (gangName == null)
		{
			try
			{
				gangName = (Code) new mojo.km.persistence.Reference(gangNameId, Code.class,
						"GANG_NAME").getObject();
			}
			catch (Throwable t)
			{
				
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getGangName()
	{
		fetch();
		initGangName();
		return gangName;
	}

	/**
	 * set the type reference for class member gangName
	 */
	public void setGangName(Code gangName)
	{
		if (this.gangName == null || !this.gangName.equals(gangName))
		{
			markModified();
		}
		if (gangName.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(gangName);
		}
		setGangNameId("" + gangName.getOID());
		this.gangName = (Code) new mojo.km.persistence.Reference(gangName).getObject();
	}
	
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setCliqueSetId(String cliqueSetId)
	{
		if (this.cliqueSetId == null || !this.cliqueSetId.equals(cliqueSetId))
		{
			markModified();
		}
		this.cliqueSetId = cliqueSetId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getCliqueSetId()
	{
		fetch();
		return cliqueSetId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCliqueSet()
	{
		if (cliqueSet == null)
		{
			try
			{
				cliqueSet = (Code) new mojo.km.persistence.Reference(cliqueSetId, Code.class,
						"GANG_CLIQUE").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCliqueSet()
	{
		fetch();
		initCliqueSet();
		return cliqueSet;
	}

	/**
	 * set the type reference for class member cliqueSet
	 */
	public void setCliqueSet(Code cliqueSet)
	{
		if (this.cliqueSet == null || !this.cliqueSet.equals(cliqueSet))
		{
			markModified();
		}
		if (cliqueSet.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(cliqueSet);
		}
		setCliqueSetId("" + cliqueSet.getOID());
		this.cliqueSet = (Code) new mojo.km.persistence.Reference(cliqueSet).getObject();
	}
	
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setCurrentStatusId(String currentStatusId)
	{
		if (this.currentStatusId == null || !this.currentStatusId.equals(currentStatusId))
		{
			markModified();
		}
		this.currentStatusId = currentStatusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getCurrentStatusId()
	{
		fetch();
		return currentStatusId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCurrentStatus()
	{
		if (currentStatus == null)
		{
			try
			{
				currentStatus= (Code) new mojo.km.persistence.Reference(currentStatusId, Code.class,
						"GANG_STATUS").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCurrentStatus()
	{
		fetch();
		initCurrentStatus();
		return currentStatus;
	}

	/**
	 * set the type reference for class member currentStatus
	 */
	public void setCurrentStatus(Code currentStatus)
	{
		if (this.currentStatus == null || !this.currentStatus.equals(currentStatus))
		{
			markModified();
		}
		if (currentStatus.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(currentStatus);
		}
		setCurrentStatusId("" + currentStatus.getOID());
		this.currentStatus = (Code) new mojo.km.persistence.Reference(currentStatus).getObject();
	}
	

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setAssociationTypeId(String associationTypeId)
	{
		if (this.associationTypeId == null || !this.associationTypeId.equals(associationTypeId))
		{
			markModified();
		}
		this.associationTypeId = associationTypeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getAssociationTypeId()
	{
		fetch();
		return associationTypeId;
	}
	
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initAssociationType()
	{
		if (associationType == null)
		{
			try
			{
				associationType = (Code) new mojo.km.persistence.Reference(associationTypeId, Code.class,
						"GANG_ASSOCIATION_TYPE").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getAssociationType()
	{
		fetch();
		initAssociationType();
		return associationType;
	}

	/**
	 * set the type reference for class member associationType
	 */
	public void setAssociationType(Code associationType)
	{
		if (this.associationType == null || !this.associationType.equals(associationType))
		{
			markModified();
		}
		if (associationType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(associationType);
		}
		setAssociationTypeId("" + associationType.getOID());
		this.associationType = (Code) new mojo.km.persistence.Reference(associationType).getObject();
	}

	
	/**
	 * @return Returns the moniker.
	 */
	public String getAliasMoniker()
	{
		fetch();
		return aliasMoniker;
	}

	/**
	 * The moniker is set.
	 * @param moniker 
	 */
	public void setAliasMoniker(String aliasMoniker)
	{
		if (this.aliasMoniker == null || !this.aliasMoniker.equals(aliasMoniker))
		{
			markModified();
		}
		this.aliasMoniker = aliasMoniker;
	}


	/**
	 * @return Returns the rank.
	 */
	public String getRank()
	{
		fetch();
		return rank;
	}

	/**
	 * Rank is set.
	 * @param rank 
	 */
	public void setRank(String rank)
	{
		if (this.rank == null || !this.rank.equals(rank))
		{
			markModified();
		}
		this.rank = rank;
	}
	
	
	/**
	 * @return the OtherGangName.
	 */
	public String getOtherGangName()
	{
		fetch();
		return otherGangName;
	}

	/**
	 * Other gang name is set.
	 * @param otherGangName 
	 */
	public void setOtherGangName(String otherGangName)
	{
		if (this.otherGangName == null || !this.otherGangName.equals(otherGangName))
		{
			markModified();
		}
		this.otherGangName = otherGangName;
	}
	
	
	/**
	 * @return otherCliqueSet.
	 */
	public String getOtherCliqueSet()
	{
		fetch();
		return otherCliqueSet;
	}

	/**
	 * sets the otherCliqueSet
	 * @param otherCliqueSet 
	 */
	public void setOtherCliqueSet(String otherCliqueSet)
	{
		if (this.otherCliqueSet == null || !this.otherCliqueSet.equals(otherCliqueSet))
		{
			markModified();
		}
		this.otherCliqueSet = otherCliqueSet;
	}
	
	
	/**
	 * get the desc hybrid.
	 * @return Returns the descHybrid.
	 */
	public String getDescHybrid()
	{
		fetch();
		return descHybrid;
	}

	/**
	 * Sets the descHybrid.
	 * @param descHybrid 
	 */
	public void setDescHybrid(String descHybrid)
	{
		if (this.descHybrid == null || !this.descHybrid.equals(descHybrid))
		{
			markModified();
		}
		this.descHybrid = descHybrid;
	}
}
