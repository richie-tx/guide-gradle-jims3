package pd.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import messaging.juvenilecase.reply.FacilityAdmitReasonResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.criminal.JuvenileAdmitReasons;
import pd.km.util.Name;

public class JJSFacilityReport extends PersistentObject
{
    private Date admitDate;
    private String admitTime;
    private String admitReason;
    private String bookingSupervisionNum;
    private String currentSupervisionNum;
    private String hDetentionStatus;
    private String headerFacility;
    private String juvenileNumber;
    private int lastSeqNum;
    private String referralNumber;
    private String juvenileLname;
    private String juvenileFname;
    private String juvenileMname;
    private Date dateOfBirth;
    private String originalRace;
    private String sexId;
    private String raceId;
    private String currentOffense;
    private String detainedFacility;
    private String facilityCd;
    private String floorNum;
    private String roomNum;
    private String secureStatus;
    private String lockerNumber;
    private String seqNum;    
    private String unit;
    private String mou;
    private String reasonCode;
    private String specialAttention;
    private Date nextCourtDate;;
    private Date nextDetDate;
    private String officerId;
    private String officerLname;
    private String officerFname;
    private String officerMname;
    private String recType;
    private String risk;
    private String needs;
    static HashMap codeMap;
    private static HashMap<String,String> admitReasonMap;
    private Date releaseDate;
    
	   /**
	* @return 
	* @param event
	*/
    static public Iterator findAll(IEvent event)
    {
	initObservationCode();
	initAdmitReasons();
	IHome home = new Home();
	return home.findAll(event, JJSFacilityReport.class);
    }

    
    public Date getAdmitDate()
    {
	fetch();
        return admitDate;
    }
    public void setAdmitDate(Date admitDate)
    {
        this.admitDate = admitDate;
    }
    public String getAdmitTime()
    {
	fetch();
        return admitTime;
    }
    public void setAdmitTime(String admitTime)
    {
        this.admitTime = admitTime;
    }
    public String getAdmitReason()
    {
	fetch();
        return admitReason;
    }

    public void setAdmitReason(String admitReason)
    {
        this.admitReason = admitReason;
    }

    public String getBookingSupervisionNum()
    {
	fetch();
        return bookingSupervisionNum;
    }
    public void setBookingSupervisionNum(String bookingSupervisionNum)
    {
        this.bookingSupervisionNum = bookingSupervisionNum;
    }
    public String getCurrentSupervisionNum()
    {
	fetch();
        return currentSupervisionNum;
    }
    public void setCurrentSupervisionNum(String currentSupervisionNum)
    {
        this.currentSupervisionNum = currentSupervisionNum;
    }
    public String gethDetentionStatus()
    {
	fetch();
        return hDetentionStatus;
    }
    public void sethDetentionStatus(String hDetentionStatus)
    {
        this.hDetentionStatus = hDetentionStatus;
    }
    public String getHeaderFacility()
    {
	fetch();
        return headerFacility;
    }
    public void setHeaderFacility(String headerFacility)
    {
        this.headerFacility = headerFacility;
    }
    public String getJuvenileNumber()
    {
	fetch();
        return juvenileNumber;
    }
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    public int getLastSeqNum()
    {
	fetch();
        return lastSeqNum;
    }
    public void setLastSeqNum(int lastSeqNum)
    {
        this.lastSeqNum = lastSeqNum;
    }
    public String getReferralNumber()
    {
	fetch();
        return referralNumber;
    }
    public void setReferralNumber(String referralNumber)
    {
        this.referralNumber = referralNumber;
    }
    public String getJuvenileLname()
    {
	fetch();
        return juvenileLname;
    }
    public void setJuvenileLname(String juvenileLname)
    {
        this.juvenileLname = juvenileLname;
    }
    public String getJuvenileFname()
    {
	fetch();
        return juvenileFname;
    }
    public void setJuvenileFname(String juvenileFname)
    {
        this.juvenileFname = juvenileFname;
    }
    public String getJuvenileMname()
    {
	fetch();
        return juvenileMname;
    }
    public void setJuvenileMname(String juvenileMname)
    {
        this.juvenileMname = juvenileMname;
    }
    public Date getDateOfBirth()
    {
	fetch();
        return dateOfBirth;
    }
    public void setDateOfBirth(Date birthDate)
    {
        this.dateOfBirth = birthDate;
    }
    public String getOriginalRace()
    {
	fetch();
        return originalRace;
    }
    public void setOriginalRace(String originalRace)
    {
        this.originalRace = originalRace;
    }
    public String getSexId()
    {
	fetch();
        return sexId;
    }
    public void setSexId(String sexId)
    {
        this.sexId = sexId;
    }
    public String getRaceId()
    {
	fetch();
        return raceId;
    }

