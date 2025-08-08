package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.productionsupport.DeleteFamilyConstellationEvent;
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

public class performDeleteFamilyConstellationAction extends Action
{
    	private Logger log = Logger.getLogger("");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    
	    ProdSupportForm regform = (ProdSupportForm) form;
	    String forward = "success";
	    String constellationId = regform.getConstellationId();
	    String juvenileId = regform.getJuvenileId();
	    
	    ActionErrors errors = new ActionErrors();
	    
	    if ( constellationId != null 
		    && constellationId.length() > 0 ) {
		
		List<FamilyConstellation> consList = this.getJuvConstellations(constellationId, juvenileId);
		if(consList != null && consList.size() == 0)
		{
		    regform.setMsg("This record is the only active family constellation left for the juvenile, no deletion allowed.");
		    return mapping.findForward("error");
		}
		
		DeleteFamilyConstellationEvent deleteEvent = (DeleteFamilyConstellationEvent) 
		    						EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEFAMILYCONSTELLATION);
	    	deleteEvent.setConstellationId(constellationId);
	    	CompositeResponse resp = MessageUtil.postRequest(deleteEvent);
	    	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(resp, ErrorResponseEvent.class);
	    	if ( error != null ) {
	    	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
	    	    return mapping.findForward("error");
	    	}
	    }
	    
	    //update status of constellation with the highest OID to active if none exists - US 181038
	    boolean  activeConsExists = this.activeConstellationExist(juvenileId);	    
	    if(!activeConsExists)
	    {
		FamilyConstellation constellationHighestOID = this.getHighestOIDConstellation(juvenileId);
		
		if(constellationHighestOID != null && constellationHighestOID.getFamilyConstellationId() != null)
		{
		    FamilyConstellation familyConstellation = (FamilyConstellation)FamilyConstellation.find(constellationHighestOID.getFamilyConstellationId());
		    
		    familyConstellation.setActive(true);
		    
		    IHome home = new Home();
		    home.bind(familyConstellation);	
		    
		}		
	    }
    	    
	    return mapping.findForward(forward);
		
	}
	
	private List<FamilyConstellation> getJuvConstellations(String constellationId, String juvenileId)
	{
		List<FamilyConstellation> constellations = new ArrayList<FamilyConstellation>();
		List<FamilyConstellationListResponseEvent>familyConstellationList = new ArrayList<FamilyConstellationListResponseEvent>();
		
		GetFamilyConstellationsEvent reqEvent = new GetFamilyConstellationsEvent();
                reqEvent.setJuvenileNum(juvenileId);
		    
                Iterator<FamilyConstellation> iter =  FamilyConstellation.findAll(reqEvent);            	    
		while(iter.hasNext())
		{
		    FamilyConstellation familyConstellation = (FamilyConstellation)iter.next();
	     	
	     		if(familyConstellation != null && familyConstellation.getFamilyConstellationId() != null 
	     			&& !familyConstellation.getFamilyConstellationId().equalsIgnoreCase(constellationId))
	     		{   	    
	     		    constellations.add(familyConstellation);
	     		}
		}
		    
		return constellations;
	}
	
	private FamilyConstellation getHighestOIDConstellation(String juvenileId)
	{
		FamilyConstellation constellation = null;		
		List<FamilyConstellationListResponseEvent>familyConstellationList = new ArrayList<FamilyConstellationListResponseEvent>();
		
		GetFamilyConstellationsEvent reqEvent = new GetFamilyConstellationsEvent();
                reqEvent.setJuvenileNum(juvenileId);
		    
                Iterator<FamilyConstellation> iter =  FamilyConstellation.findAll(reqEvent);  
		int oid = 0;
		while(iter.hasNext())
		{
		    FamilyConstellation familyConstellation = (FamilyConstellation)iter.next();
	     	
	     		if(familyConstellation != null && familyConstellation.getFamilyConstellationId() != null 
	     			&& !"".equals(familyConstellation.getFamilyConstellationId()))
	     		{   	    
	     		    if(Integer.parseInt(familyConstellation.getFamilyConstellationId()) > oid)
	     		    {
	     			oid = Integer.parseInt(familyConstellation.getFamilyConstellationId());
	     			constellation = familyConstellation;
	     		    }
	     		    
	     		}
		}
		    
		return constellation;
	}
	
	private boolean activeConstellationExist(String juvenileId)
    	{
    	    boolean activeConstellationExists = false;
    	    
            	GetFamilyConstellationsEvent reqEvent = new GetFamilyConstellationsEvent();
        	reqEvent.setJuvenileNum(juvenileId);
        	Iterator<FamilyConstellation> listIter = FamilyConstellation.findAll(reqEvent);
        	    
        	List<FamilyConstellation> constellationList = new ArrayList<FamilyConstellation>();
        	
        	while(listIter.hasNext())
        	{
        	    FamilyConstellation constellation = (FamilyConstellation)listIter.next();
        	    
        	    if(constellation != null && constellation.getFamilyConstellationId() != null)
        	    {
        		if(constellation.isActive())
        		{
        		    return activeConstellationExists = true;        		    
        		}       	       		
        	    }
   
        	}
        	
        	return activeConstellationExists;
    	}

}
