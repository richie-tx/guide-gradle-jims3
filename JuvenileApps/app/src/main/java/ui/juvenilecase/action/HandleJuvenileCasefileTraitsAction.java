package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.form.TraitsForm;

public class HandleJuvenileCasefileTraitsAction extends LookupDispatchAction
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

		String tempAction = form.getAction( ) ;

		if( tempAction != null && !UIConstants.FIND.equals( tempAction ) )
		{ /*
		 * 9 aug 2006 - mjt - this fix is so the traits page is shown in a "lookup only mode",
		 * i.e., without the ability to *add* new traits. this is used when the traits page is
		 * called from various Risk Analysis pages, and the traits page is popped up in its own
		 * browser window. the key is when the action is of *FIND*.
		 */
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
		traitsForm.setCameFromCasefile( true ) ;

		//form.setCasefiles(casefiles); //no need to get casefile since this is from casefile
		traitsForm.setTraitTypeDescriptionId( "" ) ;
		traitsForm.setTraitTypeId( "" ) ;
		traitsForm.setInformationSources(CodeHelper.getActiveCodes(PDCodeTableConstants.INFORMATION_SOURCE, false));
		return( aMapping.findForward( UIConstants.ADD_SUCCESS ) ) ;
	}

    public ActionForward updateTraitStatus(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws Exception
    {	
	TraitsForm traitsForm = (TraitsForm) aForm;
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
