package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.productionsupport.GetJuvenileReferralOffensesQueryEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenile.JuvenileBuilder;
import pd.juvenilecase.casefile.PDCasefileDocumentsHelper;
import ui.common.Name;
import ui.juvenilecase.form.ProdSupportForm;

public class DeleteReferralOffenseQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {

        ProdSupportForm regform = (ProdSupportForm) form;
        String clrChk = request.getParameter("clr");
        regform.setMsg("");
        if (clrChk!=null && clrChk.equalsIgnoreCase("Y")) {
        	regform.clearAllResults();
        	regform.setMsg("");
        	return mapping.findForward("error");		
        }
        
        String juvenileNumber = regform.getJuvenileId().trim();
        String referralNumber = regform.getReferralId().trim();
        
        if ( juvenileNumber != null 
        	&& juvenileNumber.length() > 0 
        	&& referralNumber != null
        	&& referralNumber.length() > 0 ) {
            JJSJuvenileResponseEvent juvenileInfo = getJuvenileInfo( juvenileNumber );
            if ( juvenileInfo == null
        	    || juvenileInfo.getJuvenileNum() == null
        	    ||  juvenileInfo.getJuvenileNum().length() == 0 ) {
        	regform.setMsg("Juvenile Number is invalid.  Please retry search.");
                return mapping.findForward("error");	
            } else {
        	Name name = new Name(juvenileInfo.getFirstName(), juvenileInfo.getMiddleName(), juvenileInfo.getLastName());
        	regform.setJuvenileName(name.getFormattedName());
        	
        	String statusDesc = JuvenileBuilder.getMasterStatusDesc(juvenileInfo.getStatusId());
        	regform.setStatusId(statusDesc);
        	
        	ArrayList<JJSOffenseResponseEvent>referralOffenses = getReferralOffenses(juvenileNumber, referralNumber );
        	if ( referralOffenses != null
        		&& referralOffenses.size() > 0 ){
        	    regform.setReferralOffenses(referralOffenses);
        	} else {
        	    regform.setMsg("No matching record found.  Please retry search.");
                    return mapping.findForward("error");
        	}
            }
        } else {
            regform.setMsg("Juvenile Number is invalid or Referral Number is invalid.  Please retry search.");
            return mapping.findForward("error");	
        }
       
      
        	
        return mapping.findForward("success");
    }
    
    private JJSJuvenileResponseEvent getJuvenileInfo( String juvenileNumber ) {
	GetJJSJuvenileEvent getJuvenileInfoEvent = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	getJuvenileInfoEvent.setJuvenileNum(juvenileNumber);

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(getJuvenileInfoEvent, JJSJuvenileResponseEvent.class);

	return juvenileResp;
    }
    
    private ArrayList<JJSOffenseResponseEvent> getReferralOffenses(String juvenileNum, String referralNum)
    {
	ArrayList referralOffenses = null;	
	GetJuvenileReferralOffensesQueryEvent getOffensesEvent = (GetJuvenileReferralOffensesQueryEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJUVENILEREFERRALOFFENSESQUERY);
	getOffensesEvent.setJuvenileNum(juvenileNum);
	getOffensesEvent.setReferralNum(referralNum);
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest( getOffensesEvent );
	referralOffenses = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, JJSOffenseResponseEvent.class);
	
	return referralOffenses;
    }

}
