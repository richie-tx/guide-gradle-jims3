// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\JuvenileProgramReferral.java

package pd.supervision.programreferral;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.juvenilecase.GetJuvenileInfoEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import pd.codetable.Code;
import pd.codetable.SimpleCodeTableHelper;
import pd.contact.PDContactHelper;
import pd.juvenile.JuvenileCore;
import pd.km.util.Phone;
import pd.supervision.administerserviceprovider.ProviderProgram;
import ui.common.CodeHelper;
import ui.common.Name;

public class JuvenileProgramReferral extends PersistentObject
{
	/**
     * 
     */
    private static final long serialVersionUID = -3649486305061425331L;
	private Date			acknowledgementDate;
	private String			assignedHours;
	private float			creditHours;
	private Date			beginDate;
	private Date			endDate;
	private boolean			courtOrdered;

	private String			programOutcomeCd;
	private Code			programOutcome;
	private ProviderProgram	providerProgram;
	private String			programOutcomeSubcategoryCd;

	private String			referralStatusCd;
	private Code			referralStatus;
	private String			referralSubStatusCd;
	private Code			referralSubStatus;

	private String			provProgramId;
	private String			casefileId;
	private String			sprvsnType;

	private Date			sentDate;
	private Date			lastActionDate;
	private String			statusReason;
	private String			referralId;

	private String			juvenileId;
	//private Juvenile		juvenile;
	private JuvenileCore		juvenile;
	private String lastName;	//Juvenile name computation
	private String firstName;
	private String middleName;
	/*
	 * private String juvenileLastName; private String juvenileFirstName; private
	 * String juvenileMiddleName;
	 */
	private String			officerId;
	private String			officerLastName;
	private String			officerMiddleName;
	private String			officerFirstName;

	private String			providerProgramName;

	private String			juvServiceProviderId;
	private String			juvServiceProviderName;
	private String			phone;
	private String			extnNum;
	private boolean			inHouse;

	private String			controllingReferralNum;
	
	private Collection		referralComments	= null;
	private String			fundSource;
	private String			juvLocUnitId;
	//Guardian info
	private String guardianLastName;	//Juvenile name computation
	private String guardianFirstName;
	private String guardianMiddleName;
	private String guardianPhoneNum;
	
	

	/**
	 * Properties for theServiceEvent
	 * @referencedType pd.supervision.calendar.ServiceEvent
	 */
	private Collection theServiceEvent = null; //added by sruti
	private String providerProgramId ;

	/**
	 * @roseuid 463BA4EF024C
	 */
	public JuvenileProgramReferral()
	{
	}

	/**
	 * @return Returns the acknowledgementDate.
	 */
	public Date getAcknowledgementDate()
	{
		fetch();
		return acknowledgementDate;
	}

	/**
	 * @return Returns the assignedHours.
	 */
	public String getAssignedHours()
	{
		fetch();
		return assignedHours;
	}
	

	public float getCreditHours( )
	{
	    fetch();
	    return creditHours;
	}	

	/**
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate()
	{
		fetch();
		return beginDate;
	}

	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate()
	{
		fetch();
		return endDate;
	}

	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId()
	{
		fetch();
		return casefileId;
	}

	/**
	 * @param casefileId
	 *          The casefileId to set.
	 */
	public void setCasefileId( String casefileId )
	{
		if( this.casefileId == null || !this.casefileId.equals( casefileId ) )
		{
			markModified();
		}
		this.casefileId = casefileId;
	}

	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId()
	{
		fetch();
		return juvenileId;
	}

	/**
	 * @return Returns the juvServiceProviderId.
	 */
	public String getJuvServiceProviderId()
	{
		fetch();
		return juvServiceProviderId;
	}

	/**
	 * @return Returns the juvServiceProviderName.
	 */
	public String getJuvServiceProviderName()
	{
		fetch();
		return juvServiceProviderName;
	}

	/**
	 * @return Returns the officerFirstName.
	 */
	public String getOfficerFirstName()
	{
		fetch();
		return officerFirstName;
	}

	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId()
	{
		fetch();
		return officerId;
	}

	/**
	 * @return Returns the officerLastName.
	 */
	public String getOfficerLastName()
	{
		fetch();
		return officerLastName;
	}

	/**
	 * @return Returns the officerMiddleName.
	 */
	public String getOfficerMiddleName()
	{
		fetch();
		return officerMiddleName;
	}

	/**
	 * @return Returns the sentDate.
	 */
	public Date getSentDate()
	{
		fetch();
		return sentDate;
	}

