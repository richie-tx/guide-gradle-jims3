package pd.juvenilecase;

import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.util.DateUtil;
import pd.juvenile.Juvenile;

import java.util.Date;
import java.util.Iterator;

/**
 * 
 * @roseuid 43BA9F090142
 */
public class JJSHeader extends PersistentObject
{
	private String facilityStatus;
	private String referralNumber;
	private String lastSequenceNumber;
	private String highestSeqNumberInUse;
	private String facilityCode;
	private String juvenileNumber;
	private Date relocationDate;
	private String relocationTime;
	private Date nextHearingDate;
	private Date probableCauseDate;
	
	//added for facility admit
	private Date lcDate;
	private Date lcTime;
	private String lcUser;
	private String bookingSupervisionNum;
	private String headerFacility; //is always DET
	private String tjpcseqnum;

	/**
	 * Properties for juvenile
	 */
	private Juvenile juvenile = null;
	private String juvenileId;
	private String releaseDecisionStatus;
	private String distReleaseDecisionStatus;

	//addedfor BUG 157024
	private String facility;

	/**
	 * 
	 * @roseuid 43BA9F090142
	 */
	public JJSHeader()
	{
	}
	

	/**
	 * Initialize class relationship to class pd.juvenile.Juvenile
	 */
	private void initJuvenile()
	{
		if (juvenile == null)
		{
			juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenileId, Juvenile.class).getObject();
		}
	}
	
	public static JJSHeader find(String juvenileNum) {
	    IHome home = new Home();
	    return (JJSHeader) home.find("juvenileNumber", juvenileNum , JJSHeader.class);
	}
	
	public static JJSHeader findById(String headerId) {
	    IHome home = new Home();
	    return (JJSHeader) home.find("OID", headerId , JJSHeader.class);
	}
	
	
	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator<JJSHeader> findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,JJSHeader.class);
	}	
	
	   /**
	* @return 
	* @param event
	*/
	static public Iterator<JJSHeader> findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JJSHeader.class);
	}
	
	   /**
	* @return
	* 
	*/
	static public Iterator<JJSHeader> findAll()
	{
		IHome home = new Home();
		return home.findAll(JJSHeader.class);
	}

	/**
	 * 
	 * @return Returns the facilityCode.
	 */
	public String getFacilityCode()
	{
		fetch();
		return facilityCode;
	}

	/**
	 * 
	 * @param facilityCode The facilityCode to set.
	 */
	public void setFacilityCode(String facilityCode)
	{
		if (this.facilityCode == null || !this.facilityCode.equals(facilityCode))
		{
			markModified();
		}
		this.facilityCode = facilityCode;
	}

	/**
	 * 
	 * @return Returns the juvenile.
	 */
	public Juvenile getJuvenile()
	{
		initJuvenile();
		return juvenile;
	}	

	/**
	 * 
	 * @return Returns the juvenileNumber.
	 */
	public String getJuvenileNumber()
	{
		fetch();
		return juvenileNumber;
	}

	/**
	 * 
	 * @param juvenileNumber The juvenileNumber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber)
	{
		if (this.juvenileNumber == null || !this.juvenileNumber.equals(juvenileNumber))
		{
			markModified();
		}
		this.juvenileNumber = juvenileNumber;
	}

	/**
	 * 
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber()
	{
		fetch();
		return referralNumber;
	}

	/**
	 * 
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber)
	{

		if (this.referralNumber == null || !this.referralNumber.equals(referralNumber))
		{
			markModified();
		}
		this.referralNumber = referralNumber;
	}
	
	/**
	 * 
	 * @return Returns the relocationDate.
	 */
	public Date getRelocationDate()
	{
		fetch();
		return relocationDate;
	}

	/**
	 * 
	 * @param relocationDate The relocationDate to set.
	 */
	public void setRelocationDate(Date relocationDate)
	{
		if (this.relocationDate == null || !this.relocationDate.equals(relocationDate))
		{
			markModified();
		}
		this.relocationDate = relocationDate;
	}
	
	/**
	 * setter with integer
	 * @param relocationDate
	 */
	public void setRelocationDate( Integer relocationDate)
	{
		Date calculatedDate = null;
		try {
			if(relocationDate!=null){
				calculatedDate = DateUtil.IntToDate(relocationDate, DateUtil.DATE_FMT_2);
				markModified();
			}
		} catch (ParseRuntimeException e) {
			e.printStackTrace();
		}
		this.relocationDate = calculatedDate;
	}
	/**
	 * 
	 * @return Returns the relocationTime.
	 */
	public String getRelocationTime()
	{
		fetch();
		return relocationTime;
	}

	/**
	 * 
	 * @param relocationTime The relocationTime to set.
	 */
	public void setRelocationTime(String relocationTime)
	{
		if (this.relocationTime == null || !this.relocationTime.equals(relocationTime))
		{
			markModified();
		}
		this.relocationTime = relocationTime;
	}

	/**
	 * 
	 * @return Returns the nextHearingDate.
	 */
	public Date getNextHearingDate()
	{
		fetch();
		return nextHearingDate;
	}

	/**
	 * 
	 * @param nextHearingDate The nextHearingDate to set.
	 */
	public void setNextHearingDate(Date nextHearingDate)
	{
		if (this.nextHearingDate == null || !this.nextHearingDate.equals(nextHearingDate))
		{
			markModified();
		}
		this.nextHearingDate = nextHearingDate;
	}
	
	/**
	 * setter with integer
	 * @param nextHearingDate
	 */
	public void setNextHearingDate( Integer nextHearingDate)
	{
		Date calculatedDate = null;
		try {
			if(nextHearingDate!=null){
				calculatedDate = DateUtil.IntToDate(nextHearingDate, DateUtil.DATE_FMT_2);	
				markModified();
			}
		} catch (ParseRuntimeException e) {
			e.printStackTrace();
		}
		this.nextHearingDate = calculatedDate;
	}	/**
	 * 
	 * @return Returns the probableCauseDate.
	 */
	public Date getProbableCauseDate()
	{
		fetch();
		return probableCauseDate;
	}

	/**
	 * 
	 * @param probableCauseDate The probableCauseDate to set.
	 */
	public void setProbableCauseDate(Date probableCauseDate)
	{
		if (this.probableCauseDate == null || !this.probableCauseDate.equals(probableCauseDate))
		{
			markModified();
		}
		this.probableCauseDate = probableCauseDate;
	}
	
	/**
	 * setter with integer
	 * @param probableCauseDate
	 */
	public void setProbableCauseDate( Integer probableCauseDate)
	{
		Date calculatedDate = null;
		try {
			if(probableCauseDate!=null){
				calculatedDate = DateUtil.IntToDate(probableCauseDate, DateUtil.DATE_FMT_2);
				markModified();
			}
		} catch (ParseRuntimeException e) {
			e.printStackTrace();
		}
		this.probableCauseDate = calculatedDate;
	}
	

	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 */
	public void setJuvenileId(String juvenileId)
	{
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId))
		{
			markModified();
		}
		this.juvenileId = juvenileId;
	}

	/**
	 * Get the reference value to class :: pd.juvenile.Juvenile
	 */
	public String getJuvenileId()
	{
		fetch();
		return juvenileId;
	}


	/**
	 * set the type reference for class member juvenile
	 */
	public void setJuvenile(Juvenile juvenile)
	{
		setJuvenileId("" + juvenile.getOID());
		this.juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenile).getObject();
	}
	

	/**
	 * @param lcDate the lcDate to set
	 */
	public void setLcDate(Date lcDate) {
		if (this.lcDate == null || !this.lcDate.equals(lcDate))
		{
			markModified();
		}
		this.lcDate = lcDate;
	}

	/**
	 * @return the lcDate
	 */
	public Date getLcDate() {
		fetch();
		return lcDate;
	}

	/**
	 * @param lcUser the lcUser to set
	 */
	public void setLcUser(String lcUser) {
		if (this.lcUser == null || !this.lcUser.equals(lcUser))
		{
			markModified();
		}
		this.lcUser = lcUser;
	}

	/**
	 * @return the lcUser
	 */
	public String getLcUser() {
		fetch();
		return lcUser;
	}

	/**
	 * @param bookingSupervisionNum the bookingSupervisionNum to set
	 */
	public void setBookingSupervisionNum(String bookingSupervisionNum) {
		if (this.bookingSupervisionNum == null || !this.bookingSupervisionNum.equals(bookingSupervisionNum))
		{
			markModified();
		}
		this.bookingSupervisionNum = bookingSupervisionNum;
	}

	/**
	 * @return the bookingSupervisionNum
	 */
	public String getBookingSupervisionNum() {
		fetch();
		return bookingSupervisionNum;
	}

	/**
	 * @param headerFacility the headerFacility to set
	 */
	public void setHeaderFacility(String headerFacility) {
		if (this.headerFacility == null || !this.headerFacility.equals(headerFacility))
		{
			markModified();
		}
		this.headerFacility = headerFacility;
	}

	/**
	 * @return the headerFacility
	 */
	public String getHeaderFacility() {
		return headerFacility;
	}

	/**
	 * @param lcTime the lcTime to set
	 */
	public void setLcTime(Date lcTime) {
		if (this.lcTime == null || !this.lcTime.equals(lcTime))
		{
			markModified();
		}
		this.lcTime = lcTime;
	}

	/**
	 * @return the lcTime
	 */
	public Date getLcTime() {
	     fetch();
	     return lcTime;
	}

	/**
	 * @return the tjpcseqnum
	 */
	public String getTjpcseqnum() {
		fetch();
		return tjpcseqnum;
	}

	/**
	 * @param tjpcseqnum the tjpcseqnum to set
	 */
	public void setTjpcseqnum(String tjpcseqnum) {
		if(this.tjpcseqnum==null || !this.tjpcseqnum.equals(tjpcseqnum))
		{
			markModified();
		}
		this.tjpcseqnum = tjpcseqnum;
	}
	
	
	/**
	 * 
	 * @return Returns the facilityStatus.
	 */
	public String getFacilityStatus() {
		fetch();
		return facilityStatus;
	}

	/**
	 * 
	 * @param facilityStatus The facilityStatus to set.
	 */
	public void setFacilityStatus(String facilityStatus) {
		if (this.facilityStatus == null || !this.facilityStatus.equals(facilityStatus))
		{
			markModified();
		}
		this.facilityStatus = facilityStatus;
	}

	/**
	 * 
	 * @return Returns the lastSequenceNumber.
	 */
	public String getLastSequenceNumber() {
		fetch();
		return lastSequenceNumber;
	}

	/**
	 * 
	 * @param lastSequenceNumber The lastSequenceNumber to set.
	 */
	public void setLastSequenceNumber(String lastSequenceNumber) {
		if (this.lastSequenceNumber == null || !this.lastSequenceNumber.equals(lastSequenceNumber))
		{
			markModified();
		}
		this.lastSequenceNumber = lastSequenceNumber;
	}


	/**
	 * 
	 * @return
	 */
	public String getHighestSeqNumberInUse()
	{
	    fetch();
	    return highestSeqNumberInUse;
	}

	/**
	 * 
	 * @param highestSeqNumberInUse
	 */
	public void setHighestSeqNumberInUse(String highestSeqNumberInUse)
	{
	    this.highestSeqNumberInUse = highestSeqNumberInUse;
	}
	
	public String getReleaseDecisionStatus()
	{
	    fetch();
	    return releaseDecisionStatus;
	}


	public void setReleaseDecisionStatus(String releaseDecisionStatus)
	{
	    this.releaseDecisionStatus = releaseDecisionStatus;
	}
	public String getDistReleaseDecisionStatus()
	{
	    fetch();
	    return distReleaseDecisionStatus;
	}


	public void setDistReleaseDecisionStatus(String distReleaseDecisionStatus)
	{
	    this.distReleaseDecisionStatus = distReleaseDecisionStatus;
	}


	public String getFacility()
	{
	    fetch();
	    return facility;
	}


	public void setFacility(String facility)
	{
	    this.facility = facility;
	}

	
}
