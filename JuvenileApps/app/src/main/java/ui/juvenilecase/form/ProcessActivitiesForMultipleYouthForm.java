package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;

import org.apache.struts.action.ActionForm;

public class ProcessActivitiesForMultipleYouthForm extends ActionForm
{
    private String activityDate;
    private Collection<JuvenileActivityTypeCodeResponseEvent> activityCodes;
    private String selectedCategoryId;
    private String selectedCategoryIdDesc;
    private String selectedTypeId;
    private String selectedTypeIdDesc;
    private String selectedActivityId;
    private String selectedActivityIdDesc;
    private String juvenileId;
    private String comments;
    private String message;
    private List<JuvenileProfileDetailResponseEvent>juveniles = new ArrayList<>();
    

    public void clear(){
	this.activityDate = "";
	this.activityCodes = null;
	this.selectedCategoryId = "";
	this.selectedTypeId = "";
	this.selectedActivityId = "";
	this.juvenileId = "";
	this.comments = "";
	this.juveniles = new ArrayList<>();
	this.message= "";
	this.selectedCategoryIdDesc = "";
	this.selectedTypeIdDesc = "";
	this.selectedActivityIdDesc = "";
		
    }
    
    public String getActivityDate()
    {
        return activityDate;
    }

    public void setActivityDate(String activityDate)
    {
        this.activityDate = activityDate;
    }

    public Collection getActivityCodes()
    {
        return activityCodes;
    }

    public void setActivityCodes(Collection<JuvenileActivityTypeCodeResponseEvent> activityCodes)
    {
        this.activityCodes = activityCodes;
    }

    public String getSelectedCategoryId()
    {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(String selectedCategoryId)
    {
        this.selectedCategoryId = selectedCategoryId;
    }

    public String getSelectedTypeId()
    {
        return selectedTypeId;
    }

    public void setSelectedTypeId(String selectedTypeId)
    {
        this.selectedTypeId = selectedTypeId;
    }

    public String getSelectedActivityId()
    {
        return selectedActivityId;
    }

    public void setSelectedActivityId(String selectedActivityId)
    {
        this.selectedActivityId = selectedActivityId;
    }

    public String getJuvenileId()
    {
        return juvenileId;
    }

    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }

    public List<JuvenileProfileDetailResponseEvent> getJuveniles()
    {
        return juveniles;
    }

    public void setJuveniles(List<JuvenileProfileDetailResponseEvent> juveniles)
    {
        this.juveniles = juveniles;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getSelectedCategoryIdDesc()
    {
        return selectedCategoryIdDesc;
    }

    public void setSelectedCategoryIdDesc(String selectedCategoryIdDesc)
    {
        this.selectedCategoryIdDesc = selectedCategoryIdDesc;
    }

    public String getSelectedTypeIdDesc()
    {
        return selectedTypeIdDesc;
    }

    public void setSelectedTypeIdDesc(String selectedTypeIdDesc)
    {
        this.selectedTypeIdDesc = selectedTypeIdDesc;
    }

    public String getSelectedActivityIdDesc()
    {
        return selectedActivityIdDesc;
    }

    public void setSelectedActivityIdDesc(String selectedActivityIdDesc)
    {
        this.selectedActivityIdDesc = selectedActivityIdDesc;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
