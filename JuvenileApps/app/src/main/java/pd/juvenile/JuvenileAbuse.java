package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.juvenile.GetJuvenileAbuseListEvent;
import messaging.juvenile.SaveJuvenileAbuseEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.juvenilecase.TraitType;
import ui.common.CodeHelper;

public class JuvenileAbuse extends PersistentObject
{
	private String traitTypeId;

	private String relationshipToJuvenileId;
	//private String  allegedAbuserRelationship;	

	private String details;

	/**
	 * Juvenile abuse level
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey JUV_ABUSE_LEVEL
	 * @detailerDoNotGenerate false
	 */
	private Code level = null;

	private Code informationBasis = null;

	private String informationBasisId;

	private String event;

	private String supervisionNum;

	private String treatment;

	private boolean cpsInvolvement;
	private boolean cpsCustody;
	private String cpsCaseNumber;

	

	private String juvenileNum;

	/**
	 * Juvenile abuse level
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey JUVENILE_RELATIONSHIP
	 * @detailerDoNotGenerate false
	 */
	private Code relationshipToJuvenile = null;

	private String perpetratorFirstName;

	private String perpetratorMiddleName;

	private String levelId;

	private String memberId;

	private String contactId;

	private Date createDate;

	private String perpetratorLastName;

	public TraitType traitType = null;

	//added for US 40635
	private String informationSrcCd; 

	/**
	 * @roseuid 42B062E1016F
	 */
	public JuvenileAbuse()
	{
	}

