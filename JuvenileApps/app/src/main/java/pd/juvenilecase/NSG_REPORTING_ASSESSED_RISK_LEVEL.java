package pd.juvenilecase;

import java.util.Date;
import java.util.Iterator;
import messaging.juvenilecase.reply.JuvenilePactScoresResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class NSG_REPORTING_ASSESSED_RISK_LEVEL extends PersistentObject
{

    private static final long serialVersionUID = 1L;
    private int subjectEID;
    private String formInstanceId;
    private Date dateCompleted;
    private String scoreType;
    private String scoreClassification;
    private String formName;
    private String formInstanceSequenceReverse;
    private String formInstanceSequence;
    private String formInstanceChronology;

   

    public int getSubjectEID()
    {
	fetch();
        return subjectEID;
    }

    public void setSubjectEID(int subjectEID)
    {
        this.subjectEID = subjectEID;
    }

    public String getFormInstanceId()
    {
	fetch();
        return formInstanceId;
    }

    public void setFormInstanceId(String formInstanceId)
    {
        this.formInstanceId = formInstanceId;
    }

    public Date getDateCompleted()
    {
	fetch();
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted)
    {
        this.dateCompleted = dateCompleted;
    }

    public String getScoreType()
    {
	fetch();
        return scoreType;
    }

    public void setScoreType(String scoreType)
    {
        this.scoreType = scoreType;
    }

    public String getScoreClassification()
    {
	fetch();
        return scoreClassification;
    }

    public void setScoreClassification(String scoreClassification)
    {
        this.scoreClassification = scoreClassification;
    }

    public String getFormName()
    {
	fetch();
        return formName;
    }

    public void setFormName(String formName)
    {
        this.formName = formName;
    }

    public String getFormInstanceSequenceReverse()
    {
	fetch();
        return formInstanceSequenceReverse;
    }

    public void setFormInstanceSequenceReverse(String formInstanceSequenceReverse)
    {
        this.formInstanceSequenceReverse = formInstanceSequenceReverse;
    }

    public String getFormInstanceSequence()
    {
	fetch();
        return formInstanceSequence;
    }

    public void setFormInstanceSequence(String formInstanceSequence)
    {
        this.formInstanceSequence = formInstanceSequence;
    }

    public String getFormInstanceChronology()
    {
	fetch();
        return formInstanceChronology;
    }

    public void setFormInstanceChronology(String formInstanceChronology)
    {
        this.formInstanceChronology = formInstanceChronology;
    }

    static public Iterator findAll(String attrName, String attrValue)
    {
	IHome home = new Home();
	Iterator riskNeedLevel = home.findAll(attrName, attrValue, NSG_REPORTING_ASSESSED_RISK_LEVEL.class);
	return riskNeedLevel;
    }

    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	Iterator riskNeedLevels = home.findAll(event, NSG_REPORTING_ASSESSED_RISK_LEVEL.class);
	return riskNeedLevels;
    }

    public JuvenilePactScoresResponseEvent valueObject()
    {

	JuvenilePactScoresResponseEvent response = new JuvenilePactScoresResponseEvent();

	response.setDateCompleted(this.getDateCompleted());
	response.setForm_Name(this.getFormName());
	response.setFormInstanceChronology(this.getFormInstanceChronology());
	response.setFormInstanceId(this.getFormInstanceId());
	response.setFormInstanceSequence(this.getFormInstanceSequence());
	response.setFormInstanceSequenceReverse(this.getFormInstanceSequenceReverse());
	response.setPactOID(this.getOID());
	response.setScoreClassification(this.getScoreClassification());
	response.setScoreType(this.getScoreType());
	response.setSubjectEID(this.getSubjectEID());

	return response;
    }

}
