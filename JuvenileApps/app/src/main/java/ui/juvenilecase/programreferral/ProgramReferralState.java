/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import java.util.List;

import naming.PDCodeTableConstants;
import ui.common.SimpleCodeTableHelper;


/**
 */
public abstract class ProgramReferralState {
							
		public abstract String getStatus() ;		
		public abstract void setStatus(String status) ;
		public abstract String getSubStatus();
		public abstract void setSubStatus(String subStatus);
		public abstract List getPossibleNextActions(String userType);
		public abstract List getPossibleNextActions(String userType, boolean isInHouse);
		
		public String getDescription(){
			String statusCd = this.getStatus();
			StringBuffer sb = new StringBuffer();
			if (statusCd!=null && !"".equals(statusCd)){				
				sb.append(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PROGRAM_REFERRAL_STATUS,statusCd));
				String subStatusCd = this.getSubStatus();
				if (subStatusCd!=null && !"".equals(subStatusCd)){
					sb.append(" - ");
					sb.append(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PROGRAM_REFERRAL_SUBSTATUS,subStatusCd));
				}
			}
			return sb.toString(); 
		}
				
}
