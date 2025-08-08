/*
 * Created on Dec 15, 2006
 *
 */
package pd.juvenilewarrant;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author Jim Fisher
 *  
 */
public class JuvenileWarrantAssociate extends JuvenileWarrant
{
    private String associateFirstName;

    private String associateLastName;

    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, JuvenileWarrantAssociate.class);
    }
    
    /**
     * @return Returns the associateFirstName.
     */
    public String getAssociateFirstName()
    {
        fetch();
        return associateFirstName;
    }

    /**
     * @param associateFirstName
     *            The associateFirstName to set.
     */
    public void setAssociateFirstName(String associateFirstName)
    {
        this.associateFirstName = associateFirstName;
    }

    /**
     * @return Returns the associateLastName.
     */
    public String getAssociateLastName()
    {
        fetch();
        return associateLastName;
    }

    /**
     * @param associateLastName
     *            The associateLastName to set.
     */
    public void setAssociateLastName(String associateLastName)
    {
        this.associateLastName = associateLastName;
    }
}
