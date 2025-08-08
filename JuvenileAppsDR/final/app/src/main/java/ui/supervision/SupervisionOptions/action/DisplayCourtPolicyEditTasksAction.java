//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicyEditTasksAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.GenerateCourtPolicyNameEvent;
import messaging.supervisionoptions.GetConditionsToDisassociateFromCourtPoliciesEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionoptions.reply.CourtPolicyNameResponseEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import messaging.supervisionoptions.reply.VariableElementTypeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;

public class DisplayCourtPolicyEditTasksAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 42F7C481009C
    */
   public DisplayCourtPolicyEditTasksAction() 
   {
    
   }
   
   protected void addButtonMapping(Map buttonMap) {
		
	buttonMap.put("button.back", "back");
		buttonMap.put("button.preview", "preview");
		buttonMap.put("button.filter", "filter");
		buttonMap.put("button.next", "next");
		buttonMap.put("button.showAll", "showAll");

		
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
			CourtPolicyForm form = (CourtPolicyForm) aForm;
			
			Collection filterList = UISupervisionOptionHelper.filter(form.getDetailDictionary(), "*", "*","*");
			if(filterList != null )
				form.setFilteredDetailDictionary(filterList);
			form.clearDetailDictionary();
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("filter",form.getAgencyId()));
	   }
	   
	public ActionForward back(
				  ActionMapping aMapping,
				  ActionForm aForm,
				  HttpServletRequest aRequest,
				  HttpServletResponse aResponse)
		   {
			
			CourtPolicyForm form=(CourtPolicyForm)aForm;
			   return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
		   }
	   
	public ActionForward filter(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	 {
		  CourtPolicyForm form = (CourtPolicyForm) aForm;
		  Collection filterList = UISupervisionOptionHelper.filter(form.getDetailDictionary(), form.getDdName(), form.getDdDescription(),form.getDdType());
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
   public ActionForward preview(
		   ActionMapping aMapping,
		   ActionForm aForm,
		   HttpServletRequest aRequest,
		   HttpServletResponse aResponse)
	{
		CourtPolicyForm form = (CourtPolicyForm) aForm;
	
	
		String courtPolicyLiteral = form.getCourtPolicyLiteral();
		String courtPolicyLiteralSample = courtPolicyLiteral;
	
		Collection tokens = UISupervisionOptionHelper.tokenizeVariables(courtPolicyLiteral, "{{", "}}");
		Collection referenceTokens = UISupervisionOptionHelper.tokenizeVariables(courtPolicyLiteral, "\\[", "\\]");
		tokens.addAll(referenceTokens);
	
		form.setVariableElementNames(tokens);
	
		HashMap detailDictionaryNameIdMapping = 
			UISupervisionOptionHelper.createDetailDictionaryNameIdMapping(form.getDetailDictionary());
		
		form.setDetailDictionaryNameIdHashMap(detailDictionaryNameIdMapping);
		
		courtPolicyLiteralSample = UISupervisionOptionHelper.createLiteralSample(courtPolicyLiteral, tokens, detailDictionaryNameIdMapping);
		form.setCourtPolicyLiteralPreview(courtPolicyLiteralSample);

	
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("preview",form.getAgencyId()));
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
		CourtPolicyForm form = (CourtPolicyForm) aForm;
		String testLit=UIUtil.removeXMLtags(form.getCourtPolicyLiteral(),true);
		if(testLit==null || testLit.trim().equals("")){
			sendToErrorPage(aRequest,"error.requiredNonEditableMissing", "Policy");
			form.setCourtPolicyLiteral("");
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("createFailure",form.getAgencyId()));
		}
		if(testLit!=null){
			if(testLit.length()<10){
				sendToErrorPage(aRequest,"errors.minlength","Policy","10");
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("createFailure",form.getAgencyId()));
			}
			else if(testLit.length()>1000){
				sendToErrorPage(aRequest,"errors.maxlength","Policy","1000");
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("createFailure",form.getAgencyId()));
			}
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
					//get the group name from group Id
					setGroupName(form,form.getGroups());
		
					if(UIConstants.UPDATE.equals(form.getAction()))
					{
						CompositeResponse response = UISupervisionOptionHelper.validateCourtPolicy(form.getAgencyId(), form.getCourtPolicyName(), form.getPolicyId());
		
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
					}else{
						// post event to generate name
						if(form.getCourtPolicyName()!=null && !(form.getCourtPolicyName().equals(""))){
								// skip generating name
						}
						else{
								GenerateCourtPolicyNameEvent event = new GenerateCourtPolicyNameEvent();
								event.setGroupId(form.getGroup1Id()); 
								event.setAgencyId(form.getAgencyId());
					
								dispatch.postEvent(event);
								Collection names =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CourtPolicyNameResponseEvent.class );
								if(names != null){
									Iterator iter = names.iterator();
									CourtPolicyNameResponseEvent nameRespEvt = (CourtPolicyNameResponseEvent)iter.next();
									form.setCourtPolicyName(nameRespEvt.getName());
								}
						}
					}

					String conditionLiteral = form.getCourtPolicyLiteral();
					
	
					Collection tokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "{{", "}}");
					Collection referenceTokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "\\[", "\\]");
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
								vereMap.put(vere.getName().toUpperCase(), vere);
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
						//get the variableElementTypeResposneEvent from the map
						Collection vetres = form.getDetailDictionary();
						Map vetreMap = convertIntoMap(vetres);
						Iterator tokenIterator = tokens.iterator();
						while(tokenIterator.hasNext()) //create new vere if it doesn't exist
						{
							String varName = (String)tokenIterator.next();
					
							VariableElementResponseEvent vere = (VariableElementResponseEvent)vereMap.get(varName.toUpperCase());
							//get the variableElementTypeResposneEvent from the map
							VariableElementTypeResponseEvent vereType = (VariableElementTypeResponseEvent)vetreMap.get(varName);
							if(vere == null) //the literals are stored as upper case
							{
								vere = new VariableElementResponseEvent();
								vere.setName(varName);
								if(vereType  == null)
								{
									ActionErrors errors = new ActionErrors();
									errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.condition.literal", "Invalid Variable Reference"));
									saveErrors(aRequest, errors);
									return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));							
								}						
								if(vereType.isEnumration()){
									vere.setEnumeration(true);
									vere.setCodeTableName(vereType.getCodeTableName());
									// get enumerated values
									List codeValues = (List)CodeHelper.getCodes(vereType.getCodeTableName(),true);
									Collections.sort(codeValues);
									vere.setCodeValues(codeValues);
								}
								if(vereType.getIsReference())
								{
									vere.setReference(true);
								}
								vere.setValueType(vereType.getType());
								vere.setEnumerationTypeId(vereType.getValueType()); 
								updatedVeres.add(vere);
							}
							else if (vereType != null){
									// get enumerated values
									vere.setCodeValues(CodeHelper.getCodes(vereType.getCodeTableName(),true));
									updatedVeres.add(vere.clone());
							}
						}
					}
			
					form.setVariableElementResponseEvents(updatedVeres);
			
					HashMap detailDictionaryNameIdMapping = UISupervisionOptionHelper.createDetailDictionaryNameIdMapping(form.getDetailDictionary());
					form.setDetailDictionaryNameIdHashMap(detailDictionaryNameIdMapping);

