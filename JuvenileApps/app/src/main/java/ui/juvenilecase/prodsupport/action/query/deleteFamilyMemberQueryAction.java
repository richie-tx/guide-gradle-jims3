package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyConstellationMembersEvent;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.juvenile.GetJuvenileInfoLightEvent;
import messaging.juvenile.reply.JuvenileCoreLightResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;

import ui.juvenilecase.form.ProdSupportForm;

public class deleteFamilyMemberQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "success";
	String familyMemberId = regform.getFamilyMemberId();
	String clrChk = aRequest.getParameter("clr");
	List<FamilyConstellationListResponseEvent>familyConstellationList = new ArrayList<FamilyConstellationListResponseEvent>();
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}
	
	if ( familyMemberId != null && !"".equals(familyMemberId) ) {

	    String listFlag = aRequest.getParameter("list");
		
	    if(listFlag != null && listFlag.equalsIgnoreCase("Y"))
	    {
		Iterator iter = FamilyConstellationMember.findAll("theFamilyMemberId", familyMemberId);
		    List consRelations = new ArrayList<>();
	         	    
		    while(iter.hasNext())
		    {
	         	FamilyConstellationMember member = (FamilyConstellationMember)iter.next();
	         	consRelations.add(member);
		    }
		    
		    regform.setFamilyConstellationMemberList(consRelations);
		    //regform.setFamilyMemberId(familyMemberId);
		    forward = "listsuccess";
	    }	    
        	
	}	
	
	return mapping.findForward(forward);
	
    }
}
