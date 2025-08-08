package pd.juvenilecase;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import pd.codetable.Code;
import ui.common.CodeHelper;

import messaging.juvenilecase.SaveJuvenileTraitsEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 42A71A8501B4
*/
public class JuvenileTrait extends PersistentObject
{
	private String traitTypeId;
	private String traitName;
	private String traitParent;
	private String traitId;	
	private String traitComments;
	private String juvenileNum;
	private String dispositionNum;
	private String supervisionNum;
	private Date createdDate;
	private String statusId;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey FAMILY_TRAIT_STATUS
	*/
	private Code status = null;
	/**
	* Properties for traits
	* @associationType simple
	* @referencedType pd.juvenilecase.JuvenileTrait
	* @detailerDoNotGenerate false
	*/
	public Collection traits;
	private String comments;
	
	
	//added for US 42660 - Facility
	private String facilityAdmitOID;
	//added for task 128545
	private String transferAdmitOID;	

	//added for US 40635
	private String informationSrcCd; 
	
	/**
	* Properties for traitType
	*/
	public TraitType traitType = null;
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileTrait.class);
	}
	
	static public Iterator findAllByAttributeName(String attributeName, String attributeValue)
	{
			IHome home = new Home();
			return home.findAll(attributeName, attributeValue, JuvenileTrait.class);
	}
	
	/**
	* @param casefileId
	* @return JuvenileCasefile
	*/
	static public JuvenileTrait find(String juvenileTraitId)
	{
		IHome home = new Home();
		JuvenileTrait juvTrait = (JuvenileTrait) home.find(juvenileTraitId, JuvenileTrait.class);
		return juvTrait;
	}
	
	public JuvenileTraitResponseEvent getValueObject()
	{
		JuvenileTraitResponseEvent event = new JuvenileTraitResponseEvent();
		event.setJuvenileTraitId(this.getTraitId());
		event.setJuvenileNum(this.getJuvenileNum());
		event.setSupervisionNum(this.getSupervisionNum());
		event.setDispositionNum(this.getDispositionNum());
		event.setEntryDate(this.getCreatedDate());
		event.setTraitComments(this.getComments());
		event.setTraitTypeName(this.getTraitParent());
		event.setTraitTypeDescription(this.getTraitName());
		event.setFacilityAdmitOID(this.getFacilityAdmitOID());
		event.setInformationSrcCd(this.getInformationSrcCd());
		if(this.getInformationSrcCd()!=null && !this.getInformationSrcCd().equals(""))
			event.setInformationSrcDesc((Code.find(PDCodeTableConstants.INFORMATION_SOURCE, this.getInformationSrcCd())).getDescription());
		TraitType traitType = this.getTraitType();
		if(traitType != null) 
		{
			event.setTraitTypeId(traitType.getTraitTypeId());
			event.setParentTypeId(traitType.getParentTypeId());
		}
		event.setStatusId(this.getStatusId());
		Code myStat = this.getStatus();
		if( myStat != null )
		{
			event.setStatus(myStat.getDescription());
		}
		else
		{
			event.setStatus("");
		}

		return event;
	}

	/**
	 * @param saveEvent
	 */
	public void hydrate(SaveJuvenileTraitsEvent saveEvent)
	{
		this.setJuvenileNum(saveEvent.getJuvenileNum());
		this.setSupervisionNum(saveEvent.getSupervisionNum());
		this.setDispositionNum(saveEvent.getDispositionNum());		
	}

	/*
	 * 
	 */
	public void hydrate(JuvenileTraitResponseEvent event)
	{
		this.comments = event.getTraitComments();
		this.createdDate = event.getEntryDate();
		this.traitTypeId = event.getTraitTypeId();
		this.statusId = event.getStatusId();
		this.informationSrcCd = event.getInformationSrcCd();   //added for US 40635
		this.facilityAdmitOID = event.getFacilityAdmitOID(); //US 42660
		this.transferAdmitOID = event.getTransferAdmitOID(); // 128545
	}

	/**
	* @roseuid 42A71A8501B4
	*/
	public JuvenileTrait()
	{
	}

	/**
	* Access method for the comments property.
	* @return the current value of the comments property
	*/
	public String getComments()
	{
		fetch();
		return comments;
	}
	
	/**
	* Sets the value of the comments property.
	* @param aComments the new value of the comments property
	*/
	public void setComments(String aComments)
	{
		if (this.comments == null || !this.comments.equals(aComments))
		{
			markModified();
		}
		comments = aComments;
	}
	
	/**
	* Access method for the dispositionNumber property.
	* @return the current value of the dispositionNumber property
	*/
	public String getDispositionNum()
	{
		fetch();
		return dispositionNum;
	}
	
	/**
	* Sets the value of the dispositionNumber property.
	* @param aDispositionNumber the new value of the dispositionNumber property
	*/
	public void setDispositionNum(String dispositionNum)
	{
		if (this.dispositionNum == null || !this.dispositionNum.equals(dispositionNum))
		{
			markModified();
		}
		this.dispositionNum = dispositionNum;
	}
	
	/**
	* Access method for the juvenileNum property.
	* @return the current value of the juvenileNum property
	*/
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}
	
	/**
	* Sets the value of the juvenileNum property.
	* @param aJuvenileNum the new value of the juvenileNum property
	*/
	public void setJuvenileNum(String aJuvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(aJuvenileNum))
		{
			markModified();
		}
		juvenileNum = aJuvenileNum;
	}
	
	/**
	* Access method for the supervisionNum property.
	* @return the current value of the supervisionNum property
	*/
	public String getSupervisionNum()
	{
		fetch();
		return supervisionNum;
	}
	
	/**
	* Sets the value of the supervisionNum property.
	* @param aSupervisionNum the new value of the supervisionNum property
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
	* @roseuid 42A70E2E0137
	*/
	public void findByType()
	{
		fetch();
	}
	
	/**
	* @roseuid 42A70E2E0138
	*/
	public void findAll()
	{
		fetch();
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
	* Initialize class relationship to class pd.juvenilecase.TraitType
	*/
	private void initTraitType()
	{
		if( traitType == null )
		{
			try
			{
				traitType = (TraitType) new mojo.km.persistence.Reference(
						this.getTraitTypeId(), TraitType.class).getObject();
			}
			catch( Throwable t )
			{
			}
		}
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
	* Initialize class relationship implementation for pd.juvenilecase.JuvenileTrait
	*/
	private void initTraits()
	{
		if (traits == null)
		{
			if( this.getOID() == null )
			{
				new mojo.km.persistence.Home().bind(this);
			}
			
			try
			{
				traits = new mojo.km.persistence.ArrayList(
						JuvenileTraitTraitsJuvenileTrait.class, "parentId", "" + getOID());
			}
			catch (Throwable t)
			{
				traits = new java.util.ArrayList();
			}
		}
	}
	
	/**
	* returns a collection of pd.juvenilecase.JuvenileTrait
	*/
	public Collection getTraits()
	{
		initTraits();
		java.util.ArrayList retVal = new java.util.ArrayList();
		
		Iterator<JuvenileTraitTraitsJuvenileTrait> i = traits.iterator();
		while( i.hasNext() )
		{
			JuvenileTraitTraitsJuvenileTrait actual = i.next();
			retVal.add(actual.getChild());
		}
		
		return retVal;
	}
	
	/**
	* insert a pd.juvenilecase.JuvenileTrait into class relationship collection.
	*/
	public void insertTraits(JuvenileTrait anObject)
	{
		initTraits();
		JuvenileTraitTraitsJuvenileTrait actual =
				new JuvenileTraitTraitsJuvenileTrait();
		if( this.getOID() == null )
		{
			new Home().bind(this);
		}
		
		if( anObject.getOID() == null )
		{
			new Home().bind(anObject);
		}
		
		actual.setChild(anObject);
		actual.setParent(this);
		traits.add(actual);
	}
	
	/**
	* Removes a pd.juvenilecase.JuvenileTrait from class relationship collection.
	*/
	public void removeTraits(JuvenileTrait anObject)
	{
		initTraits();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			
			JuvenileTraitTraitsJuvenileTrait actual =
					(JuvenileTraitTraitsJuvenileTrait)new mojo.km.persistence.Reference(
							assocEvent, JuvenileTraitTraitsJuvenileTrait.class).getObject();
			traits.remove(actual);
		}
		catch (Throwable t)
		{
		}
	}
	
	/**
	* Clears all pd.juvenilecase.JuvenileTrait from class relationship collection.
	*/
	public void clearTraits()
	{
		initTraits();
		traits.clear();
	}
	
	/**
	 * @return
	 */
	public Date getCreatedDate()
	{
		fetch();
		return createdDate;
	}

	/**
	 * @param date
	 */
	public void setCreatedDate(Date date)
	{
		if (this.createdDate == null || !this.createdDate.equals(date))
		{
			markModified();
		}
		this.createdDate = date;
	}

	/**
	 * @return
	 */
	public String getTraitId()
	{
		return super.getOID().toString();
	}

	/**
	 * @param string
	 */
	public void setTraitId(String string)
	{
		super.setOID(string);
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
	 * @return
	 */
	public String getTraitName()
	{
		fetch();
		return traitName;
	}

	/**
	 * @return
	 */
	public String getTraitParent()
	{
		fetch();
		return traitParent;
	}

	/**
	 * @param string
	 */
	public void setTraitName(String string)
	{
		if (this.traitName == null || !this.traitName.equals(string))
		{
			markModified();
		}
		traitName = string;
		
	}

	/**
	 * @param string
	 */
	public void setTraitParent(String string)
	{
		if (this.traitParent == null || !this.traitParent.equals(string))
		{
			markModified();
		}
		traitParent = string;
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatusId(String statusId) 
	{
		if (this.statusId == null || !this.statusId.equals(statusId)) 
		{
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatusId() 
	{
		fetch();
		return statusId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() 
	{
		if( status == null ) 
		{
			status = (Code) new mojo.km.persistence.Reference(
					statusId, Code.class, PDCodeTableConstants.FAMILY_TRAIT_STATUS).getObject();
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus()
	{
		fetch();
		initStatus();
		return status;
	}
	
	/**
	* set the type reference for class member status
	*/
	public void setStatus(Code status)
	{
		if (this.status == null || !this.status.equals(status)) 
		{
			markModified();
		}
		
		if (status.getOID() == null) 
		{
			new mojo.km.persistence.Home().bind(status);
		}
		
		setStatusId("" + status.getOID());
		status.setContext(PDCodeTableConstants.FAMILY_TRAIT_STATUS);
		
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}

	public String getFacilityAdmitOID() {
		return facilityAdmitOID;
	}

	public void setFacilityAdmitOID(String facilityAdmitOID) {
		this.facilityAdmitOID = facilityAdmitOID;
	}
	public String getTransferAdmitOID()
	{
	    return transferAdmitOID;
	}

	public void setTransferAdmitOID(String transferAdmitOID)
	{
	    this.transferAdmitOID = transferAdmitOID;
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

}
