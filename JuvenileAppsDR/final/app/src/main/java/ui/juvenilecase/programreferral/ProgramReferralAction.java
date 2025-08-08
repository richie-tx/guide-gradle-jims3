/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import naming.ProgramReferralConstants;
import ui.juvenilecase.programreferral.UIProgramReferralBean;



/**
 */
public interface ProgramReferralAction {
		
						
		ProgramReferralAction ACCEPTWITHCHANGES=new ProgramReferralAcceptAction(ProgramReferralConstants.ACTION_ACCEPTWITHCHANGES);
		ProgramReferralAction ACCEPT=new ProgramReferralAcceptAction(ProgramReferralConstants.ACTION_ACCEPT);	
		ProgramReferralAction ACCEPTANDCLOSE=new ProgramReferralCloseAction(ProgramReferralConstants.ACTION_ACCEPTANDCLOSE); //Task 52635
		ProgramReferralAction REJECT=new ProgramReferralCloseAction(ProgramReferralConstants.ACTION_REJECT);
		ProgramReferralAction COMPLETE=new ProgramReferralCloseAction(ProgramReferralConstants.ACTION_COMPLETE);
		ProgramReferralAction WITHDRAW=new ProgramReferralCloseAction(ProgramReferralConstants.ACTION_WITHDRAW);
		ProgramReferralAction UPDATE=new ProgramReferralUpdateAction(ProgramReferralConstants.ACTION_UPDATE);
		ProgramReferralAction CREATE=new ProgramReferralUpdateAction(ProgramReferralConstants.ACTION_CREATE);
		ProgramReferralAction CANCEL=new ProgramReferralCloseAction(ProgramReferralConstants.ACTION_CANCEL);
		ProgramReferralAction ADDCOMMENTS=new ProgramReferralUpdateAction(ProgramReferralConstants.ACTION_ADDCOMMENTS);
		ProgramReferralAction VIEW=new ProgramReferralUpdateAction(ProgramReferralConstants.ACTION_VIEW);
		ProgramReferralAction TRANSFER=new ProgramReferralUpdateAction(ProgramReferralConstants.ACTION_TRANSFER);
	
		public String getActionName();				
		public void execute(UIProgramReferralBean programReferral) ;		

				
}
