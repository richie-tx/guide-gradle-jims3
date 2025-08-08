package pd.supervision.manageassociate;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import messaging.manageassociate.domintf.ISuperviseeAssociate;
import java.util.Date;
import pd.contact.party.Party;
import java.util.Iterator;
import pd.codetable.Code;
import messaging.manageassociate.CreateUpdateSuperviseeAssociateEvent;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;

/**
 * Properties for supervisee
 * @useParent true
 * @detailerDoNotGenerate true
 */
public class SuperviseeAssociate extends PersistentObject
{

	/**
	 * 
	 * @roseuid 45E741C70213
	 * @return pd.supervision.manageassociate.SuperviseeAssociate
	 * @param oid
	 */
	static public SuperviseeAssociate find(String oid)
	{
		return (SuperviseeAssociate) new Home().find(oid, SuperviseeAssociate.class);
	}

	/**
	 * 
	 * @roseuid 45E741C703D8
	 * @return java.util.Iterator
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(event, SuperviseeAssociate.class);
	}

	/**
	 * 
	 * @roseuid 45E741C703D8
	 * @return java.util.Iterator
	 * @param aSpn
	 */
	static public Iterator findAll(String aSpn)
	{
		return new Home().findAll("spn", aSpn, SuperviseeAssociate.class);
	}
	
	/**
	 * Properties for associate
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private Party associate;
	private String associateId;
	private String cellPhone;
	private String comments;
	private Date createDate;
	private String email;
	private String firstName;
	private String homePhone;
	private String lastName;
	private String middleName;
	/**
	 * Properties for otherAddress
	 */
	private AssociateAddress otherAddress = null;
	private String otherAddressId;
	private String pager;
	/**
	 * Properties for primaryResidenceAddress
	 */
	private AssociateAddress primaryResidenceAddress = null;
	private String primaryResidenceAddressId;
	/**
	 * Properties for relationshipType
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate true
	 * @contextKey RELATIONSHIP_JUVENILE

	 */
	private Code relationshipType = null;
	private String relationshipTypeId;
	private String spn;
	private boolean status;
	/**
	 * Properties for supervisee
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private Party supervisee;
	private Date updateDate;
	private String workPhone;

	/**
	 * 
	 * @roseuid 45E73EED0203
	 * @param anAssociate
	 */
	public void fillSuperviseeAssociate(ISuperviseeAssociate anAssociate)
	{
		markModified();
		anAssociate.setAssociateId(getAssociateId());
		anAssociate.setSpn(getSpn());
		anAssociate.setRelationshipTypeId(getRelationshipTypeId());
		anAssociate.setSuperviseeAssociateId(getOID().toString());
		anAssociate.setAssociateName(getAssociateName());
	}

	/**
	 * returns the Associated [Party]
	 * @roseuid 45E741C70232
	 * @return pd.contact.party.Party
	 */
	public Party getAssociate()
	{
		fetch();
		initAssociate();
		return associate;
	}

	/**
	 * 
	 * @roseuid 45E73E98034B
	 * @return Returns the associateId.
	 */
	public String getAssociateId()
	{
		fetch();
		return associateId;
	}

	/**
	 * 
	 * @roseuid 45E73E98036B
	 * @return Returns the name.
	 */
	public String getAssociateName()
	{
		fetch();
		getAssociate();
		return associate.getLastName() + ", " + associate.getFirstName() + " " + associate.getMiddleName();
	}

