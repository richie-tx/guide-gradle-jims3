package pd.juvenilecase.referral;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import naming.PDJuvenileCaseConstants;

import messaging.juvenile.reply.JJSOffenseResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.util.DateUtil;
import pd.codetable.criminal.JuvenileOffenseCode;

/**
 * @roseuid 42A9A0210002
 */
public class JJSOffense extends PersistentObject
{
    private String offenseDescription;
    private String juvenileNum;
    private Date offenseDate;
    /**
     * Properties for offenseCode
     * 
     * @referencedType pd.codetable.criminal.JuvenileOffenseCode
     * @detailerDoNotGenerate true
     */
    private JuvenileOffenseCode offenseCode = null;
    private String offenseCodeId;
    private String sequenceNum;
    private String referralNum;

    private String offenseCategory;
    private String offenseReportGroup;
    private String offenseNumericCode;
    private String severity;
    private String catagory;
    private String citationCode;
    private String citationSource;

    private String investigationNumber;

    //US 71173
    private String keyMapLocation;

    //US 71177
    private String offenseID;
    //US 70523
    private String offenseStreetNum;
    private String offenseStreetName;
    private String offenseAptNum;
    private String offenseCity;
    private String offenseState;
    private String offenseZip;
    private String weaponType;
    private String cjisNum;
    private Date arrestDate;
    private String arrestTime;
    private String lcUser;
    private Date lcDate;
    private Date lcTime;
    private String chargeSequenceNum;
    private String onCampOffense;
    private String onCampDistrict;
    private String onCampSchool;
    private String recType;
    private String delComments;
    
    //US 187922
    private String tjpcseqnum;

    /**
     * @roseuid 42A9A0210002
     */
    public JJSOffense()
    {
    }

    /**
     * Access method for the juvenileNum property.
     * 
     * @return the current value of the juvenileNum property
     */
    public String getJuvenileNum()
    {
	fetch();
	return juvenileNum;
    }

    /**
     * Sets the value of the juvenileNum property.
     * 
     * @param aJuvenileNum
     *            the new value of the juvenileNum property
     */
    public void setJuvenileNum(String aJuvenileNum)
    {
	this.juvenileNum = aJuvenileNum;
    }

    /**
     * Access method for the referralNum property.
     * 
     * @return the current value of the referralNum property
     */
    public String getReferralNum()
    {
	fetch();
	return referralNum;
    }

    /**
     * Sets the value of the referralNum property.
     * 
     * @param aReferralNum
     *            the new value of the referralNum property
     */
    public void setReferralNum(String aReferralNum)
    {
	this.referralNum = aReferralNum;
    }

    /**
     * Access method for the sequenceNum property.
     * 
     * @return the current value of the sequenceNum property
     */
    public String getSequenceNum()
    {
	fetch();
	return sequenceNum;
    }

    /**
     * Sets the value of the sequenceNum property.
     * 
     * @param aSequenceNum
     *            the new value of the sequenceNum property
     */
    public void setSequenceNum(String aSequenceNum)
    {
	this.sequenceNum = aSequenceNum;
    }

    /**
     * Access method for the offenseCodeId property.
     * 
     * @return the current value of the offenseCodeId property
     */
    public String getOffenseCodeId()
    {
	fetch();
	return offenseCodeId;
    }

    /**
     * Sets the value of the offenseCode property.
     * 
     * @param aOffenseCodeId
     *            the new value of the offenseCodeId property
     */
    public void setOffenseCodeId(String aOffenseCodeId)
    {
	this.offenseCodeId = aOffenseCodeId;
    }

    /**
     * Access method for the offenseDate property.
     * 
     * @return the current value of the offenseDate property
     */
    public Date getOffenseDate()
    {
	fetch();
	return offenseDate;
    }

    /**
     * Sets the value of the offenseDate property.
     * 
     * @param aOffenseDate
     *            the new value of the offenseDate property
     */
    public void setOffenseDate(Date aOffenseDate)
    {
	this.offenseDate = aOffenseDate;
    }

    /**
     * Access method for the offenseDescription property.
     * 
     * @return the current value of the offenseDescription property
     */
    public String getOffenseDescription()
    {
	fetch();
	//US 71184		
	if (this.offenseCode == null)
	    initOffenseCode();
	this.offenseDescription = this.offenseCode.getShortDescription();

	return offenseDescription;
    }

