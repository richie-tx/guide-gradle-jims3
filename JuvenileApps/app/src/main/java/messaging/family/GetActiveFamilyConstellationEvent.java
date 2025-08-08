package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * Class GetFamilyConstellationsEvent.
 *  
 * @author  mchowdhury
 * @version  1.0.0
 */
public class GetActiveFamilyConstellationEvent extends mojo.km.messaging.RequestEvent {
    public String juvenileNum;

    /**
     * @roseuid 43299A4D0157
     */
    public GetActiveFamilyConstellationEvent() {

    }

    /**
     *  
     * @param juvenileNum @roseuid 432997A90263
     */
    public void setJuvenileNum(String juvenileNum) {
        this.juvenileNum = juvenileNum;
    }
    
    /**
     *  
     * @return  String
     * @roseuid 432997A90265
     */
    public String getJuvenileNum() {
        return juvenileNum;
    }
} 
