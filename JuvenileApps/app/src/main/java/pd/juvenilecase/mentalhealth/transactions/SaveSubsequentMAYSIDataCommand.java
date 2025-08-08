//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveSubsequentMAYSIDataCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import naming.PDJuvenileCaseConstants;
import messaging.mentalhealth.SaveSubsequentMAYSIDataEvent;
import messaging.mentalhealth.reply.MAYSIAssessResponseEvent;
import messaging.mentalhealth.reply.MAYSISubAssessResponseEvent;
import messaging.mentalhealth.reply.NoMAYSIRecordResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;
import pd.juvenilecase.mentalhealth.JuvenileSubMAYSI;

/**
 * 
 * @author dapte
 *
 * This command saves the metadata for a Subsequent MAYSI Assessment.
 * A subsequent MAYSI assessment is for a particular MAYSI assessment.
 * Hence all the subsequent assessment attributes are on the JuvenileMAYSI 
 * entity. The command looks up a JuvenileMAYSI instance based off of the
 * sequenceNumber in the RequestEvent and updates it with the subsequent 
 * assessment attributes.
 * 
 * It responds with a ReturnException whenever system failure causes the save
 * to abort.
 * 
 * Name of the ResponseEvent: 
 * 1. messaging.juvenilecase.reply.MAYSISuccessEvent: if save was successful
 * 3. messaging.juvenilecase.reply.NoMAYSIRecordResponseEvent if MAYSI Detail or MAYSI Sub not found
 * 2. mojo.km.exception.ReturnException: If save failed. 
 * 
 */
public class SaveSubsequentMAYSIDataCommand implements ICommand
{

	/**
	 * @roseuid 42791F920000
	 */
	public SaveSubsequentMAYSIDataCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D7B0256
	 */
	public void execute(IEvent event)
	{
		SaveSubsequentMAYSIDataEvent sEvent = (SaveSubsequentMAYSIDataEvent) event;
		JuvenileSubMAYSI juvMAYSISubdata = new JuvenileSubMAYSI();
		if(sEvent.getJuvenileMAYSIAssessId()==null || sEvent.getJuvenileMAYSIAssessId().equals("") || sEvent.getJuvenileMAYSIAssessId().equals("0")){
			sendNoMAYSIRecordEvent(sEvent.getJuvenileMAYSIAssessId());
			return;
		}	
		JuvenileMAYSIMetadata juvMAYSIMetaData = JuvenileMAYSIMetadata.find(sEvent.getJuvenileMAYSIAssessId());
		if (juvMAYSIMetaData == null)
		{
			sendNoMAYSIRecordEvent(sEvent.getJuvenileMAYSIAssessId());
			return;
		}
		juvMAYSISubdata.setAssessComplete(sEvent.isAssessmentComplete());
		juvMAYSISubdata.setJuvenileMAYSIAssessId(sEvent.getJuvenileMAYSIAssessId());
		juvMAYSISubdata.setProviderTypeReferredId(sEvent.getProviderTypeReferredId());
		juvMAYSISubdata.setReviewComments(sEvent.getReviewComments());
		juvMAYSISubdata.setReviewDate(sEvent.getReviewDate());
		juvMAYSISubdata.setReviewerId(sEvent.getReviewUserId());
		juvMAYSISubdata.setSubReferral(sEvent.isSubReferral());
		IHome home=new Home();
   		home.bind(juvMAYSISubdata);
   		juvMAYSIMetaData.setAssessmentOptionId(PDJuvenileCaseConstants.SUBSEQUENT_DONE_ASSESSMENT_OPTION);
   		home=new Home();
   		home.bind(juvMAYSIMetaData);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		MAYSISubAssessResponseEvent maysiEvent = juvMAYSISubdata.getResponseEvent();
		if(juvMAYSIMetaData.getAssessmentOption()!=null)
			maysiEvent.setAssessmentOption(juvMAYSIMetaData.getAssessmentOption().getDescription());
		dispatch.postEvent(maysiEvent);
		

	}

	/**
	 * @param string
	 */
	private void sendNoMAYSIRecordEvent(String string)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		NoMAYSIRecordResponseEvent response = new NoMAYSIRecordResponseEvent();
		response.setMessage(" MAYSI record not found for Assessment ID= " + string);
		dispatch.postEvent(response);
		
	}


	/**
	 * @param event
	 * @roseuid 42791D7B0261
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D7B0271
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42791D7B0273
	 */
	public void update(Object anObject)
	{

	}

}
