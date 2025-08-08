package pd.juvenilecase.referral;

import java.util.Date;
import java.util.Iterator;

import messaging.referral.reply.JJSDecisionHistoryResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;


public class JJSDecisionHistory extends PersistentObject
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileNum;
    private String referralNum;
    private String inDecisionId;
    private String courtDecisionId;
    private String tjpcDisp;
    private Date   decisionDate;
    private String tjpcDispSeqNum;
    
    
    public String getJuvenileNum()
    {
	fetch();
        return juvenileNum;
    }
    
    public void setJuvenileNum(String juvenileNum)
    {
	if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
	{
		markModified();
	}
        this.juvenileNum = juvenileNum;
    }
    
    public String getReferralNum()
    {
	fetch();
        return referralNum;
    }
    
    public void setReferralNum(String referralNum)
    {
	if (this.referralNum == null || !this.referralNum.equals(referralNum))
	{
		markModified();
	}
        this.referralNum = referralNum;
    }
    
    public String getInDecisionId()
    {
	fetch();
        return inDecisionId;
    }
    
    public void setInDecisionId(String inDecisionId)
    {
	if (this.inDecisionId == null || !this.inDecisionId.equals(inDecisionId))
	{
		markModified();
	}
        this.inDecisionId = inDecisionId;
    }
    
    public String getCourtDecisionId()
    {
	fetch();
        return courtDecisionId;
    }
    
    public void setCourtDecisionId(String courtDecisionId)
    {
	if (this.courtDecisionId == null || !this.courtDecisionId.equals(courtDecisionId))
	{
		markModified();
	}
        this.courtDecisionId = courtDecisionId;
    }
    
    public String getTjpcDisp()
    {
	fetch();
        return tjpcDisp;
    }
    
    public void setTjpcDisp(String tjpcDisp)
    {
	if (this.tjpcDisp == null || !this.tjpcDisp.equals(tjpcDisp))
	{
		markModified();
	}
        this.tjpcDisp = tjpcDisp;
    }
    
    public Date getDecisionDate()
    {
	fetch();
        return decisionDate;
    }
                                            
    public void setDecisionDate(Date decisionDate)
    {
	if (this.decisionDate == null || !this.decisionDate.equals(decisionDate))
	{
		markModified();
	}
        this.decisionDate = decisionDate;
    }
    
    
    public String getTjpcDispSeqNum()
    {
	fetch();
        return tjpcDispSeqNum;
    }
    
    
    public void setTjpcDispSeqNum(String tjpcDispSeqNum)
    {
	if (this.tjpcDispSeqNum == null || !this.tjpcDispSeqNum.equals(tjpcDispSeqNum))
	{
		markModified();
	}
        this.tjpcDispSeqNum = tjpcDispSeqNum;
    }
    
    public static Iterator findAll(String attributeName, String attributeValue)
	{
	    IHome home = new Home();
	    return home.findAll(attributeName, attributeValue,JJSDecisionHistory.class);		
	}
    
    static public JJSDecisionHistory find(String OID)
	{
	   IHome home = new Home();
	   JJSDecisionHistory history = (JJSDecisionHistory) home.find(OID, JJSDecisionHistory.class);
	   return history;
	}
  	
    static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JJSDecisionHistory.class);
	}
    
    public JJSDecisionHistoryResponseEvent valueObject(){
	
	JJSDecisionHistoryResponseEvent resp = new JJSDecisionHistoryResponseEvent();
	resp.setJuvenileNum(this.getJuvenileNum());
	resp.setReferralNum(this.getReferralNum());
	resp.setCourtDecisionId( this.getCourtDecisionId());
	resp.setDecisionDate(this.getDecisionDate());
	resp.setTjpcDisp(this.getTjpcDisp());
	resp.setTjpcDispSeqNum(this.getTjpcDispSeqNum());
		
	return resp;	
	
    }

}
