//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileCommand.java

package pd.juvenilecase.transactions;

import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.NoJuvenileCasefilesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;

/**
 * @author dgibler
 * Retrieves JuvenileCasefile.
 */
public class GetJuvenileCasefileByCasefileIdCommand implements ICommand
{

	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetJuvenileCasefileByCasefileIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
		GetJuvenileCasefileByCasefileIdEvent file = (GetJuvenileCasefileByCasefileIdEvent) event;
		JuvenileCasefile casefile = JuvenileCasefile.find(file.getSupervisionNum());		
		if (casefile != null)
		{
			JuvenileCasefileResponseEvent casefileResponse =
				JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
			// This is the potential spot to call the JJS object to get the data
			if(casefile.getJuvenile()!=null && casefile.getJuvenile().getJuvenileNum()!=null ){
				JJSJuvenile myJJSInfo=casefile.getJuvenile().getJjsJuvInfo();
				if(myJJSInfo!=null){
					casefileResponse.setDetentionStatusId(myJJSInfo.getDetentionStatusId());
//					Defect 53065 - JJS only displays facility if there is a status.  Modified this code to do the same.
					if (myJJSInfo.getDetentionStatusId() != null && !myJJSInfo.getDetentionStatusId().equals("")) {
						casefileResponse.setDetentionFacilityId(myJJSInfo.getDetentionFacilityId());
					}
				}
			}
			
			dispatch.postEvent(casefileResponse);
		} else {
			NoJuvenileCasefilesResponseEvent none = 
				new NoJuvenileCasefilesResponseEvent();
			none.setMessage("No Juvenile Casefile found for Supervision Number: " + file.getSupervisionNum());
			dispatch.postEvent(none);
		}
	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject)
	{

	}
}
