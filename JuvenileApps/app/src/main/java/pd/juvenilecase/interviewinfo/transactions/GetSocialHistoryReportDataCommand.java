package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.GetSocialHistoryReportDataEvent;
import messaging.interviewinfo.reply.MissingConstellationResponseEvent;
import messaging.interviewinfo.reply.SocialHistoryReportDataResponseEvent;
import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.interviewinfo.InterviewHelper;

public class GetSocialHistoryReportDataCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public GetSocialHistoryReportDataCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event) 
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		GetSocialHistoryReportDataEvent request = (GetSocialHistoryReportDataEvent)event;
		String casefileId = request.getCasefileId();

		JuvenileCasefile casefile = JuvenileCasefile.find( casefileId );
		// Profile stripping fix task 97536
		//Juvenile juvenile = casefile.getJuvenile();
		JuvenileCore juvenile = casefile.getJuvenile();
		//
		Juvenile jcjuvenile = Juvenile.findJCJuvenile(juvenile.getJuvenileNum());
		
		SocialHistoryReportDataResponseEvent response = new SocialHistoryReportDataResponseEvent();
		SocialHistoryReportDataTO reportTO = response.getTO();
		//<KISHORE>JIMS200060775 : Add Social Hist. link to Program Ref Detail(PD)-KK
		reportTO.setWarrantHistoryNeeded(request.isWarrantHistoryNeeded());
		
		//Precondition: constellation must exist 
		FamilyConstellation constellation = juvenile.getCurrentFamilyConstellation();
		MissingConstellationResponseEvent error = new MissingConstellationResponseEvent();
		if(constellation == null)
		{
			dispatch.postEvent(error);
			return;
		}
		
		InterviewHelper.buildSocialHistoryReportData( reportTO, juvenile, jcjuvenile, casefile );
		
		
		dispatch.postEvent(response);
	}

	
   /**
    * @param event
    * @roseuid 448D7EEE03B4
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE03BB
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 448D7EEE03BD
    */
   public void update(Object anObject) 
   {
    
   }
   
}
