//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\UpdateNonCompliantEventEvent.java

package messaging.administercompliance;

import java.util.List;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateNonCompliantEventEvent extends RequestEvent 
{
	private String orderConditionName;	
	private String caseNumber;
	private String occurrenceDate;
	private String occurrenceTime;
	private boolean isAM;
	private String[] eventTypeCodeIds;
	private String newEventType;	
	private String details;
	private int sprOrderConditionId;
	private String conditionId;
	private List eventTypeList;
    private String eventTypes; 
    private String sprvnBeginDate;
    private int orderChainNumber;
    private String groupId;
    private boolean compliant;
    private String AMPMId; 
    private String displayCaseNum;

	/**
	 * @return Returns the orderChainNumber.
	 */
	public int getOrderChainNumber() {
		return orderChainNumber;
	}
	/**
	 * @param orderChainNumber The orderChainNumber to set.
	 */
	public void setOrderChainNumber(int orderChainNumber) {
		this.orderChainNumber = orderChainNumber;
	}
    /**
     * @roseuid 473B899800E3
     */
    public CreateNonCompliantEventEvent() 
    {
     
    }
    
    /**
	 * @return Returns the sprOrderConditionId.
	 */
	public int getSprOrderConditionId() {
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(int sprOrderConditionId) {
		this.sprOrderConditionId = sprOrderConditionId;
	}
   /**
    * @return Returns the details.
    */
   public String getDetails() {
   	   return details;
   }
   /**
    * @param details The details to set.
    */
   public void setDetails(String details) {
   	   this.details = details;
   }
   /**
    * @return Returns the eventTypeIds.
    */
   public String[] getEventTypeCodeIds() {
   	   return eventTypeCodeIds;
   }
   /**
    * @param eventTypeIds The eventTypeIds to set.
    */
   public void setEventTypeCodeIds(String[] eventTypeCodeIds) {
  /** 		eventTypes="";
		if(eventTypeIds!=null && eventTypeIds.length>0){
			StringBuffer myStrBuffer=new StringBuffer();
			for(int loopX=0;loopX<eventTypeIds.length;loopX++){
				String eventItem=eventTypeIds[loopX];
		//		if(getCasenoteSubjectList() !=null &&  getCasenoteSubjectList().size()>0){
		//			String subjDesc=CodeHelper.getCodeDescriptionByCode(getCasenoteSubjectList(),subjectItem);
					if(myStrBuffer.length()>0){
						myStrBuffer.append(", ");
					}
					myStrBuffer.append(eventItem);
			//	}
				
			}
			eventTypes=myStrBuffer.toString();
		}
    */	
   	   this.eventTypeCodeIds = eventTypeCodeIds;
   }
   /**
    * @return Returns the isAM.
    */
   public boolean isAM() {
   	   return isAM;
   }
   /**
    * @param isAM The isAM to set.
    */
   public void setAM(boolean isAM) {
   	   this.isAM = isAM;
   }
   /**
    * @return Returns the occurenceDate.
    */
   public String getOccurrenceDate() {
   	   return occurrenceDate;
   }
   /**
    * @param occurenceDate The occurenceDate to set.
    */
   public void setOccurrenceDate(String occurrenceDate) {
   	   this.occurrenceDate = occurrenceDate;
   }
   /**
    * @return Returns the occurrenceTime.
    */
   public String getOccurrenceTime() {
   	   return occurrenceTime;
   }
   /**
    * @param occurrenceTime The occurrenceTime to set.
    */
   public void setOccurrenceTime(String occurrenceTime) {
   	   this.occurrenceTime = occurrenceTime;
   }
	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}

	/**
	 * @param caseNumber
	 *            The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	/**
	 * @return Returns the newEventType.
	 */
	public String getNewEventType() {
		return newEventType;
	}

	/**
	 * @param newEventType
	 *            The newEventType to set.
	 */
	public void setNewEventType(String newEventType) {
		this.newEventType = newEventType;
	}

	/**
	 * @return Returns the orderConditionName.
	 */
	public String getOrderConditionName() {
		return orderConditionName;
	}

	/**
	 * @param orderConditionName
	 *            The orderConditionName to set.
	 */
	public void setOrderConditionName(String orderConditionName) {
		this.orderConditionName = orderConditionName;
	}
	
	/**
	 * @return Returns the eventTypeList.
	 */
	public List getEventTypeList() {
		return eventTypeList;
	}
	/**
	 * @param eventTypeList The eventTypeList to set.
	 */
	public void setEventTypeList(List eventTypeList) {
		this.eventTypeList = eventTypeList;
	}
	
	/**
	 * @return Returns the eventTypes.
	 */
	public String getEventTypes() {
		return eventTypes;
	}
	/**
	 * @param eventTypes The eventTypes to set.
	 */
	public void setEventTypes(String eventTypes) {
		this.eventTypes = eventTypes;
	}
	
	/**
	 * @return Returns the conditionId.
	 */
	public String getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * @return Returns the sprvnBeginDate.
	 */
	public String getSprvnBeginDate() {
		return sprvnBeginDate;
	}
	/**
	 * @param sprvnBeginDate The sprvnBeginDate to set.
	 */
	public void setSprvnBeginDate(String sprvnBeginDate) {
		this.sprvnBeginDate = sprvnBeginDate;
	}
	/**
	 * @return Returns the compliant.
	 */
	public boolean isCompliant() {
		return compliant;
	}
	/**
	 * @param compliant The compliant to set.
	 */
	public void setCompliant(boolean compliant) {
		this.compliant = compliant;
	}
	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the aMPMId
	 */
	public String getAMPMId() {
		return AMPMId;
	}
	/**
	 * @param id the aMPMId to set
	 */
	public void setAMPMId(String id) {
		AMPMId = id;
	}
	/**
	 * @return the displayCaseNum
	 */
	public String getDisplayCaseNum() {
		return displayCaseNum;
	}
	/**
	 * @param displayCaseNum the displayCaseNum to set
	 */
	public void setDisplayCaseNum(String displayCaseNum) {
		this.displayCaseNum = displayCaseNum;
	}
	
}