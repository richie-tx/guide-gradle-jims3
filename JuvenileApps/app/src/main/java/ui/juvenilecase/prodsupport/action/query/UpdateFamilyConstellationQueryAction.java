package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetFamilyConstellationsEvent;

import messaging.juvenile.GetDrugTestingInfoEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileFamilyMemberViewResponseEvent;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.action.query.UpdateFamilyMemberQueryAction.Answer;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;

public class UpdateFamilyConstellationQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
	    				HttpServletRequest request, 
	    				HttpServletResponse response) throws Exception 
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "";
	 /** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}  

	String juvenileId = null;
	String familyMemberId = null; 
	String constellationQueryType = null;
	FamilyConstellation familyConstellation = null;	
	regform.setFamilyConstellation(null);
	regform.setMsg("");
	regform.setSearchedJuvenileId("");
	List<FamilyConstellation> constellationList = new ArrayList<FamilyConstellation>();
	
	if(regform != null && regform.getConstellationQueryType() != null && !"".equals(regform.getConstellationQueryType()))
	{
	    constellationQueryType = regform.getConstellationQueryType();
	}
	
	String listFlag = request.getParameter("list");
	
	    if(listFlag != null && listFlag.equalsIgnoreCase("Y"))
	    {	
		if(constellationQueryType.equalsIgnoreCase("familyMemberId"))
		{
		    familyMemberId = regform.getFamilyMemberId();
		    Iterator consIter = this.getConstellations(familyMemberId).iterator();
		     while(consIter != null && consIter.hasNext())
		     {
			FamilyConstellation constellation = (FamilyConstellation)consIter.next();
			if(constellation != null)
			{
			    constellationList.add(constellation);
			}
	     	    	
		     }
	    
		}
		else if(constellationQueryType.equalsIgnoreCase("juvenileId"))
		{
		    juvenileId = regform.getJuvenileId();
		    if(juvenileId != null && !"".equals(juvenileId))
		    {
	        	GetFamilyConstellationsEvent reqEvent = new GetFamilyConstellationsEvent();
	                reqEvent.setJuvenileNum(juvenileId);

	                Iterator<FamilyConstellation> listIter = FamilyConstellation.findAll(reqEvent);               	                   	
	                	    
	                while(listIter.hasNext())
	                {
	                    FamilyConstellation constellation = (FamilyConstellation)listIter.next();
	                    constellationList.add(constellation);
	                }                	 	                	
		    } 		    
		    
		}		
		
		if(constellationList.size() > 0 )
        	{
        	    Collections.sort(constellationList, new DateComparator());
        	    
        	    regform.setFamilyConstellationList(constellationList);
        	    regform.setSearchedJuvenileId(juvenileId);
            	    forward = "listsuccess";
        	}
        	else 
        	{
        	    regform.setMsg("No Constellation Record(s) Found.");
    	            forward = "error";
        	}
		
	    }
	
	if(listFlag != null && listFlag.equalsIgnoreCase("N"))
	{
	    String familyConstellationId =  regform.getFamilyConstellationId();
	    familyConstellation = (FamilyConstellation)FamilyConstellation.find(familyConstellationId);
	    
		if(familyConstellation != null)
	        {
	            if(familyConstellation.getEntryDate() != null)
	            {
	                familyConstellation.setEntryDateString(DateUtil.dateToString(familyConstellation.getEntryDate(), "MM/dd/yyyy"));
	            }
	            
	            List<Status> statusList = new ArrayList<Status>();
	            Status status1 = new Status();
	            status1.description = "True";
	            status1.code = true;
	            
	            Status status2 = new Status();
	            status2.description = "False";
	            status2.code = false;        
	            
	            statusList.add(status1);
	            statusList.add(status2);
	            
	            regform.setFamilyConstellation(familyConstellation);
	            regform.setStatusList(statusList); 
	            if(familyConstellation.getFamilyConstellationId() != null && !"".equals(familyConstellation.getFamilyConstellationId())){
	                regform.setFamilyConstellationId(familyConstellation.getFamilyConstellationId());
	            }
	            
	            regform.setMsg("");	            
	            forward = "success";
	            
	        } else {
	            
	            regform.setMsg("No Constellation Record Found.");
	            forward = "error";
	       }
	    
	}	                
        
	regform.setJuvenileId("");
	regform.setFamilyMemberId("");
	
	return mapping.findForward(forward);
	
    }
    
    public List<FamilyConstellation> getConstellations(String familyMemberId)
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
    

    
    public class Status {
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
    
    public class DateComparator implements Comparator<FamilyConstellation>{
	
	public int compare(FamilyConstellation f1, FamilyConstellation f2)
	{
	    if(f1.getEntryDate().equals(f2.getEntryDate()))
		return 0;
	    else if (f1.getEntryDate().after(f2.getEntryDate()))
		return 1;
	    else
		return -1;
	}
    }
    
}