    /**
     * Sets the value of the offenseDescription property.
     * 
     * @param aOffenseDescription
     *            the new value of the offenseDescription property
     */
    public void setOffenseDescription(String aOffenseDescription)
    {
	this.offenseDescription = aOffenseDescription;
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.criminal.JuvenileOffenseCode
     */
    private void initOffenseCode()
    {
	if (offenseCode == null)
	{
	    try
	    {
		offenseCode = (JuvenileOffenseCode) JuvenileOffenseCode.find("offenseCode", offenseCodeId);
	    }
	    catch (Throwable t)
	    {
		t.printStackTrace();
	    }
	}
    }

    /**
     * Gets referenced type pd.codetable.criminal.JuvenileOffenseCode
     * 
     * @return offenseCode
     */
    public JuvenileOffenseCode getOffenseCode()
    {
	initOffenseCode();
	return offenseCode;
    }

    /**
     * set the type reference for class member offenseCode
     * 
     * @param offenseCode
     */
    public void setOffenseCode(JuvenileOffenseCode aoffenseCode)
    {
	setOffenseCodeId("" + aoffenseCode.getOID());
	this.offenseCode = (JuvenileOffenseCode) new mojo.km.persistence.Reference(aoffenseCode).getObject();
    }

    /**
     * @roseuid 42A99B9802DD
     */
    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	return home.findAll(event, JJSOffense.class);
    }

    /**
     * @return
     * @param JJSOffense
     */
    static public JJSOffense find(String offenseID)
    {
	return (JJSOffense) new Home().find(offenseID, JJSOffense.class);
    }

    /**
     * @return Returns the offenseCategory.
     */
    public String getOffenseCategory()
    {
	fetch();
	//US 71184		
	if (this.offenseCode != null)
	    initOffenseCode();
	this.offenseCategory = this.getOffenseCode().getCategory();

	return offenseCategory;
    }

    /**
     * @return Returns the offenseReportGroup.
     */
    public String getOffenseReportGroup()
    {
	fetch();
	if (this.offenseCode != null)
	    initOffenseCode();
	this.offenseReportGroup = this.getOffenseCode().getReportGroup();

	return offenseReportGroup;
    }

    /**
     * @param offenseCategory
     *            The offenseCategory to set.
     */
    public void setOffenseCategory(String offenseCategory)
    {
	this.offenseCategory = offenseCategory;
    }

    /**
     * @param offenseReportGroup
     *            The offenseReportGroup to set.
     */
    public void setOffenseReportGroup(String offenseReportGroup)
    {
	this.offenseReportGroup = offenseReportGroup;
    }

    public String getOffenseNumericCode()
    {
	fetch();
	if (this.offenseCode != null)
	    initOffenseCode();
	this.offenseNumericCode = this.getOffenseCode().getNumericCode();

	return offenseNumericCode;
    }

    public void setOffenseNumericCode(String offenseNumericCode)
    {
	this.offenseNumericCode = offenseNumericCode;
    }

    /**
     * @param attributeName
     * @param attributeValue
     * @return Iterator
     */
    public static Iterator findAll(String attributeName, String attributeValue)
    {
	IHome home = new Home();
	return home.findAll(attributeName, attributeValue, JJSOffense.class);
    }

    /**
     * @return Returns the severity.
     */
    public String getSeverity()
    {
	fetch();
	//US 71184		
	if (this.offenseCode == null)
	    initOffenseCode();
	this.severity = this.getOffenseCode().getSeverity();
	return severity;
    }

    /**
     * @param severity
     *            The severity to set.
     */
    public void setSeverity(String severity)
    {
	this.severity = severity;
    }

    /**
     * @return Returns the catagory.
     */
    public String getCatagory()
    {
	fetch();
	//US 71184		
	if (this.offenseCode == null)
	    initOffenseCode();
	this.catagory = this.getOffenseCode().getCategory();

	return catagory;
    }

    /**
     * @param catagory
     *            The catagory to set.
     */
    public void setCatagory(String catagory)
    {
	this.catagory = catagory;
    }

    /**
     * @return Returns the citationCode.
     */
    public String getCitationCode()
    {
	fetch();
	//US 71184		
	if (this.offenseCode == null)
	    initOffenseCode();
	this.citationCode = this.getOffenseCode().getCitationCode();

	return citationCode;
    }

