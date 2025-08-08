package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.security.PDSecurityHelper;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.contact.UIContactHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileDrugForm;

public class DisplayJuvenileDrugTestingCreateAction extends Action
{
    public DisplayJuvenileDrugTestingCreateAction (){}
    
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
	JuvenileDrugForm drugForm = (JuvenileDrugForm) aForm;
	String clrChk = aRequest.getParameter("clr");
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    drugForm.setDrugTestAdmins(null);
	    drugForm.setDrugTestResults(null);
	    drugForm.setDrugTypeCodes(null);
	    drugForm.setAdministeredBy(null);
	    drugForm.setLocationCodes(null);
	}
          
	if ( drugForm.getDrugTestAdmins() == null
		|| drugForm.getDrugTestAdmins().size() == 0 ){
	    drugForm.setDrugTestAdmins(CodeHelper.getDrugTestAdministered());
	}
	
	if ( drugForm.getDrugTestResults() == null
		|| drugForm.getDrugTestResults().size() == 0  ){
	    drugForm.setDrugTestResults(ComplexCodeTableHelper.getDrugTestResultCodes());
	}
	
	if ( drugForm.getDrugTypeCodes() == null
		|| drugForm.getDrugTypeCodes().size() == 0 ){
	    drugForm.setDrugTypeCodes(ComplexCodeTableHelper.getDrugTypeCodes());
	}
        if ( drugForm.getAdministeredBy() == null
        	|| drugForm.getAdministeredBy().length() == 0 ) {
            drugForm.setAdministeredBy(PDSecurityHelper.getLogonId());
        }
        
        if ( drugForm.getLocationCodes() == null
        	|| drugForm.getLocationCodes().size() == 0 ){
            List<LocationResponseEvent>locationUnits = UIContactHelper.getLocationUnits();
            if ( locationUnits != null
        	    && locationUnits.size() > 0 ){
        	List<LocationResponseEvent>tempLocationUnits = new ArrayList<>();
                for ( LocationResponseEvent location : locationUnits ) {
                    if ( 1 == location.getDrugFlag() ) {
                	tempLocationUnits.add(location);
                    }
                }
                
                Collections.sort(tempLocationUnits, new Comparator<LocationResponseEvent>() {
                    @Override
                    public int compare(LocationResponseEvent a, LocationResponseEvent b) {
                        return a.getLocationUnitName().compareTo(b.getLocationUnitName());
                    }
                });
                
                drugForm.setLocationCodes(tempLocationUnits);
                
            }
        }
        
      
        return aMapping.findForward("success");
    }
    
    
   
}
