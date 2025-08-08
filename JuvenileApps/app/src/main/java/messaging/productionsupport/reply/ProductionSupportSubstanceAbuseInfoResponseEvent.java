package messaging.productionsupport.reply;

import java.util.ArrayList;
import java.util.List;

import naming.PDCodeTableConstants;
import ui.common.CodeHelper;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.messaging.ResponseEvent;

public class ProductionSupportSubstanceAbuseInfoResponseEvent extends ResponseEvent
{
    private String fullName;
    private String substanceAbuseId;
    private String juvenileId;
    private String casefileId;
    private String referralNum;
    private String sAbuse;
    private String sAbuseCode;
    private String substanceType;
    private String treatmentLocation;
    private String [] substanceTypeCode;
    private List<String> casefileIds = new ArrayList<>();
    private List<String> referrals = new ArrayList<>();
    
    
    public ProductionSupportSubstanceAbuseInfoResponseEvent(){};
    
    public ProductionSupportSubstanceAbuseInfoResponseEvent(ProductionSupportSubstanceAbuseInfoResponseEvent resp){
	this.fullName = resp.getFullName();
	this.substanceAbuseId = resp.getSubstanceAbuseId();
	this.juvenileId = resp.getJuvenileId();
	this.casefileId = resp.getCasefileId();
	this.referralNum = resp.getReferralNum();
	this.substanceType = resp.getSubstanceType();
	this.sAbuseCode = resp.getsAbuseCode();
	this.substanceTypeCode = resp.getSubstanceTypeCode();
	this.casefileIds.addAll( resp.getCasefileIds());
	this.referrals.addAll( resp.getReferrals() );
	this.treatmentLocation = resp.getTreatmentLocation();
    }
    
    public String getSubstanceAbuseId()
    {
        return substanceAbuseId;
    }
    public void setSubstanceAbuseId(String substanceAbuseId)
    {
        this.substanceAbuseId = substanceAbuseId;
    }
    public String getJuvenileId()
    {
        return juvenileId;
    }
    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }
    public String getCasefileId()
    {
        return casefileId;
    }
    public void setCasefileId(String casefileId)
    {
        this.casefileId = casefileId;
    }
    public String getReferralNum()
    {
        return referralNum;
    }
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }
    public String getsAbuse()
    {
	if (this.sAbuseCode != null
		&& this.sAbuseCode.length() > 0  ){
	    this.sAbuse =  CodeHelper.getCodeDescription( PDCodeTableConstants.TJJD_SUBSTANCE_ABUSE, this.sAbuseCode );
	}

        return sAbuse;
    }
    public void setsAbuse(String sAbuse)
    {
        this.sAbuse = sAbuse;
    }
    public String getSubstanceType()
    {
	if (	this.substanceTypeCode != null
		&& this.substanceTypeCode.length > 0  ){
	    StringBuffer substanceTypeBuffer = new StringBuffer();
	    for( int i = 0; i < this.substanceTypeCode.length ; i++ ){
		String substanceType = "";
		if ( this.substanceTypeCode[i].trim() != null
			&& this.substanceTypeCode[i].trim().length() > 0 ) {
		    substanceType = CodeHelper.getCodeDescription(PDCodeTableConstants.DRUG_TYPE, this.substanceTypeCode[i].trim() );
		}
		if( i == 0 ) {
		    substanceTypeBuffer.append(substanceType);
		}
		
		if ( i > 0 ){
		    substanceTypeBuffer.append(", " + substanceType);
		}
	    }
	    
	     this.substanceType = substanceTypeBuffer.toString();
	   
	}
        return substanceType;
    }
    public void setSubstanceType(String substanceType)
    {
        this.substanceType = substanceType;
    }
    public String getFullName()
    {
        return fullName;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getsAbuseCode()
    {
	
        return sAbuseCode;
    }

    public void setsAbuseCode(String sAbuseCode)
    {
        this.sAbuseCode = sAbuseCode;
    }

    public String[] getSubstanceTypeCode()
    {
        return substanceTypeCode;
    }

    public void setSubstanceTypeCode(String[] substanceTypeCode)
    {
        this.substanceTypeCode = substanceTypeCode;
    }

    public List<String> getCasefileIds()
    {
        return casefileIds;
    }

    public void setCasefileIds(List<String> casefileIds)
    {
        this.casefileIds = casefileIds;
    }

    public List<String> getReferrals()
    {
        return referrals;
    }

    public void setReferrals(List<String> referrals)
    {
        this.referrals = referrals;
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
