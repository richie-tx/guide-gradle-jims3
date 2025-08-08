/*
 * Created on Dec 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package ui.supervision.administerserviceprovider.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.reply.ContactFundSourceResponseEvent;
import messaging.administerserviceprovider.reply.ProgramSourceFundResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import naming.PDCodeTableConstants;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.common.form.AddressValidationForm;
// import messaging.administerlocation.reply.LocationResponseEvent;

/**
 * @ugopinath To change the template for this generated type comment go to
 *            Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *            Comments
 */
public class ServiceProviderForm extends AddressValidationForm
{
	// -----------------------------------------------
	// --- fields ---
	// -----------------------------------------------
	private String providerName = "" ;
	private String providerId = "" ;
	private String isInHouse = "" ;
	private String adminContactId = "" ;
	private String contactUserId = "" ;
	private String ifasNumber = "" ;

	private IPhoneNumber phoneNum = new PhoneNumber( "" ) ;
	private IPhoneNumber fax = new PhoneNumber( "" ) ;

	private String webSite = "" ;
	private String ftp = "" ;

	private ProviderAddress mailingAddress = new ProviderAddress() ;
	private ProviderAddress billingAddress = new ProviderAddress() ;

	private String startDate = "" ;
	

	private String actionType = "" ;

	private Program currentProgram = new Program() ;

	private Contact currentContact = new Contact() ;

	private String mailingAddrStatus = "" ;
	private String billingAddrStatus = "" ;
	
	private String selectedValue = "" ;
	private String selectedServiceValue = "" ;
	private String confirmMessage = "" ;
	
	private String fundSource;
	private String fundSourceDescription;
	private String fundStartDate;
	private String fundEndDate;
	private String comments;
	private String maxYouth=""; //US 177341

	// variables added for ASP Part 2
	private String searchTypeId = "" ;
	private int resultsSize = 0 ;
	private String frmEndDate = "" ;
	private String toEndDate = "" ;
	private String adminUserIDsString = "" ;
	private String contactUserIDsString = "" ;
	private String statusId = "" ;
	private String department = "" ;
	private String providerNameQueryString = "";
	private Date statusChangeDate;
	private String email;
	private String isEmailCheck = "" ;

	// Collections
	private static Collection genericLogonIds = new ArrayList() ;
	private static Collection stateList = new ArrayList() ;
	private Collection programs = new ArrayList() ;
	private static Collection contacts = new ArrayList() ;
	private static Collection streetTypeList = new ArrayList() ;
	private static Collection serviceTypeList = new ArrayList() ;
	private static Collection targetInterventionList = new ArrayList() ;
	private static Collection programScheduleTypeList = new ArrayList() ; //added for U.S #11099
	private static Collection typeList = new ArrayList() ;
	private static Collection subTypeList = new ArrayList() ;
	private static Collection stateProgramCodeList = new ArrayList() ;
	private static Collection typeProgramCodeList = new ArrayList() ;	
	private static Collection costBasisList = new ArrayList() ;
	private Collection serviceLocationNames = new ArrayList() ;
	private static Collection prefixList = new ArrayList() ;
	private static Collection suffixList = new ArrayList() ;

	// collections added for ASP Part 2
	private static Collection spSearchTypes = new ArrayList() ;
	private static Collection statusTypes = new ArrayList() ;
	private static Collection spTypes = new ArrayList() ;
	private Collection searchResults = new ArrayList() ;
	private Collection serviceEvents = new ArrayList() ;
	private Collection contracts = new ArrayList() ;
	
	//added for US #11376
	private Collection programLocationList = new ArrayList() ;
	private Collection programCategoryList = new ArrayList() ;
	private Collection fundSourceList = new ArrayList() ;
	private List<ContactFundSourceResponseEvent> contactFundSourceList = new ArrayList();
	
	// ------------------------------------------------------------------------
	// --- methods ---
	// ------------------------------------------------------------------------

	

	/*
	 */
	public void clear()
	{
		providerName = "" ;
		statusId = "" ;
		isInHouse = "" ;
		providerId = "" ;
		startDate = "" ;
		adminContactId = "" ;
		contactUserId = "" ;
		confirmMessage = "" ;
		ifasNumber = "" ;
		phoneNum = new PhoneNumber( "" ) ;
		fax = new PhoneNumber( "" ) ;
		webSite = "" ;
		ftp = "" ;
		selectedValue = "" ;
		selectedServiceValue = "";
		resultsSize = 0 ;
		frmEndDate = "" ;
		toEndDate = "" ;
		currentProgram.programService = new Program.Service() ;
		currentProgram = new Program() ;
		currentContact = new Contact() ;
		//searchTypeId = "" ;
		department = "" ;
		serviceEvents = new ArrayList() ;
		fundSource = "";
		fundStartDate = "";
		fundEndDate = "";
		comments = "";
		maxYouth = "";
		email = null;
		isEmailCheck = "";
		
	}

	public void clearAddress()
	{
		setMailingAddress( new ProviderAddress() ) ;
		setBillingAddress( new ProviderAddress() ) ;
		setAddressStatus( "" ) ;
	}

	public void clearProgram()
	{
		currentProgram = new Program() ;
	}

	public void clearContact()
	{
		currentContact = new Contact() ;
	}

	public void clearLists()
	{
		this.setPrograms( new ArrayList() ) ;
		this.setContacts( new ArrayList() ) ;
	}
	

	/**
	 * 
	 * @return action
	 */
	public String getActionType()
	{
		return actionType ;
	}

	/**
	 * 
	 * @return adminContactId
	 */
	public String getAdminContactId()
	{
		return adminContactId ;
	}

	public Address getBillingAddress()
	{
		return billingAddress ;
	}

	/**
	 * 
	 * @return confirmMessage
	 */
	public String getConfirmMessage()
	{
		return confirmMessage ;
	}

	/**
	 * 
	 * @return contactUserId
	 */
	public String getContactUserId()
	{
		return contactUserId ;
	}