	public JuvenileAbuseResponseEvent getValueObject()
	{
		JuvenileAbuseResponseEvent event = new JuvenileAbuseResponseEvent();
		event.setAbuseId(this.getAbuseId());
		Code abuseLevel = this.getLevel();
		if (abuseLevel != null)
		{
			event.setAbuseLevelCode(abuseLevel.getCode());
			event.setAbuseLevelDescription(abuseLevel.getDescription());
		}

		Code abuseInformationBasis = this.getInformationBasis();
		if (abuseInformationBasis != null)
		{
			event.setAbuseInformationBasisCode(abuseInformationBasis.getCode());
			event.setAbuseInformationBasisDescription(abuseInformationBasis.getDescription());
		}

		TraitType traitType = this.getTraitType();
		if (traitType != null)
		{
			event.setTraitTypeId(traitType.getTraitTypeId());
			event.setTraitTypeName(traitType.getName());
		}

		event.setTreatment(this.getTreatment());
		event.setEntryDate(this.getCreateDate());
		Code relationship = this.getRelationshipToJuvenile();
		event.setRelationshipToJuvenileCode(this.getRelationshipToJuvenileId());
		//event.setAllegedAbuserRelationship(this.getAllegedAbuserRelationship());
		if (relationship != null)
		{
			event.setRelationshipToJuvenileDescription(relationship.getDescription());
		}
		else
		{
			event.setRelationshipToJuvenileDescription(null);
		}
		event.setAbuseDetails(this.getDetails());
		event.setAbuseEvent(this.getEvent());
		event.setInformationBasisId(this.getInformationBasisId());
		if (abuseLevel != null)
		{
			event.setAbuseLevelCode(abuseLevel.getCode());
			event.setAbuseLevelDescription(abuseLevel.getDescription());
		}

		event.setCpsInvolvement(this.getCpsInvolvement());
		event.setCpsCustody(this.getCpsCustody());
		event.setCpsCaseNumber(this.getCpsCaseNumber());

		event.setFirstName(this.getPerpetratorFirstName());
		event.setMiddleName(this.getPerpetratorMiddleName());
		event.setLastName(this.getPerpetratorLastName());
		event.setMemberId(this.getMemberId());
		event.setContactId(this.getContactId());
		event.setInformationSrcCd(this.getInformationSrcCd());
		event.setJuvenileNumber(this.getJuvenileNum());
		if(this.getInformationSrcCd()!=null) {
			event.setInformationSrcDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.INFORMATION_SOURCE, this.getInformationSrcCd()));
		}
		
			return event;
	}

	/**
	 * 
	 */
	private String getAbuseId()
	{
		Object oidObj = this.getOID();
		if (oidObj != null)
		{
			return oidObj.toString();
		}
		else
		{
			return null;
		}
	}

	/**
	 * Access method for the treatment property.
	 * 
	 * @return the current value of the treatment property
	 */
	public String getTreatment()
	{
		fetch();
		return treatment;
	}

	/**
	 * Sets the value of the treatment property.
	 * 
	 * @param aTreatment
	 *            the new value of the treatment property
	 */
	public void setTreatment(String aTreatment)
	{
		if (this.treatment == null || !this.treatment.equals(aTreatment))
		{
			markModified();
		}
		treatment = aTreatment;
	}

	/**
	 * Access method for the createDate property.
	 * 
	 * @return the current value of the createDate property
	 */
	public Date getCreateDate()
	{
		fetch();
		return createDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param aCreateDate
	 *            the new value of the createDate property
	 */
	public void setCreateDate(Date aCreateDate)
	{
		if (this.createDate == null || !this.createDate.equals(aCreateDate))
		{
			markModified();
		}
		createDate = aCreateDate;
	}

	/**
	 * Access method for the perpetratorLastName property.
	 * 
	 * @return the current value of the perpetratorLastName property
	 */
	public String getPerpetratorLastName()
	{
		fetch();
		return perpetratorLastName;
	}

	/**
	 * Sets the value of the perpetratorLastName property.
	 * 
	 * @param aPerpetratorLastName
	 *            the new value of the perpetratorLastName property
	 */
	public void setPerpetratorLastName(String aPerpetratorLastName)
	{
		if (this.perpetratorLastName == null || !this.perpetratorLastName.equals(aPerpetratorLastName))
		{
			markModified();
		}
		perpetratorLastName = aPerpetratorLastName;
	}

	/**
	 * Access method for the perpetratorFirstName property.
	 * 
	 * @return the current value of the perpetratorFirstName property
	 */
	public String getPerpetratorFirstName()
	{
		fetch();
		return perpetratorFirstName;
	}

	/**
	 * Sets the value of the perpetratorFirstName property.
	 * 
	 * @param aPerpetratorFirstName
	 *            the new value of the perpetratorFirstName property
	 */
	public void setPerpetratorFirstName(String aPerpetratorFirstName)
	{
		if (this.perpetratorFirstName == null || !this.perpetratorFirstName.equals(aPerpetratorFirstName))
		{
			markModified();
		}
		perpetratorFirstName = aPerpetratorFirstName;
	}

	/**
	 * Access method for the perpetratorMiddleName property.
	 * 
	 * @return the current value of the perpetratorMiddleName property
	 */
	public String getPerpetratorMiddleName()
	{
		fetch();
		return perpetratorMiddleName;
	}

	/**
	 * Sets the value of the perpetratorMiddleName property.
	 * 
	 * @param aPerpetratorMiddleName
	 *            the new value of the perpetratorMiddleName property
	 */
	public void setPerpetratorMiddleName(String aPerpetratorMiddleName)
	{
		if (this.perpetratorMiddleName == null || !this.perpetratorMiddleName.equals(aPerpetratorMiddleName))
		{
			markModified();
		}
		perpetratorMiddleName = aPerpetratorMiddleName;
	}

	/**
	 * Access method for the supervisionNum property.
	 * 
	 * @return the current value of the supervisionNum property
	 */
	public String getSupervisionNum()
	{
		fetch();
		return supervisionNum;
	}

	/**
	 * Sets the value of the supervisionNum property.
	 * 
	 * @param aSupervisionNum
	 *            the new value of the supervisionNum property
	 */
	public void setSupervisionNum(String aSupervisionNum)
	{
		if (this.supervisionNum == null || !this.supervisionNum.equals(aSupervisionNum))
		{
			markModified();
		}
		supervisionNum = aSupervisionNum;
	}

	/**
	 * Determines if the cpsInvolvement property is true.
	 * 
	 * @return <code>true<code> if the cpsInvolvement property is true
	 */
	public boolean getCpsInvolvement()
	{
		fetch();
		return cpsInvolvement;
	}

	/**
	 * Sets the value of the cpsInvolvement property.
	 * 
	 * @param aCpsInvolvement
	 *            the new value of the cpsInvolvement property
	 */
	public void setCpsInvolvement(boolean aCpsInvolvement)
	{
		if (this.cpsInvolvement != aCpsInvolvement)
		{
			markModified();
		}
		cpsInvolvement = aCpsInvolvement;
	}

	public boolean getCpsCustody()
	{
	    fetch();
	    return cpsCustody;
	}

	public void setCpsCustody(boolean aCpsCustody)
	{
	    if (this.cpsCustody != aCpsCustody)
		{
			markModified();
		}
	    	cpsCustody = aCpsCustody;
	}
	/**
	 * Access method for the details property.
	 * 
	 * @return the current value of the details property
	 */
	public String getDetails()
	{
		fetch();
		return details;
	}

	/**
	 * Sets the value of the details property.
	 * 
	 * @param aDetails
	 *            the new value of the details property
	 */
	public void setDetails(String aDetails)
	{
		if (this.details == null || !this.details.equals(aDetails))
		{
			markModified();
		}
		details = aDetails;
	}

	/**
	 * Access method for the event property.
	 * 
	 * @return the current value of the event property
	 */
	public String getEvent()
	{
		fetch();
		return event;
	}

	/**
	 * Sets the value of the event property.
	 * 
	 * @param aEvent
	 *            the new value of the event property
	 */
	public void setEvent(String aEvent)
	{
		if (this.event == null || !this.event.equals(aEvent))
		{
			markModified();
		}
		event = aEvent;
	}

	/**
	 * @param juvenileNum
	 * @roseuid 42B06037001C
	 */
	public void findAll(String juvenileNum)
	{
		fetch();
	}

	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 */
	public void setJuvenileNum(String juvenileId)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileId))
		{
			markModified();
		}
		this.juvenileNum = juvenileId;
	}

	/**
	 * Get the reference value to class :: pd.juvenile.Juvenile
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setLevelId(String levelId)
	{
		if (this.levelId == null || !this.levelId.equals(levelId))
		{
			markModified();
		}
		level = null;
		this.levelId = levelId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getLevelId()
	{
		fetch();
		return levelId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initLevel()
	{
		if (level == null)
		{
			try
			{
				level = (Code) new mojo.km.persistence.Reference(levelId, Code.class,
						"JUV_ABUSE_LEVEL").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getLevel()
	{
		initLevel();
		return level;
	}

	/**
	 * set the type reference for class member level
	 */
	public void setLevel(Code level)
	{
		if (this.level == null || !this.level.equals(level))
		{
			markModified();
		}
		if (level.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(level);
		}
		setLevelId("" + level.getOID());
		level.setContext("JUV_ABUSE_LEVEL");
		this.level = (Code) new mojo.km.persistence.Reference(level).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setRelationshipToJuvenileId(String relationshipToJuvenileId)
	{
		if (this.relationshipToJuvenileId == null || !this.relationshipToJuvenileId.equals(relationshipToJuvenileId))
		{
			markModified();
		}
		relationshipToJuvenile = null;
		this.relationshipToJuvenileId = relationshipToJuvenileId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getRelationshipToJuvenileId()
	{
		fetch();
		return relationshipToJuvenileId;
	}
	/*public String getAllegedAbuserRelationship()
	{
	    fetch();
	    return allegedAbuserRelationship;
	}

	public void setAllegedAbuserRelationship(String allegedAbuserRelationship)
	{
	    if (this.allegedAbuserRelationship == null || !this.allegedAbuserRelationship.equals(allegedAbuserRelationship))
		{
			markModified();
		}
	    	//allegedAbuserRelationship = null;
		this.allegedAbuserRelationship = allegedAbuserRelationship;
	}*/
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initRelationshipToJuvenile()
	{
		if (relationshipToJuvenile == null)
		{
			try
			{
				if (memberId != null && !memberId.equals(""))
				{
					relationshipToJuvenile = (Code) new mojo.km.persistence.Reference(
							relationshipToJuvenileId, Code.class,
							PDCodeTableConstants.RELATIONSHIP_JUVENILE).getObject();
				}
				else if (contactId != null && !contactId.equals(""))
				{
					relationshipToJuvenile = (Code) new mojo.km.persistence.Reference(
							relationshipToJuvenileId, Code.class,
							PDCodeTableConstants.CONTACT_RELATIONSHIP).getObject();
				}
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getRelationshipToJuvenile()
	{
		initRelationshipToJuvenile();
		return relationshipToJuvenile;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	// public void setTypeId(String typeId)
	// {
	// if (this.typeId == null || !this.typeId.equals(typeId))
	// {
	// markModified();
	// }
	// type = null;
	// this.typeId = typeId;
	// }
	// /**
	// * Get the reference value to class :: pd.codetable.Code
	// */
	// public String getTypeId()
	// {
	// fetch();
	// return typeId;
	// }
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	// private void initType()
	// {
	// if (type == null)
	// {
	// try
	// {
	// type =
	// (pd.codetable.Code) new mojo
	// .km
	// .persistence
	// .Reference(typeId, pd.codetable.Code.class, "JUV_ABUSE_TYPE")
	// .getObject();
	// }
	// catch (Throwable t)
	// {
	// }
	// }
	// }
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	// public pd.codetable.Code getType()
	// {
	// initType();
	// return type;
	// }
	/**
	 * set the type reference for class member type
	 */
	// public void setType(pd.codetable.Code type)
	// {
	// if (this.type == null || !this.type.equals(type))
	// {
	// markModified();
	// }
	// if (type.getOID() == null)
	// {
	// new mojo.km.persistence.Home().bind(type);
	// }
	// setTypeId("" + type.getOID());
	// type.setContext("JUV_ABUSE_TYPE");
	// this.type = (pd.codetable.Code) new
	// mojo.km.persistence.Reference(type).getObject();
	// }
	/**
	 * @param event
	 * @return
	 */
	public static Iterator findJuvenileAbuses(GetJuvenileAbuseListEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileAbuse.class);
	}

	/**
	 * @param saveEvent
	 */
	public void hydrate(SaveJuvenileAbuseEvent saveEvent)
	{
		this.setDetails(saveEvent.getAbuseDetails());
		this.setCpsInvolvement(saveEvent.isCpsInvolvement());
		this.setCpsCustody(saveEvent.isCpsCustody());
		this.setEvent(saveEvent.getAbuseEvent());
		this.setJuvenileNum(saveEvent.getJuvenileNum());
		this.setLevelId(saveEvent.getAbuseLevelId());
		// this.setTypeId(saveEvent.getAbuseTypeId());
		this.setInformationBasisId(saveEvent.getInformationBasisId());
		this.setTraitTypeId(saveEvent.getTraitTypeId());
		this.setPerpetratorFirstName(saveEvent.getFirstName());
		this.setPerpetratorMiddleName(saveEvent.getMiddleName());
		this.setPerpetratorLastName(saveEvent.getLastName());
		this.setRelationshipToJuvenileId(saveEvent.getRelationshipToJuvenileId());
		this.setSupervisionNum(saveEvent.getSupervisionNum());
		this.setTreatment(saveEvent.getAbuseTreatment());
		/*this.setMemberId(saveEvent.getMemberId());
		this.setContactId(saveEvent.getContactId());*/
		this.setInformationSrcCd(saveEvent.getInformationSrcCd());
		this.setCpsCaseNumber(saveEvent.getCpsCasenumber());
		//this.setAllegedAbuserRelationship(saveEvent.getAllegedAbuserRelationship());
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.TraitType
	 */
	private void initTraitType()
	{
		if (traitType == null)
		{
			try
			{
				traitType = (TraitType) new mojo.km.persistence.Reference(this.getTraitTypeId(),
						TraitType.class).getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.TraitType
	 */
	public String getTraitTypeId()
	{
		fetch();
		return this.traitTypeId;
	}

	/**
	 * Gets referenced type pd.juvenilecase.TraitType
	 */
	public TraitType getTraitType()
	{
		fetch();
		initTraitType();
		return traitType;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		if (this.traitTypeId == null || !this.traitTypeId.equals(string))
		{
			markModified();
		}
		traitTypeId = string;
	}

	/**
	 * Access method for the informationBasisId property.
	 * 
	 * @return the current value of the informationBasisId property
	 */
	public String getInformationBasisId()
	{
		fetch();
		return informationBasisId;
	}

	/**
	 * Sets the value of the informationBasisId property.
	 * 
	 * @param aInformationBasisId
	 *            the new value of the informationBasisId property
	 */
	public void setInformationBasisId(String aInformationBasisId)
	{
		if (this.informationBasisId == null || !this.informationBasisId.equals(aInformationBasisId))
		{
			markModified();
		}
		informationBasisId = aInformationBasisId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initInformationBasis()
	{
		if (informationBasis == null)
		{
			try
			{
				informationBasis = (Code) new mojo.km.persistence.Reference(informationBasisId,
						Code.class, "FAMILY_TRAIT_STATUS").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getInformationBasis()
	{
		initInformationBasis();
		return informationBasis;
	}

	/**
	 * set the type reference for class member level
	 */
	public void setInformationBasis(Code level)
	{
		if (this.informationBasis == null || !this.informationBasis.equals(informationBasis))
		{
			markModified();
		}
		if (informationBasis.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(informationBasis);
		}
		setInformationBasisId("" + informationBasis.getOID());
		informationBasis.setContext("FAMILY_TRAIT_STATUS");
		this.informationBasis = (Code) new mojo.km.persistence.Reference(informationBasis).getObject();
	}

	public String getMemberId()
	{
		fetch();
		return memberId;
	}

	public void setMemberId(String aMemberId)
	{
		if (this.memberId == null || !this.memberId.equals(aMemberId))
		{
			markModified();
			relationshipToJuvenile = null;
		}
		memberId = aMemberId;
	}

	public String getContactId()
	{
		fetch();
		return contactId;
	}

	/**
	 * Sets the value of the treatment property.
	 * 
	 * @param aTreatment
	 *            the new value of the treatment property
	 */
	public void setContactId(String aContactId)
	{
		if (this.contactId == null || !this.contactId.equals(aContactId))
		{
			markModified();
			relationshipToJuvenile = null;
		}
		contactId = aContactId;
	}
	
	public String getInformationSrcCd() {
		fetch();
		return informationSrcCd;
	}

	public void setInformationSrcCd(String informationSrcCd) {
		
		if (this.informationSrcCd == null || !this.informationSrcCd.equals(informationSrcCd)) 
		{
			markModified();
		}
		this.informationSrcCd = informationSrcCd;
	}

	public String getCpsCaseNumber()
	{
	    fetch();
	    return cpsCaseNumber;
	}

	public void setCpsCaseNumber(String cpsCaseNumber)
	{
	    if ( this.cpsCaseNumber == null || !this.cpsCaseNumber.equals(cpsCaseNumber)){
		markModified();
	    }
	    this.cpsCaseNumber = cpsCaseNumber;
	}
	
	

}
