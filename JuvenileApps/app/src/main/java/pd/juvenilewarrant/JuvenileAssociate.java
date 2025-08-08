package pd.juvenilewarrant;

import mojo.km.context.multidatasource.Home;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;

import java.util.Date;
import java.util.Iterator;
import pd.codetable.Code;

/**
* @author ryoung
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class JuvenileAssociate extends PersistentObject
{
	private String firstName;
	private String sexId;
	/**
	* Properties for addresses
	* @associationType composition
	* @referencedType pd.juvenilewarrant.JuvenileAssociateAddress
	* @detailerDoNotGenerate true
	*/
	private java.util.Collection addresses = null;
	private String raceId;
	/**
	* Properties for race
	* @referencedType pd.codetable.Code
	* @contextKey RACE
	* @detailerDoNotGenerate true
	*/
	private Code race = null;
	private String faxNumber;
	private String homePhone;
	private String cellPhone;
	private String ssn;
	private String relationshipToJuvenileId;
	private String dlNumber;
	private Code dlState = null;
	private String dlStateId;
	/**
	* Properties for sex
	* @referencedType pd.codetable.Code
	* @contextKey SEX
	* @detailerDoNotGenerate true
	*/
	private Code sex = null;
	private String associateNum;
	private String workPhone;
	private String pager;
	private String releasedTo;
	private String warrantNum;
	private String lastName;
	private String faxLocation;
	private Date dateOfBirth;
	private String email3;
	private String email2;
	/**
	* Properties for relationshipToJuvenile
	* @referencedType pd.codetable.Code
	* @contextKey JUVENILE_RELATIONSHIP
	* @detailerDoNotGenerate true
	*/
	private Code relationshipToJuvenile = null;
	private String email1;
	private String middleName;
	/**
	* @roseuid 41E5A2400271
	*/
	public JuvenileAssociate()
	{
	}
	/**
	* Clears all pd.juvenilewarrant.JuvenileAssociateAddress from class relationship collection.
	*/
	public void clearAddresses()
	{
		initAddresses();
		addresses.clear();
	}
	/**
	* returns a collection of pd.juvenilewarrant.JuvenileAssociateAddress
	* @return Collection addresses
	*/
	public java.util.Collection getAddresses()
	{
		fetch();
		initAddresses();
		return addresses;
	}
	/**
	* Access method for the associateNum property.
	* @return the current value of the associateNum property
	*/
	public String getAssociateNum()
	{
		fetch();
		return "" + getOID();
	}
	/**
	* @return String cellPhone
	*/
	public String getCellPhone()
	{
		fetch();
		return cellPhone;
	}
	/**
	* Access method for the dateOfBirth property.
	* @return the current value of the dateOfBirth property
	*/
	public Date getDateOfBirth()
	{
		fetch();
		return dateOfBirth;
	}
	/**
	* @return String email1
	*/
	public String getEmail1()
	{
		fetch();
		return email1;
	}
	/**
	* @return String email2
	*/
	public String getEmail2()
	{
		fetch();
		return email2;
	}
	/**
	* @return String email3
	*/
	public String getEmail3()
	{
		fetch();
		return email3;
	}
	/**
	* @return String faxLocation
	*/
	public String getFaxLocation()
	{
		fetch();
		return faxLocation;
	}
	/**
	* @return String faxNumber
	*/
	public String getFaxNumber()
	{
		fetch();
		return faxNumber;
	}
	/**
	* Access method for the firstName property.
	* @return the current value of the firstName property
	*/
	public String getFirstName()
	{
		fetch();
		return firstName;
	}
	/**
	* @return String homePhone
	*/
	public String getHomePhone()
	{
		fetch();
		return homePhone;
	}
	/**
	* Access method for the lastName property.
	* @return the current value of the lastName property
	*/
	public String getLastName()
	{
		fetch();
		return lastName;
	}
	/**
	* Access method for the middleName property.
	* @return the current value of the middleName property
	*/
	public String getMiddleName()
	{
		fetch();
		return middleName;
	}
	/**
	* @return String pager
	*/
	public String getPager()
	{
		fetch();
		return pager;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code race
	*/
	public Code getRace()
	{
		fetch();
		initRace();
		return race;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return String raceId
	*/
	public String getRaceId()
	{
		fetch();
		return raceId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code relationshipToJuvenile
	*/
	public Code getRelationshipToJuvenile()
	{
		fetch();
		initRelationshipToJuvenile();
		return relationshipToJuvenile;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return String relationshipToJuvenileId
	*/
	public String getRelationshipToJuvenileId()
	{
		fetch();
		return relationshipToJuvenileId;
	}
	/**
	* Access method for the releasedTo property.
	* @return the current value of the releasedTo property
	*/
	public String getReleasedTo()
	{
		fetch();
		return releasedTo;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code sex
	*/
	public Code getSex()
	{
		fetch();
		initSex();
		return sex;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return Strng sexId
	*/
	public String getSexId()
	{
		fetch();
		return sexId;
	}
	/**
	* Access method for the ssn property.
	* @return the current value of the ssn property
	*/
	public String getSsn()
	{
		fetch();
		return ssn;
	}
	/**
	* Access method for the warrantNum property.
	* @return the current value of the warrantNum property
	*/
	public String getWarrantNum()
	{
		fetch();
		return warrantNum;
	}
	/**
	* @return String workPhone
	*/
	public String getWorkPhone()
	{
		fetch();
		return workPhone;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code
	*/
	public Code getDlState()
	{
		fetch();
		initDlState();
		return dlState;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return String
	*/
	public String getDlStateId()
	{
		fetch();
		return dlStateId;
	}
	/**
	* @return String workPhone
	*/
	public String getDlNumber()
	{
		fetch();
		return dlNumber;
	}	
	/**
	* Initialize class relationship implementation for pd.juvenilewarrant.JuvenileAssociateAddress
	*/
	private void initAddresses()
	{
		if (addresses == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}

			addresses =
				new mojo.km.persistence.ArrayList(
					JuvenileAssociateAddress.class,
					"associateNum",
					"" + getOID());
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initRace()
	{
		if (race == null)
		{
			race =
				(Code) new mojo
					.km
					.persistence
					.Reference(raceId, Code.class, PDCodeTableConstants.RACE)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initRelationshipToJuvenile()
	{
		if (relationshipToJuvenile == null)
		{
			relationshipToJuvenile =
				(Code) new mojo
					.km
					.persistence
					.Reference(relationshipToJuvenileId, Code.class, "JUVENILE_RELATIONSHIP")
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSex()
	{
		if (sex == null)
		{
			sex =
				(Code) new mojo
					.km
					.persistence
					.Reference(sexId, Code.class, "SEX")
					.getObject();
		}
	}

	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initDlState()
	{
		if (dlState == null)
		{
			try
			{
				dlState =
					(Code) new mojo
						.km
						.persistence
						.Reference(dlStateId, Code.class, PDCodeTableConstants.STATE_ABBR)
						.getObject();
			}
			catch (Throwable t)
			{
				dlState = null;
			}
		}
	}
	
	/**
	* insert a pd.juvenilewarrant.JuvenileAssociateAddress into class relationship collection.
	*/
	public void insertAddresses(JuvenileAssociateAddress anObject)
	{
		initAddresses();
		addresses.add(anObject);
	}
	/**
	* Removes a pd.juvenilewarrant.JuvenileAssociateAddress from class relationship collection.
	*/
	public void removeAddresses(JuvenileAssociateAddress anObject)
	{
		initAddresses();
		addresses.remove(anObject);
	}
	/**
	* @param collection
	*/
	public void setAddresses(java.util.Collection collection)
	{
		if (this.addresses == null || !this.addresses.equals(collection))
		{
			markModified();
		}
		addresses = collection;
	}
	/**
	* Sets the value of the associateNum property.
	* @param aAssociateNum the new value of the associateNum property
	*/
	public void setAssociateNum(String aAssociateNum)
	{
		if (this.associateNum == null || !this.associateNum.equals(aAssociateNum))
		{
			markModified();
		}
		associateNum = aAssociateNum;
	}
	/**
	* @param string
	*/
	public void setCellPhone(String acellPhone)
	{
		if (this.cellPhone == null || !this.cellPhone.equals(acellPhone))
		{
			markModified();
		}
		this.cellPhone = acellPhone;
	}
	/**
	* Sets the value of the dateOfBirth property.
	* @param aDateOfBirth the new value of the dateOfBirth property
	*/
	public void setDateOfBirth(Date aDateOfBirth)
	{
		if (this.dateOfBirth == null || !this.dateOfBirth.equals(aDateOfBirth))
		{
			markModified();
		}
		dateOfBirth = aDateOfBirth;
	}
	/**
	* @param string
	*/
	public void setEmail1(String aemail1)
	{
		if (this.email1 == null || !this.email1.equals(aemail1))
		{
			markModified();
		}
		this.email1 = aemail1;
	}
	/**
	* @param string
	*/
	public void setEmail2(String aemail2)
	{
		if (this.email2 == null || !this.email2.equals(aemail2))
		{
			markModified();
		}
		this.email2 = aemail2;
	}
	/**
	* @param string
	*/
	public void setEmail3(String aemail3)
	{
		if (this.email3 == null || !this.email3.equals(aemail3))
		{
			markModified();
		}
		this.email3 = aemail3;
	}
	/**
	* @param string
	*/
	public void setFaxLocation(String afaxLocation)
	{
		if (this.faxLocation == null || !this.faxLocation.equals(afaxLocation))
		{
			markModified();
		}
		this.faxLocation = afaxLocation;
	}
	/**
	* @param string
	*/
	public void setFaxNumber(String afaxNumber)
	{
		if (this.faxNumber == null || !this.faxNumber.equals(afaxNumber))
		{
			markModified();
		}
		this.faxNumber = afaxNumber;
	}
	/**
	* Sets the value of the firstName property.
	* @param aFirstName the new value of the firstName property
	*/
	public void setFirstName(String aFirstName)
	{
		if (this.firstName == null || !this.firstName.equals(aFirstName))
		{
			markModified();
		}
		firstName = aFirstName;
	}
	/**
	* @param string
	*/
	public void setHomePhone(String ahomePhone)
	{
		if (this.homePhone == null || !this.homePhone.equals(ahomePhone))
		{
			markModified();
		}
		this.homePhone = ahomePhone;
	}
	/**
	* Sets the value of the lastName property.
	* @param aLastName the new value of the lastName property
	*/
	public void setLastName(String aLastName)
	{
		if (this.lastName == null || !this.lastName.equals(aLastName))
		{
			markModified();
		}
		lastName = aLastName;
	}
	/**
	* Sets the value of the middleName property.
	* @param aMiddleName the new value of the middleName property
	*/
	public void setMiddleName(String aMiddleName)
	{
		if (this.middleName == null || !this.middleName.equals(aMiddleName))
		{
			markModified();
		}
		middleName = aMiddleName;
	}
	/**
	* @param string
	*/
	public void setPager(String apager)
	{
		if (this.pager == null || !this.pager.equals(apager))
		{
			markModified();
		}
		this.pager = apager;
	}
	/**
	* set the type reference for class member race
	* @param race
	*/
	public void setRace(Code arace)
	{
		if (this.race == null || !this.race.equals(arace))
		{
			markModified();
		}
		if (arace.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(arace);
		}
		setRaceId("" + arace.getOID());
		this.race = (Code) new mojo.km.persistence.Reference(arace).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param raceId
	*/
	public void setRaceId(String araceId)
	{
		if (this.raceId == null || !this.raceId.equals(araceId))
		{
			markModified();
		}
		race = null;
		this.raceId = araceId;
	}
	/**
	* set the type reference for class member relationshipToJuvenile
	* @param relationshipToJuvenile
	*/
	public void setRelationshipToJuvenile(Code arelationshipToJuvenile)
	{
		if (this.relationshipToJuvenile == null || !this.relationshipToJuvenile.equals(arelationshipToJuvenile))
		{
			markModified();
		}
		if (arelationshipToJuvenile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(arelationshipToJuvenile);
		}
		setRelationshipToJuvenileId("" + arelationshipToJuvenile.getOID());
		this.relationshipToJuvenile =
			(Code) new mojo.km.persistence.Reference(arelationshipToJuvenile).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param relationshipToJuvenileId
	*/
	public void setRelationshipToJuvenileId(String arelationshipToJuvenileId)
	{
		if (this.relationshipToJuvenileId == null || !this.relationshipToJuvenileId.equals(arelationshipToJuvenileId))
		{
			markModified();
		}
		relationshipToJuvenile = null;
		this.relationshipToJuvenileId = arelationshipToJuvenileId;
	}
	/**
	* Sets the value of the releasedTo property.
	* @param aReleasedTo the new value of the releasedTo property
	*/
	public void setReleasedTo(String aReleasedTo)
	{
		if (this.releasedTo == null || !this.releasedTo.equals(aReleasedTo))
		{
			markModified();
		}
		releasedTo = aReleasedTo;
	}
	/**
	* set the type reference for class member sex
	* @param sex
	*/
	public void setSex(Code asex)
	{
		if (this.sex == null || !this.sex.equals(asex))
		{
			markModified();
		}
		if (sex.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(asex);
		}
		setSexId("" + sex.getOID());
		this.sex = (Code) new mojo.km.persistence.Reference(sex).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param sexId
	*/
	public void setSexId(String asexId)
	{
		if (this.sexId == null || !this.sexId.equals(asexId))
		{
			markModified();
		}
		sex = null;
		this.sexId = asexId;
	}
	/**
	* Sets the value of the ssn property.
	* @param aSsn the new value of the ssn property
	*/
	public void setSsn(String aSsn)
	{
		if (this.ssn == null || !this.ssn.equals(aSsn))
		{
			markModified();
		}
		ssn = aSsn;
	}
	/**
	* Sets the value of the warrantNum property.
	* @param aWarrantNum the new value of the warrantNum property
	*/
	public void setWarrantNum(String aWarrantNum)
	{
		if (this.warrantNum == null || !this.warrantNum.equals(aWarrantNum))
		{
			markModified();
		}
		warrantNum = aWarrantNum;
	}
	/**
	* @param string
	*/
	public void setWorkPhone(String aworkPhone)
	{
		if (this.workPhone == null || !this.workPhone.equals(aworkPhone))
		{
			markModified();
		}
		this.workPhone = aworkPhone;
	}
	/**
	* set the type reference for class member state
	* @param dlStateCode
	*/
	public void setDlState(Code aState)
	{
		if (this.dlState == null || !this.dlState.equals(aState))
		{
			markModified();
		}
		if (dlState.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aState);
		}
		setDlStateId("" + aState.getOID());
		this.dlState = (Code) new mojo.km.persistence.Reference(aState).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param aStateId
	*/
	public void setDlStateId(String aStateId)
	{
		if (this.dlStateId == null || !this.dlStateId.equals(aStateId))
		{
			markModified();
		}
		dlState = null;
		this.dlStateId = aStateId;
	}
	/**
	* @param string
	*/
	public void setDlNumber(String aDlNumber)
	{
		if (this.dlNumber == null || !this.dlNumber.equals(aDlNumber))
		{
			markModified();
		}
		this.dlNumber = aDlNumber;
	}	
	/**
	* @return pd.juvenilewarrant.JuvenileAssociate
	* @param associateNumber
	* @param associateNum
	* @roseuid 41E59C150271
	*/
	static public JuvenileAssociate find(String associateNum)
	{
		JuvenileAssociate juvenileAssociate = null;
		IHome home = new Home();
		juvenileAssociate = (JuvenileAssociate) home.find(associateNum, JuvenileAssociate.class);
		return juvenileAssociate;
	}
	/**
	* @return java.util.Iterator
	* @param warrantNumber
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator associates = null;
		associates = home.findAll(attrName, attrValue, JuvenileAssociate.class);
		return associates;
	}
	
	/**
	* @return Iterator JuvenileWarrant
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileAssociate.class);
	}
	

}
