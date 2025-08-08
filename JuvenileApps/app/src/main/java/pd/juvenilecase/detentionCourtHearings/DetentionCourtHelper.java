package pd.juvenilecase.detentionCourtHearings;


import messaging.detentionCourtHearings.reply.JuvenileDetentionNotificationResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.notification.CreateNotificationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.UIConstants;

public final class DetentionCourtHelper
{
    private static final String fromEmail = "jims2notification@itc.hctx.net";
    private static final String clmEmail = "Carla.Glover@hc.hctx.net";
    private static final String jpoEmail = "Lakshmi.Gopinath@cts.hctx.net";
    private static final String clmUVCode = "UVDIB";//carla glover
    private static final String jpoUVCode = "UVB6K";//juvma
    
    
    public DetentionCourtHelper()
    {
    }
    
    /**
     * 
     * @param nevt
     * @param topic
     */
    public static void sendDetainedNotification( JuvenileDetentionNotificationResponseEvent nevt, String topic )
    {
	StringBuffer message = new StringBuffer(100);
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
        notificationEvent.setNotificationTopic( topic );
        notificationEvent.setSubject(" DETAIN " + nevt.getJuvenileName() + ", Juvenile #: " + nevt.getJuvenileNum() );

        if( nevt.isCLM() ){
            message.append(" Court #: ");
            message.append( nevt.getAssignedCourt());
            message.append(" ordered the DETENTION of ");
    	    message.append( nevt.getJuvenileName() );
    	    message.append(" under supervision# ");
    	    message.append( nevt.getSupervisionNumber());
    	    message.append(" and Referral#: ");
    	    message.append(nevt.getBookingReferral());
    	    message.append(" from your facility.");
    	    nevt.setNotificationMessage( message.toString() );
    	    //nevt.setIdentity(clmUVCode);
        }else{
            message.append(" On : ");
            message.append(nevt.getHearingDate());
            message.append(" Court ");
            message.append( nevt.getAssignedCourt());
            message.append(" ordered ");
    	    message.append( nevt.getJuvenileName() );
    	    message.append(" Juvenile# ");
    	    message.append( nevt.getJuvenileNum());
    	    message.append(" To Be DETAINED ");
    	    nevt.setNotificationMessage( message.toString() );
    	   // nevt.setIdentity(jpoUVCode);
        }
        notificationEvent.addIdentity( UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, (IAddressable) nevt );
        notificationEvent.addContentBean( nevt );
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        try{
            dispatch.postEvent( notificationEvent ); 
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
       
    }
    
    /**
     * 
     * @param nevt
     * @param topic
     */
    public static void sendReleaseNotification( JuvenileDetentionNotificationResponseEvent nevt, String topic )
    {
	StringBuffer message = new StringBuffer(100);
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
        notificationEvent.setNotificationTopic( topic );
        notificationEvent.setSubject(" RELEASE ordered for " + nevt.getJuvenileName() + ", Juvenile #: " + nevt.getJuvenileNum()+ " from " + nevt.getFacility());

        if( nevt.isCLM() ){
            message.append(" Court : ");
            message.append( nevt.getAssignedCourt());
            message.append("ordered the RELEASE of ");
    	    message.append( nevt.getJuvenileName() );
    	    message.append(" under  ");
    	    message.append(nevt.getSupervisionType());
    	    message.append(" supervision # ");
    	    message.append( nevt.getSupervisionNumber());
    	    message.append(" and Referral#: ");
    	    message.append(nevt.getBookingReferral());
    	    message.append(" from your facility.");
    	    nevt.setNotificationMessage( message.toString() ); 
    	    //nevt.setIdentity(clmUVCode);
        }else{
            message.append(" On : ");
            message.append(nevt.getHearingDate());
            message.append(" Court ");
            message.append( nevt.getAssignedCourt());
            message.append(" ordered the release of ");
    	    message.append( nevt.getJuvenileName() );
    	    message.append(" Juvenile# ");
    	    message.append( nevt.getJuvenileNum());
    	    message.append(" from  ");
    	    message.append( nevt.getDetainedFacilityDesc());
    	    nevt.setNotificationMessage( message.toString() ); 
    	    //nevt.setIdentity(jpoUVCode);
        }
        
        notificationEvent.addIdentity( UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, (IAddressable) nevt );
        notificationEvent.addContentBean( nevt );
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        try
	{
            dispatch.postEvent( notificationEvent );
	}
	catch (Exception e)
	{
	    System.out.println(e.getMessage());
	}        
    }
    
    /**
     * 
     * @param juvenile
     * @param casefile
     */
    public static void sendDetainedNotificationEmail( JuvenileDetentionNotificationResponseEvent nevt, String emailTo )
    {
	StringBuffer message = new StringBuffer(100);
	SendEmailEvent sendEmailEvent  = new SendEmailEvent();
        // create and populate notification event
	if( nevt.isCLM() ){
            message.append(" Court #: ");
            message.append( nevt.getAssignedCourt());
            message.append(" ordered the DETENTION of ");
    	    message.append( nevt.getJuvenileName() );
    	    message.append(" under supervision# ");
    	    message.append( nevt.getSupervisionNumber());
    	    message.append(" and Referral#: ");
    	    message.append(nevt.getBookingReferral());
    	    message.append(" from your facility.");
    	    nevt.setNotificationMessage( message.toString() );
    	    //nevt.setIdentity(clmUVCode);
        }else{
            message.append(" On : ");
            message.append(nevt.getHearingDate());
            message.append(" Court ");
            message.append( nevt.getAssignedCourt());
            message.append(" ordered ");
    	    message.append( nevt.getJuvenileName() );
    	    message.append(" Juvenile# ");
    	    message.append( nevt.getJuvenileNum());
    	    message.append(" To Be DETAINED ");
    	    nevt.setNotificationMessage( message.toString() );
    	   // nevt.setIdentity(jpoUVCode);
        }
        
        sendEmailEvent.setFromAddress( fromEmail);
        sendEmailEvent.addToAddress( emailTo );
        sendEmailEvent.setMessage( message.toString() );
        sendEmailEvent.setSubject(" DETAIN " + nevt.getJuvenileName() + ", Juvenile #: " + nevt.getJuvenileNum() );
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent( sendEmailEvent );        

    }
    
    public static void sendReleaseNotificationEmail( JuvenileDetentionNotificationResponseEvent nevt, String emailTo )
    {
	StringBuffer message = new StringBuffer(100);
	SendEmailEvent sendEmailEvent  = new SendEmailEvent();
         // create and populate notification event
        if( nevt.isCLM() ){
            message.append(" Court #: ");
            message.append( nevt.getAssignedCourt());
            message.append(" ordered the RELEASE of ");
    	    message.append( nevt.getJuvenileName() );
    	    message.append(" under  ");
    	    message.append(nevt.getSupervisionType());
    	    message.append(" supervision # ");
    	    message.append( nevt.getSupervisionNumber());
    	    message.append(" and Referral#: ");
    	    message.append(nevt.getBookingReferral());
    	    message.append(" from your facility.");
    	    //sendEmailEvent.setMessage( message.toString() ); 
        }else{
            message.append(" On #: ");
            message.append(nevt.getHearingDate());
            message.append(" Court ");
            message.append( nevt.getAssignedCourt());
            message.append(" ordered the release of ");
    	    message.append( nevt.getJuvenileName() );
    	    message.append(" Juvenile# ");
    	    message.append( nevt.getJuvenileNum());
    	    message.append(" from  ");
    	    message.append(nevt.getFacility());
    	   //sendEmailEvent.setMessage( message.toString() ); 
        }
        
        sendEmailEvent.setFromAddress( fromEmail);
        sendEmailEvent.addToAddress( emailTo );
        sendEmailEvent.setMessage( message.toString() );  
        sendEmailEvent.setSubject(" RELEASE ordered for " + nevt.getJuvenileName() + ", Juvenile #: " + nevt.getJuvenileNum()+ " from " + nevt.getFacility());
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent( sendEmailEvent );   

    }

}
