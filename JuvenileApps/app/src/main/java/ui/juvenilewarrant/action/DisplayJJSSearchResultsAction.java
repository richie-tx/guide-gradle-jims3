package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.ICode;
import messaging.juvenile.GetJuvenilePhysicalAttributesEvent;
import messaging.juvenile.GetTattoosScarsPhysicalAttributesEvent;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import messaging.juvenile.reply.TattoosScarsPhysicalAttributesResponseEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.AbstractAction;
import ui.common.UIUtil;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ldeen
 *
 */
public class DisplayJJSSearchResultsAction extends AbstractAction
{

	/**
	@roseuid 40D89C070110
	 */
	public DisplayJJSSearchResultsAction()
	{

	}

	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forwardStr = UIConstants.SUCCESS;

		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
// trap refresh button		
		if (form.getRefreshButton().equalsIgnoreCase("Y"))
		{
			form.clear();
			return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		}

		this.processHttpRequest(aRequest, form);

		CompositeResponse response =
			UIJuvenileWarrantHelper.fetchJJSWarrantData(
				form.getReferralNum(),
				form.getJuvenileNum(),
				form.getPetitionNum(),
				form.getWarrantTypeId());

		MessageUtil.processReturnException(response);

		Collection warrants =
			(Collection) MessageUtil.compositeToCollection(
				response,
				PDJuvenileWarrantConstants.JUVENILE_JUSTICE_SYSTEM_EVENT_TOPIC);

		ActionErrors errors = new ActionErrors();
		this.processBusinessExceptions(aRequest, response, errors, form);
		this.saveErrors(aRequest, errors);

		if (errors.isEmpty())
		{
			if (warrants.size() == 0)
			{
				forwardStr = this.processZeroResults(form);
			}
			else if (warrants.size() == 1)
			{
				forwardStr = this.processSingleResult(aRequest, response, form, errors);
			}
			else
			{
				forwardStr = this.processMultipleResults();
			}
		}
		else
		{
			forwardStr = UIConstants.SEARCH_FAILURE;
		}
		
		form.setBackToWarrantUrl(aMapping.findForward(forwardStr).getPath());

