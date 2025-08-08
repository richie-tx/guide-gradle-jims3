package ui.juvenilecase.facility.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;
import ui.juvenilecase.form.TraitsForm;

public class HandleJuvenileFacilityTraitsAction extends LookupDispatchAction
{
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap( )
	{
		Map keyMap = new HashMap( ) ;
		keyMap.put( "button.addMoreTraits", "addTraits" ) ;
		keyMap.put( "button.viewTraits", "viewTraits" ) ;
		keyMap.put("button.updateTraitStatus", "updateTraitStatus");
		
		return keyMap ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewTraits( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse ) throws Exception
	{
		TraitsForm form = (TraitsForm)aForm ;
		form.setTraitTypeId("26") ;
		form.setUIFacility(true);
		
		String tempAction = form.getAction( ) ;

		if( tempAction != null && !UIConstants.FIND.equals( tempAction ) )
		{ 
			form.setAction( UIConstants.VIEW ) ;
		}
		form.initializeTraitForm( true ) ;
		return aMapping.findForward( UIConstants.VIEW_SUCCESS ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward addTraits( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse ) throws Exception
	{
		TraitsForm traitsForm = (TraitsForm)aForm ;
		traitsForm.setAction( UIConstants.VIEW ) ;
		traitsForm.clearNewTraits( ) ;
		traitsForm.setCameFromCasefile(true) ; // set to true as the facility has only one casefile.For the current detention.
		traitsForm.setTraitTypeDescriptionId("") ;
		traitsForm.setTraitTypeId("26") ;
		traitsForm.setUIFacility(true);
		//get the AdmitReleaseForm from session - US 42660
	   HttpSession session= aRequest.getSession();
	   //bug fix for 129279
	   /*AdmitReleaseForm arForm = (AdmitReleaseForm)	session.getAttribute("admitReleaseForm");
	   if(arForm!=null)
	   {
		   traitsForm.setFacilityAdmitOID(arForm.getOriginalAdmitOID());
	   }*/
		return( aMapping.findForward( UIConstants.ADD_SUCCESS ) ) ;
	}

    public ActionForward updateTraitStatus(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception
    {	
	TraitsForm traitsForm = (TraitsForm) aForm;
	traitsForm.setUIFacility(true);
	traitsForm.setNewStatusId(UIConstants.EMPTY_STRING);
	traitsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
	String forwardStr = UIConstants.UPDATE_SUCCESS;
	List temp1 = new ArrayList(traitsForm.getTraitsSearchResult() );
	List temp2 = new ArrayList();
	traitsForm.setUpdateTraitCasefile(new ArrayList() );
	traitsForm.setUpdateTraitStatuses(new ArrayList() );
	traitsForm.setUpdateTraitsList(new ArrayList() );
	for(int x=0; x<temp1.size(); x++)
	{
		JuvenileTraitResponseEvent jtrEvent = (JuvenileTraitResponseEvent) temp1.get(x);
		if (traitsForm.getSelectedValue().equals(jtrEvent.getJuvenileTraitId() ) )
		{
			temp2.add(jtrEvent); 
			traitsForm.setUpdateTraitStatuses(UIJuvenileTraitsHelper.loadUpdateTraitStatuses());
			break;
    	}
	}
    traitsForm.setAction(UIConstants.EMPTY_STRING);
	traitsForm.setUpdateTraitsList(temp2);
	temp1 = null;
	temp2 = null;
	return aMapping.findForward(forwardStr);
    }

}
