package pd.supervision.administercompliance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.UpdateNCRecommendationEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import naming.ViolationReportConstants;
import pd.codetable.Code;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * 
 * @roseuid 47DA99E502FA
 */
public class RecommendedApprovedCourtAction extends PersistentObject
{
	private String courtActionCodeId;
	private int ncResponseId; // this is the oid of violation report FK
	private String type;
	private String courtActionDescription;
	private boolean manual;

	Code recommendations = null;
	
	/**
	 * 
	 * @return Returns the courtActionCodeId.
	 */
	public String getCourtActionCodeId()
	{
		fetch();
		return courtActionCodeId;
	}

	/**
	 * 
	 * @param courtActionCodeId The courtActionCodeId to set.
	 */
	public void setCourtActionCodeId(String courtActionCodeId)
	{
		if (this.courtActionCodeId == null || !this.courtActionCodeId.equals(courtActionCodeId))
		{
			markModified();
		}
		this.courtActionCodeId = courtActionCodeId;
	}

	/**
	 * 
	 * @return Returns the courtActionDescription.
	 */
	public String getCourtActionDescription()
	{
		fetch();
		return courtActionDescription;
	}

	/**
	 * 
	 * @param courtActionDescription The courtActionDescription to set.
	 */
	public void setCourtActionDescription(String courtActionDescription)
	{
		if (this.courtActionDescription == null || !this.courtActionDescription.equals(courtActionDescription))
		{
			markModified();
		}
		this.courtActionDescription = courtActionDescription;
	}

	/**
	 * 
	 * @return Returns the type.
	 */
	public String getType()
	{
		fetch();
		if(this.type == null){
			return "";
		}else{
			return type;
		}		
	}

	/**
	 * 
	 * @param type The type to set.
	 */
	public void setType(String type)
	{
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		this.type = type;
	}
	
	/**
	 * @return Returns the ncResponseId.
	 */
	public int getNcResponseId() {
		fetch();
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(int ncResponseId) {
		if (this.ncResponseId != this.ncResponseId)
		{
			markModified();
		}
		this.ncResponseId = ncResponseId;
	}
	
	/**
	 * @return Returns the manual.
	 */
	public boolean isManual() {
		fetch();
		return manual;
	}

	/**
	 * @param ncResponseId The manual to set.
	 */
	public void setManual(boolean manual) {
		if (this.manual != this.manual)
		{
			markModified();
		}
		this.manual = manual;
	}
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, RecommendedApprovedCourtAction.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, RecommendedApprovedCourtAction.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), RecommendedApprovedCourtAction.class);
    }
    
    public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), RecommendedApprovedCourtAction.class);
        while(iter.hasNext()){
        	RecommendedApprovedCourtAction rec = (RecommendedApprovedCourtAction) iter.next();
        	map.put(rec.getOID(), rec);
        }
        return map;
    }
    
    /**
	 * @param recommendationId
	 * @return
	 */
	public static RecommendedApprovedCourtAction find(String recommendationId) {
        return (RecommendedApprovedCourtAction) new Home().find(recommendationId, RecommendedApprovedCourtAction.class);
    }

	/**
	 * @return
	 */
	public CodeResponseEvent getResponse() {
		CodeResponseEvent resp = new CodeResponseEvent();
		resp.setCode(this.getCourtActionCodeId());
		if(this.getCourtActionCodeId() != null  && !this.getCourtActionCodeId().equals("")){
			Code code = this.getRecommendation();
			if(code != null){
				resp.setDescription(code.getDescription());
				resp.setCodeId(this.getOID());
				resp.setTopic(ViolationReportConstants.REQUEST_RECOMMENDATION);
				resp.setTransaction(this.getType());
			}
		}	
		return resp;
	}

	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setRecomendation(UpdateNCRecommendationEvent event, String ncResponseId) {
		this.setCourtActionCodeId(event.getCourtActionCodeId());
		this.setCourtActionDescription(event.getCourtActionCodeDesc());	
		this.setNcResponseId(Integer.parseInt(ncResponseId));
		this.setManual(event.isManual());	
		this.setType(event.getType());
	}	
	
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code
	* @roseuid 4107DFB60134
	*/
	public Code getRecommendation()
	{
		fetch();
		initRecommendations();
		return recommendations;
	}


	/**
	* Initialize class relationship to class pd.codetable.Code
	* @roseuid 4107DFB6012A
	*/
	private void initRecommendations()
	{
		if (recommendations == null)
		{
			try
			{
				recommendations =
					(Code) new mojo
						.km
						.persistence
						.Reference(courtActionCodeId, Code.class, "SUGGESTED_COURT_ACTION")
						.getObject();
			}
			catch (Throwable t)
			{
				recommendations = null;
			}
		}
	}
	
	public void commit() {
		new Home().bind(this);
	}
}
