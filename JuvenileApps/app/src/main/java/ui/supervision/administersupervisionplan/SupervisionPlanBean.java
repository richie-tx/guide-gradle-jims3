/*
 * Created on Apr 29, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administersupervisionplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ui.supervision.administerassessments.AssessmentLightBean;

import mojo.km.utilities.DateUtil;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SupervisionPlanBean implements Comparable<SupervisionPlanBean>
{
	private String SupervisionPlanId = "";
	
	private Date supervisionPlanDate = null;
	
	private Date lastChangeDate = null;
	
	private String lastChangeDateStr = "";
	
	private String lastChangeUserId = "";
	
//	private Name lastChangeUserName = null;
	
	private String statusCd = "";
	
	private String lastChangeUserName = "";
	
	private String statusDesc = "";
		
	
	
	
	
	/**
	 * @return Returns the supervisionPlanDate.
	 */
	public Date getSupervisionPlanDate() {
		return supervisionPlanDate;
	}
	/**
	 * @param supervisionPlanDate The supervisionPlanDate to set.
	 */
	public void setSupervisionPlanDate(Date supervisionPlanDate) {
		this.supervisionPlanDate = supervisionPlanDate;
	}
	/**
	 * @return Returns the statusDesc.
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	/**
	 * @param statusDesc The statusDesc to set.
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	/**
	 * @return Returns the lastChangeDateStr.
	 */
	public String getLastChangeDateStr() {
		return lastChangeDateStr;
	}
	/**
	 * @param lastChangeDateStr The lastChangeDateStr to set.
	 */
	public void setLastChangeDateStr(String lastChangeDateStr) {
		this.lastChangeDateStr = lastChangeDateStr;
	}
	/**
	 * @return Returns the lastChangeDate.
	 */
	public Date getLastChangeDate() {
		return lastChangeDate;
	}
	/**
	 * @param lastChangeDate The lastChangeDate to set.
	 */
	public void setLastChangeDate(Date lastChangeDate)
	{
		this.lastChangeDate = lastChangeDate;
		this.lastChangeDateStr = "";
		
		if(lastChangeDate!=null)
		{
			try
			{
				this.lastChangeDateStr = DateUtil.dateToString(lastChangeDate, DateUtil.DATE_FMT_1);
			}
			catch(Exception ex)
			{
				this.lastChangeDateStr = "";
			}
		}
	}
	/**
	 * @return Returns the lastChangeUserId.
	 */
	public String getLastChangeUserId() {
		return lastChangeUserId;
	}
	/**
	 * @param lastChangeUserId The lastChangeUserId to set.
	 */
	public void setLastChangeUserId(String lastChangeUserId)
	{
		this.lastChangeUserId = lastChangeUserId;		
	}
	/**
	 * @return Returns the lastChangeUserName.
	 */
	public String getLastChangeUserName() {
		return lastChangeUserName;
	}
	/**
	 * @param lastChangeUserName The lastChangeUserName to set.
	 */
	public void setLastChangeUserName(String lastChangeUserName) {
		this.lastChangeUserName = lastChangeUserName;
	}
	/**
	 * @return Returns the statusCd.
	 */
	public String getStatusCd() {
		return statusCd;
	}
	/**
	 * @param statusCd The statusCd to set.
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	/**
	 * @return Returns the supervisionPlanId.
	 */
	public String getSupervisionPlanId() {
		return SupervisionPlanId;
	}
	/**
	 * @param supervisionPlanId The supervisionPlanId to set.
	 */
	public void setSupervisionPlanId(String supervisionPlanId) {
		SupervisionPlanId = supervisionPlanId;
	}
	@Override
	public int compareTo(SupervisionPlanBean o) {
		if(o == null)
		{
			return 1;
		}
		if(this.getLastChangeDate() == o.getLastChangeDate()
				&& this.getStatusDesc() == o.getStatusDesc()){
			return 0;
		}else{
			if(!(this.getStatusDesc().compareTo(o.getStatusDesc())== 0)){
				return this.getStatusDesc().compareTo(o.getStatusDesc());
			}else{
				return o.getLastChangeDate().compareTo(this.getLastChangeDate());
			}
		}
	}

}
