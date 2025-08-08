package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportPetitionDetailsEvent extends RequestEvent
{
    /**
     * 
     */

    public GetProductionSupportPetitionDetailsEvent(){};
    
 

    public String getJuvenileNum()
    {
	return juvenileNum;
    }
    
    public void setJuvenileNum(String juvenileNumber)
    {
	this.juvenileNum = juvenileNumber;
    }



    public String getReferralNum()
    {
	return referralNum;
    }



    public void setReferralNum(String referralNumber)
    {
	this.referralNum = referralNumber;
    }
    
    
    public String getLcUser()
    {
        return lcUser;
    }



    public void setLcUser(String lcUser)
    {
        this.lcUser = lcUser;
    }



    public  String juvenileNum;
    public  String referralNum;
    public  String lcUser;
    
    
}
