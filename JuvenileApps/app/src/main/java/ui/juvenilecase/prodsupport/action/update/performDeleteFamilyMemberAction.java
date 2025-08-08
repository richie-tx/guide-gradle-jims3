package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.family.SaveFamilyConstellationEvent;
import messaging.family.SaveFamilyConstellationMemberInfoEvent;
import messaging.productionsupport.DeleteFamilyConstellationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
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

import ui.juvenilecase.form.ProdSupportForm;

public class performDeleteFamilyMemberAction extends Action
{
    	private Logger log = Logger.getLogger("");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    
	    ProdSupportForm regform = (ProdSupportForm) form;
	    String forward = "success";
	    String constellationId = regform.getFamilyConstellationId();
	    String familyMemberId = regform.getFamilyMemberId();
	    ActionErrors errors = new ActionErrors();	    
	    
	    if (constellationId != null && !"".equals(constellationId)) {
		
		FamilyConstellationMember member = this.getMemberConstellation(familyMemberId, constellationId);   	    
		if(member != null && member.getTheFamilyMemberId() != null && member.getTheFamilyMemberId().equals(familyMemberId))
	         {
		    if(member.isGuardian())
		    {
	         	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("The FAMMEMBER ID is designated as GUARDIAN in the JCCONSTELLTION, no deletion allowed"));
			regform.setMsg("The FAMMEMBER ID is designated as GUARDIAN in the JCCONSTELLTION, no deletion allowed");
			return mapping.findForward("error");
		    }		    

		    //get constellation info from JCConstellation
		    FamilyConstellation familyConstellation = (FamilyConstellation)FamilyConstellation.find(constellationId);

		    if(familyConstellation != null && familyConstellation.getJuvenileId() != null && !"".equals(familyConstellation.getJuvenileId()))
		    {
			List<FamilyConstellationMember> memberList = this.getOtherConstellationMembers(constellationId, familyMemberId);
			if(memberList != null && memberList.size() == 0)
			{
			    regform.setMsg("The FAMMEMBER_ID is the only family member in the JCCONSTELLTION, no deletion allowed.");
			    return mapping.findForward("error");
			}
			    
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
			SaveFamilyConstellationEvent evt = (SaveFamilyConstellationEvent)
				EventFactory.getInstance( JuvenileFamilyControllerServiceNames.SAVEFAMILYCONSTELLATION );
        		evt.setJuvNum( familyConstellation.getJuvenileId() );
        		evt.setConstellationNum(constellationId);
        		
        		SaveFamilyConstellationMemberInfoEvent event = new SaveFamilyConstellationMemberInfoEvent();
        		event.setConstellationMemberNum(member.getOID());//consrelationId
        		event.setRemoveMemberFromConstellation(true);
        		
        		evt.addRequest(event);
       		
        		dispatch.postEvent( evt );

			// Get PD Response Event
			CompositeResponse resp = (CompositeResponse)dispatch.getReply();

			MessageUtil.processReturnException(resp); // Perform Error handling
			ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(resp, ErrorResponseEvent.class);
                         
	                        if ( error != null ) {
	                            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
	                            return mapping.findForward("error");
	                        } 
	                        else 
	                        { 	
	                            regform.setFamilyConstellationMember(member); 
	                            regform.setFamilyMemberId(familyMemberId);
	                            regform.setMsg("");	    	    
	                        }		    
		    }                       
		    
	         }		    		
	    }
	    
	    
	    return mapping.findForward(forward);
		
	}
	
	
	public FamilyConstellationMember getMemberConstellation(String familyMemberId, String constellationId)
	{
	    FamilyConstellationMember familyMember = null;
	    
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
	    
	    return familyMember;
	}
	
	public List<FamilyConstellationMember> getOtherConstellationMembers(String constellationId, String familyMemberId)
	{	
	    List<FamilyConstellationMember> consMembers = new ArrayList<FamilyConstellationMember>();
	    FamilyConstellation familyConstellation = (FamilyConstellation)FamilyConstellation.find(constellationId);	    
	    
	    if(familyConstellation != null)
	    {
		Collection familyMembers = familyConstellation.getFamilyConstellationMembers();

		Iterator iter = familyMembers.iterator();
	   
		while(iter.hasNext())
		{
		    FamilyConstellationMember member = (FamilyConstellationMember)iter.next();
		    if(member != null && !member.getTheFamilyMemberId().equals(familyMemberId))
		    {
	         	consMembers.add(member);
		    }         	
		}
	    }	    
	    
	    return consMembers;
	}
	
	public List<FamilyConstellation> getConstellations(String constellationId, String juvenileId, String familyMemberId)
	{
		List<FamilyConstellation> constellations = new ArrayList<FamilyConstellation>();
		    
		Iterator iter = FamilyConstellationMember.findAll("theFamilyMemberId", familyMemberId);         	    
		while(iter.hasNext())
		{
	     		FamilyConstellationMember member = (FamilyConstellationMember)iter.next();
	     	
	     		if(member != null && member.getFamilyConstellationId() != null && !"".equals(member.getFamilyConstellationId()))
	     		{   	    
	     		    constellations.add(member.getFamilyConstellation());
	     		}
		}
		    
		return constellations;
	}

}