    public void setRaceId(String raceId)
    {
        this.raceId = raceId;
    }

    public String getCurrentOffense()
    {
	fetch();
        return currentOffense;
    }
    public void setCurrentOffense(String currentOffense)
    {
        this.currentOffense = currentOffense;
    }
    public String getDetainedFacility()
    {
	fetch();
        return detainedFacility;
    }
    public void setDetainedFacility(String detainedFacility)
    {
        this.detainedFacility = detainedFacility;
    }
    public String getFacilityCd()
    {
	fetch();
        return facilityCd;
    }
    public void setFacilityCd(String facilityCd)
    {
        this.facilityCd = facilityCd;
    }
    public String getFloorNum()
    {
	fetch();
        return floorNum;
    }
    public void setFloorNum(String floorNum)
    {
        this.floorNum = floorNum;
    }
    public String getRoomNum()
    {
	fetch();
        return roomNum;
    }
    public void setRoomNum(String roomNum)
    {
        this.roomNum = roomNum;
    }
    public String getSecureStatus()
    {
	fetch();
        return secureStatus;
    }
    public void setSecureStatus(String secureStatus)
    {
        this.secureStatus = secureStatus;
    }
    public String getLockerNumber()
    {
	fetch();
        return lockerNumber;
    }
    public void setLockerNumber(String lockerNumber)
    {
        this.lockerNumber = lockerNumber;
    }
    public String getSeqNum()
    {
	fetch();
        return seqNum;
    }
    public void setSeqNum(String seqNum)
    {
        this.seqNum = seqNum;
    }
    public String getUnit()
    {
	fetch();
        return unit;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    public String getMou()
    {
	fetch();
        return mou;
    }
    public void setMou(String mou)
    {
        this.mou = mou;
    }
    public String getReasonCode()
    {
	fetch();
        return reasonCode;
    }
    public void setReasonCode(String reasonCode)
    {
        this.reasonCode = reasonCode;
    }
    public String getSpecialAttention()
    {
	fetch();
        return specialAttention;
    }
    public void setSpecialAttention(String specialAttention)
    {
        this.specialAttention = specialAttention;
    }
    public Date getNextCourtDate()
    {
	fetch();
        return nextCourtDate;
    }
    public void setNextCourtDate(Date nextCourtDate)
    {
        this.nextCourtDate = nextCourtDate;
    }
    public Date getNextDetDate()
    {
	fetch();
        return nextDetDate;
    }
    public void setNextDetDate(Date nextDetDate)
    {
        this.nextDetDate = nextDetDate;
    }
    public String getOfficerId()
    {
	fetch();
        return officerId;
    }
    public void setOfficerId(String officerId)
    {
        this.officerId = officerId;
    }
    public String getOfficerLname()
    {
	fetch();
        return officerLname;
    }
    public void setOfficerLname(String officerLname)
    {
        this.officerLname = officerLname;
    }
    public String getOfficerFname()
    {
	fetch();
        return officerFname;
    }
    public void setOfficerFname(String officerFname)
    {
        this.officerFname = officerFname;
    }
    public String getOfficerMname()
    {
	fetch();
        return officerMname;
    }
    public void setOfficerMname(String officerMname)
    {
        this.officerMname = officerMname;
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
    
    public String getRisk()
    {
	fetch();
        return risk;
    }

    public void setRisk(String risk)
    {
        this.risk = risk;
    }

    public String getNeeds()
    {
	fetch();
        return needs;
    }

    public void setNeeds(String needs)
    {
        this.needs = needs;
    }
    
    public Date getReleaseDate()
    {
	fetch();
        return this.releaseDate;
    }

    public void setReleaseDate(Date releasedate)
    {
        this.releaseDate = releasedate;
    }

    /**
     * ValueObj
     * @return
     */
    public JuvenileFacilitiesCurrPopResponseEvent valueObject(){
	
	JuvenileFacilitiesCurrPopResponseEvent response = new JuvenileFacilitiesCurrPopResponseEvent();
	//vars
	SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
	SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
	String formattedAdmitDate = "";	
	String formattedNextCourtDate = "";
	String formattedNextHearingDate = "";
	String formattedJuvDOB = "";  //added for US 169455
	
	response.setAdmitDate(this.getAdmitDate());
	response.setAdmitTime(this.getAdmitTime().substring(0, 5).replaceAll(":", ""));
	response.setAdmitReason(this.getAdmitReason());
	response.setAgeInYears(calculateJuvenileAge(this.getDateOfBirth()));
	response.setBookingSupervisionNum(this.getBookingSupervisionNum());
	response.setCourtDate(this.getNextCourtDate());
	
	//calculate days difference				
	int diffInDays = 0;
	if(admitDate != null)
		diffInDays = (int)((new Date().getTime() - admitDate.getTime()) / (1000 * 60 * 60 * 24) );
	//}
	response.setDiffInDays(diffInDays);	
	
	response.setDiffInDays(diffInDays);
	response.setFacilityCode(this.getFacilityCd());
	response.setFirstName(this.getJuvenileFname());
	response.setMiddleName(this.getJuvenileMname());
	response.setLastName(this.getJuvenileLname());
	response.setFloorNum(this.getFloorNum());
	
	if(this.getNextCourtDate() != null)
	{
		formattedNextCourtDate = sdf1.format(this.getNextCourtDate());
		response.setFormattedCourtDate(formattedNextCourtDate);
	}
	
	if(this.getNextDetDate() != null)
	{
		formattedNextHearingDate = sdf1.format(this.getNextDetDate());
		response.setFormattedNextHearingDate(formattedNextHearingDate);
	}	
	formattedJuvDOB = sdf1.format( this.getDateOfBirth() );
	response.setJuvenileDateOfBirth(formattedJuvDOB); //added for US 169455
	formattedAdmitDate = sdf1.format( this.getAdmitDate() ); //bug fix: #32059
	response.setFormattedAdmitDate(formattedAdmitDate);
	response.setJuvenileNum(this.getJuvenileNumber());
	response.setMou(this.getMou());
	response.setOffenseCodeId(this.getCurrentOffense());
	
	Name officerName = new Name(this.getOfficerFname(), this.getOfficerMname(), this.getOfficerLname());
	response.setOfficerFullName( officerName.getFormattedName());						 
	response.setOfficerUVCode(this.getOfficerId());
	
	response.setRaceId(this.getRaceId());
	response.setReferralNumber(this.getReferralNumber());
	response.setRoomNum(this.getRoomNum());
	response.setSecureStatus(this.getSecureStatus());
	response.setSexId(this.getSexId());
	if( this.getSpecialAttention() != null ) {
	    
	    response.setSpecialAttention(this.getSpecialAttention());
	    response.setSpecialAttentionDesc(codeMap.get( this.getSpecialAttention()).toString() );
	}
	
	response.setReasonDescription(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_REASON, this.getAdmitReason())+"-" + this.getSecureStatus());
	response.setUnit(this.getUnit());
	
	if(this.getRisk() != null){
	    
	    response.setRiskLvlDesc(this.getRisk());
	    response.setRiskLvl(this.getRisk().substring(0, 1).toUpperCase());
	}
	
	if(this.getNeeds() != null){
	   
	    response.setNeedsLvlDesc(this.getNeeds());
	    response.setNeedsLvl(this.getNeeds().substring(0, 1).toUpperCase());
	    response.setRiskneedLvl(response.getRiskLvl()+'/'+ response.getNeedsLvl());
	}
	
	
	
	return response;
    }
    
    /**
     * 
     * @return
     */
    public FacilityAdmitReasonResponseEvent valueObject2(){	    
	    
	FacilityAdmitReasonResponseEvent respEvent = new FacilityAdmitReasonResponseEvent();
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
	String formattedAdmitDate = "";	
	Date currDate = new Date();
	
	admitDate = this.getAdmitDate();
	formattedAdmitDate = sdf.format(admitDate);
	respEvent.setFormattedAdmitDate(formattedAdmitDate);
	respEvent.setAdmitDate(admitDate);
	//calculate days difference
	int diffInDays = 0;
	if(admitDate != null)
		diffInDays = (int)((currDate.getTime() - admitDate.getTime()) / (1000 * 60 * 60 * 24) +1 );
	respEvent.setDiffInDays(diffInDays);
	//respEvent.setAdmitTime(JuvenileFacilityHelper.getHoursMinsFromTime( this.getAdmitTime()));
	respEvent.setReasonCode(this.getAdmitReason());
	respEvent.setFirstName(this.juvenileFname);
	respEvent.setLastName(this.juvenileLname);
	respEvent.setJuvenileNum(this.juvenileNumber);
	respEvent.setDob(this.dateOfBirth);
	respEvent.setReleaseDate(this.getReleaseDate()); 
	respEvent.setReferralNumber( this.getReferralNumber());	
	respEvent.setSexId(this.sexId);
	respEvent.setOffenseCodeId(this.currentOffense);
	
	 respEvent.setAgeInYears(calculateJuvenileAge(respEvent.getDob()));
	
	 String hispanicInd="";
	 if(this.raceId !=null && this.raceId.equals("W") && hispanicInd !=null && hispanicInd.equals("Y"))
	    {
		raceId = "L";
	    }
	    
	 respEvent.setRaceId(raceId);
	
	String secureStatus="";
	if(this.getSecureStatus()!=null)
	{							
		if(this.getSecureStatus().equalsIgnoreCase("N"))
			secureStatus= "NON-SECURE";
		else
			secureStatus="SECURE";
	}
	respEvent.setSecureStatus(this.getSecureStatus());
			
	respEvent.setReasonDescription(admitReasonMap.get(this.getAdmitReason().toString())+ "-"+secureStatus);		
	
	
	return respEvent;
}
    
    /**
     * 
     * @param dateOfBirth
     * @return
     */
    public int calculateJuvenileAge(Date birthDate)
    {
	if (birthDate == null)
	{
	    return 0;
	}
	Calendar birthdate = Calendar.getInstance();
	birthdate.setTime(birthDate);
	Calendar now = Calendar.getInstance();

	int age = 0;
	age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
	birthdate.add(Calendar.YEAR, age);
	if (now.before(birthdate))
	{
	    age--;
	}
	return age;
    }
    
    /**
     * 
     * @param code_table_name
     * @return
     */
    private static HashMap initObservationCode(){
	 
	if( codeMap == null ) {
	    
		Collection codes = Code.findAll( PDCodeTableConstants.SPECIAL_ATTENTION );
		codeMap = new HashMap();
	        Iterator i = codes.iterator();

	        while (i.hasNext())
	        {
	            Code code = (Code) i.next();
	            codeMap.put(code.getCode(), code.getDescription());                

	        }
	    
	}

        return codeMap;
        
	}
    
    /**
     * 
     * @return Map
     */
    private static HashMap initAdmitReasons(){
	 
   	if(admitReasonMap == null ) {
   	    
   	    admitReasonMap = new HashMap<String, String>();   	    
            Iterator codeIter = JuvenileAdmitReasons.findAll();         	
            while(codeIter != null && codeIter.hasNext()){
         	    
                JuvenileAdmitReasons reason = (JuvenileAdmitReasons) codeIter.next();
                admitReasonMap.put(reason.getCode(), reason.getDescription());	                   
            }   	    
   	}
           return admitReasonMap;           
   	}

}
