/*
 * Created on Nov 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetCodetableRegistrationAttributesAndTypesEvent;
import messaging.codetable.GetCodetableRegistrationAttributesEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableRegistrationAttributesAndTypesResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableRegistrationForm;
import ui.common.CodeHelper;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCodetableRegistrationAttributeAction  extends JIMSBaseAction {
	
   /**
    * @roseuid 
    */
   public DisplayCodetableRegistrationAttributeAction() {
    
   }
   
   /**
	 * @return Map
	 * @roseuid 
	 */
   protected void addButtonMapping(Map keyMap) {
   		keyMap.put("button.update", "success");	 
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 
	 */
	public ActionForward success(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) {
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		if(cForm.getOpType().equalsIgnoreCase("update")|| cForm.getOpType().equalsIgnoreCase("view")) {		
			GetCodetableRegistrationAttributesEvent request =
				(GetCodetableRegistrationAttributesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODETABLEREGISTRATIONATTRIBUTES);
		
			request.setCodetableRegId(cForm.getCodetableRegId());
			request.setType(cForm.getCodetableType());	
			List codetableAttributes =
				   MessageUtil.postRequestListFilter(request, CodetableAttributeResponseEvent.class);
			if (codetableAttributes == null || codetableAttributes.isEmpty())	{
				cForm.setCodetableAttributes(new ArrayList());
				cForm.setCodetableAttributesOriginal(new ArrayList());
			} else {
				Collections.sort((ArrayList)codetableAttributes);
				cForm.setCodetableAttributes(codetableAttributes);
				Collection clone = (Collection) CodeHelper.replica(codetableAttributes);	            
				cForm.setCodetableAttributesOriginal(clone);
			}
		} else {
			cForm.setCodetableAttributes(new ArrayList());
			cForm.setCodetableAttributesOriginal(new ArrayList());
		}
		cForm.setSearchResultsCount("" + cForm.getCodetableAttributes().size());
		GetCodetableRegistrationAttributesAndTypesEvent request =
			(GetCodetableRegistrationAttributesAndTypesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODETABLEREGISTRATIONATTRIBUTESANDTYPES);
	
		request.setCodetableRegId(cForm.getCodetableRegId());
		CompositeResponse response=MessageUtil.postRequest(request);
		ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(response,
        		ErrorResponseEvent.class);
        if(errorEvt!=null){
        	cForm.setAddAttributeList(new ArrayList());
			cForm.setAddAttrDataTypeList(new ArrayList());
			cForm.setShowAddAttributes(false);
        	if(errorEvt.getMessage()!=null && errorEvt.getMessage().equalsIgnoreCase("No CallBacks Found.")) {
        		sendToErrorPage(aRequest,"error.codetable.nocallbacks");         		
        		return aMapping.findForward(UIConstants.FAILURE);
        	} else if(errorEvt.getMessage()!=null && errorEvt.getMessage().equalsIgnoreCase("There are no New Attributes to add.")) {
        		sendToErrorPage(aRequest,"error.codetable.noattributetoadd");      
        	}
        } else {
        	cForm.setShowAddAttributes(true);
        }
        cForm.resetForAttributeUpdate();
        List codetableAttributesAndTypes =
			   MessageUtil.compositeToList(response, CodetableRegistrationAttributesAndTypesResponseEvent.class);
		if (codetableAttributesAndTypes == null || codetableAttributesAndTypes.isEmpty())	{
			cForm.setAddAttributeList(new ArrayList());
			cForm.setAddAttrDataTypeList(new ArrayList());
		} else {
			List attrTypeList = new ArrayList();
			Iterator attListIte = codetableAttributesAndTypes.iterator();
			while(attListIte.hasNext()) {
				CodetableRegistrationAttributesAndTypesResponseEvent attrResponse = (CodetableRegistrationAttributesAndTypesResponseEvent)
																					attListIte.next();
				String dataType = attrResponse.getType();
				if(dataType!=null) {
					dataType = dataType.trim();
					if(!attrTypeList.contains(dataType)) {
						attrTypeList.add(dataType);
					}
				}
			}
			Collections.sort(attrTypeList);
			cForm.setAddAttributeList(codetableAttributesAndTypes);
			cForm.setAddAttrDataTypeList(attrTypeList);
		}		

//		cForm.setOpStatus("details"); 
		List codetableTypes = CodeHelper.getCodetableTypeCodes();		
		cForm.setCodetableTypeList(codetableTypes);	
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 
	 */
	public ActionForward reset(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		cForm.resetForUpdate();
		
		return aMapping.findForward(UIConstants.RESET_SUCCESS);
	}
	
}


