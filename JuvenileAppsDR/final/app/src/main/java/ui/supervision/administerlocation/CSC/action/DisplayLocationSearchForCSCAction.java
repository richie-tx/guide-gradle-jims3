/*
 * Created on Dec 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerlocation.CSC.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.GetServiceProviderFromDepartmentIdEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.codetable.GetServiceTypeCdEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.supervision.administerlocation.form.LocationForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayLocationSearchForCSCAction extends Action
{

	public DisplayLocationSearchForCSCAction() 
	   {
	    
	   }
	   
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    * @roseuid 45104B420018
	    */
	   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
	   		LocationForm locationForm = (LocationForm)aForm;
	   		
	   		locationForm.clear();
	   		locationForm.clearAddress();
	   		locationForm.setSecondaryAction("");
	   		Collection locationRegionList = CodeHelper.getCodes(PDCodeTableConstants.LOCATION_REGION, true);	
			locationForm.setLocationRegionList(locationRegionList);
			
			Collection locationStatusList = CodeHelper.getCodes(PDCodeTableConstants.LOCATION_STATUS, true);
		   	locationForm.setLocationStatusList(locationStatusList);
		   	
			Collection stateList = CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR,true);
			locationForm.setStateList(stateList);
			
		   	return aMapping.findForward(UIConstants.SUCCESS);
					   		
	   }   
}
