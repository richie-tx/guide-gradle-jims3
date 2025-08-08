package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyConstellationsEvent;
import messaging.juvenile.GetJuvenileInfoLightEvent;
import messaging.juvenile.reply.JuvenileCoreLightResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class deleteFamilyConstellationQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "success";
	String juvenileNum = regform.getJuvenileId();
	String clrChk = aRequest.getParameter("clr");
	List<FamilyConstellationListResponseEvent>familyConstellationList = new ArrayList<FamilyConstellationListResponseEvent>();
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y"))
	{
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}
	
	if ( juvenileNum != null 
		&& juvenileNum.length() > 0 ) {
	    GetFamilyConstellationsEvent getFamilyEvent = 
		(GetFamilyConstellationsEvent)EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
	    getFamilyEvent.setJuvenileNum(juvenileNum);

	    CompositeResponse response = MessageUtil.postRequest(getFamilyEvent);
	    
	    familyConstellationList= MessageUtil.compositeToList(response, FamilyConstellationListResponseEvent.class);
	    
	    
	    if ( familyConstellationList.size() > 0 ) {
		Collections.sort(familyConstellationList, Collections.reverseOrder(new Comparator<FamilyConstellationListResponseEvent>(){
		    @Override
			public int compare(FamilyConstellationListResponseEvent evt1, FamilyConstellationListResponseEvent evt2) {
				return Integer.valueOf(evt1.getFamilyNum()).compareTo(Integer.valueOf(evt2.getFamilyNum()));
			}
		}));
		regform.setFamilyConstellationResults(familyConstellationList);
	    } else {
		 regform.clearAllResults();
		 regform.setMsg("No Records found. Please retry search.");
		 return mapping.findForward("error");
	    }
	    
	    GetJuvenileInfoLightEvent req = (GetJuvenileInfoLightEvent) 
			EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEINFOLIGHT);
	    req.setJuvenileNum(juvenileNum);
	    JuvenileCoreLightResponseEvent juvCore = (JuvenileCoreLightResponseEvent) MessageUtil.postRequest(req, JuvenileCoreLightResponseEvent.class);
	    String juvenileName = juvCore.getLastName() + ", " + juvCore.getFirstName();
	    
	    if (juvCore != null ) {
		regform.setJuvenileName(juvenileName);
	    }
	   
	}
	
	
	return mapping.findForward(forward);
	
    }
}
