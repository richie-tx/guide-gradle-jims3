package messaging.contact.officer.reply;


import mojo.km.messaging.ResponseEvent;

public class TrainingTopicsResponseEvent extends ResponseEvent implements Comparable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int trainingTopicId;
    
    private String code;

    private String description;

    private String status;

    private int trainingHours;

    
    public TrainingTopicsResponseEvent(){
	
    }
    
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getTrainingHours()
    {
        return trainingHours;
    }

    public void setTrainingHours(int trainingHours)
    {
        this.trainingHours = trainingHours;
    }

    public int getTrainingTopicId()
    {
        return trainingTopicId;
    }

    public void setTrainingTopicId(int trainingTopicId)
    {
        this.trainingTopicId = trainingTopicId;
    }
    // below method added for US 187828 - to add sort for the Trainning Topic
    public int compareTo(Object o) {
        TrainingTopicsResponseEvent e1 = (TrainingTopicsResponseEvent) o;
        return this.getDescription().compareTo(e1.getDescription());
    }
         
}
