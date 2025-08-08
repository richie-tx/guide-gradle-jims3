package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.address.reply.AddressResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.family.SaveFamilyConstellationEvent;
import messaging.family.SaveFamilyConstellationMemberInfoEvent;
import messaging.family.SaveFamilyMemberEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.MessageUtil;
import mojo.km.utilities.DateUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

    public class PerformUpdateFamilyMemberRelationAction extends Action {
    	private Logger log = Logger.getLogger("PerformUpdateFamilyMemberRelationAction");
    	
    	public ActionForward execute(ActionMapping mapping, ActionForm form,
    			HttpServletRequest request, HttpServletResponse response)
    			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String familyConstellationId = null;
		String familyMemberId = null;
		String juvenileId = null;
		
		if(regform.getFamilyConstellationMember() != null && regform.getFamilyConstellationMember().getFamilyConstellation() != null)
		{
		    familyConstellationId = regform.getFamilyConstellationMember().getFamilyConstellationId();
		}
		
		if(regform.getFamilyConstellationMember() != null && regform.getFamilyConstellationMember().getTheFamilyMemberId() != null)
		{
		    familyMemberId = regform.getFamilyConstellationMember().getTheFamilyMemberId();
		}
		
		if(familyConstellationId != null){
		  //get constellation info from JCConstellation
		    FamilyConstellation familyConstellation = (FamilyConstellation)FamilyConstellation.find(familyConstellationId);
		    
		    if(familyConstellation != null && familyConstellation.getJuvenileId() != null)
		    {
			juvenileId = familyConstellation.getJuvenileId();
		    }
		    
		}
		
		FamilyConstellationMember fcm = this.getConstellationMember(familyMemberId, familyConstellationId); //get the current record
		FamilyConstellationMember familyConstellationMember = (FamilyConstellationMember)regform.getFamilyConstellationMember(); //get updated record
		
		boolean isUpdated = false;
		if(fcm != null && familyConstellationMember != null)
		{
		    if((fcm.isInHomeStatus() != familyConstellationMember.isInHomeStatus()) 
			    	|| (fcm.isParentalRightsTerminated() != familyConstellationMember.isParentalRightsTerminated()) 
			    	|| (fcm.isPrimaryContact() != familyConstellationMember.isPrimaryContact()))
			{
			    isUpdated = true;
			}
		} else {
		    regform.setFamilyConstellationMember(familyConstellationMember);
		    regform.setFamilyConstellationId("");
		    regform.setFamilyMemberId("");
		        
		    regform.setMsg("Family Constellation object is null. Please check the logs or contact admin for more info.");
		    return mapping.findForward("error");
		}
		
		if(!isUpdated){
		    regform.setFamilyConstellationMember(familyConstellationMember);
		    regform.setFamilyConstellationId("");
		    regform.setFamilyMemberId("");
		        
		    regform.setMsg("No changes to the record");
		    return mapping.findForward("error");
		}			

		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		SaveFamilyConstellationEvent evt = new SaveFamilyConstellationEvent();
		evt.setJuvNum(juvenileId);
		evt.setConstellationNum(familyConstellationId);
		
		SaveFamilyConstellationMemberInfoEvent event = new SaveFamilyConstellationMemberInfoEvent();
		event.setConstellationMemberNum(familyConstellationMember.getOID());//consrelationId
		event.setMemberNum(familyMemberId);
		event.setRemoveMemberFromConstellation(false);
		event.setParentalRightsTerminated(familyConstellationMember.isParentalRightsTerminated());
		event.setInHomeStatus(familyConstellationMember.isInHomeStatus());
		event.setPrimaryContact(familyConstellationMember.isPrimaryContact());
		
		//update guardian to true if primary contact has been updated to true - US 186989
		if(familyConstellationMember.isPrimaryContact())
		{
		    event.setGuardian(true);
		    familyConstellationMember.setGuardian(true);
		}
		
		evt.addRequest(event);
		
		dispatch.postEvent( evt );

		// Get PD Response Event
		CompositeResponse resp = (CompositeResponse)dispatch.getReply();
		
		familyConstellationMember = this.getConstellationMember(familyMemberId, familyConstellationId); //get the latest record
		if(familyConstellationMember != null){
		    regform.setFamilyConstellationMember(familyConstellationMember);
		}		       
		regform.setMsg("");

		return mapping.findForward("success");
	}
	
    	public static boolean compareStrings(String str1, String str2) {
    	    return (str1 == null ? str2 == null : str1.equals(str2));
    	}
    	
    	public static boolean compareDates(Date date1, Date date2) {
    	    return (date1 == null ? date2 == null : date1.equals(date2));
    	}
    	
    	private FamilyConstellationMember getConstellationMember(String familyMemberId, String constellationId)
        {
    	    FamilyConstellationMember familyMember = null;
    	    
    	    if(familyMemberId != null && constellationId != null)
    	    {
    		Iterator iter = FamilyConstellationMember.findAll("theFamilyMemberId", familyMemberId);         	    
        	    while(iter.hasNext())
        	    {
                 	FamilyConstellationMember member = (FamilyConstellationMember)iter.next();
                 	
                 	if(member != null){
                 	    
                 	    if(member.getTheFamilyMemberId() != null && member.getTheFamilyMemberId().equals(familyMemberId)
                 		    && member.getFamilyConstellationId() != null && member.getFamilyConstellationId().equals(constellationId))
                 	    {
                 		familyMember = member;
                 		break;
                 	    }
                 	}
        	    }
    	    }
    	     	    
    	    return familyMember;
        }
}
