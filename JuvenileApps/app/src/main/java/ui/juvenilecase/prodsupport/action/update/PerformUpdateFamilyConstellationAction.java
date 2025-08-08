package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.address.reply.AddressResponseEvent;
import messaging.family.GetFamilyConstellationsEvent;
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyMember;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

    public class PerformUpdateFamilyConstellationAction extends Action {
    	private Logger log = Logger.getLogger("PerformUpdateFamilyConstellationAction");
    	
    	public ActionForward execute(ActionMapping mapping, ActionForm form,
    			HttpServletRequest request, HttpServletResponse response)
    			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		if(regform.getFamilyConstellation() != null && (regform.getFamilyConstellation().getFamilyConstellationId() == null 
			|| "".equals(regform.getFamilyConstellation().getFamilyConstellationId())))
		{
		    regform.setMsg("Cannot update family constellation record. The family constellation Id is null");
		    return mapping.findForward("error");
		}

		FamilyConstellation fc = (FamilyConstellation)FamilyConstellation.find(regform.getFamilyConstellation().getFamilyConstellationId());
		FamilyConstellation familyConstellation =  (FamilyConstellation)regform.getFamilyConstellation();
		
		if(multipleActiveConstellationsExist(familyConstellation.getJuvenileId(), familyConstellation.getFamilyConstellationId()))
		{
		    regform.setMsg("An active constellation already exist. Please update the status of existing constellation to false before choosing a new one. ");
		    return mapping.findForward("error");
		}
		
		if(familyConstellation != null && familyConstellation.getEntryDateString() != null && !"".equals(familyConstellation.getEntryDateString()))
		{
		    familyConstellation.setEntryDate((DateUtil.stringToDate(familyConstellation.getEntryDateString(),DateUtil.DATE_FMT_1)));
		}
		
		boolean isUpdated = false;
		if(fc != null && familyConstellation != null)
		{
		    if( !compareStrings(fc.getJuvenileId(), familyConstellation.getJuvenileId()) 
				|| !compareDates(fc.getEntryDate(), familyConstellation.getEntryDate())
				|| (fc.isActive() != familyConstellation.isActive()))
		    {
			 isUpdated = true;
		    }
		}			
				
		if(!isUpdated){
		    regform.setFamilyConstellation(familyConstellation);
		        
		    regform.setMsg("No changes to the record");
		    return mapping.findForward("error");
		}
		
		IHome home = new Home();
		home.bind(familyConstellation);	
			
		regform.setFamilyConstellation(familyConstellation);
		regform.setMsg("");
		return mapping.findForward("success");
	}
    	
    	
    	public boolean multipleActiveConstellationsExist(String juvenileId, String constellationId)
    	{
    	    boolean multipleExist = false;
    	    
            	GetFamilyConstellationsEvent reqEvent = new GetFamilyConstellationsEvent();
        	reqEvent.setJuvenileNum(juvenileId);
        	Iterator<FamilyConstellation> listIter = FamilyConstellation.findAll(reqEvent);
        	    
        	List<FamilyConstellation> constellationList = new ArrayList<FamilyConstellation>();
        	
        	int count = 0;
        	while(listIter.hasNext())
        	{
        	    FamilyConstellation constellation = (FamilyConstellation)listIter.next();
        	    
        	    if(constellation != null && constellation.getFamilyConstellationId() != null 
        		    && !"".equals(constellation.getFamilyConstellationId()) && constellationId != null && !"".equals(constellationId))
        	    {
        		if(!constellation.getFamilyConstellationId().equals(constellationId) && constellation.isActive())
        		{
        		    count = count + 1;        		    
        		}       	       		
        	    }
   
        	}
        	
        	if(count > 0)
		{
		    return multipleExist = true;        		    
		}
        	
        	return multipleExist;
    	}
	
    	public static boolean compareStrings(String str1, String str2) {
    	    return (str1 == null ? str2 == null : str1.equals(str2));
    	}
    	
    	public static boolean compareDates(Date date1, Date date2) {
    	    return (date1 == null ? date2 == null : date1.equals(date2));
    	}
    	
}
