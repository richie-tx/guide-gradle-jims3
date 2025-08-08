//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionCreateTasksAction.java

package ui.supervision.SupervisionOptions.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.GenerateSupervisionConditionNameEvent;
import messaging.supervisionoptions.GetPoliciesToDisassociateEvent;
import messaging.supervisionoptions.reply.CourtPolicyResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyResponseEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import messaging.supervisionoptions.reply.SupervisionConditionNameResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import messaging.supervisionoptions.reply.VariableElementTypeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.TextUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class DisplaySupervisionConditionCreateTasksAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 42F7C4900261
    */
   public DisplaySupervisionConditionCreateTasksAction() 
   {
    
   }
   
   protected void addButtonMapping(Map buttonMap) {
	   
	buttonMap.put("button.back", "back");
		buttonMap.put("button.addMore", "addMore");
	   buttonMap.put("button.preview", "preview");
	   buttonMap.put("button.filter", "filter");
	   buttonMap.put("button.next", "next");
	   buttonMap.put("button.showAll", "showAll");
	   
   }
   
   public ActionForward back(
			   ActionMapping aMapping,
			   ActionForm aForm,
			   HttpServletRequest aRequest,
			   HttpServletResponse aResponse)
		{
   	SupervisionConditionForm form = (SupervisionConditionForm) aForm;
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
		}
   
   public ActionForward addMore(
			 ActionMapping aMapping,
			 ActionForm aForm,
			 HttpServletRequest aRequest,
			 HttpServletResponse aResponse)
	  {
		  SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		  form.getTasks().addTaskItem();
		  return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
	  }
   
   /**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
   public ActionForward preview(
		   ActionMapping aMapping,
		   ActionForm aForm,
		   HttpServletRequest aRequest,
		   HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		
		String conditionLiteral = form.getConditionLiteral();
		String conditionLiteralSample = conditionLiteral;
		
		Collection tokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "{{", "}}");
		Collection referenceTokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "\\[", "\\]");
		tokens.addAll(referenceTokens);
		
		form.setVariableElementNames(tokens);
		
		HashMap detailDictionaryNameIdMapping = 
			UISupervisionOptionHelper.createDetailDictionaryNameIdMapping(form.getDetailDictionary());
			
		form.setDetailDictionaryNameIdHashMap(detailDictionaryNameIdMapping);
		conditionLiteralSample = UISupervisionOptionHelper.createLiteralSample(conditionLiteral, tokens, detailDictionaryNameIdMapping);
		form.setConditionLiteralPreview(conditionLiteralSample);
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("preview",form.getAgencyId()));
	}
	
	/**
	   * @param aMapping
	   * @param aForm
	   * @param aRequest
	   * @param aResponse
	   * @return ActionForward
	   */
	  public ActionForward showAll(
			  ActionMapping aMapping,
			  ActionForm aForm,
			  HttpServletRequest aRequest,
			  HttpServletResponse aResponse) 
	   {
			SupervisionConditionForm form = (SupervisionConditionForm) aForm;
			
			Collection filterList = UISupervisionOptionHelper.filter(form.getDetailDictionary(), "*", "*", "*");
			if(filterList != null )
				form.setFilteredDetailDictionary(filterList);
			form.clearDetailDictionary();
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("filter",form.getAgencyId()));
	   }
	   
	public ActionForward filter(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	 {
		  SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		  String ddName = form.getDdName();
		  String ddDescription = form.getDdDescription();
		  String ddType = form.getDdType();
		  
		  
		  Collection filterList = UISupervisionOptionHelper.filter(form.getDetailDictionary(), ddName, ddDescription, ddType);
		  if(filterList != null )
			  form.setFilteredDetailDictionary(filterList);
			
	
		  return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("filter",form.getAgencyId()));
	 }
	/**
	   * @param aMapping
	   * @param aForm
	   * @param aRequest
	   * @param aResponse
	   * @return ActionForward
	   */
	  public ActionForward next(
			  ActionMapping aMapping,
			  ActionForm aForm,
			  HttpServletRequest aRequest,
			  HttpServletResponse aResponse)
	   {
			SupervisionConditionForm form = (SupervisionConditionForm) aForm;
			//get the group name from group Id
			setGroupName(form,form.getGroups());
			
			String conditionLiteral = form.getConditionLiteral();
			String testCondLit=UIUtil.removeXMLtags(conditionLiteral,true);
			if(!form.isSpecialCondition() && (testCondLit==null || testCondLit.trim().equals(""))){
				sendToErrorPage(aRequest,"error.requiredNonEditableMissing", "Literal");
				form.setConditionLiteral("");
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
			}
			if(testCondLit!=null && !testCondLit.trim().equals("")){
				if(testCondLit.length()<10){
					sendToErrorPage(aRequest,"errors.minlength","Literal","10");
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
				}
				else if(testCondLit.length()>1000){
					sendToErrorPage(aRequest,"errors.maxlength","Literal","1000");
				
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
				}
			}
			
			/*
			 * ER 24452 
			 * Add a second ConditionLiteral to the handle Spanish version that is used to inform the Supervisee or the conditions when 
			 * their only language is Spanish.  The Spanish version is not a legally recognized copy of the order.  Only the English version 
			 * of the Order is Filed.  The Spanish ConditionLiteral does not have variable fields but can contain ReferenceFields.  
			 * If Variable fields are inserted into the Spanish literal they will render as blank underscores.
			 */
			String spanConditionLiteral = form.getConditionSpanishLiteral();
			String testSpanConditionLit=UIUtil.removeXMLtags(spanConditionLiteral,true);
			
			if(testSpanConditionLit!=null && !testSpanConditionLit.trim().equals("")){
				if(testSpanConditionLit.length()<10){
					sendToErrorPage(aRequest,"errors.minlength","Spanish Literal","10");
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
					
				}
				else if(testSpanConditionLit.length()>1000){
					sendToErrorPage(aRequest,"errors.maxlength","Spanish Literal","1000");
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
				}
			}
			
			StringBuffer spanishConditionLiteral = new StringBuffer(form.getConditionSpanishLiteral());
			Collection spanishTokens = UISupervisionOptionHelper.tokenizeVariables(spanishConditionLiteral.toString(), "{{", "}}");
			Iterator spanishTokenIterator = spanishTokens.iterator();
			while(spanishTokenIterator.hasNext())
			{
				//String testString = spanishConditionLiteral.substring(spanishConditionLiteral.indexOf("{{"),spanishConditionLiteral.indexOf("}}")+2);
				TextUtil.replace(spanishConditionLiteral,"{{" + spanishTokenIterator.next()+ "}}","________", 0);
				
			}
					
			form.setConditionSpanishLiteral(spanishConditionLiteral.toString());
			
			//End ER 24452
			
			Collection tokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "{{", "}}");
			Collection referenceTokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "\\[", "\\]");
			
			//need to retain information that these referenceTokens are indeed, reference variables
			tokens.addAll(referenceTokens);
			form.setVariableElementNames(tokens);
			
			//this flow is for both create and update, for create veres will always be empty
			HashMap vereMap = new HashMap();
			Collection veres = form.getVariableElementResponseEvents();
			
			Collection updatedVeres = new ArrayList();
			
			if(veres != null)
			{
				//create a hashMap of veres
				Iterator veresIter = veres.iterator();
				while(veresIter.hasNext())
				{
					VariableElementResponseEvent vere = (VariableElementResponseEvent)veresIter.next();
					if(vere.getName() != null && vere.getName().length() > 0)
					{
						vereMap.put(vere.getName(), vere);
					} 
				}
			}
			
			if(tokens.size() == 0)
			{
				VariableElementResponseEvent vere = new VariableElementResponseEvent();
				vere.setName("");
				updatedVeres.add(vere);
					
			}
			else
			{
				
				//get the variableElementTypeResponseEvent from the map
				Collection vetres = form.getDetailDictionary();
				Map vetreMap = convertIntoMap(vetres);
				Iterator tokenIterator = tokens.iterator();
				while(tokenIterator.hasNext()) //create new vere if it doesn't exist
				{
					String varName = (String)tokenIterator.next();
					
					VariableElementResponseEvent vere = (VariableElementResponseEvent)vereMap.get(varName);
					//get the variableElementTypeResposneEvent from the map
					VariableElementTypeResponseEvent vereType = (VariableElementTypeResponseEvent)vetreMap.get(varName);
					if(vere == null) //the literals are stored as upper case
					{ //this is a new variable element.
						vere = new VariableElementResponseEvent();
						vere.setName(varName);
						
						//special condition only applies to 1 court, which is set during creation. 
						if(form.isSpecialCondition())
						{
							Collection selectedCourtIds = form.getSelectedCourtIds();
							if(selectedCourtIds != null && selectedCourtIds.size() > 0)
							{
								vere.setCourtId((String)selectedCourtIds.iterator().next());
							} 
						}
							
						if(vereType  == null)
						{
							ActionErrors errors = new ActionErrors();
							if(form.isSpecialCondition())
								errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.details.literal", "Invalid Variable Reference"));
							else{
								errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.condition.literal", "Invalid Variable Reference"));
							}
							saveErrors(aRequest, errors);
							return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));							
						}						
						if(vereType.isEnumration()){
							vere.setEnumeration(true);
							vere.setCodeTableName(vereType.getCodeTableName());
							// get enumerated values
							List codeValues = (List)CodeHelper.getCodes(vereType.getCodeTableName(),true);
							//  special sort and edit for DAYS_TO_ADD codes
							List temp = UICommonSupervisionHelper.sortDaysToAddCodes(codeValues, vereType.getCodeTableName());
							if (!temp.isEmpty()){
								codeValues = temp;
							}
							vere.setCodeValues(codeValues);
						}
						if(vereType.getIsReference())
						{
							vere.setReference(true);
							vere.setFixed(true);
						}
						vere.setValueType(vereType.getType());
						vere.setEnumerationTypeId(vereType.getValueType()); 
						updatedVeres.add(vere);
						
					} else {//updating
						if (vereType != null && vereType.isEnumration())
						{
							// get enumerated values
							vere.setCodeValues(CodeHelper.getCodes(vereType.getCodeTableName(),true));
						}
						updatedVeres.add(vere);
					}
				}
			}
			
			form.setVariableElementResponseEvents(updatedVeres);
				  
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			
			HashMap detailDictionaryNameIdMapping = UISupervisionOptionHelper.createDetailDictionaryNameIdMapping(form.getDetailDictionary());
			form.setDetailDictionaryNameIdHashMap(detailDictionaryNameIdMapping);

			// validate here if it is update, for name duplication
			// name is non editable for special condition 
			if(form.getAction().equals(UIConstants.UPDATE)){
				if(!form.isSpecialCondition())
				{
					CompositeResponse response = UISupervisionOptionHelper.validateCondition(form.getAgencyId(), form.getConditionName(), form.getConditionId());
		
					ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
					if (returnException != null)
					{
						return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
					}
					DuplicationNameErrorEvent duplicateException = (DuplicationNameErrorEvent) MessageUtil.filterComposite(response, DuplicationNameErrorEvent.class);
					if (duplicateException  != null)
					{
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.name.exist", "Duplicate Name"));
						saveErrors(aRequest, errors);
						return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
					}
				}
				if(form.isInUse()){
					boolean passedSupTypeTest=true;
					if(form.getOldSelSupervisionTypes() != null && form.getOldSelSupervisionTypes().length > 0)
					{
						if(form.getSelSupervisionTypes() == null || form.getSelSupervisionTypes().length == 0)
						{
							passedSupTypeTest=false;
						}
						else
						{
							String[] oldSelSupTypeIds = form.getOldSelSupervisionTypes();
							String[] selSupTypeIds = form.getSelSupervisionTypes();
							String oldSupTypeKey;
							String newSupTypeKey;
							boolean found=false;
							for(int i=0; i < oldSelSupTypeIds.length && passedSupTypeTest; i++)
							{
								found=false;
								oldSupTypeKey=oldSelSupTypeIds[i];
								
								for(int j=0; j < selSupTypeIds.length && !found; j++)
								{
									newSupTypeKey=selSupTypeIds[j];
									if(oldSupTypeKey!=null){
										if(oldSupTypeKey.equalsIgnoreCase(newSupTypeKey)){
											found=true;
											break;
										}
										else{}
									}
								}
								if(!found){
									passedSupTypeTest=false;
								}
							} // end for loop
						}// END ELSE LOOP 
					}// END IF LOOP
					if(!passedSupTypeTest){
						
						sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Supervision types cannot be removed for an in use condition, they can only be added. \n The existing types are: " + form.getOldDispSelSupTypes());
						return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
					}
				}// END IF FORM IN USE 
			}else{
				 
				//This should not happen, but just in case if javascript validation
				//broke, we can make sure group1Id and agencyId are valid.
				if(form.getGroup1Id() == null || form.getGroup1Id().length() < 1 ||
					form.getAgencyId() == null || form.getAgencyId().length() < 1)
				{
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.common", "Common Error"));
					saveErrors(aRequest, errors);
					return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
				}
				
				//post event to generate name
				if(form.getConditionName()!=null && !(form.getConditionName().equals(""))){
					// skip generating name
				}
				else{
					GenerateSupervisionConditionNameEvent event = new GenerateSupervisionConditionNameEvent();
					event.setGroupId(form.getGroup1Id()); 
					event.setAgencyId(form.getAgencyId());
					dispatch.postEvent(event);
					Collection names =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), SupervisionConditionNameResponseEvent.class );
					if(names != null){
						Iterator iter = names.iterator();
						SupervisionConditionNameResponseEvent nameRespEvt = (SupervisionConditionNameResponseEvent)iter.next();
						form.setConditionName(nameRespEvt.getName());
					}
				}
			}			
			//look up names from code
			if(!"".equals( form.getSeverityLevelId()) && form.getSeverityLevelId() != null )
			{
				String severityLevelDescription = (String) form.getSeverityLevelsMap().get(form.getSeverityLevelId());
				form.setSeverityLevel(severityLevelDescription);
			} else{
				//Default to medium if id is missing. RRY
				StringBuffer sb = new StringBuffer();
				sb.append("Medium Level Technical Violations");
				form.setSeverityLevel( sb.toString() );
				form.setSeverityLevelId( "129" );
			}
			
			if(form.getJurisdictionId() != null)
			{
				String jurisdiction = (String) form.getJurisdictionsMap().get(form.getJurisdictionId());
				form.setJurisdiction(jurisdiction);
			} 
			
			if(form.getSelDocumentIds() != null && form.getSelDocumentIds().length > 0)
			{
				String[] selDocumentIds = form.getSelDocumentIds();
				StringBuffer selDocuments = new StringBuffer();
				
				for(int i=0;i<selDocumentIds.length;i++)
				{
				    if(form.getDocumentsMap().containsKey(selDocumentIds[i])){
						selDocuments.append((String)form.getDocumentsMap().get(selDocumentIds[i]));
						if(i<selDocumentIds.length-1)
							selDocuments.append(", ");
				    }
				}
				
				
				form.setSelDocuments(selDocuments.toString());
			}
			
			if(form.getPeriodId() != null && !form.getPeriodId().equals(""))
			{
				String period = (String) form.getPeriodsMap().get(form.getPeriodId());
				form.setPeriod(period);
			}
			
			
