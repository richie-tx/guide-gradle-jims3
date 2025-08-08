package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenile.CreateDrugTestingInfoEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.security.UserEntityBean;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import net.minidev.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.security.PDSecurityHelper;


import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilecase.form.JuvenileDrugForm;

public class HandleJuvenileDrugTestingCreateAction extends JIMSBaseAction
{
    public HandleJuvenileDrugTestingCreateAction(){}
    
    public ActionForward addDrugTestingInfo(ActionMapping aMapping, ActionForm aForm,
						HttpServletRequest aRequest, HttpServletResponse aResponse){
	
	JuvenileDrugForm drugForm = (JuvenileDrugForm) aForm;
	drugForm.setTestAdministeredDescr(CodeHelper.getCodeDescription(PDCodeTableConstants.DRUG_TEST_ADMINISTERED, drugForm.getTestAdministered()));
	if (drugForm.getDrugTestResult()!= null
		&& drugForm.getDrugTestResult().length() > 0 ){
	    drugForm.setDrugTestResultDescr(ComplexCodeTableHelper.getDrugTestResultDescription(drugForm.getDrugTestResults(), drugForm.getDrugTestResult()));
	} else {
	    drugForm.setDrugTestResultDescr("");
	}
	
	if (drugForm.getSubstancesTested() != null
		&& drugForm.getSubstancesTested().length > 0 ){
	   // drugForm.setSubstanceTestedDescr(ComplexCodeTableHelper.getDrugTypeCodeDescription(drugForm.getDrugTypeCodes(), drugForm.getSubstanceTested() ));
	    StringBuffer  substancesTestedDescr = new StringBuffer ();
	    StringBuffer  substancesTested = new StringBuffer ();
	    for (int i = 0; i <  drugForm.getSubstancesTested().length; i ++ ){
		if ( i == 0 ){
		    substancesTestedDescr.append(ComplexCodeTableHelper.getDrugTypeCodeDescription(drugForm.getDrugTypeCodes(), drugForm.getSubstancesTested()[i]));
		    substancesTested.append(drugForm.getSubstancesTested()[i]);
		} else {
		    substancesTestedDescr.append(", " + ComplexCodeTableHelper.getDrugTypeCodeDescription(drugForm.getDrugTypeCodes(), drugForm.getSubstancesTested()[i]));
		    substancesTested.append("," + drugForm.getSubstancesTested()[i]);
		}
	    }
		
		drugForm.setSubstanceTestedDescr(  substancesTestedDescr.toString() );
		drugForm.setSubstanceTested( substancesTested.toString() );
	    
	} else {
	    drugForm.setSubstanceTestedDescr("");
	}
	
	if ( drugForm.getLocationCodes() != null
		&& drugForm.getLocationCodes().size()> 0 ) {
	    for ( LocationResponseEvent location : drugForm.getLocationCodes() ){
		if ( drugForm.getTestLocation().equals( location.getJuvLocationUnitId() ) ){
		    drugForm.setTestLocationDescr( location.getLocationUnitName() );
		}
	    }
	}
	//drugForm.setTestLocationDescr(ComplexCodeTableHelper.getLocationDesc(drugForm.getLocationCodes(), drugForm.getTestLocation()));
	return aMapping.findForward("addSuccess");
    }
    
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileDrugForm drugForm = (JuvenileDrugForm) aForm;
	CreateDrugTestingInfoEvent createEvent = (CreateDrugTestingInfoEvent)
		EventFactory.getInstance(JuvenileControllerServiceNames.CREATEDRUGTESTINGINFO);
	createEvent.setAssociateCasefile(drugForm.getAssociateCasefile());
	createEvent.setJuvenileNumber(drugForm.getJuvenileNum());
	createEvent.setTestAdministered(drugForm.getTestAdministered());
	createEvent.setTestDate(drugForm.getTestDate());
	createEvent.setTestTime(drugForm.getTestTime());
	createEvent.setSubstanceTested(drugForm.getSubstanceTested());
	createEvent.setDrugTestResult(drugForm.getDrugTestResult());
	createEvent.setTestLocation(drugForm.getTestLocation());
	createEvent.setAdministeredBy(drugForm.getAdministeredBy());
	createEvent.setComments(drugForm.getComments());
	IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
	try {
	    dispatch.postEvent( createEvent ) ;
	    drugForm.setMsg(" The drug testing information is created successfully. ");
	} catch (Exception e ){
	    drugForm.setMsg("Failed to create the drug testing information - " + e.toString());
	    return aMapping.findForward("addSuccess");
	}
	
	return aMapping.findForward("finishSuccess");
    }
    
    public ActionForward validateJPO (ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	JuvenileDrugForm drugForm = (JuvenileDrugForm) aForm;
	
	if (drugForm.getAdministeredBy() != null && !drugForm.getAdministeredBy().equals("") && drugForm.getAdministeredBy().length() <= 3)
	{
	    drugForm.setAdministeredBy("UV" + drugForm.getAdministeredBy());
	}

	//Validate the jpo
	UserEntityBean userEBean = PDSecurityHelper.getSecurityUserProfileByJUCode(drugForm.getAdministeredBy());
	OfficerProfileResponseEvent officerProfile = UIUserFormHelper.getUserOfficerProfile(drugForm.getAdministeredBy());
	JSONObject validation = new JSONObject();
	if ( officerProfile == null 
		&& userEBean == null ){
	    validation.put("error","Juvenile Probation Officer is invalid. Please modify.");
	    validation.put("success","");
	    aResponse.getWriter().println(validation);
	   
	} else {
	    validation.put("success","Juvenile Probation Officer is valid.");
	    validation.put("error","");
	    aResponse.getWriter().println(validation);
	}
	return null;
    }
    
    public ActionForward backToList(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileDrugForm drugForm = (JuvenileDrugForm)aForm;
	drugForm.setAction(UIConstants.VIEW);
	
	return aMapping.findForward("backToList");
    }

    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.BACK);
    }
    
    
    protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.add", "addDrugTestingInfo");
		buttonMap.put("button.finish", "submit");
		buttonMap.put("button.validate", "validateJPO");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "back");
		buttonMap.put("button.backToList", "backToList");

		return;
	}

}
