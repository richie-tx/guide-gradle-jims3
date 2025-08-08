/*
 * Created on Nov 16, 2006
 *
 */
package ui.supervision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SupervisionPeriodInfo implements Comparable{
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	private Date supPeriodBeginDate=null;
	private Date supPeriodEndDate=null;
	private String supPeriodId="";
	private Collection cases=new ArrayList();  // collection of CaseInfo objects
	private boolean selected=false;
	
	
	public int compareTo(Object otherSupervisionPeriod) {
		  Date otherBeginDate = ((SupervisionPeriodInfo)otherSupervisionPeriod).getSupPeriodBeginDate();
		  if(this.supPeriodBeginDate==null || otherBeginDate==null)
		  	return 1;
		  return this.supPeriodBeginDate.compareTo(otherBeginDate);
		}	
	/**
	 * @return Returns the cases.
	 */
	
	public void addCase(CaseInfo aCaseToAdd){
		if(aCaseToAdd!=null){
			if(getCases()==null){
				cases=new ArrayList();
			}
			cases.add(aCaseToAdd);
		}
	}
	
	public Collection getCases() {
		return cases;
	}
	/**
	 * @param cases The cases to set.
	 */
	public void setCases(Collection cases) {
		this.cases = cases;
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
	 * @return Returns the dateOfBirth.
	 */
	public void setSupPeriodBeginDateAsStr(String aStringDate) {
		if(aStringDate==null || aStringDate.equals(""))
			supPeriodBeginDate=null;
		try{
			supPeriodBeginDate=DateUtil.stringToDate(aStringDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			supPeriodBeginDate=null;
		}
	}
	
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getSupPeriodBeginDateAsStr() {
		if(supPeriodBeginDate==null){
			return "";
		}
		try{
			return DateUtil.dateToString(supPeriodBeginDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			return "";
		}
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
	 * @return Returns the dateOfBirth.
	 */
	public void setSupPeriodEndDateAsStr(String aStringDate) {
		if(aStringDate==null || aStringDate.equals(""))
			supPeriodEndDate=null;
		try{
			supPeriodEndDate=DateUtil.stringToDate(aStringDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			supPeriodEndDate=null;
		}
	}
	
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getSupPeriodEndDateAsStr() {
		if(supPeriodEndDate==null){
			return "";
		}
		try{
			return DateUtil.dateToString(supPeriodEndDate, UIConstants.DATE_FMT_1);
		}
		catch(Exception e){
			return "";
		}
	}
	/**
	 * @return Returns the supPeriodId.
	 */
	public String getSupPeriodId() {
		if(supPeriodId==null)
			return "";
		return supPeriodId;
	}
	/**
	 * @param supPeriodId The supPeriodId to set.
	 */
	public void setSupPeriodId(String supPeriodId) {
		this.supPeriodId = supPeriodId;
	}
	/**
	 * @return Returns the selected.
	 */
	public boolean isSelected() {
		return selected;
	}
	/**
	 * @param selected The selected to set.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