    /**
     * @param citationCode
     *            The citationCode to set.
     */
    public void setCitationCode(String citationCode)
    {
	this.citationCode = citationCode;
    }

    /**
     * @return Returns the citationSource.
     */
    public String getCitationSource()
    {
	fetch();
	//US 71184		
	if (this.offenseCode == null)
	    initOffenseCode();
	this.citationSource = this.getOffenseCode().getCitationSource();

	return citationSource;
    }

    /**
     * @param citationSource
     *            The citationSource to set.
     */
    public void setCitationSource(String citationSource)
    {
	this.citationSource = citationSource;
    }

    /**
     * @return Returns the investigationNumber.
     */
    public String getInvestigationNumber()
    {
	fetch();
	return investigationNumber;
    }

    /**
     * @param investigationNumber
     *            The investigationNumber to set.
     */
    public void setInvestigationNumber(String investigationNumber)
    {
	this.investigationNumber = investigationNumber;
    }

    /**
     * @return Returns the v.
     */
    public String getKeyMapLocation()
    {
	fetch();
	return keyMapLocation;
    }

    /**
     * @param keyMapLocation
     *            The keyMapLocation to set.
     */
    public void setKeyMapLocation(String keyMapLocation)
    {
	this.keyMapLocation = keyMapLocation;
    }

    /**
     * @return the offenseID
     */
    public String getOffenseID()
    {
	return offenseID;
    }

    /**
     * @param offenseID
     *            the offenseID to set
     */
    public void setOffenseID(String offenseID)
    {
	this.offenseID = offenseID;
    }

    public String getCjisNum()
    {
	fetch();
	return cjisNum;
    }

    public void setCjisNum(String cjisNum)
    {
	this.cjisNum = cjisNum;
    }

    public String getOffenseStreetNum()
    {
	fetch();
	return offenseStreetNum;
    }

    public void setOffenseStreetNum(String offenseStreetNum)
    {
	this.offenseStreetNum = offenseStreetNum;
    }

    public String getOffenseStreetName()
    {
	fetch();
	return offenseStreetName;
    }

    public void setOffenseStreetName(String offenseStreetName)
    {
	this.offenseStreetName = offenseStreetName;
    }

    public String getOffenseAptNum()
    {
	fetch();
	return offenseAptNum;
    }

    public void setOffenseAptNum(String offenseAptNum)
    {
	this.offenseAptNum = offenseAptNum;
    }

    public String getOffenseCity()
    {
	fetch();
	return offenseCity;
    }

    public void setOffenseCity(String offenseCity)
    {
	this.offenseCity = offenseCity;
    }

    public String getOffenseState()
    {
	fetch();
	return offenseState;
    }

    public void setOffenseState(String offenseState)
    {
	this.offenseState = offenseState;
    }

    public String getOffenseZip()
    {
	fetch();
	return offenseZip;
    }

    public void setOffenseZip(String offenseZip)
    {
	this.offenseZip = offenseZip;
    }

    public String getWeaponType()
    {
	fetch();
	return weaponType;
    }

    public void setWeaponType(String weaponType)
    {
	this.weaponType = weaponType;
    }

    public Date getArrestDate()
    {
	fetch();
	return arrestDate;
    }

    public void setArrestDate(Date arrestDate)
    {
	this.arrestDate = arrestDate;
    }

    public String getArrestTime()
    {
	fetch();
	return arrestTime;
    }

    public void setArrestTime(String arrestTime)
    {
	this.arrestTime = arrestTime;
    }

    public String getLcUser()
    {
	fetch();
	return lcUser;
    }

    public void setLcUser(String lcUser)
    {
	if (this.lcUser == null || !this.lcUser.equals(lcUser))
	{
	    markModified();
	}
	this.lcUser = lcUser;
    }

    public Date getLcDate()
    {
	fetch();
	return lcDate;
    }

    public void setLcDate(Date lcDate)
    {
	if (this.lcDate == null || !this.lcDate.equals(lcDate))
	{
	    markModified();
	}
	this.lcDate = lcDate;
    }

    public Date getLcTime()
    {
	fetch();
	return lcTime;
    }

    public void setLcTime(Date lcTime)
    {
	if (this.lcTime == null || !this.lcTime.equals(lcTime))
	{
	    markModified();
	}
	this.lcTime = lcTime;
    }

    public String getChargeSequenceNum()
    {
	fetch();
	return chargeSequenceNum;
    }

