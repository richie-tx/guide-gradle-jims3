package pd.supervision.administercompliance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import naming.ViolationReportConstants;

import messaging.administercompliance.UpdateNCReasonForTransferEvent;
import messaging.administercompliance.reply.NCReasonForTransferResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * 
 * @roseuid 47DA99E301D1
 */
public class ReasonForTransfer extends PersistentObject
{
	private String reasonForTransferCodeId;
	private String reasonForTransferCodeDesc;
	private int ncResponseId; // this is the oid of violation report FK

	
	/**
	 * 
	 * @return Returns the reasonForTransferCodeId.
	 */
	public String getReasonForTransferCodeId()
	{
		fetch();
		return reasonForTransferCodeId;
	}

	/**
	 * 
	 * @param reasonForTransferCodeId The reasonForTransferCodeId to set.
	 */
	public void setReasonForTransferCodeId(String reasonForTransferCodeId)
	{
		if (this.reasonForTransferCodeId == null || !this.reasonForTransferCodeId.equals(reasonForTransferCodeId))
		{
			markModified();
		}
		this.reasonForTransferCodeId = reasonForTransferCodeId;
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
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, ReasonForTransfer.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, ReasonForTransfer.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), ReasonForTransfer.class);
    }
    
    public static ReasonForTransfer find(String reasonForTransferId){
        return (ReasonForTransfer) new Home().find(reasonForTransferId, ReasonForTransfer.class);
    }
    
    public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), ReasonForTransfer.class);
        while(iter.hasNext()){
        	ReasonForTransfer rft = (ReasonForTransfer) iter.next();
        	map.put(rft.getOID(), rft);
        }
        return map;
    }

	/**
	 * @return
	 */
	public CodeResponseEvent getResponse() {
		CodeResponseEvent resp = new CodeResponseEvent();
		resp.setCodeId(this.getOID());
		resp.setCode(this.getReasonForTransferCodeId());
		resp.setDescription(this.getReasonForTransferCodeDesc());
		resp.setTopic(ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER);
		return resp;
	}

	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setReasonForTransfer(UpdateNCReasonForTransferEvent event, String ncResponseId) {
		this.setReasonForTransferCodeId(event.getReasonForTransferCodeId());
		this.setNcResponseId(Integer.parseInt(ncResponseId));
		this.setReasonForTransferCodeDesc(event.getReasonForTransferCodeDesc());		
	}

	/**
	 * @return the reasonForTransferCodeDesc
	 */
	public String getReasonForTransferCodeDesc() {
		fetch();
		return reasonForTransferCodeDesc;
	}

	/**
	 * @param reasonForTransferCodeDesc the reasonForTransferCodeDesc to set
	 */
	public void setReasonForTransferCodeDesc(String reasonForTransferCodeDesc) {
		if (this.reasonForTransferCodeDesc == null || !this.reasonForTransferCodeDesc.equals(reasonForTransferCodeDesc))
		{
			markModified();
		}
		this.reasonForTransferCodeDesc = reasonForTransferCodeDesc;
	}
	
	public void commit() {
		new Home().bind(this);
	}
}
