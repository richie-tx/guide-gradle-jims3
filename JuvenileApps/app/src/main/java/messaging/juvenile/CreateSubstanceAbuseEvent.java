package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class CreateSubstanceAbuseEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileNumber;
    private String associateCasefile;
    private String referralNumber;
    private String substanceAbuse;
    private String substanceType;
    private String treatmentLocation;
    
    
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    public String getAssociateCasefile()
    {
        return associateCasefile;
    }
    public void setAssociateCasefile(String associateCasefile)
    {
        this.associateCasefile = associateCasefile;
    }
    public String getReferralNumber()
    {
        return referralNumber;
    }
    public void setReferralNumber(String referralNumber)
    {
        this.referralNumber = referralNumber;
    }
    public String getSubstanceAbuse()
    {
        return substanceAbuse;
    }
    public void setSubstanceAbuse(String substanceAbuse)
    {
        this.substanceAbuse = substanceAbuse;
    }
    public String getSubstanceType()
    {
        return substanceType;
    }
    public void setSubstanceType(String substanceType)
    {
        this.substanceType = substanceType;
    }
    public String getTreatmentLocation()
    {
        return treatmentLocation;
    }
    public void setTreatmentLocation(String treatmentLocation)
    {
        this.treatmentLocation = treatmentLocation;
    }   
    

}
