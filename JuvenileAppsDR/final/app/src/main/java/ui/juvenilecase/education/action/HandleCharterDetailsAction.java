package ui.juvenilecase.education.action;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.education.form.EducationCharterDetailsForm;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenile.reply.CharterGEDResponseEvent;
import messaging.juvenile.reply.CharterVEPResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HandleCharterDetailsAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put( "button.addGED", "addGED" ) ;
		keyMap.put( "button.link", "viewGED" ) ;
		keyMap.put( "button.addVEP", "addVEP" ) ;
		keyMap.put( "button.updateVEP", "updateVEP" ) ;
		keyMap.put( "button.addPostRelease", "addPostRelease" ) ;
 		keyMap.put( "button.cancel", "cancel" );
		keyMap.put( "button.back", "back" );
	}
	
	public ActionForward addGED( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    EducationCharterDetailsForm form = (EducationCharterDetailsForm)aForm ;
	    Collection hsepList = form.getHsepProgramList();
	    String version = form.getVersion();
	    boolean locked = form.isLockStatus();
	    form.clearAll( ) ;
	    String curDate = DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1);
	    /**Changes for ER: JIMS200077481 starts **/
	   /* form.setReadingTestDateStr(curDate);
	    form.setWritingTestDateStr(curDate);*/
	    form.setRlaTestDateStr(curDate);
	    /**Changes for ER: JIMS200077481 ends**/
	    form.setMathTestDateStr(curDate);
	    form.setScienceTestDateStr(curDate);
	    form.setSocialStudiesTestDateStr(curDate);
	    form.setAction("create");
	    form.setHsepProgramList(hsepList);
	    form.setVersion(version);
	    form.setLockStatus(locked);
	    /**Changes for ER: JIMS200077481 starts **/
	   /* form.setReadingShowRetest(false);
	    form.setWritingShowRetest(false);*/
	    form.setRlaShowRetest(false);
	    /**Changes for ER: JIMS200077481 ends**/
	    form.setMathShowRetest(false);
	    form.setScienceShowRetest(false);
	    form.setSocialStudiesShowRetest(false);
	    form.setShowRetest(false);
	    /**Changes for ER: JIMS200077481 starts**/
	    /*form.setReadingScoreNotApplicable(false);
	    form.setWritingScoreNotApplicable(false);*/
	    /**Changes for ER: JIMS200077481 ends**/
	    form.setRlaScoreNotApplicable(false);
	    form.setMathScoreNotApplicable(false);
	    form.setScienceScoreNotApplicable(false);
	    form.setSocialStudiesScoreNotApplicable(false);
	    /**Changes for ER: JIMS200077481 starts **/
	    /*form.setReadingBeforePlacement(false);
	    form.setReadingAfterPlacement(false);
	    form.setWritingBeforePlacement(false);
	    form.setWritingAfterPlacement(false);*/
	    form.setRlaBeforePlacement(false);
	    form.setRlaAfterPlacement(false);
	    form.setMathBeforePlacement(false);
	    /**Changes for ER: JIMS200077481 ends**/
	    form.setMathAfterPlacement(false);
	    form.setScienceBeforePlacement(false);
	    form.setScienceAfterPlacement(false);
	    form.setSocialStudiesBeforePlacement(false);
	    form.setSocialStudiesAfterPlacement(false);
	    /**Changes for ER: JIMS200077481 starts **/
	    form.setHasGEDEffectiveDateApplied(true);
	    /**Changes for ER: JIMS200077481 ends **/
	    List wrkList = (List) hsepList;
	    if (!"0".equalsIgnoreCase(version) ) {
			form.setShowRetest(true);
	    	if (hsepList != null && !hsepList.isEmpty()) {
	    		for (int x=0; x<hsepList.size(); x++){
	    			CharterGEDResponseEvent cgre = (CharterGEDResponseEvent) wrkList.get(x);
	    			 /**Changes for ER: JIMS200077481 starts **/
	    			  //condition check for readingScore executes once when the JPOs move from old page to new page. 
		    			if ((cgre.getReadingScore() != null && !"0".equalsIgnoreCase(cgre.getReadingScore())) 
		    					|| (cgre.getWritingScore() != null && !"0".equalsIgnoreCase(cgre.getWritingScore()))) {
		    				form.setRlaShowRetest(true);
		    			}else if (cgre.getRlaScore() != null && !"0".equalsIgnoreCase(cgre.getRlaScore())) {
		    				form.setRlaShowRetest(true);
		    			}
		    		/**Changes for ER: JIMS200077481 ends **/
	    			if (cgre.getMathScore() != null && !"0".equalsIgnoreCase(cgre.getMathScore() ) ) {
	    				form.setMathShowRetest(true);
	    			}
	    			if (cgre.getScienceScore() != null && !"0".equalsIgnoreCase(cgre.getScienceScore() ) ) {
	    				form.setScienceShowRetest(true);
	    			}
	    			if (cgre.getSocialStudiesScore() != null && !"0".equalsIgnoreCase(cgre.getSocialStudiesScore() ) ) {
	    				form.setSocialStudiesShowRetest(true);
	    			}
	    		}
	    		//Changes for ER:JIMS200077481 include RLA and exclude the Language for reading and writing.
	    		if (form.isRlaShowRetest() == false && form.isMathShowRetest() == false
	    			&& form.isScienceShowRetest() == false && form.isSocialStudiesShowRetest() == false) {
	    			form.setShowRetest(false);
	    		}
	    	}
	    }	
	    String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        form.setJuvenileNum(juvenileNum);
		if( form.getHsepProgramCodeList().isEmpty() )
		{
			Collection hsepProgramCodeList = CodeHelper.getCodes(PDCodeTableConstants.GEDPROGRAM, true);
			form.setHsepProgramCodeList(hsepProgramCodeList);
		}
		hsepList = null;
		wrkList = null;
		juvenileNum = null;
	    return aMapping.findForward( UIConstants.CREATE_GED ) ;
    }
	
	public ActionForward addVEP( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    EducationCharterDetailsForm form = (EducationCharterDetailsForm)aForm ;
	    form.clearAll( ) ;
	    form.setVepProgramCodeId("");
	    form.setProgramStartDateStr("");
	    form.setAction("create");
	    String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        form.setJuvenileNum(juvenileNum);
		if( form.getVepProgramCodeList().isEmpty() )
		{
			Collection vepProgramCodeList = CodeHelper.getCodes(PDCodeTableConstants.VEPPROGRAM, true);
			form.setVepProgramCodeList(vepProgramCodeList);
		}
	    return aMapping.findForward( UIConstants.CREATE_VEP ) ;
    }
	
	public ActionForward addPostRelease( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    EducationCharterDetailsForm form = (EducationCharterDetailsForm)aForm ;
	    boolean releasedFromFacility = form.isReleasedFromFacility();
	    form.clearAll( ) ;
	    form.setAction("create");
	    String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        form.setJuvenileNum(juvenileNum);
        form.setPostReleaseStatusDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
        form.setReleasedFromFacility(releasedFromFacility);
		if( form.getPostReleaseEmployedCodeList().isEmpty() )
		{
			Collection postReleaseEmployedCodeList = CodeHelper.getCodes(PDCodeTableConstants.POSTRELEASEEMPLOYED, true);
			form.setPostReleaseEmployedCodeList(postReleaseEmployedCodeList);
		}
		if( form.getSelectedFromList().isEmpty() )
		{
			List postReleaseContinuingEdCodeList = CodeHelper.getCodes(PDCodeTableConstants.POSTRELEASECONTINUINGED, true);
			form.setSelectedFromList(postReleaseContinuingEdCodeList);
		}
	    return aMapping.findForward( UIConstants.CREATE_PRT ) ;
    }
	
	public ActionForward updateVEP( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    EducationCharterDetailsForm form = (EducationCharterDetailsForm)aForm ;
	    form.setAction("update");
	    String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        form.setJuvenileNum(juvenileNum);
        
        Collection vepDetails = form.getVepProgramList();
		if (vepDetails != null) {
			Iterator charterVEPs = vepDetails.iterator();
			while (charterVEPs.hasNext())
			{
				CharterVEPResponseEvent charterVEP = (CharterVEPResponseEvent) charterVEPs.next();
				if (form.getVepProgramCode().equals(charterVEP.getJuvenileCharterVEPId())) {
					form.setProgram(charterVEP.getProgramName());
					form.setProgramId(charterVEP.getProgramCodeId());
					form.setProgramStartDate(charterVEP.getStartDate());
					form.setProgramCompletionDateStr("");
					break;
				}
			}
		}
	    return aMapping.findForward( UIConstants.UPDATE_SUCCESS ) ;
    }
	
	public ActionForward viewGED( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    EducationCharterDetailsForm form = (EducationCharterDetailsForm)aForm ;
	    form.setAction("view");
	    String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        form.setJuvenileNum(juvenileNum);
        String charterGEDId = (String)aRequest.getParameter("charterGEDId");
        
        Collection charterDetails = form.getHsepProgramList();
		if (charterDetails != null) {
			Iterator charterGEDs = charterDetails.iterator();
			while (charterGEDs.hasNext())
			{
				CharterGEDResponseEvent charterGED = (CharterGEDResponseEvent) charterGEDs.next();
				if (charterGEDId.equals(charterGED.getCharterGEDId())) {
					 /**Changes for ER: JIMS200077481 starts **/
					//changes for ER: JIMS200077481 condition check to show old form based on effective date 
					if(charterGED.getGEDEntryDate().before( DateUtil.stringToDate(UIConstants.GED_EFFECTIVE_DATE, UIConstants.DATE_FMT_1))){ 
						form.setReadingAfterPlacement(charterGED.isReadingAfterPlacement());
						form.setReadingBeforePlacement(charterGED.isReadingBeforePlacement());
						form.setReadingPassFailInd(charterGED.isReadingPassFailIndicator());
						form.setReadingRetest(charterGED.isReadingRetest());
						form.setReadingScore(charterGED.getReadingScore());
						form.setReadingTestDate(charterGED.getReadingTestDate());
						form.setWritingAfterPlacement(charterGED.isWritingAfterPlacement());
						form.setWritingBeforePlacement(charterGED.isWritingBeforePlacement());
						form.setWritingPassFailInd(charterGED.isWritingPassFailIndicator());
						form.setWritingRetest(charterGED.isWritingRetest());
						form.setWritingScore(charterGED.getWritingScore());
						form.setWritingTestDate(charterGED.getWritingTestDate());
						form.setHasGEDEffectiveDateApplied(false); // for old view.
					
						if (charterGED.getReadingScore() != null && "0".equalsIgnoreCase(charterGED.getReadingScore() ) ) {
		    				form.setReadingScoreNotApplicable(true);
		    				form.setReadingScore("");
		    			}
		    			if (charterGED.getWritingScore() != null && "0".equalsIgnoreCase(charterGED.getWritingScore() ) ) {
		    				form.setWritingScoreNotApplicable(true);
		    				form.setWritingScore("");
		    			}
					}else{
						form.setRlaAfterPlacement(charterGED.isRlaAfterPlacement());
						form.setRlaBeforePlacement(charterGED.isRlaBeforePlacement());
						form.setRlaPassFailInd(charterGED.isRlaPassFailIndicator());
						form.setRlaRetest(charterGED.isRlaRetest());
						form.setRlaScore(charterGED.getRlaScore());
						form.setRlaTestDate(charterGED.getRlaTestDate());
					
						if (charterGED.getRlaScore() != null && "0".equalsIgnoreCase(charterGED.getRlaScore() ) ) {
		    				form.setRlaScoreNotApplicable(true);
		    				form.setRlaScore("");
		    			}
						form.setHasGEDEffectiveDateApplied(true);
					}
					 /**Changes for ER: JIMS200077481 ends **/
					form.setMathAfterPlacement(charterGED.isMathAfterPlacement());
					form.setMathBeforePlacement(charterGED.isMathBeforePlacement());
					form.setMathPassFailInd(charterGED.isMathPassFailIndicator());
					form.setMathRetest(charterGED.isMathRetest());
					form.setMathScore(charterGED.getMathScore());
					form.setMathTestDate(charterGED.getMathTestDate());
					form.setScienceAfterPlacement(charterGED.isScienceAfterPlacement());
					form.setScienceBeforePlacement(charterGED.isScienceBeforePlacement());
					form.setSciencePassFailInd(charterGED.isSciencePassFailIndicator());
					form.setScienceRetest(charterGED.isScienceRetest());
					form.setScienceScore(charterGED.getScienceScore());
					form.setScienceTestDate(charterGED.getScienceTestDate());
					form.setSocialStudiesAfterPlacement(charterGED.isSocialStudiesAfterPlacement());
					form.setSocialStudiesBeforePlacement(charterGED.isSocialStudiesBeforePlacement());
					form.setSocialStudiesPassFailInd(charterGED.isSocialStudiesPassFailIndicator());
					form.setSocialStudiesRetest(charterGED.isSocialStudiesRetest());
					form.setSocialStudiesScore(charterGED.getSocialStudiesScore());
					form.setSocialStudiesTestDate(charterGED.getSocialStudiesTestDate());
					form.setCharterGEDId(charterGED.getCharterGEDId());
					form.setOtherProgramCode(charterGED.getOtherProgram());
					form.setHsepProgramCodeId(charterGED.getGedProgramCodeId());
					form.setHsepProgramCode(charterGED.getGedProgramCodeDesc());
					form.setLockStatus(charterGED.isLockStatus());
					form.setPassFailInd(charterGED.isGedPassFailIndicator());
					form.setTotalIncomplete(charterGED.getTotalIncomplete());
					form.setTotalScore(charterGED.getTotalScore());
					form.setVersion(charterGED.getVersion());
	    		
	    			if (charterGED.getMathScore() != null && "0".equalsIgnoreCase(charterGED.getMathScore() ) ) {
	    				form.setMathScoreNotApplicable(true);
	    				form.setMathScore("");
	    			}
	    			if (charterGED.getScienceScore() != null && "0".equalsIgnoreCase(charterGED.getScienceScore() ) ) {
	    				form.setScienceScoreNotApplicable(true);
	    				form.setScienceScore("");
	    			}
	    			if (charterGED.getSocialStudiesScore() != null && "0".equalsIgnoreCase(charterGED.getSocialStudiesScore() ) ) {
	    				form.setSocialStudiesScoreNotApplicable(true);
	    				form.setSocialStudiesScore("");
	    			}
					break;
				}
		   }
		}
	    return aMapping.findForward( UIConstants.VIEW_SUCCESS ) ;
    }
	
	/*
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.CANCEL );
	}

	/*
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK );
	}
}
