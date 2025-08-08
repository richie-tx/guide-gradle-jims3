package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportJuvenileMasterEvent extends RequestEvent 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileId;
    private Date birthDate;
    private Date realDOB;
    private String purgeBoxNum;
    private String purgeDate;
    private String purgeSerNum;
    private String purgeFlag;	
    private String detentionFacilityId ;	
    private String detentionStatusId ;
    private String caseNotePart1;    
    private Date checkedOutDate;
    private String checkedOutTo;
    private String juvenileFName;
    private String juvenileMName;
    private String juvenileLName;
    private String juvenileNameSuffix;
    private String lastReferral;
    private String raceId;
    private String originalRaceId;
    private String sexId ;
    private String masterDOB;
    private String masterStatusId;
    private String juvenileSsn;
    
    //US 189272
    private String sealComments;
    private String sealedDate;
    private String dateOfDeath;
    private int ageAtDeath ;
    private int juvExcluded;
    private String recType;
    private String liveWith;
    private String causeOfDeath;
    private String deathVerification;
    
   
	public String getJuvenileId()
    {
        return juvenileId;
    }



    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }



	public Date getBirthDate()
    {
        return birthDate;
    }



    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public Date getRealDOB()
    {
	return this.realDOB;
    } 
    
    public void setRealDOB(Date realDOB)
    {
	this.realDOB = realDOB;

    } 


    public String getPurgeBoxNum()
    {
        return purgeBoxNum;
    }



    public void setPurgeBoxNum(String purgeBoxNum)
    {
        this.purgeBoxNum = purgeBoxNum;
    }



    public String getPurgeDate()
    {
        return purgeDate;
    }



    public void setPurgeDate(String purgeDate)
    {
        this.purgeDate = purgeDate;
    }



    public String getPurgeSerNum()
    {
        return purgeSerNum;
    }



    public void setPurgeSerNum(String purgeSerNum)
    {
        this.purgeSerNum = purgeSerNum;
    }



    public String getPurgeFlag()
    {
        return purgeFlag;
    }



    public void setPurgeFlag(String purgeFlag)
    {
        this.purgeFlag = purgeFlag;
    }



    public String getDetentionFacilityId()
    {
        return detentionFacilityId;
    }



    public void setDetentionFacilityId(String detentionFacilityId)
    {
        this.detentionFacilityId = detentionFacilityId;
    }



    public String getDetentionStatusId()
    {
        return detentionStatusId;
    }



    public void setDetentionStatusId(String detentionStatusId)
    {
        this.detentionStatusId = detentionStatusId;
    }



    public String getCaseNotePart1()
    {
        return caseNotePart1;
    }



    public void setCaseNotePart1(String caseNotePart1)
    {
        this.caseNotePart1 = caseNotePart1;
    }



    public Date getCheckedOutDate()
    {
        return checkedOutDate;
    }



    public void setCheckedOutDate(Date checkedOutDate)
    {
        this.checkedOutDate = checkedOutDate;
    }



    public String getCheckedOutTo()
    {
        return checkedOutTo;
    }



    public void setCheckedOutTo(String checkedOutTo)
    {
        this.checkedOutTo = checkedOutTo;
    }



    public String getJuvenileFName()
    {
        return juvenileFName;
    }



    public void setJuvenileFName(String juvenileFName)
    {
        this.juvenileFName = juvenileFName;
    }



    public String getJuvenileMName()
    {
        return juvenileMName;
    }



    public void setJuvenileMName(String juvenileMName)
    {
        this.juvenileMName = juvenileMName;
    }



    public String getJuvenileLName()
    {
        return juvenileLName;
    }



    public void setJuvenileLName(String juvenileLName)
    {
        this.juvenileLName = juvenileLName;
    }



    public String getJuvenileNameSuffix()
    {
        return juvenileNameSuffix;
    }



    public void setJuvenileNameSuffix(String juvenileNameSuffix)
    {
        this.juvenileNameSuffix = juvenileNameSuffix;
    }



    public String getLastReferral()
    {
        return lastReferral;
    }



    public void setLastReferral(String lastReferral)
    {
        this.lastReferral = lastReferral;
    }



    public String getRaceId()
    {
        return raceId;
    }



    public void setRaceId(String raceId)
    {
        this.raceId = raceId;
    }
    
    public String getOriginalRaceId()
    {
        return originalRaceId;
    }



    public void setOriginalRaceId(String originalRaceId)
    {
        this.originalRaceId = originalRaceId;
    }



    public String getSexId()
    {
        return sexId;
    }



    public void setSexId(String sexId)
    {
        this.sexId = sexId;
    }



    public String getMasterDOB()
    {
        return masterDOB;
    }



    public void setMasterDOB(String masterDOB)
    {
        this.masterDOB = masterDOB;
    }



    public String getMasterStatusId()
    {
        return masterStatusId;
    }



    public void setMasterStatusId(String masterStatusId)
    {
        this.masterStatusId = masterStatusId;
    }



    public String getJuvenileSsn()
    {
        return juvenileSsn;
    }



    public void setJuvenileSsn(String juvenileSsn)
    {
        this.juvenileSsn = juvenileSsn;
    }



	/**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportJuvenileMasterEvent() 
   {       
   
   }



	public String getRecType()
	{
	    return recType;
	}



	public void setRecType(String recType)
	{
	    this.recType = recType;
	}



	public int getAgeAtDeath()
	{
	    return ageAtDeath;
	}



	public void setAgeAtDeath(int ageAtDeath)
	{
	    this.ageAtDeath = ageAtDeath;
	}



	public String getSealComments()
	{
	    return sealComments;
	}



	public void setSealComments(String sealComments)
	{
	    this.sealComments = sealComments;
	}

	public String getDateOfDeath()
	{
	    return dateOfDeath;
	}



	public void setDateOfDeath(String dateOfDeath)
	{
	    this.dateOfDeath = dateOfDeath;
	}



	public int getJuvExcluded()
	{
	    return juvExcluded;
	}



	public void setJuvExcluded(int juvExcluded)
	{
	    this.juvExcluded = juvExcluded;
	}



	public String getLivewith()
	{
	    return liveWith;
	}



	public void setLivewith(String livewith)
	{
	    this.liveWith = livewith;
	}



	public String getCauseOfDeath()
	{
	    return causeOfDeath;
	}



	public void setCauseOfDeath(String causeOfDeath)
	{
	    this.causeOfDeath = causeOfDeath;
	}



	public String getDeathVerification()
	{
	    return deathVerification;
	}



	public void setDeathVerification(String deathVerification)
	{
	    this.deathVerification = deathVerification;
	}



	public String getSealedDate()
	{
	    return sealedDate;
	}



	public void setSealedDate(String sealedDate)
	{
	    this.sealedDate = sealedDate;
	}
   
}
