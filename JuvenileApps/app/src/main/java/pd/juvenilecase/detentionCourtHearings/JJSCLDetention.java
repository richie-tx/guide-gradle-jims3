package pd.juvenilecase.detentionCourtHearings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByRefereeCourtEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;

import org.apache.commons.lang.StringUtils;

import pd.juvenile.JuvenileCore;

/**
 * //Added for U.S #11645
 * 
 * @author sthyagarajan Converted M204-JJSDETENTION VIEW TO SQL
 */
public class JJSCLDetention extends PersistentObject
{
    private String alternateSettingInd;
    private String attorneyName;
    private String barNumber;
    private String chainNumber;
    private Date courtDate;
    private Date courtDateWithTime;
    private String courtId;
    private String courtTime;
    private String hearingDisposition;
    private String hearingDispositionDesc;
    private String hearingResult;
    private String hearingResultDesc;
    private String hearingType;
    private String issueFlag;
    private String galBarNumber;
    private String galName;
    private String atyConfirmation; 

    

    // Profile stripping fix
    //private Juvenile juvenile;
    private JuvenileCore juvenile;
    //
    private String juvenileFirstName;
    private String juvenileLastName;
    private String juvenileMiddleName;
    private String juvenileNumber;
    private Date lcDate;
    private Date lcTime;
    private String lcUser;
    private String petitionNumber;
    private String referralNumber;
    private String seqNumber;
    private String updateFlag;
    private String attorneyConnection;
    private String comments;
    private Date resetDate;

    //added for facility
    private String recType;
    private String tjpcseqnum;

    private String transferTo;
    
    private String detentionId;
  //task 147422
    private String attorney2BarNum;   
    private String attorney2Name; 
    private String attorney2Connection; 
//  task 168662
    private String interpreter;
    //task 187260
    private Date resettoDate;    

    

    /**
     * @roseuid 43BA9F090142
     */
    public JJSCLDetention()
    {
    }

    /**
     * Task #11645
     * 
     * @param event
     * @return JJSDetention
     */

    static public Iterator findAll(GetJJSCLDetentionByRefereeCourtEvent event)
    {

	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (!grantedFeature)
	{
	    event.setRecType("DETENTION");
	}
	else
	    event.setRecType("");
	IHome home = new Home();
	return home.findAll(event, JJSCLDetention.class);
    }

    /**
     * @param attributeName
     * @param attributeValue
     * @return Iterator
     */
    public static Iterator findAll(String attributeName, String attributeValue)
    {
	IHome home = new Home();
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (grantedFeature)
	    return home.findAll(attributeName, attributeValue, JJSCLDetention.class);
	else
	    return filterSealedPurged(home.findAll(attributeName, attributeValue, JJSCLDetention.class));
    }

