package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.juvenile.GetJuvenileAbuseListEvent;
import messaging.juvenile.GetJuvenileDualStatusListEvent;
import messaging.juvenile.SaveJuvenileAbuseEvent;
import messaging.juvenile.SaveJuvenileDualStatusEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import messaging.juvenile.reply.JuvenileDualStatusResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.juvenilecase.TraitType;
import ui.common.CodeHelper;

public class JuvenileDualStatus extends PersistentObject
{
    	private String juvenileNum;
    	private String supervisionNum;
    	private Date createDate;
    	private String referralRegion;	
	private String custodyStatus;	
	private String cpsLevelofcare;
	private String parentalRights;
	private String CPSServices;
	private String placementDate;
	private String placementType;
	private String placementtypeOther;
	private String placementRemovalReason;
	private String placementremovalOther;	
	private String dualstatusComments;
	private String traitTypeId;
	private Code informationBasis = null;
	private String informationBasisId;
	public TraitType traitType = null;
	private String informationSrcCd;
	
	public JuvenileDualStatus()
	{
	}
	
	public JuvenileDualStatusResponseEvent getValueObject()
	{
	    	JuvenileDualStatusResponseEvent event = new JuvenileDualStatusResponseEvent();
		event.setDualstatusId(this.getDualStatusId());		
		event.setEntryDate(this.getCreateDate());
		event.setReferralRegion(this.getReferralRegion());
		event.setCustodyStatus(this.getCustodyStatus());
		event.setCpslevelofCare(this.getCpsLevelofcare());
		event.setParentalrightsTermination(this.getParentalRights());
		event.setCPSServices(this.getCPSServices());
		/*event.setPlacementDate(this.getPlacementDate());
		event.setPlacementType(this.getPlacementType());
		event.setPlacementtypeotherReason(this.getPlacementtypeOther());
		event.setPlacementRemovalReason(this.getPlacementRemovalReason());
		event.setPlacementremovalreasonOther(this.getPlacementremovalOther());*/
		event.setDualstatusComments(this.getDualstatusComments());
		TraitType traitType = this.getTraitType();
		if (traitType != null)
		{
			event.setTraitTypeId(traitType.getTraitTypeId());
			event.setTraitTypeName(traitType.getName());
		}
		event.setInformationSrcCd(this.getInformationSrcCd());
		event.setJuvenileNumber(this.getJuvenileNum());
		if(this.getInformationSrcCd()!=null)
			event.setInformationSrcDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.INFORMATION_SOURCE, this.getInformationSrcCd()));
		return event;
	}

	/**
	 * 
	 */
	private String getDualStatusId()
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

	

	
	public static Iterator findJuvenileDualStatuses(GetJuvenileDualStatusListEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileDualStatus.class);
	}

	/**
	 * @param saveEvent
	 */
	public void hydrate(SaveJuvenileDualStatusEvent saveEvent)
	{
		this.setCreateDate(saveEvent.getCreateDate());
		this.setReferralRegion(saveEvent.getReferralRegion());
		this.setCustodyStatus(saveEvent.getCustodyStatus());
		this.setCpsLevelofcare(saveEvent.getCpsLevelofcare());
		//this.setParentalRights(saveEvent.isParentalRights());
		this.setParentalRights(saveEvent.getParentalRights());
		this.setCPSServices(saveEvent.getCPSServices());
		this.setPlacementDate(saveEvent.getPlacementDate());
		this.setPlacementType(saveEvent.getPlacementType());
		this.setPlacementtypeOther(saveEvent.getPlacementtypeOther());
		this.setPlacementRemovalReason(saveEvent.getPlacementRemovalReason());
		this.setPlacementremovalOther(saveEvent.getPlacementremovalOther());
		this.setDualstatusComments(saveEvent.getDualstatusComments());
		this.setJuvenileNum(saveEvent.getJuvenileNum());
		this.setSupervisionNum(saveEvent.getSupervisionNum());
		/*this.setInformationBasisId(saveEvent.getInformationBasisId());
		this.setTraitTypeId(saveEvent.getTraitTypeId());*/
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
	public String getReferralRegion()
	{
	    return referralRegion;
	}

	public void setReferralRegion(String referralRegion)
	{
	    this.referralRegion = referralRegion;
	}
	public String getCustodyStatus()
	{
	    return custodyStatus;
	}

	public void setCustodyStatus(String custodyStatus)
	{
	    this.custodyStatus = custodyStatus;
	}
	public String getCpsLevelofcare()
	{
	    return cpsLevelofcare;
	}

	public void setCpsLevelofcare(String cpsLevelofcare)
	{
	    this.cpsLevelofcare = cpsLevelofcare;
	}
	
	
	
	public String getCPSServices()
	{
	    return CPSServices;
	}

	public void setCPSServices(String cPSServices)
	{
	    CPSServices = cPSServices;
	}
	

	
	
	public String getPlacementType()
	{
	    return placementType;
	}

	public void setPlacementType(String placementType)
	{
	    this.placementType = placementType;
	}
	
	public String getPlacementRemovalReason()
	{
	    return placementRemovalReason;
	}

	public void setPlacementRemovalReason(String placementRemovalReason)
	{
	    this.placementRemovalReason = placementRemovalReason;
	}
	

	public String getDualstatusComments()
	{
	    return dualstatusComments;
	}

	public void setDualstatusComments(String dualstatusComments)
	{
	    this.dualstatusComments = dualstatusComments;
	}
	public String getPlacementDate()
	{
	    return placementDate;
	}

	public void setPlacementDate(String placementDate)
	{
	    this.placementDate = placementDate;
	}
	public String getPlacementtypeOther()
	{
	    return placementtypeOther;
	}

	public void setPlacementtypeOther(String placementtypeOther)
	{
	    this.placementtypeOther = placementtypeOther;
	}

	
	public String getPlacementremovalOther()
	{
	    return placementremovalOther;
	}

	public void setPlacementremovalOther(String placementremovalOther)
	{
	    this.placementremovalOther = placementremovalOther;
	}
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
	public String getParentalRights()
	{
	    fetch();
	    return parentalRights;
	}

	public void setParentalRights(String parentalRights)
	{
	    if (this.parentalRights == null || !this.parentalRights.equals(parentalRights)) 
		{
			markModified();
		}
	    this.parentalRights = parentalRights;
	}



}
