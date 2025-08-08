/*
 * Created on Dec 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.spnsplit.reply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpnSplitOrderPeriodResponseEvent extends ResponseEvent { //implements Comparable {
    private String supPeriodId;
	private Date supPeriodBeginDate;
    private Date supPeriodEndDate;
    //private Date supOrderBeginDate;
    //private Date supOrderEndDate;
    private List spnSplitOrderDetails = new ArrayList();
    
	/**
	 * @return Returns the spnSplitOrderDetails.
	 */
	public List getSpnSplitOrderDetails() {
		return spnSplitOrderDetails;
	}
	/**
	 * @param spnSplitOrderDetails The spnSplitOrderDetails to set.
	 */
	public void setSpnSplitOrderDetails(List spnSplitOrderDetails) {
        
		this.spnSplitOrderDetails.addAll(spnSplitOrderDetails);
	}
    
    /**
     * @param spnOrderDetails
     */
    public void addToSpnSplitOrderDetailsList(SpnSplitOrderDetailsResponseEvent spnOrderDetails)
    {
    	this.spnSplitOrderDetails.add(spnOrderDetails);
    }
    
   
    /**
     * @return Returns the supPeriodBeginDate.
     */
    public Date getSupPeriodBeginDate() {
    	return supPeriodBeginDate;
    }
    /**
     * @param supPeriodBeginDate The supPeriodBeginDate to set.
     */
    public void setSupPeriodBeginDate(Date supPeriodBeginDate) {
    	this.supPeriodBeginDate = supPeriodBeginDate;
    }
	/**
	 * @return Returns the supPeriodEndDate.
	 */
	public Date getSupPeriodEndDate() {
		return supPeriodEndDate;
	}
	/**
	 * @param supPeriodEndDate The supPeriodEndDate to set.
	 */
	public void setSupPeriodEndDate(Date supPeriodEndDate) {
		this.supPeriodEndDate = supPeriodEndDate;
	}

    /**
     * @return Returns the supPeriodId.
     */
    public String getSupPeriodId() {
        return supPeriodId;
    }
    /**
     * @param supPeriodId The supPeriodId to set.
     */
    public void setSupPeriodId(String supPeriodId) {
        this.supPeriodId = supPeriodId;
    }
    
}
