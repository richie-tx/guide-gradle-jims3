/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import java.util.Date;
import java.util.HashMap;

import naming.PDTaskConstants;
import naming.ProgramReferralConstants;
import naming.UIConstants;
import ui.juvenilecase.programreferral.UIProgramReferralBean.ProgramReferralTaskInfo;

/**
 */
public class ProgramReferralAcceptAction implements ProgramReferralAction
{
	private String subAction;

	/**
		 * 
		 */
	public ProgramReferralAcceptAction(String subAction)
	{
		this.subAction = subAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.programreferral.ProgramReferralAction#getActionName()
	 */
	public String getActionName()
	{
		return subAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * messaging.programreferral.ProgramReferralAction#execute(ui.juvenilecase
	 * .programreferral.UIProgramReferralBean)
	 */
	public void execute(UIProgramReferralBean programReferral)
	{
		String userType = (programReferral.getCurrentUserType() == null) ? "" : programReferral.getCurrentUserType() ;

		if( ProgramReferralConstants.ACTION_ACCEPTWITHCHANGES.equals( subAction ) )
		{
			programReferral.setReferralState(
					ProgramReferralStateManager.getReferralState(ProgramReferralConstants.TENTATIVE, 
							ProgramReferralConstants.ACCEPTEDWITHCHANGES));

			ProgramReferralTaskInfo taskInfo = new ProgramReferralTaskInfo();
			HashMap map = new HashMap();
			map.put("submitAction", "Link");
			map.put("referralId", programReferral.getReferralId());

			if( ProgramReferralConstants.SP_USER.equals( userType ) )
			{
				if( programReferral.getAcknowledgementDate() == null )
				{
					programReferral.setAcknowledgementDate(new Date());
				}
				StringBuffer title = new StringBuffer();
				title.append(programReferral.getProviderProgramName());
				title.append(" Referral Accepted with changes for Juvenile# ");
				title.append(programReferral.getJuvenileId());

				taskInfo.setParameterMap(map);
				taskInfo.setTaskUserId(UIProgramReferralHelper.getOfficerUserId(
						programReferral.getOfficerId()));
				taskInfo.setTaskName(PDTaskConstants.JPO_REFERRAL_TASK);
				taskInfo.setTitle(title.toString());
			}
			else if( ProgramReferralConstants.JPO_USER.equals( userType ) )
			{
				StringBuffer title = new StringBuffer();
				title.append(programReferral.getProviderProgramName());
				title.append(" Referral Accepted with changes");
				String adminContactId = UIProgramReferralHelper.getSPAdminContactId(
						programReferral.getJuvServiceProviderId());

				taskInfo.setParameterMap(map);
				taskInfo.setTaskUserId(adminContactId);
				taskInfo.setTaskName(PDTaskConstants.SP_REFERRAL_TASK);
				taskInfo.setTitle(title.toString());
			}
			programReferral.setReferralTaskInfo(taskInfo);
		}
		else if( ProgramReferralConstants.ACTION_ACCEPT.equals( subAction ) )
		{
			programReferral.setReferralState(
					ProgramReferralStateManager.getReferralState(
							ProgramReferralConstants.ACCEPTED, ProgramReferralConstants.ACCEPTED));

			if( ProgramReferralConstants.JPO_USER.equals( userType ) )
			{
				UIProgramReferralBean.ProgramReferralNoticeInfo noticeInfo = 
						new UIProgramReferralBean.ProgramReferralNoticeInfo();
				noticeInfo.setNotificationTopic(UIConstants.PROGRAM_REFERRAL_NOTIFICATION);

				String adminContactId = UIProgramReferralHelper.getSPAdminContactId(
						programReferral.getJuvServiceProviderId());

				noticeInfo.setIdentity(adminContactId);
				StringBuffer title = new StringBuffer();
				title.append(programReferral.getProviderProgramName());
				title.append(" Referral Accepted");
				noticeInfo.setSubject(title.toString());

				StringBuffer message = new StringBuffer();
				message.append(programReferral.getOfficerName());
				message.append(" has accepted the referral request for ");
				message.append(programReferral.getJuvenileName());
				message.append(" ( ");
				message.append(programReferral.getJuvenileId());
				message.append(" )");
				noticeInfo.setMessage(message.toString());
				programReferral.setReferralNoticeInfo(noticeInfo);
			}
			else if( ProgramReferralConstants.SP_USER.equals( userType ) )
			{
				if( programReferral.getAcknowledgementDate() == null )
				{
					programReferral.setAcknowledgementDate(new Date());
				}

				UIProgramReferralBean.ProgramReferralNoticeInfo noticeInfo = 
						new UIProgramReferralBean.ProgramReferralNoticeInfo();

				noticeInfo.setNotificationTopic(UIConstants.PROGRAM_REFERRAL_NOTIFICATION);
				noticeInfo.setIdentity(UIProgramReferralHelper.getOfficerUserId(
						programReferral.getOfficerId()));
				noticeInfo.setSubject("Program Referral Accepted");

				StringBuffer message = new StringBuffer();
				message.append(programReferral.getJuvServiceProviderName());
				message.append(" ( ");
				message.append(programReferral.getJuvServiceProviderId());
				message.append(" ) ");
				message.append(" has accepted the referral request for ");
				message.append(programReferral.getJuvenileName());
				message.append(" ( ");
				message.append(programReferral.getJuvenileId());
				message.append(" ) without any modifications to the date, time or location");
				noticeInfo.setMessage(message.toString());
				programReferral.setReferralNoticeInfo(noticeInfo);
			}
		} // else if ACTION_ACCEPT
	}
}
