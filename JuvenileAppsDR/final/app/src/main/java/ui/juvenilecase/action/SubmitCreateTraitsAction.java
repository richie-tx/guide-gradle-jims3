/*
 * Created on Jun 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList ;
import java.util.Collection ;
import java.util.HashMap ;
import java.util.Iterator ;
import java.util.List;
import java.util.Map ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.SaveJuvenileTraitsEvent ;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent ;
import messaging.juvenilecase.reply.TraitTypeResponseEvent ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.EventFactory ;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.UserEntityBean;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.JuvenileCaseControllerServiceNames ;
import naming.PDCodeTableConstants;
import naming.UIConstants ;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import org.apache.struts.actions.LookupDispatchAction ;

import pd.contact.officer.PDOfficerProfileHelper;
import pd.security.PDSecurityHelper;

import ui.common.CodeHelper;
import ui.common.UINotificationHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm ;

/**
 * @author jfisher
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitCreateTraitsAction extends LookupDispatchAction
{
	private static final String REMOVE_TRAITS = "removeTraits" ;
	private static final String ADD_TRAIT = "addTrait" ;
	
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
				HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		TraitsForm traitsForm = (TraitsForm)aForm ;

		Collection newTraits = traitsForm.getNewTraits( ) ;
		String juvenileNum = "";
		String juvenileName = "";
		String traitDescriptionId = "";
		
		
		
		
		if( newTraits != null && !newTraits.isEmpty( ) )
		{
			SaveJuvenileTraitsEvent saveEvent = (SaveJuvenileTraitsEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.SAVEJUVENILETRAITS ) ;
			juvenileNum =  traitsForm.getJuvenileNumber( );
			saveEvent.setJuvenileNum( juvenileNum ) ;
			// TODO Apply available disposition number in future iteration
			//saveEvent.setDispositionNum( DISPOSITION_NUM ) ;
			saveEvent.setSupervisionNum( traitsForm.getSupervisionNum( ) ) ;

			Iterator i = newTraits.iterator( ) ;

			while( i.hasNext( ) )
			{
				JuvenileTraitResponseEvent juvenileTraitResponseEvent = (JuvenileTraitResponseEvent)i.next( );
				
				if (juvenileTraitResponseEvent.getTraitTypeId() == null || juvenileTraitResponseEvent.getTraitTypeId().equals("") ||
						juvenileTraitResponseEvent.getTraitTypeDescription() == null || juvenileTraitResponseEvent.getTraitTypeDescription().equals("") ||
						juvenileTraitResponseEvent.getStatusId() == null || juvenileTraitResponseEvent.getStatusId().equals("")) {
					
					//This needs a new error code 
					ActionMessage myError=new ActionMessage("error.createTraitError");
					ArrayList coll=new ArrayList();
					coll.add(myError);
					sendToErrorPage(aRequest,coll);
					return aMapping.findForward(UIConstants.CANCEL);
					
				} else {
					//US 42660
					if(traitsForm.isUIFacility())
					    if(traitsForm.getFacilityAdmitOID()!=null)
					    {
						juvenileTraitResponseEvent.setFacilityAdmitOID(traitsForm.getFacilityAdmitOID());
					    }
					    else{
					    
						juvenileTraitResponseEvent.setFacilityAdmitOID(traitsForm.getCurrentFacilityOID());
					    }
					if(traitsForm.getTransferAdmitOID()!=null)
					    {
						juvenileTraitResponseEvent.setTransferAdmitOID(traitsForm.getTransferAdmitOID());
					    }
						traitDescriptionId =  juvenileTraitResponseEvent.getTraitTypeId();
						//System.out.println("traitDescriptionId" + traitDescriptionId);
						saveEvent.addRequest( juvenileTraitResponseEvent ) ;
						
						
				}
			}

		
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			dispatch.postEvent( saveEvent ) ;
			traitsForm.setAction( UIConstants.CONFIRM ) ;
			
		}

		aRequest.setAttribute( "traitAdded", "true" ) ;
		
		String myForward = setForwardMapping( UIConstants.SUCCESS, traitsForm.getCategoryName( ) ) ;
		ActionForward forward = aMapping.findForward( myForward ) ;
		
		//Changes for US 31029 - check for restricted access		
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		if(casefileForm!=null){
			casefileForm.setRestrictedAccess(UIJuvenileHelper.checkRestrictedAcces(traitsForm.getJuvenileNumber()));
			if( ! "profile".equals( traitsForm.getTraitFrom() )){
			    juvenileName = casefileForm.getJuvenileFullName();
			}
		}
		JuvenileProfileForm profileForm = UIJuvenileHelper.getHeaderForm(aRequest, false);
		if(profileForm != null)
		{
			profileForm.setRestrictedAccess(UIJuvenileHelper.checkRestrictedAcces(profileForm.getJuvenileNum()));
			if( "profile".equals( traitsForm.getTraitFrom() )){
			    juvenileName = profileForm.getJuvenileName();
			}
		
		}
		
		
		sendDualStatusEmail( juvenileNum , juvenileName, traitDescriptionId);
		
		
	
		return forward ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addTrait( ActionMapping aMapping, ActionForm aForm, 
				HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		TraitsForm form = (TraitsForm)aForm ;

		JuvenileTraitResponseEvent juvenileTrait = this.createJuvenileTrait( form ) ;
		
		form.getNewTraits( ).add( juvenileTrait ) ;

		form.clearAddTraitsForm( ) ;

		form.setTraitTypeId( "" ) ;
			
		return aMapping.findForward( ADD_TRAIT ) ;
	}

	/**
	 * @param form
	 * @return
	 */
	private JuvenileTraitResponseEvent createJuvenileTrait( TraitsForm form )
	{
		JuvenileTraitResponseEvent juvenileTrait = new JuvenileTraitResponseEvent( ) ;

		String traitTypeId = form.getTraitTypeId( ) ;
		if(traitTypeId==null || traitTypeId=="")
		{
		    traitTypeId="26";
		}
		String traitTypeDescriptionId = form.getTraitTypeDescriptionId( ) ;
		
		TraitTypeResponseEvent rootTrait = null ;
		TraitTypeResponseEvent leafTrait = null ;

		if( form.getRootTraitMap( ) != null )
			rootTrait = (TraitTypeResponseEvent)form.getRootTraitMap( ).get( traitTypeId ) ;
		
		if( form.getLeafTraitMap( ) != null )
			leafTrait = (TraitTypeResponseEvent)form.getLeafTraitMap( ).get( traitTypeDescriptionId ) ;

		if( rootTrait != null )
			juvenileTrait.setTraitTypeName( rootTrait.getTraitName( ) ) ;

		if( leafTrait != null )
			juvenileTrait.setTraitTypeDescription( leafTrait.getTraitName( ) ) ;

		juvenileTrait.setTraitTypeId( ((traitTypeDescriptionId != null) ? traitTypeDescriptionId : "") ) ;

		juvenileTrait.setTraitComments( form.getTraitComments( ) ) ;
		juvenileTrait.setStatusId( form.getStatusId( ) ) ;
		juvenileTrait.setStatus( form.getStatus( ) ) ;
		juvenileTrait.setInformationSrcCd(form.getInformationSrcCd());
		juvenileTrait.setInformationSrcDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.INFORMATION_SOURCE, form.getInformationSrcCd()));
		juvenileTrait.setEntryDate(DateUtil.getCurrentDate());
		return juvenileTrait ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward removeTraits( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(REMOVE_TRAITS);

		TraitsForm form = (TraitsForm) aForm;
		if(	  form.getSelectedValue()!= null && 
				!(form.getSelectedValue().equals("")) && 
				  form.getNewTraits()!= null )
		{
			int offset = Integer.parseInt(form.getSelectedValue() ) ;
			if( form.getNewTraits().size() > offset )
			{
				((ArrayList)form.getNewTraits()).remove( offset );							
			}
		}

		return forward;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
				HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
				HttpServletRequest aRequest, HttpServletResponse aResponse )
	{

		TraitsForm form = (TraitsForm)aForm ;
		String myForward = setForwardMapping( UIConstants.CANCEL, form.getCategoryName( ) ) ;
		ActionForward forward = aMapping.findForward( myForward ) ;
		return forward ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap( )
	{
		Map keyMap = new HashMap( ) ;

		keyMap.put( "button.addTrait", "addTrait" ) ;
		keyMap.put( "button.removeSelected", "removeTraits" ) ;
		keyMap.put( "button.remove", "removeTraits" ) ;
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ;

		return keyMap ;
	}

	/*
	 * @param aForward
	 * @param aCategoryForward
	 * @return
	 */
	private String setForwardMapping( String aForward, String aCategoryForward )
	{
		if( aCategoryForward != null && aCategoryForward.length( ) > 0 )
			return aForward + aCategoryForward ;
		else
			return aForward ;
	}
	
	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
   {
		ActionErrors errors = new ActionErrors();
		if(aActionErrors!=null && aActionErrors.size()>0){
			Iterator i=aActionErrors.iterator();
			while(i.hasNext()){
				ActionMessage error=(ActionMessage)i.next();
				errors.add(ActionErrors.GLOBAL_MESSAGE,error);
			}
		   saveErrors(aRequest, errors);
		}
   }
   
   private void  sendDualStatusEmail( String juvenileNum,
	   				String juvenileName,
	   					String traitDescriptionId){
       	boolean isDST = false;
       	String headerFacilityStatus = "";
       	IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
       	
	if ("DST".equals( traitDescriptionId ) ) {
	    isDST = true;
	}
	
	JuvenileFacilityHeaderResponseEvent jjsHeaderInfo = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNum);
	if ( jjsHeaderInfo != null ){
	    headerFacilityStatus = jjsHeaderInfo.getFacilityStatus();
	}
	
	if ( isDST && "D".equals( headerFacilityStatus ) ){
	    //Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("DUAL STATUS YOUTH DETAINED NOTIFICATION GROUP");
	    List<UserEntityBean> userGroup = PDSecurityHelper.getUserGroupByIdOrName("DUAL STATUS YOUTH DETAINED NOTIFICATION GROUP", "");
	    if( userGroup!=null){
		 
		
		   for ( UserEntityBean user : userGroup )
		   {
			
						
			if( user.getEmail()!=null && ! user.getEmail().equals(""))
		    	{
			    
	    			//send email
			    	System.out.println("Email:" + user.getEmail() );
	    			SendEmailEvent sendEmailEvent = new SendEmailEvent();
	    			sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
			    	UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, user.getEmail());
			    	//sendEmailEvent.addToAddress("Dustin.Nguyen@us.hctx.net");
	    			sendEmailEvent.setSubject( "Dual Status - Youth is in Detention, Juvenile: " + juvenileNum + ", " + juvenileName);
			    	sendEmailEvent.setMessage("This email serves as notice of a Dual Status youth who is actively booked in Detention.");
			    	dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			    	dispatch.postEvent(sendEmailEvent);
		    	}
		    }
		}
	}
   }

}
