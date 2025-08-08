package pd.juvenilecase;/*package pd.juvenilecase;
//81390
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenilecase.DeleteJJSDetentionEvent;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.Name;
import pd.juvenile.Juvenile;

*//**
 * @author mchowdhury
 *//*
*//**
 * @roseuid 43BA9F090142
 *//*
public class JJSDetention extends PersistentObject implements Comparable<JJSDetention>
{
    private String alternateSettingInd;
    private String attorneyName;
    private String barNumber;
    private String chainNumber;
    private Date courtDate;
     11mar2009 - mjt - we could have folded in
     * courtTime into courtDate, but it's possible
     * that downstream code would break. the member
     * that follows is set with the courtDate, then
     * the courtTime is folded in. 
    
    private Date courtDateWithTime;
    private String courtId;
    private String courtTime;
    private String hearingDisposition;
    private String hearingDispositionDesc;
    private String hearingResult;
    private String hearingResultDesc;
    private String issueFlag;
    private Juvenile juvenile;
    private String juvenileFirstName;
    private String juvenileLastName;
    private String juvenileMiddleName;
    private String juvenileNumber;
    private Date lcDate;
    private String lcTime;
    private String lcUser;
    private String petitionNumber;
    private String referralNumber;
    private String seqNumber;
    private String updateFlag;

    //added for facility
    private String recType;
    private String tjpcseqnum;

    *//**
     * @roseuid 43BA9F090142
     *//*
    public JJSDetention()
    {
    }

    *//**
     * @roseuid 42A99B9802DD
     *//*
    static public Iterator findAll(DeleteJJSDetentionEvent event)
    {
	IHome home = new Home();
	return home.findAll(event, JJSDetention.class);
    }

    *//**
     * @param attributeName
     * @param attributeValue
     * @return Iterator
     *//*
    public static Iterator findAll(String attributeName, String attributeValue)
    {
	IHome home = new Home();
	return home.findAll(attributeName, attributeValue, JJSDetention.class);
    }

    *//**
     * @return
     * @param event
     *//*
    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	return home.findAll(event, JJSDetention.class);
    }

    *//**
     * @return Returns the alternateSettingInd.
     *//*
    public String getAlternateSettingInd()
    {
	fetch();
	return alternateSettingInd;
    }

    *//**
     * @return
     *//*
    public String getAttorneyName()
    {
	fetch();
	return attorneyName;
    }

    *//**
     * @return
     *//*
    public String getBarNumber()
    {
	fetch();
	return barNumber;
    }

    *//**
     * @return
     *//*
    public String getChainNumber()
    {
	fetch();
	return chainNumber;
    }

    *//**
     * @return
     *//*
    public Date getCourtDate()
    {
	fetch();
	return courtDate;
    }

    *//**
     * @return
     *//*
    public String getCourtId()
    {
	fetch();
	return courtId;
    }

    *//**
     * @return
     *//*
    public String getCourtTime()
    {
	fetch();
	return courtTime;
    }

    *//**
     * @return
     *//*
    public String getHearingDisposition()
    {
	fetch();
	return hearingDisposition;
    }

    *//**
     * @return Returns the hearingDispositionDesc.
     *//*
    public String getHearingDispositionDesc()
    {
	return hearingDispositionDesc;
    }

    *//**
     * @return
     *//*
    public String getHearingResult()
    {
	fetch();
	return hearingResult;
    }

    *//**
     * @return Returns the hearingResultDesc.
     *//*
    public String getHearingResultDesc()
    {
	return hearingResultDesc;
    }

    *//**
     * @return
     *//*
    public String getIssueFlag()
    {
	fetch();
	return issueFlag;
    }

    *//**
     * Gets referenced type pd.juvenile.Juvenile
     *//*
    public pd.juvenile.Juvenile getJuvenile()
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

    *//**
     * @return
     *//*
    public String getJuvenileNumber()
    {
	fetch();
	return juvenileNumber;
    }

    *//**
     * @return
     *//*
    public Date getLcDate()
    {
	fetch();
	return lcDate;
    }

    *//**
     * @return
     *//*
    public String getLcTime()
    {
	fetch();
	return lcTime;
    }

    *//**
     * @return
     *//*
    public String getLcUser()
    {
	fetch();
	return lcUser;
    }

    *//**
     * @return
     *//*
    public String getPetitionNumber()
    {
	fetch();
	return petitionNumber;
    }

    *//**
     * @return
     *//*
    public String getReferralNumber()
    {
	fetch();
	return referralNumber;
    }

    *//**
     * @return
     *//*
    public String getSeqNumber()
    {
	fetch();
	return seqNumber;
    }

    *//**
     * @return
     *//*
    public String getUpdateFlag()
    {
	fetch();
	return updateFlag;
    }

    *//**
     * Initialize class relationship to class pd.juvenile.Juvenile
     *//*
    private void initJuvenile()
    {
	if (juvenile == null)
	{
	    try
	    {
		juvenile = Juvenile.find(juvenileNumber);
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    *//**
     * @param alternateSettingInd
     *            The alternateSettingInd to set.
     *//*
    public void setAlternateSettingInd(String alternateSettingInd)
    {
	this.alternateSettingInd = alternateSettingInd;
    }

    *//**
     * @param string
     *//*
    public void setAttorneyName(String attorneyName)
    {
	this.attorneyName = attorneyName;
    }

    *//**
     * @param string
     *//*
    public void setBarNumber(String barNumber)
    {
	this.barNumber = barNumber;
    }

    *//**
     * @param string
     *//*
    public void setChainNumber(String chainNumber)
    {
	this.chainNumber = chainNumber;
    }

    *//**
     * @param date
     *//*
    public void setCourtDate(Date courtDate)
    {
	this.courtDate = courtDate;
    }

    *//**
     * @param string
     *//*
    public void setCourtId(String courtId)
    {
	this.courtId = courtId;
    }

    *//**
     * @param string
     *//*
    public void setCourtTime(String courtTime)
    {
	this.courtTime = courtTime;
    }

    *//**
     * @param string
     *//*
    public void setHearingDisposition(String hearingDisposition)
    {
	this.hearingDisposition = hearingDisposition;
    }

    *//**
     * @param hearingDispositionDesc
     *            The hearingDispositionDesc to set.
     *//*
    public void setHearingDispositionDesc(String hearingDispositionDesc)
    {
	this.hearingDispositionDesc = hearingDispositionDesc;
    }

    *//**
     * @param string
     *//*
    public void setHearingResult(String hearingResult)
    {
	this.hearingResult = hearingResult;
    }

    *//**
     * @param hearingResultDesc
     *            The hearingResultDesc to set.
     *//*
    public void setHearingResultDesc(String hearingResultDesc)
    {
	this.hearingResultDesc = hearingResultDesc;
    }

    *//**
     * @param string
     *//*
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

    *//**
     * @param string
     *//*
    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }

    *//**
     * @param date
     *//*
    public void setLcDate(Date lcDate)
    {
	this.lcDate = lcDate;
    }

    *//**
     * @param string
     *//*
    public void setLcTime(String lcTime)
    {
	this.lcTime = lcTime;
    }

    *//**
     * @param string
     *//*
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    *//**
     * @param string
     *//*
    public void setPetitionNumber(String petitionNumber)
    {
	this.petitionNumber = petitionNumber;
    }

    *//**
     * @param string
     *//*
    public void setReferralNumber(String referralNumber)
    {
	this.referralNumber = referralNumber;
    }

    *//**
     * @param string
     *//*
    public void setSeqNumber(String seqNumber)
    {
	this.seqNumber = seqNumber;
    }

    *//**
     * @param string
     *//*
    public void setUpdateFlag(String updateFlag)
    {
	this.updateFlag = updateFlag;
    }

    
     * 
     
    public DocketEventResponseEvent valueObject()
    {
	DocketEventResponseEvent resp = new DocketEventResponseEvent();
	if (this.getOID() != null)
	{
	    resp.setDocketEventId(this.getOID());
	}
	else
	{
	    resp.setDocketEventId("");
	}

	resp.setEventDate(this.getCourtDate());
	resp.setAttorneyName(this.getAttorneyName());
	resp.setStartDatetime(this.getCourtDate());
	resp.setEndDatetime(this.getCourtDate());
	resp.setPetitionNumber(this.getPetitionNumber());
	resp.setCourt(this.getCourtId());
	resp.setCourtResult(this.getHearingResult());
	resp.setCourtResultDesc(this.getHearingResultDesc());
	resp.setDisposition(this.getHearingDisposition());
	resp.setDispositionDesc(this.getHearingDispositionDesc());
	resp.setReferralNum(this.referralNumber);
	resp.setSeqNum(this.seqNumber);
	resp.setChainNumber(this.chainNumber);
	Name name = new Name(this.juvenileFirstName, this.juvenileMiddleName, this.juvenileLastName);
	resp.setJuvenileName(name.getFormattedName());

	setCourtDateWithTime(this.getCourtDate());
	resp.setEventDateWithTime(this.getCourtDateWithTime());
	resp.setJuvenileNumber(this.juvenileNumber);
	resp.setDocketType("Detention");

	return resp;
    }

    private Date getCourtDateWithTime()
    {
	return courtDateWithTime;
    }

    
     * given a Date, set the hour and minute to midnight (00:00)
     * and set the field's value appropriately
     
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
	this.recType = recType;
    }

    *//**
     * @return the tjpcseqnum
     *//*
    public String getTjpcseqnum()
    {
	fetch();
	return tjpcseqnum;
    }

    *//**
     * @param tjpcseqnum
     *            the tjpcseqnum to set
     *//*
    public void setTjpcseqnum(String tjpcseqnum)
    {
	if (this.tjpcseqnum == null || !this.tjpcseqnum.equals(tjpcseqnum))
	{
	    markModified();
	}
	this.tjpcseqnum = tjpcseqnum;
    }

    @Override
    public int compareTo(JJSDetention det)
    {
	JJSDetention detention = (JJSDetention) det;
	return this.getSeqNumber().compareTo(detention.getSeqNumber());
    }
}
*/