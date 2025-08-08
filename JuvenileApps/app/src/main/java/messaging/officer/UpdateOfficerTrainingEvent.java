package messaging.officer;


import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateOfficerTrainingEvent extends RequestEvent
{
    
    private int trainingTopicsId;

    private Date trainingBeginDate;

    private Date trainingEndDate;

    private double trainingHours;
    
    private int officerId;

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

    public int getOfficerId()
    {
        return officerId;
    }

    public void setOfficerId(int officerId)
    {
        this.officerId = officerId;
    }    

}
