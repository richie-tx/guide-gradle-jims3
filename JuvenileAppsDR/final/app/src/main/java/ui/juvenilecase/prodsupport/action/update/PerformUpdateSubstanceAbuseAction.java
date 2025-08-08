package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.productionsupport.UpdateProductionSupportSubstanceAbuseInfoEvent;
import messaging.productionsupport.reply.ProductionSupportSubstanceAbuseInfoResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSReferral;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformUpdateSubstanceAbuseAction extends JIMSBaseAction
{
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.next","next");
	keyMap.put("button.updateRecord","update");
	keyMap.put("button.cancel","cancel");
	keyMap.put("button.back","back");
	
    }
    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm)aForm;
	if ( form.getSubstanceAbuseInfos() != null 
		&& form.getSubstanceAbuseInfos() .size() > 0  ) {
	    for (ProductionSupportSubstanceAbuseInfoResponseEvent info :  form.getSubstanceAbuseInfos() ) {
		if ( form.getSubstanceAbuseId().equals( info.getSubstanceAbuseId() ) ){
		    getCasefiles(info);
		    form.setSubstanceAbuseInfo(info);
		    form.setOriginalSubstanceAbuseInfo(info);
		}
	    }
	   
	}
	return aMapping.findForward("continue");
    }
    
    
    public ActionForward update(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
{
	ProdSupportForm form = (ProdSupportForm)aForm;
	UpdateProductionSupportSubstanceAbuseInfoEvent updateEvent = (UpdateProductionSupportSubstanceAbuseInfoEvent)
											EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTSUBSTANCEABUSEINFO);
	updateEvent.setSubstanceAbuse(form.getSubstanceAbuseInfo());
	
	if ( form.getSubstanceAbuseInfos() != null
		&& form.getSubstanceAbuseInfos().size() > 0  ) {
	    List<ProductionSupportSubstanceAbuseInfoResponseEvent>filteredSubstanceAbuseInfos = new ArrayList<>();
	    for ( ProductionSupportSubstanceAbuseInfoResponseEvent substanceAbuseInfo : form.getSubstanceAbuseInfos() ){
		if ( substanceAbuseInfo.getReferralNum().equals( form.getSubstanceAbuseInfo().getReferralNum()  ) ){
		    filteredSubstanceAbuseInfos.add( substanceAbuseInfo );
		}
	    }
	    
	    Collections.sort((List<ProductionSupportSubstanceAbuseInfoResponseEvent>)filteredSubstanceAbuseInfos,Collections.reverseOrder(new Comparator<ProductionSupportSubstanceAbuseInfoResponseEvent>() {
		@Override
	  	public int compare(ProductionSupportSubstanceAbuseInfoResponseEvent sbInfo1, ProductionSupportSubstanceAbuseInfoResponseEvent sbInfo2) {
		    return Integer.parseInt( sbInfo1.getSubstanceAbuseId() ) - Integer.parseInt( sbInfo2.getSubstanceAbuseId() );
	  	}
	    }));
	    ProductionSupportSubstanceAbuseInfoResponseEvent substanceAbuseInfo = filteredSubstanceAbuseInfos.get(0);
	    if ( substanceAbuseInfo.getSubstanceAbuseId().equals( form.getSubstanceAbuseInfo().getSubstanceAbuseId() ) ){
		updateEvent.setHighestOID( true );
	    } else {
		updateEvent.setHighestOID( false );
	    }
	} else {
	    updateEvent.setHighestOID( false );
	}
	MessageUtil.postRequest(updateEvent);
	
	return aMapping.findForward("success");
}
    
    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("back");
    }
    
    private void getCasefiles(ProductionSupportSubstanceAbuseInfoResponseEvent info){
   	Iterator casefileIter = JuvenileCasefile.findAll("juvenileId", info.getJuvenileId());
   	while( casefileIter.hasNext() ){
   	    JuvenileCasefile casefile = (JuvenileCasefile)casefileIter.next();
   	 info.getCasefileIds().add( casefile.getOID().trim() );
   	    
   	}
   	
   	Iterator referralIter = JJSReferral.findAll("juvenileNum", info.getJuvenileId());
   	while ( referralIter.hasNext() ) {
   	 JJSReferral referral = (JJSReferral)referralIter.next();
   	 info.getReferrals().add( referral.getReferralNum().trim());
   	}
   	
   	
       }

}
