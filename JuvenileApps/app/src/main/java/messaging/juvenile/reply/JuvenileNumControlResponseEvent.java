package messaging.juvenile.reply;

import mojo.km.messaging.ResponseEvent;

public class JuvenileNumControlResponseEvent extends ResponseEvent implements Comparable
{
    private String lastJuvenileNum;

    

    public String getLastJuvenileNum()
    {
        return lastJuvenileNum;
    }



    public void setLastJuvenileNum(String lastJuvenileNum)
    {
        this.lastJuvenileNum = lastJuvenileNum;
    }



    @Override
    public int compareTo(Object o)
    {
	// TODO Auto-generated method stub
	return 0;
    }

}

