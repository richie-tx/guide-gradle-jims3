/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import naming.ProgramReferralConstants;


/**
 */
public class ProgramReferralAcceptedState extends ProgramReferralState{
	
		private String status;
		private String subStatus;
			
		/**
		 * 
		 */
		public ProgramReferralAcceptedState() {
			this.setStatus(ProgramReferralConstants.ACCEPTED);
		}
		/**
		 * @return Returns the status.
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status The status to set.
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		/**
		 * @return Returns the subStatus.
		 */
		public String getSubStatus() {
			return subStatus;
		}
		/**
		 * @param subStatus The subStatus to set.
		 */
		public void setSubStatus(String subStatus) {
			this.subStatus = subStatus;
		}
		/* (non-Javadoc)
		 * @see messaging.programreferral.ProgramReferralState#getPossibleNextActions()
		 */
		public List getPossibleNextActions(String userType) {
			List actionList=null;
			if (userType.equals(ProgramReferralConstants.JPO_USER)){
				actionList = this.getJPOActions();
			}else if (userType.equals(ProgramReferralConstants.SP_USER)){ 
				actionList = this.getSPActions();
			}
			return actionList;
		}

		public List getPossibleNextActions(String userType, boolean isInHouse) {
			List actionList=null;
			if (userType.equals(ProgramReferralConstants.JPO_USER)){
				actionList = this.getJPOActions();
			}else if (userType.equals(ProgramReferralConstants.SP_USER) && !isInHouse){ 
				actionList = this.getSPActions();
			} else if (userType.equals(ProgramReferralConstants.SP_USER) && isInHouse){
				actionList = this.getInHouseSPActions();
			}
			return actionList;
		}

		private List getJPOActions(){
			 List actionList = new ArrayList();
			 actionList.add(ProgramReferralAction.CANCEL);
			 actionList.add(ProgramReferralAction.WITHDRAW);
			 actionList.add(ProgramReferralAction.COMPLETE);
			 actionList.add(ProgramReferralAction.ADDCOMMENTS);
			 return actionList;
		}
		
		private List getSPActions(){
			 List actionList = new LinkedList();
			 actionList.add(ProgramReferralAction.ADDCOMMENTS);
			 return actionList;
		}		
		
		private List getInHouseSPActions(){
			 List actionList = new LinkedList();
			 actionList.add(ProgramReferralAction.ADDCOMMENTS);
			 actionList.add(ProgramReferralAction.REJECT);
			 return actionList;
		}
}
