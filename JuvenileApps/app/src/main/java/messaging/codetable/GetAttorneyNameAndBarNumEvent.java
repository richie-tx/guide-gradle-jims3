package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetAttorneyNameAndBarNumEvent extends RequestEvent{

    private String attorneyName;
    private String barNum;
    
    /**
     * @return the attorneyName
     */
    public String getAttorneyName()
    {
        return attorneyName;
    }
    /**
     * @param attorneyName the attorneyName to set
     */
    public void setAttorneyName(String attorneyName)
    {
        this.attorneyName = attorneyName;
    }
    /**
     * @return the barNum
     */
    public String getBarNum()
    {
        return barNum;
    }
    /**
     * @param barNum the barNum to set
     */
    public void setBarNum(String barNum)
    {
        this.barNum = barNum;
    }
}
