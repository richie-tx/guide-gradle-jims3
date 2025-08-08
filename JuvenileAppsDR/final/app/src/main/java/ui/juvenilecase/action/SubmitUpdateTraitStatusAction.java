/*
 * Created on Jun 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.SaveJuvenileTraitsEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm;

/**
 * @author jfisher
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitUpdateTraitStatusAction extends LookupDispatchAction
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap( )
	{
		Map keyMap = new HashMap( ) ;
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ;
		return keyMap ;
	}
	
	
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
				HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		TraitsForm traitsForm = (TraitsForm)aForm ;
		String forwardStr = UIConstants.SUCCESS;
		SaveJuvenileTraitsEvent saveEvent = (SaveJuvenileTraitsEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.SAVEJUVENILETRAITS ) ;
		// add code to update trait status -- insert with OID (TRAIT_ID)
		saveEvent.setJuvenileNum( traitsForm.getJuvenileNumber( ) ) ;
		saveEvent.setSupervisionNum( traitsForm.getSupervisionNum( ) ) ;
		JuvenileTraitResponseEvent  jtre = (JuvenileTraitResponseEvent ) traitsForm.getUpdateTraitsList().get(0); 
		jtre.setStatusId(traitsForm.getNewStatusId());
		saveEvent.addRequest( jtre ) ;

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( saveEvent ) ;
		
		traitsForm.setAction( UIConstants.CONFIRM_UPDATE) ;
		if (traitsForm.getCategoryName() != null && !"".equals(traitsForm.getCategoryName()))
		{
			forwardStr += traitsForm.getCategoryName();
		}
		//Changes for US 31029 - check for restricted access		
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		if(casefileForm!=null)
			casefileForm.setRestrictedAccess(UIJuvenileHelper.checkRestrictedAcces(traitsForm.getJuvenileNumber()));
		JuvenileProfileForm profileForm = UIJuvenileHelper.getHeaderForm(aRequest, false);
		if(profileForm != null)
		{
			profileForm.setRestrictedAccess(UIJuvenileHelper.checkRestrictedAcces(profileForm.getJuvenileNum()));
			
		}
		
		return aMapping.findForward(forwardStr);
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
		return aMapping.findForward( myForward ) ;
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

}
