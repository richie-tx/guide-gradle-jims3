// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderSpecialConditionSummaryAction.java

package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.CreateSpecialConditionEvent;
import messaging.supervisionorder.CreateSpecialConditionsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSTaskControllerServiceNames;
import naming.PDConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

public class DisplaySupervisionOrderSpecialConditionSummaryAction extends JIMSBaseAction
{
	//private static final String CODE_TABLE_MANAGER = "CSCD CODE TABLE MANAGERS";

    

    /**
     * @roseuid 438F2404014F
     */
    public DisplaySupervisionOrderSpecialConditionSummaryAction()
    {

    }

    /**
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.next", "next");
        keyMap.put("button.saveContinueWithOrder", "continueWithOrder");
        keyMap.put("button.addMoreConditions", "addMoreConditions");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        return keyMap;
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        if (sof.getDisplayConditionList() == null || sof.getDisplayConditionList().isEmpty()){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Condition entered."));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.CREATE_FAILURE);
        }
        List inList = mergeDisplayIntoInputList(sof.getInputConditionList(),sof.getDisplayConditionList());
        List outList = new ArrayList();
        for (int x = 0; x<inList.size(); x++){
        	ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) inList.get(x);
        	if (cdre.getDescription() != null && !"".equals(cdre.getDescription())){
	            if (cdre.getGroup1Id() != null && !"".equals(cdre.getGroup1Id()))
	            {
	                //get group1 name
	                cdre.setGroup1Name(UISupervisionOptionHelper.getGroup1Name(sof.getGroup1List(), cdre.getGroup1Id()));
	            }
	            else
	            {
	                cdre.setGroup1Name("");
	                if (!"".equals(cdre.getGroup1Id()))
	                {
	                    cdre.setGroup1Id(null); // make sure we don't pass an empty string here
	                }
	            }
	            outList.add(cdre);
        	}     
        }
        sof.setOutputConditionList(outList);
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    public ActionForward addMoreConditions(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        if (sof.getInputConditionList() != null && sof.getDisplayConditionList() != null){
        	List aList = mergeDisplayIntoInputList(sof.getInputConditionList(),sof.getDisplayConditionList());
	        sof.setInputConditionList(aList);
	        List xList = new ArrayList();
	        UISupervisionOrderHelper.add10LightConditions(xList = new ArrayList());
	        sof.setDisplayConditionList(xList);  
        } 
        if (sof.getAgencyId().equalsIgnoreCase(UIConstants.CSC)){
        	forward = aMapping.findForward(UIConstants.ADD_TO_LIST_SUCCESS);
        } else {
        	forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS, UIUtil.getCurrentUserAgencyID()));
        }	
        return forward;
    }  
    private static String SPECIAL_CONDITION = "SpecialCondition";
    
    private List mergeDisplayIntoInputList(Collection inputList, Collection displayList){
    	List tempList = new ArrayList(displayList);
    	List inList = new ArrayList(inputList);
    	boolean matchFound = false;
    	for (int x = 0; x < 10; x++){
    		ConditionDetailResponseEvent cdre1 = (ConditionDetailResponseEvent) tempList.get(x);
    		matchFound = false;
    		if (cdre1.getDescription() != null && !cdre1.getDescription().equals(PDConstants.BLANK)){
				for (int y =0; y < inList.size(); y++){
					ConditionDetailResponseEvent cdre2 = (ConditionDetailResponseEvent) inList.get(y);
					if (cdre1.getName().equals(cdre2.getName())
							|| (cdre1.getName().startsWith(SPECIAL_CONDITION)
									&& (cdre1.getDescription().equals(cdre2.getDescription())))){
						cdre2.setDescription(cdre1.getDescription());
						cdre2.setGroup1Id(cdre1.getGroup1Id());
						cdre2.setGroup1Name(PDConstants.BLANK);
						cdre2.setNotes(cdre1.getNotes());
						matchFound = true;
						break;
					} 				}
				if (matchFound == false){
					inList.add(cdre1);
				}
    		}
    	}
    	return inList;
    }
    
    public ActionForward continueWithOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

 //      if (!conditionExists(sof.getConditionId(), sof.getConditionSelectedList()))
        if (sof.getOutputConditionList() != null && !sof.getOutputConditionList().isEmpty())  {
        	CreateSpecialConditionsEvent mainEvent = (CreateSpecialConditionsEvent) EventFactory.getInstance(
				                                  SupervisionOrderControllerServiceNames.CREATESPECIALCONDITIONS);
        		mainEvent.setAgencyId(sof.getAgencyId());
				mainEvent.setCdi(sof.getCdi());
				mainEvent.setCourtId(sof.getCourtId());
				mainEvent.setOrderId(sof.getOrderId());
				int nextSeqNum = 0;
				if (sof.getConditionSelectedList() != null && sof.getConditionSelectedList().size() > 0){
					nextSeqNum = sof.getConditionSelectedList().size();
				}
				CreateSpecialConditionEvent specCondEvt = null;
				List cList = new ArrayList(sof.getOutputConditionList());
				ConditionDetailResponseEvent cdre = null;
				for (int x = 0; x < sof.getOutputConditionList().size(); x++){	
					cdre = (ConditionDetailResponseEvent) cList.get(x);
					specCondEvt = new CreateSpecialConditionEvent();
					replaceBRTags(cdre);
					specCondEvt.setDescription(cdre.getDescription());
					
					specCondEvt.setGroup1(cdre.getGroup1Id());
					if (cdre.getNotes() == null){
						cdre.setNotes("");
					}
					specCondEvt.setNotes(cdre.getNotes());
					nextSeqNum++;
					specCondEvt.setSequenceNum(nextSeqNum);
					mainEvent.addRequest(specCondEvt);
				}
				CompositeResponse cr = MessageUtil.postRequest(mainEvent);
				ErrorResponseEvent ere = (ErrorResponseEvent) MessageUtil.filterComposite(cr, ErrorResponseEvent.class);
				if (ere != null){
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", ere.getMessage()));
					saveErrors(aRequest, errors);
					return aMapping.findForward(UIConstants.UPDATE_FAILURE);
		        }
		        Collection cResults = MessageUtil.compositeToCollection(cr,
		                    ConditionDetailResponseEvent.class);
		        MessageUtil.processEmptyCollection(cResults);
		        List cdreList = CollectionUtil.iteratorToList(cResults.iterator());
		        ConditionDetailResponseEvent condRespEvent = null;
		        for (int i = 0; i < cdreList.size(); i++) {
		            condRespEvent = (ConditionDetailResponseEvent) cdreList.get(i);
		            sof.addConditionSelectedList(condRespEvent);
		                //the following isn't needed since creating multiple special conditions.
		                //Also no longer creating a task.
		                /* sof.setConditionId(condRespEvent.getConditionId());
		                // Adding to the form for Tasks
		                sof.setConditionName(condRespEvent.getName());
		                sof.setConditionLiteral(condRespEvent.getConditionLiteralPreview());
		                sof.setGroup1Id(condRespEvent.getGroup1Id());
		                sof.setConditionNotes(condRespEvent.getNotes());*/
		       }
			}

        sof.setGroup1Id(null);
        sof.setConditionLiteral(""); 

        ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONTINUE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    /**
     * 
     * @param conditions
     * @return
     */
    private ConditionDetailResponseEvent replaceBRTags( ConditionDetailResponseEvent response ){
		
    	String condDesc = response.getDescription();
    	String formattedStr = "";
    	
    	if ( condDesc != "" ){
    		
    		formattedStr = condDesc.replaceAll( "<br>", "" ).replaceAll("</br>", "").replaceAll("<br />", "")
    								.replaceAll("&#60;", "<").replaceAll("&#62;", ">").replaceAll("\\\\+", "&#92;&#92;");
    		response.setDescription( formattedStr );
    	}
    	return response;
    }

    /**
     * 
     * @param dispatch
     * @param taskId
     */
    public void createCSTaskForNewCondition(String taskId, String condId, SupervisionOrderForm sof)
    {

        CreateCSTaskEvent createCSTask = (CreateCSTaskEvent) EventFactory
                .getInstance(CSTaskControllerServiceNames.CREATECSTASK);

        createCSTask.setConditionId(condId);
        createCSTask.setDefendantId(sof.getDefendantId());
        String court = sof.getCourtId();
        if(!court.equals("") && (court.length()>2 )){
            String courtNum = court.substring(3);
            String courtId = UIUtil.removeLeadingZeros(courtNum);
            createCSTask.setCourtId(courtId);
        }
        
        createCSTask.setSubject2("New Condition Created");
        createCSTask.setNtTaskId(taskId);

        MessageUtil.postRequest(createCSTask);

    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
         return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

   
	@Override
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub
		
	}
}
