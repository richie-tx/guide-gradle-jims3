package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateVOPProdSupportDetailsEvent extends RequestEvent
{
    private String juvenileNum;
    private String referralNum;
    private Date referralDate;
    private String referralDateStr;
    private String vopOffenseCode;
    private String vopOffenseCodeDesc;
    private String offenseCharge;
    private String offenseChargeDesc;
    private Date offenseChargeDate;
    private String offenseChargeDateStr;
    private int inCCountyOrigPetitionedRefNum;
    private int adultIndicator;
    private String locationIndicator;
    private String adultIndicatorStr;
    private String vopOID;
   
    
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    public String getReferralNum()
    {
        return referralNum;
    }
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }
    public Date getReferralDate()
    {
        return referralDate;
    }
    public void setReferralDate(Date referralDate)
    {
        this.referralDate = referralDate;
    }
    public String getReferralDateStr()
    {
        return referralDateStr;
    }
    public void setReferralDateStr(String referralDateStr)
    {
        this.referralDateStr = referralDateStr;
    }
    public String getVopOffenseCode()
    {
        return vopOffenseCode;
    }
    public void setVopOffenseCode(String vopOffenseCode)
    {
        this.vopOffenseCode = vopOffenseCode;
    }
    public String getVopOffenseCodeDesc()
    {
        return vopOffenseCodeDesc;
    }
    public void setVopOffenseCodeDesc(String vopOffenseCodeDesc)
    {
        this.vopOffenseCodeDesc = vopOffenseCodeDesc;
    }
    public String getOffenseCharge()
    {
        return offenseCharge;
    }
    public void setOffenseCharge(String offenseCharge)
    {
        this.offenseCharge = offenseCharge;
    }
    public String getOffenseChargeDesc()
    {
        return offenseChargeDesc;
    }
    public void setOffenseChargeDesc(String offenseChargeDesc)
    {
        this.offenseChargeDesc = offenseChargeDesc;
    }
    public Date getOffenseChargeDate()
    {
        return offenseChargeDate;
    }
    public void setOffenseChargeDate(Date offenseChargeDate)
    {
        this.offenseChargeDate = offenseChargeDate;
    }
    public String getOffenseChargeDateStr()
    {
        return offenseChargeDateStr;
    }
    public void setOffenseChargeDateStr(String offenseChargeDateStr)
    {
        this.offenseChargeDateStr = offenseChargeDateStr;
    }
    public int getInCCountyOrigPetitionedRefNum()
    {
        return inCCountyOrigPetitionedRefNum;
    }
    public void setInCCountyOrigPetitionedRefNum(int inCCountyOrigPetitionedRefNum)
    {
        this.inCCountyOrigPetitionedRefNum = inCCountyOrigPetitionedRefNum;
    }
    public int getAdultIndicator()
    {
        return adultIndicator;
    }
    public void setAdultIndicator(int adultIndicator)
    {
        this.adultIndicator = adultIndicator;
    }
    public String getLocationIndicator()
    {
        return locationIndicator;
    }
    public void setLocationIndicator(String locationIndicator)
    {
        this.locationIndicator = locationIndicator;
    }
    public String getAdultIndicatorStr()
    {
        return adultIndicatorStr;
    }
    public void setAdultIndicatorStr(String adultIndicatorStr)
    {
        this.adultIndicatorStr = adultIndicatorStr;
    }
    public String getVopOID()
    {
        return vopOID;
    }
    public void setVopOID(String vopOID)
    {
        this.vopOID = vopOID;
    }


}