	/**
	 * 
	 * @return currentContact
	 */
	public Contact getCurrentContact()
	{
		return currentContact ;
	}

	/**
	 * 
	 * @return currentProgram
	 */
	public Program getCurrentProgram()
	{
		return currentProgram ;
	}

	/**
	 * 
	 * @return ftp
	 */
	public String getFtp()
	{
		return ftp ;
	}

	/**
	 * 
	 * @return ifasNumber
	 */
	public String getIfasNumber()
	{
		return ifasNumber ;
	}

	/**
	 * 
	 * @return isInHouse
	 */
	public String getIsInHouse()
	{
		return isInHouse ;
	}

	/**
	 * 
	 * @return phoneNum
	 */
	public IPhoneNumber getPhoneNum()
	{
		return phoneNum ;
	}

	/**
	 * 
	 * @return fax
	 */
	public IPhoneNumber getFax()
	{
		return fax ;
	}

	public Address getMailingAddress()
	{
		return mailingAddress ;
	}

	/**
	 * 
	 * @return the providerId
	 */
	public String getProviderId()
	{
		return providerId ;
	}

	/**
	 * 
	 * @return the providerName
	 */
	public String getProviderName()
	{
		return providerName ;
	}

	/**
	 * 
	 * @return startDate
	 */
	public String getStartDate()
	{
		return startDate ;
	}

	/**
	 * 
	 * @return website
	 */
	public String getWebSite()
	{
		return webSite ;
	}

	/**
	 * 
	 * @return streetTypeList
	 */
	public Collection getStreetTypeList()
	{
		return streetTypeList ;
	}

	/**
	 * 
	 * @return stateList
	 */
	public Collection getStateList()
	{
		return stateList ;
	}

	/**
	 * 
	 * @return genericLogonIds
	 */
	public Collection getGenericLogonIds()
	{
		return genericLogonIds ;
	}

	/**
	 * 
	 * @return programs
	 */
	public Collection getPrograms()
	{
		return programs ;
	}

	/**
	 * 
	 * @return contacts
	 */
	public Collection getContacts()
	{
		return contacts ;
	}

	
	/**
	 * 
	 * @return targetInterventionList
	 */
	public Collection getTargetInterventionList()
	{
		return targetInterventionList ;
	}

	
	/**
	 * @return the programScheduleTypeList
	 */
	public Collection getProgramScheduleTypeList() {
		return programScheduleTypeList;
	}

	
	/**
	 * 
	 * @return typeList
	 */
	public Collection getTypeList()
	{
		return typeList ;
	}

	/**
	 * 
	 * @return subTypeList
	 */
	public Collection getSubTypeList()
	{
		return subTypeList ;
	}

	/**
	 * 
	 * @return stateProgramCodeList
	 */
	public Collection getStateProgramCodeList()
	{
		return stateProgramCodeList ;
	}
	public Collection getTypeProgramCodeList()
	{
	    return typeProgramCodeList;
	}
	/**
	 * 
	 * @return suffixList
	 */
	public Collection getSuffixList()
	{
		return suffixList ;
	}

	/**
	 * 
	 * @return prefixList
	 */
	public Collection getPrefixList()
	{
		return prefixList ;
	}

	/**
	 * 
	 * @return serviceTypeList
	 */
	public Collection getServiceTypeList()
	{
		return serviceTypeList ;
	}

	/**
	 * 
	 * @return costBasisList
	 */
	public Collection getCostBasisList()
	{
		return costBasisList ;
	}

	/**
	 * 
	 * @return serviceLocationNames
	 */
	public Collection getServiceLocationNames()
	{
		return serviceLocationNames ;
	}

