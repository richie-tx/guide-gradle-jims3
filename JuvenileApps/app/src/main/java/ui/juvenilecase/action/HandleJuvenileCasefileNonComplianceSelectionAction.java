//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\HandleJuvenileCasefileVOPSelectionAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileNonComplianceNoticeProbationViolationResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeSanctionResponseEvent;
import messaging.codetable.criminal.reply.JuvenileTechnicalVOPCodesResponseEvent;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileNonComplianceForm;


public class HandleJuvenileCasefileNonComplianceSelectionAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "displayExistingNotice");
		keyMap.put("button.createNotice", "createNotice");
//		keyMap.put("button.view", "displayNoticeDetails");
	}	
   
   /**
    * @roseuid 467FB5C80014
    */
   public HandleJuvenileCasefileNonComplianceSelectionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward createNotice(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   jncForm.createClear();
	   jncForm.setConfirmationMsg("");
	   jncForm.setNoiticeGeneratedInd("");
	   jncForm.setSanctionAssignedDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1)); 
	   jncForm.setMinorMinComments(new String[jncForm.getMinorSanctionLevelMinList().size() ]);
	   jncForm.setMinorMedComments(new String[jncForm.getMinorSanctionLevelMedList().size() ]);
	   jncForm.setMinorMaxComments(new String[jncForm.getMinorSanctionLevelMaxList().size() ]);
	   jncForm.setMinorIntComments(new String[jncForm.getMinorSanctionLevelIntesiveList().size() ]);
	   jncForm.setModSevereMinComments(new String[jncForm.getModSevereSanctionLevelMinList().size() ]);
	   jncForm.setModSevereMedComments(new String[jncForm.getModSevereSanctionLevelMedList().size() ]);
	   jncForm.setModSevereMaxComments(new String[jncForm.getModSevereSanctionLevelMaxList().size() ]);
	   jncForm.setModSevereIntComments(new String[jncForm.getModSevereSanctionLevelIntesiveList().size() ]);
	   jncForm.setSelectedSanctionIds(new String[0]);
	   jncForm.setProbationViolationList(new ArrayList());
	 
	   return aMapping.findForward("createNotice");
   }
   
    /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward displayExistingNotice(ActionMapping aMapping, ActionForm aForm, 
		   HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
		JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
		jncForm.setConfirmationMsg("");
		jncForm.setJuvenileCompletedStatus("");
		jncForm.setActionTakenId("");
		jncForm.setActionTakenDesc("");
		jncForm.setOtherActionTakenComment("");
		jncForm.setCompletionDateStr("");
		jncForm.setJuvenileCompletedComments("");
		jncForm.setProbationViolationList(new ArrayList());
// find record based on noticeId
	   String noticeId = aRequest.getParameter("noticeID");
	   for (int x=0; x<jncForm.getExistingNoticesList().size(); x++)
	   {
		   CasefileNonComplianceNoticeResponseEvent jcnre = (CasefileNonComplianceNoticeResponseEvent) jncForm.getExistingNoticesList().get(x);
		   if (noticeId.equals(jcnre.getCasefileNonComplianceNoticeId()))
		   {
			  jncForm.setSelectedNoticeId(noticeId);
			  jncForm.setNonComplianceDate(jcnre.getNonComplianceDate());
			  jncForm.setSanctionAssignedDate(jcnre.getSanctionAssignedDate());
			  jncForm.setSanctionCompleteByDate(jcnre.getCompleteSanctionByDate());
			  jncForm.setParentalNotified("false");
			  if (jcnre.isParentInformed() == true)
			  { 	  
				  jncForm.setParentalNotified("true");
			  }	 
			  jncForm.setProbationViolationLevelDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCVIOLATION_LEVEL,jcnre.getViolationLevelId()));		
			  jncForm.setSanctionLevelDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCSANCTION_LEVEL,jcnre.getSanctionLevelId() ) );
			   
			   if (jcnre.getSignatureStatusId() == null || "".equals(jcnre.getSignatureStatusId()))
			   {
				   jncForm.setUpdateFlow("Sign");
				   jncForm.setSignatureSignedDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
				   jncForm.setSignatureStatus("");
				   jncForm.setSignatureStatusLiteral("UNSIGNED"); 
			   } else {
				   jncForm.setUpdateFlow("Comp");
				   jncForm.setSignatureSignedDate(jcnre.getSignedDate());
				   jncForm.setJuvenileCompletedStatus(jcnre.getCompletionStatusId());
				   jncForm.setJuvenileCompletedComments(jcnre.getCompletionComments());
				   jncForm.setActionTakenId(jcnre.getActionTakenId());
				   jncForm.setOtherActionTakenComment(jcnre.getActionTakenComments());
				   jncForm.setSignatureStatus(jcnre.getSignatureStatusId());
				   jncForm.setSignatureStatusLiteral(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCSIGNATURE_STATUS, jcnre.getSignatureStatusId()));
				   jncForm.setOriginalActionTakenId(jcnre.getActionTakenId());
				   jncForm.setOriginalOtherActionTakenComment(jcnre.getActionTakenComments());
			   }