	/**
	 * @return Returns the statusReason.
	 */
	public String getStatusReason()
	{
		fetch();
		return statusReason;
	}

	/**
	 * @return Returns the isCourtOrdered.
	 */
	public boolean isCourtOrdered()
	{
		fetch();
		return courtOrdered;
	}

	/**
	 * @param acknowledgementDate
	 *          The acknowledgementDate to set.
	 */
	public void setAcknowledgementDate( Date acknowledgementDate )
	{
		if( this.acknowledgementDate == null || 
				!this.acknowledgementDate.equals( acknowledgementDate ) )
		{
			markModified();
		}
		this.acknowledgementDate = acknowledgementDate;
	}

	/**
	 * @param assignedHours
	 *          The assignedHours to set.
	 */
	public void setAssignedHours( String assignedHours )
	{
		if( this.assignedHours == null || !this.assignedHours.equals( assignedHours ) )
		{
			markModified();
		}
		this.assignedHours = assignedHours;
	}
	
	public void setCreditHours( float creditHours )
	{
		if(this.creditHours != creditHours )
		{
			markModified();
		}
		this.creditHours = creditHours;
	}

	/**
	 * @param beginDate
	 *          The beginDate to set.
	 */
	public void setBeginDate( Date beginDate )
	{
		if( this.beginDate == null || !this.beginDate.equals( beginDate ) )
		{
			markModified();
		}
		this.beginDate = beginDate;
	}

	/**
	 * @param isCourtOrdered
	 *          The isCourtOrdered to set.
	 */
	public void setCourtOrdered( boolean courtOrdered )
	{
		if( this.courtOrdered != courtOrdered )
		{
			markModified();
		}
		this.courtOrdered = courtOrdered;
	}

	/**
	 * @param endDate
	 *          The endDate to set.
	 */
	public void setEndDate( Date endDate )
	{
		if( this.endDate == null || !this.endDate.equals( endDate ) )
		{
			markModified();
		}
		this.endDate = endDate;
	}

	/**
	 * @param sentDate
	 *          The sentDate to set.
	 */
	public void setSentDate( Date sentDate )
	{
		if( this.sentDate == null || !this.sentDate.equals( endDate ) )
		{
			markModified();
		}
		this.sentDate = sentDate;
	}

	/**
	 * @param statusReason
	 *          The statusReason to set.
	 */
	public void setStatusReason( String statusReason )
	{
		if( this.statusReason == null || !this.statusReason.equals( statusReason ) )
		{
			markModified();
		}
		this.statusReason = statusReason;
	}

	/**
	 * @return java.util.Iterator
	 * @param event
	 * @roseuid 4177C29D03A9
	 */
	static public Iterator findAll( IEvent event )
	{
		IHome home = new Home();
		return (Iterator)home.findAll( event, JuvenileProgramReferral.class );
	}

	/**
	 * @return Returns the provProgramId.
	 */
	public String getProvProgramId()
	{
		fetch();
		return provProgramId;
	}

