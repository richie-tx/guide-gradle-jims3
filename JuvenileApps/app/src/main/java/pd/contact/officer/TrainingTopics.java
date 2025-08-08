package pd.contact.officer;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class TrainingTopics extends PersistentObject
{
    
    private String code;

    private String description;

    private String status;

    private int trainingHours;
    
    /**
     * @return pd.contact.OfficerTraining
     */
    static public TrainingTopics find(String officerTrainingId)
    {
	TrainingTopics trainingTopics = null;
	IHome home = new Home();
	trainingTopics = (TrainingTopics) home.find(officerTrainingId, TrainingTopics.class);
	return trainingTopics;
    }

    /**
     * @roseuid 42E65EA6010F
     */
    static public Iterator findAll()
    {
	IHome home = new Home();
	Iterator iter = home.findAll(TrainingTopics.class);
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
	Iterator iter = home.findAll(event, TrainingTopics.class);
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
	return (Iterator) home.findAll(attrName, attrValue, TrainingTopics.class);
    }

    public String getCode()
    {
	fetch();
        return code;
    }

    public void setCode(String code)
    {
	if (this.code == null || !this.code.equals(code))
	{
	    markModified();
	}
        this.code = code;
    }

    public String getDescription()
    {
	fetch();
        return description;
    }

    public void setDescription(String description)
    {
	if (this.description == null || !this.description.equals(description))
	{
	    markModified();
	}
        this.description = description;
    }

    public String getStatus()
    {
	fetch();
        return status;
    }

    public void setStatus(String status)
    {
	if (this.status == null || !this.status.equals(status))
	{
	    markModified();
	}
        this.status = status;
    }

    public int getTrainingHours()
    {
	fetch();
        return trainingHours;
    }

    public void setTrainingHours(int trainingHours)
    {
	if (this.trainingHours != 0 || this.trainingHours != trainingHours )
	{
	    markModified();
	}
        this.trainingHours = trainingHours;
    }    

}
