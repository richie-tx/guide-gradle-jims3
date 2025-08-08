package pd.juvenile;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class SubstanceAbuse extends PersistentObject
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SubstanceAbuse(){}
    
    private String juvenileId;
    private String casefileId;
    private String referralNum;
    private String sAbuse;
    private String substanceType;
    private String treatmentLocation;
    
    public String getJuvenileId()
    {
	fetch();
        return juvenileId;
    }
    public void setJuvenileId(String juvenileId)
    {
	if ( this.juvenileId == null
		|| !this.juvenileId.equals( juvenileId )){
	    markModified();
	}
	
        this.juvenileId = juvenileId;
    }
    
    public String getCasefileId()
    {
	fetch();
        return casefileId;
    }
    public void setCasefileId(String casefileId)
    {
	if ( this.casefileId == null
		|| !this.casefileId.equals( casefileId )){
	    markModified();
	}
        this.casefileId = casefileId;
    }
    
    public String getReferralNum()
    {
	fetch();
        return referralNum;
    }
    public void setReferralNum(String referralNum)
    {
	if ( this.referralNum == null
		|| !this.referralNum.equals( referralNum )){
	    markModified();
	}
        this.referralNum = referralNum;
    }
    
    public String getsAbuse()
    {
	fetch();
        return sAbuse;
    }
    public void setsAbuse(String sAbuse)
    {
	if ( this.sAbuse == null
		|| !this.sAbuse.equals( sAbuse )){
	    markModified();
	}
        this.sAbuse = sAbuse;
    }
    public String getSubstanceType()
    {
	fetch();
        return substanceType;
    }
    public void setSubstanceType(String substanceType)
    {
	if ( this.substanceType == null
		|| !this.substanceType.equals( substanceType )){
	    markModified();
	}
        this.substanceType = substanceType;
    }
    
 
    public String getTreatmentLocation()
    {
	fetch();
        return treatmentLocation;
    }
    
    public void setTreatmentLocation(String treatmentLocation)
    {
	if ( this.treatmentLocation == null
		|| !this.treatmentLocation.equals( treatmentLocation )){
	    markModified();
	}
        this.treatmentLocation = treatmentLocation;
    }
    
    static public SubstanceAbuse find( String substanceAbuseId ) {
	IHome home = new Home();
	SubstanceAbuse substanceAbuse = (SubstanceAbuse )home.find( substanceAbuseId, SubstanceAbuse.class);
	return substanceAbuse;
    }
    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	Iterator substanceAbuses = home.findAll(event, SubstanceAbuse.class);
	return substanceAbuses;
    }
	
    static public Iterator findAll(String attrName, String attrValue)
    {
	IHome home = new Home();
	Iterator substanceAbuses = home.findAll(attrName, attrValue, SubstanceAbuse.class);
	return substanceAbuses;
    }
    

}
