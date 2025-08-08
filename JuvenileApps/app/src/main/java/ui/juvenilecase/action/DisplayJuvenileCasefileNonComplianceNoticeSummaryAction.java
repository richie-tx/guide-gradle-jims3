//Source file: C:\\views\\juvenileCasework\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefileNonComplianceNoticeSummaryAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileNonComplianceNoticeSanctionResponseEvent;
import messaging.codetable.criminal.reply.JuvenileTechnicalVOPCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSanctionsResponseEvent;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.form.JuvenileNonComplianceForm;


public class DisplayJuvenileCasefileNonComplianceNoticeSummaryAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}	
   
   /**
    * 
    */
   public DisplayJuvenileCasefileNonComplianceNoticeSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   	JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   	jncForm.setConfirmationMsg("");
	   	if (!"".equals(jncForm.getNonComplianceDateStr() ) )
	   	{
	   		jncForm.setNonComplianceDate(DateUtil.stringToDate(jncForm.getNonComplianceDateStr(), DateUtil.DATE_FMT_1));
	   	}
	   	if (!"".equals(jncForm.getSanctionAssignedDateStr() ) )
	   	{
	   		jncForm.setSanctionAssignedDate(DateUtil.stringToDate(jncForm.getSanctionAssignedDateStr(), DateUtil.DATE_FMT_1));
	   	}
	   	if (!"".equals(jncForm.getSanctionCompleteByDateStr() ) )
	   	{
	   		jncForm.setSanctionCompleteByDate(DateUtil.stringToDate(jncForm.getSanctionCompleteByDateStr(), DateUtil.DATE_FMT_1));
	   	}

	   	jncForm.setSanctionLevelDesc("");
		for (int x =0; x<jncForm.getSanctionLevelTypes().size(); x++)
		{
			CodeResponseEvent cre = (CodeResponseEvent) jncForm.getSanctionLevelTypes().get(x);
			if (jncForm.getSanctionLevelId().equalsIgnoreCase(cre.getCode()))
			{
				jncForm.setSanctionLevelDesc(cre.getDescription());
				break;
			}
		}
	   	jncForm.setProbationViolationLevelDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCVIOLATION_LEVEL,jncForm.getViolationLevelId()));		
		List temp1 = new ArrayList();
		List temp2 = new ArrayList();
		if ("MINOR".equalsIgnoreCase(jncForm.getViolationLevelId() ) ) {
			temp1 = jncForm.getMinorProbationViolationList();
			jncForm.setSelectedTechnicalIds(jncForm.getSelectedMinorTechnicalIds());
		} else {
			temp1 = jncForm.getModSevereProbationViolationList();
			jncForm.setSelectedTechnicalIds(jncForm.getSelectedModSevereTechnicalIds());
		}
// load Probation Violation descriptions list		
		for (int y=0; y< jncForm.getSelectedTechnicalIds().length; y++)
		{
			for (int z=0; z<temp1.size(); z++)
			{
				JuvenileTechnicalVOPCodesResponseEvent jtvcre = (JuvenileTechnicalVOPCodesResponseEvent) temp1.get(z);
				if (jncForm.getSelectedTechnicalIds()[y].equalsIgnoreCase(jtvcre.getCode())) {
					temp2.add(jtvcre);
					break;
				}
			}
		}
		jncForm.setProbationViolationList(temp2);
