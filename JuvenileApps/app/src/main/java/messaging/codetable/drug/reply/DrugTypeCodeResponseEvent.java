package messaging.codetable.drug.reply;

import pd.codetable.criminal.DrugTypeCode;
import mojo.km.messaging.ResponseEvent;

public class DrugTypeCodeResponseEvent  extends ResponseEvent
{
    private String drugTypeCodeId;
    private String code;
    private String description;
    private String drugTest;
    private String status;
    
    public String getDrugTypeCodeId()
    {
        return drugTypeCodeId;
    }
    public void setDrugTypeCodeId(String drugTypeCodeId)
    {
        this.drugTypeCodeId = drugTypeCodeId;
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
    public String getDrugTest()
    {
        return drugTest;
    }
    public void setDrugTest(String drugTest)
    {
        this.drugTest = drugTest;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    
    public static DrugTypeCodeResponseEvent getResponseValues(DrugTypeCode drugTypeCode){
	DrugTypeCodeResponseEvent response =  new DrugTypeCodeResponseEvent();
	response.setCode(drugTypeCode.getCode());
	response.setDescription(drugTypeCode.getDescription());
	response.setDrugTest(drugTypeCode.getDrugTest());
	response.setStatus(drugTypeCode.getStatus());
	return response;
    }
}
