package ui.juvenilecase.education.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.education.form.EducationCharterDetailsForm;

public class DisplayGEDSummaryAction extends JIMSBaseAction
{
	public DisplayGEDSummaryAction() 
	   {
	    
	   }
	
	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put( "button.next", "next" );
		keyMap.put( "button.cancel", "cancel" );
		keyMap.put( "button.back", "back" );
	}
	
	public ActionForward next( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    EducationCharterDetailsForm eduForm = (EducationCharterDetailsForm) aForm ;
	    eduForm.setAction(UIConstants.SUMMARY);
	    
	    eduForm.setHsepProgramCode(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GEDPROGRAM, eduForm.getHsepProgramCodeId() ));
	    /**Changes for ER: JIMS200077481 starts **/
		/*eduForm.setReadingPassFailInd(false);
		eduForm.setWritingPassFailInd(false);*/
		eduForm.setRlaPassFailInd(false);
		/* Commented for old score range.
		 * eduForm.setMathPassFailInd(false);
		eduForm.setSciencePassFailInd(false);
		eduForm.setSocialStudiesPassFailInd(false);
		eduForm.setPassFailInd(false);
		eduForm.setLockStatus(false);
		
		int readingScore = getTestScoreIntValue(eduForm.getReadingScore());
		int writingScore = getTestScoreIntValue(eduForm.getWritingScore());
		int mathScore = getTestScoreIntValue(eduForm.getMathScore());
		int scienceScore = getTestScoreIntValue(eduForm.getScienceScore());
		int socialStudiesScore = getTestScoreIntValue(eduForm.getSocialStudiesScore());

		 if (readingScore > 409) {
				eduForm.setReadingPassFailInd(true);
			}
		    if (writingScore > 409) {
				eduForm.setWritingPassFailInd(true);
			}
		    if (mathScore > 409) {
				eduForm.setMathPassFailInd(true);
			}
		    if (scienceScore > 409) {
				eduForm.setSciencePassFailInd(true);
			}
		    if (socialStudiesScore > 409) {
				eduForm.setSocialStudiesPassFailInd(true);
			}
		    int totalScore = 0;
		    totalScore = readingScore + writingScore + mathScore + scienceScore + socialStudiesScore;
		    String totScore = Integer.toString(totalScore);
		    eduForm.setTotalScore(totScore);
		    if (totalScore > 2249 && 
	            eduForm.isReadingPassFailInd() && 
	            eduForm.isWritingPassFailInd() &&
	            eduForm.isMathPassFailInd() &&
	            eduForm.isSciencePassFailInd() &&
	            eduForm.isSocialStudiesPassFailInd()) {
		    		eduForm.setPassFailInd(true);
		    		eduForm.setLockStatus(true);
			}*/
			calculateScoreAfterEffectiveDate(eduForm); // calculates the score based on new range.
		/**Changes for ER: JIMS200077481 ends **/
	    
	    return aMapping.findForward(UIConstants.SUCCESS);
    }
	
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.CANCEL );
	}

	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK );
	}
	
	private int getTestScoreIntValue(String fldName) 
	{
		int score = 0;
		if (!"".equals(fldName) ) {
			score = new Integer(fldName);
		}
		return score;
	
	}
	
	/**
	 * Changes for ER: JIMS200077481 
	 * Calculate the score with range 100-200 and with 4 categories
	 * @param eduForm
	 */
	private void calculateScoreAfterEffectiveDate(EducationCharterDetailsForm eduForm){
		// task 40495
		int rlaScore=getTestScoreIntValue(eduForm.getRlaScore());
		int mathScore = getTestScoreIntValue(eduForm.getMathScore());
		int scienceScore = getTestScoreIntValue(eduForm.getScienceScore());
		int socialStudiesScore = getTestScoreIntValue(eduForm.getSocialStudiesScore());
		String writingrlaPass = eduForm.getWritingRlaPass();
		String writingrlaFail = eduForm.getWritingRlaFail();
		String writingmathPass = eduForm.getWritingMathPass();
		String writingmathFail = eduForm.getWritingMathFail();
		String writingsciencePass = eduForm.getWritingSciencePass();
		String writingscienceFail = eduForm.getWritingScienceFail();
		String writingsocialPass = eduForm.getWritingSocialPass();
		String writingsocialFail = eduForm.getWritingSocialFail();
		String writingtotalPass = eduForm.getWritingTotalPass();
		String writingtotalFail = eduForm.getWritingTotalFail();
		String writingtotalincomplete = eduForm.getWritingTotalIncomplete();
		
		if(writingsciencePass != null && writingrlaPass.equalsIgnoreCase("on")){
			eduForm.setRlaPassFailInd(true);
		} else{
			eduForm.setRlaPassFailInd(false);
		}
		if(writingmathPass != null && writingmathPass.equalsIgnoreCase("on")){
			eduForm.setMathPassFailInd(true);
		} else{
			eduForm.setMathPassFailInd(false);
		}
		if(writingsciencePass != null && writingsciencePass.equalsIgnoreCase("on")){
			eduForm.setSciencePassFailInd(true);
		} else{
			eduForm.setSciencePassFailInd(false);
		}
		if(writingsocialPass != null && writingsocialPass.equalsIgnoreCase("on")){
			eduForm.setSocialStudiesPassFailInd(true);
		} else{
			eduForm.setSocialStudiesPassFailInd(false);
		}
		if(writingtotalPass != null &&  writingtotalPass.equalsIgnoreCase("on")){
			eduForm.setPassFailInd(true);
		} else if (writingtotalFail != null && writingtotalFail.equalsIgnoreCase("on") ){
			eduForm.setPassFailInd(false);
		} else{
			eduForm.setTotalIncomplete("1");
		}
			
		
		 /*if (rlaScore > 149) {
				eduForm.setRlaPassFailInd(true); ///set RLAPASSFAIL IND
			}
		    if (mathScore > 149) {
				eduForm.setMathPassFailInd(true);
			}
		    if (scienceScore > 149) {
				eduForm.setSciencePassFailInd(true);
			}
		    if (socialStudiesScore > 149) {
				eduForm.setSocialStudiesPassFailInd(true);
			}*/
		
		//    int totalScore = 0;
		  //  totalScore = rlaScore + mathScore + scienceScore + socialStudiesScore;
		    String totScore = eduForm.getTotalScoreScore(); 

		    eduForm.setTotalScore(totScore);
		    if (eduForm.isRlaPassFailInd() && 
	            eduForm.isMathPassFailInd() &&
	            eduForm.isSciencePassFailInd() &&
	            eduForm.isSocialStudiesPassFailInd()) {
		    	eduForm.setLockStatus(true);
			}
	}
}
