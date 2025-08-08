package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.DeleteCategoryEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDeleteForm;

import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class SubmitRiskCategoryDeleteAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "removeRequest");
		keyMap.put("button.categorySearch", "backToSearch");
	}

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward removeRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	RiskCategoryDeleteForm rcDeleteForm = (RiskCategoryDeleteForm) aForm;
      	DeleteCategoryEvent delCtgEvt = 
       		(DeleteCategoryEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.DELETECATEGORY);
       	
      	delCtgEvt.setCategoryId(rcDeleteForm.getCategory().getCategoryId());

    	MessageUtil.postRequest(delCtgEvt);
    	
    	rcDeleteForm.setPageType("confirm");
    	return aMapping.findForward("deleteSuccess");
    }  
	
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	RiskCategoryDeleteForm rcDeleteForm = (RiskCategoryDeleteForm) aForm;
       	rcDeleteForm.clear();
       	rcDeleteForm.setPageType("");
    	return aMapping.findForward("backToSearch");
    }  

}
