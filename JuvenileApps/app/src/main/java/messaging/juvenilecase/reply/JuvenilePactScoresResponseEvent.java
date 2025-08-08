package messaging.juvenilecase.reply;


import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class JuvenilePactScoresResponseEvent extends ResponseEvent
{
    private String pactOID;
    private int subjectEID;
    private String formInstanceId;
    private Date dateCompleted;
    private String scoreClassification;
    private String scoreType;
    private String form_Name;
    private String formInstanceSequenceReverse;
    private String formInstanceSequence;
    private String formInstanceChronology;
    public String getPactOID()
    {
        return pactOID;
    }
    public void setPactOID(String pactOID)
    {
        this.pactOID = pactOID;
    }
    public int getSubjectEID()
    {
        return subjectEID;
    }
    public void setSubjectEID(int subjectEID)
    {
        this.subjectEID = subjectEID;
    }
    public String getFormInstanceId()
    {
        return formInstanceId;
    }
    public void setFormInstanceId(String formInstanceId)
    {
        this.formInstanceId = formInstanceId;
    }
    public Date getDateCompleted()
    {
        return dateCompleted;
    }
    public void setDateCompleted(Date dateCompleted)
    {
        this.dateCompleted = dateCompleted;
    }
    public String getScoreClassification()
    {
        return scoreClassification;
    }
    public void setScoreClassification(String scoreClassification)
    {
        this.scoreClassification = scoreClassification;
    }
    public String getForm_Name()
    {
        return form_Name;
    }
    public void setForm_Name(String form_Name)
    {
        this.form_Name = form_Name;
    }
    public String getFormInstanceSequenceReverse()
    {
        return formInstanceSequenceReverse;
    }
    public void setFormInstanceSequenceReverse(String formInstanceSequenceReverse)
    {
        this.formInstanceSequenceReverse = formInstanceSequenceReverse;
    }
    public String getFormInstanceSequence()
    {
        return formInstanceSequence;
    }
    public void setFormInstanceSequence(String formInstanceSequence)
    {
        this.formInstanceSequence = formInstanceSequence;
    }
    public String getFormInstanceChronology()
    {
        return formInstanceChronology;
    }
    public void setFormInstanceChronology(String formInstanceChronology)
    {
        this.formInstanceChronology = formInstanceChronology;
    }
    public String getScoreType()
    {
        return scoreType;
    }
    public void setScoreType(String scoreType)
    {
        this.scoreType = scoreType;
    }
    
}
