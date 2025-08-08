/*
 * Created on Nov 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision;

import java.util.Collection;
import java.util.Date;

import ui.common.CodeHelper;

import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
	public class CaseInfo extends BasicCaseInfo implements Comparable{
		private String supPeriodId="";
		private Date orderFileDate=null;
		private String orderStatusId="";
		private String orderStatus="";
		private String orderVersionNum="";
		private String orderVersionId="";
		private String orderVersion="";
		private boolean selected=false;
		private String orderId="";
		private String agencyId="";
		private String offense="";
		private String offenseCodeId="";
		
		public int compareTo(Object otherCase) {
			  Date otherBeginDate = ((CaseInfo)otherCase).getCaseSupPeriodBeginDate();
			  if(this.caseSupPeriodBeginDate==null || otherBeginDate==null)
			  	return 1;
			  return this.caseSupPeriodBeginDate.compareTo(otherBeginDate);
			}	
		
		public String getOrderVersionTitle(){
			if(orderVersionNum!=null && orderVersionNum.length()>0){
				String versionNum=orderVersionNum;
				if(versionNum.equalsIgnoreCase("1")){
					versionNum=versionNum + "st";
				}
				else if(versionNum.equalsIgnoreCase("2")){
					versionNum=versionNum + "nd";
				}
				else if(versionNum.equalsIgnoreCase("3")){
					versionNum=versionNum + "rd";
				}
				else{
					versionNum=versionNum + "th";
				}
				return versionNum + " " + getOrderVersion();
			}
			else
				return getOrderVersion();
		}

		/**
		 * @return Returns the orderFileDate.
		 */
		public Date getOrderFileDate() {
			return orderFileDate;
		}
		public String getOrderFileDateAsStr(){
			if(orderFileDate==null)
				return "";
			else{
				try{
					return DateUtil.dateToString(orderFileDate,UIConstants.DATE_FMT_1);
				}
				catch(Exception e){
					return "";
				}
			}
		}
		/**
		 * @param orderFileDate The orderFileDate to set.
		 */
		public void setOrderFileDate(Date orderFileDate) {
			this.orderFileDate = orderFileDate;
		}
		/**
		 * @return Returns the orderId.
		 */
		public String getOrderId() {
			return orderId;
		}
		/**
		 * @param orderId The orderId to set.
		 */
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		/**
		 * @return Returns the orderStatus.
		 */
		public String getOrderStatus() {
			return orderStatus;
		}
		/**
		 * @param orderStatus The orderStatus to set.
		 */
		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}
		/**
		 * @return Returns the orderStatusId.
		 */
		public String getOrderStatusId() {
			return orderStatusId;
			
		}
		/**
		 * @param orderStatusId The orderStatusId to set.
		 */
		public void setOrderStatusId(String orderStatusId) {
			this.orderStatusId = orderStatusId;
			if (orderStatusId == null || orderStatusId.equals(""))
			{
				orderStatus = "";
				return;
			}
			Collection myCodes=CodeHelper.getCodes(PDCodeTableConstants.ORDER_STATUS);
			if (myCodes != null && myCodes.size() > 0)
			{
				orderStatus = CodeHelper.getCodeDescriptionByCode(myCodes, orderStatusId);
			}
		}
		/**
		 * @return Returns the orderVersion.
		 */
		public String getOrderVersion() {
			return orderVersion;
		}
		/**
		 * @param orderVersion The orderVersion to set.
		 */
		public void setOrderVersion(String orderVersion) {
			this.orderVersion = orderVersion;
		}
		/**
		 * @return Returns the orderVersionId.
		 */
		public String getOrderVersionId() {
			return orderVersionId;
		}
		/**
		 * @param orderVersionId The orderVersionId to set.
		 */
		public void setOrderVersionId(String orderVersionId) {
			this.orderVersionId = orderVersionId;
			if (orderVersionId == null || orderVersionId.equals(""))
			{
				orderVersionId = "";
				return;
			}
			Collection myCodes=CodeHelper.getCodes(PDCodeTableConstants.VERSION_TYPE);
			if (myCodes != null && myCodes.size() > 0)
			{
				orderVersion = CodeHelper.getCodeDescriptionByCode(myCodes, orderVersionId);
			}
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
		
		/**
		 * @return Returns the agencyId.
		 */
		public String getAgencyId() {
			return agencyId;
		}
		
		/**
		 * @param agencyId The agencyId to set.
		 */
		public void setAgencyId(String agencyId) {
			this.agencyId = agencyId;
		}
		
		/**
		 * @return the offense
		 */
		public String getOffense() {
			return offense;
		}

		/**
		 * @param offense the offense to set
		 */
		public void setOffense(String offense) {
			this.offense = offense;
		}

		/**
		 * @return the offenseCodeId
		 */
		public String getOffenseCodeId() {
			return offenseCodeId;
		}

		/**
		 * @param offenseCodeId the offenseCodeId to set
		 */
		public void setOffenseCodeId(String offenseCodeId) {
			this.offenseCodeId = offenseCodeId;
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
		/**
		 * @return Returns the orderVersionNum.
		 */
		public String getOrderVersionNum() {
			return orderVersionNum;
		}
		/**
		 * @param orderVersionNum The orderVersionNum to set.
		 */
		public void setOrderVersionNum(String orderVersionNum) {
			this.orderVersionNum = orderVersionNum;
		}
	}
