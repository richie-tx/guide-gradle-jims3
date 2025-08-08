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
public class ProgramReferralTentativeState extends ProgramReferralState{
	
		private String status;
		private String subStatus;
			
		/**
		 * 
		 */
		public ProgramReferralTentativeState() {
			this.setStatus(ProgramReferralConstants.TENTATIVE);
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
		
		private List getJPOActions(){
			 List actionList = new ArrayList();
			 if (this.getSubStatus().equals(ProgramReferralConstants.REFERRED)){
			 	actionList.add(ProgramReferralAction.UPDATE);
			 	actionList.add(ProgramReferralAction.TRANSFER);
			 	actionList.add(ProgramReferralAction.CANCEL);			 	
			 }else if (this.getSubStatus().equals(ProgramReferralConstants.ACCEPTEDWITHCHANGES)){
			 	actionList.add(ProgramReferralAction.ACCEPT);
			 	actionList.add(ProgramReferralAction.ACCEPTWITHCHANGES);			 	
			 	actionList.add(ProgramReferralAction.CANCEL);
			 }
			 return actionList;
		}
		
		private List getSPActions(){
			 List actionList = new LinkedList();
			 if (this.getSubStatus().equals(ProgramReferralConstants.REFERRED)||this.getSubStatus().equals(ProgramReferralConstants.ACCEPTEDWITHCHANGES)){			 	
			 	actionList.add(ProgramReferralAction.ACCEPT);
			 	actionList.add(ProgramReferralAction.ACCEPTWITHCHANGES);
			 	actionList.add(ProgramReferralAction.ACCEPTANDCLOSE);
			 	actionList.add(ProgramReferralAction.REJECT);
			 }
			 return actionList;
		}
		@Override
		public List getPossibleNextActions(String userType, boolean isInHouse) {
			return this.getPossibleNextActions(userType);
		}		
		
}