	/**
	 * @param provProgramId
	 *          The provProgramId to set.
	 */
	public void setProvProgramId( String provProgramId )
	{
		if( this.provProgramId == null || !this.provProgramId.equals( provProgramId ) )
		{
			markModified();
		}
		this.provProgramId = provProgramId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initProgramOutcome()
	{
		if( programOutcome == null )
		{
			programOutcome = (Code)new mojo.km.persistence.Reference(
					programOutcomeCd, Code.class, "PROGRAM_REFERRAL_OUTCOME" ).getObject();
		}
	}

	/**
	 * @return Returns the programOutcome.
	 */
	public Code getProgramOutcome()
	{
		initProgramOutcome();
		return programOutcome;
	}

	/**
	 * @return Returns the programOutcomeCd.
	 */
	public String getProgramOutcomeCd()
	{
		fetch();
		return programOutcomeCd;
	}

	/**
	 * @param programOutcomeCd
	 *          The programOutcomeCd to set.
	 */
	public void setProgramOutcomeCd( String programOutcomeCd )
	{
		if( this.programOutcomeCd == null || !this.programOutcomeCd.equals( programOutcomeCd ) )
		{
			markModified();
		}
		this.programOutcomeCd = programOutcomeCd;
	}

	/**
	 * @return the programOutcomeSubcategoryCd
	 */
	public String getProgramOutcomeSubcategoryCd() 
	{
		fetch();
		return programOutcomeSubcategoryCd;
	}

	/**
	 * @param programOutcomeSubcategoryCd
	 *          The programOutcomeSubcategoryCd to set.
	 */
	public void setProgramOutcomeSubcategoryCd( String programOutcomeSubcategoryCd )
	{
		if( this.programOutcomeSubcategoryCd == null || !this.programOutcomeSubcategoryCd.equals( programOutcomeSubcategoryCd ) )
		{
			markModified();
		}
		this.programOutcomeSubcategoryCd = programOutcomeSubcategoryCd;
	}	
	
	/**
	 * @return Returns the referralStatusCd.
	 */
	public String getReferralStatusCd()
	{
		fetch();
		return referralStatusCd;
	}

	/**
	 * @param referralStatusCd
	 *          The referralStatusCd to set.
	 */
	public void setReferralStatusCd( String referralStatusCd )
	{
		if( this.referralStatusCd == null || 
				!this.referralStatusCd.equals( referralStatusCd ) )
		{
			markModified();
		}
		this.referralStatusCd = referralStatusCd;
	}
	public String getFundSource()
	{
	    fetch();
	    return fundSource;
	}

	public void setFundSource(String fundSource)
	{
	    if( this.fundSource == null || 
			!this.fundSource.equals( fundSource ) )
	{
		markModified();
	}
	    this.fundSource = fundSource;
	}

	/**
	 * @return Returns the referralSubStatusCd.
	 */
	public String getReferralSubStatusCd()
	{
		fetch();
		return referralSubStatusCd;
	}

	/**
	 * @param referralSubStatusCd
	 *          The referralSubStatusCd to set.
	 */
	public void setReferralSubStatusCd( String referralSubStatusCd )
	{
		if( this.referralSubStatusCd == null || 
				!this.referralSubStatusCd.equals( referralSubStatusCd ) )
		{
			markModified();
		}
		this.referralSubStatusCd = referralSubStatusCd;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initJuvenile()
	{
		if( juvenile == null )
		{
			// juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenileId,
			// pd.juvenile.Juvenile.class).getObject();
		    	// Profile stripping fix 97547
			//juvenile = Juvenile.find( juvenileId );		    	
		    	juvenile = JuvenileCore.findCore( juvenileId );	
		}
	}

	/**
	 * @return Returns the juvenile
	 */
	//public Juvenile getJuvenile()
	public JuvenileCore getJuvenile()
	{
		initJuvenile();
		return juvenile;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initReferralStatus()
	{
		if( referralStatus == null )
		{
			referralStatus = (Code)new mojo.km.persistence.Reference(
					referralStatusCd, Code.class, "PROGRAM_REFERRAL_STATUS" ).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initReferralSubStatus()
	{
		if( referralSubStatus == null )
		{
			referralSubStatus = (Code)new mojo.km.persistence.Reference(
					referralSubStatusCd, Code.class, "PROGRAM_REFERRAL_SUBSTATUS" ).getObject();
		}
	}

	/**
	 * @return Returns the referralStatus.
	 */
	public Code getReferralStatus()
	{
		initReferralStatus();
		return referralStatus;
	}

	/**
	 * @return Returns the referralSubStatus.
	 */
	public Code getReferralSubStatus()
	{
		initReferralSubStatus();
		return referralSubStatus;
	}

	/**
	 * @return Returns the providerProgramName.
	 */
	public String getProviderProgramName()
	{
		fetch();
		return providerProgramName;
	}

	public void initProviderProgram()
	{
		if( providerProgram == null )
		{
			providerProgram = (ProviderProgram)new mojo.km.persistence.Reference( 
					"" + provProgramId, ProviderProgram.class ).getObject();
		}
	}

	/**
	 * @return Returns the providerProgram.
	 */
	public ProviderProgram getProviderProgram()
	{
		initProviderProgram();
		return providerProgram;
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.supervision.administerserviceprovider.ProviderProgram
	 */
	private void initReferralComments()
	{
		if( referralComments == null )
		{
			if( this.getOID() == null )
			{
				new mojo.km.persistence.Home().bind( this );
			}
			referralComments = (Collection)new mojo.km.persistence.ArrayList( 
					JuvenileProgramReferralComment.class, "programReferralId", "" + getOID() );
		}
	}

	/**
	 * @return Returns the referralComments.
	 */
	public Collection getReferralComments()
	{
		initReferralComments();
		return referralComments;
	}

	/**
	 * @param referralComments
	 *          The referralComments to set.
	 */
	public void setReferralComments( List referralComments )
	{
		this.referralComments = referralComments;
	}

	/**
	 * @param providerProgram
	 *          The providerProgram to set.
	 */
	public void setProviderProgram( ProviderProgram providerProgram )
	{
		this.providerProgram = providerProgram;
	}

	public ProgramReferralResponseEvent getValueObject( boolean detailNeeded )
	{
		ProgramReferralResponseEvent resp = new ProgramReferralResponseEvent();

		resp.setReferralId(this.getOID()) ;
		resp.setAssignedHours( this.getAssignedHours() );
		resp.setCreditHours(this.getCreditHours());
		resp.setBeginDate( this.getBeginDate() );
		resp.setEndDate( this.getEndDate() );
		resp.setSentDate( this.getSentDate() );
		resp.setAcknowledgementDate( this.getAcknowledgementDate() );
		resp.setLastActionDate( this.getLastActionDate() );
		resp.setCourtOrdered( this.isCourtOrdered() );

		resp.setCasefileId( this.getCasefileId() );
		
		String val = this.getJuvenileId() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setJuvenileId( val );
			//// Profile stripping fix 97547
			//Juvenile juv = this.getJuvenile();
			JuvenileCore juv = this.getJuvenile();
			//
			if( juv != null )
			{
				resp.setJuvenileFirstName( juv.getFirstName() );
				resp.setJuvenileMiddleName( juv.getMiddleName() );
				resp.setJuvenileLastName( juv.getLastName() );
			}
		}

		resp.setOfficerId( this.getOfficerId() );
		resp.setOfficerFirstName( this.getOfficerFirstName() );
		resp.setOfficerMiddleName( this.getOfficerMiddleName() );
		resp.setOfficerLastName( this.getOfficerLastName() );

		resp.setProvProgramId( this.getProvProgramId() );
		resp.setProviderProgramName( this.getProviderProgramName() );

		resp.setJuvServiceProviderId( this.getJuvServiceProviderId() );
		resp.setJuvServiceProviderName( this.getJuvServiceProviderName() );
		resp.setPhone( this.getPhone() );
		resp.setExtnNum( this.getExtnNum() );
		resp.setInHouse( this.isInHouse() );
		resp.setFundSource(this.getFundSource());

		val = this.getProgramOutcomeCd() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setOutComeCd( val );
			String outcomeDescription = SimpleCodeTableHelper.getDescrByCode("PROGRAM_REFERRAL_OUTCOME", val);
			if(outcomeDescription != null &&  (outcomeDescription.length() > 0)){
				resp.setOutComeDesc(outcomeDescription);
			}
		} else {
		    resp.setOutComeCd("");
		    resp.setOutComeDesc("");
		}

		val = this.getProgramOutcomeSubcategoryCd();
		if( val != null &&  (val.length() > 0) )
		{
			resp.setOutComeSubcategoryCd(val);
		} else {
		    resp.setOutComeSubcategoryCd("");
		}
		
		val = this.getReferralStatusCd() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setReferralStatusCd( val );
			resp.setReferralStatusDescription( this.getReferralStatus().getDescription() );
		} else {
		    resp.setReferralStatusCd("");
		    resp.setReferralStatusDescription("");
		}

		val = this.getReferralSubStatusCd() ;
		if( val != null && (val.length() > 0) )
		{
			resp.setReferralSubStatusCd( val );
			resp.setReferralSubStatusDescription( this.getReferralSubStatus().getDescription() );
		} else {
		    resp.setReferralSubStatusCd("");
		    resp.setReferralSubStatusDescription("");
		}

		if( detailNeeded  &&  this.getReferralComments() != null )
		{
			JuvenileProgramReferralComment refComment = null ;
			ProgramReferralCommentResponseEvent commentResp = null ;
			List commentsList = new ArrayList();

			Iterator<JuvenileProgramReferralComment> iter = this.getReferralComments().iterator();
			while(iter.hasNext())
			{
				refComment = iter.next();
				commentResp = refComment.getValue();
				commentsList.add( commentResp );
			}
			if( commentsList.size() > 1 )
			{
				Collections.sort( commentsList );
			}
			resp.setReferralComments( commentsList );
		}

		String userLogonId = this.getCreateUserID();
		if (userLogonId != null) {
			String fullName = PDContactHelper.getUserProfileName(userLogonId);
			resp.setCreatedBy(fullName);
		}
		return resp;
	}
	
	public ProgramReferralResponseEvent getMinValueObject()
	{
		ProgramReferralResponseEvent resp = new ProgramReferralResponseEvent();

		resp.setJuvProgRefId(this.getOID());
		resp.setReferralId(this.getOID()) ; // Used for hyperlink
		resp.setAssignedHours( this.getAssignedHours() );
		resp.setCreditHours(this.getCreditHours());
		resp.setBeginDate( this.getBeginDate() );
		
		Calendar cal = Calendar.getInstance();
		int daysBetween = calculateDaysBetween(this.getBeginDate(),cal.getTime());
				
		resp.setTimeInProgram(daysBetween);
		resp.setEndDate( this.getEndDate() );
		resp.setSentDate( this.getSentDate() );
		resp.setAcknowledgementDate( this.getAcknowledgementDate() );
		resp.setLastActionDate( this.getLastActionDate() );
		resp.setCourtOrdered( this.isCourtOrdered() );

		resp.setCasefileId( this.getCasefileId() );
		resp.setJuvenileId(this.getJuvenileId());
		
		GetJuvenileInfoEvent getJuvenile = new GetJuvenileInfoEvent();
		getJuvenile.setJuvenileNum( this.getJuvenileId() );
		
		Name juvenileFullName = new Name();
		juvenileFullName.setFirstName(this.getFirstName());
		juvenileFullName.setMiddleName(this.getMiddleName());
		juvenileFullName.setLastName(this.getLastName());
		resp.setJuvenileFullName(juvenileFullName.getFormattedName());
		
		resp.setOfficerId( this.getOfficerId() );
		resp.setOfficerFirstName( this.getOfficerFirstName() );
		resp.setOfficerMiddleName( this.getOfficerMiddleName() );
		resp.setOfficerLastName( this.getOfficerLastName() );
		
		resp.getFormattedOfficerName().setFirstName(this.getOfficerFirstName());
		resp.getFormattedOfficerName().setMiddleName(this.getOfficerMiddleName());
		resp.getFormattedOfficerName().setLastName(this.getOfficerLastName());
		
		Name guardianFullName = new Name();
		guardianFullName.setFirstName(this.getGuardianFirstName());
		guardianFullName.setMiddleName(this.getGuardianMiddleName());
		guardianFullName.setLastName(this.getGuardianLastName());
		resp.setContactName(guardianFullName.getFormattedName());
		Phone phoneNumber = new Phone( this.getGuardianPhoneNum() );
		if( phoneNumber != null ){
		    resp.setContactPhone( phoneNumber.getFormattedPhoneNumber() );
		}

		resp.setProvProgramId( this.getProvProgramId() );
		resp.setProviderProgramName( this.getProviderProgramName() );

		resp.setJuvServiceProviderId( this.getJuvServiceProviderId() );
		resp.setJuvServiceProviderName( this.getJuvServiceProviderName() );
		resp.setPhone( this.getPhone() );
		resp.setExtnNum( this.getExtnNum() );
		resp.setInHouse( this.isInHouse() );
		resp.setFundSource(this.getFundSource());
		resp.setJuvLocUnitId(this.getJuvLocUnitId());
		
		String val = "";
		val = this.getProgramOutcomeCd() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setOutComeCd( val );
			String outcomeDescription = SimpleCodeTableHelper.getDescrByCode("PROGRAM_REFERRAL_OUTCOME", val);
			if(outcomeDescription != null &&  (outcomeDescription.length() > 0)){
				resp.setOutComeDesc(outcomeDescription);
			}
		} else {
		    resp.setOutComeCd("");
		    resp.setOutComeDesc("");
		}

		val = this.getProgramOutcomeSubcategoryCd();
		if( val != null &&  (val.length() > 0) )
		{
			resp.setOutComeSubcategoryCd(val);
		} else {
		    resp.setOutComeSubcategoryCd("");
		}
		
		val = this.getReferralStatusCd() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setReferralStatusCd( val );
			resp.setReferralStatusDescription( this.getReferralStatus().getDescription() );
		} else {
		    resp.setReferralStatusCd("");
		    resp.setReferralStatusDescription("");
		}

		val = this.getReferralSubStatusCd() ;
		if( val != null && (val.length() > 0) )
		{
			resp.setReferralSubStatusCd( val );
			resp.setReferralSubStatusDescription( this.getReferralSubStatus().getDescription() );
		} else {
		    resp.setReferralSubStatusCd("");
		    resp.setReferralSubStatusDescription("");
		}

		return resp;
	}
	
	public static int calculateDaysBetween(Date d1, Date d2){ 
	    
	    return Days.daysBetween( new LocalDate(d1.getTime()), new LocalDate(d2.getTime())).getDays(); 
	    
	}

	

	/**
	 * @return JuvenileProgramReferral
	 * @param programReferralId
	 * @roseuid 4177C29D03A9
	 */
	static public JuvenileProgramReferral find( String oid )
	{
		IHome home = new Home();
		return (JuvenileProgramReferral)home.find( oid, JuvenileProgramReferral.class );
	}

	/**
	 * @param juvenileId
	 *          The juvenileId to set.
	 */
	public void setJuvenileId( String juvenileId )
	{
		if( this.juvenileId == null || !this.juvenileId.equals( juvenileId ) )
		{
			markModified();
		}
		this.juvenileId = juvenileId;
	}

	/**
	 * @param juvServiceProviderId
	 *          The juvServiceProviderId to set.
	 */
	public void setJuvServiceProviderId( String juvServiceProviderId )
	{
		if( this.juvServiceProviderId == null || 
				!this.juvServiceProviderId.equals( juvServiceProviderId ) )
		{
			markModified();
		}
		this.juvServiceProviderId = juvServiceProviderId;
	}

	/**
	 * @param juvServiceProviderName
	 *          The juvServiceProviderName to set.
	 */
	public void setJuvServiceProviderName( String juvServiceProviderName )
	{
		if( this.juvServiceProviderName == null || 
				!this.juvServiceProviderName.equals( juvServiceProviderName ) )
		{
			markModified();
		}
		this.juvServiceProviderName = juvServiceProviderName;
	}

	/**
	 * @param officerFirstName
	 *          The officerFirstName to set.
	 */
	public void setOfficerFirstName( String officerFirstName )
	{
		if( this.officerFirstName == null || 
				!this.officerFirstName.equals( officerFirstName ) )
		{
			markModified();
		}
		this.officerFirstName = officerFirstName;
	}

	/**
	 * @param officerId
	 *          The officerId to set.
	 */
	public void setOfficerId( String officerId )
	{
		if( this.officerId == null || !this.officerId.equals( officerId ) )
		{
			markModified();
		}
		this.officerId = officerId;
	}

	/**
	 * @param officerLastName
	 *          The officerLastName to set.
	 */
	public void setOfficerLastName( String officerLastName )
	{
		if( this.officerLastName == null || 
				!this.officerLastName.equals( officerLastName ) )
		{
			markModified();
		}
		this.officerLastName = officerLastName;
	}

	/**
	 * @param officerMiddleName
	 *          The officerMiddleName to set.
	 */
	public void setOfficerMiddleName( String officerMiddleName )
	{
		if( this.officerMiddleName == null || 
				!this.officerMiddleName.equals( officerMiddleName ) )
		{
			markModified();
		}
		this.officerMiddleName = officerMiddleName;
	}

	/**
	 * @param providerProgramName
	 *          The providerProgramName to set.
	 */
	public void setProviderProgramName( String providerProgramName )
	{
		if( this.providerProgramName == null || 
				!this.providerProgramName.equals( providerProgramName ) )
		{
			markModified();
		}
		this.providerProgramName = providerProgramName;
	}

	/**
	 * @return Returns the lastActionDate.
	 */
	public Date getLastActionDate()
	{
		fetch();
		return lastActionDate;
	}

	/**
	 * @param lastActionDate
	 *          The lastActionDate to set.
	 */
	public void setLastActionDate( Date lastActionDate )
	{
		if( this.lastActionDate == null || !this.lastActionDate.equals( endDate ) )
		{
			markModified();
		}
		this.lastActionDate = lastActionDate;
	}

	/**
	 * @return Returns the referralId.
	 */
	public String getReferralId()
	{
		fetch();
		return referralId;
	}

	/**
	 * @param referralId
	 *          The referralId to set.
	 */
	public void setReferralId( String referralId )
	{
		if( this.referralId == null || !this.referralId.equals( referralId ) )
		{
			markModified();
		}
		this.referralId = referralId;
	}

	/**
	 * @return Returns the extnNum.
	 */
	public String getExtnNum()
	{
		fetch();
		return extnNum;
	}

	/**
	 * @param extnNum
	 *          The extnNum to set.
	 */
	public void setExtnNum( String extnNum )
	{
		if( this.extnNum == null || !this.extnNum.equals( extnNum ) )
		{
			markModified();
		}
		this.extnNum = extnNum;
	}

	/**
	 * @return Returns the inHouse.
	 */
	public boolean isInHouse()
	{
		fetch();
		return inHouse;
	}

	/**
	 * @param inHouse
	 *          The inHouse to set.
	 */
	public void setInHouse( boolean aInHouse )
	{
		if( this.inHouse != aInHouse )
		{
			markModified();
		}
		inHouse = aInHouse;
	}

	/**
	 * @return Returns the phone.
	 */
	public String getPhone()
	{
		fetch();
		return phone;
	}

	/**
	 * @param phone
	 *          The phone to set.
	 */
	public void setPhone( String phone )
	{
		if( this.phone == null || !this.phone.equals( phone ) )
		{
			markModified();
		}
		this.phone = phone;
	}

	/**
	 * @return the controllingReferralNum
	 */
	public String getControllingReferralNum() {
		fetch();
		return controllingReferralNum;
	}

	/**
	 * @param controllingReferralNum the aControllingReferralNum to set
	 */
	public void setControllingReferralNum(String aControllingReferralNum) {
		if( this.controllingReferralNum == null || !this.controllingReferralNum.equals(aControllingReferralNum))
		{
			markModified();
		}
		this.controllingReferralNum = aControllingReferralNum;
	}
	
	public String getJuvLocUnitId()
	{
	    fetch();
	    return juvLocUnitId;
	}

	public void setJuvLocUnitId(String juvLocUnitId)
	{
	    this.juvLocUnitId = juvLocUnitId;
	}

	public String getLastName()
	{
	    fetch();
	    return lastName;
	}

	public void setLastName(String lastName)
	{
	    this.lastName = lastName;
	}

	public String getFirstName()
	{
	    fetch();
	    return firstName;
	}

	public void setFirstName(String firstName)
	{
	    this.firstName = firstName;
	}

	public String getMiddleName()
	{
	    fetch();
	    return middleName;
	}

	public void setMiddleName(String middleName)
	{
	    this.middleName = middleName;
	}
	public String getSprvsnType()
	{
	    fetch();
	    return sprvsnType;
	}

	public void setSprvsnType(String sprvsnType)
	{
	    this.sprvsnType = sprvsnType;
	}

	public String getGuardianLastName()
	{
	    fetch();
	    return guardianLastName;
	}

	public void setGuardianLastName(String guardianLastName)
	{
	    this.guardianLastName = guardianLastName;
	}	

	public String getGuardianFirstName()
	{
	    fetch();
	    return guardianFirstName;
	}

	public void setGuardianFirstName(String guardianFirstName)
	{
	    this.guardianFirstName = guardianFirstName;
	}

	public String getGuardianMiddleName()
	{
	    fetch();
	    return guardianMiddleName;
	}

	public void setGuardianMiddleName(String guardianMiddleName)
	{
	    this.guardianMiddleName = guardianMiddleName;
	}

	public String getGuardianPhoneNum()
	{
	    fetch();
	    return guardianPhoneNum;
	}

	public void setGuardianPhoneNum(String guardianPhoneNum)
	{
	    this.guardianPhoneNum = guardianPhoneNum;
	}

	/*
	 * 
	 */
	static public Iterator findAll( String attrName, String attrValue )
	{
		Iterator juvenileProgramReferral = null;
		IHome home = new Home();
		juvenileProgramReferral = home.findAll( attrName, attrValue, JuvenileProgramReferral.class );
		
		return juvenileProgramReferral;
	}

	// Profile stripping fix   97547
	//public Juvenile postValueObject(boolean detailNeeded, Juvenile juvenile) {
	public JuvenileCore postValueObject(boolean detailNeeded, JuvenileCore juvenile) {
		ProgramReferralResponseEvent resp = new ProgramReferralResponseEvent();

		resp.setReferralId(this.getOID()) ;
		resp.setAssignedHours( this.getAssignedHours() );
		resp.setCreditHours(this.getCreditHours());
		resp.setBeginDate( this.getBeginDate() );
		resp.setEndDate( this.getEndDate() );
		resp.setSentDate( this.getSentDate() );
		resp.setAcknowledgementDate( this.getAcknowledgementDate() );
		resp.setLastActionDate( this.getLastActionDate() );
		resp.setCourtOrdered( this.isCourtOrdered() );

		resp.setCasefileId( this.getCasefileId() );
		if( this.getSprvsnType() != null &&  (this.getSprvsnType().length() > 0) )
		{
		    resp.setSupervisionType(this.getSprvsnType());
		    resp.setSupervisionName(CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE , this.getSprvsnType()) );
		}
		String val = this.getJuvenileId() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setJuvenileId( val );
			if(juvenile == null){
			    //juvenile = this.getJuvenile();
			    juvenile = this.getJuvenile();
			}
			if (juvenile != null) {
				resp.setJuvenileFirstName( juvenile.getFirstName() );
				resp.setJuvenileMiddleName( juvenile.getMiddleName() );
				resp.setJuvenileLastName( juvenile.getLastName() );
			}
		}

