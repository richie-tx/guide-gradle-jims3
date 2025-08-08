package pd.supervision.administercompliance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.UpdateNCFeeEvent;
import messaging.administercompliance.reply.NCFeeResponseEvent;
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
 * Type of fee assessed, such as Fine, Court Costs, Restitution, Attorney Fees.
 * Populated from legacy - FAS and Cost Bill Tracking.
 */
public class DelinquentFee extends PersistentObject
{
	/**
	 * Type of fee assessed, such as Fine, Court Costs, Restitution, Attorney Fees.
	 * Populated from legacy - FAS and Cost Bill Tracking.
	 */
	private String payType;
	private String amountOrdered;
	private String paidToDate;
	private String amountDelinquent;
	private int ncResponseId; // this is the oid of violation report FK
	private boolean manualAdded;
	
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		fetch();
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		if (this.manualAdded != manualAdded)
		{
			markModified();
		}
		this.manualAdded = manualAdded;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public int getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(int ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	

	/**
	 * 
	 * @return Returns the amountDelinquent.
	 */
	public String getAmountDelinquent()
	{
		fetch();
		return amountDelinquent;
	}

	/**
	 * 
	 * @param amountDelinquent The amountDelinquent to set.
	 */
	public void setAmountDelinquent(String amountDelinquent)
	{
		if (this.amountDelinquent == null || !this.amountDelinquent.equals(amountDelinquent))
		{
			markModified();
		}
		this.amountDelinquent = amountDelinquent;
	}

	/**
	 * 
	 * @return Returns the amountOrdered.
	 */
	public String getAmountOrdered()
	{
		fetch();
		return amountOrdered;
	}

	/**
	 * 
	 * @param amountOrdered The amountOrdered to set.
	 */
	public void setAmountOrdered(String amountOrdered)
	{
		if (this.amountOrdered == null || !this.amountOrdered.equals(amountOrdered))
		{
			markModified();
		}
		this.amountOrdered = amountOrdered;
	}

	/**
	 * 
	 * @return Returns the paidToDate.
	 */
	public String getPaidToDate()
	{
		fetch();
		return paidToDate;
	}

	/**
	 * 
	 * @param paidToDate The paidToDate to set.
	 */
	public void setPaidToDate(String paidToDate)
	{
		if (this.paidToDate == null || !this.paidToDate.equals(paidToDate))
		{
			markModified();
		}
		this.paidToDate = paidToDate;
	}

	/**
	 * 
	 * @return Returns the payType.
	 */
	public String getPayType()
	{
		fetch();
		return payType;
	}

	/**
	 * 
	 * @param payType The payType to set.
	 */
	public void setPayType(String payType)
	{
		if (this.payType == null || !this.payType.equals(payType))
		{
			markModified();
		}
		this.payType = payType;
	}
	
	public static DelinquentFee find(String delinquentFeeId){
        return (DelinquentFee) new Home().find(delinquentFeeId, DelinquentFee.class);
    }
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, DelinquentFee.class);
    }
	
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, DelinquentFee.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), DelinquentFee.class);
    }
    
    public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), DelinquentFee.class);
        while(iter.hasNext()){
        	DelinquentFee fee = (DelinquentFee) iter.next();
        	map.put(fee.getOID(), fee);
        }
        return map;
    }
    
	/**
	 * @return
	 */
	public NCFeeResponseEvent getResponse() {
		NCFeeResponseEvent resp = new NCFeeResponseEvent();
		resp.setAmountOrdered(this.getAmountOrdered());
		resp.setDelinquentAmount(this.getAmountDelinquent());
		resp.setFeeId(this.getOID());
		resp.setPaidToDate(this.getPaidToDate());
		resp.setPayType(this.getPayType());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		resp.setManualAdded(this.isManualAdded());
		return resp;
	}
	
	/**
	 * @return
	 */
	public NCFeeResponseEvent getLegacyResponse() {
		NCFeeResponseEvent resp = new NCFeeResponseEvent();
		if(this.getAmountOrdered() != null && !"".equals(this.getAmountOrdered())){
			resp.setAmountOrdered(this.formatAmt(this.getAmountOrdered()));
		}		
		if(this.getAmountDelinquent() != null && !"".equals(this.getAmountDelinquent())){
			resp.setDelinquentAmount(this.formatAmt(this.getAmountDelinquent()));
		}
		if(this.getPaidToDate() != null && !"".equals(this.getPaidToDate())){
			resp.setPaidToDate(this.formatAmt(this.getPaidToDate()));
		}
		resp.setFeeId(this.getOID());
		resp.setPayType(this.getPayType());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		resp.setManualAdded(this.isManualAdded());
		return resp;
	}
	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setDelinquentFee(UpdateNCFeeEvent event, String ncResponseId) {
		this.setAmountOrdered(event.getAmountOrdered());
		this.setAmountDelinquent(event.getAmountDelinquent());
		this.setPaidToDate(event.getPaidToDate());
		this.setPayType(event.getPayType());	
		this.setNcResponseId(Integer.parseInt(ncResponseId));
		this.setManualAdded(event.isManualAdded());
	}
	
	public void commit() {
		new Home().bind(this);
	}
	
	/**
	 * @param amount
	 */
	public String formatAmt(String inAmt){
		double d= (Double.parseDouble(inAmt) / 100);
		String amt = new StringBuffer("").append(d).toString();
		String [] amtStr = amt.split("\\.");
		if (amtStr[1].length() == 1){
			amt = amt + "0";
		}
		return amt;
	}
}