// load Selected Sanctions with comments	
		temp1 = new ArrayList();
		String comments[] = new String[0];
		String level = jncForm.getSanctionLevelId().toLowerCase();
		if (UIConstants.MINOR.equalsIgnoreCase(jncForm.getViolationLevelId() ) ) {
			jncForm.setSelectedSanctionIds(jncForm.getSelectedMinorSanctionIds());
			if ("min".equals(level))
			{	
				temp1 = jncForm.getMinorSanctionLevelMinList();
				comments = new String[jncForm.getMinorSanctionLevelMinList().size() ];
				loadComments(comments, jncForm.getMinorMinComments());
			}
			if ("med".equals(level))
			{	
				temp1 = jncForm.getMinorSanctionLevelMedList();
				comments = new String[jncForm.getMinorSanctionLevelMedList().size() ];
				loadComments(comments, jncForm.getMinorMedComments());
			}	
			if ("max".equals(level))
			{	
				temp1 = jncForm.getMinorSanctionLevelMaxList();
				comments = new String[jncForm.getMinorSanctionLevelMaxList().size() ];
				loadComments(comments, jncForm.getMinorMaxComments());
			}
			if ("int".equals(level))
			{	
				temp1 = jncForm.getMinorSanctionLevelIntesiveList();
				comments = new String[jncForm.getMinorSanctionLevelIntesiveList().size() ];
				loadComments(comments, jncForm.getMinorIntComments());
			}			
		} else {
			jncForm.setSelectedSanctionIds(jncForm.getSelectedModSevereSanctionIds());
			if ("min".equals(level))
			{	
				temp1 = jncForm.getModSevereSanctionLevelMinList();
				comments = new String[jncForm.getModSevereSanctionLevelMinList().size() ];
				loadComments(comments, jncForm.getModSevereMinComments());
			}
			if ("med".equals(level))
			{	
				temp1 = jncForm.getModSevereSanctionLevelMedList();
				comments = new String[jncForm.getModSevereSanctionLevelMedList().size() ];
				loadComments(comments, jncForm.getModSevereMedComments());
			}	
			if ("max".equals(level))
			{	
				temp1 = jncForm.getModSevereSanctionLevelMaxList();
				comments = new String[jncForm.getModSevereSanctionLevelMaxList().size() ];
				loadComments(comments, jncForm.getModSevereMaxComments());
			}
			if ("int".equals(level))
			{	
				temp1 = jncForm.getModSevereSanctionLevelIntesiveList();
				comments = new String[jncForm.getModSevereSanctionLevelIntesiveList().size() + 1 ];
				loadComments(comments, jncForm.getModSevereIntComments());
			}
		}
		temp2 = new ArrayList();
		String selSanctionId = "";
		CasefileNonComplianceNoticeSanctionResponseEvent sancEvent = new CasefileNonComplianceNoticeSanctionResponseEvent();
		for (int s=0; s<jncForm.getSelectedSanctionIds().length; s++)
		{
			selSanctionId = jncForm.getSelectedSanctionIds()[s];
			for (int c = 0; c<temp1.size(); c++ )
			{
				JuvenileCasefileSanctionsResponseEvent jcsre2 = (JuvenileCasefileSanctionsResponseEvent) temp1.get(c);	
				if (selSanctionId.equalsIgnoreCase(jcsre2.getSanctionId())) {
					sancEvent = new CasefileNonComplianceNoticeSanctionResponseEvent();
					sancEvent.setJuvenileVOPSanctionCodesId(jcsre2.getSanctionId());
					sancEvent.setOtherText(jcsre2.getSanctionDesc());
					sancEvent.setComments(comments[c]);
					if (selSanctionId.indexOf("OTH") > -1) {
						sancEvent.setJuvenileVOPSanctionCodesId("OTH");
						sancEvent.setOtherText(getOtherSanctionDescription(jcsre2, jncForm));
					}
					temp2.add(sancEvent);
					break;
				}
			}
		}
		jncForm.setSelectedSanctionsList(temp2);
		jncForm.setAction("summary");
		return (aMapping.findForward(UIConstants.SUCCESS));

   }
   
   private void loadComments(String[]comments, String[]sourceArray)
   {
	   for (int a=0; a<sourceArray.length; a++)
	   {
		   comments[a] = sourceArray[a];
	   }
   }
   
   private String getOtherSanctionDescription(JuvenileCasefileSanctionsResponseEvent jcsre, JuvenileNonComplianceForm jncForm)
   {
	   String desc = "";
	   String sType = jcsre.getSanctionType();
	   if (UIConstants.MINOR.equalsIgnoreCase(jcsre.getSanctionLevel() ) ) {
			if ("MIN".equals(sType))
			{
				desc = jncForm.getMinorMinOtherDescription();
			}
			if ("MED".equals(sType))
			{
				desc = jncForm.getMinorMedOtherDescription();
			}
			if ("MAX".equals(sType))
			{
				desc = jncForm.getMinorMaxOtherDescription();
			}
			if ("INT".equals(sType))
			{
				desc = jncForm.getMinorIntOtherDescription();
			}
	   } else {
			if ("MIN".equals(sType))
			{
				desc = jncForm.getModSevereMinOtherDescription();
			}
			if ("MED".equals(sType))
			{
				desc = jncForm.getModSevereMedOtherDescription();
			}
			if ("MAX".equals(sType))
			{
				desc = jncForm.getModSevereMaxOtherDescription();
			}
			if ("INT".equals(sType))
			{
				desc = jncForm.getModSevereIntOtherDescription();
			}
	   }
	   return desc;
   }

   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
//	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;	
	   return aMapping.findForward("back");
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
//	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   return aMapping.findForward("cancel");
   }   
}
