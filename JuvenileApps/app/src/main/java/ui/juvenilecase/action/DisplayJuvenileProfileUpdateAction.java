/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayJuvenileProfileUpdateAction
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package ui.juvenilecase.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SaveJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.Features;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileProfileForm;

/**
 * Class DisplayJuvenileProfileUpdateAction.
 *  
 * @author  Anand Thorat
 */
public class DisplayJuvenileProfileUpdateAction extends org.apache.struts.action.Action
{

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42A9C0DD02CE
	 */
	public DisplayJuvenileProfileUpdateAction()
	{

	} //end of ui.juvenilecase.action.DisplayJuvenileProfileUpdateAction.DisplayJuvenileProfileUpdateAction

	// ------------------------------------------------------------------------
	// --- method                                                           ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param aMapping The a mapping.
	 * @param aForm The a form.
	 * @param aRequest The a request.
	 * @param aResponse The a response.
	 * @return  ActionForward
	 * @roseuid 42A9B48800CD
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileMainForm juvMainForm = (JuvenileMainForm) aForm;
		
		//bug fix #30724
		String juvenileNum = aRequest.getParameter("juvenileNum");
		
		 if (juvenileNum == null)
		    {
		        JuvenileProfileForm form = UIJuvenileHelper.getHeaderForm(aRequest);
		        if( form != null )
		        {
		          juvenileNum = form.getJuvenileNum();
		        }
		        else
		        {
		          return aMapping.findForward(UIConstants.FAILURE);
		        }
		    }
        
        GetJuvenileProfileMainEvent getJuvenileEvent = (GetJuvenileProfileMainEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
        getJuvenileEvent.setJuvenileNum(juvenileNum);
        CompositeResponse replyEvent = MessageUtil.postRequest(getJuvenileEvent);
        JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
                JuvenileProfileDetailResponseEvent.class);
        if (detail != null)
        {
            UIJuvenileHelper.setJuvenileMainForm(juvMainForm, detail);
            UIJuvenileHelper.putHeaderForm(aRequest, detail);
        }
        juvMainForm.setAction(UIConstants.CONFIRM_UPDATE_SUCCESS);
        // task 139689
        HttpSession session = aRequest.getSession();
        JuvenileBriefingDetailsForm juvenileBriefingForm = null;

        juvenileBriefingForm = UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);

        if (juvenileBriefingForm == null)
        {

            juvenileBriefingForm = new JuvenileBriefingDetailsForm();
            setProfileDetail(UIJuvenileCaseworkHelper.getHeaderForm( aRequest, true ).getJuvenileNum(), juvenileBriefingForm);
            session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
        }
        //
        //finish bug fix #30724 		

		//Changes added for ER JIMS200077276 starts 
		juvMainForm.setHasFeature(UIJuvenileHelper.isFeatureAllowed(Features.JCW_PRF_MAS_SCL_STU_U));
		//Changes added for ER JIMS200077276 Ends

		juvMainForm.setMatchDetectedSSN(false);
		return aMapping.findForward(UIConstants.SUCCESS);
	} //end of ui.juvenilecase.action.DisplayJuvenileProfileUpdateAction.execute
	private void setProfileDetail(String juvenileNum, JuvenileBriefingDetailsForm form)
	    {
		GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		reqProfileMain.setJuvenileNum(juvenileNum);
		reqProfileMain.setFromProfile(form.getFromProfile());
		CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
		JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

		form.setJisInfo(new JuvenileJISResponseEvent());
		if (juvProfileRE != null)
		{
		    form.setProfileDetail(juvProfileRE);
		    form.setProfileDescriptions();

		    if (juvProfileRE.getDateOfBirth() != null)
		    { // Get age based on
		      // year
			int age = UIUtil.getAgeInYears(juvProfileRE.getDateOfBirth());
			if (age > 0)
			{
			    form.setAge(String.valueOf(age));
			}
			else
			{
			    form.setAge(UIConstants.EMPTY_STRING);
			}
		    }
		    Collection jisResps = juvProfileRE.getJisInfo();
		    if (jisResps != null)
		    {
			Collections.sort((List) jisResps);
			Iterator jisIter = jisResps.iterator();
			if (jisIter.hasNext())
			{
			    JuvenileJISResponseEvent jis = (JuvenileJISResponseEvent) jisIter.next();
			    form.setJisInfo(jis);
			}
		    }

		    form.setInMentalHealthServices(juvProfileRE.isMentalHealthServices());
		    //U.S 88526
		    if (juvProfileRE.getHispanic() != null)
		    {
			if (juvProfileRE.getHispanic().equalsIgnoreCase("Y"))
			{
			    form.setHispanic(UIConstants.YES_FULL_TEXT);
			}
			else
			{
			    form.setHispanic(UIConstants.NO_FULL_TEXT);
			}
		    }
		    else
		    {
			form.setHispanic(UIConstants.EMPTY_STRING);
		    }
		}
	    }
    	



} // end DisplayJuvenileProfileUpdateAction
