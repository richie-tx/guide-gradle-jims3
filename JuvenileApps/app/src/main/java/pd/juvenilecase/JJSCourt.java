package pd.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;
import pd.codetable.Code;
import pd.juvenile.JuvenileCore;

/**
 * //Added for U.S #11645
 * 
 * @author sthyagarajan mIGRATED FROM M204. THIS PD NOW POINTS TO
 *         JIMS2.JJSCLCOURT IN SQL
 */
public class JJSCourt extends PersistentObject
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String sortOrder;
    private Date amendmentDate;
    private String attorneyName;
    private String attorneyConnection;
    private String atyConfirmation; 

    private String barNumber;
    private String chainNumber;
    private String comments;
    private Date courtDate;
    private Date courtDateWithTime;
    private String courtId;
    private String courtTime;
    private Date filingDate;
    private String hearingCategory;
    private String hearingDisposition;
    private String hearingDispositionDesc;
    private String hearingResult;
    private String hearingResultDesc;
    private String hearingType;
    private String hearingTypeDesc;
    private String galBarNumber;
    private String galName;

    // Properties for hearingTypes
    private Code hearingTypes = null;
    private String issueFlag;
    private String juryFlag;
    //private Juvenile juvenile;
    private JuvenileCore juvenile;
    private String juvenileFirstName;
    private String juvenileLastName;
    private String juvenileMiddleName;
    private String juvenileNumber;
    private Date lcDate;
    private Date lcTime;
    private String lcUser;
    private String optionFlag;
    private String petitionAllegation;
    private String petitionAllegationDesc;
    private String petitionAmendment;
    private String petitionNumber;
    private String petitionStatus;
    private String prevNotes;
    private String referralNumber;
    private String resetHearingType;
    private String seqNumber;
    private String updateFlag;
    private int courtSeqNumber;
    private int courtChainNumber;
    private String tjpcSeqNumber;
    private String transferTo;

    //added for Facility
    private String rectype;
    //task 147422
    private String attorney2BarNum;   
    private String attorney2Name; 
    private String attorney2Connection;
    private String appAttorney;
    //  task 168662
    private String interpreter;
    // task 186479
    private String tjpcDisp;
    //task 187260
    private Date resetDate;

    

    //
    /**
     * @roseuid 43BA9F090142
     */
    public JJSCourt()
    {
    }

    /**
     * @return
     * @param event
     */
    static public Iterator<JJSCourt> findAll(IEvent event)
    {
	IHome home = new Home();
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (grantedFeature)
	    return home.findAll(event, JJSCourt.class);
	else
	    return filterSealedPurged(home.findAll(event, JJSCourt.class));
    }

    /**
     * @param attributeName
     * @param attributeValue
     * @return Iterator
     */
    public static Iterator<JJSCourt> findAll(String attributeName, String attributeValue)
    {

	IHome home = new Home();
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (grantedFeature)
	    return home.findAll(attributeName, attributeValue, JJSCourt.class);
	else
	    return filterSealedPurged(home.findAll(attributeName, attributeValue, JJSCourt.class));
    }

    private static Iterator filterSealedPurged(Iterator iter)
    {
	ArrayList<JJSCourt> courtDocs = new ArrayList<JJSCourt>();
	while (iter.hasNext())
	{
	    //my view contains both court records and detention
	    JJSCourt det = (JJSCourt) iter.next();
	    if ( det != null && ("COURT".equalsIgnoreCase(det.getRectype()) ||"DETENTION".equalsIgnoreCase(det.getRectype()))){
		courtDocs.add(det);
	    }
	}
	return courtDocs.iterator();
    }

    public String getSortOrder()
    {

	fetch();
	return sortOrder;
    }

    public void setSortOrder(String sortOder)
    {
	this.sortOrder = sortOder;
    }

    /**
     * @return
     */
    public Date getAmendmentDate()
    {
	fetch();
	return amendmentDate;
    }

    /**
     * @return
     */
    public String getAttorneyName()
    {
	fetch();
	return attorneyName;
    }

    /**
     * @return
     */
    public String getBarNumber()
    {
	fetch();
	return barNumber;
    }

    /**
     * @return
     */
    public String getChainNumber()
    {
	fetch();
	return chainNumber;
    }

    /**
     * @return
     */
    public String getComments()
    {
	fetch();
	return comments;
    }

    /**
     * @return
     */
    public Date getCourtDate()
    {
	fetch();
	return courtDate;
    }

    /**
     * @return
     */
    public String getCourtId()
    {
	fetch();
	return courtId;
    }

    /**
     * @return
     */
    public String getCourtTime()
    {
	fetch();
	return courtTime;
    }

    /**
     * @return
     */
    public Date getFilingDate()
    {
	fetch();
	return filingDate;
    }

    /**
     * @return
     */
    public String getHearingCategory()
    {
	fetch();
	return hearingCategory;
    }

    /**
     * @return
     */
    public String getHearingDisposition()
    {
	fetch();
	return hearingDisposition;
    }

    /**
     * @return Returns the hearingDispositionDesc.
     */
    public String getHearingDispositionDesc()
    {
	return hearingDispositionDesc;
    }

    /**
     * @return
     */
    public String getHearingResult()
    {
	fetch();
	return hearingResult;
    }

    /**
     * @return Returns the hearingResultDesc.
     */
    public String getHearingResultDesc()
    {
	return hearingResultDesc;
    }

    /**
     * @return
     */
    public String getHearingType()
    {
	fetch();
	return hearingType;
    }

    /**
     * @return Returns the hearingTypeDesc.
     */
    public String getHearingTypeDesc()
    {
	return hearingTypeDesc;
    }

    /**
     * @return
     */
    public Code getHearingTypes()
    {
	initHearingTypes();
	return hearingTypes;
    }

    /**
     * @return
     */
    public String getIssueFlag()
    {
	fetch();
	return issueFlag;
    }

    /**
     * @return
     */
    public String getJuryFlag()
    {
	fetch();
	return juryFlag;
    }

    public String getJuvenileFirstName()
    {
	return juvenileFirstName;
    }

    public String getJuvenileLastName()
    {
	return juvenileLastName;
    }

    public String getJuvenileMiddleName()
    {
	return juvenileMiddleName;
    }

    /**
     * @return
     */
    public String getJuvenileNumber()
    {
	fetch();
	return juvenileNumber;
    }

    /**
     * @return
     */
    public Date getLcDate()
    {
	fetch();
	return lcDate;
    }

    /**
     * @return
     */
    public Date getLcTime()
    {
	fetch();
	return lcTime;
    }

    /**
     * @return
     */
    public String getLcUser()
    {
	fetch();
	return lcUser;
    }

    /**
     * @return
     */
    public String getOptionFlag()
    {
	fetch();
	return optionFlag;
    }

    /**
     * @return
     */
    public String getPetitionAllegation()
    {
	fetch();
	return petitionAllegation;
    }

    /**
     * @return Returns the petitionAllegationDesc.
     */
    public String getPetitionAllegationDesc()
    {
	return petitionAllegationDesc;
    }

    /**
     * @return
     */
    public String getPetitionAmendment()
    {
	fetch();
	return petitionAmendment;
    }

    /**
     * @return
     */
    public String getPetitionNumber()
    {
	fetch();
	return petitionNumber;
    }

    /**
     * @return
     */
    public String getPetitionStatus()
    {
	fetch();
	return petitionStatus;
    }

    /**
     * @return
     */
    public String getPrevNotes()
    {
	fetch();
	return prevNotes;
    }

    /**
     * @return
     */
    public String getReferralNumber()
    {
	fetch();
	return referralNumber;
    }

    /**
     * @return
     */
    public String getResetHearingType()
    {
	fetch();
	return resetHearingType;
    }

    /**
     * @return
     */
    public String getSeqNumber()
    {
	fetch();
	return seqNumber;
    }

    /**
     * @return
     */
    public int getCourtSeqNumber()
    {
	fetch();
	return courtSeqNumber;
    }

    /**
     * @return
     */
    public int getCourtChainNumber()
    {
	fetch();
	return courtChainNumber;
    }

    /**
     * @return
     */
    public String getUpdateFlag()
    {
	fetch();
	return updateFlag;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initHearingTypes()
    {
	if (hearingTypes == null)
	{
	    hearingTypes = (Code) new mojo.km.persistence.Reference(hearingType, Code.class).getObject();
	}
    }

    /**
     * @param date
     */
    public void setAmendmentDate(Date amendmentDate)
    {
	this.amendmentDate = amendmentDate;
    }

    /**
     * @param string
     */
    public void setAttorneyName(String attorneyName)
    {
	this.attorneyName = attorneyName;
    }

    /**
     * @param string
     */
    public void setBarNumber(String barNumber)
    {
	this.barNumber = barNumber;
    }

    /**
     * @param string
     */
    public void setChainNumber(String chainNumber)
    {
	this.chainNumber = chainNumber;
    }

    /**
     * @param string
     */
    public void setComments(String comments)
    {
	this.comments = comments;
    }

    /**
     * @param date
     */
    public void setCourtDate(Date courtDate)
    {
	this.courtDate = courtDate;
    }

    public String getAttorneyConnection()
    {
	fetch();
	return attorneyConnection;
    }

    public void setAttorneyConnection(String attorneyConnection)
    {
	this.attorneyConnection = attorneyConnection;
    }

    /**
     * setter with integer
     * 
     * @param courtDate
     */
  /*  public void setCourtDate(Integer courtDate)
    {
	Date calculatedDate = null;
	try
	{
	    calculatedDate = DateUtil.IntToDate(courtDate, DateUtil.DATE_FMT_2);
	}
	catch (ParseRuntimeException e)
	{
	    e.printStackTrace();
	}
	this.courtDate = calculatedDate;
    }*/

    /**
     * @param string
     */
    public void setCourtId(String courtId)
    {
	this.courtId = courtId;
    }

    /**
     * @param string
     */
    public void setCourtTime(String courtTime)
    {
	this.courtTime = courtTime;
    }

    /**
     * @param date
     */
    public void setFilingDate(Date filingDate)
    {
	this.filingDate = filingDate;
    }

    /**
     * @param string
     */
    public void setHearingCategory(String hearingCategory)
    {
	this.hearingCategory = hearingCategory;
    }

    /**
     * @param string
     */
    public void setHearingDisposition(String hearingDisposition)
    {
	this.hearingDisposition = hearingDisposition;
    }

    /**
     * @param hearingDispositionDesc
     *            The hearingDispositionDesc to set.
     */
    public void setHearingDispositionDesc(String hearingDispositionDesc)
    {
	this.hearingDispositionDesc = hearingDispositionDesc;
    }

    /**
     * @param string
     */
    public void setHearingResult(String hearingResult)
    {
	this.hearingResult = hearingResult;
    }

    /**
     * @param hearingResultDesc
     *            The hearingResultDesc to set.
     */
    public void setHearingResultDesc(String hearingResultDesc)
    {
	this.hearingResultDesc = hearingResultDesc;
    }

    /**
     * @param string
     */
    public void setHearingType(String hearingType)
    {
	this.hearingType = hearingType;
    }

    /**
     * @param hearingTypeDesc
     *            The hearingTypeDesc to set.
     */
    public void setHearingTypeDesc(String hearingTypeDesc)
    {
	this.hearingTypeDesc = hearingTypeDesc;
    }

    /**
     * set the type reference for class member hearingTypes
     */
    public void setHearingTypes(Code hearingTypes)
    {
	setHearingType("" + hearingTypes.getOID());
	this.hearingTypes = (Code) new mojo.km.persistence.Reference(hearingTypes).getObject();
    }

    /**
     * @param string
     */
    public void setIssueFlag(String issueFlag)
    {
	this.issueFlag = issueFlag;
    }

    /**
     * @param string
     */
    public void setJuryFlag(String juryFlag)
    {
	this.juryFlag = juryFlag;
    }

    public void setJuvenileFirstName(String juvenileFirstName)
    {
	this.juvenileFirstName = juvenileFirstName;
    }

    public void setJuvenileLastName(String juvenileLastName)
    {
	this.juvenileLastName = juvenileLastName;
    }

    public void setJuvenileMiddleName(String juvenileMiddleName)
    {
	this.juvenileMiddleName = juvenileMiddleName;
    }

    /**
     * @param string
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }

    /**
     * @param date
     */
    public void setLcDate(Date lcDate)
    {
	this.lcDate = lcDate;
    }

    /**
     * @param string
     */
    public void setLcTime(Date lcTime)
    {
	this.lcTime = lcTime;
    }

    /**
     * @param string
     */
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    /**
     * @param string
     */
    public void setOptionFlag(String optionFlag)
    {
	this.optionFlag = optionFlag;
    }

    /**
     * @param string
     */
    public void setPetitionAllegation(String petitionAllegation)
    {
	this.petitionAllegation = petitionAllegation;
    }

    /**
     * @param petitionAllegationDesc
     *            The petitionAllegationDesc to set.
     */
    public void setPetitionAllegationDesc(String petitionAllegationDesc)
    {
	this.petitionAllegationDesc = petitionAllegationDesc;
    }

    /**
     * @param string
     */
    public void setPetitionAmendment(String petitionAmendment)
    {
	this.petitionAmendment = petitionAmendment;
    }

    /**
     * @param string
     */
    public void setPetitionNumber(String petitionNumber)
    {
	this.petitionNumber = petitionNumber;
    }

    /**
     * @param string
     */
    public void setPetitionStatus(String petitionStatus)
    {
	this.petitionStatus = petitionStatus;
    }

    /**
     * @param string
     */
    public void setPrevNotes(String prevNotes)
    {
	this.prevNotes = prevNotes;
    }

    /**
     * @param string
     */
    public void setReferralNumber(String referralNumber)
    {
	this.referralNumber = referralNumber;
    }

    /**
     * @param string
     */
    public void setResetHearingType(String resetHearingType)
    {
	this.resetHearingType = resetHearingType;
    }

    /**
     * @param string
     */
    public void setSeqNumber(String seqNumber)
    {
	this.seqNumber = seqNumber;
    }

    /**
     * @param int
     */
    public void setCourtSeqNumber(int courtSeqNumber)
    {
	this.courtSeqNumber = courtSeqNumber;
    }

    /**
     * @param int
     */
    public void setCourtChainNumber(int courtChainNumber)
    {
	this.courtChainNumber = courtChainNumber;
    }

    /**
     * @param string
     */
    public void setUpdateFlag(String updateFlag)
    {
	this.updateFlag = updateFlag;
    }

    public String getGalBarNumber()
    {
        fetch();
	return galBarNumber;
    }

    public void setGalBarNumber(String galBarNumber)
    {
	 if (this.galBarNumber == null || !this.galBarNumber.equals(galBarNumber))
	 {
	  markModified();
	 }
        this.galBarNumber = galBarNumber;
    }

    public String getGalName()
    {
	fetch();
        return galName;
    }

    public void setGalName(String galName)
    {
	if (this.galName == null || !this.galName.equals(galName))
	 {
	  markModified();
	 }
        this.galName = galName;
    }

    /**
     * Initialize class relationship to class pd.juvenile.Juvenile
     */
    private void initJuvenile()
    {
	if (juvenile == null)
	{
	    try
	    {
		//juvenile = Juvenile.find(juvenileNumber);
		juvenile = JuvenileCore.findCore(juvenileNumber);
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * Gets referenced type pd.juvenile.Juvenile
     */
    //public pd.juvenile.Juvenile getJuvenile()
    public JuvenileCore getJuvenile()
    {
	fetch();
	initJuvenile();
	return juvenile;
    }
    /**
     * @param jjsCLCOURT_ID
     * @return
     */
    static public JJSCourt find(String jjsCOURT_ID)
    {

	JJSCourt court = (JJSCourt) new Home().find(jjsCOURT_ID, JJSCourt.class);
	if (court != null && court.getRectype().equalsIgnoreCase("COURT"))
	    return court;
	else
	{
	    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	    if (grantedFeature)
		return court;
	    else
		return null;
	}

    }
    public DocketEventResponseEvent valueObject()
    {
	DocketEventResponseEvent resp = new DocketEventResponseEvent();
	resp.setDocketEventId(this.getOID());
	resp.setEventDate(this.getCourtDate());
	resp.setStartDatetime(this.getCourtDate());
	resp.setEndDatetime(this.getCourtDate());
	resp.setCourtTime(this.getCourtTime());	
	resp.setOldcourtTime(this.getCourtTime());	
	if (this.getCourtTime() != null)
	{
	    	Calendar cal=Calendar.getInstance();	    	
        	cal.setTime(DateUtil.stringToDate(this.getCourtTime(), "HH:mm"));
        	SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
        	String time = localDateFormat.format(cal.getTime());
        	resp.setFormattedCourtTime(time);
	}
	resp.setComments(this.getComments());
	resp.setOldcomments(this.getComments());
	resp.setAttorneyName(this.getAttorneyName());
	if (this.getAtyConfirmation() != null)
	{
	    resp.setAtyConfirmation(this.getAtyConfirmation());
	}
	else
	{
	    resp.setAtyConfirmation("");
	}

	resp.setPetitionNumber(this.getPetitionNumber());
	resp.setAllegation(this.getPetitionAllegation());
	resp.setAllegationDesc(this.getPetitionAllegationDesc());
	resp.setPetitionStatus(this.getPetitionStatus());
	resp.setPetitionStatusCd(this.getPetitionStatus());
	resp.setPetitionAmendment(this.getPetitionAmendment());
	resp.setPetitionAmendmentDate(DateUtil.dateToString(this.getAmendmentDate(), DateUtil.DATE_FMT_1));
	resp.setFilingDate(DateUtil.dateToString(this.getFilingDate(),DateUtil.DATE_FMT_1));
	resp.setCourt(this.getCourtId());
	resp.setCourtResult(this.getHearingResult());
	resp.setOldcourtResult(this.getHearingResult());
	resp.setCourtResultDesc(this.getHearingResultDesc());

	resp.setDisposition(this.getHearingDisposition());
	resp.setOlddisposition(this.getHearingDisposition());
	resp.setDispositionDesc(this.getHearingDispositionDesc());

	resp.setHearingType(this.getHearingType());
	resp.setResetHearingType(this.getResetHearingType());
	resp.setOldresetHearingType(this.getResetHearingType());
	//83426
	/*if (this.getHearingType() != null && this.getHearingTypeDesc() == null || (this.getHearingTypeDesc() != null && this.getHearingTypeDesc().isEmpty()))
	{
	    resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(this.getHearingType()).getDescription());
	}
	else
	{
	    resp.setHearingTypeDesc(this.getHearingTypeDesc());
	}
	if(this.resetHearingType!=null){
	    resp.setResetHearingTypeDesc(JuvenileHearingTypeCode.find(this.getResetHearingType()).getDescription());
	}*/
	resp.setDocketType("COURT");
	/*if (this.getHearingResult() != null && this.getHearingResult().equalsIgnoreCase("TRN"))
	{*/
	    resp.setTransferTo(this.getTransferTo());
	    resp.setOldtransferTo(this.getTransferTo());
	//}
	resp.setLastCourtDate(DateUtil.dateToString(this.getCourtDate(), DateUtil.DATE_FMT_1));
	resp.setCourtDate(DateUtil.dateToString(this.getCourtDate(), DateUtil.DATE_FMT_1));
	if (this.getCourtDate() != null)
	{
	    setCourtDateWithTime(this.getCourtDate());
	}
	resp.setEventDateWithTime(this.getCourtDateWithTime());

	if (this.juvenileNumber != null)
	{
	    //Name name = new Name(this.getJuvenile().getFirstName(), this.getJuvenile().getMiddleName(), this.getJuvenile().getLastName()); Doesnt work when sorted by juvenile name. Causing issues.
	    StringBuffer full = new StringBuffer();
	    JuvenileCore juvenile = this.getJuvenile(); //83426
	    if (juvenile != null)
	    {
		if (juvenile.getLastName() != null)
		{
		    full.append(juvenile.getLastName());
		}

		if (juvenile.getFirstName() != null)
		{
		    full.append(", ");
		    full.append(juvenile.getFirstName());
		    if (juvenile.getMiddleName() != null)
		    {
			full.append(" " + juvenile.getMiddleName());
		    }
		}

		resp.setJuvenileName(full.toString());
		resp.setAge(String.valueOf(juvenile.getAgeInYears(juvenile.getDateOfBirth())));
		resp.setRace(juvenile.getRaceCodeDescription());
		resp.setGender(juvenile.getSexCodeDescription());
		resp.setDob(DateUtil.dateToString(juvenile.getDateOfBirth(), DateUtil.DATE_FMT_1)); //performance bug 84351
	    }
	    else
	    {
		resp.setJuvenileName("NO JUVENILE RECORD FOUND");
	    }
	}
	else
	{
	    resp.setJuvenileName("NO JUVENILE NAME RECORD FOUND");
	}
	resp.setJuvenileNumber(this.getJuvenileNumber());
	resp.setIssueFlag(this.getIssueFlag());
	resp.setBarNum(this.getBarNumber());
	resp.setAttorneyConnection(this.getAttorneyConnection());
	resp.setOldattorneyConnection(this.getAttorneyConnection());
	if (this.getAttorneyConnection() != null && !this.getAttorneyConnection().isEmpty())
	{
	    if (this.getAttorneyConnection().equals("AAT"))
	    {
		resp.setAttorneyConnectionDesc("APPOINTED");
	    }
	    else
	    {	    
        	    if (this.getAttorneyConnection().equals("HAT"))
        	    {
        		resp.setAttorneyConnectionDesc("HIRED");
        	    }
        	    if (this.getAttorneyConnection().equals("PDO"))
        	    {
        		resp.setAttorneyConnectionDesc("PUBLIC DEFENDER");
        	    }
	    }
	}
	if (this.getAttorney2Connection() != null && !this.getAttorney2Connection().isEmpty())
	{
	    if (this.getAttorney2Connection().equals("AAT"))
	    {
		resp.setAttorney2ConnectionDesc("APPOINTED");
	    }
	    else
	    {	    
        	    if (this.getAttorney2Connection().equals("HAT"))
        	    {
        		resp.setAttorney2ConnectionDesc("HIRED");
        	    }
        	    if (this.getAttorney2Connection().equals("PDO"))
        	    {
        		resp.setAttorney2ConnectionDesc("PUBLIC DEFENDER");
        	    }
	    }
	}
	resp.setRecType(this.getRectype());
	resp.setTjpcSeqNum(this.getTjpcSeqNumber());
	resp.setChainNumber(this.getChainNumber());
	resp.setSeqNum(this.getSeqNumber());
	resp.setReferralNum(this.getReferralNumber());
	resp.setPrevNotes(this.getPrevNotes());
	resp.setOldprevNotes(this.getPrevNotes());
	resp.setOptionFlag(this.getOptionFlag());
	resp.setCreateDate(new Date(this.getCreateTimestamp().getTime()));
	//for uniqueness
	resp.setDocketEventIdKey(resp.getDocketEventId()+resp.getCreateDate().getTime());
	resp.setLcUser(this.getLcUser());
	resp.setLcDate(this.getLcDate());	
	if (this.getLcTime() != null)// && !this.getLcTime().isEmpty()
	{
	    	Calendar cal=Calendar.getInstance();
        	cal.setTime(this.getLcTime());
        	SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
        	String time = localDateFormat.format(cal.getTime());
        	resp.setLcTime(time);
	}
	resp.setOldattorneyName(this.getAttorneyName());
	resp.setOldallegation(this.getPetitionAllegation());
	resp.setOldbarNum(this.getBarNumber());
	resp.setGalBarNum( this.galBarNumber );
	resp.setGalName( this.galName );
	//add 2nd attorney details 
	resp.setAttorney2Name(this.attorney2Name);
	resp.setAttorney2BarNum(this.attorney2BarNum);
	resp.setAttorney2Connection(this.attorney2Connection);
	//
	resp.setAppAttorney(this.appAttorney);
	resp.setInterpreter(this.interpreter);
	return resp;
    }

    /*
     * 
     */
    private Date getCourtDateWithTime()
    {
	return courtDateWithTime;
    }

    /*
     * given a Date, add in the hour and minute
     * and set the field's value appropriately
     */
    private void setCourtDateWithTime(Date courtDate)
    {
	String hrstr = "", minstr = "", rawTime = getCourtTime();

	// the time coming from M204 looks like: "1330",
	// but could be "13:30"
	try
	{
	    if(rawTime!=null) //bug fix for  142111
	    {
        	    hrstr = rawTime.substring(0, 2);
        	    if (rawTime.charAt(2) == ':')
        	    {
        		minstr = rawTime.substring(3, 5);
        	    }
        	    else
        	    {
        		minstr = rawTime.substring(2, 4);
        		this.setCourtTime(hrstr.concat(":").concat(minstr));
        	    }
	    }
	}
	catch (IndexOutOfBoundsException ioobe)
	{
	}

	int hours = 0, minutes = 0;
	try
	{
	    hours = Integer.parseInt(hrstr);
	}
	catch (NumberFormatException nfe)
	{
	}

	try
	{
	    minutes = Integer.parseInt(minstr);
	}
	catch (NumberFormatException nfe)
	{
	}

	// set the date, then fold in the time
	Calendar cal = Calendar.getInstance();
	if (courtDate != null)
	{
	    cal.setTime(courtDate);
	}
	cal.set(Calendar.HOUR_OF_DAY, hours);
	cal.set(Calendar.MINUTE, minutes);
	this.courtDateWithTime = cal.getTime();
    }

    public void setRectype(String rectype)
    {
	this.rectype = rectype;
    }

    public String getRectype()
    {
	fetch();
	return rectype;
    }

    public String getTjpcSeqNumber()
    {
	fetch();
	return tjpcSeqNumber;
    }

    public void setTjpcSeqNumber(String tjpcSeqNumber)
    {
	this.tjpcSeqNumber = tjpcSeqNumber;
    }

    public String getTransferTo()
    {
	fetch();
	return transferTo;
    }

    public void setTransferTo(String transferTo)
    {
	this.transferTo = transferTo;
    }
    public String getAtyConfirmation()
    {
        return atyConfirmation;
    }

    public void setAtyConfirmation(String atyConfirmation)
    {
        this.atyConfirmation = atyConfirmation;
    }
  //task 147422
    public String getAttorney2Connection()
    {
	fetch();
        return attorney2Connection;
    }

    public void setAttorney2Connection(String attorney2Connection)
    {
        this.attorney2Connection = attorney2Connection;
    }

    public String getAttorney2Name()
    {
	fetch();
        return attorney2Name;
    }

    public void setAttorney2Name(String attorney2Name)
    {
        this.attorney2Name = attorney2Name;
    }

    
    public String getAttorney2BarNum()
    {
	fetch();
        return attorney2BarNum;
    }

    public void setAttorney2BarNum(String attorney2BarNum)
    {
        this.attorney2BarNum = attorney2BarNum;
    }
    public String getAppAttorney()
    {
	fetch();
        return appAttorney;
    }

    public void setAppAttorney(String appAttorney)
    {
        this.appAttorney = appAttorney;
    }
    // task 168662
    public String getInterpreter()
    {
	fetch();
        return interpreter;
    }

    public void setInterpreter(String interpreter)
    {
        this.interpreter = interpreter;
    }
    
    public String getTJPCDisp()
    {
	fetch();
        return this.tjpcDisp;
    }

    public void setTJPCDisp(String tjpcDisp)
    {
        this.tjpcDisp = tjpcDisp;
    }
    
    public Date getResetDate()
    {
	fetch();
        return resetDate;
    }

    public void setResetDate(Date resetDate)
    {
        this.resetDate = resetDate;
    }
}