	/**
	 * @return Returns the cellphone.
	 */
	public String getCellPhone()
	{
		fetch();
		return cellPhone;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments()
	{
		fetch();
		return comments;
	}

	/**
	 * 
	 * @return Returns the createDate.
	 */
	public Date getCreateDate()
	{
		fetch();
		return createDate;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail()
	{
		fetch();
		return email;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName()
	{
		fetch();
		return firstName;
	}

	/**
	 * @return Returns the homePhone.
	 */
	public String getHomePhone()
	{
		fetch();
		return homePhone;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName()
	{
		fetch();
		return lastName;
	}

	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName()
	{
		fetch();
		return middleName;
	}

	/**
	 * Gets referenced type pd.supervision.manageassociate.AssociateAddress
	 */
	public AssociateAddress getOtherAddress()
	{
		fetch();
		initOtherAddress();
		return otherAddress;
	}

	/**
	 * Get the reference value to class :: pd.supervision.manageassociate.AssociateAddress
	 */
	public String getOtherAddressId()
	{
		fetch();
		return otherAddressId;
	}

	/**
	 * @return Returns the pager.
	 */
	public String getPager()
	{
		fetch();
		return pager;
	}

	/**
	 * Gets referenced type pd.supervision.manageassociate.AssociateAddress
	 */
	public AssociateAddress getPrimaryResidenceAddress()
	{
		fetch();
		initPrimaryResidenceAddress();
		return primaryResidenceAddress;
	}

	/**
	 * Get the reference value to class :: pd.supervision.manageassociate.AssociateAddress
	 */
	public String getPrimaryResidenceAddressId()
	{
		fetch();
		return primaryResidenceAddressId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getRelationshipType()
	{
		initRelationshipType();
		return relationshipType;
	}

	/**
	 * 
	 * @roseuid 45E73E98037A
	 * @return Returns the relationship.
	 */
	public String getRelationshipTypeId()
	{
		fetch();
		return relationshipTypeId;
	}

	/**
	 * 
	 * @roseuid 45E73E9803A9
	 * @return Returns the spn.
	 */
	public String getSpn()
	{
		fetch();
		return spn;
	}

	/**
	 * @return Returns the status.
	 */
	public boolean getStatus()
	{
		fetch();
		return status;
	}

	/**
	 * returns the Supervisee [Party]
	 * @roseuid 45E741C70290
	 * @return pd.contact.party.Party
	 */
	public Party getSupervisee()
	{
		fetch();
		initSpn();
		return supervisee;
	}

	/**
	 * 
	 * @return Returns the updateDate.
	 */
	public Date getUpdateDate()
	{
		fetch();
		return updateDate;
	}

	/**
	 * @return Returns the workPhone.
	 */
	public String getWorkPhone()
	{
		fetch();
		return workPhone;
	}

	/**
	 * Initialize class relationship to class pd.contact.party.Party
	 * @roseuid 45E73E98035B
	 */
	private void initAssociate()
	{
		if (associate == null)
		{
			associate = (Party) new mojo.km.persistence.Reference(associateId, Party.class,
					(mojo.km.persistence.PersistentObject) this, "associate").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.manageassociate.AssociateAddress
	 */
	private void initOtherAddress()
	{
		if (otherAddress == null)
		{
			otherAddress = (AssociateAddress) new mojo.km.persistence.Reference(
					otherAddressId, AssociateAddress.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.manageassociate.AssociateAddress
	 */
	private void initPrimaryResidenceAddress()
	{
		if (primaryResidenceAddress == null)
		{
			primaryResidenceAddress = (AssociateAddress) new mojo.km.persistence.Reference(
					primaryResidenceAddressId, AssociateAddress.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initRelationshipType()
	{
		if (relationshipType == null)
		{
			relationshipType = (Code) new mojo.km.persistence.Reference(relationshipTypeId,
					Code.class, PDCodeTableConstants.CSCD_RELATIONSHIP).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.party.Party
	 * @roseuid 45E73E9803B9
	 */
	private void initSpn()
	{
		if (supervisee == null)
		{
			associate = (Party) new mojo.km.persistence.Reference(spn, Party.class,
					(mojo.km.persistence.PersistentObject) this, "supervisee").getObject();
		}
	}

	/**
	 * 
	 * @roseuid 45E73E99005D
	 * @param anAssociate The associate to set.
	 */
	public void setAssociate(Party anAssociate)
	{
		if (this.associate == null || !this.associate.equals(anAssociate))
		{
			markModified();
		}
	}

	/**
	 * 
	 * @roseuid 45E73E99003E
	 * @param aAssociateId The associateId to set.
	 * @param anAssociateId
	 */
	public void setAssociateId(String anAssociateId)
	{
		if (associateId == null || !associateId.equals(anAssociateId))
		{
			markModified();
		}
		associate = null;
		this.associateId = anAssociateId;
	}

	/**
	 * @param cellphone The cellphone to set.
	 */
	public void setCellPhone(String cellPhone)
	{
		if (this.cellPhone == null || !this.cellPhone.equals(cellPhone))
		{
			markModified();
		}
		this.cellPhone = cellPhone;
	}

	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments)
	{
		if (this.comments == null || !this.comments.equals(comments))
		{
			markModified();
		}
		this.comments = comments;
	}

	/**
	 * 
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(Date createDate)
	{
		if (this.createDate == null || !this.createDate.equals(createDate))
		{
			markModified();
		}
		this.createDate = createDate;
	}

	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email)
	{
		if (this.email == null || !this.email.equals(email))
		{
			markModified();
		}
		this.email = email;
	}

	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName)
	{
		if (this.firstName == null || !this.firstName.equals(firstName))
		{
			markModified();
		}
		this.firstName = firstName;
	}

	/**
	 * @param homePhone The homePhone to set.
	 */
	public void setHomePhone(String homePhone)
	{
		if (this.homePhone == null || !this.homePhone.equals(homePhone))
		{
			markModified();
		}
		this.homePhone = homePhone;
	}

	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName)
	{
		if (this.lastName == null || !this.lastName.equals(lastName))
		{
			markModified();
		}
		this.lastName = lastName;
	}

	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName)
	{
		if (this.middleName == null || !this.middleName.equals(middleName))
		{
			markModified();
		}
		this.middleName = middleName;
	}

	/**
	 * set the type reference for class member otherAddress
	 */
	public void setOtherAddress(AssociateAddress otherAddress)
	{
		if (this.otherAddress == null || !this.otherAddress.equals(otherAddress))
		{
			markModified();
		}
		if (otherAddress.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(otherAddress);
		}
		setOtherAddressId("" + otherAddress.getOID());
		this.otherAddress = (AssociateAddress) new mojo.km.persistence.Reference(
				otherAddress).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.manageassociate.AssociateAddress
	 */
	public void setOtherAddressId(String otherAddressId)
	{
		if (this.otherAddressId == null || !this.otherAddressId.equals(otherAddressId))
		{
			markModified();
		}
		otherAddress = null;
		this.otherAddressId = otherAddressId;
	}

	/**
	 * @param pager The pager to set.
	 */
	public void setPager(String pager)
	{
		if (this.pager == null || !this.pager.equals(pager))
		{
			markModified();
		}
		this.pager = pager;
	}

	/**
	 * set the type reference for class member primaryResidenceAddress
	 */
	public void setPrimaryResidenceAddress(AssociateAddress primaryResidenceAddress)
	{
		if (this.primaryResidenceAddress == null || !this.primaryResidenceAddress.equals(primaryResidenceAddress))
		{
			markModified();
		}
		if (primaryResidenceAddress.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(primaryResidenceAddress);
		}
		setPrimaryResidenceAddressId("" + primaryResidenceAddress.getOID());
		this.primaryResidenceAddress = (AssociateAddress) new mojo.km.persistence.Reference(
				primaryResidenceAddress).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.manageassociate.AssociateAddress
	 */
	public void setPrimaryResidenceAddressId(String primaryResidenceAddressId)
	{
		if (this.primaryResidenceAddressId == null || !this.primaryResidenceAddressId.equals(primaryResidenceAddressId))
		{
			markModified();
		}
		primaryResidenceAddress = null;
		this.primaryResidenceAddressId = primaryResidenceAddressId;
	}

	/**
	 * set the type reference for class member relationshipType
	 */
	public void setRelationshipType(Code relationshipType)
	{
		if (this.relationshipType == null || !this.relationshipType.equals(relationshipType))
		{
			markModified();
		}
		if (relationshipType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(relationshipType);
		}
		setRelationshipTypeId("" + relationshipType.getOID());
		this.relationshipType = (Code) new mojo.km.persistence.Reference(relationshipType).getObject();
	}

	/**
	 * 
	 * @roseuid 45E73E9803C8
	 * @param aRelationshipTypeId The relationship to set.
	 */
	public void setRelationshipTypeId(String aRelationshipTypeId)
	{
		if (this.relationshipTypeId == null || !this.relationshipTypeId.equals(aRelationshipTypeId))
		{
			markModified();
		}
		this.relationshipTypeId = aRelationshipTypeId;
	}

	/**
	 * 
	 * @roseuid 45E73E9803D9
	 * @param aSpn The spn to set.
	 */
	public void setSpn(String aSpn)
	{
		if (spn == null || !spn.equals(aSpn))
		{
			markModified();
		}
		supervisee = null;
		this.spn = aSpn;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status)
	{
		if (this.status != status)
		{
			markModified();
		}
		this.status = status;
	}

	/**
	 * 
	 * @roseuid 45E73E99000F
	 * @param aSupervisee The supervisee to set.
	 */
	public void setSupervisee(Party aSupervisee)
	{
		if (this.supervisee == null || !this.supervisee.equals(aSupervisee))
		{
			markModified();
		}
	}

	/**
	 * @param locationEvent
	 */
	public void setSuperviseeAssociate(CreateUpdateSuperviseeAssociateEvent evt)
	{
		if (this.associate == null || !this.associate.equals(evt))
		{
			markModified();
		}
		this.setLastName(evt.getAssociateName().getLastName());
		this.setMiddleName(evt.getAssociateName().getMiddleName());
		this.setFirstName(evt.getAssociateName().getFirstName());
		this.setRelationshipTypeId(evt.getRelationshipId());
		this.setComments(evt.getComments());
		this.setHomePhone(evt.getHomePhone().getAreaCode() + evt.getHomePhone().getPrefix()
				+ evt.getHomePhone().getLast4Digit());
		this.setWorkPhone(evt.getWorkPhone().getAreaCode() + evt.getWorkPhone().getPrefix()
				+ evt.getWorkPhone().getLast4Digit());
		this.setCellPhone(evt.getCellPhone().getAreaCode() + evt.getCellPhone().getPrefix()
				+ evt.getCellPhone().getLast4Digit());
		this.setPager(evt.getPager().getAreaCode() + evt.getPager().getPrefix() + evt.getPager().getLast4Digit());
		this.setEmail(evt.getEmail());
		this.setStatus(evt.getStatus());
		this.setSpn(evt.getSpn());
	}

	/**
	 * 
	 * @param updateDate The updateDate to set.
	 */
	public void setUpdateDate(Date updateDate)
	{
		if (this.updateDate == null || !this.updateDate.equals(updateDate))
		{
			markModified();
		}
		this.updateDate = updateDate;
	}

	/**
	 * @param workPhone The workPhone to set.
	 */
	public void setWorkPhone(String workPhone)
	{
		if (this.workPhone == null || !this.workPhone.equals(workPhone))
		{
			markModified();
		}
		this.workPhone = workPhone;
	}

	/**
	 * 
	 * @roseuid 45E73EED01B5
	 * @param anAssociate
	 */
	public void updateSuperviseeAssociate(ISuperviseeAssociate anAssociate)
	{
		markModified();
		setAssociateId(anAssociate.getAssociateId());
		setSpn(anAssociate.getSpn());
		setRelationshipTypeId(anAssociate.getRelationshipTypeId());
	}
}
