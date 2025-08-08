package pd.juvenilecase.districtCourtHearings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;
import pd.codetable.Code;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class JJSCLAncillary extends PersistentObject
{

    /**
	 * 
	 */

    private static final long serialVersionUID = 1L;
    private String attorneyConnection;
    private String attorneyName;
    private String barNumber;
    private String chainNumber;
    private String comments;
    private Date courtDate;
    private String galBarNumber;
    private String galName;
    //  task 168662
    private String interpreter;
    /* 11mar2009 - mjt - we could have folded in
     * courtTime into courtDate, but it's possible
     * that downstream code would break. the member
     * that follows is set with the courtDate, then
     * the courtTime is folded in. 
    */

    
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

    private Date courtDateWithTime;
    private String courtId;
    private String courtTime;
    private Date filingDate;
    private String hearingDisposition;
    private String hearingDispositionDesc;
    private String hearingResult;
    private String hearingResultDesc;
    private String hearingType;
    private String hearingTypeDesc;
    private Code hearingTypes = null;
    private String issueFlag;
    private String juryFlag;
    private Date lcDate;
    private Date lcTime;
    private String lcUser;
    private String petitionAmendment;
    private String petitionNumber;
    private String prevNotes;
    private String rectype;
    private String resetHearingType;
    private String respondantName;
    private String seqNumber;
    private String settingReason;
    private String tjpcSeqNum;
    private String typeCase;
    private String updateFlag;
    private String transferTo;

    /**
     * @roseuid 43BA9F090142
     */
    public JJSCLAncillary()
    {
    }

    /**
     * @return
     * @param event
     */
    static public Iterator<JJSCLAncillary> findAll(IEvent event)
    {
	IHome home = new Home();
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (grantedFeature)
	    return home.findAll(event, JJSCLAncillary.class);
	else
	    return filterSealedPurged(home.findAll(event, JJSCLAncillary.class));
    }

    /**
     * @param attributeName
     * @param attributeValue
     * @return Iterator
     */
    public static Iterator<JJSCLAncillary> findAll(String attributeName, String attributeValue)
    {
	IHome home = new Home();
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (grantedFeature)
	    return home.findAll(attributeName, attributeValue, JJSCLAncillary.class);
	else
	    return filterSealedPurged(home.findAll(attributeName, attributeValue, JJSCLAncillary.class));
    }

    public static JJSCLAncillary find(String docketId)
    {
	JJSCLAncillary ancillary = (JJSCLAncillary) new Home().find(docketId, JJSCLAncillary.class);
	if (ancillary != null && ancillary.getRectype().equalsIgnoreCase("ANCILLARY"))
	    return ancillary;
	else
	{
	    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	    if (grantedFeature)
		return ancillary;
	    else
		return null;
	}

    }

    private static Iterator filterSealedPurged(Iterator iter)
    {
	ArrayList<JJSCLAncillary> ancillaryDocs = new ArrayList<JJSCLAncillary>();
	while (iter.hasNext())
	{
	    JJSCLAncillary det = (JJSCLAncillary) iter.next();
	    if (det != null && det.getRectype() != null && det.getRectype().equalsIgnoreCase("ANCILLARY"))
		ancillaryDocs.add(det);
	}
	return ancillaryDocs.iterator();
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
    public String getPrevNotes()
    {
	fetch();
	return prevNotes;
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
     * @param string
     */
    public void setAttorneyName(String attorneyName)
    {
	this.attorneyName = attorneyName;
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

    /**
     * setter with integer
     * 
     * @param courtDate
     */
    public void setCourtDate(Integer courtDate)
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
    }

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
    public void setPrevNotes(String prevNotes)
    {
	this.prevNotes = prevNotes;
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
     * @param string
     */
    public void setUpdateFlag(String updateFlag)
    {
	this.updateFlag = updateFlag;
    }

    public DocketEventResponseEvent valueObject()
    {
	DocketEventResponseEvent resp = new DocketEventResponseEvent();
	resp.setDocketEventId(this.getOID());
	resp.setDocketType("ANCILLARY");
	resp.setAttorneyName(this.getAttorneyName()); //
	resp.setOldattorneyConnection(this.getAttorneyConnection());
	resp.setAttorneyConnection(this.getAttorneyConnection());
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
	resp.setChainNumber(this.getChainNumber());
	resp.setComments(this.getComments());
	resp.setEventDate(this.getCourtDate());
	resp.setStartDatetime(this.getCourtDate());
	resp.setEndDatetime(this.getCourtDate());
	resp.setOldCourt(this.getCourtId());
	resp.setCourt(this.getCourtId());
	resp.setOldcourtTime(this.getCourtTime());
	resp.setCourtTime(this.getCourtTime());
	//resp.setFormattedCourtTime(JuvenileFacilityHelper.stripColonInDate(this.getCourtTime()));
	if (this.getCourtTime() != null)
	{
	    	Calendar cal=Calendar.getInstance();	    	
        	cal.setTime(DateUtil.stringToDate(this.getCourtTime(), "HH:mm"));
        	SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
        	String time = localDateFormat.format(cal.getTime());
        	resp.setFormattedCourtTime(time);
        	resp.setOldFormattedCourtTime(time);
	}
	resp.setLastCourtDate(DateUtil.dateToString(this.getCourtDate(), DateUtil.DATE_FMT_1));
	resp.setOldCourtDate(DateUtil.dateToString(this.getCourtDate(), DateUtil.DATE_FMT_1));
	resp.setCourtDate(DateUtil.dateToString(this.getCourtDate(), DateUtil.DATE_FMT_1));
	setCourtDateWithTime(this.getCourtDate());
	resp.setEventDateWithTime(this.getCourtDateWithTime());
	resp.setOldFilingDate(DateUtil.dateToString(this.getFilingDate(), DateUtil.DATE_FMT_1));
	resp.setFilingDate(DateUtil.dateToString(this.getFilingDate(), DateUtil.DATE_FMT_1));
	resp.setOlddisposition(this.getHearingDisposition());
	resp.setDisposition(this.getHearingDisposition());	
	resp.setDispositionDesc(this.getHearingDispositionDesc());
	resp.setOldcourtResult(this.getHearingResult());
	resp.setCourtResult(this.getHearingResult());
	resp.setCourtResultDesc(this.getHearingResultDesc());
	resp.setHearingType(this.getSettingReason());//Hearing type is null in JJSCLANCILLARY. Setting reason is populated.Check with regina or gordon.
	
	resp.setHearingDisposition(this.getHearingDisposition());
	resp.setHearingResult(this.getHearingResult());
	
	resp.setIssueFlag(this.getIssueFlag());
	resp.setJuryFlag(this.getJuryFlag());
	resp.setPetitionAmendment(this.getPetitionAmendment());
	resp.setOldPetitionNumber(this.getPetitionNumber());
	resp.setPetitionNumber(this.getPetitionNumber());
	resp.setPrevNotes(this.getPrevNotes());
	resp.setRecType(this.getRectype());
	resp.setOldresetHearingType(this.getResetHearingType());
	resp.setResetHearingType(this.getResetHearingType());
	if (this.getHearingResult() != null && this.getHearingResult().equalsIgnoreCase("TRN"))
	{
	    resp.setTransferTo(this.getTransferTo());
	}
	if (this.getRespondantName() == null || (this.getRespondantName() != null && this.getRespondantName().isEmpty()))
	{
	    resp.setJuvenileName("NO JUVENILE NAME RECORD FOUND");
	    resp.setRespondantName("NO JUVENILE NAME RECORD FOUND");
	    resp.setOldRespondantName("NO JUVENILE NAME RECORD FOUND");
	}
	else
	{
	    resp.setOldRespondantName(this.getRespondantName());
	    resp.setRespondantName(this.getRespondantName());
	    resp.setJuvenileName(this.getRespondantName());
	}
	resp.setSeqNum(this.getSeqNumber());
	resp.setOldSettingReason(this.getSettingReason());
	resp.setSettingReason(this.getSettingReason());
	//83426
	/*if (this.getSettingReason() != null && this.getHearingTypeDesc() == null || (this.getHearingTypeDesc() != null && this.getHearingTypeDesc().isEmpty()))
	{
	    resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(this.getSettingReason()).getDescription());
	}
	else
	{
	    resp.setHearingTypeDesc(this.getHearingTypeDesc());
	}//in ancillary table setting Reason = hearing type
	if(this.resetHearingType!=null){
	    resp.setResetHearingTypeDesc(JuvenileHearingTypeCode.find(this.getResetHearingType()).getDescription());
	}*/
	resp.setTjpcSeqNum(this.getTjpcSeqNum());
	resp.setOldTypeCase(this.getTypeCase());
	resp.setTypeCase(this.getTypeCase());
	resp.setBarNum(this.getBarNumber());
	if (this.getTypeCase() != null && !this.getTypeCase().isEmpty())
	{
	    if (this.getTypeCase().equals("C"))
	    {
		resp.setSetNote("DFPS");//Child welfare Unit.
	    }
	    else if (this.getTypeCase().equals("P"))
	    {
		resp.setSetNote("PRIVATE");//Adoptions etc
	    }
	    else if (this.getTypeCase().equals("I"))
	    {
		resp.setSetNote("IMMIGRATION");//Adoptions etc
	    }
	}
	resp.setUpdateFlag(this.getUpdateFlag());
	resp.setCreateDate(new Date(this.getCreateTimestamp().getTime()));
	//for uniqueness
	resp.setDocketEventIdKey(resp.getDocketEventId()+resp.getCreateDate().getTime());
	resp.setGalBarNum( this.galBarNumber );
	resp.setGalName( this.galName );
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
	cal.setTime(courtDate);
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
	return rectype;
    }

    /**
     * @return the attorneyConnection
     */
    public String getAttorneyConnection()
    {
	fetch();
	return attorneyConnection;
    }

    /**
     * @param attorneyConnection
     *            the attorneyConnection to set
     */
    public void setAttorneyConnection(String attorneyConnection)
    {
	this.attorneyConnection = attorneyConnection;
    }

    /**
     * @return the respondantName
     */
    public String getRespondantName()
    {
	fetch();
	return respondantName;
    }

    /**
     * @param respondantName
     *            the respondantName to set
     */
    public void setRespondantName(String respondantName)
    {
	this.respondantName = respondantName;
    }

    /**
     * @return the settingReason
     */
    public String getSettingReason()
    {
	fetch();
	return settingReason;
    }

    /**
     * @param settingReason
     *            the settingReason to set
     */
    public void setSettingReason(String settingReason)
    {
	this.settingReason = settingReason;
    }

    /**
     * @return the tjpcSeqNum
     */
    public String getTjpcSeqNum()
    {
	fetch();
	return tjpcSeqNum;
    }

    /**
     * @param tjpcSeqNum
     *            the tjpcSeqNum to set
     */
    public void setTjpcSeqNum(String tjpcSeqNum)
    {
	this.tjpcSeqNum = tjpcSeqNum;
    }

    /**
     * @return the caseType
     */
    public String getTypeCase()
    {
	fetch();
	return typeCase;
    }

    /**
     * @param caseType
     *            the caseType to set
     */
    public void setTypeCase(String typeCase)
    {
	this.typeCase = typeCase;
    }

    public String getBarNumber()
    {
	fetch();
	return barNumber;
    }

    public void setBarNumber(String barNumber)
    {
	this.barNumber = barNumber;
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
    //task 168662
    public String getInterpreter()
    {
	fetch();
        return interpreter;
    }

    public void setInterpreter(String interpreter)
    {
        this.interpreter = interpreter;
    }

}
