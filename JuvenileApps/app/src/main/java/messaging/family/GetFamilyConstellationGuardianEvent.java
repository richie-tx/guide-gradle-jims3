package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * Class GetFamilyConstellationGuardianEvent.
 *  
 * @author  mchowdhury
 * @version  1.0.0
 */
public class GetFamilyConstellationGuardianEvent extends mojo.km.messaging.RequestEvent {

    private int memberNum;
   
    /**
     * @roseuid 43299A4D0157
     */
    public GetFamilyConstellationGuardianEvent() {

    }
 


	/**
	 * @return
	 */
	public int getMemberNum()
	{
		return memberNum;
	}

	/**
	 * @param i
	 */
	public void setMemberNum(int i)
	{
		memberNum = i;
	}

} 
