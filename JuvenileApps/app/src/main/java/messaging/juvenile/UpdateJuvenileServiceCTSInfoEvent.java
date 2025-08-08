package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileServiceCTSInfoEvent extends RequestEvent
{


    private String caseNum;
    private int courtDate;
    private String courtNum;
    private String crossRegionUpdate;
    private String defendant;
    private String juvenileNum;
    private int lcDate;
    private String lcTime;
    private String lcUser;
    private String nameType;
    private int paperExpirationDate;
    private String phone;
    private String pltName;
    private String state;
    private String streetName;
    private String streetNum;
    private String subpoenaForInd;
    private String trackingNum;
    private String zip;
    private String serveName; //83496
    private String jims2LcUser;
    /**
     * @return the caseNum
     */
    public String getCaseNum()
    {
        return caseNum;
    }
    /**
     * @param caseNum the caseNum to set
     */
    public void setCaseNum(String caseNum)
    {
        this.caseNum = caseNum;
    }
    /**
     * @return the courtDate
     */
    public int getCourtDate()
    {
        return courtDate;
    }
    /**
     * @param courtDate the courtDate to set
     */
    public void setCourtDate(int courtDate)
    {
        this.courtDate = courtDate;
    }
    /**
     * @return the courtNum
     */
    public String getCourtNum()
    {
        return courtNum;
    }
    /**
     * @param courtNum the courtNum to set
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
        return crossRegionUpdate;
    }
    /**
     * @param crossRegionUpdate the crossRegionUpdate to set
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
        return defendant;
    }
    /**
     * @param defendant the defendant to set
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
        return juvenileNum;
    }
    /**
     * @param juvenileNum the juvenileNum to set
     */
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    /**
     * @return the lcDate
     */
    public int getLcDate()
    {
        return lcDate;
    }
    /**
     * @param lcDate the lcDate to set
     */
    public void setLcDate(int lcDate)
    {
        this.lcDate = lcDate;
    }
    /**
     * @return the lcTime
     */
    public String getLcTime()
    {
        return lcTime;
    }
    /**
     * @param lcTime the lcTime to set
     */
    public void setLcTime(String lcTime)
    {
        this.lcTime = lcTime;
    }
    /**
     * @return the lcUser
     */
    public String getLcUser()
    {
        return lcUser;
    }
    /**
     * @param lcUser the lcUser to set
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
        return nameType;
    }
    /**
     * @param nameType the nameType to set
     */
    public void setNameType(String nameType)
    {
        this.nameType = nameType;
    }
    /**
     * @return the paperExpirationDate
     */
    public int getPaperExpirationDate()
    {
        return paperExpirationDate;
    }
    /**
     * @param paperExpirationDate the paperExpirationDate to set
     */
    public void setPaperExpirationDate(int paperExpirationDate)
    {
        this.paperExpirationDate = paperExpirationDate;
    }
    /**
     * @return the phone
     */
    public String getPhone()
    {
        return phone;
    }
    /**
     * @param phone the phone to set
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
        return pltName;
    }
    /**
     * @param pltName the pltName to set
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
        return state;
    }
    /**
     * @param state the state to set
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
        return streetName;
    }
    /**
     * @param streetName the streetName to set
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
        return streetNum;
    }
    /**
     * @param streetNum the streetNum to set
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
        return subpoenaForInd;
    }
    /**
     * @param subpoenaForInd the subpoenaForInd to set
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
        return trackingNum;
    }
    /**
     * @param trackingNum the trackingNum to set
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
        return zip;
    }
    /**
     * @param zip the zip to set
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
        return jims2LcUser;
    }
    /**
     * @param jims2LcUser the jims2LcUser to set
     */
    public void setJims2LcUser(String jims2LcUser)
    {
        this.jims2LcUser = jims2LcUser;
    }
    public String getServeName()
    {
	return serveName;
    }
    public void setServeName(String serveName)
    {
	this.serveName = serveName;
    }
}
