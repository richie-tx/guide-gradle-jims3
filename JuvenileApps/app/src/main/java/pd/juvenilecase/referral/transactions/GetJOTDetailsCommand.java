package pd.juvenilecase.referral.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.address.reply.AddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.juvenilewarrant.reply.SummaryOfFactsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Reference;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import pd.codetable.Code;
import pd.exception.ActiveWarrantException;
import pd.juvenilewarrant.JuvenileOffenderTracking;
import pd.juvenilewarrant.JuvenileOffenderTrackingCharge;
import pd.juvenilewarrant.JuvenileOffenderTrackingFamily;
import pd.juvenilewarrant.JuvenileWarrantJOTBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import pd.pattern.IBuilder;
import messaging.referral.GetJOTDetailsEvent;

/**
 * @author ryoung
 *  
 */
public class GetJOTDetailsCommand implements ICommand
{

    /**
     * @roseuid 416C38340352
     */
    public GetJOTDetailsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41659D75011B
     */
    public void execute(IEvent event) throws ActiveWarrantException
    {
	GetJOTDetailsEvent jotEvent = (GetJOTDetailsEvent) event;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        JuvenileOffenderTracking jot = JuvenileOffenderTracking.find(jotEvent.getDaLogNum());
        if (jot != null)
        {           
        	
                this.sendJOTValues(dispatch, jot);
                this.sendJOTCharges(dispatch, jot);
                this.sendAssociateValues(dispatch, jotEvent.getDaLogNum());                
                this.sendSummaryOfFacts(dispatch, jot);
            
        }
    }

    /**
     * @param dispatch
     * @param jot
     */
    private void sendSummaryOfFacts(IDispatch dispatch, JuvenileOffenderTracking jot)
    {
        SummaryOfFactsResponseEvent summaryEvent = PDJuvenileWarrantHelper.getSummaryOfFactsResponseEvent(jot);
        dispatch.postEvent(summaryEvent);

    }

    private void sendJOTCharges(IDispatch dispatch, JuvenileOffenderTracking jot)
    {
        Collection jotCharges = jot.getCharges();

        if (jotCharges != null)
        {
            Iterator i = jotCharges.iterator();
            while (i.hasNext())
            {
                JuvenileOffenderTrackingCharge jotCharge = (JuvenileOffenderTrackingCharge) i.next();
                dispatch.postEvent(jotCharge.valueObject());
            }
        }
    }

    /**
     * @param dispatch
     * @param jot
     */
    private void sendJOTValues(IDispatch dispatch, JuvenileOffenderTracking jot)
    {
        // TODO Auto-generated method stub
        IBuilder builder = new JuvenileWarrantJOTBuilder(jot);
        builder.build();
        JuvenileOffenderTrackingResponseEvent responseEvent = (JuvenileOffenderTrackingResponseEvent) builder.getResult();
        dispatch.postEvent(responseEvent);
        AddressResponseEvent addressResponse = jot.getJuvenileAddress();
        if (addressResponse != null)
        {
            dispatch.postEvent(addressResponse);
        }
    }
    
    /**
     * 
     * @param dispatch
     * @param daLogNum
     */
    private void sendAssociateValues(IDispatch dispatch, String daLogNum)
    {
	JuvenileOffenderTrackingFamily jotFamily = JuvenileOffenderTrackingFamily.find( daLogNum );
	if (jotFamily != null)
	{
	 
	    JuvenileAssociateResponseEvent jare = new JuvenileAssociateResponseEvent();
	    jare.setFirstName(jotFamily.getMothersFirstName());
	    jare.setLastName(jotFamily.getMothersLastName());
	    jare.setMiddleName(jotFamily.getMothersMiddleName());
	    jare.setAssociateNum(PDJuvenileWarrantConstants.MOTHER_ASSOCIATE_NUM);
	    Code juvCode = (Code) new Reference("BM", Code.class, PDCodeTableConstants.RELATIONSHIP_JUVENILE)
	          .getObject();
	    if (juvCode != null)
	    {
	       jare.setRelationshipToJuvenileId(juvCode.getCode());
	       jare.setRelationshipToJuvenile(juvCode.getDescription());
	    }
	       jare.setHomePhoneNum(jotFamily.getMothersPhoneNum());
	       if (jotFamily.getMothersSsn() != null)
	        {
	            jare.setSsn(jotFamily.getMothersSsn());
	        }
	        jare.setAddress(jotFamily.getMotherAddress());
	        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_MOTHER_EVENT_TOPIC);
	        dispatch.postEvent( jare );
	    
	    jare = new JuvenileAssociateResponseEvent();
	    
	    jare.setFirstName(jotFamily.getOtherEmployer());
	    jare.setLastName(jotFamily.getOtherLastName());
	    jare.setMiddleName(jotFamily.getOtherMiddleName());
	    jare.setAssociateNum(PDJuvenileWarrantConstants.OTHER_ASSOCIATE_NUM);
	    Code othCode = (Code) new Reference("OT", Code.class, PDCodeTableConstants.RELATIONSHIP_JUVENILE)
	          .getObject();
	    if (othCode != null)
	    {
	       jare.setRelationshipToJuvenileId(othCode.getCode());
	       jare.setRelationshipToJuvenile(othCode.getDescription());
	    }
	       jare.setHomePhoneNum(jotFamily.getOtherPhoneNum());
	       if (jotFamily.getOtherSsn() != null)
	        {
	            jare.setSsn(jotFamily.getOtherSsn());
	        }
	        jare.setAddress(jotFamily.getOtherAddress());
	        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_OTHER_EVENT_TOPIC);
	        dispatch.postEvent( jare );
	        
	        jare = new JuvenileAssociateResponseEvent();
		    
		    jare.setFirstName(jotFamily.getFathersFirstName());
		    jare.setLastName(jotFamily.getFathersLastName());
		    jare.setMiddleName(jotFamily.getFathersMiddleName());
		    jare.setAssociateNum(PDJuvenileWarrantConstants.FATHER_ASSOCIATE_NUM);
		    Code fthCode = (Code) new Reference("BF", Code.class, PDCodeTableConstants.RELATIONSHIP_JUVENILE)
		          .getObject();
		    if (fthCode != null)
		    {
		       jare.setRelationshipToJuvenileId(fthCode.getCode());
		       jare.setRelationshipToJuvenile(fthCode.getDescription());
		    }
		       jare.setHomePhoneNum(jotFamily.getFathersPhoneNum());
		       if (jotFamily.getFathersSsn() != null)
		        {
		            jare.setSsn(jotFamily.getFathersSsn());
		        }
		        jare.setAddress(jotFamily.getFatherAddress());
		        jare.setTopic(PDJuvenileWarrantConstants.JUVENILE_ASSOCIATE_FATHER_EVENT_TOPIC);
		        dispatch.postEvent( jare );
	}

     
    }

    /**
     * @param event
     * @roseuid 41659D750128
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 41659D75012A
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param anObject
     * @roseuid 41659D75012C
     */
    public void update(Object anObject)
    {
    }
}