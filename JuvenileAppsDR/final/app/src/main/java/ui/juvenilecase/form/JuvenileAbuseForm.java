/*
 * Created on Jun 23, 2005
 *
 */
package ui.juvenilecase.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import messaging.juvenile.reply.JuvenileDualStatusResponseEvent;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.AbuseEntryComparator;
import ui.juvenilecase.AbusePerpetrator;
import ui.juvenilecase.DualStatusEntryComparator;

/**
 * @author jfisher
 *
 */
public class JuvenileAbuseForm extends ActionForm
{
	private final String DATE_FORMAT_1 = "M/d/yyyy";
	private String action;
	private String abuseId;
	
	private Date entryDate;
	private Name perpetratorName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String relationshipToJuvenile;
	private String relationshipToJuvenileId;
	private String allegedAbuserRelationship;
	
	private String abuseLevel;
	private String abuseLevelId;
	private String informationBasis;
	private String informationBasisId;
	private String traitTypeName;
	private String traitTypeId;
	private String memberId;
	private String contactId;
	private String perpId;
	private Collection perpID;
	private String statusId;
	private String status;
	private String cpsCaseNumber;
	
	private String relationShips;
	private String multiNames;
	private String namewithRelationShips;	
	
	//	private String abuseType;
//	private String abuseTypeId;
	private String abuseTreatment;
	private Map abuseMap;
	private Map dualMap;
	
	private String cpsInvolvement;
	private String cpsCustody;	

	private String abuseDetails;
	private String abuseEvent;
	private String supervisionNum;
	private Collection juvenileRelationships;
//	private Collection abuseTypes;
	private Collection abuseLevels;
	private Collection placementYears;
	private Collection informationBasisLevels;
	private TraitsForm traitsForm;
	private Collection perpetrators=new ArrayList();
	private List<AbusePerpetrator> multiplePrep;
	private Object prepId;
	
	//added for US 40635
	private String informationSrcCd;
	private String informationSrcDesc;
	
	//US 109828
	
	private String dualstatusId;
	private Collection dualstatuses;
	private String referralRegion;
	private String custodyStatus;
	private String cpslevelofCare;
	private String parentalrightsTermination;
	private String CPSServices;
	private String placementDate;
	private String placementdateMonth;
	private String placementdateYear;	
	private String placementType;
	private String placementtypeotherReason;
	private String placementRemovalReason;
	private String placementremovalreasonOther;
	private String selectedServices;	

	private String dualstatusComments;
	private Collection newPlacements ;
	private String selectedValue = UIConstants.EMPTY_STRING ;
	private String informationSource;

	

	public JuvenileAbuseForm()
	{

	}

	public void clear()
	{
		this.memberId=null;
		this.contactId=null;
		this.abuseId = null;
		this.entryDate = null;
		this.perpetratorName = new Name();
		this.firstName = null;
		this.lastName = null;
		this.middleName = null;
		this.relationshipToJuvenile = null;
		this.relationshipToJuvenileId = null;
		this.abuseLevel = null;
		this.abuseLevelId = null;
//		this.abuseType = null;
//		this.abuseTypeId = null;
		this.abuseTreatment = null;
		this.cpsInvolvement = null;
		this.abuseDetails = null;
		this.abuseEvent = null;
		this.abuseMap = new HashMap();
		this.dualMap=new HashMap();
		this.informationBasis=null;
		this.informationBasisId=null;
		this.perpetrators=new ArrayList();
		this.perpId=null;
		this.cpsCustody=null;
		this.allegedAbuserRelationship=null;
		this.dualstatusId=null;
		this.referralRegion=null;
		this.custodyStatus=null;
		this.cpslevelofCare=null;
		this.parentalrightsTermination=null;
		this.CPSServices=null;
		this.placementDate=null;
		this.placementType=null;
		this.placementRemovalReason=null;
		this.dualstatusComments=null;
		this.newPlacements=null;
		this.placementdateMonth=null;
		this.placementdateYear=null;
		this.placementremovalreasonOther=null;
		this.placementtypeotherReason=null;
		this.informationSrcCd=null;
		this.dualstatuses=null;
		this.selectedServices=null;
		this.informationSource=null;
		this.cpsCaseNumber=null;
	}

	/**
	 * @return
	 */
	public String getAbuseLevel()
	{
		this.abuseLevel = CodeHelper.getCodeDescriptionByCode(abuseLevels, this.abuseLevelId);
		return this.abuseLevel;
	}

