package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenile.GetJuvenileGangsEvent;
import messaging.juvenile.reply.JuvenileGangsResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileGangsForm;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm;
/**
 * 
 * @author sthyagarajan
 *
 */
public class DisplayJuvenileGangsAction extends LookupDispatchAction
{
	//Added for task 11051, as gang is accessed from the profile page.
	private static final String BRIEFING_FORM_STR = "juvenileBriefingDetailsForm" ;
	private static final String PROFILE_FORM_STR = "juvenileProfileForm" ;

	
    public DisplayJuvenileGangsAction()
    {

    }
    
    /**
     * ER_GANG-JIMS200074578 CHANGES.
     * Fetches the gang list by calling the get service from JCGANGS table. 
     * @param juvenileNum
     * @return
     */
    private List fetchJuvenileGangs(String juvenileNum)
    {
        GetJuvenileGangsEvent event = (GetJuvenileGangsEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETJUVENILEGANGS);
        event.setJuvenileNum(juvenileNum);
        CompositeResponse composite = MessageUtil.postRequest(event);
        List<JuvenileGangsResponseEvent> gangs = MessageUtil.compositeToList(composite, JuvenileGangsResponseEvent.class);
        Collections.sort(gangs);

        return gangs;
    }
    
    /**
     * 
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.link", "link");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.tab", "tab");
        return keyMap;
    }
    /**
     * ER_GANG-JIMS200074578 CHANGES.
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	HttpSession session = aRequest.getSession();
    	//Added for task 11051, as gang is accessed from the profile page.
    	JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm)
				session.getAttribute( BRIEFING_FORM_STR );
    	
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        
        TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
        traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_GANGS);
        traitsForm.clear();
        
        String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        traitsForm.setJuvenileNumber(juvenileNum);
        traitsForm.setUICasefile(false); // mark the fact that we are coming in from profile not casefile
        traitsForm.initializeTraitForm(true);
        traitsForm.setActiveCasefileFound(UIJuvenileTraitsHelper.findActiveCasefile(juvenileNum));
        
        //ER GANG #JIMS200074578 STARTS
        JuvenileGangsForm juvGangsForm = (JuvenileGangsForm) aForm;
       // juvGangsForm.setAction(UIConstants.VIEW);
        juvGangsForm.setGangsInfoList(new ArrayList());
        juvGangsForm.clear();
        juvGangsForm.setJuvenileNum(juvenileNum);
        
        String action = juvGangsForm.getAction();
        if (action==null)
        {
        	juvGangsForm.setAction(UIConstants.VIEW);
        }

        Collection gangsResponse = this.fetchJuvenileGangs(juvenileNum);
        juvGangsForm.setGangsList(gangsResponse);
        //ER GANG #JIMS200074578 ENDS
        
        //ER 11051 GANG TATTOO STARTS
        JuvenilePhotoForm myPhotoForm = UIJuvenileHelper.getJuvPhotoForm(aRequest, true);
        myPhotoForm.setIsTattoo(true);
        myPhotoForm.setJuvenileNumber(juvenileNum);
        //ER 11051 GANG TATTOO ENDS
        
        
        // code added for defect #72492 to display all profile tabs
		JuvenileMainForm jmForm = (JuvenileMainForm)session.getAttribute("juvenileMainForm");
		if(jmForm == null )
		{
			jmForm = new JuvenileMainForm();
		}
        if (jmForm.isHasCasefile() == false){
        	jmForm.setHasCasefile(true);  // must be true to display tabs
        }
        aRequest.getSession().setAttribute("juvenileProfileMainForm", jmForm);
       
        //Added for task 11051, as gang is accessed from the profile page.
        JuvenileProfileForm profileForm = (JuvenileProfileForm)session.getAttribute(PROFILE_FORM_STR);
		if( profileForm == null )
		{
			if (form != null) {
				UIJuvenileHelper.populateJuvenileProfileHeaderForm( aRequest, juvenileNum );	
			}
		}
		
        // end defect code
	return forward;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward tab(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
        traitsForm.setAction(UIConstants.VIEW);
       
        return link(aMapping, aForm, aRequest, aResponse);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }
 
}