// Begin Load probation violation display list			   
			   List temp0 = ComplexCodeTableHelper.getTechnicalVOPs();
			   List temp1 = (List) jcnre.getProbationViolations();
			   if (temp1 == null)
			   {
				   temp1 = new ArrayList(); 
			   }
			   List temp2 = new ArrayList();
			   for (int v=0; v<temp1.size(); v++)
			   {
				   CasefileNonComplianceNoticeProbationViolationResponseEvent cnc = (CasefileNonComplianceNoticeProbationViolationResponseEvent) temp1.get(v);
				   for (int c=0; c<temp0.size(); c++)
				   {	   
					   JuvenileTechnicalVOPCodesResponseEvent jtvcre = (JuvenileTechnicalVOPCodesResponseEvent) temp0.get(c);
					   if (cnc.getJuvenileTechnicalVOPCodesId().equals(jtvcre.getCode()))
					   {	
						   temp2.add(jtvcre);
						   break;
					   }	
				   }
			   }
			   jncForm.setProbationViolationList(sortVOPs(temp2) );
// End load probation violation display list
			   temp0 = new ArrayList();
			   temp1 = new ArrayList();
			   temp2 = new ArrayList();
			   if (jcnre.getSanctions() != null)
			   {	   
				   Iterator iter = jcnre.getSanctions().iterator();
				   while(iter.hasNext()){
						CasefileNonComplianceNoticeSanctionResponseEvent ncre = (CasefileNonComplianceNoticeSanctionResponseEvent) iter.next();	
						if (!"OTH".equals(ncre.getJuvenileVOPSanctionCodesId() ) )
						{	
							temp0.add(ncre);
						} else {
							temp2.add(ncre);
						}
					}
			   }	   
			   temp1 = sortSanctions( temp0 );
// Add Other Sanction if present to end of list			
			   if (temp2.size() > 0)
			   {
				   temp1.add(temp2.get(0));
			   }
			   jncForm.setSelectedSanctionsList(temp1);
			   temp0 = null;
			   temp1 = null;
			   temp2 = null;
		   }   
	   }
   
	   jncForm.setAction(UIConstants.UPDATE);
	   return aMapping.findForward("existingSuccess");
   }  

	public static List sortSanctions(Collection sanctionsList){
		Iterator iter = sanctionsList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			CasefileNonComplianceNoticeSanctionResponseEvent ncre = (CasefileNonComplianceNoticeSanctionResponseEvent) iter.next();	
			map.put(ncre.getOtherText(), ncre);
		}
		return new ArrayList(map.values());
	} 
	
	public static List sortVOPs(Collection vopList){
		Iterator iter = vopList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			JuvenileTechnicalVOPCodesResponseEvent jtre = (JuvenileTechnicalVOPCodesResponseEvent) iter.next();	
			map.put(jtre.getDescription(), jtre);
		}
		return new ArrayList(map.values());
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
