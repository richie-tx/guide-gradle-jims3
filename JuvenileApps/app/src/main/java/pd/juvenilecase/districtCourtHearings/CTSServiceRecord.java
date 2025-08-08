package pd.juvenilecase.districtCourtHearings;

import java.util.Date;
import java.util.Iterator;


import messaging.districtCourtHearings.UpdateCTSServiceRecordEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

public class CTSServiceRecord extends PersistentObject
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
    
    public CTSServiceRecord() {}
    
    public String getJuvenileNumber()
    {
	fetch();
        return juvenileNumber;
    }
    public void setJuvenileNumber(String juvenileNumber)
    {
	if ( this.juvenileNumber == null
		|| !this.juvenileNumber.equals(juvenileNumber) ) {
	    markModified();
	}
        this.juvenileNumber = juvenileNumber;
    }
    
    public String getPetitionNumber()
    {
	fetch();
        return petitionNumber;
    }
    public void setPetitionNumber(String petitionNumber)
    {
	if ( this.petitionNumber == null 
		|| !this.petitionNumber.equals(petitionNumber)) {
	    markModified();
	}
        this.petitionNumber = petitionNumber;
    }
    
    public Date getCourtDate()
    {
	fetch();
        return courtDate;
    }
    public void setCourtDate(Date courtDate)
    {
	if( this.courtDate == null ){
	    markModified();
	}
        this.courtDate = courtDate;
    }
    
    public Date getPaperExpirationDate()
    {
	fetch();
        return paperExpirationDate;
    }
    public void setPaperExpirationDate(Date paperExpirationDate)
    {
	if ( this.paperExpirationDate == null 
		|| !this.paperExpirationDate.equals(paperExpirationDate)) {
	    markModified();
	}
        this.paperExpirationDate = paperExpirationDate;
    }
    
    public String getCourtNumber()
    {
	fetch();
        return courtNumber;
    }
    public void setCourtNumber(String courtNumber)
    {
	if ( this.courtNumber == null 
		|| !this.courtNumber.equals(courtNumber)) {
	    markModified();
	}
        this.courtNumber = courtNumber;
    }
    
    public String getNameType()
    {
	fetch();
	return nameType;
    }
    public void setNameType(String nameType)
    {
	if ( this.nameType == null 
		|| !this.nameType.equals(nameType)) {
	    markModified();
	}
	this.nameType = nameType;
    }
    
    public String getSubpoenaFor()
    {
	fetch();
        return subpoenaFor;
    }
    public void setSubpoenaFor(String subpoenaFor)
    {

	if ( this.subpoenaFor == null 
		|| !this.subpoenaFor.equals(subpoenaFor)) {
	    markModified();
	}
        this.subpoenaFor = subpoenaFor;
    }
    
    public String getPhone()
    {
	fetch();
        return phone;
    }
    public void setPhone(String phone)
    {
	if ( this.phone == null 
		|| !this.phone.equals(phone)) {
	    markModified();
	}
        this.phone = phone;
    }
    
    public String getPlaintiff()
    {
	fetch();
        return plaintiff;
    }
    public void setPlaintiff(String plaintiff)
    {
	if ( this.plaintiff == null 
		|| !this.plaintiff.equals(plaintiff)) {
	    markModified();
	}
        this.plaintiff = plaintiff;
    }
    
    public String getState()
    {
	fetch();
        return state;
    }
    public void setState(String state)
    {
	if ( this.state == null 
		|| !this.state.equals(state)) {
	    markModified();
	}
        this.state = state;
    }
    
    public String getStreetNumber()
    {
	fetch();
        return streetNumber;
    }
    public void setStreetNumber(String streetNumber)
    {
	if ( this.streetNumber == null 
		|| !this.streetNumber.equals(streetNumber)) {
	    markModified();
	}
        this.streetNumber = streetNumber;
    }
    
    public String getStreet()
    {
	fetch();
        return street;
    }
    public void setStreet(String street)
    {
	if ( this.street == null 
		|| !this.street.equals(street)) {
	    markModified();
	}
        this.street = street;
    }
    
    public String getServName()
    {
	fetch();
        return servName;
    }
    public void setServName(String servName)
    {
	if ( this.servName == null 
		|| !this.servName.equals(servName)) {
	    markModified();
	}
        this.servName = servName;
    }
    
    public String getDefendant()
    {
	fetch();
        return defendant;
    }
    public void setDefendant(String defendant)
    {
	if ( this.defendant == null 
		|| !this.defendant.equals(defendant)) {
	    markModified();
	}
        this.defendant = defendant;
    }
    
    public String getTrackingNumber()
    {
	fetch();
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber)
    {

	if ( this.trackingNumber == null 
		|| !this.trackingNumber.equals(trackingNumber)) {
	    markModified();
	}
        this.trackingNumber = trackingNumber;
    }
    
    public String getZip()
    {
	fetch();
        return zip;
    }
    public void setZip(String zip)
    {
	if ( this.zip == null 
		|| !this.zip.equals(zip)) {
	    markModified();
	}
        this.zip = zip;
    }
    
    public String getCrossRegionUpdate()
    {
	fetch();
        return crossRegionUpdate;
    }
    public void setCrossRegionUpdate(String crossRegionUpdate)
    {
	if ( this.crossRegionUpdate == null 
		|| !this.crossRegionUpdate.equals(crossRegionUpdate)) {
	    markModified();
	}
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
    
    public void setCTSServiceRecord(UpdateCTSServiceRecordEvent record){
	this.juvenileNumber 		= record.getJuvenileNumber();
	this.petitionNumber 		= record.getPetitionNumber();
	this.courtDate	    		= record.getCourtDate();
	this.paperExpirationDate	= record.getPaperExpirationDate();
	this.courtNumber		= record.getCourtNumber();
	this.nameType			= record.getNameType();
	this.subpoenaFor		= record.getSubpoenaFor();
	this.phone			= record.getPhone();
	this.plaintiff			= record.getPlaintiff();
	this.state			= record.getState();
	this.streetNumber		= record.getStreetNumber();
	this.street			= record.getStreet();
	this.servName			= record.getServName();
	this.defendant			= record.getDefendant();
	this.trackingNumber		= record.getTrackingNumber();
	this.zip			= record.getZip();
	this.crossRegionUpdate		= record.getCrossRegionUpdate();
    }
    
    public static CTSServiceRecord find(String OID)
    {
	return (CTSServiceRecord) new Home().find(OID, CTSServiceRecord.class);
    }
      
    public static CTSServiceRecord find(String attributeName, String attributeValue) {
   	return (CTSServiceRecord ) new Home().find(attributeName, attributeValue, CTSServiceRecord.class);
    }
    
    public static Iterator findAll()
    {
	return new Home().findAll(CTSServiceRecord.class);
    }
    
    public static Iterator findAll(String attributeName, String attributeValue) {
	return new Home().findAll(attributeName, attributeValue, CTSServiceRecord.class);
    }
    
    
   
    

}
