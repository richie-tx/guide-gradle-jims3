//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\transfers\\CSTransfer.java
package pd.commonfunctionality.spnsplit;

import java.util.Iterator;

import messaging.spnsplit.reply.SpnSplitErrorResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class SpnSplitException extends PersistentObject {
	static public SpnSplitException find(String oid) {
		IHome home = new Home();
		return (SpnSplitException) home.find(oid, SpnSplitException.class);
	}
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SpnSplitException.class);
	}
	
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(SpnSplitException.class);
	}
    
	private String validDefendantId;
	private String errDefendantId;
	private String errorCodeId;
	private String statusId;
	private String errMessage;
	private String criminalCaseIds;
	
	public String getCriminalCaseIds() {
		fetch();
		return criminalCaseIds;
	}
	public void setCriminalCaseIds(String criminalCaseIds) {
		if (this.criminalCaseIds == null || !this.criminalCaseIds.equals(criminalCaseIds))
		{
			markModified();
		}
		this.criminalCaseIds = criminalCaseIds;
	}
	public String getValidDefendantId() {
		fetch();
		return validDefendantId;
	}
	public void setValidDefendantId(String validDefendantId) {
		if (this.validDefendantId == null || !this.validDefendantId.equals(validDefendantId))
		{
			markModified();
		}
		this.validDefendantId = validDefendantId;
	}
	public String getErrDefendantId() {
		fetch();
		return errDefendantId;
	}
	public void setErrDefendantId(String errDefendantId) {
		if (this.errDefendantId == null || !this.errDefendantId.equals(errDefendantId))
		{
			markModified();
		}
		this.errDefendantId = errDefendantId;
	}
	public String getErrorCodeId() {
		fetch();
		return errorCodeId;
	}
	public void setErrorCodeId(String errorCodeId) {
		if (this.errorCodeId == null || !this.errorCodeId.equals(errorCodeId))
		{
			markModified();
		}
		this.errorCodeId = errorCodeId;
	}
	public String getStatusId() {
		fetch();
		return statusId;
	}
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		this.statusId = statusId;
	}
	public String getErrMessage() {
		fetch();
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		if (this.errMessage == null || !this.errMessage.equals(errMessage))
		{
			markModified();
		}
		this.errMessage = errMessage;
	}
	
	public static void create(String errDefendantId, String validDefendantId, String errMsg, String errCodeId, String criminalCaseIds, String statusId) {
    	SpnSplitException sp = new SpnSplitException();
    	sp.setErrDefendantId(errDefendantId);
    	sp.setValidDefendantId(validDefendantId);
    	sp.setErrMessage(errMsg);
    	sp.setErrorCodeId(errCodeId);
    	sp.setStatusId(statusId);
    	sp.setCriminalCaseIds(criminalCaseIds);
	}
	public SpnSplitErrorResponseEvent getResponseEvent() {
		SpnSplitErrorResponseEvent resp = new SpnSplitErrorResponseEvent();
		resp.setErroneousSpn(this.getErrDefendantId());
		resp.setErrorCodeId(this.getErrorCodeId());
		resp.setErrorResponseId(this.getOID());
		resp.setMsg(this.getErrMessage());
		resp.setStatusId(this.getStatusId());
		resp.setValidSpn(this.getValidDefendantId());
		return resp;
	}
}
