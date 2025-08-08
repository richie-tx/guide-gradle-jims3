package messaging.juvenile.reply;

import mojo.km.messaging.ResponseEvent;

//added for task 151689

public class JuvenileCoactorsResponseEvent extends ResponseEvent implements Comparable
{
    private String juvenileNum;    
    private String attorneyName;

    

    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    public String getAttorneyName()
    {
        return attorneyName;
    }
    public void setAttorneyName(String attorneyName)
    {
        this.attorneyName = attorneyName;
    }
    @Override
    public int compareTo(Object o)
    {
	// TODO Auto-generated method stub
	return 0;
    }

}

