package ui.juvenilecase.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileDetailsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileProfileCasefileListForm;
import ui.juvenilecase.form.JuvenileProfileForm;

/**
 * Class DisplayJuvenileProfileCasefileListAction.
 * 
 * @author Anand Thorat
 */
public class DisplayJuvenileProfileCasefileListAction extends
		org.apache.struts.action.Action
{
	/**
	 * @roseuid 42B88AAF0186
	 */
	public DisplayJuvenileProfileCasefileListAction()
	{
	}

	// ui.juvenilecase.action.DisplayJuvenileProfileCasefileListAction.
	// DisplayJuvenileProfileCasefileListAction

	// ------------------------------------------------------------------------
	// --- methods ---
	// ------------------------------------------------------------------------
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42B888920198
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileProfileForm form = UIJuvenileHelper.getHeaderForm(aRequest);

		GetJuvenileCasefileDetailsEvent search = (GetJuvenileCasefileDetailsEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEDETAILS);
		
		String juvenileId = "" ;
		if( form != null )
		{ /* per a Defect, the next line of code was throwing NPE,
			* so we're going to try to get the Juv# from the Casefile form.
			*/
			juvenileId = form.getJuvenileNum();
			//UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, juvenileId);
		}
		else
		{ // can't get the Profile form, try for the Casefile form
			JuvenileCasefileForm cForm =  UIJuvenileHelper.getJuvenileCasefileForm( aRequest ) ;
			if( cForm != null)
			{
				juvenileId = cForm.getJuvenileNum() ;
			}
			//added for US 13323
			UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, juvenileId);
		}
		String action = aRequest.getParameter("action");
		if((juvenileId==null || juvenileId.equals("")) || (action!=null && action.equalsIgnoreCase("casefilesLink"))){
			juvenileId = aRequest.getParameter("juvenileNum");
			//if header form is empty // backup.
			if( form == null ){
			    
			    form = new JuvenileProfileForm();
			    form.setJuvenileNum(juvenileId);
			    UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, juvenileId);
			}
			else
			{
			    //always use juvenil# passed in querystring.
			    form.setJuvenileNum(juvenileId);
			    UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, juvenileId);
			}	
		}
			
		search.setJuvenileNum(juvenileId);

		List caseFileCollection = MessageUtil.postRequestListFilter(search,
				JuvenileProfileCasefileListResponseEvent.class);

		JuvenileProfileCasefileListForm listForm = (JuvenileProfileCasefileListForm)aForm;
		if(listForm==null)
		{
			listForm = new JuvenileProfileCasefileListForm();
			
		}
		listForm.setSearchResultSize(caseFileCollection.size());
		Collections.sort(caseFileCollection);
		listForm.setJuvenileProfileCasefileList(caseFileCollection);
		HttpSession session = aRequest.getSession();
		session.setAttribute( "juvenileProfileCasefileListForm", listForm );
		aRequest.setAttribute(PDJuvenileCaseConstants.JUVENILENUM_PARAM,juvenileId);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
