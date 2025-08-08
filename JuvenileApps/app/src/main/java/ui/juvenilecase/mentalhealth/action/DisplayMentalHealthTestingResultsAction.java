//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthTestingResultsAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection ;
import java.util.Collections ;
import java.util.List ;
import java.util.Map;
import messaging.codetable.GetServiceTypeCdEvent ;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent ;
import messaging.mentalhealth.reply.TestingSessionResponseEvent ;
import messaging.mentalhealth.GetMentalHealthTestingResultsEvent ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.EventFactory ;
import mojo.km.messaging.Composite.CompositeResponse ;
import mojo.km.utilities.MessageUtil ;
import naming.CodeTableControllerServiceNames ;
import naming.JuvenileMentalHealthControllerServiceNames ;
import naming.UIConstants ;
import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.JuvenileProfileForm ;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;
import javax.servlet.http.HttpSession ;

public class DisplayMentalHealthTestingResultsAction extends JIMSBaseAction
{
	/**
	 * @roseuid 45D4AEAA02E3
	 */
	public DisplayMentalHealthTestingResultsAction( )
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D36FDA03AC
	 */
	public ActionForward execute( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		TestingSessionForm sessForm = (TestingSessionForm)aForm ;
		HttpSession session = aRequest.getSession( ) ;
		
		JuvenileProfileForm headerForm = (JuvenileProfileForm)session.getAttribute( "juvenileProfileHeader" ) ;
		sessForm.setJuvNum( headerForm.getJuvenileNum( ) ) ;
		
		GetMentalHealthTestingResultsEvent listEvent = (GetMentalHealthTestingResultsEvent)
				EventFactory.getInstance( JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHTESTINGRESULTS ) ;
		listEvent.setJuvenileNum( headerForm.getJuvenileNum( ) ) ;
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( listEvent ) ;
		
		CompositeResponse response = (CompositeResponse)dispatch.getReply( ) ;
		Collection<TestingSessionResponseEvent> coll = MessageUtil.compositeToCollection( response, TestingSessionResponseEvent.class ) ;
		
		// get the service type list to set the event type
		GetServiceTypeCdEvent serviceTypeEvent = (GetServiceTypeCdEvent)EventFactory.getInstance( CodeTableControllerServiceNames.GETSERVICETYPECD ) ;
		serviceTypeEvent.setCodeTableName( "SERVICE_TYPE" ) ;

		dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( serviceTypeEvent ) ;
		
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( ) ;
		MessageUtil.processReturnException( compositeResponse ) ;
		
		if( coll != null )
		{
			Collection serviceTypeResponses = MessageUtil.compositeToCollection( compositeResponse, ServiceTypeCdResponseEvent.class ) ;

			Collections.sort( (List)coll ) ;
			for( TestingSessionResponseEvent resp : coll )
			{
				String eventType = getEventType( resp.getEventType( ), serviceTypeResponses ) ;
				resp.setEventType( eventType ) ;
			}
		}
		
		sessForm.setTestResultsList( coll ) ;
		
		ActionForward forward = aMapping.findForward( UIConstants.SUCCESS ) ;
		return forward ;
	}

	/*
	 * @param eventTypeId
	 * @param serviceTypeResponses
	 * @return
	 */
	private String getEventType( String eventTypeId, 
			Collection<ServiceTypeCdResponseEvent> serviceTypeResponses )
	{
		if( serviceTypeResponses != null )
		{
			for( ServiceTypeCdResponseEvent resp : serviceTypeResponses )
			{
				if( resp.getServiceTypeId( ).equals( eventTypeId ) )
				{
					return resp.getDescription( ) ;
				}
			}
		}

		return null ;
	}

	protected void addButtonMapping(Map keyMap) {
	}
}
