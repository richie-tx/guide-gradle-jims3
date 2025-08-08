package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileNumControl extends PersistentObject
{
    private static final long serialVersionUID = 1L;
    private int lastJuvenileNum;
    private String rectype;
    private Date lcDate;
    private Date lcTime;
    private String lcUser;
    
    


    public int getLastJuvenileNum()
    {
        return lastJuvenileNum;
    }

    public void setLastJuvenileNum(int lastJuvenileNum)
    {
        this.lastJuvenileNum = lastJuvenileNum;
    }

    public String getRectype()
    {
	return rectype;
    }

    public void setRectype(String rectype)
    {
	this.rectype = rectype;
    }

    public Date getLcDate()
    {
	return lcDate;
    }

    public void setLcDate(Date lcDate)
    {
	this.lcDate = lcDate;
    }

    public Date getLcTime()
    {
	return lcTime;
    }

    public void setLcTime(Date lcTime)
    {
	this.lcTime = lcTime;
    }

    public String getLcUser()
    {
	return lcUser;
    }

    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }
    
    /**
     * Finds juvenile JuvenileNumControl list by an event
     * 
     * @return Iterator of JuvenileNumControl list
     * @param event
     */
    static public Iterator<JuvenileNumControl> findAll(IEvent event)
    {
	IHome home = new Home();
	Iterator<JuvenileNumControl> JuvenileNumControl = home.findAll(event, JuvenileNumControl.class);
	return JuvenileNumControl;
    }

    /**
     * @return Iterator JuvenileNumControl
     * @param attrName
     *            name fo the attribute for where clause
     * @param attrValue
     *            value to be checked in the where clause
     */
    static public Iterator<JuvenileNumControl> findAll(String attrName,String attrValue)
    {
	IHome home = new Home();
	Iterator<JuvenileNumControl> JuvenileNumControl = home.findAll(attrName, attrValue, JuvenileNumControl.class);
	return JuvenileNumControl;
    }

    /**
     * @return
     * 
     */
    static public Iterator<JuvenileNumControl> findAll()
    {
	IHome home = new Home();
	return home.findAll(JuvenileNumControl.class);
    }

}
