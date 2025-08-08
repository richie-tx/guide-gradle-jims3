package pd.juvenilecase.interviewinfo.transactions;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import messaging.interviewinfo.GetInterviewReportsEvent;
import messaging.interviewinfo.reply.InterviewReportHeaderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.InterviewDocument;

public class GetInterviewReportsCommand implements ICommand 
{
   /**
    * @roseuid 448EC54501B8
    */
   public GetInterviewReportsCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event) 
	{
		GetInterviewReportsEvent request = (GetInterviewReportsEvent)event;
		String casefileId = request.getCasefileId();

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		
		Iterator iter = null;
		if ( request.getJuvenileId() != null )
		{
			iter = InterviewDocument.findAllForJuvenile( request.getJuvenileId() );
		}
		else
		{
			iter = InterviewDocument.findAllForCasefile( casefileId );
		}
		
		//<KISHORE>JIMS200060775 : Add Social Hist. link to Program Ref Detail(PD)-KK
		if(StringUtils.isEmpty(request.getReportType())){
			while ( iter.hasNext() )
			{
				InterviewDocument doc = (InterviewDocument)iter.next();
				InterviewReportHeaderResponseEvent response = new InterviewReportHeaderResponseEvent();

				response.setReportId( doc.getOID().toString() );
				response.setCasefileId( doc.getCasefileId() );
				response.setCreationDate( doc.getCreationDate() );
				//response.setReportType( doc.getDocumentTypeCodeId() ); //commented for bug 40487
				response.setReportType( doc.getDocumentTypeCode().getDescription() ); //added for bug 40487
				if(doc.getCreateUserID() != null){
					response.setCreateUserID(doc.getCreateUserID());
				}
				if(doc.getCreateTimestamp() != null){
					response.setCreateDate(new Date(doc.getCreateTimestamp().getTime()));
				}
				if(doc.getUpdateUserID() != null){
					response.setUpdateUser(doc.getUpdateUserID());
				}
				if(doc.getUpdateTimestamp() != null){
					response.setUpdateDate(new Date(doc.getUpdateTimestamp().getTime()));
				}
				if(doc.getCreateJIMS2UserID() != null){
					response.setCreateJIMS2UserID(doc.getCreateJIMS2UserID());
				}
				if(doc.getUpdateJIMS2UserID() != null){
					response.setUpdateJIMS2UserID(doc.getUpdateJIMS2UserID());
				}
				
				dispatch.postEvent(response);
			}
		}
		else{
			while ( iter.hasNext() )
			{
				InterviewDocument doc = (InterviewDocument)iter.next();
				if(request.getReportType().equalsIgnoreCase(doc.getDocumentTypeCodeId())){
					InterviewReportHeaderResponseEvent response = new InterviewReportHeaderResponseEvent();
					
					response.setReportId( doc.getOID().toString() );
					response.setCasefileId( doc.getCasefileId() );
					response.setCreationDate( doc.getCreationDate() );
					response.setReportType( doc.getDocumentTypeCode().getDescription() );
					if(doc.getCreateUserID() != null){
						response.setCreateUserID(doc.getCreateUserID());
					}
					if(doc.getCreateTimestamp() != null){
						response.setCreateDate(new Date(doc.getCreateTimestamp().getTime()));
					}
					if(doc.getUpdateUserID() != null){
						response.setUpdateUser(doc.getUpdateUserID());
					}
					if(doc.getUpdateTimestamp() != null){
						response.setUpdateDate(new Date(doc.getUpdateTimestamp().getTime()));
					}
					if(doc.getCreateJIMS2UserID() != null){
						response.setCreateJIMS2UserID(doc.getCreateJIMS2UserID());
					}
					if(doc.getUpdateJIMS2UserID() != null){
						response.setUpdateJIMS2UserID(doc.getUpdateJIMS2UserID());
					}
					
					dispatch.postEvent(response);
				}
			}
		}
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
