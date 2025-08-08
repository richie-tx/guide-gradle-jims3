package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.CreateSubstanceAbuseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.juvenilecase.form.JuvenileDrugForm;

public class HandleSubstanceAbuseCreateAction extends JIMSBaseAction
{
    public HandleSubstanceAbuseCreateAction(){}
    
    public ActionForward addSubstanceAbuse(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse){
	
	JuvenileDrugForm drugForm = (JuvenileDrugForm)aForm;
	if (drugForm.getAssociateCasefile().isEmpty() || drugForm.getAssociateCasefile().equalsIgnoreCase("0"))
	{
	    drugForm.setMsg("Associated Casefile is required."); 
	    return aMapping.findForward(UIConstants.FAILURE);
	}//added the above check for BUG 179592
	drugForm.setSubstanceAbuseDesc( CodeHelper.getCodeDescription(PDCodeTableConstants.TJJD_SUBSTANCE_ABUSE, drugForm.getSubstanceAbuse()));
	if( drugForm.getSubstancesType() != null
		&& drugForm.getSubstancesType().length > 0  ){
	    StringBuffer substanceType = new StringBuffer();
	    StringBuffer substanceTypeDesc = new StringBuffer();
	    for ( int i =0; i < drugForm.getSubstancesType().length; i++ ){
		if ( i == 0 ){
		    substanceType.append( drugForm.getSubstancesType()[i]);
		    substanceTypeDesc.append(CodeHelper.getCodeDescription(PDCodeTableConstants.DRUG_TYPE, drugForm.getSubstancesType()[i] ));
		} else {
		    substanceType.append( ", " +  drugForm.getSubstancesType()[i]);
		    substanceTypeDesc.append(", " + CodeHelper.getCodeDescription(PDCodeTableConstants.DRUG_TYPE, drugForm.getSubstancesType()[i] ) );
		}
	    }
	    
	    drugForm.setSubstanceType(substanceType.toString() );
	    drugForm.setSubstanceTypeDesc(substanceTypeDesc.toString());
	    
	}
	
	
	return aMapping.findForward("addSuccess");
    }
    
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse){
	
	JuvenileDrugForm drugForm = (JuvenileDrugForm) aForm;
	CreateSubstanceAbuseEvent createEvent = (CreateSubstanceAbuseEvent)
							EventFactory.getInstance(JuvenileControllerServiceNames.CREATESUBSTANCEABUSE);
	createEvent.setJuvenileNumber(drugForm.getJuvenileNum());
	createEvent.setAssociateCasefile(drugForm.getAssociateCasefile());
	createEvent.setSubstanceAbuse(drugForm.getSubstanceAbuse());
	createEvent.setSubstanceType(drugForm.getSubstanceType());
	createEvent.setTreatmentLocation( drugForm.getTreatmentLocation() );
	
	IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
	try {
	    if( drugForm.getReferralNum() != null
		    && drugForm.getReferralNum().length() > 0 ){
		String[] referralNumbers = drugForm.getReferralNum().split(",");
		for (String referralNumber : referralNumbers ) {
		    createEvent.setReferralNumber(referralNumber);
		    dispatch.postEvent( createEvent ) ;
		}
	    }
	  
	    drugForm.setMsg("Substance abuse information is created successfully. ");
	} catch (Exception e ){
	    drugForm.setMsg("Failed to create the substance abuse information - " + e.toString());
	    return aMapping.findForward("addSuccess");
	}
	
	return aMapping.findForward("finishSuccess");
    }
    

    public ActionForward backToList(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileDrugForm drugForm = (JuvenileDrugForm)aForm;
	drugForm.setAction(UIConstants.VIEW);
	
	return aMapping.findForward("backToList");
    }
    
    protected void addButtonMapping(Map buttonMap)
   {
   	buttonMap.put("button.add", "addSubstanceAbuse");
   	buttonMap.put("button.finish", "submit");
   	buttonMap.put("button.back", "back");
   	buttonMap.put("button.cancel", "back");
   	buttonMap.put("button.backToList", "backToList");

   	return;
   }

}
