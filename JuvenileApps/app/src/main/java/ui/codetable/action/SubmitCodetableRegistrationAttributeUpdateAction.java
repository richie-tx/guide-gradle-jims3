/*
 * Created on Dec 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetCodetableRegistrationAttributesEvent;
import messaging.codetable.UpdateCodetableRegistrationAttributesEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
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
public class SubmitCodetableRegistrationAttributeUpdateAction extends JIMSBaseAction {
		
		   /**
		    * @roseuid 
		    */
		   public SubmitCodetableRegistrationAttributeUpdateAction() {
		    
		   }
		   
		   /**
			 * @return Map
			 * @roseuid 
			 */
		   protected void addButtonMapping(Map keyMap) {
		   		keyMap.put("button.resequence", "resequence");
		   		keyMap.put("button.resequenceBack", "resequenceBack");
		   		keyMap.put("button.finish", "finish");
			}
			
			
			/**
			 * @param aMapping
			 * @param aForm
			 * @param aRequest
			 * @param aResponse
			 * @return ActionForward
			 * @roseuid 
			 */
			public ActionForward resequence(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse) {
				CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;				
								
				return aMapping.findForward(UIConstants.RESEQUENCE_SUCCESS);		
			}
			/**
			 * @param aMapping
			 * @param aForm
			 * @param aRequest
			 * @param aResponse
			 * @return ActionForward
			 * @roseuid 
			 */
			public ActionForward resequenceBack(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse) {
				CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;				
				String finishPage = cForm.getAttrFinishPage();							
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
			public ActionForward finish(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse) {
				CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
				String finishPage = cForm.getAttrFinishPage();
				if(finishPage!=null && finishPage.trim().equalsIgnoreCase("resequence")) {
					String resequenceOrderValues = cForm.getResequencedOrderValue();
					StringTokenizer myTokenizer = new StringTokenizer(resequenceOrderValues, ",");
					List originalList = new ArrayList(cForm.getCodetableAttributes());
					Collections.sort(originalList);
					int seq = 0;
					while(myTokenizer.hasMoreTokens()) {
						seq++;
						String myNewSequenceNum = myTokenizer.nextToken();
						CodetableAttributeResponseEvent reseqAttr = (CodetableAttributeResponseEvent)originalList.get(Integer.parseInt(myNewSequenceNum)-1);
						reseqAttr.setDisplayOrder(seq+"");						
					}
				}
				UpdateCodetableRegistrationAttributesEvent request =				
					(UpdateCodetableRegistrationAttributesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.UPDATECODETABLEREGISTRATIONATTRIBUTES);
				Collection changedAttributes = cForm.getCodetableAttributes();
				Map changedAttrMap = new HashMap();
				Iterator changedAttrIte = changedAttributes.iterator();

				while(changedAttrIte.hasNext()) {
					CodetableAttributeResponseEvent changedAttr = (CodetableAttributeResponseEvent)changedAttrIte.next();
					if(changedAttr!=null && changedAttr.getCodetableRegAttributeId()!=null) {
						Integer changedAttrId = new Integer(changedAttr.getCodetableRegAttributeId());
						changedAttrMap.put(changedAttrId, changedAttr);	
					}
				}
				List removeAttributes = new ArrayList();
				Collection originalAttributes = cForm.getCodetableAttributesOriginal();
				Iterator originalAttrIte = originalAttributes.iterator();
				CodetableAttributeResponseEvent originalAttr = null;
				Integer originalAttrId = null;
				while(originalAttrIte.hasNext()) {
					originalAttr = (CodetableAttributeResponseEvent)originalAttrIte.next();
					if(originalAttr!=null && originalAttr.getCodetableRegAttributeId()!=null) {
						try {
							originalAttrId = new Integer(originalAttr.getCodetableRegAttributeId());
							CodetableAttributeResponseEvent removeAttr  = (CodetableAttributeResponseEvent) changedAttrMap.get(originalAttrId);	
							if(removeAttr==null) removeAttributes.add(originalAttr);
						} catch (Exception ex) { }
					}
				}
				request.setCodeRegId(cForm.getCodetableRegId());
				request.setUpdateAttributes(new ArrayList(changedAttributes));
				request.setRemoveAttributes(removeAttributes);
				CompositeResponse response=MessageUtil.postRequest(request);
				ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(response,
		        ErrorResponseEvent.class);
				if(errorEvt!=null){
					if(errorEvt.getMessage()!=null) {
						sendToErrorPage(aRequest,errorEvt.getMessage()); 
						return aMapping.findForward(UIConstants.FAILURE);
					} 
				}
				
				GetCodetableRegistrationAttributesEvent getRequest =
					(GetCodetableRegistrationAttributesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODETABLEREGISTRATIONATTRIBUTES);
			
				getRequest.setCodetableRegId(cForm.getCodetableRegId());
				getRequest.setType(cForm.getCodetableType());	
				List codetableAttributes =
					   MessageUtil.postRequestListFilter(getRequest, CodetableAttributeResponseEvent.class);
				if (codetableAttributes == null || codetableAttributes.isEmpty())	{
					cForm.setCodetableAttributes(new ArrayList());
					cForm.setCodetableAttributesOriginal(new ArrayList());
				} else {
					Collections.sort((ArrayList)codetableAttributes);
					cForm.setCodetableAttributes(codetableAttributes);
					Collection clone = (Collection) CodeHelper.replica(codetableAttributes);	            
					cForm.setCodetableAttributesOriginal(clone);
				}
		        
				cForm.setOpStatus("confirm");	
				cForm.setOpType("update");
				cForm.setShowAttributes(true);
				cForm.setAttributesUpdated(true);
				return aMapping.findForward(UIConstants.SUCCESS);
			}
			
		}