	/**
	 * @return
	 */
	public String getAbuseTreatment()
	{
		return abuseTreatment;
	}

	/**
	 * @return
	 */
//	public String getAbuseType()
//	{
//		this.abuseType = CodeHelper.getCodeDescriptionByCode(abuseTypes, this.abuseTypeId);
//		return this.abuseType;
//	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	public String getEntryDateString()
	{
		String dateString = null;
		if (entryDate != null)
		{
			SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT_1);
			dateString = fmt.format(entryDate);
		}
		return dateString;
	}

	/**
	 * @return
	 */
	public Name getPerpetratorName()
	{
		return perpetratorName;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenile()
	{
//		this.relationshipToJuvenile =
//			CodeHelper.getCodeDescriptionByCode(juvenileRelationships, this.relationshipToJuvenileId);
		return this.relationshipToJuvenile;
	}

	/**
	 * @param string
	 */
	public void setAbuseLevel(String string)
	{
		this.abuseLevel = string;
	}

	/**
	 * @param string
	 */
	public void setAbuseTreatment(String string)
	{
		abuseTreatment = string;
	}

	/**
	 * @param string
	 */
//	public void setAbuseType(String string)
//	{
//		abuseType = string;
//	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setPerpetratorName(Name name)
	{
		perpetratorName = name;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvenile(String string)
	{
		relationshipToJuvenile = string;
	}

	/**
	 * @return
	 */
	public Map getAbuseMap()
	{
		if (abuseMap == null)
		{
			abuseMap = new HashMap();
		}
		return abuseMap;
	}

	/**
	 * @param map
	 */
	public void setAbuseMap(Map map)
	{
		abuseMap = map;
	}
	public Map getDualMap()
	{
	    if (dualMap == null)
		{
			dualMap = new HashMap();
		}
		return dualMap;
	}

	public void setDualMap(Map dualMap)
	{
	    this.dualMap = dualMap;
	}
	public void setAbuses(Collection abuses)
	{
		if (abuses != null)
		{
			Iterator i = abuses.iterator();
			while (i.hasNext())
			{
				JuvenileAbuseResponseEvent abuseEvent = (JuvenileAbuseResponseEvent) i.next();
				this.getAbuseMap().put(abuseEvent.getAbuseId(), abuseEvent);
			}
		}
	}
	public void setDualstatuses(Collection dualstatuses)
	{
		if (dualstatuses != null)
		{
			Iterator i = dualstatuses.iterator();
			while (i.hasNext())
			{
			    JuvenileDualStatusResponseEvent dualEvent = (JuvenileDualStatusResponseEvent) i.next();
				this.getDualMap().put(dualEvent.getDualstatusId(), dualEvent);
			}
		}		
		    
	}
	public Collection getAbuses()
	{
		Collection abuses = new ArrayList();
		abuses.addAll(this.getAbuseMap().values());

		Collections.sort((List) abuses, new AbuseEntryComparator());
		return abuses;
	}
	public Collection getDualstatuses()
	{
		Collection dualstatuses = new ArrayList();
		dualstatuses.addAll(this.getDualMap().values());

		Collections.sort((List) dualstatuses, new DualStatusEntryComparator());
		return dualstatuses;
	}

	/**
	 * @return
	 */
	public String getAbuseId()
	{
		return abuseId;
	}

	/**
	 * @param string
	 */
	public void setAbuseId(String string)
	{
		abuseId = string;
	}

	/**
	 * @return
	 */
	public String getAbuseDetails()
	{
		return abuseDetails;
	}

	/**
	 * @return
	 */
	public String getAbuseEvent()
	{
		return abuseEvent;
	}

	/**
	 * @return
	 */
	public Collection getAbuseLevels()
	{
		if (abuseLevels == null)
		{
			this.abuseLevels = new ArrayList();
		}
		return abuseLevels;
	}

	/**
	 * @return
	 */
//	public Collection getAbuseTypes()
//	{
//		if (abuseTypes == null)
//		{
//			this.abuseTypes = new ArrayList();
//		}
//		return abuseTypes;
//	}

	

	/**
	 * @return
	 */
	public Collection getJuvenileRelationships()
	{
		if (this.juvenileRelationships == null)
		{
			this.juvenileRelationships = new ArrayList();
		}
		return this.juvenileRelationships;
	}

	/**
	 * @param string
	 */
	public void setAbuseDetails(String string)
	{
		abuseDetails = string;
	}

	/**
	 * @param string
	 */
	public void setAbuseEvent(String string)
	{
		abuseEvent = string;
	}

	/**
	 * @param collection
	 */
	public void setAbuseLevels(Collection collection)
	{
		abuseLevels = collection;
	}

	/**
	 * @param collection
	 */
//	public void setAbuseTypes(Collection collection)
//	{
//		abuseTypes = collection;
//	}

	
	/**
	 * @param collection
	 */
	public void setJuvenileRelationships(Collection collection)
	{
		juvenileRelationships = collection;
	}
	/**
	 * @param abuseId
	 */
	public void initAbuse(String abuseId)
	{
		JuvenileAbuseResponseEvent abuseEvent = (JuvenileAbuseResponseEvent) abuseMap.get(abuseId);

		if (abuseEvent != null)
		{
			this.contactId=abuseEvent.getContactId();
			this.memberId=abuseEvent.getMemberId();
			this.entryDate = abuseEvent.getEntryDate();
			this.abuseDetails = abuseEvent.getAbuseDetails();
			this.abuseEvent = abuseEvent.getAbuseEvent();
			this.abuseId = abuseEvent.getAbuseId();
			this.abuseLevel = abuseEvent.getAbuseLevelCode();
			this.abuseTreatment = abuseEvent.getTreatment();
			this.traitTypeName=abuseEvent.getTraitTypeName();
			if (abuseEvent.isCpsInvolvement() == true)
			{
				this.cpsInvolvement = PDJuvenileCaseConstants.CPS_INVOLVEMENT_YES;
			}
			else
			{
				this.cpsInvolvement = PDJuvenileCaseConstants.CPS_INVOLVEMENT_NO;
			}
	//		this.abuseType = abuseEvent.getAbuseTypeCode();
			this.setInformationBasisId(abuseEvent.getInformationBasisId());
			Name perpName = new Name();
			perpName.setFirstName(abuseEvent.getFirstName());
			perpName.setMiddleName(abuseEvent.getMiddleName());
			perpName.setLastName(abuseEvent.getLastName());
			this.perpetratorName = perpName;
			this.relationshipToJuvenileId = abuseEvent.getRelationshipToJuvenileCode();
			this.relationshipToJuvenile=abuseEvent.getRelationshipToJuvenileDescription();
			this.abuseLevelId = abuseEvent.getAbuseLevelCode();
			this.allegedAbuserRelationship=abuseEvent.getAllegedAbuserRelationship();
			this.namewithRelationShips=abuseEvent.getNamewithRelationship();
	//		this.abuseTypeId = abuseEvent.getAbuseTypeCode();

			if (abuseEvent.isCpsCustody() == true)
			{
				this.cpsCustody = PDJuvenileCaseConstants.CPS_CUSTODY_YES;
			}
			else
			{
				this.cpsCustody = PDJuvenileCaseConstants.CPS_CUSTODY_NO;
			}
			this.cpsCaseNumber = abuseEvent.getCpsCaseNumber();
		}

	}
	public void initDualStatus(String dualstatusId)
	{
	    JuvenileDualStatusResponseEvent dualstatusEvent = (JuvenileDualStatusResponseEvent) dualMap.get(dualstatusId);

		if (dualstatusEvent != null)
		{
		    	this.dualstatusId=dualstatusEvent.getDualstatusId();
		    	this.referralRegion=dualstatusEvent.getReferralRegion();
			this.custodyStatus=dualstatusEvent.getCustodyStatus();
			this.cpslevelofCare = dualstatusEvent.getCpslevelofCare();
			this.parentalrightsTermination=dualstatusEvent.getParentalrightsTermination();			
			this.entryDate = dualstatusEvent.getEntryDate();
			this.CPSServices = dualstatusEvent.getCPSServices();
			this.dualstatusComments = dualstatusEvent.getDualstatusComments();
			/*this.placementDate = dualstatusEvent.getPlacementDate();
			this.placementType = dualstatusEvent.getPlacementType();
			this.placementRemovalReason = dualstatusEvent.getPlacementRemovalReason();
			*/
			}

	}

	/**
	 * @return
	 */
	public String getAbuseLevelId()
	{
		return abuseLevelId;
	}

	/**
	 * @return
	 */
//	public String getAbuseTypeId()
//	{
//		return abuseTypeId;
//	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenileId()
	{
		return relationshipToJuvenileId;
	}

	/**
	 * @param string
	 */
	public void setAbuseLevelId(String string)
	{
		abuseLevelId = string;
	}

	/**
	 * @param string
	 */
//	public void setAbuseTypeId(String string)
//	{
//		abuseTypeId = string;
//	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvenileId(String string)
	{
		relationshipToJuvenileId = string;
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum(String string)
	{
		supervisionNum = string;
	}
	
	/**
	 * @return
	 */
	public TraitsForm getTraitsForm()
	{
		return traitsForm;
	}

	/**
	 * @param form
	 */
	public void setTraitsForm(TraitsForm form)
	{
		traitsForm = form;
	}

	/**
	 * @return
	 */
	public String getTraitTypeName()
	{
		return traitTypeName;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeName(String string)
	{
		traitTypeName = string;
	}

	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
	}

	/**
	 * @return Returns the informationBasis.
	 */
	public String getInformationBasis() {
		
		return informationBasis;
	}
	/**
	 * @param informationBasis The informationBasis to set.
	 */
	public void setInformationBasis(String informationBasis) {
		this.informationBasis = informationBasis;
	}
	/**
	 * @return Returns the informationBasisId.
	 */
	public String getInformationBasisId() {
		
		return informationBasisId;
	}
	/**
	 * @param informationBasisId The informationBasisId to set.
	 */
	public void setInformationBasisId(String informationBasisId) {
		
		this.informationBasisId = informationBasisId;
		if(informationBasisId==null || informationBasisId.equals("")){
			informationBasis="";
		}
		else{
			this.informationBasis = CodeHelper.getCodeDescriptionByCode(getInformationBasisLevels(), this.informationBasisId);
		}
		return;
	}
	/**
	 * @return Returns the informationBasisLevels.
	 */
	public Collection getInformationBasisLevels() {
		if (informationBasisLevels == null)
		{
			// CHANGED during er: JIMS200034925 at request of PAM said the user would need to be trained
			//this.informationBasisLevels = CodeHelper.getCodes(PDCodeTableConstants.ABUSE_INFO_BASIS, true);
			this.informationBasisLevels = CodeHelper.getCodes(PDCodeTableConstants.FAMILY_TRAIT_STATUS, true);
		}
		if(informationBasisLevels==null){
			return new ArrayList();
		}
		return informationBasisLevels;
	}
	/**
	 * @param informationBasisLevels The informationBasisLevels to set.
	 */
	public void setInformationBasisLevels(Collection informationBasisLevels) {
		this.informationBasisLevels = informationBasisLevels;
	}
	/**
	 * @return Returns the contactId.
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId The contactId to set.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return Returns the memberId.
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId The memberId to set.
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return Returns the perpetrators.
	 */
	public Collection getPerpetrators() {
		return perpetrators;
	}
	/**
	 * @param perpetrators The perpetrators to set.
	 */
	public void setPerpetrators(Collection perpetrators) {
		this.perpetrators = perpetrators;
	}
	/**
	 * @return Returns the perpId.
	 */
	public String getPerpId() {
		return perpId;
	}
	/**
	 * @param perpId The perpId to set.
	 */
	public void setPerpId(String perpId) {
		this.perpId = perpId;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String aStatusId) {
		this.statusId = aStatusId;
		setStatus("");
		if(statusId!=null && !statusId.equals("")){
			status=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FAMILY_TRAIT_STATUS,statusId);
			
		}
	}

	public List<AbusePerpetrator> getMultiplePrep() {
		return multiplePrep;
	}

	public void setMultiplePrep(List<AbusePerpetrator> multiplePrep) {
		this.multiplePrep = multiplePrep;
	}

public void setprepId(Collection prepID) {
		this.prepId = prepId;
	}

public Collection getprepID()
{return this.getprepID();
}

public String getRelationShips() {
	return relationShips;
}

public void setRelationShips(String relationShips) {
	this.relationShips = relationShips;
}

public String getMultiNames() {
	return multiNames;
}

public void setMultiNames(String multiNames) {
	this.multiNames = multiNames;
}

/**
 * @return the informationSrcCd
 */
public String getInformationSrcCd() {
	return informationSrcCd;
}

/**
 * @param informationSrcCd the informationSrcCd to set
 */
public void setInformationSrcCd(String informationSrcCd) {
	this.informationSrcCd = informationSrcCd;
}

/**
 * @return the informationSrcDesc
 */
public String getInformationSrcDesc() {
	return informationSrcDesc;
}

/**
 * @param informationSrcDesc the informationSrcDesc to set
 */
public void setInformationSrcDesc(String informationSrcDesc) {
	this.informationSrcDesc = informationSrcDesc;
}
/**
 * @return
 */
/**
 * @param string
 */
public void setCpsInvolvement(String string)
{
	cpsInvolvement = string;
}
public String getCpsInvolvement()
{
	return cpsInvolvement;
}

public String getCpsInvolvementString()
{
	if (PDJuvenileCaseConstants.CPS_INVOLVEMENT_YES.equals(cpsInvolvement))
	{
		return "Yes";
	}
	else
	{
		return "No";
	}
}
//epic 109828
public String getCpsCustody()
{
    return cpsCustody; 
}
public String getCpsCustodyString()
{
    if (PDJuvenileCaseConstants.CPS_CUSTODY_YES.equals(cpsCustody))
	{
		return "Yes";
	}
	else
	{
		return "No";
	}    
}

public void setCpsCustody(String cpsCustody)
{
    this.cpsCustody = cpsCustody;
}
public String getAllegedAbuserRelationship()
{
    return allegedAbuserRelationship;
}

public void setAllegedAbuserRelationship(String allegedAbuserRelationship)
{
    this.allegedAbuserRelationship = allegedAbuserRelationship;
}
public String getDualstatusId()
{
    return dualstatusId;
}

public void setDualstatusId(String dualstatusId)
{
    this.dualstatusId = dualstatusId;
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
public String getCpslevelofCare()
{
    return cpslevelofCare;
}

public void setCpslevelofCare(String cpslevelofCare)
{
    this.cpslevelofCare = cpslevelofCare;
}
public String getParentalrightsTermination()
{
    return parentalrightsTermination;
}

public void setParentalrightsTermination(String parentalrightsTermination)
{
    this.parentalrightsTermination = parentalrightsTermination;
}
public String getCPSServices()
{
    return CPSServices;
}

public void setCPSServices(String cPSServices)
{
    CPSServices = cPSServices;
}


public String getPlacementDate()
{
    return placementDate;
}

public void setPlacementDate(String placementDate)
{
    this.placementDate = placementDate;
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
public String getNamewithRelationShips()
{
    return namewithRelationShips;
}

public void setNamewithRelationShips(String namewithRelationShips)
{
    this.namewithRelationShips = namewithRelationShips;
}
public Collection getNewPlacements()
{
    if( this.newPlacements == null )
	{
		this.newPlacements = new ArrayList( ) ;
	}
    return newPlacements;
}

public void setNewPlacements(Collection newPlacements)
{
    this.newPlacements = newPlacements;
}
public String getSelectedValue()
{
    return selectedValue;
}

public void setSelectedValue(String selectedValue)
{
    this.selectedValue = selectedValue;
}
public String getPlacementdateYear()
{
    return placementdateYear;
}

public void setPlacementdateYear(String placementdateYear)
{
    this.placementdateYear = placementdateYear;
}
public String getPlacementdateMonth()
{
    return placementdateMonth;
}

public void setPlacementdateMonth(String placementdateMonth)
{
    this.placementdateMonth = placementdateMonth;
}
public String getPlacementremovalreasonOther()
{
    return placementremovalreasonOther;
}

public void setPlacementremovalreasonOther(String placementremovalreasonOther)
{
    this.placementremovalreasonOther = placementremovalreasonOther;
}
public String getPlacementtypeotherReason()
{
    return placementtypeotherReason;
}

public void setPlacementtypeotherReason(String placementtypeotherReason)
{
    this.placementtypeotherReason = placementtypeotherReason;
}
public Collection getPlacementYears()
{
	if (placementYears == null)
	{
		this.placementYears = new ArrayList();
	}
	return placementYears;
}

public void setPlacementYears(Collection placementYears)
{
    this.placementYears = placementYears;
}
public String getSelectedServices()
{
    return selectedServices;
}

public void setSelectedServices(String selectedServices)
{
    this.selectedServices = selectedServices;
}
public String getInformationSource()
{
    return informationSource;
}

public void setInformationSource(String informationSource)
{
    this.informationSource = informationSource;
}

public String getCpsCaseNumber()
{
    return cpsCaseNumber;
}

public void setCpsCaseNumber(String cpsCaseNumber)
{
    this.cpsCaseNumber = cpsCaseNumber;
}


//
}