//		this is to format data to be displayed.
				  if(form.getSelectedEventTypeIds() != null && form.getSelectedEventTypeIds().length > 0)
				  {
					  String[] selectedEventTypesId = form.getSelectedEventTypeIds();
					  StringBuffer selectedEventTypes = new StringBuffer();

					  for(int i=0;i<selectedEventTypesId.length;i++)
					  {
					      if(form.getEventTypesMap().containsKey(selectedEventTypesId[i])){
							  selectedEventTypes.append((String)form.getEventTypesMap().get(selectedEventTypesId[i]));
							  if(i<selectedEventTypesId.length-1)
								  selectedEventTypes.append(", ");
					      }
					  }
					  form.setSelectedEventTypes(selectedEventTypes.toString());
				
					  String eventCount = form.getEventCount();
				
					  if(eventCount != null && eventCount.trim().length() > 1)
					  {
						  if(Integer.parseInt(eventCount) > 2)
						  {
							  if(form.getPeriodId() != null)
							  {
								  String period = (String) form.getPeriodsMap().get(form.getPeriodId());
								  form.setPeriod(period);
							  }
						  }
						  else
						  {
							  form.setEventCount("Immediate");	
							  form.setPeriodId("");
							  form.setPeriod("");
						
						  }
					  }
				  }
				  else
				  {
					  //blank out Period Value, Period, and Event Count
					  form.setPeriodId("");
					  form.setPeriod("");
					  form.setEventCount("");
					  form.setSelectedEventTypes("");
				  }
			if(form.getSelSupervisionTypes() != null && form.getSelSupervisionTypes().length > 0)
			{
				String[] selSupTypeIds = form.getSelSupervisionTypes();
				StringBuffer selSupTypes = new StringBuffer();
					
				for(int i=0; i < selSupTypeIds.length; i++)
				{
				    if(form.getSupervisionTypesMap().containsKey(selSupTypeIds[i])){
						selSupTypes.append((String)form.getSupervisionTypesMap().get(selSupTypeIds[i]));
						if(i < selSupTypeIds.length - 1)
						selSupTypes.append(", ");
				    }
				}
				form.setDispSelSupTypes(selSupTypes.toString());
			}

			// get policies to be removed if Group1/Group2 have been changed
			// special condition cannot be associated
			if((UIConstants.UPDATE.equals(form.getAction()) || UIConstants.COPY.equals(form.getAction())) && !form.isSpecialCondition()){
				Collection removedAssociations = new ArrayList();
				GetPoliciesToDisassociateEvent asscEvent = new GetPoliciesToDisassociateEvent();
				String groupId = null;
				String group1Id = form.getGroup1Id();
				String group2Id = form.getGroup2Id();
				if(group2Id != null && !group2Id.equals("")){
					groupId = form.getGroup2Id();
				}else{
					groupId = form.getGroup1Id();
				}
				
				asscEvent.setGroupId(groupId);
				asscEvent.setConditionId(form.getConditionId());
				asscEvent.setCourtIds(form.getSelectedCourtIds());
				// post event
				dispatch.postEvent(asscEvent);
				
				// check for court Policies
				Collection cpres =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CourtPolicyResponseEvent.class );
				if(cpres != null){
					List oldCPAssociations = (List) form.getAssociatedCourtPolicies();
					Iterator cpresIter = cpres.iterator();
					while(cpresIter.hasNext())
					{
						CourtPolicyResponseEvent cpre = (CourtPolicyResponseEvent)cpresIter.next();
						AssociateBean ab = UISupervisionOptionHelper.createAsscBean(cpre);
						removedAssociations.add(ab);
						oldCPAssociations.remove(ab);
					}
					form.setAssociatedCourtPolicies(oldCPAssociations);
				}
				
				// check for department Policies
				Collection dpres =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), DepartmentPolicyResponseEvent.class );
				if(dpres != null){
					List oldDPAssociations = (List) form.getAssociatedDeptPolicies();
					Iterator dpresIter = dpres.iterator();
					while(dpresIter.hasNext())
					{
						DepartmentPolicyResponseEvent dpre = (DepartmentPolicyResponseEvent)dpresIter.next();
						AssociateBean ab = UISupervisionOptionHelper.createAsscBean(dpre);
						removedAssociations.add(ab);
						oldDPAssociations.remove(ab);
					}
					form.setAssociatedDeptPolicies(oldDPAssociations);
				}
				
				
					form.setRemovedAssociations(removedAssociations);
			}
			
			// make sure we don't pass condition id from here on for copy operation
			if(UIConstants.COPY.equals(form.getAction())){
				form.setConditionId(null);
			}
			if(UIConstants.UPDATE.equals(form.getAction()) && form.getAgencyId().equals(UIConstants.JUV)){
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("successCourts",form.getAgencyId()));
			}
			else{
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
			}
	   }	   
	
	
	
	
	
   private SupervisionConditionForm setGroupName(SupervisionConditionForm form, Collection groups)
   {
   	
	   //recursion breaking condition 
	   if(groups == null)
		   return null;
					
	   Iterator groupsIter = groups.iterator();
	
		if(form.getGroup1Id()==null || form.getGroup1Id().equalsIgnoreCase(""))
					form.setGroup1Name("");
			if(form.getGroup2Id()==null || form.getGroup2Id().equalsIgnoreCase(""))
							form.setGroup2Name("");
			if(form.getGroup3Id()==null || form.getGroup3Id().equalsIgnoreCase(""))
							form.setGroup3Name("");
	   while(groupsIter.hasNext())
	   {
			GroupResponseEvent eachGroup = (GroupResponseEvent) groupsIter.next();
			
			
		   if(form.getGroup1Id() != null && form.getGroup1Id().equals(eachGroup.getGroupId()))
		   {
			   form.setGroup1Name(eachGroup.getName());
		   }
		   if(form.getGroup2Id() != null && form.getGroup2Id().equals(eachGroup.getGroupId()))
		   {
			   form.setGroup2Name(eachGroup.getName());
		   }
		   if(form.getGroup3Id() != null && form.getGroup3Id().equals(eachGroup.getGroupId()))
		   {
			   form.setGroup3Name(eachGroup.getName());
		   }
		
		   //recursion here
			setGroupName(form, eachGroup.getSubgroups());
	
	   }
	   
	   return null;
   }
   
   private Map convertIntoMap(Collection veres){
   		Map vereMap = new HashMap();
   		if(veres != null && veres.size() > 0){
   			Iterator it = veres.iterator();
   			while(it.hasNext()){
				VariableElementTypeResponseEvent vetreEvent = (VariableElementTypeResponseEvent)it.next();
				vereMap.put(vetreEvent.getName(), vetreEvent);
   			}
   		}
   		return vereMap;
   }
}