    public void setChargeSequenceNum(String chargeSequenceNum)
    {
	this.chargeSequenceNum = chargeSequenceNum;
    }

    public String getOnCampOffense()
    {
	fetch();
	return onCampOffense;
    }

    public void setOnCampOffense(String onCampOffense)
    {
	if (this.onCampOffense == null || !this.onCampOffense.equals(onCampOffense))
	{
	    markModified();
	}
	this.onCampOffense = onCampOffense;
    }

    public String getOnCampDistrict()
    {
	fetch();
	return onCampDistrict;
    }

    public void setOnCampDistrict(String onCampDistrict)
    {
	if (this.onCampDistrict == null || !this.onCampDistrict.equals(onCampDistrict))
	{
	    markModified();
	}
	this.onCampDistrict = onCampDistrict;
    }

    public String getOnCampSchool()
    {
	fetch();
	return onCampSchool;
    }

    public void setOnCampSchool(String onCampSchool)
    {
	if (this.onCampSchool == null || !this.onCampSchool.equals(onCampSchool))
	{
	    markModified();
	}
	this.onCampSchool = onCampSchool;
    }

    public JJSOffenseResponseEvent valueObject()
    {

	JJSOffenseResponseEvent resp = new JJSOffenseResponseEvent();

	resp.setTopic(PDJuvenileCaseConstants.JUVENILE_OFFENSES_TOPIC);
	resp.setJuvenileNum(this.getJuvenileNum());
	resp.setOffDate(DateUtil.dateToString(this.getOffenseDate(), DateUtil.DATE_FMT_1));
	resp.setOffenseDate( this.getOffenseDate() );
	resp.setOffenseDescription(this.getOffenseDescription());
	resp.setReferralNum(this.getReferralNum());
	resp.setSequenceNum(this.getSequenceNum());
	resp.setCatagory(this.getCatagory());
	resp.setCitationCode(this.getCitationCode());
	resp.setCitationSource(this.getCitationSource());
	resp.setSequenceNum(this.getSequenceNum());
	resp.setChargeSequenceNum(this.getChargeSequenceNum());
	resp.setInvestigationNum(this.getInvestigationNumber());
	resp.setOffenseReportGroup(this.getOffenseReportGroup());
	resp.setOffenseCodeId(this.getOffenseCodeId());
	resp.setOffenseCode(this.getOffenseCode().getOffenseCode());
	resp.setOldoffenseCode(this.getOffenseCode().getOffenseCode());
	//resp.setSeveritySubtype(this.getOffenseCode().getSeveritySubtype()); 
	resp.setOffenseSeverity(this.getSeverity());
	resp.setKeyMapLocation(this.getKeyMapLocation());
	resp.setOffenseStreetNum(this.getOffenseStreetNum());
	resp.setOffenseStreetName(this.getOffenseStreetName());
	resp.setOffenseAptNum(this.getOffenseAptNum());
	resp.setOffenseCity(this.getOffenseCity());
	resp.setOffenseState(this.getOffenseState());
	resp.setOffenseZip(this.getOffenseZip());
	resp.setOnCampDistrict(this.getOnCampDistrict());
	resp.setOnCampOffense(this.getOnCampOffense());
	resp.setOnCampSchool(this.getOnCampSchool());
	resp.setWeaponType(this.getWeaponType());
	resp.setCjisNum(this.getCjisNum());
	resp.setArrestDate(DateUtil.dateToString(this.getArrestDate(), DateUtil.DATE_FMT_1));
	resp.setArrestTime(this.getArrestTime());
	resp.setLcUser(this.getLcUser());
	resp.setLcDate(this.getLcDate());
	resp.setOID(this.getOID());
	if (this.getLcTime() != null)
	{
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(this.getLcTime());
	    SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
	    String time = localDateFormat.format(cal.getTime());
	    resp.setLcTime(time);
	}

	return resp;

    }

    public String getRecType()
    {
	return recType;
    }

    public void setRecType(String recType)
    {
	this.recType = recType;
    }
    
    public String getdelComments()
    {
	return delComments;
    }

    public void setDelComments(String delComments)
    {
	this.delComments = delComments;
    }

    public String getTjpcseqnum()
    {
	return tjpcseqnum;
    }

    public void setTjpcseqnum(String tjpcseqnum)
    {
	this.tjpcseqnum = tjpcseqnum;
    }

}