//					// post event to generate name
//					if(form.getPolicyId() == null || form.getPolicyId().equals("")){
//						GenerateCourtPolicyNameEvent event = new GenerateCourtPolicyNameEvent();
//						event.setGroupId(form.getGroup1Id()); 
//						event.setAgencyId(form.getAgencyId());
//				
//						dispatch.postEvent(event);
//						Collection names =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CourtPolicyNameResponseEvent.class );
//						if(names != null){
//							Iterator iter = names.iterator();
//							CourtPolicyNameResponseEvent nameRespEvt = (CourtPolicyNameResponseEvent)iter.next();
//							form.setCourtPolicyName(nameRespEvt.getName());
//						}
//					}
			
					//this is to format data to be displayed.
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
			
					//get policies to be removed if Group1/Group2 have been changed

					 if(UIConstants.UPDATE.equals(form.getAction()) || UIConstants.COPY.equals(form.getAction()))
					 {
						Collection removedAssociations = new ArrayList();
						GetConditionsToDisassociateFromCourtPoliciesEvent asscEvent = new GetConditionsToDisassociateFromCourtPoliciesEvent();
					
						String groupId = null;
						String group1Id = form.getGroup1Id();
						String group2Id = form.getGroup2Id();
						if(group2Id != null && !group2Id.equals("")){
							groupId = form.getGroup2Id();
						}else{
							groupId = form.getGroup1Id();
						}
				
						asscEvent.setGroupId(groupId);
						asscEvent.setPolicyId(form.getPolicyId());
						asscEvent.setCourtIds(form.getSelectedCourtIds());
				
						 // post event
						dispatch.postEvent(asscEvent);
			
						// check for conditions
						Collection condres = MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), ConditionResponseEvent.class );
						 if(condres != null){
							 Iterator condresIter = condres.iterator();
							 while(condresIter.hasNext())
							 {
								 ConditionResponseEvent condre = (ConditionResponseEvent)condresIter.next();
								 AssociateBean ab = UISupervisionOptionHelper.createAsscBean(condre);
								 removedAssociations.add(ab);
							 }
			
						 }
			 
						form.setRemovedAssociations(removedAssociations);
					 
					 }
		// make sure we don't pass courtPolicy id from here on for copy operation
		if(UIConstants.COPY.equals(form.getAction())){
			form.setCourtPolicyId(null);
		}

			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
	   }
		  	   
   private CourtPolicyForm setGroupName(CourtPolicyForm form, Collection groups)
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
