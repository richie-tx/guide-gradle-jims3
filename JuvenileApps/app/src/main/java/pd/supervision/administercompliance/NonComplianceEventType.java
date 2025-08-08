package pd.supervision.administercompliance;

import java.util.Iterator;

import pd.codetable.supervision.SupervisionCode;
import pd.security.PDSecurityHelper;

import messaging.administercompliance.reply.NonComplianceEventTypeResponseEvent;
import messaging.codetable.GetSupervisionCodesByCodeEvent;
import messaging.codetable.GetSupervisionCodesByValueEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class NonComplianceEventType extends PersistentObject
{
	private int nonComplianceEventId;
	private String nonComplianceEventCodeId;
	private String nonComplianceEventCodeDesc;

	public static Iterator findAllByNumericParam(String attributeName, String attributeValue){
		return new Home().findAll(attributeName, new Integer(attributeValue), NonComplianceEventType.class);
	}
	
	public static Iterator findAll(String attributeName, String attributeValue){
		return new Home().findAll(attributeName, attributeValue, NonComplianceEventType.class);
	}
/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public NonComplianceEventType()
	{
	}
	
	/**
	 * @return
	 */
	public NonComplianceEventType(int nonComplianceEventId, String nonComplianceEventCodeId){
		this.setNonComplianceEventId(nonComplianceEventId);
		this.setNonComplianceEventCodeId(nonComplianceEventCodeId);
	}	


	/**
	 * 
	 * @return Returns the nonComplianceEventCode.
	 */
	public String getNonComplianceEventCodeId()
	{
		fetch();
		return nonComplianceEventCodeId;
	}

	/**
	 * @return Returns the nonComplianceEventId.
	 */
	public int getNonComplianceEventId() {
		fetch();
		return nonComplianceEventId;
	}
	
	/**
	 * @param nonComplianceEventId The nonComplianceEventId to set.
	 */
	public void setNonComplianceEventId(int nonComplianceEventId) {
		if (this.nonComplianceEventId != nonComplianceEventId)
		{
			markModified();
		}
		this.nonComplianceEventId = nonComplianceEventId;
	}

	/**
	 * @return
	 */
	public NonComplianceEventTypeResponseEvent getResponseEvent() {
		NonComplianceEventTypeResponseEvent resp = new NonComplianceEventTypeResponseEvent();
		resp.setNonComplianceEventId(new StringBuffer("").append(this.getNonComplianceEventId()).toString());
		resp.setNonComplianceEventCodeId(this.getNonComplianceEventCodeId());
		resp.setNonComplianceEventTypeId(this.getOID());
		return resp;
	}
	
	/**
	 * @param nonComplianceEventCodeId The nonComplianceEventCodeId to set.
	 */
	public void setNonComplianceEventCodeId(String nonComplianceEventCodeId) {
		if (this.nonComplianceEventCodeId != nonComplianceEventCodeId)
		{
			markModified();
		}
		this.nonComplianceEventCodeId = nonComplianceEventCodeId;
	}
	/**
	 * @return Returns the nonComplianceEventCodeDesc.
	 */
	public String getNonComplianceEventCodeDesc() {
		fetch();
		return nonComplianceEventCodeDesc;
	}
	
	public String getNonComplianceEventCodeDescription() {
		GetSupervisionCodesByCodeEvent gEvent = new GetSupervisionCodesByCodeEvent();
		gEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
		gEvent.setCodeTableName("EVENT_TYPE");
		gEvent.setCodeId(this.getNonComplianceEventCodeId());
		Iterator iterator = SupervisionCode.findAll(gEvent);
		while(iterator.hasNext()){
			SupervisionCode code = (SupervisionCode) iterator.next();
			if(code != null){
				return code.getDescription();
			}	
		}			
		return "";
	}
	
	/**
	 * @param nonComplianceEventCodeDesc The nonComplianceEventCodeDesc to set.
	 */
	public void setNonComplianceEventCodeDesc(String nonComplianceEventCodeDesc) {
		if (this.nonComplianceEventCodeDesc != nonComplianceEventCodeDesc)
		{
			markModified();
		}
		this.nonComplianceEventCodeDesc = nonComplianceEventCodeDesc;
	}

	public void bind() {
		new Home().bind(this);		
	}
}