		return aMapping.findForward(forwardStr);
	}

	/**
	 * 
	 */
	private String processMultipleResults()
	{
		// Should not be getting multiple results due to the nature of JJS search criteria
		return UIConstants.SEARCH_FAILURE;
	}

	private String processZeroResults(JuvenileWarrantForm form)
	{
		// Put this in the form
		// clear form
		form.clear();
		return UIConstants.SEARCH_FAILURE;
	}

	private String processSingleResult(
		HttpServletRequest aRequest,
		CompositeResponse response,
		JuvenileWarrantForm form,
		ActionErrors errors)
	{
		String forwardStr = UIConstants.SUCCESS;
		
		boolean unrecoverableError = form.setJJSProperties(response, errors);
		
		this.saveErrors(aRequest, errors);

		if (unrecoverableError == true)
		{
			forwardStr = UIConstants.SEARCH_FAILURE;
			StringBuffer buffer = new StringBuffer(30);
			String juvenileNumber = form.getJuvenileNum();
		    String petitionNumber = form.getPetitionNum();
		    String referralNumber = form.getReferralNum();
		    
		    if(juvenileNumber != null && !juvenileNumber.equals(""))
		    {
		    	buffer.append(" Juvenile # ");
		    	buffer.append(juvenileNumber);
		    }
		    
		    if(petitionNumber != null && !petitionNumber.equals(""))
		    {
		    	buffer.append(" Petition # ");
		    	buffer.append(petitionNumber);
		    }
		    
    		buffer.append(" and Referral # ");
    		buffer.append(referralNumber);
		    String errorString = buffer.toString();
			ActionMessage error = new ActionMessage("error.noRecordsFound", errorString);
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			super.setUnrecoverableError(aRequest);
		}
		else
		{
			// TODO Move this to the Form director
		    	/*String schoolDistrictId = form.getSchoolDistrictId();
			String schoolCodeId = form.getSchoolCodeId();
			if (schoolDistrictId != null)
			{
				if (schoolDistrictId.length() == 1)
				{
					schoolDistrictId = "00" + schoolDistrictId;
					form.setSchoolDistrictId(schoolDistrictId);
				}
				if (schoolDistrictId.length() == 2)
				{
					schoolDistrictId = "0" + schoolDistrictId;
					form.setSchoolDistrictId(schoolDistrictId);
				}
			}
			if (schoolCodeId != null)
			{
				if (schoolCodeId.length() == 1)
				{
					schoolCodeId = "00" + schoolCodeId;
					form.setSchoolCodeId(schoolCodeId);
				}
				if (schoolCodeId.length() == 2)
				{
					schoolCodeId = "0" + schoolCodeId;
					form.setSchoolCodeId(schoolCodeId);
				}
			}*/			
			
			form.refreshSchool();
		}
		handlePhysicalCharacteristics(aRequest, response, form, errors);
		return forwardStr;
	}

	/**
	 * 
	 * @param aRequest
	 * @param aForm
	 */
	private void processHttpRequest(HttpServletRequest aRequest, JuvenileWarrantForm aForm)
	{
		// TODO Put these literal values into Constants class

		// Pull petitionNum from Request
		String petitionNum = aRequest.getParameter("petitionNum");
		aRequest.removeAttribute("petitionNum");
		if (petitionNum != null)
		{
			aForm.setPetitionNum(petitionNum);
		}

		// Pull Juvenile Number from Request
		String juvNum = aRequest.getParameter("juvenileNum");
		aRequest.removeAttribute("juvenileNum");
		if (juvNum != null)
		{
			aForm.setJuvenileNum(juvNum);
		}

		// Pull Referral Number from Request
		String referralNum = aRequest.getParameter("referralNum");
		aRequest.removeAttribute("referralNum");
		if (referralNum != null)
		{
			aForm.setReferralNum(referralNum);
		}
	}

	/* (non-Javadoc)
	 * @see ui.action.AbstractAction#processBusinessExceptions(mojo.km.messaging.Composite.CompositeResponse, org.apache.struts.action.ActionErrors)
	 */
	public void processBusinessExceptions(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors, JuvenileWarrantForm form )
	{
		ActiveWarrantErrorEvent activeError =
			(ActiveWarrantErrorEvent) MessageUtil.filterComposite(response, ActiveWarrantErrorEvent.class);

		Collection charges =
			(Collection) MessageUtil.compositeToCollection(response, PDJuvenileWarrantConstants.PETITION_EVENT_TOPIC);
		charges = MessageUtil.processEmptyCollection(charges);

		Collection warrants =
			(Collection) MessageUtil.compositeToCollection(
				response,
				PDJuvenileWarrantConstants.JUVENILE_JUSTICE_SYSTEM_EVENT_TOPIC);
		warrants = MessageUtil.processEmptyCollection(warrants);

		if (activeError != null)
		{
			ActionMessage error = new ActionMessage("error.juvenilewarrant.activeorpending.warrant.found");
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			 saveErrors(aRequest, errors);
		}
		else if (warrants.size() == 0)
		{
			StringBuffer buffer = new StringBuffer(30);
			String juvenileNumber = form.getJuvenileNum();
		    String petitionNumber = form.getPetitionNum();
		    String referralNumber = form.getReferralNum();
		    
		    if(juvenileNumber != null && !juvenileNumber.equals(""))
		    {
		    	buffer.append(" Juvenile # ");
		    	buffer.append(juvenileNumber);
		    }
		    
		    if(petitionNumber != null && !petitionNumber.equals(""))
		    {
		    	buffer.append(" Petition # ");
		    	buffer.append(petitionNumber);
		    }
		    
    		buffer.append(" and Referral # ");
    		buffer.append(referralNumber);
		    String errorString = buffer.toString();
			ActionMessage error = new ActionMessage("error.noRecordsFound", errorString);
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
		}
	}

	public void processBusinessExceptions(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 
	 */
	private void handlePhysicalCharacteristics( HttpServletRequest aRequest, CompositeResponse response,
												JuvenileWarrantForm form, ActionErrors errors)
	{
		GetJuvenilePhysicalAttributesEvent requestEvent = new GetJuvenilePhysicalAttributesEvent();
		requestEvent.setJuvenileNum(form.getJuvenileNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		List<JuvenilePhysicalAttributesResponseEvent> physicalChars = 
				MessageUtil.compositeToList(replyEvent, JuvenilePhysicalAttributesResponseEvent.class);

		if( physicalChars.size() > 0 )
		{
			// sort this list based on entryDate
			Collections.sort( (List)physicalChars );
			JuvenilePhysicalAttributesResponseEvent phyAttRespEvent = physicalChars.get(0);
			
			String item = phyAttRespEvent.getBuild();
			if( notNullNotEmptyString(item) )
			{
				form.setBuildId(item);
			}

			item = phyAttRespEvent.getHeightFeet();
			if( notNullNotEmptyString(item) )
			{
				form.setHeightFeet(item);
			}

			item = phyAttRespEvent.getHeightInch();
			if( notNullNotEmptyString(item) )
			{
				form.setHeightInch(item);
			}
			
			item = phyAttRespEvent.getWeight();
			if( notNullNotEmptyString(item) )
			{
				form.setWeight(item);
			}
			
			item = phyAttRespEvent.getEyeColor();
			if( notNullNotEmptyString(item) )
			{
				form.setEyeColorId(item);
			}
			
			item = phyAttRespEvent.getHairColor();
			if( notNullNotEmptyString(item) )
			{
				form.setHairColorId(item);
			}
			
			item = phyAttRespEvent.getComplexion();
			if( notNullNotEmptyString(item) )
			{
				form.setComplexionId(item);
			}
			
			GetTattoosScarsPhysicalAttributesEvent request = new GetTattoosScarsPhysicalAttributesEvent();

			request.setJuvenileNum(form.getJuvenileNum());

			// get current Tattoos and Scars
			TattoosScarsPhysicalAttributesResponseEvent responseEvent = (TattoosScarsPhysicalAttributesResponseEvent)
       		MessageUtil.postRequest(request, TattoosScarsPhysicalAttributesResponseEvent.class); 
			
			Collection scars = responseEvent.getScars(); 
			String[] currentScars = new String[20];
			List currScarDescList = new ArrayList();
			if ( scars != null ) {
				Iterator s = scars.iterator();
				while (s.hasNext())
				{
					for ( int a =0; a < scars.size(); a ++ ){
						
						ICode code = (ICode) s.next();
						currentScars[a]= code.getCode();
						currScarDescList.add(code.getDescription());
					}
				}
				// set Current Scars and Marks
				String currentScarsDesc = UIUtil.separateByDelim( currScarDescList , "; " );
				form.setOriginalScarsString( currentScarsDesc );
				// select Current Scars and Marks on Additional Scars and Marks list
				form.setSelectedScars(currentScars);
			}
				
			Collection tattoos = responseEvent.getTattoos();
			String[] currentTattoos = new String[20];
			List currTattDescList = new ArrayList();
			if ( tattoos != null ) {
				Iterator t = tattoos.iterator();
				while (t.hasNext())
				{
					for ( int a =0; a < tattoos.size(); a ++ ){
						
						ICode code = (ICode) t.next();
						currentTattoos[a]= code.getCode();
						currTattDescList.add(code.getDescription());
					}
				}
				// set Current Tattoos 
				String currentTattoosDesc = UIUtil.separateByDelim( currTattDescList , "; " );
				form.setOriginalTattoosString( currentTattoosDesc );
				// select Current Tattoos on Additional Tattoos list
				form.setSelectedTattoos(currentTattoos);
			}
		}
	}

	/*
	 * returns true if string isn't null and contains one or more chars
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null && str.trim().length() > 0 );
	}
}
