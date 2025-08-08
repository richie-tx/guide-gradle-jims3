package pd.juvenile;

import java.util.Date;

import mojo.km.persistence.PersistentObject;

public class JuvenileServiceCTSInfo extends PersistentObject
{

    private String caseNum;
    private Date courtDate;
    private String courtNum;
    private String crossRegionUpdate;
    private String defendant;
    private String juvenileNum;
    private Date lcDate;
    private Date lcTime;
    private String lcUser;
    private String nameType;
    private Date paperExpirationDate;
    private String phone;
    private String pltName;
    private String state;
    private String streetName;
    private String streetNum;
    private String subpoenaForInd;
    private String trackingNum;
    private String zip;
    private String jims2LcUser;
    private String serveName; //83496

    /**
     * @return the caseNum
     */
    public String getCaseNum()
    {
	fetch();
	return caseNum;
    }

    /**
     * @param caseNum
     *            the caseNum to set
     */
    public void setCaseNum(String caseNum)
    {
	this.caseNum = caseNum;
    }

    /**
     * @return the courtDate
     */
    public Date getCourtDate()
    {
	fetch();
	return courtDate;
    }

    /**
     * @param courtDate
     *            the courtDate to set
     */
    public void setCourtDate(Date courtDate)
    {
	this.courtDate = courtDate;
    }

    /**
     * @return the courtNum
     */
    public String getCourtNum()
    {
	fetch();
	return courtNum;
    }

    /**
     * @param courtNum
     *            the courtNum to set
     */
    public void setCourtNum(String courtNum)
    {
	this.courtNum = courtNum;
    }

    /**
     * @return the crossRegionUpdate
     */
    public String getCrossRegionUpdate()
    {
	fetch();
	return crossRegionUpdate;
    }

    /**
     * @param crossRegionUpdate
     *            the crossRegionUpdate to set
     */
    public void setCrossRegionUpdate(String crossRegionUpdate)
    {
	this.crossRegionUpdate = crossRegionUpdate;
    }

    /**
     * @return the defendant
     */
    public String getDefendant()
    {
	fetch();
	return defendant;
    }

    /**
     * @param defendant
     *            the defendant to set
     */
    public void setDefendant(String defendant)
    {
	this.defendant = defendant;
    }

    /**
     * @return the juvenileNum
     */
    public String getJuvenileNum()
    {
	fetch();
	return juvenileNum;
    }

    /**
     * @param juvenileNum
     *            the juvenileNum to set
     */
    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
    }

    /**
     * @return the lcDate
     */
    public Date getLcDate()
    {
	fetch();
	return lcDate;
    }

    /**
     * @param lcDate
     *            the lcDate to set
     */
    public void setLcDate(Date lcDate)
    {
	this.lcDate = lcDate;
    }

    /**
     * @return the lcUser
     */
    public String getLcUser()
    {
	fetch();
	return lcUser;
    }

    /**
     * @param lcUser
     *            the lcUser to set
     */
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    /**
     * @return the nameType
     */
    public String getNameType()
    {
	fetch();
	return nameType;
    }

    /**
     * @param nameType
     *            the nameType to set
     */
    public void setNameType(String nameType)
    {
	this.nameType = nameType;
    }

    /**
     * @return the paperExpirationDate
     */
    public Date getPaperExpirationDate()
    {
	fetch();
	return paperExpirationDate;
    }

    /**
     * @param paperExpirationDate
     *            the paperExpirationDate to set
     */
    public void setPaperExpirationDate(Date paperExpirationDate)
    {
	this.paperExpirationDate = paperExpirationDate;
    }

    /**
     * @return the phone
     */
    public String getPhone()
    {
	fetch();
	return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone)
    {
	this.phone = phone;
    }

    /**
     * @return the pltName
     */
    public String getPltName()
    {
	fetch();
	return pltName;
    }

    /**
     * @param pltName
     *            the pltName to set
     */
    public void setPltName(String pltName)
    {
	this.pltName = pltName;
    }

    /**
     * @return the state
     */
    public String getState()
    {
	fetch();
	return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state)
    {
	this.state = state;
    }

    /**
     * @return the streetName
     */
    public String getStreetName()
    {
	fetch();
	return streetName;
    }

    /**
     * @param streetName
     *            the streetName to set
     */
    public void setStreetName(String streetName)
    {
	this.streetName = streetName;
    }

    /**
     * @return the streetNum
     */
    public String getStreetNum()
    {
	fetch();
	return streetNum;
    }

    /**
     * @param streetNum
     *            the streetNum to set
     */
    public void setStreetNum(String streetNum)
    {
	this.streetNum = streetNum;
    }

    /**
     * @return the subpoenaForInd
     */
    public String getSubpoenaForInd()
    {
	fetch();
	return subpoenaForInd;
    }

    /**
     * @param subpoenaForInd
     *            the subpoenaForInd to set
     */
    public void setSubpoenaForInd(String subpoenaForInd)
    {
	this.subpoenaForInd = subpoenaForInd;
    }

    /**
     * @return the trackingNum
     */
    public String getTrackingNum()
    {
	fetch();
	return trackingNum;
    }

    /**
     * @param trackingNum
     *            the trackingNum to set
     */
    public void setTrackingNum(String trackingNum)
    {
	this.trackingNum = trackingNum;
    }

    /**
     * @return the zip
     */
    public String getZip()
    {
	fetch();
	return zip;
    }

    /**
     * @param zip
     *            the zip to set
     */
    public void setZip(String zip)
    {
	this.zip = zip;
    }

    /**
     * @return the jims2LcUser
     */
    public String getJims2LcUser()
    {
	fetch();
	return jims2LcUser;
    }

    /**
     * @param jims2LcUser
     *            the jims2LcUser to set
     */
    public void setJims2LcUser(String jims2LcUser)
    {
	this.jims2LcUser = jims2LcUser;
    }

    public Date getLcTime()
    {
	fetch();
	return lcTime;
    }

    public void setLcTime(Date lcTime)
    {
	this.lcTime = lcTime;
    }

    public String getServeName()
    {
	fetch();
	return serveName;
    }

    public void setServeName(String serveName)
    {
	this.serveName = serveName;
    }
}
