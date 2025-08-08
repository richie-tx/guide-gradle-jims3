package messaging.districtCourtHearings;

import java.util.Date;

import ui.juvenilecase.districtCourtHearings.SubpoenaReportBean;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;

import mojo.km.messaging.RequestEvent;
import mojo.km.utilities.DateUtil;

public class UpdateCTSServiceRecordEvent extends RequestEvent
{
    private String juvenileNumber;
    private String petitionNumber;
    private Date courtDate;
    private Date paperExpirationDate;
    private String courtNumber;
    private String nameType;
    private String subpoenaFor;
    private String phone;
    private String plaintiff;
    private String state;
    private String streetNumber;
    private String street;
    private String servName;
    private String defendant;
    private String trackingNumber;
    private String zip;
    private String crossRegionUpdate;
    private Date lcDate;
    private Date lcTime;
    private String lcUser;
    
    
    

    public  UpdateCTSServiceRecordEvent () {}
    
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    public String getPetitionNumber()
    {
        return petitionNumber;
    }
    public void setPetitionNumber(String petitionNumber)
    {
        this.petitionNumber = petitionNumber;
    }
    public Date getCourtDate()
    {
        return courtDate;
    }
    public void setCourtDate(Date courtDate)
    {
        this.courtDate = courtDate;
    }
    public Date getPaperExpirationDate()
    {
        return paperExpirationDate;
    }
    public void setPaperExpirationDate(Date paperExpirationDate)
    {
        this.paperExpirationDate = paperExpirationDate;
    }
    public String getCourtNumber()
    {
        return courtNumber;
    }
    public void setCourtNumber(String courtNumber)
    {
        this.courtNumber = courtNumber;
    }
    public String getNameType()
    {
        return nameType;
    }
    public void setNameType(String nameType)
    {
        this.nameType = nameType;
    }
    public String getSubpoenaFor()
    {
        return subpoenaFor;
    }
    public void setSubpoenaFor(String subpoenaFor)
    {
        this.subpoenaFor = subpoenaFor;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getPlaintiff()
    {
        return plaintiff;
    }
    public void setPlaintiff(String plaintiff)
    {
        this.plaintiff = plaintiff;
    }
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    public String getStreetNumber()
    {
        return streetNumber;
    }
    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
    }
    public String getStreet()
    {
        return street;
    }
    public void setStreet(String street)
    {
        this.street = street;
    }
    public String getServName()
    {
        return servName;
    }
    public void setServName(String servName)
    {
        this.servName = servName;
    }
    public String getDefendant()
    {
        return defendant;
    }
    public void setDefendant(String defendant)
    {
        this.defendant = defendant;
    }
    public String getTrackingNumber()
    {
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber)
    {
        this.trackingNumber = trackingNumber;
    }
    public String getZip()
    {
        return zip;
    }
    public void setZip(String zip)
    {
        this.zip = zip;
    }
    public String getCrossRegionUpdate()
    {
        return crossRegionUpdate;
    }
    public void setCrossRegionUpdate(String crossRegionUpdate)
    {
        this.crossRegionUpdate = crossRegionUpdate;
    }
    
    public Date getLcDate()
    {
        return lcDate;
    }

    public void setLcDate(Date lcDate)
    {
        this.lcDate = lcDate;
    }

    public Date getLcTime()
    {
        return lcTime;
    }

    public void setLcTime(Date lcTime)
    {
        this.lcTime = lcTime;
    }

    public String getLcUser()
    {
        return lcUser;
    }

    public void setLcUser(String lcUser)
    {
        this.lcUser = lcUser;
    }
    
    public void setCTSServiceRecordEvent(SubpoenaReportBean rptBean, CourtHearingForm form ){
   	this.juvenileNumber 		= rptBean.getJuvenileNumber();
   	this.courtDate			= DateUtil.stringToDate(form.getCourtDate(), "MM/dd/yyyy");
   	this.paperExpirationDate	= DateUtil.stringToDate(form.getCourtDate(), "MM/dd/yyyy");
   	this.petitionNumber 		= rptBean.getPetitionNum();
   	this.courtNumber		= rptBean.getCourtId();
   	this.nameType			= "D";
   	this.subpoenaFor		= form.getQueryString();
   	if(form.getMemberContact()!= null && form.getMemberContact().getContactPhoneNumber()!=null){
		this.phone 		= form.getMemberContact().getContactPhoneNumber().getFormattedPhoneNumber().replaceAll("-", "");
	}
   	this.plaintiff			= rptBean.getPlaintiffName();
   	this.state			= "TX";
   	if (rptBean.getMemberName() != null ){
   	    this.servName 		= rptBean.getMemberName().getFormattedName();
   	}
   	if(rptBean.getJuvenileAddress()!=null){
	    this.streetNumber		= rptBean.getJuvenileAddress().getStreetNum();
	    this.street 		= rptBean.getJuvenileAddress().getStreetName();
	    this.zip			= rptBean.getJuvenileAddress().getZipCode();
	}
	this.setCrossRegionUpdate("Y");
	if(this.subpoenaFor!=null && this.subpoenaFor.equalsIgnoreCase("J")){
	    this.servName		= rptBean.getJuvenileName()+ ", RESPONDENT"; //83496
	}
   
   	this.defendant			= rptBean.getJuvenileName();
   	this.trackingNumber		= rptBean.getTrackingNum();
   
   	
    }

}