		resp.setOfficerId( this.getOfficerId() );
		resp.setOfficerFirstName( this.getOfficerFirstName() );
		resp.setOfficerMiddleName( this.getOfficerMiddleName() );
		resp.setOfficerLastName( this.getOfficerLastName() );
		resp.getFormattedOfficerName().setFirstName(this.getOfficerFirstName());
		resp.getFormattedOfficerName().setMiddleName(this.getOfficerMiddleName());
		resp.getFormattedOfficerName().setLastName(this.getOfficerLastName());

		resp.setProvProgramId( this.getProvProgramId() );
		resp.setProviderProgramName( this.getProviderProgramName() );

		resp.setJuvServiceProviderId( this.getJuvServiceProviderId() );
		resp.setJuvServiceProviderName( this.getJuvServiceProviderName() );
		resp.setPhone( this.getPhone() );
		resp.setExtnNum( this.getExtnNum() );
		resp.setInHouse( this.isInHouse() );

		val = this.getProgramOutcomeCd() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setOutComeCd( val );
		}

		val = this.getProgramOutcomeSubcategoryCd() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setOutComeSubcategoryCd( val );
		} 
		
		val = this.getReferralStatusCd() ;
		if( val != null &&  (val.length() > 0) )
		{
			resp.setReferralStatusCd( val );
			resp.setReferralStatusDescription( this.getReferralStatus().getDescription() );
		}

		val = this.getReferralSubStatusCd() ;
		if( val != null && (val.length() > 0) )
		{
			resp.setReferralSubStatusCd( val );
			resp.setReferralSubStatusDescription( this.getReferralSubStatus().getDescription() );
		}

		if( detailNeeded  &&  this.getReferralComments() != null )
		{
			JuvenileProgramReferralComment refComment = null ;
			ProgramReferralCommentResponseEvent commentResp = null ;
			List commentsList = new ArrayList();

			Iterator<JuvenileProgramReferralComment> iter = this.getReferralComments().iterator();
			while(iter.hasNext())
			{
				refComment = iter.next();
				commentResp = refComment.getValue();
				commentsList.add( commentResp );
			}
			if( commentsList.size() > 1 )
			{
				Collections.sort( commentsList );
			}
			resp.setReferralComments( commentsList );
		}
		MessageUtil.postReply(resp);
		return juvenile;
	}
	
	
	
	/**
	 * Initialize class relationship implementation for pd.supervision.calendar.ServiceEvent
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initTheServiceEvent()
	{
		if (theServiceEvent == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			
			try {
				theServiceEvent = new mojo.km.persistence.ArrayList(pd.supervision.calendar.ServiceEvent.class, "programReferralId", ""
					+ getOID());
			}
			catch(Throwable t) {
				theServiceEvent = new ArrayList();
			}
		}
		
	}
	
	/**
	 * returns a collection of pd.supervision.calendar.ServiceEvent
	 * @methodInvocation fetch
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation fetch
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation fetch
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation fetch
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation initTheServiceEvent
	 */
	public Collection getTheServiceEvent()
	{
		fetch();
		initTheServiceEvent();
		return theServiceEvent;
	}

	/**
	 * insert a pd.supervision.calendar.ServiceEvent into class relationship collection.
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation add
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation add
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation add
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation add
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation add
	 */
	public void insertTheServiceEvent(pd.supervision.calendar.ServiceEvent anObject)
	{
		initTheServiceEvent();
		theServiceEvent.add(anObject);
	}
	
	
	/**
	 * Removes a pd.supervision.calendar.ServiceEvent from class relationship collection.
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation remove
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation remove
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation remove
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation remove
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation remove
	 */
	public void removeTheServiceEvent(pd.supervision.calendar.ServiceEvent anObject)
	{
		initTheServiceEvent();
		theServiceEvent.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.calendar.ServiceEvent from class relationship collection.
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation clear
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation clear
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation clear
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation clear
	 * @methodInvocation initTheServiceEvent
	 * @methodInvocation clear
	 */
	public void clearTheServiceEvent()
	{
		initTheServiceEvent();
		theServiceEvent.clear();
	}

}
