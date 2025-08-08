package messaging.contact.officer.reply;


import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class OfficerTrainingResponseEvent extends ResponseEvent implements Comparable<OfficerTrainingResponseEvent>
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String trainingDescription;
    
    private String trainingTopicCd;
    
    private int trainingTopicsId;

    private Date trainingBeginDate;
    
    private String sortOrder;

    private Date trainingEndDate;

    private double trainingHours;

    public int getTrainingTopicsId()
    {
        return trainingTopicsId;
    }

    public void setTrainingTopicsId(int trainingTopicsId)
    {
        this.trainingTopicsId = trainingTopicsId;
    }

    public Date getTrainingBeginDate()
    {
        return trainingBeginDate;
    }

    public void setTrainingBeginDate(Date trainingBeginDate)
    {
        this.trainingBeginDate = trainingBeginDate;
    }

    public Date getTrainingEndDate()
    {
        return trainingEndDate;
    }

    public void setTrainingEndDate(Date trainingEndDate)
    {
        this.trainingEndDate = trainingEndDate;
    }

    public double getTrainingHours()
    {
        return trainingHours;
    }

    public void setTrainingHours(double trainingHours)
    {
        this.trainingHours = trainingHours;
    }

    public String getTrainingTopicCd()
    {
        return trainingTopicCd;
    }

    public void setTrainingTopicCd(String trainingTopicCd)
    {
        this.trainingTopicCd = trainingTopicCd;
    }

    public String getTrainingDescription()
    {
        return trainingDescription;
    }

    public void setTrainingDescription(String trainingDescription)
    {
        this.trainingDescription = trainingDescription;
    }


    public String getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compareTo(OfficerTrainingResponseEvent responseEvent) {
        return this.getSortOrder().compareTo(responseEvent.getSortOrder());
    }  
        
}
