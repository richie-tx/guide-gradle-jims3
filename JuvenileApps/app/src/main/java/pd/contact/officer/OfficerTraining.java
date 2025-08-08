package pd.contact.officer;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

@SuppressWarnings("serial")
public class OfficerTraining extends PersistentObject
{

    private int officerProfileId;
    
    private int trainingTopicsId;

    private Date trainingBeginDate;

    private Date trainingEndDate;

    private double trainingHours;

    
    /**
     * @return pd.contact.OfficerTraining
     */
    static public OfficerTraining find(String officerTrainingId)
    {
	OfficerTraining officerTraining = null;
	IHome home = new Home();
	officerTraining = (OfficerTraining) home.find(officerTrainingId, OfficerTraining.class);
	return officerTraining;
    }

    /**
     * @roseuid 42E65EA6010F
     */
    static public Iterator findAll()
    {
	IHome home = new Home();
	Iterator iter = home.findAll(OfficerTraining.class);
	return iter;
    }

    /**
     * @return java.util.Iterator
     * @param event
     * @roseuid 4107B06D01BB
     */
    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	Iterator iter = home.findAll(event, OfficerTraining.class);
	return iter;
    }

    /**
     * @return java.util.Iterator
     * @param attrName
     * @param attrValue
     * @roseuid 42E65EA6010F
     */
    static public Iterator findAll(String attrName, String attrValue)
    {
	IHome home = new Home();
	return (Iterator) home.findAll(attrName, attrValue, OfficerTraining.class);
    }
    
    
    public int getTrainingTopicsId()
    {
	fetch();
        return trainingTopicsId;
    }

    public void setTrainingTopicsId(int trainingTopicsId)
    {
	if (this.trainingTopicsId !=0 || this.trainingTopicsId != trainingTopicsId)
	{
	    markModified();
	}
        this.trainingTopicsId = trainingTopicsId;
    }

    public Date getTrainingBeginDate()
    {
	fetch();
        return trainingBeginDate;
    }

    public void setTrainingBeginDate(Date trainingBeginDate)
    {
	if (this.trainingBeginDate == null || !this.trainingBeginDate.equals(trainingBeginDate))
	{
	    markModified();
	}
	
        this.trainingBeginDate = trainingBeginDate;
    }

    public Date getTrainingEndDate()
    {
	fetch();
        return trainingEndDate;
    }

    public void setTrainingEndDate(Date trainingEndDate)
    {
	if (this.trainingEndDate == null || !this.trainingEndDate.equals(trainingEndDate))
	{
	    markModified();
	}
        this.trainingEndDate = trainingEndDate;
    }

    public double getTrainingHours()
    {
	fetch();
        return trainingHours;
    }

    public void setTrainingHours(double trainingHours)
    {
        this.trainingHours = trainingHours;
    }

    public int getOfficerProfileId()
    {
	fetch();
        return officerProfileId;
    }

    public void setOfficerProfileId(int officerProfileId)
    {
        this.officerProfileId = officerProfileId;
    }
}
