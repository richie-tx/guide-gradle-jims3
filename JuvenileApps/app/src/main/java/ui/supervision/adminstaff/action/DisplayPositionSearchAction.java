/*
 * Created on Mar 13, 2007
 *
 */
package ui.supervision.adminstaff.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.supervision.adminstaff.Position;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayPositionSearchAction extends JIMSBaseAction {

	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.link", "link");
	}
	
	public ActionForward link(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffSearchForm myForm=(AdminStaffSearchForm)aForm;
		myForm.clearAll();
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward submit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffSearchForm myForm=(AdminStaffSearchForm)aForm;
		String mapForward=UIConstants.SEARCH_SUCCESS;
		myForm.clearFoundPositions();
		
		// added to improve performance			
		List locationCodes = ComplexCodeTableHelper.getLocationCodes();
		
		ArrayList list=new ArrayList();
		myForm.setFoundPositions(list);
		GetCSCDSupervisionStaffEvent staffEvent = (GetCSCDSupervisionStaffEvent) EventFactory
		.getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);
		UIAdminStaffHelper.populateSupervisionStaffGetEvent(myForm,staffEvent);
		CompositeResponse myResp=postRequestEvent(staffEvent);
		Collection staffPositions = MessageUtil.compositeToCollection(myResp, CSCDSupervisionStaffResponseEvent.class);	
		if (staffPositions!=null && !staffPositions.isEmpty())
		{
			Iterator iter = staffPositions.iterator();
			while (iter.hasNext())
			{
				CSCDSupervisionStaffResponseEvent staffPosRespEvt = (CSCDSupervisionStaffResponseEvent)iter.next();
				Position positionBean = UIAdminStaffHelper.getPositionFromStaffRespEvt(staffPosRespEvt, locationCodes);
				list.add(positionBean);
			}
		}
		myForm.setSelectedValue("");
		if((myForm.getFoundPositions()!=null || myForm.getFoundPositions().size()==1) && myForm.getFoundPositions().size() != 0){
			Position myPos=(Position)myForm.getFoundPositions().get(0);
			myForm.setSelectedValue(myPos.getPositionId());
		}
		else if(myForm.getFoundPositions()==null || myForm.getFoundPositions().size()<1){
			sendToErrorPage(aRequest,"error.no.search.results.found");
	//		mapForward=UIConstants.FAILURE;
		}
		if (myForm.getFoundPositions()!=null){
			Collections.sort((ArrayList) myForm.getFoundPositions());
		}
		return aMapping.findForward(mapForward);
	}
	
	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffSearchForm myForm=(AdminStaffSearchForm)aForm;
		myForm.clearPosSearch();
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffSearchForm myForm=(AdminStaffSearchForm)aForm;
		myForm.clearAll();
		return mainPage(aMapping,aForm,aRequest,aResponse);
	}
	
}// END CLASS
