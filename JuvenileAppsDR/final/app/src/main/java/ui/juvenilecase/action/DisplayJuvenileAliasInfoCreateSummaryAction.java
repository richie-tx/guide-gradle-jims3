package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileAliasInfoForm;
import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;

public class DisplayJuvenileAliasInfoCreateSummaryAction extends
		LookupDispatchAction {
	
	public static final String ALIAS_INFO_FORM = "juvenileAliasInfoForm";
	public static final String PHYSICALCHARACTERISTICS_FORM = "juvenilePhysicalCharacteristicsForm";

	protected Map getKeyMethodMap() {
        Map keyMap = new HashMap();
        keyMap.put("button.next", "next");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.deleteAlias", "deleteAlias");

        return keyMap;
	}
	
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
    		HttpServletRequest aRequest, HttpServletResponse aResponse){
    	
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
        
    }

    public ActionForward deleteAlias(ActionMapping aMapping, ActionForm aForm, 
    		HttpServletRequest aRequest, HttpServletResponse aResponse){
    	JuvenileAliasInfoForm juvenileAliasInfoForm = (JuvenileAliasInfoForm) aForm;
    	String juvId = aRequest.getParameter("aliasId");
    	if(juvId != null){
    		JuvenilePhysicalCharacteristicsForm phyForm = (JuvenilePhysicalCharacteristicsForm)aRequest.getSession().getAttribute(PHYSICALCHARACTERISTICS_FORM);
    		List<JuvenileAliasInfoForm> list = phyForm.getJuvenileAliasList();
    		for(JuvenileAliasInfoForm j:list){
    			String tempId = UIJuvenileHelper.encodeOID(juvId); 
    			if(tempId.equals(j.getId())){
    				juvenileAliasInfoForm = j;
    				juvenileAliasInfoForm.setAction(UIConstants.DELETE);
    				aRequest.getSession().setAttribute(ALIAS_INFO_FORM, juvenileAliasInfoForm);
    				break;
    			}
    		}
    	}
        ActionForward forward = aMapping.findForward("removeConfirm");
        return forward;
        
    }

    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
    		HttpServletRequest aRequest, HttpServletResponse aResponse){
    	HttpSession session = aRequest.getSession();
    	
        JuvenileAliasInfoForm juvenileAliasInfoForm = (JuvenileAliasInfoForm) aForm;
        juvenileAliasInfoForm.setAction(UIConstants.SUMMARY);
        session.setAttribute(ALIAS_INFO_FORM, juvenileAliasInfoForm);
        
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }
}
