package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileStatus;
import pd.juvenilecase.JuvenileCasefile;
import messaging.casefile.GetCasefileForActivationEvent;
import messaging.casefile.reply.CasefileForActivationResponseEvent;
import messaging.juvenile.GetJuvenileStatusByJuvenileEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;

/**
 * 
 */
public class GetCasefileForActivationCommand implements ICommand
{
	/**
	 * 
	 */
	public GetCasefileForActivationCommand()
	{

	}

	/**
	 * @param event
	 * 
	 */
	public void execute(IEvent event)
	{
		GetCasefileForActivationEvent evt = (GetCasefileForActivationEvent) event;

		JuvenileCasefile casefile = JuvenileCasefile.find(evt.getSupervisionNum());
		JuvenileCore juv = JuvenileCore.findCore(casefile.getJuvenileNum());

		CasefileForActivationResponseEvent reply = new CasefileForActivationResponseEvent();

		reply.setSupervisionNum(evt.getSupervisionNum());

		reply.setJuvenileMasterStatusId(juv.calculateStatusId());
		reply.setDateOfBirth(juv.getDateOfBirth());

		reply.setSupervisionStatusId(casefile.getCaseStatusId());
		reply.setMAYSINeeded(casefile.getIsMAYSINeeded());
		//taken out for US 14459
		//reply.setTitle4eNeeded(casefile.getIsBenefitsAssessmentNeeded());
		reply.setRiskAnalysis(casefile.getIsRiskAssessmentNeeded());
		reply.setSupervisionTypeId(casefile.getSupervisionTypeId());
		reply.setSupervisionEndDate(casefile.getSupervisionEndDate());

		MessageUtil.postReply(reply);
	}

	/**
	 * @param event
	 * 
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * 
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * 
	 */
	public void update(Object anObject)
	{

	}

}
