/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import java.util.Date;
import java.util.HashMap;

import naming.PDTaskConstants;
import naming.ProgramReferralConstants;
import ui.juvenilecase.programreferral.UIProgramReferralBean.ProgramReferralTaskInfo;




/**
 */
public class ProgramReferralUpdateAction implements ProgramReferralAction{
			
		private String subAction;
			
		/**
		 * 
		 */
		public ProgramReferralUpdateAction(String subAction) {
			this.subAction = subAction;
		}

		/* (non-Javadoc)
		 * @see messaging.programreferral.ProgramReferralAction#getActionName()
		 */
		public String getActionName() {
			return subAction;
		}
		/* (non-Javadoc)
		 * @see messaging.programreferral.ProgramReferralAction#execute(ui.juvenilecase.programreferral.UIProgramReferralBean)
		 */
		public void execute(UIProgramReferralBean programReferral) {
			
			if (subAction.equals(ProgramReferralConstants.ACTION_CREATE)){
				if (programReferral.getReferralState() == null){
					programReferral.setReferralState(ProgramReferralStateManager.getReferralState(ProgramReferralConstants.TENTATIVE,ProgramReferralConstants.REFERRED));
				}
//				programReferral.setReferralState(ProgramReferralStateManager.getReferralState(ProgramReferralConstants.TENTATIVE,ProgramReferralConstants.REFERRED));
				Date date = new Date();
				programReferral.setSentDate(date);
				
				ProgramReferralTaskInfo taskInfo = new ProgramReferralTaskInfo();
				HashMap map = new HashMap();
				map.put("submitAction", "Link");
				
				StringBuffer title = new StringBuffer();
				title.append(" New referral for  ");
				title.append(programReferral.getProviderProgramName());
				
				String adminContactId = UIProgramReferralHelper.getSPAdminContactId(programReferral.getJuvServiceProviderId());
												
				taskInfo.setParameterMap(map);
				taskInfo.setTaskUserId(adminContactId);
				taskInfo.setTaskName(PDTaskConstants.SP_REFERRAL_TASK);
				taskInfo.setTitle(title.toString());
				
				programReferral.setReferralTaskInfo(taskInfo);					
			}
		}		
		
}
