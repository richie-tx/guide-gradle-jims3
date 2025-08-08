//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefileNonComplianceNoticeListAction.java

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

import messaging.casefile.GetCasefileNonComplianceNoticesEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileTechnicalVOPCodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileVOPSanctionCodesResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSanctionsResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileNonComplianceControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileNonComplianceForm;

/**
 * @author cshimek
 * 
 */
public class DisplayJuvenileCasefileNonComplianceNoticeListAction extends JIMSBaseAction{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.link", "displaySelections");
	}
	/**
	 * 
	 */
	public DisplayJuvenileCasefileNonComplianceNoticeListAction() {
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward displaySelections(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
//		jncForm.clear();
// get needed juvenile info from heading for display		
		JuvenileCasefileForm jcfForm = (JuvenileCasefileForm) getSessionForm(aMapping, aRequest, "juvenileCasefileForm", true);
		String fmlName = "";
		if (jcfForm.getJuvenileName() != null && !"".equals(jcfForm.getJuvenileName()) )
		{
			jcfForm.getJuvenileName().setPrefix("");
			fmlName = jcfForm.getJuvenileName().getCompleteFullNameFirst();
		}
		jncForm.setJuvenileFullName(fmlName.trim());
		jncForm.setCaseAssignmentDateStr(DateUtil.dateToString(jcfForm.getAssignmentDate(), DateUtil.DATE_FMT_1));
// load action taken list 
		jncForm.setActionTakenCodes(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.NCACTION_TAKEN));
		jncForm.setSanctionLevelTypes(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.NCSANCTION_LEVEL));
		JuvenileCasefileForm jcForm = (JuvenileCasefileForm) getSessionForm(aMapping, aRequest, "juvenileCasefileForm", true);
		String casefileId = jcForm.getSupervisionNum();
		if (StringUtils.isEmpty(casefileId)) {
			casefileId = UIJuvenileCaseworkHelper.getCasefileNumber(aRequest, false, true);
		}
		jncForm.setJuvenileNum(jcForm.getJuvenileNum());
		jncForm.setSupervisionNum(casefileId);
		jncForm.setCaseStatusId(jcForm.getCaseStatusId());
		if ("A".equals(jcForm.getCaseStatusId() ) )
		{	
// load technical violation codes
			List temp0 = ComplexCodeTableHelper.getTechnicalVOPs();
			List temp1 = new ArrayList();
			List temp2 = new ArrayList();
			List temp3 = new ArrayList();
			List temp4 = new ArrayList();
			List temp5 = new ArrayList();
			List temp6 = new ArrayList();
			List temp7 = new ArrayList();
			List temp8 = new ArrayList();		
			for (int x =0; x< temp0.size(); x++)
			{
				JuvenileTechnicalVOPCodesResponseEvent jtvcre = (JuvenileTechnicalVOPCodesResponseEvent) temp0.get(x);
				if (UIConstants.MINOR.equalsIgnoreCase(jtvcre.getLevel()) ) {
					temp1.add(jtvcre);
				}
				if (UIConstants.MODERATE_TO_SEVERE.equalsIgnoreCase(jtvcre.getLevel()) ) {
					temp2.add(jtvcre);
				}
			}
			jncForm.setMinorProbationViolationList(sortVOPs(temp1));
			jncForm.setModSevereProbationViolationList(sortVOPs(temp2));
	// load sanction (alternative and Response) codes		
			
			temp0 = ComplexCodeTableHelper.getJuvenileVOPSanctions();
			temp1 = new ArrayList();
			temp2 = new ArrayList();
			JuvenileCasefileSanctionsResponseEvent jcsre = new JuvenileCasefileSanctionsResponseEvent();
			for (int x =0; x< temp0.size(); x++)
			{
				JuvenileVOPSanctionCodesResponseEvent jvscre = (JuvenileVOPSanctionCodesResponseEvent) temp0.get(x);
				jcsre = new JuvenileCasefileSanctionsResponseEvent();
				jcsre.setSanctionId(jvscre.getCode());
				jcsre.setSanctionLevel(jvscre.getSanctionLevel());
				jcsre.setSanctionType(jvscre.getLevel());
				jcsre.setSanctionDesc(jvscre.getDescription());	
				jcsre.setComments("");
				if (UIConstants.MINOR.equalsIgnoreCase(jvscre.getLevel()) ) {
					if ("MIN".equalsIgnoreCase(jvscre.getSanctionLevel()))
					{
						temp1.add(jcsre);
					}
					if ("MED".equalsIgnoreCase(jvscre.getSanctionLevel()))
					{
						temp2.add(jcsre);
					}
					if ("MAX".equalsIgnoreCase(jvscre.getSanctionLevel()))
					{
						temp3.add(jcsre);
					}
					if ("INT".equalsIgnoreCase(jvscre.getSanctionLevel()))
					{
						temp4.add(jcsre);
					}	
	
				}
				if (UIConstants.MODERATE_TO_SEVERE.equalsIgnoreCase(jvscre.getLevel()) ) {
					if ("MIN".equalsIgnoreCase(jvscre.getSanctionLevel()))
					{
						temp5.add(jcsre);
					}
					if ("MED".equalsIgnoreCase(jvscre.getSanctionLevel()))
					{
						temp6.add(jcsre);
					}
					if ("MAX".equalsIgnoreCase(jvscre.getSanctionLevel()))
					{
						temp7.add(jcsre);
					}
					if ("INT".equalsIgnoreCase(jvscre.getSanctionLevel()))
					{
						temp8.add(jcsre);
					}	
				}
			}
	// add other sanction to temp lists as last value
			JuvenileCasefileSanctionsResponseEvent otherSanc = new JuvenileCasefileSanctionsResponseEvent();
			otherSanc.setSanctionId("OTH1");
			otherSanc.setSanctionLevel(UIConstants.MINOR);
			otherSanc.setSanctionType("MIN");
			otherSanc.setSanctionDesc("");
			otherSanc.setComments("");
			temp1.add(otherSanc);
			otherSanc = new JuvenileCasefileSanctionsResponseEvent();
			otherSanc.setSanctionId("OTH2");
			otherSanc.setSanctionLevel(UIConstants.MINOR);
			otherSanc.setSanctionType("MED");
			otherSanc.setSanctionDesc("");
			otherSanc.setComments("");
			temp2.add(otherSanc);
			otherSanc = new JuvenileCasefileSanctionsResponseEvent();
			otherSanc.setSanctionId("OTH3");
			otherSanc.setSanctionLevel(UIConstants.MINOR);
			otherSanc.setSanctionType("MAX");
			otherSanc.setSanctionDesc("");
			otherSanc.setComments("");
			temp3.add(otherSanc);
			otherSanc = new JuvenileCasefileSanctionsResponseEvent();
			otherSanc.setSanctionId("OTH4");
			otherSanc.setSanctionLevel(UIConstants.MINOR);
			otherSanc.setSanctionType("INT");
			otherSanc.setSanctionDesc("");
			otherSanc.setComments("");
			temp4.add(otherSanc);
	
			otherSanc = new JuvenileCasefileSanctionsResponseEvent();
			otherSanc.setSanctionId("OTH5");
			otherSanc.setSanctionLevel(UIConstants.MODERATE_TO_SEVERE);
			otherSanc.setSanctionType("MIN");
			otherSanc.setSanctionDesc("");
			otherSanc.setComments("");
			temp5.add(otherSanc);	
			otherSanc = new JuvenileCasefileSanctionsResponseEvent();
			otherSanc.setSanctionId("OTH6");
			otherSanc.setSanctionLevel(UIConstants.MODERATE_TO_SEVERE);
			otherSanc.setSanctionType("MED");
			otherSanc.setSanctionDesc("");
			otherSanc.setComments("");
			temp6.add(otherSanc);
			otherSanc = new JuvenileCasefileSanctionsResponseEvent();
			otherSanc.setSanctionId("OTH7");
			otherSanc.setSanctionLevel(UIConstants.MODERATE_TO_SEVERE);
			otherSanc.setSanctionType("MAX");
			otherSanc.setSanctionDesc("");
			otherSanc.setComments("");
			temp7.add(otherSanc);
			otherSanc = new JuvenileCasefileSanctionsResponseEvent();
			otherSanc.setSanctionId("OTH8");
			otherSanc.setSanctionLevel(UIConstants.MODERATE_TO_SEVERE);
			otherSanc.setSanctionType("INT");
			otherSanc.setSanctionDesc("");
			otherSanc.setComments("");
			temp8.add(otherSanc);
	
	// load GRID lists
	
			jncForm.setMinorSanctionLevelMinList(temp1);
			jncForm.setMinorSanctionLevelMedList(temp2);
			jncForm.setMinorSanctionLevelMaxList(temp3);
			jncForm.setMinorSanctionLevelIntesiveList(temp4);
			
			jncForm.setModSevereSanctionLevelMinList(temp5);
			jncForm.setModSevereSanctionLevelMedList(temp6);
			jncForm.setModSevereSanctionLevelMaxList(temp7);
			jncForm.setModSevereSanctionLevelIntesiveList(temp8); 
			
			temp0 = null;
			temp1 = null;
			temp2 = null;
			temp3 = null;
			temp4 = null;
			temp5 = null;
			temp6 = null;
			temp7 = null;
			temp8 = null;		

		}
	// load existing notices list 
		GetCasefileNonComplianceNoticesEvent event = (GetCasefileNonComplianceNoticesEvent) EventFactory.getInstance(JuvenileCasefileNonComplianceControllerServiceNames.GETCASEFILENONCOMPLIANCENOTICES);
        event.setCasefileId(jncForm.getSupervisionNum());
        List notices = MessageUtil.postRequestListFilter(event, CasefileNonComplianceNoticeResponseEvent.class);
        List documents = MessageUtil.postRequestListFilter(event, CasefileDocumentsResponseEvent.class);
        jncForm.setDocuments(documents);
        if (notices == null) {
			notices = new ArrayList();
		} 
		for (int n=0; n<notices.size(); n++)
		{
			CasefileNonComplianceNoticeResponseEvent cncEvent = (CasefileNonComplianceNoticeResponseEvent) notices.get(n);
			cncEvent.setNoticeSignatureStatusLit("");
			cncEvent.setJuvenileCompletedLit("");
			cncEvent.setViolationLevelLit("");
	// set default literal
			cncEvent.setNoticeSignatureStatusLit("UNSIGNED");
			cncEvent.setJuvenileCompletedLit("PENDING");
			cncEvent.setViolationLevelLit("MINOR");
			if (cncEvent.getSignatureStatusId() != null)
			{
				cncEvent.setNoticeSignatureStatusLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCSIGNATURE_STATUS, cncEvent.getSignatureStatusId()));
			}
			if (cncEvent.getCompletionStatusId() != null)
			{
				cncEvent.setJuvenileCompletedLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCCOMPLETION_STATUS, cncEvent.getCompletionStatusId()));
			}
			if (cncEvent.getViolationLevelId() != null)
			{
				cncEvent.setViolationLevelLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCVIOLATION_LEVEL, cncEvent.getViolationLevelId()));
			}
		}
		jncForm.setExistingNoticesList(notices);
	// end load existing notices list
		return (aMapping.findForward(UIConstants.SUCCESS));
	}
	
	public static List sortVOPs(Collection vopList){
		Iterator iter = vopList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			JuvenileTechnicalVOPCodesResponseEvent jtre = (JuvenileTechnicalVOPCodesResponseEvent) iter.next();	
			String desc = jtre.getDescription().toUpperCase();
			map.put(desc, jtre);
		}
		return new ArrayList(map.values());
	}	
}