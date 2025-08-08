package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import ui.juvenilecase.prodsupport.action.query.UpdateFamilyConstellationQueryAction.DateComparator;
import ui.juvenilecase.prodsupport.action.query.UpdateFamilyMemberQueryAction.Answer;

public class UpdateFamilyMemberRelationQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "success";
	String familyMemberId = null;
	String constellationId = null;
	String constellationQueryType = null;
	String clrChk = aRequest.getParameter("clr");
	List<FamilyConstellationListResponseEvent>familyConstellationList = new ArrayList<FamilyConstellationListResponseEvent>();
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}
	
	if(regform != null && regform.getConstellationQueryType() != null && !"".equals(regform.getConstellationQueryType()))
	{
	    constellationQueryType = regform.getConstellationQueryType();
	}
	
	String listFlag = aRequest.getParameter("list");
	
	if(listFlag != null && !"".equals(listFlag) && listFlag.equalsIgnoreCase("Y"))
	{
	    if(constellationQueryType != null && constellationQueryType.equalsIgnoreCase("familyMemberId"))
	    {
		    familyMemberId = regform.getFamilyMemberId();
		    
		    if ( familyMemberId != null && !"".equals(familyMemberId) ) 
		    {			
			Iterator iter = FamilyConstellationMember.findAll("theFamilyMemberId", familyMemberId);
			List consRelations = new ArrayList();
			         	    
			while(iter.hasNext())
			{
			    FamilyConstellationMember member = (FamilyConstellationMember)iter.next();
			    consRelations.add(member);
			}
			
			Collections.sort(consRelations, new DateComparator());
			
			regform.setFamilyConstellationMemberList(consRelations);
			regform.setFamilyMemberId(familyMemberId);
			regform.setFamilyConstellationId("");
			regform.setMsg("");
			
			forward = "listsuccess";    
		        	
		    }	
		    
		}
		
		if(constellationQueryType != null && constellationQueryType.equalsIgnoreCase("familyConstellationId"))
		{
		    constellationId = regform.getFamilyConstellationId();
		    
		    if ( constellationId != null && !"".equals(constellationId) ) 
		    {					
			Iterator iter = FamilyConstellationMember.findAll("familyConstellationId", constellationId);
			List consRelations = new ArrayList();
			         	    
			while(iter.hasNext())
			{
			    FamilyConstellationMember member = (FamilyConstellationMember)iter.next();
			    consRelations.add(member);
			}
			
			Collections.sort(consRelations, new DateComparator());
			
			regform.setFamilyConstellationMemberList(consRelations);
			regform.setFamilyConstellationId(constellationId);
			regform.setFamilyMemberId("");
			regform.setMsg("");

			forward = "listsuccess";    
		        	
		    }	
		    
		}
	    
	}
	
	
	//single item
	if(listFlag != null && !"".equals(listFlag) && listFlag.equalsIgnoreCase("N"))
	{
	    familyMemberId = regform.getFamilyMemberId();
	    constellationId = regform.getFamilyConstellationId();
	    FamilyConstellationMember member = null;
	    
	    if(familyMemberId != null && !"".equals(familyMemberId) && constellationId != null && !"".equals(constellationId))
	    {
		member = this.getConstellationMember(familyMemberId, constellationId);
		
		if(member != null){
		    
		    regform.setFamilyConstellationMember(member);
		    List yesnoList = new ArrayList();
		    BooleanResponse resp1 = new BooleanResponse();
		    resp1.description = "YES";
		    resp1.code = true;
		        
		    BooleanResponse resp2 = new BooleanResponse();
		    resp2.description = "NO";
		    resp2.code = false;        
		        
		    yesnoList.add(resp1);
		    yesnoList.add(resp2);
		    regform.setBooleanList(yesnoList);
		    regform.setMsg("");
		    
		    forward = "success"; 
		}
	    }
	    
	}
	
		
	return mapping.findForward(forward);
	
    }
    
    public class BooleanResponse {
	    private boolean code;
	    private String description;
	    
	    public boolean getCode()
	    {
		return this.code;
	    }
	    
	    public void setCode(boolean code)
	    {
		this.code = code;
	    }
	    
	    public String getDescription()
	    {
		return this.description;
	    }
	    
	    public void setDescription(String description)
	    {
		this.description = description;
	    }
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
    
    private class DateComparator implements Comparator<FamilyConstellationMember>{
	
	public int compare(FamilyConstellationMember f1, FamilyConstellationMember f2)
	{
	    if(f1 != null && f1.getCreateTimestamp() != null && f2 != null && f2.getCreateTimestamp() != null)
	    {
		if(f2.getCreateTimestamp().equals(f1.getCreateTimestamp()))
		    return 0;
		else if (f2.getCreateTimestamp().after(f1.getCreateTimestamp()))
		    return 1;
		else
		    return -1;
	    }
	    
	    return 0;
	    
	}
    }
    
    
}
