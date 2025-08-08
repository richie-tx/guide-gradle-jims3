package messaging.juvenile.reply;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;



import naming.PDCodeTableConstants;
import ui.common.CodeHelper;
import mojo.km.messaging.ResponseEvent;

public class SubstanceAbuseResponseEvent extends ResponseEvent
{
    public SubstanceAbuseResponseEvent(){}
    
    private String substanceAbuseId;
    private String juvenileNumber;
    private String associatedCasefileId;
    private String referralNumber;
    private String substanceAbuse;
    private String substanceAbuseDesc;
    private String substanceType;
    private String substanceTypeDesc;
    private Date createDate;

    private String createUser;    
    private String createJims2User; 
    private String updateJims2User; 
    private Date updateDate; 
    private String treatmentLocation;

    
    private static Collection<String>substanceAbuseCodes = CodeHelper.getCodes(PDCodeTableConstants.TJJD_SUBSTANCE_ABUSE, true);
    private static Collection<String>substanceTypeCodes = CodeHelper.getCodes(PDCodeTableConstants.DRUG_TYPE, true);
    
    
    public String getCreateUser()
    {
	return createUser;
    }

    public void setCreateUser(String createUser)
    {
	this.createUser = createUser;
    }

    public String getCreateJims2User()
    {
	return createJims2User;
    }

    public void setCreateJims2User(String createJims2User)
    {
	this.createJims2User = createJims2User;
    }

    public String getUpdateJims2User()
    {
	return updateJims2User;
    }

    public void setUpdateJims2User(String updateJims2User)
    {
	this.updateJims2User = updateJims2User;
    }

    public Date getUpdateDate()
    {
	return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
	this.updateDate = updateDate;
    }

    public String getTreatmentLocation()
    {
	return treatmentLocation;
    }

    public void setTreatmentLocation(String treatmentLocation)
    {
	this.treatmentLocation = treatmentLocation;
    }
    
    
    public String getSubstanceAbuseId()
    {
        return substanceAbuseId;
    }


    public void setSubstanceAbuseId(String substanceAbuseId)
    {
        this.substanceAbuseId = substanceAbuseId;
    }


    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    
    
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    public String getAssociatedCasefileId()
    {
        return associatedCasefileId;
    }
    public void setAssociatedCasefileId(String associatedCasefileId)
    {
        this.associatedCasefileId = associatedCasefileId;
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
    public String getSubstanceAbuseDesc()
    {
	if (this.substanceAbuse != null 
		&& this.substanceAbuse.length() > 0
		&& substanceAbuseCodes != null
		&& substanceAbuseCodes.size() > 0 ) {
	    this.substanceAbuseDesc = CodeHelper.getCodeDescriptionByCode(substanceAbuseCodes, this.substanceAbuse);
	}
        return substanceAbuseDesc;
    }
    public void setSubstanceAbuseDesc(String substanceAbuseDesc)
    {
        this.substanceAbuseDesc = substanceAbuseDesc;
    }
    public String getSubstanceType()
    {
        return substanceType;
    }
    public void setSubstanceType(String substanceType)
    {
        this.substanceType = substanceType;
    }
    public String getSubstanceTypeDesc()
    {
	if ( this.substanceType != null
		&& this.substanceType.length() > 0
		&& substanceTypeCodes != null
		&& substanceTypeCodes.size() > 0 ){
	    List<String>substanceTypeList = Arrays.asList( this.substanceType.split(",") );
	    StringBuffer substanceTypeBuffer = new StringBuffer();
		for (int i = 0; i <  substanceTypeList.size(); i++ ) {
		    if ( i == 0 ) {
			substanceTypeBuffer.append( CodeHelper.getCodeDescription(PDCodeTableConstants.DRUG_TYPE, substanceTypeList.get(i).trim() ) ) ;
		    } else {
			substanceTypeBuffer.append(", " + CodeHelper.getCodeDescription(PDCodeTableConstants.DRUG_TYPE, substanceTypeList.get(i).trim() )   ) ;
		    }
		}
	    this.substanceTypeDesc = substanceTypeBuffer.toString();
	}
        return substanceTypeDesc;
    }
    public void setSubstanceTypeDesc(String substanceTypeDesc)
    {
        this.substanceTypeDesc = substanceTypeDesc;
    }


    public Date getCreateDate()
    {
        return createDate;
    }


    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
    
    
    
    
}
