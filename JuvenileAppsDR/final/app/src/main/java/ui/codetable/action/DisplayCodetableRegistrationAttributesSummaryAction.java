/*
 * Created on Nov 30, 2007
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import messaging.codetable.ValidateCompoundEntityEvent;
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


/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCodetableRegistrationAttributesSummaryAction extends JIMSBaseAction {
		
	   /**
	    * @roseuid 
	    */
	   public DisplayCodetableRegistrationAttributesSummaryAction() {
	    
	   }
	   
	   /**
		 * @return Map
		 * @roseuid 
		 */
	   protected void addButtonMapping(Map keyMap) {
	   		keyMap.put("button.addAttr", "addAttribute");
	   		keyMap.put("button.removeAttr", "removeAttribute");
	   		keyMap.put("button.updateAAttr", "displayUpdateAttribute");
	   		keyMap.put("button.next", "next");
	   		keyMap.put("button.updateAttr", "updateAttribute");
	   		keyMap.put("button.resequenceBack", "resequenceBack");
	   		keyMap.put("button.submit", "success");
		}
	   
		/**
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return ActionForward
		 * @roseuid 
		 */
		public ActionForward addAttribute(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
			CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;			
			
			if(cForm.isAttrCompound()) {
				ValidateCompoundEntityEvent request =
			
				(ValidateCompoundEntityEvent) EventFactory.getInstance(CodeTableControllerServiceNames.VALIDATECOMPOUNDENTITY);
				String compoundCodetableType = cForm.getAttrCodetableType();
				if (compoundCodetableType.equalsIgnoreCase("SL"))	{			
					request.setContextkey(cForm.getCompoundAttrContextKey());
				} else request.setEntityName(cForm.getCompoundAttrEntityName());
				request.setId(cForm.getCompoundAttrID());
				request.setName(cForm.getCompoundAttrName());
				CompositeResponse response=MessageUtil.postRequest(request);
				ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(response,
	        		ErrorResponseEvent.class);
				if(errorEvt!=null){
					if(errorEvt.getMessage()!=null) {
						sendToErrorPage(aRequest,errorEvt.getMessage()); 
						return aMapping.findForward(UIConstants.VALIDATE_FAILURE);
					} 
				} 
			}	        
	        CodetableRegistrationAttributesAndTypesResponseEvent selectedAttr = cForm.getAttributeTypeSelected();
	        cForm.getAddAttributeList().remove(selectedAttr);
	        Collection codetableAttributesAndTypes = cForm.getAddAttributeList();			
			List attrTypeList = new ArrayList();
			
			Iterator attListIte = codetableAttributesAndTypes.iterator();			
			CodetableRegistrationAttributesAndTypesResponseEvent attrResponse = null;
			while (attListIte.hasNext()) {
				attrResponse = (CodetableRegistrationAttributesAndTypesResponseEvent) attListIte
																						.next();
				String dataType = attrResponse.getType();
				if (dataType != null) {
					dataType = dataType.trim();
					if (!attrTypeList.contains(dataType)) {
						attrTypeList.add(dataType);
					}
				}
			}
			int currentSize = 0;
			if(cForm.getCodetableAttributes()!=null)
				currentSize = cForm.getCodetableAttributes().size();
			currentSize++;
			CodetableAttributeResponseEvent addAttribute = new CodetableAttributeResponseEvent();
			addAttribute.setDisplayName(cForm.getAttrDisplayName());
//			addAttribute.setDisplayOrder(selectedAttr.getDipslayOrder());
			addAttribute.setDisplayOrder(currentSize+"");
			addAttribute.setDbColumn(selectedAttr.getDbItemName());
			addAttribute.setType(selectedAttr.getType());
			addAttribute.setAudit(cForm.isAttrAudit());
			addAttribute.setRequired(cForm.isAttrRequired());
			addAttribute.setCompoundId(cForm.getCompoundAttrID());
			addAttribute.setCompoundName(cForm.getCompoundAttrName());
			addAttribute.setEntityName(cForm.getCompoundAttrEntityName());
			addAttribute.setContextKey(cForm.getCompoundAttrContextKey());
			addAttribute.setUpdatable(cForm.isAttrUpdatable());
			addAttribute.setCompundType(cForm.getAttrCodetableType());						
			addAttribute.setNumeric(cForm.isAttrNumeric());
			addAttribute.setMaxLength(cForm.getAttrMaxLength());
			addAttribute.setMinLength(cForm.getAttrMinLength());
			addAttribute.setUnique(cForm.isAttrUnique());
			addAttribute.setValidCheckRequired(cForm.isAttrVerification());
			
			cForm.getCodetableAttributes().add(addAttribute);
			cForm.resetForAttributeUpdate();
			Collections.sort((ArrayList)cForm.getCodetableAttributes());
			Collections.sort(attrTypeList);
			cForm.setAddAttrDataTypeList(attrTypeList);			
			cForm.setSearchResultsCount("" + cForm.getCodetableAttributes().size());
			if(attrTypeList.isEmpty()) cForm.setShowAddAttributes(false);
			else cForm.setShowAddAttributes(true);
			return aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
		}
		
		/**
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return ActionForward
		 * @roseuid 
		 */
		public ActionForward removeAttribute(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
			CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;			
			
			CodetableAttributeResponseEvent editAttr = cForm.getEditAttributeEvent();			
			try {
				Iterator originalListIter = cForm.getCodetableAttributes().iterator();	
				Map newSeqMap = new HashMap();
				while (originalListIter.hasNext()) {
					CodetableAttributeResponseEvent reseqAttr = (CodetableAttributeResponseEvent) originalListIter.next();						
					newSeqMap.put(reseqAttr.getDisplayOrder(), reseqAttr);
				}
				int removeOrder = Integer.parseInt(editAttr.getDisplayOrder());
				int seqSize = newSeqMap.keySet().size();
				for(int i=0;i<seqSize;i++) {
					if(i>=removeOrder) {
						CodetableAttributeResponseEvent reseqAttr = (CodetableAttributeResponseEvent)newSeqMap.get((i+1)+"");
						reseqAttr.setDisplayOrder(i+"");
					}
				}
			} catch (Exception e) {}
	        cForm.getCodetableAttributes().remove(editAttr);
	        CodetableRegistrationAttributesAndTypesResponseEvent addAttrResponse =  new CodetableRegistrationAttributesAndTypesResponseEvent();
	        addAttrResponse.setDbItemName(editAttr.getDbColumn());
	        addAttrResponse.setType(editAttr.getType());
	        addAttrResponse.setDipslayOrder(editAttr.getDisplayOrder() + "");
	        cForm.getAddAttributeList().add(addAttrResponse);
	        Collection codetableAttributesAndTypes = cForm.getAddAttributeList();			
			List attrTypeList = new ArrayList();
			Iterator attListIte = codetableAttributesAndTypes.iterator();
			CodetableRegistrationAttributesAndTypesResponseEvent attrResponse = null;
			while (attListIte.hasNext()) {
				attrResponse = (CodetableRegistrationAttributesAndTypesResponseEvent) attListIte
																						.next();
				String dataType = attrResponse.getType();
				if (dataType != null) {
					dataType = dataType.trim();
					if (!attrTypeList.contains(dataType)) {
						attrTypeList.add(dataType);
					}
				}
			}			
			cForm.resetForAttributeUpdate();
			Collections.sort((ArrayList)cForm.getCodetableAttributes());
			Collections.sort(attrTypeList);
			cForm.setAddAttrDataTypeList(attrTypeList);			
			cForm.setSearchResultsCount("" + cForm.getCodetableAttributes().size());
			if(attrTypeList.isEmpty()) cForm.setShowAddAttributes(false);
			else cForm.setShowAddAttributes(true);
			return aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
		}
		
		/**
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return ActionForward
		 * @roseuid 
		 */
		public ActionForward displayUpdateAttribute(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
			CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;			
			
			CodetableAttributeResponseEvent editAttr = cForm.getEditAttributeEvent();
	        cForm.getCodetableAttributes().remove(editAttr);
	        CodetableRegistrationAttributesAndTypesResponseEvent addAttrResponse =  new CodetableRegistrationAttributesAndTypesResponseEvent();
	        addAttrResponse.setDbItemName(editAttr.getDbColumn());
	        addAttrResponse.setType(editAttr.getType());
	        addAttrResponse.setDipslayOrder(editAttr.getDisplayOrder() + "");
	        cForm.getAddAttributeList().add(addAttrResponse);
	        Collection codetableAttributesAndTypes = cForm.getAddAttributeList();			
			List attrTypeList = new ArrayList();
			Iterator attListIte = codetableAttributesAndTypes.iterator();
			CodetableRegistrationAttributesAndTypesResponseEvent attrResponse = null;
			while (attListIte.hasNext()) {
				attrResponse = (CodetableRegistrationAttributesAndTypesResponseEvent) attListIte
																						.next();
				String dataType = attrResponse.getType();
				if (dataType != null) {
					dataType = dataType.trim();
					if (!attrTypeList.contains(dataType)) {
						attrTypeList.add(dataType);
					}
				}
			}			

			Collections.sort(attrTypeList);
			cForm.setAddAttrDataTypeList(attrTypeList);			
			cForm.setSearchResultsCount("" + cForm.getCodetableAttributes().size());
			
			cForm.setAttributeSelected(editAttr.getDbColumn());
			cForm.setAttrDisplayName(editAttr.getDisplayName());
			cForm.setAttributeDataType(editAttr.getType());
			cForm.setAttrRequired(editAttr.isRequired());
			cForm.setAttrAudit(editAttr.isAudit());
			cForm.setAttrUnique(editAttr.isUnique());
			cForm.setAttrUpdatable(editAttr.isUpdatable());
			cForm.setAttrVerification(editAttr.isValidCheckRequired());
			cForm.setAttrMinLength(editAttr.getMinLength());
			cForm.setAttrMaxLength(editAttr.getMaxLength());			
			cForm.setAttrCompound(editAttr.isCompound());
			cForm.setCompoundAttrID(editAttr.getCompoundId());
			cForm.setCompoundAttrName(editAttr.getCompoundName());
			cForm.setAttrCodetableType(editAttr.getCompundType());
			cForm.setCompoundAttrContextKey(editAttr.getContextKey());
			cForm.setCompoundAttrEntityName(editAttr.getEntityName());
			
			return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
		}
		
		/**
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return ActionForward
		 * @roseuid 
		 */
		public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
			CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;	
			
			if(cForm.isAttrCompound()) {
				ValidateCompoundEntityEvent request =
			
				(ValidateCompoundEntityEvent) EventFactory.getInstance(CodeTableControllerServiceNames.VALIDATECOMPOUNDENTITY);
				String compoundCodetableType = cForm.getAttrCodetableType();
				if (compoundCodetableType.equalsIgnoreCase("SL"))	{			
					request.setContextkey(cForm.getCompoundAttrContextKey());
				} else request.setEntityName(cForm.getCompoundAttrEntityName());
				request.setId(cForm.getCompoundAttrID());
				request.setName(cForm.getCompoundAttrName());
				CompositeResponse response=MessageUtil.postRequest(request);
				ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(response,
	        		ErrorResponseEvent.class);
				if(errorEvt!=null){
					if(errorEvt.getMessage()!=null) {
						sendToErrorPage(aRequest,errorEvt.getMessage()); 
						return aMapping.findForward(UIConstants.UPDATE_FAILURE);
					} 
				} else {
					cForm.setShowAddAttributes(true);
				}
			}
			
			CodetableAttributeResponseEvent editAttr = cForm.getEditAttributeEvent();			
			
	        CodetableRegistrationAttributesAndTypesResponseEvent selectedAttr = cForm.getAttributeTypeSelected();
	        cForm.getAddAttributeList().remove(selectedAttr);
	        Collection codetableAttributesAndTypes = cForm.getAddAttributeList();			
			List attrTypeList = new ArrayList();
			Iterator attListIte = codetableAttributesAndTypes.iterator();
			CodetableRegistrationAttributesAndTypesResponseEvent attrResponse = null;
			while (attListIte.hasNext()) {
				attrResponse = (CodetableRegistrationAttributesAndTypesResponseEvent) attListIte
																						.next();
				String dataType = attrResponse.getType();
				if (dataType != null) {
					dataType = dataType.trim();
					if (!attrTypeList.contains(dataType)) {
						attrTypeList.add(dataType);
					}
				}
			}
			
			Collections.sort(attrTypeList);
			cForm.setAddAttrDataTypeList(attrTypeList);			
			cForm.setSearchResultsCount("" + cForm.getCodetableAttributes().size());			
			
			editAttr.setDisplayName(cForm.getAttrDisplayName());
			editAttr.setDisplayOrder(selectedAttr.getDipslayOrder());
			editAttr.setDbColumn(selectedAttr.getDbItemName());
			editAttr.setType(selectedAttr.getType());
			editAttr.setAudit(cForm.isAttrAudit());
			editAttr.setRequired(cForm.isAttrRequired());
			if(cForm.isAttrCompound()) {
				editAttr.setCompound(true);
				editAttr.setCompoundId(cForm.getCompoundAttrID());
				editAttr.setCompoundName(cForm.getCompoundAttrName());
				editAttr.setEntityName(cForm.getCompoundAttrEntityName());
				editAttr.setContextKey(cForm.getCompoundAttrContextKey());
				editAttr.setCompundType(cForm.getAttrCodetableType());	
			} else {
				editAttr.setCompound(false);
				editAttr.setCompoundId(null);
				editAttr.setCompoundName(null);
				editAttr.setEntityName(null);
				editAttr.setContextKey(null);
				editAttr.setCompundType(null);	
			}
			editAttr.setUpdatable(cForm.isAttrUpdatable());								
			editAttr.setNumeric(cForm.isAttrNumeric());
			editAttr.setMaxLength(cForm.getAttrMaxLength());
			editAttr.setMinLength(cForm.getAttrMinLength());
			editAttr.setUnique(cForm.isAttrUnique());
			editAttr.setValidCheckRequired(cForm.isAttrVerification());
			
			cForm.resetForAttributeUpdate();
			
			cForm.getCodetableAttributes().add(editAttr);
			Collections.sort((ArrayList)cForm.getCodetableAttributes());
			
			return aMapping.findForward(UIConstants.VALIDATE_SUCCESS);		
		}
		
		/**
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return ActionForward
		 * @roseuid 
		 */
		public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
			CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;				
			cForm.resetForAttributeUpdate();			
			return aMapping.findForward(UIConstants.BACK);		
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
			cForm.resetForAttributeUpdate();			
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
		public ActionForward success(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
			CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;	
			cForm.setOpStatus("summary");
			if(cForm.getCodetableAttributes()!=null)
				cForm.setSearchResultsCount("" + cForm.getCodetableAttributes().size());
			else 
				cForm.setSearchResultsCount("0");
			int resCount = Integer.parseInt(cForm.getSearchResultsCount());
			if(resCount>0) {
				cForm.setShowAttributes(true);				
			}
			cForm.setShowResequenceButton(true);
			return aMapping.findForward(UIConstants.SUCCESS);		
		}
		
	}



