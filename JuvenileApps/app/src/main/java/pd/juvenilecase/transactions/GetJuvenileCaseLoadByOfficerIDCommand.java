package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileCaseLoadByOfficerIDEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;

public class GetJuvenileCaseLoadByOfficerIDCommand implements ICommand{
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetJuvenileCaseLoadByOfficerIDCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event)
	{
		GetJuvenileCaseLoadByOfficerIDEvent officerEvent = (GetJuvenileCaseLoadByOfficerIDEvent) event;	
		
		Iterator files = JuvenileCasefile.findAll(officerEvent);
		while (files != null && files.hasNext())
		{
			JuvenileCasefile casefile = (JuvenileCasefile) files.next();
			JuvenileCasefileResponseEvent casefileNew = new JuvenileCasefileResponseEvent();
			casefileNew.setJuvenileNum(casefile.getJuvenileId());
			casefileNew.setSupervisionNum(casefile.getOID());
			casefileNew.setSupervisionType(casefile.getSupervisionType().getDescription());
			casefileNew.setSupervisionEndDate(casefile.getSupervisionEndDate());
			// Profile stripping fix task 97536
			//Juvenile juv = casefile.getJuvenile();
			JuvenileCore juv = casefile.getJuvenile();
			//
			if( juv != null ){
			    
				casefileNew.setJuvenileFirstName(juv.getFirstName());
				casefileNew.setJuvenileMiddleName(juv.getMiddleName());
				casefileNew.setJuvenileLastName(juv.getLastName());
				casefileNew.setCaseStatus(casefile.getCaseStatusId());
				MessageUtil.postReply(casefileNew);
			}

		}
		
	}
	

	public void onRegister(IEvent event)
	{

	}

	public void onUnregister(IEvent event)
	{

	}

	public void update(Object anObject)
	{

	}
}
