package messaging.codetable.drug.reply;

import pd.codetable.criminal.DrugTestResultCode;
import pd.codetable.criminal.DrugTypeCode;
import mojo.km.messaging.ResponseEvent;

public class DrugTestResultCodeResponseEvent extends ResponseEvent
{
    private String drugTestResultCodeId;
    private String code;
    private String description;
    private String result;
    private String status;
    private String activityCd;
    
    public String getDrugTestResultCodeId()
    {
        return drugTestResultCodeId;
    }
    public void setDrugTestResultCodeId(String drugTestResultCodeId)
    {
        this.drugTestResultCodeId = drugTestResultCodeId;
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
    public String getResult()
    {
        return result;
    }
    public void setResult(String result)
    {
        this.result = result;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getActivityCd()
    {
        return activityCd;
    }
    public void setActivityCd(String activityCd)
    {
        this.activityCd = activityCd;
    }
    
    public static DrugTestResultCodeResponseEvent getResponseValues(DrugTestResultCode drugTestResultCode){
	DrugTestResultCodeResponseEvent response =  new DrugTestResultCodeResponseEvent();
   	response.setCode(drugTestResultCode.getCode());
   	response.setDescription(drugTestResultCode.getDescription());
   	response.setResult(drugTestResultCode.getResults());
   	response.setStatus(drugTestResultCode.getStatus());
   	response.setActivityCd(drugTestResultCode.getActivityCd());
   	return response;
       }
    

}
