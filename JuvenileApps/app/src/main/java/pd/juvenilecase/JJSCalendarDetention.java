package pd.juvenilecase;/*package pd.juvenilecase;
//user-story 81390
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.Name;
import pd.juvenile.Juvenile;

*//**
 *Copy of JJS DETENTION. ADDED FOR FACILITY AS M204 cannot handle update and insert at the same time.
 * BUG #40457
 *//*
*//**
 * @roseuid 43BA9F090142
 *//*
public class JJSCalendarDetention extends PersistentObject implements Comparable<JJSCalendarDetention>
{
    *//**
	 * 
	 *//*
    private static final long serialVersionUID = 1L;
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
     * @param juvenile
     *            the juvenile to set
     *//*
    public void setJuvenile(Juvenile juvenile)
    {
	this.juvenile = juvenile;
    }

    *//**
     * @roseuid 43BA9F090142
     *//*
    public JJSCalendarDetention()
    {
    }

    *//**
     * @param attributeName
     * @param attributeValue
     * @return Iterator
     *//*
    public static Iterator<JJSCalendarDetention> findAll(String attributeName, String attributeValue)
    {
	IHome home = new Home();
	return home.findAll(attributeName, attributeValue, JJSCalendarDetention.class);
    }

    *//**
     * @return
     * @param event
     *//*
    static public Iterator<JJSCalendarDetention> findAll(IEvent event)
    {
	IHome home = new Home();
	return home.findAll(event, JJSCalendarDetention.class);
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
	if (this.alternateSettingInd == null || !this.alternateSettingInd.equals(alternateSettingInd))
	{
	    markModified();
	}
	this.alternateSettingInd = alternateSettingInd;
    }

    *//**
     * @param string
     *//*
    public void setAttorneyName(String attorneyName)
    {
	if (this.attorneyName == null || !this.attorneyName.equals(attorneyName))
	{
	    markModified();
	}
	this.attorneyName = attorneyName;
    }

    *//**
     * @param string
     *//*
    public void setBarNumber(String barNumber)
    {
	if (this.barNumber == null || !this.barNumber.equals(barNumber))
	{
	    markModified();
	}
	this.barNumber = barNumber;
    }

    *//**
     * @param string
     *//*
    public void setChainNumber(String chainNumber)
    {
	if (this.chainNumber == null || !this.chainNumber.equals(chainNumber))
	{
	    markModified();
	}
	this.chainNumber = chainNumber;
    }

    *//**
     * @param date
     *//*
    public void setCourtDate(Date courtDate)
    {
	if (this.courtDate == null || !this.courtDate.equals(courtDate))
	{
	    markModified();
	}
	this.courtDate = courtDate;
    }

    *//**
     * @param string
     *//*
    public void setCourtId(String courtId)
    {
	if (this.courtId == null || !this.courtId.equals(courtId))
	{
	    markModified();
	}
	this.courtId = courtId;
    }

    *//**
     * @param string
     *//*
    public void setCourtTime(String courtTime)
    {
	if (this.courtTime == null || !this.courtTime.equals(courtTime))
	{
	    markModified();
	}
	this.courtTime = courtTime;
    }

    *//**
     * @param string
     *//*
    public void setHearingDisposition(String hearingDisposition)
    {
	if (this.hearingDisposition == null || !this.hearingDisposition.equals(hearingDisposition))
	{
	    markModified();
	}
	this.hearingDisposition = hearingDisposition;
    }

    *//**
     * @param hearingDispositionDesc
     *            The hearingDispositionDesc to set.
     *//*
    public void setHearingDispositionDesc(String hearingDispositionDesc)
    {
	if (this.hearingDispositionDesc == null || !this.hearingDispositionDesc.equals(hearingDispositionDesc))
	{
	    markModified();
	}
	this.hearingDispositionDesc = hearingDispositionDesc;
    }

    *//**
     * @param string
     *//*
    public void setHearingResult(String hearingResult)
    {
	if (this.hearingResult == null || !this.hearingResult.equals(hearingResult))
	{
	    markModified();
	}
	this.hearingResult = hearingResult;
    }

    *//**
     * @param hearingResultDesc
     *            The hearingResultDesc to set.
     *//*
    public void setHearingResultDesc(String hearingResultDesc)
    {
	if (this.hearingResultDesc == null || !this.hearingResultDesc.equals(hearingResultDesc))
	{
	    markModified();
	}
	this.hearingResultDesc = hearingResultDesc;
    }

    *//**
     * @param string
     *//*
    public void setIssueFlag(String issueFlag)
    {
	if (this.issueFlag == null || !this.issueFlag.equals(issueFlag))
	{
	    markModified();
	}
	this.issueFlag = issueFlag;
    }

    public void setJuvenileFirstName(String juvenileFirstName)
    {
	if (this.juvenileFirstName == null || !this.juvenileFirstName.equals(juvenileFirstName))
	{
	    markModified();
	}
	this.juvenileFirstName = juvenileFirstName;
    }

    public void setJuvenileLastName(String juvenileLastName)
    {
	if (this.juvenileLastName == null || !this.juvenileLastName.equals(juvenileLastName))
	{
	    markModified();
	}
	this.juvenileLastName = juvenileLastName;
    }

    public void setJuvenileMiddleName(String juvenileMiddleName)
    {
	if (this.juvenileMiddleName == null || !this.juvenileMiddleName.equals(juvenileMiddleName))
	{
	    markModified();
	}
	this.juvenileMiddleName = juvenileMiddleName;
    }

    *//**
     * @param string
     *//*
    public void setJuvenileNumber(String juvenileNumber)
    {
	if (this.juvenileNumber == null || !this.juvenileNumber.equals(juvenileNumber))
	{
	    markModified();
	}
	this.juvenileNumber = juvenileNumber;
    }

    *//**
     * @param date
     *//*
    public void setLcDate(Date lcDate)
    {
	if (this.lcDate == null || !this.lcDate.equals(lcDate))
	{
	    markModified();
	}
	this.lcDate = lcDate;
    }

    *//**
     * @param string
     *//*
    public void setLcTime(String lcTime)
    {
	if (this.lcTime == null || !this.lcTime.equals(lcTime))
	{
	    markModified();
	}
	this.lcTime = lcTime;
    }

    *//**
     * @param string
     *//*
    public void setLcUser(String lcUser)
    {
	if (this.lcUser == null || !this.lcUser.equals(lcUser))
	{
	    markModified();
	}
	this.lcUser = lcUser;
    }

    *//**
     * @param string
     *//*
    public void setPetitionNumber(String petitionNumber)
    {
	if (this.petitionNumber == null || !this.petitionNumber.equals(petitionNumber))
	{
	    markModified();
	}
	this.petitionNumber = petitionNumber;
    }

    *//**
     * @param string
     *//*
    public void setReferralNumber(String referralNumber)
    {
	if (this.referralNumber == null || !this.referralNumber.equals(referralNumber))
	{
	    markModified();
	}
	this.referralNumber = referralNumber;
    }

    *//**
     * @param string
     *//*
    public void setSeqNumber(String seqNumber)
    {
	if (this.seqNumber == null || !this.seqNumber.equals(seqNumber))
	{
	    markModified();
	}
	this.seqNumber = seqNumber;
    }

    *//**
     * @param string
     *//*
    public void setUpdateFlag(String updateFlag)
    {
	if (this.updateFlag == null || !this.updateFlag.equals(updateFlag))
	{
	    markModified();
	}
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
	if (this.recType == null || !this.recType.equals(recType))
	{
	    markModified();
	}
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
    public int compareTo(JJSCalendarDetention det)
    {
	JJSCalendarDetention detention = (JJSCalendarDetention) det;
	return this.getReferralNumber().compareTo(detention.getReferralNumber());
    }
}
*/