    /**
     * @return
     * @param event
     */
    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (grantedFeature)
	    return home.findAll(event, JJSCLDetention.class);
	else
	    return filterSealedPurged(home.findAll(event, JJSCLDetention.class));
    }

    private static Iterator filterSealedPurged(Iterator iter)
    {
	ArrayList<JJSCLDetention> detDocs = new ArrayList<JJSCLDetention>();
	while (iter.hasNext())
	{
	    JJSCLDetention det = (JJSCLDetention) iter.next();
	    if (det != null && det.getRecType() != null && det.getRecType().equalsIgnoreCase("DETENTION"))
		detDocs.add(det);
	}
	return detDocs.iterator();
    }

    /**
     * @param jjsCLDETENTION_ID
     * @return
     */
    static public JJSCLDetention find(String jjsCLDETENTION_ID)
    {

	JJSCLDetention detention = (JJSCLDetention) new Home().find(jjsCLDETENTION_ID, JJSCLDetention.class);
	if (detention != null && detention.getRecType().equalsIgnoreCase("DETENTION"))
	    return detention;
	else
	{
	    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	    if (grantedFeature)
		return detention;
	    else
		return null;
	}

    }

    /**
     * @return Returns the alternateSettingInd.
     */
    public String getAlternateSettingInd()
    {
	fetch();
	return alternateSettingInd;
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

    public String getHearingType()
    {
	fetch();
	return hearingType;
    }

    public void setHearingType(String hearingType)
    {
	this.hearingType = hearingType;
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
     * Gets referenced type pd.juvenile.Juvenile
     */
     // Profile stripping fix
    //public pd.juvenile.Juvenile getJuvenile()
    public JuvenileCore getJuvenile()
    {
	fetch();
	initJuvenile();
	return juvenile;
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
    public String getPetitionNumber()
    {
	fetch();
	return petitionNumber;
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
     * Initialize class relationship to class pd.juvenile.Juvenile
     */
    private void initJuvenile()
    {
	if (juvenile == null)
	{
	    try
	    {
	    // Profile stripping fix
		//juvenile = Juvenile.find(juvenileNumber);
		juvenile = JuvenileCore.findCore(juvenileNumber);
		//
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * @param alternateSettingInd
     *            The alternateSettingInd to set.
     */
    public void setAlternateSettingInd(String alternateSettingInd)
    {
	this.alternateSettingInd = alternateSettingInd;
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
     * @param date
     */
    public void setCourtDate(Date courtDate)
    {
	this.courtDate = courtDate;
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
    public void setIssueFlag(String issueFlag)
    {
	this.issueFlag = issueFlag;
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
    public void setPetitionNumber(String petitionNumber)
    {
	this.petitionNumber = petitionNumber;
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

    public Date getResetDate()
    {
	fetch();
	return resetDate;
    }

    public void setResetDate(Date resetDate)
    {
	this.resetDate = resetDate;
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

    /*
     * 
     */
    public DocketEventResponseEvent valueObject()
    {
	DocketEventResponseEvent resp = new DocketEventResponseEvent();
	if (this.getOID() != null)
	{
	    resp.setDocketEventId(this.getOID());
	}
	else
	{
	    resp.setDocketEventId(StringUtils.EMPTY);
	}

	resp.setEventDate(this.getCourtDate());
	resp.setAttorneyName(this.getAttorneyName());
	resp.setOldattorneyName(this.getAttorneyName());
	if (this.getAtyConfirmation() != null)
	{
	    resp.setAtyConfirmation(this.getAtyConfirmation());
	}
	else
	{
	    resp.setAtyConfirmation("");
	}
	resp.setStartDatetime(this.getCourtDate());
	resp.setEndDatetime(this.getCourtDate());
	resp.setPetitionNumber(this.getPetitionNumber());
	resp.setCourt(this.getCourtId());
	if (this.resetDate != null)
	{
	    resp.setResetTo(DateUtil.dateToString(this.getResetDate(), DateUtil.DATE_FMT_1));
	    resp.setPrevResetTo(DateUtil.dateToString(this.getResetDate(), DateUtil.DATE_FMT_1));
	}
	else
	{
	    resp.setResetTo(StringUtils.EMPTY);
	    resp.setPrevResetTo(StringUtils.EMPTY);// needed for hidden field
	}
	resp.setLastCourtDate(DateUtil.dateToString(this.getCourtDate(), DateUtil.DATE_FMT_1));
	resp.setCourtDate(DateUtil.dateToString(this.getCourtDate(), DateUtil.DATE_FMT_1));
	//add time	
	resp.setCourtTime(this.getCourtTime());	
	resp.setOldcourtTime(this.getCourtTime());	
	//
	if (this.getHearingResult() != null && !this.getHearingResult().isEmpty())
	{

	    resp.setCourtResult(this.getHearingResult());
	    resp.setOldcourtResult(this.getHearingResult());
	    resp.setPrevCourtResult(this.getHearingResult());
	}
	else
	{

	    resp.setCourtResult(StringUtils.EMPTY);
	    resp.setOldcourtResult(this.getHearingResult());
	    resp.setPrevCourtResult("EMPTY");
	}
	resp.setCourtResultDesc(this.getHearingResultDesc());
	resp.setDisposition(this.getHearingDisposition());
	resp.setOlddisposition(this.getHearingDisposition());
	resp.setDispositionDesc(this.getHearingDispositionDesc());
	resp.setReferralNum(this.getReferralNumber());
	resp.setSeqNum(this.getSeqNumber());
	resp.setIssueFlag(this.getIssueFlag());
	resp.setAlternateSettingInd(this.getAlternateSettingInd());
	resp.setTjpcSeqNum(this.getTjpcseqnum());
	resp.setHearingType(this.getHearingType());
	resp.setAttorneyConnection(this.getAttorneyConnection());
	resp.setOldattorneyConnection(this.getAttorneyConnection());
	if (this.getAttorneyConnection() != null && !this.getAttorneyConnection().isEmpty())
	{
	    if (this.getAttorneyConnection().trim().equals("AAT"))
	    {
		resp.setAttorneyConnectionDesc("APPOINTED");
	    }
	    else
	    {
		if (this.getAttorneyConnection().trim().equals("HAT"))
		{
		    resp.setAttorneyConnectionDesc("HIRED");
		}
		if (this.getAttorneyConnection().equals("PDO"))
		{
			resp.setAttorneyConnectionDesc("PUBLIC DEFENDER");
		}
		
	    }
	}
	resp.setComments(this.getComments());
	resp.setOldcomments(this.getComments());
	resp.setResetReason(this.getComments());
	resp.setChainNumber(this.getChainNumber());
	//83426
	// Profile stripping fix
	//Juvenile juvenile = this.getJuvenile();
	JuvenileCore juvenile = this.getJuvenile();
	//
	if (this.juvenileNumber != null)
	{
	    StringBuffer full = new StringBuffer();
	    if (juvenile!= null && juvenile.getLastName() != null)
	    {
		full.append(juvenile.getLastName());
	    }

	    if (juvenile != null && juvenile.getFirstName() != null)
	    {
		full.append(", ");
		full.append(juvenile.getFirstName());
		if (juvenile.getMiddleName() != null)
		{
		    full.append(" " + juvenile.getMiddleName());
		}
	    }

	    resp.setJuvenileName(full.toString());
	}

	resp.setAlternateSettingInd(this.getAlternateSettingInd());
	if (this.getBarNumber() != null)
	{

	    String barNum = this.getBarNumber();
	    StringBuffer sb = new StringBuffer(barNum);
	   /* if (barNum.length() < 8)
	    {
		for (int i = 0; i < 8 - barNum.length(); i++)
		{
		    sb.insert(0, "0");
		}
	    }*/
	    resp.setBarNum(sb.toString());
	}
	if (this.getCourtDate() != null)
	{
	    setCourtDateWithTime(this.getCourtDate());
	    resp.setEventDateWithTime(this.getCourtDateWithTime());
	}

	resp.setJuvenileNumber(this.getJuvenileNumber());
	resp.setInterpreter(this.interpreter);
	resp.setDocketType("DETENTION");
	resp.setRecType(this.getRecType());
	/*if (this.getHearingResult() != null && this.getHearingResult().equalsIgnoreCase("TRN"))
	{*/
	    resp.setTransferTo(this.getTransferTo());
	    resp.setOldtransferTo(this.getTransferTo());
	//}
	/**
	 * When a PC hearing receives a PC decision, then the next seqnum on
	 * that chainnum should be a DT hearing type. If the PC hearing does NOT
	 * have a PC decision because it is reset, then the next seqnum on that
	 * chainnum should be a PC hearing type again.
	 */
	/*		if(this.getIssueFlag()==null && "A".equalsIgnoreCase(this.getAlternateSettingInd())){
				resp.setHearingType("AP");
				resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(resp.getHearingType()).getDescription());
			}else if("E".equalsIgnoreCase(this.getIssueFlag()) && "A".equalsIgnoreCase(this.getAlternateSettingInd())){
				resp.setHearingType("AS");
				resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(resp.getHearingType()).getDescription());
			}else if("E".equalsIgnoreCase(this.getIssueFlag()) && this.getAlternateSettingInd()==null){
				resp.setHearingType("DT");
				resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(resp.getHearingType()).getDescription());
			}else { //all else should be Probable Cause 
				resp.setHearingType("PC");
				resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(resp.getHearingType()).getDescription());
				
			}*///code commented for Bug 70749/

	//resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(resp.getHearingType()).getDescription()); 83426
	
	if (juvenile != null) //83426
	{

	    resp.setAge(String.valueOf(juvenile.getAgeInYears(juvenile.getDateOfBirth())));
	    resp.setRaceId(juvenile.getRaceId());
	    resp.setRace(juvenile.getRaceCodeDescription());
	    // Profile stripping fix
	    //resp.setHispanicInd(juvenile.getHispanic()); //U.S 88526
	    if(juvenile.getRaceId()!=null && juvenile.getRaceId().equalsIgnoreCase("L"))
		resp.setHispanicInd("Y");
	    else
		resp.setHispanicInd("N");
		//
	    resp.setGender(juvenile.getSexCodeDescription());
	    resp.setSexId(juvenile.getSexId());
	    resp.setDob(DateUtil.dateToString(juvenile.getDateOfBirth(), DateUtil.DATE_FMT_1)); //performance bug 84351
	}
	else
	{
	    resp.setJuvenileName("JUVENILE NOT FOUND ON MASTER.");
	}
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
	resp.setOldbarNum(this.getBarNumber());
	resp.setGalBarNum( this.getGalBarNumber() );
	resp.setPreviousGalBarNum( this.getGalBarNumber() );
	resp.setGalName( this.getGalName() );
	resp.setDetentionId(detentionId);
	//add 2nd attorney details 
	resp.setAttorney2Name(this.attorney2Name);
	resp.setAttorney2BarNum(this.attorney2BarNum);
	resp.setAttorney2Connection(this.attorney2Connection);
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
	//
	return resp;
    }

    private Date getCourtDateWithTime()
    {
	return courtDateWithTime;
    }

    /*
     * given a Date, set the hour and minute to midnight (00:00)
     * and set the field's value appropriately
     */
    private void setCourtDateWithTime(Date courtDate)
    {
	Calendar cal = Calendar.getInstance();

	// set the date, then fold in the time, 
	// which is "midnight's minute".
	cal.setTime(courtDate);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.AM_PM, Calendar.AM);
	this.courtDateWithTime = cal.getTime();

	this.setCourtTime("00:00"); // fix the null field

    }

    public String getRecType()
    {
	fetch();
	return recType;
    }

    public void setRecType(String recType)
    {

	if (this.recType == null || !this.recType.equals(recType))
	{
	    markModified();
	}
	this.recType = recType;
    }

    /**
     * @return the tjpcseqnum
     */
    public String getTjpcseqnum()
    {
	fetch();
	return tjpcseqnum;
    }

    /**
     * @param tjpcseqnum
     *            the tjpcseqnum to set
     */
    public void setTjpcseqnum(String tjpcseqnum)
    {
	if (this.tjpcseqnum == null || !this.tjpcseqnum.equals(tjpcseqnum))
	{
	    markModified();
	}
	this.tjpcseqnum = tjpcseqnum;
    }

    /**
     * @return the attortneyConnection
     */
    public String getAttorneyConnection()
    {
	fetch();
	return attorneyConnection;
    }

    /**
     * @param attortneyConnection
     *            the attortneyConnection to set
     */
    public void setAttorneyConnection(String attortneyConnection)
    {
	if (this.attorneyConnection == null || !this.attorneyConnection.equals(attortneyConnection))
	{
	    markModified();
	}
	this.attorneyConnection = attortneyConnection;
    }

    /**
     * @return the comments
     */
    public String getComments()
    {
	fetch();
	return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(String comments)
    {
	if (this.comments == null || !this.comments.equals(comments))
	{
	    markModified();
	}
	this.comments = comments;
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

    public String getDetentionId()
    {
	return detentionId;
    }

    public void setDetentionId(String detentionId)
    {
	this.detentionId = detentionId;
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

    public String getInterpreter()
    {
	fetch();
        return interpreter;
    }

    public void setInterpreter(String interpreter)
    {
        this.interpreter = interpreter;
    }
    public Date getResettoDate()
    {
	fetch();
        return resettoDate;
    }

    public void setResettoDate(Date resettoDate)
    {
        this.resettoDate = resettoDate;
    }

}
