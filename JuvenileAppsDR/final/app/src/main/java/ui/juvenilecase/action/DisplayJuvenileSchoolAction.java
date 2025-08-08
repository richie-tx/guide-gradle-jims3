// Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileSchoolAction.java

package ui.juvenilecase.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileGEDProgramEvent;
import messaging.juvenile.GetJuvenileInfoLightEvent;
import messaging.juvenile.reply.JuvenileCoreLightResponseEvent;
import messaging.juvenile.reply.JuvenileGEDProgramResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTraitsHelper;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.JuvenileSchoolHistoryForm;
import ui.juvenilecase.form.TraitsForm;

public class DisplayJuvenileSchoolAction extends LookupDispatchAction
{
    //extends LookupDispatchAction
    private final static String VIEW = "view";

    /**
     * @roseuid 42B1A2B00196
     */
    public DisplayJuvenileSchoolAction()
    {

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

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42B03B350171
     */
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
        traitsForm.setCategoryName(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_SCHOOL);
        traitsForm.clear();
        String juvenileNum = aRequest.getParameter("juvnum");
        if ( juvenileNum == null
        	|| juvenileNum.length() == 0 ) {
            juvenileNum =UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        } else {
            UIJuvenileHelper.populateJuvenileProfileHeaderForm( aRequest, juvenileNum );
        }
      
        traitsForm.setJuvenileNumber(juvenileNum);
        traitsForm.setUICasefile(false); // mark the fact that we are coming in from profile not
                                         // casefile
        traitsForm.initializeTraitForm(true);
        traitsForm.setActiveCasefileFound(UIJuvenileTraitsHelper.findActiveCasefile(juvenileNum));

        // SCHOOL SPECIFIC STUFF
        JuvenileSchoolHistoryForm schoolForm = (JuvenileSchoolHistoryForm) aForm;
        // get the juvenile's current school history
        this.setSchoolHistoryForm(juvenileNum, schoolForm);

        return forward;
    }

    /**
     * @param schoolForm
     */
    private void setSchoolHistoryForm(String juvenileNum, JuvenileSchoolHistoryForm schoolForm)
    {
        Collection schoolHistory = UIJuvenileHelper.fetchSchoolHistory(juvenileNum);
        /*JuvenileCore juvenile = JuvenileCore.findCore(juvenileNum);*/
        GetJuvenileInfoLightEvent req = (GetJuvenileInfoLightEvent) 
        			EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEINFOLIGHT);
        req.setJuvenileNum(juvenileNum);
        JuvenileCoreLightResponseEvent juvenile = (JuvenileCoreLightResponseEvent) MessageUtil.postRequest(req, JuvenileCoreLightResponseEvent.class);
        schoolForm.clear();
        System.out.println("Set School History Form");
	if ( juvenile != null) {
	    String createDate = new SimpleDateFormat("MM/dd/yyyy").format(juvenile.getCreateDate());
	    schoolForm.setCreateDate( createDate);
	}
        schoolForm.setJuvenileNum(juvenileNum);
        schoolForm.setAction(VIEW);
        schoolForm.setSchoolHistory(schoolHistory);
       
        List programGEDs = new ArrayList();

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		GetJuvenileGEDProgramEvent event = (GetJuvenileGEDProgramEvent)EventFactory.getInstance( 
				JuvenileControllerServiceNames.GETJUVENILEGEDPROGRAM );

		event.setJuvenileNum( juvenileNum );
		dispatch.postEvent( event );

		CompositeResponse composite = (CompositeResponse)dispatch.getReply();
		programGEDs = MessageUtil.compositeToList( composite, JuvenileGEDProgramResponseEvent.class );
 
		if (programGEDs != null && !programGEDs.isEmpty()) 
		{
			int len = programGEDs.size();
			for (int x=0; x<len; x++)
			{
				JuvenileGEDProgramResponseEvent jgrEvent = (JuvenileGEDProgramResponseEvent)  programGEDs.get(x);
				jgrEvent.setProgramDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GEDPROGRAM, jgrEvent.getProgramCd() ) );
				jgrEvent.setEnrollmentStatusDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.EXIT_TYPE, jgrEvent.getEnrollmentStatusCd() ) );
				jgrEvent.setParticipationDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SCHOOL_PARTICIPATION, jgrEvent.getParticipationCd() ) );
			}
		}
        schoolForm.setExistingGEDPrograms(programGEDs);
        programGEDs = null;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42B03B350171
     */
    public ActionForward tab(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TraitsForm traitsForm = UIJuvenileHelper.getTraitsForm(aRequest, true);
        traitsForm.setAction(UIConstants.VIEW);
        JuvenileMainForm jmForm = (JuvenileMainForm)aRequest.getSession().getAttribute("juvenileMainForm");
	
	if(jmForm == null )
	{
		jmForm = new JuvenileMainForm();
	}
        if (jmForm.isHasCasefile() == false){
        	jmForm.setHasCasefile(true);  // must be true to display tabs
        }
        aRequest.getSession().setAttribute("juvenileProfileMainForm", jmForm);
        return link(aMapping, aForm, aRequest, aResponse);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
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

}