	/**
	 * 
	 * @return selectedValue
	 */
	public String getSelectedValue()
	{
		return selectedValue ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setActionType( String string )
	{
		actionType = string ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setAdminContactId( String string )
	{
		adminContactId = string ;
	}

	/**
	 * 
	 * @param billing
	 */
	public void setBillingAddress( ProviderAddress billing )
	{
		billingAddress = billing ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setConfirmMessage( String string )
	{
		confirmMessage = string ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setContactUserId( String string )
	{
		contactUserId = string ;
	}

	/**
	 * 
	 * @param contact
	 */
	public void setCurrentContact( Contact contact )
	{
		currentContact = contact ;
	}

	/**
	 * 
	 * @param program
	 */
	public void setCurrentProgram( Program program )
	{
		currentProgram = program ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setFtp( String string )
	{
		ftp = string ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setIfasNumber( String string )
	{
		ifasNumber = string ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setIsInHouse( String string )
	{
		isInHouse = string ;
	}

	/**
	 * 
	 * @param phone
	 */
	public void setPhoneNum( IPhoneNumber phone )
	{
		phoneNum = phone ;
	}

	/**
	 * 
	 * @param phone
	 */
	public void setFax( IPhoneNumber phone )
	{
		fax = phone ;
		;
	}

	/**
	 * 
	 * @param mailing
	 */
	public void setMailingAddress( ProviderAddress mailing )
	{
		mailingAddress = mailing ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setProviderId( String string )
	{
		providerId = string ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setProviderName( String string )
	{
		providerName = string ;
	}

	/**
	 * 
	 * @param stDate
	 */
	public void setStartDate( String stDate )
	{
		startDate = stDate ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setSelectedValue( String string )
	{
		selectedValue = string ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setWebSite( String string )
	{
		webSite = string ;
	}

	/**
	 * 
	 * @param streetTypes
	 */
	public void setStreetTypeList( Collection streetTypes )
	{
		streetTypeList = streetTypes ;
	}

	/**
	 * 
	 * @param states
	 */
	public void setStateList( Collection states )
	{
		stateList = states ;
	}

	/**
	 * 
	 * @param genericLogonIds
	 */
	public void setGenericLogonIds( Collection logons )
	{
		genericLogonIds = logons ;
	}

	/**
	 * 
	 * @param progs
	 */
	public void setPrograms( Collection progs )
	{
		programs = progs ;
	}

	/**
	 * 
	 * @param genericLogonIds
	 */
	public void setContacts( Collection contactList )
	{
		contacts = contactList ;
	}

	/**
	 * 
	 * @param targetInterventions
	 */
	public void setTargetInterventionList( Collection targetInterventions )
	{
		targetInterventionList = targetInterventions ;
	}
	
	/**
	 * @param programScheduleTypeList the programScheduleTypeList to set
	 */
	public void setProgramScheduleTypeList(Collection programScheduleTypeLst) {
		programScheduleTypeList = programScheduleTypeLst;
	}

	/**
	 * 
	 * @param types
	 */
	public void setTypeList( Collection types )
	{
		typeList = types ;
	}

	/**
	 * 
	 * @param subTypes
	 */
	public void setSubTypeList( Collection subTypes )
	{
		subTypeList = subTypes ;
	}

	/**
	 * 
	 * @param stateProgCode
	 */
	public void setStateProgramCodeList( Collection stateProgCode )
	{
		stateProgramCodeList = stateProgCode ;
	}
	
	public void setTypeProgramCodeList(Collection typeProgramCodeList)
	{
	    ServiceProviderForm.typeProgramCodeList = typeProgramCodeList;
	}

	/**
	 * 
	 * @param coll
	 */
	public void setSuffixList( Collection coll )
	{
		suffixList = coll ;
	}

	/**
	 * 
	 * @param coll
	 */
	public void setPrefixList( Collection coll )
	{
		prefixList = coll ;
	}

	/**
	 * 
	 * @param coll
	 */
	public void setServiceTypeList( Collection coll )
	{
		serviceTypeList = coll ;
	}

	/**
	 * 
	 * @param coll
	 */
	public void setCostBasisList( Collection coll )
	{
		costBasisList = coll ;
	}

	/**
	 * 
	 * @param coll
	 */
	public void setServiceLocationNames( Collection coll )
	{
		serviceLocationNames = coll ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.common.form.AddressValidationForm#setAddressStatus(java.lang.String)
	 */
	public void setAddressStatus( String status )
	{
		super.setAddressStatus( status ) ;
		if( super.getCurrentAddressInd() != null )
		{
			if( super.getCurrentAddressInd().equals( "mailingAddress" ) )
			{
				this.setMailingAddrStatus( status ) ;
			}
			else if( super.getCurrentAddressInd().equals( "billingAddress" ) )
			{
				this.setBillingAddrStatus( status ) ;
			}
			else
			{
				this.setMailingAddrStatus( "U" ) ;
				this.setBillingAddrStatus( "U" ) ;
			}
		}
	}

	/*
	 * @return
	 */
	public String getMailingAddrStatus()
	{
		return mailingAddrStatus ;
	}

	public String getBillingAddrStatus()
	{
		return billingAddrStatus ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setMailingAddrStatus( String string )
	{
		mailingAddrStatus = string ;
	}

	/**
	 * 
	 * @param string
	 */
	public void setBillingAddrStatus( String string )
	{
		billingAddrStatus = string ;
	}

		/* *****************************************
		 * inner class Program, owned by Form.
		 */
		public static class Program
		{
			private String programName = "" ;
			private String targetInterventionId = "" ;
			private String targetIntervention = "" ;
			private String programCode = "" ;
			private String programId = "" ;
			private String typeId = "" ;
			private String type = "" ;
			private String subTypeId = "" ;
			private String subType = "" ;
			private String startDate = "" ;
			private String endDate = "" ;
			private String description = "" ;
			private String stateProgramCodeId = "" ;
			private String typeProgramCodeId = "" ;
			private String stateProgramCode = "" ;
			private String typeProgramCode = "" ;
			private Service programService = new Service() ;
			private String statusId = "" ;
			private String programScheduleTypeId; //added for U.S #11099
			private String programScheduleType;//added for U.S #11099
	
			// Collections
			private Collection services = new ArrayList() ;
			
			//Added for US #11376
			private String programLocationCd = "" ;
			private String programCategoryCd = "" ;
			private String programFundSourceCd = "" ;
			private String fundStartDate = "" ;
			private String fundEndDate = "" ;
			private String programLocationStr = "" ;
			private String programCategoryStr = "" ;
			private String programFundSourceStr = "" ;
			private ProgramSourceFundResponseEvent currentSourceFund = new ProgramSourceFundResponseEvent();
			
			private Collection<ProgramSourceFundResponseEvent> funds;
			private String transferredProgRef = "0";
			//task 171956
			private String programID = "" ;
			
			private String discontinueDate = "";
			private Collection<JuvenileCodeTableChildCodesResponseEvent> supervisionCategories=new ArrayList<JuvenileCodeTableChildCodesResponseEvent>();
			private String[] selectedSupervisionCategories = null ;
			private String selectedSupervisions = "" ;
			private String tjjdEdiCode = "" ;
			private String maxYouth;  //added for US 190589

			/**
			 * 
			 * @return void
			 */
			public void clearProgramService()
			{
				programService = new Service() ;
			}
	
			

			/**
			 * 
			 * @return void
			 */
			public void clearServices()
			{
				services = new ArrayList() ;
			}
	
			/**
			 * 
			 * @return endDate
			 */
			public String getEndDate()
			{
				return endDate ;
			}
	
			/**
			 * 
			 * @return programName
			 */
			public String getProgramName()
			{
				return programName ;
			}
	
			/**
			 * 
			 * @return targetIntervention
			 */
			public String getTargetIntervention()
			{
				return targetIntervention ;
			}
	
			/**
			 * 
			 * @return targetInterventioniD
			 */
			public String getTargetInterventionId()
			{
				return targetInterventionId ;
			}
			
			/**
			 * @return the programScheduleType
			 */
			public String getProgramScheduleType() {
				return programScheduleType;
			}

			/**
			 * @return the programScheduleTypeId
			 */
			public String getProgramScheduleTypeId() {
				return programScheduleTypeId;
			}
	
			/**
			 * 
			 * @return programCode
			 */
			public String getProgramCode()
			{
				return programCode ;
			}
	
			/**
			 * 
			 * @return programId
			 */
			public String getProgramId()
			{
				return programId ;
			}
	
			/**
			 * 
			 * @return programService
			 */
			public Service getProgramService()
			{
				return programService ;
			}
	
			/**
			 * 
			 * @return type
			 */
			public String getType()
			{
				return type ;
			}
	
			/**
			 * 
			 * @return typeId
			 */
			public String getTypeId()
			{
				return typeId ;
			}
	
			/**
			 * 
			 * @return subType
			 */
			public String getSubType()
			{
				return subType ;
			}
	
			/**
			 * 
			 * @return subTypeId
			 */
			public String getSubTypeId()
			{
				return subTypeId ;
			}
	
			/**
			 * 
			 * @return startDate
			 */
			public String getStartDate()
			{
				return startDate ;
			}
	
			/**
			 * 
			 * @return description
			 */
			public String getDescription()
			{
				return description ;
			}
	
			/**
			 * 
			 * @return stateProgramCode
			 */
			public String getStateProgramCode()
			{
				return stateProgramCode ;
			}
	
			/**
			 * 
			 * @return stateProgramCodeId
			 */
			public String getStateProgramCodeId()
			{
				return stateProgramCodeId ;
			}
			/**
			 * 
			 * @return stateProgramCode
			 */
			public String getTypeProgramCode()
			{
				return typeProgramCode ;
			}
	
			/**
			 * 
			 * @return stateProgramCodeId
			 */
			public String getTypeProgramCodeId()
			{
				return typeProgramCodeId ;
			}
	
			/**
			 * 
			 * @return services
			 */
			public Collection getServices()
			{
				return services ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setEndDate( String string )
			{
				endDate = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setProgramName( String string )
			{
				programName = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setTargetInterventionId( String string )
			{
				targetInterventionId = string ;
				if( notNullNotEmptyString( targetInterventionId ) )
				{
					this.targetIntervention = CodeHelper.getCodeDescription( PDCodeTableConstants.JUV_PROGRAM_GROUP, targetInterventionId ) ;
				}
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setProgramCode( String string )
			{
				programCode = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setProgramId( String string )
			{
				programId = string ;
			}
	
			/**
			 * @return the programLocationCd
			 */
			public String getProgramLocationCd() {
				return programLocationCd;
			}

			/**
			 * @param programLocationCd the programLocationCd to set
			 */
			public void setProgramLocationCd(String programLocationCd) {
				this.programLocationCd = programLocationCd;
			}

			/**
			 * @return the programCategoryCd
			 */
			public String getProgramCategoryCd() {
				return programCategoryCd;
			}

			/**
			 * @param programCategoryCd the programCategoryCd to set
			 */
			public void setProgramCategoryCd(String programCategoryCd) {
				this.programCategoryCd = programCategoryCd;
			}

			/**
			 * @return the programFundSourceCd
			 */
			public String getProgramFundSourceCd() {
				return programFundSourceCd;
			}

			/**
			 * @param programFundSourceCd the programFundSourceCd to set
			 */
			public void setProgramFundSourceCd(String programFundSourceCd) {
				this.programFundSourceCd = programFundSourceCd;
			}

			/**
			 * 
			 * @param service
			 */
			public void setProgramService( Service service )
			{
				programService = service ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setTypeId( String string )
			{
				typeId = string ;
				if( notNullNotEmptyString( typeId ) )
				{
					this.type = CodeHelper.getCodeDescription( "PROGRAM_TYPE", typeId ) ;
				}
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setSubTypeId( String string )
			{
				subTypeId = string ;
				if( notNullNotEmptyString( subTypeId ) )
				{
					this.subType = CodeHelper.getCodeDescription( "PROGRAM_SUB_TYPE", subTypeId ) ;
				}
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setStartDate( String string )
			{
				startDate = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setDescription( String string )
			{
				description = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setStateProgramCodeId( String string )
			{
				stateProgramCodeId = string ;
				if( notNullNotEmptyString( stateProgramCodeId ) )
				{
					this.stateProgramCode = CodeHelper.getCodeDescription( "STATE_PROGRAM_CODE", stateProgramCodeId ) ;
				}
			}
			/**
			 * 
			 * @param string
			 */
			public void setTypeProgramCodeId( String string )
			{
				typeProgramCodeId = string ;
				if( notNullNotEmptyString( typeProgramCodeId ) )
				{
					this.typeProgramCode = CodeHelper.getCodeDescription( "TJJD_PROGRAM_SUBTYPE", typeProgramCodeId ) ;
				}
			}
	
			/**
			 * 
			 * @param coll
			 */
			public void setServices( Collection coll )
			{
				services = coll ;
			}
			public String getProgramID()
			{
			    return programID;
			}

			public void setProgramID(String programID)
			{
			    this.programID = programID;
			}
			
			public String getDiscontinueDate(){
			    return this.discontinueDate;
			}
			
			public void setDiscontinueDate(String programDiscontinueDate){
			    this.discontinueDate = programDiscontinueDate;
			}
			
			public String getTjjdEdiCode()
			{
			    return tjjdEdiCode;
			}

			public void setTjjdEdiCode(String tjjdEdiCode)
			{
			    this.tjjdEdiCode = tjjdEdiCode;
			}
			
			public String getMaxYouth()
			{
			    return maxYouth;
			}

			public void setMaxYouth(String maxYouth)
			{
			    this.maxYouth = maxYouth;
			}

			/*
			 * inner class Service, owned by Program inner class
			 */
			public static class Service
			{
				private String serviceName = "" ;
				private String code = "" ;
				private String typeId = "" ;
				private String type = "" ;
				private String maxEnrollment = "" ;
				private String cost = "" ;
				private String costBasisId = "" ;
				private String displayCost = "" ;
				private String [ ] locationNames ;
				private String serviceId = "" ;
				private String locationDescription = "" ;
				private String locationString = "" ;
				private String statusId = "" ;
				private String description = "" ;
				private int prevLocationsSize = 0 ;
				private String updatedName = "" ;
	
				// Collection
				private Collection selectedLocations = new ArrayList() ;
				private Collection previousLocations = new ArrayList() ;
	
				public void clear()
				{
					serviceName = "" ;
					code = "" ;
					typeId = "" ;
					type = "" ;
					maxEnrollment = "" ;
					cost = "" ;
					costBasisId = "" ;
					locationNames = null ;
					serviceId = "" ;
					displayCost = "" ;
				}
	
				/**
				 * 
				 * @return serviceId
				 */
				public String getServiceId()
				{
					return serviceId ;
				}
	
				/**
				 * 
				 * @return serviceName
				 */
				public String getServiceName()
				{
					return serviceName ;
				}
	
				/**
				 * 
				 * @return code
				 */
				public String getCode()
				{
					return code ;
				}
	
				/**
				 * 
				 * @return displayCost
				 */
				public String getDisplayCost()
				{
					return displayCost ;
				}
	
				/**
				 * 
				 * @return type
				 */
				public String getType()
				{
					return type ;
				}
	
				/**
				 * 
				 * @return typeId
				 */
				public String getTypeId()
				{
					return typeId ;
				}
	
				/**
				 * 
				 * @return maxEnrollment
				 */
				public String getMaxEnrollment()
				{
					return maxEnrollment ;
				}
	
				/**
				 * 
				 * @return cost
				 */
				public String getCost()
				{
					return cost ;
				}
	
				/**
				 * 
				 * @return costBasisId
				 */
				public String getCostBasisId()
				{
					return costBasisId ;
				}
	
				/**
				 * 
				 * @return locationDescription
				 */
				public String getLocationDescription()
				{
					return locationDescription ;
				}
	
				/**
				 * @return
				 */
				public String [ ] getLocationNames()
				{
	
					return locationNames ;
				}
	
				/**
				 * @return
				 */
				public Collection getPreviousLocations()
				{
	
					return previousLocations ;
				}
	
				/**
				 * @return
				 */
				public Collection getSelectedLocations()
				{
	
					return selectedLocations ;
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setServiceId( String string )
				{
					serviceId = string ;
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setServiceName( String string )
				{
					serviceName = string ;
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setCode( String string )
				{
					code = string ;
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setDisplayCost( String string )
				{
					displayCost = string ;
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setType( String string )
				{
					type = string ;
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setTypeId( String str )
				{
					typeId = str ;
	
					if( str.length() > 0 )
					{
						Collection<ServiceTypeCdResponseEvent> coll = ServiceProviderForm.serviceTypeList ;
						if( coll != null )
						{
							for( ServiceTypeCdResponseEvent resp : coll )
							{
								if( typeId.equals( resp.getServiceTypeCode() ) )
								{
									this.type = resp.getDescription() ;
								}
							}
						} // if coll
					}
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setMaxEnrollment( String string )
				{
					maxEnrollment = string ;
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setCost( String string )
				{
					cost = string ;
				}
	
				/**
				 * 
				 * @param string
				 */
				public void setCostBasisId( String string )
				{
					costBasisId = string ;
				}
	
				/**
				 * @param strings
				 */
	
				public void setLocationNames( String [ ] strings )
				{
					locationNames = strings ;
				}
	
				/**
				 * @param coll
				 */
				public void setPreviousLocations( Collection coll )
				{
					previousLocations = coll ;
				}
	
				/**
				 * @param coll
				 */
				public void setSelectedLocations( Collection coll )
				{
					selectedLocations = coll ;
				}
	
				/**
				 * @return locationDescription
				 */
				public void setLocationDescription( Collection<LocationResponseEvent> coll )
				{
					if( locationNames != null )
					{
						Collection locations = new ArrayList() ;
						Collection selectLocation = new ArrayList() ;
						for( int i = 0; i < locationNames.length; i++ )
						{
							if( coll != null )
							{
								for( LocationResponseEvent locUnit : coll )
								{
									if( locationNames[ i ].equals( locUnit.getJuvLocationUnitId() ) )
									{
										locations.add( locUnit.getLocationUnitName() ) ;
										selectLocation.add( locUnit ) ;
									}
								}
							}
						} //for
						locationDescription = UIUtil.separateByDelim( locations, ", " ) ;
						this.setSelectedLocations( selectLocation ) ;
					}
				}
	
				/**
				 * @return
				 */
				public String getLocationString()
				{
					return locationString ;
				}
	
				/**
				 * @param string
				 */
				public void setLocationString( String string )
				{
					locationString = string ;
				}
	
				/**
				 * @return Returns the statusId.
				 */
				public String getStatusId()
				{
					return statusId ;
				}
	
				/**
				 * @param statusId
				 *          The statusId to set.
				 */
				public void setStatusId( String statusId )
				{
					this.statusId = statusId ;
				}
	
				/**
				 * @return Returns the description.
				 */
				public String getDescription()
				{
					return description ;
				}
	
				/**
				 * @param description
				 *          The description to set.
				 */
				public void setDescription( String description )
				{
					this.description = description ;
				}
	
				/**
				 * @return Returns the prevLocationsSize.
				 */
				public int getPrevLocationsSize()
				{
					return prevLocationsSize ;
				}
	
				/**
				 * @param prevLocationsSize
				 *          The prevLocationsSize to set.
				 */
				public void setPrevLocationsSize( int prevLocationsSize )
				{
					this.prevLocationsSize = prevLocationsSize ;
				}
	
				/**
				 * @return Returns the updatedName.
				 */
				public String getUpdatedName()
				{
					return updatedName ;
				}
	
				/**
				 * @param updatedName
				 *          The updatedName to set.
				 */
				public void setUpdatedName( String updatedName )
				{
					this.updatedName = updatedName ;
				}
				
			} // end Service inner class, owned by Program inner class

			
			/* Program inner class continues ...
			*/
			
			/**
			 * @return Returns the statusId.
			 */
			public String getStatusId()
			{
				return statusId ;
			}
	
			/**
			 * @param statusId
			 *          The statusId to set.
			 */
			public void setStatusId( String statusId )
			{
				this.statusId = statusId ;
			}
	
			/**
			 * @param targetIntervention
			 *          The targetIntervention to set.
			 */
			public void setTargetIntervention( String targetIntervention )
			{
				this.targetIntervention = targetIntervention ;
			}

			/*
			 * @param str
			 * @return
			 */
			private boolean notNullNotEmptyString( String str )
			{
				return( str != null && str.length() > 0 ) ; 
			}

			/**
			 * @param programScheduleTypeId the programScheduleTypeId to set
			 */
			public void setProgramScheduleTypeId(String programScheduleTypeId) {
				if( notNullNotEmptyString( programScheduleTypeId ) )
				{
					this.programScheduleType = CodeHelper.getCodeDescription( "PROGRAM_SCHEDULE_TYPE", programScheduleTypeId ) ;
				}
				this.programScheduleTypeId = programScheduleTypeId;
			}

			/**
			 * @param programScheduleType the programScheduleType to set
			 */
			public void setProgramScheduleType(String programScheduleType) {
				this.programScheduleType = programScheduleType;
			}

			public String getFundStartDate() {
				return fundStartDate;
			}

			public void setFundStartDate(String fundStartDate) {
				this.fundStartDate = fundStartDate;
			}

			public String getFundEndDate() {
				return fundEndDate;
			}

			public void setFundEndDate(String fundEndDate) {
				this.fundEndDate = fundEndDate;
			}

			public String getProgramLocationStr() {
				return programLocationStr;
			}

			public void setProgramLocationStr(String programLocationStr) {
				this.programLocationStr = programLocationStr;
			}

			public String getProgramCategoryStr() {
				return programCategoryStr;
			}

			public void setProgramCategoryStr(String programCategoryStr) {
				this.programCategoryStr = programCategoryStr;
			}

			public String getProgramFundSourceStr() {
				return programFundSourceStr;
			}

			public void setProgramFundSourceStr(String programFundSourceStr) {
				this.programFundSourceStr = programFundSourceStr;
			}

			public Collection<ProgramSourceFundResponseEvent> getFunds() {
				return funds;
			}

			public void setFunds(Collection<ProgramSourceFundResponseEvent> funds) {
				this.funds = funds;
			}

			public ProgramSourceFundResponseEvent getCurrentSourceFund() {
				return currentSourceFund;
			}

			public void setCurrentSourceFund(ProgramSourceFundResponseEvent currentSourceFund) {
				this.currentSourceFund = currentSourceFund;
			}

			public String getTransferredProgRef()
			{
			    return transferredProgRef;
			}

			public void setTransferredProgRef(String transferredProgRef)
			{
			    this.transferredProgRef = transferredProgRef;
			}

			public Collection getSupervisionCategories()
			{
			        return supervisionCategories;
			}

			public void setSupervisionCategories(Collection supervisionCategories)
			{
			     this.supervisionCategories = supervisionCategories;
			}
			
			public String[] getSelectedSupervisionCategories()
			{
			        return this.selectedSupervisionCategories;
			}

			public void setSelectedSupervisionCategories(String[] selectedSupervisionCategories)
			{
			     this.selectedSupervisionCategories = selectedSupervisionCategories;
			}
			
			public String getSelectedSupervisions()
			{
			        return this.selectedSupervisions;
			}

			public void setSelectedSupervisions(String selectedSupervisions)
			{
			     this.selectedSupervisions = selectedSupervisions;
			}
			
		} // end Program inner class

		/*
		 * Contact inner class, owned by Form.
		 */
		public static class Contact
		{
			private String logonId = "" ;
			private String employeeId = "" ;
			private String jobTitle = "" ;
			private String isAdminContact = "" ;
			private IPhoneNumber officePhone = new PhoneNumber( "" ) ;
			private IPhoneNumber fax = new PhoneNumber( "" ) ;
			private IPhoneNumber pager = new PhoneNumber( "" ) ;
			private IPhoneNumber cellPhone = new PhoneNumber( "" ) ;
			private String email = "" ;
			private String contactEmail = "";
			private String notes = "" ;
			private String contactId = "" ;
			private IName contactName = new Name() ;
			private String contactNameStr = "" ;
			private String status = "" ;
			private boolean validLogonId = false ;
			private int smUserOID=0;
	
			public int getSmUserOID()
			{
			    return smUserOID;
			}

			public void setSmUserOID(int smUserOID)
			{
			    smUserOID = smUserOID;
			}

			/**
			 * 
			 * @return contactId
			 */
			public String getContactId()
			{
				return contactId ;
			}
	
			/**
			 * 
			 * @return contactNameStr
			 */
			public String getContactNameStr()
			{
				return contactNameStr ;
			}
			
			public String getContactEmail()
			{
				return contactEmail ;
			}
	
			/**
			 * 
			 * @return employeeId
			 */
			public String getEmployeeId()
			{
				return employeeId ;
			}
	
			/**
			 * 
			 * @return isAdminContact
			 */
			public String getIsAdminContact()
			{
				return isAdminContact ;
			}
	
			/**
			 * 
			 * @return jobTitle
			 */
			public String getJobTitle()
			{
				return jobTitle ;
			}
	
			/**
			 * 
			 * @return logonId
			 */
			public String getLogonId()
			{
				return logonId ;
			}
	
			/**
			 * 
			 * @return officePhone
			 */
			public IPhoneNumber getOfficePhone()
			{
				return officePhone ;
			}
	
			/**
			 * 
			 * @return fax
			 */
			public IPhoneNumber getFax()
			{
				return fax ;
			}
	
			/**
			 * 
			 * @return pager
			 */
			public IPhoneNumber getPager()
			{
				return pager ;
			}
	
			/**
			 * 
			 * @return cellPhone
			 */
			public IPhoneNumber getCellPhone()
			{
				return cellPhone ;
			}
	
			/**
			 * 
			 * @return email
			 */
			public String getEmail()
			{
				return email ;
			}
	
			/**
			 * 
			 * @return contactName
			 */
			public IName getContactName()
			{
				return contactName ;
			}
	
			/**
			 * 
			 * @return notes
			 */
			public String getNotes()
			{
				return notes ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setContactId( String string )
			{
				contactId = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setContactNameStr( String string )
			{
				contactNameStr = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setEmployeeId( String string )
			{
				employeeId = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setIsAdminContact( String string )
			{
				isAdminContact = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setJobTitle( String string )
			{
				jobTitle = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setLogonId( String string )
			{
				logonId = string ;
			}
	
			/**
			 * 
			 * @param phone
			 */
			public void setOfficePhone( IPhoneNumber phone )
			{
				officePhone = phone ;
			}
	
			/**
			 * 
			 * @param phone
			 */
			public void setFax( IPhoneNumber phone )
			{
				fax = phone ;
			}
	
			/**
			 * 
			 * @param phone
			 */
			public void setPager( IPhoneNumber phone )
			{
				pager = phone ;
			}
	
			/**
			 * 
			 * @param phone
			 */
			public void setCellPhone( IPhoneNumber phone )
			{
				cellPhone = phone ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setEmail( String string )
			{
				email = string ;
			}
			
			public void setContactEmail( String string )
			{
				contactEmail = string ;
			}
			
			
			/**
			 * 
			 * @param string
			 */
			public void setNotes( String string )
			{
				notes = string ;
			}
	
			/**
			 * 
			 * @param string
			 */
			public void setContactName( IName name )
			{
				contactName = name ;
			}
	
			/**
			 * @return Returns the status.
			 */
			public String getStatus()
			{
				return status ;
			}
	
			/**
			 * @param status
			 *          The status to set.
			 */
			public void setStatus( String status )
			{
				this.status = status ;
			}
	
			/**
			 * @return Returns the validLogonId.
			 */
			public boolean isValidLogonId()
			{
				return validLogonId ;
			}
	
			/**
			 * @param validLogonId
			 *          The validLogonId to set.
			 */
			public void setValidLogonId( boolean validLogonId )
			{
				this.validLogonId = validLogonId ;
			}
		} // end Contact inner class

		
		/*
		 * Provider Address inner class, owned by Form
		 */
		public static class ProviderAddress extends Address implements Comparable
		{
			Address address = new Address() ;

			/*
			 * @param str
			 * @return
			 */
			private boolean nullOrEmptyString( String str )
			{
				return( str == null || str.length() < 1 ) ;
			}
			
			/*
			 * @param str
			 * @return
			 */
			private boolean notNullNotEmptyCollection( Collection coll )
			{
				return( coll != null && coll.size() > 0 ) ;
			}

			/*
			 * (non-Javadoc)
			 * @see java.lang.Comparable#compareTo(java.lang.Object)
			 */
			public int compareTo( Object obj ) throws ClassCastException
			{
				if( obj == null )
				{
					return -1 ;
				}
	
				ProviderAddress other = (ProviderAddress)obj ;
				if( getStreetNumber().equals( other.getStreetNumber() ) && 
						getStreetName().equals( other.getStreetName() ) && 
						getCity().equals( other.getCity() ) && 
						getStreetTypeId().equals( other.getStreetTypeId() ) && 
						getStateId().equals( other.getStateId() ) && 
						getZipCode().equals( other.getZipCode() ) && 
						getAdditionalZipCode().equals( other.getAdditionalZipCode() ) && 
						getAptNumber().equals( other.getAptNumber() ) )
				{
					return( 0 ) ;
				}

				return( -1 ) ;
			}
			
			/**
			 * @return
			 */
			public Address getAddress()
			{
				return address ;
			}
	
			/**
			 * @param address
			 */
			public void setAddress( Address address )
			{
				this.address = address ;
			}

			/*
			 * (non-Javadoc)
			 * @see (overrides) ui.common.Address#setStreetTypeId(java.lang.String)
			 */
			public void setStreetTypeId( String string )
			{
				super.setStreetTypeId( string ) ;
				if( nullOrEmptyString( getStreetTypeId() ) )
				{
					setStreetType( "" ) ;
					return ;
				}

				if( notNullNotEmptyCollection( streetTypeList ) )
				{
					setStreetType( CodeHelper.getCodeDescriptionByCode( streetTypeList, getStreetTypeId() ) ) ;
				}	
			}

			/*
			 * (non-Javadoc)
			 * @see (overrides) ui.common.Address#setStateId(java.lang.String)
			 */
			public void setStateId( String string )
			{
				super.setStateId( string ) ;
				if( nullOrEmptyString( getStateId() ) )
				{
					setState( "" ) ;
					return ;
				}

				if( notNullNotEmptyCollection( stateList ) )
				{
					setState( CodeHelper.getCodeDescriptionByCode( stateList, getStateId() ) ) ;
				}
			}
			
			
	
		}// end Contact inner class

	/* class ServiceProviderForm continues ...  
	*/
		
	/**
	 * @return Returns the searchTypeId.
	 */
	public String getSearchTypeId()
	{
		return searchTypeId ;
	}

	/**
	 * @param searchTypeId
	 *          The searchTypeId to set.
	 */
	public void setSearchTypeId( String searchTypeId )
	{
		this.searchTypeId = searchTypeId ;
	}

	/**
	 * @return Returns the spSearchTypes.
	 */
	public Collection getSpSearchTypes()
	{
		return spSearchTypes ;
	}

	/**
	 * @param spSearchTypes
	 *          The spSearchTypes to set.
	 */
	public void setSpSearchTypes( Collection spSearchTypes )
	{
		ServiceProviderForm.spSearchTypes = spSearchTypes ;
	}

	/**
	 * @return Returns the resultsSize.
	 */
	public int getResultsSize()
	{
		return resultsSize ;
	}

	/**
	 * @param resultsSize
	 *          The resultsSize to set.
	 */
	public void setResultsSize( int resultsSize )
	{
		this.resultsSize = resultsSize ;
	}

	/**
	 * @return Returns the searchResults.
	 */
	public Collection getSearchResults()
	{
		return searchResults ;
	}

	/**
	 * @param searchResults
	 *          The searchResults to set.
	 */
	public void setSearchResults( Collection searchResults )
	{
		this.searchResults = searchResults ;
	}

	/**
	 * @return Returns the frmEndDate.
	 */
	public String getFrmEndDate()
	{
		return frmEndDate ;
	}

	/**
	 * @param frmEndDate
	 *          The frmEndDate to set.
	 */
	public void setFrmEndDate( String frmEndDate )
	{
		this.frmEndDate = frmEndDate ;
	}

	/**
	 * @return Returns the toEndDate.
	 */
	public String getToEndDate()
	{
		return toEndDate ;
	}

	/**
	 * @param toEndDate
	 *          The toEndDate to set.
	 */
	public void setToEndDate( String toEndDate )
	{
		this.toEndDate = toEndDate ;
	}

	/**
	 * @return Returns the adminUserIDsString.
	 */
	public String getAdminUserIDsString()
	{
		return adminUserIDsString ;
	}

	/**
	 * @param adminUserIDsString
	 *          The adminUserIDsString to set.
	 */
	public void setAdminUserIDsString( String adminUserIDsString )
	{
		this.adminUserIDsString = adminUserIDsString ;
	}

	/**
	 * @return Returns the contactUserIDsString.
	 */
	public String getContactUserIDsString()
	{
		return contactUserIDsString ;
	}

	/**
	 * @param contactUserIDsString
	 *          The contactUserIDsString to set.
	 */
	public void setContactUserIDsString( String contactUserIDsString )
	{
		this.contactUserIDsString = contactUserIDsString ;
	}

	/**
	 * @return Returns the statusTypes.
	 */
	public Collection getStatusTypes()
	{
		return statusTypes ;
	}

	/**
	 * @param statusTypes The statusTypes to set.
	 */
	public void setStatusTypes( Collection statusTypes )
	{
		ServiceProviderForm.statusTypes = statusTypes ;
	}

	/**
	 * @return Returns the spTypes.
	 */
	public Collection getSpTypes()
	{
		return spTypes ;
	}

	/**
	 * @param spTypes The spTypes to set.
	 */
	public void setSpTypes( Collection spTypes )
	{
		ServiceProviderForm.spTypes = spTypes ;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId()
	{
		return statusId ;
	}

	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId( String statusId )
	{
		this.statusId = statusId ;
	}

	/**
	 * @return Returns the department.
	 */
	public String getDepartment()
	{
		return department ;
	}

	/**
	 * @param department The department to set.
	 */
	public void setDepartment( String department )
	{
		this.department = department ;
	}

	/**
	 * @return Returns the serviceEvents.
	 */
	public Collection getServiceEvents()
	{
		return serviceEvents ;
	}

	/**
	 * @param serviceEvents The serviceEvents to set.
	 */
	public void setServiceEvents( Collection serviceEvents )
	{
		this.serviceEvents = serviceEvents ;
	}

	/**
	 * @return Returns the contracts.
	 */
	public Collection getContracts()
	{
		return contracts ;
	}

	/**
	 * @param contracts The contracts to set.
	 */
	public void setContracts( Collection contracts )
	{
		this.contracts = contracts ;
	}

	public Collection getProgramLocationList() {
		return programLocationList;
	}

	public void setProgramLocationList(Collection programLocationList) {
		this.programLocationList = programLocationList;
	}

	public Collection getProgramCategoryList() {
		return programCategoryList;
	}

	public void setProgramCategoryList(Collection programCategoryList) {
		this.programCategoryList = programCategoryList;
	}

	public Collection getFundSourceList() {
		return fundSourceList;
	}

	public void setFundSourceList(Collection fundSourceList) {
		this.fundSourceList = fundSourceList;
	}

	public String getFundSource()
	{
	    return fundSource;
	}

	public void setFundSource(String fundSource)
	{
	    this.fundSource = fundSource;
	}
	
	public String getFundSourceDescription()
	{
	    return this.fundSourceDescription;
	}

	public void setFundSourceDescription(String fundSourceDescription)
	{
	    this.fundSourceDescription = fundSourceDescription;
	}

	public String getFundStartDate()
	{
	    return fundStartDate;
	}

	public void setFundStartDate(String fundStartDate)
	{
	    this.fundStartDate = fundStartDate;
	}

	public String getFundEndDate()
	{
	    return fundEndDate;
	}

	public void setFundEndDate(String fundEndDate)
	{
	    this.fundEndDate = fundEndDate;
	}

	public String getComments()
	{
	    return comments;
	}

	public void setComments(String comments)
	{
	    this.comments = comments;
	}

	public List getContactFundSourceList()
	{
	    return contactFundSourceList;
	}

	public void setContactFundSourceList(List contactFundSourceList)
	{
	    this.contactFundSourceList = contactFundSourceList;
	}

	public String getSelectedServiceValue()
	{
	    return selectedServiceValue;
	}

	public void setSelectedServiceValue(String selectedServiceValue)
	{
	    this.selectedServiceValue = selectedServiceValue;
	}
	
	
	public String getProviderNameQueryString()
	{
		return this.providerNameQueryString ;
	}

	public void setProviderNameQueryString( String providerNameQueryString )
	{
		this.providerNameQueryString = providerNameQueryString ;
	}
	
	public Date getStatusChangeDate() {
		return statusChangeDate;
	}
	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}

	public String getMaxYouth()
	{
	    return maxYouth;
	}

	public void setMaxYouth(String maxYouth)
	{
	    this.maxYouth = maxYouth;
	}
	
	public String getEmail()
	{
	    return this.email;
	}

	public void setEmail(String email)
	{
	    this.email = email;
	}

	public String getIsEmailCheck()
	{
	    return isEmailCheck;
	}

	public void setIsEmailCheck(String isEmailCheck)
	{
	    this.isEmailCheck = isEmailCheck;
	}
	

} // end ServiceProviderForm
